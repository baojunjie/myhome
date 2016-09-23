define(["jquery", "./preventRepeatRequest", "tip", "loginDialog"], function ($, preventRepeatRequest, tip, loginDialog) {
    // api
    var ajax = function (settings) {
        this.settings = $.extend({
            type: "get",
            url: "",
            data: {},
            success: $.noop,
            error: $.noop,
            async: true,
            prr: false,
            complete: function (jqXHR, textStatus) {
                if (textStatus === "timeout") {
                    tip("请求超时，请稍后再试");
                }
                else if (textStatus === "error" || textStatus === "parsererror" || textStatus === "abort") {
                    tip("服务器忙，请稍后再试");
                }
            }
        }, settings);
        if (this.settings.type === "get") {
            if (this.settings.url.indexOf("?") === -1) {
                this.settings.url += "?";
            }
            else {
                this.settings.url += "&";
            }
            this.settings.url += "random=" + String(Math.random()).slice(2);
        }
        // 防重复提交
        if (this.settings.prr !== false) {
            var key = this.settings.url;
            if (this.settings.data) {
                key += typeof this.settings.data === "object" ? $.param(this.settings.data) : this.settings.data;
            }
            if (preventRepeatRequest.prevent(key)) {
                return;
            }
        }
        this.settings.url = siteConfig.serverUrl + this.settings.url;
        this.settings.success = function (response) {
            response = JSON.parse(response);
            var data = response.result || response.data;
            if (response.success) {
                settings.success(data);
            }
            else {
                tip(response.msg || "服务器忙，请稍后再试").event.on("hide", function () {
                    // 未登录
                    if (response.code === 1) {
                        new loginDialog().show();
                    }
                });
            }
        };
        $.ajax(this.settings);
    };

    return ajax;
});
