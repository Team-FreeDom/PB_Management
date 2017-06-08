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
<title>湖南农业大学基地实习综合管理系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Bootstrap -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/font-awesome.min.css">
<!--datatable-->
<link rel="stylesheet" href="../css/jquery.dataTables.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/Professionalmanage.css">
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

			<span class="teachCenterTitle">基地实习综合管理系统</span>
			<!-- Navbar Left -->

			<!-- Navbar Right -->
			<div class="navbar-right">
				<!-- Notifications -->
				<ul class="notifications" avalonctrl="subNotificationsController">
					<li class="hidden-sm hidden-xs"><a data-toggle="modal" data-target="#help"
						class="dropdown-toggle notification-icon"> <i
							class="glyphicon glyphicon-question-sign"><span class="badge"></span></i> <!--ms-if-->
					</a></li>
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
									${(visitRight[3]==0&&visitRight[4]==0&&visitRight[5]==0&&visitRight[6]==0&&visitRight[7]==0&&visitRight[8]==0&&visitRight[9]==0&&visitRight[10]==0&&visitRight[11]==0&&visitRight[13]==0)?"style='display:none;'":" "}>
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
											
										<li ${visitRight[13]==0?"style='display:none;'":" "}><a
											href="Professionalmanage.jsp"><span class="text"> 实习专业维护</span></a></li>
											
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
							<li><a href="RepairManage.jsp">实习专业管理</a></li>
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
						<form action="" method="post" enctype="multipart/form-data"
							name="formApplyInfo" id="formApplyInfo">
							<table id="major" class="cell-border" cellspacing="0"
								width="100%">
								<thead>
									<tr bgcolor="#ECF1F5">
									<td hidden id="exportMaintain">${exportMaintain}</td>
										<td colspan="3" id="button-left">
											<button type="button" class="btn btn-danger" id="delete">删除</button>
											<button type="button" class="btn btn-info"
												data-toggle="modal" data-target="#add" id="ZJ">增加</button>
										</td>										
										<td colspan="3" id="button-right">
<!-- 											<button type="button" id="import" class="btn btn-primary" -->
<!-- 												data-toggle="modal" data-target="#exportmodal">导出</button> -->
										</td>

									</tr>
									<tr>
										<th>序号</th>
										<th>学院名称</th>
										<th>专业编号</th>
										<th>专业名称</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody class="text-center">

								</tbody>
								<thead>
									<tr>
										<td colspan="11"><label><input type="checkbox"
												name="0" class="ck-all" id="ck1" />全选</label></td>
									</tr>

								</thead>
							</table>
						</form>
					</div>
				</div>


			</div>
			<!-- End Sidebar-->
			<!-- 弹出框-->
			<div class="modal fade" id="edit" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabe" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="border:#3071a9 8px solid">
						<div class="modal-header" style="background:#3071a9; color:#FFF">
							<div class="glyphicon glyphicon-remove closeModal"
								data-dismiss="modal"></div>
							<h4 class="modal-title text-center" id="myModalLabel">专业详情</h4>
						</div>
						<div class="modal-body table-responsive">
							<div class="row">
								<div class="col-md-12">
									<form action="updatemajor.do" method="post" class="form-horizontal"
										role="form" id="majoreditform">
										<table class="table" style="border:none !important;">
											<tr>
												<td>所属学院 ：</td>
												<td><input type="text" class="form-control"
													id="Mdept" name="Mdept" disabled/></td>
											</tr>
											<tr>
												<td>专业编号 ：</td>
												<td><input type="text" class="form-control"
													id="Mid" name="Mid" readonly/>
													</td>
											</tr>
											<tr>
												<td>专业名称 ：</td>
												<td><input type="text" class="form-control" id="Mname" name="Mname"
													/><span id="display1" style="color:#f00;"></span></td>
											</tr>
											
										</table>
									</form>
								</div>
							</div>
						</div>
						<div class="modal-footer table-responsive">
							<center>
								<button type="button" class="btn btn-primary" id="saverun">保存</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
							</center>
						</div>
					</div>
				</div>
			</div>


			<div class="modal fade" id="add" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabe" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="border:#3071a9 8px solid">
						<div class="modal-header" style="background:#3071a9; color:#FFF">
							<div class="glyphicon glyphicon-remove closeModal"
								data-dismiss="modal"></div>
							<h4 class="modal-title text-center" id="myModalLabel">新增专业信息</h4>
						</div>
						<div class="modal-body table-responsive">
							<div class="row">
								<div class="col-md-12">
									<form action="addmajor.do" method="post"
										class="form-horizontal" enctype="multipart/form-data"
										role="form" id="applyaddform">
										<table class="table" style="border:none !important;">
<!-- 											<tr> -->
<!-- 												<td>项目名称 ： <span class="setTag">*</span><input type="hidden" -->
<!-- 											value="<%=request.getAttribute("index")%>" id="AddrepairInfo" /></td> -->
<!-- 												<td><input type="text" class="form-control" -->
<!-- 													id="Aprojectname" name="Aprojectname" value=""></td> -->
<!-- 											</tr> -->
											<tr>
												<td>学院名称 ： <span class="setTag">*</span></td>
												<td><select class="form-control" id="deptSelectOne1"
													name="deptSelectOne1" value="">
														<option id="deptSelect1" value="" selected>请选择</option>
												</select></td>
											</tr>
											<tr>
												<td>新增专业编号： <span class="setTag">*</span>
												</td>
												<td><input type="text" class="form-control" id="mid"
													name="mid" value=""><span id="display"
												style="color:#f00;"></span></td>
												<input type="hidden" value="<%=request.getAttribute("flag")%>" id="baseapply" />
											</tr>
											<tr>
												<td>新增专业名称：<span class="setTag"> *</td>
												<td><input type="text" class="form-control" id="mname"
													name="mname" value=""><span id="display2"
												style="color:#f00;"></span></td>
											</tr>

										</table>
									</form>
								</div>
							</div>
						</div>
						<div class="modal-footer table-responsive">
							<center>
								<button type="button" class="btn btn-primary" id="save">保存</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
							</center>
						</div>
					</div>
				</div>
			</div>




		</div>
		<!--row end-->
	</div>
	
	<div class="modal fade bs-example-modal-sm" id="help" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header" style="background:#3071a9; color:#FFF">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title text-center" id="myModalLabel">演示视频</h4>
      </div>
      <div class="modal-body text-center">
 	    <div class="row">   
    		<div class="col-md-12 helpcolor"><a href="../audio/userMedia.rar">普通用户功能演示视频</a></div>
  	   </div>
  	   <div class="row" style="margin-top:20px;">
  	 		 <div class="col-md-12 helpcolor"><a href="../audio/collegeMedia.rar">学院负责人功能演示视频</a></div>  
  		</div>
      </div>
    </div>
  </div>
</div>

	<div class="clearfix"></div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../js/jquery.min.js"></script>
	<!--datatable javascript-->
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/jquery.dataTables.min.js"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/bootbox.min.js"></script>
	<script type="text/javascript" src="../js/laydate.js"></script>
	<script src="../js/myNeed/Professionalmanage.js"></script>
	<script src="../js/kg.js"></script>
	<script type="text/javascript">
		var flag = document.getElementById('baseapply').value;
		if (flag == 1) {
			bootbox.alert({
				message : "操作成功",
				size : 'small'
			});
		} else if (flag == 0) {
			bootbox.alert({
				message : "操作失败",
				size : 'small'
			});
		}
	</script>

</body>
</html>
