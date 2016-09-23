define(["jquery"], function ($) {
    $(".J_save").click(function () {
        var $this = $(this);
        $this.closest("form").submit();
    });
});