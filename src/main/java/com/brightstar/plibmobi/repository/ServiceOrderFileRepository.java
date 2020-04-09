package com.brightstar.plibmobi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.ServiceOrderFile;

public interface ServiceOrderFileRepository extends JpaRepository<ServiceOrderFile, Integer> {
	
	//public List<ServiceOrderFile> findTop50OrderById();

}
