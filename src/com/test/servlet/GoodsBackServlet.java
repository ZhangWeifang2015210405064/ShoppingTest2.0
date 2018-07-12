package com.test.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.RecordDao;
import com.test.impl.RecordDaoImpl;

/**
 * Servlet implementation class GoodsBackServlet
 */
@WebServlet("/GoodsBackServlet")
public class GoodsBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsBackServlet() {
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
		
		long backRecordId=Long.parseLong(request.getParameter("backRecordId"));
		
		RecordDao dao=new RecordDaoImpl();
		
		boolean flag=dao.backGoods(backRecordId);
		
		int n=0;
		
		if(flag) {
			n=1;
		}
		
		response.getWriter().print(n);
		
	}

}
