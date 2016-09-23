define(["jquery", "base", "ajax", "dataApi", "tip"], function ($, base, ajax, dataApi, tip) {
    var works = function () {
        this._init();
        this._bindEvent();
    };
    works.prototype = {
        _init: function () {
        },
        _bindEvent: function () {
           
            $("body").on("click", ".J_vote", function () {
                var $this = $(this);
                var amount = $this.data("amount");
                ajax({
                    url: "/voting/interceptorAdd.do",
                    data: { worksid: $this.data("id") },
                    success: function (result) {
                        var t = tip(result ? "点赞成功" : "已点赞");
                        if (result) {
                            amount++;
                        }
                        t.event.on("hide", function () {
                            $this.data("amount", amount);
                            var tmpl = $this.data("tmpl");
                            if (tmpl) {
                                $this.text(tmpl.replace("#vote", amount));
                            }
                            var $voteWrap = $this.closest(".J_voteWrap");
                            if ($voteWrap.length) {
                                $voteWrap.find(".J_voteText").text(amount);
                            }
                        });
                    }
                });
            });

            // 删除
            $("body").on("click", ".J_deleteWorks", function () {
                var $this = $(this);
                var name = $this.data("name");
                var id = $this.data("id");
                if (confirm(base.format("确定删除作品'{0}'吗?", name))) {
                    ajax({
                        type: "post",
                        url: "/works/deleteworks.do",
                        data: { worksid: id },
                        success: function () {
                            tip("删除成功").event.on("hide", function () {
                                location.reload();
                            });
                        }
                    });
                }
            });

            // 提交审核
            $("body").on("click", ".J_submitWorks", function () {
                var $this = $(this);
                var name = $this.data("name");
                var id = $this.data("id");
                if (confirm(base.format("确定提交作品'{0}'吗?", name))) {
                    ajax({
                        type: "post",
                        url: "/game/worksexamine.do",
                        data: { worksid: id },
                        success: function (url) {
                            tip("提交成功").event.on("hide", function () {
                                location.href = url;
                            });
                        }
                    });
                }
            });

            // 个人中心作品项操作
            $("body").on("mouseover", ".J_works", function () {
                var $this = $(this);
                $this.find(".operation").show();
            });

            $("body").on("mouseout", ".J_works", function () {
                var $this = $(this);
                $this.find(".operation").hide();
            });
            $("body").on("mouseover", ".J_worksauthed", function () {
                var $this = $(this);
                $this.find(".operation").show();
            });

            $("body").on("mouseout", ".J_worksauthed", function () {
                var $this = $(this);
                $this.find(".operation").hide();
            });
        }
    };

    new works();
})