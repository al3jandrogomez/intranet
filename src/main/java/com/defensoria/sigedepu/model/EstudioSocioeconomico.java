package com.defensoria.sigedepu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="estudio_socio_economico")
public class EstudioSocioeconomico {
	@Id
	@GeneratedValue
	@Column(name = "id")
private Integer id;
private Integer ultimo_grado_estudio_id;
private String nombre_institucion;
private String lugar_institucion;
private String idioma;
private Integer estudia_actualmente;
private Integer nivel_estudio_cursa_id;
private String nivel_estudio_cursa_nombre_institucion;
private String tipo_vivienda;
private String propia;
private Integer num_personas_habita;
private String parentesco_habita;
private String material_construccion;
private Integer num_niveles;
private Integer num_habitaciones;
private Integer num_focos;
private Integer luz;
private Integer agua;
private Integer telefono;
private Integer cable;
private Integer internet;
private Integer alumbrado_publico;
private Integer pavimentado;
private Integer tiene_hijos;
private String conyuge_conc;
private String nombre_conyuge;
private String fecha_nac_con;
private Integer edad_con;
private String escolaridad_con;
private String tel_con;
private String nombre_padre;
private String paterno_padre;
private String materno_padre;
private String nombre_madre;
private String paterno_madre;
private String materno_madre;
private String domicilio_padre;
private String telefono_padre;
private Integer num_hermanos;
private Integer posee_bien;
private String tipo_inmueble_casa;
private String tipo_inmueble_otro;
private String auto;
private Integer trabaja;
private String para_quien_labora;
private String nombre_jefe;
private String domicilio_laboral;
private String telefono_laboral;
private Integer num_dependientes_econ;
private Double  ingreso_mensual;
private String forma_pago;
private String otros_especificar;
private String tramite_solicita;
private String estatus;
private Integer salario_minimo_id;
private Date fecha_socio_economica;
@JsonIgnore
 @OneToOne
 @JoinColumn(name = "id", referencedColumnName = "estudio_socio_economico_id")
private ConclusionEstudio conclusion;




private Integer usuario_id;
private String observaciones;
private Integer es_viable;
private String folio;
private String estado_salud;
private String enfermedad_padece;
private String especificacion_tipo_vivienda;
private String numero_cuartos;
private Integer cuarto_exclusivo;
private Integer sala;
private Integer comedor;
private Integer cocina;
private Integer bano_privado;
private Integer bano_colectivo;
private Integer no_tiene_bano;
private Integer obra_negra;
private Integer sin_acabado;
private Integer obra_terminada;
private Integer vivienda_terminada;
private Integer construccion_adobe;
private Integer drenaje;
private Integer comercio;
private Integer servicio_educativo;
private Integer servicio_transporte;
private String observaciones_vivienda;
private String tipo_contratacion;
private String cargo;
private String tiempo_estructura_familiar;
private String especificacion_ingreso_indirecto;
private Double  monto_ayuda_familiar;
private Double  monto_beca;
private Double  monto_pensiones;
private Double  monto_rentas;
private Double  monto_venta_inmueble;
private Double  monto_otra;
private String observaciones_conclusion;
private String accion_juzgado;
private Integer autorizado;
private Date fecha_autorizacion;
private Integer zona_geografica_id;
private String motivo_rechazo;
private Integer autorizador_id;
private Integer concluded_by_id;
@Column( nullable = true)
private Integer blocked_by_id;
private String estatus_patrocinio;
private Date fecha_creacion;
private Double  servicios_basicos;
private Double  renta_hipoteca_predial;
private Double  alimentacion;
private Double  transporte_gas;
private Double  escolares;
private Double  diversion;
private Double  deudas;
private Double  atencion_medica;
private Double  otros;
private Double  total_ingreso;
private Double  total_egreso;
private Double  remanente;
private Double  vestido;
private Double  ayuda_familiar;
private Double  becas;
private Double  pensiones;
private Double  rentas;
private Double  venta_inmueble;
private Double  otro_ingreso_indirecto;
private Double  valor;
private Double  valor_auto;
private Integer peticionario_id;
private Integer reutilizar_estudio;
private Integer ocupacion_con_id;
private Integer visita_domicilio;
private String observaciones_visita;
private Integer numero_viven_domicilio;
private Integer numero_dependientes;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getUltimo_grado_estudio_id() {
	return ultimo_grado_estudio_id;
}
public void setUltimo_grado_estudio_id(Integer ultimo_grado_estudio_id) {
	this.ultimo_grado_estudio_id = ultimo_grado_estudio_id;
}
public String getNombre_institucion() {
	return nombre_institucion;
}
public void setNombre_institucion(String nombre_institucion) {
	this.nombre_institucion = nombre_institucion;
}
public String getLugar_institucion() {
	return lugar_institucion;
}
public void setLugar_institucion(String lugar_institucion) {
	this.lugar_institucion = lugar_institucion;
}
public String getIdioma() {
	return idioma;
}
public void setIdioma(String idioma) {
	this.idioma = idioma;
}
public Integer getEstudia_actualmente() {
	return estudia_actualmente;
}
public void setEstudia_actualmente(Integer estudia_actualmente) {
	this.estudia_actualmente = estudia_actualmente;
}
public Integer getNivel_estudio_cursa_id() {
	return nivel_estudio_cursa_id;
}
public void setNivel_estudio_cursa_id(Integer nivel_estudio_cursa_id) {
	this.nivel_estudio_cursa_id = nivel_estudio_cursa_id;
}

public String getNivel_estudio_cursa_nombre_institucion() {
	return nivel_estudio_cursa_nombre_institucion;
}
public void setNivel_estudio_cursa_nombre_institucion(String nivel_estudio_cursa_nombre_institucion) {
	this.nivel_estudio_cursa_nombre_institucion = nivel_estudio_cursa_nombre_institucion;
}
public String getTipo_vivienda() {
	return tipo_vivienda;
}
public void setTipo_vivienda(String tipo_vivienda) {
	this.tipo_vivienda = tipo_vivienda;
}
public String getPropia() {
	return propia;
}
public void setPropia(String propia) {
	this.propia = propia;
}
public Integer getNum_personas_habita() {
	return num_personas_habita;
}
public void setNum_personas_habita(Integer num_personas_habita) {
	this.num_personas_habita = num_personas_habita;
}

public String getParentesco_habita() {
	return parentesco_habita;
}
public void setParentesco_habita(String parentesco_habita) {
	this.parentesco_habita = parentesco_habita;
}
public String getMaterial_construccion() {
	return material_construccion;
}
public void setMaterial_construccion(String material_construccion) {
	this.material_construccion = material_construccion;
}
public Integer getNum_niveles() {
	return num_niveles;
}
public void setNum_niveles(Integer num_niveles) {
	this.num_niveles = num_niveles;
}

public Integer getNum_habitaciones() {
	return num_habitaciones;
}
public void setNum_habitaciones(Integer num_habitaciones) {
	this.num_habitaciones = num_habitaciones;
}
public Integer getNum_focos() {
	return num_focos;
}
public void setNum_focos(Integer num_focos) {
	this.num_focos = num_focos;
}
public Integer getLuz() {
	return luz;
}
public void setLuz(Integer luz) {
	this.luz = luz;
}
public Integer getAgua() {
	return agua;
}
public void setAgua(Integer agua) {
	this.agua = agua;
}
public Integer getTelefono() {
	return telefono;
}
public void setTelefono(Integer telefono) {
	this.telefono = telefono;
}
public Integer getCable() {
	return cable;
}
public void setCable(Integer cable) {
	this.cable = cable;
}
public Integer getInternet() {
	return internet;
}
public void setInternet(Integer internet) {
	this.internet = internet;
}
public Integer getAlumbrado_publico() {
	return alumbrado_publico;
}
public void setAlumbrado_publico(Integer alumbrado_publico) {
	this.alumbrado_publico = alumbrado_publico;
}
public Integer getPavimentado() {
	return pavimentado;
}
public void setPavimentado(Integer pavimentado) {
	this.pavimentado = pavimentado;
}
public Integer getTiene_hijos() {
	return tiene_hijos;
}
public void setTiene_hijos(Integer tiene_hijos) {
	this.tiene_hijos = tiene_hijos;
}
public String getConyuge_conc() {
	return conyuge_conc;
}
public void setConyuge_conc(String conyuge_conc) {
	this.conyuge_conc = conyuge_conc;
}
public String getNombre_conyuge() {
	return nombre_conyuge;
}
public void setNombre_conyuge(String nombre_conyuge) {
	this.nombre_conyuge = nombre_conyuge;
}
public String getFecha_nac_con() {
	return fecha_nac_con;
}
public void setFecha_nac_con(String fecha_nac_con) {
	this.fecha_nac_con = fecha_nac_con;
}
public Integer getEdad_con() {
	return edad_con;
}
public void setEdad_con(Integer edad_con) {
	this.edad_con = edad_con;
}
public String getEscolaridad_con() {
	return escolaridad_con;
}
public void setEscolaridad_con(String escolaridad_con) {
	this.escolaridad_con = escolaridad_con;
}
public String getTel_con() {
	return tel_con;
}
public void setTel_con(String tel_con) {
	this.tel_con = tel_con;
}
public String getNombre_padre() {
	return nombre_padre;
}
public void setNombre_padre(String nombre_padre) {
	this.nombre_padre = nombre_padre;
}
public String getPaterno_padre() {
	return paterno_padre;
}
public void setPaterno_padre(String paterno_padre) {
	this.paterno_padre = paterno_padre;
}
public String getMaterno_padre() {
	return materno_padre;
}
public void setMaterno_padre(String materno_padre) {
	this.materno_padre = materno_padre;
}
public String getNombre_madre() {
	return nombre_madre;
}
public void setNombre_madre(String nombre_madre) {
	this.nombre_madre = nombre_madre;
}
public String getPaterno_madre() {
	return paterno_madre;
}
public void setPaterno_madre(String paterno_madre) {
	this.paterno_madre = paterno_madre;
}
public String getMaterno_madre() {
	return materno_madre;
}
public void setMaterno_madre(String materno_madre) {
	this.materno_madre = materno_madre;
}
public String getDomicilio_padre() {
	return domicilio_padre;
}
public void setDomicilio_padre(String domicilio_padre) {
	this.domicilio_padre = domicilio_padre;
}
public String getTelefono_padre() {
	return telefono_padre;
}
public void setTelefono_padre(String telefono_padre) {
	this.telefono_padre = telefono_padre;
}
public Integer getNum_hermanos() {
	return num_hermanos;
}
public void setNum_hermanos(Integer num_hermanos) {
	this.num_hermanos = num_hermanos;
}
public Integer getPosee_bien() {
	return posee_bien;
}
public void setPosee_bien(Integer posee_bien) {
	this.posee_bien = posee_bien;
}
public String getTipo_inmueble_casa() {
	return tipo_inmueble_casa;
}
public void setTipo_inmueble_casa(String tipo_inmueble_casa) {
	this.tipo_inmueble_casa = tipo_inmueble_casa;
}
public String getTipo_inmueble_otro() {
	return tipo_inmueble_otro;
}
public void setTipo_inmueble_otro(String tipo_inmueble_otro) {
	this.tipo_inmueble_otro = tipo_inmueble_otro;
}
public String getAuto() {
	return auto;
}
public void setAuto(String auto) {
	this.auto = auto;
}
public Integer getTrabaja() {
	return trabaja;
}
public void setTrabaja(Integer trabaja) {
	this.trabaja = trabaja;
}

public String getPara_quien_labora() {
	return para_quien_labora;
}
public void setPara_quien_labora(String para_quien_labora) {
	this.para_quien_labora = para_quien_labora;
}
public String getNombre_jefe() {
	return nombre_jefe;
}
public void setNombre_jefe(String nombre_jefe) {
	this.nombre_jefe = nombre_jefe;
}
public String getDomicilio_laboral() {
	return domicilio_laboral;
}
public void setDomicilio_laboral(String domicilio_laboral) {
	this.domicilio_laboral = domicilio_laboral;
}
public String getTelefono_laboral() {
	return telefono_laboral;
}
public void setTelefono_laboral(String telefono_laboral) {
	this.telefono_laboral = telefono_laboral;
}
public Integer getNum_dependientes_econ() {
	return num_dependientes_econ;
}
public void setNum_dependientes_econ(Integer num_dependientes_econ) {
	this.num_dependientes_econ = num_dependientes_econ;
}
public Double  getIngreso_mensual() {
	return ingreso_mensual;
}
public void setIngreso_mensual(Double  ingreso_mensual) {
	this.ingreso_mensual = ingreso_mensual;
}
public String getForma_pago() {
	return forma_pago;
}
public void setForma_pago(String forma_pago) {
	this.forma_pago = forma_pago;
}
public String getOtros_especificar() {
	return otros_especificar;
}
public void setOtros_especificar(String otros_especificar) {
	this.otros_especificar = otros_especificar;
}
public String getTramite_solicita() {
	return tramite_solicita;
}
public void setTramite_solicita(String tramite_solicita) {
	this.tramite_solicita = tramite_solicita;
}
public String getEstatus() {
	return estatus;
}
public void setEstatus(String estatus) {
	this.estatus = estatus;
}
public Integer getSalario_minimo_id() {
	return salario_minimo_id;
}
public void setSalario_minimo_id(Integer salario_minimo_id) {
	this.salario_minimo_id = salario_minimo_id;
}
public Date getFecha_socio_economica() {
	return fecha_socio_economica;
}
public void setFecha_socio_economica(Date fecha_socio_economica) {
	this.fecha_socio_economica = fecha_socio_economica;
}


public Integer getUsuario_id() {
	return usuario_id;
}
public void setUsuario_id(Integer usuario_id) {
	this.usuario_id = usuario_id;
}
public String getObservaciones() {
	return observaciones;
}
public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
}
public Integer getEs_viable() {
	return es_viable;
}
public void setEs_viable(Integer es_viable) {
	this.es_viable = es_viable;
}
public String getFolio() {
	return folio;
}
public void setFolio(String folio) {
	this.folio = folio;
}
public String getEstado_salud() {
	return estado_salud;
}
public void setEstado_salud(String estado_salud) {
	this.estado_salud = estado_salud;
}
public String getEnfermedad_padece() {
	return enfermedad_padece;
}
public void setEnfermedad_padece(String enfermedad_padece) {
	this.enfermedad_padece = enfermedad_padece;
}
public String getEspecificacion_tipo_vivienda() {
	return especificacion_tipo_vivienda;
}
public void setEspecificacion_tipo_vivienda(String especificacion_tipo_vivienda) {
	this.especificacion_tipo_vivienda = especificacion_tipo_vivienda;
}
public String getNumero_cuartos() {
	return numero_cuartos;
}
public void setNumero_cuartos(String numero_cuartos) {
	this.numero_cuartos = numero_cuartos;
}
public Integer getCuarto_exclusivo() {
	return cuarto_exclusivo;
}
public void setCuarto_exclusivo(Integer cuarto_exclusivo) {
	this.cuarto_exclusivo = cuarto_exclusivo;
}
public Integer getSala() {
	return sala;
}
public void setSala(Integer sala) {
	this.sala = sala;
}
public Integer getComedor() {
	return comedor;
}
public void setComedor(Integer comedor) {
	this.comedor = comedor;
}
public Integer getCocina() {
	return cocina;
}
public void setCocina(Integer cocina) {
	this.cocina = cocina;
}

public Integer getBano_privado() {
	return bano_privado;
}
public void setBano_privado(Integer bano_privado) {
	this.bano_privado = bano_privado;
}
public Integer getBano_colectivo() {
	return bano_colectivo;
}
public void setBano_colectivo(Integer bano_colectivo) {
	this.bano_colectivo = bano_colectivo;
}
public Integer getNo_tiene_bano() {
	return no_tiene_bano;
}
public void setNo_tiene_bano(Integer no_tiene_bano) {
	this.no_tiene_bano = no_tiene_bano;
}
public Integer getObra_negra() {
	return obra_negra;
}
public void setObra_negra(Integer obra_negra) {
	this.obra_negra = obra_negra;
}
public Integer getSin_acabado() {
	return sin_acabado;
}
public void setSin_acabado(Integer sin_acabado) {
	this.sin_acabado = sin_acabado;
}
public Integer getObra_terminada() {
	return obra_terminada;
}
public void setObra_terminada(Integer obra_terminada) {
	this.obra_terminada = obra_terminada;
}
public Integer getVivienda_terminada() {
	return vivienda_terminada;
}
public void setVivienda_terminada(Integer vivienda_terminada) {
	this.vivienda_terminada = vivienda_terminada;
}
public Integer getConstruccion_adobe() {
	return construccion_adobe;
}
public void setConstruccion_adobe(Integer construccion_adobe) {
	this.construccion_adobe = construccion_adobe;
}
public Integer getDrenaje() {
	return drenaje;
}
public void setDrenaje(Integer drenaje) {
	this.drenaje = drenaje;
}
public Integer getComercio() {
	return comercio;
}
public void setComercio(Integer comercio) {
	this.comercio = comercio;
}
public Integer getServicio_educativo() {
	return servicio_educativo;
}
public void setServicio_educativo(Integer servicio_educativo) {
	this.servicio_educativo = servicio_educativo;
}
public Integer getServicio_transporte() {
	return servicio_transporte;
}
public void setServicio_transporte(Integer servicio_transporte) {
	this.servicio_transporte = servicio_transporte;
}
public String getObservaciones_vivienda() {
	return observaciones_vivienda;
}
public void setObservaciones_vivienda(String observaciones_vivienda) {
	this.observaciones_vivienda = observaciones_vivienda;
}
public String getTipo_contratacion() {
	return tipo_contratacion;
}
public void setTipo_contratacion(String tipo_contratacion) {
	this.tipo_contratacion = tipo_contratacion;
}
public String getCargo() {
	return cargo;
}
public void setCargo(String cargo) {
	this.cargo = cargo;
}
public String getTiempo_estructura_familiar() {
	return tiempo_estructura_familiar;
}
public void setTiempo_estructura_familiar(String tiempo_estructura_familiar) {
	this.tiempo_estructura_familiar = tiempo_estructura_familiar;
}
public String getEspecificacion_ingreso_indirecto() {
	return especificacion_ingreso_indirecto;
}
public void setEspecificacion_ingreso_indirecto(String especificacion_ingreso_indirecto) {
	this.especificacion_ingreso_indirecto = especificacion_ingreso_indirecto;
}
public Double  getMonto_ayuda_familiar() {
	return monto_ayuda_familiar;
}
public void setMonto_ayuda_familiar(Double  monto_ayuda_familiar) {
	this.monto_ayuda_familiar = monto_ayuda_familiar;
}
public Double  getMonto_beca() {
	return monto_beca;
}
public void setMonto_beca(Double  monto_beca) {
	this.monto_beca = monto_beca;
}
public Double  getMonto_pensiones() {
	return monto_pensiones;
}
public void setMonto_pensiones(Double  monto_pensiones) {
	this.monto_pensiones = monto_pensiones;
}
public Double  getMonto_rentas() {
	return monto_rentas;
}
public void setMonto_rentas(Double  monto_rentas) {
	this.monto_rentas = monto_rentas;
}
public Double  getMonto_venta_inmueble() {
	return monto_venta_inmueble;
}
public void setMonto_venta_inmueble(Double  monto_venta_inmueble) {
	this.monto_venta_inmueble = monto_venta_inmueble;
}
public Double  getMonto_otra() {
	return monto_otra;
}
public void setMonto_otra(Double  monto_otra) {
	this.monto_otra = monto_otra;
}
public String getObservaciones_conclusion() {
	return observaciones_conclusion;
}
public void setObservaciones_conclusion(String observaciones_conclusion) {
	this.observaciones_conclusion = observaciones_conclusion;
}
public String getAccion_juzgado() {
	return accion_juzgado;
}
public void setAccion_juzgado(String accion_juzgado) {
	this.accion_juzgado = accion_juzgado;
}
public Integer getAutorizado() {
	return autorizado;
}
public void setAutorizado(Integer autorizado) {
	this.autorizado = autorizado;
}
public Date getFecha_autorizacion() {
	return fecha_autorizacion;
}
public void setFecha_autorizacion(Date fecha_autorizacion) {
	this.fecha_autorizacion = fecha_autorizacion;
}
public Integer getZona_geografica_id() {
	return zona_geografica_id;
}
public void setZona_geografica_id(Integer zona_geografica_id) {
	this.zona_geografica_id = zona_geografica_id;
}
public String getMotivo_rechazo() {
	return motivo_rechazo;
}
public void setMotivo_rechazo(String motivo_rechazo) {
	this.motivo_rechazo = motivo_rechazo;
}
public Integer getAutorizador_id() {
	return autorizador_id;
}
public void setAutorizador_id(Integer autorizador_id) {
	this.autorizador_id = autorizador_id;
}
public Integer getConcluded_by_id() {
	return concluded_by_id;
}
public void setConcluded_by_id(Integer concluded_by_id) {
	this.concluded_by_id = concluded_by_id;
}
public Integer getBlocked_by_id() {
	return blocked_by_id;
}
public void setBlocked_by_id(Integer blocked_by_id) {
	this.blocked_by_id = blocked_by_id;
}
public String getEstatus_patrocinio() {
	return estatus_patrocinio;
}
public void setEstatus_patrocinio(String estatus_patrocinio) {
	this.estatus_patrocinio = estatus_patrocinio;
}
public Date getFecha_creacion() {
	return fecha_creacion;
}
public void setFecha_creacion(Date fecha_creacion) {
	this.fecha_creacion = fecha_creacion;
}
public Double  getServicios_basicos() {
	return servicios_basicos;
}
public void setServicios_basicos(Double  servicios_basicos) {
	this.servicios_basicos = servicios_basicos;
}
public Double  getRenta_hipoteca_predial() {
	return renta_hipoteca_predial;
}
public void setRenta_hipoteca_predial(Double  renta_hipoteca_predial) {
	this.renta_hipoteca_predial = renta_hipoteca_predial;
}
public Double  getAlimentacion() {
	return alimentacion;
}
public void setAlimentacion(Double  alimentacion) {
	this.alimentacion = alimentacion;
}
public Double  getTransporte_gas() {
	return transporte_gas;
}
public void setTransporte_gas(Double  transporte_gas) {
	this.transporte_gas = transporte_gas;
}
public Double  getEscolares() {
	return escolares;
}
public void setEscolares(Double  escolares) {
	this.escolares = escolares;
}
public Double  getDiversion() {
	return diversion;
}
public void setDiversion(Double  diversion) {
	this.diversion = diversion;
}
public Double  getDeudas() {
	return deudas;
}
public void setDeudas(Double  deudas) {
	this.deudas = deudas;
}
public Double  getAtencion_medica() {
	return atencion_medica;
}
public void setAtencion_medica(Double  atencion_medica) {
	this.atencion_medica = atencion_medica;
}
public Double  getOtros() {
	return otros;
}
public void setOtros(Double  otros) {
	this.otros = otros;
}
public Double  getTotal_ingreso() {
	return total_ingreso;
}
public void setTotal_ingreso(Double  total_ingreso) {
	this.total_ingreso = total_ingreso;
}
public Double  getTotal_egreso() {
	return total_egreso;
}
public void setTotal_egreso(Double  total_egreso) {
	this.total_egreso = total_egreso;
}
public Double  getRemanente() {
	return remanente;
}
public void setRemanente(Double  remanente) {
	this.remanente = remanente;
}
public Double  getVestido() {
	return vestido;
}
public void setVestido(Double  vestido) {
	this.vestido = vestido;
}
public Double  getAyuda_familiar() {
	return ayuda_familiar;
}
public void setAyuda_familiar(Double  ayuda_familiar) {
	this.ayuda_familiar = ayuda_familiar;
}
public Double  getBecas() {
	return becas;
}
public void setBecas(Double  becas) {
	this.becas = becas;
}
public Double  getPensiones() {
	return pensiones;
}
public void setPensiones(Double  pensiones) {
	this.pensiones = pensiones;
}
public Double  getRentas() {
	return rentas;
}
public void setRentas(Double  rentas) {
	this.rentas = rentas;
}
public Double  getVenta_inmueble() {
	return venta_inmueble;
}
public void setVenta_inmueble(Double  venta_inmueble) {
	this.venta_inmueble = venta_inmueble;
}
public Double  getOtro_ingreso_indirecto() {
	return otro_ingreso_indirecto;
}
public void setOtro_ingreso_indirecto(Double  otro_ingreso_indirecto) {
	this.otro_ingreso_indirecto = otro_ingreso_indirecto;
}
public Double  getValor() {
	return valor;
}
public void setValor(Double  valor) {
	this.valor = valor;
}
public Double  getValor_auto() {
	return valor_auto;
}
public void setValor_auto(Double  valor_auto) {
	this.valor_auto = valor_auto;
}
public Integer getPeticionario_id() {
	return peticionario_id;
}
public void setPeticionario_id(Integer peticionario_id) {
	this.peticionario_id = peticionario_id;
}
public Integer getReutilizar_estudio() {
	return reutilizar_estudio;
}
public void setReutilizar_estudio(Integer reutilizar_estudio) {
	this.reutilizar_estudio = reutilizar_estudio;
}
public Integer getOcupacion_con_id() {
	return ocupacion_con_id;
}
public void setOcupacion_con_id(Integer ocupacion_con_id) {
	this.ocupacion_con_id = ocupacion_con_id;
}
public Integer getVisita_domicilio() {
	return visita_domicilio;
}
public void setVisita_domicilio(Integer visita_domicilio) {
	this.visita_domicilio = visita_domicilio;
}
public String getObservaciones_visita() {
	return observaciones_visita;
}
public void setObservaciones_visita(String observaciones_visita) {
	this.observaciones_visita = observaciones_visita;
}
public Integer getNumero_viven_domicilio() {
	return numero_viven_domicilio;
}
public void setNumero_viven_domicilio(Integer numero_viven_domicilio) {
	this.numero_viven_domicilio = numero_viven_domicilio;
}
public Integer getNumero_dependientes() {
	return numero_dependientes;
}
public void setNumero_dependientes(Integer numero_dependientes) {
	this.numero_dependientes = numero_dependientes;
}
public ConclusionEstudio getConclusion() {
	return conclusion;
}
public void setConclusion(ConclusionEstudio conclusion) {
	this.conclusion = conclusion;
}






















}
