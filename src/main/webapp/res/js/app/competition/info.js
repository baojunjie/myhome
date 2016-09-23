define(["jquery"], function ($) {
    $("#btnRead").click(function () {
        $("#read").show();
    });
    $("#btnBack").click(function () {
        $("#read").hide();
    });
})