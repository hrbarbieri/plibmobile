package com.brightstar.plibmobi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.ServiceOrderBbeMpx;

public interface ServiceOrderBbeMpxRepository extends JpaRepository<ServiceOrderBbeMpx, Long> {
	
	Optional<List<ServiceOrderBbeMpx>> findOdsBbeMpxByServiceOrderId(Integer id);
	
	Optional<ServiceOrderBbeMpx> findOdsBbeMpxByServiceOrderIdAndItgType(Integer id, Integer itgType);
	
}
