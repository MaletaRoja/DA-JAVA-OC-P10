package com.formation.projet7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.formation.projet7.Constants.Constants;
import com.formation.projet7.controller.MailController;
import com.formation.projet7.proxy.MicroServiceBibliotheque;
import com.formation.projet7.service.EmpruntService;
import com.formation.projet7.service.UserConnexion;

@EnableFeignClients("com.formation.projet7")
@SpringBootApplication
public class OcDaJavaP7ServiceMailApplication {

	@Autowired
	MicroServiceBibliotheque microServiceBibliotheque;

	@Autowired
	UserConnexion userConnexion;

	@Autowired
	MailController mailController;
	
	@Autowired
	EmpruntService empruntService;

	public static void main(String[] args) {
		SpringApplication.run(OcDaJavaP7ServiceMailApplication.class, args);
	}

	@Scheduled(initialDelay = 1000L, fixedDelay = 10000L)
	void scrutation() throws InterruptedException {
		
		empruntService.relancer();
		Thread.sleep(1000L * 60 * 60 * Constants.HEURES); 

	}

	@Configuration
	@EnableScheduling
	@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
	class SchedulingConfiguration {

	}

}
