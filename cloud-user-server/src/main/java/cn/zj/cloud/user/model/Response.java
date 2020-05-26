package cn.zj.cloud.user.model;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.zj.cloud.constant.Constant;

public class Response {
	private String status = Constant.EMPTY_STRING;
	private String code = Constant.EMPTY_STRING;
	private String message = Constant.EMPTY_STRING;
	private Map<String, Object> business = new LinkedHashMap<String, Object>();

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getBusiness() {
		return business;
	}
	public void setBusiness(Map<String, Object> business) {
		this.business = business;
	}
}
