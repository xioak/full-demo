// 轮播
// nivo-slider轮播特效
// $('#slider').nivoSlider({
//     effect: 'random', //效果
//     slices: 15, //切片效果时的数量
//     boxCols: 8, //格子效果列
//     animSpeed: 500, //动画速度
//     pauseTime: 3000, //暂停时间
//     directionNav: false, //不使用左右导航
// });

//面包屑更新
$(document).ready(function () {
    $('.nav-ul li a').click(function () {
        $('.bread-crumb li.active')[0].innerHTML = this.innerHTML;
        $('.bread-crumb p')[0].innerHTML = this.innerHTML;
    })
});

$(function () {
    //主导航跨页面点击跳转
    var str = window.location.search;
    var N = Number(str.match(/\d+/g));
    if (N == 0 || N == 1 || N == 2) {
        $('.nav-ul li a').eq(N).trigger('click');
    } else if (N == 3) {
        N = 0;
        $('.nav-ul li a').eq(N).trigger('click');
    } else if (N == 4) {
        N = 1;
        $('.nav-ul li a').eq(N).trigger('click');
    } else if (N == 5) {
        N = 0;
        $('.nav-ul li a').eq(N).trigger('click');
    } else if (N == 6) {
        N = 1;
        $('.nav-ul li a').eq(N).trigger('click');
    } else if (N == 7) {
        N = 0;
        $('.nav-ul li a').eq(N).trigger('click');
    } else if (N == 8) {
        N = 1;
        $('.nav-ul li a').eq(N).trigger('click');
    }
    //主导航同页面内跳转
    $('.sub-nav li a').click(function () {
        $('.nav-ul li a').eq($(this).attr('rel')).trigger('click');
    });
});
