package com.brightstar.plibmobi.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.TransactionUnit;
import com.brightstar.plibmobi.util.ProcessStep;
import com.brightstar.plibmobi.util.TransactionMasterFlow;

public interface TransactionUnitRepository extends JpaRepository<TransactionUnit, Integer> {
	
	public List<TransactionUnit> findByServiceOrderProcessStepAndMasterBrightstarSubsidiaryAndStatus(ProcessStep processStep, TransactionMasterFlow brightstarSubsidiary, String status, Pageable pageable);

}
