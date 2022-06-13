<input type="hidden" id="hddIdContador" value="0">
<div class="form-group row">
	<h1>Catalogo de Contadores</h1>


</div>
<div id="frmTiposContadores">
	<div class="dropdown-divider"></div>
	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			id="guardarContadores">Guardar</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarContadores()">Buscar</button>
		<button type="button" class="btn btn-success btn-space"
			onclick="limpiarContadores()">Limpiar</button>
		<button type="button" class="btn btn-danger btn-space">Eliminar</button>

	</div>
	<div class="dropdown-divider"></div>

	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Tipo
			de contador</label>
		<div class="col-10">
			<select id="cmbTipoContadores">
				<option value="">Seleccione el tipo de contador</option>
			</select>
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Adscripcion</label>
		<div class="col-10">
			<select id="cmbAdscripciones">
				<option>Seleccione la adscripcion</option>
			</select>
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Numero
			inicial</label>
		<div class="col-10">
			<input type="text" id="txtNumero" />
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">A&ntilde;o</label>
		<div class="col-10">
			<input type="text" id="txtAnio">
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
				<button type="button" class="btn btn-primary"
					onclick="guardarContadores();">Guardar Tipo Contador</button>
			</div>
		</div>
	</div>
</div>

<script>
	TipoContador = function(cveTipoContador, descTipoContador, activo) {
		this.cveTipoContador = cveTipoContador;
		this.descTipoContador = descTipoContador;
		this.activo = activo;
	}

	Contador = function(idContador, tipoContador, numero, anio, activo,
			adscripcion) {

		this.idContador = idContador;
		this.tipoContador = tipoContador;
		this.numero = numero;
		this.anio = anio;
		this.adscripcion = adscripcion;
		this.activo = activo;
	}

	Adscripcion = function(cveAdscripcion, descAdscripcion, activo) {

		this.cveAdscripcion = cveAdscripcion;
		this.descAdscripcion = descAdscripcion;
		this.activo = activo;
	}

	buscarTiposContadores = function() {

		var dTipoContador = new TipoContador(null, null, "S");
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
						$("#cmbTipoContadores").empty();
						if (response.length > 0) {
							var option = "<option value=''>SELECCIONE UN TIPO DE CONTADOR</option>";
							$
									.each(
											response,
											function(index, element) {
												option += "<option value ='"+element.cveTipoContador+"' >"
														+ element.descTipoContador
														+ "</option>";
											});
							$("#cmbTipoContadores").append(option);
						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron tipos de contadores con los criterios ingresados");
							$("#myModalCorrect").modal();
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalError")
								.append(
										"No se encontraron tipos de contadores con los criterios ingresados");
						$("#myModalError").modal();
					}
				});

	}
	buscarAdscripciones = function() {

		var adscripciones = new Adscripcion(null, null, "S");
		adscripciones = JSON.stringify(adscripciones);
		console.log(adscripciones);

		$
				.ajax({
					data : adscripciones,
					url : 'consultaradscripcion',
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
						$("#cmbAdscripciones").empty();

						if (response.length > 0) {
							var option = "<option value=''>SELECCIONE UNA ADSCRIPCION</option>";
							$
									.each(
											response,
											function(index, element) {
												option += "<option value ='"+element.cveAdscripcion+"' >"
														+ element.descAdscripcion
														+ "</option>";
											});
							$("#cmbAdscripciones").append(option);
						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron tipos de contadores con los criterios ingresados");
							$("#myModalCorrect").modal();
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalError")
								.append(
										"No se encontraron tipos de contadores con los criterios ingresados");
						$("#myModalError").modal();
					}
				});

	}

	buscarContadores = function() {

		var idContador = $("#hddIdContador").val() == "0" ? "" : $(
				"#hddIdContador").val();

		var cveTipoContador = $("#cmbTipoContadores").val() == "" ? null : $(
				"#cmbTipoContadores").val();

		var dTipoContador = new TipoContador(null, cveTipoContador, null);

		var cveAdscripcion = $("#cmbAdscripciones").val() == "" ? null : $(
				"#cmbAdscripciones").val();
		var dAdscripciones = new Adscripcion(cveAdscripcion, null, null);

		var numero = $("#txtNumero").val() == "" ? null : $("#txtNumero").val();

		var anio = $("#txtAnio").val() == "" ? null : $("#txtAnio").val();
		var dContador = new Contador(idContador, dTipoContador, numero, anio,
				"S", dAdscripciones)
		dContador = JSON.stringify(dContador);
		console.log(dContador);

		$
				.ajax({
					data : dContador,
					url : 'consultarcontador',
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

							$
									.each(
											response,
											function(index, element) {
												lista += "<tr><th scope=\"row\">"
														+ (index + 1)
														+ "</th><td>"
														+ element.tipoContador.descTipoContador+" "+element.adscripcion.descAdscripcion
														+ "</td><td><img onclick=\"editarContador("
														+ element.idContador
														+ ")\" src=\"resources/images/edit.jpg\" width=40px heigth=40px class=\"img-thumbnail\"><img src=\"resources/images/delete.png\" width=40px heigth=40px class=\"img-thumbnail\" onclick=\"eliminarContador("
														+ element.idContador
														+ ")\"></td></tr>";
											});
							lista += "</tbody></table>";

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
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalError")
								.append(
										"No se encontraron tipos de contadores con los criterios ingresados");
						$("#myModalError").modal();
					}
				});

	}

	guardarContadores = function() {

		$('#myModal').modal('hide');
		
		var idContador = $("#hddIdContador").val() == "0" ? "" : $(
				"#hddIdContador").val();

		var cveTipoContador = $("#cmbTipoContadores").val() == "" ? null : $(
				"#cmbTipoContadores").val();

		var dTipoContador = new TipoContador(cveTipoContador,null , null);

		var cveAdscripcion = $("#cmbAdscripciones").val() == "" ? null : $(
				"#cmbAdscripciones").val();
		var dAdscripciones = new Adscripcion(cveAdscripcion, null, null);

		var numero = $("#txtNumero").val() == "" ? null : $("#txtNumero").val();

		var anio = $("#txtAnio").val() == "" ? null : $("#txtAnio").val();
		
		var flag = 1;
		var faltante = "";

		if (cveTipoContador == null) {
			faltante += "Seleccione un tipo de contador<br>";
			flag = 0;
		}
		if (cveAdscripcion == null) {
			faltante += "Seleccione una adscripcion<br>";
			flag = 0;
		}
		if (numero == null) {
			faltante += "ingrese el numero inicial del contador<br>";
			flag = 0;
		}else if(numero<1){
				faltante += "El numero inicial debe se mayor<br>";
				flag = 0;
			}
		
		if (anio == null) {
			faltante += "ingrese el a&ntilde;o para el contador<br>";
			flag = 0;
		}else if(anio<2015){
			faltante += "el año debe ser mayor a 2014<br>";
			flag = 0;
			
		}

		if (flag == 1) {

			var dContador = new Contador(idContador, dTipoContador, numero, anio,
					"S", dAdscripciones)
			dContador = JSON.stringify(dContador);
			console.log(dContador);

			$
					.ajax({
						data : dContador,
						url : 'guardarcontador',
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
							if (response.idContador > 0) {
								$("#modalCorrect").append(
										"Se guardo el Contador");
								limpiarContadores();
							} else
								$("#modalCorrect")
										.append(
												"El contador ya existe verifique los datos");
							$("#myModalCorrect").modal();
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$("#myModalCorrect").modal("hide");
							$("#myModalError")
									.append(
											"No se encontro el contador  con los criterios ingresados");
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

	limpiarContadores = function() {
		$("#hddIdContador").val(0);
		$("#txtDescTipoContador").val("");

	}
	editarContador = function(cveTipoContador) {
		limpiarContadores();

		$('#myCorrect').modal('hide');
		var dTipoContador = new TipoContador(cveTipoContador, null, null);
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

				$.each(response, function(index, element) {
					$("#hddIdContador").val(element.cveTipoContador)

					$("#txtDescTipoContador").val(element.descTipoContador)
					$("#frmTiposContadores").show();
					$("#ListaTiposContadores").hide();

				});

			}
		});
	};
	eliminarContador = function(idContador) {

		$('#myCorrect').modal('hide');
		var dContador = new TipoContador(idContador, null, null);
		dContador = JSON.stringify(dContador);
		console.log(dTipoContador);

		$.ajax({
			data : dTipoContador,
			url : 'eliminarcontador',
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
	mostrarArticulos = function() {
		if ($("#ListaDetalleEntrada").css("display") === "none") {
			$("#ListaDetalleEntrada").show();
			$("#frmEntrada").hide();
		} else {
			$("#ListaDetalleEntrada").hide();
			$("#frmUnidades").show();
		}
	};
	$(function() {
		$(".datepicker").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("#guardarContadores").click(function() {
			$('#myModal').modal();

		});

		buscarTiposContadores();
		buscarAdscripciones();
	});
</script>

