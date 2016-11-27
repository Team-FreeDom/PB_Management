<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <title>土地租赁</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    
    <link rel="stylesheet" href="../css/jquery.dataTables.min.css">

	<link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/gridstack.min.css" />
	<link rel="stylesheet" href="../css/style.css">
    <style>
    .bottom{
	margin-top:10px;
	}
	.dataTables_length{
	margin-left:5%;}
    </style>
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
                      <a href="notification.jsp" class="dropdown-toggle notification-icon">
                          <i class="icon-envelope"><span class="badge msg">0</span></i>
                              <!--ms-if-->
                      </a>
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
						<div class="sidebar-menu" >
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
											<li><a ><span class="text">我的实习</span></a></li>
											<li><a><span class="text">我的报修</span></a></li>
											<li><a><span class="text">我的基地</span></a></li>
										</ul>
									</li>

									<li class="menuItem nav-parent">
										<a>
											<i class="icon-copy" aria-hidden="true"></i><span>审批工作</span>
										</a>
										<ul class="nav nav-children">
                                            <li><a href="rent-approve.jsp"><span class="text">租赁审批</span></a></li>
                                            <li><a><span class="text">实习审批</span></a></li>
                                            <li><a><span class="text">基地审批</span></a></li>
                                            <li><a><span class="text">基地审批</span></a></li>

										</ul>
									</li>

										<li class="menuItem nav-parent">
										<a>
											<i class="icon-copy" aria-hidden="true"></i><span>数据管理</span>
										</a>
										<ul class="nav nav-children">
                                        	<li><a href="notification.jsp"><span class="text"> 发布通知公告</span></a></li>
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
					<div class="page-header">
						<div class="pull-left">
							<ol class="breadcrumb visible-sm visible-md visible-lg">
                            	<li><a>位置</a></li>
								<li><a href="field-rent.jsp">土地租赁</a></li>
							</ol>
						</div>
						<div class="pull-right">
							<ol class="breadcrumb visible-sm visible-md visible-lg wz">
								<li><a><i class=" icon-building"></i>基地申报</a></li>
                                <li><a href="field-rent.jsp"><i class="icon-legal"></i>土地租赁</a></li>
                                <li><a><i class="icon-user"></i>实习申请</a></li>
                                <li><a><i class="icon-home"></i>报修申请</a></li>
							</ol>
						</div>
					</div>
					<!-- 主面板内容 -->
					
					<div class="row">
                    	<div class=" col-md-4">
                          <form class="form-horizontal" role="form">
                            <div class="form-group">
                              <label for="inputEmail3" class="col-sm-4 control-label">选择区域：</label>
                              <div class="col-sm-8">
                                <select id ="choose-grid" class="form-control">
                                  <option value="" id="choose-gridh">请选择</option>
                                 
                                </select>
                              </div>
                            </div>                  
                          </form>
                        </div>

                        <div class="col-md-4">                   
						   
                        </div>
                        <div class="col-md-4">
                            <ul class="nav nav-tabs  nav-justified" role="tablist">
                              <li role="presentation" class="active"><a href="#tuzulin" role="tab" data-toggle="tab">土地信息详情</a></li>
                              <li role="presentation"><a href="#lishi" role="tab" data-toggle="tab">租赁参考信息</a></li>
                            </ul>                        
                        
                        </div>
                        
                    </div>
                    <div class="row form-group">
                      <div class="col-md-8">
                       	<div class="grid-stack"></div>
                      </div>
                      <div class="col-md-4">

                        <div class="tab-content">
                          <div role="tabpanel" class="tab-pane active" id="tuzulin" style="min-height:540px; background:#FFF">
                            <div style="background:#FFF; padding:20px">
                               <form class="form-horizontal" role="form">
                                  <fieldset disabled>
                                  <div class="form-group">
                                    <label for="disabledIDInput" class="col-sm-3 control-label">土地编号：</label>
                                    <div class="col-sm-6">
                                        <input type="text" id="tudi_id" class="form-control">
                                    </div>
                                  </div>
                                  </fieldset>
                                <fieldset disabled>
                                  <div class="form-group">
                                      <label for="inputname" class="col-sm-3 control-label">土地名称：</label>
                                      <div class="col-sm-6">
                                          <input type="text" class="form-control" id="tudi_name">
                                      </div>
                                  </div>
                                 </fieldset>
                                 <fieldset disabled>
                                <div class="form-group">
                                    <label for="inputname" class="col-sm-3 control-label">推荐用途：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="tudi_plantingContent">    
                                </div>
                                </div>
                                 </fieldset>
                                 <fieldset disabled>
                                <div class="form-group">
                                    <label for="inputname" class="col-sm-3 control-label">土地面积：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="tudi_landArea">
                                </div>
                                </div>
                                </fieldset>
                                <fieldset disabled>
                                <div class="form-group">
                                    <label for="inputname" class="col-sm-3 control-label">建筑面积：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="tudi_buildingArea" placeholder="单位：平方">
                                </div>
                                </div>
                                </fieldset>
                                 <fieldset disabled>
                                <div class="form-group">
                                    <label for="inputname" class="col-sm-3 control-label">实习上限：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="tudi_Afford" placeholder="人数">
                                </div>
                                </div>
                                </fieldset>
                                <div class="form-group">
                                  <div class="col-sm-offset-3 col-sm-6">
                                    <a class="btn btn-success"  style="padding-left:20px;" id="sum_app" data-toggle="modal" data-target="#landModal">申 请 租 赁</a>
                                  </div>
                                </div>
                                    
                                </form>
                                </div> 
                          
                          </div>
                          <div role="tabpanel" class="tab-pane" id="lishi" style="min-height:540px; background:#FFF">
                         
							<table id="field_rent" class="cell-border" cellspacing="0" width="100%">
                                    <thead>
                                        <tr bgcolor="#3B6290" style="color:#FFF">
                                            <th>租赁人</th>
                                            <th>种植内容</th>
                                            <th>时间</th>
                                        </tr>
                                    </thead> 
									<tbody>                       
                          			</tbody>
                            </table>
                         
                          </div>
                        </div>




                      </div><!-- end col-md-4-->
                    </div><!-- end row-->

						<div class="col-lg-12 form-group">
                        
						</div>

					</div>
				</div><!-- End Sidebar-->                 
                
                    
            
        </div><!--row end-->
  
  
<div class="modal fade bs-example-modal-lg" id="landModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h3 class="modal-title text-center" id="myModalLabel">土地租赁申请工单</h3>
      </div>
      <div class="modal-body"> 			
          <form class="form-inline" role="form" id='land_lease_table'></form>
           
   		   <div class="text-center" style="margin-top:20px;">
           <form class="form-inline " role="form">
 			<div class="form-group">
                <label>开始日期：</label>
                <input class="form-control" type="text" value='2016-2-3' id="stime" disabled>
            </div>
            <div class="form-group">
              <label>结束日期：</label>
              <input type="text" class="form-control" id="etime" value='2017-2-3' disabled>
            </div>
            </form>
            </div>    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" id='sub_land_apply'>提交</button>
      </div>
    </div>
  </div>
</div>  
  
    </div>
	<div class="clearfix"></div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery.min.js"></script>
	<script src="../js/jquery-ui.js"></script>
 
    <script src="../js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script src="../js/jquery.dataTables.min.js"></script>
	<script src="../js/lodash.min.js"></script>
	<script src="../js/gridstack.min.js"></script>
	<script src="../js/gridstack.jQueryUI.min.js"></script>
    <script src="../js/bootbox.min.js"></script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
     <script src="../dist/jquery.cokie.min.js"></script>    
 	<script src="../js/kg.js"></script>   
    <script src="../js/tu-zl.js"></script>
  </body>
</html>
