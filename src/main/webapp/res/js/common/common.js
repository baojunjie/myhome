define(["jquery", "jsrender", "base", "tip", "userType"], function ($, jsrender, base, tip, userType) {
    var common = function () {
        this._init();
        this._bindEvent();
    };
    common.prototype = {
        _init: function () {
            // jsrender
            $.views.helpers({
                getWorksStatus: function (status) {
                    var list = ["全部", "未审核", "审核中", "审核通过", "未通过"];
                    return list[status];
                },
                getRole: function (role) {
                    var list = ["", "普通用户", "小画家", "幸运小朋友", "老师", "赞助商", "小画家&幸运小朋友"];
                    return list[role];
                },
                getPersonalCenterUrl: function (type, params) {
                    var url = "";
                    if (type == userType.normal) {
                        url = siteConfig.personalCenter.userUrl;
                    }
                    else if (type == userType.luckykids) {
                        url = siteConfig.personalCenter.kidsUrl;
                    }
                    else if (type == userType.teacher) {
                        url = siteConfig.personalCenter.teacherUrl;
                    }
                    else if (type == userType.painter || type == userType.artistAndLuckyKids) {
                        url = siteConfig.personalCenter.painterUrl;
                    }
                    if (params) {
                        url = url + "?" + $.param(params);
                    }
                    return url;
                },
                getGender: function (gender) {
                    return gender ? "男" : "女";
                }
            });
        },
        _bindEvent: function () {
            var _this = this;
            var $searchWrap = $("#searchWrap");
            $("#searchForm").on("submit", function () {
                if ($searchWrap.is(":hidden")) {
                    $searchWrap.fadeIn();
                    $searchWrap.find("[type=text]").focus();
                    return false;
                }
                _this.search($(this));
                return false;
            })
        },
        search: function ($form) {
            var data = base.deserialize($form.serialize());
            if (!$.trim(data.keywords)) {
                tip("请输入搜索内容");
                return false;
            }
            location.href = siteConfig.searchUrl + "#" + $.param(data);
            return true;
        }
    };
    return new common();
});