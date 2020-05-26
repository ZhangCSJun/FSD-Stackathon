package cn.zj.cloud.user.model;

import java.util.List;

import cn.zj.cloud.user.entity.Company;

public class CompanyResponse {

	private List<Company> CompanyList;

	public List<Company> getCompanyList() {
		return CompanyList;
	}

	public void setCompanyList(List<Company> companyList) {
		CompanyList = companyList;
	}
	
}
