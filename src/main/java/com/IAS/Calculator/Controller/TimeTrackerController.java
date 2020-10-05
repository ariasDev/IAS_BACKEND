package com.IAS.Calculator.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IAS.Calculator.Entities.TimeTrackerEntity;
import com.IAS.Calculator.Services.TimeTrackerService;

@RestController
@CrossOrigin(origins="*", methods= {RequestMethod.GET, RequestMethod.POST})
@RequestMapping(value = "/timetracker")
public class TimeTrackerController {
	
	@Autowired
	TimeTrackerService timeTrackerService;
	
	@PostMapping(path = "/save",produces = "application/json")
	public HashMap<String, Object> saveNewService (@RequestBody TimeTrackerEntity timeTrackerEntity) {
		HashMap<String, Object> serviceResponse =  timeTrackerService.saveNewService(timeTrackerEntity);
		return serviceResponse;
	}

}
