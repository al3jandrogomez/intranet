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


import com.defensoria.integral.model.almacen.TiposDocumentos;
import com.defensoria.integral.repository.almacen.TiposDocumentosRepository;

@RestController
public class TiposDocumentosRestController {
	@Autowired
	TiposDocumentosRepository tiposDocumentosRepository;
	
	@PostMapping (value="/guardartiposdocumentos")
	public ResponseEntity<?> guardarTiposDocumentos(@RequestBody TiposDocumentos tiposDocumentos) throws ParseException{
		
		if(tiposDocumentos.getCveTipoDocumento()== null){
		
		
		if(tiposDocumentosRepository.find(tiposDocumentos.getDescTipoDocumento()).size()==0){
			
			System.err.println("guardando registro");
			tiposDocumentos.setActivo("S");
			tiposDocumentosRepository.save(tiposDocumentos);
			return new ResponseEntity<TiposDocumentos>(tiposDocumentos, HttpStatus.OK);
		}else{
			return new ResponseEntity<TiposDocumentos>(tiposDocumentos, HttpStatus.OK);
		}
		}else{
			tiposDocumentos.setActivo("S");
			tiposDocumentosRepository.save(tiposDocumentos);
			return new ResponseEntity<TiposDocumentos>(tiposDocumentos, HttpStatus.OK);
		}
		
		
	}

	@PostMapping("/buscartiposdocumentos")
	public ResponseEntity<?> catalogoTiposDocumentos(@RequestBody TiposDocumentos tiposDocumentos) {
		Example<TiposDocumentos> example = Example.of(tiposDocumentos);
		List<TiposDocumentos> resultado = tiposDocumentosRepository.findAll(example);
		
		
		return new ResponseEntity <List<TiposDocumentos>>(resultado,HttpStatus.OK);
	}
}
