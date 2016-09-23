define(["jquery", "jsrender", "dialog"], function ($, jsrender, dialog) {
    var artwrokDialog = function () {
        this._init();
        this._bindEvent();
    };
    artwrokDialog.prototype = {
        _init: function () {

        },
        _bindEvent: function () {
            $("body").on("click", ".J_artwrok", function () {
                var $this = $(this);
                var d = new dialog({
                    content: $("#artwrokDialog").render($this.data()),
                    width: "80%",
                    height: "80%",
                    dialogClass: "artwrokDialog",
                    opacity: 0.7
                });
                d.show();
            });
        }
    };
    new artwrokDialog();
});