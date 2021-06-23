$(function raty() {
$(".str").attr("src","../image/star-half.png");
$(".str").attr("src","../image/star-off.png");
$(".str").attr("src","../image/star-on.png");

	let stars = document.getElementsByClassName("star");
	let stars2 = document.getElementsByClassName("star2");
	let stars3 = document.getElementsByClassName("star3");

	for(const star of stars){

      $('#'+star.id).raty({
      readOnly : true,
      score: $('#'+star.id).data('star')
    });

    }

	for(const star of stars2){

      $('#'+star.id).raty({
      readOnly : true,
      score: $('#'+star.id).data('star2')
    });

    }

	for(const star of stars3){

      $('#'+star.id).raty({
      readOnly : true,
      score: $('#'+star.id).data('star3')
    });

    }
})