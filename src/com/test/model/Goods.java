package com.test.model;

import com.test.sqlconnection.MySQLConnection;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Goods implements Serializable{

   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   
   private int id;
   private String name;
   private String picture;
   private float price;
   private String type;
   private String introduce;
   private String supplier;
   private String facilitator;
   private String date;
   private int cycle;
   private String contact;


   public Goods(int id, String name, String picture, float price, String type, String introduce, String supplier, String facilitator, String date, int cycle, String contact) {
      this.id = id;
      this.name = name;
      this.picture = picture;
      this.price = price;
      this.type = type;
      this.introduce = introduce;
      this.supplier = supplier;
      this.facilitator = facilitator;
      this.date = date;
      this.cycle = cycle;
      this.contact = contact;
   }

   public Goods() {
      this.id = 0;
      this.name = "";
      this.picture = "";
      this.price = 0.0F;
      this.type = "";
      this.introduce = "";
      this.supplier = "";
      this.facilitator = "";
      this.date = "";
      this.cycle = 0;
      this.contact = "";
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPicture() {
      return this.picture;
   }

   public void setPicture(String picture) {
      this.picture = picture;
   }

   public float getPrice() {
      return this.price;
   }

   public void setPrice(float price) {
      this.price = price;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getIntroduce() {
      return this.introduce;
   }

   public void setIntroduce(String introduce) {
      this.introduce = introduce;
   }

   public String getSupplier() {
      return this.supplier;
   }

   public void setSupplier(String supplier) {
      this.supplier = supplier;
   }

   public String getFacilitator() {
      return this.facilitator;
   }

   public void setFacilitator(String facilitator) {
      this.facilitator = facilitator;
   }

   public String getDate() {
      return this.date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public int getCycle() {
      return this.cycle;
   }

   public void setCycle(int cycle) {
      this.cycle = cycle;
   }

   public String getContact() {
      return this.contact;
   }

   public void setContact(String contact) {
      this.contact = contact;
   }

   public static Goods getGoodsById(int id) {
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
    	  goods.setPicture("../img/" + map.get("goods_picture").toString());
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