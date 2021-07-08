package com.formation.projet7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.projet7.model.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

}
