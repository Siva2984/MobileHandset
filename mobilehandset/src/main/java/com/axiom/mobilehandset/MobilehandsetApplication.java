package com.axiom.mobilehandset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MobilehandsetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobilehandsetApplication.class, args);
	}

}
