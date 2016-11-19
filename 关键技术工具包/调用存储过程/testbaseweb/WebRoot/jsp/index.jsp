<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <form action="jsp/query.do" method="post">
  	<input type="submit" value="查询">
  </form>
    <table>
    	<tr>
    		<td>序号 </td>
    		<td>开始日期 </td>
    		<td>结束日期</td>
    		<td>基地名 </td>
    		<td>土地编号 </td>
    		<td>最终状态 </td>    		
    	</tr>
		<c:forEach items='${list}' var="list">
		<tr>
    		<td>${list.la_id}</td>
    		<td>${list.startTime}</td>
    		<td>${list.endTime}</td>
    		<td>${list.bname}</td>
    		<td>${list.lid}</td>
    		<td>${list.applicantId}</td>
    		<td>${list.times}</td>
    	</tr>
    	</c:forEach>
    </table>
  </body>
</html>
