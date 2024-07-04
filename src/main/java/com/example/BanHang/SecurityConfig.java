package com.example.BanHang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	public void config(AuthenticationManagerBuilder auth) throws Exception {
//		fit cung
//		auth.inMemoryAuthentication()
//			.withUser("test")
//			.password(new BCryptPasswordEncoder().encode("1234"))
//			.authorities("ADMIN")
//			.and().passwordEncoder(new BCryptPasswordEncoder());
		
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public SecurityFilterChain config(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.requestMatchers("/admin/**")
			.hasAnyAuthority("ADMIN")
			.requestMatchers("/member/**")
			.authenticated()
			.anyRequest().permitAll()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
			.and().httpBasic()
			.and().csrf().disable();
		return http.build();
	}
}
