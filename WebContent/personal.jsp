<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page import="com.test.model.*,com.test.dao.*,com.test.impl.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>

	<link rel="stylesheet" href="lib/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="lib/font-awesome-4.7.0/css/font-awesome.min.css">
	<link href="css/personal.css" rel="stylesheet">
	<!-- <link rel="stylesheet" type="text/css" href="css/homepage.css" /> -->

</head>
<body style="margin:0;">

	<!-- 图片上传回调信息 -->
	<%
		if(session.getAttribute("message") != null) { //判断保存在request范围内的对象是否为空
			out.println("<script >alert('" + session.getAttribute("message") + "');</script>"); //页面显示提示信息    	
			session.setAttribute("message", null);
		}
	%>
		
	<jsp:include page="src/head.jsp"></jsp:include>
	
	<%
		User user=(User)session.getAttribute("user");
		if(user != null){
			UserDao dao = new UserDaoImpl();
		    User tmp = dao.login(user);
			
		    if (tmp != null){
		    	request.getSession().setAttribute("user", tmp);
		    }
		}
		
	%>
	
		<!--主体-->
		<div class="container personal_body" >
			<!--导航滑动条-->
			<div class="row personal_body_bar">
				<div class="col-md-4 personal_body_bar_item bar_be_selected" id="personal_information">
					<span>个人信息</span>
				</div>
				<div class="col-md-4 personal_body_bar_item" id="collection_center">
					<span>收藏中心</span>
				</div>
				<div class="col-md-4 personal_body_bar_item" id="shopping_record">
					<span>购物记录</span>
				</div>
			</div>
			<!--主体内容-->
			<div class="">
			<div class="row main_content" id="PersonalInfo" >
				<div class="col-md-3 big_head">
					<div class="row">
					<%
						String abpath = user.getAvator();
					%>
						<img src=<%=abpath %> />
					</div>
					<div class="row upload_box">
						<form id="form_upload" action="IMGServlet" method="post" 
						enctype="multipart/form-data" >
							<input class="up_file_type" type="file" name="file" />
							<input class="up_big_head" type="submit" name="Submit" value="上传头像" />
						</form>
					</div>
				</div>
				<div class="col-md-4 basic_info">
					<div class=" basic_info_title">基本信息</div>
					
					<form action="user/saveInfo" method="post" id="userInfoForm">
					<div class="  basic_info_item">
						<img src="img/personal/u642.png" width="27px" height="30px" />
						<p>注册账号：</p>
						<input type="text" value=<%=user.getId() %> readonly="true" />
						<i>（不可修改）</i>
					</div>
					<div class=" basic_info_item">
						<img src="img/personal/u658.png" width="27px" height="27px"/>
						<p>账号昵称：</p>
						<input type="text" value="<%=user.getNickname() %>" id="newNickname" name="newNickname">
					</div>
					<div class=" basic_info_item">
						<img src="img/personal/u649.png" width="27px" height="27px"/>
						<p>登录密码：</p>
						<input type="password" value="<%=user.getPassword() %>" id="newPassword" name="newPassword">
						<div class="btn_password_visual" id="btn_login_pwd"><p id="passwordVersion">可见</p></div>
					</div>
					<div class=" basic_info_item">
						<img src="img/personal/u705.png" width="27px" height="27px"/>
						<p>支付密码：</p>
						<input type="password" value="<%=user.getPayPassword() %>" id="newPaypassword" name="newPaypassword">
						<div class="btn_password_visual" id="btn_pay_pwd"><p id="paypasswordVersion">可见</p></div>
					</div>
					<div class=" basic_info_item">
						<img src="img/personal/u671.png" width="27px" height="19px"/>
						<p>账户余额：</p>
						<input type="text" value=<%=user.getMoney() %> readonly="true" />
						<i>（不可修改）</i>
					</div>
					<div style="float: left; width: 100%">
						<button class="row basic_info_save" type="submit" id="userInfoSubmit">保存</button>
					</div>
					
					</form>
					
				</div>
				<div class="col-md-4 receipt_info">
					<div class="row receipt_info_title">
						<p>收货信息</p>
					</div>
					
					<form action="user/saveAddr" method="post" id="userAddrForm">
					<div class="receipt_info_item">
						<img src="img/personal/u684.png" width="27px" height="27px"/>
						<p>收货地址：</p>
						<textarea name="txt" clos="6" rows="3" id="newAddress"><%=user.getAddress() %></textarea>
					</div>
					<div class="receipt_info_item">
						<img src="img/personal/u687.png" width="27px" height="27px"/>
						<p>联系方式：</p>
						<input type="text" value="<%=user.getPhone() %>" id="newPhone" name="newPhone">
					</div>
					<div class="receipt_info_item">
						<img src="img/personal/u694.png" width="27px" height="33px"/>
						<p>收货人：</p>
						<input type="text" value="<%=user.getName() %>" id="newName" name="newName"/>
					</div>
					<div style="float: left; width: 100%">
						<button class="row receipt_info_save" type="submit" id="userAddrSubmit">保存</button>
					</div>
					</form>
				</div>
			</div>
			<div class="row main_content "  id="CollectionCenter" style="display: none">
				<div class="row collection_body">
					<div class="row collection_body_bar">
						<div class="col-md-3 collection_body_bar_title">已收藏的商品</div>
						<div class="col-md-1 collection_body_bar_classify">
							<select class="row select_classify">
	  							<option value ="1">按时间</option>
	  							<option value ="2">按价格升</option>
	  							<option value="3">按价格降</option>
							</select>
						</div>
						
					</div>
				</div>
				<div class="row collection_goods" id="f_goods_list">
					
				</div>
			</div>
			<div class="row main_content " id="ShoppingRecord" style="display: none">
				<div class="row shopping_body">
					<div class="row shopping_body_bar">
						<div class="col-md-2 shopping_body_bar_classify">
							<select class="row select_classify">
								<option>按日期</option>
								<option>按价格升</option>
								<option>按价格降</option>
							</select>
						</div>
						
						<div class="col-md-3 shopping_body_bar_search">
							<div class="input-group input_group_type">
	    						<span class="input-group-btn">
	    							<button id="shopping_body_bar_go" class="btn btn-default" type="button">Go!</button>
	    						</span>
	    						<input type="text" class="form-control" placeholder="商品名称">
	    					</div>
						</div>
					</div>
					<div class="row shopping_body_receipt">
						<table class="table table-bordered table-striped" style="width:95%">
						
							<caption></caption>
							
							<thead>
								<tr>
									<th>商品名称</th>
									<th>价格</th>
									<th>收货地址</th>
									<th>收货人</th>
									<th>购买日期</th>
									<th>退购日期</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<%
								List<Record> list=new ArrayList<Record>();
								RecordDao dao=new RecordDaoImpl();
								list=dao.getAllRecord(user.getId());
								for(int i=0;i<list.size();i++){
									Record record=list.get(i);
									
									Goods tmpgood=new Goods();
									GoodsDao goodsdao=new GoodsDaoImpl();
									tmpgood=goodsdao.getGoodsById(record.getItemId());
							%>
								<tr>
									<td class="hideId" style="display:none;"><%=record.getId() %></td>
									<td><%=tmpgood.getName() %></td>
									<td><%=tmpgood.getPrice() %></td>
									<td><%=record.getAddress() %></td>
									<td><%=record.getCustomer() %></td>
									<td><%=record.getBuy_date() %></td>
									<%
										if(record.getReturn_date()!=null){
									%>
									<td><%=record.getReturn_date() %></td>
									<%
										}else{
									%>
									<td>-</td>
									<%
										}
									%>
									<td><%=record.getStatus() %></td>
									<td>
										<a class="record_delete" id="record_delete" name="record_delete" style="text-decoration:none;cursor:pointer;">删除</a>
										/
										<a id="record_back" name="record_back" style="text-decoration:none;cursor:pointer;">退购</a>
									</td>
								</tr>
								
						<%
							}
						%>
								
							</tbody>
							
						</table>
					</div>
					
					
				</div>
			</div>
			</div>
		</div>
		<!--主体-->

	    <!-- 尾部 -->
		<div class="foot">
		</div>
		<!-- 尾部 -->	
	
</body>

	<script src="lib/jquery.js"></script>
	<script src="lib/layer-v3.1.1/layer/layer.js"></script>
	<script src="lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/personal.js"></script>
	
</html>