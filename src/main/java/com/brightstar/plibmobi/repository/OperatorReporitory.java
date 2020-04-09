package com.brightstar.plibmobi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.Operator;

public interface OperatorReporitory extends JpaRepository<Operator, Integer> {
	
	public Optional<Operator> findByCountryCountryIdAndUnlocked(Integer countryId, Integer unlocked);

}
