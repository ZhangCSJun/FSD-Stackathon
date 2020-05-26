package cn.zj.cloud.auth.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import cn.zj.cloud.constant.Constant;

@Component
public class JwtHandler {
	/**
	* Issue JWT Token
	* @param userId String
	* @return jwtToken
	*/
	public String issueToken(String userId){

		// create issue expire date
		long sysTime = System.currentTimeMillis();
		long expireTime = sysTime + (30L*60L*1000L);
		Date expiresAt = new Date(expireTime);
		
		// build token
		String token = JWT.create()
				.withIssuer(Constant.ISSUER_CONTENT)
				.withSubject(Constant.SUBJECT_CONTENT)
				.withAudience(Constant.AUDIENCE_CONTENT)
				.withClaim(Constant.CLAIM_USER_ID, userId)
				.withExpiresAt(expiresAt)
				.sign(Algorithm.HMAC256(Constant.SECRET_KEY));
		return token;
	}

	/**
	* verify JWT Token
	* @param userId String
	* @return jwtToken
	*/
	public String verifyToken(String token) throws Exception{

		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Constant.SECRET_KEY))
				.withIssuer(Constant.ISSUER_CONTENT)
				.withSubject(Constant.SUBJECT_CONTENT)
				.withAudience(Constant.AUDIENCE_CONTENT)
				.build();
		DecodedJWT jwt = verifier.verify(token);
		Map<String, Claim> claims = jwt.getClaims();
		String userId = claims.get(Constant.CLAIM_USER_ID).asString();

		return userId;
	}
	

}
