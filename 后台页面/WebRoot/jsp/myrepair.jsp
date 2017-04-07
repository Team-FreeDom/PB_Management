<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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
<link href="../css/fileinput.css" media="all" rel="stylesheet"
	type="text/css" />

<link rel="stylesheet" href="../css/font-awesome.min.css">
<!--datatable-->
<link rel="stylesheet" href="../css/jquery.dataTables.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/myrepair.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
							<li><a href="myrepair.jsp"></i>我的报修</a></li>
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
						<table id="myrepair" class="cell-border" cellspacing="0"
							width="100%">
							<thead>
								<tr bgcolor="#3B6290" style="color:#FFF">
									<th>项目名称</th>
									<th>基地名称</th>
									<th>申报人</th>
									<th>申报时间</th>
									<th>预算金额</th>
									<th>申请状态</th>
									<th hidden>实际金额</th>
									<th hidden>具体地址</th>
									<th hidden>原因说明</th>
									<th hidden>材料地址</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>

					<!--弹出框-->
					<div class="modal fade" id="Checkdetail" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog" style="width:800px;">
							<div class="modal-content" id="modal-content"
								style="border:#3071a9 8px solid">
								<div class="modal-header" style="background:#3071a9; color:#FFF">
									<div class="glyphicon glyphicon-remove closeModal"
										data-dismiss="modal"></div>
									<h4 class="modal-title text-center" id="myModalLabel">详情查看</h4>
								</div>
								<div class="modal-body table-responsive">
									<div class="row">
										<div class="col-md-12">
											<table class="table" style="border:none !important;">
												<tr>
													<td>项目名称 ：</td>
													<td><input id="projectname" type="text"
														class="form-control" value="" disabled></td>
												</tr>
												<tr>
													<td>基地名称 ：</td>
													<td><input id="basename" type="text"
														class="form-control" value="" disabled></td>
												</tr>
												<tr>
													<td>报修人 ：</td>
													<td><input id="name" type="text" class="form-control"
														value="" disabled></td>
												</tr>
												<tr>
													<td>申报时间 ：</td>
													<td><input id="time" type="text" class="form-control"
														value="" disabled></td>
												</tr>
												<tr>
													<td>预算金额 ：</td>
													<td><input id="budget" type="text"
														class="form-control" value="" disabled></td>
												</tr>
												<tr>
													<td>实际金额 ：</td>
													<td><input id="actualmoney" type="text"
														class="form-control" value="" disabled></td>
												</tr>
												<tr>
													<td>具体地址 ：</td>
													<td><input id="address" type="text"
														class="form-control" value="" disabled></td>
												</tr>
												<tr id="lastButOne">
													<td>报修原因 ：</td>
													<td><textarea id="reason" class="form-control"
															disabled></textarea></td>
												</tr>
												<tr hidden id="hideReason">
													<td>拒绝理由 ：</td>
													<td style="text-align:left;"><textarea id="reason_0"
															style="border:#ccc 1px solid;height:80px;" disabled></textarea></td>

												</tr>

											</table>
										</div>
									</div>
								</div>
								<div class="modal-footer table-responsive">
									<center id="resourcetr1">
										申请材料：<a id="linkaddress" href="#"
											style="color:#00F; text-decoration:underline; font-size:16px;">点击查看</a>
									</center>
								</div>
							</div>
						</div>
					</div>



					<div class="col-lg-12 form-group">
						<table id="myrepair2" class="cell-border" cellspacing="0"
							width="100%">
							<thead>
								<tr bgcolor="#ECF1F5">
									<td>历史记录</td>
									<td colspan="3">
										<!--筛选<span class="glyphicon glyphicon-sort-by-attributes"> </td>-->
										<ul id="dropdown_show">
											<li><span class="icon-filter">筛选</span>
												<ul id="hide_ul" style="display:none;">
													<li>
														<form id="SearchForm">
															<table class="table">
																<tr>
																	<td style="text-align:center;">维修状态 ： <select
																		id="status">
																			<option value="-2">显示全部</option>
																			<option value="15">维修完成</option>
																			<option value="12">申请失败</option>
																			<option value="11">失效</option>
																	</select>  
																	</td>
																</tr>

																<tr>
																	<td align="center">

																		<button type="reset" class="btn btn-primary"
																			style="margin-left:25%">重置</button>
																		<button type="button" class="btn btn-primary"
																			id="finish">完成</button>

																	</td>
																</tr>
															</table>
														</form>
													</li>
												</ul></li>
										</ul>
									</td>
									<td colspan="7"></td>
								</tr>
								<tr style="background:#eeeff4">
									<th>项目名称</th>
									<th>基地名称</th>
									<th>申报人</th>
									<th>申报时间</th>
									<th>预算金额</th>
									<th>申请状态</th>
									<th hidden>实际金额</th>
									<th hidden>具体地址</th>
									<th hidden>原因说明</th>
									<th hidden>材料地址</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>


				</div>
			</div>
			<!-- End Sidebar-->




		</div>
		<!--row end-->
	</div>
	<div class="clearfix"></div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../js/jquery.min.js"></script>
	<!--datatable javascript-->
	<script src="../js/jquery.dataTables.min.js"></script>
	<!--updatefile-->
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/laydate.js"></script>
	<script src="../js/bootbox.min.js"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/myNeed/myrepair.js"></script>
	<script src="../js/kg.js"></script>



</body>
</html>
