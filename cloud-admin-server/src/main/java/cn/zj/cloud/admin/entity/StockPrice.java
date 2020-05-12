package cn.zj.cloud.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;

@Entity
@Table(name="Stockprice")
@IdClass(StockPricePrimaryKey.class)
@EqualsAndHashCode(callSuper = true)
public class StockPrice {
	@Id
	@Column(name="companycode", nullable=false)
	private String companycode;
	@Id
	@Column(name="stockcode", nullable=false)
	private String stockcode;
	@Id
	@Column(name="stockexchange", nullable=false)
	private String stockexchange;	
	@Id
	@Column(name="currentprice", nullable=false)
	private double currentprice;
	@Id
	@Column(name="currency", nullable=false)
	private String currency;
	@Id
	@Column(name="date", nullable=false)
	private String date;
	@Id
	@Column(name="time", nullable=false)
	private String time;

	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public String getStockcode() {
		return stockcode;
	}
	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}
	public String getStockexchange() {
		return stockexchange;
	}
	public void setStockexchange(String stockexchange) {
		this.stockexchange = stockexchange;
	}
	public double getCurrentprice() {
		return currentprice;
	}
	public void setCurrentprice(double currentprice) {
		this.currentprice = currentprice;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
