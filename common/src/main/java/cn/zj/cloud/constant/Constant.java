package cn.zj.cloud.constant;

public class Constant {

	public static final String COMMON_DATA = "common_data";
	public static final String COMMON_DATA_STATUS = "status";
	public static final String COMMON_DATA_CODE = "code";
	public static final String COMMON_DATA_MESSAGE = "message";
	public static final String BUSINESS_DATA = "business_data";
	
	public static final String BUSINESS_DATA_ID = "id";
	public static final String BUSINESS_DATA_ROLE = "role";
	public static final String BUSINESS_DATA_DATA = "data";
	public static final String BUSINESS_DATA_TOKEN = "token";
	
	public static final String CODE_ONE = "001";
	public static final String CODE_ZERO = "000";
	public static final String EMPTY_STRING = "";
	public static final String NUM_ZERO = "0";
	public static final String NUM_ONE = "1";
	
	
	/* MESSAGE DEFINE */
	public static final String LOGIN_SUCESS = "Welcome";
	public static final String LOGIN_FAIL = "Invalid username or password";
	public static final String REGIST_SUCESS = "Regist sucess";
	public static final String REGIST_FAIL = "Regist fail";
	public static final String REGIST_FAIL_CONSTRAINT = "Regist fail by constraint";
	public static final String REGIST_FAIL_USER_NAME_EXISTED = "Username is existed";
	public static final String ACTIVE_SUCESS = "Confirm sucess";
	public static final String ACTIVE_FAIL = "Confirm fail";
	public static final String UPDATE_PASSWORD_SUCESS = "Update password sucess";
	public static final String UPDATE_PASSWORD_FAIL = "Update password fail";
	
	public static final String UPDATE_STATUS_SUCESS = "Update status sucess";
	public static final String UPDATE_STATUS_FAIL = "Update status fail";
	
	public static final String QUERY_DATA_SUCESS = "{0} record(s) searched";
	public static final String QUERY_DATA_FAIL = "No data";
	
	public static final String DELETE_DATA = "Delete operation was excuted";
	
	public static final String CONFIRM_MAIL_SUBJECT = "User Rigest Confirmation";
	
	public static final String TEST = "demo";
	
	public static final String AUTH_TOKEN_IS_EMPTY = "Authorization token is empty";
	
	public static final String INTERNAL_SERVER_ERROR = "Internal server error";
	public static final String ACCESS_FORBIDDEN = "Access forbidden";
	
	/* Token Header */
	public static final String ALG = "alg";
	public static final String TYP = "typ";
	public static final String ALG_HS256 = "HS256";
	public static final String TYP_JWT = "JWT";
	/* Token Payload */
	public static final String ISSUER = "iss";
	public static final String SUBJECT = "sub";
	public static final String AUDIENCE = "aud";
	public static final String EXPIRATION = "exp";
	public static final String CLAIM_USER_ID = "userId";
	
	public static final String ISSUER_CONTENT = "StockMarket";
	public static final String SUBJECT_CONTENT = "access";
	public static final String AUDIENCE_CONTENT = "user";
	
	public static final String SECRET_KEY = "nosecret";
	
	public static final String TOKEN_ISSUED = "token issue sucessful";
	public static final String TOKEN_VERIFY_INVILAD = "invilad token";
	public static final String TOKEN_VERIFY_VALIAD = "token verify sucessful";
	
	/* Request Method */
	public static final String REQUEST_METHOD_POST = "POST";
	
	/* Exception Message */
	public static final String MESSAGE_INTERNAL_SERVER_ERROR = "Sever error";
	
	/* Skip Filter URL */
	public static final String SKIP_FILTER_URL_LOGIN = "/login";
	public static final String SKIP_FILTER_URL_USER = "/user";
}
