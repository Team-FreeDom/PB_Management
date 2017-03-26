<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="UTF-8">
    <title>实习申请</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/practiceapply.css">
    
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
						<a href="#" target="_blank"><img
							src="../image/manage-logo.png" alt=""></a>
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
										class="icon-home" aria-hidden="true" data-placement='top' data-toggle='tooltip' title='hah'></i><span>主界面</span>
								</a></li>

								<li class="menuItem nav-parent"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>我的工作</span>
								</a>
									<ul class="nav nav-children">
										<li><a href="myRent.jsp"><span class="text">我的租赁</span></a></li>				
										<li><a href="myrepair.jsp"><span class="text">我的报修</span></a></li>
										<li><a href="myBase.jsp"><span class="text">我的基地</span></a></li>
										<li><a href="practiapply.jsp"><span class="text">实习申请</span></a></li>
									</ul></li>

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

										<li class="menuItem nav-parent" ${(visitRight[3]==0&&visitRight[4]==0&&visitRight[5]==0&&visitRight[6]==0&&visitRight[7]==0&&visitRight[8]==0&&visitRight[9]==0&&visitRight[10]==0&&visitRight[11]==0)?"style='display:none;'":" "}>
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
								<li class="menuItem nav-parent"><a> <i
										class="icon-copy" aria-hidden="true"></i><span>统计分析</span>
								</a>
									<ul class="nav nav-children">
										<li><a href="#"><span class="text">租赁统计</span></a></li>
										<li ${visitRight[12]==0?"style='display:none;'":" "}><a href="statisticData.jsp"><span class="text">实习分析</span></a></li>
										<li><a href="#"><span class="text">实习基地统计</span></a></li>
									</ul></li>

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
							<li><a href="practiapply.jsp"><i class=" icon-home"></i>实习申请</a></li>							
						</ol>							
					</div>
					<div class="pull-left">
					  <ol class="breadcrumb visible-sm visible-md visible-lg wz">
					    <li><a class="modeClass" href="#"><i class=" icon-book"></i>集中实习在线课堂</a></li>
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

							<table id="practiceapplytable" class="hover" cellspacing="0" width="100%">
                                    <thead>
										
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
                                            <th hidden>编号</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                    </tbody>
                                  </table>

						</div>
						
						
					</div>
				</div><!-- End Sidebar-->                 
                
                             
                              
                    
            
        </div><!--row end-->
    </div>
	<div class="clearfix"></div>
	<!--弹出框-->
								<div class="modal-content" id="Applychart" style="border:#3071a9 8px solid;">
                                  <div class="modal-header" style="background:#3071a9; color:#FFF">
                                    <div id="closemodal" class="glyphicon glyphicon-remove closeModal" data-dismiss="modal" ></div>
                                    <h4 class="modal-title text-center" id="myModalLabel">实习申请表</h4>
                                  </div>
                                  
                                  <div class="modal-body table-responsive">
                                  	<form class="form-horizontal" role="form" id="PraForm">
                                        
                                          <table>
                                          	<tr>
                                            	<td>
                                                    单位：<input readonly type="text" class="noborder" id="division" value="">
                                                </td>
                                                <td>
                                                    课程名称：<input readonly type="text"  id="classname" value="农学实践(3)">
                                                </td>
                                                <td>
                                                	面向专业：<input readonly type="text" class="noborder" id="major" value="">
                                                </td>
                                                <td>
                                                	班级：<input readonly type="text" class="noborder" id="class" value="">
                                                </td>
                                                <td>
                                                	学分：<input readonly type="text" id="grade" value="4">
                                                </td>
                                                <td>
                                                	学习人数：<input readonly type="text" id="number" value="26">
                                                </td>
                                                <td>
                                                	实习周数：<input readonly type="text" id="weeks" value="12">
                                                </td>
                                            </tr>
                                          </table>
                                        
                                         <br>
                                          <button type="button" class="btn btn-primary" id="addTbody">添加</button>
                                          <button type="button" class="btn btn-success" id="save">保存</button>
<!--                                          <button type="button" id="testexmple" class="btn btn-success">测试</button>
-->                                          <table class="table-bordered"  id="table">
                                          	
												<tr>
													<td>带队老师</td>
													<td colspan="4"><input class="noborder text-center flag" readonly id="leaderTeacher" type="text" value="李林"></td>
													<td>指导教师</td>
													<td colspan="4"><input readonly class="noborder text-center flag" id="adviser" value="" type="text"></td>
												</tr>
												<tr>
													<td>实验员姓名</td>
													<td colspan="9"><input class="noborder text-center flag" readonly id="testername" value="" type="text">
													</td>
												</tr>
                                         	
                                          	<tbody >
												
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
							<div class="modal-content" id="modalcontent" style="border:#3071a9 8px solid;width: 450px">
								<div class="modal-header" style="background:#3071a9; color:#FFF">
									<div class="glyphicon glyphicon-remove closeModal" data-dismiss="modal" ></div>
									<h4 class="modal-title text-center">请选择实验员</h4>
								</div>

								<center>
									<div class="modal-body">
										<div class="row" style="margin-bottom: 30px;">
											<div class="col-sm-12 form-inline">
												<lable>实验员:</lable>
												<input id="tester" type="text" class="form-control exeWidth" value="">
											</div>
										</div>
										<div class="row" style="margin-bottom: 30px;">
											<div class="col-sm-6">
												<select name="" id="selectCollege" class="form-control">
													<option value="" id="collegeID">请选择学院</option>
													<!-- <option value="test1">信息科学技术学院</option>
													<option value="test2">test2</option>
													<option value="test3">test3</option> --> 
												</select>
											</div>
											<div class="col-sm-6">
												<select name="" id="selectTname" class="form-control">
													<option value="" id="teacherNmaeID">请选择老师</option>
													<!-- <option value="赵勇">赵勇</option>
													<option value="李彩">李彩</option>
													<option value="宋超">宋超</option> -->
												</select>
											</div>
										</div>
											
											
										
									</div>
								</center>
								<div class="modal-footer">
									<center>
										<button type="button" class="btn btn-primary" data-dismiss="modal" id="finished">确定</button>
										<button type="button" class="btn btn-default" data-dismiss="modal" id="">取消</button>
									</center>
								</div>
							</div>
						</form>
					</div>
				</div>
				
				
           <div class="modal fade" id="Selectteacher" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
							<div class="modal-content" id="modalcontent2" style="border:#3071a9 8px solid;width: 450px">
								<div class="modal-header" style="background:#3071a9; color:#FFF">
									<div class="glyphicon glyphicon-remove closeModal" data-dismiss="modal" ></div>
									<h4 class="modal-title text-center">请选择指导老师</h4>
								</div>

								<center>
									<div class="modal-body">
										<div class="row" style="margin-bottom: 30px;">
											<div class="col-sm-12 form-inline">
												<lable>指导老师:</lable>
												<input id="leadteachername" type="text" class="form-control exeWidth" value="">
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
										<button type="button" class="btn btn-primary" data-dismiss="modal" id="finished2">确定</button>
										<button type="button" class="btn btn-default" data-dismiss="modal" id="">取消</button>
									</center>
								</div>
							</div>
					</div>
				</div>                         
                                                              
                                                                                                                    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery-ui.min.js"></script>
    <!--datatable javascript-->
     <script src="../js/bootstrap.min.js"></script>
	<script src="../js/jquery.dataTables.min.js"></script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
   
     <script src="../js/bootbox.min.js"></script>
    <script src="../dist/jquery.cokie.min.js"></script>
    <script src="../js/Calendar.js"></script> 
    <script src="../js/myNeed/practiceapply.js"></script>
    <script src="../js/kg.js"></script>
    
  </body>
</html>