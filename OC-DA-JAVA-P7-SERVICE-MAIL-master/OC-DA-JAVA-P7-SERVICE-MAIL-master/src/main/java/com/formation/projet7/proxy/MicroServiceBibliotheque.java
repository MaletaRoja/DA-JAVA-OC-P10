package com.formation.projet7.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.formation.projet7.model.Avis;
import com.formation.projet7.model.EmpruntAuxMail;
import com.formation.projet7.model.Login;
import com.formation.projet7.model.Transfert;
import com.formation.projet7.model.UtilisateurAux;

//@FeignClient(name="biblio-service", url="localhost:8081/biblio")
@FeignClient(name="biblio-service", url="localhost:8081/biblio")
public interface MicroServiceBibliotheque {
	
	@GetMapping("ouvrage/emprunts/mail")
	public List<EmpruntAuxMail> obtenirEmpruntsActif(@RequestHeader("Authorization") String token);
	

	@PostMapping("connexion/")
	public ResponseEntity<UtilisateurAux> generate(@RequestBody final Login login);
	
	@GetMapping("/reservations/mail")
	public List<Avis> obtenirReservationsActives(@RequestHeader("Authorization") String token);
	
	@PostMapping("/reservations/supprimer/mail")
	public void supprimerReservationMail(@RequestHeader("Authorization") String token, @RequestBody Transfert transfertAvisDepasses);

	@PostMapping("/reservations/avis/dater/mail")
	public void ajouterDatesAvisMail(@RequestHeader("Authorization") String token, @RequestBody Transfert transfertAvisDates);

}
