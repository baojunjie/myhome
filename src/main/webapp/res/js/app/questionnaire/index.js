define(["jquery", "validation", "formAjax", "qrcode"], function ($, validation, formAjax, qrcode) {
    var index = function () {
        this._init();
        this._bindEvent();
    };
    index.prototype = {
        _init: function () {
            this.$gifts = $("#gifts");
            this.$form = $("form");
            this.valid = new validation(this.$form);
            this.formAjax = new formAjax(this.$form);
        },
        _bindEvent: function () {
            var _this = this;
            $("#btnAddGifts").click(function () {
                var tmpl = _this.$gifts.children().eq(0).clone();
                tmpl.find("input").val("");
                tmpl.find(".tip").text("");
                _this.$gifts.append(tmpl);
            });
            this.formAjax.event.on("submit", function (data) {
                $("#questionnaireShareBox").show();
            });
            $("#questionnaireShareClose").click(function () {
                $("#questionnaireShareBox").hide();
            });
        }
    };

    new index();
});