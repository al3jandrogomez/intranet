/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.sigedepu2;


import com.defensoria.integral.model.adminpersonal.GeneryObjectCount;
import com.defensoria.integral.model.sigedepu2.Carpeta;
import com.defensoria.integral.model.sigedepu2.CarpetaRepresentado;
import com.defensoria.integral.model.sigedepu2.TiposCarpetas;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface CarpetasRepository extends JpaRepository<Carpeta, Long>{
    
    @Query("SELECT cr FROM CarpetaRepresentado cr INNER JOIN cr.representado as r inner join r.delitosRepresentados dr  WHERE cr.carpeta.fechaRegistro>=:fechaInicio and cr.carpeta.fechaRegistro<=:fechaFinal and cr.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and cr.carpeta.tipoCarpeta.idTipoCarpeta=:idTipoCarpeta and cr.carpeta.activo='S'  ")
    List<CarpetaRepresentado> countCarpetas(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("idTipoCarpeta") Integer idTipoCarpeta,@Param("cveDistrito") Integer cveDistrito); 
    @Query("SELECT new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(cr.carpeta.idCarpeta),cr.carpeta.adscripcion.municipio.descMunicipio,'',month(cr.carpeta.fechaRegistro),dr.delito.descDelito) FROM CarpetaRepresentado cr INNER JOIN cr.representado as r inner join r.delitosRepresentados dr  WHERE cr.carpeta.fechaRegistro>=:fechaInicio and cr.carpeta.fechaRegistro<=:fechaFinal and cr.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and cr.carpeta.tipoCarpeta.idTipoCarpeta=:idTipoCarpeta and cr.carpeta.activo='S' GROUP BY month(cr.carpeta.fechaRegistro) ORDER BY month(cr.carpeta.fechaRegistro) asc")

    List<GeneryObjectCount> countCarpetasVitralPorMes(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("idTipoCarpeta") Integer idTipoCarpeta,@Param("cveDistrito") Integer cveDistrito);  
    @Query("SELECT new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(cr.carpeta.idCarpeta),cr.carpeta.adscripcion.municipio.descMunicipio,'',month(cr.carpeta.fechaRegistro))  FROM CarpetaRepresentado cr INNER JOIN cr.representado as r inner join r.delitosRepresentados dr  WHERE cr.carpeta.fechaRegistro>=:fechaInicio and cr.carpeta.fechaRegistro<=:fechaFinal and cr.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and cr.carpeta.tipoCarpeta.idTipoCarpeta=:idTipoCarpeta and cr.carpeta.activo='S' GROUP BY month(cr.carpeta.fechaRegistro) ORDER BY month(cr.carpeta.fechaRegistro) asc")
    List<GeneryObjectCount> countCarpetasVitralPorMes2(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("idTipoCarpeta") Integer idTipoCarpeta,@Param("cveDistrito") Integer cveDistrito);    
    List<Carpeta> findByNucLikeAndActivoEqualsAndTipoCarpetaEquals(String nuc, String activo,TiposCarpetas tipoCarpeta);
    List<Carpeta> findByNumAdminLikeAndActivoEquals(String numAdmin, String activo);
    // List<Carpeta> findByNumeroEqualsAndAnioEqualsAndAdscripcionEqualsAndTipoCarpetaEqualsAndActivoEquals(Integer numero,Integer anio,Adscripciones adscripcion,TiposCarpetas tipoCarpeta, String activo);
    @Query("SELECT c FROM Carpeta c WHERE c.numero=:numero and c.anio =:anio and c.adscripcion.cveAdscripcion=:cveAdscripcion and c.tipoCarpeta.idTipoCarpeta=:idTipoCarpeta and Activo=:activo")
    List<Carpeta> findByCarpetasPenales(@Param("numero") Integer numero,@Param("anio") Integer anio, @Param("cveAdscripcion") Integer cveAdscripcion,@Param("idTipoCarpeta") Integer idTipoCarpeta,@Param("activo")String activo); 
    
    @Query("SELECT new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(cr.carpeta.idCarpeta),cr.carpeta.adscripcion.municipio.descMunicipio,'',month(cr.carpeta.fechaRegistro),dr.delito.descDelito) FROM CarpetaRepresentado cr INNER JOIN cr.representado as r inner join r.delitosRepresentados dr  WHERE cr.carpeta.fechaRegistro>=:fechaInicio and cr.carpeta.fechaRegistro<=:fechaFinal and cr.carpeta.adscripcion.municipio.cveMunicipio=:cveMunicipio and cr.carpeta.tipoCarpeta.idTipoCarpeta=:idTipoCarpeta and cr.carpeta.activo='S'  GROUP BY (dr.delito) ORDER BY month(cr.carpeta.fechaRegistro) asc, count(cr.carpeta.idCarpeta) DESC")
    List<GeneryObjectCount> countCarpetasVitralPorMesConDelito(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("idTipoCarpeta") Integer idTipoCarpeta,@Param("cveMunicipio") Integer cveMunicipio);  
    
   
    @Query("SELECT new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(cr.carpeta.idCarpeta),cr.carpeta.adscripcion.municipio.descMunicipio,'',month(cr.carpeta.fechaRegistro)) FROM CarpetaRepresentado cr INNER JOIN cr.representado as r inner join r.delitosRepresentados dr  WHERE cr.carpeta.fechaRegistro>=:fechaInicio and cr.carpeta.fechaRegistro<=:fechaFinal and cr.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and cr.carpeta.tipoCarpeta.idTipoCarpeta=:idTipoCarpeta and cr.carpeta.activo='S' GROUP BY cr.carpeta.adscripcion.municipio.cveMunicipio ")
    List<GeneryObjectCount> countCarpetasVitralPorMesPorMunicipio(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("idTipoCarpeta") Integer idTipoCarpeta,@Param("cveDistrito") Integer cveDistrito);  
    
    @Query("SELECT new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(cr.carpeta.idCarpeta),cr.carpeta.adscripcion.municipio.descMunicipio,'',month(cr.carpeta.fechaRegistro),dr.delito.descDelito) FROM CarpetaRepresentado cr INNER JOIN cr.representado as r inner join r.delitosRepresentados dr  WHERE cr.carpeta.fechaRegistro>=:fechaInicio and cr.carpeta.fechaRegistro<=:fechaFinal and cr.carpeta.adscripcion.municipio.cveMunicipio=:cveMunicipio and cr.carpeta.tipoCarpeta.idTipoCarpeta=:idTipoCarpeta and cr.carpeta.activo='S'  GROUP BY   dr.delito.descDelito ORDER BY month(cr.carpeta.fechaRegistro) asc, count(cr.carpeta.idCarpeta) DESC")
    List<GeneryObjectCount> countCarpetasVitralPorMesPorMunicipioPorDelito(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("idTipoCarpeta") Integer idTipoCarpeta,@Param("cveMunicipio") Integer cveMunicipio);  
    
    @Query("SELECT cr FROM CarpetaRepresentado cr INNER JOIN cr.representado as r inner join r.delitosRepresentados dr  WHERE cr.carpeta.fechaRegistro>=:fechaInicio and cr.carpeta.fechaRegistro<=:fechaFinal  and cr.carpeta.tipoCarpeta.idTipoCarpeta=:idTipoCarpeta and cr.carpeta.activo='S'  ")
    List<CarpetaRepresentado> countCarpetasEdomex(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("idTipoCarpeta") Integer idTipoCarpeta); 

    @Query("SELECT new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(cr.carpeta.idCarpeta),cr.carpeta.adscripcion.municipio.descMunicipio,'',month(cr.carpeta.fechaRegistro))  FROM CarpetaRepresentado cr INNER JOIN cr.representado as r inner join r.delitosRepresentados dr  WHERE cr.carpeta.fechaRegistro>=:fechaInicio and cr.carpeta.fechaRegistro<=:fechaFinal  and cr.carpeta.tipoCarpeta.idTipoCarpeta=:idTipoCarpeta and cr.carpeta.activo='S' GROUP BY month(cr.carpeta.fechaRegistro) ORDER BY month(cr.carpeta.fechaRegistro) asc")
    List<GeneryObjectCount> countCarpetasVitralPorMesEdomex(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("idTipoCarpeta") Integer idTipoCarpeta);   
  
    
    
}
 