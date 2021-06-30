package com.formation.projet7.service.jpa;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.projet7.constants.Constants;
import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.EmpruntAux;
import com.formation.projet7.model.EmpruntAuxMail;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.LigneEmprunt;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.repository.EmpruntRepo;
import com.formation.projet7.repository.ExemplaireRepo;
import com.formation.projet7.repository.OuvrageRepo;
import com.formation.projet7.service.IEmpruntService;
import com.formation.projet7.service.utils.OuvrageOutil;

@Service
public class EmpruntService implements IEmpruntService {

	@Autowired
	EmpruntRepo empruntRepo;

	@Autowired
	OuvrageRepo ouvrageRepo;

	@Autowired
	UserService userService;

	@Autowired
	ExemplaireRepo exemplaireRepo;

	@Override
	public List<Emprunt> listerUserEmprunt(Utilisateur user) {

		List<Emprunt> emprunts = empruntRepo.findByEmprunteur(user);
		return emprunts;
	}

	@Override
	public List<Exemplaire> listerOuvrageEmprunts(Ouvrage ouvrage) {

		List<Exemplaire> exemplaires = ouvrage.getExemplaires();
		return exemplaires;
	}


	 @Override public void saveEmprunt(Emprunt emprunt) {
		 empruntRepo.save(emprunt);
	  
	  }
	 

	@Override
	public void enregistrerEmprunt(String rubrique, Integer id, Utilisateur utilisateur) {

		LocalDateTime debut = LocalDateTime.now();
		// LocalDateTime fin = debut.plus(Constants.DELAY_MOUNTS, ChronoUnit.MONTHS);
		LocalDateTime fin = debut.plus(Constants.DELAY_MIN, ChronoUnit.MINUTES);
		Emprunt emprunt = new Emprunt();
		emprunt.setActif(true);
		emprunt.setDebut(debut);
		emprunt.setFin(fin);
		emprunt.setEmprunteur(utilisateur);
		emprunt.setProlongation(false);

		List<Ouvrage> ouvrages = ouvrageRepo.findByGenre(rubrique);
		Ouvrage ouvrage = ouvrages.get(id);
		List<Exemplaire> exemplaires = ouvrage.getExemplaires();

		List<Exemplaire> livrables = new OuvrageOutil().exemplairesLivrables(exemplaires);
		Exemplaire ex = livrables.get(0);
		emprunt.setExemplaire(ex);
		ex.setDisponible(false);
		exemplaireRepo.save(ex);
		System.out.println("date début: " + debut);
		System.out.println("date fin:   " + fin);

		empruntRepo.save(emprunt);
	}

	public void enregistrerEmprunt(EmpruntAux empruntAux) {

		Utilisateur utilisateur = userService.obtenirUser(empruntAux.getIdUser());

		Integer id = empruntAux.getNumero();
		String rubrique = empruntAux.getRubrique();

		LocalDateTime debut = LocalDateTime.now();
		// LocalDateTime fin = debut.plus(Constants.DELAY_MOUNTS, ChronoUnit.MONTHS);
		LocalDateTime fin = debut.plus(Constants.DELAY_MIN, ChronoUnit.MINUTES);

		Emprunt emprunt = new Emprunt();
		emprunt.setActif(true);
		emprunt.setDebut(debut);
		emprunt.setFin(fin);
		emprunt.setEmprunteur(utilisateur);
		emprunt.setProlongation(false);
		List<Ouvrage> ouvrages = new ArrayList<Ouvrage>();
		if (rubrique.equals("toutes")) {

			ouvrages = ouvrageRepo.findAll();

		} else {

			ouvrages = ouvrageRepo.findByGenre(rubrique);

		}

		Ouvrage ouvrage = ouvrages.get(id);
		List<Exemplaire> exemplaires = ouvrage.getExemplaires();
		List<Exemplaire> livrables = new OuvrageOutil().exemplairesLivrables(exemplaires);
		Exemplaire ex = livrables.get(0);
		emprunt.setExemplaire(ex);
		ex.setDisponible(false);
		exemplaireRepo.save(ex);
		empruntRepo.save(emprunt);

	}

	@Override
	public List<Emprunt> listerUserEmpruntActifs(Utilisateur user) {

		List<Emprunt> emprunts = empruntRepo.findByEmprunteurAndActif(user, true);

		return emprunts;
	}

	public List<LigneEmprunt> obtenirUserEmpruntActifs(Integer id) {
		
		Utilisateur user = userService.obtenirUser(id);
		List<Emprunt> emprunts = empruntRepo.findByEmprunteurAndActif(user, true);
		List<LigneEmprunt> tabEmprunts = new ArrayList<LigneEmprunt>();
		
		for(Emprunt em : emprunts) {
			
			Exemplaire ex = em.getExemplaire();
			Ouvrage o = ex.getOuvrage();
			LigneEmprunt ligne = new LigneEmprunt();
			ligne.setId(em.getId());
			ligne.setActif(em.isActif());
			ligne.setProlongation(em.isProlongation());
			ligne.setTitre(o.getTitre());
			ligne.setAuteur_nom(o.getAuteur_nom());
			ligne.setAuteur_prenom(o.getAuteur_prenom());
			ligne.setEdition(o.getEdition());
			ligne.setGenre(o.getGenre());
			ligne.setDebut(em.getDebut());
			ligne.setFin(em.getFin());
			tabEmprunts.add(ligne);
			
		}
		
		return tabEmprunts;
	}

	public Emprunt obtenirEmpruntParId(Integer id) {
		Emprunt emprunt = empruntRepo.getOne(id);
		return emprunt;
	}

	public List<Emprunt> obtenirEmpruntsActif() {

		List<Emprunt> emprunts = empruntRepo.findByActif(true);
		return emprunts;
	}
	
	public List<EmpruntAuxMail> obtenirEmpruntActifParDate(LocalDateTime date){
		
		List<Emprunt> emprunts = empruntRepo.findByActifAndFinBefore(true, date);
		List<EmpruntAuxMail> empruntsAux = new ArrayList<EmpruntAuxMail>();
		
		 for (Emprunt em : emprunts) {
			 
			 EmpruntAuxMail e = new EmpruntAuxMail();
			 e.setId(em.getId());
			 e.setEmprunteur(em.getEmprunteur().getPrenom() + " " + em.getEmprunteur().getNom());
			 e.setEmail(em.getEmprunteur().getUsername());
			 e.setTitre(em.getExemplaire().getOuvrage().getTitre());
			 e.setDebut(em.getDebut());
			 e.setFin(em.getFin());
			 
			 empruntsAux.add(e);
		 }
		 
		return empruntsAux;
	}
	
	
	// test JPA persistence 
	
	public Emprunt trouveEmprunt(Integer id) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Emprunt emp = em.find(Emprunt.class, id);
		tx.commit();
		return emp;
	}
	
	

	

}
