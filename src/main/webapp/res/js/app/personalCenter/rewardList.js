// 打赏列表
define(["paging", "masonry"], function (paging, masonry) {
    var rewardList = function () {
        this._init();
        this._bindEvent();
    };
    rewardList.prototype = {
        _init: function () {
            this.paging = new paging(".site-paging");
        },
        _bindEvent: function () {
            this.paging.event.on("success", function () {
                new masonry($(".grid")[0], {
                    itemSelector: '.grid-item',
                    columnWidth: 296
                })
            });
        }
    };
    new rewardList();
});