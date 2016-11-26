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
			
			
			$.ajax({
				type : 'POST',
				dataType : 'text',
				url : 'setReadMessage.do',
				data:{
					id:$(this).find('td').eq(0).text(),
				},
				async : false,
				cache : false,
				error : function(request) {		
				},
				success : function(data) {		
				}
			});
			
		})
 
     var temp = $.cookie("noReadNumber");
     $('.msg').html($.cookie("noReadNumber"));
 });

 
