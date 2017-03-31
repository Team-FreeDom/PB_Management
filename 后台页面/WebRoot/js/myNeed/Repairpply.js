// JavaScript Document
  var flag1=true;
  var flag2=true;
$(function () {
		/*********************/
		$.ajax({
					type : 'POST',
					dataType : 'json',
					url : 'baseNeiName.do',
					async : true,
					cache : false,
					error : function(request) {
						bootbox.alert({
							message : "请求异常",
							size : 'small'
						});
						},
					success : function(data) {
							var i = 0;
							for ( var item in data) {
								$("#baselistid").after(
									"<option value="+data[i].id+">"
											+ data[i].name + "</option>");
								i++;
									}
					
								}
					
							});
	 /**************************/
		
	/*########*/
	 	$(document).on("click", "#Submit", function() {
	 		
			var projectname=$("#projectname").val();
			var name=$("#name").val();
			var address=$("#address").val();
			var budget=$("#budget").val();
			budget=budget.trim();
			var baselist=$("#baselist").val();
			var reason=$("#reason").val();
			var strmoney=/^[0-9]*$/.test(budget);
			var money=budget.substring(1,0);
			if(projectname==""){
				bootbox.alert({
					message : "请填写项目名称",
					size : 'small'
					});	
				return 0;
				}
			else if(name==""){
				bootbox.alert({
					message : "请填写报修人",
					size : 'small'
					});	
				return 0;
				}
			else if(address==""){
				bootbox.alert({
					message : "请填写具体位置",
					size : 'small'
					});	
				return 0;
				}
			else if(budget==""){
				bootbox.alert({
					message : "请填写预算金额",
					size : 'small'
					});	
				return 0;
				}
			else if(strmoney==false){
				bootbox.alert({
					message : "预算金额只能为数字",
					size : 'small'
					});	
				return 0;
				}
			else if(budget.length>1&&money==0){
				bootbox.alert({
					message : "请填写正确的预算金额格式，第一个数字不能为零",
					size : 'small'
					});	
				return 0;
				}
			else if(baselist=="请选择"){
				bootbox.alert({
					message : "请选择基地",
					size : 'small'
					});	
				return 0;
				}
			else if(reason==""){
				bootbox.alert({
					message : "请填写原因",
					size : 'small'
					});	
				return 0;
				}
			  if (!flag1) {
			        bootbox.alert({
			            message: "上传资料仅限于rar,zip压缩包格式",
			            size: 'small'
			        });			    
			        return;
			    }
			    if (!flag2) {
			        bootbox.alert({
			            message: "上传资料大小不能大于10M",
			            size: 'small'
			        });			      
			        return;
			    }   
			/*************/
			$("#applyform").submit();
			
			/*************/
			
		})
		
		$('#applyfile').change(function() {    
    var filepath = $(this).val();
    var file_size = this.files[0].size;
    var size = file_size / 1024;
    var extStart = filepath.lastIndexOf(".");
    var ext = filepath.substring(extStart, filepath.length).toUpperCase();
    if (ext != ".RAR" && ext != ".ZIP") {
        bootbox.alert({
            message: "上传资料仅限于rar,zip压缩包格式",
            size: 'small'
        });
        flag1=false;
        return;
    }
    if (size > 1024 * 10) {
        bootbox.alert({
            message: "上传资料大小不能大于10M",
            size: 'small'
        });
        flag2=false;
        return;
    }   
    flag1=true;
    flag2=true;
});
	/*########*/	 
 });