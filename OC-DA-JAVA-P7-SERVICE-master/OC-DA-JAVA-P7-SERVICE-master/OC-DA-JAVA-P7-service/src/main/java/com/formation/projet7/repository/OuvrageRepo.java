package com.formation.projet7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.Utilisateur;

import feign.Param;

@Repository
public interface OuvrageRepo extends JpaRepository<Ouvrage, Integer> {
	
	@Query(value = "SELECT DISTINCT genre FROM ouvrage", nativeQuery = true)
	List<String> findRubriques();
	
	List<Ouvrage> findByGenre(String genre);
	
	List<Ouvrage> findByTitreIgnoreCase(String phrase);
	
	List<Ouvrage> findByGenreIgnoreCase(String phrase);
	
	@Query("select o from Ouvrage o where o.auteur_nom = ?1 or o.auteur_prenom = ?1 or o.titre = ?1")
	List<Ouvrage> findByAuteur(String nom);
	
	// Version2
	//@Query("select o from Ouvrage o where o.auteur_nom = :phrase or o.auteur_prenom = :phrase or o.titre = :phrase")
	//List<Ouvrage> findByAuteurLike(@Param("phrase") String phrase);
	
	@Query("SELECT o FROM Ouvrage o WHERE o.auteur_nom  LIKE %:phrase% OR o.auteur_prenom  LIKE %:phrase% OR o.titre LIKE %:phrase% OR o.genre LIKE %:phrase%")
	List<Ouvrage> findByPhraseLike(@Param("phrase") String phrase);
	
	@Query("SELECT o FROM Ouvrage o WHERE o.auteur_nom  LIKE %:phrase OR o.auteur_prenom  LIKE %:phrase OR o.titre LIKE %:phrase OR o.genre LIKE %:phrase")
	List<Ouvrage> findByPhraseStartLike(@Param("phrase") String phrase);
}
