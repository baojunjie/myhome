define(["jquery"], function ($) {
    var select = function (selector) {
        this.$select = $(selector);
        this.$hidden = $("[type=hidden]", this.$select);
        this.$label = $("> a", this.$select);
        this._init();
        this._bindEvent();
    };
    select.prototype = {
        _init: function () {

        },
        _bindEvent: function () {
            var _this = this;
            this.$select.on("click", "> a", function (e) {
                var $this = $(this);
                $("ul", _this.$select).toggle();
                e.stopPropagation();
            });

            this.$select.on("click", "li a", function (e) {
                var $this = $(this);
                var text = $this.data("text");
                var val = $this.data("val");
                _this.$label.text(text);
                _this.$hidden.val(val);
                _this.hide();
                e.stopPropagation();
            });

            $("body").on("click", function () {
                _this.hide();
            });
        },
        show: function () {
            $("ul", this.$select).show();
        },
        hide: function () {
            $("ul", this.$select).hide();
        }
    };
    return select;
});