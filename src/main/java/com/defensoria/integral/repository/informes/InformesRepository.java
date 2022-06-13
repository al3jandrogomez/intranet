package com.defensoria.integral.repository.informes;

import java.util.List;

import com.defensoria.integral.model.informes.Informe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InformesRepository extends JpaRepository<Informe,Long> {

    @Query("SELECT i FROM Informe i where  i.adscripcion.cveAdscripcion in(:claves) and i.estatusInforme=:estatus and i.cveMes=:cveMes and i.anio=:anio ORDER BY fechaRegistro ASC")
	List<Informe>findInformesPorAdscripcionesPorEstatus(@Param("claves")List<Integer> claves,@Param("estatus")String estatus,@Param("cveMes")Integer cveMes,@Param("anio")Integer anio );

    @Query("SELECT i FROM Informe i where i.tipoCarpeta.idTipoCarpeta=:idTipoCarpeta and i.adscripcion.municipio.cveRegion=:cveRegion and  i.estatusInforme=:estatus and i.cveMes=:cveMes ORDER BY fechaRegistro ASC")
	List<Informe>findInformesPorTiposCarpetas(@Param("cveRegion")Integer cveRegion,@Param("idTipoCarpeta")Integer idTipoCarpeta,@Param("estatus")String estatus,@Param("cveMes")Integer cveMes );
    
}
