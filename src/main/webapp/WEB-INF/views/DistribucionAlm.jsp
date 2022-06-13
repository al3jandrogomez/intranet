<input type="hidden" id="hddIdDistribucion" value="0">
<input type="hidden" id="hddIdDetalleDistribucion" value="0">
<input type="hidden" id="hddCveArticulo" value="0">
<input type="hidden" id="hddCveUsuario" value="0">
<div class="form-group row">
	<h1>Distribuci&oacute;n y Entrega de Papeleria y/o Consumibles</h1>


</div>
<div id="frmDistribuciones" style="display: none">
	<div class="dropdown-divider"></div>
	<label for="example-search-input" class="col-10 col-form-label"
		id="numdistribucion"></label>
	<div class="  form-group row btn-toolbar col-10">

		<button type="button" class="btn btn-primary btn-sm btn-space" id=""
			onclick='guardardetalledistribucion()'>Agregar a la
			distribuci&oacute;n</button>
		<button type="button" class="btn btn-sm btn-info btn-space"
			onclick="limpiarDetalle();">Limpiar Campos</button>
		<button type="button" class="btn btn-sm btn-info btn-success"
			onclick="enviarDistribucion()">Enviar Distribuci&oacute;n</button>

		<button type="button" class="btn btn-sm btn-info btn-space"
			data-toggle="modal" data-target="#exampleModalRes"
			data-whatever="@getbootstrap">Capturar Observaciones</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onclick="regresarListaDistribucion()">Salir</button>


	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Nombre
			del articulo</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtAddArticulo">
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">cantidad</label>
		<div class="col-10">
			<input class="form-control" type="text" value=""
				id="txtCantidadRequerida">
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Nombre
			Servidor Publico</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtnombre">
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Observaciones</label>
		<div id="observaciones" class="col-10"></div>
	</div>
	<div class="dropdown-divider"></div>
</div>
<div id="ListaDistribuciones" style="display: block">
	<div class="dropdown-divider"></div>

	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			onclick="nuevaDistribucion()">Nueva distribuci&oacute;n</button>
	</div>
	<div class="dropdown-divider"></div>
	<div id="tblListaDistribuciones"></div>
</div>

<div id="ListaDetalleRequisiciones" style="display: none">
	<div class="dropdown-divider"></div>

</div>

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
				<h5 class="modal-title" id="exampleModalLabel"></h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">�Esta seguro que quiere generar una
				nueva solicitud?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
				<button type="button" class="btn btn-primary"
					onclick="guardarRequisiciones();">Generar Nueva Solicitud</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModalNoAut" tabindex="-1" role="dialog"
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
			<div class="modal-body">�Esta seguro que no desea autorizar la
				solicitud?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
				<button type="button" class="btn btn-primary"
					onclick="autorizarSolicitud(2);">No autorizar solicitud</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
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
			<div class="modal-body">�la informacion capturada es correcta?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
				<button type="button" class="btn btn-primary"
					onclick="agregarArticulo();">Si</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
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
			<div class="modal-body">�Esta seguro que quiere autorizar la
				solicitud?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
				<button type="button" class="btn btn-primary"
					onclick="autorizarSolicitud(1);">Si</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="exampleModalRes" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Observaciones de
					la distribuci&oacute;n</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form>

					<div class="form-group">
						<label for="message-text" class="col-form-label">Observaci&oacute;n:</label>
						<textarea class="form-control" id="message-text"></textarea>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
				<button type="button" class="btn btn-primary"
					onclick="guardarObservacion()">Guardar</button>
			</div>
		</div>
	</div>
</div>


<script>
	Requisicion = function(idRequisicion, cveUsuario, cveAdscripcion, activo,
			cveEstatusRequisicion, numeroRequisicion, anioRequisicion) {
		this.numeroRequisicion = numeroRequisicion;
		this.anioRequisicion = anioRequisicion;
		this.idRequisicion = idRequisicion;
		this.cveUsuario = cveUsuario;
		this.cveAdscripcion = cveAdscripcion;
		this.activo = activo;
		this.cveEstatusRequisicion = cveEstatusRequisicion;

	};
	estatusRequisicion = function(cveEstatusRequisicion) {
		this.cveEstatusRequisicion = cveEstatusRequisicion;
	};
	movRequisicion = function(requisicion, estatusRequisicion, observaciones,
			idMovRequisicion) {
		this.requisicion = requisicion;
		this.estatusRequisicion = estatusRequisicion;
		this.observaciones = observaciones;
		this.idMovRequisicion = idMovRequisicion;

	};

	regresarRequisicion = function() {
		var idRequisicion = $("#hddIdRequisiciones").val() == 0 ? null : $(
				"#hddIdRequisiciones").val();

		var observaciones = $("#message-text").val();
		$("#exampleModalRes").modal('hide');
		console.log("regresar requisicion" + observaciones + " idRequisicion "
				+ idRequisicion);
		dRequisicion = new Requisicion(idRequisicion, null, null, null,
				idRequisicion, null, null);
		dEstatusRequisicion = new estatusRequisicion(8);
		dMovRequisicion = new movRequisicion(dRequisicion, dEstatusRequisicion,
				observaciones, idRequisicion);

		dMovRequisicion = JSON.stringify(dMovRequisicion);
		console.log(dMovRequisicion);
		$
				.ajax({
					data : dMovRequisicion,
					url : 'regresarrequisicion',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$("#modalCorrect").empty();
						$("#myModalCorrect").modal('hide');
						$("#myModal2").modal("hide");
						if (response.idDetalleRequisicion > 0) {
							$("#modalCorrect").append("Se guardo el detalle");
							limpiarRequisiciones();
							mostrarDetalleRequisicion(idRequisicion);
						} else {
							$("#modalCorrect")
									.append(
											"El detalle  ya existe verifique los datos");
							$("#myModalCorrect").modal();
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalCorrect").modal("hide");
						$("#myModal2").modal("hide");

						$("#myModalError")
								.append(
										"No se encontraron programas con los criterios ingresados");
						$("#myModalError").modal();
					}
				});
	};

	agregarArticulo = function() {

		var idRequisicion = $("#hddIdRequisiciones").val() == 0 ? null : $(
				"#hddIdRequisiciones").val();
		var idDetalleRequisicion = $("#hddIdDetalleRequisicion").val() == 0 ? null
				: $("#hddIdDetalleRequisicion").val();
		var cveArticulo = $("#hddCveArticulo").val();
		var cantidad = $("#txtCantidadRequerida").val();

		var flag = 1;
		var faltante = "";
		if (cantidad == "") {
			faltante += "Falta ingresar la cantidad de articulos<br>";
			flag = 0;
		}
		if (cveArticulo == 0) {
			faltante += "Falta seleccionar un articulo<br>";
			flag = 0;
		}

		if (flag == 1) {
			dArticulo = new Articulo(cveArticulo);

			dRequisicion = new Requisicion(idRequisicion, null, null, "S")
			dDetalleRequisicion = new DetalleRequisicion(idDetalleRequisicion,
					dRequisicion, dArticulo, cantidad, "S");

			dDetalleRequisicion = JSON.stringify(dDetalleRequisicion);
			console.log(dDetalleRequisicion);
			$
					.ajax({
						data : dDetalleRequisicion,
						url : 'guardardrequisicion',
						dataType : 'json',
						contentType : "application/json",
						type : 'post',
						beforeSend : function() {

						},
						success : function(response) {
							$("#modalCorrect").empty();
							$("#myModalCorrect").modal('hide');
							$("#myModal2").modal("hide");
							if (response.idDetalleRequisicion > 0) {
								$("#modalCorrect").append(
										"Se guardo el detalle");
								limpiarRequisiciones();
								mostrarDetalleRequisicion(idRequisicion);
							} else {
								$("#modalCorrect")
										.append(
												"El detalle  ya existe verifique los datos");
								$("#myModalCorrect").modal();
							}
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$("#myModalCorrect").modal("hide");
							$("#myModal2").modal("hide");

							$("#myModalError")
									.append(
											"No se encontraron programas con los criterios ingresados");
							$("#myModalError").modal();
						}
					});
		} else {
			$("#modalCorrect").empty();
			$("#myModalCorrect").modal('hide');
			$("#myModal2").modal("hide");
			$("#modalErrorText").empty();
			$("#modalErrorText").append(faltante);

			$("#myModalError").modal();

		}

	};

	autorizarSolicitud = function(tipoAut) {
		var operacion = "autorizarRequ";
		var idRequisicion = $("#hddIdRequisiciones").val();
		dRequisicion = new Requisicion(idRequisicion, null, null, null);
		dRequisicion = JSON.stringify(dRequisicion);
		console.log(dRequisicion);

		if (tipoAut == 1)
			operacion = "autorizarreq";
		else if (tipoAut == 2)
			operacion = "noautorizarreq";

		$
				.ajax({
					data : dRequisicion,
					async : false,
					url : operacion,
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$("#ListaDetalleRequisiciones").empty();
						$("#modalCorrect").empty();
						$("#myModal3").modal("hide");
						$("#myModal3").hide();
						$("#myModalNoAut").modal("hide");
						$("#myModalNoAut").hide();
						if (response.idRequisicion > 0) {
							$("#hddIdRequisiciones").val(0);
							$("#frmRequisiciones").hide();
							$("#ListaRequisiciones").show();

							limpiarRequisiciones();
							buscarRequisiciones(3);

						} else {
							$("#modalCorrect")
									.append(
											"El detalle  ya existe verifique los datos");
							$("#myModalCorrect").modal();
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#modalCorrect").empty();
						$("#myModalCorrect").modal("hide");
						$("#myModal3").hide();
						$("#myModal3").modal('hide');
						$("#myModalNoAut").hide();
						$("#myModalNoAut").modal('hide');

						$("#myModalError")
								.append(
										"No se encontraron programas con los criterios ingresados");
						$("#myModalError").modal();
					}
				});

	};

	guardarRequisiciones = function(tipoAut) {
		// 		$("#modalCorrect").empty();
		// 		$("#modalCorrect").append("Procesando");
		// 		$("#myModalCorrect").modal();
		$("#enviarRequisicion").hide();

		$("#myModalCorrect").modal('hide');
		$("#myModal").modal('hide');
		dRequisicion = new Requisicion(null, 1, 2, "R");
		dRequisicion = JSON.stringify(dRequisicion);
		console.log(dRequisicion);

		$
				.ajax({
					data : dRequisicion,
					async : false,
					url : 'guardarrequisicion',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$("#modalCorrect").empty();
						$("#myModalCorrect").modal("hide");
						$("#myModal").hide();
						if (response.idRequisicion > 0) {
							$("#hddIdRequisiciones")
									.val(response.idRequisicion);
							$("#frmRequisiciones").show();
							$("#ListaRequisiciones").hide();

							limpiarRequisiciones();
							buscarRequisiciones(3)

						} else {
							$("#modalCorrect")
									.append(
											"El detalle  ya existe verifique los datos");
							$("#myModalCorrect").modal();
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {

						$("#myModalError")
								.append(
										"No se encontraron programas con los criterios ingresados");
						$("#myModalError").modal();
					}
				});

	};

	$("#txtAddArticulo")
			.autocomplete(
					{
						source : function(request, response) {
							var nomArticulo = $("#txtAddArticulo").val() == "" ? null
									: "%" + $("#txtAddArticulo").val() + "%";

							var dArticulo = new Articulo(null, nomArticulo,
									null, null, null, null, null, null, null);
							dArticulo = JSON.stringify(dArticulo);
							console.log(dArticulo);

							$
									.ajax({
										data : dArticulo,
										url : 'buscarlikearticulos',
										dataType : 'json',
										contentType : "application/json",
										type : 'post',
										beforeSend : function() {

										},
										success : function(data) {
											response($
													.map(
															data,
															function(item) {
																return {
																	label : item.descArticulo,
																	value : item.cveArticulo
																}
															}));
										},
										error : function(xhr, ajaxOptions,
												thrownError) {
											$('#myModal').modal('hide');
											$("#myModalCorrect").modal("hide");
											$("#myModalError")
													.append(
															"No se pudo registrar el articulo, verifique los datos");
											$("#myModalError").modal();
										}
									});

						},
						minlength : 2,
						select : function(event, ui) {
							event.preventDefault();
							console.log(ui.item ? "Selected: " + ui.item.label
									: "Nothing selected, input was "
											+ this.value);
							$("#txtAddArticulo").val(ui.item.label);
							$("#hddCveArticulo").val(ui.item.value);

						},
						open : function() {
							$(this).removeClass("ui-corner-all").addClass(
									"ui-corner-top");
						},
						close : function() {
							$(this).removeClass("ui-corner-top").addClass(
									"ui-corner-all");
						}
					});
	limpiarRequisiciones = function() {
		$("#hddCveArticulo").val(0);
		$("#hddIdDetalleRequisicion").val(0);
		$("#txtAddArticulo").val("");
		$("#txtCantidadRequerida").val("");

	};
	DetalleRequisicion = function(idDetalleRequisicion, requisicion, articulo,
			cantidadRequerida, cantidadAutorizada, activo, observaciones) {
		this.idDetalleRequisicion = idDetalleRequisicion;
		this.requisicion = requisicion;
		this.articulo = articulo;
		this.cantidadRequerida = cantidadRequerida;
		this.cantidadAutorizada = cantidadAutorizada;
		this.activo = activo;
		this.observaciones = observaciones;

	};
	mostrarDetalleRequisicion = function(idRequisicion) {
		$("#frmRequisiciones").show();
		$("#ListaDetalleRequisiciones").show();
		$("#ListaRequisiciones").hide();
		$("#myModalCorrect").modal('hide');
		$("#myModal").modal('hide');
		$("#ListaDetalleRequisiciones").empty();
		dRequisicion = new Requisicion(idRequisicion, null, null, null);
		dRequisiciones = new DetalleRequisicion(null, dRequisicion, null, null,
				null, null);
		dRequisiciones = JSON.stringify(dRequisiciones);
		console.log(dRequisiciones);
		$
				.ajax({
					data : dRequisiciones,
					async : false,
					url : 'buscardetallerequisicion',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$("#modalCorrect").empty();
						$("#myModalCorrect").modal("hide");
						$("#myModal").hide();
						if (response.length > 0) {
							$("#enviarRequisicion").show();
							$("#hddIdRequisiciones").val(idRequisicion);
							$("#ListaRequisiciones").hide();
							var lista = "<table class=\"table\"> <thead>";
							lista += "<thead><tr><th>#</th> <th>Articulo</th><th>Cantidad solicitada</th><th>Cantidad autorizada</th><th>Observaciones</th> <th>Disponibilidad en Almacen</th><th>&nbsp;</th></tr></thead><tbody>";

							$
									.each(
											response,
											function(index, element) {
												if (index == 0) {
													$("#adsSolicitante")
															.empty();
													$("#adsSolicitante")
															.append(
																	"<h2 style='font-size:12px; font-weight:bold;'>"
																			+ element.requisicion.adscripcion.descAdscripcion
																			+ " Solicitud: "
																			+ element.requisicion.numeroRequisicion
																			+ "/"
																			+ element.requisicion.anioRequisicion
																			+ "</h2> ");
												}

												lista += "<tr><th scope=\"row\">"
														+ (index + 1)
														+ "</th><td>"
														+ element.articulo.descArticulo
														+ " "
														+ element.articulo.marcas.descMarca

														+ "</td><td>"
														+ element.cantidadRequerida
														+ "</td><td><input  class ='txtAutorizadas' id = 'txt"+element.idDetalleRequisicion+"' type='text' value='"+element.cantidadAutorizada+"' />"
														+ "</td><td> <input class='txtObservaciones' type ='text' id='txt"+element.idDetalleRequisicion+"obs' value='"+element.observaciones+"' name = 'observacionesAutorizadas[]'/>"
														+ "</td><td>&nbsp;"
														//+ element.disponibilidad+
														+ "</td><td>"
												// 														<img onclick=\"cargarDetalleRequisicion("
												// 														+ element.idDetalleRequisicion
												// 														+ ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\">
												"</td></tr>";
											});
							lista += "</tbody></table>";
							$("#ListaDetalleRequisiciones").append(lista);

						} else {
							$("#enviarRequisicion").hide();
							$("#hddIdRequisiciones").val(idRequisicion);
							$("#ListaDetalleRequisiciones").append(
									"Sin Articulos");
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {

						$("#myModalError")
								.append(
										"No se encontraron programas con los criterios ingresados");
						$("#myModalError").modal();
					}
				});

	};

	testCampos = function() {
		var lista = [];
		var cantidadAutorizada = $(".txtAutorizadas");
		var observaciones = $(".txtObservaciones");

		console.log(" cantidad de campos cantidades="
				+ cantidadAutorizada.length);
		console.log(" cantidad de campos observaciones= "
				+ observaciones.length);

		var idRequisicion = $("#hddIdRequisiciones").val();
		$.each(cantidadAutorizada, function(index, element) {
			cantidad = element.value;
			observacion = observaciones[index].value;
			idDetalleRequisicion = element.id.split("txt");

			dRequisicion = new Requisicion(idRequisicion, null, null, "S")
			dDetalleRequisicion = new DetalleRequisicion(
					idDetalleRequisicion[1], dRequisicion, null, null,
					cantidad, "S", observacion);
			lista.push(dDetalleRequisicion);
		});
		lista = JSON.stringify(lista);
		console.log(lista);

		$
				.ajax({
					data : lista,
					url : 'guardardrequisicionaut',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$("#modalCorrect").empty();
						$("#myModalCorrect").modal('hide');
						$("#myModal2").modal("hide");
						if (response[0].idDetalleRequisicion > 0) {
							$("#modalCorrect").append(
									"se guardar&oacute;n los cambios");
							$("#myModalCorrect").modal();
						} else {
							$("#modalCorrect")
									.append(
											"El detalle  ya existe verifique los datos");
							$("#myModalCorrect").modal();
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalCorrect").modal("hide");
						$("#myModal2").modal("hide");

						$("#myModalError")
								.append(
										"No se encontraron programas con los criterios ingresados");
						$("#myModalError").modal();
					}
				});

	};

	buscarRequisiciones = function(cveEstatusRequisicion) {

		var numero = null;
		var anio = null;
		var busqueda = 1;
		var mensaje = "";
		var operacion = "";

		if (cveEstatusRequisicion == null) {
			numero = $("#txtNumero").val();
			anio = $("#txtAnio").val();
			if (numero === "") {
				mensaje += "Debe ingresar el numero de la solicitud<br>";
				busqueda = 0;
			}
			if (anio === "") {
				mensaje += "Debe ingresar el a&ntilde;o de la solicitud<br>";
				busqueda = 0;
			}
			operacion = "buscarrequisionesEntrega";
			busqueda = 1;

		} else {
			operacion = "buscarrequisionesaut";
			cveEstatusRequisicion = $("#cmbListaEstatus").val();
		}
		if (busqueda == 1) {
			$("#ListaRequisiciones").show();
			$("#frmRequisiciones").hide();
			$("#adsSolicitante").empty();
			$("#ListaDetalleRequisiciones").hide();
			$("#tblListaRequisiciones").empty();
			$("#myModalCorrect").modal('hide');
			$("#myModal").modal('hide');
			dRequisicion = new Requisicion(cveEstatusRequisicion, null, null,
					null, cveEstatusRequisicion, numero, anio);
			dRequisicion = JSON.stringify(dRequisicion);
			console.log(dRequisicion);
			$
					.ajax({
						data : dRequisicion,
						async : false,
						url : operacion,
						dataType : 'json',
						contentType : "application/json",
						type : 'post',
						beforeSend : function() {
							$("#ListaDetalleRequisiciones").empty();
							$("#ListaDetalleRequisiciones").hide();
							$("#tblListaRequisiciones").empty();
							$("#hddIdRequisiciones").val(0);
						},
						success : function(response) {
							$("#modalCorrect").empty();
							$("#myModalCorrect").modal("hide");
							$("#myModal").hide();
							if (response.length > 0) {
								$("#ListaDetalleRequisiciones").empty();
								var numero = "";
								var descAdscripcion = "";
								var fechaenvio = "";
								var botones = "";
								var estatus = "";
								var lista = "<table class=\"table\"> <thead>";
								lista += "<thead><tr><th>#</th> <th>Numero Solicitud</th><th>Fecha Registro</th> <th>Fecha Autorizacion</th> <th>Estatus</th><th>&nbsp;</th></tr></thead><tbody>";

								$
										.each(
												response,
												function(index, element) {

													botones = "";
													numero = "";
													descAdscripcion = "";
													fechaenvio = "";
													estatus = "";
													if (element.numeroRequisicion == null)
														numero = "";
													else
														numero = element.numeroRequisicion
																+ "/"
																+ element.anioRequisicion;
													if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion == 3) {
														estatus = "RECIBIDA";

														botones = "<img onclick=\"mostrarDetalleRequisicion("
																+ element.idRequisicion
																+ ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\">";
													} else if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion >= 4
															&& element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion <= 6) {
														estatus = element.movRequisicion[0].estatusRequisicion.descEstatusRequisicion;
														botones = "<img onclick=\"mostrarDetalleRequisicion("
																+ element.idRequisicion
																+ ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\">";
														if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion >= 3) {
															fechaenvio = cambiarFecha2(element.movRequisicion[0].fechaRegistro);

														}
													} else {
														estatus = element.movRequisicion[0].estatusRequisicion.descEstatusRequisicion;
													}

													if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion == 4
															|| element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion == 6) {
														fechaenvio = cambiarFecha2(element.movRequisicion[0].fechaRegistro);
														botones += "<img onclick=\"imprimirDocumento("
																+ element.idRequisicion
																+ ")\" class='documento' src=\"resources/images/pdf3.png\"  class=\"img-thumbnail\">";
													} else {
														fechaenvio
													}

													lista += "<tr><th scope=\"row\">"
															+ (index + 1)
															+ "</th><td><b>"
															+ numero
															+ "</b> <br>"
															+ element.adscripcion.descAdscripcion
															+ "</td><td>"
															+ cambiarFecha2(element.fechaRegistro)
															+ "</td><td>"
															+ fechaenvio
															+ "</td><td>"
															+ estatus
															+ "</td><td>"
															+ botones
															+ "</td></tr>";
												});
								lista += "</tbody></table>";
								$("#tblListaRequisiciones").append(lista);
								$("#ListaRequisiciones").show();
								$("#frmRequisiciones").hide();

							} else {
								$("#modalCorrect").append(
										"No hay requisiciones capturadas");
								$("#myModalCorrect").modal();
							}
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$("#modalErrorText").empty();
							$("#modalErrorText")
									.append(
											"No se encontraron programas con los criterios ingresados");
							$("#myModalError").modal();
						}
					});
		} else {
			$("#modalErrorText").empty();
			$("#modalErrorText").append(mensaje);
			$("#myModalError").modal();
		}

	};
	limpiarBusqueda = function() {
		$("#txtNumero").val("");
		$("#txtAnio").val("");
		var cmbEstatus = $("#cmbListaEstatus").val();
		buscarRequisiciones(cmbEstatus);

	}
	cargarDetalleRequisicion = function(idDetalleRequisicion) {
		var cantidadAutorizada = 0;
		var observaciones = "";
		if ($("#txt" + idDetalleRequisicion).length == 0) {
			cantidadAutorizada = $("#td" + idDetalleRequisicion).text();
			$("#td" + idDetalleRequisicion).empty();
			observaciones = $("#td" + idDetalleRequisicion + "obs").text();
			$("#td" + idDetalleRequisicion + "obs").empty();
			var input = "<input id ='txt"+idDetalleRequisicion+"' type='text' value='"+cantidadAutorizada+"'/>";
			var observaciones = "<input id ='txt"+idDetalleRequisicion+"obs' type='text' value='"+observaciones+"'/>";
			$("#td" + idDetalleRequisicion).append(input);
			$("#td" + idDetalleRequisicion + "obs").append(observaciones);

		} else {
			var idRequisicion = $("#hddIdRequisiciones").val();
			cantidadAutorizada = $("#txt" + idDetalleRequisicion).val();
			observaciones = $("#txt" + idDetalleRequisicion + "obs").val();

			dRequisicion = new Requisicion(idRequisicion, null, null, "S")
			dDetalleRequisicion = new DetalleRequisicion(idDetalleRequisicion,
					dRequisicion, null, cantidadAutorizada, "S", observaciones);

			dDetalleRequisicion = JSON.stringify(dDetalleRequisicion);
			console.log(dDetalleRequisicion);
			$
					.ajax({
						data : dDetalleRequisicion,
						url : 'guardardrequisicionaut',
						dataType : 'json',
						contentType : "application/json",
						type : 'post',
						beforeSend : function() {

						},
						success : function(response) {
							$("#modalCorrect").empty();
							$("#myModalCorrect").modal('hide');
							$("#myModal2").modal("hide");
							if (response.idDetalleRequisicion > 0) {
								$("#modalCorrect").append(
										"Se guardo el detalle");
								mostrarDetalleRequisicion(idRequisicion);
							} else {
								$("#modalCorrect")
										.append(
												"El detalle  ya existe verifique los datos");
								$("#myModalCorrect").modal();
							}
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$("#myModalCorrect").modal("hide");
							$("#myModal2").modal("hide");

							$("#myModalError")
									.append(
											"No se encontraron programas con los criterios ingresados");
							$("#myModalError").modal();
						}
					});

		}

	};

	enviarSolicitud = function(idRequisicion) {
		idRequisicion = idRequisicion == null ? $("#hddIdRequisiciones").val()
				: idRequisicion;
		dRequisicion = new Requisicion(idRequisicion, null, 2, null);
		dRequisicion = JSON.stringify(dRequisicion);
		console.log(dRequisicion);
		$.ajax({
			data : dRequisicion,
			async : false,
			url : 'enviarrequisicion',
			dataType : 'json',
			contentType : "application/json",
			type : 'post',
			beforeSend : function() {

			},
			success : function(response) {
				$("#modalCorrect").empty();
				$("#myModalCorrect").modal("hide");
				$("#myModal").modal('hide');
				$("#myModal3").modal('hide');
				if (response.idRequisicion > 0) {

					$("#frmRequisiciones").hide();
					$("#ListaDetalleRequisiciones").hide();
					$("#ListaRequisiciones").show();

					limpiarRequisiciones();
					buscarRequisiciones(3);

				} else {
					$("#modalCorrect").append(
							"El detalle  ya existe verifique los datos");
					$("#myModalCorrect").modal();
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {

				$("#myModalError").append("No se pudo enviar la solicitud");
				$("#myModalError").modal();
			}
		});

	}

	eliminarSolicitudes = function(idRequisicion) {
		idRequisicion = idRequisicion == null ? $("#hddIdRequisiciones").val()
				: idRequisicion;
		dRequisicion = new Requisicion(idRequisicion, null, 2, null);
		dRequisicion = JSON.stringify(dRequisicion);
		console.log(dRequisicion);
		$.ajax({
			data : dRequisicion,
			async : false,
			url : 'eliminarrequisicion',
			dataType : 'json',
			contentType : "application/json",
			type : 'post',
			beforeSend : function() {

			},
			success : function(response) {
				$("#modalCorrect").empty();
				$("#myModalCorrect").modal("hide");
				$("#myModalEliminar").modal('hide');

				if (response.idRequisicion > 0) {

					$("#frmRequisiciones").hide();
					$("#ListaDetalleRequisiciones").hide();
					$("#ListaRequisiciones").show();

					limpiarRequisiciones();
					buscarRequisiciones();

				} else {
					$("#modalCorrect").append(
							"Error al eliminar la requisicion");
					$("#myModalCorrect").modal();
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$("#myModalEliminar").modal('hide');

				$("#myModalError").append("No se pudo enviar la solicitud");
				$("#myModalError").modal();
			}
		});
	};
	var EstatusReq = function(cveEstatusRequisicion) {
		this.cveEstatusRequisicion = cveEstatusRequisicion;
	}

	ListaEstatus = function() {
		dEstatusReq = new EstatusReq(null);
		dEstatusReq = JSON.stringify(dEstatusReq);
		console.log(dEstatusReq);
		$
				.ajax({
					data : dEstatusReq,
					async : false,
					url : 'listaestatusreq',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$("#modalCorrect").empty();
						$("#myModalCorrect").modal("hide");
						$("#myModalEliminar").modal('hide');
						var combo = "";
						var desc = "";
						if (response.length > 0) {
							$
									.each(
											response,
											function(index, element) {

												if (element.cveEstatusRequisicion > 2) {
													desc = element.cveEstatusRequisicion === 3 ? "RECIBIDA"
															: element.descEstatusRequisicion
													combo += "<option value='"+element.cveEstatusRequisicion+"'>"
															+ desc
															+ "</option>";
												}
											});
							$("#cmbListaEstatus").append(combo);

						} else {
							$("#modalCorrect").append(
									"Error al eliminar la requisicion");
							$("#myModalCorrect").modal();
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalEliminar").modal('hide');

						$("#myModalError").append(
								"No se pudo enviar la solicitud");
						$("#myModalError").modal();
					}
				});

	};

	// 	######################################################################################################################################

	Articulo = function(cveArticulo, descArticulo, especificacion, capacidad,
			unidad, marca, clave, version, partida) {
		this.cveArticulo = cveArticulo;
		this.descArticulo = descArticulo;
		this.especificacion = especificacion;
		this.capacidad = capacidad;
		this.unidades = unidad;
		this.marcas = marca;
		this.clave = clave;
		this.version = version;
		this.partidas = partida;

	};
	distribucion = function(idDistribucion, cveAdscripcion, activo,
			fechaRegistro, fechaActualizacion, observaciones) {
		this.idDistribucion = idDistribucion;
		this.cveAdscripcion = cveAdscripcion;
		this.activo = activo;
		this.fechaRegistro = fechaRegistro;
		this.fechaActualizacion = fechaActualizacion;
		this.observaciones = observaciones;

	};
	imprimirDocumento = function(idDetalleRequisicion) {

		var key = "";
		var data = "";

		key = "idDetalleRequisicion";
		data = idDetalleRequisicion;
		;
		var url = "/reportes/reportedistribucion";

		var form = $('<form target="_blank"></form>').attr('action', url).attr(
				'method', 'post');
		// Add the one key/value
		form.append($("<input></input>").attr('type', 'hidden').attr('name',
				key).attr('value', data));
		form.appendTo('body').submit();

	}
	buscarDistribuciones = function() {

		$
				.ajax({
					// 		data : dEstatusReq,
					async : false,
					url : 'buscardistribuciones',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$("#modalCorrect").empty();
						$("#myModalCorrect").modal("hide");
						$("#myModalEliminar").modal('hide');
						$("#tblListaDistribuciones").empty();
						;
						var combo = "";
						var desc = "";
						if (response.length > 0) {
							var lista = "<table class=\"table\"> <thead>";
							lista += "<thead><tr><th>#</th> <th>Numero de Distribucion</th><th>Fecha Registro</th> <th>Fecha De envio</th><th>Estatus</th></tr></thead><tbody>";

							$
									.each(
											response,
											function(index, element) {

												botones = "";
												numero = "";
												descAdscripcion = "";
												fechaenvio = "";
												estatus = "";
												if (element.numeroDistribucion == null) {
													numero = "";
													estatus = "No Enviada";
													botones = "<img onclick=\"editarDistribucion("
															+ element.idDistribucion
															+ ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\"><img class=\"eliminar\" onclick=\"eliminarDistribucion("
															+ element.idDistribucion
															+ ")\"	src=\"resources/images/delete.png\"  class=\"img-thumbnail\"/>";
												} else {

													numero = element.numeroDistribucion
															+ "/"
															+ element.anioDistribucion;

													estatus = "Enviada";
													botones += "<img onclick=\"imprimirDocumento("
															+ element.idDistribucion
															+ ")\" class='documento' src=\"resources/images/pdf3.png\"  class=\"img-thumbnail\">";
												}
												// 												if (element.movRequisicion[0].estatusRequisicion.cveEstatusRequisicion == 3) {
												// 													estatus = "RECIBIDA";

												lista += "<tr><th scope=\"row\">"
														+ (index + 1)
														+ "</th><td><b>"
														+ numero
														+ "</b> <br>"

														+ "</td><td>"
														+ cambiarFecha2(element.fechaRegistro)
														+ "</td><td>"
														+ fechaenvio
														+ "</td><td>"
														+ estatus
														+ "</td><td>"
														+ botones
														+ "</td></tr>";
											});
							lista += "</tbody></table>";
							$("#tblListaDistribuciones").append(lista);

						} else {
							$("#modalCorrect").append(
									"No hay distribuciones capturadas ");
							$("#myModalCorrect").modal();
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalEliminar").modal('hide');

						$("#myModalError").append(
								"No se pudo enviar la solicitud");
						$("#myModalError").modal();
					}
				});

	};
	nuevaDistribucion = function() {
		console.log("nueva distribucion");
		$("#frmDistribuciones").show();
		$("#ListaDistribuciones").hide();
		ddistribucion = new distribucion(null, null, null, null, null);
		ddistribucion = JSON.stringify(ddistribucion);

		$.ajax({
			data : ddistribucion,
			async : false,
			url : 'guardardistribucion',
			dataType : 'json',
			contentType : "application/json",
			type : 'post',
			beforeSend : function() {

			},
			success : function(response) {

				if (response.idDistribucion > 0) {
					$("#hddIdDistribucion").val(response.idDistribucion);
				} else {
					$("#modalCorrect").empty();
					$("#modalCorrect").append(
							"Error al registrar la distribucion");
					$("#myModalCorrect").modal();
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$("#myModalEliminar").modal('hide');

				$("#myModalError").append("No se pudo enviar la solicitud");
				$("#myModalError").modal();
			}
		});

	};
	detalleDistribucion = function(idDetalleDistribucion, cantidadDistribuida,
			articulo, personal, activo, distribucion) {
		this.idDetalleDistribucion = idDetalleDistribucion;
		this.cantidadDistribuida = cantidadDistribuida;
		this.articulo = articulo;
		this.personal = personal;
		this.activo = activo;
		this.distribucion = distribucion;

	};
	articulo = function(cveArticulo, descArticulo) {
		this.cveArticulo = cveArticulo;
		this.descArticulo = descArticulo;
	};
	editarDistribucion = function(idDistribucion) {
		console.log("idDistribucion =>" + idDistribucion);
		$("#frmDistribuciones").show();
		$("#ListaDistribuciones").hide();
		$("#hddIdDistribucion").val(idDistribucion);

		dDistribucion = new distribucion(idDistribucion);

		dDetalledistribucion = new detalleDistribucion(null, null, null, null,
				null, dDistribucion);
		dDetalledistribucion = JSON.stringify(dDetalledistribucion);
		console.log(dDetalledistribucion);
		$
				.ajax({
					data : dDetalledistribucion,
					async : false,
					url : 'buscardetalledistribucion',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {

						if (response.length > 0) {
							$("#observaciones").empty();
							$("#ListaDetalleRequisiciones").empty();
							$("#ListaDetalleRequisiciones").show();
							var tabla = "<table class=\"table\"> <thead>";
							var botones = "";
							tabla += "<thead><tr><th>#</th> <th>Descripcion</th><th>Cantidad</th> <th>Servidor Publico</th><th>&nbsp;</th></tr></thead><tbody>";
							$
									.each(
											response,
											function(index, element) {
												if (index == 0) {
													$("#observaciones")
															.append(
																	element.distribucion.observaciones);

												}
												botones = "<img onclick=\"cargarDetalleDistribucion("
														+ element.idDetalleDistribucion
														+ ")\" class='modificar' src=\"resources/images/edit.jpg\"  class=\"img-thumbnail\" /><img class=\"eliminar\" onclick=\"eliminarDetalleDistribucion("
														+ element.idDetalleDistribucion
														+ ")\"	src=\"resources/images/delete.png\"  class=\"img-thumbnail\"/>";
												tabla += "<tr><td>"
														+ (index + 1)
														+ "</td> <td>"
														+ element.articulo.descArticulo
														+ "</td><td>"
														+ element.cantidadDistribuida
														+ "</td> <td>"
														+ element.personal.nombre
														+ " "
														+ element.personal.paterno
														+ " "
														+ element.personal.materno
														+ "</td><td>" + botones
														+ "</td></tr>";
											});
							tabla += "</tbody></table>";
							$("#ListaDetalleRequisiciones").append(tabla);
						} else {
							$("#modalCorrect").empty();
							$("#modalCorrect").append(
									"La distribucion esta vacia");
							$("#myModalCorrect").modal();
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalEliminar").modal('hide');

						$("#myModalError").append(
								"No se pudo enviar la solicitud");
						$("#myModalError").modal();
					}
				});

	};
	regresarListaDistribucion = function() {
		$("#ListaDetalleRequisiciones").empty();
		$("#observaciones").empty();
		$("#frmDistribuciones").hide();
		$("#ListaDistribuciones").show();
		limpiarDetalle();
		buscarDistribuciones();

	};

	personal = function(cveUsuario) {
		this.cveUsuario = cveUsuario;
	};

	guardardetalledistribucion = function() {

		var cveArticulo = $("#hddCveArticulo").val();
		var cantidad = $("#txtCantidadRequerida").val();
		var cveUsuario = $("#hddCveUsuario").val();
		var idDistribucion = $("#hddIdDistribucion").val();
		var idDetalleDistribucion = $("#hddIdDetalleDistribucion").val() == "" ? null
				: $("#hddIdDetalleDistribucion").val();
		dArticulo = new articulo(cveArticulo);
		dDistribucion = new distribucion(idDistribucion);
		dPersonal = new personal(cveUsuario);
		dDetalleDistribucion = new detalleDistribucion(idDetalleDistribucion,
				cantidad, dArticulo, dPersonal, "S", dDistribucion);

		dDetalleDistribucion = JSON.stringify(dDetalleDistribucion);
		console.log(dDetalleDistribucion);
		if (cveArticulo != "" && cantidad != "" && cveUsuario != ""
				&& idDistribucion != "") {
			$
					.ajax({
						data : dDetalleDistribucion,
						async : false,
						url : 'guardardetalledistribucion',
						dataType : 'json',
						contentType : "application/json",
						type : 'post',
						beforeSend : function() {

						},
						success : function(response) {

							if (response.idDetalleDistribucion > 0) {
								$("#ListaDetalleRequisiciones").empty();
								limpiarDetalle();
								editarDistribucion(idDistribucion);

							} else {
								$("#modalCorrect").empty();
								$("#modalCorrect").append(
										"Error al registrar la distribucion");
								$("#myModalCorrect").modal();
							}
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$("#myModalEliminar").modal('hide');

							$("#myModalError").append(
									"No se pudo enviar la solicitud");
							$("#myModalError").modal();
						}
					});
		} else {
			$("#myModalEliminar").modal('hide');
			$("#modalErrorText").empty();
			$("#modalErrorText").append("Falta ingresar informaci&oacute;n");
			$("#myModalError").modal();

		}

	};
	limpiarDetalle = function() {
		$("#hddCveArticulo").val(0);
		$("#txtCantidadRequerida").val("");
		$("#txtAddArticulo").val("");
		$("#txtnombre").val("");
		$("#hddCveUsuario").val(0);
		 $("#hddIdDetalleDistribucion").val("");
	};

	NPersonalC = function(cveUsuario, nombreC, cveServPub, cveModulo) {
		this.cveUsuario = cveUsuario;
		this.nombreCompleto = nombreC;
		this.cveServidorPub = cveServPub;
		this.cveModulo = cveModulo;

	}
	$("#txtnombre")
			.autocomplete(
					{
						source : function(request, response) {
							var nombre = $("#txtnombre").val() == "" ? null
									: "%"
											+ $("#txtnombre").val().replace(
													/\s/g, "%") + "%";

							var dNombre = new NPersonalC(null, nombre, null);
							dNombre = JSON.stringify(dNombre);
							console.log(dNombre);

							$
									.ajax({
										data : dNombre,
										url : 'buscarpersonal',
										dataType : 'json',
										contentType : "application/json",
										type : 'post',
										beforeSend : function() {

										},
										success : function(data) {
											response($
													.map(
															data,
															function(item) {
																return {
																	label : item.nombreCompleto,
																	value : item.cveUsuario
																}
															}));
										},
										error : function(xhr, ajaxOptions,
												thrownError) {
											$('#myModal').modal('hide');
											$("#myModalCorrect").modal("hide");
											$("#myModalError")
													.append(
															"No se pudo registrar el articulo, verifique los datos");
											$("#myModalError").modal();
										}
									});

						},
						minlength : 2,
						select : function(event, ui) {
							event.preventDefault();
							console.log(ui.item ? "Selected: " + ui.item.label
									: "Nothing selected, input was "
											+ this.value);
							$("#txtnombre").val(ui.item.label);
							$("#hddCveUsuario").val(ui.item.value);
							//	buscar();
							//$("#txtnombre").val(ui.item.value);

						},
						open : function() {
							$(this).removeClass("ui-corner-all").addClass(
									"ui-corner-top");
						},
						close : function() {
							$(this).removeClass("ui-corner-top").addClass(
									"ui-corner-all");
						}
					});

	guardarObservacion = function() {

		var idDistribucion = $("#hddIdDistribucion").val();
		var observaciones = $("#message-text").val();
		dDistribucion = new distribucion(idDistribucion, null, null, null,
				null, observaciones);
		dDistribucion = JSON.stringify(dDistribucion);
		console.log(dDistribucion);
		$.ajax({
			data : dDistribucion,
			async : false,
			url : 'guardarobservaciondistribucion',
			dataType : 'json',
			contentType : "application/json",
			type : 'post',
			beforeSend : function() {

			},
			success : function(response) {

				if (response.idDistribucion > 0) {
					$("#exampleModalRes").modal('hide');
					$("#observaciones").append(response.observaciones);

				} else {
					$("#modalCorrect").empty();
					$("#modalCorrect").append(
							"Error al registrar la distribucion");
					$("#myModalCorrect").modal();
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$("#myModalEliminar").modal('hide');

				$("#myModalError").append("No se pudo enviar la solicitud");
				$("#myModalError").modal();
			}
		});
		

	};
	enviarDistribucion = function() {

		var idDistribucion = $("#hddIdDistribucion").val();

		dDistribucion = new distribucion(idDistribucion, null, null, null,
				null, null);
		var tbldetalle = $("#ListaDetalleRequisiciones").find(".table");
		if (tbldetalle.length > 0) {
			console.log("existe la tabla");

			dDistribucion = JSON.stringify(dDistribucion);
			console.log(dDistribucion);
			$.ajax({
				data : dDistribucion,
				async : false,
				url : 'terminardistribucion',
				dataType : 'json',
				contentType : "application/json",
				type : 'post',
				beforeSend : function() {

				},
				success : function(response) {

					if (response.idDistribucion > 0) {
						$("#hddIdDistribucion").val(0);
						$("#frmDistribuciones").hide();
						limpiarDetalle();
						regresarListaDistribucion()

					} else {
						$("#modalCorrect").empty();
						$("#modalCorrect").append(
								"Error al registrar la distribucion");
						$("#myModalCorrect").modal();
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$("#myModalEliminar").modal('hide');
					$("#modalErrorText").empty();
					$("#modalErrorText").append(
							"No se pudo enviar la solicitud");
					$("#myModalError").modal();
				}
			});
		} else{
			
			$("#modalErrorText").empty();
			$("#modalErrorText").append(
					"No se puede enviar la distribucion sin art�culos");
		$("#myModalError").modal();
		}

	};

	eliminarDetalleDistribucion = function(idDetalleDistribucion) {

		var idDistribucion = $("#hddIdDistribucion").val();

		dDetalleDistribucion = new detalleDistribucion(idDetalleDistribucion,
				null, null, null, null, null);
		dDetalleDistribucion = JSON.stringify(dDetalleDistribucion);
		console.log(dDetalleDistribucion);
		$.ajax({
			data : dDetalleDistribucion,
			async : false,
			url : 'eliminardetalledistribucion',
			dataType : 'json',
			contentType : "application/json",
			type : 'post',
			beforeSend : function() {

			},
			success : function(response) {
				if (response.idDetalleDistribucion > 0) {
					$("#ListaDetalleRequisiciones").empty();
					limpiarDetalle();
					editarDistribucion(idDistribucion);

				} else {
					$("#modalCorrect").empty();
					$("#modalCorrect").append(
							"Error al registrar la distribucion");
					$("#myModalCorrect").modal();
				}
			},

			error : function(xhr, ajaxOptions, thrownError) {
				$("#myModalEliminar").modal('hide');

				$("#myModalError").append("No se pudo enviar la solicitud");
				$("#myModalError").modal();
			}
		});

	};
	eliminarDistribucion = function(idDistribucion) {
		dDistribucion = new distribucion(idDistribucion, null, null, null,
				null, null);
		dDistribucion = JSON.stringify(dDistribucion);
		console.log(dDistribucion);

		$.ajax({
			data : dDistribucion,
			async : false,
			url : 'eliminardistribucion',
			dataType : 'json',
			contentType : "application/json",
			type : 'post',
			beforeSend : function() {

			},
			success : function(response) {

				if (response.idDistribucion > 0) {
					$("#hddIdDistribucion").val(0);
					$("#frmDistribuciones").hide();
					limpiarDetalle();
					regresarListaDistribucion()

				} else {
					$("#modalCorrect").empty();
					$("#modalCorrect").append(
							"Error al registrar la distribucion");
					$("#myModalCorrect").modal();
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$("#myModalEliminar").modal('hide');

				$("#myModalError").append("No se pudo enviar la solicitud");
				$("#myModalError").modal();
			}
		});

	};

	cargarDetalleDistribucion = function(idDetalleDistribucion) {

		dDetalleDistribucion = new detalleDistribucion(idDetalleDistribucion,
				null, null, null, null, null);
		dDetalleDistribucion = JSON.stringify(dDetalleDistribucion);
		console.log(dDetalleDistribucion);
		$.ajax({
			data : dDetalleDistribucion,
			async : false,
			url : 'buscardetalledistribucion',
			dataType : 'json',
			contentType : "application/json",
			type : 'post',
			beforeSend : function() {

			},
			success : function(response) {

				if (response.length > 0) {
					var nombre = "";
					$.each(response,
							function(index, element) {
								nombre = element.personal.nombre + " "
										+ element.personal.paterno + " "
										+ element.personal.materno;

								$("#txtAddArticulo").val(
										element.articulo.descArticulo);
								$("#hddCveArticulo").val(
										element.articulo.cveArticulo);
								$("#txtCantidadRequerida").val(
										element.cantidadDistribuida);
								$("#txtnombre").val(nombre);
								$("#hddCveUsuario").val(
										element.personal.cveUsuario);
								$("#hddIdDetalleDistribucion").val(
										element.idDetalleDistribucion);
							});

				} else {
					$("#modalCorrect").empty();
					$("#modalCorrect").append(
							"Error al registrar la distribucion");
					$("#myModalCorrect").modal();
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$("#myModalEliminar").modal('hide');

				$("#myModalError").append("No se pudo enviar la solicitud");
				$("#myModalError").modal();
			}
		});

	};

	//	####################################

	function AskForWebNotificationPermissions()

	{

		if (Notification) {

			Notification.requestPermission();

		}

	}
	notificar = function() {

		var options = {

			body : "Este es le cuerpo de la notificaci�n",

			icon : "imgs/logoNotifs.png"

		};

		var notif = new Notification("Ejemplo de notificaci�n", options);

	}

	$(function() {
		// 		AskForWebNotificationPermissions();
		// 		notificar();

		$("#guardarRequisicion").click(function() {
			$('#myModal').modal();

		});
		$("#agregarArticulo").click(function() {
			$('#myModal2').modal();

		});
		$(".eliminar").click(function() {
			$('#myModalNoAut').modal();

		});

		$("#txtAddArticulo")
				.autocomplete(
						{
							source : function(request, response) {
								var nomArticulo = $("#txtAddArticulo").val() == "" ? null
										: "%" + $("#txtAddArticulo").val()
												+ "%";

								var dArticulo = new Articulo(null, nomArticulo,
										null, null, null, null, null, null,
										null);
								dArticulo = JSON.stringify(dArticulo);
								console.log(dArticulo);

								$
										.ajax({
											data : dArticulo,
											url : 'buscarlikearticulos',
											dataType : 'json',
											contentType : "application/json",
											type : 'post',
											beforeSend : function() {

											},
											success : function(data) {
												response($
														.map(
																data,
																function(item) {
																	return {
																		label : item.descArticulo
																				+ " "
																				+ item.marcas.descMarca,
																		value : item.cveArticulo
																	}
																}));
											},
											error : function(xhr, ajaxOptions,
													thrownError) {
												$('#myModal').modal('hide');
												$("#myModalCorrect").modal(
														"hide");
												$("#myModalError")
														.append(
																"No se pudo registrar el articulo, verifique los datos");
												$("#myModalError").modal();
											}
										});

							},
							minlength : 2,
							select : function(event, ui) {
								event.preventDefault();
								console.log(ui.item ? "Selected: "
										+ ui.item.label
										: "Nothing selected, input was "
												+ this.value);
								$("#txtAddArticulo").val(ui.item.label);
								$("#hddCveArticulo").val(ui.item.value);

							},
							open : function() {
								$(this).removeClass("ui-corner-all").addClass(
										"ui-corner-top");
							},
							close : function() {
								$(this).removeClass("ui-corner-top").addClass(
										"ui-corner-all");
							}
						});

		$("#enviarRequisicion").click(function() {
			$('#myModal3').modal();
		});

		buscarDistribuciones();

	});
</script>

