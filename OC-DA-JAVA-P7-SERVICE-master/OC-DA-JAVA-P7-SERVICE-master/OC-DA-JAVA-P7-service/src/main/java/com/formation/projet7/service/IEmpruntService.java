package com.formation.projet7.service;

import java.util.List;

import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.Utilisateur;

public interface IEmpruntService {
	
	List<Emprunt> listerUserEmprunt(Utilisateur user);
	List<Emprunt> listerUserEmpruntActifs(Utilisateur user);
	List<Exemplaire> listerOuvrageEmprunts(Ouvrage ouvrage);
	void enregistrerEmprunt(String rubrique, Integer id, Utilisateur utilisateur);
	public void saveEmprunt(Emprunt emprunt);
	
	
}
