
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
		let storeId = $('#storeId').val();
		let newReview = $("#newReview").val();

		$.get("inputReview/"+storeId+'/'+newReview, function(){
			location.reload();
		})


	});
});