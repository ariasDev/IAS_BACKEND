package com.IAS.Calculator.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.IAS.Calculator.Entities.TimeTrackerEntity;

@Repository
public interface TimeTrackerRepositoryJPA extends JpaRepository<TimeTrackerEntity, String> {
	
	@Query(
			value = "SELECT * FROM reports u WHERE u.technician_identification = ?1 and u.week =?2", 
			nativeQuery = true)
	List<TimeTrackerEntity> findByIdentification(String id, int week);

}
