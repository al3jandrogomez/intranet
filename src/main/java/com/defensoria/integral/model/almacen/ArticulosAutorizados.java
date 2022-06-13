package com.defensoria.integral.model.almacen;

import javax.persistence.Column;

public class ArticulosAutorizados {
	
	
	public ArticulosAutorizados(Long total,Integer cvePartida,String descPartida,Integer cveArticulo,String descArticulo,String clavePartida,String descUnidad){
		this.total=total;
		this.descPartida=descPartida;
		this.descArticulo=descArticulo;
		this.cveArticulo=cveArticulo;
		this.cvePartida=cvePartida;
		this.clavePartida=clavePartida;
		this.descUnidad=descUnidad;
	}
	public ArticulosAutorizados(){}
	
	@Column(name="total")
	long total;
	@Column(name="descPartida")
	String descPartida;
	@Column(name="descArticulo")
	String descArticulo;
	@Column(name="cveArticulo")
	Integer cveArticulo;
	@Column(name="cvePartida")
	Integer cvePartida;
	@Column(name="clavePartida")
	String clavePartida;
	@Column(name="descUnidad")
	String descUnidad;
	
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getDescPartida() {
		return descPartida;
	}
	public void setDescPartida(String descPartida) {
		this.descPartida = descPartida;
	}
	public String getDescArticulo() {
		return descArticulo;
	}
	public void setDescArticulo(String descArticulo) {
		this.descArticulo = descArticulo;
	}
	public Integer getCveArticulo() {
		return cveArticulo;
	}
	public void setCveArticulo(Integer cveArticulo) {
		this.cveArticulo = cveArticulo;
	}
	public Integer getCvePartida() {
		return cvePartida;
	}
	public void setCvePartida(Integer cvePartida) {
		this.cvePartida = cvePartida;
	}
	public String getClavePartida() {
		return clavePartida;
	}
	public void setClavePartida(String clavePartida) {
		this.clavePartida = clavePartida;
	}
	public String getDescUnidad() {
		return descUnidad;
	}
	public void setDescUnidad(String descUnidad) {
		this.descUnidad = descUnidad;
	}
	
	
	
	
	

}
