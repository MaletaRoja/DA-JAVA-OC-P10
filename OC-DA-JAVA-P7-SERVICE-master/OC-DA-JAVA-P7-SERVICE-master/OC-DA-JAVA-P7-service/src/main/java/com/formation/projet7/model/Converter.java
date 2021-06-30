package com.formation.projet7.model;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Converter implements IConverter {
	
	public ExemplaireAux[] convertExemplaires(List<Exemplaire> exemplaires) {
		
		ExemplaireAux[] exTab = null;
		for (int i=0; i<exemplaires.size(); i++) {
			
			Exemplaire ex = exemplaires.get(i);
			ExemplaireAux exAux = new ExemplaireAux(ex);
			exTab[i] = exAux;
		}
		
		return exTab;
	}

	@Override
	public EmpruntAux[] convertEmprunts(List<Emprunt> emprunts) {
		
		/*
		EmpruntAux[] empTab = null;
		for (int i=0; i<emprunts.size(); i++) {
			
			Emprunt emp = emprunts.get(i);
			EmpruntAux empAux = new EmpruntAux(emp);
			empTab[i] = empAux;
		}
	
		return empTab;
		*/
		return null;
	}

	@Override
	public OuvrageAux[] convertOuvrages(List<Ouvrage> ouvrages) {
		
		OuvrageAux[] ouvTab = null;
		for (int i=0; i<ouvrages.size(); i++) {
			
			Ouvrage ouv = ouvrages.get(i);
			OuvrageAux ouvAux = new OuvrageAux(ouv);
			ouvTab[i] = ouvAux;
		}
	
		return ouvTab;
	}


	
}
