package cn.zj.cloud.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Sector")
public class Sector {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="sectorname")
	private String sectorname;
	@Column(name="breif")
	private String breif;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSectorname() {
		return sectorname;
	}
	public void setSectorname(String sectorname) {
		this.sectorname = sectorname;
	}
	public String getBreif() {
		return breif;
	}
	public void setBreif(String breif) {
		this.breif = breif;
	}
	
	
}
