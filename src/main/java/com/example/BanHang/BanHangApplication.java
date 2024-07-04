package com.example.BanHang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.BanHang.aop.LogInterceptor;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class BanHangApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(BanHangApplication.class, args);
	}
	
	@Autowired
	LogInterceptor logInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor);
	}
}
