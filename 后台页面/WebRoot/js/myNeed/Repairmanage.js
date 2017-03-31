// JavaScript Document
var obj=[];
var flag1=true;
var flag2=true;
$(document).ready(function() {
				//分页表格 
              var applytable =$('#Repairmanage').DataTable(
			  {
				  "processing": true,
        		  "serverSide": true,
				  "bSort": false,
				  "ordering":true,
				  "aLengthMenu":[ 5, 10, 20, 30 ], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 10,  //默认每页显示多少条记录
					"bfilter":true,
					"dom":'ftipr<"bottom"l>',
					"ajax" : {
							"url" : "query_maintainapply.do",
							"type" : "POST"
						},  
					"aoColumns" : [                                        
									{
										"mData" : "id",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										
									},
									{ //aoColumns设置列时，不可以任意指定列，必须列出所有列。
										"mData" : "pro_name",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
									},
									{
										"mData" : "basename",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",

									},
									{
										"mData" : "username",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",

									},
									{
										"mData" : "apply_time",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
									},
									{
										"mData" : "actualmoney",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
									},									
									{
										"mData" : "money",
										"orderable" : false, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									{
										"mData" : "address",
										"orderable" : false, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									{
										"mData" : "reason",
										"orderable" : false, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},									
									{
										"mData" : "file",
										"orderable" : false, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									
									{
										"mData" : "id",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : '',
										"render":function(data,type,row){					
											obj.push(row);							
											return data="<button type='button' class='btn btn-warning btn-xs' value='"+(obj.length-1)
														+ "' id='checkdetale'>查看</button>";
										}
									}
							],
					
					
					"columnDefs" :
							[{
								"orderable" : false, // 禁用排序
								"targets" : [0], // 指定的列
								"data" : "id",
								"render" : function(data, type, row) {									
									return '<input type="checkbox" value="'+ data + '" name="idname" class="ck"  />';
								}
							}],
					
                    "language": {
                        "lengthMenu": "每页 _MENU_ 条记录",
                        "zeroRecords": "没有找到记录",
                        "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                        "infoEmpty": "无记录",
                        "infoFiltered": "(从 _MAX_ 条记录过滤)",
						"sSearch": "模糊查询：",
						"oPaginate": {
						   "sFirst": "首页",
						   "sPrevious": " 上一页 ",
						   "sNext": " 下一页 ",
						   "sLast": " 尾页 "
					   }
                    }
					  });
              
              //基地列表
              $.ajax({
  				type : 'POST',
  				dataType : 'json',
  				url : 'baseNeiName.do',
  				async : false,
  				cache : false,
  				error : function(request) {
  					bootbox.alert({
  	     			  message: "请求异常",
  	     			  size: 'small'
  	     		  });
  				},
  				success : function(data) {
  					 for (var i=0;i<data.length;i++) { 					
  										$("#AbasenameID").after(
  										"<option value="+data[i].id+">"
  												+ data[i].name + "</option>");
  								
  				

  	 }
  				}
  			}); 
					  
	 
					//删除操作	 
					var flag=0;
					$('#delete').click(function() {
					  flag=0;
						$("#Repairmanage input[name='idname']").each(function () {
							if($(this).prop("checked")==true){
								flag=1;
					  			return false;
						}
					});
					if(flag==0){
						bootbox.alert({
							  message: "您还没有选择任何内容",
							  size: 'small'
						  });
								}
					else{
						bootbox.confirm({
							message: "确定删除？",
							size: 'small',
							buttons: {
								confirm: {
									label: '确定',
									className: 'btn-success'
								},
								cancel: {
									label: '取消',
									className: 'btn-danger'
								},
							},
							callback: function (result) {									
								if(result){									
									var deletstr = '(';//删除记录的格式(1,2,3,4,5)									
									var i=0;
									$("input[type='checkbox'][name='idname']:checked").each(function() {															
															if (i != 0) {
																deletstr = deletstr+ ','+ $(this).val();
															}
															else{
																deletstr = deletstr+ $(this).val();
																}
															i++;
														});
										deletstr = deletstr + ')';
										$.ajax({
											url : 'delmaintainapply.do',
											type : 'post',
											dataType : 'json',
											data : {
												"deletstr" : deletstr
											},
											success : function(msg) {
												bootbox.alert({
													message : msg.str,
													size : 'small'
												});
												applytable.draw(false);
											}
										});//end

								}
							}
						});
			
					}
					});
//点击增加清空数据函数
$("#ZJ").click(function(){
		$("#Aprojectname").val("");
		$("#Abasename").val("-1");
		$("#Aname").val("");
		$("#Atime").val("");
		$("#Abudget").val("");
		$("#Aaddress").val("");
		$("#Areason").val("");
		$("#file").val("");	
		

})					
//增加操作
$("#save").click(function(){
		if($("#Aprojectname").val()==""){
				bootbox.alert({
				message : "请填写项目名称",
				size : 'small'
			});	
			return;
		}
		else if($("#Abasename").val()=="-1"){
				bootbox.alert({
				message : "请选择基地名称",
				size : 'small'
				});	
				return;
		}					
		else if($("#Aname").val()==""){
				bootbox.alert({
				message : "请填写报修人",
				size : 'small'
				});	
				return;
		}
		else if($("#Abudget").val()==""){
				bootbox.alert({
				message : "请填写预算金额",
				size : 'small'
				});	
				return;
		}else if($("#ActualMoney").val()==""){
				bootbox.alert({
				message : "请填写实际金额",
				size : 'small'
				});	
				return;
		}
		else if($("Aaddress").val()==""){
				bootbox.alert({
				message : "请填写具体地址",
				size : 'small'
				});	
				return;
		}
		else if($("#Areason").val()==""){
				bootbox.alert({
				message : "请填写原因说明",
				size : 'small'
				});	
				return;
		}
		var dataFormatWeek=/^[0-9]+\.?[0-9]*$/;
		var value=$("#Abudget").val();
		value=value.trim();
			if (!dataFormatWeek.exec(value)) {
				bootbox.alert({
					message : "预算金额的格式不对，只能输入数字",
					size : 'small'
				});
				return;
			}
		var value2=$("#ActualMoney").val();
		value2=value2.trim();
		if (!dataFormatWeek.exec(value2)) {
			bootbox.alert({
				message : "实际金额的格式不对，只能输入数字",
				size : 'small'
			});
			return;
		}
		if(!flag1){
			bootbox.alert({
				message : "请上传正确的文件格式",
				size : 'small'
			});
			return;
		}
		if(!flag2){
			bootbox.alert({
				message : "上传的文件文件太大",
				size : 'small'
			});
			return;
		}
		$("#applyaddform").submit();
					
})
					 
//查看操作
$(document).on("click", "#checkdetale", function() {	
	
	var index=$(this).val();
	
	$("#Eprojectname").val(obj[index].pro_name);
	$("#Ebasename").val(obj[index].basename);
	$("#Ename").val(obj[index].username);
	$("#Etime").val(obj[index].apply_time);
	$("#Ebudget").val(obj[index].money);
	$("#Emoney").val(obj[index].actualmoney);
	$("#Eaddress").val(obj[index].address);
	$("#Ereason").val(obj[index].reason);
	var file=obj[index].file;
	if(file=="null"||file==""||file==null){
		$("#resourcetr").prop("hidden",true);
	}else{
		$("#resourcetr").prop("hidden",false);
		$("#Elink").prop("href",file);
	}	
	$("#edit").modal('show');
	
});
$("#saverun").click(function(){
				bootbox.confirm({
				message: "是否确认修改",
				buttons: {
						confirm: {
								label: 'Yes',
								className: 'btn-success'
								},
						cancel: {
								label: 'No',
								className: 'btn-danger'
								},
							},
						callback: function (result) {
								if(result){
									$("#applyeditform").submit();
									}
								}
							});
})
//文件上传限制								
$('.file').change(function() {    
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

				////全选反选
				var flag=0;
				$("#ck1").on("click", function () {
					if ($(this).prop("checked") === true) {
						$("#Repairmanage input[name='idname']").prop("checked", true);
						
					} else {
						$("#Repairmanage input[name='idname']").prop("checked", false);
						
					}					
				 });
				
$("#import").click(function (){//每次点击导出是清空数据
	$("#Sbasename").val("-1");
	$("#year").val("-1");
	$.ajax({
		url : 'getThoseYear.do',
		type : 'post',
		dataType : 'json',		
		success : function(data) {
			$("#year option:gt(0)").remove();
			$("#Sbasename option:gt(0)").remove();
			for (var i=0;i<data.list[0].length;i++) {
			   $("#yearId").after("<option value='"+data.list[0][i]+"'>"+data.list[0][i]+"</option>");
			 }
			 for (var i=0;i<data.list[1].length;i++) { 					
					$("#SbasenameID").after(
					"<option value="+data.list[1][i]+">"
							+ data.list[1][i] + "</option>");
			


                }
		}
	});
	
	});	

$("#export").click(function(){
	$("#exportmodal").modal('hide');
});

$("#year").change(function(){
	
	var year=$("#year").val();
	
	$("#Sbasename").find("option:gt(0)").remove();
		$.ajax({
				type : 'POST',
				dataType : 'json',
				url : 'basename.do',
				data:{"year":year},
				async : false,
				cache : false,
				error : function(request) {
					bootbox.alert({
	     			  message: "请求异常",
	     			  size: 'small'
	     		  });
				},
				success : function(data) {
					 for (var i=0;i<data.length;i++) { 					
										$("#SbasenameID").after(
										"<option value="+data[i]+">"
												+ data[i] + "</option>");
								
				

	         }
				}
			}); 
	
});
					 					 					
});



