/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.sigedepu2;

import com.defensoria.integral.model.adminpersonal.GeneryObjectCount;
import com.defensoria.integral.model.sigedepu2.Audiencias;
import com.defensoria.integral.model.sigedepu2.Conclusion;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface AudienciasRepository extends JpaRepository<Audiencias, Long>{
    
    @Query("SELECT a FROM Audiencias a  WHERE a.defensa_id in:listaDefensas ")
	List<Audiencias> listaAudiencias(@Param("listaDefensas") List<Integer> listaDefensas);
    @Query("SELECT a FROM Audiencias a  WHERE a.fechaAudiencia>=:fechaInicio and a.fechaAudiencia<=:fechaFinal and a.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and a.tipoAudiencia.descTipoAudiencia like:descTipoAudiencia ")
	List<Audiencias> listaAudienciasVitral(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("descTipoAudiencia") String descTipoAudiencia,@Param("cveDistrito") Integer cveDistrito); 
    
    @Query("SELECT c FROM Conclusion c inner join  c.audiencia as a inner join c.representado as r inner join r.delitosRepresentados as dr  WHERE a.fechaAudiencia>=:fechaInicio and a.fechaAudiencia<=:fechaFinal and a.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and a.tipoAudiencia.cveTipoAudiencia in (:listaClaves) ")
	List<Conclusion> listaAudienciasVitralIn(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves,@Param("cveDistrito") Integer cveDistrito); 
    
    @Query("SELECT c FROM Conclusion c inner join  c.audiencia as a inner join c.representado as r inner join r.delitosRepresentados as dr  WHERE a.fechaAudiencia>=:fechaInicio and a.fechaAudiencia<=:fechaFinal  and a.tipoAudiencia.cveTipoAudiencia in (:listaClaves) ")
	List<Conclusion> listaAudienciasVitralInEdomex(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves); 
    
    
    
  //   @Query("SELECT new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(a.idAudiencia), a.carpeta.adscripcion.municipio.descMunicipio,'',month(a.fechaAudiencia)) FROM Audiencias a  WHERE a.fechaAudiencia>=:fechaInicio and a.fechaAudiencia<=:fechaFinal and a.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and a.tipoAudiencia.cveTipoAudiencia in (:listaClaves) GROUP by month(a.fechaAudiencia) ORDER BY month(a.fechaAudiencia)asc ")
    @Query("SELECT new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(a.idAudiencia), a.carpeta.adscripcion.municipio.descMunicipio,'',month(a.fechaAudiencia),dr.delito.descDelito) FROM Conclusion c inner join  c.audiencia as a inner join c.representado as r inner join r.delitosRepresentados as dr  WHERE a.fechaAudiencia>=:fechaInicio and a.fechaAudiencia<=:fechaFinal and a.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and a.tipoAudiencia.cveTipoAudiencia in (:listaClaves) GROUP by month(a.fechaAudiencia) ORDER BY month(a.fechaAudiencia)asc ")
	List<GeneryObjectCount> listaAudienciasVitralInPorMes(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves,@Param("cveDistrito") Integer cveDistrito); 
    
    
    @Query("SELECT new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(a.idAudiencia), a.carpeta.adscripcion.municipio.descMunicipio,'',month(a.fechaAudiencia),dr.delito.descDelito) FROM Conclusion c inner join  c.audiencia as a inner join c.representado as r inner join r.delitosRepresentados as dr  WHERE a.fechaAudiencia>=:fechaInicio and a.fechaAudiencia<=:fechaFinal and a.carpeta.adscripcion.municipio.cveMunicipio=:cveMunicipio and a.tipoAudiencia.cveTipoAudiencia in (:listaClaves) GROUP by month(a.fechaAudiencia),(dr.delito.descDelito) ORDER BY   month(a.fechaAudiencia)asc, count(a.idAudiencia) DESC ")
	List<GeneryObjectCount> listaAudienciasVitralInPorMesConDelito(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves,@Param("cveMunicipio") Integer cveMunicipio); 

    @Query("SELECT new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(a.idAudiencia), a.carpeta.adscripcion.municipio.descMunicipio,'',month(a.fechaAudiencia),dr.delito.descDelito) FROM Conclusion c inner join  c.audiencia as a inner join c.representado as r inner join r.delitosRepresentados as dr  WHERE a.fechaAudiencia>=:fechaInicio and a.fechaAudiencia<=:fechaFinal and a.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and a.tipoAudiencia.cveTipoAudiencia in (:listaClaves) GROUP by a.carpeta.adscripcion.municipio ORDER BY   month(a.fechaAudiencia)asc, count(a.idAudiencia) DESC ")
	List<GeneryObjectCount> listaAudienciasVitralInPorMesPorMunicipio(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves,@Param("cveDistrito") Integer cveDistrito); 
    
    @Query("SELECT new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(a.idAudiencia), a.carpeta.adscripcion.municipio.descMunicipio,'',month(a.fechaAudiencia),dr.delito.descDelito) FROM Conclusion c inner join  c.audiencia as a inner join c.representado as r inner join r.delitosRepresentados as dr  WHERE a.fechaAudiencia>=:fechaInicio and a.fechaAudiencia<=:fechaFinal and a.carpeta.adscripcion.municipio.cveMunicipio=:cveMunicipio and a.tipoAudiencia.cveTipoAudiencia in (:listaClaves) GROUP by dr.delito.descDelito ORDER BY   month(a.fechaAudiencia)asc, count(a.idAudiencia) DESC ")
	List<GeneryObjectCount> listaAudienciasVitralInPorMesPorDelito(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves,@Param("cveMunicipio") Integer cveMunicipio); 


    @Query("SELECT new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(a.idAudiencia), a.carpeta.adscripcion.municipio.descMunicipio,'',month(a.fechaAudiencia),dr.delito.descDelito) FROM Conclusion c inner join  c.audiencia as a inner join c.representado as r inner join r.delitosRepresentados as dr  WHERE a.fechaAudiencia>=:fechaInicio and a.fechaAudiencia<=:fechaFinal and a.tipoAudiencia.cveTipoAudiencia in (:listaClaves) GROUP by month(a.fechaAudiencia) ORDER BY month(a.fechaAudiencia)asc ")
	List<GeneryObjectCount> listaAudienciasVitralInPorMesEdomex(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves); 
    
    
}
