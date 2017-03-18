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
                                                    .table {
                                                        background: #FFF;
                                                    }
                                                    .table > tbody > tr > td,
                                                    .table > tbody > tr > th,
                                                    .table > tfoot > tr > td,
                                                    .table > tfoot > tr > th,
                                                    .table > thead > tr > td,
                                                    .table > thead > tr > th {
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
                                                            <li class="visible-md visible-lg">
                                                                <a>
                                                                    <i class="icon-th-large"></i>
                                                                </a>
                                                            </li>
                                                            <li class="visible-xs visible-sm">
                                                                <a>
                                                                    <i class="icon-align-justify"></i>
                                                                </a>
                                                            </li>
                                                        </ul>

                                                        <span class="teachCenterTitle">基地管理系统</span>
                                                        <!-- Navbar Left -->

                                                        <!-- Navbar Right -->
                                                        <div class="navbar-right">
                                                            <!-- Notifications -->
                                                            <ul class="notifications" avalonctrl="subNotificationsController">
                                                                <li class="hidden-sm hidden-xs">
                                                                    <a href="getMessage.do" class="dropdown-toggle notification-icon">
                                                                        <i class="icon-envelope">
                                                                            <span class="badge msg"></span>
                                                                        </i>
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
                                                                    <a href="#" target="_blank">
                                                                        <img src="../image/manage-logo.png" alt=""></a>
                                                                    </div>

                                                                    <!-- Sidebar Menu-->
                                                                    <div class="sidebar-menu">
                                                                        <nav id="menu" class="nav-main" role="navigation">
                                                                            <ul class="nav nav-sidebar">
                                                                                <div class="panel-body text-center">
                                                                                    <div class="bk-avatar">
                                                                                        <a href="user.jsp">
                                                                                            <img class="img-circle bk-img-60" alt="" src="" id="imageMain"></a>
                                                                                            <!--ms-if-->
                                                                                        </div>
                                                                                        <div class="bk-padding-top-10">
                                                                                            <i class="icon-circle text-danger"></i>
                                                                                            <small></small>
                                                                                            <!--ms-if-->
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="divider2"></div>
                                                                                    <li class="menuItem">
                                                                                        <a href="index.do">
                                                                                            <i class="icon-home" aria-hidden="true"></i>
                                                                                            <span>主界面</span>
                                                                                        </a>
                                                                                    </li>

                                                                                    <li class="menuItem nav-parent">
                                                                                        <a>
                                                                                            <i class="icon-copy" aria-hidden="true"></i>
                                                                                            <span>我的工作</span>
                                                                                        </a>
                                                                                        <ul class="nav nav-children">
                                                                                            <li>
                                                                                                <a href="myRent.jsp">
                                                                                                    <span class="text">我的租赁</span>
                                                                                                </a>
                                                                                            </li>
                                                                                           
                                                                                            <li>
                                                                                                <a href="myrepair.jsp">
                                                                                                    <span class="text">我的报修</span>
                                                                                                </a>
                                                                                            </li>
                                                                                            <li>
                                                                                                <a href="myBase.jsp">
                                                                                                    <span class="text">我的基地</span>
                                                                                                </a>
                                                                                            </li>
                                                                                            <li><a href="practiapply.jsp"><span class="text">实习申请</span></a></li>
                                                                                        </ul>
                                                                                    </li>

                                                                                    <li class="menuItem nav-parent" ${(visitRight[0]==0&&visitRight[1]==0&&visitRight[2]==0)?"style='display:none;'":" "}>
										<a>
											<i class="icon-copy" aria-hidden="true"></i><span>审批工作</span>
										</a>
										<ul class="nav nav-children">
                                            <li ${visitRight[0]==0?"style='display:none;'":" "}><a href="rent-approve.jsp"><span class="text">租赁审批</span></a></li>
                                           
                                            <li ${visitRight[2]==0?"style='display:none;'":" "}><a href="baseCheck.jsp"><span class="text">基地审批</span></a></li>
                                            <li ${visitRight[1]==0?"style='display:none;'":" "}><a href="repairApprove.jsp"><span class="text">维修审批</span></a></li>
                                            
										</ul>
									</li>

										<li class="menuItem nav-parent">
										<a>
											<i class="icon-copy" aria-hidden="true"></i><span>数据管理</span>
										</a>
										<ul class="nav nav-children">
                                        	<li ${visitRight[3]==0?"style='display:none;'":" "}><a href="notification.do"><span class="text"> 发布通知公告</span></a></li>
                                        	<li ${visitRight[4]==0?"style='display:none;'":" "}><a href="land_modle.jsp"><span class="text"> 土地布局设置</span></a></li>
                                            <li ${visitRight[6]==0?"style='display:none;'":" "}><a href="fieldRent_maintain.jsp"><span class="text"> 土地租赁维护</span></a></li>
                                            <li ${visitRight[5]==0?"style='display:none;'":" "}><a href="baseMaintain.jsp"><span class="text"> 实习基地维护</span></a></li>
                                            <li ${visitRight[11]==0?"style='display:none;'":" "}><a href="Repairmanage.jsp"><span class="text"> 报修信息维护</span></a></li>
                                            <li ${visitRight[7]==0?"style='display:none;'":" "}><a href="practicePlanMaintain.jsp"><span class="text"> 实习计划维护</span></a></li>
                                            <li ${visitRight[10]==0?"style='display:none;'":" "}><a href="start.jsp"><span class="text"> 工作计划制定</span></a></li>
                                            <li ${visitRight[8]==0?"style='display:none;'":" "}><a href="mangeruser.jsp"><span class="text"> 系统用户维护</span></a></li>
                                            <li ${visitRight[9]==0?"style='display:none;'":" "}><a href="system_power.jsp"><span class="text"> 系统权限设置</span></a></li>
										</ul>
									</li>
                                                                                    <li class="menuItem nav-parent">
                                                                                        <a>
                                                                                            <i class="icon-copy" aria-hidden="true"></i>
                                                                                            <span>统计分析</span>
                                                                                        </a>
                                                                                        <ul class="nav nav-children">
                                                                                            <li>
                                                                                                <a href="#">
                                                                                                    <span class="text">租赁统计</span>
                                                                                                </a>
                                                                                            </li>
                                                                                            <li>
                                                                                                <a href="#">
                                                                                                    <span class="text">实习分析</span>
                                                                                                </a>
                                                                                            </li>
                                                                                            <li>
                                                                                                <a href="#">
                                                                                                    <span class="text">实习基地统计</span>
                                                                                                </a>
                                                                                            </li>
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
                                                                            <div>湖南农业大学版权所有
                                                                            </div>
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
                                                                                <li>
                                                                                    <a>位置 :</a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="newdetail.jsp">
                                                                                        <i class=" icon-home"></i>新闻详情</a>
                                                                                </li>
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
                                                                        <div class="article">
                                                                        <div class="title">
                                                                            <h1 class="text-center">${notification.title }</h1>
                                                                        </div>
                                                                        <div class="content" id="newdetail">
                                                                          <!-- 在此插入新闻详情。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。-->
                                                                             ${notification.message }
                                                                        </div>
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

                                                    </body>
                                                </html>
