package com.test.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.MissionDao;
import com.test.dao.UserDao;
import com.test.impl.MissionDaoImpl;
import com.test.impl.UserDaoImpl;
import com.test.model.Goods;
import com.test.model.User;

/**
 * Servlet implementation class UpdateFavoriteServlet
 */
@WebServlet("/UpdateFavoriteServlet")
public class UpdateFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFavoriteServlet() {
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
		
		User user=(User)request.getSession().getAttribute("user");
		
		Goods goods=(Goods)request.getSession().getAttribute("tmpItem");
		
		UserDao dao=new UserDaoImpl();
		boolean flag=dao.updateFavorite(user.getId(), goods.getId());
		
		int n=0;
		if(flag) {
			n=1;
			MissionDao missiondao=new MissionDaoImpl();
	    	missiondao.finishMission(user.getId(), 4);
		}
		
		response.getWriter().print(n);
		
	}

}
