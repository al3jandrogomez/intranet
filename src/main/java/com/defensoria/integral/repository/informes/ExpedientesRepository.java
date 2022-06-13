package com.defensoria.integral.repository.informes;

import com.defensoria.integral.model.informes.Expediente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpedientesRepository extends JpaRepository<Expediente,Long> {
    
}
