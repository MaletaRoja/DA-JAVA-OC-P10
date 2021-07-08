package com.formation.projet7.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Utilisateur demandeur;		 // L'utilisateur qui réserve
	
	@ManyToOne
	private Ouvrage ouvrage;			 // Ouvrage demandé
	private LocalDateTime dateDemande;   // Date à laquelle la reservation est faite
	private LocalDateTime dateAvis;		 // Date du mail pour notifier la disponibilité de l'exemplaire réservé

	private Integer exemplaire_id;		 // Exemplaire attribué (devenu disponible) pour la réservation
	
	private boolean actif;               // Etat de la réservation. True: la réservation est valide
										 // False: la réservation a été annulée ou le prêt est devenu actif
	private Integer priorite; 			 // Ordre de priorité de la réservation
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(Integer id, Utilisateur demandeur, Ouvrage ouvrage, LocalDateTime dateDemande,
			LocalDateTime dateAvis, Integer exemplaire_id, boolean actif, Integer priorite) {
		super();
		this.id = id;
		this.demandeur = demandeur;
		this.ouvrage = ouvrage;
		this.dateDemande = dateDemande;
		this.dateAvis = dateAvis;
		this.exemplaire_id = exemplaire_id;
		this.actif = actif;
		this.priorite = priorite;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Utilisateur getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(Utilisateur demandeur) {
		this.demandeur = demandeur;
	}

	public Ouvrage getOuvrage() {
		return ouvrage;
	}

	public void setOuvrage(Ouvrage ouvrage) {
		this.ouvrage = ouvrage;
	}

	public LocalDateTime getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(LocalDateTime dateDemande) {
		this.dateDemande = dateDemande;
	}

	public LocalDateTime getDateAvis() {
		return dateAvis;
	}

	public void setDateAvis(LocalDateTime dateAvis) {
		this.dateAvis = dateAvis;
	}

	public Integer getExemplaire_id() {
		return exemplaire_id;
	}

	public void setExemplaire_id(Integer exemplaire_id) {
		this.exemplaire_id = exemplaire_id;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Integer getPriorite() {
		return priorite;
	}

	public void setPriorite(Integer priorite) {
		this.priorite = priorite;
	}

	
	
	
	
}
