package com.defensoria.integral.repository.app;

import com.defensoria.integral.model.app.Registro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro,Long> {
    
}
