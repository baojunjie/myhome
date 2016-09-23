define(["jquery", "masonry"], function ($, masonry) {
    var grid = document.querySelector('.grid');
    var msnry = new masonry(grid, {
        itemSelector: '.grid-item',
        columnWidth: 246
    });
});