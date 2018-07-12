package com.test.dao;

import java.util.List;

import com.test.model.user_favorite;

public interface user_favoriteDao {
	
	public List<user_favorite> getAllFavoriteGoods(String user_id);
	
	public boolean delFavoriteGoods(String goods_id, String user_id);
	
}
