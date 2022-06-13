package com.defensoria.integral.repository.app;

import com.defensoria.integral.model.app.Queja;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuejasRepository extends JpaRepository<Queja,Long> {
    
}
