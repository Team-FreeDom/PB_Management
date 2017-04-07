<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<link rel="stylesheet" href="../css/font-awesome.min.css">
<link rel="stylesheet" href="../dist/gridstack.css" />
<!--<link rel="stylesheet" href="../css/tu.css">-->
<link rel="stylesheet" href="../css/style.css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!--[if lt IE 9]>
	<script src="../js/html5shiv.min.js"></script>
	<script src="../js/respond.min.js"></script>
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

								<li class="menuItem nav-parent opened nav-expanded"
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
							<li><a href="land_modle.jsp"><i class=" icon-home"></i>土地布局设置</a></li>
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
				<div class="row">
					<div class=" col-md-4">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-4 control-label">选择区域：</label>
								<div class="col-sm-8">
									<select id="load-grid" class="form-control">
										<option value="" id="load-gridh">请选择</option>
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
					<div class="col-md-4"></div>
				</div>
				<!-- 土地布局 -->
				<div class="row">
					<div class="col-md-4">
						<div class="btn-group" style="padding:0px 0px 5px 20px;">
							<a class="btn btn-default" id="girdin"><span
								class="glyphicon glyphicon-zoom-in"></span></a> <a
								class="btn btn-default" id="girdout"><span
								class="glyphicon glyphicon-zoom-out"></span></a>
						</div>
					</div>
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<p class="lead text-center bg-primary">土地信息设置区</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8" style="max-width:1500px;overflow-x:scroll;">

						<div class="grid-stack" id="gridmouse"></div>
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
									<label for="inputname" class="col-sm-3 control-label">推荐学院：</label>
									<div class="col-sm-6">
										<select class="form-control input-sm" id="tudi_aptCollege"
											name="tudi_aptCollage">
											<option value="" id="tudi_scollegeh">请选择</option>
											<option value="0">生物科学技术学院</option>
											<option value="1">资源环境学院</option>
											<option value="2">动物科学技术学院</option>
											<option value="3">农学院</option>
											<option value="4">动物医学院</option>
											<option value="5">园艺园林学院</option>
											<option value="6">植物保护学院</option>
											<option value="7">其他</option>
										</select>
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
											placeholder="单位：亩">
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
									<div class="col-sm-9">
										<label for="inputname" class="control-label"
											style="float:left;">土地效果图：</label> <label
											style="float:right;" for="file"><a href="#"
											id='upimg' style="color:#5cb85c;text-decoration:underline;">上传图片</a>
										</label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9">
										<input hidden="hidden" type="file" name="imgfile" id="imgfile"
											style="position:absolute;clip:rect(0 0 0 0);">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9" id="preview" data-toggle="modal"
										data-target="#myModal">
										<a href="#"> <img id="imghead"
											class="bk-img-60 specialOne" border=0 src="" alt=" ">
										</a>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9">
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

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:750px;height:700px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">土地效果图</h4>
				</div>
				<div class="modal-body">
					<center>
						<img src="" id="belowImg" class="special" />
					</center>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="clearfix"></div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../js/jquery.min.js"></script>
	<script src="../js/jquery-ui.js"></script>
	<script src="../dist/jquery.mousewheel.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/lodash.min.js"></script>
	<script src="../dist/gridstack.js"></script>
	<script src="../dist/gridstack.jQueryUI.js"></script>
	<script src="../dist/bootbox.min.js"></script>
	<script src="../dist/ajaxfileupload.js" type="text/javascript"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/tu.js"></script>
	<script src="../js/kg.js"></script>

</body>
</html>
