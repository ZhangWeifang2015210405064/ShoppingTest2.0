package com.test.dao;

import com.test.model.User;

public interface UserDao{
	
  public User login(User paramUser);
  
  public boolean register(User paramUser);
  
  public boolean updateInfo(User paramUser);
  
  public boolean updateAddress(User user);
  
  public boolean updateMoney(float money,long userId,String password);
  
  public boolean updateFavorite(long user_id,int item_id);
  
}