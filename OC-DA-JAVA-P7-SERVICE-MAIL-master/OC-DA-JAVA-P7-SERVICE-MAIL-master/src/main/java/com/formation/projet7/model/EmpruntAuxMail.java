package com.formation.projet7.model;

import java.time.LocalDateTime;

public class EmpruntAuxMail {
	
	private Integer id;
	private String emprunteur;
	private String email;
	private String titre;
	private LocalDateTime debut;
	private LocalDateTime fin;
	
	public EmpruntAuxMail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpruntAuxMail(Integer id, String emprunteur, String email, String titre, LocalDateTime debut,
			LocalDateTime fin) {
		super();
		this.id = id;
		this.emprunteur = emprunteur;
		this.email = email;
		this.titre = titre;
		this.debut = debut;
		this.fin = fin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
}
