package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.ArticulosAutorizados;
import com.defensoria.integral.model.almacen.DetalleRequisiciones;

public interface DetalleRequisicionesRepository extends JpaRepository<DetalleRequisiciones, Long>{
 @Query("SELECT dr FROM DetalleRequisiciones dr inner join dr.requisicion r where r.idRequisicion in(:listaRequisicion) and dr.activo='S' ")
 List<DetalleRequisiciones>findByClaveRequisiciones(@Param("listaRequisicion")List<Integer> listaRequisicion);
 @Query("SELECT dr FROM DetalleRequisiciones dr inner join dr.articulo a  where a.cveArticulo in :listaArticulos and dr.activo='S' order by a.cveArticulo DESC")
 List<DetalleRequisiciones>ListaArticulosAutorizados(@Param("listaArticulos")List<Integer>listaArticulos);
  @Query("SELECT dr FROM DetalleRequisiciones dr inner join dr.articulo a  where dr.idDetalleRequisicion=:idDetalleRequisicion and dr.activo='S' ")
 List<DetalleRequisiciones>detalleArticulo(@Param("idDetalleRequisicion")Integer idDetalleRequisicion);
  @Query("select new com.defensoria.integral.model.almacen.ArticulosAutorizados(sum(dr.cantidadAutorizada)as total,p.cvePartida as cvePartida, p.descPartida as descPartida,a.cveArticulo as cveArticulo, a.descArticulo as descArticulo,p.clavePartida as clavePartida,u.descUnidad as descUnidad) from DetalleRequisiciones dr" 
+" inner join dr.requisicion r "
+" inner join r.movRequisicion mr "
+" inner join mr.estatusRequisicion e "
+" inner join dr.articulo a "
+" inner join a.partidas p "
+" inner join a.unidades u "
+" where r.activo='S' and e.cveEstatusRequisicion=7 and mr.activo='S'  "
+" group by p.cvePartida,a.cveArticulo"
+" order by p.descPartida,a.descArticulo")
  List<ArticulosAutorizados>ListaArticulosAutorizados();
  
}
