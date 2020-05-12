package cn.zj.cloud.user.entity;

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
	private String StockExchange;	
	@Column(name="pricepershare")
	private BigDecimal pricePerShare;
	@Column(name="totalnumberofshares")
	private int totalNumberOfShares;
	@Column(name="opendatatime")
	private Timestamp openDataTime;
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
		return StockExchange;
	}
	public void setStockExchange(String stockExchange) {
		StockExchange = stockExchange;
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
	public Timestamp getOpenDataTime() {
		return openDataTime;
	}
	public void setOpenDataTime(Timestamp openDataTime) {
		this.openDataTime = openDataTime;
	}

}
