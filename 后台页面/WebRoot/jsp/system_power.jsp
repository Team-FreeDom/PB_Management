<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 -->
 <!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="UTF-8">
    <title>基地管理系统-权限管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/style.css">
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
                      <!-- <ul class="dropdown-menu">
                         <li class="dropdown-header" style="text-align: center;">
                          <a href="getMessage.do"><strong>未读消息列表</strong></a>
                          </li>    
                          <li class="dropdown-menu-footer text-center">
                              <a href="getMessage.do">更多消息</a>
                          </li>
                      </ul> -->
    
                  </li>
                  <li>
                      <a href="../loginout.do" class="dropdown-toggle notification-icon" data-toggle="modal">
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
						<div class="sidebar-menu" style="height: 384px;">
							<nav id="menu" class="nav-main" role="navigation">
								<ul class="nav nav-sidebar">
									<div class="panel-body text-center">
										<div class="bk-avatar">
											<a href="#"><img class="img-circle bk-img-60" alt="" src="../image/psu.jpg"></a>
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
                                            <li><a href="#"><span class="text">基地审批</span></a></li>
                                            <li><a href="#"><span class="text">基地审批</span></a></li>

										</ul>
									</li>

										<li class="menuItem nav-parent">
										<a>
											<i class="icon-copy" aria-hidden="true"></i><span>数据管理</span>
										</a>
										<ul class="nav nav-children">
                                        	<li><a href="#"><span class="text"> 发布通知公告</span></a></li>
                                        	<li><a href="land_modle.jsp"><span class="text"> 土地布局设置</span></a></li>
                                        	<li><a href="#"><span class="text"> 实习基地维护</span></a></li>
                                            <li><a href="fieldRent_maintain.jsp"><span class="text"> 土地租赁维护</span></a></li>
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
					<div class="page-header">
						<div class="pull-left">
							<ol class="breadcrumb visible-sm visible-md visible-lg">
                            	<li><a>位置</a></li>
								<li><a href="system_power.jsp"><i class=" icon-home"></i>系统权限设置</a></li>
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
                    <div class="col-md-12" style="background:#FFF">
                    		<div style="margin:20px 0; padding:20px 0;">
                              <form class="form-inline" role="form">
                                <div class="form-group">
                                  <label>身份：</label>
                                </div>
                                <div class="form-group">
                                  <input type="text" class="form-control input-sm" id="role_text" placeholder="权限角色">
                                </div>
                                <button  class="btn btn-default" id='role_add' style="display:none;">增加</button>
                                <button  class="btn btn-default" id='role_del' style="display:none;">删除</button>
                                <button  class="btn btn-default" id='role_name' >修改角色名</button>
                                <button  class="btn btn-success" id='pow_update'>更新到服务器</button>
                              </form>  
                              </div>   
                    </div>

						<div class="col-md-12" style="background:#FFF">
							<div class="col-md-4" >   
                              <div class="list-group" id="sflist">
                                <a href="#" class="list-group-item active">
                                  身份列表
                                </a>
                              </div>           
							</div>
                            <div class="col-md-8">
                              <ul class="list-group" id='pow_list'>
                                <li class="list-group-item list-group-item-info"><div class="text-center">权限设置</div></li>
                                <li class="list-group-item"><span style="float:left; margin-right:20px;">业务审批权限：</span>
                                  <ul class="list-inline" id="powlist1">
                                    <li><label><input type="checkbox"> 土地租赁申请</label></li>
                                    <li><label><input type="checkbox"> 实习申请</label></li>
                                    <li><label><input type="checkbox"> 实习基地申请</label></li>
                                    <li><label><input type="checkbox"> 基地维修申请</label></li>
                                  </ul>                                
                                </li>
                                <li class="list-group-item"><span style="float:left; margin-right:20px;">数据维护权限：</span>
                                  <ul class="list-inline" id="powlist2">
                                    <li><label><input type="checkbox"> 土地租赁申请</label></li>
                                    <li><label><input type="checkbox"> 实习申请</label></li>
                                    <li><label><input type="checkbox"> 实习基地申请</label></li>
                                    <li><label><input type="checkbox"> 基地维修申请</label></li>
                                  </ul>                                    
                                </li>
                                <li class="list-group-item"><span style="float:left; margin-right:20px;">数据分析权限：</span>
                                  <ul class="list-inline" id="powlist3">
                                    <li><label><input type="checkbox"> 土地租赁申请</label></li>
                                    <li><label><input type="checkbox"> 实习申请</label></li>
                                    <li><label><input type="checkbox"> 实习基地申请</label></li>
                                    <li><label><input type="checkbox"> 基地维修申请</label></li>
                                  </ul> 
                                </li>
     
                              </ul>                            
                            </div>
						</div>

						<div class="col-lg-12 form-group">

						</div>

					</div>
				</div><!-- End Sidebar-->                 
                
                    
            
        </div><!--row end-->
    </div>
	<div class="clearfix"></div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery.min.js"></script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
     <script src="../js/bootbox.min.js"></script>
      <script src="../dist/jquery.cokie.min.js"></script> 
     <script src="../js/lodash.min.js"></script>
    <script src="../js/kg.js"></script>
    <script src="../js/set_power.js"></script>
  </body>
</html>
