define(["jquery", "widget", "event"], function ($, widget, event) {
    var formAjax = function (selector) {
        this.$form = $(selector);
        this._bindEvent();
        this.event = new event(this);
    };
    var tag=false;
    formAjax.prototype = {
        _bindEvent: function () {
            var _this = this;
            this.$form.on("submit", function () {
                var $this = $(this);
                //if ($this.hasClass("site-form")) {
                //    var validation = widget.get($this.attr("id"));
                //    if (!validation.valid()) {
                //        return false;
                //    }
                //}
                if ($this.attr("data-valid-result") === "false") {
                    return false;
                }
                if(tag){
                	return false;
                }
                tag=true;
                ajax({
                    url: $this.attr("action"),
                    type: $this.attr("method") || "post",
                    data: $this.serialize(),
                    prr: true,
                    success: function (data) {
                        if (_this.event.get("submit")) {
                            _this.event.fire("submit", data);
                        }
                        else {
                            location.reload();
                        }
                    }
                });
                return false;
            });
        }
    };
    return formAjax;
});