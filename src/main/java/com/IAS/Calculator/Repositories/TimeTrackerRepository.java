package com.IAS.Calculator.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.IAS.Calculator.Entities.TimeTrackerEntity;

@Repository
public interface TimeTrackerRepository extends CrudRepository<TimeTrackerEntity, String>{

}
