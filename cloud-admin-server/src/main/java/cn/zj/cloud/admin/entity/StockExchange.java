package cn.zj.cloud.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Stockexchange")
public class StockExchange {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="abbrname")
	private String abbrname;
	@Column(name="fullname")
	private String fullname;	
	@Column(name="brief")
	private String brief;
	@Column(name="contactaddress")
	private String contactaddress;
	@Column(name="remark")
	private String remark;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAbbrname() {
		return abbrname;
	}
	public void setAbbrname(String abbrname) {
		this.abbrname = abbrname;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getContactaddress() {
		return contactaddress;
	}
	public void setContactaddress(String contactaddress) {
		this.contactaddress = contactaddress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
