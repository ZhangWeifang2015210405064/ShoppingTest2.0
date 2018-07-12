package com.test.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Date;

import com.test.dao.RecordDao;
import com.test.model.Record;
import com.test.sqlconnection.MySQLConnection;

public class RecordDaoImpl implements RecordDao{

	@Override
	public List<Record> getAllRecord(long userId) {
		
		MySQLConnection con=new MySQLConnection();
		
		String sql="select *from shopping_record where version=1 and user_id="+userId;
		
		List<Record> recordlist=new ArrayList<Record>();
		
		try {
			con.getConnection();
			List<Map<String, Object>> mapList = con.findResult(sql, null);
			for(Map<String, Object> map : mapList) {
				Record tmp=new Record();
				tmp.setId(Long.parseLong(map.get("id").toString()));
				tmp.setAddress(map.get("address").toString());
				tmp.setUserId(Long.parseLong(map.get("user_id").toString()));
				tmp.setItemId(Integer.parseInt(map.get("good_id").toString()));
				tmp.setStatus(map.get("status").toString());
				tmp.setBuy_date(Date.valueOf(map.get("buy_date").toString()));
				if(map.get("return_date").toString().length()!=0) {
					tmp.setReturn_date(Date.valueOf(map.get("return_date").toString()));
				}
				tmp.setCustomer(map.get("customer").toString());
				tmp.setVersion((int)map.get("version"));
				recordlist.add(tmp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return recordlist;
	}

	@Override
	public boolean updateRecord(Record record) {
		
		MySQLConnection con = new MySQLConnection();
		
		String sql="insert into shopping_record(good_id,user_id,status,buy_date,return_date,address,customer,version) values(?,?,?,?,?,?,?,1)";
		
		List<Object> parm=new ArrayList<Object>();
		parm.add(record.getItemId());
		parm.add(record.getUserId());
		parm.add(record.getStatus());
		parm.add(record.getBuy_date());
		parm.add(record.getReturn_date());
		parm.add(record.getAddress());
		parm.add(record.getCustomer());
		
		boolean flag=false;
		
		try {
			con.getConnection();
			flag=con.updateByPreparedStatement(sql, parm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean deleteRecord(long id) {
		
		MySQLConnection con = new MySQLConnection();
		
		String sql="update shopping_record set version=0 where id="+id;
		
		boolean flag=false;
		
		try {
			con.getConnection();
			flag=con.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean backGoods(long id) {
		MySQLConnection con = new MySQLConnection();
		
		String sql="update shopping_record set status='ÍË¹ºÖÐ' where id="+id;
		
		boolean flag=false;
		
		try {
			con.getConnection();
			flag=con.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

}
