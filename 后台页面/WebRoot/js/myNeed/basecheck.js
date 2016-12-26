$(document).ready(function() {
	
	var Spage = $('#basecheck').DataTable(
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
				{
					"mData" : "id",					
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "2%",
				}, { // aoColumns设置列时，不可以任意指定列，必须列出所有列。
					"mData" : "baseType",
					"orderable" : true, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "2%"
				}, {
					"mData" : "applyDept",
					"orderable" : true, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "6%",

				}, {
					"mData" : "landarea",
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "6%"
				}, 
				
				{
					"mData" : "buildingarea",
					"bSortable":true,
					"orderable" : true, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "10%"
				},
				{
					"mData" : "address",
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
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
					"orderable" : true, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "undertake",
					"visible":false,
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "material_path",
					"visible":false,
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "id",
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%",
					"render":function(data,type, row){
						return data="<button type='button' class='btn btn-warning btn-xs' data-id='"+ la_id
									+ "' id='frame1_scan'>查看</button>";
					}

				}

				],
				"columnDefs" : [ {
					"orderable" : false, // 禁用排序
					"targets" : [ 0 ], // 指定的列
					"data" : "id",
					"render" : function(data, type, row) {
						
						return '<input type="checkbox" value="'
								+ data
								+ '" name="idname" />';
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
	
});

////全选反选
$("#ck1").on("click",function() {
	
			if ($(this).prop("checked") == true) {
				$("#basecheck input[name='idname']").prop(
						"checked", true);
			} else {
				$("#basecheck input[name='idname']").prop(
						"checked", false);
			}
		});
$(".icon-filter").on("click", function() {	
	$('#hide_ul').toggle();
});