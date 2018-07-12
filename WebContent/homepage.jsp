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

	<link rel="stylesheet" type="text/css" href="css/homepage.css" />
	<link rel="stylesheet" href="lib/font-awesome-4.7.0/css/font-awesome.min.css">

</head>
<body style="margin:0;">

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
			
<%
	String url=request.getRequestURI();
%>
			
			<div class="content-goods-goodslist-page">
				<a href="homepage.jsp?currentPage=1">首页</a>
				<a href="homepage.jsp?currentPage=<% if(currentPage==1){out.print(1);}else{out.print(currentPage-1);} %>">上一页</a>
				<a href="homepage.jsp?currentPage=<% if(currentPage==pageCount){out.print(currentPage);}else{out.print(currentPage+1);} %>">下一页</a>
				<a href="homepage.jsp?currentPage=<%=pageCount%>">尾页</a>
				<span>第<%=currentPage%>页/共<%=pageCount %>页</span>
			</div>

		</div>

<%
	User user=null;
	if(session.getAttribute("user")!=null){
		user=(User)session.getAttribute("user");
		//session.setMaxInactiveInterval(10);
		MissionDao missiondao=new MissionDaoImpl();
		missiondao.finishMission(user.getId(), 1);
	}
	String abpath = user.getAvator();
%>

		<div class="content-userinfo">
			<div class="content-userinfo-title">
				<i class="fa fa-user-circle"></i>
				<span>个人信息</span>
			</div>

			<div class="content-userinfo-info">
				
				<img src=<%=abpath %> class="content-userinfo-info-head"></img>
				
				<div class="content-userinfo-info-list">
					<i class="fa fa-user"></i>
					<span title='<%=user.getNickname() %>'>昵称：<%=user.getNickname() %></span> 
				</div>
				<div class="content-userinfo-info-list">
					<i class="fa fa-star"></i>
					<span>等级：<%=user.getStar() %></span>
				</div>
				<div class="content-userinfo-info-list">
					<i class="fa fa-money"></i>
					<span>余额：<%=user.getMoney() %></span>
				</div>
			</div>
	<%
		MissionDao missiondao=new MissionDaoImpl();
		List<Mission> missiontmp=missiondao.getMission(user.getId());
	%>
			<div class="content-userinfo-title">
				<i class="fa fa-tasks"></i>
				<span>任务列表</span>
			</div>
			<%
				for(int i=0;i<missiontmp.size();++i){
					Mission mission=missiontmp.get(i);
				
			%>
			<div class="content-userinfo-mission">
				<input class="mission-id" type="hidden" value="<%=mission.getMission_id() %>" />
				<i class="fa fa-calendar"><span><%=mission.getMission_name() %></span></i>
				<i class="fa fa-money"><span class="reward"><%=mission.getReward() %></span></i>
				<button class="mission-finish" ><%=mission.getStatus() %></button>
			</div>
			<%
				}
			%>
			

		</div>

		
	</div>

	<div class="foot">
	</div>

</body>

	<script type="text/javascript" src="lib/jquery.js"></script>
	<script type="text/javascript" src="js/mission.js"></script>
</html>