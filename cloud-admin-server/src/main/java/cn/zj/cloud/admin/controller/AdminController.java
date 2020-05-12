package cn.zj.cloud.admin.controller;

import java.util.List;
import java.util.Map;

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
import cn.zj.cloud.admin.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	// Admin login
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String>requestBody){
		String username = requestBody.get("username");
		String password = requestBody.get("password");
		Map<String, String> loginInfo = adminService.login(username, password);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(loginInfo);
	}
	
	// Company related process
	@GetMapping("/company")
	public ResponseEntity<List<Map<String, String>>> queryCompany(){
		List<Map<String, String>> result = adminService.queryCompaniesInfo();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}
	
	@GetMapping("/company/{companyName}")
	public ResponseEntity<List<Map<String, String>>> queryCompanyByCompanyName(@PathVariable String companyName){
		List<Map<String, String>> result = adminService.queryCompanyByCompanyName(companyName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}

	@PostMapping("/company")
	public ResponseEntity<Map<String, String>> registCompany(@RequestBody Company company){
		Map<String, String> result = adminService.registCompany(company);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}
	
	@PutMapping("/company")
	public ResponseEntity<Map<String, String>> updateCompanyStatus(@RequestBody Map<String, String> requestBody){
		String id = requestBody.get("id");
		String status = requestBody.get("status");
		
		Map<String, String> result = adminService.updateCompanyStatus(id, status);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}
	
	// IpoDetail related process
	@GetMapping("/ipodetail")
	public ResponseEntity<List<Map<String, String>>> queryIpoDetails(){
		List<Map<String, String>> result = adminService.queryIpoDetails();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}
	
	@GetMapping("/ipodetail/{companyname}")
	public ResponseEntity<List<Map<String, String>>> queryIpoDetailsByCompanyName(@PathVariable String companyname){
		List<Map<String, String>> result = adminService.queryIpoDetailsByCompanyName(companyname);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}
	
	@PostMapping("/ipodetail")
	public ResponseEntity<Map<String, String>> registIpoDetail(@RequestBody IpoDetail ipodetail){
		Map<String, String> result = adminService.registIpoDetail(ipodetail);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}
	
	// Sector related process
	@GetMapping("/sector")
	public ResponseEntity<List<Map<String, String>>> querySector(){
		List<Map<String, String>> result = adminService.querySector();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}

	@GetMapping("/sector/{id}")
	public ResponseEntity<Map<String, String>> querySectorById(@PathVariable String id){
		Map<String, String> result = adminService.querySectorById(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}

	@PostMapping("/sector")
	public ResponseEntity<Map<String, String>> registSector(@RequestBody Sector sector){
		Map<String, String> result = adminService.registSector(sector);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}
	
	// StockExchange related process
	@GetMapping("/stockexchange")
	public ResponseEntity<List<Map<String, String>>> queryStockExchange(){
		List<Map<String, String>> result = adminService.queryStockExchange();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}

	@PostMapping("/stockexchange")
	public ResponseEntity<Map<String, String>> registStockExchange(@RequestBody StockExchange stockExchange){
		Map<String, String> result = adminService.registStockExchange(stockExchange);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}
	
	@DeleteMapping("/stockexchange/{id}")
	public ResponseEntity delStockExchangeById(@PathVariable String id){
		adminService.deleteStockExchangeById(id);
		return ResponseEntity.ok().build();
	}
	
	// Stock Price related process
	@PostMapping("/stockprice")
	public ResponseEntity<Map<String, String>> registStockExchange(@RequestBody List<StockPrice> StockPrice){
		Map<String, String> result = adminService.uploadStockPrice(StockPrice);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
	}
}
