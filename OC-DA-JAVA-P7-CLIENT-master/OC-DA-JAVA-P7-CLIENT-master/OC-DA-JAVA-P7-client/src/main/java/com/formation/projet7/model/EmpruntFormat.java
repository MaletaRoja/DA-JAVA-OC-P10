package com.formation.projet7.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmpruntFormat {
	
	private Integer id;
	private Exemplaire exemplaire;
	private Utilisateur emprunteur;
	private String debut;
	private String fin;
	private boolean prolongation; 
	private boolean actif;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public EmpruntFormat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpruntFormat(Integer id, Exemplaire exemplaire, Utilisateur emprunteur, String debut, String fin,
			boolean prolongation, boolean actif) {
		
		this.id = id;
		this.exemplaire = exemplaire;
		this.emprunteur = emprunteur;
		this.debut = debut;
		this.fin = fin;
		this.prolongation = prolongation;
		this.actif = actif;
	}
	
	public EmpruntFormat(Emprunt emprunt) {
		
		this.id = emprunt.getId();
		this.exemplaire = emprunt.getExemplaire();
		this.emprunteur = emprunt.getEmprunteur();
		this.debut = emprunt.getDebut().format(formatter);
		this.fin = emprunt.getFin().format(formatter);
		this.prolongation = emprunt.isProlongation();
		this.actif = emprunt.isActif();
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public Utilisateur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Utilisateur emprunteur) {
		this.emprunteur = emprunteur;
	}

	public String getDebut() {
		return debut;
	}

	public void setDebut(String debut) {
		this.debut = debut;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public boolean isProlongation() {
		return prolongation;
	}

	public void setProlongation(boolean prolongation) {
		this.prolongation = prolongation;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
	
	
	
	

}
