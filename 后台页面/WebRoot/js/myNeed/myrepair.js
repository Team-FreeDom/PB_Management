// JavaScript Document
var obj1=[];
var obj2=[];

	
$(".icon-filter").on("click", function () {
	$("#status").val("-2");	
	$('#hide_ul').toggle();
});
$(document).ready(function() {	
var page1=$('#myrepair').DataTable(
	{
		"serverSide" : true,
		"bSort": true,
		"bFilter": false,
		"aLengthMenu":[5,10,20,30], //动态指定分页后每页显示的记录数。
		"lengthChange":true, //是否启用改变每页显示多少条数据的控件
		"iDisplayLength" : 5,  //默认每页显示多少条记录
		"dom":'ftipr<"bottom"l>',
		"ajax" : {
					"url" : "Mymaintain.do",
					"type" : "POST"
				},
		"aoColumns" : [
			
			{
				"mData" : "pro_name",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "10%",
				},
			{
				"mData" : "basename",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
				"mData" : "username",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
				"mData" : "apply_time",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
				"mData" : "money",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
				"mData" : "descp",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
					"mData" : "actualmoney",					
					"orderable" : false, // 禁用排序
					"visible":false,
					"sDefaultContent" : "",
					"sWidth" : "6%",
					},
			{
				"mData" : "address",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				"sWidth" : "8%",
				},
			{
				"mData" : "reason",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				"sWidth" : "8%",
				},
			{
				"mData" : "file",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				"sWidth" : "8%",
				},
			{
				"mData" : "status",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				"render":function(data,type,row){					
						obj1.push(row);							
						if(data==13){
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
			  
	  
var page2=$('#myrepair2').DataTable(
	{
	"serverSide" : true,
	"bSort": true,
	"bFilter": false,
	"aLengthMenu":[5,10,20,30], //动态指定分页后每页显示的记录数。
	"lengthChange":true, //是否启用改变每页显示多少条数据的控件
	"iDisplayLength" :5,  //默认每页显示多少条记录
	"dom":'ftipr<"bottom"l>',
	"ajax" : {
					"url" : "Mymaintain2.do",
					"type" : "POST"
				},
		"aoColumns" : [
			
			{
				"mData" : "pro_name",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "10%",
				},
			{
				"mData" : "basename",					
				"orderable" : true, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
				"mData" : "username",					
				"orderable" : true, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
				"mData" : "apply_time",					
				"orderable" : true, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
				"mData" : "money",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
				"mData" : "descp",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
		    {
					"mData" : "actualmoney",					
					"orderable" : false, // 禁用排序
					"visible":false,
					"sDefaultContent" : "",
					"sWidth" : "6%",
				},
			{
				"mData" : "address",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				"sWidth" : "8%",
				},
			{
				"mData" : "reason",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				"sWidth" : "8%",
				},
			{
				"mData" : "file",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				"sWidth" : "8%",
				},
			{
				"mData" : "id",
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				"render":function(data,type, row){
							obj2.push(row);					
					return data='<span class="icon-search"  id="scan" value="'+(obj2.length-1)+'$2"></span>';
												
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
						object=obj1;
					}else{
						object=obj2;
					}
					var status=object[index].status;	
					$("#projectname").val(object[index].pro_name);
					$("#basename").val(object[index].basename);
					$("#name").val(object[index].username);
					$("#time").val(object[index].apply_time);
					$("#budget").val(object[index].money);
					$("#address").val(object[index].address);
					$("#reason").val(object[index].reason);
					$("#actualmoney").val(object[index].actualmoney);
					$("#linkaddress").prop("href",object[index].file);
					
					if(object[index].file=="null"||object[index].file==""||object[index].file==null){			
						$("#resourcetr1").prop("hidden",true); 
					}else{		
						$("#resourcetr1").prop("hidden",false); 
						$("#linkaddress").prop("href",object[index].file);	
					}
					
					$("#reason_0").html('');
					$("#hideReason").prop("hidden",true);
					if(status==12){
						$("#reason_0").html(object[index].refuse);
						$("#hideReason").prop("hidden",false);
					}
					
					$("#Checkdetail").modal('show');					
				});

//撤回操作
$(document).on("click", "#cancel", function() {
	var index=$(this).val();					
  	var basename=obj1[index].pro_name;
  	var userid=obj1[index].userid;
  	var info_str='[{userid:"'+userid+'",basename:"'+basename+'"}]';
  	var id=obj1[index].id;
	bootbox.confirm({							
							message: "是否确认撤回",
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
								if(result==true){//上传ID
							
								  	$.ajax({
								  		type : 'POST',
								  		//dataType : 'json',
								  		data:{
								  			"id":id,
								  			"infostr":info_str
								  		},
								  		url : 'recallmymaint.do',  
								  		async : false,
								  		cache : false,
								  		error : function(request) {
								  			bootbox.alert({
								  				message : "撤回失败",
								  				size : 'small'
								  			});								  		
								  		},
								  		success : function(msg) {								  	
								  			if(msg==0){
								  				bootbox.alert({
								  				message :"撤回失败请刷新页面",
								  				size : 'small'
								  			});
								  			}else{
								  				bootbox.alert({
									  				message :"撤回成功",
									  				size : 'small'
									  			});
								  			page1.draw(false);
								  			page2.draw(false);
								  			}
								  			
								  			
								  		}
								  	});										
								}
								}
							});

	});
	
//筛选操作
$(document).on("click", "#finish", function() {
		obj2=[];
		var status=$("#status option:selected").val();
		page2=$('#myrepair2').DataTable(
					{		
					"serverSide" : true,
					"bSort": true,
					"bFilter": false,
					"bDestroy":true,
					"aLengthMenu":[5,10,20,30], //动态指定分页后每页显示的记录数。
					"lengthChange":true, //是否启用改变每页显示多少条数据的控件
					"iDisplayLength" : 5,  //默认每页显示多少条记录
					"dom":'ftipr<"bottom"l>',
					"ajax" : {
								"data" : {"status" : status},																								
								"url" : "screen.do",
								"type" : "POST"
								},
	"aoColumns" : [
			{
				"mData" : "pro_name",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "10%",
				},
			{
				"mData" : "basename",					
				"orderable" : true, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
				"mData" : "username",					
				"orderable" : true, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
				"mData" : "apply_time",					
				"orderable" : true, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
				"mData" : "money",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
			{
				"mData" : "descp",					
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				},
		    {
					"mData" : "actualmoney",					
					"orderable" : false, // 禁用排序
					"visible":false,
					"sDefaultContent" : "",
					"sWidth" : "6%",
				},
			{
				"mData" : "address",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				"sWidth" : "8%",
				},
			{
				"mData" : "reason",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				"sWidth" : "8%",
				},
			{
				"mData" : "file",					
				"orderable" : false, // 禁用排序
				"visible":false,
				"sDefaultContent" : "",
				"sWidth" : "8%",
				},
			{
				"mData" : "id",
				"orderable" : false, // 禁用排序
				"sDefaultContent" : "",
				"sWidth" : "6%",
				"render":function(data,type, row){
					obj2.push(row);					
			     return data='<span class="icon-search"  id="scan" value="'+(obj2.length-1)+'$2"></span>';
										
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
			