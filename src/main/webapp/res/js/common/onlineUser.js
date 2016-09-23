define(["ajax"], function (ajax) {
    var onlineUser = {
        get: function () {
            //var user = {
            //    id: 21,
            //    votenum: 1727,
            //    name: "董铃琳",
            //    school: "余姚市三七市镇中心小学",
            //    region: "余姚市",
            //    age: 7,
            //    img: "http://cdn.goujiawang.com/store/myhome/artist/thumb_1447249722760.png",
            //    male: 0,
            //    type: 0
            //};
            var user;
            ajax({
                url: "/user/info/getUserInfo.do",
                async: false,
                success: function (data) {
                    user = data;
                }
            });
            return user;
        }
    };

    return onlineUser;
});
