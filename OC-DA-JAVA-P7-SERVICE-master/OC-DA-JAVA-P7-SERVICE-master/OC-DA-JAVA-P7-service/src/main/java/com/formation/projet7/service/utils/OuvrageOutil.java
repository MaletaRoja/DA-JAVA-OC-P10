package com.formation.projet7.service.utils;

import java.util.ArrayList;
import java.util.List;

import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.Ouvrage;

public class OuvrageOutil {

	public int DenombreExDisponibles(Ouvrage ouvrage) {

		int nbreExemplairesDisponibles = 0;
		List<Exemplaire> exs = ouvrage.getExemplaires();
		for (Exemplaire ex : exs) {

			if (ex.isActif() && ex.isDisponible()) {

				nbreExemplairesDisponibles++;
			}
		}

		return nbreExemplairesDisponibles;
	}

	public int[] DenombreExDisponibles(List<Ouvrage> ouvrages) {

		int[] nbreExemplairesDisponibles = null;
		int i = 0;
		for (Ouvrage ouvrage: ouvrages) {
			
			int nbreExDispoParOuvrage = 0;
			List<Exemplaire> exs = ouvrage.getExemplaires();
			for (Exemplaire ex : exs) {

				if (ex.isActif() && ex.isDisponible()) {

					nbreExDispoParOuvrage++;
				}
			}
			
			nbreExemplairesDisponibles[i] = nbreExDispoParOuvrage;
			i++;
		}
		
		return nbreExemplairesDisponibles;
	}
	
	public List<Exemplaire> exemplairesLivrables(List<Exemplaire> exemplaires){
		
		List<Exemplaire> exDispos = new ArrayList<Exemplaire>();
		
		for (Exemplaire ex : exemplaires) {
			
			if (ex.isActif() && ex.isDisponible()) {
				
				exDispos.add(ex);
			}
		}
		
		return exDispos;
	}
	
public List<Exemplaire> exemplairesLivrables(Ouvrage ouvrage){
		
		List<Exemplaire> exemplaires = ouvrage.getExemplaires();
		List<Exemplaire> exDispos = new ArrayList<Exemplaire>();
		
		for (Exemplaire ex : exemplaires) {
			
			if (ex.isActif() && ex.isDisponible()) {
				
				exDispos.add(ex);
			}
		}
		
		return exDispos;
	}
}
