package com.formation.projet7.service;

import java.util.List;

import com.formation.projet7.model.Reservation;
import com.formation.projet7.service.jpa.ExemplaireService;

public interface IReservationService {
	
	void enregistrerReservation(Reservation reservation);
	List<Reservation> obtenirListeReservation(Integer id);  // Liste les réservations pour un ouvrage (id = id de l'ouvrage)
	boolean isReservationPossible(Integer id);

	
	

}
