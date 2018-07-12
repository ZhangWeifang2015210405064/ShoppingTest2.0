package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.MissionDao;
import com.test.dao.RecordDao;
import com.test.dao.UserDao;
import com.test.impl.MissionDaoImpl;
import com.test.impl.RecordDaoImpl;
import com.test.impl.UserDaoImpl;
import com.test.model.Goods;
import com.test.model.Record;
import com.test.model.User;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/user/buyprocess")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		User user=null;
		Goods goods=null;
		Record record=new Record();
		
		if(request.getSession().getAttribute("user")!=null) {
			user=(User)request.getSession().getAttribute("user");
		}
		
		if(request.getSession().getAttribute("tmpItem")!=null) {
			goods=(Goods)request.getSession().getAttribute("tmpItem");
		}
		
		java.util.Date date = new java.sql.Date(new java.util.Date().getTime());
		
		record.setItemId(goods.getId());
		record.setUserId(user.getId());
		record.setStatus("¼Ä¼þÖÐ");
		record.setBuy_date(date);
		record.setAddress(request.getParameter("trueAddress"));
		record.setCustomer(request.getParameter("customer"));
		
		String paypassword="";
		paypassword=request.getParameter("pwd_hid").toString();
		
		float tmpmoney=user.getMoney()-goods.getPrice();
		UserDao dao1=new UserDaoImpl();
		boolean flag1=dao1.updateMoney(tmpmoney,user.getId(),paypassword);
		
		boolean flag=false;
		
		if(flag1) {
			
			user.setMoney(tmpmoney);
			request.getSession().setAttribute("user", user);
			
			RecordDao dao=new RecordDaoImpl();
			flag=dao.updateRecord(record);
		}
		
		int n=0;
		if(flag && flag1) {
			n=1;
			MissionDao missiondao=new MissionDaoImpl();
	    	missiondao.finishMission(user.getId(), 3);
		}else {
			n=0;
		}
		
		response.getWriter().print(n);
	
	}

}
