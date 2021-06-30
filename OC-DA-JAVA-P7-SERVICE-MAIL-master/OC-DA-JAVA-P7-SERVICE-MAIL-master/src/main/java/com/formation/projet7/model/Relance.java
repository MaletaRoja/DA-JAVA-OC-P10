package com.formation.projet7.model;

import java.time.LocalDateTime;

public class Relance {
	
	private String emprunteur;
	private String email;
	private String titre;
	private String fin;
	
	public Relance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Relance(String emprunteur, String email, String titre, String fin) {
		super();
		this.emprunteur = emprunteur;
		this.email = email;
		this.titre = titre;
		this.fin = fin;
	}

	public String getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(String emprunteur) {
		this.emprunteur = emprunteur;
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

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	
}
