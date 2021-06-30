package com.formation.projet7.model;

import java.util.List;

public interface IConverter {
	
	ExemplaireAux[] convertExemplaires(List<Exemplaire> exemplaires);
	EmpruntAux[] convertEmprunts(List<Emprunt> emprunts);
	OuvrageAux[] convertOuvrages(List<Ouvrage> ouvrages);


}
