package com.formation.projet7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.Reservation;
import com.formation.projet7.model.Utilisateur;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

	List<Reservation> findByDemandeur(Utilisateur demandeur);

	Reservation findByDemandeurAndOuvrage(Utilisateur demandeur, Ouvrage ouvrage);

}
