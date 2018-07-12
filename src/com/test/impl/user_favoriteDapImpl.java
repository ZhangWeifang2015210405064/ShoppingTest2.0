package com.test.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.test.dao.user_favoriteDao;
import com.test.model.user_favorite;
import com.test.sqlconnection.MySQLConnection;

public class user_favoriteDapImpl implements user_favoriteDao {
	
	@Override
	public List<user_favorite> getAllFavoriteGoods(String user_id) {
		MySQLConnection con = new MySQLConnection();
		
		try {
		    con.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<user_favorite> fgoodslist=new ArrayList<user_favorite>();
		String sql = "select * from goods natural join user_favorite where user_id =" + user_id;
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		try {
			list=con.findResult(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<list.size();++i) {
			user_favorite tmp=new user_favorite();
			tmp.setGoods_id((int) list.get(i).get("goods_id"));
			tmp.setGoods_picture(list.get(i).get("goods_picture").toString());
			tmp.setGoods_price((float)list.get(i).get("goods_price"));
			tmp.setGoods_introduce(list.get(i).get("goods_introduce").toString());
			tmp.setSavedate(list.get(i).get("savedate").toString());
			fgoodslist.add(tmp);
		}
		
		return fgoodslist;
		    
	}
	
	@Override
	public boolean delFavoriteGoods(String goods_id, String user_id)  {
		MySQLConnection con = new MySQLConnection();
		boolean result = false;
		String sql = "delete from user_favorite where goods_id ='" + goods_id + "'and user_id =" + user_id;
		try {
		    con.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			result = con.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
