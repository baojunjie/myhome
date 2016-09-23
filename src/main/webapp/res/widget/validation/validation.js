/// <reference path="../../../js/_references.js" />

define(["jquery", "base", "widget", "tip", "event"], function ($, base, widget, tip, event) {
    var validation = function (selector, settings) {
        this.$form = $(selector);
        this.settings = $.extend({
            mode: "tip", // inline
            tipSelector: ".tip",
            tipSuccessClass: "tip-success iconfont icon-duigou",
            tipErrorClass: "tip-error",
            itemSelector: ".J_validItem",
            inputSelecot: ".J_validInput"
        }, settings, this.$form.data());
        widget.set("validation", this, this.$form);
        this.event = new event(this);
        this._init();
        this._bindEvent();
    };
    validation.prototype = {
        validList: {
            format: "[data-{0}]",
            status: "valid-status", // 0:default , 1:success , 2:error
            control: "valid-control",
            defaultTip: "valid-default",
            server: "valid-server",
            require: "valid-require",
            custom: {
                selector: "valid-custom",
                regex: "valid-custom-regex",
                regexFlag: "valid-custom-regex-flag"
            },
            range: {
                selector: "valid-range",
                type: "valid-range-type",
                min: "valid-range-min",
                max: "valid-range-max"
            },
            remote: {
                selector: "valid-remote",
                url: "valid-remote-url",
                params: "valid-remote-params",
                action: "valid-remote-action",
                otherParams: "valid-remote-params-other"
            },
            equal: {
                selector: "valid-equal",
                to: "valid-equal-to"
            },
            group: "valid-group",
            mobile: "valid-mobile",
            idcard: "valid-idcard",
            numeric: "valid-numeric",
            integer: "valid-integer",
            email: "valid-email",
            name: "valid-name",
            bankcard: "valid-bankcard",
            alipay: "valid-alipay",
            requireGroup: {
                selector: "valid-require-group",
                message: "valid-require-group-message"
            }
        },
        _init: function () {
            var _this = this;
            this.$items = this.$form.find(this.settings.inputSelecot);
            this.$items.each(function () {
                var $this = $(this);
                $this.data(_this.validList.defaultTip, _this.getTip($this).text());
                _this.show($this, "error", _this.validList.server);
            });
        },
        _bindEvent: function () {
            var _this = this;
            // inline 
            if (this.settings.mode === "inline") {
                this.$form.on("focusout", this.settings.inputSelecot, function () {
                    var $this = $(this);
                    if ($this.data(_this.validList.control)) {
                        return;
                    }
                    _this.validItem($this, "focusout");
                });
            }
            this.$form.on("submit", function () {
                var v = _this.valid();
                if (_this.event.get("submit")) {
                    if (_this.event.fire("submit", v) === false) {
                        v = false;
                    }
                }
                _this.$form.attr("data-valid-result", v);
                return v;
            });
            // bind custom control event
            this.$items.filter("[data-valid-control='custom']").each(function () {
                var $this = $(this);
                var control = widget.get($this.attr("id"));
                if (control) {
                    control.onvalid(function () {
                        _this.validItem($this);
                    });
                }
            });
        },
        getTip: function ($input) {
            var $tip = $input.siblings(this.settings.tipSelector);
            if (!$tip.length) {
                $tip = $input.find(this.settings.tipSelector);
            }
            if (!$tip.length) {
                $tip = $input.parent().siblings(this.settings.tipSelector);
            }
            if (!$tip.length) {
                $tip = $input.closest(this.settings.itemSelector).find(this.settings.tipSelector);
            }
            return $tip;
        },
        valid: function () {
            var _this = this;
            var v = true;
            this.$items.each(function () {
                var $this = $(this);
                if (_this.validItem($this, "submit") === false) {
                    v = false;
                }
                if (_this.settings.mode === "tip" && v === false) {
                    return false;
                }
            });
            return v;
        },
        validItem: function ($input, action) {
            var _this = this;
            var val = $.trim($input.val());
            var status = $input.data(this.validList.status);
            var group = $input.data(this.validList.group);
            // require
            if ($input.filter(base.format(this.validList.format, this.validList.require)).length) {
                if (this.getType($input) === "checkbox") {
                    if (!$input.prop("checked")) {
                        this.show($input, "error", this.validList.require);
                        return false;
                    }
                    else {
                        this.show($input, "success");
                        return true;
                    }
                }// custom control
                else if (this.getType($input) === "custom") {
                    var $hidden = $input.find("[type=hidden]");
                    var validCount = 0;
                    $hidden.each(function () {
                        var $this = $(this);
                        if ($this.val()) {
                            validCount++;
                        }
                    });

                    if (group === "min") {
                        if (validCount > 0) {
                            this.show($input, "success");
                            return true;
                        }
                        else {
                            this.show($input, "error", this.validList.require);
                            return false;
                        }
                    }
                    else {
                        if (validCount === $hidden.length) {
                            this.show($input, "success");
                            return true;
                        }
                        else {
                            this.show($input, "error", this.validList.require);
                            return false;
                        }
                    }
                }
                else {
                    if (!val) {
                        this.show($input, "error", this.validList.require);
                        return false;
                    }
                    else {
                        this.show($input, "success");
                    }
                }
            }
            // require group
            if ($input.filter(base.format(this.validList.format, this.validList.requireGroup.selector)).length) {
                var $groups = $("[data-" + this.validList.requireGroup.selector + "=" + $input.data(this.validList.requireGroup.selector) + "]");
                var requireGroupLength = 0;
                $groups.each(function () {
                    var $this = $(this);
                    if (!$.trim($this.val())) {
                        requireGroupLength++;
                    }
                });
                if (requireGroupLength === $groups.length) {
                    this.show($groups.eq(0), "error", this.validList.requireGroup.message);
                    return false;
                }
            }

            if (!val) {
                if (status !== 2) {
                    this.show($input, "default", this.validList.defaultTip);
                }
                return;
            }
            // mobile
            if ($input.filter(base.format(this.validList.format, this.validList.mobile)).length) {
                if (!base.isMobile(val)) {
                    this.show($input, "error", this.validList.mobile);
                    return false;
                }
            }
            // email
            if ($input.filter(base.format(this.validList.format, this.validList.email)).length) {
                if (!base.isEmail(val)) {
                    this.show($input, "error", this.validList.email);
                    return false;
                }
            }
            // id card
            if ($input.filter(base.format(this.validList.format, this.validList.idcard)).length) {
                if (!base.isIdCard(val)) {
                    this.show($input, "error", this.validList.idcard);
                    return false;
                }
            }
            // numeric
            if ($input.filter(base.format(this.validList.format, this.validList.numeric)).length) {
                if (!$.isNumeric(val)) {
                    this.show($input, "error", this.validList.numeric);
                    return false;
                }
            }
            // integer
            if ($input.filter(base.format(this.validList.format, this.validList.integer)).length) {
                if (!base.isInteger(val)) {
                    this.show($input, "error", this.validList.integer);
                    return false;
                }
            }
            // name
            if ($input.filter(base.format(this.validList.format, this.validList.name)).length) {
                if (!base.isName(val)) {
                    this.show($input, "error", this.validList.name);
                    return false;
                }
            }
            // bank card
            if ($input.filter(base.format(this.validList.format, this.validList.bankcard)).length) {
                if (!base.isBankCard(val)) {
                    this.show($input, "error", this.validList.bankcard);
                    return false;
                }
            }
            // alipay
            if ($input.filter(base.format(this.validList.format, this.validList.alipay)).length) {
                if (!base.isEmail(val) && !base.isMobile(val)) {
                    this.show($input, "error", this.validList.alipay);
                    return false;
                }
            }
            // equal to 
            if ($input.filter(base.format(this.validList.format, this.validList.equal.selector)).length) {
                var equalVal = $.trim(this.$form.find($input.data(this.validList.equal.to)).val());
                if (val !== equalVal) {
                    this.show($input, "error", this.validList.equal.selector);
                    return false;
                }
            }
            // custom regex
            if ($input.filter(base.format(this.validList.format, this.validList.custom.selector)).length) {
                var reg = new RegExp($input.data(this.validList.custom.regex), $input.data(this.validList.custom.regexFlag));
                if (!reg.test(val)) {
                    this.show($input, "error", this.validList.custom.selector);
                    return false;
                }
            }
            // range
            if ($input.filter(base.format(this.validList.format, this.validList.range.selector)).length) {
                var len;
                if ($input.data(this.validList.range.type) === "string") {
                    len = val.replace(/[^\x00-\xff]/g, "**").length;
                }
                else if ($input.data(this.validList.range.type) === "number") {
                    len = Number(val);
                }
                else {
                    len = val.length;
                }
                if (len < $input.data(this.validList.range.min) || len > $input.data(this.validList.range.max)) {
                    this.show($input, "error", this.validList.range.selector);
                    return false;
                }
            }
            // remote
            if ($input.filter(base.format(this.validList.format, this.validList.remote.selector)).length) {
                var v;
                var params = $input.data(this.validList.remote.otherParams);
                params = params ? JSON.parse(params) : {}
                params[$input.data(this.validList.remote.params)] = val;
                ajax({
                    type: "post",
                    url: $input.data(this.validList.remote.url),
                    data: params,
                    async: false,
                    success: function (data) {
                        if (!data) {
                            _this.show($input, "error", _this.validList.remote.selector);
                            v = false;
                        }
                        else {
                            v = true;
                        }
                    }
                });
                if (!v) {
                    return false;
                }
            }
            this.show($input, "success");
            return true;
        },
        // mode : success , error
        // type : require, server..
        show: function ($input, mode, type) {
            type = type || "error";
            var message = $input.data(type);
            if (this.settings.mode === "inline") {
                var $tip = this.getTip($input);
                if (mode === "success") {
                    $tip.removeClass(this.settings.tipErrorClass).addClass(this.settings.tipSuccessClass);
                    $tip.text("");
                }
                else if (mode === "error") {
                    if (!message) {
                        return;
                    }
                    $tip.removeClass(this.settings.tipSuccessClass).addClass(this.settings.tipErrorClass);
                    $tip.text(message);
                }
                else if (mode === "default") {
                    $tip.removeClass(this.settings.tipSuccessClass + " " + this.settings.tipErrorClass);
                    $tip.text(message);
                }
            }
            else {
                if (!message) {
                    return;
                }
                if (mode === "error") {
                    tip(message, this.$form);
                }
            }
        },
        getType: function ($input) {
            var el = $input[0];
            var control = $input.data(this.validList.control);
            if (control) {
                return control;
            }
            if ($input.attr("type") === "checkbox") {
                return "checkbox";
            }
            return "text";
        }
    };

    return validation;
});