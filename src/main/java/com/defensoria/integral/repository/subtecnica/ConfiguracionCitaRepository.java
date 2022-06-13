/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.subtecnica;

import com.defensoria.integral.model.subtecnica.ConfiguracionCita;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alec1_000
 */
public interface ConfiguracionCitaRepository extends JpaRepository<ConfiguracionCita, Long>{
    List<ConfiguracionCita> findByFechaAplicacionLessThanEqualAndCveRegionEqualsAndActivoEqualsOrderByIdConfiguracionCitaDesc(Date fechaAplicacion,Integer cveRegion,String activo);
    @Query("SELECT cc from ConfiguracionCita cc where cc.fechaAplicacion<=:fechaAplicacion and cc.cveRegion=:cveRegion and activo='S' order by cc.fechaAplicacion desc")
    List<ConfiguracionCita> findConfiguracionCitaByFechaAplicacion(@Param("fechaAplicacion") Date fechaAplicacion,@Param("cveRegion")Integer cveRegion);
    @Query("SELECT cc from ConfiguracionCita cc where cc.fechaAplicacion<=:fechaAplicacion and cc.cveRegion=:cveRegion and cc.numAsesores>0 and tipoConfiguracion='E' and activo='S' and (timediff(:hora,cc.horaInicio))>0 order by timediff(:hora,cc.horaInicio) asc")
    List<ConfiguracionCita> findConfiguracionCitaByFechaAplicacionPorHora(@Param("fechaAplicacion") Date fechaAplicacion,@Param("cveRegion")Integer cveRegion,@Param("hora") Time hora);
    @Query("SELECT cc from ConfiguracionCita cc where cc.fechaAplicacion<=:fechaAplicacion and cc.cveRegion=:cveRegion and cc.numAsesores>0 and tipoConfiguracion='E' and activo='S' order by cc.fechaAplicacion desc") 
    List<ConfiguracionCita> findConfiguracionCitaParaHoras(@Param("fechaAplicacion") Date fechaAplicacion,@Param("cveRegion")Integer cveRegion);
}
