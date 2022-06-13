package com.defensoria.integral.controller.almacen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.almacen.Distribucion;
import com.defensoria.integral.model.almacen.Requisiciones;
import com.defensoria.integral.model.almacen.Contadores;
import com.defensoria.integral.model.almacen.DetalleDistribucion;

import com.defensoria.integral.model.almacen.TiposContadores;
import com.defensoria.integral.repository.almacen.DetalleDistribucionRepository;
import com.defensoria.integral.repository.almacen.DistribucionRepository;
import com.defensoria.integral.repository.almacen.RequisicionesRepository;

@RestController

public class DistribucionesRestController {
	@Autowired
	ContadoresRestController contadorController;
	@Autowired
	DetalleDistribucionRepository detalleDistribucionRepository;
	@Autowired
	DistribucionRepository distribucionRepository;
	@Autowired
	RequisicionesRepository requisicionesRepository;
	@Autowired
	private HttpSession httpSession;
	
	@PostMapping("/buscardistribuciones")
	public ResponseEntity<?> BuscarDistribuciones() {
		Distribucion distribucion = new Distribucion();
		Integer cveAdscripcion = Integer.parseInt(getHttpSession().getAttribute("cveAdscripcion").toString());
		Adscripciones ads = new Adscripciones ();
		ads.setCveAdscripcion(cveAdscripcion);
		distribucion.setActivo("S");
		distribucion.setAdscripcion(ads);
		
		List <Distribucion >resultado = distribucionRepository.findAll(Example.of(distribucion));
		
		return new ResponseEntity<List<Distribucion>>(resultado, HttpStatus.OK);
	}
	@PostMapping("/buscardistribuciones2")
	public ResponseEntity<?> BuscarDistribuciones2(@RequestBody Adscripciones ads) {
		
		System.out.println("clave descripcion recibida = "+ads.getCveAdscripcion());
		Distribucion distribucion = new Distribucion();
		distribucion.setActivo("S");
		distribucion.setAdscripcion(ads);
		List <Distribucion> lista = new ArrayList<>();
		List <Distribucion >resultado = distribucionRepository.findAll(Example.of(distribucion));
		if(resultado.size()>0)
		for (Distribucion  rs :resultado){
			if(rs.getNumeroDistribucion()!=null){
				lista.add(rs);
			}
			
		}
		
		return new ResponseEntity<List<Distribucion>>(lista, HttpStatus.OK);
	}
	@PostMapping("/verificardistribuciones")
	public ResponseEntity<?> VerificarDistribuciones() {
		
		Integer cveAdscripcion = Integer.parseInt(getHttpSession().getAttribute("cveAdscripcion").toString());
		Adscripciones ads = new Adscripciones ();
		ads.setCveAdscripcion(cveAdscripcion);
		Requisiciones rq = new Requisiciones ();
		rq.setAdscripcion(ads);
		rq.setActivo("S");
	
		List<Requisiciones> resultado = requisicionesRepository.findAll(Example.of(rq),Sort.by(Direction.DESC, "idRequisicion"));
		if(resultado.size()>0){
			
		}
		
//		Distribucion distribucion = new Distribucion();
//		Integer cveAdscripcion = Integer.parseInt(getHttpSession().getAttribute("cveAdscripcion").toString());
//		Adscripciones ads = new Adscripciones ();
//		ads.setCveAdscripcion(cveAdscripcion);
//		distribucion.setActivo("S");
//		distribucion.setAdscripcion(ads);
//		
//		List <Distribucion >resultado = distribucionRepository.findAll(Example.of(distribucion));
		
		return new ResponseEntity<List<Requisiciones>>(resultado, HttpStatus.OK);
	}
	@Transactional
	@PostMapping("/guardardistribucion")
	public ResponseEntity<?> GuardarDistribucion(@RequestBody Distribucion distribucion) {
		//Distribucion distribucion = new Distribucion();
		
		distribucion.setActivo("S");
			
		
		Integer cveAdscripcion = Integer.parseInt(getHttpSession().getAttribute("cveAdscripcion").toString());
		Adscripciones ads = new Adscripciones ();
		ads.setCveAdscripcion(cveAdscripcion);
		distribucion.setAdscripcion(ads);
		distribucionRepository.save(distribucion);
		
		
		return new ResponseEntity<Distribucion>(distribucion, HttpStatus.OK);
	}
	
	@PostMapping("/guardarobservaciondistribucion")
	public ResponseEntity<?> GuardarObservacionDistribucion(@RequestBody Distribucion distribucion) {
		//Distribucion distribucion = new Distribucion();
		Distribucion dis = new Distribucion();
		dis.setIdDistribucion(distribucion.getIdDistribucion());
		List<Distribucion> distribuciones = distribucionRepository.findAll(Example.of(dis));
		if(distribuciones.size()>0){
			Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
			Personal personal = new Personal();
			personal.setCveUsuario(cveUsuario);
			dis=distribuciones.get(0);
			dis.setPersonal(personal);
			dis.setObservaciones(distribucion.getObservaciones());
			distribucionRepository.save(dis);
		}
		
		return new ResponseEntity<Distribucion>(distribucion, HttpStatus.OK);
	}
	@Transactional
	@PostMapping("/terminardistribucion")
	public ResponseEntity<Distribucion>TerminarDistribucion(@RequestBody Distribucion distribucion){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		String[] fecha = dateFormat.format(date).toString().split("-");
//		String[] dia = fecha[2].toString().split(" ");
		Adscripciones adscripcion = new Adscripciones();
		TiposContadores tipoContador = new TiposContadores();
		Contadores contador = new Contadores();
		adscripcion.setCveAdscripcion(80);
		tipoContador.setCveTipoContador(2);
		contador.setAdscripcion(adscripcion);
		contador.setAnio(Integer.parseInt(fecha[0]));
		contador.setTipoContador(tipoContador);
		contador = contadorController.GenerarContador(contador);
		distribucion.setNumeroDistribucion(contador.getNumero());
		distribucion.setAnioDistribucion(contador.getAnio());
		distribucion.setActivo("S");
		Distribucion dis = new Distribucion();
		dis.setIdDistribucion(distribucion.getIdDistribucion());
		
		List<Distribucion> distribuciones = distribucionRepository.findAll(Example.of(dis));
		if(distribuciones.size()>0){
			Requisiciones rq = new Requisiciones();
			Adscripciones ads = new Adscripciones();
			
			ads.setCveAdscripcion(distribuciones.get(0).getAdscripcion().getCveAdscripcion());
			rq.setAdscripcion(ads);
			
			List<Requisiciones> resultado = requisicionesRepository.findAll(Example.of(rq), Sort.by(Direction.DESC, "idRequisicion"));
			Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
			Personal personal = new Personal();
			personal.setCveUsuario(cveUsuario);
			dis=distribuciones.get(0);
			dis.setPersonal(personal);
			dis.setNumeroDistribucion(contador.getNumero());
			dis.setAnioDistribucion(contador.getAnio());
			dis.setActivo("S");
			dis.setIdRequisicion(resultado.get(0).getIdRequisicion());
			distribucionRepository.save(dis);
		}
		
		return new ResponseEntity<Distribucion>(distribucion,HttpStatus.OK);
	}
	@Transactional
	@PostMapping("/guardardetalledistribucion")
	public ResponseEntity<DetalleDistribucion> GuardarDetalleDistribucion(@RequestBody DetalleDistribucion detalleDistribucion){
		detalleDistribucion.setActivo("S");
		
		System.out.println("Clave Usuario recibida"+detalleDistribucion.getPersonal().getCveUsuario());
		detalleDistribucion = detalleDistribucionRepository.save(detalleDistribucion);
		return new ResponseEntity<DetalleDistribucion>(detalleDistribucion,HttpStatus.OK);
		
	}
	
	@PostMapping("/buscardetalledistribucion")
	public ResponseEntity<List<DetalleDistribucion>> BuscarDetalleDistribucion(@RequestBody DetalleDistribucion detalleDistribucion){
		System.out.println(detalleDistribucion.getIdDetalleDistribucion());
		detalleDistribucion.setActivo("S");
		List<DetalleDistribucion> resultado =  detalleDistribucionRepository.findAll(Example.of(detalleDistribucion));
		
		List<DetalleDistribucion> resultado2=resultado;
		for(DetalleDistribucion detalle:resultado2){
			detalle.getPersonal().setContrasena("");
			detalle.getPersonal().setFechaNacimiento(new Date());
			detalle.getPersonal().setCurp("");
			detalle.getPersonal().setCveIssemym("");
//			detalle.getPersonal().setCveUsuario(null);
			detalle.getPersonal().setEmail("");
			detalle.getPersonal().setCveServidorPub("");
			detalle.getPersonal().setRfc("");
			detalle.getPersonal().setUsuario("");
			
			
		}
		return new ResponseEntity<List<DetalleDistribucion>>(resultado,HttpStatus.OK);
		
	}
	@PostMapping("/eliminardetalledistribucion")
	public ResponseEntity<DetalleDistribucion> EliminarDetalleDistribucion(@RequestBody DetalleDistribucion detalleDistribucion){
		
		List<DetalleDistribucion> resultado = detalleDistribucionRepository.findAll(Example.of(detalleDistribucion));
		System.out.println("numero de registros "+resultado.size());
		if(resultado.size() >0){
			resultado.get(0).setActivo("N");
			detalleDistribucion = detalleDistribucionRepository.save(resultado.get(0));
		}
		return new ResponseEntity<DetalleDistribucion>(detalleDistribucion,HttpStatus.OK);
		
	}
	public void EliminarDetalleDistribucion2(DetalleDistribucion detalleDistribucion){
		
		List<DetalleDistribucion> resultado = detalleDistribucionRepository.findAll(Example.of(detalleDistribucion));
		System.out.println("numero de registros "+resultado.size());
		if(resultado.size() >0){
			
			for (DetalleDistribucion dtd : resultado){
				dtd.setActivo("N");
				detalleDistribucion = detalleDistribucionRepository.save(dtd);
			}
			
		}
		
	}
	@PostMapping("/eliminardistribucion")
	public ResponseEntity<Distribucion> EliminarDistribucion(@RequestBody Distribucion distribucion){
		
		List<Distribucion> resultado = distribucionRepository.findAll(Example.of(distribucion));
		if(resultado.size()>0){
			
			DetalleDistribucion dtd = new DetalleDistribucion();
			dtd.setDistribucion(distribucion);
			EliminarDetalleDistribucion2(dtd);
			resultado.get(0).setActivo("N");
			
			distribucion = distribucionRepository.save(resultado.get(0));
		}
		return new ResponseEntity<Distribucion>(distribucion,HttpStatus.OK);
		
	}
	

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	
	
}
