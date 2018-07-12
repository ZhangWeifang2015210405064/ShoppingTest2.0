package com.test.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.MissionDao;
import com.test.impl.MissionDaoImpl;
import com.test.model.User;

/**
 * Servlet implementation class MissionServlet
 */
@WebServlet("/MissionServlet")
public class MissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MissionServlet() {
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
		
		long missionId=Long.parseLong(request.getParameter("missionId").toString());
		
		float reward=Float.parseFloat(request.getParameter("reward").toString());
		
		MissionDao dao=new MissionDaoImpl();
		
		int missionFinished=dao.checkMission(missionId, user.getId());
		
		if(missionFinished==2) {
			boolean doMission=dao.updateMission(missionId, user.getId(),reward);
		}
		
		response.getWriter().print(missionFinished);
		
	}

}
