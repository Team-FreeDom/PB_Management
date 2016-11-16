<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的租赁</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />

<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css"	href="../css/font-awesome.min.css">
<link type="text/css" rel="stylesheet"	href="../css/dataTables.tableTools.css" />
<link rel="stylesheet" type="text/css"	href="../css/jquery.dataTables.css">
<link rel="stylesheet" href="../css/myrent.css">
<!-- <script type="text/javascript" language="javascript" src="../js/jquery.js"></script> -->
  <script src="../js/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"/>
<script type="text/javascript" src="../js/main.js"></script>
<script>
	$(document).ready(function() {

		$('#table1').dataTable({
			"processing" : true,
			"aLengthMenu" : [ 2, 4, 6, 8, 10 ],
			// "serverSide": true,
			"ajax" : {
				"url" : "mySelfandStateApply1.do",
				"type" : "POST"
			},
			"columns" : [ {
				"data" : "startTime"
			}, {
				"data" : "tenancy"
			}, {
				"data" : "bname"
			}, {
				"data" : "lid"
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
	});

	$(document).ready(function() {
		$('#table2').DataTable({
			"processing" : true,
			"aLengthMenu" : [ 2, 4, 6, 8, 10 ],
			// "serverSide": true,
			"ajax" : {
				"url" : "mySelfandStateApply2.do",
				"type" : "POST"
			},
			"columns" : [ {
				"data" : "startTime"
			}, {
				"data" : "tenancy"
			}, {
				"data" : "bname"
			}, {
				"data" : "lid"
			}, {
				"data" : "lid"
			}, {
				"data" : "desc"
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
	});

	$(document)
			.ready(
					function() {
						$('#table3')
								.DataTable(
										{
											"processing" : true,
											"aLengthMenu" : [ 2, 4, 6, 8, 10 ],
											// "serverSide": true,
											"ajax" : {
												"url" : "selfApply.do",
												"type" : "POST"
											},

											"aoColumns" : [
													{ //aoColumns设置列时，不可以任意指定列，必须列出所有列。
														"mData" : "la_id",
														"orderable" : true, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "2%"
													},
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
														"mData" : "desc",
														"orderable" : false, // 禁用排序
														"sDefaultContent" : "",
														"sWidth" : "8%"
													},
													{
														"mData" : "la_id",
														"orderable" : false, // 禁用排序
														"sDefaultContent" : '',
														"sWidth" : "5%",
														"render" : function(
																data, type,
																full, meta) { //render改变该列样式,4个参数，其中参数数量是可变的。
															return data = '<span data-id='+data+' id="scanOne" class=" glyphicon glyphicon-search"></span>';

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
										}

								);
					});

	/**
	 * 土地租赁详情查看
	 */

	$(document).delegate('#scanOne', 'click', function() {
		var la_id = $(this).data("id");

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

					$("#bname").val(data[i].bname);
					$("#lname").val(data[i].lname);
					$("#lid").val(data[i].lid);
					$("#mycollege").val(data[i].college);					
					$("#major_oriented").val(data[i].major_oriented);
					$("#landArea").val(data[i].landArea);
					$("#buildingArea").val(data[i].buildingArea);
					$("#afford").val(data[i].afford);
					$("#aptPlanting").val(data[i].aptPlanting);
					$("#startTime").val(data[i].startTime);
					$("#endTime").val(data[i].endTime);
					$("#name").val(data[i].name);
					$("#planting").val(data[i].planting);
					i++;
				}

			}

		});

		$("#scan").modal('show');
	});
	
	function showsubmenu() {
		var submenu = document.getElementById("hide_ul");
		if (submenu.style.display == 'none') {
			submenu.style.display = 'block';
		} else {
			submenu.style.display = 'none';
		}

	}
	function hidesubmenu() {
		var submenu = document.getElementById("hide_ul");
		submenu.style.display = 'none';
	}
</script>
</head>

<body>
	<div class="container-fluid">
		<div class="index_top row">
			<div class="col-md-6 col-xs-6" id="webname">
				<span>湖南农业大学</span>实习基地管理系统
			</div>
			<div class="col-md-6 col-xs-6">
				<div class="row" id="login">
					<ul>
						<li><a href="#">退出</a></li>
						<li
							style="border-right:solid 1px #FFFFFF; border-left:solid 2px #FFFFFF"><a
							href="#">更改密码</a></li>
						<li>张三<span class="icon-sort-up"></span></li>
					</ul>
				</div>
				<div class="row" id="research">
					<ul>
						<li><a href="#"><span class="glyphicon glyphicon-wrench"></span>报修申请</a></li>
						<li><a href="#">实习申请</a></li>
						<li><a href="#">土地租赁</a></li>
						<li><a href="#">首页</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="row">
			<div id="menue" class="col-md-3 col-xs-3">
				<ul>
					<li onClick="showsubmenu(this)"><a href="javascript:void(0)">我的工作<span
							class="glyphicon glyphicon-chevron-right"></span></a>
						<ul style="display:none;">
							<li><a href="#">我的租赁</a></li>
							<li><a href="#">我的实习</a></li>
							<li><a href="#">我的报修</a></li>
							<li><a href="#">个人信息</a></li>
						</ul></li>
					<li onClick="showsubmenu(this)"><a href="javascript:void(0)">事务审批<span
							class="glyphicon glyphicon-chevron-right"></span></a>
						<ul style="display:none;">
							<li><a href="#">租赁审批</a></li>
							<li><a href="#">实习审批</a></li>
							<li><a href="#">报修工单</a></li>
						</ul></li>
					<li onClick="showsubmenu(this)"><a href="javascript:void(0)">数据维护<span
							class="glyphicon glyphicon-chevron-right"></span></a>
						<ul style="display:none;">
							<li><a href="#">土地布局设置</a></li>
							<li><a href="#">租赁记录维护</a></li>
							<li><a href="#">实习基地维护</a></li>
							<li><a href="#">个人信息维护</a></li>
							<li><a href="#">权限 维护</a></li>
						</ul></li>
					<li onClick="showsubmenu(this)"><a href="javascript:void(0)">统计分析<span
							class="glyphicon glyphicon-chevron-right"></span></a>
						<ul style="display:none;">
							<li><a href="#">租赁统计</a></li>
							<li><a href="#">实习统计</a></li>
							<li><a href="#">维护统计</a></li>
						</ul></li>
				</ul>
			</div>

			<div id="content" class="col-md-9 col-xs-9">

				<div id="rent_position" class="row">
					<div class="col-md-11">
						<p>站内位置：我的工作 > 我的租赁</p>
					</div>
				</div>
				<!-- Nav tabs -->
				<script>
					$('#myTab a').click(function(e) {
						e.preventDefault()
						$(this).tab('show')
					})
				</script>
				
				<div id="rent_nav" class="row">
					<div class="col-md-10 col-xs-10 col-md-offset-1">
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#home"
								role="tab" data-toggle="tab">审核中</a></li>
							<li role="presentation"><a href="#profile" role="tab"
								data-toggle="tab">交费中</a></li>
						</ul>
					</div>
				</div>

				<!-- Tab panes -->
				<div class="tab-content row">
					<div role="tabpanel"
						class="tab-pane active col-md-10 col-md-offset-1" id="home">
						<table id="table1" class="display" cellspacing="0" width="100%">
							<thead>
								<tr bgcolor="#3B6290" style="color:#FFF">
									<th>开始日期</th>
									<th>租期</th>
									<th>基地名</th>
									<th>土地编号</th>
									<th>修改</th>

								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
					<div role="tabpanel" class="tab-pane col-md-10 col-md-offset-1"
						id="profile">
						<table id="table2" class="display" cellspacing="0" width="100%">
							<thead>
								<tr bgcolor="#3B6290" style="color:#FFF">
									<th>开始日期</th>
									<th>租期</th>
									<th>基地名</th>
									<th>土地编号</th>
									<th>交费截止日期</th>
									<th>交费所需材料</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
				</div>
				<!--弹出框-->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content" style="border:#4D719B 8px solid">
							<div class="modal-header" style="background:#4D719B; color:#FFF">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title text-center" id="myModalLabel">土地租赁详情查看</h4>
							</div>
							<div class="modal-body">
								<table class="table" id="modal_table">
									<tr>
										<td>基地名 ：</td>
										<td><input type="text" value="基地的名称"></td>
										<td>租赁人 ：</td>
										<td><input type="text" value="租赁人的名称"></td>
									</tr>
									<tr>
										<td>所属学院 ：</td>
										<td><input type="text" value="农学院"></td>
										<td>面向专业 ：</td>
										<td><input type="text" value="茶学"></td>
									</tr>
									<tr>
										<td>土地面积 ：</td>
										<td><input type="text" value="12平方米"></td>
										<td>适宜种植内容 ：</td>
										<td><input type="text" value="玉米"></td>
									</tr>
									<tr>
										<td>可承担人数 ：</td>
										<td><input type="text" value="70人"></td>
										<td>建筑面积 ：</td>
										<td><input type="text" value="10平方米"></td>
									</tr>
									<tr>
										<td>土地名称 ：</td>
										<td><input type="text" value="景园气象站"></td>
										<td>土地编号 ：</td>
										<td><input type="text" value="#001"></td>
									</tr>
									<tr>
										<td>起止年限 ：</td>
										<td><input type="text" value="2013-08-01"></td>
										<td>———————</td>
										<td><input type="text" value="2017-08-01"></td>
									</tr>
									<tr>
										<td>计划种植内容 ：</td>
										<td><input type="text" value="玉米"></td>
									</tr>
								</table>
							</div>
							<div class="modal-footer">
								<table class="table">
									<tr>
										<td>申请材料</td>
										<td><input type="text"></td>
										<td><button type="button">浏览</button>
											<button type="button">上传</button></td>
										<td width="160px"></td>
									</tr>
								</table>
								<center>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary">确定</button>
								</center>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<table id="table3" class="display" cellspacing="0" width="100%">
							<thead>
								<tr bgcolor="#ECF1F5">
										<td colspan="2">租赁历史</td>
										<td colspan="5">
											<!--筛选<span class="glyphicon glyphicon-sort-by-attributes"> </td>-->
											<ul id="dropdown_show">
												<li><span class="icon-filter" onClick="showsubmenu()">筛选</span>
													<ul id="hide_ul" style="display:none;">
														<li>
															<form>
																<table class="table">
																	<tr>
																		<td>基地名： <select name="" id="">
																				<option value="">显示全部</option>
																				<option value="">耘园科教综合基地</option>
																				<option value="">西南角农学基地</option>
																		</select>
																		</td>
																		<td>起止年份： <select name="" id="">
																				<option value="">请选择</option>
																				<option value="">2015</option>
																				<option value="">2016</option>
																		</select>年 ----- <select name="" id="">
																				<option value="">请选择</option>
																				<option value="">2015</option>
																				<option value="">2016</option>
																		</select>年
																		</td>
																	</tr>
																	<tr>
																		<td>土地编号 <select name="" id="">
																				<option value="">显示全部</option>
																				<option value="">#1</option>
																				<option value="">#2</option>
																		</select>
																		</td>
																		<td>最终状态 <select name="college" id="college"
																			size="1">
																				<option value="">显示全部</option>
																				<option value="">申请成功</option>
																				<option value="">申请失败</option>
																		</select>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2">
																			<button type="reset" class="btn btn-primary">重置</button>
																			<button onClick="hidesubmenu()" type="button"
																				class="btn btn-primary">完成</button>
																		</td>
																	</tr>
																</table>
															</form>
														</li>
													</ul></li>
											</ul>
									</tr>
									<tr style="background:#eeeff4">
										<th>序号</th>
										<th>开始日期</th>
										<th>结束日期</th>
										<th>基地名</th>
										<th>土地编号</th>
										<th>最终状态期</th>
										<th>详情</th>
									</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>



					<div class="modal fade" id="scan" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="false">
						<div class="modal-dialog">
							<div class="modal-content" style="border:#4D719B 8px solid">
								<div class="modal-header" style="background:#4D719B; color:#FFF">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title text-center" id="myModalLabel">土地租赁详情查看</h4>
								</div>
								<div class="modal-body">
									<table class="table" id="modal_table">
										<tr>
											<td>基地名 ：</td>
											<td><input type="text" id="bname"></td>
											<td>租赁人 ：</td>
											<td><input type="text" id="name"></td>
										</tr>
										<tr>
											<td>所属学院 ：</td>
											<td><input type="text" id="mycollege"></td>
											<td>面向专业 ：</td>
											<td><input type="text" id="major_oriented"></td>
										</tr>
										<tr>
											<td>土地编号 ：</td>
											<td><input type="text" id="lid"></td>
											<td>土地名称 ：</td>
											<td><input type="text" id="lname"></td>
										</tr>
										<tr>
											<td>土地面积 ：</td>
											<td><input type="text" id="landArea"></td>
											<td>建筑面积 ：</td>
											<td><input type="text" id="buildingArea"></td>
										</tr>
										<tr>
											<td>适宜种植内容 ：</td>
											<td><input type="text" id="aptPlanting"></td>
											<td>可承担人数 ：</td>
											<td><input type="text" id="afford"></td>
										</tr>
										<tr>
											<td>起止年限 ：</td>
											<td><input type="text" id="startTime"></td>
											<td>———————</td>
											<td><input type="text" id="endTime"></td>
										</tr>
										<tr>
											<td>计划种植内容 ：</td>
											<td><input type="text" id="planting"></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>

		</div>
	</div>

</body>

</html>
