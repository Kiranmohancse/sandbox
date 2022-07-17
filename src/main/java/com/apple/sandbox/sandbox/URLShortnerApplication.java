package com.apple.sandbox.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.apple.sandbox.sandbox")
public class URLShortnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(URLShortnerApplication.class, args);
	}

}
