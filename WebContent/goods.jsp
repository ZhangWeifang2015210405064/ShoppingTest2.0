<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.test.model.*,com.test.dao.*,com.test.impl.*"%>

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + ":/" + request.getServerName() + ":/" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>

	<link rel="stylesheet" href="lib/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="lib/font-awesome-4.7.0/css/font-awesome.min.css">
	<link href="css/personal.css" rel="stylesheet"> 
	<link rel="stylesheet" type="text/css" href="css/goods.css">
	<link rel="stylesheet" type="text/css" href="css/purchase_process_box.css">
	<link href="css/register.css" rel="stylesheet">
	<link href="css/login.css" rel="stylesheet">

</head>
<body style="margin: 0;">

	<%
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		GoodsDao goods = new GoodsDaoImpl();
		Goods tmp = goods.getGoodsById(itemId);
		session.setAttribute("tmpItem", tmp);
		
		User user=new User();
		if(session.getAttribute("user")!=null){
			user=(User)session.getAttribute("user");
			//session.setMaxInactiveInterval(10);
		}
	%>

	<!--购买流程-->
	<div class="purchase_process_box" style="display: none">

		<div class="check_goods" id="check_goods">
			<div class="title">
				<span>确认商品</span>
			</div>
			<div class="personal_box">
				<img src="<%=tmp.getPicture() %>" width="319px" height="220px" />
			</div>
			<div class="intro_box">
				<p><%=tmp.getName() %></p>
				<span><%=tmp.getIntroduce() %></span>
				<div class="icon_box">
					<div class="icon_box_money">
						<img src="img/personal/u140.png" width="36px" height="36px" /> <span><%=tmp.getPrice() %></span>
					</div>
					<div class="icon_box_quality" style="margin-left: 50px">
						<img src="img/personal/u940.png" width="30px" height="30px" /> <span><%=tmp.getCycle() %>天</span>
					</div>
				</div>
				<div class="check_goods_box">
					<div class="check_goods_btn" id="check_goods_btn">下一步</div>
				</div>
			</div>
		</div>
		
		<div class="receipt_info" style="display: none">
			<div class="title">
				<span>确认收货信息</span>
			</div>
			<div class="receipt_info_item">
				<img src="img/personal/u684.png" width="27px" height="27px" />
				<p>收货地址：</p>
				<textarea id="trueAddress" name="trueAddress" clos="6" rows="3"><%=user.getAddress() %></textarea>
			</div>
			<div class="receipt_info_item">
				<img src="img/personal/u687.png" width="27px" height="27px" />
				<p>联系方式：</p>
				<input id="truephone" name="truephone" type="text" value="<%=user.getPhone() %>" />
			</div>
			<div class="receipt_info_item">
				<img src="img/personal/u694.png" width="27px" height="33px" />
				<p>收货人：</p>
				<input id="customer" name="customer" type="text" value="<%=user.getName() %>" />
			</div>
			<div class="receipt_info_btn_group">
				<div class="return_default_btn" id="return_default_btn">默认地址</div>
				<div class="receipt_info_btn" id="receipt_info_btn">下一步</div>
			</div>
		</div>
		<div class="pay_pwd" style="display: none">
			<div class="title">
				<span>输入支付密码</span>
			</div>
			<div class="input_place_group">
				<div>
					<input id="pwd_hid" name="pwd_hid" type="hidden" />
					<div id="input_place_first" class="input_place">
						<span style="display: none">·</span>
					</div>
					<div class="input_place">
						<span style="display: none">·</span>
					</div>
					<div class="input_place">
						<span style="display: none">·</span>
					</div>
					<div class="input_place">
						<span style="display: none">·</span>
					</div>
					<div class="input_place">
						<span style="display: none">·</span>
					</div>
					<div class="input_place">
						<span style="display: none">·</span>
					</div>
				</div>
			</div>
			<div class="key_place_group">
				<div class="key_box">
					<div class="key_press_num">0</div>
					<div class="key_press_num">1</div>
					<div class="key_press_num">2</div>
					<div class="key_press_num">3</div>
					<div class="key_press_num">4</div>
					<div class="key_press_num">5</div>
					<div class="key_press_num">6</div>
					<div class="key_press_num">7</div>
					<div class="key_press_num">8</div>
					<div class="key_press_num">9</div>
					<div class="key_press_delete" id="key_press_delete">
						<img src="img/personal/u1173.png" height="50px" width="50px" />
					</div>
				</div>
			</div>
			<div class="pay_btn_box">
				<input type="submit" class="pay_btn" id="pay_btn" name="pay_btn" value="支付" />
			</div>
		</div>
	</div>
	<!--购买流程-->
	<!-- 成功 失败 -->
	<div class="success_box" style="display: none">
			<div class="title" id="personal_success_title">
				<span>交易完成</span>
			</div>
			<div class="img_success" id="personal_success_img">
				<img src="img/personal/u1087.png" width="200px" height="200px" />
			</div>
			<div class="span_success">
				<span id="success_box_message">支付成功！</span>
			</div>
			<div class="btn_success_box">
				<div class="btn_success" >完成</div>
			</div>
	</div>
	<div class="fail_box" style="display : none">
		<div class="span_success">
			<span id="success_box_message">密码错误！</span>
		</div>
		<div class="btn_success_box">
				<div class="btn_success" >我知道了</div>
		</div>
	</div>
	<!-- 成功失败  -->
	
	<!-- 收藏成功与否  -->
		<div class="sucess_collect_box" style="display : none">
			<div class="span_collect_success">
				<span>收藏成功！</span>
			</div>
			<div class="btn_success_box">
				<div class="btn_success" >我知道了</div>
			</div>
		</div>
		<div class="have_collect_box" style="display : none">
			<div class="span_have_success">
				<span>亲，您已经收藏该商品~</span>
			</div>
			<div class="btn_success_box">
				<div class="btn_success" >我知道了</div>
			</div>
		</div>
	<!-- 收藏成功与否  -->
	
	<!--分享框-->
	<div class="share-box" style="display: none">
		<div class="input_url">
			<img src="img/personal/u1112.png" height="50px" width="50px" />
			 <input type="text" onready="true" id="input_url"/>
		</div>
		<div class="btn_copy" id="btn_copy">
			<p>点击复制即可分享</p>
		</div>
		<div class="btn_understand" id="btn_understand">
			<span>我知道了</span>
		</div>
	</div>
	<!--分享框-->

	<!--购买提示框-->
	<div class="purchase_hints_box" style="display: none">
		<div class="balance_warning">
			<div class="balance_warning_p">
				<img src="img/personal/u971.png" width="40px" height="40px" />
				<p id="information">余额不足！</p>
			</div>
			<div class="balance_intro">
				<p>您的余额：</p>
				<span><%=user.getMoney() %></span> <img src="img/personal/u140.png" width="20px" height="20px" />
			</div>
			<div class="balance_warning_btn" id="balance_warning_btn">
				<p>我知道了</p>
			</div>
		</div>
	</div>
	<!--购买提示框-->

	<jsp:include page="src/head.jsp"></jsp:include>

	<!--主体-->
	<div class="container " style="margin-bottom: 50px">

		<div class="row return_bar">
			<div class="col-md-12 navbar-left warning">
				<span>交易中请勿使用聊天工具沟通，不要接收可疑文件和不要点击不明来源的链接，支付前核实好域名和支付详情。
					本网站不会以订单有问题，让您提供任何银行卡、密码、手机验证码！遇到可疑情况可在钱盾“诈骗举报”中进行举报</span>
			</div>
		</div>

		<div class="row">
			<div class="col-md-8">
				<div class="row goods_item1">
					<div class="col-md-6 big_goods_img">
						 <img src="<%=tmp.getPicture()%>" width="392px"
							height="226px" />
					</div>
					<div class="col-md-6 goods_info">
						<div class="row goods_info_title"><%=tmp.getName()%></div>
						<div class="row goods_info_intro">
							<span><%=tmp.getIntroduce()%></span>
						</div>
						<div class="row">
							<div class="row goods_price">
								<img src="img/personal/u140.png" width="40px" height="40px" />
								<p><%=tmp.getPrice()%></p>
							</div>
							<div class="row goods_ensure">
								<img src="img/personal/u940.png" width="40px" height="40px" />
								<p><%=tmp.getCycle()%>天
								</p>
								<span>（购买日起期限内可退货）</span>
							</div>
						</div>
						<div class="row goods_info_button_group">
						
							<form action="user/buy" method="post" id="checkbuyform">
							<div class="col-md-3 button_type" id="btn_goods_buy">购买</div>
							</form>
							
							<form action="UpdateFavoriteServlet" method="post" id="saveItemform">
							<div class="col-md-3 button_type" id="btn_goods_collect">收藏</div>
							</form>
							<div class="col-md-3 button_type" id="btn_goods_show">分享</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3 navbar-right">
				<div class="row goods_item2">
					<div class="row goods_item2_intro">
						<h2>供应商</h2>
						<span><%=tmp.getFacilitator()%></span>
					</div>
					<div class="row goods_item2_intro">
						<h2>服务商</h2>
						<span><%=tmp.getSupplier()%></span>
					</div>
					<div class="row goods_item2_intro">
						<h2>电话</h2>
						<span><%=tmp.getContact()%><br> 17301643305</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--主体-->
	
	<div class="foot"></div>

</body>

	<script src="lib/jquery.js"></script>
	<script src="lib/layer-v3.1.1/layer/layer.js"></script>
	<script src="lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="js/goods.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript" src="js/register.js"></script>

</html>