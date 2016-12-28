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
<title>我的基地</title>
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
<link rel="stylesheet" href="../css/mybase.css">
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
						<a href="#" target="_blank"><img
							src="../image/manage-logo.png" alt=""></a>
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

								<li class="menuItem nav-parent opened nav-expanded"><a>
										<i class="icon-copy" aria-hidden="true"></i><span>我的工作</span>
								</a>
									<ul class="nav nav-children">
										<li><a href="myRent.jsp"><span class="text">我的租赁</span></a></li>
										<li><a href="#"><span class="text">我的实习</span></a></li>
										<li><a href="#"><span class="text">我的报修</span></a></li>
										<li><a href="myBase.jsp"><span class="text">我的基地</span></a></li>
									</ul></li>

								<li class="menuItem nav-parent"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>审批工作</span>
								</a>
									<ul class="nav nav-children">
										<li><a href="rent-approve.jsp"><span class="text">租赁审批</span></a></li>
										<li><a href="#"><span class="text">实习审批</span></a></li>
										<li><a href="baseCheck.jsp"><span class="text">基地审批</span></a></li>
										<li><a href="#"><span class="text">维修审批</span></a></li>

									</ul></li>

								<li class="menuItem nav-parent"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>数据管理</span>
								</a>
									<ul class="nav nav-children">
										<li><a href="notification.do"><span class="text">
													发布通知公告</span></a></li>
										<li><a href="land_modle.jsp"><span class="text">
													土地布局设置</span></a></li>
										<li><a href="fieldRent_maintain.jsp"><span
												class="text"> 土地租赁维护</span></a></li>
										<li><a href="#"><span class="text"> 实习基地维护</span></a></li>
										<li><a href="#"><span class="text"> 实习计划维护</span></a></li>
										<li><a href="start.jsp"><span class="text">
													工作计划制定</span></a></li>
										<li><a href="mangeruser.jsp"><span class="text">
													系统用户维护</span></a></li>
										<li><a href="system_power.jsp"><span class="text">
													系统权限设置</span></a></li>
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
				<div class="page-header row">
					<div class="pull-left">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a>位置 :</a></li>
							<li><a href="myBase.jsp"><i class=" icon-home"></i>我的基地</a></li>
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
						<table id="mybasetable1" class="cell-border" cellspacing="0"
							width="100%">
							<thead>
								<tr bgcolor="#3B6290" style="color:#FFF">
									<th>基地名称</th>
									<th>基地类型</th>
									<th>申报部门</th>
									<th>土地面积</th>
									<th>建筑面积</th>
									<th>可承担人数</th>
									<th hidden>通信地址</th>
									<th hidden>联系人姓名</th>
									<th hidden>联系人电话</th>
									<th hidden>面向专业</th>
									<th hidden>申请材料</th>
									<th hidden>申请人</th>
									<th hidden>创建时间</th>
									<th hidden>有效周期</th>
									<th>申请状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>耘园气象站</td>
									<td>创业孵化基地</td>
									<td>信息科学技术学院</td>
									<td>30</td>
									<td>1.5</td>
									<td>1000</td>
									<td hidden>湖南省</td>
									<td hidden>张三</td>
									<td hidden>13567656789</td>
									<td hidden>计算机</td>
									<td hidden></td>
									<td hidden>213144</td>
									<td hidden>2016-09-18</td>
									<td hidden>1</td>
									<td>审核中</td>
									<td>
										<button type="button" class="btn btn-warning btn-xs" id="scan">查看</button>
										<button type="button" class="btn btn-danger btn-xs"
											data-toggle="modal" data-target="#delete_data">撤回</button>

									</td>
								</tr>
								<tr>
									<td>耘园气象站</td>
									<td>创业孵化基地</td>
									<td>信息科学技术学院</td>
									<td>30</td>
									<td>1.5</td>
									<td>1000</td>
									<td hidden>湖南省</td>
									<td hidden>张三</td>
									<td hidden>13567656789</td>
									<td hidden>计算机</td>
									<td hidden></td>
									<td hidden>213144</td>
									<td hidden>2016-09-18</td>
									<td hidden>1</td>
									<td>申请成功</td>
									<td>
										<button type="button" class="btn btn-warning btn-xs" id="scan">查看</button>

									</td>
								</tr>
								<tr>
									<td>耘园气象站</td>
									<td>创业孵化基地</td>
									<td>信息科学技术学院</td>
									<td>30</td>
									<td>1.5</td>
									<td>1000</td>
									<td hidden>湖南省</td>
									<td hidden>张三</td>
									<td hidden>13567656789</td>
									<td hidden>计算机</td>
									<td hidden></td>
									<td hidden>213144</td>
									<td hidden>2016-09-18</td>
									<td hidden>1</td>
									<td>申请失败</td>
									<td>
										<button type="button" class="btn btn-warning btn-xs"
											data-toggle="modal" data-target="#rent_edit">查看</button>
									</td>
								</tr>
							</tbody>
						</table>
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
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel"></h4>
									</div>
									<div class="modal-body" style="text-align: left;">
										<h5>您确定要撤回吗？</h5>
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

					<!--弹出框-->
					<div class="modal fade" id="fontTable" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content" style="border:#3071a9 8px solid">
								<div class="modal-header" style="background:#3071a9; color:#FFF">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title text-center" id="myModalLabel">基地申请详情</h4>
								</div>

								<div class="modal-body table-responsive">
									<div class="row">
										<div class="col-md-12">
											<table class="table">
												<tr>
													<td>基地名称 ：</td>
													<td><input type="text" id="basename" disabled></td>
													<td>基地类型 ：</td>
													<td><input type="text" id="basetype" disabled></td>
												</tr>
												<tr>
													<td>申请部门 ：</td>
													<td><input type="text" id="dept0" disabled></td>
													<td>土地面积 ：</td>
													<td><input type="text" id="landarea" disabled /></td>
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
													<td colspan="3"><div id="major_oriented"
															style="border:#ccc 1px solid;height:80px;"></div></td>

												</tr>

												<tr>
													<td>通信地址 ：</td>
													<td colspan="3"><div id="linkAddress"
															style="border:#ccc 1px solid;height:80px;"></div></td>

												</tr>


												<tr>
													<td>申请材料 ：</td>
													<td colspan="3" style="text-align:left;"><a
														id="resource" href="#" style="color:#3071a9;">点击查看</a></td>
												</tr>
												<tr hidden id="hidecol">
													<td>创建时间：</td>
													<td><input type="text" id="setdate" disabled></td>
													<td>有效周期 ：</td>
													<td><input type="text" id="validdate" disabled></td>
												</tr>

											</table>
										</div>
									</div>
								</div>
								<div class="modal-footer table-responsive">
									<button type="button" class="btn btn-primary"
										data-dismiss="modal">关闭</button>
								</div>
							</div>
						</div>
					</div>



					<div class="col-lg-12 form-group">
						<table id="mybasetable2" class="cell-border" cellspacing="0"
							width="100%">
							<thead>
								<tr bgcolor="#ECF1F5">
									<td>申请历史</td>
									<td colspan="5">
										<ul id="dropdown_show">
											<li><span class="icon-filter">筛选</span>
												<ul id="hide_ul" style="display:none;">
													<li>
														<form>
															<table class="table">
																<tr>
																<td>
																	最终状态
																	<select name="status" id="status" style="width:150px;margin-top:0px;">
																		<option value="-1">显示全部</option>
																		<option value="1">申请成功</option>
																		<option value="0">申请失败</option>
																		<option value="2">失效</option>
																	</select>  
																	</td>
																</tr>
																<tr>
																	<td colspan="2" style="text-align:center">
																		<button type="reset" class="btn btn-primary">重置</button>
																		<button type="button" class="btn btn-primary" id="finish">完成</button>
																	</td>
																</tr>
															</table>
														</form>
													</li>
												</ul></li>
										</ul>
									</td>
                                    <td colspan="3">
								</tr>
								<tr style="background:#eeeff4">
									<th>基地名称</th>
									<th>基地类型</th>
									<th>申报部门</th>
									<th>土地面积</th>
									<th>建筑面积</th>
									<th>可承担人数</th>
									<th hidden>通信地址</th>
									<th hidden>联系人姓名</th>
									<th hidden>联系人电话</th>
									<th hidden>面向专业</th>
									<th hidden>申请材料</th>
									<th hidden>申请人</th>
									<th hidden>创建时间</th>
									<th hidden>有效周期</th>
									<th>申请状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>耘园气象站</td>
									<td>创业孵化基地</td>
									<td>信息科学技术学院</td>
									<td>30</td>
									<td>1.5</td>
									<td>1000</td>
									<td hidden>湖南省</td>
									<td hidden>张三</td>
									<td hidden>13567656789</td>
									<td hidden>计算机</td>
									<td hidden></td>
									<td hidden>213144</td>
									<td hidden>2016-09-14</td>
									<td hidden>1</td>
									<td>申请成功</td>
									<td><span class="icon-search" id="scan"></span></td>
								</tr>
								<tr>
									<td>耘园气象站</td>
									<td>创业孵化基地</td>
									<td>信息科学技术学院</td>
									<td>30</td>
									<td>1.5</td>
									<td>1000</td>
									<td hidden>湖南省</td>
									<td hidden>张三</td>
									<td hidden>13567656789</td>
									<td hidden>计算机</td>
									<td hidden></td>
									<td hidden>213144</td>
									<td hidden>2016-09-14</td>
									<td hidden>1</td>
									<td>申请失败</td>
									<td><span class="icon-search" data-toggle="modal"
										data-target="#failapplication"></span></td>
								</tr>
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
	<script src="../js/fileinput.js" type="text/javascript"></script>
	<script src="../js/fileinput_locale_zh.js" type="text/javascript"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/myNeed/mybase.js"></script>
	<script src="../js/kg.js"></script>

</body>
</html>
