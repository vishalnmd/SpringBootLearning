package com.springboot.signUp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig{
	
	@Autowired
	private MyUserDetailsService userDetailService;		
	
	@SuppressWarnings("deprecation")
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.requestMatchers("/signUp","/login")
				.permitAll()
//				.requestMatchers("/public/**").hasRole("employee")
				.anyRequest()				
				.authenticated()
				.and()
				.httpBasic();
		
		return http.build();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails admin = User.withUsername("admin")
//								.password(this.passwordEncoder().encode("admin"))
//								.roles("admin")
//								.build();
//		
//		UserDetails employee = User.withUsername("employee")
//									.password(this.passwordEncoder().encode("employee"))
//									.roles("employee")
//									.build();
//		
//		return new InMemoryUserDetailsManager(admin,employee);
//	}
    
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(this.passwordEncoder());
		provider.setUserDetailsService(userDetailService);
		
		return provider;
	}      
    
  /*  @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
    	return auth.getAuthenticationManager();
    }*/
		
	@Bean
	PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder(12);
	}
}
