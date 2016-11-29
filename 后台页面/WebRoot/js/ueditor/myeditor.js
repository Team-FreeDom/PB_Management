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
		// ����������ťʱ�༭�����Ѿ�ʧȥ�˽��㣬����ֱ����getText�������õ����ݣ�����Ҫ��ѡ������Ȼ��ȡ������
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
				title:"ceshi"
			},
			async : false,
			cache : false,
			error : function(request) {
				bootbox.alert({
					message : "����ʧ��",
					size : 'small'
				});
			},
			success : function(em) {
				bootbox.alert({
					message : "�����ɹ�",
					size : 'small'
				});
			}
		});
	  });

	$(document).on("click", "#sendMessage", function() {
		// ����������ťʱ�༭�����Ѿ�ʧȥ�˽��㣬����ֱ����getText�������õ����ݣ�����Ҫ��ѡ������Ȼ��ȡ������
		// var range = UE.getEditor('editor').selection.getRange();
		// range.select();
		alert("1234566");
		var message = '';
		message = UE.getEditor('editor').getContentTxt();
		var depatment = $('#collage_list option:selected').val();

		$.ajax({
			type : 'POST',
			dataType : 'text',
			url : 'saveMessage.do',// ��̨�޸�
			data : {
				content : message,
				depatment : depatment
			},
			async : false,
			cache : false,
			error : function(request) {
				bootbox.alert({
					message : "����ʧ��",
					size : 'small'
				});
			},
			success : function(em) {
				bootbox.alert({
					message : "�����ɹ�",
					size : 'small'
				});
			}
		});
	  });


});
