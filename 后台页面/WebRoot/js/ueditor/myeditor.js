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
		$('#titleform').toggle(100);
		//$('#titletext').toggle(100);

	});


	var ue = UE.getEditor('editor');

	$(document).on("click", "#setContent", function() {

		UE.getEditor('editor').setContent('');
	});

	$(document).on("click", "#sendNotifitation", function() {
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
			url : 'saveNotification.do',// ��̨�޸�
			data : {
				data : message,
				title: title
			},
			async : false,
			cache : false,
			error : function() {
				bootbox.alert({
					message : "发布失败!",
					size : 'small'
				});
			},
			success : function() {
				bootbox.alert({
					message : "发布成功!",
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
					message : "发布失败!",
					size : 'small'
				});
			},
			success : function(em) {
				bootbox.alert({
					message : "发布成功!",
					size : 'small'
				});
			}
		});
	  });


});
