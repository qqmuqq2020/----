function choose(mas,info){
	
	
	$("#mas").html(mas);
	$("#info").html(info);
	$("#alert1").show();
	$("#ok").click(function(){
		$("#alert1").hide();
		return false;
	})
	$("#unok").click(function(){
		$("#alert1").hide();
		return true;
	})
}