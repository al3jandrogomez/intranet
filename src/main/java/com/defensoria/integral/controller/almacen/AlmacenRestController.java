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

import com.defensoria.integral.model.almacen.Entrada;
import com.defensoria.integral.repository.almacen.EntradaRepository;

@RestController
public class AlmacenRestController {
	@Autowired
	EntradaRepository entradaRepository ;
	
	@PostMapping (value="/entradasave")
	public ResponseEntity<?> EntradaSave(@RequestBody Entrada entrada) throws ParseException{
		
		if(entrada.getIdEntrada()== null){
		
		
		if(entradaRepository.find(entrada.getProveedor().getCveProveedor() ,entrada.getTipoDocumento().getCveTipoDocumento(),entrada.getNoDocumento()).size()==0){
			System.err.println("guardando registro");
			entrada.setActivo("S");
			entradaRepository.save(entrada);
			return new ResponseEntity<Entrada>(entrada, HttpStatus.OK);
		}else{
			return new ResponseEntity<Entrada>(entrada, HttpStatus.OK);
		}
		}else{
			entrada.setActivo("S");
			entradaRepository.save(entrada);
			return new ResponseEntity<Entrada>(entrada, HttpStatus.OK);
		}
		
		
	}
	@PostMapping (value="/buscarEntrada")
	public ResponseEntity<?> buscarEntrada(@RequestBody Entrada entrada) throws ParseException{
		Example<Entrada> example = Example.of(entrada); 
		List<Entrada> resultado = entradaRepository.findAll(example);
		
		
		return new ResponseEntity<List<Entrada>>(resultado, HttpStatus.OK);
		
		
	}
	

}
