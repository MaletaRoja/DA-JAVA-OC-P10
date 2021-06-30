package com.formation.projet7.service.utils;

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


}
