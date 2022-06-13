package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
@Query("SELECT p FROM Partida p WHERE clavePartida like:clavePartida AND descPartida like:descPartida")
public List<Partida> find(@Param("clavePartida") String clavePartida,@Param("descPartida")String descPartida);
@Query("SELECT p FROM Partida p WHERE activo like:activo")
public List<Partida> findByActivo(@Param("activo") String activo);


}
