<input type="hidden" id="hddIdEntrada" value="0">
<input type="hidden" id="hddIdDetalleEntrada" value="0">
<input type="hidden" id="hddCveArticulo" value="0">
<div class="form-group row">
	<h1>Entradas de Almac&eacute;n</h1>


</div>
<div id="frmEntrada">
	<div class="dropdown-divider"></div>
	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			id="entradaGuardar">Guardar</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarEntradas()">Buscar</button>
		<button type="button" class="btn btn-success btn-space"
			onclick="limpiar()">Limpiar</button>
		<button type="button" class="btn btn-danger btn-space">Eliminar</button>
		<button id="btnListaArticulos" type="button"
			class="btn btn-warning btn-space" onClick="mostrarArticulos()"
			style="display: none">Lista de Articulos</button>



	</div>
	<div class="dropdown-divider"></div>
	<div class="form-group ">
		<label for="exampleSelect1" class="col-10 col-form-label">Tipo
			de Documento</label>
		<div class="col-10">
			<select class="form-control" id="cmbTipoDocumento">
				<option value="">Seleccione el documento</option>

			</select>
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Numero
			de Documento</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtNoDocumento">
		</div>
	</div>
	<div class="form-group ">
		<label for="example-date-input" class="col-10 col-form-label">Fecha
			de Entrada</label>
		<div class="col-10">
			<input class="form-control datepicker" type="text" value=""
				id="txtFechaEntrada">
		</div>
	</div>
	<div class="form-group ">
		<label class="col-10 col-form-label" for="exampleSelect1">Proveedor</label>
		<div class="col-10">
			<select class="form-control" id="cmbProveedor">
				<option value="">Seleccione el proveedor</option>

			</select>
		</div>
	</div>

	<div class="form-group ">
		<label for="example-date-input" class="col-10 col-form-label">Tipo
			de Inversion</label>
		<div class="col-10">
			<select class="form-control" id="cmbTipoInversion">
				<option value="">Seleccione el tipo de inversion</option>

			</select>
		</div>
	</div>

	<div class="form-group ">
		<label for="example-date-input" class="col-10 col-form-label">Programa</label>
		<div class="col-10">
			<select class="form-control" id="cmbPrograma">
				<option value="">Seleccione el tipo de Programa</option>

			</select>
		</div>
	</div>

	<div class="form-group ">
		<label for="example-date-input" class="col-10 col-form-label">Obervaciones</label>
		<div class="col-10">
			<textarea id="txtObservaciones" class="form-control form-rounded"
				rows="3"></textarea>
		</div>
	</div>

	<div class="form-group">
		<label for="example-search-input" class="col-10 col-form-label">Numero
			de Pedido</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtNoPedido">
		</div>
	</div>

	<div class="form-group">
		<label for="example-date-input" class="col-10 col-form-label">Fecha
			de Pedido</label>
		<div class="col-10">
			<input class="form-control datepicker" type="text" value=""
				id="txtFechaPedido">
		</div>
	</div>
</div>
<div id="ListaEntradas"></div>


<div id="frmDetEntrada" style="display: none;">
	<div class="dropdown-divider"></div>
	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			id="entradaGuardarDetalle">Agregar</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarEntradas()">Buscar</button>
		<button type="button" class="btn btn-success btn-space"
			onclick="limpiarDetalle();buscarDetalleEntrada();">Limpiar</button>
		<button type="button" class="btn btn-danger btn-space">Eliminar</button>
		<button type="button" class="btn btn-warning btn-space"
			onClick="mostrarArticulos()">Regresar</button>
	</div>
	<div class="form-group">
		<label for="example-date-input" class="col-10 col-form-label">Nombre
			Producto</label>
		<div class="col-10">
			<input class="form-control autocomplete" type="text" value=""
				id="txtAddArticulo">

		</div>

	</div>
	<div class="form-group">
		<label for="example-date-input" class="col-10 col-form-label">Cantidad</label>
		<div class="col-10">
			<input class="form-control " type="text" value="" id="txtCantidad">
		</div>
	</div>
	<div class="form-group">
		<label for="example-date-input" class="col-10 col-form-label">Precio
		</label>
		<div class="col-10">
			<input class="form-control " type="text" value="" id="txtAddPrecio">
		</div>

	</div>
	<!-- 	<div class="  form-group row btn-toolbar col-10"> -->
	<!-- 		<button type="button" class="btn btn-primary btn-sm btn-space" -->
	<!-- 			id="entradaGuardarDetalle">Agregar</button> -->

	<!-- 	</div> -->
	<div class="dropdown-divider"></div>
</div>
<div id="ListaDetalleEntrada" style="display: none;"></div>

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
				<button type="button" class="btn btn-primary" onclick="guardar();">Guardar
					Entrada</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
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
					onclick="guardarDetalle();">Guardar Entrada</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="myModalEliminar" tabindex="-1" role="dialog"
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
			<div class="modal-body">Esta seguro que desea eliminar el
				articulo?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
				<button class="btnEliminarDetalle btn btn-primary" type="button">Eliminar
					Articulo</button>
			</div>
		</div>
	</div>
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
<script>
	Proveedor = function(cveProveedor, descProveedor, activo) {
		this.cveProveedor = cveProveedor;
		this.descProveedor = descProveedor;
		this.activo = activo;
	}
	buscarProveedores = function() {

		var dProveedor = new Proveedor(null, null, "S");
		dProveedor = JSON.stringify(dProveedor);
		console.log(dProveedor);

		$
				.ajax({
					data : dProveedor,
					url : 'buscarproveedores',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$('#myModalCorrect').modal('hide');
						$("#modalCorrect").empty();

						if (response.length > 0) {

							var option = "";
							$
									.each(
											response,
											function(index, element) {

												option += "<option value='"+element.cveProveedor+"'>"
														+ element.descProveedor
														+ "</option>";
											});
							$("#cmbProveedor").append(option);

						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron tipos de documentos con los criterios ingresados");
							$("#myModalCorrect").modal();
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalError")
								.append(
										"No se encontraron proveedores con los criterios ingresados");
						$("#myModalError").modal();
					}
				});

	}

	TipoInversion = function(cveTipoInversion, descTipoInversion, activo) {
		this.cveTipoInversion = cveTipoInversion;
		this.descTipoinversion = descTipoInversion;
		this.activo = activo;
	}
	buscarTiposInversiones = function() {

		var dTipoInversion = new TipoInversion(null, null, "S");
		dTipoInversion = JSON.stringify(dTipoInversion);
		console.log(dTipoInversion);

		$
				.ajax({
					data : dTipoInversion,
					url : 'buscartiposinversiones',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$('#myModalCorrect').modal('hide');
						$("#modalCorrect").empty();
						if (response.length > 0) {

							var option = "";
							$
									.each(
											response,
											function(index, element) {

												option += "<option value='"+element.cveTipoInversion+"'>"
														+ element.descTipoInversion
														+ "</option>";
											});
							$("#cmbTipoInversion").append(option);

						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron tipos de documentos con los criterios ingresados");
							$("#myModalCorrect").modal();
						}

					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalError")
								.append(
										"No se encontro el tipo de inversion con los criterios ingresados");
						$("#myModalError").modal();
					}
				});

	}

	Programa = function(cvePrograma, descPrograma, activo) {
		this.cvePrograma = cvePrograma;
		this.descPrograma = descPrograma;
		this.activo = activo;
	}

	buscarProgramas = function() {
		var dPrograma = new Programa(null, null, "S");
		dPrograma = JSON.stringify(dPrograma);
		console.log(dPrograma);

		$
				.ajax({
					data : dPrograma,
					url : 'buscarprograma',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$('#myModalCorrect').modal('hide');
						$("#modalCorrect").empty();
						if (response.length > 0) {

							var option = "";
							$
									.each(
											response,
											function(index, element) {

												option += "<option value='"+element.cvePrograma+"'>"
														+ element.descPrograma
														+ "</option>";
											});
							$("#cmbPrograma").append(option);

						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron tipos de documentos con los criterios ingresados");
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

	}

	TipoDocumento = function(cveTipoDocumento, descTipoDocumento) {
		this.cveTipoDocumento = cveTipoDocumento;
		this.descTipoDocumento = descTipoDocumento;
	}
	buscarTiposDocumentos = function() {

		var dTipoDocumento = new TipoDocumento(null, null, "S");
		dTipoDocumento = JSON.stringify(dTipoDocumento);
		console.log(dTipoDocumento);

		$
				.ajax({
					data : dTipoDocumento,
					url : 'buscartiposdocumentos',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$('#myModalCorrect').modal('hide');
						$("#modalCorrect").empty();

						if (response.length > 0) {

							var option = "";
							$
									.each(
											response,
											function(index, element) {

												option += "<option value='"+element.cveTipoDocumento+"'>"
														+ element.descTipoDocumento
														+ "</option>";
											});
							$("#cmbTipoDocumento").append(option);

						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron tipos de documentos con los criterios ingresados");
							$("#myModalCorrect").modal();
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalError")
								.append(
										"No se encontraron tipos de documentos con los criterios ingresados");
						$("#myModalError").modal();
					}
				});

	}
	Entrada = function(idEntrada, tipoDocumento, noDocumento, fechaEntrada,
			proveedor, tipoInversion, programa, observaciones, noPedido,
			fechaPedido) {
		this.idEntrada = idEntrada;
		this.tipoDocumento = tipoDocumento;
		if (noDocumento == "")
			this.noDocumento = null;
		else
			this.noDocumento = noDocumento;
		this.fechaEntrada = fechaEntrada;
		this.proveedor = proveedor;
		this.tipoInversion = tipoInversion;
		this.programa = programa;
		if (observaciones == "")
			this.observaciones = null;
		else
			this.observaciones = observaciones;
		if (noPedido == "")
			this.numeroPedido = null;
		else
			this.numeroPedido = noPedido;
		this.fechaPedido = fechaPedido;

	}
	buscarEntradas = function() {

		var tipoDocumento = $("#cmbTipoDocumento").val() == "" ? null : $(
				"#cmbTipoDocumento").val();
		var noDocumento = $("#txtNoDocumento").val() == "" ? "" : "%"
				+ $("#txtNoDocumento").val() + "%";
		var fechaEntrada = cambiarfecha($("#txtFechaEntrada").val());
		var vproveedor = $("#cmbProveedor").val() == "" ? null : $(
				"#cmbProveedor").val();
		var tipoInversion = $("#cmbTipoInversion").val() == "" ? null : $(
				"#cmbTipoInversion").val();
		var programa = $("#cmbPrograma").val() == "" ? null : $("#cmbPrograma")
				.val();
		var observaciones = $("#txtObservaciones").val() == "" ? null : $(
				"#txtObservaciones").val();
		var noPedido = $("#txtNoPedido").val() == "" ? null : $("#txtNoPedido")
				.val();
		var fechaPedido = $("#txtFechaPedido").val() == "" ? null
				: cambiarfecha($("#txtFechaPedido").val());
		var dProveedor = vproveedor == "" ? null : new Proveedor(vproveedor,
				null);
		var dTipoDocumento = tipoDocumento == "" ? null : new TipoDocumento(
				tipoDocumento, null);
		var dTipoInversion = tipoInversion == "" ? null : new TipoInversion(
				tipoInversion, null);
		var dPrograma = programa == "" ? null : new Programa(programa, null);

		var dEntrada = new Entrada(null, dTipoDocumento, noDocumento,
				fechaEntrada, dProveedor, dTipoInversion, dPrograma,
				observaciones, noPedido, fechaPedido);
		dEntrada = JSON.stringify(dEntrada);
		console.log(dEntrada);

		$
				.ajax({
					data : dEntrada,
					url : 'buscarEntrada',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$("#modalCorrect").empty();
						$("#ListaEntradas").empty();

						if (response.length > 0) {
							var lista = "<table class=\"table\"> <thead>";
							lista += "<thead><tr><th>#</th> <th>Documento</th><th>Proveedor</th> <th>No documento</th> <th>FechaEntrada</th><th>&nbsp;</th></tr></thead><tbody>";

							$
									.each(
											response,
											function(index, element) {
												lista += "<tr><th scope=\"row\">"
														+ (index + 1)
														+ "</th><td>"
														+ element.tipoDocumento.descTipoDocumento
														+ "</td><td>"
														+ element.proveedor.descProveedor
														+ "</td><td>"
														+ element.noDocumento
														+ "</td><td>"
														+ element.fechaEntrada
														+ "</td><td><img onclick=\"editarEntrada("
														+ element.idEntrada
														+ ")\" src=\"resources/images/edit.jpg\" width=15% heigth=15% class=\"img-thumbnail\"><img src=\"resources/images/delete.png\" width=17% heigth=17% class=\"img-thumbnail\"></td></tr>";
											});
							lista += "</tbody></table>";

							$("#ListaEntradas").append(lista);
							$("#frmEntrada").hide();
							$("#ListaEntradas").show();

						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron entradas con los criterios ingresados");
							$("#myModalCorrect").modal();
						}
					}
				});

	}

	DetalleEntrada = function(idDetalleEntrada, Entrada, Articulo, cantidad,
			precio, activo) {
		this.idDetalleEntrada = idDetalleEntrada;
		this.entrada = Entrada;
		this.articulo = Articulo, this.cantidad = cantidad;
		this.precio = precio;
		this.activo = activo;
	}
	buscarDetalleEntrada = function() {
		var idEntrada = $("#hddIdEntrada").val();

		var dEntrada = new Entrada(idEntrada, null, null, null, null, null,
				null, null, null, null);
		var dDetalleEntrada = new DetalleEntrada(null, dEntrada, null, null,
				null, null);
		console.log(dDetalleEntrada);
		dDetalleEntrada = JSON.stringify(dDetalleEntrada);
		console.log(dDetalleEntrada);
		$
				.ajax({
					data : dDetalleEntrada,
					url : 'buscardetalle',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$("#modalCorrect").empty();
						$("#ListaDetalleEntrada").empty();

						$("#ListaDetalleEntrada").hide();
						if (response.length > 0) {
							var lista = "<table class=\"table\"> <thead>";
							lista += "<thead><tr><th>#</th> <th>Articulo</th><th>Cantidad</th> <th>Precio</th> <th>fecha</th><th>&nbsp;</th></tr></thead><tbody>";

							$
									.each(
											response,
											function(index, element) {
												lista += "<tr><th scope=\"row\">"
														+ (index + 1)
														+ "</th><td>"
														+ element.articulo.descArticulo
														+ "</td><td>"
														+ element.cantidad
														+ "</td><td>"
														+ element.precio
														+ "</td><td>"
														+ element.fechaRegistro
														+ "</td><td><img onclick=\"editarDetalleEntrada("
														+ element.idDetalleEntrada
														+ ")\" src=\"resources/images/edit.jpg\" width=15% heigth=15% class=\"img-thumbnail\"><img class='eliminarDetalle' id='"
														+ element.idDetalleEntrada
														+ "'src=\"resources/images/delete.png\" width=17% heigth=17% class=\"img-thumbnail\"></td></tr>";
											});
							lista += "</tbody></table>";

							$("#ListaDetalleEntrada").append(lista);

							$("#ListaDetalleEntrada").show();
							$(".eliminarDetalle")
									.click(
											function() {
												$('#myModalEliminar').modal(
														function() {

														});
												var idDetalleEntrada = this.id;
												$(".btnEliminarDetalle")
														.on(
																"click",
																function() {
																	eliminarDetalle(idDetalleEntrada);
																})
											});

						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron articulo para esta entrada con los criterios ingresados");
							$("#myModalCorrect").modal();
							$("#ListaDetalleEntrada").append(
									"No se han capturado articulos");

							$("#ListaDetalleEntrada").show();

						}
					}
				});
	}

	editarDetalleEntrada = function(idDetalleEntrada) {

		var dDetalleEntrada = new DetalleEntrada(idDetalleEntrada, null, null,
				null, null, null);
		console.log(dDetalleEntrada);
		dDetalleEntrada = JSON.stringify(dDetalleEntrada);
		console.log(dDetalleEntrada);

		$
				.ajax({
					data : dDetalleEntrada,
					url : 'buscardetalle2',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {

					},
					success : function(response) {
						$("#modalCorrect").empty();
						$("#ListaDetalleEntrada").empty();

						$("#ListaDetalleEntrada").hide();
						limpiarDetalle();
						if (response.length > 0) {

							$.each(response, function(index, element) {
								$("#hddIdDetalleEntrada").val(
										element.idDetalleEntrada);
								$("#hddCveArticulo").val(
										element.articulo.cveArticulo);
								$("#txtAddArticulo").val(
										element.articulo.descArticulo);
								$("#txtCantidad").val(element.cantidad);
								$("#txtAddPrecio").val(element.precio);

							});

						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron articulo para esta entrada con los criterios ingresados");
							$("#myModalCorrect").modal();

						}
					}
				});

	}
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

	}

	guardarDetalle = function() {

		$("#myModal2").modal("hide");
		var idEntrada = $("#hddIdEntrada").val();
		var idDetalleEntrada = $("#hddIdDetalleEntrada").val() == 0 ? "" : $(
				"#hddIdDetalleEntrada").val();
		var cveArticulo = $("#hddCveArticulo").val();
		var cantidad = $("#txtCantidad").val();
		var precio = $("#txtAddPrecio").val();

		var flag = 1;
		var faltante = "";
		if (cantidad == "") {
			faltante += "Falta ingresar la cantidad de articulos<br>";
			flag = 0;
		}
		if (precio == "") {
			faltante += "falta el precio unitario del articulo<br>";
			flag = 0;
		}

		if (flag == 1) {
			dArticulo = new Articulo(cveArticulo, null, null, null, null, null,
					null, null, null);

			dEntrada = new Entrada(idEntrada, null, null, null, null, null,
					null, null, null, null)

			dDetalleEntrada = new DetalleEntrada(idDetalleEntrada, dEntrada,
					dArticulo, cantidad, precio, "S");
			dDetalleEntrada = JSON.stringify(dDetalleEntrada);
			console.log(dDetalleEntrada);
			$.ajax({
				data : dDetalleEntrada,
				url : 'guardardetalle',
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
					if (response.idDetalleEntrada > 0) {
						$("#modalCorrect").append("Se guardo el detalle");
						limpiarDetalle();
						buscarDetalleEntrada();
					} else {
						$("#modalCorrect").append(
								"El detalle  ya existe verifique los datos");
						$("#myModalCorrect").modal();
					}
				}
			});
		} else {
			$("#modalErrorText").empty();
			$("#modalErrorText").append(faltante);

			$("#myModalError").modal();

		}

	}

	eliminarDetalle = function(idDetalleEntrada) {
		$("#myModalEliminar").modal('hide');
		var dDetalleEntrada = new DetalleEntrada(idDetalleEntrada, null, null,
				null, null, "N");
		dDetalleEntrada = JSON.stringify(dDetalleEntrada);
		console.log(dDetalleEntrada);
		$.ajax({
			data : dDetalleEntrada,
			url : 'eliminardetalle',
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
				if (response.idDetalleEntrada > 0) {
					$("#modalCorrect").append("Se Elimino el articulo");
					buscarDetalleEntrada();
				} else {
					$("#modalCorrect").append(
							"El detalle  ya existe verifique los datos");
					$("#myModalCorrect").modal();
				}
			}
		});

	}

	limpiarDetalle = function() {

		$("#hddIdDetalleEntrada").val(0);
		$("#hddCveArticulo").val(0);
		$("#txtCantidad").val("");
		$("#txtAddPrecio").val("");
		$("#txtAddArticulo").val("");

	}

	guardar = function() {

		$('#myModal').modal('hide');
		var tipoDocumento = $("#cmbTipoDocumento").val();
		var noDocumento = $("#txtNoDocumento").val();
		var fechaEntrada = cambiarfecha($("#txtFechaEntrada").val());
		var vProveedor = $("#cmbProveedor").val();
		var tipoInversion = $("#cmbTipoInversion").val();
		var programa = $("#cmbPrograma").val();
		var observaciones = $("#txtObservaciones").val();
		var noPedido = $("#txtNoPedido").val();
		var fechaPedido = cambiarfecha($("#txtFechaPedido").val());
		var idEntrada = $("#hddIdEntrada").val() == 0 ? null : $(
				"#hddIdEntrada").val();
		var flag = 1;
		var faltante = "";
		if (tipoDocumento == 0) {
			faltante += "Seleccione el tipo de documento<br>";
			flag = 0;
		}
		if (noDocumento == "") {
			faltante += "Anote el numero del documento<br>";
			flag = 0;
		}
		if (fechaEntrada == "") {
			faltante += "Elija la fecha de entrada a almacen<br>";
			flag = 0;
		}
		if (vProveedor == 0) {
			faltante += "Elija el proveedor<br>";
			flag = 0;
		}
		if (tipoInversion == 0) {
			faltante += "Elija el tipo de inversion<br>";
			flag = 0;
		}
		if (programa == 0) {
			faltante += "Elija el programa<br>";
			flag = 0;
		}
		// 		if (observaciones == 0) {
		// 			faltante += "seleccione el tipo de documento";
		// 			flag = 0;
		// 		}
		if (noPedido == "") {
			faltante += "Anote el numero de pedido<br>";
			flag = 0;
		}
		if (fechaPedido == "") {
			faltante += "Elija la fecha del pedido<br>";
			flag = 0;
		}

		if (flag == 1) {

			var dProveedor = new Proveedor(vProveedor, null);
			var dTipoDocumento = new TipoDocumento(tipoDocumento, null);
			var dTipoInversion = new TipoInversion(tipoInversion, null);
			var dPrograma = new Programa(programa, null);
			var dEntrada = new Entrada(idEntrada, dTipoDocumento, noDocumento,
					fechaEntrada, dProveedor, dTipoInversion, dPrograma,
					observaciones, noPedido, fechaPedido);
			dEntrada = JSON.stringify(dEntrada);
			console.log(dEntrada);

			$.ajax({
				data : dEntrada,
				url : 'entradasave',
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
					if (response.idEntrada > 0) {
						$("#modalCorrect").append("Se guardo la entrada");
						$("#btnListaArticulos").show();
						$("#hddIdEntrada").val(response.idEntrada);
					} else
						$("#modalCorrect").append(
								"La entrada ya existe verifique los datos");
					$("#myModalCorrect").modal();
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

	limpiar = function() {
		$("#cmbTipoDocumento").val(0);
		$("#txtNoDocumento").val("");
		$("#txtFechaEntrada").val("");
		$("#cmbProveedor").val(0);
		$("#cmbTipoInversion").val(0);
		$("#cmbPrograma").val(0);
		$("#txtObservaciones").val("");
		$("#txtNoPedido").val("");
		$("#txtFechaPedido").val("");
		$("#hddIdEntrada").val("0");
		$("#btnListaArticulos").hide();
		limpiarDetalle();
	}
	editarEntrada = function(idEntrada) {
		limpiar();

		var dProveedor = new Proveedor(null, null);
		var dTipoDocumento = new TipoDocumento(null, null);
		var dTipoInversion = new TipoInversion(null, null);
		var dPrograma = new Programa(null, null);
		var dEntrada = new Entrada(idEntrada, dTipoDocumento, null, null,
				dProveedor, dTipoInversion, dPrograma, null, null, null);
		dEntrada = JSON.stringify(dEntrada);
		console.log(dEntrada);

		$.ajax({
			data : dEntrada,
			url : 'buscarEntrada',
			dataType : 'json',
			contentType : "application/json",
			type : 'post',
			beforeSend : function() {
				$("#modalCorrect").empty();
				$("#modalCorrect").append("Procesando");
				$("#myModalCorrect").modal();
			},
			success : function(response) {
				$("#myModalCorrect").modal('hide');
				$.each(response, function(index, element) {
					$("#cmbTipoDocumento").val(
							element.tipoDocumento.cveTipoDocumento);
					$("#txtNoDocumento").val(element.noDocumento);
					$("#txtFechaEntrada").val(
							cambiarFecha2(element.fechaEntrada));
					$("#cmbProveedor").val(element.proveedor.cveProveedor);
					$("#cmbTipoInversion").val(
							element.tipoInversion.cveTipoInversion);
					$("#cmbPrograma").val(element.programa.cvePrograma);
					$("#txtObservaciones").val(element.observaciones);
					$("#txtNoPedido").val(element.numeroPedido);
					$("#txtFechaPedido")
							.val(cambiarFecha2(element.fechaPedido));
					$("#hddIdEntrada").val(element.idEntrada);
					$("#frmEntrada").show();
					$("#ListaEntradas").hide();
					$("#btnListaArticulos").show();

				});

			}
		});

	};
	// FALTA EL METODO PARA ELIMINAR LAS ENTRADAS
	mostrarArticulos = function() {
		if ($("#ListaDetalleEntrada").css("display") === "none") {
			$("#ListaDetalleEntrada").show();
			$("#frmDetEntrada").show();
			buscarDetalleEntrada();

			$("#frmEntrada").hide();
		} else {
			$("#ListaDetalleEntrada").hide();
			$("#frmDetEntrada").hide();
			$("#frmEntrada").show();
		}
	}

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

	}

	buscarArticulos = function() {
		$('#myModal').modal('hide');

		var nomArticulo = $("#txtAddArticulo").val() == "" ? null : "%"
				+ $("#txtAddArticulo").val() + "%";

		var dArticulo = new Articulo(null, nomArticulo, null, null, null, null,
				null, null, null);
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
						// 							$("#modalCorrect").empty();
						// 							$("#modalCorrect").append("Procesando");
						// 							$("#myModalCorrect").modal();
					},
					success : function(response) {
						$('#myModal').modal('hide');
						$("#modalCorrect").empty();
						$("#misArticulos").empty();
						if (response.length > 0) {
							return $.map(response, function(value, key) {
								return {
									label : value,
									value : key
								};
							})

						} else {

						}

					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#myModal').modal('hide');
						$("#myModalCorrect").modal("hide");
						$("#myModalError")
								.append(
										"No se pudo registrar el articulo, verifique los datos");
						$("#myModalError").modal();
					}
				});

	}

	$(function() {

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
																		label : item.descArticulo,
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

		$(".datepicker").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("#entradaGuardar").click(function() {
			$('#myModal').modal(function() {
				guardar();

			});

		});

		$("#entradaGuardarDetalle").click(function() {
			$('#myModal2').modal();

		});
		buscarProveedores();
		buscarTiposInversiones();
		buscarProgramas();
		buscarTiposDocumentos();
	});
</script>

