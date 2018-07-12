package com.test.model;

import java.io.Serializable;

public class Mission implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long mission_id;
	String mission_name;
	String mission_description;
	float reward;
	String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Mission() {
		this.status="Î´Íê³É";
	}
	
	public long getMission_id() {
		return mission_id;
	}
	public void setMission_id(long mission_id) {
		this.mission_id = mission_id;
	}
	public String getMission_name() {
		return mission_name;
	}
	public void setMission_name(String mission_name) {
		this.mission_name = mission_name;
	}
	public String getMission_description() {
		return mission_description;
	}
	public void setMission_description(String mission_description) {
		this.mission_description = mission_description;
	}
	public float getReward() {
		return reward;
	}
	public void setReward(float reward) {
		this.reward = reward;
	}
}
