package cn.zj.cloud.auth.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import cn.zj.cloud.auth.service.AuthService;

@RestController
public class AuthController {

	@Autowired
	private AuthService auth;
	
	@PostMapping("/token")
	public ResponseEntity issueToken(@RequestBody Map<String, String>requestBody) {
		String userId = requestBody.get("userid");
		String token = auth.issueToken(userId);
		return ResponseEntity.status(HttpStatus.OK).header("tkn", token).build();
	}
	
	@GetMapping("/token")
	public ResponseEntity<Map<String, Object>> verify(@RequestHeader("tkn") String token) {
		Map<String, Object> responseBody = auth.verify(token);
		return ResponseEntity.status(HttpStatus.OK).header("tkn", token).body(responseBody);
	}
	

}
