<%@ page language="java" import="java.util.*,com.base.po.*,com.base.dao.*,com.base.daoImpl.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="UTF-8">
    <title>湖南农业大学土地管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/index_main.css">
    <link rel="stylesheet" href="../css/calendar.css" media="screen">

	<style>
	.table{ background:#FFF;}
	.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {border-top:0px solid #ddd;}
    </style>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    
  </head>
  <body>
    <div class="navbar" role="navigation">
      <div class="container-fluid container-nav">
          <!-- 点击收缩左边的菜单栏  + 缩小后左边菜单栏的显示 -->
          <ul class="nav navbar-nav navbar-actions navbar-left">
              <li class="visible-md visible-lg"><a href="index.html#"><i class="icon-th-large"></i></a></li>
              <li class="visible-xs visible-sm"><a href="index.html#"><i class="icon-align-justify"></i></a></li>
          </ul>
      
          <span class="teachCenterTitle">基地管理系统</span>
          <!-- Navbar Left -->
    
          <!-- Navbar Right -->
          <div class="navbar-right">
              <!-- Notifications -->
              <ul class="notifications" avalonctrl="subNotificationsController">
                  <li class="hidden-sm hidden-xs">
                      <a href="getMessage.do" class="dropdown-toggle notification-icon">
                          <i class="icon-envelope"><span class="badge msg">0</span></i>
                              <!--ms-if-->
                      </a>   
                     <!--  <ul class="dropdown-menu">
							<li class="dropdown-header" style="text-align: center;">
                          <a href="getMessage.do"><strong>未读消息列表</strong></a>
                          </li>    
                          <li class="dropdown-menu-footer text-center">
                              <a href="getMessage.do">更多消息</a>
                          </li>
						</ul>  -->
                  </li>
                  <li>
                      <a href="../loginout.do" class="dropdown-toggle notification-icon">
                          <i class="icon-remove"></i>
                      </a>
                  </li>
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
							<a href="#" target="_blank"><img src="../image/manage-logo.png" alt=""></a>
						</div>
						
						<!-- Sidebar Menu-->
						<div class="sidebar-menu">
							<nav id="menu" class="nav-main" role="navigation">
								<ul class="nav nav-sidebar">
									<div class="panel-body text-center">
										<div class="bk-avatar">
											<a href="#"><img class="img-circle bk-img-60" alt="" src="" id="imageMain"></a>
											<!--ms-if-->
										</div>
										<div class="bk-padding-top-10">
											<i class="icon-circle text-success"></i> 
											<small>罗旭</small>
											<!--ms-if-->
										</div>
									</div>
									<div class="divider2"></div>
									<li class="menuItem">
										<a href="index.do">
											<i class="icon-home" aria-hidden="true"></i><span>主界面</span>
										</a>
									</li>
									
									 <li class="menuItem nav-parent">
										<a>
											<i class="icon-copy" aria-hidden="true"></i><span>我的工作</span>
										</a>
										<ul class="nav nav-children">
											<li><a href="myRent.jsp"><span class="text">我的租赁</span></a></li>
											<li><a href="#"><span class="text">我的实习</span></a></li>
											<li><a href="#"><span class="text">我的报修</span></a></li>
											<li><a href="#"><span class="text">我的基地</span></a></li>
										</ul>
									</li>

									<li class="menuItem nav-parent">
										<a>
											<i class="icon-copy" aria-hidden="true"></i><span>审批工作</span>
										</a>
										<ul class="nav nav-children">
                                            <li><a href="rent-approve.jsp"><span class="text">租赁审批</span></a></li>
                                            <li><a href="#"><span class="text">实习审批</span></a></li>
                                            <li><a href="#"><span class="text">维修审批</span></a></li>
                                            <li><a href="#"><span class="text">基地审批</span></a></li>
										</ul>
									</li>

										<li class="menuItem nav-parent">
										<a>
											<i class="icon-copy" aria-hidden="true"></i><span>数据管理</span>
										</a>
										<ul class="nav nav-children">
                                        	<li><a href="notification.do"><span class="text"> 发布通知公告</span></a></li>
                                        	<li><a href="land_modle.jsp"><span class="text"> 土地布局设置</span></a></li>
                                            <li><a href="fieldRent_maintain.jsp"><span class="text"> 土地租赁维护</span></a></li>
                                            <li><a href="#"><span class="text"> 实习基地维护</span></a></li>
                                            <li><a href="#"><span class="text"> 实习计划维护</span></a></li>
                                            <li><a href="user.jsp"><span class="text"> 系统用户维护</span></a></li>
                                            <li><a href="system_power.jsp"><span class="text"> 系统权限设置</span></a></li> 
										</ul>
									</li>
									 <li class="menuItem nav-parent">
										<a>
											<i class="icon-copy" aria-hidden="true"></i><span>统计分析</span>
										</a>
										<ul class="nav nav-children">
											<li><a href="#"><span class="text">租赁统计</span></a></li>
											<li><a href="#"><span class="text">实习分析</span></a></li>
											<li><a href="#"><span class="text">实习基地统计</span></a></li>
										</ul>
									</li>									
									
								</ul>
							</nav>
						</div>
						<!-- End Sidebar Menu-->
					</div>
					<!-- Sidebar Footer-->
					<div class="sidebar-footer">
						<div class="copyright text-center">
							<div>湖南农业大学版权所有 </div>
						</div>
					</div>
					<!-- End Sidebar Footer-->
				</div>   <!-- End Sidebar--> 
		        
                
			<div class="main " style="min-height: 584px;">
					<!-- 当前地址导航 -->
					<div class="page-header row">
						<div class="pull-left">
							<ol class="breadcrumb visible-sm visible-md visible-lg">
                            	<li><a>位置  :</a></li>
								<li><a href="index.do"><i class=" icon-home"></i>首页</a></li>
							</ol>
						</div>
						<div class="pull-right">
							<ol class="breadcrumb visible-sm visible-md visible-lg wz">
								<li><a href="#"><i class=" icon-building"></i>基地申报</a></li>
                                <li><a href="field-rent.jsp"><i class="icon-legal"></i>土地租赁</a></li>
                                <li><a href="#"><i class="icon-user"></i>实习申请</a></li>
                                <li><a href="#"><i class="icon-home"></i>报修申请</a></li>
							</ol>
						</div>
					</div>
					<!-- 主面板内容 -->
					<div class="row form">
						<div class="col-lg-12">
							<div id="post1" class="col-md-3">
								<p>通知公告</p>
                                <div class="post">
                                     <div>${notification } </div>
                                </div>                                
							</div>
							<div id="information1" class=" col-md-offset-1 col-md-3 ">
								<p>个人消息提示</p>
                                <div class="information">
                                    <c:forEach items='${messageList}' var='message'>
                                            <p>${message.title}<span name="date" style="text-align:right;"></span></p>
                                    </c:forEach>
                                </div>
							</div>

							
							<div id="calendar" class="col-md-4" style="background:none;"></div>
                            <script src="../js/calendar.jquery.min.js"></script>
    						<script src="../js/jquery-ui-datepicker.min.js"></script>
                            <script>
                                $('#calendar').datepicker({
                                    inline: true,
                                    firstDay: 1,
                                    showOtherMonths: true,
                                    dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
                                });
                            </script>					
						</div>
						<div id="caption" class="col-lg-12">
                        	<p>今日代办</p>
						</div>
						<div id="data" class="col-lg-12">
							<div class="col-md-2 col-xs-2 data_display">
								<table class="table" id='tvb'>
                                    <tr>
                                        <td style="font-size:50%">注册用户</td>
                                        <td rowspan="2"><span class="icon-user" style="background:#FA8562;"></span></td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:120%" id="userCount">${userCount }</td>
                                    </tr>
                                </table>
							</div>
							
							<div class=" col-md-offset-1 col-xs-offset-1 col-md-2 col-xs-2 data_display">
								<table class="table">
                                    <tr>
                                        <td style="font-size:50%">租赁申请</td>
                                        <td rowspan="2" ><span class="icon-th-list" style="background:#ADC885;"></span></td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:120%" id="applyCount">${applyCount }</td>
                                    </tr>
                                </table>

							</div>

							<div class=" col-md-offset-1 col-xs-offset-1 col-md-2 col-xs-2 data_display">
								<!--<a href="message.html">-->
								<table class="table" width="100%">
                                    <tr style=" border-color:#FFF">
                                        <td style="font-size:50%">实习申请</td>
                                        <td rowspan="2"><span class="icon-list-alt" style="background:#63ADDC;"></span></td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:120%" id="practiceCount">0</td>
                                    </tr>
                                </table>
							</div>
                            
                            <div class=" col-md-offset-1 col-xs-offset-1 col-md-2 col-xs-2 data_display">
								<!--<a href="message.html">-->
								<table class="table">
                                    <tr>
                                        <td style="font-size:50%">维修申请</td>
                                        <td rowspan="2"><span class="icon-wrench" style="background:#CC82B5;"></span></td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:120%" id="maintainCount">0</td>
                                    </tr>
                                </table>
							</div>
						</div>

					</div>
				</div><!-- End Sidebar-->                 
                
                    
            
        </div><!--row end-->
    </div>
	<div class="clearfix"></div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="../dist/jquery.cokie.min.js"></script>  
    <script src="../js/kg.js"></script>
   
  </body>
</html>
