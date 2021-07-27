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
	
	

	public ReservationRepo getReservationRepo() {
		return reservationRepo;
	}

	public void setReservationRepo(ReservationRepo reservationRepo) {
		this.reservationRepo = reservationRepo;
	}

	public OuvrageRepo getOuvrageRepo() {
		return ouvrageRepo;
	}

	public void setOuvrageRepo(OuvrageRepo ouvrageRepo) {
		this.ouvrageRepo = ouvrageRepo;
	}

	public ExemplaireService getExemplaireService() {
		return exemplaireService;
	}

	public void setExemplaireService(ExemplaireService exemplaireService) {
		this.exemplaireService = exemplaireService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void enregistrerReservation(Reservation reservation) {

		reservationRepo.save(reservation);

	}

	@Override
	public List<Reservation> obtenirListeReservationsParOuvrage(Integer id) {

		Ouvrage ouvrage = ouvrageRepo.getOne(id);
		List<Reservation> reservations = ouvrage.getReservations();

		return reservations;
	}

	/*
	 * Permet de déterminer si le nombre limite de réservations pour un ouvrage a
	 * été atteint et retourne l'ordre de priorité pour la réservation envisagée
	 * 
	 * nombre limite de réservation : 2 x le nombre d'exemplaires actifs
	 * 
	 */

	@Override
	public int isReservationPossible(Integer id) {

		Ouvrage ouvrage = ouvrageRepo.getOne(id);
		List<Reservation> reservations = ouvrage.getReservations();
		System.out.println("taille liste reservations: " + reservations.size());
		List<Reservation> reservationsActives = new ArrayList<>();
		int priorite = 0;
		if (reservations.size() == 0) {

			priorite = 1;
			
		} else {
			
			for (Reservation r : reservations) {
				
				if (r.isActif()) {
					reservationsActives.add(r);
					priorite++;

				}
				priorite++;
			}
		}

		List<Exemplaire> exemplaires = ouvrage.getExemplaires();
		List<Exemplaire> exActifs = new ArrayList<>();
		for (Exemplaire e : exemplaires) {

			if (e.isActif()) {
				exActifs.add(e);
			}
		}
		int reservationsPermises = exActifs.size() * Constants.FACTEUR_RESERVATION;
		if (reservationsActives.size() <= reservationsPermises) {

			List<Exemplaire> disponibles = exemplaireService.exemplairesDisposParOuvrage(id);
			if (disponibles.size() == 0) {

				return priorite;

			} else {

				return -1;
			}

		} else {

			return -1;
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
		int priorite = reservation.getPriorite();
		List<Reservation> reservations = obtenirListeReservationsParOuvrage(idOuvrage);
		for(Reservation r: reservations) {
			
			int prioriteReservation = r.getPriorite();
			if (prioriteReservation > priorite) {
				
				prioriteReservation--;
				r.setPriorite(prioriteReservation);
				enregistrerReservation(r);
			}
		}
		
		reservationRepo.delete(reservation);
	}

	public boolean isReserve(OuvrageAux o, Integer idUser) {

		Utilisateur demandeur = userService.obtenirUser(idUser);
		Integer idOuvrage = o.getId();
		Ouvrage ouvrage = ouvrageRepo.getOne(idOuvrage);
		Reservation reservation = reservationRepo.findByDemandeurAndOuvrage(demandeur, ouvrage);
		if (reservation != null) {

			return false;

		} else {

			return o.isReservable();
		}

	}

	public List<Reservation> obtenirReservationsActives() {
		
		List<Reservation> reservations = reservationRepo.findByActif(true);
		return reservations;
	}

	public Reservation obtenirReservationParId(Integer id) {
		
		Reservation reservation = reservationRepo.getOne(id);
		return reservation;
	}

	public void rotationReservations(List<Reservation> reservations, Exemplaire exemplaire) {
	
		for(Reservation r: reservations) {
			
			if(r.isActif()) {
				
				if(r.getPriorite() > 0) {
					
					r.setPriorite(r.getPriorite() - 1);
					if(r.getPriorite() == 1) {
						
						r.setExemplaire_id(exemplaire.getId());
						r.setDateAvis(null);
					}
					
					if(r.getPriorite() == 0) {
						
						r.setExemplaire_id(null);
					}
				}
				
				enregistrerReservation(r);
			}
		}
		
	}

}
