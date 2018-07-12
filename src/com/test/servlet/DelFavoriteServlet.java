package com.test.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.user_favoriteDao;
import com.test.impl.user_favoriteDapImpl;
import com.test.model.User;

/**
 * Servlet implementation class DelFavoriteServlet
 */
@WebServlet("/DelFavoriteServlet")
public class DelFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelFavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User user = (User)request.getSession().getAttribute("user");
		//String user_id = ((int)user.getId()) + "";
		User user = (User)request.getSession().getAttribute("user");
		String user_id = String.valueOf(user.getId());
		
		String goods_id = request.getParameter("goods_id");
		
		System.out.println(goods_id);
		System.out.println(user_id);
		user_favoriteDao ufDao = new user_favoriteDapImpl();
		boolean result = ufDao.delFavoriteGoods(goods_id, user_id);
		System.out.println(result);
		response.getWriter().println(result);
	}

}
