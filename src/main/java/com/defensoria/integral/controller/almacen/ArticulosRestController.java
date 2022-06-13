package com.defensoria.integral.controller.almacen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.almacen.Articulos;

import com.defensoria.integral.repository.almacen.ArticuloRepository;

@RestController
public class ArticulosRestController {
	@Autowired
	ArticuloRepository articuloRepository;
	
	@PostMapping (value="/guardararticulo")
	public ResponseEntity<?> GuardarArticulo(@RequestBody Articulos articulo){
		if(articulo.getCveArticulo()==null){
			System.err.println("el un articulo nuevo"+articuloRepository.find(articulo.getClave(), articulo.getVersion(), articulo.getDescArticulo(), articulo.getPartidas().getCvePartida()).toString());
			if(articuloRepository.find(articulo.getClave(), articulo.getVersion(), articulo.getDescArticulo(), articulo.getPartidas().getCvePartida()).size()==0){
				articulo.setActivo("S");
				articuloRepository.save(articulo);
				return new ResponseEntity<Articulos>(articulo, HttpStatus.OK);
			}else{
				return new ResponseEntity<Articulos>(articulo, HttpStatus.OK);
			}
		}
		else{
			articulo.setActivo("S");
			articuloRepository.save(articulo);
			return new ResponseEntity<Articulos>(articulo, HttpStatus.OK);
		}
		 
		
	}
	
	@PostMapping (value="/buscararticulos")
	public ResponseEntity<?>buscarArticulos(@RequestBody Articulos articulos){
		Example<Articulos> example = Example.of(articulos);
		
		List<Articulos> resultado = articuloRepository.findAll(example,  Sort.by(Direction.ASC, "descArticulo"));
		return new ResponseEntity<List<Articulos>>(resultado, HttpStatus.OK);
		
	}
	@PostMapping (value="/buscarlikearticulos")
	public ResponseEntity<?>buscarLikeArticulos(@RequestBody Articulos articulos){
		
		List<Articulos> resultado = articuloRepository.findByDescArticulo(articulos.getDescArticulo());
		return new ResponseEntity<List<Articulos>>(resultado, HttpStatus.OK);
		
	}
	

}
