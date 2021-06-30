package com.formation.projet7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.projet7.model.Utilisateur;

public interface UserRepo extends JpaRepository<Utilisateur, Integer> {
	

	Utilisateur findByUsername(String email);
	
	Utilisateur findByUsernameAndPassword(String username,String password);
	
	@Query("select u from Utilisateur u where u.nom = ?1 or u.prenom = ?1")
	  Utilisateur findByIdentity(String nom);
	

	
}
