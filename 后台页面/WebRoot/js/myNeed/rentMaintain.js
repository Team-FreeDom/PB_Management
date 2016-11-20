$(document).ready(function() {
    	
	$('#fieldrent_maintain').DataTable(
			{
				"aLengthMenu" : [ 2, 4, 6, 8, 10 ], //动态指定分页后每页显示的记录数。
				"lengthChange" : true, //是否启用改变每页显示多少条数据的控件
				"bSort" : false,				
				"iDisplayLength" : 8, //默认每页显示多少条记录
				"dom" : 'ftipr<"bottom"l>',
				"ajax" : {
					"url" : "landRentInfo.do",
					"type" : "POST"
				},
				"aoColumns" : [
						{
							"mData" : "id",
							"orderable" : true, // 禁用排序
							"sDefaultContent" : "",
							"sWidth" : "6%",							
						},
						{
							"mData" : "startTime",
							"orderable" : true, // 禁用排序
							"sDefaultContent" : "",
							"sWidth" : "6%"

						},
						{
							"mData" : "endTime",
							"orderable" : true, // 禁用排序
							"sDefaultContent" : "",
							"sWidth" : "6%"
						},
						{
							"mData" : "bname",
							"orderable" : false, // 禁用排序
							"sDefaultContent" : "",
							"sWidth" : "10%"
						},
						{
							"mData" : "lid",
							"orderable" : true, // 禁用排序
							"sDefaultContent" : "",
							"sWidth" : "8%"
						},
						{
							"mData" : "name",
							"orderable" : true, // 禁用排序
							"sDefaultContent" : "",
							"sWidth" : "8%"
						},
						{
							"mData" : "deptName",
							"orderable" : true, // 禁用排序
							"sDefaultContent" : "",
							"sWidth" : "8%"
						},
						{
							"mData" : "times",
							"orderable" : true, // 禁用排序
							"sDefaultContent" : "",
							"sWidth" : "8%"
						},
						{
							"mData" : "planting",
							"orderable" : true, // 禁用排序
							"sDefaultContent" : "",
							"sWidth" : "8%"
						},
						{
							"mData" : "lr_id",
							"orderable" : false, // 禁用排序
							"sDefaultContent" : '',
							"sWidth" : "5%",
							"render" : function(data, type, row) { //render改变该列样式,4个参数，其中参数数量是可变的。

								return data = '<span class="glyphicon glyphicon-pencil" data-id='+data+' data-toggle="modal" data-target="#myModal3"></span>';

							}
						}
				//data指该行获取到的该列数据
				//row指该行，可用row.name或row[2]获取第3列字段名为name的值
				//type调用数据类型，可用类型“filter”,"display","type","sort",具体用法还未研究
				//meta包含请求行索引，列索引，tables各参数等信息

				],
				
				"columnDefs" : 
					[{
						"orderable" : false, // 禁用排序
						"targets" : [0], // 指定的列
						"data" : "id",
						"render" : function(data, type, row) {
							data=row.lr_id;
							return '<input type="checkbox" value="'+ data + '" name="idname"  />';
						}
					}],


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


/* 全选，反选按钮 */
/*
 * allCkBox2(); function allCkBox2(id){ var tableBox =
 * document.getElementById(id||"fieldrent_maintain"), ck =
 * tableBox.getElementsByClassName("ck"), ckAll =
 * tableBox.getElementsByClassName("ck-all")[0], ckRe =
 * tableBox.getElementsByClassName("ck-re")[0]; ckAll.onchange = function(){
 * allCk(this.checked); }; ckRe.onchange = function(){ reCk(); }; function
 * allCk(bool){ for(var i =0; i<ck.length;i++){ ck[i].checked = bool; } }
 * 
 * function reCk(){ for(var i =0; i<ck.length;i++){ ck[i].checked ?
 * ck[i].checked = false : ck[i].checked = true; } } }
 */