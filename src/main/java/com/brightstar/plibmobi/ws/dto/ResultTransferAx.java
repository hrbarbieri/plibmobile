package com.brightstar.plibmobi.ws.dto;

public class ResultTransferAx {
    
    private String messageError;
    private String message;
    private String field;
    private String value;
    
    public ResultTransferAx() {
    }
    
    public ResultTransferAx(String message) {
        this.message = message;
    }

    public String getMessageError() {
        return messageError;
    }
    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
