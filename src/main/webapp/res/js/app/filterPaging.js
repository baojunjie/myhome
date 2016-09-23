define(["jquery", "widget"], function ($, widget) {
    $(".J_filter").on("click", "a", function () {
        var $this = $(this);
        var value = $this.data("value");
        var $parent = $this.closest("ul");
        var $paging = $this.closest(".site-paging");
        var paging = widget.get($paging.attr("id"));
        $parent.find("a").removeClass("active");
        $this.addClass("active");

        var params = $paging.data("params") || {};
        params[$parent.data("name")] = value;
        $paging.attr("data-params", JSON.stringify(params));

        paging.reload();
    });
});