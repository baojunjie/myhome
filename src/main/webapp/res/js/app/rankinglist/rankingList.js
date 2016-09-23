    define(["jquery", "base", "./listPage", "imageAdaptHeight"], function ($, base, listPage, imageAdaptHeight) {

    imageAdaptHeight.adapt();
    var rankingList = function () {
        this._init();
        this._bindEvent();
    };
    rankingList.prototype = {
        _init: function () {
            imageAdaptHeight.adapt();
            this.listPage1 = new listPage($(".J_listPage").eq(0));
            this.listPage2 = new listPage($(".J_listPage").eq(1));

            var $levels = $("a", "#timeline");
            var currenttype = 1;
            for (var i = 0; i < $levels.length; i++) {
                if ($levels[i].className == "active") {
                    currenttype = (i + 1 - $levels.length) * -1 + 1;
                }
            }
            if (currenttype == 10) {
                $(".awardsCategories a").show();
            } else {
                if (currenttype == 6) {
                    $(".awardsCategories a").eq(1).attr("data-awards", "3").show();
                    $(".awardsCategories a").eq(2).attr("data-awards", "2").show();
                    $(".awardsCategories a").eq(3).attr("data-awards", "1").show();
                } else {
                    if (currenttype == 9) {
                        $(".awardsCategories a").eq(1).attr("data-awards", "6").show();
                        $(".awardsCategories a").eq(2).attr("data-awards", "5").show();
                        $(".awardsCategories a").eq(3).attr("data-awards", "4").show();
                    }
                }
            }
                
            
            
        },
        _bindEvent: function () {
            var _this = this;

            var $levels = $("a", "#timeline");
            var currenttype = 1;
            for (var i = 0; i < $levels.length; i++) {
                if ($levels[i].className == "active") {
                    currenttype = (i + 1 - $levels.length) * -1 + 1;
                }
            }
            $("#timeline").on("click", "a", function () {
                var $this = $(this);                
                var type = ($levels.index($this) + 1 - $levels.length) * -1 + 1;
                if (type > currenttype) { return false};
                $(".stage").text($this.attr("data-title"));
                $(".awardsCategories").find("a").removeClass("on");
                $(".awardsCategories a").hide();
                if (type == 10) {
                    $(".awardsCategories a").show();
                } else {
                    if (type == 6) {
                        $(".awardsCategories a").eq(1).attr("data-awards", "3").show();
                        $(".awardsCategories a").eq(2).attr("data-awards", "2").show();
                        $(".awardsCategories a").eq(3).attr("data-awards", "1").show();
                    } else {
                        if (type == 9) {
                            $(".awardsCategories a").eq(1).attr("data-awards", "6").show();
                            $(".awardsCategories a").eq(2).attr("data-awards", "5").show();
                            $(".awardsCategories a").eq(3).attr("data-awards", "4").show();
                        }
                    }
                }
                $levels.removeClass("active");
                $this.addClass("active");
                var hashs = base.deserialize(location.hash);
                hashs.type = type;
                hashs.pageType = 3;
                hashs.index = hashs.pageIndex = hashs.size = hashs.awards = hashs.pageSize = ""; 
                location.hash = "#" + $.param(hashs);
                console.log(location.hash);
            });

            /*年龄筛选*/
            $("#screenWorks").on("click", "a", function () {
                var $this = $(this);
                var age = $(this).attr("dataVal");
                $this.parent("dd").siblings().find("a").removeClass("on");
                $this.addClass("on");

                var worksLen = $("#screenWorks a");
                var worksLenNum;
                for (var j = 0; j < worksLen.length; j = j + 1) {
                    if (worksLen.eq(j).hasClass("on")) {
                        worksLenNum = j;
                    }
                }
                $("#screenArtist a").removeClass("on");
                $("#screenArtist a").eq(worksLenNum).addClass("on");
                var hashs = base.deserialize(location.hash);
                console.log(hashs);
                hashs.age = age;
                hashs.pageType = 3;
                hashs.index = hashs.pageIndex = hashs.size = hashs.pageSize = "";
                location.hash = "#" + $.param(hashs);
            });

            $("#screenArtist").on("click", "a", function () {
                var $this = $(this);
                var age = $(this).attr("dataVal");
                $this.parent("dd").siblings().find("a").removeClass("on");
                $this.addClass("on");

                var artistLen = $("#screenArtist a");
                var artistLenNum;
                for (var k = 0; k < artistLen.length; k = k + 1) {
                    if (artistLen.eq(k).hasClass("on")) {
                        artistLenNum = k;
                    }
                }
                $("#screenWorks a").removeClass("on");
                $("#screenWorks a").eq(artistLenNum).addClass("on");
                var hashs = base.deserialize(location.hash);
                hashs.age = age;
                hashs.pageType = 3;
                hashs.index = hashs.pageIndex = hashs.size = hashs.pageSize = "";
                location.hash = "#" + $.param(hashs);
            })

            function keyDown(e) {
                var keycode = e.which || window.event;
                var hashs = base.deserialize(location.hash);
                hashs.pageType = 3;
                hashs.type = $("newType").val();
                if (keycode == 116) {
                    hashs.index = hashs.pageIndex = hashs.size = hashs.pageSize = "";
                    location.hash = "#" + $.param(hashs);
                }
            }
            document.onkeydown = keyDown;
            /*奖项筛选*/
            $(".awardsCategories").on("click", "a", function () {
                var $this = $(this);
                var awards = $(this).attr("data-awards");
                $(".awardsCategories").find("a").removeClass("on");
                $this.addClass("on");

                var hashs = base.deserialize(location.hash);
                console.log(hashs);
                hashs.awards = awards;
                hashs.pageType = 3;
                hashs.index = hashs.pageIndex = hashs.size = hashs.pageSize = "";
                location.hash = "#" + $.param(hashs);
            });
            /*筛选 end*/
            $(window).on("hashchange", function () {
                var hashs = base.deserialize(location.hash);
                var pageType = hashs.pageType || 3;
                if (pageType == 3) {
                    _this.listPage1.load();
                    _this.listPage2.load();
                }
                else if (pageType == 1) {
                    _this.listPage1.load();
                }
                else if (pageType == 2) {
                    _this.listPage2.load();
                }
            });
        }
    };
    new rankingList();
});