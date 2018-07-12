<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退出登录</title>
</head>
<body>
	<% 
		Cookie cookie = null;
	   	Cookie[] cookies = null;
	   	// 获取当前域名下的cookies，是一个数组
	   	cookies = request.getCookies();
	   	if( cookies != null ){
	      	for (int i = 0; i < cookies.length; i++){
	         	cookie = cookies[i];
	         	if(cookie.getName().equals("fs_id") || cookie.getName().equals("fs_password")){
	         		System.out.println(cookie.getValue());
	         		cookie.setPath("/");
	            	cookie.setMaxAge(0);
	            	response.addCookie(cookie);
	         	}
	      	}
	  	}
	   	
		session.invalidate();
		response.sendRedirect("../index.jsp");
	%>
</body>
</html>