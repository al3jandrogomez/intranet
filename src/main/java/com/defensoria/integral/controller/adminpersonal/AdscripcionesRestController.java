package com.defensoria.integral.controller.adminpersonal;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.repository.adminpersonal.AdscripcionesRepository; 

@RestController
public class AdscripcionesRestController {
	
	@Autowired
	AdscripcionesRepository adscripcionesRepository;
	
	@PostMapping("/guardaradscripcion")
	public ResponseEntity<?> GuardarAdscripciones(@RequestBody Adscripciones adscripcion) {
		
		
		return new ResponseEntity<Adscripciones>(adscripcion, HttpStatus.OK);
	}
	@PostMapping("/consultaradscripciones")
	public ResponseEntity<?> ConsultarAdscripciones(@RequestBody Adscripciones adscripcion) {
		
		List<Adscripciones> result= adscripcionesRepository.findAll(Example.of(adscripcion));
		//List<Adscripciones> result= adscripcionesRepository.findAll(pageable);
		
		return new ResponseEntity<List<Adscripciones>>(result, HttpStatus.OK);
	}
	@PostMapping("/eliminaradscripcion")
	public ResponseEntity<?> EliminarAdscripciones(@RequestBody Adscripciones adscripcion) {
		
		
		return new ResponseEntity<Adscripciones>(adscripcion, HttpStatus.OK);
	}
        
        @PostMapping("/buscaradscripciones")
	public ResponseEntity<?> BuscarAdscripciones(@RequestBody Adscripciones adscripcion) {
            System.out.println("%"+adscripcion.getDescAdscripcion()+"%");
		List<Adscripciones> result= adscripcionesRepository.findDescAdscripcion("%"+adscripcion.getDescAdscripcion()+"%");
		
		return new ResponseEntity <List<Adscripciones>>(result, HttpStatus.OK);
	}
        
        

}
