$(document).ready(function() {

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
		
	});

	
	var ue = UE.getEditor('editor');
	
	$(document).on("click", "#setContent", function() {
		
		UE.getEditor('editor').setContent('');
	});
	
	$(document).on("click", "#sendNotifitation", function() {
		// 当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
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
			url : 'saveNotification.do',// 后台修改
			data : {
				data : message,
				title:"ceshi"
			},
			async : false,
			cache : false,
			error : function(request) {
				bootbox.alert({
					message : "发布失败",
					size : 'small'
				});
			},
			success : function(em) {
				bootbox.alert({
					message : "发布成功",
					size : 'small'
				});
			}
		});
	  });

	$(document).on("click", "#sendMessage", function() {
		// 当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
		// var range = UE.getEditor('editor').selection.getRange();
		// range.select();
		alert("1234566");
		var message = '';
		message = UE.getEditor('editor').getContentTxt();
		var depatment = $('#collage_list option:selected').val();

		$.ajax({
			type : 'POST',
			dataType : 'text',
			url : 'saveMessage.do',// 后台修改
			data : {
				content : message,
				depatment : depatment
			},
			async : false,
			cache : false,
			error : function(request) {
				bootbox.alert({
					message : "发布失败",
					size : 'small'
				});
			},
			success : function(em) {
				bootbox.alert({
					message : "发布成功",
					size : 'small'
				});
			}
		});
	  });
	
	
});

