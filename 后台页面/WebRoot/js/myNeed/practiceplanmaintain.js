// JavaScript Document
var obj = [];
var flag = 0;
var table;
var str = null;
var tidFlag=true;
var midFlag=true;
var userWarn=[];
$(document)
		.ready(
				function() {
				
					table = $("#practiceplanmaintain")
							.DataTable(
									{
										"processing" : true,
										"serverSide" : true,
										"bSort" : false,
										"bFilter" : false,
										"aLengthMenu" : [ 5, 7, 9, 12 ], // 动态指定分页后每页显示的记录数。
										"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
										"iDisplayLength" : 5, // 默认每页显示多少条记录
										"dom" : 'ftipr<"bottom"l>',
										"ordering" : true,
										"searching" : true,
										"ajax" : {
											"url" : "displayPlanInfo.do",
											"type" : "POST",
											"data" : {
												"semester" : str
											}
										},
										"aoColumns" : [ {
											"mData" : "id",
											"orderable" : false,
											"sDefaultContent" : "",
											"sWidth" : "2%"
										}, {
											"mData" : "semester",// 学期学年
											"orderable" : false,
											"sDefaultContent" : "",
											"sWidth" : "6%"
										}, {
											"mData" : "cid",// 课程代码
											"orderable" : false,
											"sDefaultContent" : "",
											"sWidth" : "6%"
										}, {
											"mData" : "coursename",// 课程名称
											"orderable" : false,
											"sDefaultContent" : "",
											"sWidth" : "6%"
										}, {
											"mData" : "count",// 人数
											"orderable" : true,
											"sDefaultContent" : "",
											"sWidth" : "6%"
										}, {
											"mData" : "selectedCount",// 已选人数
											"orderable" : true,
											"sDefaultContent" : "",
										}, {
											"mData" : "composition",// 教学班组成
											"orderable" : false,
											"sDefaultContent" : "",
										}, {
											"mData" : "college",// 开课学院
											"orderable" : true,
											"sDefaultContent" : "",
										},

										{
											"mData" : "weekClassify",// 周学时
											"orderable" : true,
											"sDefaultContent" : "",
										}, {
											"mData" : "credit",// 学分
											"orderable" : true,
											"sDefaultContent" : "",
										}, {
											"mData" : "courseNature",// 课程性质
											"orderable" : false,
											"sDefaultContent" : "",
										}, {
											"mData" : "courseCategory",// 课程类别
											"orderable" : false,
											"sDefaultContent" : "",
										}, {
											"mData" : "tid",// 教职工号
											"orderable" : true,
											"sDefaultContent" : "",
										}, {
											"mData" : "tname",// 教师姓名
											"orderable" : true,
											"sDefaultContent" : "",
										}, {
											"mData" : "week",// 起始周
											"orderable" : false,
											"sDefaultContent" : "",
										}, {
											"mData" : "major_oriented",// 面向专业
											"orderable" : false,
											"visible" : false,
											"sDefaultContent" : "",
										}, {
											"mData" : "mid",// 专业编号
											"orderable" : false,
											"visible" : false,
											"sDefaultContent" : "",
										}, {
											"mData" : "checkMethod",// 考核
											"orderable" : false,
											"sDefaultContent" : ""
										} ],
										"columnDefs" : [ {
											"orderable" : false,
											"targets" : [ 0 ],
											"data" : "id",
											"render" : function(data, type, row) {
												obj.push(row);
												return '<input type="checkbox" name="allcheckbox" value="'
														+ data + '">';
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

					// 获取学院，学期
					$.ajax({
						type : 'POST',
						dataType : 'json',
						url : 'getPlanMaintainInfo.do',
						async : false,
						cache : false,
						error : function(request) {
							alert("error");
						},
						success : function(data) {

							for (var i = 0; i < data.semester.length; i++) {
								$("#termYearID").after(
										"<option value=" + data.semester[i]
												+ ">" + data.semester[i]
												+ "</option>");

							}

							
							  for ( var i=0;i<data.college.length;i++) {
							  $("#Acollegeh").after( "<option value="+data.college[i].dept+">" + data.college[i].dept + "</option>");
							  }
							 

						}

					});

					// 全选
					$("#ck1")
							.on(
									"click",
									function() {
										// $("#Applychart").hide();
										if ($(this).prop("checked") === true) {
											$(
													"#practiceplanmaintain input[name='allcheckbox']")
													.prop("checked", true);
										} else {
											$(
													"#practiceplanmaintain input[name='allcheckbox']")
													.prop("checked", false);
										}
									});

					/*学年学期的js控制---start*/
					$("#termYear").on("change", function() {
						var termYear = $(this).val();
						var semester = $("#semester").val();
						if (termYear != "") {
							if (semester=="0") {
								str = termYear;							
							} else if(semester!=""){
								str = termYear + '-' + semster;
							}else{
								str=null;
							}	
							
						
						}else{
							$("#semester").val("");
							str=null;
						}
						

						table = $("#practiceplanmaintain")
						.DataTable(
								{
									"processing" : true,
									"serverSide" : true,
									"bSort" : false,
									"bFilter" : false,
									"aLengthMenu" : [ 5, 7, 9, 12 ], // 动态指定分页后每页显示的记录数。
									"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
									"iDisplayLength" : 5, // 默认每页显示多少条记录
									"dom" : 'ftipr<"bottom"l>',
									"ordering" : true,
									"bDestroy":true,
									"searching" : true,
									"ajax" : {
										"url" : "displayPlanInfo.do",
										"type" : "POST",
										"data" : {
											"semester" : str
										}
									},
									"aoColumns" : [ {
										"mData" : "id",
										"orderable" : false,
										"sDefaultContent" : "",
										"sWidth" : "6%"
									}, {
										"mData" : "semester",// 学期学年
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "cid",// 课程代码
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "coursename",// 课程名称
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "count",// 人数
										"orderable" : true,
										"sDefaultContent" : "",
										"sWidth" : "6%"
									}, {
										"mData" : "selectedCount",// 已选人数
										"orderable" : true,
										"sDefaultContent" : "",
									}, {
										"mData" : "composition",// 教学班组成
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "college",// 开课学院
										"orderable" : true,
										"sDefaultContent" : "",
									},

									{
										"mData" : "weekClassify",// 周学时
										"orderable" : true,
										"sDefaultContent" : "",
									}, {
										"mData" : "credit",// 学分
										"orderable" : true,
										"sDefaultContent" : "",
									}, {
										"mData" : "courseNature",// 课程性质
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "courseCategory",// 课程类别
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "tid",// 教职工号
										"orderable" : true,
										"sDefaultContent" : "",
									}, {
										"mData" : "tname",// 教师姓名
										"orderable" : true,
										"sDefaultContent" : "",
									}, {
										"mData" : "week",// 起始周
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "major_oriented",// 面向专业
										"orderable" : false,
										"visible" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "mid",// 专业编号
										"orderable" : false,
										"visible" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "checkMethod",// 考核
										"orderable" : false,
										"sDefaultContent" : ""
									} ],
									"columnDefs" : [ {
										"orderable" : false,
										"targets" : [ 0 ],
										"data" : "id",
										"render" : function(data, type, row) {
											obj.push(row);
											return '<input type="checkbox" name="allcheckbox" value="'
													+ data + '">';
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

					$("#semester").on("change", function() {
						var termYear = $("#termYear").val();
						if (termYear == "") {
							$("#semester").val("");
							bootbox.alert({
								message : "请先选择学年",
								size : 'small'
							});
							return;
						}
						var semester = $(this).val();
						if (semester=="0") {
							str = termYear;							
						} else if(semester!=""){
							str = termYear + '-' + semester;
						}else{
							str=null;
						}	
						table = $("#practiceplanmaintain")
						.DataTable(
								{
									"processing" : true,
									"serverSide" : true,
									"bSort" : false,
									"bFilter" : false,
									"aLengthMenu" : [ 5, 7, 9, 12 ], // 动态指定分页后每页显示的记录数。
									"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
									"iDisplayLength" : 5, // 默认每页显示多少条记录
									"dom" : 'ftipr<"bottom"l>',
									"ordering" : true,
									"bDestroy":true,
									"searching" : true,
									"ajax" : {
										"url" : "displayPlanInfo.do",
										"type" : "POST",
										"data" : {
											"semester" : str
										}
									},
									"aoColumns" : [ {
										"mData" : "id",
										"orderable" : false,
										"sDefaultContent" : "",
										"sWidth" : "6%"
									}, {
										"mData" : "semester",// 学期学年
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "cid",// 课程代码
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "coursename",// 课程名称
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "count",// 人数
										"orderable" : true,
										"sDefaultContent" : "",
										"sWidth" : "6%"
									}, {
										"mData" : "selectedCount",// 已选人数
										"orderable" : true,
										"sDefaultContent" : "",
									}, {
										"mData" : "composition",// 教学班组成
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "college",// 开课学院
										"orderable" : true,
										"sDefaultContent" : "",
									},

									{
										"mData" : "weekClassify",// 周学时
										"orderable" : true,
										"sDefaultContent" : "",
									}, {
										"mData" : "credit",// 学分
										"orderable" : true,
										"sDefaultContent" : "",
									}, {
										"mData" : "courseNature",// 课程性质
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "courseCategory",// 课程类别
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "tid",// 教职工号
										"orderable" : true,
										"sDefaultContent" : "",
									}, {
										"mData" : "tname",// 教师姓名
										"orderable" : true,
										"sDefaultContent" : "",
									}, {
										"mData" : "week",// 起始周
										"orderable" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "major_oriented",// 面向专业
										"orderable" : false,
										"visible" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "mid",// 专业编号
										"orderable" : false,
										"visible" : false,
										"sDefaultContent" : "",
									}, {
										"mData" : "checkMethod",// 考核
										"orderable" : false,
										"sDefaultContent" : ""
									} ],
									"columnDefs" : [ {
										"orderable" : false,
										"targets" : [ 0 ],
										"data" : "id",
										"render" : function(data, type, row) {
											obj.push(row);
											return '<input type="checkbox" name="allcheckbox" value="'
													+ data + '">';
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
					/*学年学期的js控制---end*/
					
					/*增加实习计划js控制---start*/
					$(document).on("blur", "#intid", function() {
						var id = $(this).val();
						if(id==""){
							return;
						}
						$.ajax({
							type : 'POST',
							dataType : 'json',
							data:{"id":id},
							url : 'checkIsThisUser.do',
							async : false,
							cache : false,
							error : function(request) {
								alert("error");
							},
							success : function(data) {
								if(!data.flag){
								bootbox.alert({
									message : "不存在该教师",
									size : 'small'
								});
								tidFlag=false;
								}else{
								tidFlag=true;	
								}
							}

						});
					});
					
					$(document).on("blur", "#inmid", function() {
						var aid = $("#inmid").val();
						if(aid==""){
							return;
						}
						$.ajax({
							type : 'POST',
							dataType : 'json',
							data:{"aid":aid},
							url : 'checkIsThisMid.do',
							async : false,
							cache : false,
							error : function(request) {
								alert("error");
							},
							success : function(data) {
								if(!data.flag){
								bootbox.alert({
									message : "不存在该专业",
									size : 'small'
								});
								midFlag=false;
								}else{
									midFlag=true;
								}
							}

						});
					});
					
					$(document).on("click", "#saveAdd", function() {// 保存一条增加的实习记录
						var semester = $("#insemester").val();
						var college = $("#incollege").val();
						var cid =$("#incid").val();
						var count =$("#incount").val();
						var selectedCount = $("#inselectedCount").val();
						var composition = $("#incomposition").val();
						var coursename = $("#incoursename").val();
						var weekhours =$("#inweekClassify").val();// 周学时
						var credit = $("#incredit").val();// 学分
						var courseNature = $("#incourseNature").val();
						var courseCategory = $("#incourseCategory").val();
						var tid = $("#intid").val();
						var tname = $("#intname").val();
						var week = $("#inweek").val();// 起始周
						var checkMethod = $("#incheckMethod").val();
						var mid = $("#inmid").val();
						var major_oriented =$("#inmajor_oriented").val();
						var dateFormatP=/^[0-9]{4}-[0-9]{4}-[12]$/;
						
						if(count==""){
							count="0";
						}
						if(selectedCount==""){
							selectedCount="0";
						}
						if(weekhours==""){
							weekhours="0";
						}
						if(credit==""){
							credit="0";
						}
						
						if(cid==""){
							bootbox.alert({
								message : "请填写课程代码",
								size : 'small'
							});
							return;
						}
						if(coursename==""){
							bootbox.alert({
								message : "请填写课程名称",
								size : 'small'
							});
							return;
						}
						if(courseNature==""){
							bootbox.alert({
								message : "请选择课程性质",
								size : 'small'
							});
							return;
						}
						if(courseCategory==""){
							bootbox.alert({
								message : "请选择课程类别",
								size : 'small'
							});
							return;
						}
						if(college==""){
							bootbox.alert({
								message : "请选择开课学院",
								size : 'small'
							});
							return;
						}
						if(tname==""){
							bootbox.alert({
								message : "请填写带队教师",
								size : 'small'
							});
							return;
						}
						if(tid==""){
							bootbox.alert({
								message : "请填写教师职工号",
								size : 'small'
							});
							return;
						}
						if(week==""){
							bootbox.alert({
								message : "请填写起始周",
								size : 'small'
							});
							return;
						}
						if(mid==""){
							bootbox.alert({
								message : "请填写专业编号",
								size : 'small'
							});
							return;
						}
						if(semester==""){
							bootbox.alert({
								message : "请填写学年学期",
								size : 'small'
							});
							return;
						}
						if(!tidFlag){
							bootbox.alert({
								message : "不存在该教师",
								size : 'small'
							});
							return;
						}
						if(!midFlag){
							bootbox.alert({
								message : "不存在该专业",
								size : 'small'
							});
							return;
						}
						if (!dateFormatP.exec(semester)) {
							bootbox.alert({
								message : "学年学期的格式不对，格式为：××××-××××-×",
								size : 'small'
							});
							return false;
						}
						var str1 = "('" + semester + "','" + college + "','" + cid + "',"
						+ count + ',' + selectedCount + ",'" + composition + "','"
						+ coursename + "'," + weekhours + ',' + credit + ",'"
						+ courseNature + "','" + courseCategory + "','" + tid + "','"
						+ tname + "','" + week + "','" + checkMethod + "','" + mid
						+ "','" + major_oriented + "')";
						
						$.ajax({
							type : 'POST',
							dataType : 'json',
							data:{"str":str1},
							url : 'addOnePlanInfo.do',
							async : false,
							cache : false,
							error : function(request) {
								alert("error");
							},
							success : function(data) {
								if(data.flag){
								bootbox.alert({
									message : "保存成功",
									size : 'small'
								});
								table.draw(false);
								}
							}

						});
						
						$("#addPraItem").modal('hide');
					});
					/*增加实习计划js控制---end*/
					
					//导入文件的js控制			
					$("#daoru").click(function(){
						$("#fileResource").val('');
					});
					
					//导出文件的js控制
                     $("#confirmDaoButton").click(function(){
                    	
                    		$("#semesterchu").val(str); 	
 							$("#daochuForm").submit();
 							$("#export").modal('hide');
					});
                     
                     $("#chu").click(function(){
                    	 
 						$("#daoCollege").val('-1');  						
 						if($("#export").attr("aria-hidden")=="true"){ 							
 						  if(str==null){
 							bootbox.alert({
 								message : "请先选择学年学期",
 								size : 'small'
 							}); 	
 							return;
 						}else{
 							//获取开课学院
 							$(".removeCollege").remove();
 							$.ajax({
 								type : 'POST',
 								dataType : 'json',
 								data:{"semester":str},
 								url : 'getReadyCollege.do',
 								async : false,
 								cache : false,
 								error : function(request) {
 									alert("error");
 								},
 								success : function(data) {
 									
 									  for ( var i=0;i<data.college.length;i++) {
 										  $("#daodept").after( "<option class='removeCollege' value="+data.college[i]+">" + data.college[i] + "</option>");
 										  }
 									}
 								

 							});
 							$("#export").modal('show');
 						}
 						}
 					});
					
					// 点击 检测数据完整性
					$(document).on("click","#checkIsSave",function() {
                           if(str==null){
                        	   bootbox.alert({
    								message : "请先选择学年学期",
    								size : 'small'
    							}); 
                        	   return;
                           }
                           userWarn=[];
										table = $("#practiceplanmaintain").DataTable(
														{
															"processing" : true,
															"serverSide" : true,
															"bSort" : false,
															"bFilter" : false,
															"aLengthMenu" : [
																	5, 7, 9, 12 ], // 动态指定分页后每页显示的记录数。
															"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
															"iDisplayLength" : 5, // 默认每页显示多少条记录
															"dom" : 'ftipr<"bottom"l>',
															"bDestroy" : true,
															"ordering" : true,
															"searching" : true,
															"ajax" : {
																"url" : "checkIsSave.do",
																"type" : "POST",
																"data" : {
																	"semester" : str
																}
															},
															"aoColumns" : [
																	{
																		"mData" : "id",
																		"orderable" : false,
																		"sDefaultContent" : "",
																	// "sWidth"
																	// : "4%"
																	},
																	{
																		"mData" : "semester",// 学期学年
																		"orderable" : false,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "cid",// 课程代码
																		"orderable" : false,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "coursename",// 课程名称
																		"orderable" : false,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "count",// 人数
																		"orderable" : true,
																		"sDefaultContent" : ""
																	},
																	{
																		"mData" : "selectedCount",// 已选人数
																		"orderable" : true,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "composition",// 教学班组成
																		"orderable" : false,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "college",// 开课学院
																		"orderable" : true,
																		"sDefaultContent" : "",
																	},

																	{
																		"mData" : "weekClassify",// 周学时
																		"orderable" : true,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "credit",// 学分
																		"orderable" : true,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "courseNature",// 课程性质
																		"orderable" : false,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "courseCategory",// 课程类别
																		"orderable" : false,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "tid",// 教职工号
																		"orderable" : true,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "tname",// 教师姓名
																		"orderable" : true,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "week",// 起始周
																		"orderable" : false,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "major_oriented",// 面向专业
																		"orderable" : false,
																		"visible" : false,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "mid",// 专业编号
																		"orderable" : false,
																		"visible" : false,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "checkMethod",// 考核
																		"orderable" : false,
																		"sDefaultContent" : ""
																	} ],
															"columnDefs" : [ {
																"orderable" : false,
																"targets" : [ 0 ],
																"data" : "id",
																"render" : function(data,type,row) {																	
																	obj.push(row);
																	userWarn.push(data);
																	return '<input type="checkbox" name="allcheckbox" value="'
																			+ data
																			+ '">';
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
										flag = 1;
										$("#remind").prop("hidden", false);
									});
					
                       //发送消息提醒用户
					$("#remind").click(function(){
						var i=0;
						var recordstr='(';
						$("input[type='checkbox'][name='allcheckbox']:checked").each(function() {							
								if(i==0){
									recordstr=recordstr+$(this).val();
								}else{
									recordstr=recordstr+','+$(this).val();
								}						
							i++;
						});
	                    if(i==0){
	                    	bootbox.alert({
								message : "您尚未选择任何内容",
								size : 'small'
							});
	                    	return;
	                    }					
						recordstr=recordstr+')';					
						bootbox.confirm({
							message: "发送信息提醒用户完成实习计划？",
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
								if(result){			
									
										$.ajax({
											url : 'callAttention.do',
											type : 'post',
											dataType : 'json',
											data : {											
												"recordstr":recordstr
											},
											success : function(data) {
												bootbox.alert({
													message : data.msg,
													size : 'small'
												});
												$("input[type='checkbox'][name='allcheckbox']:checked").prop("checked",false);
											}
										});//end

								}
							}
						});
					});
					
					// 隐藏实习申请表
					$(document).on("click", "#closemodal", function() {

						$("#Applychart").hide();

					});

					$(document).on("click", "#add", function() {// 增加一条实习记录弹出框的弹出
						$("#addPraItem").find("input").val("");
						$("#addPraItem").find("select").val("");
						$("#addPraItem").modal('show');
					});
					
					
					// 显示实习申请表
					var tbodyStyle = '<tbody><tr>'
							+ '<td>序号</td>'
							+ '<td>周次</td>'
							+ '<td>开始时间</td>'
							+ '<td>结束时间</td>'
							+ '<td>实习内容</td>'
							+ '<td>实习基地来源</td>'
							+ '<td>实习地点</td>'
							+ '<td>实习类别</td>'
							+ '<td>备注</td>'
							+ '</tr>'
							+ '<tr>'
							+ '<td rowspan="3">1</td>'
							+ '<td><input id="weekend" type="text" class="inputWidth flag"></td>'
							+ '<td><input id="startweek" type="text" class="inputWidth flag"></td>'
							+ '<td><input id="endweek" type="text" class="inputWidth flag"></td>'
							+ '<td><input id="content" type="text" class="inputWidth flag"></td>'
							+ '<td><select name="" id="baseFrom" class="flag"><option value="">请选择</option><option value="校内基地">校内基地</option><option value="校外基地">校外基地</option></select></td>'
							+ '<td id="practicePlace"><select id="schoolBase" class="flag" hidden><option id="schoolBaseID" value="">请选择</option></select><input id="outBase" type="text" class="inputWidth flag" hidden></td>'
							+ '<td><select name="" class="flag"><option value="">请选择</option><option value="生产实习">生产实习</option><option value="教学实习">教学实习</option><option value="毕业实习">毕业实习</option><option value="综合实习">综合实习</option></select></td>'
							+ '<td><input id="remark" type="text" class="flag"></td>'
							+ '</tr>'
							+ '<tr>'
							+ '<td>实习形式</td>'
							+ '<td colspan="2">实习基地联系人/电话</td>'
							+ '<td>目的</td>'
							+ '<td>实习经费预算</td>'
							+ '<td>指导老师</td>'
							+ '<td>实验员</td>'
							+ '<td>操作</td>'
							+ '</tr>'
							+ '<tr>'
							+ '<td><select name="" id="practiceClass" class="flag"><option value="">请选择</option><option value="集中">集中</option><option value="分散">分散</option></select></td>'
							+ '<td colspan="2"><input id="phone" type="text" class="flag"></td>'
							+ '<td><select id="aim" class="flag"><option id="aimID" value="">请选择</option></select></td>'
							+ '<td><input id="budget" type="text" class="inputWidth flag"></td>'
							+ '<td><input type="text" class="adviser2 inputWidth flag"></td>'
							+ '<td><a class="btn btn-primary choice">选择</a></td>'
							+ '<td><span class="deleteID">删除</span></td>'
							+ '</tr></tbody>';
					$("#practiceplanmaintain tbody tr").on(
							"click",
							"td:gt(0)",
							function() {
								/*
								 * var index= table.row().data()[0].id;
								 * $("#division").val(obj[index].division);
								 * $("#classname").val(obj[index].className);
								 * $("#major").val(obj[index].major);
								 * $("#class").val(obj[index].className);
								 * $("#grade").val(obj[index].grade);
								 * $("#number").val(obj[index].number);
								 * $("#weeks").val(obj[index].Prweeks);
								 * $("#leaderTeacher").val(obj[index].leaderTeacher);
								 */
								$.ajax({
									url : "",
									type : "POST",
									dataType : "json",
									success : function(data) {

										for (var i = 0; i < data.length; i++) {
											var p = 0;
											$("#table tbody:last-child").after(
													tbodyStyle);
											$("#table tbody:last-child").find(
													".flag").each(function() {
												$(this).val(data[i][p]);
												p++;
											});
										}
									}
								});
								$("#Applychart").show();
							});

					/*
					 * $("#testexmple").click(function(){ $("#table
					 * tbody:last-child").find(".flag").each(function(){
					 * $(this).val("yes"); //p++; }); });
					 */

					/* 实习申请表里面的操作 */
					// 实习基地来源改变，对应的实习基地改变
					$(document).on("change", "#baseFrom", function(e) {
						// $("#practicePlace").empty();
						if (e.target.value === '校内基地') {
							$(this).parent().next().children(":first").show();
							// $("#schoolBase").show();
							$(this).parent().next().children(":last").hide();
						}
						if (e.target.value === '校外基地') {
							$(this).parent().next().children(":last").show();
							$(this).parent().next().children(":first").hide();
						}
						if (e.target.value === '') {
							$(this).parent().next().children(":last").hide();
							$(this).parent().next().children(":first").hide();
						}
					});
					// 获取选择的内容
					$.ajax({
						URL : "",
						type : "POST",
						dataType : 'json',
						success : function(data) {
							for (var i = 0; i < data[0].length; i++) {// 获取校内基地的实习地点下拉框
								$("#schoolBaseID").after(
										"<option class='rest' value="
												+ data[0][i] + ">" + data[0][i]
												+ "</option>");
							}
							for (i = 0; i < data[1].length; i++) {// 获取实习目的下拉框
								$("#aimID").after(
										"<option class='rest' value="
												+ data[1][i] + ">" + data[1][i]
												+ "</option>");
							}
							for (i = 0; i < data[2].length; i++) {// 获取学院下拉框
								$("#collegeID").after(
										"<option class='rest' value="
												+ data[2][i] + ">" + data[2][i]
												+ "</option>");
							}
						}
					});
					var name = "";
					$(document).on("change", ".adviser2", function(e) {// 填写指导老师姓名
						name += e.target.value + " ";
						$("#adviser").val(name);
					});

					$(document).on("click", ".choice", function() {// 点击选择弹出
																	// 框并且清空框里的数据
						$("#Selectname").modal('show');
						$("#selectTname").val("");
						$("#tester").val("");
						$("#selectCollege").val("");
					});

					// 选择学院并且上传学院的名称，放回改学院老师的数据（包含老师名称和老师员工编号）
					var obj2;
					$(document)
							.on(
									"change",
									"#selectCollege",
									function() {
										var college = $("#selectCollege").val;
										$
												.ajax({
													url : " ",
													type : "POST",
													dataType : 'json',
													data : {
														"college" : college,
													},
													success : function(data) {
														obj2 = data;// 用于下面函数里面的判断
														for (var i = 0; i < data.length; i++) {// 获取校内基地的实习地点下拉框
															$("#schoolBaseID")
																	.after(
																			"<option class='rest' value="
																					+ data[i].teacherName
																					+ ">"
																					+ data[i].teacherName
																					+ "</option>");
														}
													}
												});
									});
					// 将实验员姓名显示在界面中，并且在选择的同时根据实验员的职工编号判断有没有选择同一人
					var value = [];
					var value2 = "";
					var value3 = [];
					$(document).on("change", "#selectTname", function(e) {

						$.each(obj2, function(index, item) {
							if (item.teacherName === e.target.value) {
								if ($.inArray(item.teacherID, value3) === -1) {
									value.push(e.target.value);
									value3.push(item.teacherID);
									value2 = value.join(" ");
									$("#tester").val(value2);
								} else {
									alert("此人已经存在，请重新选择！");
								}
							}
						});

					});

					$(document).on("click", "#finished", function() {// 点击确定之后讲实验员姓名在表格中显示出来
						$("#testername").val(value2);
						value2 = "";
					});

					// 实习表中添加一条记录

			

					$(document).on("click", "#addTbody", function() {// 添加一条空表的记录
						$("#table tbody:last-child").after(tbodyStyle);
					});

					$(document).on("click", ".deleteID", function() {// 弹出框的删除
						$(this).closest("tbody").remove();
					});

					$("#save")
							.click(
									function() {// 弹出框的保存
										// $("#PraForm").submit();
										bootbox
												.confirm({
													message : "确定保存？",
													size : 'small',
													buttons : {
														confirm : {
															label : '确定',
															className : 'btn-success'
														},
														cancel : {
															label : '取消',
															className : 'btn-danger'
														},
													},
													callback : function(result) {
														if (result) {
															var str = "(";
															var y = 0;
															$("#table")
																	.find(
																			"tbody")
																	.each(
																			function() {
																				var x = 0;
																				if (y !== 0) {
																					str = str
																							+ ',';
																				}
																				$(
																						this)
																						.find(
																								".flag")
																						.each(
																								function() {
																									if (x !== 0) {
																										str = str
																												+ ','
																												+ '"'
																												+ $(
																														this)
																														.val()
																												+ '"';
																									} else {
																										str = str
																												+ '"'
																												+ $(
																														this)
																														.val()
																												+ '"';
																										x++;
																									}

																								});
																				str = str
																						+ ')';
																				y++;
																			});

															$
																	.ajax({
																		url : "",
																		type : "POST",
																		dataType : "json",
																		data : {
																			"str" : str,
																			"courseID" : obj[index].courseID
																		},
																		success : function(
																				msg) {
																			bootbox
																					.alert({
																						message : msg.str,
																						size : 'small'
																					});
																			// 怎样刷新啊？
																		}
																	});

														}
													}
												});
									});
					// 删除表格的中记录
					$("#delete")
							.click(
									function() {
										var flag = 0;
										$('input[name="allcheckbox"]:checked')
												.each(function() {
													flag++;
												});
										if (flag === 0) {
											bootbox.alert({
												message : "您还没有选择任何内容",
												size : 'small'
											});
										} else {
											bootbox
													.confirm({
														message : "确定删除？",
														size : 'small',
														buttons : {
															confirm : {
																label : '确定',
																className : 'btn-success'
															},
															cancel : {
																label : '取消',
																className : 'btn-danger'
															},
														},
														callback : function(
																result) {
															if (result) {
																var deletstr = '(';
																var i = 0;
																$(
																		"input[type='checkbox'][name='allcheckbox']:checked")
																		.each(
																				function() {
																					if (i !== 0) {
																						deletstr = deletstr
																								+ ','
																								+ $(
																										this)
																										.val();
																					} else {
																						deletstr = deletstr
																								+ $(
																										this)
																										.val();
																					}
																					i++;
																				});
																deletstr = deletstr
																		+ ')';
																$
																		.ajax({
																			url : 'deletePlanInfo.do',
																			type : 'post',
																			dataType : 'json',
																			data : {
																				"deletstr" : deletstr
																			},
																			success : function(
																					data) {
																				bootbox
																						.alert({
																							message : data.msg,
																							size : 'small'
																						});
																				table
																						.draw(false);
																			}
																		});
															}
														}
													});
										}
									});

				});
