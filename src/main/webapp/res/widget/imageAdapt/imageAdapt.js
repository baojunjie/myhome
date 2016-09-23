define(["jquery", "base"], function ($, base) {
    var imageAdapt = {
        adapt: function () {
            var _this = this;
            var $imgs = $(".J_imageAdapt");
            $imgs.on("load", function () {
                _this._do(this);
            });
            var count = 0;
            var time = 500;
            var inter = setInterval(function () {
                var len = 0;
                $imgs.each(function () {
                    var $this = $(this);
                    var adapted = $this.data("adapted");
                    if (!adapted) {
                        if (this.complete) {
                            _this._do(this);
                        }
                    }
                    else {
                        len++;
                    }
                });
                // 20秒或者全部加载完成
                if (count * time >= 20000 || len === $imgs.length) {
                    clearInterval(inter);
                }
                count++;
            }, time);
        },
        _do: function ($img) {
            $img = $($img);
            if ($img.data("adapted") === true) {
                return true;
            }
            var $parent = $img.parent();
            var imageWidth = $img.naturalWidth();
            var imageHeight = $img.naturalHeight();
            var width = $parent.width();
            var height = $parent.height();
            var calcWidth;
            var calcHeight;
            var scale;
            var marginLeft, marginTop;
            if (imageWidth === imageHeight) {
                calcWidth = width;
                calcHeight = width;
            }
            else if (imageWidth < imageHeight) {
                scale = imageHeight / imageWidth;
                calcWidth = width;
                calcHeight = calcWidth * scale;
            }
            else {
                scale = imageWidth / imageHeight;
                calcHeight = height;
                calcWidth = calcHeight * scale;
            }
            // 显示图片正中间位置
            marginLeft = -(calcWidth / 2 - width / 2);
            marginTop = -(calcHeight / 2 - height / 2);
            $parent.css("overflow", "hidden");
            $img.css({
                maxWidth: "none",
                maxHeight: "none",
                width: calcWidth,
                height: calcHeight,
                marginLeft: marginLeft,
                marginTop: marginTop
            });
            $img.attr("data-adapted", true);
        }
    };
    console.log("222");
    return imageAdapt;
});