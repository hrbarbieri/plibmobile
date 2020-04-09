package com.brightstar.plibmobi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.InterfaceConfig;

public interface InterfaceConfigRepository extends JpaRepository<InterfaceConfig, String>{
	
	public Optional<InterfaceConfig> findByGroupAndName(String group, String name);
	
	public List<InterfaceConfig> findByGroup(String group);

}
