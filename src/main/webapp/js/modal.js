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
		let reviewId = $('#reviewId').val();
		let newReview = $("#newReview").val();

		console.log(reviewId);

		if(newReview[0]===undefined){
			$("#errMsg").html('レビュー内容を入力してください');
		} else {
			$.get("inputReview/"+reviewId+'/'+newReview, function(){
				location.reload();
			})
		}
	});

	//	レビュー削除
	$('#reviewDel').click(function(){
		let reviewId = $('[class=delReview]:checked').map(function(){
						return $(this).val();
						}).get();

		console.log(reviewId);

		if(!reviewId.length){
			alert('削除したいレビューにチェックを入れてください');
		} else {
			$.get("reviewDel/"+reviewId, function(){
				location.reload();
			})
		}

	})

	// レビューの追加
	$('#reviewInsert').click(function(){
		let storeId = $('#storeId').val();
		let userId = $('#userId').val();
		let newReview = $("#newReview").val();

		if(newReview[0]===undefined){
			$("#errMsg").html('レビュー内容を入力してください');
		} else {
			$.get("insertReview/"+storeId+'/'+userId+'/'+newReview, function(){
				location.reload();
			});
		}

	})
});

// 店舗の削除
$('#storeDelete').click(function() {

	let storeId = $('#storeId').val();
	let storeName = $('#storeName').val();

	if(window.confirm('「' + storeName + '」の情報を削除しますか?\nOKを押した場合、検索画面に遷移します')){
		$.get("storeDelete/"+storeId, function(){
			console.log('削除完了');
		})
		location.href = "http://localhost:8080/search";
	}
	return false;
});

// 戻るボタン
$('#backSearch').click(function(){
	$.get("returnSearch/", function(){
		console.log('戻るボタンクリック');
		location.href = "http://localhost:8080/search";

	})
});