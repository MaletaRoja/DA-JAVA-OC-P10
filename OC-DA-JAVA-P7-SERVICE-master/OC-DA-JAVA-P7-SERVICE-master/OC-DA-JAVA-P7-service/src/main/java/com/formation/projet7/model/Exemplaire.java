package com.formation.projet7.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Exemplaire implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Ouvrage ouvrage;
	
	@OneToMany(mappedBy="exemplaire")
	private List<Emprunt> emprunts; 
	
	private boolean disponible;
	private boolean actif;
	
	private static final long serialVersionUID = 1L;
	
	public Exemplaire() {
		
	}

	public Exemplaire(Integer id, Ouvrage ouvrage, List<Emprunt> emprunts, boolean disponible, boolean actif) {
		super();
		this.id = id;
		this.ouvrage = ouvrage;
		this.emprunts = emprunts;
		this.disponible = disponible;
		this.actif = actif;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ouvrage getOuvrage() {
		return ouvrage;
	}

	public void setOuvrage(Ouvrage ouvrage) {
		this.ouvrage = ouvrage;
	}

	public List<Emprunt> getEmprunts() {
		return emprunts;
	}

	public void setEmprunts(List<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	
}
