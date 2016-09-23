// 个人中心公用脚本
define(["jquery", "ajax", "tip"], function ($, ajax, tip) {
    var main = function () {
        this._bindEvent();
    };
    main.prototype = {
        getQuestionnaireState: function (cb) {
            var _this = this;
            if (!this.questionnaireState) {
                ajax({
                    url: "/user/info/getQuestionnaireState.do",
                    async: false,
                    success: function (data) {
                        _this.questionnaireState = data;
                    }
                });
            }
            return this.questionnaireState;
        },
        _bindEvent: function () {
            var _this = this;
            // 问卷
            $(".J_questionnaire").on("click", "a", function () {
                var $this = $(this);
                var i = $this.parent().index();
                if (i > 3) {
                    return true;
                }
                var status = _this.getQuestionnaireState();
                if (i === 0) {
                    if (status.haveToDecorate) {
                        tip("您已经参加过此问卷调查");
                        return false;
                    }
                }
                if (i === 1) {
                    if (status.wantToDecorate) {
                        tip("您已经参加过此问卷调查");
                        return false;
                    }
                }
                if (i === 2) {
                    if (status.wantBuyHouse) {
                        tip("您已经参加过此问卷调查");
                        return false;
                    }
                }
                if (i === 3) {
                    if (status.myBalcony) {
                        tip("您已经参加过此问卷调查");
                        return false;
                    }
                }
            });
        }
    };
    new main();
});