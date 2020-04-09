package com.brightstar.plibmobi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightstar.plibmobi.model.Carrier;
import com.brightstar.plibmobi.model.Model;
import com.brightstar.plibmobi.service.UtilsService;

@Controller
@RequestMapping("/model")
public class ModelController {
	
	@Autowired
	private UtilsService utilService;

	@GetMapping("/listarCarriers")
	public @ResponseBody List<Carrier> listCarrier(@RequestParam("countryId") Integer countryId) {
		List<Carrier> list = utilService.findListCarrierByCountry(countryId, "1");
		return list;
	}
	
	@GetMapping("/listarModels")
	public @ResponseBody List<Model> listModel(@RequestParam("carrierId") Integer carrierId) {
		List<Model> list = utilService.findModelByCarrierCarrierId(carrierId, "1");
		return list;
	}

}
