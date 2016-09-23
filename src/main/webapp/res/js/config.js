require.config({
    baseUrl: "js/common",
    paths: {
        jquery: "../lib/jquery/jquery",
        jsrender: "../lib/jsrender/jsrender",
        md5: "../lib/blueimp-md5/md5",
        placeholder: "../lib/jquery-placeholder/jquery.placeholder",
        slick: "../lib/slick-carousel/slick",
        webuploader: "../lib/webuploader/webuploader",
        jsurl: "../lib/jsurl/url",
        countdown: "../lib/jquery.countdown/jquery.countdown",
        jcrop: "../lib/jcrop/js/jquery.Jcrop",
        qrcode: "../lib/qrcode/qrcode",
        masonry: "../lib/masonry/masonry.pkgd",

        widget: "../widget/base/js/widget",
        validation: "../widget/validation/validation",
        tip: "../widget/tip/js/tip",
        event: "../widget/event/event",
        tab: "../widget/tab/tab",
        upload: "../widget/upload/upload",
        paging: "../widget/paging/paging",
        page: "../widget/page/page",
        mask: "../widget/mask/js/mask",
        dialog: "../widget/dialog/dialog",
        loginDialog: "../widget/loginDialog/loginDialog",
        painterDialog: "../widget/painterDialog/painterDialog",
        crop: "../widget/crop/crop",
        imageAdapt: "../widget/imageAdapt/imageAdapt",
        imageAdaptHeight: "../widget/imageAdapt/imageAdaptHeight",
        formAjax: "../widget/formAjax/formAjax",
        artwrokDialog: "../widget/artwrokDialog/artwrokDialog",
        select: "../widget/select/select"
    },
    shim: {
        jsrender: ["jquery"],
        placeholder: ["jquery"],
        slick: ["jquery"],
        jcrop: ["jquery"],
        masonry: ["jquery"],
        jsurl: {
            deps: ["jquery"],
            exports: "Url"
        },
        qrcode: {
            exports: "qrcode"
        }
    }
});