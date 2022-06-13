<input type="hidden" id="hddCveTipoContador" value="0">
<div class="form-group row">
	<h1>Catalogo de Tipos Contadores</h1>


</div>
<div id="frmTiposContadores">
	<div class="dropdown-divider"></div>
	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			id="guardarTiposContadores">Guardar</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarTiposContadores()">Buscar</button>
		<button type="button" class="btn btn-success btn-space"
			onclick="limpiarTiposContadores()">Limpiar</button>
		<button type="button" class="btn btn-danger btn-space">Eliminar</button>

	</div>
	<div class="dropdown-divider"></div>
	
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Nombre del tipo de contador</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtDescTipoContador">
		</div>
	</div>
</div>
<div id="ListaTiposContadores" style="display: none"></div>

<div class="modal fade" id="myModalCorrect" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel"></h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="modalCorrect"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>

			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="myModalError" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Se encontraron
					algunos errores</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div id="modalErrorText" class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>

			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">Los datos que ingreso son correctos?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
				<button type="button" class="btn btn-primary" onclick="guardarTiposContadores();">Guardar
					Tipo Contador</button>
			</div>
		</div>
	</div>
</div>

<script>
 TipoContador = function(cveTipoContador,descTipoContador,activo){
	 this.cveTipoContador=cveTipoContador;
	 this.descTipoContador= descTipoContador;
	 this.activo=activo;
 }
 
	buscarTiposContadores = function() {

		var cveTipoContador = $("#hddCveTipoContador").val()=="0" ? "":$("#hddCveTipoContador").val();
		
		var descTipoContador = $("#txtDescTipoContador").val()=="" ? null:"%"+$("#txtDescTipoContador").val()+"%";
		
		
		var dTipoContador = new TipoContador(null, descTipoContador,null);
		dTipoContador = JSON.stringify(dTipoContador);
		console.log(dTipoContador);

		$
				.ajax({
					data : dTipoContador,
					url : 'consultartipocontador',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {
						$("#modalCorrect").empty();
						$("#modalCorrect").append("Procesando");
						$("#myModalCorrect").modal();
					},
					success : function(response) {
						$('#myModalCorrect').modal('hide');
						$("#modalCorrect").empty();
						$("#ListaTiposContadores").empty();
						
												if (response.length > 0) {
							var lista = "<table class=\"table\"> <thead>";
							lista += "<thead><tr><th>#</th> <th>Descripcion </th><th>&nbsp;</th></tr></thead><tbody>";
							
							$.each(response, function(index,element){
								lista+="<tr><th scope=\"row\">"+(index+1)+"</th><td>"+element.descTipoContador+"</td><td><img onclick=\"editarTipoContador("+element.cveTipoContador+")\" src=\"resources/images/edit.jpg\" width=40px heigth=40px class=\"img-thumbnail\"><img src=\"resources/images/delete.png\" width=40px heigth=40px class=\"img-thumbnail\" onclick=\"eliminarTipoContador("+element.cveTipoContador+")\"></td></tr>";
							});
							lista+="</tbody></table>";

							$("#ListaTiposContadores").append(lista);
							$("#frmTiposContadores").hide();
							$("#ListaTiposContadores").show();
							
							
						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron tipos de contadores con los criterios ingresados");
							$("#myModalCorrect").modal();
						}
					},
					error: function (xhr, ajaxOptions, thrownError) {
						$("#myModalError")
						.append(
								"No se encontraron tipos de contadores con los criterios ingresados");
				$("#myModalError").modal();
				      }
				});

	}
	
	guardarTiposContadores = function() {

		$('#myModal').modal('hide');
		var cveTipoContador = $("#hddCveTipoContador").val()=="0" ? "":$("#hddCveTipoContador").val();
		
		var descTipoContador = $("#txtDescTipoContador").val()=="" ?"":$("#txtDescTipoContador").val();
		var flag = 1;
		var faltante = "";
		
		if (descTipoContador == "") {
			faltante += "Ingrese el Nombre del tipo de contador<br>";
			flag = 0;
		}
		
		if (flag == 1) {

			
			var dTipoContador = new TipoContador(cveTipoContador, descTipoContador,null);
			dTipoContador = JSON.stringify(dTipoContador);
			console.log(dTipoContador);

			$.ajax({
				data : dTipoContador,
				url : 'guardartipocontador',
				dataType : 'json',
				contentType : "application/json",
				type : 'post',
				beforeSend : function() {
					$("#modalCorrect").empty();
					$("#modalCorrect").append("Procesando");
					$("#myModalCorrect").modal();
				},
				success : function(response) {
					$("#modalCorrect").empty();
					if (response.cveTipoContador > 0){
						$("#modalCorrect").append("Se guardo el tipo de documento");
						limpiarTiposContadores();
					}
					else
						$("#modalCorrect").append(
								"El tipo de documento ya existe verifique los datos");
					$("#myModalCorrect").modal();
				},
				error: function (xhr, ajaxOptions, thrownError) {
					$("#myModalCorrect").modal("hide");
					$("#myModalError")
					.append(
							"No se encontro el tipo de documento  con los criterios ingresados");
			$("#myModalError").modal();
			      }
			});

			// 			$("#modalCorrect").empty();
			// 			$("#modalCorrect").append(dEntrada);
			// 			$("#myModalCorrect").modal();

		} else {
			$("#modalErrorText").empty();
			$("#modalErrorText").append(faltante);

			$("#myModalError").modal();

		}
	};

	limpiarTiposContadores = function() {
		 $("#hddCveTipoContador").val(0);
		 $("#txtDescTipoContador").val("");
		 
	}
	editarTipoContador = function(cveTipoContador){
		limpiarTiposContadores();
		
		$('#myCorrect').modal('hide');
		var dTipoContador = new TipoContador(cveTipoContador, null,null);
		dTipoContador = JSON.stringify(dTipoContador);
		console.log(dTipoContador);
		
		

		$.ajax({
			data : dTipoContador,
			url : 'consultartipocontador',
			dataType : 'json',
			contentType : "application/json",
			type : 'post',
			beforeSend : function() {
				$("#modalCorrect").empty();
				$("#modalCorrect").append("Procesando");
				$("#myModalCorrect").modal();
			},
			success : function(response) {
				
				$.each(response, function(index, element){
					$("#hddCveTipoContador").val(element.cveTipoContador)
					
					$("#txtDescTipoContador").val(element.descTipoContador)
					$("#frmTiposContadores").show();
					$("#ListaTiposContadores").hide();
					
				});
				
			}
		});
	};
		eliminarTipoContador = function(cveTipoContador){
		
		
		$('#myCorrect').modal('hide');
		var dTipoContador = new TipoContador(cveTipoContador, null,null);
		dTipoContador = JSON.stringify(dTipoContador);
		console.log(dTipoContador);
		
		

		$.ajax({
			data : dTipoContador,
			url : 'eliminartipocontador',
			dataType : 'json',
			contentType : "application/json",
			type : 'post',
			beforeSend : function() {
				$("#modalCorrect").empty();
				$("#modalCorrect").append("Procesando");
				$("#myModalCorrect").modal();
			},
			success : function(response) {
				
				
			}
		});
		
		
	}; 
	//FALTA EL METODO PARA ELIMINAR LAS ENTRADAS
	mostrarArticulos = function (){
		if($("#ListaDetalleEntrada").css("display")==="none"){
		$("#ListaDetalleEntrada").show();
		$("#frmEntrada").hide();
	}else{
		$("#ListaDetalleEntrada").hide();
		$("#frmUnidades").show();
	}
	};
	$(function() {
		$(".datepicker").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("#guardarTiposContadores").click(function() {
			$('#myModal').modal();

		});
	});

</script>

