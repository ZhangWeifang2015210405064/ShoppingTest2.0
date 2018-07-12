package com.test.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.test.dao.MissionDao;
import com.test.model.Mission;
import com.test.sqlconnection.MySQLConnection;

public class MissionDaoImpl implements MissionDao{

	MySQLConnection con=new MySQLConnection();
	
	@Override
	public List<Mission> getMission(long userId) {
		
		String sql="select *from mission_today join mission_all on mission_today.mission_id=mission_all.mission_id where user_id="+userId;
		List<Mission> missionList=new ArrayList<Mission>();
		
		try {
			con.getConnection();
			
			List<Map<String, Object>> mapList = con.findResult(sql, null);
			
			for(Map<String, Object> map : mapList) {
				Mission tmp=new Mission();
				tmp.setMission_id(Long.parseLong(map.get("mission_id").toString()));
				tmp.setMission_name(map.get("mission_name").toString());
				tmp.setMission_description(map.get("description").toString());
				tmp.setReward(Float.parseFloat(map.get("reward").toString()));
				tmp.setStatus(map.get("status").toString());
				missionList.add(tmp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return missionList;
	}

	@Override
	public boolean updateMission(long mission_id, long user_id,float reward) {
		
		String sql="update mission_today set status='已领取' where mission_id=? and user_id=?";
		
		String sql1="update user_info set money=money+"+reward+" where id="+user_id;
		
		List<Object> parm=new ArrayList<Object>();
		parm.add(mission_id);
		parm.add(user_id);
		
		boolean flag=false;
		
		try {
			con.getConnection();
			flag=con.updateByPreparedStatement(sql, parm);
			con.updateByPreparedStatement(sql1, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public int checkMission(long mission_id, long user_id) {
		
		String sql="select * from mission_today where user_id="+user_id+" and mission_id="+mission_id;
		
		List<Map<String, Object>> mapList=new ArrayList<Map<String,Object>>();
		try {
			con.getConnection();
			mapList=con.findResult(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int flag=0;
		
		if(mapList.get(0).get("status").equals("已领取")) {
			flag=1;
		}
		
		if(mapList.get(0).get("status").equals("可领取")) {
			flag=2;
		}
		
		if(mapList.get(0).get("status").equals("未完成")){
			flag=0;
		}
		
		return flag;
	}

	@Override
	public void initialMission(long user_id) {
		
		String sql="insert into mission_today(mission_id,user_id,status) values(?,?,'未完成')";
		
		String sql1="select mission_id from mission_all";
		
		try {
			con.getConnection();
			
			List<Map<String, Object>> missionIdList=con.findResult(sql1, null);
			
			for(Map<String, Object> missionIdMap : missionIdList) {
				
				List<Object> parm=new ArrayList<Object>();
				parm.add(Integer.parseInt(missionIdMap.get("mission_id").toString()));
				parm.add(user_id);
				
				con.updateByPreparedStatement(sql, parm);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void finishMission(long user_id, long mission_id) {
		// TODO Auto-generated method stub
		String sql="select status from mission_today where user_id=? and mission_id=?";
		
		String sql1="update mission_today set status='可领取' where user_id=? and mission_id=?";
		
		List<Object> parm=new ArrayList<Object>();
		parm.add(user_id);
		parm.add(mission_id);
		
		try {
			con.getConnection();
			
			String status="";
			status=con.findResult(sql, parm).get(0).get("status").toString();
			
			if(status.equals("未完成")) {
				con.updateByPreparedStatement(sql1, parm);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
