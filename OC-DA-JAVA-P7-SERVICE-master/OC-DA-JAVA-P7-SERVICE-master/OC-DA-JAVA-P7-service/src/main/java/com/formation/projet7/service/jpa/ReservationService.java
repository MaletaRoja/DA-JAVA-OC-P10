package com.formation.projet7.service.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.projet7.constants.Constants;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.OuvrageAux;
import com.formation.projet7.model.Reservation;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.repository.OuvrageRepo;
import com.formation.projet7.repository.ReservationRepo;
import com.formation.projet7.repository.UserRepo;
import com.formation.projet7.service.IReservationService;

@Service
public class ReservationService implements IReservationService {

	@Autowired
	ReservationRepo reservationRepo;

	@Autowired
	OuvrageRepo ouvrageRepo;
	
	@Autowired
	ExemplaireService exemplaireService;
	
	@Autowired
	UserService userService;


	@Override
	public void enregistrerReservation(Reservation reservation) {

		reservationRepo.save(reservation);

	}


	@Override
	public List<Reservation> obtenirListeReservationsParOuvrage(Integer id){

		Ouvrage ouvrage = ouvrageRepo.getOne(id);
		List<Reservation> reservations = ouvrage.getReservations();

		return reservations;
	}

	@Override
	public Integer isReservationPossible(Integer id) {

		Ouvrage ouvrage = ouvrageRepo.getOne(id);
		List<Reservation> reservations = ouvrage.getReservations();
		List<Reservation> reservationsActives = new ArrayList<>();
		Integer priorite = 0;
		for(Reservation r : reservations) {
			
			if (r.isActif()) {
				reservationsActives.add(r);
				priorite++;
				
			}
		}
		List<Exemplaire> exemplaires = ouvrage.getExemplaires();
		int reservationsPermises = exemplaires.size() * Constants.FACTEUR_RESERVATION;
		if (reservations.size() <= reservationsPermises) {
			
			List<Exemplaire> disponibles = exemplaireService.exemplairesDisposParOuvrage(id);
			if (disponibles.size() !=0) {
				
				return priorite+1;
				
			}else {
				
				return new Integer(1);
			}
			
			
		} else {

			return new Integer(1);
		}
	}

	

	@Override
	public List<Reservation> obtenirListeReservationsParUtilisateur(Integer id) {
		
		Utilisateur demandeur = userService.obtenirUser(id);
		List<Reservation> reservations = reservationRepo.findByDemandeur(demandeur);
		return reservations;
	}

	
	@Override
	public void annulerReservation(Integer idUser, Integer idOuvrage) {
		
		Utilisateur demandeur = userService.obtenirUser(idUser);
		Ouvrage ouvrage = ouvrageRepo.getOne(idOuvrage);
		Reservation reservation = reservationRepo.findByDemandeurAndOuvrage(demandeur, ouvrage);
		reservationRepo.delete(reservation);
	}


	public boolean isReserve(OuvrageAux o, Integer idUser) {
		
		Utilisateur demandeur = userService.obtenirUser(idUser);
		Integer idOuvrage = o.getId();
		Ouvrage ouvrage = ouvrageRepo.getOne(idOuvrage);
		Reservation reservation = reservationRepo.findByDemandeurAndOuvrage(demandeur, ouvrage);
		if(reservation != null) {
			
			return false;
			
		}else {
			
			return o.isReservable();
		}
		
	}

	

}
