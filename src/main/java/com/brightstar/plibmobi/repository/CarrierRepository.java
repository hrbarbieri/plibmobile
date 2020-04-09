package com.brightstar.plibmobi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.Carrier;

public interface CarrierRepository extends JpaRepository<Carrier, Integer>{

	List<Carrier> findCarrierByCountryIdAndActive(Integer countryId, String active);
}
