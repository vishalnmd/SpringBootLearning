package com.springboot.oauth.demo.auth;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.formLogin().disable()
			.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())		
			;
			
		
		return http.build();
	}
	
	/*
	 * @Bean JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
	 * return
	 * JwtDecoders.fromOidcIssuerLocation(properties.getJwt().getIssuerUri()); }
	 */
	
}
