var obj=[];
$(document).ready(function() {
	
	var Spage = $('#basecheck').DataTable(
			{
				"aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
				"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
				"bSort" : true,
				"ordering":true,
				"serverSide" : true,
				"bFilter": true,
				"dom": 'frtip<"bottom"l>',
				"iDisplayLength": 5,			
				"ajax" : {
					"url" : "getBaseCheck.do",
					"type" : "POST"
				},
				"aoColumns" : [ 
				{
					"mData" : "id",					
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "2%",
				}, { // aoColumns设置列时，不可以任意指定列，必须列出所有列。
					"mData" : "name",
					"orderable" : true, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%"
				}, { // aoColumns设置列时，不可以任意指定列，必须列出所有列。
					"mData" : "type",
					"orderable" : true, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%"
				},{
					"mData" : "applydp",
					"orderable" : true, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "landarea",
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%"
				}, 
				
				{
					"mData" : "constructionarea",					
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%"
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

				}, {
					"mData" : "undertake",
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

				}, {
					"mData" : "id",
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%",
					"render":function(data,type,row){					
						obj.push(row);							
						return data="<button type='button' class='btn btn-warning btn-xs' value='"+(obj.length-1)
									+ "' id='scanDetail'>查看</button>";
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
								+ '" data-id="'+(obj.length)+'" name="idname" />';
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
	
	//获取申报部门
	 $.ajax({
			url : 'getApplyDept.do',
			type : 'post',
			dataType : 'json',			
			success : function(data) {						         
				for ( var i=0;i<data.length;i++) {
					$("#deptS").after(
							"<option value="+data[i].aid+">"
									+ data[i].applydp + "</option>");
					
				}
				}
			
		});	
	 
	//同意申请
	// //////////状态值1： 2： 3： 4： 。。。。。。。
	$('#confirmDate').click(function() {
		
		                var date=$("#valideDate option:selected").val();					
						var i=0;
						var recordstr='(';
						var infostr="[";
						var index;
						$("input[type='checkbox'][name='idname']:checked").each(function() {					
							index=$(this).data("id");
							userid =obj[index].userid ;
							if(i!=0){
								recordstr=recordstr+","+$(this).val();
								infostr=infostr+',{userid:"'+userid+'",basename:"'+ obj[index].name+'"}';
								
							}else{
								recordstr=recordstr+$(this).val();
								infostr=infostr+'{userid:"'+userid+'",basename:"'+ obj[index].name+'"}';
							}					
											
								i++;
							});
					
	                    recordstr=recordstr+')';
	                    infostr=infostr+']';
	                    
	                    $.ajax({
							url : '',
							type : 'post',
							dataType : 'json',
							data : {
								"resordstr" : recordstr,
								"infostr" : infostr,
								"date":date
							},
							success : function(msg) {						
								$("#valideDate").val("10");
								$("#applyConfirm").modal('hide');
								Spage.draw(false);
								}
							
						});
					});
	
	//拒绝申请
////////////状态值1： 2： 3： 4： 。。。。。。。
$('#certain').click(function() {	

			var i=0;
			var recordstr='(';
			var infostr="[";
			var reason=$("#reason").val();
			var index;
			$("input[type='checkbox'][name='idname']:checked").each(function() {					
				index=$(this).data("id");
				userid = obj[index].userid;
				if(i!=0){
					recordstr=recordstr+","+$(this).val();
					infostr=infostr+',{userid:"'+userid+'",basename:"'+ obj[index].name+'"}';
					
				}else{
					recordstr=recordstr+$(this).val();
					infostr=infostr+'{userid:"'+userid+'",basename:"'+ obj[index].name+'"}';
				}					
								
					i++;
				});
		
         recordstr=recordstr+')';
         infostr=infostr+']';                
         $.ajax({
				url : '',
				type : 'post',
				dataType : 'json',
				data : {
					"recordstr" : recordstr,
					"infostr" : infostr,
					"reason":reason,
				},
				success : function(msg) {						
					$("#reason").val("");
					$("#reasonConfirm").modal('hide');
					Spage.draw(false);
					}
				
			});

     	            	
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






$(document).on("click", "#close", function() {	
	$("#reason").val("");
	
});

$(document).on("click", "#close1", function() {	
	$("#valideDate").val("10");
	
});

$(document).on("click", "#refuse", function() {	
	
	var chk_value =[];
	$('input[name="idname"]:checked').each(function(){
	chk_value.push($(this).val());
	});	
	
	 if(chk_value.length==0)
	 {
		 bootbox.alert({
				message : "请至少选择一项",
				size : 'small'
			});
		 return;
	  }
	$("#reasonConfirm").modal('show');
	
});

$(document).on("click", "#confirm", function() {	
	
	var chk_value =[];
	$('input[name="idname"]:checked').each(function(){
	chk_value.push($(this).val());
	});	
	
	 if(chk_value.length==0)
	 {
		 bootbox.alert({
				message : "请至少选择一项",
				size : 'small'
			});
		 return;
	  }
	$("#applyConfirm").modal('show');
	
});

$(document).on("click", "#scanDetail", function() {	
	
	var index=$(this).val();
	
	$("#basename").val(obj[index].name);
	$("#basetype").val(obj[index].type);
	$("#dept0").val(obj[index].applydp);
	$("#landarea").val(obj[index].landarea);
	$("#buildingarea").val(obj[index].constructionarea);
	$("#undertakeCount").val(obj[index].undertake);
	$("#username").val(obj[index].username);
	$("#userphone").val(obj[index].phone);
	$("#major_oriented").html(obj[index].major);
	$("#linkAddress").html(obj[index].land_address);
	$("#resource").prop("href",obj[index].material_path);
	
	$("#scan").modal('show');
	
});

$("#submitS").click(function() {	
	var dept=$('#deptSh').children('option:selected').val();	
	obj=[];	
	$('#basecheck').DataTable( //getXUBaseCheck.do
			{
				"aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
				"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
				"bSort" : true,
				"ordering":true,
				"serverSide" : true,
				"iDisplayLength": 5,	
				"bDestroy":true,
				"processing":true,
				"dom" : 'tipr<"bottom"l>',
				"ajax" : {
					"url" : "getXUBaseCheck.do",
					"type" : "POST",
					"data":{"dept":dept}
				},
				"aoColumns" : [ 
				{
					"mData" : "id",					
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "2%",
				}, { // aoColumns设置列时，不可以任意指定列，必须列出所有列。
					"mData" : "name",
					"orderable" : true, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%"
				}, { // aoColumns设置列时，不可以任意指定列，必须列出所有列。
					"mData" : "type",
					"orderable" : true, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%"
				},{
					"mData" : "applydp",
					"orderable" : true, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%",

				}, {
					"mData" : "landarea",
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%"
				}, 
				
				{
					"mData" : "constructionarea",					
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%"
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

				}, {
					"mData" : "undertake",
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

				}, {
					"mData" : "id",
					"orderable" : false, // 禁用排序
					"sDefaultContent" : "",
					"sWidth" : "8%",
					"render":function(data,type, row){
						obj.push(row);	
						return data="<button type='button' class='btn btn-warning btn-xs' value='"+(obj.length-1)
									+ "' id='scanDetail'>查看</button>";
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
								+ '" data-id="'+(obj.length)+'" name="idname" />';
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
	$('#hide_ul').toggle(100);
	
});

