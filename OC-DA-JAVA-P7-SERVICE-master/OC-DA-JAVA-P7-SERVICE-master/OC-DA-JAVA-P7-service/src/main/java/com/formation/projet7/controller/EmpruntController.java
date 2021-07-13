package com.formation.projet7.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.EmpruntAux;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.model.LigneEmprunt;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.service.jpa.EmpruntService;
import com.formation.projet7.service.jpa.ExemplaireService;
import com.formation.projet7.service.jpa.UserService;
import com.formation.projet7.constants.Constants;


@RestController
@RequestMapping("/biblio")
public class EmpruntController {

	@Autowired
	EmpruntService empruntService;

	@Autowired
	UserService userService;
	
	@Autowired
	ExemplaireService exemplaireService;

	@PostMapping("/emprunts/{id}")
	public ResponseEntity<?> tousLesEmprunts(@PathVariable Integer id) {

		Utilisateur user = userService.obtenirUser(id);
		List<Emprunt> emprunts = empruntService.listerUserEmprunt(user);
		return new ResponseEntity<>(emprunts, HttpStatus.OK);

	}

	@PutMapping("/emprunts/save")
	public void enregistrerEmprunt(@RequestBody EmpruntAux empruntAux) {

		empruntService.enregistrerEmprunt(empruntAux);
	}

	@GetMapping("/ouvrage/emprunts/actifs/{id}")
	public List<LigneEmprunt> empruntsActifs(@PathVariable Integer id) {

		Utilisateur utilisateur = userService.obtenirUser(id);
		List<Emprunt> emprunts = empruntService.listerUserEmpruntActifs(utilisateur);
		List<LigneEmprunt> tabEmprunts = new ArrayList<LigneEmprunt>();

		for (Emprunt em : emprunts) {

			Exemplaire ex = em.getExemplaire();
			Ouvrage o = ex.getOuvrage();
			LigneEmprunt ligne = new LigneEmprunt();
			ligne.setId(em.getId());
			ligne.setActif(em.isActif());
			ligne.setProlongation(em.isProlongation());
			ligne.setTitre(o.getTitre());
			ligne.setAuteur_nom(o.getAuteur_nom());
			ligne.setAuteur_prenom(o.getAuteur_prenom());
			ligne.setEdition(o.getEdition());
			ligne.setGenre(o.getGenre());
			ligne.setDebut(em.getDebut());
			ligne.setFin(em.getFin());
			tabEmprunts.add(ligne);

		}

		return tabEmprunts;
	}

	@GetMapping("/ouvrage/emprunts/hist/{id}")
	public List<LigneEmprunt> empruntsHist(@PathVariable Integer id) {

		Utilisateur utilisateur = userService.obtenirUser(id);
		List<Emprunt> emprunts = empruntService.listerUserEmprunt(utilisateur);
		List<LigneEmprunt> tabEmprunts = new ArrayList<LigneEmprunt>();

		for (Emprunt em : emprunts) {

			Exemplaire ex = em.getExemplaire();
			Ouvrage o = ex.getOuvrage();
			LigneEmprunt ligne = new LigneEmprunt();
			ligne.setId(em.getId());
			ligne.setActif(em.isActif());
			ligne.setProlongation(em.isProlongation());
			ligne.setTitre(o.getTitre());
			ligne.setAuteur_nom(o.getAuteur_nom());
			ligne.setAuteur_prenom(o.getAuteur_prenom());
			ligne.setEdition(o.getEdition());
			ligne.setGenre(o.getGenre());
			ligne.setDebut(em.getDebut());
			ligne.setFin(em.getFin());
			tabEmprunts.add(ligne);
		}

		return tabEmprunts;
	}
	
	@GetMapping("/prolonger/{id}")
	boolean prolonger(@PathVariable  Integer id) {
		
		Emprunt emprunt = empruntService.obtenirEmpruntParId(id);	
		LocalDateTime fin = emprunt.getFin();
		LocalDateTime dateActuelle = LocalDateTime.now();
		if(dateActuelle.isBefore(fin)) {
			
			fin = fin.plus(Constants.PROLONGEMENT_MIN, ChronoUnit.MINUTES);
			emprunt.setFin(fin);
			emprunt.setProlongation(true);
			 empruntService.saveEmprunt(emprunt);
			 return true;
			 
		}else {
			
			return false;
		}
		
	}
	
	@GetMapping("/retourner/{id}")
	void retourner(@PathVariable  Integer id) {
		
		Emprunt emprunt = empruntService.obtenirEmpruntParId(id);
		emprunt.setActif(false);
	
		Exemplaire exemplaire = emprunt.getExemplaire();
		exemplaire.setDisponible(true);
		exemplaireService.modifierExemplaire(exemplaire);
		
		
	}
	
	// test récupération d'un emprunt par méthode JPA persistance
	
	@GetMapping("/get")
	void recup() {
		
		Emprunt emp = empruntService.trouveEmprunt(35);
		System.out.println("id :" + emp.getId());
		System.out.println("actif: " + emp.isActif());
		System.out.println("prolongation: " + emp.isProlongation());
		System.out.println("début: " + emp.getDebut());
		System.out.println("fin: " + emp.getFin());
		System.out.println("exemplaire : " + emp.getExemplaire().getId());
		
	}
	
}
