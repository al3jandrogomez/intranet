package com.defensoria.integral.controller.almacen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.almacen.Proveedor;
import com.defensoria.integral.repository.almacen.ProveedorRepository;

@RestController
public class ProveedoresRestController {

	@Autowired
	ProveedorRepository proveedorRepository;

	@PostMapping(value = "/guardarproveedores")
	public ResponseEntity<?> GuardarProveedor(@RequestBody Proveedor proveedor) {
		if (proveedor.getCveProveedor() == null) {

			if (proveedorRepository.findActivo(proveedor.getDescProveedor()).size() == 0) {

				System.err.println("guardando registro");
				proveedor.setActivo("S");
				proveedorRepository.save(proveedor);
				return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
			} else {
				return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
			}
		} else {
			proveedor.setActivo("S");
			proveedorRepository.save(proveedor);
			return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
		}
	}
	@PostMapping("/buscarproveedores")
	public ResponseEntity<?> buscarProveedores(@RequestBody Proveedor proveedor) {
		Example<Proveedor> example = Example.of(proveedor);
		List<Proveedor> resultado = proveedorRepository.findAll(example);
		
		
		return new ResponseEntity <List<Proveedor>>(resultado,HttpStatus.OK);
	}
}
