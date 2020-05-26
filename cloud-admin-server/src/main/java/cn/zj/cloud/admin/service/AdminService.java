package cn.zj.cloud.admin.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import cn.zj.cloud.constant.Constant;
import cn.zj.cloud.enums.TableName;
import cn.zj.cloud.model.Response;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

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
	public Response login(String username, String password){
		Response response = new Response();
		List<User> userList = adminRepository.queryUser(username, password);
		if(userList.size() == 1) {
			User user = (User)userList.get(0);
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ONE);
			response.setMessage(Constant.LOGIN_SUCESS);

			Map<String, Object> business = new LinkedHashMap<String, Object>();
			business.put(Constant.BUSINESS_DATA_ID, user.getId());
			response.setBusiness(business);

		} else {
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(Constant.LOGIN_FAIL);
		}
		return response;
	}

	/**
	 * Register basic infomation about company
	 * @param company
	 * @return
	 */
	public Response registCompany(Company company) {
		Response response = new Response();
		// generate id
		String id = generateId(TableName.COMPANY);
		company.setId(id);

		try {
			Company newCompany = companyRepository.save(company);
			String companyId = newCompany.getId();
			if(!"".equals(companyId)) {
				response.setStatus(HttpStatus.OK.value());
				response.setCode(Constant.CODE_ONE);
				response.setMessage(Constant.REGIST_SUCESS);

			} else {
				response.setStatus(HttpStatus.OK.value());
				response.setCode(Constant.CODE_ZERO);
				response.setMessage(Constant.REGIST_FAIL);
			}
		} catch(Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	/**
	 * Update Company Status
	 * @param id String
	 * @param status String
	 * @return
	 */
	public Response updateCompanyStatus(String id, String status) {
		Response response = new Response();
		int updateRow = companyRepository.updateCompanyStatus(id, status);

		if(updateRow == 1) {
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ONE);
			response.setMessage(Constant.UPDATE_STATUS_SUCESS);

		} else {
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(Constant.UPDATE_STATUS_FAIL);
		}
		return response;
	}
	
	
	/**
	 * Query All Companies' Info
	 * @param companyName String
	 * @return
	 */
	public Response queryCompaniesInfo(){
		Response response = new Response();
		List<Company> companies = companyRepository.findAll();;
		response.setStatus(HttpStatus.OK.value());
		response.setCode(Constant.CODE_ONE);
		if(companies.size()>0) {
			response.setMessage(Constant.QUERY_DATA_SUCESS.replace("{0}", String.valueOf(companies.size())));
		} else {
			response.setMessage(Constant.QUERY_DATA_FAIL);
		}
		Map<String, Object> business = new LinkedHashMap<String, Object>();
		business.put(Constant.BUSINESS_DATA_DATA, companies);
		response.setBusiness(business);
		
		return response;
	}

	
	/**
	 * Query company info by keyword
	 * @param companyName String
	 * @return
	 */
	public Response queryCompanyByKeyWord(String kewWord){
		Response response = new Response();
		List<Company> companies = companyRepository.queryCompanyByKeyWord(kewWord);
		response.setStatus(HttpStatus.OK.value());
		response.setCode(Constant.CODE_ONE);
		if(companies.size()>0) {
			response.setMessage(Constant.QUERY_DATA_SUCESS.replace("{0}", String.valueOf(companies.size())));
		} else {
			response.setMessage(Constant.QUERY_DATA_FAIL);
		}
		Map<String, Object> business = new LinkedHashMap<String, Object>();
		business.put(Constant.BUSINESS_DATA_DATA, companies);
		response.setBusiness(business);
		
		return response;
	}
	
	/**
	 * Query company info by company name
	 * @param companyName String
	 * @return
	 */
	public Response queryCompanyByCompanyName(String companyName){
		Response response = new Response();
		List<Company> companies = companyRepository.queryByCompanyName(companyName);
		response.setStatus(HttpStatus.OK.value());
		response.setCode(Constant.CODE_ONE);
		if(companies.size()>0) {
			response.setMessage(Constant.QUERY_DATA_SUCESS.replace("{0}", String.valueOf(companies.size())));
		} else {
			response.setMessage(Constant.QUERY_DATA_FAIL);
		}
		Map<String, Object> business = new LinkedHashMap<String, Object>();
		business.put(Constant.BUSINESS_DATA_DATA, companies);
		response.setBusiness(business);
		
		return response;
	}

	/**
	 * IpoDetail Regist
	 * @param ipoDetail
	 * @return
	 */
	public Response registIpoDetail(IpoDetail ipoDetail) {
		Response response = new Response();
		// generate id
		String id = generateId(TableName.IPODETAIL);
		ipoDetail.setId(id);
		
		try {
			IpoDetail newIpoDetail = ipodetailRepository.save(ipoDetail);
			String ipoDetailId = newIpoDetail.getId();
			if(!"".equals(ipoDetailId)) {
				response.setStatus(HttpStatus.OK.value());
				response.setCode(Constant.CODE_ONE);
				response.setMessage(Constant.REGIST_SUCESS);

			} else {
				response.setStatus(HttpStatus.OK.value());
				response.setCode(Constant.CODE_ZERO);
				response.setMessage(Constant.REGIST_FAIL);
			}
		} catch(Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	/**
	 * Query IpoDetails
	 * @return
	 */
	public Response queryIpoDetails(){
		Response response = new Response();
		List<IpoDetail> ipoDetails = ipodetailRepository.findAll();
		response.setStatus(HttpStatus.OK.value());
		response.setCode(Constant.CODE_ONE);
		if(ipoDetails.size()>0) {
			response.setMessage(Constant.QUERY_DATA_SUCESS.replace("{0}", String.valueOf(ipoDetails.size())));
		} else {
			response.setMessage(Constant.QUERY_DATA_FAIL);
		}
		Map<String, Object> business = new LinkedHashMap<String, Object>();
		business.put(Constant.BUSINESS_DATA_DATA, ipoDetails);
		response.setBusiness(business);

		return response;
	}
	
	/**
	 * Query IpoDetails By Company Name
	 * @param companyName String
	 * @return
	 */
	public Response queryIpoDetailsByCompanyName(String companyName){
		Response response = new Response();
		List<IpoDetail> ipoDetails = ipodetailRepository.queryByCompanyName(companyName);
		response.setStatus(HttpStatus.OK.value());
		response.setCode(Constant.CODE_ONE);
		if(ipoDetails.size()>0) {
			response.setMessage(Constant.QUERY_DATA_SUCESS.replace("{0}", String.valueOf(ipoDetails.size())));
		} else {
			response.setMessage(Constant.QUERY_DATA_FAIL);
		}
		Map<String, Object> business = new LinkedHashMap<String, Object>();
		business.put(Constant.BUSINESS_DATA_DATA, ipoDetails);
		response.setBusiness(business);

		return response;
	}
	
	/**
	 * Sector Regist
	 * @param sector
	 * @return
	 */
	public Response registSector(Sector sector) {
		Response response = new Response();
		// generate  id
		String id = generateId(TableName.SECTOR);
		sector.setId(id);
		
		try {
			Sector newSector = sectorRepository.save(sector);
			String sectorId = newSector.getId();
			if(!"".equals(sectorId)) {
				response.setStatus(HttpStatus.OK.value());
				response.setCode(Constant.CODE_ONE);
				response.setMessage(Constant.REGIST_SUCESS);

			} else {
				response.setStatus(HttpStatus.OK.value());
				response.setCode(Constant.CODE_ZERO);
				response.setMessage(Constant.REGIST_FAIL);
			}
		} catch(Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	/**
	 * Query All Sectors' Info
	 * @param companyName String
	 * @return
	 */
	public Response querySector(){
		Response response = new Response();
		List<Sector> sectors = sectorRepository.findAll();
		
		response.setStatus(HttpStatus.OK.value());
		response.setCode(Constant.CODE_ONE);
		if(sectors.size()>0) {
			response.setMessage(Constant.QUERY_DATA_SUCESS.replace("{0}", String.valueOf(sectors.size())));
		} else {
			response.setMessage(Constant.QUERY_DATA_FAIL);
		}
		Map<String, Object> business = new LinkedHashMap<String, Object>();
		business.put(Constant.BUSINESS_DATA_DATA, sectors);
		response.setBusiness(business);

		return response;
	}
	
	/**
	 * Query Sector by id
	 * @param id String
	 * @return
	 */
	public Response querySectorById(String id){
		Response response = new Response();
		Optional<Sector> optinal = sectorRepository.findById(id);
		response.setStatus(HttpStatus.OK.value());
		response.setCode(Constant.CODE_ONE);
		if(optinal.isPresent()) {
			response.setMessage(Constant.QUERY_DATA_SUCESS.replace("{0}", Constant.NUM_ONE));
			Map<String, Object> business = new LinkedHashMap<String, Object>();
			business.put(Constant.BUSINESS_DATA_DATA, optinal.get());
			response.setBusiness(business);
		} else {
			response.setMessage(Constant.QUERY_DATA_FAIL);
		}

		return response;
	}
	
	/**
	 * StockExchange Regist
	 * @param stockExchange StockExchange
	 * @return
	 */
	public Response registStockExchange(StockExchange stockExchange) {
		Response response = new Response();
		// generate id
		String id = generateId(TableName.STOCKEXCHANGE);
		stockExchange.setId(id);

		try {
			StockExchange newStockExchange = stockExchangeRepository.save(stockExchange);
			String stockExchangeId = newStockExchange.getId();
			if(!"".equals(stockExchangeId)) {
				response.setStatus(HttpStatus.OK.value());
				response.setCode(Constant.CODE_ONE);
				response.setMessage(Constant.REGIST_SUCESS);

			} else {
				response.setStatus(HttpStatus.OK.value());
				response.setCode(Constant.CODE_ZERO);
				response.setMessage(Constant.REGIST_FAIL);
			}
		} catch(Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	/**
	 * Query All StockExchanges' Info
	 * @return
	 */
	public Response queryStockExchange(){
		Response response = new Response();
		List<StockExchange> stockExchangeList = stockExchangeRepository.findAll();
		
		response.setStatus(HttpStatus.OK.value());
		response.setCode(Constant.CODE_ONE);
		if(stockExchangeList.size()>0) {
			response.setMessage(Constant.QUERY_DATA_SUCESS.replace("{0}", String.valueOf(stockExchangeList.size())));
		} else {
			response.setMessage(Constant.QUERY_DATA_FAIL);
		}
		Map<String, Object> business = new LinkedHashMap<String, Object>();
		business.put(Constant.BUSINESS_DATA_DATA, stockExchangeList);
		response.setBusiness(business);

		return response;
	}
	
	/**
	 * StockExchange Delete
	 * @param id String
	 * @return
	 */
	public Response deleteStockExchangeById(String id) {
		Response response = new Response();
		try {
			stockExchangeRepository.deleteById(id);
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ONE);
			response.setMessage(Constant.DELETE_DATA);
		} catch(Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
	
	/**
	 * upload Stock Price
	 * @param stockPriceList List<StockPrice>
	 * @return`
	 */
	public Response uploadStockPrice(List<StockPrice> stockPriceList) {
		Response response = new Response();

		int uploadTotalNum = stockPriceList.size();
		StringBuilder dateBuild = new StringBuilder();
		// first one
		StockPrice sp = stockPriceList.get(0);
		String dateFrom = dateBuild.append(sp.getDate()).append(" ").append(sp.getTime()).toString();
		// last one
		dateBuild = new StringBuilder();
		sp = stockPriceList.get(uploadTotalNum-1);
		String dateTo = dateBuild.append(sp.getDate()).append(" ").append(sp.getTime()).toString();
		
		for(StockPrice stokPrice: stockPriceList) {
			stockPriceRepository.save(stokPrice);
		}
		response.setStatus(HttpStatus.OK.value());
		response.setCode(Constant.CODE_ONE);
		
		Map<String, Object> dataContent = new LinkedHashMap<String, Object>();
		dataContent.put("totalnum", String.valueOf(uploadTotalNum));
		dataContent.put("datefrom", dateFrom);
		dataContent.put("dateto", dateTo);

		Map<String, Object> business = new LinkedHashMap<String, Object>();
		business.put(Constant.BUSINESS_DATA_DATA, dataContent);
		response.setBusiness(business);

		return response; 
	}
	
	private Map<String, Map<String, Object>> responseBody(Map<String, Object> commonData, Map<String, Object> business){
		Map<String, Map<String, Object>> body = new LinkedHashMap<String, Map<String, Object>>();
		body.put(Constant.COMMON_DATA, commonData);
		if(business == null) {
			body.put(Constant.BUSINESS_DATA, new LinkedHashMap<String, Object>());
		}
		body.put(Constant.BUSINESS_DATA, business);
		return body;
	}
	
	
	/**
	 * Generate table id
	 * @param tablename TableName
	 * @return`next id
	 */
	private String generateId(TableName tablename) {
		String currentId = Constant.EMPTY_STRING;
		String nextId = Constant.EMPTY_STRING;
		List<?> queryList = null;
		switch(tablename) {
			case COMPANY:
				queryList = companyRepository.queryMaxId();
				if(queryList.size()==1) {
					currentId = ((Company)queryList.get(0)).getId();
				}else{
					nextId = "C10000101";
				};
				break;
			case IPODETAIL:
				queryList = ipodetailRepository.queryMaxId();
				if(queryList.size()==1) {
					currentId = ((IpoDetail)queryList.get(0)).getId();
				}else{
					nextId = "I10000101";
				};
				break;
			case SECTOR:
				queryList = sectorRepository.queryMaxId();
				if(queryList.size()==1) {
					currentId = ((Sector)queryList.get(0)).getId();
				}else{
					nextId = "S10101";
				};
				break;
			case STOCKEXCHANGE:
				queryList = stockExchangeRepository.queryMaxId();
				if(queryList.size()==1) {
					currentId = ((StockExchange)queryList.get(0)).getId();
				}else{
					nextId = "X10101";
				};
				break;	
			default:break;
		}
		
		if(!Util.isNullOrEmpty(currentId)) {
			StringBuilder prefix =new StringBuilder(currentId.substring(0, 1));
			int id = Integer.valueOf(currentId.substring(1))+1;
			nextId = prefix.append(String.valueOf(id)).toString();
		}

		return nextId;
	}

	
    public List readExcel(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList=new ArrayList<List>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if(cellinfo.isEmpty()){
                            continue;
                        }
                        innerList.add(cellinfo);
                        System.out.print(cellinfo);
                    }
                    outerList.add(i, innerList);
                    System.out.println();
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
