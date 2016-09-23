define(["jquery", "base", "tab"], function ($, base, tab) {
    var index = function () {
        this._init();
        this._bindEvent();
    };
    index.prototype = {
        _init: function () {
            this.tab = new tab(".site-tab");
            var index = base.deserialize(location.hash).index || 0;
            this.tab.selectByIndex(index);
        },
        _bindEvent: function () {
            this.tab.event.on("select", function (t, id, i) {
                location.hash = "index=" + i;
            });
        }
    };

    new index();
});