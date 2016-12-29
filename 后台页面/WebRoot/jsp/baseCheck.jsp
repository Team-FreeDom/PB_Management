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

			<span class="teachCenterTitle">基地管理系统</span>
			<!-- Navbar Left -->

			<!-- Navbar Right -->
			<div class="navbar-right">
				<!-- Notifications -->
				<ul class="notifications" avalonctrl="subNotificationsController">
					<li class="hidden-sm hidden-xs">
					 <a href="getMessage.do" class="dropdown-toggle notification-icon">
                          <i class="icon-envelope"><span class="badge msg"></span></i>
                              <!--ms-if-->
                      </a>
						<!-- <ul class="dropdown-menu">
							<li class="dropdown-header" style="text-align: center;"><strong>未读消息列表</strong>
							</li>
							<li class="dropdown-menu-footer text-center"><a
								href="../teach/notifications.html">更多消息</a></li>
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
						<a href="#" target="_blank"><img
							src="../image/manage-logo.png" alt=""></a>
					</div>

					<!-- Sidebar Menu-->
					<div class="sidebar-menu" style="height: 384px;">
						<nav id="menu" class="nav-main" role="navigation">
							<ul class="nav nav-sidebar">
								<div class="panel-body text-center">
									<div class="bk-avatar">
										<a href="user.jsp"><img class="img-circle bk-img-60" alt=""
											src="" id="imageMain"></a>
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
										<li><a href="myRent.jsp"><span class="text">我的租赁</span></a></li>
										<li><a href="#"><span class="text">我的实习</span></a></li>
										<li><a href="#"><span class="text">我的报修</span></a></li>
										<li><a href="myBase.jsp"><span class="text">我的基地</span></a></li>
									</ul></li>

								<li class="menuItem nav-parent opened nav-expanded"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>审批工作</span>
								</a>
									<ul class="nav nav-children">
                                            <li><a href="rent-approve.jsp"><span class="text">租赁审批</span></a></li>
                                            <li><a href="#"><span class="text">实习审批</span></a></li>
                                            <li><a href="baseCheck.jsp"><span class="text">基地审批</span></a></li>
                                            <li><a href="#"><span class="text">维修审批</span></a></li>

										</ul>
								<li class="menuItem nav-parent"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>数据管理</span>
								</a>
									<ul class="nav nav-children">
                                        	<li><a href="notification.do"><span class="text"> 发布通知公告</span></a></li>
                                        	<li><a href="land_modle.jsp"><span class="text"> 土地布局设置</span></a></li>
                                            <li><a href="fieldRent_maintain.jsp"><span class="text"> 土地租赁维护</span></a></li>
                                            <li><a href="baseMaintain.jsp"><span class="text"> 实习基地维护</span></a></li>
                                            <li><a href="#"><span class="text"> 实习计划维护</span></a></li>
                                             <li><a href="start.jsp"><span class="text"> 工作计划制定</span></a></li>
                                            <li><a href="mangeruser.jsp"><span class="text"> 系统用户维护</span></a></li>
                                            <li><a href="system_power.jsp"><span class="text"> 系统权限设置</span></a></li>
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
							<li><a href="baseCheck.jsp"></i>基地审批</a></li>
						</ol>
					</div>
					<div class="pull-right">
							<ol class="breadcrumb visible-sm visible-md visible-lg wz">
								<li><a href="baseApply.jsp"><i class=" icon-building"></i>基地申报</a></li>
                                <li><a href="field-rent.jsp"><i class="icon-legal"></i>土地租赁</a></li>
                                <li><a href="#"><i class="icon-user"></i>实习申请</a></li>
                                <li><a href="#"><i class="icon-home"></i>报修申请</a></li>
							</ol>
						</div>
				</div>
				<!-- 主面板内容 -->
				<div class="row form">
									
					<div class="col-lg-12">
						<form action="deleteLandRentInfo.do" method="post" id="deleteInfo">
							<table id="basecheck" class="cell-border"
								cellspacing="0" width="100%">
								<thead>
									<tr bgcolor="#ECF1F5">
										<td colspan="5">
											<ul class="dropdown_show">
												<li><span style="color:#24354a" class="icon-filter">筛选</span>
													<ul id="hide_ul" style="display:none;">
														<li>
															<form action="submitChoose.do" method="post"
																id="submitChoose">
																<table class="table">
																	<tr>																		
																		<td>申报部门：
																		  <select name="deptS" id="deptSh" style="width:150px;">
																			<option value="" id="deptS" >显示全部</option>
																		  </select>
																		</td>
																	</tr>																	
																	<tr>
																		<td>
																		   <button type="reset" class="btn btn-primary">重置</button>
																		   <button type="button"
																				id="submitS" class="btn btn-primary">完成</button>
																		</td>
																	</tr>
																</table>
															</form>
														</li>
													</ul>
													</li>
											</ul>
										</td>
										<td colspan="9">										
										</td>										
									</tr>
									<tr style="background:#eeeff4" width="100%">
										<th></th>
										<th>基地名称</th>
										<th>基地类型</th>
										<th>申报部门</th>										
										<th>土地面积</th>
										<th>建筑面积</th>
										<th hidden>通信地址</th>
										<th>联系人姓名</th>
										<th>联系人电话</th>
										<th hidden>面向专业</th>
										<th hidden>可承担人数</th>
										<th hidden>申请材料</th>
										<th hidden>申请人</th>
										<th>详情</th>
										
									</tr>
								</thead>
								<tbody>
								 
								</tbody>
								<thead>
										<tr>
											<td colspan="2"><label><input type="checkbox"
													name="0" id="ck1" />全选</label></td>
											<td colspan="8">
												<button type="button" class="btn btn-primary" id="confirm">同意申请</button>
												<button type="button" class="btn btn-danger"  id="refuse">拒绝申请</button>
											</td>
										</tr>
									</thead>

							</table>
						</form>
					</div>
				
					
			<!-- 同意申请 -->
			<div class="modal fade" id="applyConfirm" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-sm" style="width:600px;">
					<div class="modal-content" style="border:#3071a9 8px solid">
						<div class="modal-header" style="background:#3071a9; color:#FFF">
							<button type="button" class="close" id="close1" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h5 class="modal-title" id="myModalLabel" style="text-align:left;">同意申请</h5>
							
						</div>
						<div class="modal-body table-responsive" id="add" >
						<table id="increase1" class="table">
						</table>
						 
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close1"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="confirmDate">确定</button>
						</div>
					</div>
				</div>
			</div>
					
				<!-- Modal -->
			<div class="modal fade" id="reasonConfirm" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-sm" style="width:600px;">
					<div class="modal-content" style="border:#3071a9 8px solid">
						<div class="modal-header" style="background:#3071a9; color:#FFF">
							<button type="button" class="close" id="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h5 class="modal-title" id="myModalLabel" style="text-align:left;">拒绝申请</h5>
							
						</div>
						<div class="modal-body table-responsive">
						<table class="table" id="increase2">
						</table>
						  
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="certain">确定</button>
						</div>
					</div>
				</div>
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
												<td>基地名称 ：</td>
												<td><input type="text" id="basename" disabled></td>
												<td>基地类型 ：</td>
												<td><input type="text" id="basetype" disabled ></td>
											</tr>
											<tr>
												<td>申请部门 ：</td>
												<td><input type="text" id="dept0" disabled></td>
												<td>土地面积 ：</td>
												<td><input type="text" id="landarea"  disabled/></td>
											</tr>


											<tr>
												<td>建筑面积 ：</td>
												<td><input type="text" id="buildingarea" disabled></td>
												<td>可承担人数 ：</td>
												<td><input type="text" id="undertakeCount" disabled></td>
											</tr>
											<tr>
												<td>联系人姓名 ：</td>
												<td><input type="text" id="username" disabled></td>
												<td>联系人电话 ：</td>
												<td><input type="text" id="userphone" disabled></td>
											</tr>
											<tr>
												<td>面向专业 ：</td>
												<td colspan="3"><div id="major_oriented" style="border:#ccc 1px solid;height:80px;"></div></td>
												
											</tr>
											
											<tr>
												<td>通信地址 ：</td>
												<td colspan="3"><div id="linkAddress" style="border:#ccc 1px solid;height:80px;"></div></td>
												
											</tr>

											
											<tr>
												<td>申请材料 ：</td>
												<td colspan="3"><a id="resource" href="#" style="color:#3071a9;">点击查看</a></td>
												
											</tr>
											
										</table>
									</div>
									<div class="modal-footer table-responsive">										
											
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">关闭</button>
										
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
	<!--[if lt IE 9]>
		<script src="../js/html5shiv.min.js"></script>
		<script src="../js/respond.min.js"></script>
	<![endif]-->
	<script src="../js/jquery.min.js"></script>

	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/jquery.dataTables.min.js"></script>
	<script src="../js/bootbox.min.js"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/myNeed/basecheck.js"></script>
    <script src="../js/kg.js"></script>	
</body>
</html>