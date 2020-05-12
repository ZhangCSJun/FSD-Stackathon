package cn.zj.cloud.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zj.cloud.user.entity.User;
import cn.zj.cloud.user.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String>requestBody){
		String username = requestBody.get("username");
		String password = requestBody.get("password");
		Map<String, String> loginInfo = userService.login(username, password);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(loginInfo);
	}
	
	/**
	 * User Regist
	 * @param user User
	 * @return
	 */
	@PostMapping("/user")
	public ResponseEntity<String> register(@RequestBody User user) {

		HttpStatus resStatus = HttpStatus.CREATED;
		String resEntity = "";
		try {
			userService.regist(user);
			resEntity = "Regist Sucessful";
		} catch(Exception e) {
			resStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			resEntity = "Regist Fail";
		}
		return ResponseEntity.status(resStatus).body(resEntity);
	}
	
	/**
	 * Update User's password
	 * @param requestBody Map<String, String>
	 * @return
	 */
	@PutMapping("/user")
	public ResponseEntity<Map<String, String>> updatePassword(@RequestBody Map<String, String>requestBody) {
		String id = requestBody.get("id");
		String oldpwd = requestBody.get("oldpwd");
		String newpwd = requestBody.get("newpwd");
		
		Map<String, String> resultMap = userService.updatePassword(id, oldpwd, newpwd);
		return ResponseEntity.ok(resultMap);
	}

	/**
	 * Query IpoDetails
	 * @return
	 */
	@GetMapping("/ipodetails")
	public ResponseEntity<List<Map<String, String>>> queryIpoDetails(){
		List<Map<String, String>> resultList = userService.queryIpoDetails();
		return ResponseEntity.ok(resultList);
	}
	
	/**
	 * Query IpoDetails By Company Name
	 * @param companyName String
	 * @return
	 */
	@GetMapping("/ipodetails/{companyName}")
	public ResponseEntity<List<Map<String, String>>> queryIpoDetails(@PathVariable String companyName){
		List<Map<String, String>> resultList = userService.queryIpoDetailsByCompanyName(companyName);
		return ResponseEntity.ok(resultList);
	}
}
