package com.formation.projet7.service;
import java.util.List;

import com.formation.projet7.model.Ouvrage;
public interface IOuvrageService {
	
	List<Ouvrage> listerOuvrages();
	Ouvrage obtenirOuvrage(Integer id);
	void modifierOuvrage(Ouvrage ouvrage);
	void supprimerOuvrage(Ouvrage ouvrage);
	void ajouterOuvrage(Ouvrage ouvrage);
	List<String> genres();
	List<Ouvrage> listerOuvragesParRubrique(String rubrique);
	

}
