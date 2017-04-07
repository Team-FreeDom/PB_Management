<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<title>湖南农业大学基地管理系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Bootstrap -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/default.css">

<link rel="stylesheet" href="../css/font-awesome.min.css">
<!--datatable-->
<link rel="stylesheet" href="../css/jquery.dataTables.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/myrent.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->




</head>
<body>
	<div class="navbar" role="navigation">
		<div class="container-fluid container-nav">
			<!-- 点击收缩左边的菜单栏  + 缩小后左边菜单栏的显示 -->
			<ul class="nav navbar-nav navbar-actions navbar-left">
				<li class="visible-md visible-lg"><a><i
						class="icon-th-large"></i></a></li>
				<li class="visible-xs visible-sm"><a><i
						class="icon-align-justify"></i></a></li>
			</ul>

			<span class="teachCenterTitle">基地管理系统</span>
			<!-- Navbar Left -->

			<!-- Navbar Right -->
			<div class="navbar-right">
				<!-- Notifications -->
				<ul class="notifications" avalonctrl="subNotificationsController">
					<li class="hidden-sm hidden-xs"><a href="getMessage.do"
						class="dropdown-toggle notification-icon"> <i
							class="icon-envelope"><span class="badge msg"></span></i> <!--ms-if-->
					</a> <!-- <ul class="dropdown-menu">
							<li class="dropdown-header" style="text-align: center;">
                          <a href="getMessage.do"><strong>未读消息列表</strong></a>
                          </li>
                          <li class="dropdown-menu-footer text-center">
                              <a href="getMessage.do">更多消息</a>
                          </li>
						</ul> --></li>
					<li><a href="../loginout.do"
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
						<a href="index.do"><img src="../image/manage-logo.png" alt=""></a>
					</div>

					<!-- Sidebar Menu-->
					<div class="sidebar-menu" style="height: 384px;">
						<nav id="menu" class="nav-main" role="navigation">
							<ul class="nav nav-sidebar">
								<div class="panel-body text-center">
									<div class="bk-avatar">
										<a href="user.jsp"><img class="img-circle bk-img-60"
											alt="" id="imageMain" src=""></a>
										<!--ms-if-->
									</div>
									<div class="bk-padding-top-10">
										<i class="icon-circle text-danger"></i> <small></small>
										<!--ms-if-->
									</div>
								</div>
								<div class="divider2"></div>


								<li class="menuItem"><a href="index.do"> <i
										class="icon-home" aria-hidden="true"></i><span>主界面</span>
								</a></li>

								<li class="menuItem nav-parent opened nav-expanded"><a>
										<i class="icon-copy" aria-hidden="true"></i><span>我的工作</span>
								</a>
									<ul class="nav nav-children">
										<li><a href="field-rent.jsp"><span class="text"></span>土地租赁</a></li>
										<li><a href="baseApply.jsp"><span class="text"></span>基地申报</a></li>
										<li><a href="Repairpply.jsp"><span class="text"></span>报修申请</a></li>
										<li><a href="practiapply.jsp"><span class="text"></span>实习申请</a></li>
										<li><a href="myRent.jsp"><span class="text">我的租赁</span></a></li>
										<li><a href="myBase.jsp"><span class="text">我的基地</span></a></li>
										<li><a href="myrepair.jsp"><span class="text">我的报修</span></a></li>
									</ul></li>

								<li class="menuItem nav-parent"
									${(visitRight[0]==0&&visitRight[1]==0&&visitRight[2]==0)?"style='display:none;'":" "}>
									<a> <i class="icon-copy" aria-hidden="true"></i><span>审批工作</span>
								</a>
									<ul class="nav nav-children">
										<li ${visitRight[0]==0?"style='display:none;'":" "}><a
											href="rent-approve.jsp"><span class="text">租赁审批</span></a></li>

										<li ${visitRight[2]==0?"style='display:none;'":" "}><a
											href="baseCheck.jsp"><span class="text">基地审批</span></a></li>
										<li ${visitRight[1]==0?"style='display:none;'":" "}><a
											href="repairApprove.jsp"><span class="text">维修审批</span></a></li>

									</ul>
								</li>

								<li class="menuItem nav-parent"
									${(visitRight[3]==0&&visitRight[4]==0&&visitRight[5]==0&&visitRight[6]==0&&visitRight[7]==0&&visitRight[8]==0&&visitRight[9]==0&&visitRight[10]==0&&visitRight[11]==0)?"style='display:none;'":" "}>
									<a> <i class="icon-copy" aria-hidden="true"></i><span>数据管理</span>
								</a>
									<ul class="nav nav-children">
										<li ${visitRight[3]==0?"style='display:none;'":" "}><a
											href="notification.do"><span class="text"> 发布通知公告</span></a></li>
										<li ${visitRight[4]==0?"style='display:none;'":" "}><a
											href="land_modle.jsp"><span class="text"> 土地布局设置</span></a></li>
										<li ${visitRight[6]==0?"style='display:none;'":" "}><a
											href="fieldRent_maintain.jsp"><span class="text">
													土地租赁维护</span></a></li>
										<li ${visitRight[5]==0?"style='display:none;'":" "}><a
											href="baseMaintain.jsp"><span class="text"> 实习基地维护</span></a></li>
										<li ${visitRight[11]==0?"style='display:none;'":" "}><a
											href="Repairmanage.jsp"><span class="text"> 报修信息维护</span></a></li>
										<li ${visitRight[7]==0?"style='display:none;'":" "}><a
											href="practicePlanMaintain.jsp"><span class="text">
													实习计划维护</span></a></li>
										<li ${visitRight[10]==0?"style='display:none;'":" "}><a
											href="start.jsp"><span class="text"> 租赁计划制定</span></a></li>
										<li ${visitRight[8]==0?"style='display:none;'":" "}><a
											href="mangeruser.jsp"><span class="text"> 系统用户维护</span></a></li>
										<li ${visitRight[9]==0?"style='display:none;'":" "}><a
											href="system_power.jsp"><span class="text"> 系统权限设置</span></a></li>
									</ul>
								</li>
								<li class="menuItem nav-parent"
									${visitRight[12]==0?"style='display:none;'":" "}><a> <i
										class="icon-copy" aria-hidden="true"></i><span>统计分析</span>
								</a>
									<ul class="nav nav-children">

										<li ${visitRight[12]==0?"style='display:none;'":" "}><a
											href="statisticData.jsp"><span class="text">实习分析</span></a></li>

									</ul></li>
								<li><a href="#"> <i class="icon-copy"
										aria-hidden="true"></i><span>集中实习在线课堂</span></a></li>
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
							<li><a>位置</a></li>
							<li><a href="myRent.jsp">我的租赁</a></li>
						</ol>
					</div>
					<div class="pull-right">
						<ol class="breadcrumb visible-sm visible-md visible-lg wz">
							<li><a href="baseApply.jsp"><i class=" icon-building"></i>基地申报</a></li>
                                <li><a href="field-rent.jsp"><i class="icon-legal"></i>土地租赁</a></li>
                                <li><a href="practiapply.jsp"><i class="icon-user"></i>实习申请</a></li>
                                <li><a href="Repairpply.jsp"><i class="icon-home"></i>报修申请</a></li>
						</ol>
					</div>

				</div>
				<!-- 主面板内容 -->
				<div class="row form">

					<div class="col-lg-12">

						<table id="table1" class="cell-border" cellspacing="0"
							width="100%">
							<thead>
								<tr bgcolor="#3B6290" style="color:#FFF">
									<th>开始日期</th>
									<th>租期</th>
									<th>基地名</th>
									<th>土地名称</th>
									<th>土地编号</th>
									<th>状态</th>
									<th>操作</th>

								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>

					</div>

					<!--弹出框-->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="border:#4D719B 8px solid">
								<div class="modal-header" style="background:#4D719B; color:#FFF">
									<div class="glyphicon glyphicon-remove closeModal"
										data-dismiss="modal"></div>
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
										<div class="glyphicon glyphicon-remove closeModal"
											data-dismiss="modal"></div>
										<h4 class="modal-title" id="myModalLabel"></h4>
									</div>
									<div class="modal-body" style="text-align: left;">
										<h5>您确定要撤销吗？</h5>
									</div>
									<div class="modal-footer">

										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>

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
										<div class="glyphicon glyphicon-remove closeModal"
											data-dismiss="modal"></div>
										<h6 class="modal-title" id="myModalLabel"></h6>
									</div>
									<div class="modal-body" style="text-align: left;">
										<h5>您确定要删除吗？</h5>
									</div>
									<div class="modal-footer">

										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>

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
										<div class="glyphicon glyphicon-remove closeModal"
											data-dismiss="modal"></div>
										<h6 class="modal-title" id="myModalLabel"></h6>
									</div>
									<div class="modal-body" style="text-align: left;">
										<h5>您确定要提交吗？</h5>
									</div>
									<div class="modal-footer">

										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>

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
									<div class="glyphicon glyphicon-remove closeModal"
										data-dismiss="modal"></div>
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
												<td><a href="#" style="color:#009">点击下载</a></td>
											</tr>
											<tr>
												<td>2</td>
												<td id="upload1">土地有偿使用协议</td>
												<td><a href="#" style="color:#009">点击下载</a></td>
											</tr>
										</table>
										<span style="color:#F00">交费截止日期：</span><input type="text"
											id="closingdate" readonly>
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
									<div class="glyphicon glyphicon-remove closeModal"
										data-dismiss="modal"></div>
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


													<center>
														<div class="form-group" style="position:relative">
															<input type="file" style=" position:absolute; left:25%"
																name="fileResource"> <label
																for="exampleInputFile" class="btn btn-primary"
																style=" position:absolute; top:-10px;left:23.5%">上传文件</label>
														</div>
													</center> <!-- <div style="float:right;">

														 <form enctype="multipart/form-data">
														<input   class="file" name="fileResource"
															type="file" multiple data-min-file-count="1">
														 </form>
													</div> -->

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

						<table id="table2" class="cell-border" cellspacing="0"
							width="100%">
							<thead>
								<tr bgcolor="#ECF1F5">
									<td colspan="1">处理结果</td>
									<td colspan="6">
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

																	<!-- <td>起止年份： <select name="startTime"
																			id="startTimeUnion">
																				<option value="">请选择</option>
																				<option value="2015">2015</option>
																				<option value="2016">2016</option>
																		</select>年 ----- <select name="endTime" id="endTimeUnion">
																				<option value="">请选择</option>
																				<option value="2015">2015</option>
																				<option value="2016">2016</option>
																		</select>年
																		</td> -->

																	<td colspan="2">最终状态 <select name="desc"
																		id="descUnion" size="1">
																			<option value="-1">显示全部</option>
																			<option value="0">申请成功</option>
																			<option value="1">申请失败</option>
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

									<th>开始日期</th>
									<th>结束日期</th>
									<th>基地名</th>
									<th>土地名称</th>
									<th>土地编号</th>
									<th>最终状态期</th>
									<th>详情</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>

					</div>
					<!--弹出框2-->
					<div class="modal fade" id="scan" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="border:#4D719B 8px solid">
								<div class="modal-header" style="background:#4D719B; color:#FFF">


									<div class="glyphicon glyphicon-remove closeModal"
										data-dismiss="modal" onclick="closeScan2()"></div>


									<h4 class="modal-title text-center" id="myModalLabel">土地租赁详情</h4>
								</div>
								<div class="modal-body table-responsive">
									<table class="table">
										<tr>
											<td>基地名 ：</td>
											<td><input type="text" id="bname" readonly="readonly"></td>
											<td>租赁人 ：</td>
											<td><input type="text" id="name" readonly="readonly"></td>
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
								<div class="modal-footer table-responsive">
									<center id="scanModal" style="display:none;">
										<span> 失败原因：<input type="text" id="reason2" readonly></span>

										<%-- </center>申请材料：<a id="source" href="#">点击查看</a> --%>
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


</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!--[if lt IE 9]>
	<script src="../js/html5shiv.min.js"></script>
	<script src="../js/respond.min.js"></script>
<![endif]-->
<script src="../js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>
<!-- <script src="../js/SyearE.js"></script> -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--datatable javascript-->
<script src="../js/jquery.dataTables.min.js"></script>
<script src="../js/bootbox.min.js"></script>
<script src="../dist/jquery.cokie.min.js"></script>
<script src="../js/myNeed/myRent.js"></script>
<script src="../js/kg.js"></script>
</html>
