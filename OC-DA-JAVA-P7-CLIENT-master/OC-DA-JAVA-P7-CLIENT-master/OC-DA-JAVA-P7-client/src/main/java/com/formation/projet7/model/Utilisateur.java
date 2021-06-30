package com.formation.projet7.model;

import java.util.List;


public class Utilisateur {
	
	
	private Integer id;
	private String nom;
	private String prenom;
	private String username;
	private String password;
	private boolean enabled;
	
	
	private List<Profil> profils;
	
	
	private List<Emprunt> emprunts;
	
	
	private static final long serialVersionUID = 1L;


	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Utilisateur(Integer id, String nom, String prenom, String username, String password, boolean enabled,
			List<Profil> profils, List<Emprunt> emprunts) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.profils = profils;
		this.emprunts = emprunts;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public List<Profil> getProfils() {
		return profils;
	}


	public void setProfils(List<Profil> profils) {
		this.profils = profils;
	}


	public List<Emprunt> getEmprunts() {
		return emprunts;
	}


	public void setEmprunts(List<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}

}
