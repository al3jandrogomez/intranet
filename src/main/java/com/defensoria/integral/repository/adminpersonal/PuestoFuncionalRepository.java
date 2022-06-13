package com.defensoria.integral.repository.adminpersonal;

import java.util.List;

import com.defensoria.integral.model.adminpersonal.PuestoFuncional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PuestoFuncionalRepository  extends JpaRepository<PuestoFuncional, Long>{

    List<PuestoFuncional> findByDescPuestoFuncionalLike(String descPuestoFuncional);
    
}
