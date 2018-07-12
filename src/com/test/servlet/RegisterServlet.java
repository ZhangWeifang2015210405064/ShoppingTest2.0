package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.MissionDao;
import com.test.dao.UserDao;
import com.test.impl.MissionDaoImpl;
import com.test.impl.UserDaoImpl;
import com.test.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/user/userregister")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user=new User();
		
		String id=request.getParameter("phone");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		
		user.setId(Long.parseLong(id));
		user.setPassword(password);
		user.setEmail(email);
		user.setAvator("img/avator/headshow.jpg");
		user.setNickname("µÚËÄ¿Õ¼äµÄáÌ×Ó");
		
		UserDao dao=new UserDaoImpl();
		boolean flag=dao.register(user);
		
		if(flag) {
			request.setAttribute("info", "×¢²á³É¹¦£¡");
			request.getSession().setAttribute("user", user);
			
			MissionDao missiondao=new MissionDaoImpl();
			missiondao.initialMission(user.getId());
			
			response.sendRedirect("/ShoppingTest/homepage.jsp");
		}else {
			request.setAttribute("info", "×¢²áÊ§°Ü£¡");
			PrintWriter out = response.getWriter();
	    	String a = URLEncoder.encode("×¢²áÊ§°Ü£¡", "UTF-8"); 
	    	out.print("<script language='javascript'>alert(decodeURIComponent('"+a+"'));window.location.href='/ShoppingTest/index.jsp';</script>");
		}
		
	}

}
