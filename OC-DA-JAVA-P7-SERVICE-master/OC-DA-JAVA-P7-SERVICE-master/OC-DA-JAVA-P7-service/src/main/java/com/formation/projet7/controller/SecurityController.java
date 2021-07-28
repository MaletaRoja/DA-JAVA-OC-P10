package com.formation.projet7.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class SecurityController {
	
	
	// Méthode de test
	

	@GetMapping("/access")

	public ResponseEntity<?> getInformacionBancaria(){
		List<String> movimientosBancarios = obtenerUltimosMovimientosBancarios();
		if(movimientosBancarios != null) {
			return new ResponseEntity<>(movimientosBancarios, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	private List<String> obtenerUltimosMovimientosBancarios(){
		List<String> movimientosBancarios = new ArrayList<>();
		movimientosBancarios.add("20");
		movimientosBancarios.add("-20");
		movimientosBancarios.add("4520");
		movimientosBancarios.add("-520");
		return movimientosBancarios;
	}

}
