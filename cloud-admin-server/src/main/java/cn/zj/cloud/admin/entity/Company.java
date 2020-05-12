package cn.zj.cloud.admin.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Company")
public class Company {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="name")
	private String name;
	@Column(name="code")
	private String code;	
	@Column(name="turnover")
	private BigDecimal turnover;
	@Column(name="ceo")
	private String ceo;
	@Column(name="boardofdirectors")
	private String boardofdirectors;
	@Column(name="sectorid")
	private String sectorid;
	@Column(name="briefwriteup")
	private String briefwriteup;
	@Column(name="status")
	private String status;	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getTurnover() {
		return turnover;
	}
	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getBoardofdirectors() {
		return boardofdirectors;
	}
	public void setBoardofdirectors(String boardofdirectors) {
		this.boardofdirectors = boardofdirectors;
	}
	public String getSectorid() {
		return sectorid;
	}
	public void setSectorid(String sectorid) {
		this.sectorid = sectorid;
	}
	public String getBriefwriteup() {
		return briefwriteup;
	}
	public void setBriefwriteup(String briefwriteup) {
		this.briefwriteup = briefwriteup;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
