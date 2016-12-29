// JavaScript Document
var obj = [];
$(document).ready(function() {
					// 分页表格
					var page = $('#baseMaintain').dataTable({
						
										"processing" : true,
										"serverSide" : true,
										"bSort" : false,
										"aLengthMenu" : [ 2, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
										"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
										"iDisplayLength" : 2, // 默认每页显示多少条记录
										"dom" : 'ftipr<"bottom"l>',
										"ajax" : {
											"url" : "",
											"type" : "POST"
										},
										"aoColumns" : [
												{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
													"mData" : "id",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "4%"
												},
												{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
													"mData" : "name",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "10%"
												},
												{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
													"mData" : "type",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%"
												},
												{
													"mData" : "applydp",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "10%",

												},
												{
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
													"visible" : false,
													"sWidth" : "6%"
												},
												{
													"mData" : "username",
													"orderable" : false, // 禁用排序
													"visible" : false,
													"sDefaultContent" : "",
													"sWidth" : "8%"
												},
												{
													"mData" : "phone",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												{
													"mData" : "major",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												{
													"mData" : "material_path",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												{
													"mData" : "userid",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												{
													"mData" : "time",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												{
													"mData" : "limitTime",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												{
													"mData" : "start",													
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},
												{
													"mData" : "id",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",
													"render" : function(data,type, row) {
														   obj = row;														
															return data = '<span id="update" class="icon-edit edit-color" value='+(obj.length-1)+'>修改</span>';
														
													}
												} ],

										"columnDefs" : [ {
											"orderable" : false, // 禁用排序
											"targets" : [ 0 ], // 指定的列
											"data" : "id",
											"render" : function(data, type, row) {
												
												return '<label><input type="checkbox" name="recordcheck" value="'+data+'" class="ck"></label>';
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
					
					 $.ajax({							
							url : '',
							async : true,
							type : "POST",
							dataType : "json",
							cache : false, 
							success : function(data) {									
								$("#daodept").after(
										"<option value="+data[0][i].aid+">"
												+ data[0][i].dept+ "</option>");
							},
							error : function(data) {
								alert("请求异常");
							}
						});
					 
					
					//点击删除按钮,判断是否已选择
					 $(document).on("click", "#delete", function() {	
						 
						 var chk_value =[];
							$('input[name="recordcheck"]:checked').each(function(){
							chk_value.push($(this).val());
							});							
							 if(chk_value.length==0)
							 {
								 bootbox.alert({
										message : "您还没有选择任何内容",
										size : 'small'
									});
								 return;
							  }
							 $("#deleteOneModal").modal('show');
							 
					 });	
					 
					 $(document).on("click", "#daoclose", function() {
						 $("#daobaseh").val("");
						 $("#daodepth").val("");
						 $("#daostarh").val("");
						 
					 });
					 
					//确认删除 
					 $(document).on("click", "#delSubmit", function() {	
						 
						 var recordstr='(';
						 $("input[type='checkbox'][name='recordcheck']:checked").each(function() {	
							 
							 if(i!=0){
									recordstr=recordstr+","+$(this).val();									
								}else{
									recordstr=recordstr+$(this).val();									
								}					
												
									i++;
						 });
						 
						 recordstr=recordstr+')';
						 
						 $.ajax({
								data : {
									"recordstr" : recordstr
								},
								url : '',
								async : true,
								type : "POST",
								dataType : "json",
								cache : false, 
								success : function(data) {									
									page.draw(false); 
								},
								error : function(data) {
									alert("请求异常");
								}
							});
					 });
					 
					 $(document).on("click", "#confirmButton", function() {	
						 
						var basetype=$("#daobaseh option:checked").val();
						var applydept=$("#daodepth option:checked").val();
						var star=$("#daostarh option:checked").val();
						
						 $.ajax({
								data : {
									"basetype" : basetype,
									"applydept":applydept,
									"star":star
								},
								url : '',
								async : true,
								type : "POST",
								dataType : "json",
								cache : false, 
								success : function(data) {									
									$("#export").modal('hide'); 
								},
								error : function(data) {
									alert("请求异常");
								}							
					 });
						 
					 });
					 
         $(document).on("click", "#closebase", function() {	
						 
				$("#basename").val("");
				$("#deptRadio[value='1']").prop("checked",true);
				$("#deptty").val("");
				$("#basetype0").val("");
				$("#textContent").html("");
				$("#limit-population").val("");
				$("#filed-area").val("");
				$("#building-area").val("");
				$("#baseaddress").val("");
				$("#personName").val("");
				$("#personTel").val("");
				$("#applyfile").val("");
				$("#majorSuo").html("");
					 });		 
					 					 
				
});



// //全选反选
$("#ck1").on("click",function() {
			if ($(this).prop("checked") == true) {
				$("#baseMaintain input[name='recordcheck']").prop(
						"checked", true);
			} else {
				$("#baseMaintain input[name='recordcheck']").prop(
						"checked", false);
			}
		});
					