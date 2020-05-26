package cn.zj.cloud.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import cn.zj.cloud.user.entity.User;

import cn.zj.cloud.user.model.LoginRequest;
import cn.zj.cloud.model.Response;
import cn.zj.cloud.user.model.UpdateRequest;
import cn.zj.cloud.user.service.UserService;


@RestController
//@CrossOrigin(origins="*", allowCredentials="true")
public class UserController {
	@Autowired
	private UserService userService;
	
	private String adminServiceBaseUrl="http://127.0.0.1:8099";

	// User login
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginRequest request) {
		String userName = request.getUsername();
		String passWord = request.getPassword();
		Response response = userService.login(userName, passWord);

		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	/**
	 * User Regist
	 * 
	 * @param user User
	 * @return
	 */
	@PostMapping("/user")
	public ResponseEntity<Response> register(@RequestBody User user) {
		Response response = userService.regist(user);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	/**
	 * User Confirm
	 * 
	 * @param user User
	 * @return
	 */
	@PutMapping("/user/{id}")
	public ResponseEntity<Response> userConfirm(@PathVariable String id) {
		Response response = userService.activeUser(id);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	/**
	 * Query User Info By id
	 * 
	 * @param id String
	 * @return
	 */
	@GetMapping("/user/{id}")
	public ResponseEntity<Response> queryUserById(@PathVariable String id) {
		Response response = userService.queryUserById(id);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	
	/**
	 * Update User's password
	 * 
	 * @param requestBody Map<String, String>
	 * @return
	 */
	@PutMapping("/user")
	public ResponseEntity<Response> updatePassword(@RequestBody UpdateRequest request) {
		String id = request.getId();
		String oldpwd = request.getOldPwd();
		String newpwd = request.getNewPwd();

		Response response = userService.updatePassword(id, oldpwd, newpwd);
		return ResponseEntity.status(response.getStatus()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	/**
	 * Query company
	 * 
	 * @return
	 */
	@GetMapping("/company")
	public ResponseEntity<Response> queryComapny() {
		return proxy("/company");
	}
	
	// Query all company info by specified kewword
	@GetMapping("/company/ajax/{keyword}")
	public ResponseEntity<Response> queryCompanyByKeyWord(@PathVariable String keyword) {
		return proxy("/company/ajax/" + keyword);
	}
	
	// Query all company info by specified company name
	@GetMapping("/company/{companyName}")
	public ResponseEntity<Response> queryCompanyByCompanyName(@PathVariable String companyName) {
		return proxy("/company/" + companyName);
	}

	
	/**
	 * Query IpoDetails
	 * 
	 * @return
	 */
	@GetMapping("/ipodetail")
	public ResponseEntity<Response> queryIpoDetails() {
		return proxy("/ipodetail");
	}

	/**
	 * Query IpoDetails By Company Name
	 * 
	 * @param companyName String
	 * @return
	 */
	@GetMapping("/ipodetail/{companyname}")
	public ResponseEntity<Response> queryIpoDetails(@PathVariable String companyname) {
		return proxy("/ipodetail/" + companyname);
	}
	
	private ResponseEntity<Response> proxy(String uriPath){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Response> response = restTemplate.getForEntity(adminServiceBaseUrl + uriPath, Response.class);
		return response;
	}
}
