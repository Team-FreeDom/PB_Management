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
<title>数据统计</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Bootstrap -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/font-awesome.min.css">
<link rel="stylesheet" href="../css/jquery.dataTables.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/statisticData.css">

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
										class="icon-home" aria-hidden="true" data-placement='top'
										data-toggle='tooltip' title='hah'></i><span>主界面</span>
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
								<li class="menuItem nav-parent opened nav-expanded"
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
							<li><a href="statisticData.jsp"><i class=" icon-home"></i>实习分析</a></li>
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

					<div class="col-lg-12 form-group text-center">

						<h3 style="color: #3071a9">实习申报分析</h3>

					</div>

				</div>
				
				<div class="row form">



					<div class="col-lg-12 form-group">

						<table id="statistictable" class="hover" cellspacing="0"
							width="100%">
							<thead>
								<tr bgcolor="#ECF1F5">
									<td><button type="button" class="btn btn-primary" id="export">导出</button></td>
									<td colspan="17">
										<ul id="dropdown_show">
											<li><span class="icon-filter" id="screen">筛选</span>
												<ul class="hide_ul" style="display:none;">
													<li
														style="background:#d6dfe9;padding-left: 50px; padding-top: 10px;">
														<form action="exportStatisticForm.do" method="post" id="exportStatisticForm">
															<div class="row">
																<div class="col-md-4">
																	基地类型： <select name="baseCategory" id="baseCategory">
																		<option value="" id="baseCategoryID"
																			selected="selected">全部</option>

																	</select> 
																</div>
																<div class="col-md-4">
																	基地名字： <select name="baseName" id="baseName">
																		<option value="" id="baseNameID" selected="selected">全部</option>
																	</select>
																</div>
																<div class="col-md-4">
																	年 级： <select name="gradeClass" id="gradeClass">
																		<option value="" id="gradeClassId" selected="selected">全部</option>
																	</select>
																</div>
															</div>
															<div class="row" style="padding-top: 10px;">
																<div class="col-md-4">
																	学 院： <select name="college" id="college">
																		<option value="" id="collegeID" selected="selected">全部</option>
																	</select>
																</div>
																<div class="col-md-4">
																	专 业： <select name="major" id="major">
																		<option value="" id="majorID" selected="selected">全部</option>
																	</select>
																</div>
																<div class="col-md-4">
																	班 级： <select name="className" id="className">
																		<option value="" id="classNameID" selected="selected">全部</option>
																	</select>
																</div>
															</div>

															<div class="row" style="padding-top: 10px;">
																<div class="col-md-4 col-md-offset-4">
																	<button type="reset" class="btn btn-primary">重置</button>
																	<button type="button" id="finish"
																		class="btn btn-primary">完成</button>
																</div>
															</div>
														</form>
													</li>
												</ul></li>
										</ul>
									</td>
									
								</tr>
								<tr>
									<th>学年学期</th>
									<th>课程代码</th>
									<th>课程名称</th>
									<th>人数</th>
									<th>已选人数</th>
									<th>教学班组成</th>
									<th>开课学院</th>
									<th>周学时</th>
									<th>学分</th>
									<th>课程性质</th>
									<th>课程类别</th>
									<th>教师职工号</th>
									<th>教师姓名</th>
									<th>起始周</th>
									<th hidden>专业编号</th>
									<th hidden>面向专业</th>
									<th>考核</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>

					</div>


				</div>

				<div class="row form">

					<div class="col-lg-12 form-group text-center">

						<h3 style="color: #3071a9">实习申报数量统计</h3>

					</div>

				</div>

				<div class="row form">



					<div class="col-lg-12 form-group">

						<table id="statistictable2" class="hover" cellspacing="0"
							width="100%">
							<thead>
								<tr bgcolor="#ECF1F5">
									<td colspan="8">
										<ul id="dropdown_show">
											<li><span class="icon-filter" id="screen2">筛选</span>
												<ul class="hide_ul2" style="display:none;">
													<li
														style="background:#d6dfe9;padding-left: 50px; padding-top: 10px;">
														<div class="row">
															<div class="col-md-4">
																基地类型： <select name="" id="baseCategory2">
																	<option value="" id="baseCategory2ID">全部</option>
																</select> 
															</div>
															<div class="col-md-4">
																基地名字： <select name="" id="baseName2">
																	<option value="" id="baseName2ID">全部</option>
																</select>
															</div>
															<div class="col-md-4">
																年 级： <select name="" id="gradeClass2">
																	<option value="" id="gradeClass2Id">全部</option>
																</select>
															</div>
														</div>
														<div class="row" style="padding-top: 10px;">
															<div class="col-md-4">
																学 院： <select name="" id="college2">
																	<option value="" id="college2ID">全部</option>
																</select>
															</div>
															<div class="col-md-4">
																专 业： <select name="" id="major2">
																	<option value="" id="major2ID">全部</option>
																</select>
															</div>
															<div class="col-md-4">
																班 级： <select name="" id="className2">
																	<option value="" id="className2ID">全部</option>
																</select>
															</div>
														</div>
														<div class="row" style="padding-top: 10px;">
															<div class="col-md-12">
																<form class="form-inline">
																	<div class="form-group">
																		<label for="teacherName">老 师：</label> <input
																			type="text" class="form-control" id="teacherName"
																			value="全部">
																	</div>
																	<button type="button" id="selectName"
																		class="btn btn-default">选择</button>
																</form>
															</div>
														</div>
														<div class="row" style="padding-top: 10px;">
															<div class="col-md-4 col-md-offset-4">
																<button type="reset" class="btn btn-primary">重置</button>
																<button type="button" id="finish2"
																	class="btn btn-primary">完成</button>
															</div>
														</div>
													</li>
												</ul></li>
										</ul>
									</td>
									
								</tr>
								<tr>
									<th>基地类型</th>
									<th>基地数量</th>
									<th>学院</th>
									<th>专业</th>
									<th>老师</th>
									<th>班级</th>
									<th>经费</th>
									<th>人数</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>

					</div>


				</div>
				<div class="row form" style="padding-left:20%; margin-top:8%">

					<div id="mainEchart" style="width:900px;height:500px; "
						class="col-lg-12 form-group text-center"></div>

				</div>
			</div>
			<!-- End Sidebar-->





		</div>
		<!--row end-->
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content text-center">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">教师选择</h4>
				</div>
				<div class="modal-body">
					<form class="form-inline">
						<div class="row">
							<div class="form-group col-md-6">
								<label for="selectCollege">学院 ：</label> <select type="text"
									class="form-control" id="selectCollege">
									<option id="selectCollegeID" value="">请选择学院</option>
								</select>
							</div>
							<div class="form-group col-md-6">
								<label for="selectTeacher">老师 ：</label> <select type="email"
									class="form-control" id="selectTeacher">
									<option id="selectTeacherID" value="">请选择老师</option>
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="checkTeacher">确认</button>
				</div>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
	<!--弹出框-->
	<div class="modal-content" id="Applychart"
		style="border:#3071a9 8px solid;">
		<div class="modal-header" style="background:#3071a9; color:#FFF">
			<div id="closemodal" class="glyphicon glyphicon-remove closeModal"
				data-dismiss="modal"></div>
			<h4 class="modal-title text-center" id="myModalLabel">实习申请表</h4>
		</div>

		<div class="modal-body table-responsive">
			<form class="form-horizontal" role="form" id="PraForm">

				<table>
					<tr>
						<td>单位：<input readonly type="text" class="noborder"
							id="division" value="农学院">
						</td>
						<td>课程名称：<input readonly type="text" class="noborder"
							id="classname" value="农学实践(3)">
						</td>
						<td>面向专业：<input readonly type="text" class="noborder"
							id="major" value="农学">
						</td>
						<td>班级：<input readonly type="text" class="noborder"
							id="class" value="15信工1">
						</td>
						<td>学分：<input readonly type="text" id="grade" value="4">
						</td>
						<td>学习人数：<input readonly type="text" id="number" value="26">
						</td>
						<td>实习周数：<input readonly type="text" id="weeks" value="12">
						</td>
					</tr>
				</table>

				<br>
				<table class="table-bordered" id="table">

					<tr>
						<td>带队老师</td>
						<td colspan="3"><input class="noborder text-center flag"
							readonly id="leaderTeacher" type="text" value="李林"></td>
						<td>指导教师</td>
						<td colspan="4"><input readonly
							class="noborder text-center flag" id="adviser" value=""
							type="text"></td>
					</tr>
					<tr>
						<td>实验员姓名</td>
						<td colspan="8"><input class="noborder text-center flag"
							readonly id="testername" value="" type="text"></td>
					</tr>

					<tbody id="tbody">

					</tbody>
				</table>
			</form>

		</div>

		<!--<div class="modal-footer table-responsive">
                                    <center>
                                        <button type="button" class="btn btn-danger" id="cancelChart">取消</button>
                                    </center>
                                  </div>-->
	</div>

	<!--弹出框选择-->







	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../js/jquery.min.js"></script>
	<!--datatable javascript-->
	<script src="../js/jquery.dataTables.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/bootbox.min.js"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/echarts.js"></script>
	<script src="../js/myNeed/statisticData.js"></script>
	<script src="../js/kg.js"></script>

</body>
</html>