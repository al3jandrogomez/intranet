package com.defensoria.integral.controller.almacen;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.almacen.Programa;
import com.defensoria.integral.repository.almacen.ProgramaRepository;

@RestController
public class ProgramasRestController {
	@Autowired
	ProgramaRepository programaRepository;
	
	@PostMapping (value="/guardarprograma")
	public ResponseEntity<?> GuardarPrograma(@RequestBody Programa programa) throws ParseException{
		
		if(programa.getCvePrograma()== null){
		
		
		if(programaRepository.find(programa.getDescPrograma()).size()==0){
			System.err.println("guardando registro");
			programa.setActivo("S");
			programaRepository.save(programa);
			return new ResponseEntity<Programa>(programa, HttpStatus.OK);
		}else{
			return new ResponseEntity<Programa>(programa, HttpStatus.OK);
		}
		}else{
			programa.setActivo("S");
			programaRepository.save(programa);
			return new ResponseEntity<Programa>(programa, HttpStatus.OK);
		}
		
		
	}
	@PostMapping (value="/buscarprograma")
	public ResponseEntity<?> buscarPrograma(@RequestBody Programa programa) throws ParseException{
		Example<Programa> example = Example.of(programa); 
		List<Programa> resultado = programaRepository.findAll(example);
		
		
		return new ResponseEntity<List<Programa>>(resultado, HttpStatus.OK);
		
		
	}

}
