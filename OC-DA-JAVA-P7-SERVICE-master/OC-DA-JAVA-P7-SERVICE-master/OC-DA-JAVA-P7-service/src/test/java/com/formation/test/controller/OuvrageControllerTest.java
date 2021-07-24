package com.formation.test.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.formation.projet7.controller.OuvragesController;
import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.OuvrageAux;
import com.formation.projet7.model.Reservation;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.service.jpa.EmpruntService;
import com.formation.projet7.service.jpa.OuvrageService;
import com.formation.projet7.service.jpa.ReservationService;

import io.jsonwebtoken.lang.Assert;

@ExtendWith(MockitoExtension.class)
public class OuvrageControllerTest {
	
	
	private OuvragesController ouvragesController;
	
	@Mock
	private ReservationService reservationService;
	
	private static List<Emprunt> emprunts_u1;
	private static List<Emprunt> emprunts_u2;
	private static List<Emprunt> emprunts_u3;

	private static List<Emprunt> empruntsActifsU1;

	private static Utilisateur u1;
	private static Utilisateur u2;
	private static Utilisateur u3;

	private static List<Exemplaire> exemplaires1_o1 = new ArrayList<>();
	private static List<Exemplaire> exemplaires2_o2 = new ArrayList<>();
	private static List<Exemplaire> exemplaires3_o3 = new ArrayList<>();

	private static List<Ouvrage> ouvrages;
	private static Ouvrage o1;
	private static Ouvrage o2;
	private static Ouvrage o3;

	// Exemplaires de l'ouvrage 1

	private static Exemplaire ex1_o1;
	private static Exemplaire ex2_o1;
	private static Exemplaire ex3_o1;

	// Exemplaires de l'ouvrage 2

	private static Exemplaire ex1_o2;
	private static Exemplaire ex2_o2;
	private static Exemplaire ex3_o2;

	// Exemplaires de l'ouvrage 3

	private static Exemplaire ex1_o3;
	private static Exemplaire ex2_o3;
	private static Exemplaire ex3_o3;

	private static Emprunt e1_u1;
	private static Emprunt e2_u1;
	private static Emprunt e3_u1;

	private static Emprunt e1_u2;
	private static Emprunt e2_u2;
	private static Emprunt e3_u2;

	private static Emprunt e1_u3;
	private static Emprunt e2_u3;
	private static Emprunt e3_u3;

	@BeforeEach
	void setUp() {
		
		ouvragesController = new OuvragesController();
		ouvragesController.setReservationService(reservationService);

		emprunts_u1 = new ArrayList<>();
		emprunts_u2 = new ArrayList<>();
		emprunts_u3 = new ArrayList<>();

		empruntsActifsU1 = new ArrayList<>();

		u1 = new Utilisateur(101, "u1_nom", "u1_prenom", "email1", "u1_pass", false, "USER", emprunts_u1);
		u2 = new Utilisateur(102, "u2_nom", "u2_prenom", "email2", "u2_pass", false, "USER", emprunts_u2);
		u3 = new Utilisateur(103, "u1_nom", "u3_prenom", "email3", "u3_pass", false, "USER", emprunts_u3);

		exemplaires1_o1 = new ArrayList<>();
		exemplaires2_o2 = new ArrayList<>();
		exemplaires3_o3 = new ArrayList<>();

		ouvrages = new ArrayList<>();
		o1 = new Ouvrage(1, "titre1", "nom1", "prenom1", "edition1", "genre1", exemplaires1_o1);
		o2 = new Ouvrage(2, "titre2", "nom2", "prenom2", "edition2", "genre2", exemplaires2_o2);
		o3 = new Ouvrage(3, "titre3", "nom3", "prenom3", "edition3", "genre3", exemplaires3_o3);

		ouvrages.add(o1);
		ouvrages.add(o2);
		ouvrages.add(o3);

		// Exemplaires de l'ouvrage 1

		ex1_o1 = new Exemplaire(11, o1, emprunts_u1, false, true);
		ex2_o1 = new Exemplaire(12, o1, emprunts_u2, false, true);
		ex3_o1 = new Exemplaire(13, o1, emprunts_u3, false, true);

		// Exemplaires de l'ouvrage 2

		ex1_o2 = new Exemplaire(14, o2, emprunts_u1, false, true);
		ex2_o2 = new Exemplaire(15, o2, emprunts_u2, false, true);
		ex3_o2 = new Exemplaire(16, o2, emprunts_u3, false, true);

		// Exemplaires de l'ouvrage 3

		ex1_o3 = new Exemplaire(17, o3, emprunts_u1, false, true);
		ex2_o3 = new Exemplaire(18, o3, emprunts_u2, false, true);
		ex3_o3 = new Exemplaire(19, o3, emprunts_u3, false, true);

		Emprunt e1_u1 = new Emprunt(21, ex1_o1, u1, LocalDateTime.now(), LocalDateTime.now().plusYears(4), false, true);
		Emprunt e2_u1 = new Emprunt(22, ex1_o2, u1, LocalDateTime.now(), LocalDateTime.now().plusYears(5), false, true);
		Emprunt e3_u1 = new Emprunt(23, ex1_o3, u1, LocalDateTime.now(), LocalDateTime.now().plusYears(6), false, true);

		Emprunt e1_u2 = new Emprunt(24, ex2_o1, u2, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);
		Emprunt e2_u2 = new Emprunt(25, ex2_o2, u2, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);
		Emprunt e3_u2 = new Emprunt(26, ex2_o3, u2, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);

		Emprunt e1_u3 = new Emprunt(27, ex3_o1, u3, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);
		Emprunt e2_u3 = new Emprunt(28, ex3_o2, u3, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);
		Emprunt e3_u3 = new Emprunt(29, ex3_o3, u2, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);

		exemplaires1_o1.add(ex1_o1);
		exemplaires1_o1.add(ex2_o1);
		exemplaires1_o1.add(ex3_o1);

		exemplaires2_o2.add(ex1_o2);
		exemplaires2_o2.add(ex2_o2);
		exemplaires2_o2.add(ex3_o2);

		exemplaires3_o3.add(ex1_o3);
		exemplaires3_o3.add(ex2_o3);
		exemplaires3_o3.add(ex3_o3);

		emprunts_u1.add(e1_u1);
		// emprunts_u1.add(e2_u1);
		emprunts_u1.add(e3_u1);
		
		empruntsActifsU1.add(e1_u1);
		// empruntsActifsU1.add(e2_u1);
		empruntsActifsU1.add(e3_u1);


	}

	@Test
	public void estEmprunteTest() {

		List<OuvrageAux> listeOuvragesAux = ouvragesController.estEmprunte(ouvrages, empruntsActifsU1);
		System.out.println(
				"reservable 0? " + listeOuvragesAux.get(0).isReservable() + " id: " + listeOuvragesAux.get(0).getId());
		System.out.println(
				"reservable 1? " + listeOuvragesAux.get(1).isReservable() + " id: " + listeOuvragesAux.get(1).getId());
		System.out.println(
				"reservable 2? " + listeOuvragesAux.get(2).isReservable() + " id: " + listeOuvragesAux.get(2).getId());

		Assertions.assertTrue(!listeOuvragesAux.get(0).isReservable());
		Assertions.assertTrue(listeOuvragesAux.get(1).isReservable());
		Assertions.assertTrue(!listeOuvragesAux.get(2).isReservable());

	}
	
	@Test
	public void checkDatesTest() {
		
		Emprunt e1_u1 = new Emprunt(21, ex1_o1, u1, LocalDateTime.now(), LocalDateTime.now().plusYears(4), false, true);
		Emprunt e1_u2 = new Emprunt(24, ex2_o1, u2, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);
		Emprunt e1_u3 = new Emprunt(27, ex3_o1, u3, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);
		
		OuvrageAux o1Aux = new OuvrageAux(o1);
		List<Emprunt> empruntsActifsO1 = new ArrayList<>();
		empruntsActifsO1.add(e1_u1);
		empruntsActifsO1.add(e1_u2);
		empruntsActifsO1.add(e1_u3);
		LocalDateTime dateProche = empruntsActifsO1.get(0).getFin();
		ouvragesController.checkDates(empruntsActifsO1, o1Aux);
		System.out.println("date retour après: " + o1Aux.getRetour());
		Assertions.assertTrue(o1Aux.getRetour().equals(e1_u1.getFin()));
		
	}

	@Test 
	public void setReservationListNullTest() {
		
		List<Ouvrage> ouvrages = new ArrayList<>();
		o1 = new Ouvrage(1, "titre1", "nom1", "prenom1", "edition1", "genre1", exemplaires1_o1);
		o2 = new Ouvrage(2, "titre2", "nom2", "prenom2", "edition2", "genre2", exemplaires2_o2);
		o3 = new Ouvrage(3, "titre3", "nom3", "prenom3", "edition3", "genre3", exemplaires3_o3);
		
		ouvrages.add(o1);
		ouvrages.add(o2);
		ouvrages.add(o3);
		
		List<OuvrageAux> listeOuvragesAux = new ArrayList<>();
		for(Ouvrage o: ouvrages) {
			
			OuvrageAux oAux = new OuvrageAux(o);
			listeOuvragesAux.add(oAux);
			
		}
		
		List<Reservation> reservations_o1 = null;
		List<Reservation> reservations_o2 = null;
		List<Reservation> reservations_o3 = null;
		
		when(reservationService.obtenirListeReservationsParOuvrage(1)).thenReturn(reservations_o1);
		when(reservationService.obtenirListeReservationsParOuvrage(2)).thenReturn(reservations_o2);
		when(reservationService.obtenirListeReservationsParOuvrage(3)).thenReturn(reservations_o3);
		ouvragesController.setReservations(listeOuvragesAux);
		Assertions.assertTrue(listeOuvragesAux.get(0).getReservations() == 0);
		Assertions.assertTrue(listeOuvragesAux.get(1).getReservations() == 0);
		Assertions.assertTrue(listeOuvragesAux.get(2).getReservations() == 0);
	}
	
	
	@Test
	public void setReservationListNotNullTest() {
		
		List<Ouvrage> ouvrages = new ArrayList<>();
		o1 = new Ouvrage(1, "titre1", "nom1", "prenom1", "edition1", "genre1", exemplaires1_o1);
		o2 = new Ouvrage(2, "titre2", "nom2", "prenom2", "edition2", "genre2", exemplaires2_o2);
		o3 = new Ouvrage(3, "titre3", "nom3", "prenom3", "edition3", "genre3", exemplaires3_o3);
		
		ouvrages.add(o1);
		ouvrages.add(o2);
		ouvrages.add(o3);
		
		List<Reservation> reservations_o1 = new ArrayList<>();
		List<Reservation> reservations_o2 = new ArrayList<>();
		List<Reservation> reservations_o3 = new ArrayList<>();
		
		Reservation r1_o1 = new Reservation();
		reservations_o1.add(r1_o1);
		
		Reservation r1_o2 = new Reservation();
		reservations_o2.add(r1_o2);
		
		Reservation r2_o2 = new Reservation();
		reservations_o2.add(r2_o2);
		
		Reservation r1_o3 = new Reservation();
		reservations_o3.add(r1_o3);
		
		Reservation r2_o3 = new Reservation();
		reservations_o3.add(r2_o3);
		
		Reservation r3_o3 = new Reservation();
		reservations_o3.add(r3_o3);
		
		List<OuvrageAux> listeOuvragesAux = new ArrayList<>();
		for(Ouvrage o: ouvrages) {
			
			OuvrageAux oAux = new OuvrageAux(o);
			listeOuvragesAux.add(oAux);
			
		}

		when(reservationService.obtenirListeReservationsParOuvrage(1)).thenReturn(reservations_o1);
		when(reservationService.obtenirListeReservationsParOuvrage(2)).thenReturn(reservations_o2);
		when(reservationService.obtenirListeReservationsParOuvrage(3)).thenReturn(reservations_o3);
		ouvragesController.setReservations(listeOuvragesAux);
		Assertions.assertTrue(listeOuvragesAux.get(0).getReservations() == 1);
		Assertions.assertTrue(listeOuvragesAux.get(1).getReservations() == 2);
		Assertions.assertTrue(listeOuvragesAux.get(2).getReservations() == 3);
		
	}
}
