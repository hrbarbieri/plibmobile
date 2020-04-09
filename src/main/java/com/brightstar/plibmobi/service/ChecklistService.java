package com.brightstar.plibmobi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightstar.plibmobi.model.Checklist;
import com.brightstar.plibmobi.model.ChecklistGroup;
import com.brightstar.plibmobi.model.ChecklistType;
import com.brightstar.plibmobi.repository.ChecklistGroupRepository;
import com.brightstar.plibmobi.util.CheckListGroupType;

@Service
public class ChecklistService {

	@Autowired
	private ChecklistGroupRepository checklistGroupRep;
	
	
	public List<Checklist> findQuestionsByCountryId(Integer countryId) {
		
		List<ChecklistGroup> groups = checklistGroupRep.findChecklistGroupByCountryId(
				CheckListGroupType.getTypeForQuestions(), countryId);
		
		
		/* 
		 * Separa apenas as respostas que contenham CSS_IDENTIFIER = 'redLabel' 
		 */		
		List<Checklist> questions = new ArrayList<Checklist>();
		
		for(ChecklistGroup group : groups) {
			
			for(Checklist check : group.getChecks()) {
				
				boolean redLabel = false;
				List<ChecklistType> checklistType = null;
				
				// Comportamento diferente para TELA, CARCAÇA e OUTROS
				if(check.getName().equalsIgnoreCase("TELA") 
						|| check.getName().equalsIgnoreCase("CARCAÇA") 
						|| check.getName().equalsIgnoreCase("OUTROS")) {
					
					checklistType = new ArrayList<ChecklistType>();
					
					for(ChecklistType type : check.getCheckTypes()) {
						if(type.getCssIdentifier().contains("redLabel")) {
							checklistType.add(type);
							redLabel = true;
						}
					}
					
					if(redLabel) {
						Collections.sort(checklistType);
						
						check.setCheckTypes(checklistType);
						questions.add(check);
					}
					
					
				} else {
				
					for(ChecklistType type : check.getCheckTypes()) {
						
						if(type.getCssIdentifier().contains("redLabel")) {
							redLabel = true;
							break;
						}
					}
					
					if(redLabel) {
						questions.add(check);
					}
				}
			}
			
		}
		
		Collections.sort(questions);
		
		return questions;
	}
	
}
