/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.subtecnica;

import com.defensoria.integral.model.subtecnica.Cita;
import com.defensoria.integral.model.subtecnica.CountHoras;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alec1_000
 */
public interface CitasRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByFechaCitaEqualsOrderByHoraCitaAsc(Date fechaCita);
 @Query("SELECT c FROM Cita c inner join c.representado r WHERE  CONCAT(r.nombre,' ',r.paterno,' ',r.materno) like:nombre and c.activo='S' and c.estatus<>'R' ")
 List<Cita> listaRepresentadosCitas(@Param("nombre") String nombre);
    @Query("SELECT new com.defensoria.integral.model.subtecnica.CountHoras(c.horaCita as horas, COUNT(c.horaCita) as conteo) FROM Cita c  WHERE c.fechaCita=:fechaCita and c.estatus ='G' and c.cveRegion=:cveRegion GROUP BY c.horaCita")
    List<CountHoras> countTotalCitasHorasPorFecha(@Param("fechaCita") Date fechaCita,@Param("cveRegion") Integer cveRegion);

}
