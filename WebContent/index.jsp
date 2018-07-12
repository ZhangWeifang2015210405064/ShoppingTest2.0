<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.test.model.*,com.test.dao.*,com.test.impl.*" %>

<% 
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
%>

<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+":/"+request.getServerName()+":/"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>

	<link rel="stylesheet" type="text/css" href="css/index.css" />
	<link rel="stylesheet" href="lib/font-awesome-4.7.0/css/font-awesome.min.css">
	<link href="css/register.css" rel="stylesheet">
	<link href="css/login.css" rel="stylesheet">

</head>
<body style="margin:0;">

<%
    //进入主页先判断是否存在cookie，如果存在则读取cookie直接前往登录后的主页
    Cookie[] cookies=request.getCookies();
    if(cookies!=null){
    	User user = new User();
    	String fs_id="";
    	String fs_password="";
    	for(int i=0;i<cookies.length;i++) {
        	if(cookies[i].getName().equals("fs_id")){
        		fs_id=cookies[i].getValue();
        		user.setId(Long.parseLong(fs_id));
    		}
        	if(cookies[i].getName().equals("fs_password")){
        		fs_password=cookies[i].getValue();
        		user.setPassword(fs_password);
        	}
    	}
    	if(!fs_id.equals("") && !fs_password.equals("")){
    		
    		UserDao dao = new UserDaoImpl();
    	    User tmp = dao.login(user);
    		
    	    if (tmp != null){
    	    	request.getSession().setAttribute("user", tmp);

    	    	response.sendRedirect("/ShoppingTest/homepage.jsp");
    	    	
    	    }
    	}
	}
%>

	<%-- <%@ include file="src/head.jsp" %> --%>
	<jsp:include page="src/head.jsp"></jsp:include>

<%!
	//单页商品数
	public static final int PageSize=8;
 	//页数
	int pageCount;
 	//当前页
 	int currentPage=1;
 	//定位到某条记录
 	int absolute;
 	
 	int itemId;
 	
%>

	<div class="content">
		<div class="content-goods">
			
			<%-- <%@ include file="src/public.jsp" %> --%>
			<jsp:include page="src/public.jsp"></jsp:include>

			<div class="content-goods-goodslist">

<%
	List<Goods> list=null;
	if(request.getSession().getAttribute("goods")!=null){
		list=(List<Goods>)session.getAttribute("goods");
	}else{
		GoodsDao goods=new GoodsDaoImpl();
		list=goods.getAllGoods();
		session.setAttribute("goods", list);
	}
	//List<Goods> list=Goods.getAllGoods();
	int size=list.size();
	pageCount=(size%PageSize==0)?(size/PageSize):(size/PageSize+1);
	String tmp=request.getParameter("currentPage");
	String pcount=request.getParameter("PageCount");
	if(tmp==null || tmp.equals("0")){
		tmp="1";
	}
	currentPage=Integer.parseInt(tmp);
	if(pcount!=null){
		currentPage=Integer.parseInt(pcount);
	}
	if(currentPage>=pageCount){
		currentPage=pageCount;
	}
	absolute=(currentPage-1)*PageSize+1;
	
	for(int i=absolute-1;i<(absolute-1+PageSize);++i){
		if(i>size-1){
			break;
		}
		Goods good=list.get(i);
		itemId=good.getId();
%>
				
				<a href="goods.jsp?itemId=<%=itemId %>" target="_blank" class="content-goods-goodslist-item" style="text-decoration:none;">
				
					<img src="img/<%=good.getPicture() %>" alt="图片加载失败">
					<div class="item-introduce" style="color:#666666;"><%=good.getIntroduce() %></div>
					<div class="item-price">
						<i class="fa fa-money">&nbsp<%=good.getPrice() %></i>
						<span style="font-size: 10px;color:#666666;">142人已购</span>
					</div>
					
				</a>

<%
	}
%>
			</div>
			
			
			<div class="content-goods-goodslist-page">
				<a href="index.jsp?currentPage=1">首页</a>
				<a href="index.jsp?currentPage=<% if(currentPage==1){out.print(1);}else{out.print(currentPage-1);} %>">上一页</a>
				<a href="index.jsp?currentPage=<% if(currentPage==pageCount){out.print(currentPage);}else{out.print(currentPage+1);} %>">下一页</a>
				<a href="index.jsp?currentPage=<%=pageCount%>">尾页</a>
				<span>第<%=currentPage%>页/共<%=pageCount %>页</span>
			</div>


		</div>

		<div class="content-userinfo">
			<div class="content-userinfo-title">
				<i class="fa fa-user-circle"></i>
				<span>个人信息</span>
			</div>

			<div class="content-userinfo-info">您尚未登录，无法查看个人信息！</div>
	
			<div class="content-userinfo-title">
				<i class="fa fa-tasks"></i>
				<span>任务列表</span>
			</div>
			
			<div class="content-userinfo-info">您尚未登录，无法查看任务列表！</div>
			
		</div>

	
	</div>
	<div class="foot"></div>

</body>

	<script src="lib/jquery.js"></script>
	<script src="lib/layer-v3.1.1/layer/layer.js"></script>
	<script src="lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript" src="js/register.js"></script>
	
</html>