define([
    "jquery",
    "jsrender",
    "base",
    "ajax",
    "event",
    "widget",
    "imageAdapt"
], function ($, jsrender, base, ajax, event, widget, imageAdapt) {
    var paging = function (selector, settings) {
        this.$paging = $(selector);
        this.settings = $.extend({
            buttonSelector: ".site-paging-button",
            listSelector: ".site-paging-list",
            tmplSelector: ".site-paging-tmpl",
            tipSelector: ".site-paging-tip",
            pageSize: 12,
            pageIndex: 0,
            autoLoad: true
        }, settings, this.$paging.data());
        this.$button = this.$paging.find(this.settings.buttonSelector);
        this.$list = this.$paging.find(this.settings.listSelector);
        this.$tip = this.$paging.find(this.settings.tipSelector);
        if (this.settings.tmplSelector.charAt(0) === "#") {
            this.$tmpl = $(this.settings.tmplSelector);
        }
        else {
            this.$tmpl = this.$paging.find(this.settings.tmplSelector);
        }
        widget.set("paging", this, this.$paging);
        this.event = new event(this);
        this._init();
        this._bindEvent();
    };
    paging.prototype = {
        _init: function () {
            var _this = this;
            if (this.settings.autoLoad) {
                this.getList(this.settings.pageIndex);
            }
        },
        _bindEvent: function () {
            var _this = this;
            this.$paging.on("click", this.settings.buttonSelector, function () {
                var $this = $(this);
                $this.hide();
                _this.settings.pageIndex++;
                _this.getList(_this.settings.pageIndex);
            });
        },
        getList: function (index, cb) {
            var _this = this;
            var params = $.extend({
                index: index,
                size: this.settings.pageSize
            }, this.settings.params, this.$paging.data("params"));

            this.settings.pageIndex = params.index;
            this.settings.pageSize = params.size;

            this.$button.hide();

            ajax({
                url: this.settings.url,
                data: params,
                beforeSend: function () {
                    _this.$tip.text("加载中...").show();
                },
                success: function (data) {
                    var list = data.list;
                    var total = data.total;
                  

                    if (_this.settings.listName) {
                        list = data[_this.settings.listName];
                    }

                    if (total === null || total === undefined) {
                        total = list.length;
                    }

                    if (!data || !total) {
                        _this.$tip.text("暂无内容").show();
                        _this.$button.hide();
                        return;
                    }

                    if (list && list.length) {
                        for (var i = 0, len = list.length; i < len; i++) {
                            list[i].i = index * _this.settings.pageSize + i;
                        }
                        list.tmplData = _this.settings.tmplData;
                        //新增 #commit
                        if ($("#commit").val()) {
                        	for(var i=0; i<list.length; i++){
                        		list[i].commit = $("#commit").val();
                        	}
                        };
                        _this.$list.append(_this.$tmpl.render(list));
                        
                    }
                    if ((index + 1) * _this.settings.pageSize < total) {
                        _this.$button.show();
                    }
                    else {
                        _this.$button.hide();
                    }
                    _this.$tip.hide();
                    cb && cb();
                    imageAdapt.adapt();
                    _this.event.fire("success", data);
                }
            });
        },
        clear: function () {
            this.$list.empty();
            this.settings.pageIndex = 0;
        },
        reload: function () {
            this.clear();
            this.getList(this.settings.pageIndex);
        }
    };
    return paging;
});

 