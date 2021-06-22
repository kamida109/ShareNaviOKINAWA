$(function raty() {
$(".str").attr("src","../image/star-half.png");
$(".str").attr("src","../image/star-off.png");
$(".str").attr("src","../image/star-on.png");

	let stars = document.getElementsByClassName("star");

	for(const star of stars){

      $('#'+star.id).raty({
      readOnly : true,
      score: $('#'+star.id).data('star')
    });

    }
})