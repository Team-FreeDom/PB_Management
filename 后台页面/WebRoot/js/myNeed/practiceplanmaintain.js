// JavaScript Document
var obj = [];
var flag = 0;
var table=null;
var str = null;
var tidFlag=true;
var midFlag=true;
var cidFlag=true;
var widFlag=true;
var tag=[true,true,true,true];
var userWarn=[];
var Oneindex;
var value=[];
var writeName="";
var showName="";
var teacherString=[];
var majorString=[];
$(document)
		.ready(
				function() {
					
					$('[data-toggle="tooltip"]').tooltip();
					
					if($("#ta3").text()=="false"){
						bootbox.alert({
							message : "您导入的Excel文件格式有错,请重新选择",
							size : 'small'
						});
					}else if($("#ta3").text()=="true"){
						bootbox.alert({
							message : "导入成功",
							size : 'small'
						});
					}
					
					var exportTag=$("#exportTag").text();
					if(exportTag=="0"){
						bootbox.alert({
							message : "没有可导出的数据",
							size : 'small'
						});
					}else if(exportTag=="500"){
						bootbox.alert({
							message : "导出失败",
							size : 'small'
						});
					}
					
					// 获取学院，学期
					$.ajax({
						type : 'POST',
						dataType : 'json',
						url : 'getPlanMaintainInfo.do',
						async : false,
						cache : false,
						error : function(request) {
							bootbox.alert({
								message : "请求异常",
								size : 'small'
							});
						},
						success : function(data) {          
				          
							for (var i = 0; i < data.semester.length; i++) {
								$("#termYearID").after(
										"<option value=" + data.semester[i]
												+ " class='semeUp1'>" + data.semester[i]
												+ "</option>");								

							}
							
							  for ( var i=0;i<data.college.length;i++) {
							  $("#Acollegeh").after( "<option value="+data.college[i].dept+" class='semeUp2'>" + data.college[i].dept + "</option>");
							  $("#college_00").after( "<option value="+data.college[i].dept+" class='semeUp2'>" + data.college[i].dept + "</option>");
							  }
							 

						}

					});
					
					//导入功能刷新页面，给学年学期赋值，获取数据
					$("#termYear").val($("#ta1").text());
					$("#semester").val($("#ta2").text());
					if($("#termYear").val()!=''&&$("#semester").val()!=''){
					   str=$("#termYear").val()+'-'+$("#semester").val();
					}else{
						//获取最新的学年学期
						$.ajax({
							type : 'POST',
							dataType : 'json',
							url : 'getLatestYear.do',
							async : false,
							cache : false,
							error : function(request) {
								bootbox.alert({
									message : "请求异常",
									size : 'small'
								});
							},
							success : function(data) {          
								$("#termYear").val(data.list[0]);
								$("#semester").val(data.list[1]);
								str=$("#termYear").val()+'-'+$("#semester").val();						
							}

						});
					}
				
					table = $("#practiceplanmaintain")
							.DataTable(
									{
										"processing" : true,
										"serverSide" : true,
										"bSort" : false,
										"bFilter" : false,
										"aLengthMenu" : [ 5, 10, 20, 30], // 动态指定分页后每页显示的记录数。
										"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
										"iDisplayLength" : 10, // 默认每页显示多少条记录
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
											//"sWidth" : "2%"
										}, {
											"mData" : "semester",// 学期学年
											"orderable" : false,
											"sDefaultContent" : "",
											//"sWidth" : "3%"
										}, {
											"mData" : "cid",// 课程代码
											"orderable" : false,
											"sDefaultContent" : "",
											//"sWidth" : "3%"
										}, {
											"mData" : "coursename",// 课程名称
											"orderable" : false,
											"sDefaultContent" : "",
											//"sWidth" : "8%"
										}, {
											"mData" : "count",// 人数
											"orderable" : true,
											"sDefaultContent" : "",
											//"sWidth" : "3%"
										},{
											"mData" : "composition",// 教学班组成
											"orderable" : false,
											"sDefaultContent" : "",
											//"sWidth" : "8%"
										}, {
											"mData" : "college",// 开课学院
											"orderable" : true,
											"sDefaultContent" : "",
											//"sWidth" : "6%"
										},

										{
											"mData" : "weekClassify",// 周学时
											"orderable" : true,
											"sDefaultContent" : "",
											//"sWidth" : "3%"
										}, {
											"mData" : "credit",// 学分
											"orderable" : true,
											"sDefaultContent" : "",
											//"sWidth" : "2%"
										}, {
											"mData" : "courseNature",// 课程性质
											"orderable" : false,
											"sDefaultContent" : "",
											//"sWidth" : "4%"
										}, {
											"mData" : "courseCategory",// 课程类别
											"orderable" : false,
											"sDefaultContent" : "",
											//"sWidth" : "4%"
										}, {
											"mData" : "tid",// 教职工号
											"orderable" : true,
											"sDefaultContent" : "",
											//"sWidth" : "4%"
										}, {
											"mData" : "tname",// 教师姓名
											"orderable" : true,
											"sDefaultContent" : "",
											//"sWidth" : "4%"
										}, {
											"mData" : "week",// 起始周
											"orderable" : false,
											"sDefaultContent" : "",
											//"sWidth" : "6%"
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
											"sDefaultContent" : "",
											//"sWidth" : "4%"
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
					
					

					
					
					
					
					chooseSeme("Ainsemester");
					chooseSeme("AteamYearw");
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
					

					//显示全部数据
					$("#showAllInfo").click(function(){
						$("#termYear").change();					
					});


					/*学年学期的js控制---start*/
					$("#termYear").on("change", function() {
						
						obj = [];
						var termYear = $(this).val();
						var semester = $("#semester").val();
						if (termYear!= "") {
							if(semester!=""){
								str = termYear + '-' + semester;
							}else{
								str=null;
							}								
						
						}else{
							$("#semester").val("");
							str=null;
						}
						
						table = $("#practiceplanmaintain").DataTable(
								{
									"processing" : true,
									"serverSide" : true,
									"bSort" : false,
									"bFilter" : false,
									"aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
									"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
									"iDisplayLength" : 10, // 默认每页显示多少条记录
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
									},{
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
						if(semester!=""){
							str = termYear + '-' + semester;
						}else{
							str=null;
						}	
						
						//table.Table.Rows.Clear();
						 
						
						table = $("#practiceplanmaintain")
						.DataTable(
								{
									"processing" : true,
									"serverSide" : true,
									"bSort" : false,
									"bFilter" : false,
									"aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
									"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
									"iDisplayLength" : 10, // 默认每页显示多少条记录
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
									},{
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
						var id = $(this).val().trim();
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
								bootbox.alert({
									message : "请求异常",
									size : 'small'
								});
							},
							success : function(data) {
								if(!data.flag){
								bootbox.alert({
									message : "不存在该教师,请重新填写",
									size : 'small'
								});
								//$("#intid").focus($(this).val());
								tidFlag=false;
								}else{
								tidFlag=true;	
								}
							}

						});
					});
					
					$(document).on("blur", "#inweek", function() {
						var week=$("#inweek").val().trim();
						var dataFormat=/^([12]?[1-9]|0)-([12]?[1-9]|0)(,([12]?[1-9]|0)-([12]?[1-9]|0))*$/;
						if(week!=""){
						if (!dataFormat.exec(week)) {
							bootbox.alert({
								message : "输入不正确，请确认您的起始周格式是否正确，是否超过了正常的教学周",
								size : 'small'
							});
							widFlag=false;
						}else{
							widFlag=true;	
						}
						}
					});	
					
					
					$(document).on("blur", "#inweekClassify,#weekClassify_0", function() {
						var value=$(this).val();
						value=value.trim();
						var dataFormatWeek=/^[0-9]+\.?[0-9]*$/;
						if(value!=""){
							if (!dataFormatWeek.exec(value)) {
								bootbox.alert({
									message : "周学时格式不对，只能输入数字",
									size : 'small'
								});
								tag[0]=false;
							}
							else{
								tag[0]=true;	
							}
						}
					});
					
					$(document).on("blur", "#incredit,#credit_0", function() {
						var value=$(this).val();
						value=value.trim();
						var dataFormatWeek=/^[0-9]+\.?[0-9]*$/;
						if(value!=""){
							if (!dataFormatWeek.exec(value)) {
								bootbox.alert({
									message : "学分格式不对，只能输入数字",
									size : 'small'
								});
								tag[1]=false;
							}else{
								tag[1]=true;	
							}
						}
					});
					
					$(document).on("blur", "#incount,#count_0", function() {
						var value=$(this).val();
						value=value.trim();
						var dataFormatWeek=/^[0-9]*$/;
						if(value!=""){
							if (!dataFormatWeek.exec(value)) {
								bootbox.alert({
									message : "人数格式不对，只能输入整数",
									size : 'small'
								});
								tag[2]=false;
							}else{
								tag[2]=true;	
							}
						}
					});
					
					$(document).on("blur", "#inselectedCount,#selectedCount_0", function() {
						var value=$(this).val();
						value=value.trim();
						var dataFormatWeek=/^[0-9]*$/;
						if(value!=""){
							if (!dataFormatWeek.exec(value)) {
								bootbox.alert({
									message : "已选人数格式不对，只能输入整数",
									size : 'small'
								});
								tag[3]=false;
							}else{
								tag[3]=true;	
							}
						}
					});
					
					$(document).on("blur", "#incid", function() {
						var cid = $("#incid").val().trim();
						var semester=$("#insemester2").val();
						if(cid==""){
							return;
						}
						$.ajax({
							type : 'POST',
							dataType : 'json',
							data:{"cid":cid,"semester":semester},
							url : 'checkIsThisCid.do',
							async : false,
							cache : false,
							error : function(request) {
								bootbox.alert({
									message : "请求异常",
									size : 'small'
								});
							},
							success : function(data) {
								if(data.flag=="1"){
								bootbox.alert({
									message : "已存在该课程代码,请重新填写",
									size : 'small'
								});								
								cidFlag=false;
								}else{
									cidFlag=true;
								}
							}

						});
					});
					
					$(document).on("click", "#saveAdd", function() {// 保存一条增加的实习记录						
						var college = $("#incollege").val().trim();
						var cid =$("#incid").val().trim();
						var count =$("#incount").val().trim();
						var selectedCount = $("#inselectedCount").val().trim();
						var composition = $("#incomposition").val().trim();
						var coursename = $("#incoursename").val().trim();
						var weekhours =$("#inweekClassify").val().trim();// 周学时
						var credit = $("#incredit").val().trim();// 学分
						var courseNature = $("#incourseNature").val().trim();
						var courseCategory = $("#incourseCategory").val().trim();
						var tid = $("#intid").val().trim();
						var tname = $("#intname").val().trim();
						var week = $("#inweek").val().trim();// 起始周
						var checkMethod = $("#incheckMethod").val().trim();										
						var insemester2=$("#insemester2").val().trim();
						var format=/^([\u4e00-\u9fa5|\w|-]+[,]?)+$/;
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
						if(!cidFlag){
							bootbox.alert({
								message : "已存在该课程代码,请重新填写",
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
						if(!tidFlag){
							bootbox.alert({
								message : "不存在该教师",
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
						if(!widFlag){
							bootbox.alert({
								message : "输入不正确，请确认您的起始周格式是否正确，是否超过了正常的教学周",
								size : 'small'
							});
							return;
						}					
						if(!tag[2]){
                        	bootbox.alert({
								message : "人数格式不对，只能输入整数",
								size : 'small'
							});
                        	return;
						}
                        if(!tag[3]){
                        	bootbox.alert({
								message : "已选人数格式不对，只能输入整数",
								size : 'small'
							});
                        	return;
                        }
						if(!tag[0]){
							bootbox.alert({
								message : "周学时格式不对，只能输入数字",
								size : 'small'
							});
							return;
						}
                        if(!tag[1]){
                        	bootbox.alert({
								message : "学分格式不对，只能输入数字",
								size : 'small'
							});
                        	return;
						}	
                        if(composition==""){
                        	bootbox.alert({
								message : "请填写班级组成",
								size : 'small'
							});
                        	return;
                        }
                        if(!format.test(composition)){
                        	bootbox.alert({
								message : "班级之间用逗号','隔开",
								size : 'small'
							});
                        	return;
                        }
                        
                        
                        
						var str1 = "('" + insemester2 + "','" + college + "','" + cid + "',"
						+ count + ',' + selectedCount + ",'" + composition + "','"
						+ coursename + "'," + weekhours + ',' + credit + ",'"
						+ courseNature + "','" + courseCategory + "','" + tid + "','"
						+ tname + "','" + week + "','" + checkMethod + "'";
						
						bootbox.confirm({
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
							callback : function(
									result) {
								if (result) {						
						$.ajax({
							type : 'POST',
							dataType : 'json',
							data:{"str":str1,"semester":insemester2,"weekCount":week},
							url : 'addOnePlanInfo.do',
							async : false,
							cache : false,
							error : function(request) {
								bootbox.alert({
									message : "保存失败",
									size : 'small'
								});
							},
							success : function(data) {
								if(data.flag=="操作成功"){
								bootbox.alert({
									message : "操作成功",
									size : 'small'
								});
								if($("#termYear").val()!=semester){
								semeUp(insemester2,0);
								}
								table.draw(false);
								}else if(data.flag=="操作失败"){
									bootbox.alert({
										message : "操作失败",
										size : 'small'
									});
								}
							}

						});
						
						$("#addPraItem").hide();
								}
							}});
					});
					/*增加实习计划js控制---end*/
					
					
					/*修改实习计划js控制---start*/
					$(document).on("click", "#saveUpdate", function() {
						var id= $("#index").text().trim();
						var college = $("#college_0").val().trim();
						var cid =$("#cid_0").val().trim();
						var count =$("#count_0").val().trim();
						var selectedCount = $("#selectedCount_0").val().trim();
						var composition = $("#composition_0").val().trim();
						var coursename = $("#coursename_0").val().trim();
						var weekhours =$("#weekClassify_0").val().trim();// 周学时
						var credit = $("#credit_0").val().trim();// 学分
						var courseNature = $("#courseNature_0").val().trim();
						var courseCategory = $("#courseCategory_0").val().trim();
						var tid = $("#tid_0").val().trim();
						var tname = $("#tname_0").val().trim();
						var week = $("#week_0").val().trim();// 起始周
						var checkMethod = $("#checkMethod_0").val().trim();									
						var insemester2=$("#semsYear_0").val().trim();	
						var format=/^([\u4e00-\u9fa5|\w|-]+[,]?)+$/;
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
						 if(composition==""){
	                        	bootbox.alert({
									message : "请填写班级组成",
									size : 'small'
								});
	                        	return;
	                        }
	                        if(!format.test(composition)){
	                        	bootbox.alert({
									message : "班级之间用','隔开",
									size : 'small'
								});
	                        	return;
	                        }
						   
						var str1 = "("+id+",'" + cid+ "'," + count+ "," + selectedCount + ",'"
						+ composition + "','" + college + "','" + coursename + "',"
						+ weekhours + "," +credit + ",'"+courseNature+"','" +courseCategory + "','"
						+ checkMethod + "','" +tid + "','"
						+ tname + "','" + insemester2 + "','" + week+ "')";
						
						bootbox.confirm({
							message : "确定修改？",
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
						
						$.ajax({
							type : 'POST',
							dataType : 'json',
							data:{"str":str1,"id":id},
							url : 'alterOneRecord.do',
							async : false,
							cache : false,
							error : function(request) {
								bootbox.alert({
									message : "修改失败",
									size : 'small'
								});
							},
							success : function(data) {
								
								bootbox.alert({
									message : data.msg,
									size : 'small'
								});								
							}
						});
						
						$("#updatePlanItem").hide();
						table.draw(false);
						
								}
								}   });
					});
					/*修改实习计划js控制---end*/					
					
					//导入文件的js控制			
					$("#daoru").click(function(){
						$("#teamYearw").val('');
						$("#semesterw").val('');
						$("#oneSemesterTime").val('');	
						$("#weekTi").css("display","none");
						$("#fileResource").val('');
					});
					
					$(document).on("click","#certainimport",function(){		
						var fileResource=$("#fileResource").val();
						if($("#fileResource").val()==""){
							bootbox.alert({
								message : "请选择文件",
								size : 'small'
							});
							
							return;
						}
						var fireL=fileResource.lastIndexOf(".");
						fileResource=fileResource.substring(fireL);
						if(fileResource!=".xls"&&fileResource!=".xlsx"){
							bootbox.alert({
								message : "请导入Excel格式文档",
								size : 'small'
							});
							$("#fileResource").val("");
							return;
						}
						$("#daoruform").submit();
						$("#import").modal('hide');
					});
					
					$(document).on("change","#semesterw,#teamYearw",function(){						
						var semesterw=$("#semesterw").val();
						var teamYearw=$("#teamYearw").val();
						if(semesterw==""||teamYearw==""){
							$("#weekTi").hide();
							$("#oneSemesterTime").val('');
						}else{
							$("#weekTiSpan").html(teamYearw+"学年的第"+semesterw+"学期第1周的具体时间");
							$("#weekTi").show();
						}
					});
					
				
					$("#certainWeekTime").click(function(){
						
						var teamYearw=$("#teamYearw").val();
						var semesterw=$("#semesterw").val();
						var oneSemesterTime=$("#oneSemesterTime").val();
						
						if(teamYearw==""){
							bootbox.alert({
								message : "请选择要导入的学年",
								size : 'small'
							});
							return;
						}

						if(semesterw==""){
							bootbox.alert({
								message : "请选择要导入的学期",
								size : 'small'
							});
							return;
						}
					
						if(oneSemesterTime==""){
							bootbox.alert({
								message : "请完善导入"+teamYearw+"学年的第"+semesterw+"学期第1周的具体时间",
								size : 'small'
							});
							return;
						}
						
						teamYearw=teamYearw+'-'+semesterw;
						
							
								$("#writeWeekTime").modal('hide');
								$("#weekTi").hide();
								$("#semesterfile").val(teamYearw);
								$("#timeDi").val(oneSemesterTime);
								$("#import").modal('show');
						/*	}

						});
						*/
						
					});
					
					//导出文件的js控制
                     $("#confirmDaoButton").click(function(){
                    	                    			
 							$("#daochuForm").submit();
 							$("#export").modal('hide');
					});
                     
                     $("#chu").click(function(){
                    	 $(".daoYearh").remove();
                    	 $.ajax({
								type : 'POST',
								dataType : 'json',								
								url : 'getReadyYear.do',
								async : false,
								cache : false,
								error : function(request) {
									bootbox.alert({
										message : "请求异常",
										size : 'small'
									});
								},
								success : function(data) {
																	
									 for ( var i=0;i<data.semester.length;i++) {
									 $("#daoYearh").after(
											"<option value=" + data.semester[i]
													+ " class='daoYearh'>" + data.semester[i]
													+ "</option>");	
									 }
									}							

							});     
                    	
						$(".daosem").remove();
						$("#daosemsterh").after("<option value='1' class='daosem'>1</option><option value='2' class='daosem'>2</option>");
						$(".removeCollege").remove();
						getCollege();
						$("#daoColleget").val('-1'); 
 						$("#daosemster").val('-1');
 						
 						$("#export").modal('show');
 					});
                     
                     $(document).on("change","#daoYear",function(){
                    	 if($("#daoYear").find('option').length<=2){
                    		 return;
                    	 }
                    	 var daoYear=$("#daoYear").val();                    	 
                    	 $(".daosem").remove();
                    	 $(".removeCollege").remove();
                    	 if(daoYear=="-1"){                    		
                    		 $("#daosemsterh").after("<option value='1' class='daosem'>1</option><option value='2' class='daosem'>2</option>");
                    	 }else{
                    		 $.ajax({
 								type : 'POST',
 								dataType : 'json',								
 								url : 'getReadySem.do',
 								data:{"year":daoYear},
 								async : false,
 								cache : false,
 								error : function(request) {
 									bootbox.alert({
 										message : "请求异常",
 										size : 'small'
 									});
 								},
 								success : function(data) {
 																	
 									 for ( var i=0;i<data.semNumber.length;i++) {
 									 $("#daosemsterh").after(
 											"<option value=" + data.semNumber[i]
 													+ " class='daosem'>" + data.semNumber[i]
 													+ "</option>");	
 									 }
 									}							

 							});  
                    		 getCollege();
                    	 }
                     });
                                         
                     $(document).on("change","#daosemster",function(){
                    	 if($("#daosemster").find('option').length<=2){
                    		 return;
                    	 }
                    	 getCollege();
                     });
                     
                     //修改实习记录
                     $("#updatePlan").click(function(){
                    	 var i=$("input[type='checkbox'][name='allcheckbox']:checked").length;
                    	 if(i==0){
                    		 bootbox.alert({
 								message : "请选择一项您要进行修改的内容",
 								size : 'small'
 							}); 
                     	   return; 
                    	 }else if(i>1){
                    		 bootbox.alert({
  								message : "一次只能修改一项内容",
  								size : 'small'
  							}); 
                      	   return; 
                    	 }
                    	 var index=$("input[type='checkbox'][name='allcheckbox']:checked").prop("id");
                    	 $("#index").text(obj[index].id);
                    	 $("#semsYear_0").val(obj[index].semester);
                    	 $("#cid_0").val(obj[index].cid);
                    	 $("#coursename_0").val(obj[index].coursename);
                    	 $("#courseNature_0").val(obj[index].courseNature);
                    	 $("#courseCategory_0").val(obj[index].courseCategory);
                    	 $("#college_0").val(obj[index].college);
                    	 $("#tname_0").val(obj[index].tname);
                    	 $("#tid_0").val(obj[index].tid);
                    	 $("#mid_0").val(obj[index].mid);
                    	 $("#week_0").val(obj[index].week);
                    	 $("#count_0").val(obj[index].count);
                    	 $("#selectedCount_0").val(obj[index].selectedCount);
                    	 $("#weekClassify_0").val(obj[index].weekClassify);
                    	 $("#credit_0").val(obj[index].credit);
                    	 $("#composition_0").val(obj[index].composition);
                    	 $("#checkMethod_0").val(obj[index].checkMethod);
                    	 $("#major_oriented_0").val(obj[index].major_oriented);
                    	 
                    	var collegeName_1=$("#selectCollege_5").find("option:gt(0)");
 						var collegeName_2=$("#selectCollege_6").find("option:gt(0)");						
 						if(collegeName_1.length==0||collegeName_2.length==0){
 							collegeName_1.remove();
 							collegeName_2.remove();
 							$.ajax({
 								type : 'POST',
 								dataType : 'json',		
 								url : 'getCollege.do',  
 								async : false,
 								cache : false,
 								error : function(request) {
 									bootbox.alert({
 										message : "请求异常",
 										size : 'small'
 									});
 								},
 								success : function(data){
 									for(var i=0;i<data.length;i++){//获取学院下拉框
 										$("#collegeID_5").after(
 												"<option class='rest' value="+data[i].dept+">"+ data[i].dept + "</option>"
 												);
 										$("#collegeID_6").after(
 												"<option class='rest' value="+data[i].dept+">"+ data[i].dept + "</option>"
 												);										
 									}
 								}
 							});							
 						}	
                    	 
                    	 $("#updatePlanItem").show();
                     });
                     
					
					// 点击 检测数据完整性
					$(document).on("click","#checkIsSave",function() {
                           if(str==null||str=='-'){
                        	   bootbox.alert({
    								message : "请先选择学年学期",
    								size : 'small'
    							}); 
                        	   return;
                           }
                           userWarn=[];
                           var checkSearchValue=table.search();//获取table的模糊查询框的输入值
										table = $("#practiceplanmaintain").DataTable(
														{
															"processing" : true,
															"serverSide" : true,
															"bSort" : false,
															"bFilter" : false,
															"aLengthMenu" : [5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
															"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
															"iDisplayLength" : 10, // 默认每页显示多少条记录
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
										if(checkSearchValue!=""&&checkSearchValue!=null){
											table.search(checkSearchValue).draw(false);
										}
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
						calendar.hide();
						teacherString=[];
						value=[];

					});

					$(document).on("click", "#add", function() {// 增加一条实习记录弹出框的弹出
						var termYear = $("#termYear").val();
						var semester = $("#semester").val();
						if (termYear == "") {	
							if(semester == ""){
							   bootbox.alert({
								message : "请选择学年学期",
								size : 'small'
							});						
							}else{
								bootbox.alert({
									message : "请选择学年",
									size : 'small'
								});						
							}
							return;	
						}else if(semester == ""){
							 bootbox.alert({
									message : "请选择学期",
									size : 'small'
								});							
								return;	
						}	
						//初始化数据
						$("#addPraItem").find("input").val("");
						$("#addPraItem").find("select").val("");
						$("#insemester2").val(termYear+'-'+semester);
						
						var collegeName_1=$("#selectCollege3").find("option:gt(0)");
						var collegeName_2=$("#selectCollege4").find("option:gt(0)");						
						if(collegeName_1.length==0||collegeName_2.length==0){
							collegeName_1.remove();
							collegeName_2.remove();
							$.ajax({
								type : 'POST',
								dataType : 'json',		
								url : 'getCollege.do',  
								async : false,
								cache : false,
								error : function(request) {
									bootbox.alert({
										message : "请求异常",
										size : 'small'
									});
								},
								success : function(data){
									for(var i=0;i<data.length;i++){//获取学院下拉框
										$("#collegeID3").after(
												"<option class='rest' value="+data[i].dept+">"+ data[i].dept + "</option>"
												);
										$("#collegeID4").after(
												"<option class='rest' value="+data[i].dept+">"+ data[i].dept + "</option>"
												);										
									}
								}
							});							
						}						
						$("#addPraItem").show();
					});
					
					
/**弹出框js--start*/
					//显示实习申请表
					var tbodyStyle='<tbody class="tbodyID"><tr style="background:#3071a9; color:#FFF">'
						+'<td>序号</td>'
						+'<td>周次<span class="starColor">*</span></td>'
						+'<td>班级<span class="starColor">*</span></td>'
						+'<td>开始时间<span class="starColor">*</span></td>'
						+'<td>结束时间<span class="starColor">*</span></td>'							
						+'<td>实习基地来源<span class="starColor">*</span></td>'
						+'<td>实习地点<span class="starColor">*</span></td>'
						+'<td>实习类别<span class="starColor">*</span></td>'
						+'<td>实习形式<span class="starColor">*</span></td>'
						+'<td>备注</td>'
						+'<td>操作</td>'
					 +'</tr>'
					 +'<tr>'
					 +'<td rowspan="5"><sapn class="mark"></span></td>'
					 +'<td style="padding-top:5px"><select id="weekend" type="text" class="text-center inputWidth flag"><option value="" id="weekend_option">请选择</select></td>'
					 +'<td style="padding-top:5px"><select id="class_one" type="text" class="text-center inputWidth flag"><option value="" id="class_one_option">请选择</select></td>'
					 +'<td style="padding-top:5px"><input id="startweek" type="text"  readonly="readonly" class="flag startweek"></td>'
					 +'<td style="padding-top:5px"><input id="endweek" type="text" readonly="readonly" class="flag endweek"></td>'
					 
					 +'<td style="padding-top:5px"><select name="" id="baseFrom" class="flag"><option id="baseForm" value="">请选择</option></select></td>'
					 +'<td style="padding-top:5px" id="practicePlace"><select id="schoolBase" class="flag" style="display:none;"><option id="schoolBaseID" value="">请选择</option></select><a class="btn btn-primary" href="baseApply.jsp" style="display:none;">添加基地</a></td>'
					 +'<td style="padding-top:5px"><select id="category" class="flag"><option value="">请选择</option><option value="生产实习">生产实习</option><option value="教学实习">教学实习</option><option value="毕业实习">毕业实习</option><option value="综合实习">综合实习</option></select></td>'
					 +'<td style="padding-top:5px"><select name="" id="practiceClass" class="flag"><option value="">请选择</option><option value="集中">集中</option><option value="分散">分散</option></select></td>'
					 +'<td style="padding-top:5px"><input id="remark" type="text" class="flag"></td>'
					 +'<td rowspan="5"><span class="deleteID" id="">删除</span></td>'
					 +'</tr>'
					 +'<tr style="color:#3071a9;font-weight:bolder;">'
					 +'<td>实习基地联系人/电话<span class="starColor">*</span></td>'
					 +'<td>目的<span class="starColor">*</span></td>'
					 +'<td>实习经费预算</td>'
					 +'<td colspan="6">实习内容<span class="starColor">*</span></td>'
					 
					 +'</tr>'
					 +'<tr>'
					 
					 +'<td><input id="phone" type="text" class="flag"></td>'
					 +'<td><select id="aim" class="flag" style="width:150px;"><option id="aimID" value="">请选择</option></select></td>'
					 +'<td><input id="budget" type="text" class="inputWidth flag">万</td>'
					 +'<td colspan="6"><input id="content" type="text" class="flag" style="width:100%"></td>'						 
					 +'</tr>'
					 +'<tr style="color:#3071a9;font-weight:bolder;">'
					 +'<td colspan="3">面向专业<span class="starColor">*</span></td>'
					 +'<td colspan="3">实验员<span class="starColor">*</span></td>'
					 +'<td colspan="3">指导老师<span class="starColor">*</span></td>'
					 +'</tr>'
					 +'<tr>'
					 +'<td style="padding-bottom:15px" colspan="3"><form class="form-inline"><div class="Iwidth"><div class="input-group Iwidth"><input readonly type="text" class="form-control" id="facemajoy" placeholder="面向专业"><div class="input-group-addon choice3">选择</div></div></div></form></td>'
					 +'<td style="padding-bottom:15px" colspan="3"><form class="form-inline"><div class="Iwidth"><div class="input-group Iwidth"><input readonly type="text" class="form-control" id="tes" placeholder="实验员"><div class="input-group-addon choice">选择</div></div></div></form></td>'
					 +'<td style="padding-bottom:15px" colspan="3"><form class="form-inline"><div class="Iwidth"><div class="input-group Iwidth"><input readonly type="text" class="form-control" id="Tea" placeholder="指导老师"><div class="input-group-addon choice2">选择</div></div></div></form></td>'						 
					 +'</tr></tbody>';
				
				
				 $(document).on("click", "#practiceplanmaintain tbody tr td", function() {
					 $("#modalbody").removeClass("modalbody");	
						$("#modalbody").removeClass("modalbody2");
						$("#modalbody").removeClass("modalbody3");
				     var itLength=$(this).find("input").length;
					 if(itLength!=0){
					    return;
				       }
				Oneindex= $(this).parent('tr').find("input").attr("id");
				$("#weekend option:gt(0)").remove();//移除周次select的原来的option
				$("#division").val(obj[Oneindex].college);
				$("#classname").val(obj[Oneindex].coursename);
				$("#major").val(obj[Oneindex].major_oriented);
				$("#fromweek").val(obj[Oneindex].week);
				$("#class").val(obj[Oneindex].composition);
				$("#grade").val(obj[Oneindex].credit);
				$("#number").val(obj[Oneindex].count);
				$("#weeks").val(obj[Oneindex].weekClassify);
				$("#leaderTeacher").val(obj[Oneindex].tname);	
				
				majorString=[];//将面向专业的全局变量清空
				teacherString=[];//将指导老师的全局变量清空
				value=[];//将实验员的全局变量清空
				
				//获得周次的数组
				var fromweek=obj[Oneindex].week;
				var data_week_0=fromweek.split(',');
				var data_week=[];
				var data_week_1;				
				var data_week_3;
				for(var i in data_week_0){
					data_week_1=data_week_0[i].split('-');					
					var k=0;
					data_week_3=data_week_1[0];
					if(data_week_1[0]==data_week_1[data_week_1.length-1]){
						data_week.push(data_week_1[0]);
					}else{
					   while(data_week_3<data_week_1[data_week_1.length-1]){						
						data_week_3=Number(data_week_1[0])+(k++);
						data_week.push(data_week_3);
					}
					}
				}
				$.unique(data_week.sort(sortNumber));
				
				//获得班级的数组
				var composition=obj[Oneindex].composition;
				var data_composition=composition.split(',');
				$.unique(data_composition.sort(sortNumber));
				
				
				
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
						var majorname="";
						for(var i=0;i<data.length;i++){
							$("#table tbody:last-child").after(tbodyStyle);
							for(var week in data_week){
								$("#table tbody:last-child").find("#weekend_option").after(
										"<option value="+data_week[week]+">"+ data_week[week] + "</option>"
										);
							}	
							for(var composition_0 in data_composition){
								$("#table tbody:last-child").find("#class_one_option").after(
										"<option value="+data_composition[composition_0]+">"+ data_composition[composition_0] + "</option>"
										);
							}
							var site=data[i].site;
							$.ajax({
								type : 'POST',
								dataType : 'json',		
								url : 'getBasenameOfType.do',  
								async : false,
								cache : false,
								data:{
									"mid":obj[Oneindex].tid,
									"typename":data[i].source
								},
								error : function(request) {
									bootbox.alert({
										message : "请求异常",
										size : 'small'
									});
								},
								success : function(date){
									
									for(var i=0;i<date[0].length;i++){//获取基地类型
										$("#table tbody:last-child").find("#baseForm").after(
										"<option class='rest' value="+date[0][i].name+">"+ date[0][i].name+ "</option>"
										);
									}
									$("#table tbody:last-child").find("#schoolBase").select2({
										  data:date[1] ,
										  placeholder:site,
										  allowClear:false,
										  width:100,
										  dropdownAutoWidth:true
										});
									
									
									for(var t=0;t<date[2].length;t++){//获取实习目的下拉框
										$("#table tbody:last-child").find("#aimID").after(
												"<option class='rest' id="+date[2][t].id+" value="+date[2][t].aim+" data-placement='top' data-toggle='tooltip' title='"+date[2][t].aim+"'>"+ (date[2][t].aim.length>20?date[2][t].aim.substring(0,20)+"...":date[2][t].aim )+ "</option>"

										);
									}
									
									
								}
							});
							$("#table tbody:last-child").find(".mark").html(i+1);
							$("#table tbody:last-child").find("#weekend").val(data[i].week);
							$("#table tbody:last-child").find("#class_one").val(data[i].grade);//班级
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
							$("#table tbody:last-child").find("#Tea").val("老师:"+data[i].guideTeacher);
							$("#table tbody:last-child").find("#tes").val("实验员:"+data[i].assistant);
							$("#table tbody:last-child").find("#facemajoy").val("面向专业:"+data[i].major_oriented);
							
							$("#table tbody:last-child").find("#schoolBase").show();
							$("#table tbody:last-child").find("#schoolBase").addClass("flag");
							$("#table tbody:last-child").find("#schoolBase").val(data[i].site);	
							
							
							$("#table tbody:last-child").find(".deleteID").attr("id",data[i].id);
							if(i!==data.length-1){
								teachername=teachername+data[i].guideTeacher+",";
								testername=testername+data[i].assistant+",";
								majorname=majorname+data[i].major_oriented+',';
							}else{
								teachername=teachername+data[i].guideTeacher;
								testername=testername+data[i].assistant;
								majorname=majorname+data[i].major_oriented;
							}
							value[i]=data[i].assistant;
							teacherString[i]=data[i].guideTeacher;
							majorString[i]=data[i].major_oriented;
						}
						$("#testername").val(testername);
						$("#adviser").val(teachername);
						$("#major").val(majorname);
					}
				});
				$("#selectCollege option:gt(0)").remove();
				$("#selectCollege2 option:gt(0)").remove();
				$("#majorCollege option:gt(0)").remove();	
				//获取选择的内容
				$.ajax({
					type : 'POST',
					dataType : 'json',		
					url : 'getCollege.do',  
					async : false,
					cache : false,
					error : function(request) {
						bootbox.alert({
							message : "请求异常",
							size : 'small'
						});
					},
					success : function(data){
						for(var i=0;i<data.length;i++){//获取学院下拉框
							$("#collegeID").after(
							"<option class='rest' value="+data[i].dept+">"+ data[i].dept + "</option>"
							);
							$("#collegeID2").after(
									"<option class='rest' value="+data[i].dept+">"+ data[i].dept + "</option>"
									);
							$("#majorcollegeID").after(
									"<option class='rest' value="+data[i].dept+">"+ data[i].dept + "</option>"
									);
						}
					}
				});
				var tbodylength=$("#table tbody").size();

				if(screen.width<=1536&&tbodylength>2){
					$("#modalbody").addClass("modalbody");
				}
				if(screen.width<=1708&&screen.width>1536&&tbodylength>3){
					$("#modalbody").addClass("modalbody2");
				}
				if(screen.width>1708&&tbodylength>3){
					$("#modalbody").addClass("modalbody2");
				}
					$("#Applychart").show();
				});
				
				
				 /*实习申请表里面的操作*/
					//实习基地来源改变，对应的实习基地改变
				$(document).on("change","#baseFrom",function(e){
					var type=e.target.value;
					var selectObj=$(this).parent().next().children("select");
					var aObj=$(this).parent().next().children("a");
					selectObj.hide();
					selectObj.val("");
					selectObj.find("option:gt(0)").remove();
					aObj.css("display","none");
					var that=$(this);
					if(type!=''){	
						$.ajax({
							type : 'POST',
							dataType : 'json',	
							data:{"typename":type},
							url : 'getBasenameOneOfType.do',  
							async : false,
							cache : false,
							error : function(request) {
								bootbox.alert({
									message : "请求异常",
									size : 'small'
								});
							},
							success : function(data){
								if(data.length==0){
									aObj.css("display","block");
									$(".select2").css("display","none");
									return;
								}else{
								that.parent().next().children("select").select2({
										  data: data,
										  placeholder:'请选择',
										  allowClear:false,
										  width:100,
										  dropdownAutoWidth:true
									});
									
								selectObj.show();
								
								
								}
							}
						});
					}
				});	
	
				//选择学院并且上传学院的名称，放回改学院老师的数据（包含老师名称和老师员工编号，针对增加功能的带队老师				
				$(document).on("change","#selectCollege3",function(){                   
                    var college=$(this).val();                                    	
    				$("#selectTname3 option:gt(0)").remove();    				
					$.ajax({
						url : 'getCollege_Teacher.do',
						type : 'post',
						dataType : 'json',
						data : {
							"college" : college,								
						},
					success : function(data){						
						for(var i=0;i<data.length;i++){//获取老师名字下拉框
							$("#teacherNmaeID3").after(
							"<option class='rest' value="+data[i].name+">"+ data[i].name + "</option>"
							);
						}
					}
				});
				});
				
				/*增加按钮的带队老师js控制--start*/				
				$(document).on("focus","#intname",function(){
					$("#selectCollege3").val("");
					$("#selectTname3 option:gt(0)").remove();
					$("#selectTname3").val("");
					$("#showTeaName").val($("#intname").val());
					$("#TeaName").modal('show');
					
				});				
					
				$(document).on("change","#selectTname3",function(e){//将实验员姓名显示在界面中，并且在选择的同时根据实验员的职工编号判断有没有选择同一人
					var teststring=$("#showTeaName").val().trim();
					var testvalue=teststring.split("/");
					var str_tname;
					testvalue.push(e.target.value);
					teststring=testvalue.join("/");
					if(teststring.substring(0,1)=='/'){
						str_tname=teststring.substring(1);
					}else{
						str_tname=teststring;
					}
					$("#showTeaName").val(str_tname);				
				});
					
				$(document).on("click","#finished_increase1",function(){//点击确定之后讲实验员姓名在表格中显示出来
					var tester=$("#showTeaName").val();
					var format=/^([\u4e00-\u9fa5|\w]+[//]?)+$/;
					if(tester===""){
						bootbox.alert({
							message : "带队教师不能为空",
							size : 'small'
						});
						return;
					}else if(!format.test(tester)){
						bootbox.alert({
							message : "名字之间用'/'隔开",
							size : 'small'
						});
						return;
					}					
					$("#intname").val(tester);
				});				
				/*增加按钮的带队老师js控制--end*/
				
				
				//选择学院并且上传学院的名称，放回改学院老师的数据（包含老师名称和老师员工编号，针对增加功能的教师职工号				
				$(document).on("change","#selectCollege4",function(){                 
                    var college=$(this).val(); 
    				$("#selectTname4 option:gt(0)").remove();
    					
					$.ajax({
						url : 'getCollege_Teacher.do',
						type : 'post',
						dataType : 'json',
						data : {
							"college" : college,								
						},
					success : function(data){						
						for(var i=0;i<data.length;i++){//获取老师名字下拉框
							$("#teacherNmaeID4").after(
							"<option class='rest' value="+data[i].id+">"+ data[i].name + "</option>"
							);
						}
					}
				});
				});
				
				/*增加按钮的教师职工号js控制--start*/		
				var faculty_college="";
				var faculty_name="";
				$(document).on("focus","#intid",function(){
					if($("#intid").val()==""){
						$("#selectCollege4").val("");
						$("#selectTname4 option:gt(0)").remove();
						$("#selectTname4").val("");
					}else{		
						if($("#selectCollege4").val()==""){
							$("#selectTname4 option:gt(0)").remove();    					
							$.ajax({
								url : 'getCollege_Teacher.do',
								type : 'post',
								dataType : 'json',
								async: false,
								data : {
									"college" : faculty_college,								
								},
							success : function(data){						
								for(var i=0;i<data.length;i++){//获取老师名字下拉框
									$("#teacherNmaeID4").after(
									"<option class='rest' value="+data[i].id+">"+ data[i].name + "</option>"
									);
								}
							}
						});
						}						
						$("#selectCollege4").val(faculty_college);
						$("#selectTname4").val(faculty_name);
					}	
					
					$("#TeaId").modal('show');					
				});				
					
				$(document).on("click","#finished_increase2",function(){//点击确定之后讲教师职工号在表格中显示出来
					var nameId=$("#selectTname4").val();
					if(nameId===""){
						bootbox.alert({
							message : "教师职工号不能为空",
							size : 'small'
						});
						return;
					}else{
						faculty_college=$("#selectCollege4").val();
						faculty_name=$("#selectTname4").val();
					}					
					$("#intid").val(nameId);
					
				});				
				/*增加按钮的教师职工号js控制--end*/
				
				//选择学院并且上传学院的名称，放回改学院老师的数据（包含老师名称和老师员工编号，针对修改功能的带队老师				
				$(document).on("change","#selectCollege_5",function(){                   
                    var college=$(this).val();                                    	
    				$("#selectTname_5 option:gt(0)").remove();    				
					$.ajax({
						url : 'getCollege_Teacher.do',
						type : 'post',
						dataType : 'json',
						data : {
							"college" : college,								
						},
					success : function(data){						
						for(var i=0;i<data.length;i++){//获取老师名字下拉框
							$("#teacherNmaeID_5").after(
							"<option class='rest' value="+data[i].name+">"+ data[i].name + "</option>"
							);
						}
					}
				});
				});
				
				/*修改按钮的带队老师js控制--start*/				
				$(document).on("focus","#tname_0",function(){
					$("#selectCollege_5").val("");
					$("#selectTname_5 option:gt(0)").remove();
					$("#selectTname_5").val("");
					$("#showTeaName_5").val($("#tname_0").val());
					$("#TeaName_update").modal('show');
					
				});				
					
				$(document).on("change","#selectTname_5",function(e){//将实验员姓名显示在界面中
					var teststring=$("#showTeaName_5").val().trim();
					var testvalue=teststring.split("/");
					var str_tname;
					testvalue.push(e.target.value);
					teststring=testvalue.join("/");
					if(teststring.substring(0,1)=='/'){
						str_tname=teststring.substring(1);
					}else{
						str_tname=teststring;
					}
					$("#showTeaName_5").val(str_tname);				
				});
					
				$(document).on("click","#finished_update1",function(){//点击确定之后将带队教师在表格中显示出来
					var tester=$("#showTeaName_5").val();					
					var format=/^([\u4e00-\u9fa5|\w]+[//]?)+$/;
					if(tester===""){
						bootbox.alert({
							message : "带队教师不能为空",
							size : 'small'
						});
						return;
					}else if(!format.test(tester)){
						bootbox.alert({
							message : "名字之间用'/'隔开",
							size : 'small'
						});
						return;
					}					
					$("#tname_0").val(tester);
				});				
				/*修改按钮的带队老师js控制--end*/
				
				//选择学院并且上传学院的名称，放回改学院老师的数据（包含老师名称和老师员工编号，针对修改功能的教师职工号				
				$(document).on("change","#selectCollege_6",function(){                 
                    var college=$(this).val(); 
    				$("#selectTname_6 option:gt(0)").remove();
    					
					$.ajax({
						url : 'getCollege_Teacher.do',
						type : 'post',
						dataType : 'json',
						data : {
							"college" : college,								
						},
					success : function(data){						
						for(var i=0;i<data.length;i++){//获取老师名字下拉框
							$("#teacherNmaeID_6").after(
							"<option class='rest' value="+data[i].id+">"+ data[i].name + "</option>"
							);
						}
					}
				});
				});
				
				/*修改按钮的教师职工号js控制--start*/					
				$(document).on("focus","#tid_0",function(){
					
						$("#selectCollege_6").val("");
						$("#selectTname_6 option:gt(0)").remove();
						$("#selectTname_6").val("");					
					$("#TeaId_update").modal('show');					
				});				
					
				$(document).on("click","#finished_update2",function(){//点击确定之后讲教师职工号在表格中显示出来
					var nameId=$("#selectTname_6").val();
					if(nameId===""){
						bootbox.alert({
							message : "教师职工号不能为空",
							size : 'small'
						});
						return;
					}				
					$("#tid_0").val(nameId);
					
				});				
				/*修改按钮的教师职工号js控制--end*/
				
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
						for(var i=0;i<data.length;i++){//获取老师名字下拉框
							$("#teacherNmaeID").after(
							"<option class='rest' value="+data[i].name+">"+ data[i].name + "</option>"
							);
						}
					}
				});
				});
				
				$(document).on("change","#majorCollege",function(){
					var college=$("#majorCollege").val();
					$("#majorName option:gt(0)").remove();
					$.ajax({
						url : 'getCollege_Major.do',
						type : 'post',
						dataType : 'json',
						data : {
							"college" : college,								
						},
					success : function(data){	
						for(var i=0;i<data.length;i++){//获取老师名字下拉框
							$("#majorNmaeID").after(
							"<option class='rest' value="+data[i].major+">"+ data[i].major + "</option>"
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
					$("#selectCollege2").val("");
					$("#leadteachername").val(teacherString[selectNum]);
					$("#selectTname2 option:gt(0)").remove();
					$("#selectTname2").val("");
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
							$(this).find('#Tea').val("老师："+tester);
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
					$("#selectTname option:gt(0)").remove();
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
							$(this).find('#tes').val("实验员："+tester);
						}
					});
					var str=value.join(',');
					$("#testername").val(str);
				});
				
				var major_num;
				$(document).on("click",".choice3",function(){//点击选择弹出面向专业的弹出框
					
					major_num=$(this).closest("tbody").find(".mark").html()-1;
					$("#Selectmajor").modal('show');
					$("#showmajor").val(majorString[major_num]);
					$("#majorName").val("");
					$("#majorCollege").val("");
					$("#majorName option:gt(0)").remove();
				});

				$(document).on("change","#majorName",function(e){//将专业显示在界面中
					var teststring=$("#showmajor").val();
					var testvalue=teststring.split(" ");
					testvalue.push(e.target.value);
					teststring=testvalue.join(" ");
					$("#showmajor").val(teststring);
					
					
				});
					
				$(document).on("click","#finished3",function(){//点击确定之后将专业在表格中显示出来
					var showmajor=$("#showmajor").val();
					if(showmajor===""){
						bootbox.alert({
							message : "面向专业不能为空",
							size : 'small'
						});
						return;
					}else{
						majorString[major_num]=showmajor;
					}
					$(".tbodyID").each(function(){
						var tea=$(this).find('.mark').html()-1;
						if(tea===major_num){
							$(this).find('#facemajoy').val("面向专业："+showmajor);
						}
					});
					var str=majorString.join(',');
					$("#major").val(str);
				});
				//实习表中添加一条记录
								
			$(document).on("click","#addTbody",function(){//添加一条空表的记录
				var tbodylength=$("#table tbody").size();
				if(screen.width<=1536&&tbodylength>2){
					$("#modalbody").addClass("modalbody");
				}
				if(screen.width<=1708&&screen.width>1536&&tbodylength>3){
					$("#modalbody").addClass("modalbody2");
				}
				if(screen.width>1708&&tbodylength>3){
					$("#modalbody").addClass("modalbody3");
				}
				$("#table tbody:last-child").after(tbodyStyle);
				var tbNum=$("#table").children('tbody').length;
				$("#table tbody:last-child").find(".mark").html(tbNum-2);
				
				var fromweek=$("#fromweek").val();
				var data_week_0=fromweek.split(',');
				var data_week=[];
				var data_week_1;				
				var data_week_3;
				for(var i in data_week_0){
					data_week_1=data_week_0[i].split('-');					
					var k=0;
					data_week_3=data_week_1[0];
					if(data_week_1[0]==data_week_1[data_week_1.length-1]){
						data_week.push(data_week_1[0]);
					}else{
					   while(data_week_3<data_week_1[data_week_1.length-1]){						
						data_week_3=Number(data_week_1[0])+(k++);
						data_week.push(data_week_3);
					}
					}
				}
				$.unique(data_week.sort(sortNumber));
				for(var week in data_week){
					$("#table tbody:last-child").find("#weekend_option").after(
							"<option value="+data_week[week]+">"+ data_week[week] + "</option>"
							);
				}	
				
				//获得班级的数组
				var composition=$("#class").val();
				var data_composition=composition.split(',');
				$.unique(data_composition.sort(sortNumber));
				for(var composition_0 in data_composition){
					$("#table tbody:last-child").find("#class_one_option").after(
							"<option value="+data_composition[composition_0]+">"+ data_composition[composition_0] + "</option>"
							);
				}	
				
				
				
				$.ajax({
					type : 'POST',
					dataType : 'json',		
					url : 'getPlanAim.do',  
					async : false,
					cache : false,
					data:{
						"mid":obj[Oneindex].tid
					},
				success : function(data){//
					
					for(i=0;i<data[0].length;i++){//获取实习目的下拉框
						$("#table tbody:last-child").find("#aimID").after(
						"<option class='rest' id="+data[0][i].id+" value="+data[0][i].aim+" data-placement='top' data-toggle='tooltip' title='"+data[0][i].aim+"'>"+ (data[0][i].aim.length>20?data[0][i].aim.substring(0,20)+"...":data[0][i].aim )+ "</option>"

						);
					}
					for(var i=0;i<data[1].length;i++){//获取基地类型
						$("#table tbody:last-child").find("#baseForm").after(
						"<option class='rest' value="+data[1][i].name+">"+ data[1][i].name+ "</option>"
						);
					}
					
				}
			});
			});
				
			$(document).on("click",".deleteID",function(){//弹出框里面的记录删除
				var judget=$(this).attr("id");
				var rowNum=$(this).closest("tbody").find(".mark").html()-1;
				var tbody=$(this).closest("tbody");
				bootbox.confirm({
					message: "是否删除？",
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
						tbody.remove();
						if(result){			
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
						}
					}
				});		

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
				var major="";
				var class_one="";
				var reg=/^[1-9][0-9]*$/;
				$(".tbodyID").each(function(){
					y++;
					week=$(this).find("#weekend").val();
					if(week===""){
						return false;
					}
					
					class_one=$(this).find("#class_one").val();
					if(class_one===""){
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
					if(sSite===""){
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
					
					major=$(this).find("#facemajoy").val();
					if(major===""){
						return false;
					}
					
					Tea=$(this).find("#Tea").val();
					if(Tea===""){
						return false;
					}
					
					tes=$(this).find("#tes").val();
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
				
				if(class_one===""){
					bootbox.alert({
						message : "请填写第"+y+"条记录的班级",
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
				if(major===""){
					bootbox.alert({
						message : "请选择第"+y+"条记录的面向专业",
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
									var c=$(this).find(".mark").html()-1;
									str=str+"'"+majorString[c]+"','"+teacherString[c]+"'"+",'"+value[c]+"'";
									
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
										if(x===12||x===13){
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
										bootbox.alert({
											message : "保存失败",
											size : 'small'
										});
									},
									data:{
										"courseID":obj[Oneindex].id,							
										"str":str,
									},
									success : function(msg) {							
										bootbox.alert({
											message : "保存成功",
											size : 'small'
										});
										//$("#Applychart").hide();
										
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
										var termYear = $("#termYear").val();
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
																				semeUp(termYear,1);
																				$('input:checked').prop("checked",false);
																				table.draw(false);
																			}
																		});
															}
														}
													});
										}
									});
					
					var weekNumber=0;
					$("#inweek").click(function(){
						if(weekNumber==0){
							for(var x=0;x<12;x++){
								
								$("#starNumID").after("<option value='"+(12-x)+"'>"+(12-x)+"</option>");
								$("#endNumID").after("<option value='"+(12-x)+"'>"+(12-x)+"</option>");
								weekNumber++;
							}
						}
						
						$("#select").show();
						
					});
					var starToweek="";
					var endToweek="";
					var weekStr="";
					var cerrentStr="";
					$("#starNum").change(function(){
						var certenNum=$(this).val();
						
						if(endToweek!=""&&certenNum-endToweek>0){
							
								bootbox.alert({
									message : "开始周次不能大于等于结束周次",
									size : 'small'
								});
								$("#starNum").val(starToweek);
								return;
							
						}
						starToweek=$(this).val();
						cerrentStr=starToweek+'-'+endToweek;
						
						
					});
					$("#endNum").focus(function(){
						if(starToweek===""){
							bootbox.alert({
								message : "请先选择开始周次",
								size : 'small'
							});
							
							return;
						}
					});
					$("#endNum").change(function(){
						var certenNum=$(this).val();
						
						if(starToweek-certenNum>0){
							bootbox.alert({
								message : "结束周次不能小于开始周次",
								size : 'small'
							});
							if(endToweek!=""){
								$("#endNum").val(endToweek);
							}else{
								$("#endNum").val("0");
							}
							
							return;
						}
						endToweek=$(this).val();
						cerrentStr=starToweek+'-'+endToweek;
					});
					var addNum=0;
					$("#addWeenNum").click(function(){
						if($("#endNum").val()=="0"||$("#endNum").val()==""){
							bootbox.alert({
								message : "请将周次填写完整再添加",
								size : 'small'
							});
							return;
						}
						
						if(addNum==0){
							weekStr=weekStr+cerrentStr;
						}else{
							weekStr=weekStr+','+cerrentStr;
						}
						
						$("#inweek").val(weekStr);
						$("#endNum").val('0');
						$("#starNum").val('0');
						starToweek="";
						endToweek="";
						cerrentStr="";
						addNum=1;
						/*
						weekStr=weekStr+',';					
						*/
					});
					$("#closeNum").click(function(){
						$("#select").hide();
						$("#endNum").val('0');
						$("#starNum").val('0');
						
					});
					$("#weendelate").click(function(){
						$("#inweek").val("");
						$("#endNum").val('0');
						$("#starNum").val('0');
						addNum=0
						cerrentStr="";
						weekStr="";
						starToweek="";
						endToweek="";
					});
					
					//选择开始和结束时间
					var timeArray=0;
					$(document).on("focus","#startweek",function(){
						timeArray=$(this).closest("tbody").find(".mark").html()-1;
						var o=0,p=0;						
						$(".startweek").each(function(){
							if(o===timeArray){
								$("#Stime").val($(this).val());
								return false;
							}
							o++;
						});
						$(".endweek").each(function(){
							if(p===timeArray){
								$("#Etime").val($(this).val());
								return false;
							}
							p++;
						});
						
						$("#time").modal('show');
					});
					$(document).on("focus","#endweek",function(){
						timeArray=$(this).closest("tbody").find(".mark").html()-1;
						var o=0,p=0;
						$(".startweek").each(function(){
							if(o===timeArray){
								$("#Stime").val($(this).val());
								return false;
							}
							o++;
						});
						$(".endweek").each(function(){
							if(p===timeArray){
								$("#Etime").val($(this).val());
								return false;
							}
							p++;
						});
						$("#time").modal('show');
					});
						
					$(document).on("click","#finishTime",function(){
						var o=0,p=0;
						$(".startweek").each(function(){
							if(o===timeArray){
								$(this).val($("#Stime").val());
								return false;
							}
							o++;
						});
						$(".endweek").each(function(){
							if(p===timeArray){
								$(this).val($("#Etime").val());
								return false;
							}
							p++;
						});
						$("#time").modal('hide');
					});	
									
					
								



$(document).on("click",".closeModal_increase",function(){		
	$("#addPraItem").hide();
});

$(document).on("click",".closeModal_update",function(){		
	$("#updatePlanItem").hide();
});

				});
function sortNumber(a,b)
{
	return a<b?a:b;
}

//学年学期的选择控制
function chooseSeme(selector){
	
	 var year=new Date().getFullYear();
	 var sem1;
	 var sem2;
	 var sem;
	 var sel='#'+selector;
	 for(var i=20;i>-20;i--){
		 sem1=year+i;
		 sem2=year+i+1;
		 sem=sem1+'-'+sem2;
		 $(sel).after("<option value='"+sem+"' class='chooseSeme'>"+sem+"</option>");
	 }
}

function semeUp(sel,flag){
	
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : 'getPlanMaintainInfo.do',
		async : false,
		cache : false,
		error : function(request) {
			bootbox.alert({
				message : "请求异常",
				size : 'small'
			});
		},
		success : function(data) {  
		 if(flag==0){
			 table.draw(false);
		 }
		 if(flag==1){					
			for (var i= 0; i < data.semester.length; i++) {
				if(data.semester[i]==sel){
					return;
				}
			}
			 $("#termYear").val('');
	          $("#semester").val('');
	          $(".semeUp1").remove();
			 for (var i = 0; i < data.semester.length; i++) {
					$("#termYearID").after(
							"<option value=" + data.semester[i]
									+ " class='semeUp1'>" + data.semester[i]
									+ "</option>");

				}
				$("#termYear").change(); 
				$("#semester").change();
		}	 
		 
		}

	});
	
}

function getCollege(){
var daoYear=$("#daoYear").val();
var daosemster=$("#daosemster").val();
$.ajax({
		type : 'POST',
		dataType : 'json',								
		url : 'getReadyco.do',
		data:{"year":daoYear,"semester":daosemster},
		async : false,
		cache : false,
		error : function(request) {
			bootbox.alert({
				message : "请求异常",
				size : 'small'
			});
		},
		success : function(data) {
			$(".removeCollege").remove();							
			 for ( var i=0;i<data.college.length;i++) {
			 $("#daodept").after(
					"<option value=" + data.college[i]
							+ " class='removeCollege'>" + data.college[i]
							+ "</option>");	
			 }
			}							

	});  
}
	

