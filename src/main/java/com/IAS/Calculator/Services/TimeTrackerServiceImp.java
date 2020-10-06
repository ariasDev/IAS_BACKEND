package com.IAS.Calculator.Services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IAS.Calculator.Entities.TimeTrackerEntity;
import com.IAS.Calculator.Repositories.TimeTrackerRepository;
import com.IAS.Calculator.Repositories.TimeTrackerRepositoryJPA;

@Service
public class TimeTrackerServiceImp implements TimeTrackerService{
	
	@Autowired
	TimeTrackerRepository timeTrackerRepository;
	
	@Autowired
	TimeTrackerRepositoryJPA timeTrackerRepositoryJPA;

	@Override
	public HashMap<String, Object> saveNewService(TimeTrackerEntity timeTrackerEntity) {
		try {
			if(
					timeTrackerEntity.getId_servicio().length() != 0  && timeTrackerEntity.getId_tecnico().length() != 0 
					&& timeTrackerEntity.getFecha_inicio().length() != 0 && timeTrackerEntity.getFecha_fin().length() != 0
			) {
				String fechaInicio = timeTrackerEntity.getFecha_inicio();
				String fechaFin = timeTrackerEntity.getFecha_fin();
		        
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		        Date initialDate = format.parse(fechaInicio);
		        Date finalDate = format.parse(fechaFin);
		        
		        long horasLaboradas = finalDate.getHours() - initialDate.getHours();
		        
		        if(finalDate.after(initialDate)) {
			        	Calendar calendar = new GregorianCalendar();
						calendar.setTime(initialDate);
						System.out.println("dia de la semana: " + calendar.get(Calendar.DAY_OF_WEEK));
						
						if(calendar.get(Calendar.DAY_OF_WEEK) >= 2 && calendar.get(Calendar.DAY_OF_WEEK) <= 7) {
							if(initialDate.getHours()>= 7 && finalDate.getHours() <= 20) {
								timeTrackerEntity.setHoras_normales((int) horasLaboradas);
							}else {
								timeTrackerEntity.setHoras_normales_extra((int) horasLaboradas);
							}
						}else {
							timeTrackerEntity.setHoras_dominicales((int) horasLaboradas);
						}
						
						
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
			responseErrorHasMap.put("error", e.getLocalizedMessage());
			responseHasMap.put("response", responseErrorHasMap);
			return responseHasMap;
		}
	}

	@Override
	public HashMap<String, Object> consultService(String id, int week) {
		try {
			if(id.length() != 0 && week > 0) {
				int horasNormales = 0;
				int horasDominicales = 0;
				int horasNormalesExtra = 0;
				int horasDominicalesExtra = 0;
				
				Calendar c1 = Calendar.getInstance();
				int currentYearInt = c1.get(Calendar.YEAR);
				String currentYear = "%" + currentYearInt + "%";
				List<TimeTrackerEntity> responseRepository = timeTrackerRepositoryJPA.consultService(id, week, currentYear);
				for (int i = 0; i < responseRepository.size(); i++) {
					TimeTrackerEntity servicio = responseRepository.get(i);
					horasNormales = horasNormales + servicio.getHoras_normales();
					horasDominicales = horasDominicales + servicio.getHoras_dominicales();
					horasNormalesExtra = horasNormalesExtra + servicio.getHoras_normales_extra();
					horasDominicalesExtra = horasDominicalesExtra + servicio.getHoras_dominicales_extra();
				}
				HashMap<String, Object> responseHasMap = new HashMap<String, Object>();
				HashMap<String, Object> dataHasMap = new HashMap<String, Object>();
				dataHasMap.put("data", responseRepository);
				dataHasMap.put("horasNormales", horasNormales);
				dataHasMap.put("horasDominicales", horasDominicales);
				dataHasMap.put("horasNormalesExtra", horasNormalesExtra);
				dataHasMap.put("horasDominicalesExtra", horasDominicalesExtra);
				responseHasMap.put("response", dataHasMap);
				return responseHasMap;
			}else {
				HashMap<String, Object> responseHasMap = new HashMap<String, Object>();
	        	HashMap<String, Object> responseErrorHasMap = new HashMap<String, Object>();
				responseErrorHasMap.put("error", "Todos los campos son obligatorios y la semana debe ser mayor que 0");
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
