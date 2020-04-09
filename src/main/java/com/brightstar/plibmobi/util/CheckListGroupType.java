package com.brightstar.plibmobi.util;

import java.util.ArrayList;
import java.util.List;

public enum CheckListGroupType {
    
	SALES_GRADING_FUNCTIONAL(""),
	SALES_GRADING_DISPLAY(""),
	SALES_GRADING_BODY(""),
	SALES_GRADING_OTHERS(""),
	GRADING_COMPRA(""),
	GROUP_RELATIONAL_QUESTIONS(""),
	GHOST_PROCESS(""),
	SALES_GRADING_MEMORY_COLOR("");
    
    private String label;
    
    CheckListGroupType(String label) {
        this.label = label;
    } 

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public static List<CheckListGroupType> getTypeForQuestions() {
    	List<CheckListGroupType> types = new ArrayList<CheckListGroupType>();
    	types.add(SALES_GRADING_FUNCTIONAL);
    	types.add(SALES_GRADING_DISPLAY);
    	types.add(SALES_GRADING_BODY);
    	types.add(SALES_GRADING_OTHERS);
    	return types;
    }
    
    public static CheckListGroupType getValueOf(String arg) {
        try {
            return CheckListGroupType.valueOf(arg);
        } catch (Exception e) {
            return null;
        }
    }
    
}