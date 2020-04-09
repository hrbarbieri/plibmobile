package com.brightstar.plibmobi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.ModelPartNumber;

public interface ModelPartNumberRepository extends JpaRepository<ModelPartNumber, String>{
	
	public List<ModelPartNumber> findByModelId(Integer modelId);
	
	public ModelPartNumber findByModelIdAndGrade(Integer modelId, String grade);
	
	public ModelPartNumber findByModelIdAndGradeAndCountryId(Integer modelId, String grade, Integer countryId);

}
