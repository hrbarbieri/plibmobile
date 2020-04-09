package com.brightstar.plibmobi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
	
	Country findCountryByCountryName(String countryName);

}
