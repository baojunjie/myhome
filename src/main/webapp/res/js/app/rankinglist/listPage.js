define(["jquery", "base", "ajax", "page", "imageAdaptHeight"], function ($, base, ajax, page, imageAdaptHeight) {
    var listPage = function (selector) {
        this.$listPage = $(selector);
        this._init();
        this._bindEvent();
    };
    listPage.prototype = {
        _init: function () {
            this.page = new page($(".J_page", this.$listPage));
            this.$list = $(".J_list", this.$listPage);
            this.$tmpl = $(".J_tmpl", this.$listPage);
            this.$status = $(".J_status", this.$listPage);

            this.load();
        },
        _bindEvent: function () {
            var _this = this;
        },
        load: function () {
            var _this = this;
            var hashs = base.deserialize(location.hash);
            var params = {
                pageType: this.$listPage.attr("data-page-type"),
                type: this.$listPage.attr("data-type"),
                age: this.$listPage.attr("data-age"),
                awards: this.$listPage.attr("data-awards"),
            };
            params.size = hashs.pageSize || 10;
            params.index = hashs.pageIndex > 0 ? hashs.pageIndex - 1 : 0;
            params.type = hashs.type || params.type;
            params.age = hashs.age || 0;
            params.awards = hashs.awards;

            //console.log(params.size + "--------" + params.index + "--------" + params.type + "------" + params.age);

            ajax({
                url: this.$listPage.attr("data-url"),
                data: $.param(params),
                type: "get",
                beforeSend: function () {
                    _this.$status.text("加载中...").show();
                },
                success: function (data) {
                    if (!data || !data.list || !data.list.length) {
                        _this.$status.text("暂无数据...").show();
                    }
                    else {
                        _this.$status.hide();
                    }
                    data = data || {};
                    data.list = data.list || [];
                    _this.$list.html(_this.$tmpl.render(data.list));
                    imageAdaptHeight.adapt();
                    if (!data.list.length) {
                        _this.page.$paging.hide();
                    }
                    else {
                        _this.page.$paging.show();
                        _this.page.load(params.index + 1, params.size, data.list.length, data.total, params);
                    }
                }
            });
        },
    };
    return listPage;
});