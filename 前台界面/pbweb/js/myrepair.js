// JavaScript Document
var obj1=[];
var obj2=[];

$(document).ready(function() {
	
//点击筛选按钮并清空数据
$(".icon-filter").on("click", function () {
	$("#status").val("-1");
	$('#hide_ul').toggle();
});
				
var page1=$('#myrepair').dataTable(
	{
		"serverSide" : true,
		"bSort": false,
		"bFilter": false,
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
				"mData" : "projectname",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "basename",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "name",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "time",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "budget",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "status",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "money",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "address",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "reason",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "linkaddress",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "statusid",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				"render":function(data,type,row){					
						obj1.push(row);							
						if(statusid==2){
								return data='<button type="button" class="btn btn-warning btn-xs" id="scan" value="'+(obj1.length-1)+'$1">查看</button>'+
                                    '<button type="button" class="btn btn-danger btn-xs" id="cancel" value="'+(obj1.length-1)+'">撤回</button>';
							}else{
								return data='<button type="button" class="btn btn-warning btn-xs" id="scan" value="'+(obj1.length-1)+'$1">查看</button>';
							}
					}
					
				},
		
		
		],
	
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
			  
	  
var page2=$('#myrepair2').dataTable(
	{
	"serverSide" : true,
	"bSort": false,
	"bFilter": false,
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
				"mData" : "projectname",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "basename",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "name",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "time",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "budget",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "status",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "money",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			
			{
				"mData" : "address",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "reason",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "linkaddress",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				//"sWidth" : "",
				},
			{
				"mData" : "id",
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				//"sWidth" : "",
				"render":function(data,type, row){
							obj2.push(row);
								return data='<span class="icon-search" id="scan" value="'+(obj2.length-1)+'$2"></span>';
												
					}
				}
			],
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


//查看详情
$(document).on("click", "#scan", function() {	
					var str=$(this).attr("value");
					var position=str.indexOf('$');
					var index=str.substring(0,position);
					var tag=str.substring(position+1);
					var object=[];
					if(tag==1){
						object=obj;
					}else{
						object=obj2;
					}
						
					
					$("#projectname").val(object[index].projectname);
					$("#basename").val(object[index].basename);
					$("#name").val(object[index].name);
					$("#time").val(object[index].time);
					$("#budget").val(object[index].budget);
					$("#money").val(object[index].money);
					$("#address").val(object[index].address);
					$("#reason").val(object[index].reason);
					$("#linkaddress").prop("href",object[index].linkaddress);
					
					$("#Checkdetail").modal('show');
					
				});
//撤回操作
$(document).on("click", "#cancel", function() {
	var index=$(this).val();					
  	var basename=obj1[index].name;
  	var userid=obj1[index].userid;
  	var info_str='[{userid:"'+userid+'",basename:"'+basename+'"}]';
  	var id=obj1[index].id;
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
										$.ajax({
								  		type : 'POST',
								  		//dataType : 'json',
								  		data:{
								  			"id":id,
								  			"infostr":info_str
								  		},
								  		url : '',  
								  		async : false,
								  		cache : false,
								  		error : function(request) {
								  			alert("error");
								  		},
								  		success : function(msg) {
								  			bootbox.alert({
								  				message : msg,
								  				size : 'small'
								  			});
								  			page2.draw(false);
								  		}
								  	});					
										
									}
								}
							});

	});
	
//筛选操作
$(document).on("click", "#finish", function() {
		var obj2=[];
		var status=$("#status option:selected").val();
		page2=$('#myapply2').dataTable(
					{
					"bSort": false,
					"bFilter": false,
					"aLengthMenu":[2,4,6,8], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 2,  //默认每页显示多少条记录
					"bDestroy":true,
					"dom":'ftipr<"bottom"l>',
					"ajax" : {
								"data" : {
								"status" : status,
								},
								"url" : "",
								"type" : "POST"
								},
						"aoColumns" : [
							{
								"mData" : "projectname",					
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								//"sWidth" : "",
								},
							{
								"mData" : "basename",					
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								//"sWidth" : "",
								},
							{
								"mData" : "name",					
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								//"sWidth" : "",
								},
							{
								"mData" : "time",					
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								//"sWidth" : "",
								},
							{
								"mData" : "budget",					
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								//"sWidth" : "",
								},
							{
								"mData" : "status",					
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								//"sWidth" : "",
								},
							{
								"mData" : "money",					
								"orderable" : false, // 禁用排序
								"visible":false,
								"sDefaultContent" : "",
								//"sWidth" : "",
								},
							{
								"mData" : "address",					
								"orderable" : false, // 禁用排序
								"visible":false,
								"sDefaultContent" : "",
								//"sWidth" : "",
								},
							{
								"mData" : "reason",					
								"orderable" : false, // 禁用排序
								"visible":false,
								"sDefaultContent" : "",
								//"sWidth" : "",
								},
							{
								"mData" : "linkaddress",					
								"orderable" : false, // 禁用排序
								"visible":false,
								"sDefaultContent" : "",
								//"sWidth" : "",
								},
							{
								"mData" : "id",
								"orderable" : false, // 禁用排序
								"sDefaultContent" : "",
								//"sWidth" : "",
								"render":function(data,type, row){
											obj2.push(row);
												return data='<span class="icon-search" id="scan" value="'+(obj2.length-1)+'"></span>';
																
									}
								}
							],
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
 			$('#hide_ul').toggle();
 
	});
		
} );
