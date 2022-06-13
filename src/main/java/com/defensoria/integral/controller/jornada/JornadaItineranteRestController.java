package com.defensoria.integral.controller.jornada;

import java.util.List;

import com.defensoria.integral.model.jornadas.JornadaItinerante;
import com.defensoria.integral.repository.jornada.JornadaItineranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class JornadaItineranteRestController  {
    @Autowired
    JornadaItineranteRepository jornadaItineranteRepository;

    @PostMapping(value = "buscarjornadas")
	public ResponseEntity<?> BuscarJornadas(@RequestBody JornadaItinerante jt) {
        System.out.println("Buscando Jornadas "+jt.getNumMes());
		
		return new ResponseEntity<List<JornadaItinerante>>(jornadaItineranteRepository.findAll(Example.of(jt),Sort.by(Sort.Direction.DESC, "numSemana")), HttpStatus.OK); 

	}
    
}
