package com.test.dao;

import java.util.List;

import com.test.model.Goods;

public interface GoodsDao {
	
	public List<Goods> getAllGoods();
	
	public List<Goods> getGoodsByCondition(String condition,String type);
	
	public List<Goods> getGoodsByuserId(Long userId);
	
	public Goods getGoodsById(int id);
	
}
