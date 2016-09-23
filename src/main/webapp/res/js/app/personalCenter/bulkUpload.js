define([
    "jquery",
    "slick",
    "upload",
    "tip"
], function ($, slick, upload, tip) {
    var bulkUpload = function () {
        this._init();
        this._bindEvent();
    };
    bulkUpload.prototype = {
        _init: function () {
            $("#process ul").slick({
                prevArrow: "#process .control-preview",
                nextArrow: "#process .control-next"
            });

            this.upload = new upload(".site-upload", {
                server: siteConfig.serverUrl + '/artist/upload/Import.do',
                accept: {
                    title: "Excel",
                    extensions: "xls,xlsx",
                    mimeTypes: "application/vnd.ms-excel"
                }
            });
        },
        _bindEvent: function () {
            var _this = this;
            this.upload.webuploader.on("uploadSuccess", function (file, data) {
                if (!data.success) {
                    tip(data.msg || "服务器忙，请稍后再试");
                    return;
                }
                if (data.msgs && data.msgs.length > 0) {
                    var msg = data.msgs.join(" <br> ");
                    tip(msg, "body", { timeout: 8000 });
                }
                else {
                    location.href = siteConfig.serverUrl + data.path;
                }
            });
        }
    };
    new bulkUpload();
});