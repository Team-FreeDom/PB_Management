
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
						"mData" : "statusid",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",
						"render":function(data,type, row){
							if(statusid==2){
								return data='<button type="button" class="btn btn-warning btn-xs" id="scan" value='+data+'>查看</button>'+
                                    '<button type="button" class="btn btn-danger btn-xs" id="cancel" value='+row.id+'>撤回</button>';
							}else{
								return data='<button type="button" class="btn btn-warning btn-xs" id="scan" value='+data+'&'+row.name+'>查看</button>';
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
			  
			  
			  var page2=$('#mybasetable2').dataTable(
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
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",

					}, {
						"mData" : "id",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : "",
						"sWidth" : "8%",
						"render":function(data,type, row){
							
								return data='<span class="icon-search" id="scan" value='+id+'></span>';
												
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
			  
            } );

$(".icon-filter").on("click", function () {
	$('#hide_ul').toggle();
});

$(document).on("click", "#scan", function() {
	
	var statusid=$(this).val();
	
	var basename=$(this).closest('tr').find('td:eq(0)').text();
	var basetype=$(this).closest('tr').find('td:eq(1)').text();
	var dept=$(this).closest('tr').find('td:eq(2)').text();
	var landarea=$(this).closest('tr').find('td:eq(3)').text();
	var buildingarea=$(this).closest('tr').find('td:eq(4)').text();
	var undertake=$(this).closest('tr').find('td:eq(5)').text();
	var address=$(this).closest('tr').find('td:eq(6)').text();
	var username=$(this).closest('tr').find('td:eq(7)').text();
	var userphone=$(this).closest('tr').find('td:eq(8)').text();
	var major=$(this).closest('tr').find('td:eq(9)').text();
	var resource=$(this).closest('tr').find('td:eq(10)').text();
	var settime=$(this).closest('tr').find('td:eq(11)').text();
	var limittime=$(this).closest('tr').find('td:eq(12)').text();	
	
	$("#basename").val(basename);
	$("#basetype").val(basetype);
	$("#dept0").val(dept);
	$("#landarea").val(landarea);
	$("#buildingarea").val(buildingarea);
	$("#undertakeCount").val(undertake);
	$("#username").val(username);
	$("#userphone").val(userphone);
	$("#major_oriented").val(major);
	$("#linkAddress").val(address);
	$("#resource").prop("href",resource);
	
	if(statusid==6){
		$("#setdate").val(settime);
		$("#validdate").val(limittime);
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
	var str=$(this).val();
	var position=str.indexOf('$');
	var id=str.substring(0,position);
	var basename=str.substring(position+1);
	var info_str='[{userid:'+
	$.ajax({
		type : 'POST',
		dataType : 'json',
		data:{"id":id},
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
		
		}

	});
	
});


			