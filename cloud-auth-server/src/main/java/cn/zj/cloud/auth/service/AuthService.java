package cn.zj.cloud.auth.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import cn.zj.cloud.auth.common.Constant;
import cn.zj.cloud.auth.util.JwtHandler;
import cn.zj.cloud.auth.util.Utility;


@Service
public class AuthService {
	@Autowired
	private JwtHandler jwt;
	
	public String issueToken(String userId) {
		return jwt.issueToken(userId);
	}
	
	public Map<String, Object> verify(String token){
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Map<String, String> data = new LinkedHashMap<String, String>();
		if(Utility.isEmpty(token)) {
			result.put("status", "0");
			result.put("msg", "invilad token");
			result.put("data", data);
		} else {
			String userId = Constant.EMPTY_STRING;
			try {
				userId = jwt.verifyToken(token);
				result.put("status", "1");
				result.put("msg", "token verify sucessful");
				data.put("userid", userId);
				result.put("data", data);
			} catch(Exception e) {
				result.put("status", "0");
				result.put("msg", "token verify fail");
				result.put("data", data);
			}
		}
		return result;
	}
}
