// JavaScript Document
$(document).ready(function() {
	/*筛选的显示与收藏*/
				$(".icon-filter").on("click", function () {
					$('.hide_ul').toggle();
				});
				
			/*全选和反选*/
			
				//审批中表格的全选反选
				$(".ck1").on("click", function () {
					if ($(this).prop("checked") === true) {
						$("#Approveing input[name='allcheckbox']").prop("checked", true);
						
					} else {
						$("#Approveing input[name='allcheckbox']").prop("checked", false);
						
					}
					$(".ck2").prop("checked", false);
				 });
				 
				  $(".ck2").click(function () {//反选  
                		$("#Approveing input[name='allcheckbox']").each(function () {  
                    	$(this).prop("checked", !$(this).prop("checked"));
						 
                	}); 
					$(".ck1").prop("checked", false); 
           		 }); 
				 
				 //维修中表格的全选反选
				 $(".ck1").on("click", function () {
					if ($(this).prop("checked") === true) {
						$("#Repairing input[name='allcheckbox']").prop("checked", true);
						
					} else {
						$("#Repairing input[name='allcheckbox']").prop("checked", false);
						
					}
					$(".ck2").prop("checked", false);
				 });
				 
				  $(".ck2").click(function () {//反选  
                		$("#Repairing input[name='allcheckbox']").each(function () {  
                    	$(this).prop("checked", !$(this).prop("checked"));
						 
                	}); 
					$(".ck1").prop("checked", false); 
           		 }); 
				 			
	//审核中表格
              var Approvetable =$('#Approveing').dataTable(
			  {
				  "processing": true,
        		  "serverSide": true,
				  "bSort": false,
				  "aLengthMenu":[5,7,9,12], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 5,  //默认每页显示多少条记录
					"dom":'ftipr<"bottom"l>',
					"ajax" : {
							"url" : "",
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
						"sSearch": "查询：",
                        "infoFiltered": "(从 _MAX_ 条记录过滤)",
						"oPaginate": {
						   "sFirst": "首页",
						   "sPrevious": " 上一页 ",
						   "sNext": " 下一页 ",
						   "sLast": " 尾页 "
					   }
                    }
			  });
			  
			  //同意申请
			  var flag=0;
			  $("#agree").click(function(){
				  flag=0;
					$("#Approveing input[name='idname']").each(function () {
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
							size: 'small',
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
									
									var agreestr = '(';//同意记录的格式(1,2,3,4,5)
									var keyid;
									$("input[type='checkbox'][name='idname']:checked").each(function() {
															keyid = $(this).data("id");
															if (i != 0) {
																agreestr = agreestr+ ','+ $(this).val();
															}
															else{
																agreestr = agreestr+ $(this).val();
																}
															i++;
														});
										agreestr = agreestr + ')';
										$.ajax({
											url : '',
											type : 'post',
											dataType : 'json',
											data : {
												"agreestr" : agreestr,
											},
											success : function(msg) {
												bootbox.alert({
													message : msg.str,
													size : 'small'
												});
												Approvetable.draw(false);
											}
										});//end

								}
							}
						});
			
					}
				  
				  });
			  //拒绝申请
			  $("#refuse").click(function (){
				  flag=0;
					$("#Approveing input[name='idname']").each(function () {
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
							size: 'small',
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
									
									var refusestr = '(';//拒绝记录的格式(1,2,3,4,5)
									var keyid;
									$("input[type='checkbox'][name='idname']:checked").each(function() {
															keyid = $(this).data("id");
															if (i != 0) {
																refusestr = refusestr+ ','+ $(this).val();
															}
															else{
																refusestr = refusestr+ $(this).val();
																}
															i++;
														});
										refusestr = refusestr + ')';
										$.ajax({
											url : '',
											type : 'post',
											dataType : 'json',
											data : {
												"refusestr" : refusestr,
											},
											success : function(msg) {
												bootbox.alert({
													message : msg.str,
													size : 'small'
												});
												Approvetable.draw(false);
											}
										});//end

								}
							}
						});
			
					}
				  
				  });
			  //维修中——完成操作
			  $("#finished").click(function (){
				  $(".inputmargin").remove();
				  $("input[type='checkbox'][name='allcheckbox']:checked").each(function() {
					 $("#modalbody").append('<input type="text" class="inputmargin form-control" value="基地的名称" disabled>');
						});
					$("#myfinishedModal").modal('show');
				  });
			  
		//维修中表格	  
			  $('#Repairing').dataTable(
			  {
				  "bSort": false,
				  "aLengthMenu":[2,4,6,8], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 2,  //默认每页显示多少条记录
					"dom":'ftipr<"bottom"l>',
                    "language": {
                        "lengthMenu": "每页 _MENU_ 条记录",
                        "zeroRecords": "没有找到记录",
                        "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                        "infoEmpty": "无记录",
                        "infoFiltered": "(从 _MAX_ 条记录过滤)",
						"sSearch": "查询：",
						"oPaginate": {
						   "sFirst": "首页",
						   "sPrevious": " 上一页 ",
						   "sNext": " 下一页 ",
						   "sLast": " 尾页 "
					   }
                    }
			  });
//筛选功能
$(document).on("click","#finish",function() {
	var basenameid = $("#basenameid").value;
	var timeid = $("#timeid").value;
	var nameid = $("#nameid").value;
	$('#tableCheck').DataTable(
								{
									"aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
									"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
									"bSort" : true,
									"serverSide" : true,
									"iDisplayLength": 5,// //默认每页显示多少条记录
									"bDestroy" : true,
									"dom" : 'tipr<"bottom"l>',

									"ajax" : {
										"data" : {
										"basename" : basenameid,
										"name" : nameid,
										"time" : timeid,
											},
										"url" : "",
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
									}],
									"columnDefs" : [ {
									"orderable" : false, // 禁用排序
									"targets" : [ 0 ], // 指定的列
									"data" : "id",
									"render" : function(data,type,row) {
												data = row.la_id;
												return '<input type="checkbox" value="'+ data+ '" name="idname" class="ck"  />';
																}
															} ],
															"language" : {
																"lengthMenu" : "每页 _MENU_ 条记录",
																"zeroRecords" : "没有找到记录",
																"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
																"infoEmpty" : "无记录",
																"infoFiltered" : "(从 _MAX_ 条记录过滤)",
																"sSearch" : "模糊查询：",
																"oPaginate" : {
																	"sFirst" : "首页",
																	"sPrevious" : " 上一页 ",
																	"sNext" : " 下一页 ",
																	"sLast" : " 尾页 "
																}
															}
														});
										$('.hide_ul').toggle();
									});




            } );