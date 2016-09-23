define(["jquery", "jsrender", "base", "ajax", "validation", "../area"], function ($, jsrender, base, ajax, validation, area) {
    var join = function () {
        this._init();
        this._bindEvent();
    };
    join.prototype = {
        _init: function () {
            var _this = this;
            this.$category = $("#category");
            this.$birthday = $("#birthday");
            this.$school = $("#school");
            this.$institution = $("#institution");
            this.valid = new validation("form");

            // 作品类别
            ajax({
                url: "/tag/getTagList.do",
                success: function (data) {
                    _this.renderSelect("#category", data.list);
                    if (_this.$category.data("init")) {
                        _this.$category.val(_this.$category.data("init"));
                    }
                }
            });
        },
        _bindEvent: function () {
            var _this = this;
            this.$school.on("focusout", function () {
                _this.validSchool();
            })
            this.valid.event.on("submit", function () {
                return _this.validSchool();
            })
        },
        validSchool: function () {
            var birthday = this.$birthday.val();
            var result = true;
            if (birthday) {
                var now = new Date();
                birthday = new Date(birthday);
                var year = now.getFullYear() - birthday.getFullYear();
                if (year < 7) {
                    result = false;
                }
                else if(year === 0){
                	if (now.getMonth() > birthday.getMonth()) {
                        result = false;
                    }
                	else if(now.getMonth() === birthday.getMonth()){
                		if (now.getDate() < birthday.getDate()) {
                            result = false;
                        }
                	}
                }
                var school = $.trim(this.$school.val());
                var institution = $.trim(this.$institution.val());
                // 小于7岁
                if (!result) {
                    this.valid.show(this.$school, "default", this.valid.validList.defaultTip);
                    this.$school.attr("data-valid-status", 0);
                }
                else {
                    if (!school && !institution) {
                        this.valid.show(this.$school, "error", "valid-age");
                        this.$school.attr("data-valid-status", 2);
                        return false;
                    }
                }
            }
            return true;
        },
        renderSelect: function (selector, list) {
            var tmpl = $.templates("<option value='{{:id}}'>{{:name}}</option>");
            $(selector).html(tmpl.render(list));
        }
    };

    new join();
})