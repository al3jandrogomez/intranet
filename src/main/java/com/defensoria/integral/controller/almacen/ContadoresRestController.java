package com.defensoria.integral.controller.almacen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.almacen.Contadores;
import com.defensoria.integral.repository.almacen.ContadoresRepository;

@RestController
@Transactional
public class ContadoresRestController {
	
@Autowired
ContadoresRepository contadoresRepository;
@PostMapping("/guardarcontador")
public ResponseEntity<?> GuardarContadores(@RequestBody Contadores contador) {
	

	if(contadoresRepository.findExistencia(contador.getTipoContador().getCveTipoContador(),contador.getAdscripcion().getCveAdscripcion(), contador.getAnio()).size()<1){
		
		contador = this.GenerarContador(contador);
	}
//	
	return new ResponseEntity<Contadores>(contador, HttpStatus.OK);
}
@PostMapping("/consultarcontador")
public ResponseEntity<?> ConsultarTiposContadores(@RequestBody Contadores contador) {
	List<Contadores> result = contadoresRepository.findAll(Example.of(contador));
	
	return new ResponseEntity<List<Contadores>>(result, HttpStatus.OK);
}
@PostMapping("/eliminarcontador")
public ResponseEntity<?> EliminarContadores(@RequestBody Contadores contador) {
	
	
	return new ResponseEntity<Contadores>(contador, HttpStatus.OK);
}

public Contadores GenerarContador(Contadores contador){
	List<Contadores>result=contadoresRepository.findExistencia(contador.getTipoContador().getCveTipoContador(),contador.getAdscripcion().getCveAdscripcion(), contador.getAnio());
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	if(result.size()>0){
	result.get(0).setNumero(result.get(0).getNumero()+1);
	contador= contadoresRepository.save(result.get(0));
	}else{
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		String[] fecha = dateFormat.format(date).toString().split("-");
		String[] dia = fecha[2].toString().split(" ");
		
		
		contador.setNumero(1);
		contador.setAnio(Integer.parseInt(fecha[0]));
		contador.setActivo("S");
		contador= contadoresRepository.save(contador);
		
	}
	
	return contador;
}
public Contadores IncrementaContador(Contadores contador){
	contador= contadoresRepository.save(contador);
	return contador;
}

}
