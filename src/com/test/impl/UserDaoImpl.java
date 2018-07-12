package com.test.impl;

import com.test.dao.UserDao;
import com.test.model.User;
import com.test.sqlconnection.MySQLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao{
	
  MySQLConnection con = new MySQLConnection();
  
  public User login(User user){
	  
    Map<String, Object> userList = null;
    
    try{
      con.getConnection();
      
      java.util.Date time = new java.sql.Date(new java.util.Date().getTime());
      List<Object> loginTime = new ArrayList<Object>();
      loginTime.add(time);
      loginTime.add(user.getId());
      
      
      List<Object> info = new ArrayList<Object>();
      info.add(user.getId());
      info.add(user.getPassword());
      
      String sql1 = "update user_base set logintime=?  where id=?";
      this.con.updateByPreparedStatement(sql1, loginTime);
      
      String sql = "select * from user_base natural join user_info where id=? and password=?";
      
      if(con.findResult(sql, info)!=null && con.findResult(sql, info).size()!=0) {
    	  userList = con.findResult(sql, info).get(0);
      }
      
      if ((userList != null) && (userList.size() != 0)){
        user.setNickname(userList.get("nickname").toString());
        user.setStar(Integer.parseInt(userList.get("star").toString()));
        user.setEmail(userList.get("email").toString());
        user.setAvator( userList.get("avator").toString());
        user.setMoney(Float.parseFloat(userList.get("money").toString()));
        
        user.setPhone(Long.parseLong(userList.get("phone").toString()));
        if(user.getName() != null) 
        	user.setName(userList.get("name").toString());  
        if(user.getAddress() != null) 
        	user.setAddress(userList.get("address").toString());
        if(user.getPayPassword() != null)
        	user.setPayPassword(userList.get("paypassword").toString());
        
        return user;
      }
      return null;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public boolean register(User user){
	  
	  String sql_base = "insert into user_base(id,password,email,avator,star) values(?,?,?,?,?) ";
	  List<Object> list = new ArrayList<Object>();
	  list.add(user.getId());
	  list.add(user.getPassword());
	  list.add(user.getEmail());
	  list.add(user.getAvator());
	  list.add(user.getStar());
	  
	  
	  String sql_info = "insert into user_info(id, phone, money) values(?,?,?)";
	  List<Object> list_info = new ArrayList<Object>();
	  list_info.add(user.getId());
	  list_info.add(user.getPhone());
	  list_info.add(user.getMoney());
    
	  boolean flag = false;
	  boolean flag_info = false;
	  try{
		  con.getConnection();
		  flag = this.con.updateByPreparedStatement(sql_base, list);
		  flag_info = con.updateByPreparedStatement(sql_info, list_info);
	  }
	  catch (SQLException e){
		  e.printStackTrace();
	  }
	  if(flag == true && flag_info == true) {
		  return true;
	  } else
		  return false;
  }

@Override
public boolean updateInfo(User user) {
	  String sql = "update user_base,user_info set user_base.nickname=?,user_base.password=?,user_info.paypassword=? where user_base.id=? and user_info.id=?";
	  List<Object> list = new ArrayList<Object>();
	  list.add(user.getNickname());
	  list.add(user.getPassword());
	  list.add(user.getPayPassword());
	  list.add(user.getId());
	  list.add(user.getId());
  
	  boolean flag = false;
	  try{
		  con.getConnection();
		  flag = this.con.updateByPreparedStatement(sql, list);
	  }
	  catch (SQLException e){
		  e.printStackTrace();
	  }
	  return flag;
}

@Override
public boolean updateAddress(User user) {
	  String sql = "update user_info set address=?,phone=?,name=? where id=?";
	  List<Object> list = new ArrayList<Object>();
	  list.add(user.getAddress());
	  list.add(user.getPhone());
	  list.add(user.getName());
	  list.add(user.getId());
	  
	  System.out.println(user.getAddress() + " " +user.getName());

	  boolean flag = false;
	  try{
		  con.getConnection();
		  flag = this.con.updateByPreparedStatement(sql, list);
	  }
	  catch (SQLException e){
		  e.printStackTrace();
	  }
	  return flag;
}


@Override
public boolean updateMoney(float money, long userId, String password) {
	String sql="update user_info set money=? where id=? and paypassword=?";
	
	List<Object> list=new ArrayList<Object>();
	list.add(money);
	list.add(userId);
	list.add(password);
	
	boolean flag=false;
	
	try {
		con.getConnection();
		flag=con.updateByPreparedStatement(sql, list);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return flag;
}

@Override
public boolean updateFavorite(long user_id, int item_id) {
	
	java.util.Date time = new java.sql.Date(new java.util.Date().getTime());
	
	String sql="insert into user_favorite values(?,?,?)";
	
	List<Object> list=new ArrayList<Object>();
	list.add(user_id);
	list.add(item_id);
	list.add(time);
	
	boolean flag=false;
	
	try {
		con.getConnection();
		flag=con.updateByPreparedStatement(sql, list);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return flag;
}

}
