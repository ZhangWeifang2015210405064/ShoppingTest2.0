package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.GoodsDao;
import com.test.dao.MissionDao;
import com.test.dao.UserDao;
import com.test.impl.GoodsDaoImpl;
import com.test.impl.MissionDaoImpl;
import com.test.impl.UserDaoImpl;
import com.test.model.Goods;
import com.test.model.User;

@WebServlet(name="loginServlet",urlPatterns="/user/homepage")
public class LoginServlet extends HttpServlet{
	
  private static final long serialVersionUID = 1L;
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public LoginServlet() {
      super();
      // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    doPost(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    User user = new User();
    String id = request.getParameter("loginId").toString();
    String password = request.getParameter("loginPassword").toString();

    user.setId(Long.parseLong(id));
    user.setPassword(password);
        
    UserDao dao = new UserDaoImpl();
    User tmp = dao.login(user);
    
    
    if(request.getParameter("savepsw")!=null) {//�����ѡ�˱�������  
        //response.getWriter().append(request.getParameter("save"));
        Cookie cookie=new Cookie("fs_id",id);
        Cookie cookie1=new Cookie("fs_password",password);
        cookie.setPath("/");//��֤cookie��ŵĸ�Ŀ¼��ͬ
        cookie1.setPath("/");
        //����cookie���ʱ��Ϊ1��
        cookie.setMaxAge(60*60*24*7);
        cookie1.setMaxAge(60*60*24*7);
        //��cookie�����ڿͻ���
        response.addCookie(cookie);
        response.addCookie(cookie1);
        
    }else {
    	//���û�й�ѡ��������
        Cookie cookie=new Cookie("fs_id",id);
        cookie.setPath("/");//��֤cookie��ŵĸ�Ŀ¼��ͬ
        //����cookie���ʱ��Ϊ19s
        cookie.setMaxAge(60*60*24*7);
        //��cookie�����ڿͻ���
        response.addCookie(cookie);  

    }
    
    if (tmp != null){
    	request.setAttribute("info", "��¼�ɹ���");
    	request.getSession().setAttribute("user", tmp);
    	
    	PrintWriter out = response.getWriter();
    	String a = URLEncoder.encode("��¼�ɹ�", "UTF-8");
    	out.print("<script language='javascript'>alert(decodeURIComponent('"+a+"'));window.location.href='/ShoppingTest/homepage.jsp';</script>");
    	
    	
    	//response.sendRedirect("/ShoppingTest/homepage.jsp");
     }else {
		request.setAttribute("info", "��¼ʧ�ܣ�");
		PrintWriter out = response.getWriter();
    	String a = URLEncoder.encode("��¼ʧ��", "UTF-8"); 
    	out.print("<script language='javascript'>alert(decodeURIComponent('"+a+"'));window.location.href='/ShoppingTest/index.jsp';</script>");
	}
   
  }
}
