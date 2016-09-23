define([
    "jquery",
    "upload",
    "formAjax",
    "tip"
], function ($, upload, formAjax, tip) {
    var bulkUpload = function () {
        this._init();
        this._bindEvent();
    };
    bulkUpload.prototype = {
        _init: function () {
            var _this = this;
            this.formAjax = new formAjax("form");
            this.uploads = [];
            $(".site-upload").each(function () {
                var $this = $(this);
                _this.uploads.push(new upload(this, {
                    server: siteConfig.serverUrl + "/artist/upload/singleUpload.do"
                }));
            });
        },
        _bindEvent: function () {
            var _this = this;
            var loading = new tip("正在上传文件...", "body", {
                auto: false,
                clickHide: false
            });
            for (var i = 0; i < this.uploads.length; i++) {
                (function (i) {
                    _this.uploads[i].webuploader.on("uploadBeforeSend", function () {
                        loading.show();
                    });
                    _this.uploads[i].webuploader.on("uploadComplete", function () {
                        loading.hide();
                    });
                    _this.uploads[i].webuploader.on("uploadSuccess", function (file, data) {
                        loading.hide();
                        if (data.success) {

                        }
                        else {
                            tip("上传失败，请稍后再试");
                        }
                    });
                    _this.uploads[i].webuploader.on("fileQueued", function (file) {
                        _this.uploads[i].webuploader.makeThumb(file, function (error, src) {
                            if (error) {
                                tip("预览失败，请重新上传图片");
                                _this.uploads[i].webuploader.reset();
                                return false;
                            }
                            _this.uploads[i].$button.css("background-image", "url(" + src + ")");
                        }, {
                            width: 46,
                            height: 46,
                            quality: 100,
                            allowMagnify: false,
                            crop: false
                        });
                    });
                })(i);
            }
            this.formAjax.event.on("submit", function (data) {
                tip("注册完成。<br>初始密码为：手机号后6位", "body", { timeout: 8000 }).event.on("hide", function () {
                    location.href = siteConfig.serverUrl + "/teacher/batchimport.do";
                });
            });
        }
    };
    new bulkUpload();
});