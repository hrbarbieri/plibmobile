package com.brightstar.plibmobi.convert;

import com.brightstar.plibmobi.model.Provider;
import com.brightstar.plibmobi.model.dto.ProviderDto;

public class ProviderDtoConverter implements Converter<Provider, ProviderDto>{

	@Override
	public ProviderDto convert(Provider in) {
		
		ProviderDto dto = new ProviderDto();
		
		dto.setProviderId(in.getProviderId());
		dto.setProviderName(in.getProviderName());
		
		return dto;
	}
	
	

}
