$(function raty() {
$(".str").attr("src","../image/star-half.png");
$(".str").attr("src","../image/star-off.png");
$(".str").attr("src","../image/star-on.png");
    $(".star").raty({
    readOnly : true,
    score: 5
    });
})