require(["config"], function () {
    var rconfig = require.s.contexts._.config;
    rconfig.baseUrl = siteConfig.resUrl + "/" + rconfig.baseUrl;
    for (var i in rconfig.paths) {
        rconfig.paths[i] = siteConfig.resUrl + rconfig.paths[i].slice(2);
    }
    require.config(rconfig);
    require([
        "jquery",
        "placeholder",
        "validation",
        "tab",
        "paging",
        "upload",
        "ajax",
        "crop",
        "imageAdapt",
        "imageAdaptHeight",
        "formAjax",
        "painterDialog",
        "artwrokDialog",
        "common",
        "select"
    ],
        function (
            $,
            placeholder,
            validation,
            tab,
            paging,
            upload,
            ajax,
            crop,
            imageAdapt,
            imageAdaptHeight,
            formAjax,
            painterDialog,
            artwrokDialog,
            common,
            select
        ) {
            window.ajax = ajax;
            if (siteConfig.app) {
                var loadModules = [];
                var modules = siteConfig.app.split(",");
                for (var i = 0, len = modules.length; i < len; i++) {
                    if (modules[i]) {
                        loadModules.push("../app/" + modules[i]);
                    }
                }
                require(loadModules, function () {
                    init();
                });
            }
            else {
                init();
            }
            function init() {
                $('input, textarea').placeholder();

                $(".site-tab").each(function () {
                    var $this = $(this);
                    if ($this.data("auto-parse") === false) {
                        return true;
                    }
                    new tab($this);
                });

                $(".site-paging").each(function () {
                    var $this = $(this);
                    if ($this.data("auto-parse") === false) {
                        return true;
                    }
                    new paging($this);
                });

                $(".site-upload").each(function () {
                    var $this = $(this);
                    if ($this.data("auto-parse") === false) {
                        return true;
                    }
                    this.upload = new upload(this);
                });


                // 到页面底部
                $("#goTop").click(function () {
                    $('body,html').animate({
                        scrollTop: 0
                    }, 800);
                    return false;
                });

                // 到页面底部
                $(".J_goEnd").click(function () {
                    $('body,html').animate({
                        scrollTop: $(document).height()
                    }, 800);
                    return false;
                });

                // 图片裁剪
                $(".J_crop").click(function () {
                    new crop({ previewSelector: this }).show();
                });

                // qrcode
                $(".J_qrcode").hover(function () {
                    var $this = $(this);
                    var $popups = $(".popup");
                    var $popup = $this.siblings();
                    $popups.not($popup).hide();
                    $popup.show();
                }, function () {
                    var $this = $(this);
                    var $popups = $(".popup");
                    var $popup = $this.siblings();
                    $popup.hide();
                });

                // 图片自适应
                imageAdapt.adapt();

                // validation 
                $(".site-form").each(function () {
                    var $this = $(this);
                    if ($this.data("auto-parse") === false) {
                        return true;
                    }
                    new validation($this);
                });

                // form ajax
                $(".site-formAjax").each(function () {
                    var $this = $(this);
                    if ($this.data("auto-parse") === false) {
                        return true;
                    }
                    new formAjax(this);
                });

                // 当前角色不是画家，提示改换角色
                $(".site-painterJoin").each(function () {
                    var $this = $(this);
                    new painterDialog($this);
                });

                // select
                $(".site-select").each(function () {
                    var $this = $(this);
                    if ($this.data("auto-parse") === false) {
                        return true;
                    }
                    new select(this);
                });
            }
        });
});
