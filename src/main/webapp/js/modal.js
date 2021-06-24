
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

		if(newReview[0]===undefined){
			$("#errMsg").html('レビュー内容を入力してください');
		} else {
			$.get("inputReview/"+storeId+'/'+newReview, function(){
				location.reload();
			})
		}
	});

	$('#reviewDel').click(function(){
		let reviewId = $('#reviewId').val();

		$.get("reviewDel/"+reviewId, function(){
			location.reload();
		})
	})


});

$('#storeDelete').click(function() {

	let storeId = $('#storeId').val();
	let storeName = $('#storeName').val();

	if(window.confirm('「' + storeName + '」の情報を削除しますか?\nOKを押した場合、HOME画面に遷移します')){
		$.get("storeDelete/"+storeId, function(){
			console.log('削除完了');
		})
		location.href = "http://localhost:8080/home";
	}

	return false;
});

