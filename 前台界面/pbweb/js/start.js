// JavaScript Document
$(document).ready(function() {
	$("#start-btn").click(function (){
		$("#start").hide();
		$("#set").show()
		})
		

	$(document).on("click", "#save", function() {

		var planstime=$("#demo").val();
		var planetime=$("#demo2").val();
		var rentstime=$("#demo3").val();
		var rentetime=$("#demo4").val();	
		if(planstime==""){
			bootbox.alert({
					message: "请选择申请开始时间!",
					size: 'small'
				});
			}
		else if(planetime==""){
			bootbox.alert({
					message: "请选择申请结束时间!",
					size: 'small'
				});
			}
		else if(rentstime==""){
			bootbox.alert({
					message: "请选择租赁开始时间!",
					size: 'small'
				});
			}
		else if(rentetime==""){
			bootbox.alert({
					message: "请选择租赁结束时间!",
					size: 'small'
				});
			}
		else{
			var str='';
			str='{planstime:"'+planstime+'",planetime:"'+planetime+'",rentstime:"'+planetime+'",rentetime:"'+planetime+'"}'
			alert(str);
			bootbox.confirm({
			message: "您确认保存此设置吗.",
			size: 'small',
			buttons: {
					cancel: {
					label: '<i class="fa fa-times"></i> 取消'
							},
					confirm: {
					label: '<i class="fa fa-check"></i> 确认'
							}
						},
			callback: function (result) {
							if(result==true){
								
								bootbox.alert({
										message: "保存成功!",
										size: 'small'
									});
								
								}
						}
					});
			
			}
		})
		
		$("#end-btn").click(function (){
			$("#start").show();
			$("#set").hide();
			
			})

	})
	
