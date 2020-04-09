package com.brightstar.plibmobi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.brightstar.plibmobi.convert.CollectionConverter;
import com.brightstar.plibmobi.convert.ProviderDtoConverter;
import com.brightstar.plibmobi.model.Carrier;
import com.brightstar.plibmobi.model.Country;
import com.brightstar.plibmobi.model.InterfaceConfig;
import com.brightstar.plibmobi.model.Model;
import com.brightstar.plibmobi.model.ModelPartNumber;
import com.brightstar.plibmobi.model.Operator;
import com.brightstar.plibmobi.model.Provider;
import com.brightstar.plibmobi.model.ReceivedTransporter;
import com.brightstar.plibmobi.model.TransactionUnit;
import com.brightstar.plibmobi.model.dto.ProviderDto;
import com.brightstar.plibmobi.repository.CarrierRepository;
import com.brightstar.plibmobi.repository.CountryRepository;
import com.brightstar.plibmobi.repository.InterfaceConfigRepository;
import com.brightstar.plibmobi.repository.ModelPartNumberRepository;
import com.brightstar.plibmobi.repository.ModelRepository;
import com.brightstar.plibmobi.repository.OperatorReporitory;
import com.brightstar.plibmobi.repository.ProviderRepository;
import com.brightstar.plibmobi.repository.ReceivedTransporterRepository;
import com.brightstar.plibmobi.repository.TransactionUnitRepository;
import com.brightstar.plibmobi.util.ProcessStep;
import com.brightstar.plibmobi.util.TransactionMasterFlow;

@Service
@CacheConfig(cacheNames={"utils"})
public class UtilsService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private CarrierRepository carrierRepository;
	
	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private ModelPartNumberRepository modelPartNumberRepository;
	
	@Autowired
	private InterfaceConfigRepository interfaceConfigRepository;
	
	@Autowired
	private ReceivedTransporterRepository receivedTranspRepository;
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private InterfaceConfigRepository interfaceConfigRep;
	
	@Autowired
	private TransactionUnitRepository transactionUnitRep;
	
	@Autowired
	private OperatorReporitory operatorReporitory;
	
	
	public ModelPartNumber findModelPartNumber(Integer countryId, Integer modelId, String grade) {
		return modelPartNumberRepository.findByModelIdAndGradeAndCountryId(modelId, grade, countryId);
	}
	
	public Optional<Operator> findOperatorCountryIdAndUnlocked(Integer countryId, Integer unlocked) {
		return operatorReporitory.findByCountryCountryIdAndUnlocked(countryId, unlocked);
	}
	
	public List<InterfaceConfig> findInterfaceConfigByGroup(String group) {
		return interfaceConfigRep.findByGroup(group);
	}
	
	public InterfaceConfig findInterfaceConfigByGroupAndName(String group, String name) {
		
		Optional<InterfaceConfig> optInterfaceConfig = interfaceConfigRep.findByGroupAndName(group, name); 
		
		if(!optInterfaceConfig.isPresent()) {
			return null;
		}
		
		return optInterfaceConfig.get();
	}
	
	public List<Model> findModelByCountryId(Integer countryId) {
		return null;//modelRepository.findByCarrierCountryId(countryId);
	}
	
	public Country findCountryByName(String countryName) {
		return countryRepository.findCountryByCountryName(countryName);
	}
	
	public Optional<Country> findCountryById(Integer countryId) {
		return countryRepository.findById(countryId);
	}
	
	@Cacheable
	public List<Carrier> findListCarrierByCountry(Integer countryId, String active) {
		return carrierRepository.findCarrierByCountryIdAndActive(countryId, active);
	}
	
	public Optional<Model> findModelById(Integer id) {
		return modelRepository.findById(id);
	}	
	
	public List<Model> findModelByName(String modelName) {
		return modelRepository.findModelByModelName(modelName);
	}
	
	@Transactional
	public List<Model> findModelByCarrierCarrierId(Integer carrierId, String active) {
		return modelRepository.findModelByCarrierCarrierIdAndActive(carrierId, active);
	}
	
	
	public Optional<InterfaceConfig> findInterfaceConfig(InterfaceConfig param) {
		return interfaceConfigRepository.findByGroupAndName(param.getGroup(), param.getName());
	}
	
	public Optional<ReceivedTransporter> findReceivedTransport(Integer id) {
		return receivedTranspRepository.findById(id);
	}
	
	@Cacheable
	public List<ProviderDto> findProviderByType(String providerType) {
		
		Optional<List<Provider>> listOpt = providerRepository.findByProviderType(providerType);
		
		if(listOpt.isPresent()) {
			return new CollectionConverter<Provider, ProviderDto>(
					new ProviderDtoConverter()).convertToList(listOpt.get());
		}
		return null;
		
	}
	
	public List<TransactionUnit> findPendentesTransaferencias() {
		
		Pageable pageable = PageRequest.of(0, 50, Sort.by(Sort.Direction.DESC, "receiveTimestamp"));
		
		return transactionUnitRep.findByServiceOrderProcessStepAndMasterBrightstarSubsidiaryAndStatus(
				ProcessStep.WAITING_TRANSFER_QUARANTINE_AX, TransactionMasterFlow.ROMANEIO_CAE_TO_BRIGHTSTAR, "P", pageable);
	}
	

}
