/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.sigedepu2;


import com.defensoria.integral.model.adminpersonal.GeneryObjectCount;
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
public interface ConclusionesRepository extends JpaRepository<Conclusion, Long>{
    
     @Query("SELECT c FROM Conclusion c inner join c.representado as r inner join r.delitosRepresentados as dr WHERE c.audiencia.fechaAudiencia>=:fechaInicio and c.audiencia.fechaAudiencia<=:fechaFinal and c.audiencia.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and c.tipoConclusion.descTipoConclusiones like:descTipoConclusion ")
	List<Conclusion> listaConclusionesVitral(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("descTipoConclusion") String descTipoAudiencia,@Param("cveDistrito") Integer cveDistrito); 
    @Query("SELECT c FROM Conclusion c  inner join c.representado as r inner join r.delitosRepresentados as dr WHERE c.audiencia.fechaAudiencia>=:fechaInicio and c.audiencia.fechaAudiencia<=:fechaFinal and c.audiencia.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and c.tipoConclusion.cveTipoConclusion in(:listaClaves) ")
	List<Conclusion> listaConclusionesVitralIn(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves,@Param("cveDistrito") Integer cveDistrito); 
    
     @Query("SELECT  new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(c.idConclusion),c.audiencia.carpeta.adscripcion.municipio.descMunicipio,'',month(c.audiencia.fechaAudiencia)) FROM Conclusion c inner join c.representado as r inner join r.delitosRepresentados as dr  WHERE c.audiencia.fechaAudiencia>=:fechaInicio and c.audiencia.fechaAudiencia<=:fechaFinal and c.audiencia.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and c.tipoConclusion.cveTipoConclusion in(:listaClaves)  GROUP by month(c.audiencia.fechaAudiencia) ORDER BY month(c.audiencia.fechaAudiencia) asc ")
	List<GeneryObjectCount> listaConclusionesVitralInPorMes(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves,@Param("cveDistrito") Integer cveDistrito); 
    
     @Query("SELECT  new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(c.idConclusion),c.audiencia.carpeta.adscripcion.municipio.descMunicipio,'',month(c.audiencia.fechaAudiencia)) FROM Conclusion c inner join c.representado as r inner join r.delitosRepresentados as dr WHERE c.audiencia.fechaAudiencia>=:fechaInicio and c.audiencia.fechaAudiencia<=:fechaFinal and c.audiencia.carpeta.adscripcion.municipio.cveDistrito=:cveDistrito and c.tipoConclusion.cveTipoConclusion in(:listaClaves)  GROUP by (c.audiencia.carpeta.adscripcion.municipio) ORDER BY month(c.audiencia.fechaAudiencia) asc ")
	List<GeneryObjectCount> listaConclusionesVitralInPorMesPorMunicipio(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves,@Param("cveDistrito") Integer cveDistrito); 
    
     @Query("SELECT  new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(c.idConclusion),c.audiencia.carpeta.adscripcion.municipio.descMunicipio,'',month(c.audiencia.fechaAudiencia),dr.delito.descDelito) FROM Conclusion c inner join c.representado as r inner join r.delitosRepresentados as dr WHERE c.audiencia.fechaAudiencia>=:fechaInicio and c.audiencia.fechaAudiencia<=:fechaFinal and c.audiencia.carpeta.adscripcion.municipio.cveMunicipio=:cveMunicipio and c.tipoConclusion.cveTipoConclusion in(:listaClaves)  GROUP by   (dr.delito) ORDER BY  month(c.audiencia.fechaAudiencia) asc, count(c.idConclusion) desc ")
	List<GeneryObjectCount> listaConclusionesVitralInPorMesConDelito(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves,@Param("cveMunicipio") Integer cveMunicipio); 

     @Query("SELECT c FROM Conclusion c  inner join c.representado as r inner join r.delitosRepresentados as dr WHERE c.audiencia.fechaAudiencia>=:fechaInicio and c.audiencia.fechaAudiencia<=:fechaFinal  and c.tipoConclusion.cveTipoConclusion in(:listaClaves) ")
	List<Conclusion> listaConclusionesVitralInEdomex(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves); 

     @Query("SELECT  new com.defensoria.integral.model.adminpersonal.GeneryObjectCount(count(c.idConclusion),c.audiencia.carpeta.adscripcion.municipio.descMunicipio,'',month(c.audiencia.fechaAudiencia)) FROM Conclusion c inner join c.representado as r inner join r.delitosRepresentados as dr  WHERE c.audiencia.fechaAudiencia>=:fechaInicio and c.audiencia.fechaAudiencia<=:fechaFinal  and c.tipoConclusion.cveTipoConclusion in(:listaClaves)  GROUP by month(c.audiencia.fechaAudiencia) ORDER BY month(c.audiencia.fechaAudiencia) asc ")
	List<GeneryObjectCount> listaConclusionesVitralInPorMesEdomex(@Param("fechaInicio") Date fechaInicio,@Param("fechaFinal") Date fechaFinal, @Param("listaClaves") List<Integer> listaClaves); 
    
        
   
    
}
