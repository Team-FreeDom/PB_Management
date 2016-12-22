$("li.menuItem.nav-parent").click(function(){
	  $(this).toggleClass("opened nav-expanded");
	});
	
 $(function () {
	 	$(document).on("click", "#msgtable tr", function() {
			bootbox.dialog({
    		title: $(this).find('td').eq(1).html(),
    		message: $(this).find('td').eq(2).html()
			});
			$(this).removeClass('read');
			$(this).addClass('read');
		})
 });

