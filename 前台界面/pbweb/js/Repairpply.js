// JavaScript Document
$(function () {
		/*********************/
		$.ajax({
					type : 'POST',
					dataType : 'json',
					url : '',
					async : false,
					cache : false,
					error : function(request) {
						alert("error");
						},
					success : function(data) {
							var i = 0;
							for ( var item in data) {
								$("#baselistid").after(
									"<option value="+data[i].dept+">"
											+ data[i].baselistname + "</option>");
										i++;
									}
					
								}
					
							});
	 /**************************/
	$('#applyfile').change(function() {    
    var filepath = $(this).val();
    var file_size = this.files[0].size;
    var size = file_size / 1024;
    var extStart = filepath.lastIndexOf(".");
    var ext = filepath.substring(extStart, filepath.length).toUpperCase();
    if (ext != ".RAR" && ext != ".ZIP") {
        bootbox.alert({
            message: "上传资料仅限于压缩包格式ʽ",
            size: 'small'
        });
        flag1=false;
        return false;
    }
    if (size > 1024 * 10) {
        bootbox.alert({
            message: "上传资料大小不能大于10M",
            size: 'small'
        });
        flag2=false;
        return false;
    }   
    flag1=true;
    flag2=true;
    return false;
});
	/*########*/
	 	$(document).on("click", "#Submit", function() {
			
			var projectname=$("#projectname").val();
			var name=$("#name").val();
			var address=$("#address").val();
			var budget=$("#budget").val();
			var baselist=$("#baselist").val();
			var reason=$("#reason").val();
			//alert(baselist);
			var strmoney=/^[0-9]*$/.test(budget);
			if(projectname==""){
				bootbox.alert({
					message : "请输入项目名称",
					size : 'small'
					});	
				return 0;
				}
			else if(name==""){
				bootbox.alert({
					message : "请输入您的姓名",
					size : 'small'
					});	
				return 0;
				}
			else if(address==""){
				bootbox.alert({
					message : "请输入地址",
					size : 'small'
					});	
				return 0;
				}
			else if(budget==""){
				bootbox.alert({
					message : "请输入预算金额",
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
			/*************/
			$("#applyform").submit();
			
			/*************/
			
		})
	/*########*/	 
 });