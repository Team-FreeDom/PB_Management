// JavaScript Document
$(document).ready(function() {
	/*筛选的显示与收藏*/
				$(".icon-filter").on("click", function () {
					$('.hide_ul').toggle();
				});
				
	/*全选和反选*/
				var flag=0;
				$("#ck1").on("click", function () {
					if ($(this).prop("checked") === true) {
						$("#Approveing input[name='allcheckbox']").prop("checked", true);
						
					} else {
						$("#Approveing input[name='allcheckbox']").prop("checked", false);
						
					}
					$("#ck2").prop("checked", false);
				 });
				 
				  $("#ck2").click(function () {//反选  
                		$("#Approveing input[name='allcheckbox']").each(function () {  
                    	$(this).prop("checked", !$(this).prop("checked"));
						 
                	}); 
					$("#ck1").prop("checked", false); 
           		 }); 
				 			
	//审核中表格
              var Approvetable =$('#Approveing').dataTable(
			  {
				  "processing": true,
        		  "serverSide": true,
				  "bSort": false,
				  "bFilter": false,
				  "aLengthMenu":[5,7,9,12], //动态指定分页后每页显示的记录数。
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
						"oPaginate": {
						   "sFirst": "首页",
						   "sPrevious": " 上一页 ",
						   "sNext": " 下一页 ",
						   "sLast": " 尾页 "
					   }
                    }
			  });
			  
			  //同意申请
			  $("#agree").click(function(){
				  alert("进入函数");
				  
				  });
			  //拒绝申请
			  $("#refuse").click(function (){
				  alert("go to refuse funtion!!!");
				  
				  });
			  //维修中——完成操作
			  $("#finished").click(function (){
				  alert("go to finished function");
				  });
			  
		//维修中表格	  
			  $('#Repairing').dataTable(
			  {
				  "bSort": false,
				  "bFilter": false,
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
						"sSearch": "模糊查询：",
						"oPaginate": {
						   "sFirst": "首页",
						   "sPrevious": " 上一页 ",
						   "sNext": " 下一页 ",
						   "sLast": " 尾页 "
					   }
                    }
			  });
			  
            } );