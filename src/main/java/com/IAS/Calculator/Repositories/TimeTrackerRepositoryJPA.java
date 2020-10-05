package com.IAS.Calculator.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.IAS.Calculator.Entities.TimeTrackerEntity;

@Repository
public interface TimeTrackerRepositoryJPA extends JpaRepository<TimeTrackerEntity, String> {
	
	@Query(
			value = "SELECT * FROM time_tracker t WHERE t.id_tecnico = ?1 and t.num_semana =?2 and t.fecha_inicio like ?3", 
			nativeQuery = true)
	List<TimeTrackerEntity> consultService(String id, int week, String currentYear);

}
