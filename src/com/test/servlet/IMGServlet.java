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
		//指定上传文件的保存地址
		String message = "";
		String address = "";
		String filename=null;
		String filepath=null;
		boolean i = false;
		request.setCharacterEncoding("UTF-8");
		if (ServletFileUpload.isMultipartContent(request)) { //判断是否上传文件
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(20 * 1024); //设置内存中允许储存的字节数
			ServletFileUpload upload = new ServletFileUpload(factory); //创建新的上传文件句柄
			
			int size = 5 * 1024 * 1024; //制定上传文件的大小
			List formlists = null; //创建保存上传文件的集合对象
			try {
				formlists = upload.parseRequest(request); //获取上传文件集合
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			Iterator iter = formlists.iterator();
			while (iter.hasNext()) {
				FileItem formitem = (FileItem) iter.next(); //获取每个上传文件
				if (!formitem.isFormField()) { //忽略不是上传文件的表单域
					String name = formitem.getName(); //获取上传的文件名称
					String suffix = name.substring(name.lastIndexOf("."), name.length());
					System.out.println(suffix);
					if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".gif") || suffix.equals(".png")) {
						if (formitem.getSize() > size) {
							//message = "上传文件太大，请选择不超过5M的文件";
							message = "图片超出5M。。。";
							System.out.println("图片超出5M");
							break;
						}
						String adjunctsize = new Long(formitem.getSize()).toString();//获取上传文件的大小
						if ((name == null) || (name.equals("")) && (adjunctsize.equals("0")))
							continue;
						filename = name.substring(name.lastIndexOf("\\") + 1, name.length());
						
						address = fileDir + "\\" + filename; //文件保存地址
						//address = "/upload/" + filename;
						File saveFile = new File(address); //根据文件保存地址，创建文件
						System.out.println(saveFile.getPath());
						try {
							formitem.write(saveFile);
							filepath="img/avator/" + filename;
							
							//保存到数据库
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
						message = "请输入图片文件。。。";
					}
				}
			}
		}
		if(i == true) {
			message = "文件传输成功！";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("/ShoppingTest/personal.jsp");
		}
		else {
			message += "文件传输失败！";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("/ShoppingTest/personal.jsp");
		}
		
	}

}
