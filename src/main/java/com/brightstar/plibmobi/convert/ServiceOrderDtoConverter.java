package com.brightstar.plibmobi.convert;

import com.brightstar.plibmobi.model.ServiceOrder;
import com.brightstar.plibmobi.model.ServiceOrderBbeMpx;
import com.brightstar.plibmobi.model.dto.ServiceOrderDto;

public class ServiceOrderDtoConverter implements Converter<ServiceOrder, ServiceOrderDto> {

	@Override
	public ServiceOrderDto convert(ServiceOrder in) {
		
		ServiceOrderDto dto = new ServiceOrderDto();
		
		dto.setId(in.getId()); //ServiceOrderId
		dto.setSerialNumber(in.getSerialNumber());
		dto.setAccessKeyNF(in.getAccessKeyNf());;
		dto.setCollectedBroken(0);
		dto.setSumPrice("");
		dto.setProcessStep(in.getProcessStep().name());
		dto.setCountryId(in.getCountry().getCountryId());
		dto.setCountryName(in.getCountry().getCountryName());
		
		
		if(in.getModel() != null) { 
			dto.setModelName(in.getModel().getModelName());
			dto.setModelId(in.getModel().getModelId());
			
			if(!in.getModel().getXrefs().isEmpty()) {
				dto.setMpxId(Integer.valueOf(in.getModel().getXrefs().get(0).getXref()));
			}
		}
		
		/*if(in.getOrderBuyback() != null && in.getOrderBuyback().getOperator() != null) {
			dto.setOperatorXref(Integer.valueOf(in.getOrderBuyback().getOperator().getExternalReference()));
		}*/
		
		if(in.getOrderBbeMpx() != null) {
			
			ServiceOrderBbeMpx orderBbeMpx = in.getOrderBbeMpx().get(0);
			
			dto.setCustomerNetPrice(orderBbeMpx.getCustomerNetPrice());
			dto.setOutPortfolio(orderBbeMpx.getOutportfolio());
			dto.setGrandingInit(orderBbeMpx.getGrandingInit());
			dto.setHandsetQuoteId(orderBbeMpx.getHandsetQuoteId());
			dto.setVoucher(orderBbeMpx.getVoucher());
		}
		
		return dto;
	}

}
