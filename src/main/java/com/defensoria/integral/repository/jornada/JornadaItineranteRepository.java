package com.defensoria.integral.repository.jornada;

import com.defensoria.integral.model.jornadas.JornadaItinerante;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JornadaItineranteRepository extends JpaRepository<JornadaItinerante,Long> {
    
}
