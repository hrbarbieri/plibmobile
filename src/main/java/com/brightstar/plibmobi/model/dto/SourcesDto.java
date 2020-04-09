package com.brightstar.plibmobi.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.brightstar.plibmobi.util.SourceType;

public class SourcesDto implements Serializable {
	
	
	/**  */
	private static final long serialVersionUID = 1L;
	
	private Integer sourceId;
    private SourceType sourceType;
    private String sourceTypeStr;
    private String deposit;
    private String location;
    private Boolean active;
    
    private String updateUserId;
    private Date updateTimestamp;
    
    private String modelName;
    private String partNumber;
    
    private List<String> msgError = new ArrayList<String>();
    
    public String getDimension() {
        return getDeposit() + " / " + getLocation();
    }
    
    public Integer getSourceId() {
        return sourceId;
    }
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }
    public SourceType getSourceType() {
        return sourceType;
    }
    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }
    public String getDeposit() {
        return deposit;
    }
    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public String getUpdateUserId() {
        return updateUserId;
    }
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }
    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
    public String getSourceTypeStr() {
        return sourceTypeStr;
    }
    public void setSourceTypeStr(String sourceTypeStr) {
        this.sourceTypeStr = sourceTypeStr;
    }
    public String getPartNumber() {
        return partNumber;
    }
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }
    public String getModelName() {
        return modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public void addMsgError(String message) {
        getMsgError().add(message);
    }
    public List<String> getMsgError() {
        return msgError;
    }
    public void setMsgError(List<String> msgError) {
        this.msgError = msgError;
    }

	@Override
	public String toString() {
		return "SourcesDto [sourceId=" + sourceId + ", sourceType=" + sourceType + ", sourceTypeStr=" + sourceTypeStr
				+ ", deposit=" + deposit + ", location=" + location + ", active=" + active + ", updateUserId="
				+ updateUserId + ", updateTimestamp=" + updateTimestamp + ", modelName=" + modelName + ", partNumber="
				+ partNumber + ", msgError=" + msgError + "]";
	}
    
    

}
