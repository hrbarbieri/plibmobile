package com.brightstar.plibmobi.convert;

import com.brightstar.plibmobi.model.Sources;
import com.brightstar.plibmobi.model.dto.SourcesDto;

public class SourcesDtoConverter implements Converter<Sources, SourcesDto> {

	@Override
	public SourcesDto convert(Sources in) {
		
		SourcesDto dto = new SourcesDto();
		dto.setLocation(in.getLocation());
		dto.setDeposit(in.getDeposit());
		dto.setSourceType(in.getSourceType());
		dto.setSourceTypeStr(in.getSourceType().name());
		
		return dto;
	}

}
