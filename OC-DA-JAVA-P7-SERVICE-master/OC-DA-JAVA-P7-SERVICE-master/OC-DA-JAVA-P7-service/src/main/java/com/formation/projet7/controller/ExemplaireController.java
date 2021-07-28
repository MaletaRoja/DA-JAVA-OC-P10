package com.formation.projet7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.ExemplaireDispo;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.service.jpa.ExemplaireService;
import com.formation.projet7.service.jpa.OuvrageService;
import com.formation.projet7.service.jpa.UserService;

@RestController
//@RequestMapping("/biblio")
public class ExemplaireController {
	
	@Autowired
	ExemplaireService exemplaireService;
	
	@Autowired
	OuvrageService ouvrageService;
	
	@GetMapping("/exemplaire/{id}")
	public ResponseEntity<?> tousLesExemplaires(@PathVariable Integer id){
		
		Ouvrage ouvrage = ouvrageService.obtenirOuvrage(id);
		List<Exemplaire> exemplaires = ouvrage.getExemplaires();
		return new ResponseEntity<>(exemplaires, HttpStatus.OK);
		
	}
	

	
	@GetMapping("/exemplaire/disponibles")
	public List<Exemplaire> ListerExemplairesDisponibles() {
		
		List<Exemplaire> listeExemplairesDisponibles = exemplaireService.exemplairesDispos();
		return listeExemplairesDisponibles;
	}
	
	@GetMapping("/exemplaire/disponibles/{id}")
	public List<Exemplaire> ListerExemplairesDisponiblesParOuvrage(@PathVariable Integer id) {
		
		List<Exemplaire> listeExemplairesDisponibles = exemplaireService.exemplairesDisposParOuvrage(id);
		return listeExemplairesDisponibles;
	}
	
}
