package com.defensoria.integral.controller.informatica;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.adminpersonal.NPersonalC;
import com.defensoria.integral.repository.adminpersonal.NPersonalCRepository;
import com.defensoria.integral.repository.adminpersonal.PersonalPerfilesRepository;

@RestController
public class PermisosRestController {

	@Autowired
	NPersonalCRepository nPersonalCRepository;
	@Autowired
	PersonalPerfilesRepository personalPerfilesRepository;

	@PostMapping("/buscarpersonal")
	public ResponseEntity<?> ListaPersonal(@RequestBody NPersonalC npersonalC) {
		npersonalC.setActivo("S");
		List<NPersonalC> listaPersonal = nPersonalCRepository.ListarPersonal(npersonalC.getNombreCompleto());
		return new ResponseEntity<List<NPersonalC>>(listaPersonal, HttpStatus.OK); 

	}
	

}
