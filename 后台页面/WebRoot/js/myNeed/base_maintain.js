// JavaScript Document
var obj = [];
$(document)
		.ready(
				function() {
					// 分页表格
					var page = $('#baseMaintain')
							.DataTable(
									{

										"processing" : true,
										"serverSide" : true,
										"bSort" : false,
										"aLengthMenu" : [ 5, 10, 15, 20 ], // 动态指定分页后每页显示的记录数。
										"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
										"iDisplayLength" : 5, // 默认每页显示多少条记录
										"ordering":true,
										"filter" : true,
										"dom" : 'ftipr<"bottom"l>',
										"ajax" : {
											"url" : "sendBaseinfo.do",
											"type" : "POST"
										},
										"aoColumns" : [
												{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
													"mData" : "id",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "4%"
												},
												{
													"mData" : "buildtime",
													"orderable" : true, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "10%",

												},
												{
													"mData" : "id",
													"orderable" : true, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "6%",
													"render":function(data,
															type, row) {
														return data='#'+data;
													}

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
													"mData" : "star",
													"orderable" : true, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",
													"render" : function(data,
															type, row) {
														var str1 = "";
														var str2 = "";
														for (var i = 0; i < data; i++) {
															str1 = str1
																	+ "<span class='icon-star star-flag' name='color' id='color' value='"
																	+(i+1)
																	+"'></span>";
														}
														for (var i = 0; i < 5 - data; i++) {
															str2 = str2
																	+ "<span class='icon-star-empty star-flag' name='nocolor' id='nocolor' value='"
																	+(data+i+1)
																	+"'></span>";															
														}
														return str1 + str2;

													}

												},
												{
													"mData" : "land_address",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"visible" : false,
													"sWidth" : "6%"
												},
												{
													"mData" : "resperson",
													"orderable" : false, // 禁用排序
													"visible" : false,
													"sDefaultContent" : "",
													"sWidth" : "8%"
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
													"mData" : "facemajor",
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
													"mData" : "valid_date",
													"visible" : false,
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",

												},												
												{
													"mData" : "id",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",
													"render" : function(data,
															type, row) {
														obj.push(row);														
														return data = '<span id="updateDetail" class="icon-edit edit-color" value="'
																+ (obj.length - 1)
																+ '"></span>';

													}
												} ],

										"columnDefs" : [ {
											"orderable" : false, // 禁用排序
											"targets" : [ 0 ], // 指定的列
											"data" : "id",
											"render" : function(data, type, row) {											
												return '<label><input type="checkbox" name="recordcheck" value="'
														+ data
														+ '" class="ck" id="checkHa"></label>';
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

					/*
					 * 1.获得导出的申报部门 2.获得导出的基地类型
					 * 
					 */
					
					$.ajax({
						url : 'getManyinfo.do',
						async : true,
						type : "POST",
						dataType : "json",
						cache : false,
						success : function(data) {
							
							for (var i = 0; i < data[0].length; i++) {
								$("#daodept")
										.after(
												"<option class='rest' value="
														+ data[0][i].aid + ">"
														+ data[0][i].dept
														+ "</option>");
								$("#shaiDepth")
										.after(
												"<option class='rest' value="
														+ data[0][i].aid
														+ " class='dee'>"
														+ data[0][i].dept
														+ "</option>");

							}

							for (var i = 0; i < data[1].length; i++) {
								$("#daobase")
										.after(
												"<option value="
														+ data[1][i].id + ">"
														+ data[1][i].name
														+ "</option>");
								$("#shaiTypeh")
										.after(
												"<option value="
														+ data[1][i].id + ">"
														+ data[1][i].name
														+ "</option>");

							}

						},
						error : function(data) {
							alert("请求异常");
						}
					});
					
					// star星级点击的实时改变
					$(document).on("click", "#color", function() {
						
						var starNum=$(this).attr('value');
						$("#starget .star-flag").each(function(){
							if($(this).attr('value')>starNum){
								$(this).removeClass("icon-star");
								$(this).addClass("icon-star-empty");
								$(this).prop("id", "nocolor");
							}
						});
					});

					$(document).on("click", "#nocolor", function() {
						
						var starNum=$(this).attr('value');
						$("#starget .star-flag").each(function(){
							$(this).removeClass("icon-star-empty");
							$(this).addClass("icon-star");
							$(this).prop("id", "color");
							if(--starNum==0){
								return false;
							}
						});
												
					});
					
					$(document).on("click","#delateStar",function(){
						$("#starget .star-flag").each(function(){
							
								$(this).removeClass("icon-star");
								$(this).addClass("icon-star-empty");
								$(this).prop("id", "nocolor");
							
						});		   
					});

					// 点击删除按钮,判断是否已选择
					$("#delete").click(function() {

						var chk_value = [];
						$('input[name="recordcheck"]:checked').each(function() {
							chk_value.push($(this).val());
						});
						if (chk_value.length == 0) {
							bootbox.alert({
								message : "您还没有选择任何内容",
								size : 'small'
							});
							return;
						}
						$("#deleteOneModal").modal('show');

					});

					$(document).on("click", "#daoclose", function() {
						$("#daobaseh").val("-1");
						$("#daodepth").val("-1");
						$("#daostarh").val("-1");

					});

					$("#closeimport").click(function() {

						$("#fileResource").val("");
					});
					
					$(document).on("click", "#ZJ", function() {	
						var now= new Date();
						var date1 = now.getFullYear()+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate();
						 var date2= (now.getFullYear()+1)+"-"+((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+now.getDate();
						 $(".start_time").val(date1);
						 $(".end_time").val(date2);
						$("#add").css("display","block");
					});
					
					$(document).on("click", "#closebas", function() {					
						$("#add").css("display","none");
						$("#add input").val('');
						$(".colle").prop('checked',true);
						$("#add select").val('');
					});

					// 点击修改图标，填充修改模态框中的内容
					$(document).on("click", "#updateDetail", function() {						
						var index = $(this).attr("value");
						
						$("#baseid").val('#'+obj[index].id);
						$("#basenamed").val(obj[index].name);
						$("#basetyped").val(obj[index].type);
						$("#dept0d").val(obj[index].applydp);
						$("#landaread").val(obj[index].landarea);
						$("#buildingaread").val(obj[index].constructionarea);
						$("#undertakeCountd").val(obj[index].undertake);
						$("#usernamed").val(obj[index].username);
						$("#userphoned").val(obj[index].phone);
						$("#major_orientedd").html(obj[index].facemajor);
						$("#linkAddressd").html(obj[index].land_address);
						$("#resourced").prop("href", obj[index].material_path);
						
						if(obj[index].material_path=="null"||obj[index].material_path==""){			
							$("#resourcetr").prop("hidden",true); 
						}else{		
							$("#resourcetr").prop("hidden",false); 
							$("#resourced").prop("href", obj[index].material_path);
						}
						$("#setdated").val(obj[index].buildtime);
						$("#adddate").val(obj[index].endtime);						
						$("#starget").html("<span id='delateStar' class='icon-remove-sign'></span>"+$(this).closest('tr').find('td:eq(9)').html());
						$("#personDuty").val(obj[index].resperson);
						
						$("#edit").modal('show');

					});

					// 确认删除
					$("#delSubmit").click(function() {										
										var recordstr = '(';
										var i = 0;									
										$(
												"input[type='checkbox'][name='recordcheck']:checked")
												.each(
														function() {

															if (i != 0) {
																recordstr = recordstr
																		+ ","
																		+ $(
																				this)
																				.val();
															} else {
																recordstr = recordstr
																		+ $(
																				this)
																				.val();
															}

															i++;
														});

										recordstr = recordstr + ')';										
										$.ajax({
											data : {
												"recordstr" : recordstr
											},
											url : 'delBaseinfo.do',
											async : true,
											type : "POST",
											dataType : "json",
											cache : false,
											success : function(data) {
												$("#deleteOneModal").modal('hide');
												getExitDept();
												page.draw(false);
											},
											error : function(data) {
												alert("请求异常");
											}
										});
									});

					// 确认导出
					$("#confirmButton").click(function() {
					$("#export").modal('hide');
                    $("#daoclose").click();
					});
					
					//确认修改
					$("#saverun").click(function() {
						var baseid=$("#baseid").val();
						baseid=baseid.substring(1);
						var i=0;
						$("#starget .icon-star").each(function() {
							i++;
						});						
						var star=i;
						var adddate=$("#adddate").val();
						/*var matchstr=/^[0-9]*$/;
						if(!matchstr.test(adddate)){
							bootbox.alert({
								message : "填写的续期必须为数字",
								size : 'small'
							});
							return;
						}
						*/
						$.ajax({
							data : {
								"baseid" : baseid,
								"star" : star,
								"adddate" : adddate
							},
							url : 'updateBaseInfo.do',
							async : true,
							type : "POST",
							dataType : "json",
							cache : false,
							success : function(data) {
								$("#edit").modal('hide');
								$("#adddate").val("");
								page.draw(false);
							},
							error : function(data) {
								alert("请求异常");
							}
						});
						
						
					});

					$("#closebase").click(function() {

						$("#basename").val("");
						$("#deptRadio[value='1']").prop("checked", true);
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
					
					$(document).on("click", "#cleark", function() {	
						$("#adddate").val("");
					});

					$("#finishshai").click(function() {
                            obj=[];
										var basetype = $(
												"#shaiType option:selected")
												.val();
										var dept = $(
												"#shaiDept option:selected")
												.val();
										var star = $(
												"#starLink option:selected")
												.val();

										page = $('#baseMaintain')
												.DataTable(
														{

															"processing" : true,
															"serverSide" : true,
															"bSort" : false,
															"bDestroy" : true,
															"aLengthMenu" :[ 5, 10, 15, 20 ], // 动态指定分页后每页显示的记录数。
															"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
															"iDisplayLength" : 5, // 默认每页显示多少条记录
															"ordering":true,
															"dom" : 'ftipr<"bottom"l>',
															"ajax" : {
																"url" : "getshaiBaseinfo.do",
																"type" : "POST",
																"data" : {
																	"basetype" : basetype,
																	"dept" : dept,
																	"star" : star
																}
															},
															"aoColumns" : [
																	{ // aoColumns设置列时，不可以任意指定列，必须列出所有列。
																		"mData" : "id",
																		"orderable" : false, // 禁用排序
																		"sDefaultContent" : "",
																		"sWidth" : "4%"
																	},
																	{
																		"mData" : "buildtime",
																		"orderable" : true, // 禁用排序
																		"sDefaultContent" : "",
																		"sWidth" : "8%",

																	},
																	{
																		"mData" : "id",
																		"orderable" : true, // 禁用排序
																		"sDefaultContent" : "",
																		"sWidth" : "8%",
																		"render":function(data,
																				type, row) {
																			return data='#'+data;
																			}

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
																	},{
																		"mData" : "star",
																		"orderable" : true, // 禁用排序
																		"sDefaultContent" : "",
																		"sWidth" : "8%",
																		"render" : function(data,
																				type, row) {
																			var str1 = "";
																			var str2 = "";
																			for (var i = 0; i < data; i++) {
																				str1 = str1
																						+ "<span class='icon-star' name='color' id='color'></span>";
																			}
																			for (var i = 0; i < 5 - data; i++) {
																				str2 = str2
																						+ "<span class='icon-star-empty' name='nocolor' id='nocolor'></span>";															
																			}
																			return str1 + str2;

																		}

																	},
																	{
																		"mData" : "land_address",
																		"orderable" : false, // 禁用排序
																		"sDefaultContent" : "",
																		"visible" : false,
																		"sWidth" : "6%"
																	},
																	{
																		"mData" : "resperson",
																		"orderable" : false, // 禁用排序
																		"visible" : false,
																		"sDefaultContent" : "",
																		"sWidth" : "8%"
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
																		"mData" : "facemajor",
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
																		"mData" : "valid_date",
																		"visible" : false,
																		"orderable" : false, // 禁用排序
																		"sDefaultContent" : "",
																		"sWidth" : "8%",

																	},
																	
																	{
																		"mData" : "id",
																		"orderable" : false, // 禁用排序
																		"sDefaultContent" : "",
																		"sWidth" : "8%",
																		"render" : function(
																				data,
																				type,
																				row) {
																			obj
																					.push(row);
																			return data = '<span id="updateDetail" class="icon-edit edit-color" value="'
																					+ (obj.length - 1)
																					+ '"></span>';
																			

																		}
																	} ],

															"columnDefs" : [ {
																"orderable" : false, // 禁用排序
																"targets" : [ 0 ], // 指定的列
																"data" : "id",
																"render" : function(
																		data,
																		type,
																		row) {

																	return '<label><input type="checkbox" name="recordcheck" value="'
																			+ data
																			+ '" class="ck" id="checkHa"></label>';
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

										$(".icon-filter").click();

									});

				});

// //全选反选
$("#ck1").on("click", function() {
	if ($(this).prop("checked") == true) {
		$("#baseMaintain input[name='recordcheck']").prop("checked", true);
	} else {
		$("#baseMaintain input[name='recordcheck']").prop("checked", false);
	}
});

$(".icon-filter").on("click", function() {
	recovery();
	$('#hide_ul').toggle();
});

function recovery() {
	$("#shaiType").val("-1");
	$("#shaiDept").val("-1");
	$("#starLink").val("-1");

}

function getExitDept(){
	$(".rest").remove();
	$.ajax({
		url : 'getExistDept.do',
		async : true,
		type : "POST",
		dataType : "json",
		cache : false,
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				$("#daodept")
						.after(
								"<option value="
										+ data[i].aid + ">"
										+ data[i].dept
										+ "</option>");
				$("#shaiDepth")
						.after(
								"<option value="
										+ data[i].aid
										+ " class='dee'>"
										+ data[i].dept
										+ "</option>");

			}
			},
		error : function(data) {
			alert("请求异常");
		}
	});
}
