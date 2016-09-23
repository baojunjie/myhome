define(["jquery", "jsrender", "base", "ajax"], function ($, jsrender, base, ajax) {
    var area = function () {
        this._init();
        this._bindEvent();
    };
    area.prototype = {
        _init: function () {
            this.$province = $("#province");
            this.$city = $("#city");
            this.$district = $("#district");
            var _this = this;
            this.getProvinces(function (p) {
                if (_this.$province.data("init")) {
                    p.id = _this.$province.data("init");
                    _this.$province.val(p.id);
                }
                _this.getCitys(p.id, "#city", function (c) {
                    if (_this.$city.data("init")) {
                        c.id = _this.$city.data("init");
                        _this.$city.val(c.id);
                    }
                    _this.getCitys(c.id, "#district", function () {
                        if (_this.$district.data("init")) {
                            _this.$district.val(_this.$district.data("init"));
                        }
                    });
                })
            });
        },
        _bindEvent: function () {
            var _this = this;
            $("#province").change(function () {
                var $this = $(this);
                _this.getCitys($this.val(), "#city", function () {
                    $("#city").change();
                });
            });
            $("#city").change(function () {
                var $this = $(this);
                _this.getCitys($this.val(), "#district");
            });
        },
        getProvinces: function (cb) {
            var _this = this;
            ajax({
                url: "/region/getprovience.do",
                success: function (data) {
                    var p = data.list[0];
                    _this.renderSelect("#province", data.list);
                    if (p) {
                        cb && cb(p);
                    }
                }
            });
        },
        getCitys: function (id, selector, cb) {
            var _this = this;
            ajax({
                url: "/region/getcity.do",
                data: { code: id },
                success: function (data) {
                    var c = data.list[0];
                    _this.renderSelect(selector, data.list);
                    if (c) {
                        cb && cb(c);
                    }
                }
            });
        },
        renderSelect: function (selector, list) {
            var tmpl = $.templates("<option value='{{:id}}'>{{:name}}</option>");
            $(selector).html(tmpl.render(list));
        }
    };

    new area();
})