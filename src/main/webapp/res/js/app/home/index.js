/// <reference path="../../_references.js" />
define([
    "jquery",
    "jsrender",
    "slick",
    "ajax",
    "tab",
    "imageAdapt"
], function ($, jsrender, slick, ajax, tab, imageAdapt) {
    var index = function () {
        this._init();
        this._bindEvent();
    };
    index.prototype = {
        _init: function () {

            // 手机浏览，跳转到手机页面
//            if (/Android|iPhone|iPod/i.test(navigator.userAgent)) {
//                location.href = "http://m.haaaaaa.com";
//            }

            var _this = this;
            $("#banner ul").slick({
                autoplay: true,
                prevArrow: "#banner .control-preview",
                nextArrow: "#banner .control-next"
            });

            $(".J_worksTab").each(function () {
                _this.getWorks("init", $(".J_worksItem:eq(0)", this));
            });

            this.tabs = [];
            $(".site-tab").each(function () {
                _this.tabs.push(new tab(this));
            });
        },
        _bindEvent: function () {
            var _this = this;
            for (var j = 0; j < this.tabs.length; j++) {
                (function (j) {
                    _this.tabs[j].event.on("select", function (tabPanel, id, i) {
                        _this.getWorks("click", $("a", tabPanel.$tab), function () {
                            _this.tabs[j].select(id);
                        });
                    });
                })(j);
            }
        },
        getWorks: function (action, $item, success) {
            if ($item.data("loaded")) {
                return;
            }
            var params = {
                type: $item.data("type")
            };
            var val = $item.data("val");
            if (val) {
                params[params.type] = val;
            }
            ajax({
                url: "/works/getworklist.do",
                data: params,
                success: function (data) {
                    var $panel = $(".site-tab-panel", $item.closest(".J_worksTab"));
                    $panel.append($("#worksTmpl").render({
                        tabId: $item.parent().attr("data-tab-id"),
                        data: data,
                        action: action
                    }));
                    imageAdapt.adapt();
                    $item.data("loaded", true).attr("data-loaded", false);
                    success && success();
                }
            });
        }
    };
    new index();
});