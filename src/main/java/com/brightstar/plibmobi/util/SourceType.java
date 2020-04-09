package com.brightstar.plibmobi.util;

public enum SourceType {
    
    TRADE_IN("label.sourceType.tradeIn"),
    QA_LINE("label.sourceType.qaLine"),
    QA_FULFILLMENT("label.sourceType.qaFulfillment"),
    REPAIR("label.sourceType.repair"),
    AFTER_SALES("label.sourceType.afterSales");
    
    private String label;
    
    private SourceType(String label) {
        this.label = label;
    }
    
    public static SourceType sourceTypefindSourceType(String source) {
        for(SourceType sourceType : SourceType.values()) {
            if(sourceType.name().equalsIgnoreCase(source)) {
                return sourceType;
            }
        }
        return null;
    }

    public static SourceType[] getSourceType2ndPass() {
        return new SourceType[]{QA_LINE, QA_FULFILLMENT, REPAIR, AFTER_SALES};
    }
    
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

}
