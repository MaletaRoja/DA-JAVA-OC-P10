package com.formation.projet7.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.projet7.constants.Constants;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.Reservation;
import com.formation.projet7.repository.OuvrageRepo;
import com.formation.projet7.repository.ReservationRepo;
import com.formation.projet7.service.IReservationService;

@Service
public class ReservationService implements IReservationService {

	@Autowired
	ReservationRepo reservationRepo;

	@Autowired
	OuvrageRepo ouvrageRepo;
	
	@Autowired
	ExemplaireService exemplaireService;

	@Override
	public void enregistrerReservation(Reservation reservation) {

		reservationRepo.save(reservation);

	}

	@Override
	public List<Reservation> obtenirListeReservation(Integer id) {

		Ouvrage ouvrage = ouvrageRepo.getOne(id);
		List<Reservation> reservations = ouvrage.getReservations();

		return reservations;
	}

	@Override
	public boolean isReservationPossible(Integer id) {

		Ouvrage ouvrage = ouvrageRepo.getOne(id);
		List<Reservation> reservations = ouvrage.getReservations();
		List<Exemplaire> exemplaires = ouvrage.getExemplaires();
		int reservationsPermises = exemplaires.size() * Constants.FACTEUR_RESERVATION;
		if (reservations.size() < reservationsPermises) {
			
			List<Exemplaire> disponibles = exemplaireService.exemplairesDisposParOuvrage(id);
			if (disponibles.size() !=0) {
				
				return true;
				
			}else {
				
				return false;
			}
			
			
		} else {

			return false;
		}
	}

}
