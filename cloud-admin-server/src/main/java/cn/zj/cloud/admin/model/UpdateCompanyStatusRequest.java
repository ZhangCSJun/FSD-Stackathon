package cn.zj.cloud.admin.model;

import cn.zj.cloud.constant.Constant;

public class UpdateCompanyStatusRequest {
	private String id = Constant.EMPTY_STRING;
	private String status = Constant.EMPTY_STRING;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
