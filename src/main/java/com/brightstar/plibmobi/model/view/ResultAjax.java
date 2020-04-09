package com.brightstar.plibmobi.model.view;

import java.util.List;

public class ResultAjax {
	
	private String title;
	
	private String message;
	
	private Integer code;
	
	private Object obj;
	
	private List<?> list;
	
	
	public ResultAjax(String title, String message, Integer code, Object obj) {
		this.title = title;
		this.message = message;
		this.code = code;
		this.obj = obj;
	}
	
	public ResultAjax(String message, Integer code, Object obj) {
		this.message = message;
		this.code = code;
		this.obj = obj;
	}
	
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
