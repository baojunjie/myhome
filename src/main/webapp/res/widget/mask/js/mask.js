define(["jquery"], function ($) {
    // mask
    var mask = function (wrapSelector, opacity) {
        this.wrapSelector = wrapSelector || "body";
        this._init(opacity);
    };
    mask.prototype = {
        _init: function (opacity) {
            this.$mask = $("<div class='site-mask'>");
            if (opacity) {
                this.$mask.css("opacity", opacity);
                this.$mask.css("-ms-filter", "progid:DXImageTransform.Microsoft.Alpha(Opacity=" + opacity * 100 + ")");
            }
            this.$mask.appendTo(this.wrapSelector);
        },
        show: function () {
            this.$mask.show();
        },
        hide: function () {
            this.$mask.hide();
        },
        remove: function () {
            this.$mask.remove();
        }
    };
    return mask;
});