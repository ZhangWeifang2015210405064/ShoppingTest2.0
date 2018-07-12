package com.test.dao;

import java.util.List;

import com.test.model.Mission;

public interface MissionDao {

	public List<Mission> getMission(long user_id);
	
	public boolean updateMission(long mission_id,long user_id,float reward);
	
	public int checkMission(long mission_id,long user_id);
	
	public void initialMission(long user_id);
	
	public void finishMission(long user_id,long mission_id);
	
}
