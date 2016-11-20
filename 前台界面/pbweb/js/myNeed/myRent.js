$(document).ready(function() {
	
	/*myRent.jspҳ��js----start*/
					$('#table1').dataTable({
										"bSort" : false,
										"bFilter" : false,
										"aLengthMenu" : [ 2, 4, 5, 8, 10 ], //动态指定分页后每页显示的记录数。
										"lengthChange" : true, //是否启用改变每页显示多少条数据的控件										
										"iDisplayLength" : 8,  //默认每页显示多少条记录
										"bDestroy":true,
										"dom" : 'ftipr<"bottom"l>',

										"ajax" : {
											"url" : "myRentFont.do",
											"type" : "POST"
										},

										"aoColumns" : [

												{
													"mData" : "startTime",
													"orderable" : true, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "6%",

												},
												{
													"mData" : "tenancy",
													"orderable" : true,  // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "6%"
												},
												{
													"mData" : "bname",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "6%"
												},
												{
													"mData" : "lid",
													"orderable" : true, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%"
												},
												{
													"mData" : "descp",
													"orderable" : false, // 禁用排序
													"sDefaultContent" : "",
													"sWidth" : "8%",
													"render" : function(data,
															type, row) {
														var status = row.status;
														if (status == 5
																|| status == 8
																|| status == 3) {
															return data = '<span>不通过</span>';
														} else if (status == 6) {
															return data = '<span>ͨ通过</span>';
														} else {
															return data = '<span>'
																	+ data
																	+ '</span>';
														}
													}
												},

												{
													"mData" : "status",
													"orderable" : false,  // 禁用排序
													"sDefaultContent" : '',
													"sWidth" : "10%",
													"render" : function(data,
															type, row) {  //render改变该列样式,4个参数，其中参数数量是可变的。
														// alert(row.la_id);
														var la_id = row.la_id;
														if (data == 9) {
															return data = '<button type="button"  id='
																	+ row.la_id
																	+ ' onclick="editOne(this)" class="btn btn-warning btn-xs" data-id='
																	+ la_id
																	+ ' id="frame1_edit">修改</button><button type="button" class="btn btn-danger btn-xs" data-id='
																	+ la_id
																	+ ' id="frame1_delete">删除</button> <button type="button" class="btn btn-success btn-xs" data-id='
																	+ la_id
																	+ '  id="frame1_submit">提交</button>';

														} else if (data == 2|| data == 4) {
															return data = '<button type="button" id='
																	+ row.la_id
																	+ ' onclick="scanOne(this)" class="btn btn-warning btn-xs" data-id='
																	+ la_id
																	+ ' id="frame1_scan">查看</button><button type="button" class="btn btn-danger btn-xs" data-id='
																	+ la_id
																	+ ' id="frame1_cancel" >撤回</button>';
														} else if (data == 1) {
															return data = '<button type="button"  id='
																	+ row.la_id
																	+ ' onclick="payForOne(this)" class="btn btn-warning btn-xs" data-id='
																	+ la_id
																	+ ' id="frame1_scan">查看</button><button type="button" class="btn btn-danger btn-xs" data-id='
																	+ la_id
																	+ ' id="frame1_cancel" >撤回</button>';
														} else {
															return data = '<button type="button" id='
																	+ row.la_id
																	+ ' onclick="scanOne(this)" class="btn btn-warning btn-xs" data-id='
																	+ la_id
																	+ ' id="frame1_scan">查看</button>';
														}

													}
												}
												//data指该行获取到的该列数据
												//row指该行，可用row.name或row[2]获取第3列字段名为name的值
												//type调用数据类型，可用类型“filter”,"display","type","sort",具体用法还未研究
												//meta包含请求行索引，列索引，tables各参数等信息

										],

										"language" : {
											"lengthMenu" : "每页 _MENU_ 条记录",
											"zeroRecords" : "没有找到记录",
											"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
											"infoEmpty" : "无记录",
											"infoFiltered" :  "(从 _MAX_ 条记录过滤)",
											"sSearch" :  "模糊查询：",
											"oPaginate" : {
												"sFirst" : "首页",
												"sPrevious" : " 上一页 ",
												"sNext" : " 下一页 ",
												"sLast" : " 尾页 "
											}
										}

									});
					
					$('#table2').DataTable({
						"aLengthMenu" : [ 2, 4, 6, 8,
											10 ], //动态指定分页后每页显示的记录数。
									"lengthChange" : true, //是否启用改变每页显示多少条数据的控件
									"bSort" : false,									
									"iDisplayLength" : 4, //默认每页显示多少条记录
									"dom" : 'ftipr<"bottom"l>',
									"ajax" : {
										"url" : "selfApply.do",
										"type" : "POST"
								},

								"aoColumns" : [
										
										{
											"mData" : "startTime",
											"orderable" : true, // 禁用排序
											"sDefaultContent" : "",
											"sWidth" : "6%",

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
											"mData" : "descp",
											"orderable" : false, // 禁用排序
											"sDefaultContent" : "",
											"sWidth" : "8%",
											"render" : function(
													data, type,
													row) {

												var status = row.status;
												if (status == 6) {
													return data = '<span>'
															+ data
															+ '</span>';
												} else {
													return data = '<span>申请失败</span>';
												}
											}
										},
										{
											"mData" : "la_id",
											"orderable" : false, // 禁用排序
											"sDefaultContent" : '',
											"sWidth" : "5%",
											"render" : function(
													data, type,
													row) { //render改变该列样式,4个参数，其中参数数量是可变的。

												return data = '<span data-id='
														+ data
														+ ' id='
														+ data
														+ ' onclick="scanOne(this)"  class=" glyphicon glyphicon-search"></span>';

											}
										}
										//data指该行获取到的该列数据
										//row指该行，可用row.name或row[2]获取第3列字段名为name的值
										//type调用数据类型，可用类型“filter”,"display","type","sort",具体用法还未研究
										//meta包含请求行索引，列索引，tables各参数等信息

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
			
					$.ajax({
						type : 'POST',
						dataType : 'json',
						url : 'getDept.do',
						async : false,
						cache : false,
						error : function(request) {
							alert("error");
						},
						success : function(data) {
							var i = 0;
							for ( var item in data) {

								$("#manydept").after(
										"<option value="+data[i].aid+">"
												+ data[i].dept + "</option>");

								i++;
							}

						}

					});
					
					$.ajax({
						type : 'POST',
						dataType : 'json',
						url : 'baseInfo.do',
						async : false,
						cache : false,
						error : function(request) {
							alert("error");
						},
						success : function(data) {
							var i = 0;
							for ( var item in data) {

								$("#selectallbase").after(
										"<option value="+data[i].bname+">"
												+ data[i].bname + "</option>");

								i++;
							}

						}

					});
					
});
			
					/*土地租赁内容修改------start*/
					function submitUpdate() {
						$('#updateForm').submit();
					}

					function check() {

						var lid = document.getElementById("lidr").value;
						var dept = document.getElementById("deptme").value;

						if (lid == "") {
							alert("请填写土地编号！");
							return false;
						}
						if (dept == "") {
							alert("请填写土地编号！");
							return false;
						}      
						return true;

					}

					/*土地租赁内容修改-------end*/

					function unionSelect() {

						var bnameUnion = document.getElementById("bnameUnion").value;
						var lidUnion = document.getElementById("lidUnion").value;
						var startTimeUnion = document.getElementById("startTimeUnion").value;
						var endTimeUnion = document.getElementById("endTimeUnion").value;
						var descUnion = document.getElementById("descUnion").value;

						$('#table2')
								.DataTable(
										{
											"aLengthMenu" : [ 2, 4, 6, 8, 10 ], //动态指定分页后每页显示的记录数。
											"lengthChange" : true, //是否启用改变每页显示多少条数据的控件
											"bSort" : false,
											"iDisplayLength" : 4, //默认每页显示多少条记录
											"bDestroy" : true,
											"dom" : 'ftipr<"bottom"l>',

											"ajax" : {
												"data" : {
													"bname" : bnameUnion,
													"lid" : lidUnion,
													"startTime" : startTimeUnion,
													"endTime" : endTimeUnion,
													"desc" : descUnion
												},
												"url" : "unionSelect.do",
												"type" : "POST"
											},

											"aoColumns" : [
													
													{
														"mData" : "startTime",
														"orderable" : true, // ��������
														"sDefaultContent" : "",
														"sWidth" : "6%",

													},
													{
														"mData" : "endTime",
														"orderable" : true, // ��������
														"sDefaultContent" : "",
														"sWidth" : "6%"
													},
													{
														"mData" : "bname",
														"orderable" : false, // ��������
														"sDefaultContent" : "",
														"sWidth" : "10%"
													},
													{
														"mData" : "lid",
														"orderable" : true, // ��������
														"sDefaultContent" : "",
														"sWidth" : "8%"
													},
													{
														"mData" : "descp",
														"orderable" : false, // ��������
														"sDefaultContent" : "",
														"sWidth" : "8%",
														"render" : function(data, type, row) {
															var status = row.status;
															if (status == 6) {
																return data = '<span>'
																		+ data + '</span>';
															} else {
																return data = '<span>申请失败</span>';
															}
														}
													},
													{
														"mData" : "la_id",
														"orderable" : false, // ��������
														"sDefaultContent" : '',
														"sWidth" : "5%",
														"render" : function(data, type, row) { //render�ı������ʽ,4���������в��������ǿɱ�ġ�

															return data = '<span data-id='
																	+ data
																	+ ' id='
																	+ data
																	+ ' onclick="scanOne(this)"  class=" glyphicon glyphicon-search"></span>';

														}
													}
													//data指该行获取到的该列数据
													//row指该行，可用row.name或row[2]获取第3列字段名为name的值
													//type调用数据类型，可用类型“filter”,"display","type","sort",具体用法还未研究
													//meta包含请求行索引，列索引，tables各参数等信息

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
						document.getElementById("hide_ul").style.display = "none";
						recovery();
					}

					/**
					* 土地租赁详情查看
					*/
					function scanOne(obj) {
						var la_id = obj.id;
                        
						$.ajax({
									type : 'POST',
									data : {
										"la_id" : la_id

									},
									dataType : 'json',
									url : 'myRentdetail.do',
									async : false,
									cache : false,
									error : function(request) {
										alert("error");
									},
									success : function(data) {

										var i = 0;
										for ( var item in data) {
											var reason;
											// alert(data[i].status);
											if (data[i].status == 8) {
												reason = "同类竞争";
											} else {
												reason = data[i].descp;
											}
											$("#bname").val(data[i].bname);
											$("#lname").val(data[i].lname);
											$("#lid").val(data[i].lid);
											$("#mycollege").val(data[i].college);
											$("#landArea").val(data[i].landArea);
											$("#buildingArea").val(data[i].buildingArea);
											$("#afford").val(data[i].afford);
											$("#aptPlanting").val(data[i].aptPlanting);
											$("#startTime").val(data[i].startTime);
											$("#endTime").val(data[i].endTime);
											$("#name").val(data[i].name);
											$("#planting").val(data[i].planting);
											$("#reason2").val(reason);
											
											if(data[i].resource!=null)
												{
												   $("#source").attr("href",data[i].resource);
												}
											
											if (data[i].status != 6 && data[i].status != 2
													&& data[i].status != 4) {

												document.getElementById("scanModal").style.display = "block";
											}
											i++;
										}

									}

								});

						$("#scan").modal('show');
					}

					/*暂存中记录修改-----start*/
					function editOne(obj) {
						var la_id = obj.id;
			           
						$.ajax({
							type : 'POST',
							data : {
								"la_id" : la_id

							},
							dataType : 'json',
							url : 'myRentEdit.do',
							async : false,
							cache : false,
							error : function(request) {
								alert("error");
							},
							success : function(data) {

								var i = 0;
								for ( var item in data) {

									var filename = data[i].resource;
									if (filename != null) {
			                              filename=filename.substring(filename.indexOf("$")+1);
									}
								//	alert(filename);
									
									document.getElementById("fileResource").value=filename;
			                    
			                        
									$("#hide").val(data[i].la_id);
									$("#bnamer").val(data[i].bname);

									$("#lidr").val(data[i].lid);

									$("#deptme option[value=" + data[i].applyDept + "]")
											.attr("selected", true);

									$("#aptPlantingr").val(data[i].aptPlanting);
									$("#startTimer").val(data[i].startTime);
									$("#endTimer").val(data[i].endTime);
									$("#namer").val(data[i].name);
									$("#plantingr").val(data[i].planting);

									i++;
								}

							}

						});

						$("#rent_edit").modal('show');
					}

					/*暂存中记录修改-------end*/

					function payForOne(obj) {
						var la_id = obj.id;

						$.ajax({
							type : 'POST',
							data : {
								"la_id" : la_id

							},
							dataType : 'json',
							url : 'myRentdetail.do',
							async : false,
							cache : false,
							error : function(request) {
								alert("error");
							},
							success : function(data) {

								var i = 0;
								var closingdate;
								var date;
								for ( var item in data) {

									closingdate = data[i].startPayTime.replace(/-/g, "/");

									date = new Date(closingdate);

									date = date.getFullYear() + "-"
											+ (date.getMonth() + 1 + 1) + "-"
											+ date.getDate();

									$("#bnameP").val(data[i].bname);
									$("#lnameP").val(data[i].lname);
									$("#lidP").val(data[i].lid);
									$("#mycollegeP").val(data[i].college);
									$("#landAreaP").val(data[i].landArea);
									$("#buildingAreaP").val(data[i].buildingArea);
									$("#affordP").val(data[i].afford);
									$("#aptPlantingP").val(data[i].aptPlanting);
									$("#startTimeP").val(data[i].startTime);
									$("#endTimeP").val(data[i].endTime);
									$("#nameP").val(data[i].name);
									$("#plantingP").val(data[i].planting);

									$("#closingdate").val(date);

									i++;
								}

							}

						});

						$("#payFor").modal('show');
					}

					

					/*申请表中 状态为交费中和审核中的记录撤回----start*/
					$(document).delegate('#frame1_cancel', 'click', function() {

						var id = $(this).data("id");
						$("#cancelSubmit").val(id);
						$("#cancelOneModal").modal('show');

					});

					$(document).delegate('#cancelSubmit', 'click', function() {//���ȷ�ϳ���İ�ť��ִ��
						var id = $(this).val();
						$('#cancelOneModal').modal('hide');
						$.ajax({
							data : {
								"la_id" : id

							},
							url : 'myFameCancel1.do',
							async : true,
							type : "POST",
							dataType : "json",
							cache : false, //�����?��
							success : function(data) {

								if (data[0].flag) {
									window.location.reload();
								} else {
									alert("撤销失败");
								}

							},
							error : function(data) {
								alert("请求异常");
							}
						});
					});

					/*申请表中 状态为交费中和审核中的记录查看----end*/

					/*暂存表中 的记录删除----start*/

					$(document).delegate('#frame1_delete', 'click', function() {

						var id = $(this).data("id");
						$("#delSubmit").val(id);
						$("#deleteOneModal").modal('show');

					});

					$(document).delegate('#delSubmit', 'click', function() {//���ȷ��ɾ��İ�ť��ִ��
						var id = $(this).val();
						$('#deleteOneModal').modal('hide');
						$.ajax({
							data : {
								"la_id" : id

							},
							url : 'myFrameDel1.do',
							async : true,
							type : "POST",
							dataType : "json",
							cache : false, //�����?��
							success : function(data) {

								if (data[0].flag) {
									window.location.reload();
								} else {
									alert("删除失败");
								}

							},
							error : function(data) {
								alert("请求异常");
							}
						});
					});

					/*暂存表中 的记录删除----end*/

					/*暂存表中 的记录提交----start*/

					$(document).delegate('#frame1_submit', 'click', function() {

						var id = $(this).data("id");
						$("#SubmitTemperate").val(id);
						$("#submitOneModal").modal('show');

					});

					$(document).delegate('#SubmitTemperate', 'click', function() {//���ȷ��ɾ��İ�ť��ִ��
						var id = $(this).val();
						$('#submitOneModal').modal('hide');
						$.ajax({
							data : {
								"la_id" : id

							},
							url : 'myFrameSumbit.do',
							async : true,
							type : "POST",
							dataType : "json",
							cache : false, 
							success : function(data) {

								if (data[0].flag) {
									window.location.reload();
								} else {
									alert("提交失败");
								}

							},
							error : function(data) {
								alert("请求异常");
							}
						});
					});

					/*暂存表中 的记录提交----end*/

					function closeScan2() {
						document.getElementById("scanModal").style.display = "none";
					}

					function showsubmenu() {
						var submenu = document.getElementById("hide_ul");
						if (submenu.style.display == 'none') {
							submenu.style.display = 'block';
						} else {
							submenu.style.display = 'none';
							recovery();
						}

					}
					function hidesubmenu() {
						var submenu = document.getElementById("hide_ul");
						submenu.style.display = 'none';
						recovery();
					}

					function recovery() {
						document.getElementById("bnameUnion").value = "";
						document.getElementById("lidUnion").value = "";
						document.getElementById("startTimeUnion").value = "";
						document.getElementById("endTimeUnion").value = "";
						document.getElementById("descUnion").value = "";
					}
					
					
					/*myRent.jspҳ��js----end*/			
					
				