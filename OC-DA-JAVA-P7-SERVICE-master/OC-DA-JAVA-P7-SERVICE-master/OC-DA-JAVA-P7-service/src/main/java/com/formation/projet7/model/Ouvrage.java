package com.formation.projet7.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Ouvrage implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String titre;
	private String auteur_nom;
	private String auteur_prenom;
	private String edition;
	private String genre;
	@JsonIgnore
	@OneToMany(mappedBy="ouvrage")
	private List<Exemplaire> exemplaires = new ArrayList<Exemplaire>();
	
	private static final long serialVersionUID = 1L;
	
	public Ouvrage() {
		
	}

	public Ouvrage(Integer id, String titre, String auteur_nom, String auteur_prenom, String edition, String genre,
			List<Exemplaire> exemplaires) {
		super();
		this.id = id;
		this.titre = titre;
		this.auteur_nom = auteur_nom;
		this.auteur_prenom = auteur_prenom;
		this.edition = edition;
		this.genre = genre;
		this.exemplaires = exemplaires;
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

	public List<Exemplaire> getExemplaires() {
		return exemplaires;
	}

	public void setExemplaires(List<Exemplaire> exemplaires) {
		this.exemplaires = exemplaires;
	}


}
