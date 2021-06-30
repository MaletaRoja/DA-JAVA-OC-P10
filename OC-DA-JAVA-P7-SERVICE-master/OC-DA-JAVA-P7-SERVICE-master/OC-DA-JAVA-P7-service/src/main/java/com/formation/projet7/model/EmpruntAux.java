package com.formation.projet7.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class EmpruntAux {

	private String rubrique;
	private Integer numero;
	private Integer idUser;
	
	
	public EmpruntAux() {
	
	}


	public EmpruntAux(String rubrique, Integer numero, Integer idUser) {
		super();
		this.rubrique = rubrique;
		this.numero = numero;
		this.idUser = idUser;
	}


	public String getRubrique() {
		return rubrique;
	}


	public void setRubrique(String rubrique) {
		this.rubrique = rubrique;
	}


	public Integer getNumero() {
		return numero;
	}


	public void setNumero(Integer numero) {
		this.numero = numero;
	}


	public Integer getIdUser() {
		return idUser;
	}


	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
}
