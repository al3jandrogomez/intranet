package com.defensoria.integral.controller.almacen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VistasController {
	@RequestMapping("/unidades")
	public String Unidades(){
		return "Unidades";
	}
	@RequestMapping("/tiposdocumentos")
	public String TiposDocumentos(){
		return "TiposDocumentos";
	}
	@RequestMapping("/partidas")
	public String frmPartidas(){
		return "Partidas";	
	}
	@RequestMapping ("/marcasAlm")
	public String MarcasAlm(){
		return "MarcasAlm";
	}
	@RequestMapping("/articulos")
	public String frmArticulos(){
		return "Articulos";
	}
	@RequestMapping("/entradas")
	public String Entradas(){
		
		return "entradasAlm";
		
	}
	@RequestMapping("/proveedores")
	public String Proveedores(){
		
		return "Proveedores";
		
	}
	
	@RequestMapping("/tiposinversiones")
	public String TiposInversiones(){
		
		return "TiposInversiones";
		
	}
	
	@RequestMapping("/programas")
	public String Programas(){
		
		return "Programas";
		
	}
	@RequestMapping("/requisiciones")
	public String Requisiciones(){
		
		return "Requisiciones";
		
	}
	
	@RequestMapping("/tiposcontadores")
	public String TiposContadores(){
		
		return "TiposContadores";
		
	}
	
	@RequestMapping("/contadores")
	public String Contadores(){
		
		return "Contadores";
		
	}
	@RequestMapping("/autorizarrequisiciones")
	public String AutorizarRequisiciones(){
		
		return "AutorizarRequisiciones";
		
	}
	@RequestMapping("/permisos")
	public String Permisos(){
		
		return "informatica/permisos";
		
	}
	@RequestMapping("/entregas")
	public String Entregas(){
		
		return "EntregaRequisiciones";
		
	}
	@RequestMapping("/reporteAlmacen")
	public String ReporteAlmacen(){
		
		return "ReportesAlmacen";
		
	}
	@RequestMapping("/distribucionpapeleria")
	public String DistribucionPapeleria(){
		
		return "DistribucionAlm";
		
	}
	@RequestMapping("/vales")
	public String Vales(){
		
		return "Vales";
		
	}
	@RequestMapping("/oficios")
	public String Oficios(){
		
		return "sigedepu/Oficios";
		
	}
	
	@RequestMapping("/pantalla")
	public String Pantalla(){
		
		return "sigedepu/Pantalla";
		
	}
	
        @RequestMapping("/firma")
	public String Firma(){
		
		return "sigedepu/firma";
		
	}
         @RequestMapping("/capturapenal")
	public String CapturaPenal(){
		
		return "estadistica/Capturapenal";
		
	}
        
        @RequestMapping("/indicadores")
	public String Indicadores(){
		
		return "estadistica/Indicadores";
		
	}
        
        @RequestMapping("/constancias")
	public String Constancias(){
		
		return "informatica/constancias";
		
	}
        @RequestMapping("eventos")
	public String Eventos(){
		
		return "informatica/eventos";
		
	}
        @RequestMapping("chat")
	public String Chat(){
		
		return "subtecnica/chat";
		
	}
        
        @RequestMapping("citas")
	public String Citas(){
		
		return "sigedepu/Citas";
		
	}
        
        @RequestMapping("cargaFormulario")
	public String cargaFormulario(String frm){
		System.out.println("frm =>"+frm);
		return frm;
		
	}
	
	
	
	
	

}
