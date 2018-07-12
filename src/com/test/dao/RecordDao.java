package com.test.dao;

import java.util.List;

import com.test.model.Record;

public interface RecordDao {
	
	public List<Record> getAllRecord(long userId);
	
	public boolean updateRecord(Record record);
	
	public boolean deleteRecord(long id);
	
	public boolean backGoods(long id);
	
}
