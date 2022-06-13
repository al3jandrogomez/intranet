package com.defensoria.integral.helpers;

import org.springframework.beans.factory.annotation.Autowired;

import com.defensoria.integral.model.almacen.EstatusRequisiciones;
import com.defensoria.integral.model.almacen.MovRequisiciones;
import com.defensoria.integral.model.almacen.Requisiciones;
import com.defensoria.integral.repository.almacen.MovRequisicionesRepository;

public class CambiarEstatusRequisiciones {
	@Autowired
	MovRequisicionesRepository movRequisicionRepository;
	
	public void CambiarEstatusRequisicion(Requisiciones requisicion,Integer cveEstatusRequisicion){
		MovRequisiciones movRequisiciones= new MovRequisiciones();
		EstatusRequisiciones EstRequisiciones= new EstatusRequisiciones();
		EstRequisiciones.setCveEstatusRequisicion(cveEstatusRequisicion);
		//movRequisiciones.setRequisicion(requisicion);
		movRequisiciones.setActivo("S");
		movRequisiciones.setEstatusRequisicion(EstRequisiciones);
		movRequisiciones.setCveUsuario(1);
		movRequisicionRepository.save(movRequisiciones);
	}
}
