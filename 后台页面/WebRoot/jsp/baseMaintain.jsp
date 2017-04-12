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
<title>实习基地维护</title>
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
<link rel="stylesheet" href="../css/practicebaseapply.css">
<link rel="stylesheet" href="../css/base_maintain.css">

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
					</a> <!-- <ul class="dropdown-menu">
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
							<li><a href="baseMaintain.jsp"></i>实习基地维护</a></li>
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
							<table id="baseMaintain" class="cell-border" cellspacing="0"
								width="100%">
								<thead>
									<tr bgcolor="#ECF1F5">
										<td colspan="2" id="button-left">
											<button type="button" class="btn btn-danger" id="delete">删除</button>
											<button type="button" class="btn btn-info" id="ZJ">增加</button>
										</td>
										<td colspan="5">
											<ul class="dropdown_show">
												<li><span class="icon-filter">筛选</span>
													<ul id="hide_ul" style="display:none;">
														<li>
															<form>
																<table class="table">
																	<tr>
																		<td hidden id="tag_0">${flag}</td>
																		<td hidden id="tag_1">${tag}</td>
																		<td>基地类型 <select name="status" id="shaiType"
																			style="width:150px;margin-top:0px;">
																				<option value="-1" id="shaiTypeh" selected>显示全部</option>
																		</select>  
																		</td>
																		<td>申报部门 <select name="status" id="shaiDept"
																			style="width:150px;margin-top:0px;">
																				<option value="-1" id="shaiDepth" selected>显示全部</option>
																		</select>  
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2">星级 <select name="status"
																			id="starLink" style="width:150px;margin-top:0px;">
																				<option value="-1" selected>显示全部</option>
																				<option value="1">一星级</option>
																				<option value="2">二星级</option>
																				<option value="3">三星级</option>
																				<option value="4">四星级</option>
																				<option value="5">五星级</option>
																		</select>  
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2" style="text-align:center">
																			<button type="reset" class="btn btn-primary">重置</button>
																			<button type="button" class="btn btn-primary"
																				id="finishshai">完成</button>
																		</td>
																	</tr>
																</table>
															</form>
														</li>
													</ul></li>
											</ul>
										</td>
										<td colspan="12" id="button-right"><a
											href="../templet/BaseInfotemplet.rar" class="btn btn-primary">点击下载导入模板</a>
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#import">导入</button>
											<button type="button" class="btn btn-primary"
												id="exportButton" data-toggle="modal" data-target="#export">导出</button></td>
									</tr>
									<tr>
										<th></th>
										<th>创建时间</th>
										<th>基地编号</th>
										<th>基地名称</th>
										<th>基地类型</th>
										<th>申报部门</th>
										<th>土地面积</th>
										<th>建筑面积</th>
										<th>可承担人数</th>
										<th>星级</th>
										<th hidden>通信地址</th>
										<th hidden>法定责任人</th>
										<th hidden>联系人姓名</th>
										<th hidden>联系人电话</th>
										<th hidden>面向专业</th>
										<th hidden>申请材料</th>
										<th hidden>申请人</th>
										<th hidden>有效周期</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody class="text-center" id="thistable">

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

				<!-- Modal 导入-->
				<div class="modal fade" id="import" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-sm">
						<form action="importBaseExcel.do" method="post" id="daoruform"
							enctype="multipart/form-data">
							<div class="modal-content" style="border:#3071a9 8px solid">
								<div class="modal-header" style="background:#3071a9; color:#FFF">
									<div class="glyphicon glyphicon-remove closeModal"
										data-dismiss="modal"></div>
									<h5 class="modal-title" id="myModalLabel">请选择要导入的文件</h5>
								</div>

								<center>
									<div class="modal-body" id="exportxsl" style="height:60px;">
										<input type="file" id="fileResource" name="fileResource">
									</div>
								</center>
								<div class="modal-footer">
									<center>
										<button type="button" class="btn btn-primary"
											id="certainimport">确定</button>
										<button type="button" class="btn btn-default"
											data-dismiss="modal" id="closeimport">取消</button>
									</center>
								</div>
							</div>
						</form>
					</div>
				</div>

				<!--单个删除确认对话框-->
				<div class="modal fade" id="deleteOneModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<!-- data-backdrop="static" 禁止点击弹框后面内容 -->
					<form class="form-horizontal" role="form">
						<div class="modal-dialog modal-sm ">
							<!-- modal-sm 小的  modal-lg 大的 -->
							<div class="modal-content" style="border:#4D719B 8px solid">
								<div class="modal-header" style="background:#4D719B; color:#FFF">
									<div class="glyphicon glyphicon-remove closeModal"
										data-dismiss="modal"></div>
									<h6 class="modal-title" id="myModalLabel"></h6>
								</div>
								<div class="modal-body" style="text-align: left;">
									<h5>您确定要删除吗？</h5>
								</div>
								<div class="modal-footer">

									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>

									<button type="button" class="btn btn-primary" id="delSubmit">
										确认</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
					</form>
				</div>


			</div>
			<!-- End Sidebar-->
			<!-- 弹出框-->
			<div class="modal fade" id="edit" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabe" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="border:#3071a9 8px solid">
						<div class="modal-header" style="background:#3071a9; color:#FFF">

							<div id="cleark" class="glyphicon glyphicon-remove closeModal"
								data-dismiss="modal"></div>

							<h4 class="modal-title text-center" id="myModalLabel">详情及修改</h4>
						</div>
						<div class="modal-body table-responsive">
							<div class="row">
								<div class="col-md-12">
									<form action="" method="post" class="form-horizontal"
										role="form" id="applyeditform">
										<table class="table" id="tableupdate">
											<tr>
												<td>基地编号：</td>
												<td><input type="text" id="baseid" disabled /></td>
												<td>基地名称 ：</td>
												<td><input type="text" id="basenamed" disabled></td>
											</tr>
											<tr>

												<td>基地类型 ：</td>
												<td><input type="text" id="basetyped" disabled></td>
												<td>申请部门 ：</td>
												<td><input type="text" id="dept0d" disabled></td>
											</tr>
											<tr>

												<td>土地面积 ：</td>
												<td><input type="text" id="landaread" disabled /></td>
												<td>建筑面积 ：</td>
												<td><input type="text" id="buildingaread" disabled></td>
											</tr>


											<tr>

												<td>可承担人数 ：</td>
												<td><input type="text" id="undertakeCountd" disabled></td>
												<td>联系人姓名 ：</td>
												<td><input type="text" id="usernamed" disabled></td>
											</tr>
											<tr>

												<td>联系人电话 ：</td>
												<td><input type="text" id="userphoned" disabled></td>
												<td>法定责任人 ：</td>
												<td><input type="text" id="personDuty" disabled></td>

											</tr>
											<tr id="resourcetr">
												<td>申请材料 ：</td>
												<td colspan="3" style="text-align:left;"><a
													id="resourced" href="#" style="color:#3071a9;">点击查看</a></td>
											</tr>
											<tr>
												<td>面向专业 ：</td>
												<td colspan="3"><div id="major_orientedd"
														style="border:#ccc 1px solid;height:80px;"></div></td>

											</tr>

											<tr>
												<td>通信地址 ：</td>
												<td colspan="3"><div id="linkAddressd"
														style="border:#ccc 1px solid;height:80px;"></div></td>

											</tr>
											<tr id="hidecol">
												<td>星级：</td>
												<td colspan="3">
													<div id="starget"></div>
												</td>
											</tr>
											<tr id="hidecol">

												<td>创建时间：</td>
												<td><input type="text" id="setdated" disabled></td>
												<td>截止日期 ：</td>
												<td><input type="text" id="adddate"
													class="laydate-icon"></td>

											</tr>

										</table>
									</form>
								</div>
							</div>
						</div>
						<div class="modal-footer table-responsive">
							<center>
								<button type="button" class="btn btn-primary" id="saverun">保存</button>
								<button type="button" class="btn btn-default" id="cleark"
									data-dismiss="modal">取消</button>
							</center>
						</div>
					</div>
				</div>
			</div>



			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="border:#416793 8px solid">
						<div class="modal-header">

							<div class="glyphicon glyphicon-remove closeModal  closeit"
								data-dismiss="modal"></div>


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

			<div class="modal fade" id="export" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width:400px;height:600px;">
					<div class="modal-content" style="border:#3071a9 8px solid;">
						<div class="modal-header" style="background:#3071a9; color:#FFF">

							<div id="daoclose" class="glyphicon glyphicon-remove closeModal"
								data-dismiss="modal"></div>

							<h4 class="modal-title" id="myModalLabel">请选择导出的基地</h4>
						</div>
						<form action="exportThisInfo.do" method="post">
							<div class="modal-body" id="daochu">
								<table>
									<tr>
										<td>基地类型:</td>
										<td><select class="form-control" name="basetype"
											id="daobaseh">
												<option value="-1" id="daobase">全部</option>
										</select></td>
									</tr>
									<tr>
										<td>申报部门:</td>
										<td><select class="form-control" id="daodepth"
											name="applydept">
												<option value="-1" id="daodept">全部</option>
										</select></td>
									</tr>
									<tr>
										<td>星级:</td>
										<td><select class="form-control" id="daostarh"
											name="star">
												<option value="-1">全部</option>
												<option value="0">无</option>
												<option value="1">一星级</option>
												<option value="2">二星级</option>
												<option value="3">三星级</option>
												<option value="4">四星级</option>
												<option value="5">五星级</option>
										</select></td>
									</tr>
								</table>

							</div>
							<div class="modal-footer">
								<center>
									<button type="button" class="btn btn-default" id="daoclose"
										data-dismiss="modal">取消</button>
									<button type="submit" class="btn btn-primary"
										id="confirmButton">确定</button>
								</center>
							</div>
						</form>
					</div>
				</div>
			</div>


		</div>
		<!--row end-->
	</div>
	<div class="clearfix"></div>

	<!--增加基地start  -->
	<div class="modal-content" id="add" style="border:#3071a9 8px solid;overflow-x:hidden;">
        <div class="modal-header" style="background:#3071a9; color:#FFF">
              <div id="closebas" class="glyphicon glyphicon-remove closeModal" data-dismiss="modal" ></div>
              <h4 class="modal-title text-center" id="myModalLabel">增加基地</h4>
       </div>
                                  
        <div class="modal-body table-responsive" id="modalbody">
             <div class="row" style="margin-right:0px;">
					<div class="col-md-12">
						<form action="increaseBaseInfo.do" method="post" id="myForm"
							enctype="multipart/form-data" class="form-horizontal" role="form">
							<div class="form-group">
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
										class="colle" name="deptRadio" id="deptRadio" value="1"
										checked="true"> 学院
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
								<div class="col-md-6" data-toggle="modal" data-target="#myModal"
									id="hit">
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
									<input type="text" class="form-control" id="building-area"
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
								<label class="col-md-3 control-label">法定负责人<span
									class="setTag">*</span></label>
								<div class="col-md-6">
									<input type="text" class="form-control" id="personName"
										name="personDuty" placeholder="">
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

								<label class="col-md-3 control-label">材料</label>
								<div class="col-md-6">
									<input type="file" id="applyfile" placeholder=""
										name="material_path">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">创建日期</label>
								<div class="col-md-6">
									<input type="text" id="validdastart" name="start_time"
										class="start_time laydate-icon" placeholder="">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">截止日期</label>
								<div class="col-md-6">
									<input type="text" id="validdaend" name="end_time"
										class="end_time laydate-icon" placeholder="">
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-8 col-sm-4">
									<button id="submitForm_0" type="button" class="btn btn-primary">确定</button>
								</div>
							</div>


						</form>
					</div>
				</div>                     	
                                  	
        </div>
   </div> 
	<!-- <div id="add">
		<div
			style="border:#3071a9 8px solid;background:#fff;">
			<div>

				<div id="closebas" class="glyphicon glyphicon-remove closeModal"
					data-dismiss="modal" style="font-size:24px;color:red;"></div>

				<h4 class="text-center" id="myModalLabel">增加基地</h4>
			</div>
			<div class="table-responsive">
				
			</div>
		</div>
	</div> -->
	<!--增加基地end -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../js/jquery.min.js"></script>
	<!--datatable javascript-->

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/jquery.dataTables.min.js"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/bootbox.min.js"></script>
	<script type="text/javascript" src="../js/laydate.js"></script>
	<script src="../js/star-rating.min.js"></script>
	<script src="../js/myNeed/baseapply.js"></script>
	<script src="../js/myNeed/base_maintain.js"></script>
	<script src="../js/kg.js"></script>
	<script>
		laydate.skin('yalan'); //切换皮肤，请查看skins下面皮肤库
		laydate({
			elem : '#validdastart'
		});
		laydate({
			elem : '#validdaend'
		});
		laydate({
			elem : '#adddate'
		});
	</script>
	<script>
		jQuery(document).ready(function() {

			$(".rating-kv").rating();

		});
	</script>
</body>
</html>