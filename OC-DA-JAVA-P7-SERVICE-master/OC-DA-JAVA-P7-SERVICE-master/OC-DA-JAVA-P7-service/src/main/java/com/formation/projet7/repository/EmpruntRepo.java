package com.formation.projet7.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.Utilisateur;

public interface EmpruntRepo extends JpaRepository<Emprunt, Integer> {

	List<Emprunt> findByEmprunteur(Utilisateur utilisateur);
	List<Emprunt> findByEmprunteurAndActif(Utilisateur utilisateur, boolean actif);
	List<Emprunt> findByActif(boolean b);
	List<Emprunt> findByActifAndFinBefore(boolean b, LocalDateTime date);
	
	

}
