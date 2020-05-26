package cn.zj.cloud.admin.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.zj.cloud.admin.entity.Company;
import cn.zj.cloud.admin.entity.IpoDetail;
import cn.zj.cloud.admin.entity.Sector;
import cn.zj.cloud.admin.entity.StockExchange;
import cn.zj.cloud.admin.entity.StockPrice;
import cn.zj.cloud.admin.model.LoginRequest;
import cn.zj.cloud.admin.model.UpdateCompanyStatusRequest;
import cn.zj.cloud.admin.service.AdminService;
import cn.zj.cloud.constant.Constant;
import cn.zj.cloud.model.Response;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

//	// Admin login
//	@PostMapping("/login")
//	public ResponseEntity<Response> login(@RequestBody LoginRequest requset) {
//		String username = requset.getUserName();
//		String password = requset.getPassWord();
//		Response response = adminService.login(username, password);
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
//	}

	// Company related process
	// Query all company info
	@GetMapping("/company")
	public ResponseEntity<Response> queryCompany() {
		Response response = adminService.queryCompaniesInfo();
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	// Query all company info by specified kewword
	@GetMapping("/company/ajax/{keyword}")
	public ResponseEntity<Response> queryCompanyByKeyWord(@PathVariable String keyword) {
		Response response = adminService.queryCompanyByKeyWord(keyword);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	// Query all company info by specified company name
	@GetMapping("/company/{companyName}")
	public ResponseEntity<Response> queryCompanyByCompanyName(@PathVariable String companyName) {
		Response response = adminService.queryCompanyByCompanyName(companyName);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	// Regist company info
	@PostMapping("/company")
	public ResponseEntity<Response> registCompany(@RequestBody Company company) {
		Response response = adminService.registCompany(company);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	// Update company status
	@PutMapping("/company")
	public ResponseEntity<Response> updateCompanyStatus(@RequestBody UpdateCompanyStatusRequest requestBody) {
		String id = requestBody.getId();
		String status = requestBody.getStatus();
		Response response = adminService.updateCompanyStatus(id, status);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	// IpoDetail related process
	@GetMapping("/ipodetail")
	public ResponseEntity<Response> queryIpoDetails() {
		Response response = adminService.queryIpoDetails();
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@GetMapping("/ipodetail/{companyname}")
	public ResponseEntity<Response> queryIpoDetailsByCompanyName(@PathVariable String companyname) {
//		try {
//			companyname = URLDecoder.decode(companyname, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			Response response = new Response();
//			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//			response.setCode(Constant.CODE_ZERO);
//			response.setMessage("Internal server error");
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);
//		} 
		Response response = adminService.queryIpoDetailsByCompanyName(companyname);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}


	@PostMapping("/ipodetail")
	public ResponseEntity<Response> registIpoDetail(@RequestBody IpoDetail ipodetail) {
		Response response = adminService.registIpoDetail(ipodetail);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	// Sector related process
	@GetMapping("/sector")
	public ResponseEntity<Response> querySector() {
		Response response = adminService.querySector();
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@GetMapping("/sector/{id}")
	public ResponseEntity<Response> querySectorById(@PathVariable String id) {
		Response response = adminService.querySectorById(id);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@PostMapping("/sector")
	public ResponseEntity<Response> registSector(@RequestBody Sector sector) {
		Response response = adminService.registSector(sector);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	// StockExchange related process
	@GetMapping("/stockexchange")
	public ResponseEntity<Response> queryStockExchange() {
		Response response = adminService.queryStockExchange();
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@PostMapping("/stockexchange")
	public ResponseEntity<Response> registStockExchange(@RequestBody StockExchange stockExchange) {
		Response response = adminService.registStockExchange(stockExchange);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@DeleteMapping("/stockexchange/{id}")
	public ResponseEntity<Response> delStockExchangeById(@PathVariable String id) {
		Response response = adminService.deleteStockExchangeById(id);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	// Stock Price related process
	@PostMapping("/stockprice")
	public ResponseEntity<Response> registStockExchange(@RequestBody List<StockPrice> StockPrice) {
		Response response = adminService.uploadStockPrice(StockPrice);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	// Download stock price template
	@GetMapping("/template/download")
	public void downloadTemplate(HttpServletResponse response) {
		String filePath = "F:\\FutureStudio\\data_xls\\temp.xls";
		File file = new File(filePath);
		if (file.exists()) {
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);// 得到文件名
			response.setContentType("application/force-download");
			response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bis.close();
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

//	private HttpStatus getHttpStatus(int status) {
//		HttpStatus httpStatus;
//		switch(status) {
//			case Status.OK:httpStatus = HttpStatus.OK; break;
//			case Status.INTERNAL_SERVER_ERROR:httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; break;
//			default:httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; break;
//		}
//		return httpStatus;
//	}
}
