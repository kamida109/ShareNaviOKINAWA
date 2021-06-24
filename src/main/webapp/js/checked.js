
$(function(){
	$("#check").prop("checked");

	$('[name="sample"]:radio').change(function(){

		if($("#solved").prop("checked") == true) {
			$.get("checkSolved/", function(){
				location.reload();
			});
		} else if($("#unsolved").prop("checked") == true){
			$.get("checkUnSolved/", function(){
				location.reload()
			});
		} else {
			$.get("checkAll/", function(){
				location.reload()
			});
		}

	})

});
