$.fn.raty.defaults.path = "/image";
$(function raty() {
    $(".star").raty({
    readOnly: true,
    score: 3
    });
})