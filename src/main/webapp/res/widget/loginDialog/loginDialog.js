define(["jquery", "jsrender", "dialog", "placeholder", "validation", "formAjax"],
    function ($, jsrender, dialog, placeholder, validation, formAjax) {
        var loginDialog = function () {
            this.dialog = new dialog({
                content: $("#loginDialog").html()
            });
            this.valid = new validation(this.dialog.$dialog.find("form"));
            new formAjax(this.dialog.$dialog.find("form"));
            $('input, textarea', this.dialog.$dialog).placeholder();
        };
        loginDialog.prototype = {
            show: function () {
                this.dialog.show();
            }
        };
        return loginDialog;
    });