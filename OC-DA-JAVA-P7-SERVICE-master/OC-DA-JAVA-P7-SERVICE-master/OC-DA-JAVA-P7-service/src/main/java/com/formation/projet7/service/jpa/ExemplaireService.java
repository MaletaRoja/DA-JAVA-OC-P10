package com.formation.projet7.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.ExemplaireDispo;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.repository.ExemplaireRepo;
import com.formation.projet7.service.IExemplaireService;

@Service
public class ExemplaireService implements IExemplaireService {

	@Autowired
	ExemplaireRepo exemplaireRepo;
	@Override
	public List<Exemplaire> listerExemplaires(Ouvrage ouvrage) {
		List<Exemplaire> exemplaire = exemplaireRepo.findAll();
		return exemplaire;
	}

	@Override
	public Exemplaire obtenirExemplaire(Integer id) {
		
		Exemplaire exemplaire = exemplaireRepo.getOne(id);
		return exemplaire;
	}

	@Override
	public void ajouterExemplaire(Exemplaire exemplaire) {
		exemplaireRepo.save(exemplaire);
		
	}

	@Override
	public void modifierExemplaire(Exemplaire exemplaire) {
		exemplaireRepo.save(exemplaire);
		
	}

	@Override
	public void supprimerExemplaire(Exemplaire exemplaire) {
		exemplaireRepo.delete(exemplaire);
		
	}
	
	
	@Override
	public List<Exemplaire> exemplairesDispos() {
		List <Exemplaire> exemplairesDispos = exemplaireRepo.findByDisponible(true);
		return exemplairesDispos;
	}

	@Override
	public List<Exemplaire> exemplairesDisposParOuvrage(Integer id) {
		List <Exemplaire> exemplairesDisposParOuvrage = exemplaireRepo.findByDisponibleAndOuvrageId(true, id);
		return exemplairesDisposParOuvrage;
	}

	

}
