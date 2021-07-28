package com.formation.projet7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.model.UtilisateurAux;
import com.formation.projet7.service.jpa.UserService;

@RestController
//@RequestMapping("/biblio")
public class UtilisateurController {

	@Autowired
	UserService userService;
	
	
	@GetMapping("/users")
	public List<Utilisateur> tousLesUtilisateurs(){
		
		List<Utilisateur> users = userService.listerUsers();
		return users;
		
	}
	
	@PostMapping("/users/{id}")
	public ResponseEntity<?> oneUser(@PathVariable Integer id) {
		
		Utilisateur user = userService.obtenirUser(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("compte/")
	public void creerCompte (@RequestBody UtilisateurAux user) {
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPrenom(user.getPrenom());
		utilisateur.setNom(user.getNom());
		utilisateur.setPassword(user.getToken());
		utilisateur.setRole(user.getRole());
		utilisateur.setUsername(user.getUsername());
		utilisateur.setEnabled(true);
		userService.ajouterUser(utilisateur);
		
	}
	
	@PutMapping("/modifier/compte/{id}")
	public void modifierCompte(@PathVariable  Integer id
			, @RequestHeader("Authorization") String token
			, @RequestBody UtilisateurAux utilisateurAux) {
		
		Utilisateur utilisateur = userService.obtenirUser(id);
		utilisateur.setPrenom(utilisateurAux.getPrenom());
		utilisateur.setNom(utilisateurAux.getNom());
		utilisateur.setUsername(utilisateurAux.getUsername());
		utilisateur.setPassword(utilisateurAux.getToken());
		userService.ajouterUser(utilisateur);
		
	}
	
}
