/// <reference path="../../_references.js" />
define([
    "jquery",
    "jsurl",
    "base",
    "tab",
    "paging",
    "common"
], function ($, jsurl, base, tab, paging, common) {
    var index = function () {
        this._init();
        this._bindEvent();
    };
    index.prototype = {
        _init: function () {
            this.$text = $("#text");
            this.tab = new tab(".site-tab");
            this.worksPaging = new paging("#worksPaging", {
                url: "/search/works.do",
                autoLoad: false,
                params: {
                    size: 20
                }
            });
            this.painterPaging = new paging("#painterPaging", {
                url: "/search/artist.do",
                autoLoad: false,
                params: {
                    size: 20
                }
            });
            this.search();
        },
        _bindEvent: function () {
            var _this = this;
            $("#searchMainForm").on("submit", function () {
                common.search($(this));
                return false;
            });

            this.worksPaging.event.on("success", function (data) {
                $("#worksLabel").text(data.total||0);
                if (!data.total) {
                    _this.tab.selectByIndex(0);
                }
            });
            this.painterPaging.event.on("success", function (data) {
            		$("#painterLabel").text(data.total||0);
            		if (!data.total) {
            			_this.tab.selectByIndex(0);
            		}
            		
            });

            $(window).on("hashchange", function () {
                _this.search();
            });
        },
        search: function () {
            var urls = base.deserialize(new jsurl().hash);
            var keywords = urls["keywords"];
//            console.log(keywords);
            this.$text.val(keywords);
            this.worksPaging.settings.params.keywords = keywords;
            this.painterPaging.settings.params.keywords = keywords;
            this.worksPaging.clear();
            this.painterPaging.clear();
            $("#worksLabel").text(0);
        	$("#painterLabel").text(0);
            this.worksPaging.getList(0);
            this.painterPaging.getList(0);
        }
    };
    new index();
});