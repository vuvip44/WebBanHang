package com.example.BanHang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BanHangApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanHangApplication.class, args);
	}

}
