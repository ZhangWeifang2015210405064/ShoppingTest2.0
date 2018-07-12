package com.test.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.UserDao;
import com.test.impl.UserDaoImpl;
import com.test.model.User;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/user/saveInfo")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
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
		
		User user=(User)request.getSession().getAttribute("user");
		
		String newNickname=request.getParameter("newNickname").toString();
		String newPassword=request.getParameter("newPassword").toString();
		String newPayPassword=request.getParameter("newPaypassword").toString();
		
		user.setNickname(newNickname);
		user.setPassword(newPassword);
		user.setPayPassword(newPayPassword);
		
		UserDao dao=new UserDaoImpl();
		boolean flag=dao.updateInfo(user);
		
		if(flag) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/ShoppingTest/personal.jsp");
		}else {
			
		}
		
	}

}
