package com.formation.projet7.model;

import java.time.LocalDateTime;


public class ReservationAux {
	
	private Integer id;
	private Integer demandeur;
	private Integer ouvrage;
	private LocalDateTime dateDemande;
	private LocalDateTime dateAvis;
	private Integer exemplaire_id;	
	private boolean actif;
	private Integer priorite;
	
	public ReservationAux() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public ReservationAux(Integer id, Integer demandeur, Integer ouvrage, LocalDateTime dateDemande,
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

	

	public Integer getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(Integer demandeur) {
		this.demandeur = demandeur;
	}

	
	public Integer getOuvrage() {
		return ouvrage;
	}



	public void setOuvrage(Integer ouvrage) {
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
