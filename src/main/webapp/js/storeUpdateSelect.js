$(function(){

	$("#mainCategory1").change(function(){
		let value = $("#mainCategory1").val();
		if(value != 0){
			$("#subCategory1").show();
			$.get("pulldown/"+value, function(data){
				console.log(data);
				$("#subCategory1").html(data);
			})
		} else {
			$("#subCategory1").hide();
		}
	});

	$("#mainCategory2").change(function(){
		let value = $("#mainCategory2").val();
		if(value != 0){
			$("#subCategory2").show();
			$.get("pulldown/"+value, function(data){
				console.log(data);
				$("#subCategory2").html(data);
			})
		} else {
			$("#subCategory2").hide();
		}
	});

	$("#mainCategory3").change(function(){
		let value = $("#mainCategory3").val();
		if(value != 0){
			$("#subCategory3").show();
			$.get("pulldown/"+value, function(data){
				console.log(data);
				$("#subCategory3").html(data);
			})
		} else {
			$("#subCategory3").hide();
		}
	});

});