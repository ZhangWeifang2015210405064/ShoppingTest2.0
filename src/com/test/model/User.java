package com.test.model;

import java.io.Serializable;

public class User implements Serializable{
	
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  
  private long id;
  private String password;
  private String nickname;
  private String avator;
  private int star;
  private String email;
  private String name;
  private String address;
  private String payPassword;
  private long phone;
  private float money;
  
  public User() {
	  this.id=0;
	  this.password="";
	  this.nickname="";
	  this.avator="";
	  this.star=0;
	  this.email="";
	  this.name="";
	  this.address="";
	  this.payPassword="";
	  this.phone=0;
	  this.money=0;
	  
  }
  
  public long getId()
  {
    return this.id;
  }
  
  public void setId(long id)
  {
    this.id = id;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getNickname()
  {
    return this.nickname;
  }
  
  public void setNickname(String nickname)
  {
    this.nickname = nickname;
  }
  
  public int getStar()
  {
    return this.star;
  }
  
  public void setStar(int star)
  {
    this.star = star;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public String getPayPassword()
  {
    return this.payPassword;
  }
  
  public void setPayPassword(String payPassword)
  {
    this.payPassword = payPassword;
  }
  
  public long getPhone()
  {
    return this.phone;
  }
  
  public void setPhone(long phone)
  {
    this.phone = phone;
  }
  
  public String getAvator()
  {
    return this.avator;
  }
  
  public void setAvator(String avator)
  {
    this.avator = avator;
  }
  
  public float getMoney()
  {
    return this.money;
  }
  
  public void setMoney(float money)
  {
    this.money = money;
  }
}
