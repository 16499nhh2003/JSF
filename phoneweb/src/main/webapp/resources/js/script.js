$(document).ready(function(e) {
    $("#goTopId").click(function() {
		event.preventDefault();
		
		$("html, body").animate({
			scrollTop:$("#myHeader").offset().top
		}, 800);
	});
	
	$("#goTopId").hide();
	$(window).scroll(function() {
		if ($(window).scrollTop() > 70) {
			if ($("#goTopId").is(":hidden"))
				$("#goTopId").show("slow");
		} else {
			$("#goTopId").hide("slow");
		}
	});
});