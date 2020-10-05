package com.IAS.Calculator.Services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IAS.Calculator.Entities.TimeTrackerEntity;
import com.IAS.Calculator.Repositories.TimeTrackerRepository;

@Service
public class TimeTrackerServiceImp implements TimeTrackerService{
	
	@Autowired
	TimeTrackerRepository timeTrackerRepository;

	@Override
	public HashMap<String, Object> saveNewService(TimeTrackerEntity timeTrackerEntity) {
		try {
			if(timeTrackerEntity.getId_tecnico().length() != 0 && timeTrackerEntity.getFecha_inicio().length() != 0 && timeTrackerEntity.getFecha_fin().length() != 0) {
				String fechaInicio = timeTrackerEntity.getFecha_inicio();
				String fechaFin = timeTrackerEntity.getFecha_fin();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		        LocalDateTime fechaInicioDateTime = LocalDateTime.parse(fechaInicio, formatter);
		        LocalDateTime fechaFinDateTime = LocalDateTime.parse(fechaFin, formatter);
		        long diff = fechaFinDateTime.getHour() - fechaInicioDateTime.getHour();
		        
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		        Date initialDate = format.parse(fechaInicio);
		        
		        if(diff > 0) {
		        	timeTrackerEntity.setHoras_registro((int) diff);
		        	
		        	Calendar calendar = new GregorianCalendar();
					calendar.setTime(initialDate);
					timeTrackerEntity.setNum_semana(calendar.get(Calendar.WEEK_OF_YEAR));
					
					timeTrackerRepository.save(timeTrackerEntity);
					HashMap<String, Object> responseHasMap = new HashMap<String, Object>();
					responseHasMap.put("response", "Registro guardado con exito");
					return responseHasMap;
		        	
		        }else {
		        	HashMap<String, Object> responseHasMap = new HashMap<String, Object>();
		        	HashMap<String, Object> responseErrorHasMap = new HashMap<String, Object>();
					responseErrorHasMap.put("error", "La fecha fin no puede ser inferior a la fecha de inicio");
					responseHasMap.put("response", responseErrorHasMap);
					return responseHasMap;
		        }
				
			} else {
				HashMap<String, Object> responseHasMap = new HashMap<String, Object>();
	        	HashMap<String, Object> responseErrorHasMap = new HashMap<String, Object>();
				responseErrorHasMap.put("error", "Todos los campos son obligatorios");
				responseHasMap.put("response", responseErrorHasMap);
				return responseHasMap;
			}
			
		} catch (Exception e) {
			HashMap<String, Object> responseHasMap = new HashMap<String, Object>();
        	HashMap<String, Object> responseErrorHasMap = new HashMap<String, Object>();
			responseErrorHasMap.put("error", "Algo salió mal");
			responseHasMap.put("response", responseErrorHasMap);
			return responseHasMap;
		}
	}

}
