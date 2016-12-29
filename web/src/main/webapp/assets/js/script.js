var grid = document.querySelector('.masonry-container');
var masonry;

imagesLoaded(grid, function () {
    masonry = new Masonry(grid, {
        itemSelector: '.item',
        columnWidth: '.item'
    });
});

$('.carousel').carousel({
    interval: 2000,
    pause: 'hover'
});
