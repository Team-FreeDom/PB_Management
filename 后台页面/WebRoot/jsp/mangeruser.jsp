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
<link rel="stylesheet" href="../css/manageuser.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="../js/html5shiv.min.js"></script>
      <script src="../js/respond.min.js"></script>
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
							<li><a href="mangeruser.jsp">用户管理</a></li>
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
							name="formUserInfo" id="formUserInfo">
							<table id="manageusertable" class="cell-border" cellspacing="0"
								width="100%">

								<thead>
									<tr bgcolor="#ECF1F5">
										<td colspan="3" id="button-left">
											<button type="button" class="btn btn-danger" id="deleteOne">
												删除
												<tton>
												<button type="button" class="btn btn-info "
													data-toggle="modal" data-target="#add" id="addOne"
													onclick="clear()">
													增加
													<tton>
										</td>
										<td colspan="5"></td>
										<td colspan="3" id="button-right"><a
											href="../templet/PersonInfotemplet.rar"
											class="btn btn-primary">点击下载导入模板</a>
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#import">
												导入
												<tton>
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#lead" id="exportid">
													导出
													<tton></td>
									</tr>

									<tr style="background:#eeeff4">
										<th>选择</th>
										<th>员工编号</th>
										<th>身份属性</th>
										<th>姓名</th>
										<th>性别</th>
										<th>职称</th>
										<th>出生日期</th>
										<th>身份证号</th>
										<th>联系电话</th>
										<th>部门</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody class="text-center">

								</tbody>
								<thead>
									<tr>

										<td colspan="12"><label><input type="checkbox"
												name="0" class="ck-all" id="ck1" />全选</label> <label><input
												type="checkbox" name="0" class="ck-re" id="ck2" />反选</label></td>
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
					<form action="upInfo.do" method="post" role="form" id="userform"
						enctype="multipart/form-data">
						<div class="modal-content" style="border:#3071a9 8px solid">
							<div class="modal-header" style="background:#3071a9; color:#FFF">
								<div class="glyphicon glyphicon-remove closeModal"
									data-dismiss="modal"></div>
								<h4 class="modal-title text-center" id="myModalLabel">详情及修改</h4>
							</div>
							<div class="modal-body table-responsive">
								<table class="table">
									<tr>
										<td>员工编号 :</td>
										<td><input type="text" onFocus=this.blur()
											onClick="stop()" class="form-control" id="EworkerId"
											name="EworkerId"></td>
										<td>姓名 :</td>
										<td><input type="text" class="form-control" id="Ename"
											name="Ename"></td>
									</tr>
									<tr>
										<td>性别 :</td>
										<td id="sex-style2"><label class="radio-inline">
												<input type="radio" name="inlineRadioOptions" id="sex"
												value="男">男
										</label> <label class="radio-inline"> <input type="radio"
												name="inlineRadioOptions" id="sex" value="女">女
										</label></td>
										<td>出生年月 :</td>
										<td class="input-style"><input class="laydate-icon"
											id="demo" name="demo"></td>
									</tr>
									<tr>
										<td>职称:</td>
										<td><select class="form-control" name="Eworkerclass"
											id="Eworkerclass">
												<option id="EworkerclassId" value="">请选择</option>												
										</select></td>
										<td>身份属性 :</td>
										<td><select class="form-control" name="Estatus"
											id="Estatus">
												<option id="EstatusID" value="">请选择</option>

										</select></td>
									</tr>

									<tr>
										<td>部门 :</td>
										<td><select class="form-control" name="Edivision"
											id="Edivision">
												<option id="EdivisionID" value="">请选择</option>
										</select></td>
										<td>联系电话 :</td>
										<td><input type="text" class="form-control" id="Ephone"
											name="Ephone"></td>
									</tr>

									<tr>
										<td>身份证号 :</td>
										<td colspan="3"><input style="width:100%;" type="text"
											class="form-control" id="IDnumber" name="IDnumber"></td>
									</tr>

									<tr>
										<td>密码修改 :</td>
										<td colspan="3"><input style="width:100%;" type="text"
											class="form-control" id="Epassword" name="Epassword"></td>
									</tr>


								</table>
							</div>
							<div class="modal-footer table-responsive">
								<center>
									<button type="submit" class="btn btn-success" id="submit">确认修改</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消修改</button>
								</center>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- Modal 导入-->
			<div class="modal fade" id="import" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<form action="inputExcel.do" method="post" id="daoruform"
						enctype="multipart/form-data">
						<div class="modal-content" style="border:#3071a9 8px solid">
							<div class="modal-header" style="background:#3071a9; color:#FFF">
								<div class="glyphicon glyphicon-remove closeModal"
									data-dismiss="modal"></div>
								<h4 class="modal-title" id="myModalLabel">请选择要导入的文件</h4>
							</div>

							<center>
								<div class="modal-body" id="exportxsl" style="height:60px;">
									<input type="file" id="exampleInputFile" name="fileResource">
								</div>
							</center>
							<div class="modal-footer">
								<center>
									<button type="submit" class="btn btn-primary"
										id="certainimport">确定</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
								</center>
							</div>
						</div>
					</form>
				</div>
			</div>



			<!-- Modal 导入-->
			<div class="modal fade" id="lead" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<form action="exportPersonInfo.do" method="post" id="daochuform"
						enctype="multipart/form-data">
						<div class="modal-content" style="border:#3071a9 8px solid">
							<div class="modal-header" style="background:#3071a9; color:#FFF">
								<div class="glyphicon glyphicon-remove closeModal"
									data-dismiss="modal"></div>
								<h4 class="modal-title" id="myModalLabel">请选择导出的部门</h4>
							</div>

							<center>
								<div class="modal-body" id="exportxsl" style="height:60px;">
									<select name="dept" id="dept">
										<option id="EdeptID" value="1">全部</option>
									</select>
								</div>
							</center>
							<div class="modal-footer">
								<center>
									<button type="submit" class="btn btn-primary"
										id="certainExport">确定</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
								</center>
							</div>
						</div>
					</form>
				</div>
			</div>

			<div class="modal fade" id="add" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabe" aria-hidden="true">
				<div class="modal-dialog">
					<form action="addInfo.do" method="post" role="form"
						id="adduserform" name="adduserform" enctype="multipart/form-data">
						<div class="modal-content" style="border:#3071a9 8px solid">
							<div class="modal-header" style="background:#3071a9; color:#FFF">
								<div class="glyphicon glyphicon-remove closeModal"
									data-dismiss="modal"></div>
								<h4 class="modal-title text-center" id="myModalLabel">增加人员信息</h4>
							</div>
							<div class="modal-body table-responsive">
								<table class="table">
									<tr>
										<td>员工编号:</td>
										<td><input type="text" id="workerId" name="workerId"
											class="form-control"></td>
										<td>姓名 :</td>
										<td><input type="text" id="name" name="name"
											class="form-control"></td>
									</tr>
									<tr>
										<td>性 别:</td>
										<td id="sex-style"><label class="radio-inline"> <input
												type="radio" name="inlineRadioOptions" id="sex1" class="sex"
												value="男">男
										</label> <label class="radio-inline"> <input type="radio"
												name="inlineRadioOptions" id="sex2" class="sex" value="女">女
										</label></td>

										<td>出生年月:</td>
										<td class="input-style"><input class="laydate-icon"
											id="demo2" name="demo2"></td>
									</tr>


									<tr>
										<td>职称:</td>
										<td><select class="form-control" name="Awkclass"
											id="Awkclass">
												<option id="AworkerclassId" value="1">请选择</option>												
										</select></td>
										<td>身份属性:</td>
										<td><select class="form-control" name="Astatus"
											id="Astatus">
												<option id="AstatusID" value="1">请选择</option>
										</select></td>
									</tr>

									<tr>
										<td>部 门:</td>
										<td><select class="form-control" name="Adivision"
											id="Adivision">
												<option id="AdivisionID" value="1">请选择</option>
										</select></td>
										<td>联系电话:</td>
										<td><input type="text" id="phone" name="phone"
											class="form-control"></td>
									</tr>

									<tr>
										<td>身份证号:</td>
										<td colspan="3"><input style="width:100%;" type="text"
											id="IDnumber1" name="IDnumber1" class="form-control"></td>
									</tr>

									<tr>
										<td>设置密码:</td>
										<td colspan="3"><input style="width:100%;" type="text"
											id="password" name="password" class="form-control"></td>
									</tr>


								</table>
							</div>
							<div class="modal-footer table-responsive">
								<center>
									<button type="button" class="btn btn-primary" id="save">增加</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
								</center>
							</div>
						</div>
					</form>
				</div>
			</div>


		</div>
		<!--row end-->
	</div>
	<div class="clearfix"></div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../js/jquery.min.js"></script>
	<!--datatable javascript-->
	<script src="../js/jquery.dataTables.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/laydate.js"></script>
	<script src="../js/bootbox.min.js"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/kg.js"></script>
	<script src="../js/myNeed/manageuser.js"></script>
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
	<script>
		
	</script>
</body>
</html>
