package com.test.sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLConnection
{
  private static String user = "root";
  private static String password = "";
  private static String driver = "com.mysql.jdbc.Driver";
  private static String url = "jdbc:mysql://127.0.0.1:3306/forthspace?characterEncoding=utf-8";
  private Connection connection;
  private PreparedStatement preStatement;
  private ResultSet resultSet;
  
  public Connection getConnection() throws SQLException{
    
	try{
      Class.forName(driver);
      this.connection = DriverManager.getConnection(url, user, password);
    }
    catch (ClassNotFoundException e)
    {
      throw new RuntimeException("get connection error!", e);
    }
    return this.connection;
  }
  
  public boolean updateByPreparedStatement(String sql, List<?> params) throws SQLException{
    boolean flag = false;
    int result = -1;
    this.preStatement = this.connection.prepareStatement(sql);
    int index = 1;
    if ((params != null) && (!params.isEmpty())) {
      for (int i = 0; i < params.size(); i++) {
        this.preStatement.setObject(index++, params.get(i));
      }
    }
    result = this.preStatement.executeUpdate();
    flag = result > 0;
    return flag;
  }
  
  public List<Map<String, Object>> findResult(String sql, List<?> params) throws SQLException
  {
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    int index = 1;
    this.preStatement = this.connection.prepareStatement(sql);
    if ((params != null) && (!params.isEmpty())) {
      for (int i = 0; i < params.size(); i++) {
        this.preStatement.setObject(index++, params.get(i));
      }
    }
    this.resultSet = this.preStatement.executeQuery();
    ResultSetMetaData metaData = this.resultSet.getMetaData();
    int col_len = metaData.getColumnCount();
    while (this.resultSet.next())
    {
      Map<String, Object> map = new HashMap<String, Object>();
      for (int i = 0; i < col_len; i++)
      {
        String col_name = metaData.getColumnName(i + 1);
        Object col_value = this.resultSet.getObject(col_name);
        if (col_value == null) {
          col_value = "";
        }
        map.put(col_name, col_value);
      }
      list.add(map);
    }
    return list;
  }
  
  public void releaseConn() throws SQLException
  {
    if (this.resultSet != null) {
      this.resultSet.close();
    }
    if (this.preStatement != null) {
      this.preStatement.close();
    }
    if (this.connection != null) {
      this.connection.close();
    }
  }
}