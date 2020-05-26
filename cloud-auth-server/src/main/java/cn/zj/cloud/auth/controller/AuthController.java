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

import cn.zj.cloud.auth.model.AuthInfo;
import cn.zj.cloud.auth.service.AuthService;
import cn.zj.cloud.constant.Constant;
import cn.zj.cloud.model.Response;
import cn.zj.cloud.util.StringUtil;

@RestController
public class AuthController {

	@Autowired
	private AuthService auth;
	
	@PostMapping("/token")
	public ResponseEntity<Response> issueToken(@RequestBody AuthInfo request) {
		String userId = request.getUserId();
		Response response = auth.issueToken(userId);

		if(!StringUtil.isNull(response.getBusiness()) && 
				response.getBusiness().containsKey(Constant.BUSINESS_DATA_TOKEN) &&
				!StringUtil.isNullOrEmpty(response.getBusiness().get(Constant.BUSINESS_DATA_TOKEN).toString())) {
			String token = response.getBusiness().get(Constant.BUSINESS_DATA_TOKEN).toString();
			return ResponseEntity.status(response.getStatus()).header("tkn", token).build();
		} else {
			return ResponseEntity.status(response.getStatus()).body(response);
		}
		
	}
	
	@GetMapping("/token")
	public ResponseEntity<Response> verify(@RequestHeader("tkn") String token) {
		Response response = auth.verify(token);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	

}
