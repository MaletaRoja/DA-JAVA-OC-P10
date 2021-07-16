package com.formation.projet7.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.formation.projet7.service.utils.OuvrageOutil;

public class OuvrageAux {

	private Integer id;
	private String titre;
	private String auteur_nom;
	private String auteur_prenom;
	private String edition;
	private String genre;
	private int offrable;
	private boolean reservable;
	private LocalDateTime retour; // date de retour de l'ouvrage
	private int reservations; 		// nombre de réservations pour cet ouvrage
	private int priorite;			// Ordre de priorité dans les réservations

	public OuvrageAux() {

	}

	public OuvrageAux(Integer id, String titre, String auteur_nom, String auteur_prenom, String edition, String genre,
			int offrable, boolean reservable, LocalDateTime retour, int reservations, int priorite) {
		super();
		this.id = id;
		this.titre = titre;
		this.auteur_nom = auteur_nom;
		this.auteur_prenom = auteur_prenom;
		this.edition = edition;
		this.genre = genre;
		this.offrable = offrable;
		this.reservable = reservable;
		this.retour = retour;
		this.reservations = reservations;
		this.priorite = priorite;
	}

	public OuvrageAux(Ouvrage ouvrage) {

		this.id = ouvrage.getId();
		this.titre = ouvrage.getTitre();
		this.auteur_nom = ouvrage.getAuteur_nom();
		this.auteur_prenom = ouvrage.getAuteur_prenom();
		this.edition = ouvrage.getEdition();
		this.genre = ouvrage.getGenre();
		this.offrable = new OuvrageOutil().DenombreExDisponibles(ouvrage);
		this.reservable = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur_nom() {
		return auteur_nom;
	}

	public void setAuteur_nom(String auteur_nom) {
		this.auteur_nom = auteur_nom;
	}

	public String getAuteur_prenom() {
		return auteur_prenom;
	}

	public void setAuteur_prenom(String auteur_prenom) {
		this.auteur_prenom = auteur_prenom;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getOffrable() {
		return offrable;
	}

	public void setOffrable(int offrable) {
		this.offrable = offrable;
	}

	public boolean isReservable() {
		return reservable;
	}

	public void setReservable(boolean reservable) {
		this.reservable = reservable;
	}

	@Override
	public String toString() {
		return "OuvrageAux [id=" + id + ", titre=" + titre + ", auteur_nom=" + auteur_nom + ", auteur_prenom="
				+ auteur_prenom + ", offrable=" + offrable + ", reservable=" + reservable + "]";
	}

	public LocalDateTime getRetour() {
		return retour;
	}

	public void setRetour(LocalDateTime retour) {
		this.retour = retour;
	}

	public int getReservations() {
		return reservations;
	}

	public void setReservations(int reservations) {
		this.reservations = reservations;
	}

	public int getPriorite() {
		return priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

}
