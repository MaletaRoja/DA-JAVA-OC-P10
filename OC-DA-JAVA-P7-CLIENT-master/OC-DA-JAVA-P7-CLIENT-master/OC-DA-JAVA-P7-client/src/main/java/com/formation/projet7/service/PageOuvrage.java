package com.formation.projet7.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.EmpruntFormat;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.OuvrageAux;
import com.formation.projet7.model.auxiliaire.LigneEmprFormat;
import com.formation.projet7.model.auxiliaire.LigneEmprunt;

@Service
public class PageOuvrage {

	public List<Integer> exemplairesDisposParOuvrage(List<OuvrageAux> ouvrages) {
		List<Integer> nbreExemplairesDispos = new ArrayList<Integer>();

		for (OuvrageAux ouvrage : ouvrages) {

			int disponibles = ouvrage.getOffrable();
			nbreExemplairesDispos.add(disponibles);
		}

		return nbreExemplairesDispos;
	}

	public EmpruntFormat formatEmprunt(Emprunt emprunt) {

		return new EmpruntFormat(emprunt);
	}

	public List<EmpruntFormat> formatListeEmprunts(List<Emprunt> emprunts) {

		List<EmpruntFormat> empruntsFormat = new ArrayList<EmpruntFormat>();
		for (Emprunt em : emprunts) {

			EmpruntFormat emFormat = formatEmprunt(em);
			empruntsFormat.add(emFormat);

		}
		return empruntsFormat;
	}

	public LigneEmprFormat formatLigneEmprunt(LigneEmprunt ligne) {

		return new LigneEmprFormat(ligne);
	}

	public List<LigneEmprFormat> formatListeLigneEmprunts(List<LigneEmprunt> emprunts) {

		List<LigneEmprFormat> empruntsFormat = new ArrayList<LigneEmprFormat>();
		for (LigneEmprunt em : emprunts) {

			LigneEmprFormat emFormat = formatLigneEmprunt(em);
			empruntsFormat.add(emFormat);

		}
		return empruntsFormat;
	}

}
