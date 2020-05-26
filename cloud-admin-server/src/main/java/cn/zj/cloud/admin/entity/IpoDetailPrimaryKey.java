package cn.zj.cloud.admin.entity;

import java.io.Serializable;

public class IpoDetailPrimaryKey implements Serializable{

	private static final long serialVersionUID = 102010048867150139L;
	private String id;
	private String companyName;
	private String stockExchange;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	
}
