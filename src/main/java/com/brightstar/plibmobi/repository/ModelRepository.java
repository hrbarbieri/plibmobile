package com.brightstar.plibmobi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {

	List<Model> findModelByModelName(String modelName);
	
	List<Model> findModelByCarrierCarrierIdAndActive(Integer carrierId, String active);
	
}
