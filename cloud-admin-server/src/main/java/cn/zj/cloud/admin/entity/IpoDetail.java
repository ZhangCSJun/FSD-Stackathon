package cn.zj.cloud.admin.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ipodetails")
public class IpoDetail {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="companyname")
	private String companyName;
	@Column(name="stockexchange")
	private String stockExchange;	
	@Column(name="pricepershare")
	private BigDecimal pricePerShare;
	@Column(name="totalnumberofshares")
	private int totalNumberOfShares;
	@Column(name="opendatatime")
	private String openDataTime;
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
	public BigDecimal getPricePerShare() {
		return pricePerShare;
	}
	public void setPricePerShare(BigDecimal pricePerShare) {
		this.pricePerShare = pricePerShare;
	}
	public int getTotalNumberOfShares() {
		return totalNumberOfShares;
	}
	public void setTotalNumberOfShares(int totalNumberOfShares) {
		this.totalNumberOfShares = totalNumberOfShares;
	}
	public String getOpenDataTime() {
		return openDataTime;
	}
	public void setOpenDataTime(String openDataTime) {
		this.openDataTime = openDataTime;
	}

}
