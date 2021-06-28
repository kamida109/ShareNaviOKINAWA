/**
 *
 */
$(function(){
	$("#favorite").click(function(){
		let flag = $("#flagStatus").val();
		let storeId = $("#storeId").val();

		console.log(storeId);

		if(flag==0){
			console.log(flag+':お気に入り登録');
			$("#favorite").html('<img class="favorite_store" src="/CSS/image/heart_on.png"><input type="hidden" id="flagStatus" value=1>');

			$.get("favorite/"+storeId+'/'+flag, function(){

			});
		} else {
			console.log(flag+':お気に入り解除');
			$("#favorite").html('<img class="favorite_store" src="/CSS/image/heart_off.png"><input type="hidden" id="flagStatus" value=0>');

			$.get("favorite/"+storeId+'/'+flag, function(){

			});
		}

	});

});