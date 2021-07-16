package com.formation.projet7.model.auxiliaire;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LigneEmprFormat {
	
	private Integer id;
	private String titre;
	private String auteur_nom;
	private String auteur_prenom;
	private String edition;
	private String genre;
	private String debut;
	private String fin;
	private boolean prolongation;
	private boolean prolongeable;
	private boolean actif;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public LigneEmprFormat() {
		
	}

	
	
	public LigneEmprFormat(Integer id, String titre, String auteur_nom, String auteur_prenom, String edition,
			String genre, String debut, String fin, boolean prolongation, boolean prolongeable, boolean actif,
			DateTimeFormatter formatter) {
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
		this.formatter = formatter;
	}



	public LigneEmprFormat(LigneEmprunt ligne) {
		
		this.id = ligne.getId();
		this.titre = ligne.getTitre();
		this.auteur_nom = ligne.getAuteur_nom();
		this.auteur_prenom = ligne.getAuteur_prenom();
		this.edition = ligne.getEdition();
		this.genre = ligne.getGenre();
		this.debut = ligne.getDebut().format(formatter);
		this.fin = ligne.getFin().format(formatter);
		this.prolongation = ligne.isProlongation();
		this.prolongeable= ligne.isProlongeable();
		this.actif = ligne.isActif();
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



	public boolean isProlongeable() {
		return prolongeable;
	}



	public void setProlongeable(boolean prolongeable) {
		this.prolongeable = prolongeable;
	}



	public DateTimeFormatter getFormatter() {
		return formatter;
	}



	public void setFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}
	
	

}


