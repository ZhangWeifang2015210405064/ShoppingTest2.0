package com.test.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.GoodsDao;
import com.test.impl.GoodsDaoImpl;
import com.test.model.Goods;
import com.test.model.User;

/**
 * Servlet implementation class CheckBuyServlet
 */
@WebServlet("/user/buy")
public class CheckBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckBuyServlet() {
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
		
		User user=null;
		Goods goods=null;
		
		if(request.getSession().getAttribute("user")!=null) {
			user=(User)request.getSession().getAttribute("user");
		}
		
		if(request.getSession().getAttribute("tmpItem")!=null) {
			goods=(Goods)request.getSession().getAttribute("tmpItem");
		}
		
		int n=0;
		if(user==null) {
			n=0;
		}else if(user.getMoney()<goods.getPrice()) {
			n=2;
		}else{
			n=1;
		}
		
		response.getWriter().print(n);
		
	}

}
