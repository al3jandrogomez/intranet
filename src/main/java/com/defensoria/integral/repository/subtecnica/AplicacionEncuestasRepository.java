/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.subtecnica;

import com.defensoria.integral.model.subtecnica.AplicacionEncuesta;
import com.defensoria.integral.model.subtecnica.GraficaPreguntas;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface AplicacionEncuestasRepository extends JpaRepository<AplicacionEncuesta, Long> {

    @Query("select  new com.defensoria.integral.model.subtecnica.GraficaPreguntas(p.idPregunta, p.descPregunta,r.descRespuesta,count(r.idRespuesta))from AplicacionEncuesta ae"
            + " inner join ae.preguntasrespuestas  pr "
            + " inner join pr.pregunta p "
            + " inner join pr.respuesta r "
            + " inner join ae.encuesta e "
            + " where e.idEncuesta= (:idEncuesta) "
            + " group by p.idPregunta,r.idRespuesta")

    List<GraficaPreguntas> graficaPreguntas(@Param("idEncuesta") Integer idEncuesta);
    @Query("select  new com.defensoria.integral.model.subtecnica.GraficaPreguntas(p.idPregunta, p.descPregunta,r.descRespuesta,count(r.idRespuesta))from AplicacionEncuesta ae"
            + " inner join ae.preguntasrespuestas  pr "
            + " inner join pr.pregunta p "
            + " inner join pr.respuesta r "
            + " inner join ae.encuesta e "
            + " where e.idEncuesta= (:idEncuesta) "
            + " and ae.fechaRegistro>= :fechaRegistro "
            + " and ae.fechaRegistro<= :fechaRegistro2 "
            + " and ae.activo='S' "
            + " group by p.idPregunta,r.idRespuesta")

    List<GraficaPreguntas> graficaPreguntas(@Param("idEncuesta") Integer idEncuesta,@Param("fechaRegistro") Date fechaRegistro,@Param("fechaRegistro2") Date fechaRegistroi);

}
