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
<link rel="stylesheet" href="../css/jquery.dataTables.min.css">
<link rel="stylesheet" href="../css/font-awesome.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/fieldrent_maintain.css">
<link rel="stylesheet" href="../css/calendar.css" media="screen">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="../js/html5shiv.min.js"></script>
	<script src="../js/respond.min.js"></script>
<![endif]-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->


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
							<li><a href="fieldRent_maintain.jsp">土地租赁维护</a></li>
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
						<form action="deleteLandRentInfo.do" method="post" id="deleteInfo">
							<table id="fieldrent_maintain" class="cell-border"
								cellspacing="0" width="100%">
								<thead>
									<tr bgcolor="#ECF1F5">

										<td colspan="5">
											<ul class="dropdown_show">
												<li><span style="color:#24354a" class="icon-filter"
													onClick="showsubmenu()">筛选</span>
													<ul id="hide_ul" style="display:none;">
														<li>
															<form action="submitChoose.do" method="post"
																id="submitChoose">
																<table class="table">
																	<tr>
																		<td>基地名： <select name="baseS" id="baseSh">
																				<option value="" id="baseS">显示全部</option>
																		</select>  
																		</td>
																		<td>申报部门： <select name="deptS" id="deptSh">
																				<option value="" id="deptS">显示全部</option>

																		</select>
																		</td>
																	</tr>
																	<tr>
																		<td>种植内容 <select name="contentS" id="contentSh"
																			size="">
																				<option value="" id="contentShh">显示全部</option>

																		</select>  
																		</td>
																		<td>&nbsp;</td>
																	</tr>
																	<tr>
																		<td colspan="2">
																			<button type="reset" class="btn btn-primary">重置</button>
																			<button onClick="hidesubmenu()" type="button"
																				id="submitS" class="btn btn-primary">完成</button>
																		</td>
																	</tr>
																</table>
															</form>
														</li>
													</ul></li>
											</ul>
										</td>
										<td colspan="4">
											<!-- <button type="button" class="btn btn-info button_style"
												data-toggle="modal" data-target="#myModalAdd">
												<span class="glyphicon glyphicon-plus"></span>
											</button> -->
										</td>
										<td colspan="2">
											<button type="button" class="btn btn-danger"
												onClick="deleteInfo()">删除</button>
											<button type="button" class="btn btn-primary" id="daolead"
												data-toggle="modal" data-target="#lead">导出</button>
										</td>

									</tr>
									<tr style="background:#eeeff4" width="100%">
										<th><label><input type="checkbox" name="0"
												id="ck1" class="ck-all" /></label></th>
										<th>开始日期</th>
										<th>结束日期</th>
										<th>基地名</th>
										<th>土地名称</th>
										<th>土地编号</th>
										<th>租赁人</th>
										<th>申报部门</th>
										<th>已租用次数</th>
										<th>种植内容</th>
										<th>修改</th>
									</tr>
								</thead>

								<tbody>

								</tbody>

							</table>
						</form>
					</div>
					<div class="modal fade" id="myModalEdit" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<form action="landManageUpdate.do" method="post"
							id="landManageUpdate">
							<div class="modal-dialog">
								<div class="modal-content" style="border:#3071a9 8px solid">
									<div class="modal-header"
										style="background:#3071a9; color:#FFF">
										<div class="glyphicon glyphicon-remove closeModal"
											data-dismiss="modal"></div>
										<h4 class="modal-title text-center" id="myModalLabel">详情及修改</h4>
									</div>
									<div class="modal-body table-responsive">
										<table class="table">
											<tr>
												<td colspan="4"><input type="text" name="lr_id"
													id="lr_id" hidden></td>
											</tr>
											<tr>
												<td>基地名 ：</td>
												<td><input type="text" name="bname" id="bname" disabled /></td>

												<td>租赁人 ：</td>
												<td><input type="text" name="username" id="username"
													disabled></td>

											</tr>
											<tr>
												<td>申报部门 ：</td>
												<td><select name="deptSelect" id="deptLaLa">
														<option value="" id="deptD">请选择</option>

												</select></td>

												<td>土地编号 ：</td>
												<td><input type="text" name="lid" id="lid" disabled></td>

											</tr>


											<tr>
												<td>土地面积 ：</td>
												<td><input type="text" name="landArea" id="landArea"
													disabled></td>
												<td>适宜从事内容 ：</td>
												<td><input type="text" name="aptCareer" id="aptCareer"
													disabled></td>
											</tr>

											<tr>


												<td>计划从事内容 ：</td>
												<td><input type="text" name="planCareer"
													id="planCareer"></td>
												<td>租赁费用 ：</td>
												<td><input type="text" name="expense" id="expense"></td>
											</tr>

											<tr>

												<td>交费日期 ：</td>
												<td colspan="3"><input type="text" name="chargeDate"
													id="chargeDate" disabled></td>

											</tr>

											<tr>
												<td>使用年限 :</td>
												<td><input type="text" name="startTime" id="startTime"
													class="laydate-icon"></td>
												<td>-----------</td>
												<td><input type="text" name="endTime" id="endTime"
													class="laydate-icon"></td>
											</tr>
										</table>
									</div>
									<div class="modal-footer table-responsive">
										<center>
											<button type="button" class="btn btn-primary" id="definite">确定修改</button>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
										</center>
									</div>
								</div>
							</div>
						</form>
					</div>

					<div class="modal fade" id="myModalAdd" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<form action="landManageAdd.do" method="post" id="landManageAdd"
							onSubmit="return checkA()">
							<div class="modal-dialog">
								<div class="modal-content" style="border:#3071a9 8px solid">
									<div class="modal-header"
										style="background:#3071a9; color:#FFF">
										<div class="glyphicon glyphicon-remove closeModal"
											data-dismiss="modal"></div>
										<h4 class="modal-title text-center" id="myModalLabel">租赁记录增加</h4>
									</div>
									<div class="modal-body table-responsive">
										<table class="table">
											<tr>
												<td>土地编号 ：</td>
												<td><input type="text" name="addLid" id="addLid"></td>
												<td>租赁人编号：</td>
												<td><input type="text" name="addUserid" id="addUserid"></td>

											</tr>
											<tr>
												<td>申报部门：</td>
												<td><select name="addDept" id="addDept">
														<option value="" id="addDepth" />请选择
														</option>
												</select></td>
												<td>从事内容 ：</td>
												<td><input type="text" name="addPlanting"
													id="addPlanting" /></td>
											</tr>
											<tr>
												<td>使用年限 :</td>
												<td><input type="text" name="addStartTime"
													id="addStartTime" /></td>
												<td>--------</td>
												<td><input type="text" name="addEndTime"
													id="addEndTime" /></td>
											</tr>
											<tr>
												<td>交费日期 ：</td>
												<td><input type="text" name="addChargeDate"
													id="addChargeDate" /></td>
												<td>租赁费用 ：</td>
												<td><input type="text" name="addExpense"
													id="addExpense" /></td>


											</tr>


										</table>
									</div>
									<div class="modal-footer table-responsive">
										<center>
											<button type="button" class="btn btn-primary" id="add">确定</button>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
										</center>
									</div>
								</div>
							</div>
						</form>

					</div>

				</div>
			</div>
			<!-- End Sidebar-->

			<!-- Modal -->
			<div class="modal fade" id="lead" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content" style="border:#3071a9 8px solid">
						<div class="modal-header" style="background:#3071a9; color:#FFF">
							<div class="glyphicon glyphicon-remove closeModal"
								data-dismiss="modal"></div>
							<h4 class="modal-title" id="myModalLabel">请选择导出的部门</h4>
						</div>
						<form action="exportLandRentInfo.do" method="post"
							id="landRentForm">
							<div class="modal-body" id="daochu" style="height:60px;">
								<select name="dept" id="exportDept">
									<option value="" id="deptE">全部</option>

								</select>
							</div>
						</form>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="certainExport">确定</button>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!--row end-->
	</div>


	<div class="clearfix"></div>


</body>
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.dataTables.min.js"></script>
<script src="../js/bootbox.min.js"></script>
<script type="text/javascript" src="../js/laydate.js"></script>
<script src="../js/myNeed/rentMaintain.js"></script>
<script src="../dist/jquery.cokie.min.js"></script>
<script src="../js/kg.js"></script>
<script>
	laydate.skin('yalan'); //切换皮肤，请查看skins下面皮肤库
	laydate({
		elem : '#startTime'
	})
	laydate({
		elem : '#endTime'
	});
</script>

</html>
