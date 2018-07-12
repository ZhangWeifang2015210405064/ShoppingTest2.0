<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
	<!--注册框显示-->
	<div class="registerBox" style="display: none;">
			
		<form action="user/userregister" method="post" id="registerForm">
			<!--输入手机号-->
			<div class="registerBox-input">
				<span>注册手机:</span>
				<input type="text" id="phone" name="phone">
			</div>

			<div class="registerBox-input-error" id="phone-error" style="display:none;">手机号格式有误！</div>

			<!--设置密码-->
			<div class="registerBox-input">
				<span>设置密码:</span>
				<input type="password" id="password" name="password">
			</div>

			<div class="registerBox-input-error" id="password-error" style="display:none;">密码格式有误！</div>

			<!--确认密码-->
			<div class="registerBox-input">
				<span>确认密码:</span>
				<input type="password" id="relpassword" name="relpassword">
			</div>

			<div class="registerBox-input-error" id="relpassword-error" style="display:none;">两次密码不相同！</div>

			<!--绑定邮箱-->
			<div class="registerBox-input">
				<span>绑定邮箱:</span>
				<input type="email" id="email" name="email">
			</div>

			<div class="registerBox-input-error" id="email-error" style="display:none;">邮箱格式有误！</div>

			<!--验证信息-->
			<!-- <div class="registerBox-check">

				<div class="registerBox-input">
					<span>验证码:</span>
					<input type="text" style="width: 60px;margin-right: 10px;">
				</div>

				<button class="registerBox-check-submit">发送验证码</button>

			</div> -->

			<!--注册按钮-->
			<button class="registerBox-submit" type="submit" id="registerSubmit">注册</button>

		</form>

			<!--其他通道-->
			<div class="registerBox-otherway">

			<span>其他登录方式：</span>

			<!--其他登录方式图标-->
			<i class="fa fa-weixin" style="color: #6699cc;margin-right: 10px" ></i>
			<i class="fa fa-qq" style="color: #6699cc;margin-right: 10px"></i>
			<i class="fa fa-weibo" style="color: #6699cc"></i>

		</div>

	</div>
	
	<!--注册提示-->
	<div class="registerMessage" style="display: none;">
		<i class="fa fa-check fa-2x"><span><%=request.getParameter("info") %></span></i>
		<a class="message-check" id="register-check">我知道了</a>
	</div>
	

	<!--登录框显示-->
	<div class="loginBox" style="display: none;">
		
	<form action="user/homepage" method="post" id="loginForm">
		<!--输入用户账号-->
		<div class="loginBox-name">
			<span>用户账号:</span>
			<input type="text" id="loginId" name="loginId">
		</div>

		<div class="loginBox-input-error" id="loginId-error" style="display: none;"></div>

		<!--输入登录密码-->
		<div class="loginBox-password">
			<span>登录密码:</span>
			<input type="password" id="loginPassword" name="loginPassword">
		</div>
		
		<div class="loginBox-input-error" id="loginPassword-error" style="display: none;"></div>

		<!--记住密码和忘记密码-->
		<div class="loginBox-info">

			<!--记住密码-->
			<div class="loginBox-info-remember">
				<input type="checkbox" id="savepsw" name="savepsw">
				<span>记住密码</span>
			</div>
			
			<!--忘记密码和前往注册-->
			<div class="loginBox-info-forget">
				<a href="passwordReset.html"><span>忘记密码</span></a>
				<a id="toRegister"><span>前往注册</span></a>
			</div>
			
		</div>

		<!--登录按钮-->
		<button class="loginBox-submit" type="submit" id="loginSubmit">登录</button>
		
	</form>

		<!--其他通道-->
		<div class="loginBox-otherway">

			<span>其他登录方式：</span>

			<!--其他登录方式图标-->
			<i class="fa fa-weixin" style="color: #6699cc;margin-right: 10px" ></i>
			<i class="fa fa-qq" style="color: #6699cc;margin-right: 10px"></i>
			<i class="fa fa-weibo" style="color: #6699cc"></i>

		</div>

	</div>
	
	<!--登录提示-->
	<div class="loginMessage" style="display: none;">
		<i class="fa fa-check fa-2x"><span><%=request.getParameter("info") %></span></i>
		<a class="message-check">我知道了</a>
	</div>


	<!--导航栏-->
	<div class="guide">
		<img src="img/logo.png" alt="图片无法加载" style="height: 60px;">

		<div class="guide-content">

			<span>Welcome to Fourth Space!</span>

			<div class="guide-search">
				<i class="fa fa-search"></i>
				<input type="text" placeholder="输入商品名">
			</div>
			
			<!-- <span><a href="index.jsp">主页</a></span> -->
			
			<% 
				if(request.getSession().getAttribute("user")!=null){
			%>
				<span><a href="homepage.jsp">主页</a></span>
			
				<span><a href="personal.jsp">个人中心</a></span>

				<span><a href="src/exit.jsp" target="_top">退出</a></span>
			<%
				}else{
			%>
			<span><a href="index.jsp">主页</a></span>

			<span class="login" id="login">登录</span>
				
			<span class="register" id="register">注册</span>
			
			<%
				}
			%>

		</div>
	</div>
