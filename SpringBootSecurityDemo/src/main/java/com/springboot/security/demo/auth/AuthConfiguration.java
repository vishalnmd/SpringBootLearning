package com.springboot.security.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.security.demo.jwt.AuthEntryPointJWT;
import com.springboot.security.demo.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private AuthEntryPointJWT unauthorizedHandler;
	
	@Bean
	AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	@SuppressWarnings("deprecation")
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable()
			.formLogin().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.requestMatchers("/h2-console/*").permitAll()
			.requestMatchers("/signin").permitAll()
			.requestMatchers("/loginUser").permitAll()
			.anyRequest()
			.authenticated();
		
		http.headers().frameOptions().disable();
		
		http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

//    @Bean
//    UserDetailsService userDetailsService() {           
//		UserDetails user = User.withUsername("admin")     
//								.password("admin")	
//								.build();
//		return new InMemoryUserDetailsManager(user);
//	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService);	
		
		return authProvider;
	}
	
    @Bean
	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
    	
    	return new BCryptPasswordEncoder(18);
	}
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
    	return builder.getAuthenticationManager();
    	}
}
