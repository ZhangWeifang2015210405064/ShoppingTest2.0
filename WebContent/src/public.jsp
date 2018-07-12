<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.test.model.*,com.test.dao.*,com.test.impl.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


			<div class="content-goods-selectlist">
				
				<i class="fa fa-shopping-cart fa-2x"></i>

				<span style="font-size: 20px;">商品区</span>

				<form action="TypeServlet" method="post" id="typeForm">
				
				<select id="sort" name="sort" onchange="document.getElementById('typeForm').submit();">
				
				<%
					List<String> tmptype=(ArrayList<String>)session.getAttribute("sorttype");
					if(tmptype!=null){
						
						switch(tmptype.get(0)){
						case "default":
				%>
					<option value="default" selected>默认</option> 
					<option value="dayup">按日期升序</option>
					<option value="daydown">按日期降序</option>
					<option value="priceup">按价格升序</option>
					<option value="pricedown">按价格降序</option>

				<%
							break;
						case "dayup":
				%>
					<option value="default">默认</option> 
					<option value="dayup" selected>按日期升序</option>
					<option value="daydown">按日期降序</option>
					<option value="priceup">按价格升序</option>
					<option value="pricedown">按价格降序</option>
							
				<%
							break;
						case "daydown":
				%>
							
					<option value="default">默认</option> 
					<option value="dayup">按日期升序</option>
					<option value="daydown" selected>按日期降序</option>
					<option value="priceup">按价格升序</option>
					<option value="pricedown">按价格降序</option>
							
				<%
							break;
						case "priceup":
				%>
							
					<option value="default">默认</option> 
					<option value="dayup">按日期升序</option>
					<option value="daydown">按日期降序</option>
					<option value="priceup" selected>按价格升序</option>
					<option value="pricedown">按价格降序</option>
							
				<%
							break;
						case "pricedown":
				%>
							
					<option value="default">默认</option> 
					<option value="dayup">按日期升序</option>
					<option value="daydown">按日期降序</option>
					<option value="priceup">按价格升序</option>
					<option value="pricedown" selected>按价格降序</option>
							
				<%
							break;
						default:
							break;
						}
				%>
			
				</select>
				<select id="type" name="type" onchange="document.getElementById('typeForm').submit();">
				<%
						switch(tmptype.get(1)){
						case "all":
				%>
					<option value="all" selected>全部</option>
					<option value="sports">运动</option>
					<option value="read">阅读</option>
					<option value="food">零食</option>
					<option value="technology">科技</option>		
				<% 
						break;
						case "sports":
				%>
					<option value="all">全部</option>
					<option value="sports" selected>运动</option>
					<option value="read">阅读</option>
					<option value="food">零食</option>
					<option value="technology">科技</option>
				<%
						break;
						case "read":
				%>
					<option value="all">全部</option>
					<option value="sports">运动</option>
					<option value="read" selected>阅读</option>
					<option value="food">零食</option>
					<option value="technology">科技</option>
				<%
						break;
						case "food":
				%>
					<option value="all">全部</option>
					<option value="sports">运动</option>
					<option value="read">阅读</option>
					<option value="food" selected>零食</option>
					<option value="technology">科技</option>
				<%
						break;
						case "technology":
				%>
					<option value="all">全部</option>
					<option value="sports">运动</option>
					<option value="read">阅读</option>
					<option value="food">零食</option>
					<option value="technology" selected>科技</option>
				<%
						default:
							break;
						}
				%>
				
				<%	
					}else{
						
				%>
					<option value="default">默认</option>
					<option value="dayup">按日期升序</option>
					<option value="daydown">按日期降序</option>
					<option value="priceup">按价格升序</option>
					<option value="pricedown">按价格降序</option>
				</select>

				<select id="type" name="type" onchange="document.getElementById('typeForm').submit();">
					<option value="all">全部</option>
					<option value="sports">运动</option>
					<option value="read">阅读</option>
					<option value="food">零食</option>
					<option value="technology">科技</option>
				<%
					}
				%>
				
				</select>
				
				</form>

				<span>选择您感兴趣的商品，点击商品可查看详细信息</span>

			</div>
