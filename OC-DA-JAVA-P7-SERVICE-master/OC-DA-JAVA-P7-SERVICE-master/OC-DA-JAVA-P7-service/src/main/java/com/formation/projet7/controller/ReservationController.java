package com.formation.projet7.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.projet7.constants.Constants;
import com.formation.projet7.model.Avis;
import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.EmpruntAuxMail;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.OuvrageAux;
import com.formation.projet7.model.Reservation;
import com.formation.projet7.model.Transfert;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.service.jpa.EmpruntService;
import com.formation.projet7.service.jpa.ExemplaireService;
import com.formation.projet7.service.jpa.OuvrageService;
import com.formation.projet7.service.jpa.ReservationService;
import com.formation.projet7.service.jpa.UserService;

@RestController
@RequestMapping("/biblio")

public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@Autowired
	OuvrageService ouvrageService;

	@Autowired
	UserService userService;

	@Autowired
	EmpruntService empruntService;
	
	@Autowired
	ExemplaireService exemplaireService;
	

	@GetMapping("/reservations/{idUser}")
	public List<OuvrageAux> listerReservations(@RequestHeader("Authorization") String token,
			@PathVariable Integer idUser) {

		List<Reservation> reservations = reservationService.obtenirListeReservationsParUtilisateur(idUser);
		List<OuvrageAux> ouvrages = new ArrayList<>();

		for (Reservation r : reservations) {

			Ouvrage ouvrage = r.getOuvrage();
			OuvrageAux o = new OuvrageAux();
			o.setId(ouvrage.getId());
			o.setAuteur_nom(ouvrage.getAuteur_nom());
			o.setAuteur_prenom(ouvrage.getAuteur_prenom());
			o.setEdition(ouvrage.getEdition());
			o.setGenre(ouvrage.getGenre());
			o.setTitre(ouvrage.getTitre());
			o.setPriorite(r.getPriorite());
			List<Emprunt> empruntsActifs = empruntService.listerOuvrageEmpruntsActifs(ouvrage);
			List<LocalDateTime> retours = new ArrayList<>();
			for (Emprunt e : empruntsActifs) {

				System.out.println("date fin emprunt: " + e.getFin());
				retours.add(e.getFin());
				Collections.sort(retours);
				o.setRetour(retours.get(retours.size() - 1));
			}

			for (OuvrageAux ouv : ouvrages) {

				Integer idO = ouv.getId();
				Integer priorite = reservationService.isReservationPossible(idO);
				ouv.setPriorite(priorite);
			}

			ouvrages.add(o);
		}

		return ouvrages;
	}

	@GetMapping("/reserver/{idUser}/{idOuvrage}")
	public boolean reserverOuvrage(@RequestHeader("Authorization") String token, @PathVariable Integer idUser,
			@PathVariable Integer idOuvrage) {

		List<Reservation> reservations = reservationService.obtenirListeReservationsParOuvrage(idOuvrage);

		Ouvrage ouvrage = ouvrageService.obtenirOuvrage(idOuvrage);
		System.out.println("Id ouvrage: " + ouvrage.getId());
		List<Exemplaire> exemplaires = ouvrage.getExemplaires();

		int priorite = reservationService.isReservationPossible(idOuvrage);
		System.out.println("priorité retour isReservationPossible: " + priorite);
		if (priorite != -1) {

			Reservation reservation = new Reservation();
			reservation.setActif(true);
			reservation.setDateDemande(LocalDateTime.now());
			Utilisateur demandeur = userService.obtenirUser(idUser);
			reservation.setDemandeur(demandeur);
			reservation.setOuvrage(ouvrage);
			reservation.setPriorite(priorite);
			reservationService.enregistrerReservation(reservation);
			return true;
		}

		return false;

	}

	@GetMapping("/reservation/annuler/{idUser}/{idOuvrage}")
	void annulerReservation(@RequestHeader("Authorization") String token, @PathVariable Integer idUser,
			@PathVariable Integer idOuvrage) {

		reservationService.annulerReservation(idUser, idOuvrage);
	}

	@GetMapping("/reservations/mail")
	public List<Avis> obtenirReservationasActives(@RequestHeader("Authorization") String token) {

		List<Reservation> reservations = reservationService.obtenirReservationsActives();
		List<Avis> tousLesAvis = new ArrayList<>();
		for (Reservation r : reservations) {

			Avis a = new Avis(r);
			tousLesAvis.add(a);
		}

		return tousLesAvis;
	}

	@PostMapping("/reservations/supprimer/mail")
	public void supprimerReservationMail(@RequestHeader("Authorization") String token,
			@RequestBody Transfert transfertAvisDepasses) {
		
		List<Avis> listeAvis = transfertAvisDepasses.getListeAvis();
		for (Avis a : listeAvis) {

			Reservation r = reservationService.obtenirReservationParId(a.getReservation());
			r.setActif(false);
			Integer idExemplaire = r.getExemplaire_id();
			r.setExemplaire_id(null);
			r.setPriorite(null);
			Exemplaire ex = exemplaireService.obtenirExemplaire(idExemplaire);
			Ouvrage ouvrage = ex.getOuvrage();
			List<Reservation> reservations = ouvrage.getReservations();
			reservationService.rotationReservations(reservations, ex);
			reservationService.enregistrerReservation(r);
		}

	}

	@PostMapping("/reservations/avis/dater/mail")
	public void ajouterDatesAvisMail(@RequestHeader("Authorization") String token, @RequestBody Transfert transfertAvisDates) {

		List<Avis> listeAvis = transfertAvisDates.getListeAvis();
		for (Avis a : listeAvis) {
			
			Reservation r = reservationService.obtenirReservationParId(a.getReservation());
			LocalDateTime dateAvis = a.getDateAvis();
			r.setDateAvis(dateAvis);
			reservationService.enregistrerReservation(r);
			
		}
	}

}
