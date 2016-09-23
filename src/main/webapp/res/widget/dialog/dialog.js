define(["jquery", "mask", "event"], function ($, mask, event) {
    var dialog = function (settings) {
        this.settings = $.extend({
            content: "",
            width: "auto",
            height: "auto",
            dialogClass: "",
            opacity: ""
        }, settings);
        this._init();
        this._bindEvent();
    };
    dialog.prototype = {
        _init: function () {
            this.$dialog = $("<div class='site-dialog'></div>");
            this.$dialog.html(this.settings.content);
            this.$dialog.appendTo("body");
            this.$dialog.width(this.settings.width);
            this.$dialog.height(this.settings.height);
            this.$dialog.addClass(this.settings.dialogClass);
            this.mask = new mask("body", this.settings.opacity);
            this.event = new event(this);
        },
        _bindEvent: function () {
            var _this = this;
            this.$dialog.on("click", ".J_close", function () {
                _this.close();
            });
            this.$dialog.on("click", ".J_yes", function () {
                _this.event.fire("yes");
            });
        },
        show: function () {
            this.$dialog.css({
                "margin-left": -(this.$dialog.outerWidth() / 2),
                "margin-top": -(this.$dialog.outerHeight() / 2)
            });
            this.mask.show();
            this.$dialog.show();
            this.$dialog.css({
                "margin-left": -(this.$dialog.outerWidth() / 2),
                "margin-top": -(this.$dialog.outerHeight() / 2)
            });
        },
        close: function () {
            this.$dialog.remove();
            this.mask.remove();
        }
    };
    return dialog;
})