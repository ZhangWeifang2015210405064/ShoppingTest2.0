package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.GoodsDao;
import com.test.impl.GoodsDaoImpl;
import com.test.model.Goods;

/**
 * Servlet implementation class TypeServlet
 */
@WebServlet("/TypeServlet")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeServlet() {
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
		String sort=request.getParameter("sort");
		String type=request.getParameter("type");
		List<String> sorttype=new ArrayList<String>();
		
		System.out.println(sort);
		System.out.println(type);
		
		sorttype.add(sort);
		sorttype.add(type);
		
		request.getSession().setAttribute("sorttype", sorttype);
		
		GoodsDao goods=new GoodsDaoImpl();
		List<Goods> goodslist=goods.getGoodsByCondition(sort, type);
		
		if(goodslist!=null) {
			request.getSession().setAttribute("goods", goodslist);
			
			//request.getRequestDispatcher("").forward(request, response);
			
			if(request.getSession().getAttribute("user")!=null) {
				response.sendRedirect("/ShoppingTest/homepage.jsp");
			}else {
				response.sendRedirect("/ShoppingTest/index.jsp");
			}

		}
		
		
	}

}
