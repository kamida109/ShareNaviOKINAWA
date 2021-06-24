
$(function(){
	$('#review').click(function(){
		$('.modal_output').fadeIn();
		return false;
	});

	$('.modal_close').click(function(){
		$('.modal_output').fadeOut();
		return false;
	});

	$('#comit').click(function(){

		let newReview = $("#newReview").val();

		$.get("inputReview/"+newReview, function(){
			location.reload();
		})


	});
});