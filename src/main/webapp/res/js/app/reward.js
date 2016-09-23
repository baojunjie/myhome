// 打赏脚本 
define([
    "jquery",
    "base",
    "dialog",
    "ajax",
    "validation",
    "formAjax",
    "loginDialog"
], function ($, base, dialog, ajax, validation, formAjax, loginDialog) {
    var reward = function () {
        this._init();
        this._bindEvent();
    };
    reward.prototype = {
        _init: function () {
        },
        _bindEvent: function () {
            var _this = this;
            // 打赏
            $(".J_reward").click(function () {
                var d = new dialog({
                    content: $("#rewardTmpl").html()
                });
                var params = base.deserialize(d.$dialog.find("form").serialize());
                if (!params["userFrom.id"]) {
                    new loginDialog().show();
                    return;
                }
                d.show();
                new validation(d.$dialog.find("form"));
                new formAjax(d.$dialog.find("form")).event.on("submit", function (data) {
                    d.close();
                    var infoDialog = new dialog({
                        dialogClass: "rewardInfoDialog",
                        content: $("#rewardInfoTmpl").render(data)
                    });
                    infoDialog.show();

                    infoDialog.event.on("yes", function () {
                        ajax({
                            url: "/recommendChildren/addRecommendChildren.do",
                            data: d.$dialog.find("form").serialize(),
                            success: function () {
                                infoDialog.close();
                            }
                        });
                    });
                });
            });
        }
    };
    new reward();
});