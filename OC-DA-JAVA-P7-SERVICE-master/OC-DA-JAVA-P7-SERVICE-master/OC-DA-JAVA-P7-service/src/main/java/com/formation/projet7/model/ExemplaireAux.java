package com.formation.projet7.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class ExemplaireAux {

	private Integer id;
	private OuvrageAux ouvrageAux;
	//private EmpruntAux[] empruntsAux;
	private boolean disponible;
	private boolean actif;

	public ExemplaireAux() {

	}

	public ExemplaireAux(Integer id, OuvrageAux ouvrageAux,  boolean disponible,
			boolean actif) {
		
		this.id = id;
		this.ouvrageAux = ouvrageAux;
		//this.empruntsAux = empruntsAux;
		this.disponible = disponible;
		this.actif = actif;
	}
	
	public ExemplaireAux(Exemplaire exemplaire) {
		
		this.id = exemplaire.getId();
		this.ouvrageAux = new OuvrageAux(exemplaire.getOuvrage());
		//this.empruntsAux = exemplaire.getEmprunts().toArray(new EmpruntAux[exemplaire.getEmprunts().size()]);
		this.disponible = disponible;
		this.actif = actif;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OuvrageAux getOuvrageAux() {
		return ouvrageAux;
	}

	public void setOuvrageAux(OuvrageAux ouvrageAux) {
		this.ouvrageAux = ouvrageAux;
	}
/*
	public EmpruntAux[] getEmpruntsAux() {
		return empruntsAux;
	}

	public void setEmpruntsAux(EmpruntAux[] empruntsAux) {
		this.empruntsAux = empruntsAux;
	}
*/
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
