package com.brightstar.plibmobi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.ServiceOrderBuyback;

public interface ServiceOrderBuybackRepository extends JpaRepository<ServiceOrderBuyback, Long> {
	
	Optional<ServiceOrderBuyback> findOdsBuybackByServiceOrderId(Integer id);
	
}
