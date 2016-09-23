define(["jquery", "dialog", "loginDialog", "onlineUser"], function ($, dialog, loginDialog, onlineUser) {
    var painterDialog = function (selector) {
        this.$button = $(selector);
        this._bindEvent();
    };
    painterDialog.prototype = {
        _bindEvent: function () {
            var _this = this;
            this.$button.click(function () {
                //角色类型 1普通用（默认）,2画家，3受捐者，4老师，5赞助商,6同时是画家和受捐者
                //老师和赞助商点击首页banner上我要参赛按钮和爱心通道页面上我要参赛成为小画家按钮时做提示。
                var user = onlineUser.get();
                if(!user){
                    new loginDialog().show();
                    return false;
                }
                if (user.type == 4 || user.type == 5) {
                    var d = new dialog({
                        content: $("#painterDialog").html()
                    });
                    d.show();
                    return false;
                }
            });
        }
    };
    return painterDialog;
});