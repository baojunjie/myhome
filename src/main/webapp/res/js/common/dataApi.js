define(["ajax"], function (ajax) {
    var dataApi = {};
    dataApi.user = {};
    // 发送验证码
    dataApi.user.sendCode = function (mobile, success) {
        ajax({
            url: "/login/authentication/check.do",
            prr: true,
            data: { mobile: mobile },
            success: success
        });
    }
    return dataApi;
});