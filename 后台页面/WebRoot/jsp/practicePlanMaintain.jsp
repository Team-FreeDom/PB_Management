<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<link rel="stylesheet" href="../css/jquery.dataTables.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/practiceplanmaintain.css">
<link rel="stylesheet" href="../css/select2.css">
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
				<div class="page-header row">
					<div class="pull-left">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a>位置 :</a></li>
							<li><a href="practicePlanMaintain.jsp"><i
									class=" icon-home"></i>实习计划维护</a></li>
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



					<div class="col-lg-12 form-group">

						<table id="practiceplanmaintain" class="hover" cellspacing="0"
							width="100%">
							<thead>
								<tr>
									<td colspan="2"><select name="termYear" id="termYear"
										class="form-control" style="width:150px;">
											<option value="" id="termYearID" selected>请选择学年</option>
									</select></td>

									<td colspan="8"><select name="semester" id="semester"
										class="form-control" style="width:150px;">
											<option value="" selected>请选择学期</option>
											<option value="1">1</option>
											<option value="2">2</option>
									</select></td>
									<td hidden id="ta1">${teamYear}</td>
									<td hidden id="ta2">${sem}</td>
									<td hidden id="ta3">${tag}</td>	
									<td hidden id="exportTag">${exportTag}</td>								
									<td colspan="7">
										<button class="btn btn-primary" id="chu">导出</button>									    
										<button class="btn btn-primary" id="daoru" data-toggle="modal" data-target="#writeWeekTime" ${college==null?"":"style='display:none;'"}>导入</button>
										<a href="../templet/PracticeMaintainInfo.rar" id="LinkButton" class="btn btn-primary" ${college==null?"":"style='display:none;'"}>点击下载导入模板</a>
									</td>

								</tr>
								<tr bgcolor="#ECF1F5">
									<td colspan="8"><button class="btn btn-success" id="showAllInfo">显示全部数据</button></td>
									<td colspan="9">																														
										<span id="remind" class="glyphicon glyphicon-send"></span>
										<button class="btn btn-warning" id="checkIsSave">检测数据完整性</button>										
										<button class="btn btn-danger" id="delete" ${college==null?"":"style='display:none;'"}>删除</button>
										<button class="btn btn-success" id="updatePlan" ${college==null?"":"style='display:none;'"}>修改</button>
										<button class="btn btn-success" id="add" ${college==null?"":"style='display:none;'"}>增加</button>										
									</td>


								</tr>
								<tr>
									<th><input type="checkbox" id="ck1"></th>
									<th>学年学期</th>
									<th>课程代码</th>
									<th>课程名称</th>
									<th>人数</th>								
									<th>教学班组成</th>
									<th>开课学院</th>
									<th>周学时</th>
									<th>学时</th>
									<th>课程性质</th>
									<th>课程类别</th>
									<th>教师职工号</th>
									<th>教师姓名</th>
									<th>起始周</th>
									<th hidden>面向专业</th>
									<th hidden>专业编号</th>
									<th>考核方式</th>

								</tr>
							</thead>
							<tbody>

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

	<!-- Modal 导入-->
	<div class="modal fade" id="import" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<form action="importPlanInfo.do" method="post" id="daoruform"
				enctype="multipart/form-data">
				<div class="modal-content" style="border:#3071a9 8px solid">
					<div class="modal-header" style="background:#3071a9; color:#FFF">
						<div id="closeimport"
							class="glyphicon glyphicon-remove closeModal"
							data-dismiss="modal"></div>
						<h5 class="modal-title" id="myModalLabel">请选择要导入的文件</h5>
					</div>

					<center>
						<div class="modal-body" id="exportxsl" style="height:60px;">
							<input type="file" id="fileResource" name="fileResource">
							<input hidden type="text" name="semesterfile" id="semesterfile" />
							<input hidden type="text" name="timeDi" id="timeDi" />
						</div>
					</center>
					<div class="modal-footer">
						<center>
							<button type="button" class="btn btn-primary" id="certainimport">确定</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="closeimport">取消</button>
						</center>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- 输入学年的第一周-->
	<div class="modal fade" id="writeWeekTime" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">

			<div class="modal-content"
				style="border:#3071a9 8px solid;width:300px;">
				<div class="modal-header" style="background:#3071a9; color:#FFF">

					<div id="closeWriteWeekTime"
						class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></div>

					<h5 class="modal-title" id="myModalLabel">请先填写下述消息：</h5>
				</div>
				<center>
					<div class="modal-body" id="weekTime" style="height:200px;">
						<div>
							<span>学年：</span><select class="form-control" name="teamYearw"
								style="width:150px;" id="teamYearw">
								<option value="" id="AteamYearw">请选择学年</option>
							</select> 学期：<select class="form-control" name="semesterw"
								style="width:150px;" id="semesterw">
								<option value="" id="Asemesterw">请选择学期</option>
								<option value="1">1</option>
								<option value="2">2</option>
							</select>
						</div>
						<div id="weekTi" hidden>
							<span id="weekTiSpan"></span><input type="text"
								class="form-control laydate-icon" id="oneSemesterTime"
								style="height:32px;" />

						</div>
					</div>
				</center>
				<div class="modal-footer">
					<center>
						<button class="btn btn-primary" id="certainWeekTime">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</center>
				</div>
			</div>

		</div>
	</div>

	<!-- Modal 导出-->
	<div class="modal fade" id="export" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:400px;height:600px;">
			<div class="modal-content" style="border:#3071a9 8px solid;">
				<div class="modal-header" style="background:#3071a9; color:#FFF">

					<div id="daoclose" class="glyphicon glyphicon-remove closeModal"
						data-dismiss="modal"></div>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<form action="exportPlanInfo.do" method="post" id="daochuForm">
					<div class="modal-body" id="daochu">
						<table>
							<tr>
								<td>学年:</td>
								<td><select class="form-control" id="daoYear"
									name="daoYear" style="width:180px;margin-left:20px;">
										<option value="-1" id="daoYearh">全部</option>
								</select></td>
							</tr>
							<tr>
								<td>学期:</td>
								<td><select class="form-control" id="daosemster"
									name="daosemster" style="width:180px;margin-left:20px;">
										<option value="-1" id="daosemsterh">全部</option>
										<option value="1" class="daosem">1</option>
										<option value="2" class="daosem">2</option>
								</select></td>
							</tr>
							<tr ${college==null?"":"style='display:none;'"}>
								<td>学院:</td>
								<td><select class="form-control" id="daoColleget"}
									name=${college==null?"college":""} style="width:180px;margin-left:20px;">
										<option value="-1" id="daodept">全部</option>
								</select></td>

							</tr>
						</table>

					</div>
				</form>
				<div class="modal-footer">
					<center>
						<button class="btn btn-primary" id="confirmDaoButton">确定</button>
						<button type="button" class="btn btn-default" id="daoclose"
							data-dismiss="modal">取消</button>

					</center>
				</div>

			</div>
		</div>
	</div>



	<!--弹出框-->
	<div class="modal-content" id="Applychart"
		style="border:#3071a9 8px solid;">
		<div class="modal-header" style="background:#3071a9; color:#FFF">
			<div id="closemodal" class="glyphicon glyphicon-remove closeModal"
				data-dismiss="modal"></div>
			<h4 class="modal-title text-center" id="myModalLabel">实习申请表</h4>
		</div>

		<div class="modal-body table-responsive" id="modalbody">
			<form class="form-horizontal" role="form" id="PraForm">

				<table class="table" id="showtable">
					<tr>
						<td>单位：<input readonly type="text" class="noborder"
							id="division" value="">
						</td>
						<td>课程名称：<input readonly type="text" id="classname" value=""
							class="noborder">
						</td>

						<td>班级：<input readonly type="text"
							class="noborder" id="class" value="">
						</td>
						
						<td>起始周：<input readonly type="text"
							class="noborder" id="fromweek" value="">
						</td>

					</tr>
					<tr>
						<td>面向专业：<input readonly type="text" class="noborder"
							id="major" value="">
						</td>
						<td>学分：<input readonly type="text" id="grade" value="4"
							class="noborder">
						</td>
						<td>学习人数：<input readonly type="text" id="number" value="26"
							class="noborder">
						</td>
						<td>实习周数：<input readonly type="text" id="weeks" value="12"
							class="noborder">
						</td>
					</tr>
				</table>
				<button type="button" class="btn btn-primary" id="addTbody">添加</button>
				<button type="button" class="btn btn-success" id="save">保存</button>

				<table id="table">

					<tr>
						<td colspan="2">带队老师</td>
						<td colspan="3"><input class="noborder text-center flag"
							readonly id="leaderTeacher" type="text" value="李林"></td>
						<td>指导教师</td>
						<td colspan="4"><input readonly
							class="noborder text-center flag" id="adviser" type="text"></td>
					</tr>
					<tr>
						<td colspan="2">实验员姓名</td>
						<td colspan="8"><input class="noborder text-center flag"
							readonly id="testername" type="text"></td>
					</tr>

					<tbody>

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
	<div class="modal fade" id="Selectname" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form action="" method="post" id="">
				<div class="modal-content" id="modalcontent"
					style="border:#3071a9 8px solid;width: 450px">
					<div class="modal-header" style="background:#3071a9; color:#FFF">
						<div class="glyphicon glyphicon-remove closeModal"
							data-dismiss="modal"></div>
						<h4 class="modal-title text-center">请选择实验员</h4>
					</div>
					<center>
						<div class="modal-body">
							<div class="row" style="margin-bottom: 30px;">
								<div class="col-sm-12 form-inline">
									<lable>实验员:</lable>
									<input id="tester" type="text" class="form-control exeWidth"
										value="">
								</div>
							</div>
							<div class="row" style="margin-bottom: 30px;">
								<div class="col-sm-6">
									<select name="" id="selectCollege" class="form-control">
										<option value="" id="collegeID">请选择学院</option>

									</select>
								</div>
								<div class="col-sm-6">
									<select name="" id="selectTname" class="form-control">
										<option value="" id="teacherNmaeID">请选择老师</option>

									</select>
								</div>
							</div>



						</div>
					</center>
					<div class="modal-footer">
						<center>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal" id="finished">确定</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="">取消</button>
						</center>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="modal fade" id="Selectteacher" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" id="modalcontent2"
				style="border:#3071a9 8px solid;width: 450px">
				<div class="modal-header" style="background:#3071a9; color:#FFF">
					<div class="glyphicon glyphicon-remove closeModal"
						data-dismiss="modal"></div>
					<h4 class="modal-title text-center">请选择指导老师</h4>
				</div>

				<center>
					<div class="modal-body">
						<div class="row" style="margin-bottom: 30px;">
							<div class="col-sm-12 form-inline">
								<lable>指导老师:</lable>
								<input id="leadteachername" type="text"
									class="form-control exeWidth" value="">
							</div>

						</div>
						<div class="row" style="margin-bottom: 30px;">
							<div class="col-sm-6">
								<select name="" id="selectCollege2" class="form-control">
									<option value="" id="collegeID2">请选择学院</option>
								</select>
							</div>
							<div class="col-sm-6">
								<select name="" id="selectTname2" class="form-control">
									<option value="" id="teacherNmaeID2">请选择老师</option>
								</select>
							</div>
						</div>



					</div>
				</center>
				<div class="modal-footer">
					<center>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							id="finished2">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="">取消</button>
					</center>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="TeaName" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" 	style="border:#3071a9 8px solid;width: 450px">
				<div class="modal-header" style="background:#3071a9; color:#FFF">
					<div class="glyphicon glyphicon-remove closeModal"
						data-dismiss="modal"></div>
					<h4 class="modal-title text-center">请选择带队老师</h4>
				</div>

				<center>
					<div class="modal-body">
						<div class="row" style="margin-bottom: 30px;">
							<div class="col-sm-12 form-inline">
								<lable>带队老师:</lable>
								<input id="showTeaName" type="text"
									class="form-control exeWidth" value="">
							</div>

						</div>
						<div class="row" style="margin-bottom: 30px;">
							<div class="col-sm-6">
								<select name="" id="selectCollege3" class="form-control">
									<option value="" id="collegeID3">请选择学院</option>
								</select>
							</div>
							<div class="col-sm-6">
								<select name="" id="selectTname3" class="form-control">
									<option value="" id="teacherNmaeID3">请选择老师</option>
								</select>
							</div>
						</div>



					</div>
				</center>
				<div class="modal-footer">
					<center>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							id="finished_increase1">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="">取消</button>
					</center>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="TeaId" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" 	style="border:#3071a9 8px solid;width: 450px">
				<div class="modal-header" style="background:#3071a9; color:#FFF">
					<div class="glyphicon glyphicon-remove closeModal"
						data-dismiss="modal"></div>
					<h4 class="modal-title text-center">请选择教职工号</h4>
				</div>

				<center>
					<div class="modal-body">						
						<div class="row" style="margin-bottom: 30px;">
							<div class="col-sm-6">
								<select name="" id="selectCollege4" class="form-control">
									<option value="" id="collegeID4">请选择学院</option>
								</select>
							</div>
							<div class="col-sm-6">
								<select name="" id="selectTname4" class="form-control">
									<option value="" id="teacherNmaeID4">请选择工号</option>
								</select>
							</div>
						</div>



					</div>
				</center>
				<div class="modal-footer">
					<center>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							id="finished_increase2">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="">取消</button>
					</center>
				</div>
			</div>
		</div>
	</div>
	
	
	<!--增加按钮弹出框-->
	<div id="addPraItem">
		
			<div class="modal-content" style="border:#3071a9 8px solid;">
				<div class="modal-header" style="background:#3071a9; color:#FFF">
					<div class="glyphicon glyphicon-remove closeModal closeModal_increase"
						data-dismiss="modal"></div>
					<h4 class="modal-title text-center">增加实习计划</h4>
				</div>
				<div class="modal-body">
					<form id="addForm">
						<table class="table">
							<tr>
								<td>学年学期<span class="setTag">*</span></td>
								<td><input type="text" name="semester2" id="insemester2"
									class="form-control" disabled></td>
								<td>课程代码<span class="setTag">*</span></td>
								<td><input type="text" value="" name="cid" id="incid"
									class="form-control"></td>

							</tr>
							<tr>
								<td>课程名称<span class="setTag">*</span></td>
								<td><input type="text" name="coursename" id="incoursename"
									class="form-control"></td>
								<td>课程性质<span class="setTag">*</span></td>
								<td><select class="form-control" name="courseNature"
									id="incourseNature">
										<option value="">请选择</option>
										<option value="必修">必修</option>
										<option value="选修">选修</option>
								</select></td>
							</tr>
							<tr>
								<td>课程类别<span class="setTag">*</span></td>
								<td><select class="form-control" name="courseCategory"
									id="incourseCategory">
										<option value="">请选择</option>
										<option value="实践课">实践课</option>
										<option value="理论课">理论课</option>
								</select></td>
								<td>开课学院<span class="setTag">*</span></td>
								<td><select class="form-control" name="college"}
									id="incollege">
										<option value="" id="Acollegeh">请选择</option>
								</select></td>

							</tr>
							<tr>
								<td>带队教师<span class="setTag">*</span></td>
								<td><input type="text" value="" name="tname" id="intname"
									class="form-control"/></td>
								<td>教师职工号<span class="setTag">*</span></td>
								<td><input type="text" value="" name="tid" id="intid"
									class="form-control"/></td>
							</tr>
							<tr>								
								<td>起始周次<span class="setTag">*</span></td>
								<td id="td"><input type="text" name="week" id="inweek"
									class="form-control" placeholder="请点击添加起始周" readonly>
									<div id="select" hidden style="padding:0 10px">
										<form class="form-inline">
											<div class="form-group">
												<label for="starNum">开始周次</label> <select type="text"
													id="starNum">
													<option value="0" id="starNumID">请选择</option>
												</select>
											</div>
											<div class="form-group">
												<label for="endNum">结束周次</label> <select type="text"
													id="endNum">
													<option value="0" id="endNumID">请选择</option>
												</select>
												<div>
													<button type="button" class="btn btn-primary btn-xs"
														id="addWeenNum">添加</button>
													<button type="button" class="btn btn-primary btn-xs"
														id="weendelate">清空</button>
													<button type="button" class="btn btn-primary btn-xs"
														id="closeNum">关闭</button>
												</div>
											</div>
										</form>
									</div></td>
									<td>人数</td>
								<td><input type="text" name="count" id="incount"
									class="form-control" placeholder="例：90"></td>

							</tr>
							<tr>
								
								<td>已选人数</td>
								<td><input type="text" name="selectedCount"
									id="inselectedCount" class="form-control" placeholder="例：90"></td>
<td>周学时</td>
								<td><input type="text" name="weekClassify"
									id="inweekClassify" class="form-control" placeholder="例：2"></td>
							</tr>

							<tr>
								
								<td>学分</td>
								<td><input type="text" name="credit" id="incredit"
									class="form-control" placeholder="例：2.0"></td>
								<td>考核方式</td>
								<td><input type="text" name="checkMethod" 
									id="incheckMethod" class="form-control"></td>
								
							</tr>

							<tr>
								<td>班级组成<span class="setTag">*</span></td>
								<td colspan="3"><input type="text" name="composition"
									id="incomposition" class="form-control"></td>
							</tr>
							
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="saveAdd">保存</button>
					<button type="button" class="btn btn-default closeModal_increase" data-dismiss="modal">取消</button>
				</div>

			</div>
		
	</div>

	<div class="modal fade" id="time" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">请选择开始和结束时间</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="Stime" class="col-md-offset-2 col-md-2 control-label">开始时间</label>
							<div class="col-md-6">
								<input type="text" class="form-control laydate-icon" id="Stime">
							</div>
						</div>
						<div class="form-group">
							<label for="Etime" class="col-md-offset-2 col-md-2 control-label">结束时间</label>
							<div class="col-md-6">
								<input type="text" class="form-control laydate-icon" id="Etime">
							</div>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="finishTime">确定</button>
				</div>
			</div>
		</div>
	</div>

<div class="modal fade" id="Selectmajor" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form action="" method="post" id="">
				<div class="modal-content" id="modalcontent"
					style="border:#3071a9 8px solid;width: 450px">
					<div class="modal-header" style="background:#3071a9; color:#FFF">
						<div class="glyphicon glyphicon-remove closeModal"
							data-dismiss="modal"></div>
						<h4 class="modal-title text-center">请选择面向专业</h4>
					</div>

					<center>
						<div class="modal-body">
							<div class="row" style="margin-bottom: 30px;">
								<div class="col-sm-12 form-inline">
									<lable>面向专业:</lable>
									<input id="showmajor" type="text" class="form-control exeWidth"
										value="">
								</div>
							</div>
							<div class="row" style="margin-bottom: 30px;">
								<div class="col-sm-6">
									<select name="" id="majorCollege" class="form-control">
										<option value="" id="majorcollegeID">请选择学院</option>										
									</select>
								</div>
								<div class="col-sm-6">
									<select name="" id="majorName" class="form-control">
										<option value="" id="majorNmaeID">请选择专业</option>										
									</select>
								</div>
							</div>



						</div>
					</center>
					<div class="modal-footer">
						<center>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal" id="finished3">确定</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="">取消</button>
						</center>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- 修改按钮弹出框-->	
		<div id="updatePlanItem">
			<div class="modal-content" style="border:#3071a9 8px solid;">
				<div class="modal-header" style="background:#3071a9; color:#FFF">
					<div class="glyphicon glyphicon-remove closeModal closeModal_update"
						data-dismiss="modal"></div>
					<h4 class="modal-title text-center">修改实习计划</h4>
				</div>
				<div class="modal-body">
					<form id="addForm">
						<table class="table">
							<tr>
								<td>学年学期<span class="setTag">*</span></td>
								<td><input type="text" id="semsYear_0" class="form-control"
									disabled></td>
								<td>课程代码<span class="setTag">*</span></td>
								<td><input type="text" value="" disabled id="cid_0"
									class="form-control"></td>
								<td hidden id="index"></td>
							</tr>
							<tr>
								<td>课程名称<span class="setTag">*</span></td>
								<td><input type="text" id="coursename_0"
									class="form-control"></td>
								<td>课程性质<span class="setTag">*</span></td>
								<td><select class="form-control" id="courseNature_0">
										<option value="">请选择</option>
										<option value="必修">必修</option>
										<option value="选修">选修</option>
								</select></td>
							</tr>
							<tr>
								<td>课程类别<span class="setTag">*</span></td>
								<td><select class="form-control" id="courseCategory_0">
										<option value="">请选择</option>
										<option value="实践课">实践课</option>
										<option value="理论课">理论课</option>
								</select></td>
								<td>开课学院<span class="setTag">*</span></td>
								<td><select class="form-control" name="college"
									id="college_0">
										<option value="" id="college_00">请选择</option>
								</select></td>

							</tr>
							<tr>
								<td>带队教师<span class="setTag">*</span></td>
								<td><input type="text" value="" id="tname_0"
									class="form-control"></td>
								<td>教师职工号<span class="setTag">*</span></td>
								<td><input type="text" value="" id="tid_0"
									class="form-control"></td>
							</tr>
							<tr>							
								</td>
								<td>起始周次<span class="setTag">*</span></td>
								<td><input type="text" id="week_0" disabled
									class="form-control"></td>
									<td>人数</td>
								<td><input type="text" id="count_0" class="form-control"
									placeholder="例：90"></td>

							</tr>
							<tr>
							</tr>
							<tr>
								
								<td>已选人数</td>
								<td><input type="text" id="selectedCount_0"
									class="form-control" placeholder="例：90"></td>
									<td>周学时</td>
								<td><input type="text" id="weekClassify_0"
									class="form-control" placeholder="例：2.0"></td>

							</tr>

							<tr>
								
								<td>学分</td>
								<td><input type="text" id="credit_0" class="form-control"
									placeholder="例：2.0"></td>
									<td>考核方式</td>
								<td><input type="text" id="checkMethod_0"
									class="form-control"></td>
								
								
							</tr>

							<tr>
									<td>班级组成<span class="setTag">*</span></td>
								<td colspan="3"><input type="text" id="composition_0"
									class="form-control"></td>
								
							</tr>						
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="saveUpdate">确定修改</button>
					<button type="button" class="btn btn-default closeModal_update" data-dismiss="modal">取消</button>
				</div>

			</div>
		</div>	
		
		<div class="modal fade" id="TeaName_update" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" 	style="border:#3071a9 8px solid;width: 450px">
				<div class="modal-header" style="background:#3071a9; color:#FFF">
					<div class="glyphicon glyphicon-remove closeModal"
						data-dismiss="modal"></div>
					<h4 class="modal-title text-center">请选择带队老师</h4>
				</div>

				<center>
					<div class="modal-body">
						<div class="row" style="margin-bottom: 30px;">
							<div class="col-sm-12 form-inline">
								<lable>带队老师:</lable>
								<input id="showTeaName_5" type="text"
									class="form-control exeWidth" value="">
							</div>

						</div>
						<div class="row" style="margin-bottom: 30px;">
							<div class="col-sm-6">
								<select name="" id="selectCollege_5" class="form-control">
									<option value="" id="collegeID_5">请选择学院</option>
								</select>
							</div>
							<div class="col-sm-6">
								<select name="" id="selectTname_5" class="form-control">
									<option value="" id="teacherNmaeID_5">请选择老师</option>
								</select>
							</div>
						</div>



					</div>
				</center>
				<div class="modal-footer">
					<center>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							id="finished_update1">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="">取消</button>
					</center>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="TeaId_update" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" 	style="border:#3071a9 8px solid;width: 450px">
				<div class="modal-header" style="background:#3071a9; color:#FFF">
					<div class="glyphicon glyphicon-remove closeModal"
						data-dismiss="modal"></div>
					<h4 class="modal-title text-center">请选择教职工号</h4>
				</div>

				<center>
					<div class="modal-body">						
						<div class="row" style="margin-bottom: 30px;">
							<div class="col-sm-6">
								<select name="" id="selectCollege_6" class="form-control">
									<option value="" id="collegeID_6">请选择学院</option>
								</select>
							</div>
							<div class="col-sm-6">
								<select name="" id="selectTname_6" class="form-control">
									<option value="" id="teacherNmaeID_6">请选择工号</option>
								</select>
							</div>
						</div>



					</div>
				</center>
				<div class="modal-footer">
					<center>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							id="finished_update2">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="">取消</button>
					</center>
				</div>
			</div>
		</div>
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

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../js/jquery.min.js"></script>
	<!--datatable javascript-->
	<script src="../js/jquery.dataTables.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/bootbox.min.js"></script>
	<script type="text/javascript" src="../js/laydate.js"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/myNeed/practiceplanmaintain.js"></script>
	<script src="../js/kg.js"></script>
	<script src="../js/select2.full.js" ></script>
	<script>
		laydate.skin('yalan'); //切换皮肤，请查看skins下面皮肤库
		laydate({
			elem : '#oneSemesterTime'
		})
		laydate({
			elem : '#twoSemesterTime'
		});
		laydate({
			elem : '#Stime'
		})
		laydate({
			elem : '#Etime'
		});
	</script>
</body>
</html>
