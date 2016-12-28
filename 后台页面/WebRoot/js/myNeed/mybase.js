var obj1=[];
var obj2=[];

$(document).ready(function() {	
				
             var page1=$('#mybasetable1').dataTable(
			  {
				  "aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
					"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
					"bSort" : true,
					"ordering":true,
					"serverSide" : true,
					"iDisplayLength": 5,				
					"dom" : 'tipr<"bottom"l>',
					"ajax" : {
						"url" : "",
						"type" : "POST"
					},
					"aoColumns" : [ 
					{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
						"mData" : "name",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "10%"
					}, { // aoColumns设置列时，不可以任意指定列，必须列出所有列。
						"mData" : "type",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					},{
						"mData" : "applydp",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "landarea",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					}, 
					
					{
						"mData" : "constructionarea",					
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					},
					{
						"mData" : "undertake",					
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					},
					{
						"mData" : "land_address",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"visible":false,
						"sWidth" : "6%"
					},
					{
						"mData" : "username",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%"
					}, {
						"mData" : "phone",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "major",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},
					{
						"mData" : "material_path",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},
					 {
						"mData" : "userid",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "time",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "limitTime",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "status",
						"visible":true,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "statusid",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",
						"render":function(data,type, row){
							obj1=row;
							if(statusid==2){
								return data='<button type="button" class="btn btn-warning btn-xs" id="scan" value="'+(obj1.length-1)+'">查看</button>'+
                                    '<button type="button" class="btn btn-danger btn-xs" id="cancel" value="'+(obj1.length-1)+'">撤回</button>';
							}else{
								return data='<button type="button" class="btn btn-warning btn-xs" id="scan" value="'+(obj1.length-1)+'">查看</button>';
							}							
						}
					}
					],					
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
			  
			  
			var  page2=$('#mybasetable2').dataTable(
			  {
				  "aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
					"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
					"bSort" : true,
					"ordering":true,
					"serverSide" : true,
					"iDisplayLength": 5,				
					"dom" : 'tipr<"bottom"l>',
					"ajax" : {
						"url" : "",
						"type" : "POST"
					},
					"aoColumns" : [ 
					{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
						"mData" : "name",
						"orderable" : true, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "10%"
					}, { // aoColumns设置列时，不可以任意指定列，必须列出所有列。
						"mData" : "type",
						"orderable" : true, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					},{
						"mData" : "applydp",
						"orderable" : true, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "landarea",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					}, 
					
					{
						"mData" : "constructionarea",					
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					},
					{
						"mData" : "undertake",					
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "6%"
					},
					{
						"mData" : "land_address",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"visible":false,
						"sWidth" : "6%"
					},
					{
						"mData" : "username",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%"
					}, {
						"mData" : "phone",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "major",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},
					{
						"mData" : "material_path",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},
					 {
						"mData" : "userid",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "time",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "limitTime",
						"visible":false,
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					},{
						"mData" : "status",
						"visible":true,
						"orderable" : true, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "id",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",
						"render":function(data,type, row){
							   obj2=row;
								return data='<span class="icon-search" id="scan" value="'+(obj2.length-1)+'"></span>';
												
						}
					}
					],					
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
			  
			  $(document).on("click", "#scan", function() {	
					var str=$(this).val();
					var position=str.indexOf('$');
					var index=str.substring(0,position);
					var tag=str.substring(position+1);
					var object=[];
					if(tag==1){
						object=obj;
					}else{
						object=obj2;
					}
						
					var statusid=object[index].statusid;	
					
					$("#basename").val(object[index].name);
					$("#basetype").val(object[index].type);
					$("#dept0").val(object[index].applydp);
					$("#landarea").val(object[index].landarea);
					$("#buildingarea").val(object[index].constructionarea);
					$("#undertakeCount").val(object[index].undertake);
					$("#username").val(object[index].username);
					$("#userphone").val(object[index].phone);
					$("#major_oriented").val(object[index].major);
					$("#linkAddress").val(object[index].land_address);
					$("#resource").prop("href",object[index].material_path);
					
					if(statusid==6){
						$("#setdate").val(object[index].time);
						$("#validdate").val(object[index].limitTime);
						$("#hidecol").prop("hidden",false);
					}
					$("#fontTable").modal('show');
					
				});

				$(document).on("click", "#cancel", function() {
					$("#cancelSubmit").val($(this).val());
					$("#cancelOneModal").modal('show');	
				});

				//撤回按钮，传给后台id
				$(document).on("click", "#cancelOneModal", function() {
					var index=$(this).val();	
				  	var basename=obj[index].name;
				  	var userid=obj[index].userid;
				  	var info_str='[{userid:"'+userid+'",basename:"'+basename+'"}]';
				  	var id=obj[index].id;
				  	$.ajax({
				  		type : 'POST',
				  		dataType : 'json',
				  		data:{"id":id,
				  			  "infostr":info_str
				  		},
				  		url : '',   //
				  		async : false,
				  		cache : false,
				  		error : function(request) {
				  			alert("error");
				  		},
				  		success : function(data) {
				  			
				  			bootbox.alert({
				  				message : "成功撤回",
				  				size : 'small'
				  			});
				  			page1.draw(false);
				  		}

				  	});
				  	
				  });
				
				
				$(document).on("click", "#finish", function() {
					
					var status=$("#status option:selected").val();					
					 page2=$('#mybasetable2').dataTable(
							  {
								  "aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
									"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
									"bSort" : true,
									"ordering":true,
									"serverSide" : true,
									"iDisplayLength": 5,
									"bDestroy":true,
									"dom" : 'tipr<"bottom"l>',
									"ajax" : {
										"url" : "",
										"type" : "POST",
										"data":{"status":status}
									},
									"aoColumns" : [ 
									{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
										"mData" : "name",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "10%"
									}, { // aoColumns设置列时，不可以任意指定列，必须列出所有列。
										"mData" : "type",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "6%"
									},{
										"mData" : "applydp",
										"orderable" : true, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									}, {
										"mData" : "landarea",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "6%"
									}, 
									
									{
										"mData" : "constructionarea",					
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "6%"
									},
									{
										"mData" : "undertake",					
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "6%"
									},
									{
										"mData" : "land_address",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"visible":false,
										"sWidth" : "6%"
									},
									{
										"mData" : "username",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%"
									}, {
										"mData" : "phone",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									}, {
										"mData" : "major",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									},
									{
										"mData" : "material_path",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									},
									 {
										"mData" : "userid",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									},{
										"mData" : "time",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									},{
										"mData" : "limitTime",
										"visible":false,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									},{
										"mData" : "status",
										"visible":true,
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",

									}, {
										"mData" : "id",
										"orderable" : false, // 禁用排序
										"sDefaultContent" : "",
										"sWidth" : "8%",
										"render":function(data,type, row){
											   obj2=row;
												return data='<span class="icon-search" id="scan" value="'+(obj2.length-1)+'"></span>';
																
										}
									}
									],					
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
					 $(".icon-filter").click();
					
				});
				
			  
            } );

$(".icon-filter").on("click", function () {
	$("#status").val("-1");
	$('#hide_ul').toggle();
});




	



			