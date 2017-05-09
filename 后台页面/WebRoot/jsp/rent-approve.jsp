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
<link rel="stylesheet" href="../css/rent-approve.css">
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
				<div class="page-header">
					<div class="pull-left">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a>位置</a></li>
							<li><a href="rent-approve.jsp"></i>租赁审批</a></li>
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
										role="tab" data-toggle="tab" id="NoCheck">未审核</a></li>
									<li role="presentation"><a href="#profile" role="tab"
										data-toggle="tab" id="PayM">交费中</a></li>
									<li role="presentation"><a href="#overdue" role="tab"
										data-toggle="tab" id="overdue1">逾期</a></li>
								</ul>
							</div>
						</div>

					</div>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active col-md-12" id="home">
							<form action="" method="post" enctype="multipart/form-data"
								name="formCheck" id="formCheck">
								<!-- onSubmit="return check1()" -->
								<table id="tableCheck" class="hover" cellspacing="0"
									width="100%">
									<thead>
										<tr bgcolor="#ECF1F5">

											<td colspan="8">
												<ul class="dropdown_show">
													<li><span class="icon-filter">筛选</span>
														<ul class="hide_ul" style="display:none">
															<li>
																<form>
																	<table class="table">
																		<tr>
																			<td>基地名： <select name="bname" id="basenameid">
																					<option value="" id="selectallbase" class="aa">显示全部</option>
																					<!--  <option value="">耘园科教综合基地</option>
                                                                      <option value="">西南角农学基地</option>	 -->
																			</select>  
																			</td>
																			<td>申请人: <select name="username" id="usernameid"
																				class="bb">
																					<option value="" id="applicantId">显示全部</option>
																					<!--  <option value="">李彩</option>
                                                                      <option value="">彭心雨</option>	 -->
																			</select>
																			</td>


																		</tr>
																		<tr>
																			<td colspan="2">学院: <select name="dept"
																				id="dept" class="cc">
																					<option value="" id="selectdept">显示全部</option>
																					<!--  <option value="">信息科学技术学院</option>
                                                                          <option value="">动科院</option>	 -->
																			</select>  
																			</td>
																		</tr>
																		<tr>
																			<td colspan="3">
																				<button type="reset" class="btn btn-primary">重置</button>
																				<button id="finish" type="button"
																					class="btn btn-primary">完成</button>
																			</td>

																		</tr>
																	</table>
																</form>
															</li>
														</ul></li>
												</ul>
											</td>
											<td colspan="2">
												<!-- <button type="button" class="btn btn-primary">导出</button> -->
											</td>
										</tr>
										<tr style="background:#eeeff4">

											<th>序号</th>
											<th>开始日期</th>
											<th>结束日期</th>
											<th>基地名</th>
											<th>土地编号</th>
											<th>土地名称</th>
											<th>申请人</th>
											<th>申报部门</th>
											<th>已租用次数</th>
											<th>种植内容</th>

										</tr>
									</thead>


									<tbody>

									</tbody>
									<thead>
										<tr>
											<td colspan="2"><label><input type="checkbox"
													name="0" id="ck1" />全选</label></td>
											<td colspan="8">
												<button type="button" class="btn btn-primary" id="agreeOne"
													name="agreeOne">同意申请</button>
												<button type="button" class="btn btn-danger" id="deleteOne"
													name="deleteOne">拒绝申请</button>
											</td>
										</tr>
									</thead>
								</table>
							</form>
						</div>

						<div class="modal fade" id="scan" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content" style="border:#3071a9 8px solid">
									<div class="modal-header"
										style="background:#3071a9; color:#FFF">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										<h4 class="modal-title text-center" id="myModalLabel">土地租赁详情</h4>
									</div>
									<div class="modal-body table-responsive">
										<table class="table">
											<tr>
												<td>基地名 ：</td>
												<td><input type="text" id="basename" readonly></td>
												<td>租赁人 ：</td>
												<td><input type="text" id="username"></td>
											</tr>
											<tr>
												<td>申请学院 ：</td>
												<td><input type="text" id="usercollage" readonly></td>
												<td>面向专业 ：</td>
												<td><input type="text" id="landoriented"></td>
											</tr>


											<tr>
												<td>土地名称 ：</td>
												<td><input type="text" id="landname" readonly></td>
												<td>土地编号 ：</td>
												<td><input type="text" id="li"></td>
											</tr>

											<tr>
												<td>计划种植内容 ：</td>
												<td><input type="text" id="plant" readonly></td>
											</tr>
										</table>
									</div>
									<div class="modal-footer table-responsive">
										<center>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">确定</button>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
										</center>
									</div>
								</div>
							</div>
						</div>

						<div role="tabpanel" class="tab-pane col-md-12" id="profile">
							<form action="" method="post" enctype="multipart/form-data"
								name="formPay" id="formPay">
								<table id="tablePay" class="hover" cellspacing="0" width="100%">
									<thead>
										<tr bgcolor="#ECF1F5">

											<td colspan="8">
												<ul class="dropdown_show">
													<li><span class="icon-filter">筛选</span>
														<ul class="hide_ul" style="display:none;">
															<li>
																<form>
																	<table class="table">
																		<tr>
																			<td>基地名： <select name="bname2" id="basenameid2">
																					<option value="" id="selectallbase2" class="aa">显示全部</option>
																					<!--  <option value="">耘园科教综合基地</option>
                                                                      <option value="">西南角农学基地</option>	 -->
																			</select>  
																			</td>
																			<td>申请人: <select name="username2"
																				id="usernameid2">
																					<option value="" id="applicantId2" class="bb">显示全部</option>
																					<!--  <option value="">李彩</option>
                                                                      <option value="">彭心雨</option>	 -->
																			</select>
																			</td>
																			<td>学院: <select name="dept2" id="dept2">
																					<option value="" id="selectdept2" class="cc">显示全部</option>
																					<!--  <option value="">信息科学技术学院</option>
                                                                          <option value="">动科院</option>	 -->
																			</select>  
																			</td>
																		</tr>

																		<tr>
																			<td colspan="3">
																				<button type="reset" class="btn btn-primary">重置</button>
																				<button id="finishPay" type="button"
																					class="btn btn-primary">完成</button>
																			</td>

																		</tr>
																	</table>
																</form>
															</li>
														</ul></li>
												</ul>
											</td>
											<td colspan="2">
												<!-- <button type="button" class="btn btn-primary">导出</button> -->
											</td>
										</tr>
										<tr style="background:#eeeff4">
											<th>序号</th>
											<th>开始日期</th>
											<th>结束日期</th>
											<th>基地名</th>
											<th>土地编号</th>
											<th>土地名称</th>
											<th>申请人</th>
											<th>申报部门</th>
											<th>已租用次数</th>
											<th>种植内容</th>

										</tr>
									</thead>

									<tbody>

									</tbody>
									<thead>
										<tr>
											<td colspan="2"><label><input type="checkbox"
													name="0" id="ck2" />全选</label></td>
											<td colspan="8">
												<button type="button" class="btn btn-primary" id="confim">确认交费</button>
												<button type="button" class="btn btn-danger" id="cancel">取消交费</button>
											</td>
										</tr>
									</thead>
								</table>
							</form>
						</div>


						<div role="tabpanel" class="tab-pane" id="overdue">
							<form action="" method="post" enctype="multipart/form-data"
								name="formCheck" id="formCheck">
								<!-- onSubmit="return check1()" -->
								<table id="tabledue" class="hover" cellspacing="0" width="100%">
									<thead>
										<tr bgcolor="#ECF1F5">

											<td colspan="8">
												<ul class="dropdown_show">
													<li><span class="icon-filter">筛选</span>
														<ul class="hide_ul" style="display:none">
															<li>
																<form>
																	<table class="table">
																		<tr>
																			<td>基地名： <select name="bname" id="basenameid3"
																				class="aa">
																					<option value="" id="selectallbase3">显示全部</option>
																					<!--  <option value="">耘园科教综合基地</option>
                                                                      <option value="">西南角农学基地</option>	 -->
																			</select>  
																			</td>
																			<td>申请人: <select name="username"
																				id="usernameid3" class="bb">
																					<option value="" id="applicantId3">显示全部</option>
																					<!--  <option value="">李彩</option>
                                                                      <option value="">彭心雨</option>	 -->
																			</select>
																			</td>


																		</tr>
																		<tr>
																			<td colspan="2">学院: <select name="dept"
																				id="dept3" class="cc">
																					<option value="" id="selectdept3">显示全部</option>
																					<!--  <option value="">信息科学技术学院</option>
                                                                          <option value="">动科院</option>	 -->
																			</select>  
																			</td>
																		</tr>
																		<tr>
																			<td colspan="3">
																				<button type="reset" class="btn btn-primary">重置</button>
																				<button id="finishdue" type="button"
																					class="btn btn-primary">完成</button>
																			</td>

																		</tr>
																	</table>
																</form>
															</li>
														</ul></li>
												</ul>
											</td>
											<td colspan="2">
												<!-- <button type="button" class="btn btn-primary">导出</button> -->
											</td>
										</tr>
										<tr style="background:#eeeff4">

											<th>序号</th>
											<th>开始日期</th>
											<th>结束日期</th>
											<th>基地名</th>
											<th>土地编号</th>
											<th>土地名称</th>
											<th>申请人</th>
											<th>申报部门</th>
											<th>已租用次数</th>
											<th>种植内容</th>

										</tr>
									</thead>


									<tbody>

									</tbody>
									<thead>
										<tr>
											<td colspan="2"><label><input type="checkbox"
													name="0" id="ck3" />全选</label></td>
											<td colspan="8">
												<button type="button" class="btn btn-primary"
													id="overdueOne" name="overdueOne">恢复期限</button>

											</td>
										</tr>
									</thead>
								</table>
							</form>
						</div>


					</div>
					<div class="modal fade" id="scan2" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="border:#3071a9 8px solid">
								<div class="modal-header" style="background:#3071a9; color:#FFF">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title text-center" id="myModalLabel">土地租赁详情</h4>
								</div>
								<div class="modal-body table-responsive">
									<table class="table">
										<tr>
											<td>基地名 ：</td>
											<td><input type="text" id="basename1" readonly></td>
											<td>租赁人 ：</td>
											<td><input type="text" id="username1"></td>
										</tr>
										<tr>
											<td>申请学院 ：</td>
											<td><input type="text" id="usercollage1" readonly></td>
											<td>面向专业 ：</td>
											<td><input type="text" id="landoriented1"></td>
										</tr>


										<tr>
											<td>土地名称 ：</td>
											<td><input type="text" id="landname1" readonly></td>
											<td>土地编号 ：</td>
											<td><input type="text" id="li"></td>
										</tr>

										<tr>
											<td>计划种植内容 ：</td>
											<td><input type="text" id="plant1" readonly></td>
										</tr>
									</table>
								</div>
								<div class="modal-footer table-responsive">
									<center>
										<button type="button" class="btn btn-primary"
											data-dismiss="modal">确定</button>
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
									</center>
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
	<!--[if lt IE 9]>
		<script src="../js/html5shiv.min.js"></script>
		<script src="../js/respond.min.js"></script>
	<![endif]-->
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/jquery.dataTables.min.js"></script>
	<script src="../js/bootbox.min.js"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/myNeed/rent-approve.js"></script>
	<script src="../js/kg.js"></script>
	<script>
		/* new YMDselect('year1','month1','day1');
		new YMDselect('year2','month2','day2'); */
	</script>

</body>
</html>
