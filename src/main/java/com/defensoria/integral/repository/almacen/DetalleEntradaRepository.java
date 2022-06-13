package com.defensoria.integral.repository.almacen;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.ArticulosAutorizados;
import com.defensoria.integral.model.almacen.DetalleEntradas;

public interface DetalleEntradaRepository extends JpaRepository<DetalleEntradas, Long> {
	@Query("SELECT de FROM DetalleEntradas de inner join de.entrada e inner join de.articulo a WHERE e.idEntrada =:idEntrada and de.activo='S' order by a.descArticulo")
	public List<DetalleEntradas> porIdEntrada(@Param("idEntrada") Integer idEntrada);

	List<DetalleEntradas> findByIdDetalleEntrada(Integer idDetalleEntrada);

	@Transactional
	@Modifying
	@Query("update DetalleEntradas de set de.activo =:activo where de.idDetalleEntrada =:idDetalleEntrada")
	void Eliminar(@Param("activo") String activo, @Param("idDetalleEntrada") Integer idDetalleEntrada);

	@Query("SELECT de FROM DetalleEntradas de inner join de.articulo a WHERE a.cveArticulo in:listaArticulos ORDER BY a.cveArticulo desc")
	List<DetalleEntradas> ListaArticulosEntradas(@Param("listaArticulos") List<Integer> listaArticulos);

	@Query("SELECT new com.defensoria.integral.model.almacen.ArticulosAutorizados(sum(de.cantidad)as total,p.cvePartida as cvePartida, p.descPartida as descPartida,a.cveArticulo as cveArticulo, a.descArticulo as descArticulo,p.clavePartida as clavePartida,u.descUnidad as descUnidad) FROM DetalleEntradas de"
			+ " inner join de.articulo a" + " inner join a.unidades u " + " inner join a.partidas p where de.activo = 'S'"
			+ " group by p.cvePartida,a.cveArticulo" + " order by p.clavePartida,a.descArticulo")
	List<ArticulosAutorizados> ListaArticulosAutorizadosEx();
}
