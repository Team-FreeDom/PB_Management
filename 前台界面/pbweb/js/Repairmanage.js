// JavaScript Document
function a(){
	alert("on")
	}						
$(document).ready(function() {
	
			////全选反选
			var flag=0;
				$("#ck1").on("click", function () {
					if ($(this).prop("checked") === true) {
						$("#Repairmanage input[name='allcheckbox']").prop("checked", true);
						
					} else {
						$("#Repairmanage input[name='allcheckbox']").prop("checked", false);
						
					}
					$("#ck2").prop("checked", false);
				 });
				 
				  $("#ck2").click(function () {//反选  
                		$("#Repairmanage input[name='allcheckbox']").each(function () {  
                    	$(this).prop("checked", !$(this).prop("checked"));
						 
                	}); 
					$("#ck1").prop("checked", false); 
           		 }); 
				 
				//点击增加清空数据函数
				 $("#ZJ").click(function(){
					 
					$("#Aprojectname").val("");
			        $("#Abasename").val("1");
			        $("#Aname").val("");
					$("#Atime").val("");
					$("#Abudget").val("");
					$("#Aaddress").val("");
					$("#Areason").val("");
					 })
					 
					 
				

				//分页表格 
              var applytable =$('#Repairmanage').dataTable(
			  {
				  "processing": true,
        		  "serverSide": true,
				  "bSort": false,
				  "aLengthMenu":[5,10,20,30], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 5,  //默认每页显示多少条记录
					"dom":'ftipr<"bottom"l>',
					"ajax" : {
							"url" : "xxx.do",
							"type" : "POST"
						},  
					"aoColumns" : [                                        
									{
										"mData" : "number",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										
									},
									{ //aoColumns设置列时，不可以任意指定列，必须列出所有列。
										"mData" : "projectname",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
									},
									{
										"mData" : "basename",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",

									},
									{
										"mData" : "name",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",

									},
									{
										"mData" : "time",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
									},
									{
										"mData" : "budget",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
									},
									{
										"mData" : "status",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
									},
									
									{
										"mData" : "id",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : '',
										"render" : function(
												data, type,
												row) { 
											var data=row.id;
											return data = '<button type="button"  id='
												+ row.id
												+ ' onclick="editOne(this)" class="btn btn-warning btn-xs" data-id='+data+' id="frame1_edit" data-target="#edit" data-toggle="modal">修改</button>';
										}
									}
							],
					
					
					columnDefs :
							[{
								"orderable" : false, // 禁用排序
								"targets" : [0], // 指定的列
								"data" : "number",
								"render" : function(data, type, row) {
									 data=row.id;
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
					  
	  //获取基地列表
        $.ajax({
 			type : 'POST',
 			dataType : 'json',
 			url : '.do',
 			async : false,
 			cache : false,
 			error : function(request) {
 				bootbox.alert({
         			  message: "error",
         			  size: 'small'
         		  });
 			},
 			success : function(data) {
 				 for (var i=0;i<data[0].length;i++) {
 					$("#Ebasename").after(
 							"<option value="+data[0][i].name+">"
 									+data[0][i].name+"</option>");
 									$("#Abasename").after(
 				 							"<option value="+data[0][i].name+">"
 				 									+data[0][i].name+"</option>");

 				 }
 			}
 		}); 
		
					//删除操作	 
					var flag=0;
					$('#deleteOne').click(function() {
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
							message: "是否确认删除",
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
									$.ajax({
										url: 'deInfo.do',
										type: 'post',
										data: $("#formApplyInfo").serializeArray(),
										success: function(msg) {
											 bootbox.alert({
											  message: msg,
											  size: 'small'
										  });
											applytable.draw(false);
										  $("#ck2").prop("checked", false);
										  $("#ck1").prop("checked", false);
										}
									});
								}
							}
						});
			
					}
					});
					
				 //增加操作
				 $("#save").click(function(){
					 
					if($("#Aprojectname").val()==""){
						bootbox.alert({
						message : "请填写项目名称",
						size : 'small'
						});	
						return 0;
						}
					else if($("#Abasename").val()=="1"){
						bootbox.alert({
						message : "请选择基地名称",
						size : 'small'
						});	
						return 0;
						}
					
					else if($("#Aname").val()==""){
						bootbox.alert({
						message : "请填写申报人姓名",
						size : 'small'
						});	
						return 0;
						}
					else if($("#Abudget").val()==""){
						bootbox.alert({
						message : "请填写预算金额",
						size : 'small'
						});	
						return 0;
						}
					else if($("Aaddress").val()==""){
						bootbox.alert({
						message : "请填写具体地址",
						size : 'small'
						});	
						return 0;
						}
					else if($("#Areason").val()==""){
						bootbox.alert({
						message : "请填写原因说明",
						size : 'small'
						});	
						return 0;
						}
					$("#applyaddform").submit();
					/*bootbox.alert({
         			  message: "提交成功",
         			  size: 'small'
         		  		});*/
					 })
					 
				//修改操作
				function editOne(obj) {
						var id = obj.id;
							$.ajax({
								type : 'POST',
								data : {
									"iddetail" : id
								},
								dataType : 'json',
								url : '.do',
								async : false,
								cache : false,
								error : function(request) {
									bootbox.alert({
										  message: "error",
										  size: 'small'
									  });
								},
								success : function(data) {
										$("#Eprojectname").val(data[0].projectname);
										$("#Ebasename").val(data[0].basename);
						  				$("#Ename").val(data[0].name);//部门
										$("#Etime").val(data[0].time);//出生日期
						  				$("#Ebudget").val(data[0].budget);
						  				$("#Eaddress").val(data[0].address);
										$("#Ereason").val(data[0].reason);
									}
							});
							$("#saverun").click(function(){
								$("#applyeditform").submit();
								})
			
				}
					 					
});