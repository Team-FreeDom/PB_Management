<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	int i = 0;
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'mainRent.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="../css/admin1.css" type="text/css" rel="stylesheet">
<script src="../js/bootstrap.min.js"></script>
<script src="../js/run_main.js"></script>
<script src="../js/jquery.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<div>
		<form id="myForm" action="jsp1/getContent.do" method="post">

			<span>基地名:</span> <select id="base" name="base"
				onchange="choose(this)">
				<option value="-1">请选择基地</option>
				<c:forEach items='${base}' var="base">
					<option value="${base.bid }" ${flag1==base.bid?"selected":""}>${base.bname }</option>
				</c:forEach>
			</select> <span> 筛选：</span> <select id="planting" name="planting"
				onchange="choose(this)">
				<option value="-1" id="optionContent">请选择适宜种植内容</option>
				<c:forEach items='${planting}' var="planting">
					<option value="${planting.planting }"
						${flag2==planting.planting?"selected":""}>${planting.planting }</option>
				</c:forEach>
			</select>
		</form>

	</div>
	<div>
		<div>
			<p>土地布局参数</p>
			<table border="1">
				<c:forEach items='${layout}' var="layout">
					<tr>
						<td>序号</td>
						<td>土地编号</td>
						<td>水平方向坐标</td>
						<td>垂直方向坐标</td>
						<td>土地宽度</td>
						<td>土地高度</td>
						<td></td>
					</tr>
					<tr>
						<td>${layout.id }</td>
						<td>${layout.lid }</td>
						<td>${layout.x_axis }</td>
						<td>${layout.y_axis }</td>
						<td>${layout.width}</td>
						<td>${layout.height }</td>
						<td><a id="${layout.lid }" onclick="getInfo(this)"
							<c:forEach items='${str}' var="strs">				   
				      <c:if test='${layout.lid+""==strs }'>
				         style="color:#F8E009;"
				      </c:if>
				  </c:forEach>>
								点击</a></td>
					</tr>

				</c:forEach>
			</table>
		</div>
		<div
			style="float:left;width:300px;background:#a4bccd;margin-top:40px;margin-left:10px;">
			<p>基本信息</p>
			<table id="info">
				<tr>
					<td>基地名：</td>
					<td id="info1"></td>
				</tr>
				<tr>
					<td>土地名：</td>
					<td id="info2"></td>
				</tr>
				<tr>
					<td>土地编号：</td>
					<td id="info3"></td>
				</tr>
				<tr>
					<td>面向专业：</td>
					<td id="info4"></td>
				</tr>
				<tr>
					<td>土地面积：</td>
					<td id="info5"></td>
				</tr>
				<tr>
					<td>建筑面积：</td>
					<td id="info6"></td>
				</tr>
				<tr>
					<td>可承担人数：</td>
					<td id="info7"></td>
				</tr>
				<tr>
					<td>适宜中种植内容：</td>
					<td id="info8"></td>
				</tr>
				<tr>
					<td>备注：</td>
					<td id="info9"></td>
				</tr>
				<tr>
					<td>
						<button style="margin:40px;" onclick="applyInfo()">申请租赁</button>
					</td>
				</tr>
			</table>

		</div>
	</div>

	<c:forEach items='${landInfo}' var="landInfo">
		<div class="modal-content text-center admin_hide"
			id="${landInfo.lid}ta">
			<div class="modal-header">
				<a type="button" class="close" data-dismiss="modal"
					id="${landInfo.lid}t""> <span aria-hidden="true">&times;</span>
				</a>
				<h4 class="modal-title" id="myModalLabel">申请租赁</h4>
			</div>
			<form action="apply.do" method="post" onSubmit="return check(this)"
				enctype="multipart/form-data" name="myForm2"
				id="myForm${landInfo.lid}">
				<div class="container table-responsive">
					<table>
						<tr style="padding-top:20px;">
							<td style="width:80px;text-align:center;line-height:100px;"><label
								for="exampleInputName2">基地名：</label></td>
							<td style="text-align:left;width:80px"><input type="text"
								value="${landInfo.bname}" name="bid" class="form-control"
								disabled="disabled"></td>
							<td style="width:80px;text-align:center;margin-left:30px;"><label
								for="exampleInputName2">租赁人</label></td>
							<td style="text-align:left;width:80px"><input type="text"
								value="${landInfo.lid}" name="user" class="form-control"></td>
							<td style="width:80px;text-align:center;margin-left:30px;"><label
								for="exampleInputName2">申请学院</label></td>
							<td style="text-align:left;width:80px"><select
								name="college" style="width:195px;">
									<option>请选择</option>
							</select></td>
						</tr>

						<tr style="margin-top:20px;">
							<td style="width:80px;text-align:center;line-height:40px;"><label
								for="exampleInputName2">土地面积</label></td>
							<td style="text-align:left;width:80px"><input type="text"
								value="${landInfo.landArea}" name="landArea"
								class="form-control"></td>
							<td style="width:80px;text-align:center;line-height:40px;"><label
								for="exampleInputName2">适宜种植内容</label></td>
							<td style="text-align:left;"><input type="text"
								value="${landInfo.aptPlanting}" name="landArea"
								class="form-control"></td>
							<td style="width:80px;text-align:center;line-height:40px;"><label
								for="exampleInputName2">可承担人数</label></td>
							<td style="text-align:left;"><input type="text"
								value="${landInfo.afford}" name="landArea" class="form-control">
							</td>

						</tr>

						<tr>
							<td style="width:80px;text-align:center;line-height:40px;"><label
								for="exampleInputName2">建筑面积</label></td>
							<td style="text-align:left;"><input type="text"
								value="${landInfo.buildingArea}" name="landArea"
								class="form-control"></td>
							<td style="width:80px;text-align:center;line-height:40px;"><label
								for="exampleInputName2">土地名称</label></td>
							<td style="text-align:left;"><input type="text"
								value="${landInfo.lname}" name="landArea" class="form-control">
							</td>
							<td style="width:80px;text-align:center;line-height:40px;"><label
								for="exampleInputName2">土地编号</label></td>
							<td style="text-align:left;"><input type="text"
								value="${landInfo.lid}" name="landArea" class="form-control">
							</td>
						</tr>

						<tr>
							<td style="width:80px;text-align:center;line-height:40px;"><label
								for="exampleInputName2">起始日期</label></td>
							<td colspan="2" style="text-align:left;"><input type="text"
								name="landArea" class="form-control"> <input type="text"
								name="landArea" class="form-control"></td>
							<td style="width:80px;text-align:center;margin-left:30px;">计划种植内容</td>
							<td colspan="3" style="text-align:left;"><input type="text"
								name="landArea" class="form-control"></td>
						</tr>

						<tr>
							<td style="width:80px;text-align:center;line-height:40px;"><label
								for="exampleInputName2">组别</label></td>

						</tr>

					</table>
				</div>
		</div>
	</c:forEach>

	<script type="text/javascript">
		$(function() {
			var notimeout = ${notimeout};
			if (!notimeout) {
				alert("登录超时，请重新登录！");
				document.location.href = 'jsp1/login.jsp';
			}

		})
		function choose(obj) {
			$('#myForm').submit();

		}

		function applyInfo() {
			var lidformer = $("#info3").html();
			document.getElementById(lidformer + "ta").style.display = "block";
		}

		function getCookie(c_name) {
			var flag = false;
			var value="";
			if (document.cookie.length > 0) {				
				    c_start=document.cookie.indexOf(c_name + "=");
                    if (c_start!=-1)
                       { 
                         flag = true;
                         c_start=c_start + c_name.length+1;
                         c_end=document.cookie.indexOf(";",c_start);
                         if (c_end==-1) 
                         c_end=document.cookie.length;
                         value=unescape(document.cookie.substring(c_start,c_end));					    
				}
			}
					
			if (!flag) {
				alert("登录超时，请重新登录！");
				document.location.href = 'jsp1/login.jsp';
			}
			
			return value;
			}
		

		function SetCookie(name, value) {			
			var exp = new Date();
			exp.setTime(exp.getTime() + 10 *1000);//过期时间 10秒  
			document.cookie = name + "=" + escape(value) + ";expires="
					+ exp.toGMTString();
		}

		function getInfo(obj) {

			var value=getCookie("username");
			var lid = obj.id;
			$.ajax({
				type : 'POST',
				data : {
					"lid" : lid

				},
				dataType : 'json',
				url : 'jsp1/getInfo.do',
				async : false,
				cache : false,
				error : function(request) {
					alert("error");
				},
				success : function(data) {

					var i = 0;
					for ( var item in data) {
                        SetCookie("username", value);
						$("#info1").html(data[i].bname);
						$("#info2").html(data[i].lname);
						$("#info3").html(data[i].lid);
						$("#info4").html(data[i].major_oriented);
						$("#info5").html(data[i].landArea);
						$("#info6").html(data[i].buildingArea);
						$("#info7").html(data[i].afford);
						$("#info8").html(data[i].aptPlanting);
						$("#info9").html(data[i].remark);
						i++;
					}

				}

			});

		}
	</script>
</body>
</html>
