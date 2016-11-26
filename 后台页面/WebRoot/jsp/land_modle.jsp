<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<title>湖南微课网-教师中心</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/font-awesome.min.css">
<link rel="stylesheet" href="../dist/gridstack.css" />
<link rel="stylesheet" href="../css/style.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<style type="text/css">
.lname {
	position: absolute;
	left: 0;
	top: 0;
	padding-right: 15px;
}

.grid-stack {
	background: #fff;
}

.gay {
	cursor: pointer;
	color: #fff;
	text-align: center;
	background-color: #2B83BD;
}

.normal {
	cursor: pointer;
	color: #fff;
	text-align: center;
	background-color: #63addc;
}
</style>
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
							<li class="dropdown-header" style="text-align: center;">
                          <a href="getMessage.do"><strong>未读消息列表</strong></a>
                          </li>    
                          <li class="dropdown-menu-footer text-center">
                              <a href="getMessage.do">更多消息</a>
                          </li>
						</ul></li>
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
											src="" id="imageMain"></a>
										<!--ms-if-->
									</div>
									<div class="bk-padding-top-10">
										<i class="icon-circle text-success"></i> <small>罗旭</small>
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
										<li><a href="myRent.jsp"><span class="text">我的租赁</span></a></li>
										<li><a><span class="text">我的实习</span></a></li>
										<li><a><span class="text">我的报修</span></a></li>
										<li><a><span class="text">我的基地</span></a></li>
									</ul></li>

								<li class="menuItem nav-parent"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>审批工作</span>
								</a>
									<ul class="nav nav-children">
										<li><a href="rent-approve.jsp"><span class="text">租赁审批</span></a></li>
										<li><a><span class="text">实习审批</span></a></li>
										<li><a href="#"><span class="text">基地审批</span></a></li>
										<li><a href="#"><span class="text">基地审批</span></a></li>

									</ul></li>

								<li class="menuItem nav-parent opened nav-expanded"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>数据管理</span>
								</a>
									<ul class="nav nav-children">
										<li><a href="#"><span class="text"> 发布通知公告</span></a></li>
										<li><a href="land_modle.jsp"><span class="text"> 土地布局设置</span></a></li>
										<li><a href="#"><span class="text"> 实习基地维护</span></a></li>
										<li><a href="fieldRent_maintain.jsp"><span class="text"> 土地租赁维护</span></a></li>
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
							<li><a>位置</a></li>
							<li><a href="land_modle.jsp"><i class=" icon-home"></i>土地布局设置</a></li>
						</ol>
					</div>
					<div class="pull-right">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a><i class=" icon-building"></i>基地申报</a></li>
							<li><a href="field-rent.jsp"><i class="icon-legal"></i>土地租赁</a></li>
							<li><a><i class="icon-user"></i>实习申请</a></li>
							<li><a><i class="icon-home"></i>报修申请</a></li>
						</ol>
					</div>
				</div>
				<!-- 主面板内容 -->
				<div class="row">
					<div class=" col-md-4">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-4 control-label">选择区域：</label>
								<div class="col-sm-8">
									<select id="load-grid" class="form-control">
									  <option value="" id="load-gridh" >请选择</option>
										<!-- <option value="1">长安基地</option>
										<option value="2">耕耘基地</option> -->
										
									</select>
								</div>
							</div>
						</form>
					</div>

					<div class="col-md-4">

						<div class="btn-group">
							<button type="button" class="btn btn-success" id="save-grid">更新到服务器</button>
							<button type="button" class="btn btn-default" id="clear-grid">清空布局</button>
							<button type="button" class="btn btn-default" id="add-grid">增加节点</button>
						</div>

					</div>
					<div class="col-md-4">
						<p class="lead text-center bg-primary">土地信息设置区</p>
					</div>
				</div>
				<!-- 土地布局 -->
				<div class="row">
					<div class="col-md-8">
						<div class="grid-stack"></div>

					</div>

					<div class="col-md-4">
						<div style="background:#FFF; padding:20px;">
							<form class="form-horizontal" role="form">
								<fieldset disabled>
									<div class="form-group">
										<label for="disabledIDInput" class="col-sm-3 control-label">土地编号：</label>
										<div class="col-sm-6">
											<input type="text" id="tudi_id" class="form-control">
										</div>
									</div>
								</fieldset>

								<div class="form-group">
									<label for="inputname" class="col-sm-3 control-label">土地名称：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="tudi_name"
											placeholder="土地中文名"> <span class="help-block">建议保持唯一性，增强用户体验</span>
									</div>
								</div>
								<div class="form-group">
									<label for="inputname" class="col-sm-3 control-label">推荐用途：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control"
											id="tudi_plantingContent" placeholder="样例：玉米、大豆">
									</div>
								</div>
								<div class="form-group">
									<label for="inputname" class="col-sm-3 control-label">土地面积：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="tudi_landArea"
											placeholder="单位：平方">
									</div>
								</div>
								<div class="form-group">
									<label for="inputname" class="col-sm-3 control-label">建筑面积：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="tudi_buildingArea"
											placeholder="单位：平方">
									</div>
								</div>
								<div class="form-group">
									<label for="inputname" class="col-sm-3 control-label">实习上限：</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="tudi_Afford"
											placeholder="人数">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-6">
										<a class="btn btn-success" style="padding-left:20px;"
											id="save-array" href="#">土地信息本地保存</a>
									</div>
								</div>

							</form>
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

</script>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.0/jquery-ui.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.5.0/lodash.min.js"></script>
	<script src="../dist/gridstack.js"></script>
	<script src="../dist/gridstack.jQueryUI.js"></script>
	<script src="../dist/bootbox.min.js"></script>
	<script src="../js/tu.js"></script>	
	<script src="../dist/jquery.cokie.min.js"></script>    
	<script src="../js/kg.js"></script>
	
	
</body>
</html>
