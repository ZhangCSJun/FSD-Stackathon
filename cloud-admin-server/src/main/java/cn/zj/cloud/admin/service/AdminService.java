package cn.zj.cloud.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zj.cloud.admin.entity.Company;
import cn.zj.cloud.admin.entity.IpoDetail;
import cn.zj.cloud.admin.entity.Sector;
import cn.zj.cloud.admin.entity.StockExchange;
import cn.zj.cloud.admin.entity.StockPrice;
import cn.zj.cloud.admin.entity.User;
import cn.zj.cloud.admin.repository.AdminRepository;
import cn.zj.cloud.admin.repository.CompanyRepository;
import cn.zj.cloud.admin.repository.IpodetailRepository;
import cn.zj.cloud.admin.repository.SectorRepository;
import cn.zj.cloud.admin.repository.StockExchangeRepository;
import cn.zj.cloud.admin.repository.StockPriceRepository;
import cn.zj.cloud.admin.util.Util;


@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private IpodetailRepository ipodetailRepository;
	@Autowired
	private StockExchangeRepository stockExchangeRepository;
	@Autowired
	private StockPriceRepository stockPriceRepository;
	@Autowired
	private SectorRepository sectorRepository;
	
	
	/**
	 * Get Admin Login Info
	 * @param user
	 * @return
	 */
	public Map<String, String> login(String username, String password){
		Map<String, String> result = new LinkedHashMap<String, String>();
		List<User> userList = adminRepository.queryUser(username, password);
		if(userList.size() == 1) {
			User user = (User)userList.get(0);
			result.put("status", "1");
			result.put("id", user.getId());
		} else {
			result.put("status", "0");
		}
		return result;
	}
	
	private String generateCompanyId() {
		String companyId = "60000001";
		List<Company> companyList = companyRepository.queryMaxId();
		if(companyList.size()==1) {
			String maxId = companyList.get(0).getId();
			int nextId = Integer.valueOf(maxId) + 1;
			companyId = String.valueOf(nextId);
		}
		return companyId;
	}
	
	/**
	 * Company Regist
	 * @param company
	 * @return
	 */
	public Map<String, String> registCompany(Company company) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		// generate user id
		String id = generateCompanyId();
		company.setId(id);

		Company newCompany = companyRepository.save(company);
		String companyId = newCompany.getId();
		if(!"".equals(companyId)) {
			result.put("status", "1");
		} else {
			result.put("status", "0");
		}
		return result;
	}
	
	/**
	 * Update Company Status
	 * @param id String
	 * @param status String
	 * @return
	 */
	public Map<String, String> updateCompanyStatus(String id, String status) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		int updateRow = companyRepository.updateCompanyStatus(id, status);
		if(updateRow == 1) {
			result.put("status", "1");
		} else {
			result.put("status", "0");
		}
		return result;
	}
	
	
	/**
	 * Query All Companies' Info
	 * @param companyName String
	 * @return
	 */
	public List<Map<String, String>> queryCompaniesInfo(){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<Company> companies = companyRepository.findAll();
		
		for(Company company: companies){
			Map<String, String> companyMap = new LinkedHashMap<String, String>();
			companyMap.put("id", company.getId());
			companyMap.put("name", company.getName());
			companyMap.put("code", company.getCode());
			companyMap.put("ceo", company.getCeo());
			companyMap.put("boardofdirectors", company.getBoardofdirectors());
			companyMap.put("sectorid", company.getSectorid());
			companyMap.put("turnover", company.getTurnover().toString());
			companyMap.put("briefwriteup", company.getBriefwriteup());
			companyMap.put("status", company.getStatus());
			result.add(companyMap);
		}

		return result;
	}

	/**
	 * Query Company Info By Company Name
	 * @param companyName String
	 * @return
	 */
	public List<Map<String, String>> queryCompanyByCompanyName(String companyName){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<Company> companies = companyRepository.queryByCompanyName(companyName);
		
		for(Company company: companies){
			Map<String, String> companyMap = new LinkedHashMap<String, String>();
			companyMap.put("id", company.getId());
			companyMap.put("name", company.getName());
			companyMap.put("code", company.getCode());
			companyMap.put("ceo", company.getCeo());
			companyMap.put("boardofdirectors", company.getBoardofdirectors());
			companyMap.put("sectorid", company.getSectorid());
			companyMap.put("turnover", company.getTurnover().toString());
			companyMap.put("briefwriteup", company.getBriefwriteup());
			companyMap.put("status", company.getStatus());
			result.add(companyMap);
		}

		return result;
	}
	
	private String generateIpoDetailId() {
		String ipoDetailId = "80000001";
		List<IpoDetail> ipoDetailList = ipodetailRepository.queryMaxId();
		if(ipoDetailList.size()==1) {
			String maxId = ipoDetailList.get(0).getId();
			int nextId = Integer.valueOf(maxId) + 1;
			ipoDetailId = String.valueOf(nextId);
		}
		return ipoDetailId;
	}
	
	/**
	 * IpoDetail Regist
	 * @param company
	 * @return
	 */
	public Map<String, String> registIpoDetail(IpoDetail ipoDetail) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		// generate id
		String id = generateIpoDetailId();
		ipoDetail.setId(id);

		IpoDetail newIpoDetail = ipodetailRepository.save(ipoDetail);
		String ipoDetailId = newIpoDetail.getId();
		if(!"".equals(ipoDetailId)) {
			result.put("status", "1");
		} else {
			result.put("status", "0");
		}
		return result;
	}
	
	/**
	 * Query IpoDetails
	 * @return
	 */
	public List<Map<String, String>> queryIpoDetails(){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<IpoDetail> ipoDetails = ipodetailRepository.findAll();
		
		for(IpoDetail ipoDetail: ipoDetails){
			Map<String, String> ipoMap = new LinkedHashMap<String, String>();
			ipoMap.put("id", ipoDetail.getId());
			ipoMap.put("company", ipoDetail.getCompanyName());
			ipoMap.put("stockexchange", ipoDetail.getStockExchange());
			ipoMap.put("pircepershare", ipoDetail.getPricePerShare().toString());
			ipoMap.put("totalnumberofshares", String.valueOf(ipoDetail.getTotalNumberOfShares()));
			ipoMap.put("opendatatime", ipoDetail.getOpenDataTime());
			result.add(ipoMap);
		}

		return result;
	}
	
	/**
	 * Query IpoDetails By Company Name
	 * @param companyName String
	 * @return
	 */
	public List<Map<String, String>> queryIpoDetailsByCompanyName(String companyName){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<IpoDetail> ipoDetails = ipodetailRepository.queryByCompanyName(companyName);
		
		for(IpoDetail ipoDetail: ipoDetails){
			Map<String, String> ipoMap = new LinkedHashMap<String, String>();
			ipoMap.put("id", ipoDetail.getId());
			ipoMap.put("company", ipoDetail.getCompanyName());
			ipoMap.put("stockexchange", ipoDetail.getStockExchange());
			ipoMap.put("pircepershare", ipoDetail.getPricePerShare().toString());
			ipoMap.put("totalnumberofshares", String.valueOf(ipoDetail.getTotalNumberOfShares()));
			ipoMap.put("opendatatime", ipoDetail.getOpenDataTime());
			result.add(ipoMap);
		}

		return result;
	}
	
	
	private String generateSectorId() {
		String sectorId = "300001";
		List<Sector> sectorList = sectorRepository.queryMaxId();
		if(sectorList.size()==1) {
			String maxId = sectorList.get(0).getId();
			int nextId = Integer.valueOf(maxId) + 1;
			sectorId = String.valueOf(nextId);
		}
		return sectorId;
	}
	
	/**
	 * Sector Regist
	 * @param sector
	 * @return
	 */
	public Map<String, String> registSector(Sector sector) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		// generate  id
		String id = generateSectorId();
		sector.setId(id);

		Sector newSector = sectorRepository.save(sector);
		String sectorId = newSector.getId();
		if(!"".equals(sectorId)) {
			result.put("status", "1");
		} else {
			result.put("status", "0");
		}
		return result;
	}
	
	/**
	 * Query All Sectors' Info
	 * @param companyName String
	 * @return
	 */
	public List<Map<String, String>> querySector(){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<Sector> sectors = sectorRepository.findAll();
		
		for(Sector sector: sectors){
			Map<String, String> sectorMap = new LinkedHashMap<String, String>();
			sectorMap.put("id", sector.getId());
			sectorMap.put("sectorname", sector.getSectorname());
			sectorMap.put("brief", sector.getBreif());
			result.add(sectorMap);
		}

		return result;
	}
	
	/**
	 * Query Sector by id
	 * @param id String
	 * @return
	 */
	public Map<String, String> querySectorById(String id){
		Map<String, String> result = new LinkedHashMap<String, String>();
		Optional<Sector> sector = sectorRepository.findById(id);
		if(sector.isPresent()) {
			result.put("status","1");
			result.put("id", sector.get().getId());
			result.put("sectorname", sector.get().getSectorname());
			result.put("brief", sector.get().getBreif());
		} else {
			result.put("status","0");
		}

		return result;
	}
	
	
	private String generateStockExchangeId() {
		String stockExchangeId = "400001";
		List<StockExchange> stockExchangeList = stockExchangeRepository.queryMaxId();
		if(stockExchangeList.size()==1) {
			String maxId = stockExchangeList.get(0).getId();
			int nextId = Integer.valueOf(maxId) + 1;
			stockExchangeId = String.valueOf(nextId);
		}
		return stockExchangeId;
	}
	
	/**
	 * StockExchange Regist
	 * @param stockExchange StockExchange
	 * @return
	 */
	public Map<String, String> registStockExchange(StockExchange stockExchange) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		// generate id
		String id = generateStockExchangeId();
		stockExchange.setId(id);

		StockExchange newStockExchange = stockExchangeRepository.save(stockExchange);
		String stockExchangeId = newStockExchange.getId();
		if(!"".equals(stockExchangeId)) {
			result.put("status", "1");
		} else {
			result.put("status", "0");
		}
		return result;
	}
	
	/**
	 * Query All StockExchanges' Info
	 * @return
	 */
	public List<Map<String, String>> queryStockExchange(){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<StockExchange> stockExchangeList = stockExchangeRepository.findAll();
		
		for(StockExchange stockExchange: stockExchangeList){
			Map<String, String> stockExchangeMap = new LinkedHashMap<String, String>();
			stockExchangeMap.put("id", stockExchange.getId());
			stockExchangeMap.put("abbrname", stockExchange.getAbbrname());
			stockExchangeMap.put("fullname", stockExchange.getFullname());
			stockExchangeMap.put("brief", stockExchange.getBrief());
			stockExchangeMap.put("contactaddress", stockExchange.getContactaddress());
			stockExchangeMap.put("remark", stockExchange.getRemark());
			result.add(stockExchangeMap);
		}

		return result;
	}
	
	/**
	 * StockExchange Delete
	 * @param id String
	 * @return
	 */
	public void deleteStockExchangeById(String id) {
		stockExchangeRepository.deleteById(id);
	}
	
	/**
	 * upload Stock Price
	 * @param stockPriceList List<StockPrice>
	 * @return`
	 */
	public Map<String, String> uploadStockPrice(List<StockPrice> stockPriceList) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		int uploadTotalNum = stockPriceList.size();
		StringBuilder dateBuild = new StringBuilder();
		// first one
		StockPrice sp = stockPriceList.get(0);
		String dateFrom = dateBuild.append(sp.getDate()).append(" ").append(sp.getTime()).toString();
		// last one
		sp = stockPriceList.get(uploadTotalNum-1);
		String dateTo = dateBuild.append(sp.getDate()).append(" ").append(sp.getTime()).toString();
		
		for(StockPrice stokPrice: stockPriceList) {
			stockPriceRepository.save(stokPrice);
		}
		result.put("totalnum", String.valueOf(uploadTotalNum));
		result.put("datefrom", dateFrom);
		result.put("dateto", dateTo);
		return result; 
	}
}
