package com.formation.projet7.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import org.aspectj.apache.bcel.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.formation.projet7.controller.MailController;
import com.formation.projet7.model.Login;
import com.formation.projet7.proxy.MicroServiceBibliotheque;
import com.formation.projet7.model.Avis;
import java.util.List;

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

		List<Avis> listeAvis = microServiceBibliotheque.obtenirReservationsActives(token);
		System.out.println("Taille liste avis: " + listeAvis.size());
		List<Avis> avisDepasses = new ArrayList<>();
		List<Avis> avisDates = new ArrayList<>();
		
		try {

			for (Avis a : listeAvis) {

				/*
				String texte = "Bonjour M,Mme " + a.getUtilisateur() + "\n\n" + "Nous vous informons que l'ouvrage:\n\n"
						+ a.getTitre() + "\n" + a.getAuteur() + "\n" + "Edition: " + a.getEdition() + "\n\n"
						+ "que vous avez réservé le " + a.getDateReservation() + " "
						+ "est à votre disposition à la bibliothèque municipale " + "jusqu" + "\'" + "au "
						+ a.getDateReservation().plusDays(2) + "\n\n"
						+ "Passé ce délai, votre réservation sera supprimer et le livre sera proposé à une autre personne."
						+ "\n\n" + "Cordiales salutations" + "\n\n" + "Le responsable de la bibliothèque municipale.";

				mailController.sendSimpleEmail(a.getEmail(), "Avis de réservation", texte);
				*/
				
				if(a.getExemplaire() != null) {
					
					LocalDateTime dateAvis = a.getDateAvis();
					if (dateAvis == null) {	
						envoyerAvis(a);
						a.setDateAvis(LocalDateTime.now());
						avisDates.add(a);
						
					}else {
						
						if(dateAvis.plusDays(com.formation.projet7.Constants.Constants.DELAY_AVIS).isAfter(LocalDateTime.now())) {
							
							avisDepasses.add(a);
						}
					}
				}
				
				supprimerAvisDepasses(token, avisDepasses);
				ajouterDates(token, avisDates);
				
			}
		} catch (Exception e) {

			// TODO: handle exception
		}

	}

	private void ajouterDates(String token, List<Avis> avisDates) {
		
		microServiceBibliotheque.ajouterDatesAvisMaill(token, avisDates);
		
	}

	private void supprimerAvisDepasses(String token, List<Avis> avisDepasses) {
		
		microServiceBibliotheque.supprimerReservationMail(token, avisDepasses);
		
	}

	public void envoyerAvis(Avis a) {

		String texte = "Bonjour M,Mme " + a.getUtilisateur() + "\n\n" + "Nous vous informons que l'ouvrage:\n\n"
				+ a.getTitre() + "\n" + a.getAuteur() + "\n" + "Edition: " + a.getEdition() + "\n\n"
				+ "que vous avez réservé le " + a.getDateReservation() + " "
				+ "est à votre disposition à la bibliothèque municipale " + "jusqu" + "\'" + "au "
				+ a.getDateReservation().plusDays(2) + "\n\n"
				+ "Passé ce délai, votre réservation sera supprimer et le livre sera proposé à une autre personne."
				+ "\n\n" + "Cordiales salutations" + "\n\n" + "Le responsable de la bibliothèque municipale.";

		mailController.sendSimpleEmail(a.getEmail(), "Avis de réservation", texte);

	}

}
