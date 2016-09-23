define([
    "jquery",
    "slick",
    "masonry",
    "tab"
], function ($, slick, masonry, tab) {
    var pastReview = function () {
        this._init();
        this._bindEvent();
    };
    pastReview.prototype = {
        _init: function () {
            var _this = this;

            this.tab = new tab(".site-tab");

            this.slicks = [];
            this.$slicks = $('.slick');
            this.initSlick(this.$slicks.eq(0));

            //瀑布流
            this.grids = [];
            this.$grids = $(".grid");
            this.$grids.each(function () {
                _this.grids.push(new masonry(this, {
                    itemSelector: '.grid-item',
                    columnWidth: 296
                }));
            });
        },
        _bindEvent: function () {
            var _this = this;

            this.$slicks.hover(function () {
                var $this = $(this);
                $(".control", $this).show();
            }, function () {
                var $this = $(this);
                $(".control", $this).hide();
            });
            this.$slicks.trigger("mouseout");

            var masonryLoaded = false;
            var slickLoaded = false;
            this.tab.event.on("select", function (tabPanel, id, i) {
                if (i === 1) {
                    if (!masonryLoaded) {
                        _this.grids[i].layout();
                        masonryLoaded = true;
                    }
                    if (!slickLoaded) {
                        _this.initSlick(_this.$slicks.eq(i));
                        slickLoaded = true;
                    }
                }
            });
        },
        initSlick: function (item) {
            var slickItem = $("ul", item).slick({
                dots: false,
                prevArrow: $(".control-preview", item),
                nextArrow: $(".control-next", item)
            })
            this.slicks.push(slickItem);

            slickItem.on("afterChange", function (event, slick, currentSlide, nextSlide) {
                currentSlide++;
                item.find(".J_num").text(currentSlide);
            });
        }
    }
    new pastReview();
})



