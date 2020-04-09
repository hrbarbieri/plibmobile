package com.brightstar.plibmobi.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brightstar.plibmobi.model.ServiceOrder;
import com.brightstar.plibmobi.util.ProcessStep;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Integer>{
	

	Optional<List<ServiceOrder>> findBySerialNumber(String serialNumber);
	
	Optional<ServiceOrder> findOdsBySerialNumberAndProcessStepIn(String serialNumber, Collection<ProcessStep> processSteps);
	
	Optional<ServiceOrder> findOdsBySerialNumberAndProcessStepNotIn(String serialNumber, Collection<ProcessStep> processSteps);
	
	Optional<ServiceOrder> findOdsBySerialNumberAndProcessStep(String serialNumber, ProcessStep processStep);
	
	@Transactional
	@Query(value="UPDATE RMATOWN_SERVICE_ORDER SET PROCESS_STEP = :processStep, UPDATE_USER_ID = :userName, UPDATE_TIMESTAMP = :updateTimestamp, "
			+ "SERVICE_NOTES = :note WHERE SERVICE_ORDER_ID IN (:serviceOrderId)", nativeQuery = true)
	void updateServiceOrder(
			@Param("processStep") 		String 	processStep, 
			@Param("userName")			String 	userName, 
			@Param("updateTimestamp")	Date 	updateTimestamp, 
			@Param("note")				String 	note, 
			@Param("serviceOrderId")	Integer serviceOrderId);
	
	
}
