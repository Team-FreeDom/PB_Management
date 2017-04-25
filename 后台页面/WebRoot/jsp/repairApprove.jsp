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
<link rel="stylesheet" href="../css/Repairapprove.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
      
    <![endif]-->
<style>
#laydate_ys {
	width: 150px;
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
					</a> </li>
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

								<li class="menuItem nav-parent opened nav-expanded"
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
							<li><a href="repairApprove.jsp"><i class=" icon-home"></i>维修审批</a></li>
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

						<div id="rent_nav" class="row">
							<div class="col-md-10 col-xs-12">
								<ul class="nav nav-tabs" role="tablist">
									<li role="presentation" class="active"><a href="#home"
										role="tab" data-toggle="tab">审核中</a></li>
									<li role="presentation"><a href="#profile" role="tab"
										data-toggle="tab">维修中</a></li>
								</ul>
							</div>
						</div>

					</div>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active col-md-12" id="home">
							<form action="" method="post" id="ApproveingformInfo">
								<table id="Approveing" class="hover" cellspacing="0"
									width="100%">
									<thead>
										<tr bgcolor="#ECF1F5">
											<td colspan="4">
												<ul class="dropdown_show">
													<li><span class="icon-filter">筛选</span>
														<ul class="hide_ul" style="display:none;">
															<li>
																<form>
																	<table class="table">
																		<tr>
																			<td colspan="2">基地名称： <select id="searchbase"
																				style="width:150px;">
																					<option value="" id="searchbaseid" selected>全部</option>
																			</select>
																			</td>
																			<td colspan="2">申报人: <select id="searchname"
																				style="width:150px;">
																					<option value="" id="searchnamed" selected>全部</option>
																			</select>
																			</td>
																		</tr>
																		<tr>
																			<td colspan="4">
																				<button type="reset" class="btn btn-primary">重置</button>
																				<button type="button" id="finish"
																					class="btn btn-primary">完成</button>
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
											<th></th>
											<th>项目名称</th>
											<th>基地名称</th>
											<th>报修人</th>
											<th>报修时间</th>
											<th>预算金额</th>
											<th hidden>报修人编号</th>
											<th hidden>具体地址</th>
											<th hidden>原因说明</th>
											<th hidden>材料地址</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
									<thead>
										<tr>
											<td colspan="3"><label><input type="checkbox"
													name="0" class="ck-all ck1" id="" />全选</label></td>
											<td colspan="4" style="text-align:left;">
												<button type="button" class="botton btn btn-primary btn-sm"
													id="agree">同意申请</button>
												<button type="button" class="botton btn btn-danger btn-sm"
													id="refuse">拒绝申请</button>
											</td>
										</tr>
									</thead>
								</table>
							</form>
						</div>

						<div class="modal fade" id="refuseModal" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content" style="width:800px">
									<div class="modal-header">
										<div class="glyphicon glyphicon-remove closeModal"
											data-dismiss="modal"></div>
										<h4 class="modal-title" id="myModalLabel">拒绝详情</h4>
									</div>
									<div class="modal-body" id="modalbody">
										<table id="refusetable" class="table">

										</table>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary" id="saverefuse">保存</button>
									</div>
								</div>
							</div>
						</div>
						<div class="modal fade" id="Checkdetal" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content" style="border:#3071a9 8px solid">
									<div class="modal-header"
										style="background:#3071a9; color:#FFF">
										<div class="glyphicon glyphicon-remove closeModal"
											data-dismiss="modal"></div>
										<h4 class="modal-title text-center" id="myModalLabel">维修中详情</h4>
									</div>
									<div class="modal-body table-responsive">
										<div class="row">
											<div class="col-md-12">
												<table class="table" style="border:none !important;">
													<tr>
														<td>项目名称 ：</td>
														<td><input type="text" class="form-control" value=""
															disabled id="projectname"></td>
													</tr>
													<tr>
														<td>基地名称 ：</td>
														<td><input type="text" class="form-control"
															id="basename" disabled></td>
													</tr>
													<tr>
														<td>报修人 ：</td>
														<td><input type="text" class="form-control" id="name"
															disabled></td>
													</tr>
													<tr>
														<td>申报时间 ：</td>
														<td><input type="text" class="form-control" id="time"
															disabled></td>
													</tr>
													<tr>
														<td>预算金额 ：</td>
														<td><input type="text" class="form-control"
															id="budget" disabled></td>
													</tr>
													<tr>
														<td>具体位置 ：</td>
														<td><input type="text" class="form-control"
															id="address" disabled></td>
													</tr>
													<tr>
														<td>原因说明 ：</td>
														<td><textarea class="form-control" id="reason"
																disabled></textarea></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
									<div class="modal-footer table-responsive">
										<center id="resourcetr">
											申请材料：<a id="linkaddress" href=""
												style="color:#00F; text-decoration:underline; font-size:16px;">点击查看</a>
										</center>
										<button type="button" class="btn btn-primary"
											data-dismiss="modal">关闭</button>
									</div>
								</div>
							</div>
						</div>

						<div role="tabpanel" class="tab-pane col-md-12" id="profile">
							<table id="Repairing" class="hover" cellspacing="0" width="100%">
								<thead>
									<tr bgcolor="#ECF1F5">
										<td colspan="4">
											<ul class="dropdown_show">
												<li><span class="icon-filter" onClick="showsubmenu()">筛选</span>
													<ul class="hide_ul" style="display:none;">
														<li>
															<form>
																<table class="table">
																	<tr>
																		<td colspan="2">基地名称： <select id="searchbase2"
																			style="width:150px;">
																				<option value="" id="searchbaseid2" selected>全部</option>
																		</select>
																		</td>
																		<td colspan="2">报修人： <select id="searchname2"
																			style="width:150px;">
																				<option value="" id="searchnamed2" selected>全部</option>
																		</select>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="4">
																			<button type="reset" class="btn btn-primary">重置</button>
																			<button type="button" id="finish2"
																				class="btn btn-primary">完成</button>
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
										<th></th>
										<th>项目名称</th>
										<th>基地名称</th>
										<th>报修人</th>
										<th>报修时间</th>
										<th>预算金额</th>
										<th hidden>报修人编号</th>
										<th hidden>具体地址</th>
										<th hidden>原因说明</th>
										<th hidden>材料地址</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
								<thead>
									<tr>
										<td colspan="3"><label><input type="checkbox"
												name="0" class="ck-all ck2" />全选</label></td>
										<td colspan="4">
											<button type="button" class=" botton btn btn-primary"
												id="finished">维修完成</button>
										</td>
									</tr>
								</thead>
							</table>
						</div>
					</div>


				</div>
			</div>
			<!-- End Sidebar-->


			<!--点击完成按钮弹出的模态框-->
			<div class="modal fade" id="myfinishedModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="width:800px">
						<div class="modal-header">
							<div class="glyphicon glyphicon-remove closeModal"
								data-dismiss="modal"></div>
							<h4 class="modal-title">完成详情</h4>
						</div>
						<div class="modal-body">
							<table id="finishtable" class="table">

							</table>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="storeInfo">保存</button>
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
	<script src="../js/jquery.dataTables.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/laydate.js"></script>
	<script src="../js/bootbox.min.js"></script>
	<script src="../js/myNeed/Repairapprove.js"></script>
	<script src="../js/kg.js"></script>
	<script>
		!function() {
			laydate.skin('yalan');//切换皮肤，请查看skins下面皮肤库
			laydate({
				elem : '#demo'
			});//绑定元素
			laydate({
				elem : '#demo2'
			});
		}();
	</script>
</body>
</html>
