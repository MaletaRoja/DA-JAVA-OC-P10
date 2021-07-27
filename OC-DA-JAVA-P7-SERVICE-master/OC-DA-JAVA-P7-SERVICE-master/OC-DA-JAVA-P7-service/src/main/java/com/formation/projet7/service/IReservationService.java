package com.formation.projet7.service;

import java.util.List;

import com.formation.projet7.model.Reservation;
import com.formation.projet7.service.jpa.ExemplaireService;

public interface IReservationService {
	
	void enregistrerReservation(Reservation reservation);
	int isReservationPossible(Integer id);
	List<Reservation> obtenirListeReservationsParOuvrage(Integer id);  // Liste les réservations pour un ouvrage (id = id de l'ouvrage)
	List<Reservation> obtenirListeReservationsParUtilisateur(Integer id); // Liste les réservations pour un ouvrage (id = id de l'utilsateur)
	void annulerReservation(Integer idUser, Integer idOuvrage);
	
	
	

}
