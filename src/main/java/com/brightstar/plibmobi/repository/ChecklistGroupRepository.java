package com.brightstar.plibmobi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brightstar.plibmobi.model.ChecklistGroup;
import com.brightstar.plibmobi.util.CheckListGroupType;

public interface ChecklistGroupRepository extends JpaRepository<ChecklistGroup, Integer> {
	
	@Query("SELECT gp "
			+ "FROM ChecklistGroup gp "
			+ "WHERE 1=1 "
			+ "AND gp.type IN (?1) "
			+ "AND gp.country.countryId = ?2 ")
	public List<ChecklistGroup> findChecklistGroupByCountryId(List<CheckListGroupType> types, Integer countryId);

}


