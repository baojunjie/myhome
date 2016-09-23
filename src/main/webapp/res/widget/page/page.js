define(["jquery"], function ($) {
    var paging = function (selector) {
        this.$paging = $(selector);
    };
    paging.prototype = {
        load: function (pageIndex, pageSize, listLength, total, params) {
            var page = Math.ceil(total / pageSize);
            var startIndex = pageIndex - 5;
            var endIndex = pageIndex + 4;
            if (startIndex < 1) {
                startIndex = 1;
            }
            if (endIndex - startIndex + startIndex < 10) {
                endIndex = 10;
            }
            if (endIndex - startIndex < 10) {
                startIndex = endIndex - 9;
            }
            if (endIndex > page) {
                endIndex = page;
            }
            var urls = [];
            for (var i = startIndex - 2; i <= endIndex + 2; i++) {
                if (i === startIndex - 2) {
                    params.pageIndex = 1;
                }
                else if (i === startIndex - 1) {
                    params.pageIndex = pageIndex - 1;
                }
                else if (i === endIndex + 1) {
                    params.pageIndex = pageIndex + 1;
                }
                else if (i === endIndex + 2) {
                    params.pageIndex = page;
                }
                else {
                    params.pageIndex = i;
                }
                if (params.pageIndex < 1) {
                    params.pageIndex = 1;
                }
                if (params.pageIndex > page) {
                    params.pageIndex = page;
                }
                urls.push({
                    startIndex: startIndex,
                    endIndex: endIndex,
                    index: i,
                    pageIndex: params.pageIndex,
                    url: location.href.slice(0, location.href.indexOf("#") + 1).replace("#", "") + "#" + $.param(params)
                });
            }
            var data = {
                pageIndex: pageIndex,
                pageSize: pageSize,
                listLength: listLength,
                total: total,
                page: page,
                urls: urls
            };
            var html = $("#pagingTmpl").render(data);
            this.$paging.html(html);
        }
    }
    return paging;
});