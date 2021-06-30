package com.formation.projet7.model;

public class ExemplaireDispo {
	
	private Integer ouvrageId;
	private String auteur_nom;
	private String auteur_prenom;
	private String edition;
	private String titre;
	private Integer exemplaire_id;
	
	public ExemplaireDispo() {
		
	}

	public ExemplaireDispo(Integer ouvrageId, String auteur_nom, String auteur_prenom, String edition, String titre,
			Integer exemplaire_id) {
		super();
		this.ouvrageId = ouvrageId;
		this.auteur_nom = auteur_nom;
		this.auteur_prenom = auteur_prenom;
		this.edition = edition;
		this.titre = titre;
		this.exemplaire_id = exemplaire_id;
	}
	
	public ExemplaireDispo(String auteur_nom, String auteur_prenom, String edition) {
		super();
		
		this.auteur_nom = auteur_nom;
		this.auteur_prenom = auteur_prenom;
		this.edition = edition;
		
	}

	public Integer getOuvrageId() {
		return ouvrageId;
	}

	public void setOuvrageId(Integer ouvrageId) {
		this.ouvrageId = ouvrageId;
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

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Integer getExemplaire_id() {
		return exemplaire_id;
	}

	public void setExemplaire_id(Integer exemplaire_id) {
		this.exemplaire_id = exemplaire_id;
	}


}
