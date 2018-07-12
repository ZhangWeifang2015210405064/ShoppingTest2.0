package com.test.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;

import com.test.dao.user_favoriteDao;
import com.test.impl.user_favoriteDapImpl;
import com.test.model.Goods;
import com.test.model.User;
import com.test.model.user_favorite;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

/**
 * Servlet implementation class FavoriteServlet
 */
@WebServlet("/FavoriteServlet")
public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteServlet() {
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
		
		User user = (User)request.getSession().getAttribute("user");
		String user_id = String.valueOf(user.getId());
		System.out.println(user_id);
		response.setCharacterEncoding("UTF-8");
		JSONArray ja = new JSONArray();
		
		List<user_favorite> fgoodslist = null;
		if(request.getSession().getAttribute("fgoodslist")!=null){
			fgoodslist=(List<user_favorite>)request.getSession().getAttribute("fgoodslist");
		}else{
			user_favoriteDao user_favoritedapimpl = new user_favoriteDapImpl();
			fgoodslist = user_favoritedapimpl.getAllFavoriteGoods(user_id);
		}
		
		for(int i =0; i < fgoodslist.size(); i++) {
			JSONObject jb = new JSONObject();
			user_favorite uf = fgoodslist.get(i);
			try {
				jb.put("goods_id", uf.getGoods_id());
				jb.put("goods_picture", uf.getGoods_picture());
				jb.put("goods_introduce", uf.getGoods_introduce());
				jb.put("goods_price", uf.getGoods_price());
				jb.put("savedate", uf.getSavedate());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ja.add(jb);
		}
		
		response.getWriter().println(ja);
		
	}
}
