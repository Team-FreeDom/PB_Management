$("li.menuItem.nav-parent").click(function(){
	if($(this).hasClass("opened")){
		
		$(this).removeClass("opened nav-expanded");
	}else{
		$("li.menuItem.nav-parent").removeClass("opened nav-expanded");
		$(this).addClass("opened nav-expanded");
	}
	});
 $(function () {
	 	$(document).on("click", "#msgtable tr", function() {
			bootbox.dialog({
    		title: $(this).find('td').eq(2).html(),
    		message: $(this).find('td').eq(3).html()
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
					$(".msg").html(data);
				}
			});	
		});
 
       $.ajax({
				type : 'POST',
				dataType : 'text',
				url : 'getNoReadMessageCount.do',
				async : false,
				cache : false,
				error : function(request) {		
				},
				success : function(data) {	
					$('.msg').html(data);
				}
			});	     
       $('.text-success').html($.cookie('name'));     
       $('#imageMain').attr("src",$.cookie('image'));  
      
 });
 

 
