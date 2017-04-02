//下面是前台页面用测试的js
// JavaScript Document
var obj = [];
var teachername = "";
var testername = "";
var data_echart = [];
var option;
var myChart;
$(document)
		.ready(
				function() {

					// 基于准备好的dom，初始化echarts实例
					myChart = echarts.init(document
							.getElementById('mainEchart'));

					var table = $("#statistictable")
							.DataTable(
									{
										"aLengthMenu" : [ 5, 10, 20, 30 ], // 动态指定分页后每页显示的记录数。
										"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
										"bSort" : true,
										"serverSide" : true,
										"bFilter" : true,
										"ordering" : true,
										"dom" : 'tipr<"bottom"l>',
										"iDisplayLength" : 5,
										"ajax" : {
											"url" : 'statisticData.do',
											"type" : "POST",
										},
										"aoColumns" : [
												{
													"mData" : "semester",// 学期学年
													"orderable" : false,
													"sDefaultContent" : "",
												// "sWidth" : "2%",
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
													"mData" : "mid",// 专业编号
													"orderable" : false,
													"visible" : false,
													"sDefaultContent" : "",
												},
												{
													"mData" : "major_oriented",// 面向专业
													"orderable" : false,
													"visible" : false,
													"sDefaultContent" : "",
												},
												{
													"mData" : "checkMethod",// 考核
													"orderable" : false,
													"sDefaultContent" : "",
													"render" : function(data,
															type, row) {
														obj.push(row);
														return '<span id='
																+ (obj.length - 1)
																+ '>' + data
																+ '</span>';
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

					/*--Echart-start--*/
					// 指定图表的配置项和数据
					// 给data_echart重新赋值
					$.ajax({
						url : 'getstatisticCount.do',
						type : 'post',
						async : false,
						dataType : 'json',
						success : function(data) {
						
							option = {
								title : {
									text : '基地实习申报数据统计'
																
								},
								tooltip : {},
								legend : {
									data : [ '数目' ]
								},
								toolbox : {
									show : true,// 是否显示工具栏组件
									showTitle : true,// 是否在鼠标 hover 的时候显示每个工具
														// icon
									// 的标题
									feature : {
										magicType : {
											type : [ 'line', 'bar' ]
										},// 切换类型
										saveAsImage : {
											type : 'png'
										}
									// 存储为图片
									}
								},
								
								xAxis : {
									name : '基地类型',								
									nameTextStyle:{
										 color: 'black',
										 fontSize:15,
										 fontWeight:'bold'
									},

									data : [ '校内科教基地', '校外实习基地', '新农院服务基地',
											'创新创业孵化基地', '创新创业就业基地' ]
								// 
								},
								yAxis : {
									name : '基地数量',
									
									nameTextStyle:{
										 color: 'black',
										 fontSize:15,
										 fontWeight:'bold'
									}
								},
								series : [ { // For shadow
									type : 'bar',
									itemStyle : {
										normal : {
											color : 'rgba(0,0,0,0.05)'
										}
									},
									barGap : '-100%',
									barCategoryGap : '40%',
									data : data.list2,
									animation : false
								}, {
									name : '数目',
									type : 'bar',
									itemStyle : {
										normal : {
											color : '#3b6290'
										},
										emphasis : {
											color : '#a4bccd'
										}
									},
									data : data.list1
								} ]
							};

							// 使用刚指定的配置项和数据显示图表。
							myChart.setOption(option);
						}
					});

					/*--Echart-end--*/

					// 显示实习申请表
					var tbodyStyle = '<tbody class="tbodyID"><tr>'
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
							+ '<td rowspan="3"><sapn class="mark"></span></td>'
							+ '<td><input id="weekend" type="text" class="text-center inputWidth" disabled></td>'
							+ '<td><input id="startweek" name="control_date" type="text" disabled></td>'
							+ '<td><input id="endweek" name="control_date" type="text"  disabled></td>'
							+ '<td><input id="content" type="text" class="inputWidth" disabled></td>'
							+ '<td><input disabled id="baseFrom" class="flag"></td>'
							+ '<td><input id="outBase" type="text" class="inputWidth" disabled></td>'
							+ '<td><input id="category" disabled></td>'
							+ '<td><input id="remark" type="text" disabled></td>'

							+ '</tr>'
							+ '<tr>'
							+ '<td>实习形式</td>'
							+ '<td>实习基地联系人/电话</td>'
							+ '<td>目的</td>'
							+ '<td>实习经费预算</td>'
							+ '<td colspan="2">指导老师/实验员</td>'
							+ '<td colspan="2">指导老师/实验员</td>'
							+ '</tr>'
							+ '<tr>'
							+ '<td><input name="" id="practiceClass" disabled></td>'
							+ '<td><input id="phone" type="text" disabled></td>'
							+ '<td><input type="text" id="aim" disabled></td>'
							+ '<td><input id="budget" type="text" class="inputWidth" disabled></td>'
							+ '<td colspan="2"><input type="text" disabled id="Tea"></td>'
							+ '<td colspan="2"><input type="text" disabled id="tes"></td>'
							+ '</tr></tbody>';

					$("#statistictable tbody").on(
							"click",
							"tr",
							function() {
								Oneindex = $(this).find("span").attr("id");
								$("#division").val(obj[Oneindex].college);
								$("#classname").val(obj[Oneindex].coursename);
								$("#major").val(obj[Oneindex].major_oriented);
								$("#class").val(obj[Oneindex].composition);
								$("#grade").val(obj[Oneindex].credit);
								$("#number").val(obj[Oneindex].count);
								$("#weeks").val(obj[Oneindex].weekClassify);
								$("#leaderTeacher").val(obj[Oneindex].tname);

								$.ajax({
									url : 'getplandata.do',
									type : "POST",
									dataType : "json",
									data : {
										"mid" : obj[Oneindex].id
									},
									success : function(data) {
										var teachername = "";
										var testername = "";
										for (var i = 0; i < data.length; i++) {
											$("#table tbody:last-child").after(
													tbodyStyle);

											$("#table tbody:last-child").find(
													".mark").html(i + 1);
											$("#table tbody:last-child").find(
													"#weekend").val(
													data[i].week);
											$("#table tbody:last-child").find(
													"#startweek").val(
													data[i].starttime);
											$("#table tbody:last-child").find(
													"#endweek").val(
													data[i].endtime);
											$("#table tbody:last-child").find(
													"#content").val(
													data[i].content);
											$("#table tbody:last-child").find(
													"#baseFrom").val(
													data[i].source);
											$("#table tbody:last-child").find(
													"#category").val(
													data[i].category);
											$("#table tbody:last-child").find(
													"#remark").val(
													data[i].remark);
											$("#table tbody:last-child").find(
													"#practiceClass").val(
													data[i].form);

											$("#table tbody:last-child").find(
													"#phone").val(
													data[i].telephone);
											$("#table tbody:last-child").find(
													"#aim").val(data[i].aim);
											$("#table tbody:last-child").find(
													"#budget").val(
													data[i].expense);
											$("#table tbody:last-child").find(
													"#outBase").val(
													data[i].site);
											$("#table tbody:last-child").find(
													"#Tea").val(
													data[i].guideTeacher);
											$("#table tbody:last-child").find(
													"#tes").val(
													data[i].assistant);
											if (i !== data.length - 1) {
												teachername = teachername
														+ data[i].guideTeacher
														+ ",";
												testername = testername
														+ data[i].assistant
														+ ",";
											} else {
												teachername = teachername
														+ data[i].guideTeacher;
												testername = testername
														+ data[i].assistant;
											}
										}
										$("#testername").val(testername);
										$("#adviser").val(teachername);
									}
								});

								$("#Applychart").show();
							});

					// 获取两个筛选框里面基地类别
					$.ajax({
						url : 'statisticgetDept.do',
						type : "POST",
						dataType : 'json',
						success : function(data) {
							for (var i = 0; i < data[0].length; i++) {// 获取第一个筛选的基地类别
								$("#baseCategoryID")
										.after(
												"<option class='rest' value="
														+ data[0][i].name + ">"
														+ data[0][i].name
														+ "</option>");
								$("#baseCategory2ID")
										.after(
												// 获取第二个筛选的基地类别
												"<option class='rest' value="
														+ data[0][i].name + ">"
														+ data[0][i].name
														+ "</option>");
							}

							for (var m = 0; m < data[1].length; m++) {// 获取第一个筛选的学院
								$("#collegeID").after(
										"<option class='rest' value="
												+ data[1][m].college + ">"
												+ data[1][m].college
												+ "</option>");
								$("#college2ID").after(
										// 获取第二个筛选的学院
										"<option class='rest' value="
												+ data[1][m].college + ">"
												+ data[1][m].college
												+ "</option>");
							}
							/*
							 * for(var n=0;n<data[3].length;n++){//获取第二个筛选的学院
							 * $("#college2ID").after( "<option class='rest'
							 * value="+data[3][n].base+">"+ data[3][n].base + "</option>" ); }
							 */
							/*
							 * for(var y=0;y<data[4].length;y++){//获取第二个筛选的学院
							 * $("#teacherNameID").after( "<option class='rest'
							 * value="+data[4][y].tname+">"+ data[4][y].tname + "</option>" ); }
							 */
						}

					});
					// 当基地类别改变时，获取基地名字（基地和名字的联动）
					$(document).on(
							"change",
							"#baseCategory",
							function() {// 第一个筛选的联动
								$("#baseName option:gt(0)").remove();
								var baseCategory = $("#baseCategory").val();
								$.ajax({
									url : 'statisticgetName.do',
									type : "POST",
									dataType : 'json',
									data : {
										"baseCategory" : baseCategory
									},
									success : function(data) {
										for (var i = 0; i < data.length; i++) {// 获取基地名字
											$("#baseNameID").after(
													"<option class='rest' value="
															+ data[i].site
															+ ">"
															+ data[i].site
															+ "</option>");

										}
									}

								});
							});
					// 筛选框的弹出
					$("#screen").on("click", function() {
						$('.hide_ul').toggle();
						$('.hide_ul select').val("");
					});
					$("#screen2").on("click", function() {
						$('.hide_ul2').toggle();
						$('.hide_ul2 select').val("");
						$("#teacherName").val("全部");
					});
					$(document).on(
							"change",
							"#baseCategory2",
							function() {// 第二个筛选的联动
								$("#baseName2 option:gt(0)").remove();
								var baseCategory2 = $("#baseCategory2").val();
								$.ajax({
									url : 'statisticgetName2.do',
									type : "POST",
									dataType : 'json',
									data : {
										"baseCategory2" : baseCategory2
									},
									success : function(data) {
										for (var i = 0; i < data.length; i++) {// 获取基地名字
											$("#baseName2ID").after(
													"<option class='rest' value="
															+ data[i].site
															+ ">"
															+ data[i].site
															+ "</option>");
										}
									}

								});
							});
					$(document).on(
							"change",
							"#college",
							function() {
								$("#major option:gt(0)").remove();
								var college = $("#college").val();
								$.ajax({
									url : 'statisticgetmajor.do',
									type : "POST",
									dataType : 'json',
									data : {
										"college" : college
									},
									success : function(data) {
										for (var i = 0; i < data.length; i++) {// 获取专业（第一个）
											$("#majorID").after(
													"<option class='rest' value="
															+ data[i].major
															+ ">"
															+ data[i].major
															+ "</option>");
										}
									}

								});
							});
					$(document).on(
							"change",
							"#college2",
							function() {
								$("#major2 option:gt(0)").remove();
								var college2 = $("#college2").val();
								$.ajax({
									url : 'statisticgetmajor2.do',
									type : "POST",
									dataType : 'json',
									data : {
										"college2" : college2
									},
									success : function(data) {
										for (var i = 0; i < data.length; i++) {// 获取专业（第二个）
											$("#major2ID").after(
													"<option class='rest' value="
															+ data[i].major
															+ ">"
															+ data[i].major
															+ "</option>");
										}
									}

								});
							});
					// 导出功能
					$("#export").click(function() {
						$("#exportForm").submit();
					});
					/* 生成年级开始 */
					var myDate = new Date();
					var dateYear = myDate.getFullYear();
					var dateMonth = myDate.getMonth();
					if (dateMonth > 7) {
						for (var y = 0; y < 4; y++) {
							$("#gradeClassId").after(
									"<option value=" + (dateYear - y - 2000)
											+ ">" + (dateYear - y - 2000)
											+ "级</option>");
							$("#gradeClass2Id").after(
									"<option value=" + (dateYear - y - 2000)
											+ ">" + (dateYear - y - 2000)
											+ "级</option>");
						}
					} else {
						for (var y2 = 1; y2 <= 4; y2++) {
							$("#gradeClassId").after(
									"<option value=" + (dateYear - y2 - 2000)
											+ ">" + (dateYear - y2 - 2000)
											+ "级</option>");
							$("#gradeClass2Id").after(
									"<option value=" + (dateYear - y2 - 2000)
											+ ">" + (dateYear - y2 - 2000)
											+ "级</option>");
						}
					}
					/* 生成年级结束 */

					for (var classNum = 0; classNum < 15; classNum++) {// 生成班级
						$("#classNameID").after(
								"<option value=" + (15 - classNum) + ">"
										+ (15 - classNum) + "</option>");
						$("#className2ID").after(
								"<option value=" + (15 - classNum) + ">"
										+ (15 - classNum) + "</option>");
					}
					/* 限制必须选择年级才能选班级开始 */
					$(document).on("change", "#className", function() {
						if ($("#gradeClass").val() === "") {
							$("#className").val("");
							bootbox.alert({
								message : "请先选择年级",
								size : 'small'
							});
						}
					});
					$(document).on("change", "#className2", function() {
						if ($("#gradeClass2").val() === "") {
							$("#className2").val("");
							bootbox.alert({
								message : "请先选择年级",
								size : 'small'
							});
						}
					});
					/* 限制必须选择年级才能选班级结束 */

					// 隐藏实习申请表
					$(document).on("click", "#closemodal", function() {

						$("#Applychart").hide();
						$(".tbodyID").remove();

					});

					var table2 = $("#statistictable2").DataTable({
						// "aLengthMenu" : [ 5, 10, 20, 30 ], //
						// 动态指定分页后每页显示的记录数。
						"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
						"bSort" : true,
						"ordering" : true,
						"bFilter" : true,
						"ordering" : true,
						"dom" : 't',
						// "iDisplayLength": 5,
						"ajax" : {
							"url" : 'BaseUseRatio.do',
							"type" : "POST",
						},
						"aoColumns" : [ {
							"mData" : "typenum",// 基地类型
							"orderable" : false,
							"sDefaultContent" : "",
						// "sWidth" : "2%",
						}, {
							"mData" : "namenum",// 基地名称
							"orderable" : false,
							"sDefaultContent" : "",
						}, {
							"mData" : "collegenum",// 学院
							"orderable" : false,
							"sDefaultContent" : "",
						}, {
							"mData" : "majornum",// 专业
							"orderable" : false,
							"sDefaultContent" : "",
						}, {
							"mData" : "teachernum",// 老师
							"orderable" : true,
							"sDefaultContent" : "",
						}, {
							"mData" : "classnum",// 班级
							"orderable" : false,
							"sDefaultContent" : "",
						}, {
							"mData" : "expensenum",// 开课学院
							"orderable" : true,
							"sDefaultContent" : "",
						}, {
							"mData" : "personnum",// 人数
							"orderable" : true,
							"sDefaultContent" : ""
						},

						],
					/*
					 * "language": { "lengthMenu": "每页 _MENU_ 条记录",
					 * "zeroRecords": "没有找到记录", "info": "第 _PAGE_ 页 ( 总共 _PAGES_
					 * 页 )", "infoEmpty": "无记录", "infoFiltered": "(从 _MAX_
					 * 条记录过滤)", "sSearch": "模糊查询：", "oPaginate": { "sFirst":
					 * "首页", "sPrevious": " 上一页 ", "sNext": " 下一页 ", "sLast": "
					 * 尾页 " } }
					 */
					});

					$("#finish")
							.click(
									function() {// 第一个筛选框点击完成触发的事件
										var baseCategory = $("#baseCategory")
												.val();
										var baseName = $("#baseName").val();
										var gradeClass = $("#gradeClass").val();
										var college = $("#college").val();
										var major = $("#major").val();
										var className = $("#className").val();
										$("#statistictable")
												.DataTable(
														{
															"aLengthMenu" : [
																	5, 10, 20,
																	30 ], // 动态指定分页后每页显示的记录数。
															"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
															"bSort" : true,
															"serverSide" : true,
															"bFilter" : true,
															"bDestroy" : true,
															"processing" : true,
															"ordering" : true,
															"dom" : 'tipr<"bottom"l>',
															"iDisplayLength" : 5,
															"ajax" : {
																"url" : 'statisticDataBrush.do',
																"type" : "POST",
																"data" : {
																	"baseCategory" : baseCategory,
																	"baseName" : baseName,
																	"gradeClass" : gradeClass,
																	"college" : college,
																	"major" : major,
																	"className" : className
																}
															},
															"aoColumns" : [
																	{
																		"mData" : "semester",// 学期学年
																		"orderable" : false,
																		"sDefaultContent" : "",
																	// "sWidth"
																	// : "2%",
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
																		"mData" : "mid",// 专业编号
																		"orderable" : false,
																		"visible" : false,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "major_oriented",// 面向专业
																		"orderable" : false,
																		"visible" : false,
																		"sDefaultContent" : "",
																	},
																	{
																		"mData" : "checkMethod",// 考核
																		"orderable" : false,
																		"sDefaultContent" : "",
																		"render" : function(
																				data,
																				type,
																				row) {
																			obj
																					.push(row);
																			return '<span id='
																					+ (obj.length - 1)
																					+ '>'
																					+ data
																					+ '</span>';
																		}
																	}

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
										$('.hide_ul').toggle();
									});
					$("#selectName").click(function() {
						$('#myModal').modal('show');
						$("#selectTeacher").val("");
						$("#selectCollege").val("");
					});
					$.ajax({// 获取模态框里面的学院下拉列表
						url : 'getCollege.do',
						type : "POST",
						dataType : 'json',
						success : function(data) {
							for (var i = 0; i < data.length; i++) {// 获取学院
								$("#selectCollegeID").after(
										"<option class='rest' value="
												+ data[i].dept + ">"
												+ data[i].dept + "</option>");
							}

						}
					});

					$(document).on(
							"change",
							"#selectCollege",
							function() {
								var college = $("#selectCollege").val();
								$("#selectTeacher option:gt(0)").remove();
								$.ajax({
									url : 'getCollege_Teacher.do',
									type : 'post',
									dataType : 'json',
									data : {
										"college" : college,
									},
									success : function(data) {
										for (var i = 0; i < data.length; i++) {// 获取老师名字下拉框
											$("#selectTeacherID").after(
													"<option class='rest' value="
															+ data[i].name
															+ ">"
															+ data[i].name
															+ "</option>");
										}
									}
								});
							});
					$("#selectTeacher").click(function() {
						if ($("#selectTeacher option").length == 1) {
							$("#selectTeacher").val("");
							bootbox.alert({
								message : "请先选择学院",
								size : 'small'
							});
						}
					});
					$("#checkTeacher").click(function() {
						$("#selectTeacherID").val("");
						var t = $("#selectTeacher").val();
						$("#teacherName").val(t);
						$('#myModal').modal('hide');

					});

					$("#export").click(function() {
						bootbox.confirm({
							message : "确定导出表格中的数据?",
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
									$("#exportStatisticForm").submit();
								}
							}
						});
					});

					$("#finish2").click(function() {
						var baseCategory2 = $("#baseCategory2").val();
						var baseName2 = $("#baseName2").val();
						var gradeClass2 = $("#gradeClass2").val();
						var college2 = $("#college2").val();
						var major2 = $("#major2").val();
						var className2 = $("#className2").val();
						var teacherName = $("#teacherName").val();
						$("#statistictable2").DataTable({
							// "aLengthMenu" : [ 5, 10, 20, 30 ], //
							// 动态指定分页后每页显示的记录数。
							"lengthChange" : true, // 是否启用改变每页显示多少条数据的控件
							"bSort" : true,
							"ordering" : true,
							"bFilter" : true,
							"bDestroy" : true,
							"processing" : true,
							"ordering" : true,
							"dom" : 't',
							// "iDisplayLength": 5,
							"ajax" : {
								"url" : 'BaseUseRatioBrush.do',
								"type" : "POST",
								"data" : {
									"baseCategory2" : baseCategory2,
									"baseName2" : baseName2,
									"gradeClass2" : gradeClass2,
									"college2" : college2,
									"major2" : major2,
									"className2" : className2,
									"teacherName" : teacherName

								}
							},
							"aoColumns" : [ {
								"mData" : "typenum",// 基地类型
								"orderable" : false,
								"sDefaultContent" : "",
							// "sWidth" : "2%",
							}, {
								"mData" : "namenum",// 基地名称
								"orderable" : false,
								"sDefaultContent" : "",
							}, {
								"mData" : "collegenum",// 学院
								"orderable" : false,
								"sDefaultContent" : "",
							}, {
								"mData" : "majornum",// 专业
								"orderable" : false,
								"sDefaultContent" : "",
							}, {
								"mData" : "teachernum",// 老师
								"orderable" : true,
								"sDefaultContent" : "",
							}, {
								"mData" : "classnum",// 班级
								"orderable" : false,
								"sDefaultContent" : "",
							}, {
								"mData" : "expensenum",// 开课学院
								"orderable" : true,
								"sDefaultContent" : "",
							}, {
								"mData" : "personnum",// 人数
								"orderable" : true,
								"sDefaultContent" : ""
							},

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
						$('.hide_ul2').toggle();

					});

				});
