package com.test.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.test.dao.GoodsDao;
import com.test.model.Goods;
import com.test.sqlconnection.MySQLConnection;

public class GoodsDaoImpl implements GoodsDao{

	@Override
	public List<Goods> getAllGoods() {
		
		MySQLConnection con = new MySQLConnection();

		try {
	       con.getConnection();
	    } catch (SQLException e) {
	       e.printStackTrace();
	    }

	    List<Goods> list = new ArrayList<Goods>();
	    String sql = "select * from goods";

	    try {
	    	List<Map<String, Object>> mapList = con.findResult(sql, null);

	        for(Map<String, Object> map : mapList) {
	            int id = Integer.parseInt(map.get("goods_id").toString());
	            String name = map.get("goods_name").toString();
	            String picture = map.get("goods_picture").toString();
	            float price = Float.parseFloat(map.get("goods_price").toString());
	            String type = map.get("goods_type").toString();
	            String introduce = map.get("goods_introduce").toString();
	            String supplier = map.get("goods_supplier").toString();
	            String facilitator = map.get("goods_facilitator").toString();
	            String date = map.get("goods_date").toString();
	            int cycle = Integer.parseInt(map.get("goods_cycle").toString());
	            String contact = map.get("goods_contact").toString();
	            Goods good = new Goods(id, name, picture, price, type, introduce, supplier, facilitator, date, cycle, contact);
	            list.add(good);
	         }
	      } catch (SQLException e) {
	    	  e.printStackTrace();
	      } finally {
	    	  try {
	    		  con.releaseConn();
	    	  } catch (SQLException e) {
	    		  e.printStackTrace();
	    	  }
	      }

	    return list;
	}

	@Override
	public List<Goods> getGoodsByCondition(String condition,String type) {
		
		String sql="select * from goods ";
		
		if(type.equals("sports")) {
			sql+="where goods_type='运动'";
		}else if(type.equals("read")) {
			sql+="where goods_type='阅读'";
		}else if(type.equals("food")) {
			sql+="where goods_type='零食'";
		}else if(type.equals("technology")) {
			sql+="where goods_type='科技'";
		}
		
		if(condition.equals("dayup")) {
			sql+="order by goods_date asc";
		}else if(condition.equals("daydown")) {
			sql+="order by goods_date desc";
		}else if(condition.equals("priceup")) {
			sql+="order by goods_price asc";
		}else if(condition.equals("pricedown")) {
			sql+="order by goods_price desc";
		}
		
		List<Goods> goodslist=new ArrayList<Goods>();
		
		MySQLConnection con=new MySQLConnection();
		try {
			con.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		try {
			list=con.findResult(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<list.size();++i) {
			Goods tmp=new Goods();
			tmp.setId((int) list.get(i).get("goods_id"));
			tmp.setName(list.get(i).get("goods_name").toString());
			tmp.setPicture(list.get(i).get("goods_picture").toString());
			tmp.setPrice((float)list.get(i).get("goods_price"));
			tmp.setType(list.get(i).get("goods_type").toString());
			tmp.setIntroduce(list.get(i).get("goods_introduce").toString());
			tmp.setSupplier(list.get(i).get("goods_supplier").toString());
			tmp.setFacilitator(list.get(i).get("goods_facilitator").toString());
			tmp.setDate(list.get(i).get("goods_date").toString());
			tmp.setCycle((int)list.get(i).get("goods_cycle"));
			tmp.setContact(list.get(i).get("goods_contact").toString());
			goodslist.add(tmp);
		}
		
		return goodslist;
	}

	@Override
	public List<Goods> getGoodsByuserId(Long userId) {
		
		List<Goods> goodslist=new ArrayList<Goods>();
		
		MySQLConnection con=new MySQLConnection();
		String sql1="select goods_id from user_favorite where user_id=?";
		
		List<Object> parm=new ArrayList<Object>();
		parm.add(userId);
		
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			list=con.findResult(sql1, parm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<list.size();++i) {
			
			int good_id=(int) list.get(i).get("goods_id");
			goodslist.add(Goods.getGoodsById(good_id));
		}
		
		return goodslist;
	}

	@Override
	public Goods getGoodsById(int id) {
		
		  Goods goods = new Goods();
	      goods.setId(id);
	      MySQLConnection con = new MySQLConnection();

	      try {
	         con.getConnection();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }

	      String sql = "select * from goods where goods_id = '" + id + "'";

	      try {
	    	  Map<String, Object> map = con.findResult(sql,null).get(0);
	    	  goods.setName(map.get("goods_name").toString());
	    	  goods.setPicture("img/" + map.get("goods_picture").toString());
	    	  goods.setPrice(Float.parseFloat(map.get("goods_price").toString()));
	    	  goods.setType(map.get("goods_type").toString());
	    	  goods.setIntroduce(map.get("goods_introduce").toString());
	    	  goods.setSupplier(map.get("goods_supplier").toString());
	    	  goods.setFacilitator(map.get("goods_facilitator").toString());
	    	  goods.setDate(map.get("goods_date").toString());
	    	  goods.setCycle(Integer.parseInt(map.get("goods_cycle").toString()));
	    	  goods.setContact(map.get("goods_contact").toString());
	      } catch (SQLException e) {
	    	  e.printStackTrace();
	      } finally {
	    	  try {
	    		  con.releaseConn();
	    	  } catch (SQLException e) {
	    		  e.printStackTrace();
	         }

	      }

	      return goods;
	}

}
