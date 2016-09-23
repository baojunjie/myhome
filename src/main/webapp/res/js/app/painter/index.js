define(["jquery", "jsurl"], function ($, jsurl) {
    var sex = new jsurl().query["searchmale"] || 0;
    var $sexs = $("#sex").children().find("a");
    $sexs.removeClass("active").eq(sex).addClass("active");

    var $paging = $(".site-paging");
    if (sex > 0) {
        $paging.attr("data-params", JSON.stringify({ "searchmale": sex }));
    }
    $("#citymore li").hide();
    for(var i=0;i<=10;i++){
    	$("#citymore li").eq(i).show();
    }
    $("body").on("click", ".citymorebtn", function () {    
		 $("#citymore").css("margin-top","-70px");
		  $("#citymore li").show();
		  $("#citymore li").eq(0).hide();     
		$(".citymorebtn").hide();
		$(".citylessbtn").show();
	});
	$("body").on("click", ".citylessbtn", function () {    
		 $("#citymore").css("margin-top","0px");
		 $("#citymore li").hide();
   for(var i=0;i<=10;i++){
   	$("#citymore li").eq(i).show();
   }    
		$(".citymorebtn").show();
		$(".citylessbtn").hide();
	})
});