package com.IAS.Calculator.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		System.out.println(timeTrackerEntity.getFecha_inicio());
		HashMap<String, Object> serviceResponse =  timeTrackerService.saveNewService(timeTrackerEntity);
		return serviceResponse;
	}
	
	@GetMapping(path = "/consultService")
	public HashMap<String, Object> consultService(@RequestParam String id, @RequestParam int week)
	{
		try {
			HashMap<String, Object> serviceResponse =  timeTrackerService.consultService(id, week);
			return serviceResponse;
		} catch (Exception e) {
			HashMap<String, Object> responseHasMap = new HashMap<String, Object>();
        	HashMap<String, Object> responseErrorHasMap = new HashMap<String, Object>();
			responseErrorHasMap.put("error", e.getLocalizedMessage());
			responseHasMap.put("response", responseErrorHasMap);
			return responseHasMap;
		}
	}

}
