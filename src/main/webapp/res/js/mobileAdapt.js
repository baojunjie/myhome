// scale adapt
(function () {
    function scaleAdapt() {
        var width;
        if (window.orientation === 90 || window.orientation === -90) {
            width = window.screen.height;
        }
        else {
            width = window.screen.width;
        }
        if (width < 1400) {
            var scale = Math.floor(width / 1400 * 100) / 100;
            var viewport = document.getElementsByName("viewport")[0];
            if (viewport) {
                viewport.setAttribute("content", "width=device-width, initial-scale=" + scale);
            }
        }
    }

    window.addEventListener && window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", function () {
        scaleAdapt();
    });

    scaleAdapt();
})();