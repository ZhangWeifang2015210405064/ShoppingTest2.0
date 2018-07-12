package com.test.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.test.model.User;
import com.test.sqlconnection.MySQLConnection;


/**
 * Servlet implementation class IMGServlet
 */
@WebServlet("/IMGServlet")
public class IMGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IMGServlet() {
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
		String fileDir = getServletContext().getRealPath("/img/avator");
		System.out.println("fileDir:" + fileDir);
		//ָ���ϴ��ļ��ı����ַ
		String message = "";
		String address = "";
		String filename=null;
		String filepath=null;
		boolean i = false;
		request.setCharacterEncoding("UTF-8");
		if (ServletFileUpload.isMultipartContent(request)) { //�ж��Ƿ��ϴ��ļ�
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(20 * 1024); //�����ڴ�����������ֽ���
			ServletFileUpload upload = new ServletFileUpload(factory); //�����µ��ϴ��ļ����
			
			int size = 5 * 1024 * 1024; //�ƶ��ϴ��ļ��Ĵ�С
			List formlists = null; //���������ϴ��ļ��ļ��϶���
			try {
				formlists = upload.parseRequest(request); //��ȡ�ϴ��ļ�����
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			Iterator iter = formlists.iterator();
			while (iter.hasNext()) {
				FileItem formitem = (FileItem) iter.next(); //��ȡÿ���ϴ��ļ�
				if (!formitem.isFormField()) { //���Բ����ϴ��ļ��ı���
					String name = formitem.getName(); //��ȡ�ϴ����ļ�����
					String suffix = name.substring(name.lastIndexOf("."), name.length());
					System.out.println(suffix);
					if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".gif") || suffix.equals(".png")) {
						if (formitem.getSize() > size) {
							//message = "�ϴ��ļ�̫����ѡ�񲻳���5M���ļ�";
							message = "ͼƬ����5M������";
							System.out.println("ͼƬ����5M");
							break;
						}
						String adjunctsize = new Long(formitem.getSize()).toString();//��ȡ�ϴ��ļ��Ĵ�С
						if ((name == null) || (name.equals("")) && (adjunctsize.equals("0")))
							continue;
						filename = name.substring(name.lastIndexOf("\\") + 1, name.length());
						
						address = fileDir + "\\" + filename; //�ļ������ַ
						//address = "/upload/" + filename;
						File saveFile = new File(address); //�����ļ������ַ�������ļ�
						System.out.println(saveFile.getPath());
						try {
							formitem.write(saveFile);
							filepath="img/avator/" + filename;
							
							//���浽���ݿ�
							User user = (User)request.getSession().getAttribute("user");
							String user_id = Long.toString(user.getId());
							System.out.println(user_id);
							MySQLConnection con = new MySQLConnection();
							con.getConnection();
							String sql = "update user_base set avator ='" + filepath + "' where id =" + user_id;
							i = con.updateByPreparedStatement(sql, null);
							System.out.println(i);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else {
						message = "������ͼƬ�ļ�������";
					}
				}
			}
		}
		if(i == true) {
			message = "�ļ�����ɹ���";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("/ShoppingTest/personal.jsp");
		}
		else {
			message += "�ļ�����ʧ�ܣ�";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("/ShoppingTest/personal.jsp");
		}
		
	}

}
