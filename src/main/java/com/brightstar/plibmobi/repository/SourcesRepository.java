package com.brightstar.plibmobi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.Sources;

public interface SourcesRepository extends JpaRepository<Sources, Integer>{
	
	public Sources findByLocationAndDeposit(String location, String deposit);

}
