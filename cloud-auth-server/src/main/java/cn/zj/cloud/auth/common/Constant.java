package cn.zj.cloud.auth.common;

public class Constant {
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
	public static final String EMPTY_STRING = "";
}
