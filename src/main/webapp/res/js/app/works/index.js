define(["tab", "jsurl"], function (tab, jsurl) {
    var works = function () {
        this._init();
        this._bindEvent();
    };
    works.prototype = {
        _init: function () {
            this.tab = new tab(".site-tab");
            var index = new jsurl().query["index"] || 0;
            this.tab.selectByIndex(index);
            $("#citymore li").hide();
            for(var i=0;i<=10;i++){
            	$("#citymore li").eq(i).show();
            }
            
        },
        _bindEvent: function () { 
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
        }
    };

    new works();
})