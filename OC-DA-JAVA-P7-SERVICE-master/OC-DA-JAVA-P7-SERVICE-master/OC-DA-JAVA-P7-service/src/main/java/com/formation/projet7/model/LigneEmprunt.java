package com.formation.projet7.model;

import java.time.LocalDateTime;

public class LigneEmprunt {
	
	private Integer id;
	private String titre;
	private String auteur_nom;
	private String auteur_prenom;
	private String edition;
	private String genre;
	private LocalDateTime debut;
	private LocalDateTime fin;
	private boolean prolongation;
	private boolean prolongeable;
	private boolean actif;
	
	public LigneEmprunt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LigneEmprunt(Integer id, String titre, String auteur_nom, String auteur_prenom, String edition, String genre,
			LocalDateTime debut, LocalDateTime fin, boolean prolongation, boolean prolongeable, boolean actif) {
		super();
		this.id = id;
		this.titre = titre;
		this.auteur_nom = auteur_nom;
		this.auteur_prenom = auteur_prenom;
		this.edition = edition;
		this.genre = genre;
		this.debut = debut;
		this.fin = fin;
		this.prolongation = prolongation;
		this.prolongeable = prolongeable;
		this.actif = actif;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur_nom() {
		return auteur_nom;
	}

	public void setAuteur_nom(String auteur_nom) {
		this.auteur_nom = auteur_nom;
	}

	public String getAuteur_prenom() {
		return auteur_prenom;
	}

	public void setAuteur_prenom(String auteur_prenom) {
		this.auteur_prenom = auteur_prenom;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public LocalDateTime getDebut() {
		return debut;
	}

	public void setDebut(LocalDateTime debut) {
		this.debut = debut;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}

	public boolean isProlongation() {
		return prolongation;
	}

	public void setProlongation(boolean prolongation) {
		this.prolongation = prolongation;
	}

	public boolean isProlongeable() {
		return prolongeable;
	}

	public void setProlongeable(boolean prolongeable) {
		this.prolongeable = prolongeable;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	
}
