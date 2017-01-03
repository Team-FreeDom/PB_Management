
var obj=[];//表格中每一行（row）数组
var obj2=[];
$(document).ready(function() {
/*筛选的显示与收藏*/
$(".icon-filter").on("click", function () {
	$("#searchbase").val('1');
	$("#searchname").val("");
	$('.hide_ul').toggle();
});
			
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
			$("#Repairing input[name='allcheckbox2']").prop("checked", true);
		} else {
			$("#Repairing input[name='allcheckbox2']").prop("checked", false);
		}
		$(".ck2").prop("checked", false);
});
				 
$(".ck2").click(function () {//反选  
        $("#Repairing input[name='allcheckbox2']").each(function () {  
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
										"mData" : "id",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										
									},
									{ 
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
										"mData" : "address",
										"orderable" : true, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									{
										"mData" : "reason",
										"orderable" : true, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									{
										"mData" : "linkaddress",
										"orderable" : true, // 禁用排序
										"visible" :false,
										"sDefaultContent" : "",
									},
									
									{
										"mData" : "id",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : '',
										"render":function(data,type,row){					
										obj.push(row);							
										return data="<span type='button' class='icon-search' value='"+(obj.length-1)
										+ "' id='scanDetail'>查看</span>";
										}
									}
							],
							
							"columnDefs" :
							[{
								"orderable" : false, // 禁用排序
								"targets" : [0], // 指定的列
								"data" : "number",
								"render" : function(data, type, row) {
									return '<input type="checkbox" value="'
								+ data
								+ '" data-id="'+(obj.length)+'" name="idname" class="ck"/>';
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
//获取基地名称列表
$.ajax({
	url : '',
	type : 'post',
	dataType : 'json',			
	success : function(data) {						         
		for ( var i=0;i<data.length;i++) {
				$("#searchbaseid").after("<option value="+data[i].aid+">"+ data[i].applydp + "</option>");
				$("#searchbaseid2").after("<option value="+data[i].aid+">"+ data[i].applydp + "</option>");
				}
		}
});	

//查看详情
$(document).on("click", "#scanDetail", function() {	
	
	var index=$(this).val();
	
	$("#projectname").val(obj[index].projectname);
	$("#basename").val(obj[index].basename);
	$("#name").val(obj[index].name);
	$("#time").val(obj[index].time);
	$("#budget").val(obj[index].budget);
	$("#address").val(obj[index].address);
	$("#reason").val(obj[index].reason);
	$("#linkAddress").prop("href",obj[index].linkaddress);
	
	$("#Checkdetal").modal('show');
	
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
							message: "是否同意申请",
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
			  $("#saverefuse").click(function (){
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
							message: "是否拒绝申请",
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
				  
$("#saverefuse").click(function (){
	$(".Rtr").remove();
	$("input[type='checkbox'][name='allcheckbox']:checked").each(function() {
	index=$(this).data("id");
	$("#refusetable").append('<tr class="Rtr"><td>基地名称：<td><td><input type="text" class="form-control" value="'+obj[index].basename+'" disabled></td>'
	+'<td>申请人: </td><td><input type="text" class="form-control" value="'+obj[index].name+'" disabled></td>'
	+'<td>拒绝理由: </td><td><textarea row=1 col=1 id="reason"></textarea></td>'+'</tr>');
		});
	$("#refuseModal").modal('show');
	})	
				  
//维修中——完成操作
$("#finished").click(function (){
		$(".Rtr").remove();
		$("input[type='checkbox'][name='allcheckbox2']:checked").each(function() {
			index=$(this).data("id");
			$("#finishtable").append('<tr class="Rtr"><td>基地名称：</td><td><input type="text" value="'+obj[index].basename+'" class="form-control" disabled></td>'
			+'<td>申请人: </td><td><input type="text" class="form-control"  value="'+obj[index].name+'" disabled></td>'
			+'<td>实际金额: </td><td><input class="form-control"></td>'+'</tr>');
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
	"ajax" : {
			"url" : "",
			"type" : "POST"
			},
	"aoColumns" : [
			{
			"mData" : "id",
			"orderable" : true, // 禁用排序
			"sDefaultContent" : "",
			},
			{
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
			"orderable" : true, // 禁用排序
			"sDefaultContent" : "",
			},
			{
			"mData" : "status",
			"orderable" : true, // 禁用排序
			"sDefaultContent" : "",
			},
			{
			"mData" : "reason",
			"orderable" : true, // 禁用排序
			"visible" :false,
			"sDefaultContent" : "",
			},
			{
			"mData" : "address",
			"orderable" : true, // 禁用排序
			"visible" :false,
			"sDefaultContent" : "",
			},
			{
			"mData" : "linkaddress",
			"orderable" : true, // 禁用排序
			"visible" :false,
			"sDefaultContent" : "",
			},
			{
			"mData" : "id",
			"orderable" : false, // 禁用排序
			"sDefaultContent" : '',
			"render":function(data,type,row){					
			obj.push(row);							
			return data="<span type='button' class='icon-search' value='"+(obj.length-1)+ "' id='scanDetail'>查看</span>";
				}
			}
			],
		"columnDefs" : [ {
			"orderable" : false, // 禁用排序
			"targets" : [ 0 ], // 指定的列
			"data" : "id",
			"render" : function(data,type,row) {
					data = row.la_id;
					return '<input type="checkbox" value="'+ data+ '" name="idname" class="ck"  />';
					}
			} ],
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

//筛选功能1
$(document).on("click","#finish",function() {
	var obj=[];
	var basenameid = $("#searchbase").val();
	var timeid = $("#demo").val();
	var nameid = $("#searchname").val();
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
			"mData" : "id",
			"orderable" : true, // 禁用排序
			"sDefaultContent" : "",
			},
			{
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
			"orderable" : true, // 禁用排序
			"sDefaultContent" : "",
			},
			{
			"mData" : "status",
			"orderable" : true, // 禁用排序
			"sDefaultContent" : "",
			},
			{
			"mData" : "reason",
			"orderable" : true, // 禁用排序
			"visible" :false,
			"sDefaultContent" : "",
			},
			{
			"mData" : "address",
			"orderable" : true, // 禁用排序
			"visible" :false,
			"sDefaultContent" : "",
			},
			{
			"mData" : "linkaddress",
			"orderable" : true, // 禁用排序
			"visible" :false,
			"sDefaultContent" : "",
			},
			{
			"mData" : "id",
			"orderable" : false, // 禁用排序
			"sDefaultContent" : '',
			"render":function(data,type,row){					
			obj.push(row);							
			return data="<span type='button' class='icon-search' value='"+(obj.length-1)+ "' id='scanDetail'>查看</span>";
				}
			}
			],
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
//筛选功能2
$(document).on("click","#finish2",function() {
	var obj=[];
	var basenameid = $("#searchbase2").val();
	var timeid = $("#demo2").val();
	var nameid = $("#searchname2").val();
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
			"mData" : "id",
			"orderable" : true, // 禁用排序
			"sDefaultContent" : "",
			},
			{
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
			"orderable" : true, // 禁用排序
			"sDefaultContent" : "",
			},
			{
			"mData" : "status",
			"orderable" : true, // 禁用排序
			"sDefaultContent" : "",
			},
			{
			"mData" : "reason",
			"orderable" : true, // 禁用排序
			"visible" :false,
			"sDefaultContent" : "",
			},
			{
			"mData" : "address",
			"orderable" : true, // 禁用排序
			"visible" :false,
			"sDefaultContent" : "",
			},
			{
			"mData" : "linkaddress",
			"orderable" : true, // 禁用排序
			"visible" :false,
			"sDefaultContent" : "",
			},
			{
			"mData" : "id",
			"orderable" : false, // 禁用排序
			"sDefaultContent" : '',
			"render":function(data,type,row){					
			obj.push(row);							
			return data="<span type='button' class='icon-search' value='"+(obj.length-1)+ "' id='scanDetail'>查看</span>";
				}
			}
			],
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