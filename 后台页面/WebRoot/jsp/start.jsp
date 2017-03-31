<%@ page language="java"
	import="java.util.*,com.base.po.*,com.base.dao.*,com.base.daoImpl.*"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>湖南农业大学土地管理系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Bootstrap -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/font-awesome.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/index_main.css">
<link rel="stylesheet" href="../css/calendar.css" media="screen">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

<style>
#laydate_ys {
	width: 121px;
}
</style>

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
					</a></li>
					<li><a href="../loginout.do"
						class="dropdown-toggle notification-icon"> <i
							class="icon-remove"></i>
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
					<div class="sidebar-menu">
						<nav id="menu" class="nav-main" role="navigation">
							<ul class="nav nav-sidebar">
								<div class="panel-body text-center">
									<div class="bk-avatar">
										<a href="user.jsp"><img class="img-circle bk-img-60"
											alt="" src="" id="imageMain"></a>
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

								<li class="menuItem nav-parent"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>我的工作</span>
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

								<li class="menuItem nav-parent opened nav-expanded"><a>
										<i class="icon-copy" aria-hidden="true"></i><span>数据管理</span>
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
									</ul></li>
								<li class="menuItem nav-parent"><a> <i
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
				<div class="page-header row">
					<div class="pull-left">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a>位置 :</a></li>
							<li><a href="start.jsp"><i class=" icon-home"></i>工作计划制定</a></li>
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

						<div class="panel panel-primary">
							<div class="panel-heading">土地租赁</div>
							<div class="panel-body text-center" id="start">
								<button type="button" class="btn btn-danger btn-lg"
									id="start-btn">
									<span class="icon-play-circle">启动</span>
								</button>
							</div>
							<div class="panel-body" id="set" style="display:none">
								<center>
									<div>
										<h2>申报设置</h2>
									</div>
									<div>
										<form method="post" class="form-horizontal" role="form"
											id="submit">

											<div class="form-group form-group-lg">
												<label for="inputPassword"
													class="col-sm-2 col-sm-offset-1 control-label">申报开始时间</label>
												<div class="col-sm-3">
													<input class="form-control laydate-icon" id="demo" value="">
												</div>
												<label for="inputPassword" class="col-sm-2 control-label">申报结束时间</label>
												<div class="col-sm-3">
													<input class="form-control laydate-icon" id="demo2"
														value="">
												</div>
											</div>

											<div class="form-group form-group-lg">
												<label for="inputPassword"
													class="col-sm-2 col-sm-offset-1 control-label">租赁开始时间</label>
												<div class="col-sm-3">
													<input class="form-control laydate-icon" id="demo3"
														value="">
												</div>
												<label for="inputPassword" class="col-sm-2 control-label">租赁结束时间</label>
												<div class="col-sm-3">
													<input class="form-control laydate-icon" id="demo4"
														value="">
												</div>
											</div>
											<div class="form-group form-group-lg">
												<label for="inputPassword"
													class="col-sm-2 col-sm-offset-1 control-label">交费限制期限</label>
												<div class="col-sm-3">
													<input class="form-control" id="day" value=""
														placeholder="单位/天" />
												</div>
											</div>

											<div class="form-group form-group-lg">
												<button type="button" class="btn btn-primary" id="save">保存</button>
												<button type="button" class="btn btn-danger" id="end-btn">结束</button>
											</div>

										</form>
									</div>
									<div></div>
								</center>
							</div>
						</div>

						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">实习计划</h3>
							</div>
							<div class="panel-body">Panel content</div>
						</div>





					</div>
				</div>
				<!-- End Sidebar-->


			</div>

			<!--row end-->
		</div>
		<div class="clearfix"></div>
		<!--[if lt IE 9]>
                	<script src="../js/html5shiv.min.js"></script>
                	<script src="../js/respond.min.js"></script>
                <![endif]-->
		<script src="../js/jquery.min.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<script src="../js/bootbox.min.js"></script>
		<script type="text/javascript" src="../js/laydate.js"></script>
		<script src="../dist/jquery.cokie.min.js"></script>
		<script src="../js/kg.js"></script>
		<script src="../js/start.js"></script>
		<script>
			laydate.skin('yalan'); //切换皮肤，请查看skins下面皮肤库
			laydate({
				elem : '#demo'
			})
			laydate({
				elem : '#demo2'
			});
			laydate({
				elem : '#demo3'
			});
			laydate({
				elem : '#demo4'
			});
		</script>
</body>

</html>
