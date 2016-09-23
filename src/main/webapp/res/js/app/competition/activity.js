define(["jquery", "slick", "countdown"], function ($, slick, countdown) {
    //$('#clock').countdown('2015/11/01', function (event) {
    //    var $this = $(this);
    //    var $items = $this.find(".digit");
    //    $items.eq(0).text(event.strftime('%d'));
    //    $items.eq(1).text(event.strftime('%H'));
    //    $items.eq(2).text(event.strftime('%M'));
    //    $items.eq(3).text(event.strftime('%S'));
    //});
    $("#banner ul").slick({
        autoplay: true,
        prevArrow: "#banner .control-preview",
        nextArrow: "#banner .control-next"
    });
})