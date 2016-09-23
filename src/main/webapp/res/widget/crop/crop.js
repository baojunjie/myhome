define([
    "dialog",
    "upload",
    "jcrop",
    "tip",
    "ajax"
], function (dialog, upload, jcrop, tip, ajax) {
    var crop = function (settings) {
        this.settings = $.extend({
            cropWidth: 200,
            cropHeight: 200,
            previewSelector: ""
        }, settings);
        this._init();
        this._bindEvent();
    };
    crop.prototype = {
        _init: function () {
            this.dialog = new dialog({
                content: $("#cropTmpl").html()
            });
            this.$crop = this.dialog.$dialog;
            this.$main = this.$crop.find(".main");
            this.$upload = this.$main.find(".site-upload");
            this.$img = this.$main.find("img");
            this.$preview = this.$crop.find(".preview img");
            this.upload = new upload(this.$upload, {
                auto: false,
                server: siteConfig.serverUrl + "/works/picture/item/cut.do"
            });

            this.x1 = 0;
            this.x2 = 0;
            this.y1 = 0;
            this.y2 = 0;
            this.scale = 0;
        },
        _bindEvent: function () {
            var _this = this;
            this.upload.webuploader.on("fileQueued", function (file) {
                _this.upload.webuploader.makeThumb(file, function (error, src) {
                    if (error) {
                        tip("预览失败，请重新上传图片", _this.$main);
                        _this.upload.webuploader.reset();
                        return false;
                    }
                    if (file._info.width < _this.settings.cropWidth) {
                        tip("图片宽度小于200像素", _this.$main);
                        _this.upload.webuploader.reset();
                        return false;
                    }
                    if (file._info.height < _this.settings.cropHeight) {
                        tip("图片高度小于200像素", _this.$main);
                        _this.upload.webuploader.reset();
                        return false;
                    }
                    //_this.$upload.hide();
                    _this.$upload.addClass("webuploader-element-invisible");
                    _this.$img.attr("src", src).show();
                    _this.$preview.attr("src", src).show();

                    var panelSize = 400;
                    var imageSize = (file._info.height > file._info.width ? file._info.height : file._info.width);
                    if (imageSize < panelSize) {
                        panelSize = imageSize;
                    }

                    _this.scale = imageSize / panelSize;
                    _this.imageWidth = file._info.width;

                    var jcropLoaded = false;
                    _this.$img.on("load", function () {
                        initJcrop();
                    });
                    initJcrop();

                    // crop
                    function initJcrop() {
                        if (jcropLoaded) {
                            return;
                        }
                        _this.x1 = _this.$img.width() / 2 - _this.settings.cropWidth / 2;
                        _this.x2 = _this.$img.width() / 2 + _this.settings.cropWidth / 2;
                        _this.y1 = _this.$img.height() / 2 - _this.settings.cropHeight / 2;
                        _this.y2 = _this.$img.height() / 2 + _this.settings.cropWidth / 2;

                        _this.$img.parent().Jcrop({
                            aspectRatio: 1,
                            //allowResize: false,
                            allowSelect: false,
                            minSize: [_this.settings.cropWidth, _this.settings.cropHeight],
                            //maxSize: [_this.settings.cropWidth, _this.settings.cropHeight],
                            setSelect: [_this.x1, _this.y1, _this.x2, _this.y2],
                            onChange: $.proxy(_this.showPreview, _this),
                            onSelect: $.proxy(_this.showPreview, _this)
                        });
                        jcropLoaded = true;
                    }
                }, {
                    width: 400,
                    height: 400,
                    quality: 100,
                    allowMagnify: false,
                    crop: false
                });
            });

            // 确定按钮
            this.dialog.event.on("yes", function () {
                if (!_this.upload.webuploader.getFiles().length) {
                    tip("请上传图片", _this.$main);
                    return false;
                }
                _this.upload.webuploader.option("formData", {
                    x: Math.round(_this.x1 * _this.scale),
                    y: Math.round(_this.y1 * _this.scale),
                    w: Math.round((_this.x2 - _this.x1) * _this.scale),
                    h: Math.round((_this.x2 - _this.x1) * _this.scale),
                    width: _this.imageWidth
                });
                _this.upload.webuploader.upload();
            });

            _this.upload.webuploader.on("uploadSuccess", function (file, response) {
                tip("上传成功", _this.$main).event.on("hide", function () {
                    _this.dialog.close();
                    $(".J_avatar").attr("src", response.path);
                });
            });
        },
        showPreview: function (coords) {
            this.x1 = coords.x;
            this.x2 = coords.x2;
            this.y1 = coords.y;
            this.y2 = coords.y2;
            var rx = 100 / coords.w;
            var ry = 100 / coords.h;
            this.$preview.css({
                width: Math.round(rx * this.$img.width()) + 'px',
                height: Math.round(ry * this.$img.height()) + 'px',
                marginLeft: '-' + Math.round(rx * coords.x) + 'px',
                marginTop: '-' + Math.round(ry * coords.y) + 'px'
            });
        },
        show: function () {
            this.dialog.show();
        }
    };
    return crop;
});