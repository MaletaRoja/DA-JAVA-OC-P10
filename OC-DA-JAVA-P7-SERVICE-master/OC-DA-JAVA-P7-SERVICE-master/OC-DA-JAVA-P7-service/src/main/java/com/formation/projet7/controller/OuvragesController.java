package com.formation.projet7.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.EmpruntAuxMail;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.OuvrageAux;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.repository.OuvrageRepo;
import com.formation.projet7.repository.UserRepo;
import com.formation.projet7.service.jpa.EmpruntService;
import com.formation.projet7.service.jpa.OuvrageService;

@RestController
@RequestMapping("/biblio")
public class OuvragesController {

	@Autowired
	OuvrageService ouvrageService;

	@Autowired
	UserRepo userRepo;

	@Autowired
	OuvrageRepo ouvrageRepo;

	@Autowired
	EmpruntService empruntService;

	@GetMapping("/ouvrage/liste/{idUser}")
	public List<OuvrageAux> tousLesOuvrages(@RequestHeader("Authorization") String token,
			@PathVariable Integer idUser) {

		System.out.println("Méthode : touslesOuvrages(); Id user: " + idUser);
		List<Ouvrage> ouvrages = ouvrageService.listerOuvrages();
		List<OuvrageAux> listeOuvragesAux = new ArrayList<OuvrageAux>();
		Utilisateur user = userRepo.getOne(idUser);
		System.out.println("Id user récupéré: " + user.getId());
		List<Emprunt> empruntsActifs = empruntService.listerUserEmpruntActifs(user);
		listeOuvragesAux = isReservable(ouvrages, empruntsActifs);

		return listeOuvragesAux;
	}

	@GetMapping("/ouvrage/{id}")
	public ResponseEntity<?> unOuvrage(@PathVariable Integer id, @RequestHeader("Authorization") String token) {

		Ouvrage ouvrage = ouvrageService.obtenirOuvrage(id);
		return new ResponseEntity<>(ouvrage, HttpStatus.OK);
	}

	@GetMapping("/ouvrage/rubriques")
	public List<String> toutesLesRubriques(@RequestHeader("Authorization") String token) {

		List<String> genres = ouvrageService.genres();
		return genres;
	}

	@GetMapping("/ouvrage/liste/rubrique/{rubrique}")
	public List<OuvrageAux> tousLesOuvragesParRubrique(@PathVariable String rubrique,
			@RequestHeader("Authorization") String token) {
		List<Ouvrage> ouvrages = ouvrageService.listerOuvragesParRubrique(rubrique);
		List<OuvrageAux> ouvragesAux = ouvrageService.obtenirOuvragesAux(ouvrages);
		return ouvragesAux;
	}

	@GetMapping("/ouvrage/emprunts/mail")
	public List<EmpruntAuxMail> obtenirEmpruntsActif(@RequestHeader("Authorization") String token) {

		LocalDateTime date = LocalDateTime.now();
		List<EmpruntAuxMail> empruntsAux = empruntService.obtenirEmpruntActifParDate(date);
		System.out.println("Taille liste des emprunts envoyés au service mail: " + empruntsAux.size());
		return empruntsAux;
	}

	@GetMapping("/recherche/simple/{phrase}")
	public List<OuvrageAux> rechercheSimple(@RequestHeader("Authorization") String token, @PathVariable String phrase) {

		System.out.println("Phrase: " + phrase);
		List<OuvrageAux> ouvrages = ouvrageService.rechercherSimple(phrase);

		System.out.println("Taille liste ouvrages: " + ouvrages.size());
		for (OuvrageAux o : ouvrages) {

			System.out.println("Titre: " + o.getTitre());
		}

		return ouvrages;
	}

	private List<OuvrageAux> isReservable(List<Ouvrage> ouvrages, List<Emprunt> emprunts) {

		List<OuvrageAux> ouvragesAux = new ArrayList();
		System.out.println("Taille emprunts: " + emprunts.size());
		boolean match = false;
		
		List<Integer> idEmprunts = new ArrayList<>();

		for (Emprunt e : emprunts) {

			Ouvrage oEmprunt = e.getExemplaire().getOuvrage();
			Integer idOuvrageEmprunt = oEmprunt.getId();
			System.out.println("e: " + idOuvrageEmprunt + " - id e:" + e.getId());
			idEmprunts.add(idOuvrageEmprunt);

		}
		
		int i = 0;
		for (Ouvrage ouvrage : ouvrages) {

			i++;
			OuvrageAux o = new OuvrageAux(ouvrage);
			System.out.println("o" + i +": " + o.getId());
			ouvragesAux.add(o);

		}
		
		for (OuvrageAux o: ouvragesAux) {
			
			Integer idOuv = o.getId();
			if(idEmprunts.contains(idOuv) || o.getOffrable() != 0) {
				
				o.setReservable(false);
				System.out.println("id o extrait: " + idOuv);
				
			}
			
			if (!idEmprunts.contains(idOuv) && o.getOffrable() == 0){
				
				o.setReservable(true);
			}
			
		}

		return ouvragesAux;

	}

}
