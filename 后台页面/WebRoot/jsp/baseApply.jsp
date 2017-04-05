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
<title>基地申请</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Bootstrap -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/font-awesome.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/practicebaseapply.css">

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
					</a>
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

								<li class="menuItem nav-parent opened nav-expanded"><a> <i
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
				<div class="page-header row">
					<div class="pull-left">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a>位置 :</a></li>
							<li><a href="baseApply.jsp"><i class=" icon-home"></i>基地申报</a></li>
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

						<div class=" col-md-offset-2 col-md-8 bordor-style">
							<div class="row text-center interval padding-style">
								<p>基地申请</p>
							</div>
							<div class="row padding-style">

								<form action="getRequestBaseInfo.do" method="post" id="myForm"
									enctype="multipart/form-data" class="form-horizontal"
									role="form">
									<div class="form-group">
										<input type="hidden"
											value="<%=request.getAttribute("index")%>" id="baseapply" />
										<label class="col-md-3 control-label fontDire">基地名称<span
											class="setTag">*</span></label>
										<div class="col-md-6">
											<input type="text" class="form-control" id="basename"
												name="name" placeholder="例:浏阳基地" /> <span id="display"
												style="color:#f00;"></span>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">申报部门类型<span
											class="setTag">*</span></label>
										<div class="col-md-6">
											<label class="radio-inline"> <input type="radio"
												name="deptRadio" id="deptRadio" value="1" checked="true">
												学院
											</label> <label class="radio-inline"> <input type="radio"
												name="deptRadio" id="deptRadio" value="2"> 行政部
											</label>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">申报部门<span
											class="setTag">*</span></label>
										<div class="col-md-6">
											<select class="form-control" id="deptty" name="applyid">
												<option id="applyDept" value="">请选择</option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">基地类型<span
											class="setTag">*</span></label>
										<div class="col-md-6">
											<select class="form-control" id="basetype0" name="typeid">
												<option id="basetype" value="">请选择</option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">面向专业</label>
										<div class="col-md-6" data-toggle="modal"
											data-target="#myModal" id="hit">
											<span class="btn btn-primary">请选择</span>
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-offset-3 col-md-6">
											<div id="textContent"
												style="border:#ccc 1px solid;height:100px;"></div>

										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">可承担人数</label>
										<div class="col-md-6">
											<input type="text" class="form-control" name="undertake"
												id="limit-population" placeholder="单位：人">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">土地面积</label>
										<div class="col-md-6">
											<input type="text" class="form-control" id="filed-area"
												name="landarea" placeholder="单位：亩">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">建筑面积</label>
										<div class="col-md-6">
											<input type="text" class="form-control" id="base-area"
												name="constructionarea" placeholder="单位：平方">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">通信地址<span
											class="setTag">*</span></label>
										<div class="col-md-6">
											<input type="text" class="form-control" id="baseaddress"
												name="land_addres" placeholder="">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">法定责任人<span
											class="setTag">*</span></label>
										<div class="col-md-6">
											<input type="text" class="form-control" id="lawPerson"
												name="lawPerson" placeholder="">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">联系人姓名<span
											class="setTag">*</span></label>
										<div class="col-md-6">
											<input type="text" class="form-control" id="personName"
												name="username" placeholder="">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">联系人电话<span
											class="setTag">*</span></label>
										<div class="col-md-6">
											<input type="text" class="form-control" id="personTel"
												name="phone" placeholder="">
										</div>
									</div>

									<div class="form-group">

										<label class="col-md-3 control-label">申请材料</label>
										<div class="col-md-6">
											<input type="file" id="applyfile" placeholder=""
												name="material_path">
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-offset-8 col-sm-4">
											<button id="submitForm" type="button" class="btn btn-primary">提交</button>
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
		<div class="modal-dialog">
			<div class="modal-content" style="border:#416793 8px solid">
				<div class="modal-header">
					<button type="button" class="close closeit" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
					</button>

				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-md-3 control-label fontDire">学院:</label>

							<div class="col-md-5">
								<select class="form-control" id="deptSelectOne">
									<option id="deptSelect" value="">请选择</option>
								</select>
							</div>

						</div>
						<div class="form-group ">
							<label class="col-md-3 control-label fontDire">可选专业:</label>
							<div class="col-md-6 majorhide">
								<!-- <span class="majorcheck"><input type='checkbox' id='majorcheck' value='1' class='计算机'/><label>计算机</label>	</span>
									<span class="majorcheck"><input type='checkbox' id='majorcheck' value='1' class='电商'/><label>电商</label>	</span> -->
							</div>

						</div>
						<div class="form-group" id="majormain">
							<label class="col-md-3 control-label fontDire">已选专业:</label>
							<div class="col-md-6 " id="majorSuo"></div>
						</div>
					</form>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary confirm"
							data-dismiss="modal">确定</button>

					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../js/jquery.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/bootbox.min.js"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/myNeed/baseapply.js"></script>
	<script src="../js/kg.js"></script>
	<script type="text/javascript">
		var index = document.getElementById('baseapply').value;
		if (index == 1) {
			bootbox.alert({
				message : "提交成功",
				size : 'small'
			});
		}
	</script>
</body>

</html>
