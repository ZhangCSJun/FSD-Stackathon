package cn.zj.cloud.auth.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cn.zj.cloud.auth.util.JwtHandler;
import cn.zj.cloud.constant.Constant;
import cn.zj.cloud.model.Response;
import cn.zj.cloud.util.StringUtil;

@Service
public class AuthService {
	@Autowired(required=false)
	private JwtHandler jwt;

	/**
	 * Issue token
	 * @param userId String
	 * @return token
	 */
	public Response issueToken(String userId) {
		Response response = new Response();
		String token = jwt.issueToken(userId);

		if(StringUtil.isNullOrEmpty(token)) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(Constant.MESSAGE_INTERNAL_SERVER_ERROR);

		} else {
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ONE);
			response.setMessage(Constant.TOKEN_ISSUED);
			Map<String, Object> business = new LinkedHashMap<String, Object>();
			business.put(Constant.BUSINESS_DATA_TOKEN, token);
			response.setBusiness(business);
		}
		return response;
	}

	/**
	 * Verify token
	 * @param token String
	 * @return Response
	 */
	public Response verify(String token) {
		Response response = new Response();

		String userId = Constant.EMPTY_STRING;
		try {
			userId = jwt.verifyToken(token);
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ONE);
			response.setMessage(Constant.TOKEN_VERIFY_VALIAD);
			Map<String, Object> business = new LinkedHashMap<String, Object>();
			business.put(Constant.BUSINESS_DATA_ID, userId);
			response.setBusiness(business);

			// any token verify exception was caught 
		} catch (Exception e) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(Constant.TOKEN_VERIFY_INVILAD);
		}

		return response;
	}
}
