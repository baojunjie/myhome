/// <reference path="../../_references.js" />

define([
    "jquery",
    "base",
    "dataApi",
    "tip",
    "validation",
    "formAjax"
], function ($, base, dataApi, tip, validation, formAjax) {
    var activation = function () {
        this._init();
        this._bindEvent();
    };
    activation.prototype = {
        _init: function () {
            this.mobile = $("#mobile").val();
            this.$btnCode = $("#btnSendCode");
            this.valid = new validation("form");
            this.formAjax = new formAjax("form");

            this.sendCode();
        },
        _bindEvent: function () {
            var _this = this;
            // send code
            this.$btnCode.click(function () {
                _this.sendCode();
            });

            this.formAjax.event.on("submit", function (data) {
                location.href = data.url;
            });
        },
        sendCode: function () {
            var _this = this;
            if (!this.mobile) {
                tip("请输入手机号");
                return;
            }
            dataApi.user.sendCode(this.mobile, function () {
                tip("验证码发送成功，请查看手机");

                var defaultText = _this.$btnCode.val();
                _this.$btnCode.prop("disabled", true);
                _this.$btnCode.addClass("site-button-disabled");
                var s = 60;
                var interval = setInterval(function () {
                    if (s === 1) {
                        clearInterval(interval);
                        _this.$btnCode.prop("disabled", false);
                        _this.$btnCode.removeClass("site-button-disabled");
                        _this.$btnCode.val(defaultText);
                    }
                    else {
                        _this.$btnCode.val(s + "秒后获取");
                        s--;
                    }
                }, 1000);
            });
        }
    };
    new activation();
});