package com.formation.projet7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.formation.projet7")
public class OcDaJavaP7Application {

	public static void main(String[] args) {
		SpringApplication.run(OcDaJavaP7Application.class, args);
	}

}