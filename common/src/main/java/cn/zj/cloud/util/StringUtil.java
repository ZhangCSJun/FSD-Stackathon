package cn.zj.cloud.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.zj.cloud.constant.Constant;
import cn.zj.cloud.model.Response;

public class StringUtil {

	public static String plainDate(Timestamp ts) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(ts);
	}
	
	public static boolean isNullOrEmpty(String str) {
		boolean checkResult = false;
		if(str == null || str.length() == 0) {
			checkResult = true;
		}
		return checkResult;
	}
	
	public static String createResponseBody(Response res) {
		String responseBody = Constant.EMPTY_STRING;
		
		ObjectMapper jsonMapper = new ObjectMapper();
		try {
			responseBody = jsonMapper.writeValueAsString(res);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return responseBody;
	}
	
	public static boolean isNull(Object obj) {
		boolean checkResult = false;
		if(obj == null) {
			checkResult = true;
		}
		return checkResult;
	}
}
