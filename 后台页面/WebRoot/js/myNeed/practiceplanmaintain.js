// JavaScript Document
var obj = [];
var flag = 0;
var table;
var str = null;
var tidFlag=true;
var midFlag=true;
var userWarn=[];
var Oneindex;
var value=[];
var writeName="";
var showName="";
var teacherString=[];
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
											"sWidth" : "4%"
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
														+ data + '" id="'+(obj.length-1)+'"/>';
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
											+ data + '" id="'+(obj.length-1)+'"/>';
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
						var semester = $(this).val();
						if (termYear == "") {
							if(semester!=""){
							   bootbox.alert({
								message : "请先选择学年",
								size : 'small'
							});
							$("#semester").val("");
							return;
							}
						}						
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
											+ data + '" id="'+(obj.length-1)+'"/>';
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
						$("#teamYearw").val('');
						$("#oneSemesterTime").val('');
						$("#twoSemesterTime").val('');
						$("#fileResource").val('');
					});
					
					$("#certainWeekTime").click(function(){
						
						var teamYearw=$("#teamYearw").val();
						var oneSemesterTime=$("#oneSemesterTime").val();
						var twoSemesterTime=$("#twoSemesterTime").val();
						var dateFormatP=/^[0-9]{4}-[0-9]{4}$/;
						if(teamYearw==""){
							bootbox.alert({
								message : "请完善要导入的学年",
								size : 'small'
							});
							return;
						}
						if (!dateFormatP.exec(teamYearw)) {
							bootbox.alert({
								message : "学年的格式不对,请重新填写",
								size : 'small'
							});
							return false;
						}
						if(oneSemesterTime==""){
							bootbox.alert({
								message : "请完善导入学年的第1学期第1周的具体时间",
								size : 'small'
							});
							return;
						}
						if(twoSemesterTime==""){
							bootbox.alert({
								message : "请完善导入学年的第2学期第1周的具体时间",
								size : 'small'
							});
							return;
						}
						
						$.ajax({
							type : 'POST',
							dataType : 'json',
							data:{"teamYearw":teamYearw,
								  "oneSemesterTime":oneSemesterTime,
							      "twoSemesterTime":twoSemesterTime
							},
							url : 'saveSemesterTime.do',
							async : false,
							cache : false,
							error : function(request) {
								alert("error");
							},
							success : function(data) {
							
								$("#writeWeekTime").modal('hide');
								$("#import").modal('show');
							}

						});
						
						
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
																	+ data + '" id="'+(obj.length-1)+'"/>';
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
						$(".tbodyID").remove();

					});

					$(document).on("click", "#add", function() {// 增加一条实习记录弹出框的弹出
						$("#addPraItem").find("input").val("");
						$("#addPraItem").find("select").val("");
						$("#addPraItem").modal('show');
					});
					
					
/**弹出框js--start*/
//显示实习申请表
	 var tbodyStyle='<tbody class="tbodyID"><tr>'
			+'<td>序号</td>'
			+'<td>周次<span class="starColor">*</span></td>'
			+'<td>开始时间<span class="starColor">*</span></td>'
			+'<td>结束时间<span class="starColor">*</span></td>'
			+'<td>实习内容<span class="starColor">*</span></td>'
			+'<td>实习基地来源<span class="starColor">*</span></td>'
			+'<td>实习地点<span class="starColor">*</span></td>'
	 		+'<td>实习类别<span class="starColor">*</span></td>'
	 		+'<td>备注</td>'
		 +'</tr>'
		 +'<tr>'
		 +'<td rowspan="3"><sapn class="mark"></span></td>'
		 +'<td><input id="weekend" type="text" class="text-center inputWidth flag"></td>'
		 +'<td><input id="startweek" name="control_date" type="text" size="10" maxlength="10" onClick="new Calendar().show(this);" readonly="readonly" class="flag"></td>'
		 +'<td><input id="endweek" name="control_date" type="text" size="10" maxlength="10" onClick="new Calendar().show(this);" readonly="readonly" class="flag"></td>'
		 +'<td><input id="content" type="text" class="inputWidth flag"></td>'
		 +'<td><select name="" id="baseFrom" class="flag"><option value="">请选择</option><option value="校内基地">校内基地</option><option value="校外基地">校外基地</option></select></td>'
		 +'<td id="practicePlace"><select id="schoolBase" hidden><option id="schoolBaseID" value="">请选择</option></select><input id="outBase" type="text" class="inputWidth" hidden></td>'	 	 
		 +'<td><select id="category" class="flag"><option value="">请选择</option><option value="生产实习">生产实习</option><option value="教学实习">教学实习</option><option value="毕业实习">毕业实习</option><option value="综合实习">综合实习</option></select></td>'
	 	 +'<td><input id="remark" type="text" class="flag"></td>'
		 +'</tr>'
		 +'<tr>'
		 +'<td>实习形式<span class="starColor">*</span></td>'
		 +'<td>实习基地联系人/电话<span class="starColor">*</span></td>'
		 +'<td>目的<span class="starColor">*</span></td>'
		 +'<td>实习经费预算</td>'
		 +'<td>指导老师/实验员<span class="starColor">*</span></td>'
		 +'<td>指导老师</td>'
		 +'<td>实验员</td>'
		 +'<td>操作</td>'
		 +'</tr>'
		 +'<tr>'
		 +'<td><select name="" id="practiceClass" class="flag"><option value="">请选择</option><option value="集中">集中</option><option value="分散">分散</option></select></td>'
		 +'<td><input id="phone" type="text" class="flag"></td>'
		 +'<td><select id="aim" class="flag"><option id="aimID" value="">请选择</option></select></td>'
		 +'<td><input id="budget" type="text" class="inputWidth flag"></td>'
		 +'<td><div class="spanstyle" id="Tea"></div><div class="spanstyle" id="tes"></div></td>'
		 +'<td><button type="button" class="btn btn-primary choice2" value="">选择</button></td>'
		 +'<td><button type="button" class="btn btn-primary choice" value="">选择</button></td>'
		 +'<td><span class="deleteID" id="">删除</span></td>'
		 +'</tr></tbody>';
	
	
	 $(document).on("click", "#practiceplanmaintain tbody tr td", function() {
	     var itLength=$(this).find("input").length;
		 if(itLength!=0){
		    return;
	       }
	Oneindex= $(this).parent('tr').find("input").attr("id");
	$("#division").val(obj[Oneindex].college);
	$("#classname").val(obj[Oneindex].coursename);
	$("#major").val(obj[Oneindex].major_oriented);
	$("#class").val(obj[Oneindex].composition);
	$("#grade").val(obj[Oneindex].credit);
	$("#number").val(obj[Oneindex].count);
	$("#weeks").val(obj[Oneindex].weekClassify);
	$("#leaderTeacher").val(obj[Oneindex].tname);
	
	$.ajax({
		url:'getplandata.do',
		type:"POST",
		dataType:"json",
		data:{
			"mid":obj[Oneindex].id
		},
		success:function(data){		
			var teachername="";
			var testername="";
			for(var i=0;i<data.length;i++){
				$("#table tbody:last-child").after(tbodyStyle);
				$.ajax({
					type : 'POST',
					dataType : 'json',		
					url : 'getPlanAim.do',  
					async : false,
					cache : false,
					data:{
						"mid":obj[Oneindex].mid
					},
					error : function(request) {
						alert("error");
					},
					success : function(data){
						
						for(var j=0;j<data[0].length;j++){//获取校内基地的实习地点下拉框
							$("#table tbody:last-child").find("#schoolBaseID").after(
							"<option class='rest' value="+data[0][j].name+">"+ data[0][j].name + "</option>"
							);
						}
						for(j=0;j<data[1].length;j++){//获取实习目的下拉框
							$("#table tbody:last-child").find("#aimID").after(
							"<option class='rest' id="+data[1][j].id+" value="+data[1][j].aim+">"+ data[1][i].aim + "</option>"
							);
						}
					}
				});
				$("#table tbody:last-child").find(".mark").html(i+1);
				$("#table tbody:last-child").find("#weekend").val(data[i].week);
				$("#table tbody:last-child").find("#startweek").val(data[i].starttime);
				$("#table tbody:last-child").find("#endweek").val(data[i].endtime);
				$("#table tbody:last-child").find("#content").val(data[i].content);
				$("#table tbody:last-child").find("#baseFrom").val(data[i].source);
				$("#table tbody:last-child").find("#category").val(data[i].category);
				$("#table tbody:last-child").find("#remark").val(data[i].remark);
				$("#table tbody:last-child").find("#practiceClass").val(data[i].form);
				
				$("#table tbody:last-child").find("#phone").val(data[i].telephone);
				$("#table tbody:last-child").find("#aim").val(data[i].aim);
				$("#table tbody:last-child").find("#budget").val(data[i].expense);
				$("#table tbody:last-child").find("#Tea").html("老师:"+data[i].guideTeacher);
				$("#table tbody:last-child").find("#tes").html("实验员:"+data[i].assistant);
				if($("#table tbody:last-child").find("#baseFrom").val()==="校内基地"){
					$("#table tbody:last-child").find("#schoolBase").show();
					$("#table tbody:last-child").find("#schoolBase").addClass("flag");
					$("#table tbody:last-child").find("#schoolBase").val(data[i].site);
					

				}
				if($("#table tbody:last-child").find("#baseFrom").val()==="校外基地"){
					$("#table tbody:last-child").find("#outBase").show();
					$("#table tbody:last-child").find("#outBase").addClass("flag");
					$("#table tbody:last-child").find("#outBase").val(data[i].site);
				}
				$("#table tbody:last-child").find(".deleteID").attr("id",data[i].id);
				if(i!==data.length-1){
					teachername=teachername+data[i].guideTeacher+",";
					testername=testername+data[i].assistant+",";
				}else{
					teachername=teachername+data[i].guideTeacher;
					testername=testername+data[i].assistant;
				}
				value[i]=data[i].assistant;
				teacherString[i]=data[i].guideTeacher;
			}
			$("#testername").val(testername);
			$("#adviser").val(teachername);
		}
	});
	
//获取选择的内容
$.ajax({
	type : 'POST',
	dataType : 'json',		
	url : 'getCollege.do',  
	async : false,
	cache : false,
	error : function(request) {
		alert("error");
	},
	success : function(data){
		for(var i=0;i<data.length;i++){//获取学院下拉框
			$("#collegeID").after(
			"<option class='rest' value="+data[i].dept+">"+ data[i].dept + "</option>"
			);
			$("#collegeID2").after(
					"<option class='rest' value="+data[i].dept+">"+ data[i].dept + "</option>"
					);
		}
	}
});
	
	$("#Applychart").show();
});
	
	
	//实习基地来源改变，对应的实习基地改变
$(document).on("change","#baseFrom",function(e){
	if(e.target.value==='校内基地'){
		$(this).parent().next().children(":first").show();
		$(this).parent().next().children(":first").addClass("flag");
		//$("#schoolBase").show();
		$(this).parent().next().children(":last").hide();
		$(this).parent().next().children(":last").val("");
		$(this).parent().next().children(":last").removeClass("flag");
	}
	if(e.target.value==='校外基地'){
		$(this).parent().next().children(":last").show();
		$(this).parent().next().children(":last").addClass("flag");
		$(this).parent().next().children(":first").hide();
		$(this).parent().next().children(":first").val("");
		$(this).parent().next().children(":first").removeClass("flag");
	}
	if(e.target.value===''){
		$(this).parent().next().children(":last").hide();
		$(this).parent().next().children(":first").hide();
		$(this).parent().next().children(":last").val("");
		$(this).parent().next().children(":first").val("");
		$(this).parent().next().children(":first").removeClass("flag");
		$(this).parent().next().children(":last").removeClass("flag");
	}
});	

	
	

//选择学院并且上传学院的名称，放回改学院老师的数据（包含老师名称和老师员工编号）
var obj2;
$(document).on("change","#selectCollege",function(){
	var college=$("#selectCollege").val();
	$("#selectTname option:gt(0)").remove();
	$.ajax({
		url : 'getCollege_Teacher.do',
		type : 'post',
		dataType : 'json',
		data : {
			"college" : college,								
		},
	success : function(data){
		obj2=data;//用于下面函数里面的判断
		for(var i=0;i<data.length;i++){//获取学校老师下拉框
			$("#teacherNmaeID").after(
			"<option class='rest' value="+data[i].name+">"+ data[i].name + "</option>"
			);
		}
	}
});
});

$(document).on("change","#selectCollege2",function(){
	var college=$("#selectCollege2").val();
	$("#selectTname2 option:gt(0)").remove();
	$.ajax({
		url : 'getCollege_Teacher.do',
		type : 'post',
		dataType : 'json',
		data : {
			"college" : college,								
		},
	success : function(data){
		
		for(var i=0;i<data.length;i++){//获取老师名字下拉框
			$("#teacherNmaeID2").after(
			"<option class='rest' value="+data[i].name+">"+ data[i].name + "</option>"
			);
		}
	}
});
});

var selectNum;
$(document).on("click",".choice2",function(){//点击选择弹出 
	selectNum=$(this).closest("tbody").find(".mark").html()-1;
	$("#Selectteacher").modal('show');
	$("#selectTname").val("");
	$("#leadteachername").val(teacherString[selectNum]);
	$("#selectCollege").val("");
});

$(document).on("change","#selectTname2",function(e){//将指导老师姓名显示在界面中
	var teststring=$("#leadteachername").val();
	var testvalue=teststring.split(" ");
	testvalue.push(e.target.value);	
	teststring=testvalue.join(" ");
	$("#leadteachername").val(teststring);
	
});

$(document).on("click","#finished2",function(){//点击确定之后讲指导老师姓名在表格中显示出来
	var tester=$("#leadteachername").val();
	if(tester===""){
		bootbox.alert({
			message : "指导老师不能为空",
			size : 'small'
		});
		return;
	}else{
		teacherString[selectNum]=tester;
	}
	$(".tbodyID").each(function(){
		var tea=$(this).find('.mark').html()-1;
		if(tea===selectNum){
			$(this).find('#Tea').text("老师："+tester);
		}
	});
	var str=teacherString.join(',');
	$("#adviser").val(str);
});
var value3=[];
$(document).on("click",".choice",function(){//点击选择弹出 
	
	selectNum=$(this).closest("tbody").find(".mark").html()-1;
	$("#Selectname").modal('show');
	$("#selectTname").val("");
	$("#tester").val(value[selectNum]);
	$("#selectCollege").val("");
});
	
$(document).on("change","#selectTname",function(e){//将实验员姓名显示在界面中，并且在选择的同时根据实验员的职工编号判断有没有选择同一人
	var teststring=$("#tester").val();
	var testvalue=teststring.split(" ");
	testvalue.push(e.target.value);
	teststring=testvalue.join(" ");
	$("#tester").val(teststring);
	
});
	
$(document).on("click","#finished",function(){//点击确定之后讲实验员姓名在表格中显示出来
	var tester=$("#tester").val();
	if(tester===""){
		bootbox.alert({
			message : "实验员不能为空",
			size : 'small'
		});
		return;
	}else{
		value[selectNum]=tester;
	}	
	$(".tbodyID").each(function(){
		var tea=$(this).find('.mark').html()-1;
		if(tea===selectNum){
			$(this).find('#tes').text("实验员："+tester);
		}
	});
	var str=value.join(',');
	$("#testername").val(str);
});
					
$(document).on("click","#addTbody",function(){//添加一条空表的记录
	$("#table tbody:last-child").after(tbodyStyle);
	var tbNum=$("#table").children('tbody').length;
	$("#table tbody:last-child").find(".mark").html(tbNum-2);
	$.ajax({
		type : 'POST',
		dataType : 'json',		
		url : 'getPlanAim.do',  
		async : false,
		cache : false,
		data:{
			"mid":obj[Oneindex].mid
		},
	success : function(data){
		for(var i=0;i<data[0].length;i++){//获取校内基地的实习地点下拉框
			$("#table tbody:last-child").find("#schoolBaseID").after(
			"<option class='rest' value="+data[0][i].bname+">"+ data[0][i].bname + "</option>"
			);
		}
		for(i=0;i<data[1].length;i++){//获取实习目的下拉框
			$("#table tbody:last-child").find("#aimID").after(
			"<option class='rest' id="+data[1][i].id+" value="+data[1][i].aim+">"+ data[1][i].aim + "</option>"
			);
		}
	}
});
});
	
$(document).on("click",".deleteID",function(){//弹出框里面的记录删除
	var judget=$(this).attr("id");
	var rowNum=$(this).closest("tbody").find(".mark").html()-1;
	$(this).closest("tbody").remove();
	if(judget!==""){
		$.ajax({
			url:'deleteClassRecord.do',
			type:"POST",
			dataType:"json",
			data:{
				"planid":judget
			},
			success : function(msg){
				$(".mark").each(function(){
				var htmlValue=$(this).html();
				if(htmlValue>(rowNum+1)){
					$(this).html(htmlValue-1);
					}
				});
				teacherString.splice(rowNum,1);
				showName=teacherString.join(",");
				$("#adviser").val(showName);
				value.splice(rowNum,1);
				var value2=value.join(",");
				$("#testername").val(value2);
				bootbox.alert({
					message : "删除成功",
					size : 'small'
				});											
			}
		});
	}else{	
		$(".mark").each(function(){
			var htmlValue=$(this).html();
			if(htmlValue>(rowNum+1)){
				$(this).html(htmlValue-1);
			}
		});
		teacherString.splice(rowNum,1);
		showName=teacherString.join(",");
		$("#adviser").val(showName);
		value.splice(rowNum,1);
		var value2=value.join(",");
		$("#tester").val(value2);
	}

});	
	
	
$("#save").click(function(){//弹出框的保存
	var x=0;
	var y=0;
	var week="";        var startweek="";
	var endweek="";     var content="";
    var category="";
	var practiceClass="";     var phone="";
	var aim="";     var Tea="";
	var tes="";
	$(".tbodyID").each(function(){
		y++;
		week=$(this).find("#weekend").val();
		if(week===""){
			return false;
		}
				
		startweek=$(this).find("#startweek").val();
		if(startweek===""){
			return false;
		}
		
		endweek=$(this).find("#endweek").val();
		if(endweek===""){
			return false;
		}
		
		content=$(this).find("#content").val();
		if(content===""){
			return false;
		}
		
		var sSite=$(this).find("#schoolBase").val();
		var oSite=$(this).find("#outBase").val();
		if(sSite===""&&oSite===""){
			x++;
			return false;
		}
		
		category=$(this).find("#category").val();
		if(category===""){
			return false;
		}
		
		practiceClass=$(this).find("#practiceClass").val();
		if(practiceClass===""){
			return false;
		}
		
		phone=$(this).find("#phone").val();
		if(phone===""){
			return false;
		}
		
		aim=$(this).find("#aim").val();
		if(aim===""){
			return false;
		}
		
		Tea=$(this).find("#Tea").text();
		if(Tea===""){
			return false;
		}
		
		tes=$(this).find("#tes").text();
		if(tes===""){
			return false;
		}
	});
	
	if(week===""){
		bootbox.alert({
			message : "请填写第"+y+"条记录的实习周次",
			size : 'small'
		});
		return;
		}
	if(startweek===""){
		bootbox.alert({
			message : "请填写第"+y+"条记录的开始时间",
			size : 'small'
		});
		return;
		}
	if(endweek===""){
		bootbox.alert({
			message : "请填写第"+y+"条记录的结束时间",
			size : 'small'
		});
		return;
		}
	var start=startweek.split("-");
	var end=endweek.split("-");
	var time=0;
	if((end[0]-start[0])<0){
		time++;
	}else{
		if(end[0]===start[0]){
			if((end[1]-start[1])<0){
				time++;
			}else{
				if(end[1]===start[1]){
				   if((end[2]-start[2]<0)){
					   time++;
				   }
				   }
			}
		}
	}
	if(time!==0){
		bootbox.alert({
			message : "第"+y+"条记录的结束时间有错误",
			size : 'small'
		});
		return;
	}
	if(content===""){
		bootbox.alert({
			message : "请填写第"+y+"条记录的实习内容",
			size : 'small'
		});
		return;
		}
	if(x!==0){
		bootbox.alert({
			message : "请填写第"+y+"条记录的实习地点",
			size : 'small'
		});
		return;
	}
	
	if(category===""){
		bootbox.alert({
			message : "请选择第"+y+"条记录的实习类别",
			size : 'small'
		});
		return;
		}
	if(practiceClass===""){
		bootbox.alert({
			message : "请选择第"+y+"条记录的实习形式",
			size : 'small'
		});
		return;
		}
	if(phone===""){
		bootbox.alert({
			message : "请填写第"+y+"条记录的联系电话",
			size : 'small'
		});
		return;
		}
	if(aim===""){
		bootbox.alert({
			message : "请选择第"+y+"条记录的实习目的",
			size : 'small'
		});
		return;
		}
	if(Tea===""){
		bootbox.alert({
			message : "请选择第"+y+"条记录的指导老师",
			size : 'small'
		});
		return;
		}
	if(tes===""){
		bootbox.alert({
			message : "请选择第"+y+"条记录的实验员",
			size : 'small'
		});
		return;
		}
	bootbox.confirm({
			message: "确定保存？",
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
					var str="(";
					var y=0;
					$(".tbodyID").each(function(){
						if(y!==0){
							str=str+",(";
						}
						//var b=$(this).find(".adviser2").val();
						var c=$(this).find(".mark").html()-1;
						str=str+"'"+teacherString[c]+"'"+",'"+value[c]+"'";
						
						var x=0;
						$(this).find(".flag").each(function(){
							x++;
							if(x===1){
								if($(this).val()===""){
								str=str+','+"null";
								}else{
									str=str+","+$(this).val();
								}
							}
							if(x<=10&&x>1){
								if($(this).val()===""){
								str=str+','+"null";
								}else{
									str=str+","+"'"+$(this).val()+"'";
								}
							}
							if(x===11){
								str=str+","+$(this).find("option:selected").attr("id");
							}
							if(x===12){
								if($(this).val()===""){
								str=str+','+"null";
								}else{
									str=str+","+"'"+$(this).val()+"'";
								}
							}
							
						});
						str=str+","+obj[Oneindex].id+",'"+obj[Oneindex].semester+"'"+")";
						y++;
					});
					$.ajax({
						type : 'POST',
						dataType : 'json',		
						url : 'savePlanModify.do',  
						async : false,
						cache : false,
						error : function(request) {
							alert("error");
						},
						data:{
							"courseID":obj[Oneindex].id,							
							"str":str,
						},
						success : function(msg) {
							//alert("hah");
							bootbox.alert({
								message : "保存成功",
								size : 'small'
							});
						}
					});
					
				}
			}
	});
});
//</end>
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
