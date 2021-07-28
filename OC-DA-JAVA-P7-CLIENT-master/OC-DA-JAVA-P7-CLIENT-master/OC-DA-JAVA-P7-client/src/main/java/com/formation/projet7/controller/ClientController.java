package com.formation.projet7.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.formation.projet7.constants.Constants;
import com.formation.projet7.model.EmpruntAux;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.Login;
import com.formation.projet7.model.OuvrageAux;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.model.UtilisateurAux;
import com.formation.projet7.model.auxiliaire.FormCompte;
import com.formation.projet7.model.auxiliaire.LigneEmprFormat;
import com.formation.projet7.model.auxiliaire.LigneEmprunt;
import com.formation.projet7.proxy.MicroServiceMail;
import com.formation.projet7.proxy.MicroServiceOuvrages;
import com.formation.projet7.service.PageOuvrage;
import com.formation.projet7.service.UserConnexion;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	MicroServiceOuvrages microServiceOuvrages;

	@Autowired
	MicroServiceMail microServiceMail;

	@Autowired
	PageOuvrage pageOuvrage;

	@Autowired
	UserConnexion userConnexion;

	@GetMapping("/")
	public String accueil(Model model, HttpSession session) {

		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);
		return Constants.PAGE_ACCUEIL;
	}

	@GetMapping("/presentation")
	public String presentation(HttpSession session, Model model) {

		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);
		return Constants.PAGE_PRESENTATION;
	}

	@GetMapping("/connexion") // Accès formulaire de connexion
	public String connexion(@RequestParam(name = "error", required = false) boolean error, Model model) {

		if (error) {

			model.addAttribute("login", new Login());
			model.addAttribute("error", true);
		}
		model.addAttribute("login", new Login());
		return Constants.PAGE_CONNEXION;
	}

	@PostMapping("/connexion") // Traitement formulaire de connexion
	public String demandeConnexion(Login login, Model model, HttpSession session) {

		Utilisateur utilisateur = userConnexion.identifierUtilisateur(login, session);

		if (utilisateur != null) {
			model.addAttribute("utilisateur", utilisateur);
			model.addAttribute("authentification", true);

			return Constants.ESPACE_PERSONEL;

		} else {

			return "redirect:/biblio/client/connexion?error=true";
		}
	}

	@GetMapping("/espace")
	public String espace(Model model, HttpSession session) {

		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);
		if (utilisateur == null) {

			return Constants.PAGE_CONNEXION;

		} else {

			return Constants.ESPACE_PERSONEL;
		}

	}

	@GetMapping("/ouvrages")
	public String listeOuvgrages(Model model, HttpSession session) {

		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;
		System.out.println("Token header: " + token);
		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);
		if (utilisateur == null) {

			return Constants.PAGE_CONNEXION;

		} else {

			List<OuvrageAux> ouvrages = microServiceOuvrages.tousLesOuvrages(token, utilisateur.getId());
			List<Integer> nbreExemplairesDispos = pageOuvrage.exemplairesDisposParOuvrage(ouvrages);
			model.addAttribute("ouvrages", ouvrages);
			model.addAttribute("utilisateur", utilisateur);
			model.addAttribute("authentification", true);
			model.addAttribute("nbreExemplairesDispos", nbreExemplairesDispos);
			model.addAttribute("rubrique", "toutes");
			return Constants.OUVRAGES;

		}
	}

	@GetMapping("/rubriques") // Demande choix de rubrique pour affichage des ouvrages correspondants
	public String rubriques(Model model, HttpSession session) {

		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;
		List<String> genres = microServiceOuvrages.toutesLesRubriques(token);
		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);

		if (utilisateur == null) {

			return Constants.PAGE_CONNEXION;

		} else {

			model.addAttribute("genres", genres);
			model.addAttribute("utilisateur", utilisateur);
			model.addAttribute("authentification", true);
			return Constants.RUBRIQUES;

		}
	}

	@PostMapping("/rubriques") // Affichage des ouvrages par rubrique/genre
	public String choixRubrique(String rubrique, Model model, HttpSession session) {

		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;
		
		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);
		Integer idUser = utilisateur.getId();
		List<OuvrageAux> ouvrages = microServiceOuvrages.tousLesOuvragesParRubrique(token, rubrique, idUser);

		if (utilisateur == null) {

			return Constants.PAGE_CONNEXION;

		} else {
			List<Integer> nbreExemplairesDispos = pageOuvrage.exemplairesDisposParOuvrage(ouvrages);
			model.addAttribute("ouvrages", ouvrages);
			model.addAttribute("rubrique", rubrique);
			model.addAttribute("utilisateur", utilisateur);
			model.addAttribute("authentification", true);
			model.addAttribute("nbreExemplairesDispos", nbreExemplairesDispos);
			return Constants.OUVRAGES;

		}

	}

	@GetMapping("/exemplaire/disponibles")
	public String listeExemplairesDisponibles(Model model, HttpSession session) {

		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;
		List<Exemplaire> exemplaireDisponibles = microServiceOuvrages.ListerExemplairesDisponibles(token);

		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);
		if (utilisateur == null) {

			return Constants.PAGE_CONNEXION;

		} else {

			List<OuvrageAux> ouvrages = microServiceOuvrages.tousLesOuvrages(token, utilisateur.getId());
			return Constants.RUBRIQUES;

		}
	}

	@GetMapping("/emprunter/{id}/{rubrique}")
	public String emprunter(@PathVariable("id") Integer id, @PathVariable("rubrique") String rubrique, Model model,
			HttpSession session) {

		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);
		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;
		if (utilisateur == null) {

			return Constants.PAGE_CONNEXION;

		} else {
			EmpruntAux empruntAux = new EmpruntAux();
			empruntAux.setIdUser(utilisateur.getId());
			empruntAux.setNumero(id);
			empruntAux.setRubrique(rubrique);

			microServiceOuvrages.enregistrerEmprunt(empruntAux, token);
			model.addAttribute("enregistrement", true);
			return Constants.CONFIRMATION;

		}
	}

	@GetMapping("/emprunts/{id}")
	public String voirEmprunts(@PathVariable("id") Integer id, Model model, HttpSession session) {

		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;
		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);

		if (utilisateur == null) {

			return Constants.PAGE_CONNEXION;

		} else {

			List<LigneEmprunt> emprunts = microServiceOuvrages.empruntsActifs(utilisateur.getId(), token);
			List<LigneEmprFormat> empruntsFormat = pageOuvrage.formatListeLigneEmprunts(emprunts);
			model.addAttribute("utilisateur", utilisateur);
			model.addAttribute("emprunts", empruntsFormat);
			model.addAttribute("historique", false);
			model.addAttribute("authentification", true);
			return Constants.EMPRUNTS;

		}

	}

	@GetMapping("/emprunts/historique/{id}")
	public String voirTousEmprunts(@PathVariable("id") Integer id, Model model, HttpSession session) {

		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;
		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);

		if (utilisateur == null) {

			return Constants.PAGE_CONNEXION;

		} else {

			List<LigneEmprunt> emprunts = microServiceOuvrages.empruntsHist(utilisateur.getId(), token);
			List<LigneEmprFormat> empruntsFormat = pageOuvrage.formatListeLigneEmprunts(emprunts);
			model.addAttribute("utilisateur", utilisateur);
			model.addAttribute("emprunts", empruntsFormat);
			model.addAttribute("historique", true);
			model.addAttribute("authentification", true);
			return Constants.EMPRUNTS;

		}

	}

	@GetMapping("/prolonger/{id}")
	public String prolonger(@PathVariable("id") Integer id, Model model, HttpSession session) {

		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;
		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);

		if (utilisateur == null) {

			return Constants.PAGE_CONNEXION;

		} else {

			List<LigneEmprunt> emprunts = microServiceOuvrages.empruntsActifs(utilisateur.getId(), token);
			LigneEmprunt emprunt = emprunts.get(id);
			Integer idExemplaire = emprunt.getId();
			boolean prolongation = microServiceOuvrages.prolonger(idExemplaire);

			if (prolongation) {

				model.addAttribute("utilisateur", utilisateur);
				model.addAttribute("authentification", true);
				model.addAttribute("enregistrement", false);
				return Constants.CONFIRMATION;

			} else {

				return Constants.ERR_PROLONGATION;
			}

		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return Constants.PAGE_ACCUEIL;
	}

	@GetMapping("/compte") // Accès formulaire de création de compte
	public String compte(Model model) {

		FormCompte formCompte = new FormCompte();
		model.addAttribute("formCompte", formCompte);
		return Constants.CREATION_COMPTE;
	}

	@PostMapping("/compte") // Création du compte
	public String creationCompte(Model model, FormCompte formCompte) {

		UtilisateurAux utilisateurAux = new UtilisateurAux();
		utilisateurAux.setPrenom(formCompte.getPrenom());
		utilisateurAux.setNom(formCompte.getNom());
		utilisateurAux.setToken(formCompte.getPassword());
		utilisateurAux.setUsername(formCompte.getUsername());
		utilisateurAux.setRole("USER");

		microServiceOuvrages.creerCompte(utilisateurAux);

		return Constants.PAGE_CONNEXION;
	}

	@GetMapping("/compte/modifier")
	public String modifierCompte(@RequestParam(name = "error", required = false) boolean error, Model model,
			HttpSession session) {

		Utilisateur utilisateur = (Utilisateur) session.getAttribute("USER");
		FormCompte formCompte = new FormCompte();
		formCompte.setNom(utilisateur.getNom());
		formCompte.setPrenom(utilisateur.getPrenom());
		formCompte.setUsername(utilisateur.getUsername());
		model.addAttribute("formCompte", formCompte);
		model.addAttribute("authentification", true);
		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("error", error);

		return Constants.MODIFIER_COMPTE;
	}

	@PostMapping("/compte/modifier")
	public String enregitrementModification(Model model, HttpSession session, FormCompte formCompte) {

		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("USER");

		UtilisateurAux utilisateurAux = new UtilisateurAux();
		utilisateurAux.setId(utilisateur.getId());
		utilisateurAux.setPrenom(formCompte.getPrenom());
		utilisateurAux.setNom(formCompte.getNom());

		System.out.println("password récupéré: " + formCompte.getPassword());

		if (!formCompte.getPassword().equals("")) {

			utilisateurAux.setToken(formCompte.getPassword());
			System.out.println("chaine non vide!");
			utilisateurAux.setUsername(formCompte.getUsername());
			utilisateurAux.setRole("USER");

			utilisateur.setPrenom(formCompte.getPrenom());
			utilisateur.setNom(formCompte.getNom());

			session.setAttribute("utilisateur", utilisateur);

			microServiceOuvrages.modifierCompte(utilisateur.getId(), token, utilisateurAux);
			model.addAttribute("utilisateur", utilisateur);
			model.addAttribute("authentification", true);

			return Constants.ESPACE_PERSONEL;

		} else {

			return "redirect:/biblio/client/compte/modifier?error=true";
		}

	}

	@PostMapping("/rechercher")
	public String rechercheSimple(Model model, HttpSession session, String phrase) {

		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;

		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);

		if (phrase.isEmpty()) {

			return "redirect:/biblio/client/";

		} else {

			List<OuvrageAux> ouvrages = microServiceOuvrages.rechercheSimple(token, phrase);

			List<Integer> nbreExemplairesDispos = pageOuvrage.exemplairesDisposParOuvrage(ouvrages);

			model.addAttribute("ouvrages", ouvrages);
			model.addAttribute("nbreExemplairesDispos", nbreExemplairesDispos);
			model.addAttribute("rubrique", "toutes");

			return Constants.RECHERCHE;

		}
	}

	@GetMapping("reservations")
	public String reservations(Model model, HttpSession session) {

		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;
		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);

		if (utilisateur == null) {

			return Constants.PAGE_CONNEXION;

		} else {

			Integer idUser = utilisateur.getId();
			List<OuvrageAux> ouvrages = microServiceOuvrages.listerReservations(token, idUser);
			model.addAttribute("ouvrages", ouvrages);
			return Constants.RESERVATIONS;
		}

	}

	@GetMapping("/reserver/{id}")
	public String reserver(Model model, HttpSession session, @PathVariable(name = "id") Integer idOuvrage) {

		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;
		System.out.println("Token header: " + token);
		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);

		if (utilisateur == null) {

			return Constants.PAGE_CONNEXION;

		} else {

			Integer idUser = utilisateur.getId();
			boolean reservable = microServiceOuvrages.reserverOuvrage(token, idUser, idOuvrage);
			model.addAttribute("reservable", reservable);
			return Constants.CONFIRME_RESERVATION;

		}

	}

	@GetMapping("/reservation/annuler/{id}")
	public String annulerReservation(Model model, HttpSession session, @PathVariable(name = "id") Integer idOuvrage) {
		
		String token = (String) session.getAttribute("TOKEN");
		token = "Bearer " + token;
		System.out.println("Token header: " + token);
		Utilisateur utilisateur = userConnexion.obtenirUtilisateur(session, model);

		if (utilisateur == null) {

			return Constants.PAGE_CONNEXION;

		} else {

			Integer idUser = utilisateur.getId();
			microServiceOuvrages.annulerReservation(token, idUser, idOuvrage);
			return Constants.CONFIRME_ANNULATION;

		}

	}

}
