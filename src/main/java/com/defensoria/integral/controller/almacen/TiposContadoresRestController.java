package com.defensoria.integral.controller.almacen;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.almacen.TiposContadores;
import com.defensoria.integral.repository.almacen.TiposContadoresRepository;

@RestController
@Transactional
public class TiposContadoresRestController {

	@Autowired
	TiposContadoresRepository tiposContadoresRepository;

	@PostMapping("/guardartipocontador")
	public ResponseEntity<?> GuardarTiposContadores(@RequestBody TiposContadores tipoContador) {
		Boolean insertar = false;
		if (tipoContador.getCveTipoContador() == null) {
			if (tiposContadoresRepository.find(tipoContador.getDescTipoContador(), "S").size() == 0) {
				insertar = true;
				tipoContador.setActivo("S");
			}

		}else{
			tipoContador.setActivo("S");
			insertar=true;
		}

		if (insertar) {
			tiposContadoresRepository.save(tipoContador);
		}
		return new ResponseEntity<TiposContadores>(tipoContador, HttpStatus.OK);
	}

	@PostMapping("/consultartipocontador")
	public ResponseEntity<?> ConsultarTiposContadores(@RequestBody TiposContadores tipoContador) {
		List<TiposContadores> resultado = tiposContadoresRepository.findAll(Example.of(tipoContador));
		return new ResponseEntity<List<TiposContadores>>(resultado, HttpStatus.OK);
	}

	@PostMapping("/eliminartipocontador")
	public ResponseEntity<?> EliminarTiposContadores(@RequestBody TiposContadores tipoContador) {
		tipoContador.setActivo("N");
		tiposContadoresRepository.save(tipoContador);

		return new ResponseEntity<TiposContadores>(tipoContador, HttpStatus.OK);
	}

}
