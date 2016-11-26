$("li.menuItem.nav-parent").click(function(){
	  $(this).toggleClass("opened nav-expanded");
	});
 $(function () {
	 	$('#post1').attr('height',$('#calendar').attr('height'));
	 	$(document).on("click", "#msgtable tr", function() {
			bootbox.dialog({
    		title: $(this).find('td').eq(1).html(),
    		message: $(this).find('td').eq(2).html()
			});
			$(this).removeClass('read');
			$(this).addClass('read');
		})
 });

