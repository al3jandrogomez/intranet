<div class="form-group row">
	<h1>Captura Asuntos Materia Penal</h1>


</div>
<div id="frmMateriaPenal">
	<div class="dropdown-divider"></div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Fecha:</label>
		<div class="col-10">
			<input class="form-control datepicker" type="text" value=""
				id="txtFechaRegistro">
		</div>
		<label for="example-search-input" class="col-10 col-form-label">Region:</label>
		<div class="col-10">
			<select class="form-control" id="cmbRegion">
			</select>
		</div>
		<label for="example-search-input" class="col-10 col-form-label">Estatus
			:</label>
		<div class="col-10">
			<select class="form-control" id="cmbEstatus">
				<option value="1">Patrocinio Autorizado</option>
				<option value="2">Patrocinio Turnado</option>
			</select>
		</div>
	</div>
	<div class="  form-group row btn-toolbar col-10">
		<!-- 		<button type="button" class="btn btn-primary btn-sm btn-space" -->
		<!-- 			id="guardarArticulo">Guardar</button> -->
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarOficios()">Buscar</button>
		<!-- 		<button type="button" class="btn btn-success btn-space" -->
		<!-- 			onclick="limpiarArticulo()">Limpiar</button> -->
		<!-- 		<button type="button" class="btn btn-danger btn-space">Eliminar</button> -->

	</div>
	<div class="dropdown-divider"></div>
	<!-- 	<div class="form-group "> -->
	<!-- 		<label for="example-search-input" class="col-10 col-form-label">Clave de Verificaci&oacute;n</label> -->
	<!-- 		<div class="col-10"> -->
	<!-- 			<input class="form-control" type="text" value="" id="txtClave"> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 	<div class="form-group "> -->
	<!-- 		<label for="example-search-input" class="col-10 col-form-label">Version</label> -->
	<!-- 		<div class="col-10"> -->
	<!-- 			<input class="form-control" type="text" value="" id="txtVersion"> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 	<div class="form-group "> -->
	<!-- 		<label for="example-search-input" class="col-10 col-form-label">Nombre -->
	<!-- 			Articulo</label> -->
	<!-- 		<div class="col-10"> -->
	<!-- 			<input class="form-control" type="text" value="" id="txtNomArticulo"> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 	<div class="form-group "> -->
	<!-- 		<label for="example-search-input" class="col-10 col-form-label">Descripcion</label> -->
	<!-- 		<div class="col-10"> -->
	<!-- 			<input class="form-control" type="text" value="" id="txtDescripcion"> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 	<div class="form-group "> -->
	<!-- 		<label for="example-search-input" class="col-10 col-form-label">Capacidad</label> -->
	<!-- 		<div class="col-10"> -->
	<!-- 			<input class="form-control" type="text" value="" id="txtCapacidad"> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 	<div class="form-group "> -->
	<!-- 		<label for="example-search-input" class="col-10 col-form-label">Partida</label> -->
	<!-- 		<div class="col-10"> -->
	<!-- 			<select class="form-control" id="cmbPartidas"> -->
	<!-- 				<option value="0">Seleccione la Partida</option> -->
	<!-- 			</select> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 	<div class="form-group "> -->
	<!-- 		<label for="example-search-input" class="col-10 col-form-label">Unidad -->
	<!-- 			de Medida</label> -->
	<!-- 		<div class="col-10"> -->
	<!-- 			<select class="form-control" id="cmbUnidades"> -->
	<!-- 				<option value="0">Seleccione la Unidad de Medida</option> -->
	<!-- 			</select> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 	<div class="form-group "> -->
	<!-- 		<label for="example-search-input" class="col-10 col-form-label">Marca</label> -->
	<!-- 		<div class="col-10"> -->
	<!-- 			<select class="form-control" id="cmbMarcas"> -->
	<!-- 				<option value="0">Seleccione la Marca</option> -->
	<!-- 			</select> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- </div> -->
	<div id="listaOficios" style="display: block"></div>


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
				<div class="modal-body" id="modalCorrect">hola</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cerrar</button>

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
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cerrar</button>

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
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cerrar</button>
					<button type="button" class="btn btn-primary"
						onclick="guardarMateriaPenal();">Guardar Articulo</button>
				</div>
			</div>
		</div>
	</div>
<script>
	
	solicitudAtencion = function(region, fechaRegistro, estatus, fechaRechazo,
			id) {
		this.id = id;
		this.region = region;
		this.fecha_registro = fechaRegistro;
		this.estatus = estatus;
		this.fecha_rechazo = fechaRechazo;

	};
	regiones = function(id, nombre) {
		this.id = id;
		this.nombre = nombre;

	};

	listaAtencion = function(idRegion) {
		console.log("lista Atencion");

		dRegion = new regiones(idRegion, null);
		dSolicitudes = new solicitudAtencion(dRegion);
		dRegion = JSON.stringify(dRegion);

		var lista = "";
		var nombre = "";
		$
				.ajax({
					async : false,
					data : dRegion,
					url : 'atenciones',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {
						// 				$("#modalCorrect").empty();
						// 				$("#modalCorrect").append("Procesando");
						// 				$("#myModalCorrect").modal();
					},
					success : function(response) {
						$('#modulo').empty();
						$('#peticionario').empty();
						$("#list-informativa").empty();
						
						$
								.each(
										response,
										function(index, element) {
											

										});
						$("#list-informativa").append(lista);

					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalError").append("Error al cargar los datos");
						$("#myModalError").modal();
					}
				});

	};
	$(function() {
		
	});
</script>