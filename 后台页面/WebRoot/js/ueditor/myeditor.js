$(document).ready(function() {
	$('#msglx_list').val('1');
	$("#msglx_list").change(function() {
		/*if ($(this).val() == '1'){
			$("#part").hidden();
			$("#sendMessage").hidden();
		}else{
			$("#part").show();
			$("#sendMessage").show();
		}*/
		$("#part").toggle(100);
		$("#sendMessage").toggle(100);
		$('#sendNotifitation').toggle(100);
		$('#title').toggle(100);
		$('#titletext').toggle(100);

	});


	var ue = UE.getEditor('editor');

	$(document).on("click", "#setContent", function() {

		UE.getEditor('editor').setContent('');
	});

	$(document).on("click", "#sendNotifitation", function() {
		// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Å¥Ê±ï¿½à¼­ï¿½ï¿½ï¿½ï¿½ï¿½Ñ¾ï¿½Ê§È¥ï¿½Ë½ï¿½ï¿½ã£¬ï¿½ï¿½ï¿½ï¿½Ö±ï¿½ï¿½ï¿½ï¿½getTextï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ãµï¿½ï¿½ï¿½ï¿½Ý£ï¿½ï¿½ï¿½ï¿½ï¿½Òªï¿½ï¿½Ñ¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½È»ï¿½ï¿½È¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		// var range = UE.getEditor('editor').selection.getRange();
		// range.select();
		//alert("1234566");
		var message = '';
		message = UE.getEditor('editor').getContent();
		var title = '';
		title =  $("#title").val();
		// alert(message);

		$.ajax({
			type : 'POST',
			dataType : 'text',
			url : 'saveNotification.do',// ï¿½ï¿½Ì¨ï¿½Þ¸ï¿½
			data : {
				data : message,
				title:"ceshi"
			},
			async : false,
			cache : false,
			error : function() {
				bootbox.alert({
					message : "·¢²¼Í¨ÖªÊ§°Ü",
					size : 'small'
				});
			},
			success : function() {
				bootbox.alert({
					message : "·¢²¼Í¨Öª³É¹¦",
					size : 'small'
				});
			}
		});
	  });

	$(document).on("click", "#sendMessage", function() {
		var message = '';
		message = UE.getEditor('editor').getContentTxt();
		var depatment = $('#collage_list option:selected').val();

		$.ajax({
			type : 'POST',
			dataType : 'text',
			url : 'saveMessage.do',
			data : {
				content : message,
				depatment : depatment
			},
			async : false,
			cache : false,
			error : function(request) {
				bootbox.alert({
					message : "·¢²¼ÏûÏ¢Ê§°Ü",
					size : 'small'
				});
			},
			success : function(em) {
				bootbox.alert({
					message : "·¢²¼ÏûÏ¢³É¹¦",
					size : 'small'
				});
			}
		});
	  });


});
