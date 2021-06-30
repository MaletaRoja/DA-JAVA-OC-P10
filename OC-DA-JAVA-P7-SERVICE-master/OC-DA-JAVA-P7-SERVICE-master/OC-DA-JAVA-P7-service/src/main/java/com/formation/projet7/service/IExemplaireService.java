package com.formation.projet7.service;

import java.util.List;

import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.ExemplaireDispo;
import com.formation.projet7.model.Ouvrage;

public interface IExemplaireService {
	
	List<Exemplaire> listerExemplaires(Ouvrage ouvrage);
	Exemplaire obtenirExemplaire(Integer id);
	void ajouterExemplaire(Exemplaire exemplaire);
	void modifierExemplaire(Exemplaire exemplaire);
	void supprimerExemplaire(Exemplaire exemplaire);
	//List<ExemplaireDispo> exemplairesDisponibles();
	List<Exemplaire> exemplairesDispos();
	List<Exemplaire> exemplairesDisposParOuvrage(Integer id);
	

}
