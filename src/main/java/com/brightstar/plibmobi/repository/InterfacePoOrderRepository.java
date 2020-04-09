package com.brightstar.plibmobi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.InterfacePoOrderDetails;

public interface InterfacePoOrderRepository extends JpaRepository<InterfacePoOrderDetails, String> {

	public List<InterfacePoOrderDetails> findByServiceOrderId(Integer serviceOrderId);
	
	public List<InterfacePoOrderDetails> findByServiceOrderIdAndBbLineRefNbrBbRefNumberPoNumberIsNotNull(Integer serviceOrderId);
}
