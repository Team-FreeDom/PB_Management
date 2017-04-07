<%@ page language="java"
	import="java.util.*,com.base.po.*,com.base.dao.*,com.base.daoImpl.*"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<title>湖南农业大学土地管理系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Bootstrap -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/font-awesome.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/index_main.css">
<link rel="stylesheet" href="../css/calendar.css" media="screen">

<style>
.table {
	background: #FFF;
}

.table>tbody>tr>td,.table>tbody>tr>th,.table>tfoot>tr>td,.table>tfoot>tr>th,.table>thead>tr>td,.table>thead>tr>th
	{
	border-top: 0 solid #ddd;
}
</style>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

</head>
<body>
	<div class="navbar" role="navigation">
		<div class="container-fluid container-nav">
			<!-- 点击收缩左边的菜单栏  + 缩小后左边菜单栏的显示 -->
			<ul class="nav navbar-nav navbar-actions navbar-left">
				<li class="visible-md visible-lg"><a> <i
						class="icon-th-large"></i>
				</a></li>
				<li class="visible-xs visible-sm"><a> <i
						class="icon-align-justify"></i>
				</a></li>
			</ul>

			<span class="teachCenterTitle">基地管理系统</span>
			<!-- Navbar Left -->

			<!-- Navbar Right -->
			<div class="navbar-right">
				<!-- Notifications -->
				<ul class="notifications" avalonctrl="subNotificationsController">
					<li class="hidden-sm hidden-xs"><a href="getMessage.do"
						class="dropdown-toggle notification-icon"> <i
							class="icon-envelope"> <span class="badge msg"></span>
						</i> <!--ms-if-->
					</a> <!--  <ul class="dropdown-menu">
							<li class="dropdown-header" style="text-align: center;">
                          <a href="getMessage.do"><strong>未读消息列表</strong></a>
                          </li>
                          <li class="dropdown-menu-footer text-center">
                              <a href="getMessage.do">更多消息</a>
                          </li>
						</ul>  --></li>
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
						<a href="index.do"> <img src="../image/manage-logo.png"
							alt=""></a>
					</div>

					<!-- Sidebar Menu-->
					<div class="sidebar-menu">
						<nav id="menu" class="nav-main" role="navigation">
							<ul class="nav nav-sidebar">
								<div class="panel-body text-center">
									<div class="bk-avatar">
										<a href="user.jsp"> <img class="img-circle bk-img-60"
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
										class="icon-home" aria-hidden="true"></i> <span>主界面</span>
								</a></li>

								<li class="menuItem nav-parent"><a> <i
										class="icon-copy" aria-hidden="true"></i> <span>我的工作</span>
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
									${(visitRight[0]==0&&visitRight[1]==0&&visitRight[2]==0)?"style='display:none;'":" "}><a>
										<i class="icon-copy" aria-hidden="true"></i> <span>审批工作</span>
								</a>
									<ul class="nav nav-children">
										<li ${visitRight[0]==0?"style='display:none;'":" "}><a
											href="rent-approve.jsp"> <span class="text">租赁审批</span>
										</a></li>

										<li ${visitRight[2]==0?"style='display:none;'":" "}><a
											href="baseCheck.jsp"> <span class="text">基地审批</span>
										</a></li>
										<li ${visitRight[1]==0?"style='display:none;'":" "}><a
											href="repairApprove.jsp"> <span class="text">维修审批</span>
										</a></li>

									</ul></li>

								<li class="menuItem nav-parent"
									${(visitRight[3]==0&&visitRight[4]==0&&visitRight[5]==0&&visitRight[6]==0&&visitRight[7]==0&&visitRight[8]==0&&visitRight[9]==0&&visitRight[10]==0&&visitRight[11]==0)?"style='display:none;'":" "}><a>
										<i class="icon-copy" aria-hidden="true"></i> <span>数据管理</span>
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
									</ul></li>
								<li class="menuItem nav-parent"
									${visitRight[12]==0?"style='display:none;'":" "}><a> <i
										class="icon-copy" aria-hidden="true"></i> <span>统计分析</span>
								</a>
									<ul class="nav nav-children">

										<li ${visitRight[12]==0?"style='display:none;'":" "}><a
											href="statisticData.jsp"> <span class="text">实习分析</span>
										</a></li>

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
							<li><a href="newlist.do"> <i class=" icon-home"></i>新闻列表
							</a></li>
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
						<div class="list-group">
							<!-- <a class="list-group-item active"> 公告列表 </a> <a href="#"
								class="list-group-item">Dapibus ac facilisis in</a> <a href="#"
								class="list-group-item">Morbi leo risus</a> <a href="#"
								class="list-group-item">Porta ac consectetur ac</a> <a href="#"
								class="list-group-item ">Vestibulum at eros</a> <a href="#"
								class="list-group-item">Dapibus ac facilisis in</a> <a href="#"
								class="list-group-item">Morbi leo risus</a> <a href="#"
								class="list-group-item">Porta ac consectetur ac</a> <a href="#"
								class="list-group-item">Vestibulum at eros</a> -->
							<a class="list-group-item active"> 公告列表 </a>
							<c:forEach items='${notifications}' var='notification'>

								<a href="newdetail.do?id=${notification.id }"
									class="list-group-item">${notification.title }</a>

							</c:forEach>


						</div>
					</div>
					<div class="col-lg-12">
						<!-- <nav>
                                                                                <ul class="pagination">
                                                                                    <li class="disabled">
                                                                                        <a href="#">&laquo;</a>
                                                                                    </li>
                                                                                    <li class="active">
                                                                                        <a href="#">1
                                                                                            <span class="sr-only">(current)</span>
                                                                                        </a>
                                                                                    </li>
                                                                                    <li>
                                                                                        <a href="#">2</a>
                                                                                    </li>
                                                                                    <li>
                                                                                        <a href="#">3</a>
                                                                                    </li>
                                                                                    <li>
                                                                                        <a href="#">4</a>
                                                                                    </li>
                                                                                    <li>
                                                                                        <a href="#">5</a>
                                                                                    </li>
                                                                                    <li>
                                                                                        <a href="#">&raquo;</a>
                                                                                    </li>
                                                                                </ul>
                                                                            </nav> -->

						<nav>
							<ul class="pagination">
								<!-- 上一页 按钮 -->

								<c:choose>
									<c:when test="${page != 1}">
										<li><a href="newlist.do?page=${page-1}">上一页</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="newlist.do?page=${page}">上一页</a></li>
									</c:otherwise>
								</c:choose>

								<!-- 页数列表 -->
								<c:forEach items="${pageList}" var="item">
									<c:choose>
										<c:when test="${item == page}">
											<li><a href="newlist.do?page=${item}"
												class="currentPage">${item}</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="newlist.do?page=${item}">${item}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<!-- 下一页 按钮 -->
								<c:choose>
									<c:when test="${page != totalPages}">
										<li><a href="newlist.do?page=${page+1}">下一页</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="newlist.do?page=${page}">下一页</a></li>
									</c:otherwise>
								</c:choose>

								<!-- 直接跳转 -->
								<li><a style="padding: 3px 12px;">共${totalPages}页 -向<input
										type="text" id="jumpTo" />页<input type="button" value="跳转"
										onclick="jumpTo(${totalPages})" /></a></li>


							</ul>


						</nav>
					</div>
				</div>
			</div>
			<!-- End Sidebar-->

		</div>
		<!--row end-->
	</div>
	<div class="clearfix"></div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!--[if lt IE 9]>
		<script src="../js/html5shiv.min.js"></script>
		<script src="../js/respond.min.js"></script>
	<![endif]-->
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/bootbox.min.js"></script>
	<script src="../dist/jquery.cokie.min.js"></script>
	<script src="../js/kg.js"></script>
	<script>
		function jumpTo(maxPage) {
			var page = $("#jumpTo").val();
			if (page > maxPage || page < 1) {
				bootbox.dialog({
					message : "对不起，无法到达该页",
				});
			} else {
				$('body').load('newlist.do?page=' + page);
			}
		}
	</script>
</body>
</html>
