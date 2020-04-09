package com.brightstar.plibmobi.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum POStatus {

    PENDING("label.purchaseOrder.status.pending", "P"),
    CREATED("label.purchaseOrder.status.created", "C"),
    ERROR("label.purchaseOrder.status.error", "E"),
    REPROCESSED("label.purchaseOrder.status.reprocessed", "R");

    private String label;
    private String statusCode;

    private static Map<String, POStatus> stringToStatusMap;

    POStatus(String label, String statusCode) {
        this.label = label;
        this.statusCode = statusCode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public static List<POStatus> getPOStatuses() {
        return Arrays.asList(new POStatus[] {PENDING, CREATED, ERROR, REPROCESSED});
    }

    public static POStatus fromCode(String statusCode) {

        if(stringToStatusMap == null) {
            stringToStatusMap = new HashMap<String, POStatus>();

            for(POStatus status : POStatus.values()) {
                stringToStatusMap.put(status.getStatusCode(), status);
                stringToStatusMap.put(status.toString(), status);
            }
        }

        return stringToStatusMap.get(statusCode);
    }

}
