package com.formation.projet7.model;

import java.util.List;

public class Transfert {
	
	private List<Avis> listeAvis;

	public Transfert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transfert(List<Avis> listeAvis) {
		super();
		this.listeAvis = listeAvis;
	}

	public List<Avis> getListeAvis() {
		return listeAvis;
	}

	public void setListeAvis(List<Avis> listeAvis) {
		this.listeAvis = listeAvis;
	}
	
	

}
