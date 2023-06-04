//package com.shincha.ddh.util;
//
//import java.io.UnsupportedEncodingException;
//
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtUtil {
//	private static final String SALT = "SHINCHA";
//
//	public String createToken(String claim, String id) throws UnsupportedEncodingException {
//		return Jwts.builder().setHeaderParam("algo", "HS256").setHeaderParam("type", "JWT").claim(claim, id)
//				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8")).compact();
//	}
//
//	public void valid(String token) throws Exception {
//		Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
//	}
//
//}
