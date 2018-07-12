package com.test.model;

public class user_favorite {
	private long user_id;
	private int goods_id;
	private String savedate;
	private String goods_picture;
	private String goods_introduce;
	private float goods_price;
	
	public user_favorite(long user_id, int goods_id, String savedate, 
			String goods_picture, String goods_introduce, float goods_price) {
		this.user_id = user_id;
		this.goods_id = goods_id;
		this.savedate = savedate;
		this.goods_picture = goods_picture;
		this.goods_introduce = goods_introduce;
		this.goods_price = goods_price;
	}

	public user_favorite() {
		user_id = 0;
		goods_id = 0;
		savedate = "";
		goods_picture = "";
		goods_introduce = "";
		goods_price = 0;
	}

	public String getGoods_picture() {
		return goods_picture;
	}

	public void setGoods_picture(String goods_picture) {
		this.goods_picture = goods_picture;
	}

	public String getGoods_introduce() {
		return goods_introduce;
	}

	public void setGoods_introduce(String goods_introduce) {
		this.goods_introduce = goods_introduce;
	}

	public float getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(float goods_price) {
		this.goods_price = goods_price;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public String getSavedate() {
		return savedate;
	}

	public void setSavedate(String savedate) {
		this.savedate = savedate;
	}
}
