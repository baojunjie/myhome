/// <reference path="../../_references.js" />

define(["jquery", "base", "validation", "dataApi", "tip"], function ($, base, validation, dataApi, tip) {
    var checkCode = function () {
        this._init();
        this._bindEvent();
    };
    checkCode.prototype = {
        _init: function () {
            this.$mobile = $("#mobile");
            this.$btnCode = $("#btnSendCode");
            this.valid = new validation(".site-form form");
        },
        _bindEvent: function () {
            var _this = this;
            // send code
            this.$btnCode.click(function () {
                if (!_this.valid.validItem(_this.$mobile)) {
                    return false;
                }
                dataApi.user.sendCode(_this.$mobile.val(), function () {
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
            });
            $("#code").focusout(function () {
                var $this = $(this);
                $this.data(_this.valid.validList.remote.otherParams, JSON.stringify({ mobile: _this.$mobile.val() }));
            });
        }
    };
    new checkCode();
});