<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.test.model.*,com.test.impl.*,com.test.dao.*" %>   

<% 
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<link rel="stylesheet" type="text/css" href="css/index.css" />
	<link rel="stylesheet" href="lib/font-awesome-4.7.0/css/font-awesome.min.css">
	<link href="css/register.css" rel="stylesheet">
	<link href="css/login.css" rel="stylesheet">

</head>
<body style="margin:0;">

<%--
<%
	User user=null;
	if(session.getAttribute("user")!=null){
		user=(User)session.getAttribute("user");
	}
%>


账号：<%=user.getId() %><br>
昵称：<%=user.getNickname() %><br>
登录密码：<%=user.getPassword() %><br>
支付密码：<%=user.getPayPassword() %><br>
邮箱：<%=user.getEmail() %><br>

地址：<%=user.getAddress() %><br>
联系方式：<%=user.getPhone() %><br>
姓名：<%=user.getName() %><br>
--%>


<jsp:include page="src/head.jsp"></jsp:include>

<%=request.getParameter("itemId") %>

</body>

	<script src="lib/jquery.js"></script>
	<script src="lib/layer-v3.1.1/layer/layer.js"></script>
	<script src="lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>

</html>