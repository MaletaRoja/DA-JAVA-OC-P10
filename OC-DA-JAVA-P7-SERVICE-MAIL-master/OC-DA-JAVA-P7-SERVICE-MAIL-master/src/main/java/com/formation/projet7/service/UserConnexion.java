package com.formation.projet7.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.formation.projet7.model.Login;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.model.UtilisateurAux;
import com.formation.projet7.proxy.MicroServiceBibliotheque;

@Service
public class UserConnexion {
	
	@Autowired
	MicroServiceBibliotheque microServiceBibliotheque;
	
	
	public String identifierUtilisateur(Login login) {
		
		System.out.println("Username: " + login.getUser());
		System.out.println("password: " + login.getPassword());
		//ResponseEntity<String> tokenBody = microServiceOuvrages.generate(login);
		ResponseEntity<UtilisateurAux> userBody = microServiceBibliotheque.generate(login);
		//String token = tokenBody.getBody();
		UtilisateurAux userAux = userBody.getBody();
		System.out.println("Token: " + userAux.getToken() );
		System.out.println("Nom de l'utilisateur récupéré: " + userAux.getNom());
		
		
		String token = "Bearer " + userAux.getToken();
		System.out.println("Chaine token: " + token);
		
		return token;
		
	}
	
	public Utilisateur obtenirUtilisateur (HttpSession session, Model model) {
		
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("USER");
		if (utilisateur == null) {
			
			System.out.println("aucun utilisateur");
			model.addAttribute("authentification", false);
			
		}else {
			
			System.out.println("Nom de utilisateur session: " + utilisateur.getNom());
			model.addAttribute("utilisateur", utilisateur);
			model.addAttribute("authentification", true);
			
		}
		return utilisateur;
		
	}

}
