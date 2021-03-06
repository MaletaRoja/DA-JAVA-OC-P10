package com.formation.projet7.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.projet7.model.Login;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.model.UtilisateurAux;
import com.formation.projet7.security.JWTGenerator;
import com.formation.projet7.service.jpa.UserService;

@RestController
@RequestMapping("/biblio/")
public class TokenController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private JWTGenerator jwtGenerator;
	
	public TokenController(JWTGenerator jwtGenerator) {
		
		this.jwtGenerator = jwtGenerator;
	}
	
	@PostMapping("connexion/")
	public ResponseEntity<UtilisateurAux> generate(@RequestBody final Login login){
		
		System.out.println("**Entrée POST service");
		Utilisateur jwtUser = new Utilisateur();
		jwtUser = existUtilisateur(login);
		
		if (jwtUser != null) {
			
			UtilisateurAux userAux = new UtilisateurAux();
			userAux.setId(jwtUser.getId()); 
			userAux.setNom(jwtUser.getNom());
			userAux.setPrenom(jwtUser.getPrenom());
			userAux.setRole("USER");
			userAux.setUsername(jwtUser.getUsername());
			String token = jwtGenerator.generate(jwtUser);
			userAux.setToken(token);
			return new ResponseEntity<UtilisateurAux>(userAux, HttpStatus.OK);
					
		}else {
			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	private Utilisateur existUtilisateur(Login login) {
		
		System.out.println("Login user: " + login.getUser());
		System.out.println("Login user: " + login.getPassword());
		System.out.println("Login user: " + passwordEncoder.encode(login.getPassword()));
		
		try {
			
		Utilisateur utilisateur = userService.obtenirUserParlogin(login.getUser(), login.getPassword());
		utilisateur.setRole("Admin");
		System.out.println("Connexion réussion: " + utilisateur.getPrenom() + " " + utilisateur.getNom());
		return utilisateur;
		
		} catch (Exception e) {
			
			System.out.println("Utilisateur non identifié");
			return null;
		}

		
	}
	


}
