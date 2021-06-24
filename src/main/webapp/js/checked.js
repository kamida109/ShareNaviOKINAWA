
$(function(){
	$("#check").prop("checked");

	let bool = $("#check").prop("checked");

	console.log(bool);

	if($("#check").prop("checked") == true) {
		$.get("pulldown/"+value, function(){
			location.reload()
		})
	}

});
