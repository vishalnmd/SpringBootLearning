package com.springboot.security.demo.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JWTUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);
	
	@Value("${spring.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${spring.app.jwtExpirations}")
	private int jwtExpirations;
	
	public String getJwtFromHeader(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");		
		logger.debug("Authorization Header : {} ", bearerToken);
		
		if(bearerToken!=null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);					
		}
		return null;
	}
	
	public String generateTokenFromUsername(UserDetails userDetails) {
		String username = userDetails.getUsername();
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpirations))
				.signWith(key())
				.compact();
	}
	
	public String getUsernameFromJWTToken(String token) {
		return Jwts.parserBuilder()
				 	.setSigningKey(key())
				 	.build()
				 	.parseClaimsJws(token)
				 	.getBody().getSubject();
	}
	
	public boolean validateJWTToken(String authToken) {
		try {
			System.out.println("Validate");
			Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken);			
			return true;
			
		} catch (MalformedJwtException e) {
			logger.error("Invalid jwt token : {}",e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("jwt token is expired : {}",e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("jwt token is unsupported : {}",e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("jwt claims string is empty : {}",e.getMessage());
		}
		
		return false;
	}
	
	
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	
	
	
	
	
	
	
}
