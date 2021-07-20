package com.formation.projet7.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.projet7.controller.MailController;
import com.formation.projet7.model.Login;
import com.formation.projet7.proxy.MicroServiceBibliotheque;

@Service
public class ReservationService {
	

	@Autowired
	MicroServiceBibliotheque microServiceBibliotheque;

	@Autowired
	UserConnexion userConnexion;

	@Autowired
	MailController mailController;
	
	public void expedierAvis() {
		
		Login login = new Login("mailservice", "mail");

		String token = userConnexion.identifierUtilisateur(login);
		System.out.println("Heure actuelle: " + new Date());

		System.out.println("chaine token dans scheduler: " + token);
		
		
		
		
	}

}
