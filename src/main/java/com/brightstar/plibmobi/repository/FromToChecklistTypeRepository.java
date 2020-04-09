package com.brightstar.plibmobi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.FromToChecklistType;

public interface FromToChecklistTypeRepository extends JpaRepository<FromToChecklistType, String> {
	
	public List<FromToChecklistType> findByChecklistTypeIdFromIdIn(List<Integer> ids);
	

}
