<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
  <form action="jsp1/login.do" method="post" onsubmit="return check()">
  <table>
    <tr>
      <td> 用户名：</td>
      <td><input type="text" name="username" id="username"/></td>
    </tr>
    <tr>
      <td> 密码：</td>
      <td><input type="password" name="pwd" id="pwd"/></td>
    </tr>
    <tr>
      <td colspan="2"><center><input type="submit" value="登录"/></center></td>
    </tr>
   </table>
   </form>
  </body>
  <script type="text/javascript">
    function check()
    {
      var username=document.getElementById("username");
      var pwd=document.getElementById("pwd");
      
      if(username==null)
      {
        alert("请填写用户名！");
        return false;
      }
      if(pwd==null)
      {
        alert("请填写密码！");
        return false;
      }
    }
  </script>
</html>
