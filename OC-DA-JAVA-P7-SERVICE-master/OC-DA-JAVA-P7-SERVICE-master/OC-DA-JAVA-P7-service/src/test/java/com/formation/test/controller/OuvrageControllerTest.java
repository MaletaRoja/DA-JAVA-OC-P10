package com.formation.test.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.formation.projet7.controller.OuvragesController;
import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.OuvrageAux;
import com.formation.projet7.model.Utilisateur;

public class OuvrageControllerTest {
	
	@Test
	public void estEmprunteTest() {
		
		OuvragesController ouvragesController = new OuvragesController();
		
		List<Emprunt> emprunts_u1 = new ArrayList<>();
		List<Emprunt> emprunts_u2 = new ArrayList<>();
		List<Emprunt> emprunts_u3 = new ArrayList<>();
		
		List<Emprunt> empruntsActifsU1 = new ArrayList<>();
		
		Utilisateur u1 = new Utilisateur(101, "u1_nom", "u1_prenom", "email1", "u1_pass", false, "USER", emprunts_u1);
		Utilisateur u2 = new Utilisateur(102, "u2_nom", "u2_prenom", "email2", "u2_pass", false, "USER", emprunts_u2);
		Utilisateur u3 = new Utilisateur(103, "u1_nom", "u3_prenom", "email3", "u3_pass", false, "USER", emprunts_u3);
				
		List<Exemplaire> exemplaires_o1 = new ArrayList<>();
		List<Exemplaire> exemplaires2_o2 = new ArrayList<>();
		List<Exemplaire> exemplaires3_o3 = new ArrayList<>();
		
		List<Ouvrage> ouvrages = new ArrayList<>();
		Ouvrage o1 = new Ouvrage(1, "titre1", "nom1", "prenom1", "edition1", "genre1", exemplaires_o1);
		Ouvrage o2 = new Ouvrage(2, "titre2", "nom2", "prenom2", "edition2", "genre2", exemplaires2_o2);
		Ouvrage o3 = new Ouvrage(3, "titre3", "nom3", "prenom3", "edition3", "genre3", exemplaires3_o3);
		
		ouvrages.add(o1);
		ouvrages.add(o2);
		ouvrages.add(o3);
		
		// Exemplaires de l'ouvrage 1
		
		Exemplaire ex1_o1 =  new Exemplaire(11, o1, emprunts_u1, false, true);
		Exemplaire ex2_o1=  new Exemplaire(12, o1, emprunts_u2, false, true);
		Exemplaire ex3_o1 =  new Exemplaire(13, o1, emprunts_u3, false, true);
		
		// Exemplaires de l'ouvrage 2
		
		Exemplaire ex1_o2 =  new Exemplaire(14, o2, emprunts_u1, false, true);
		Exemplaire ex2_o2 =  new Exemplaire(15, o2, emprunts_u2, false, true);
		Exemplaire ex3_o2 =  new Exemplaire(16, o2, emprunts_u3, false, true);
		
		// Exemplaires de l'ouvrage 3
		
		Exemplaire ex1_o3 =  new Exemplaire(17, o3, emprunts_u1, false, true);
		Exemplaire ex2_o3 =  new Exemplaire(18, o3, emprunts_u2, false, true);
		Exemplaire ex3_o3 =  new Exemplaire(19, o3, emprunts_u3, false, true);
		
		Emprunt e1_u1 = new Emprunt(21, ex1_o1, u1, LocalDateTime.now(), LocalDateTime.now().plusYears(4), false, true);
		Emprunt e2_u1 = new Emprunt(22, ex1_o2, u1, LocalDateTime.now(), LocalDateTime.now().plusYears(5), false, true);
		Emprunt e3_u1 = new Emprunt(23, ex1_o3, u1, LocalDateTime.now(), LocalDateTime.now().plusYears(6), false, true);
		
		Emprunt e1_u2 = new Emprunt(24, ex2_o1, u2, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);
		Emprunt e2_u2 = new Emprunt(25, ex2_o2, u2, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);
		Emprunt e3_u2 = new Emprunt(26, ex2_o3, u2, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);
		
		Emprunt e1_u3 = new Emprunt(27, ex3_o1, u3, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);
		Emprunt e2_u3 = new Emprunt(28, ex3_o2, u3, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);
		Emprunt e3_u3 = new Emprunt(29, ex3_o3, u2, LocalDateTime.now(), LocalDateTime.now().plusYears(7), false, true);
		
		exemplaires_o1.add(ex1_o1);
		exemplaires_o1.add(ex2_o1);
		exemplaires_o1.add(ex3_o1);
		
		exemplaires2_o2.add(ex1_o2);
		exemplaires2_o2.add(ex2_o2);
		exemplaires2_o2.add(ex3_o2);
		
		exemplaires3_o3.add(ex1_o3);
		exemplaires3_o3.add(ex2_o3);
		exemplaires3_o3.add(ex3_o3);
		
		emprunts_u1.add(e1_u1);
		//emprunts_u1.add(e2_u1);
		emprunts_u1.add(e3_u1);
		
		// Emprunts actifs de l'utilisateur 1
		
		empruntsActifsU1.add(e1_u1);
		//empruntsActifsU1.add(e2_u1);
		empruntsActifsU1.add(e3_u1);
		
		List<OuvrageAux> listeOuvragesAux = ouvragesController.estEmprunte(ouvrages, empruntsActifsU1);
		System.out.println("reservable 0? " +listeOuvragesAux.get(0).isReservable() + " id: " + listeOuvragesAux.get(0).getId());
		System.out.println("reservable 1? " +listeOuvragesAux.get(1).isReservable() + " id: " + listeOuvragesAux.get(1).getId());
		System.out.println("reservable 2? " +listeOuvragesAux.get(2).isReservable() + " id: " + listeOuvragesAux.get(2).getId());
		
		Assertions.assertTrue(!listeOuvragesAux.get(0).isReservable());
		Assertions.assertTrue(listeOuvragesAux.get(1).isReservable());
		Assertions.assertTrue(!listeOuvragesAux.get(2).isReservable());
		
	}
}
