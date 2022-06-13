package com.defensoria.integral.controller.almacen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.defensoria.integral.model.almacen.Unidades;
import com.defensoria.integral.repository.almacen.UnidadRepository;

@RestController
public class UnidadesRestcontroller {
	@Autowired
	UnidadRepository unidadRepository;
	
	@PostMapping(value="/guardarunidades")
	public ResponseEntity<?> GuardarUnidades(@RequestBody Unidades unidades){
		
		if(unidades.getCveUnidad()==null){
			if(unidadRepository.find(unidades.getDescUnidad()).size()==0){
				unidades.setActivo("S");
				unidadRepository.save(unidades);
				return new ResponseEntity<Unidades>(unidades, HttpStatus.OK);
			}else{
				return new ResponseEntity<Unidades>(unidades, HttpStatus.OK);
			}
		}
		else{
			unidades.setActivo("S");
			unidadRepository.save(unidades);
			return new ResponseEntity<Unidades>(unidades, HttpStatus.OK);
		}
	}
	@PostMapping(value="/buscarunidades")
	public ResponseEntity<?> buscarUnidades(@RequestBody Unidades unidades){
		
		Example<Unidades>example = Example.of(unidades);
		List<Unidades> resultado = unidadRepository.findAll(example);
		return new ResponseEntity<List<Unidades>>(resultado,HttpStatus.OK);
		
	}

}
