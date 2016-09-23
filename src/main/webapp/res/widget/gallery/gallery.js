/// <reference path="../../js/_references.js" />
define(["jquery", "jsrender", "base", "ajax", "widget", "tip", "imageAdapt"], function ($, jsrender, base, ajax, widget, tip, imageAdapt) {
    var gallery = function (selector, settings) {
        this.$wrap = $(selector);
        this.settings = $.extend({
            imageSelector: ".site-gallery-image"
        }, settings);
        widget.set("gallery", this, this.$wrap);
        this._init();
        this._bindEvent();
    };
    gallery.prototype = {
        lastImageActiveIndex: 0,
        _init: function () {
            this.$gallery = $($("#galleryTmpl").html());
            this.$prev = this.$gallery.find(".control-prev");
            this.$next = this.$gallery.find(".control-next");
            $("body").append(this.$gallery);
        },
        _bindEvent: function () {
            var _this = this;
            this.$wrap.on("click", this.settings.imageSelector, function () {
                var $this = $(this);
                var id = $this.data("id");
                var i = _this.$wrap.find(_this.settings.imageSelector).filter("img").index($this);
                _this.show(i, id);
                return false;
            });
            this.$gallery.on("click", ".control", function () {
                var $this = $(this);
                var action = $this.data("action");
                var i;
                if (action === "prev") {
                    i = _this.lastImageActiveIndex - 1;
                }
                else if (action === "next") {
                    i = _this.lastImageActiveIndex + 1;
                }
                var id = _this.$wrap.find(_this.settings.imageSelector).filter("img").eq(i).data("id");
                _this.show(i, id);
            });
            this.$gallery.on("click", ".site-gallery-close", function () {
                _this.hide();
            });

            // 添加评论
            this.$gallery.on("submit", "form", function () {
                var $this = $(this);
                var data = $this.serialize();
                var p = base.deserialize(data);
                if (!p.words) {
                    tip("请输入评论内容");
                    return false;
                }
                if (p.words.length > 200) {
                    tip("评论内容最长200字");
                    return false;
                }
                ajax({
                    url: "/comment/interceptorAdd.do",
                    data: data,
                    success: function (response) {
                        _this.$gallery.find(".J_reviewList").prepend($("#reviewTmpl").render(response.comment));
                        $this[0].reset();
                    }
                });
                return false;
            });
        },
        detectHandle: function () {
            var imageLength = this.$wrap.find(this.settings.imageSelector).filter("img").length;
            if (imageLength === 1) {
                return;
            }
            if (this.lastImageActiveIndex === 0) {
                this.$prev.hide();
                this.$next.show();
            }
            else if (this.lastImageActiveIndex === imageLength - 1) {
                this.$prev.show();
                this.$next.hide();
            }
            else {
                this.$prev.show();
                this.$next.show();
            }
        },
        show: function (i, id) {
            var _this = this;
            ajax({
                url: "/works/worksinfo.do",
                data: { worksid: id },
                success: function (works) {
                    if (!works) {
                        tip("获取作品信息失败，请稍后再试");
                        _this.hide();
                        return;
                    }

                    _this.lastImageActiveIndex = i;
                    _this.detectHandle();
                    _this.$gallery.find(".site-gallery-container").html("");
                    $("body").css("overflow", "hidden");

                    _this.$gallery.find(".site-gallery-container").html($("#galleryContentTmpl").render(works));
                    _this.lastImageActiveIndex = i;
                    _this.$gallery.height($(window).height());
                    _this.$gallery.show();
                    imageAdapt.adapt();

                    ajax({
                        url: "/comment/getlist.do",
                        data: { worksid: id },
                        success: function (data) {
                            _this.$gallery.find(".J_reviewList").html($("#reviewTmpl").render(data.list));
                            imageAdapt.adapt();
                        }
                    });
                }
            });
        },
        hide: function () {
            $("body").css("overflow", "auto");
            this.$gallery.hide();
        }
    };
    return gallery;
});