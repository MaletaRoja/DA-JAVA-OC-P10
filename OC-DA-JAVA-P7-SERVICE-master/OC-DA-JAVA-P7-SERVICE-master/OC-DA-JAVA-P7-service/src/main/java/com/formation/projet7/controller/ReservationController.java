package com.formation.projet7.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.projet7.model.OuvrageAux;

@RestController
@RequestMapping("/biblio")

public class ReservationController {
	
	@GetMapping("/reservations/listes/{idUser}")
	public List<OuvrageAux> ListerReservations(@RequestHeader("Authorization") String token, @PathVariable Integer idUser){
		
		
		return null;
	}

}
