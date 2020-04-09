package com.brightstar.plibmobi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.InterfaceXref;

public interface InterfaceXrefRepository extends JpaRepository<InterfaceXref, String>{
	
	public InterfaceXref findByEntityId(Integer entityId);
	
	public InterfaceXref findByCountryIdAndXrefAndEntity(Integer countryId, String xref, String Entity);
	
	
}
