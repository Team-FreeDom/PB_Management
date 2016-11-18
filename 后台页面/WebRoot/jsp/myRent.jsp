<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<title>我的租赁</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Bootstrap -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/default.css">
<link href="../css/fileinput.css" media="all" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="../css/font-awesome.min.css">
<!--datatable-->
<link rel="stylesheet" href="../css/jquery.dataTables.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/myrent.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/fileinput.js" type="text/javascript"></script>
<script src="../js/fileinput_locale_zh.js" type="text/javascript"></script>

<script src="../js/bootstrap.min.js"></script>
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!--datatable javascript-->
<script src="../js/jquery.dataTables.min.js"></script>
<script src="../js/main.js"></script>


</head>
<body>
	<div class="navbar" role="navigation">
		<div class="container-fluid container-nav">
			<!-- 点击收缩左边的菜单栏  + 缩小后左边菜单栏的显示 -->
			<ul class="nav navbar-nav navbar-actions navbar-left">
				<li class="visible-md visible-lg"><a href="index.html#"><i
						class="icon-th-large"></i></a></li>
				<li class="visible-xs visible-sm"><a href="index.html#"><i
						class="icon-align-justify"></i></a></li>
			</ul>

			<span class="teachCenterTitle">基地管理系统</span>
			<!-- Navbar Left -->

			<!-- Navbar Right -->
			<div class="navbar-right">
				<!-- Notifications -->
				<ul class="notifications" avalonctrl="subNotificationsController">
					<li class="hidden-sm hidden-xs"><a href="#"
						class="dropdown-toggle notification-icon" data-toggle="dropdown">
							<i class="icon-envelope"></i> <!--ms-if-->
					</a>
						<ul class="dropdown-menu">
							<li class="dropdown-header" style="text-align: center;"><strong>未读消息列表</strong>
							</li>
							<li class="dropdown-menu-footer text-center"><a
								href="../teach/notifications.html">更多消息</a></li>
						</ul></li>
					<li><a href="#outModal"
						class="dropdown-toggle notification-icon" data-toggle="modal">
							<i class="icon-remove"></i>
					</a></li>
				</ul>

				<!-- End Notifications -->
			</div>
			<!-- End Navbar Right -->
		</div>
	</div>



	<div class="container-fluid content">
		<div class="row">

			<div class="sidebar">
				<div class="sidebar-collapse">
					<!-- Sidebar Header Logo-->
					<div class="sidebar-header ">
						<a href="#" target="_blank"><img
							src="../image/manage-logo.png" alt=""></a>
					</div>

					<!-- Sidebar Menu-->
					<div class="sidebar-menu" style="height: 384px;">
						<nav id="menu" class="nav-main" role="navigation">
							<ul class="nav nav-sidebar">
								<div class="panel-body text-center">
									<div class="bk-avatar">
										<a href="#"><img class="img-circle bk-img-60" alt=""
											src="../image/psu.jpg"></a>
										<!--ms-if-->
									</div>
									<div class="bk-padding-top-10">
										<i class="icon-circle text-success"></i> <small>罗旭</small>
										<!--ms-if-->
									</div>
								</div>
								<div class="divider2"></div>


								<li class="menuItem"><a href="index.html"> <i
										class="icon-home" aria-hidden="true"></i><span>主界面</span>
								</a></li>

								<li class="menuItem nav-parent"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>我的工作</span>
								</a>
									<ul class="nav nav-children">
										<li><a href="#"><span class="text">我的租赁</span></a></li>
										<li><a href="#"><span class="text">我的实习</span></a></li>
										<li><a href="#"><span class="text">我的报修</span></a></li>
										<li><a href="#"><span class="text">我的基地</span></a></li>
									</ul></li>

								<li class="menuItem nav-parent"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>审批工作</span>
								</a>
									<ul class="nav nav-children">
										<li><a href="#"><span class="text">租赁审批</span></a></li>
										<li><a href="#"><span class="text">实习审批</span></a></li>
										<li><a href="#"><span class="text">基地审批</span></a></li>
										<li><a href="#"><span class="text">基地审批</span></a></li>

									</ul></li>

								<li class="menuItem nav-parent"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>数据管理</span>
								</a>
									<ul class="nav nav-children">
										<li><a href="#"><span class="text"> 发布通知公告</span></a></li>
										<li><a href="#"><span class="text"> 土地布局设置</span></a></li>
										<li><a href="#"><span class="text"> 实习基地维护</span></a></li>
										<li><a href="#"><span class="text"> 土地租赁维护</span></a></li>
										<li><a href="#"><span class="text"> 实习计划维护</span></a></li>
										<li><a href="#"><span class="text"> 系统用户维护</span></a></li>
										<li><a href="#"><span class="text"> 系统权限设置</span></a></li>
									</ul></li>
								<li class="menuItem nav-parent"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>统计分析</span>
								</a>
									<ul class="nav nav-children">
										<li><a href="#"><span class="text">租赁统计</span></a></li>
										<li><a href="#"><span class="text">实习分析</span></a></li>
										<li><a href="#"><span class="text">实习基地统计</span></a></li>
									</ul></li>

							</ul>
						</nav>
					</div>
					<!-- End Sidebar Menu-->
				</div>
				<!-- Sidebar Footer-->
				<div class="sidebar-footer">
					<div class="copyright text-center">
						<div>湖南农业大学版权所有</div>
					</div>
				</div>
				<!-- End Sidebar Footer-->
			</div>
			<!-- End Sidebar-->


			<div class="main " style="min-height: 584px;">
				<!-- 当前地址导航 -->
				<div class="page-header">
					<div class="pull-left">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a href="index.html">位置</a></li>
							<li><a href="index.html"><i class=" icon-home"></i>首页</a></li>
							<li><a href="index.html">我的工作</a></li>
							<li><a href="index.html">我的租赁</a></li>
						</ol>
					</div>
					<div class="pull-right">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a href="index.html"><i class=" icon-building"></i>基地申报</a></li>
							<li><a href="index.html"><i class="icon-legal"></i>土地租赁</a></li>
							<li><a href="index.html"><i class="icon-user"></i>实习申请</a></li>
							<li><a href="index.html"><i class="icon-home"></i>报修申请</a></li>
						</ol>
					</div>
				</div>
				<!-- 主面板内容 -->
				<div class="row form">

					<div class="col-lg-12">
						<div class=" col-md-offset-1 col-md-10">
							<table id="table1" class="cell-border" cellspacing="0"
								width="100%">
								<thead>
									<tr bgcolor="#3B6290" style="color:#FFF">
										<th>开始日期</th>
										<th>租期</th>
										<th>基地名</th>
										<th>土地编号</th>
										<th>状态</th>
										<th>操作</th>

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
									<h4 class="modal-title text-center" id="myModalLabel">土地租赁详情及修改</h4>
								</div>
								<div class="modal-body table-responsive">
									<table class="table">
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
								<div class="modal-footer table-responsive">
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

					<!--撤销确认对话框-->
					<div class="modal fade" id="cancelOneModal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<!-- data-backdrop="static" 禁止点击弹框后面内容 -->
						<form class="form-horizontal" role="form">
							<div class="modal-dialog modal-sm ">
								<!-- modal-sm 小的  modal-lg 大的 -->
								<div class="modal-content" style="border:#4D719B 8px solid">
									<div class="modal-header"
										style="background:#4D719B; color:#FFF">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel"></h4>
									</div>
									<div class="modal-body" style="text-align: left;">
										<h5>您确定要撤销吗？</h5>
									</div>
									<div class="modal-footer">
										<!-- 
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消
                    </button>
                     -->
										<button type="button" class="btn btn-primary"
											id="cancelSubmit">确认</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
						</form>
					</div>

					<!--单个删除确认对话框-->
					<div class="modal fade" id="deleteOneModal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<!-- data-backdrop="static" 禁止点击弹框后面内容 -->
						<form class="form-horizontal" role="form">
							<div class="modal-dialog modal-sm ">
								<!-- modal-sm 小的  modal-lg 大的 -->
								<div class="modal-content" style="border:#4D719B 8px solid">
									<div class="modal-header"
										style="background:#4D719B; color:#FFF">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel"></h4>
									</div>
									<div class="modal-body" style="text-align: left;">
										<h5>您确定要删除吗？</h5>
									</div>
									<div class="modal-footer">
										<!-- 
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消
                    </button>
                     -->
										<button type="button" class="btn btn-primary" id="delSubmit">
											确认</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
						</form>
					</div>

					<!--单个提交确认对话框-->
					<div class="modal fade" id="submitOneModal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<!-- data-backdrop="static" 禁止点击弹框后面内容 -->
						<form class="form-horizontal" role="form">
							<div class="modal-dialog modal-sm ">
								<!-- modal-sm 小的  modal-lg 大的 -->
								<div class="modal-content" style="border:#4D719B 8px solid">
									<div class="modal-header"
										style="background:#4D719B; color:#FFF">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel"></h4>
									</div>
									<div class="modal-body" style="text-align: left;">
										<h5>您确定要提交吗？</h5>
									</div>
									<div class="modal-footer">
										<!-- 
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消
                    </button>
                     -->
										<button type="button" class="btn btn-primary"
											id="SubmitTemperate">确认</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
						</form>
					</div>

					<!--交费详情模态框-->
					<div class="modal fade" id="payFor" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="border:#4D719B 8px solid">
								<div class="modal-header" style="background:#4D719B; color:#FFF">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title text-center" id="myModalLabel">缴费中详情</h4>
								</div>
								<div class="modal-body table-responsive">
									<table class="table">
										<tr>
											<td>基地名 ：</td>
											<td><input type="text" id="bnameP" readonly="readonly"></td>
											<td>租赁人 ：</td>
											<td><input type="text" id="nameP"></td>
										</tr>
										<tr>
											<td>土地名称 ：</td>
											<td><input type="text" id="lnameP" readonly="readonly"></td>
											<td>土地编号 ：</td>
											<td><input type="text" id="lidP" readonly></td>
										</tr>
										<tr>
											<td>申报部门 ：</td>
											<td><input type="text" id="mycollegeP" readonly></td>
											<td>可承担人数 ：</td>
											<td><input type="text" id="affordP" readonly></td>
										</tr>

										<tr>
											<td>土地面积 ：</td>
											<td><input type="text" id="landAreaP" readonly></td>
											<td>建筑面积 ：</td>
											<td><input type="text" id="buildingAreaP" readonly></td>
										</tr>
										<tr>
											<td>适宜种植内容 ：</td>
											<td><input type="text" id="aptPlantingP" readonly></td>
											<td>计划种植内容 ：</td>
											<td><input type="text" id="plantingP" readonly></td>
										</tr>
										<tr>
											<td>起止年限 ：</td>
											<td><input type="text" id="startTimeP" readonly></td>
											<td>———————</td>
											<td><input type="text" id="endTimeP" readonly></td>
										</tr>
									</table>
									<center>
										<table class="table table-bordered" style="width:70%">
											<tr>
												<td>序号</td>
												<td>材料名称</td>
												<td>操作</td>
											</tr>
											<tr>
												<td>1</td>
												<td id="upload1">教学科研用地协议</td>
												<td><a href="exportFile.do?fileName=teaching.pdf"
													style="color:#009">点击下载</a></td>
											</tr>
											<tr>
												<td>2</td>
												<td id="upload1">土地有偿使用协议</td>
												<td><a href="exportFile.do?fileName=landing.docx"
													style="color:#009">点击下载</a></td>
											</tr>
										</table>
										<span style="color:#F00">交费截止日期：</span><input type="text"
											id="closingdate">
									</center>

								</div>
								<div class="modal-footer table-responsive">
									<button type="button" class="btn btn-primary"
										data-dismiss="modal">确定</button>
								</div>
							</div>
						</div>
					</div>


					<!--修改内容模态框-->
					<div class="modal fade" id="rent_edit" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="border:#4D719B 8px solid">
								<div class="modal-header" style="background:#4D719B; color:#FFF">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title text-center" id="myModalLabel">土地租赁修改</h4>
								</div>
								<form action="updateContent.do" method="post"
									enctype="multipart/form-data" onsubmit="return check()"
									id="updateForm">
									<div class="modal-body table-responsive">

										<table class="table">
											<tr>
												<td><input type="text" id="hide" name="hide" hidden></td>
											</tr>
											<tr>
												<td>基地名 ：</td>
												<td><input type="text" id="bnamer" disabled></td>
												<td>租赁人 ：</td>
												<td><input type="text" id="namer" disabled></td>
											</tr>
											<tr>
												<td>土地编号 ：</td>
												<td><input type="text" id="lidr" name="lid"></td>
												<td>申报部门 ：</td>
												<td><select name="dept" id="deptme">
														<option value="" width="10px" id="manydept">请选择</option>
												</select></td>
												<!-- <input type="text" id="mycolleger" name="dept"></td> -->
											</tr>
											<tr>
												<td>适宜种植内容 ：</td>
												<td><input type="text" id="aptPlantingr" disabled></td>
												<td>计划种植内容 ：</td>
												<td><input type="text" id="plantingr" name="planting"></td>

											</tr>

											<tr>
												<td>起止年限 ：</td>
												<td><input type="text" id="startTimer" disabled></td>
												<td>———————</td>
												<td><input type="text" id="endTimer" disabled></td>
											</tr>
										</table>
									</div>
									<div class="modal-footer table-responsive">
										<table class="table">
											<tr>

												<td colspan="3">
													<div style="float:right;">

														<!--  <form enctype="multipart/form-data"> -->
														<input  name="fileResource" class="file"
															type="file" multiple data-min-file-count="1">
														<!--  </form> -->
													</div>
												</td>
											</tr>
										</table>

										<center>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<button type="button" class="btn btn-primary"
												onclick="submitUpdate()">确定</button>
										</center>
									</div>
								</form>
							</div>
						</div>
					</div>





					<div class="col-lg-12 form-group">
						<div class=" col-md-offset-1 col-md-10">
							<table id="table2" class="cell-border" cellspacing="0"
								width="100%">
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
																		<td>基地名： <select name="bname" id="bnameUnion">
																				<option value="" id="selectallbase">显示全部</option>

																		</select>
																		</td>
																		<td>起止年份： <select name="startTime"
																			id="startTimeUnion">
																				<option value="">请选择</option>
																				<option value="2015">2015</option>
																				<option value="2016">2016</option>
																		</select>年 ----- <select name="endTime" id="endTimeUnion">
																				<option value="">请选择</option>
																				<option value="2015">2015</option>
																				<option value="2016">2016</option>
																		</select>年
																		</td>
																	</tr>
																	<tr>
																		<td>土地编号 <select name="lid" id="lidUnion">
																				<option value="">显示全部</option>
																				<option value="1">#1</option>
																				<option value="2">#2</option>
																		</select>
																		</td>
																		<td>最终状态 <select name="desc" id="descUnion"
																			size="1">
																				<option value="">显示全部</option>
																				<option value="申请成功">申请成功</option>
																				<option value="申请失败">申请失败</option>
																		</select>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2">
																			<button type="reset" class="btn btn-primary">重置</button>
																			<button onClick="unionSelect()" type="button"
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
					</div>
					<!--弹出框2-->
					<div class="modal fade" id="scan" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="border:#4D719B 8px solid">
								<div class="modal-header" style="background:#4D719B; color:#FFF">
									<button type="button" class="close" data-dismiss="modal"
										onclick="closeScan2()">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title text-center" id="myModalLabel">土地租赁详情</h4>
								</div>
								<div class="modal-body table-responsive">
									<table class="table">
										<tr>
											<td>基地名 ：</td>
											<td><input type="text" id="bname" readonly="readonly"></td>
											<td>租赁人 ：</td>
											<td><input type="text" id="name"></td>
										</tr>
										<tr>
											<td>土地名称 ：</td>
											<td><input type="text" id="lname" readonly="readonly"></td>
											<td>土地编号 ：</td>
											<td><input type="text" id="lid" readonly></td>
										</tr>
										<tr>
											<td>申报部门 ：</td>
											<td><input type="text" id="mycollege" readonly></td>
											<td>可承担人数 ：</td>
											<td><input type="text" id="afford" readonly></td>
										</tr>

										<tr>
											<td>土地面积 ：</td>
											<td><input type="text" id="landArea" readonly></td>
											<td>建筑面积 ：</td>
											<td><input type="text" id="buildingArea" readonly></td>
										</tr>
										<tr>
											<td>适宜种植内容 ：</td>
											<td><input type="text" id="aptPlanting" readonly></td>
											<td>计划种植内容 ：</td>
											<td><input type="text" id="planting" readonly></td>
										</tr>
										<tr>
											<td>起止年限 ：</td>
											<td><input type="text" id="startTime" readonly></td>
											<td>———————</td>
											<td><input type="text" id="endTime" readonly></td>
										</tr>
									</table>
								</div>
								<div class="modal-footer table-responsive"
									>
									<center  id="scanModal" style="display:none;">
									<span>	失败原因：<input type="text" id="reason2" readonly></span>
										
									</center>申请材料：<a id="source" href="#">点击查看</a>
								</div>

							</div>
						</div>
					</div>

				</div>
			</div>
			<!-- End Sidebar-->



		</div>
		<!--row end-->
	</div>
	<div class="clearfix"></div>
	<script>
		$(document)
				.ready(
						function() {
							$('#table1')
									.dataTable(
											{
												"bSort" : false,
												"bFilter" : false,
												"aLengthMenu" : [ 2, 4, 5, 8,
														10 ], //动态指定分页后每页显示的记录数。
												"lengthChange" : true, //是否启用改变每页显示多少条数据的控件
												"iDisplayLength" : 8, //默认每页显示多少条记录
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
															"orderable" : true, // 禁用排序
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
															"render" : function(
																	data, type,
																	row) {
																var status = row.status;
																if (status == 5
																		|| status == 8
																		|| status == 3) {
																	return data = '<span>不通过</span>';
																} else if (status == 6) {
																	return data = '<span>通过</span>';
																} else {
																	return data = '<span>'
																			+ data
																			+ '</span>';
																}
															}
														},

														{
															"mData" : "status",
															"orderable" : false, // 禁用排序
															"sDefaultContent" : '',
															"sWidth" : "10%",
															"render" : function(
																	data, type,
																	row) { //render改变该列样式,4个参数，其中参数数量是可变的。
																//alert(row.la_id);
																var la_id = row.la_id;
																if (data == 9) {
																	return data = '<button type="button"  id='
																			+ row.la_id
																			+ ' onclick="editOne(this)" class="btn btn-warning btn-xs" data-id='
																			+ la_id
																			+ ' id="frame1_edit">修改</button><button type="button" class="btn btn-danger btn-xs" data-id='+la_id+' id="frame1_delete">删除</button> <button type="button" class="btn btn-success btn-xs" data-id='+la_id+'  id="frame1_submit">提交</button>';

																} else if (data == 2
																		|| data == 4) {
																	return data = '<button type="button" id='
																			+ row.la_id
																			+ ' onclick="scanOne(this)" class="btn btn-warning btn-xs" data-id='
																			+ la_id
																			+ ' id="frame1_scan">查看</button><button type="button" class="btn btn-danger btn-xs" data-id='+la_id+' id="frame1_cancel" >撤回</button>';
																} else if (data == 1) {
																	return data = '<button type="button"  id='
																			+ row.la_id
																			+ ' onclick="payForOne(this)" class="btn btn-warning btn-xs" data-id='
																			+ la_id
																			+ ' id="frame1_scan">查看</button><button type="button" class="btn btn-danger btn-xs" data-id='+la_id+' id="frame1_cancel" >撤回</button>';
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

							$('#table2')
									.DataTable(
											{
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
														{ //aoColumns设置列时，不可以任意指定列，必须列出所有列。
															"mData" : null,
															"targets": 0,
															"orderable" : true, // 禁用排序
															"sDefaultContent" : "",
															"sWidth" : "2%",
															"fnDrawCallback": function(){
																var api = this.api();
																var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
																
																api.column(0).nodes().each(function(cell, i) {
																cell.innerHTML = startIndex + i + 1;
																}); 
																}
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
							
							
							
						}	
							
				);
		
		
		

		$(document).ready(

				function() {

					//alert("来啦！");

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
				}

		)

		$(document).ready(

				function() {

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
				}

		)

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
				alert("请选择申请部门！");
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
											"mData" : "descp",
											"orderable" : false, // 禁用排序
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
											"orderable" : false, // 禁用排序
											"sDefaultContent" : '',
											"sWidth" : "5%",
											"render" : function(data, type, row) { //render改变该列样式,4个参数，其中参数数量是可变的。

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

			$
					.ajax({
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

		$(document).delegate('#cancelSubmit', 'click', function() {//点击确认撤销的按钮后执行
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
				cache : false, //不允许缓存
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

		$(document).delegate('#delSubmit', 'click', function() {//点击确认删除的按钮后执行
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
				cache : false, //不允许缓存
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

		$(document).delegate('#SubmitTemperate', 'click', function() {//点击确认删除的按钮后执行
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
				cache : false, //不允许缓存
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
	</script>

</body>
</html>