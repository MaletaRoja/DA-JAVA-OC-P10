package com.formation.projet7.model;

import java.time.LocalDateTime;

public class Avis {

	private Integer reservation; // id de la réservation
	private String utilisateur;
	private String email;
	private String titre;
	private String auteur;
	private String edition;
	private LocalDateTime dateReservation;
	private LocalDateTime dateAvis;
	private Integer exemplaire;

	public Avis() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Avis(Integer reservation, String utilisateur, String email, String titre, String auteur, String edition,
			LocalDateTime dateReservation, LocalDateTime dateAvis, Integer exemplaire) {
		super();
		this.reservation = reservation;
		this.utilisateur = utilisateur;
		this.email = email;
		this.titre = titre;
		this.auteur = auteur;
		this.edition = edition;
		this.dateReservation = dateReservation;
		this.dateAvis = dateAvis;
		this.exemplaire = exemplaire;
	}

	public Avis(Reservation reservation) {
		super();
		this.reservation = reservation.getId();
		this.utilisateur = reservation.getDemandeur().getPrenom() + reservation.getDemandeur().getNom();
		this.email = reservation.getDemandeur().getUsername();
		this.titre = reservation.getOuvrage().getTitre();
		this.auteur = reservation.getOuvrage().getAuteur_nom();
		this.edition = reservation.getOuvrage().getEdition();
		this.dateReservation = reservation.getDateDemande();
		this.dateAvis = reservation.getDateAvis();
		this.exemplaire = reservation.getExemplaire_id();
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public LocalDateTime getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(LocalDateTime dateReservation) {
		this.dateReservation = dateReservation;
	}

	public LocalDateTime getDateAvis() {
		return dateAvis;
	}

	public void setDateAvis(LocalDateTime dateAvis) {
		this.dateAvis = dateAvis;
	}

	public Integer getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Integer exemplaire) {
		this.exemplaire = exemplaire;
	}

	public Integer getReservation() {
		return reservation;
	}

	public void setReservation(Integer reservation) {
		this.reservation = reservation;
	}
	

}
