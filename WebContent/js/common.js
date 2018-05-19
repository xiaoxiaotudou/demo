$(document).ready(function(){
	$('#left-manage a').on('click', function() {
		$('#left-manage a').parent().removeClass("active");
		$(this).parent().addClass("active");
	});
});