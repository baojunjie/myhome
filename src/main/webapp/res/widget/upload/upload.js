define(["base", "widget", "webuploader", "tip"], function (base, widget, webuploader, tip) {
    var upload = function (selector, settings) {
        this.$upload = $(selector);
        this.$button = this.$upload.find(".site-upload-button");
        this.$hidden = this.$upload.find("[type=hidden]");
        this.settings = $.extend({
            preview: false
        }, settings, this.$button.data());
        widget.set("upload", this, this.$upload);
        this._init();
        this._bindEvent();
    };
    upload.prototype = {
        _init: function () {
            var id = this.$button.attr("id");
            if (!id) {
                var id = base.generateId("upload");
                this.$button.attr("id", id);
            }
            if (this.settings.fileType === "image") {
                // 只允许选择图片文件。
                this.settings.accept = {
                    title: 'Images',
                    extensions: 'jpg,jpeg,png',
                    mimeTypes: 'image/*'
                }
            }
            this.webuploader = webuploader.create($.extend({
                auto: true,
                // swf文件路径
                swf: siteConfig.resUrl + '/lib/webuploader/Uploader.swf',
                // 文件接收服务端。
                server: siteConfig.serverUrl + '/works/picture/item/upload.do',
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: {
                    id: "#" + id,
                    multiple: this.settings.multiple === undefined ? false : this.settings.multiple
                },
                fileVal: "file",
                resize: false
            }, this.settings));
            if (this.$button.data("init")) {
                this.setPreview(this.$button.data("init"));
            }
        },
        _bindEvent: function () {
            var _this = this;
            this.webuploader.on("uploadBeforeSend", function (object, data, headers) {
                data._fileName = encodeURIComponent(data.name);
            });
            // 文件上传失败，显示上传出错
            this.webuploader.on("uploadError", function (file) {
                tip(base.format("文件【{0}】上传失败", file.name));
                _this.webuploader.reset();
            });
            // 文件上传成功
            this.webuploader.on("uploadSuccess", function (file, response) {
                if (_this.settings.preview) {
                    _this.setPreview(response.orginpath);
                }
                _this.$hidden.val(response.path);
                _this.webuploader.options.auto && _this.webuploader.reset();
            });
            // 文件上传完成
            this.webuploader.on("uploadComplete", function () {
                _this.valid && _this.valid();
            });
            // 文件添加进来
            this.webuploader.on("fileQueued", function (file) {
                if (file.size / 1000 < _this.settings.fileSizeMin) {
                    tip(base.format("文件【{0}】小于{1}KB", file.name, _this.settings.fileSizeMin));
                    _this.webuploader.reset();
                }
            });
        },
        setPreview: function (url) {
            url = siteConfig.fileUrl + url;
            var $preview = this.$upload.find(this.settings.previewSelector);
            if ($preview.length) {
                $preview.show().attr("src", url);
            }
            else {
                this.$button.css("background-image", "url(" + url + ")");
                this.$button.css("filter", base.format("progid:DXImageTransform.Microsoft.AlphaImageLoader(src='{0}',sizingMethod='scale');", url));
            }
        },
        onvalid: function (valid) {
            this.valid = valid;
        }
    };

    return upload;
});