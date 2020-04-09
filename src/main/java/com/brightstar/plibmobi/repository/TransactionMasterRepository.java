package com.brightstar.plibmobi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.ServiceOrder;
import com.brightstar.plibmobi.model.TransactionMaster;
import com.brightstar.plibmobi.util.TransactionMasterFlow;

public interface TransactionMasterRepository extends JpaRepository<TransactionMaster, Integer>{
	
	
	Integer countByBrightstarSubsidiaryAndUnitsServiceOrder(TransactionMasterFlow brightstarSubsidiary, ServiceOrder serviceOrder);

}
