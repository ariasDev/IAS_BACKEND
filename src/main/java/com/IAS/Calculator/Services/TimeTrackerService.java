package com.IAS.Calculator.Services;

import java.util.HashMap;

import com.IAS.Calculator.Entities.TimeTrackerEntity;

public interface TimeTrackerService {
	
	public HashMap<String, Object> saveNewService(TimeTrackerEntity timeTrackerEntity);
	public HashMap<String, Object> consultService(String id, int week);

}
