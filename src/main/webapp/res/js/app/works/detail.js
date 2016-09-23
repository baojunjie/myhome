define([
    "jquery",
    "jsrender",
    "base",
    "ajax",
    "widget",
    "tip",
    "imageAdapt",
    "qrcode"
], function ($, jsrender, base, ajax, widget, tip, imageAdapt, qrcode) {
    var detail = function () {
        this._init();
        this._bindEvent();
    };
    detail.prototype = {
        _init: function () {
            this.worksId = $("#worksId").val();
            if (this.worksId) {
                ajax({
                    url: "/comment/getlist.do",
                    data: { worksid: this.worksId },
                    success: function (data) {
                        $("#review").html($("#reviewTmpl").render(data.list));
                        imageAdapt.adapt();
                    }
                });
            }

            this.$shareWechat = $("#shareWechat");
            new QRCode(document.getElementById("shareWechatDialogContent"), {
                text: this.$shareWechat.data("url"),
                width: 150,
                height: 150,
                correctLevel: QRCode.CorrectLevel.H
            });
        },
        _bindEvent: function () {
            $("#reviewForm").on("submit", function () {
                var $this = $(this);
                var data = $this.serialize();
                var p = base.deserialize(data);
                if (!p.words) {
                    tip("请输入评论内容");
                    return false;
                }
                if (p.words.length > 200) {
                    tip("评论内容最长200字");
                    return false;
                }
                ajax({
                    url: "/comment/interceptorAdd.do",
                    data: data,
                    success: function (response) {
                        $("#review").prepend($("#reviewTmpl").render(response.comment));
                        $this[0].reset();
                    }
                });
                return false;
            });

            var $shareWechatDialog = $("#shareWechatDialog");
            this.$shareWechat.click(function () {
                $shareWechatDialog.show();
            });
            $("#shareWechatDialogClose").click(function () {
                $shareWechatDialog.hide();
            });
        }
    };
    new detail();
})