// JavaScript Document
var obj=[];
var flag1=true;
var flag2=true;
var tag = true;
var tag1 = true;
var tag2=true;
$(document).ready(function() {
				//分页表格 
              var applytable =$('#major').DataTable(
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
							"url" : "query_majors.do",
							"type" : "POST"
						},  
					"aoColumns" : [                                        
									{
										"mData" : "mid",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										//"sWidth" : "2%",
										
									},
									{ //aoColumns设置列时，不可以任意指定列，必须列出所有列。
										"mData" : "dept",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										//"sWidth" : "5%",
									},
									{
										"mData" : "mid",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										//"sWidth" : "2%",

									},
									{
										"mData" : "mname",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										//"sWidth" : "5%",

									},
									
									{
										"mData" : "id",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : '',
										"render":function(data,type,row){					
											obj.push(row);							
											return data="<button type='button' class='btn btn-warning btn-xs' value='"+(obj.length-1)
														+ "' id='checkdetale1'>修改</button>";
										}
									}
							],
					
					
					"columnDefs" :
							[{
								"orderable" : false, // 禁用排序
								"targets" : [0], // 指定的列
								"data" : "mid",
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
              
              //学院列表
              $.ajax({
  				type : 'POST',
  				dataType : 'json',
  				url : 'BaseApplyAllInfo.do',
  				async : false,
  				cache : false,
  				error : function(request) {
  					bootbox.alert({
  	     			  message: "请求异常",
  	     			  size: 'small'
  	     		  });
  				},
  				success : function(data) {
  					for ( var i=0;i<data[0].length;i++){
						$("#deptSelect1").after(
								"<option value="+data[0][i].aid+">"
										+ data[0][i].dept + "</option>");
					}
  				}
  			}); 
					  
	 
					//删除操作	 
					var flag=0;
					$('#delete').click(function() {
					  flag=0;
						$("#major input[name='idname']").each(function () {
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
									var deletstr = "('";//删除记录的格式(1,2,3,4,5)									
									var i=0;
									$("input[type='checkbox'][name='idname']:checked").each(function() {															
															if (i != 0) {
																deletstr = deletstr+ "','"+ $(this).val();
															}
															else{
																deletstr = deletstr+ $(this).val();
																}
															i++;
														});
										deletstr = deletstr + "')";
										$.ajax({
											url : 'delmajor.do',
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
		if(!tag){		
			 bootbox.alert({
					message : "该专业编号已存在，请重新输入",
					size : 'small'
				});
			 return;
		}
		if(!tag2){		
			 bootbox.alert({
					message : "该专业名称已存在，请重新输入",
					size : 'small'
				});
			 return;
		}
		if($("#deptSelectOne1").val()==""){
				bootbox.alert({
				message : "请填写学院名称",
				size : 'small'
			});	
			return;
		}
		else if($("#deptSelectOne1").val()=="-1"){
				bootbox.alert({
				message : "请选择学院名称",
				size : 'small'
				});	
				return;
		}					
		else if($("#mid").val()==""){
				bootbox.alert({
				message : "请填专业编号",
				size : 'small'
				});	
				return;
		}
		else if($("#mname").val()==""){
				bootbox.alert({
				message : "请填写专业名称",
				size : 'small'
				});	
				return;
		}
		
		$("#applyaddform").submit();
					
})
			
//检查专业id，名称是否已存在
$(document).on("blur", "#mid", function() {
	var value=$(this).val();
	if(value!=""){
		
		 $.ajax({
				type : 'POST',
				data:{
					"mid":value
				},
				dataType:'text',
				url : 'CheckmName.do', 
				async : false,
				cache : false,
				error : function(request) {
					bootbox.alert({
						message : "请求异常",
						size : 'small'
					});
				},
				success : function(data) {	
					if(data=="false"){						
						$("#display").html("");
						tag=true;
					}else{						
						$("#display").html("该专业已存在，请重新输入!");
						$("#mid")[0].focus();
						tag=false;
					}
				}

			}); 
	}
	
});
//检查专业名字
$(document).on("blur", "#mname", function() {
	var value=$(this).val();
	if(value!=""){
		
		 $.ajax({
				type : 'POST',
				data:{
					"mname1":value
				},
				dataType:'text',
				url : 'CheckmName1.do', 
				async : false,
				cache : false,
				error : function(request) {
					bootbox.alert({
						message : "请求异常",
						size : 'small'
					});
				},
				success : function(data) {	
					if(data=="false"){						
						$("#display2").html("");
						tag2=true;
					}else{						
						$("#display2").html("该专业名称已存在，请重新输入!");
						$("#mname")[0].focus();
						tag2=false;
					}
				}

			}); 
	}
	
});
//详情
$(document).on("blur", "#Mname", function() {
	var value=$(this).val();
	if(value!=""){		
		 $.ajax({
				type : 'POST',
				data:{
					"mname1":value
				},
				dataType:'text',
				url : 'CheckmName1.do', 
				async : false,
				cache : false,
				error : function(request) {
					bootbox.alert({
						message : "请求异常",
						size : 'small'
					});
				},
				success : function(data) {	
					if(data=="false"){						
						$("#display1").html("");
						tag1=true;
					}else{						
						$("#display1").html("该专业名称已存在，请重新输入!");
						$("#Mname")[0].focus();
						tag1=false;
					}
				}

			}); 
	}
	
});
//修改操作
$(document).on("click", "#checkdetale1", function() {	
	
	var index=$(this).val();
	
	$("#Mdept").val(obj[index].dept);
	$("#Mid").val(obj[index].mid);
	$("#Mname").val(obj[index].mname);
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
						if ($("#Mdept").val() == "") {
							bootbox.alert({
								message : "学院名称不能为空",
								size : 'small'
							});
							return;
						} else if ($("#Mid").val() == "") {
							bootbox.alert({
								message : "专业编号不能为空",
								size : 'small'
							});
							return;
						} else if ($("#Mname").val() == "") {
							bootbox.alert({
								message : "专业名称不能为空",
								size : 'small'
							});
							return;
						} else if(!tag1){
							bootbox.alert({
								message : "该专业名称已存在，请重新输入",
								size : 'small'
							});
						 return;
						}
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
									$("#majoreditform").submit();
									}
								}
							});
})


				////全选反选
				var flag=0;
				$("#ck1").on("click", function () {
					if ($(this).prop("checked") === true) {
						$("#major input[name='idname']").prop("checked", true);
						
					} else {
						$("#major input[name='idname']").prop("checked", false);
						
					}					
				 });
				
//$("#year").change(function(){
//	
//	var year=$("#year").val();
//	
//	$("#Sbasename").find("option:gt(0)").remove();
//		$.ajax({
//				type : 'POST',
//				dataType : 'json',
//				url : 'basename.do',
//				data:{"year":year},
//				async : false,
//				cache : false,
//				error : function(request) {
//					bootbox.alert({
//	     			  message: "请求异常",
//	     			  size: 'small'
//	     		  });
//				},
//				success : function(data) {
//					 for (var i=0;i<data.length;i++) { 					
//										$("#SbasenameID").after(
//										"<option value="+data[i]+">"
//												+ data[i] + "</option>");
//								
//				
//
//	         }
//				}
//			}); 
//	
//});
					 					 					
});
