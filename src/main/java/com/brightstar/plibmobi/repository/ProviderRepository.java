package com.brightstar.plibmobi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brightstar.plibmobi.model.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {

	Optional<List<Provider>> findByProviderType(String providerType);
	
}
