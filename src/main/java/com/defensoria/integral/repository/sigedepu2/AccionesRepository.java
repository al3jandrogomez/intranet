package com.defensoria.integral.repository.sigedepu2;

import java.util.List;

import com.defensoria.integral.model.sigedepu2.Accion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccionesRepository  extends JpaRepository<Accion,Long>{

    List<Accion> findByDescAccionLike(String descAccion);
    
}
