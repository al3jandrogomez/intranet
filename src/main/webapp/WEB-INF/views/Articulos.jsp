<input type="hidden" id="hddCveArticulo" value="0">
<div class="form-group row">
	<h1>Catalogo de Articulos</h1>


</div>
<div id="frmArticulos">
	<div class="dropdown-divider"></div>
	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			id="guardarArticulo">Guardar</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarArticulos()">Buscar</button>
		<button type="button" class="btn btn-success btn-space"
			onclick="limpiarArticulo()">Limpiar</button>
		<button type="button" class="btn btn-danger btn-space">Eliminar</button>

	</div>
	<div class="dropdown-divider"></div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Clave de Verificaci&oacute;n</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtClave">
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Version</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtVersion">
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Nombre
			Articulo</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtNomArticulo">
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Descripcion</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtDescripcion">
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Capacidad</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtCapacidad">
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Partida</label>
		<div class="col-10">
			<select class="form-control" id="cmbPartidas">
				<option value="0">Seleccione la Partida</option>
			</select>
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Unidad
			de Medida</label>
		<div class="col-10">
			<select class="form-control" id="cmbUnidades">
				<option value="0">Seleccione la Unidad de Medida</option>
			</select>
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Marca</label>
		<div class="col-10">
			<select class="form-control" id="cmbMarcas">
				<option value="0">Seleccione la Marca</option>
			</select>
		</div>
	</div>
</div>
<div id="ListaArticulos" style="display: none"></div>


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
					onclick="guardarArticulos();">Guardar Articulo</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	Partida = function(cvePartida, clavePartida, descPartida, activo) {
		this.cvePartida = cvePartida;
		this.clavePartida = clavePartida;
		this.descPartida = descPartida;
		this.activo = activo;

	}
	Unidad = function(cveUnidad, descUnidad, activo) {
		this.cveUnidad = cveUnidad;
		this.descUnidad = descUnidad;
		this.activo = activo;

	}
	Marca = function(cveMarcaAlm, descMarca, activo) {
		this.cveMarcaAlm = cveMarcaAlm;
		this.descMarca = descMarca;
		this.activo = activo

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

	cargarPartidas = function() {
		dPartida = new Partida(null, null, null, "S");
		dPartida = JSON.stringify(dPartida);
		console.log(dPartida);

		$.ajax({
			data : dPartida,
			url : 'buscarpartidaactiva',
			dataType : 'json',
			contentType : "application/json",
			type : 'post',
			beforeSend : function() {
// 				$("#modalCorrect").empty();
// 				$("#modalCorrect").append("Procesando");
// 				$("#myModalCorrect").modal();
			},
			success : function(response) {
// 				$('#myModalCorrect').modal('hide');
// 				$("#modalCorrect").empty();
				if (response.length > 0) {
					var option = "";
					$.each(response, function(index, element) {
						option += "<option value = '"+element.cvePartida+"'>"
								+ element.descPartida + "</option>"
					});

					$("#cmbPartidas").append(option);

				} else {
					$("#modalCorrect").append("Error en catalogo de partidas");
					$("#myModalCorrect").modal();
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$("#myModalError").append("Error en catalogo de partidas");
				$("#myModalError").modal();
			}
		});

	}

	cargarUnidades = function() {
		dUnidad = new Unidad(null, null, "S");
		dUnidad = JSON.stringify(dUnidad);
		console.log(dUnidad);

		$.ajax({
			data : dUnidad,
			url : 'buscarunidades',
			dataType : 'json',
			contentType : "application/json",
			type : 'post',
			beforeSend : function() {
// 				$("#modalCorrect").empty();
// 				$("#modalCorrect").append("Procesando");
// 				$("#myModalCorrect").modal();
			},
			success : function(response) {
// 				$('#myModalCorrect').modal('hide');
// 				$("#modalCorrect").empty();
				if (response.length > 0) {
					var option = "";
					$.each(response, function(index, element) {
						option += "<option value = '"+element.cveUnidad+"'>"
								+ element.descUnidad + "</option>"
					});

					$("#cmbUnidades").append(option);

				} else {
					$("#modalCorrect").append("Error en catalogo de partidas");
					$("#myModalCorrect").modal();
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$("#myModalError").append("Error en catalogo de partidas");
				$("#myModalError").modal();
			}
		});
	}

	cargarMarcas = function() {
		var dMarca = new Marca(null, null, "S");
		dMarca = JSON.stringify(dMarca);
		console.log(dMarca);

		$
				.ajax({
					data : dMarca,
					url : 'buscarmarcas',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {
// 						$("#modalCorrect").empty();
// 						$("#modalCorrect").append("Procesando");
// 						$("#myModalCorrect").modal();
					},
					success : function(response) {
						
// 						$('#myModalCorrect').modal('hide');
// 						$("#modalCorrect").empty();

						if (response.length > 0) {
							var option = "";
							$
									.each(
											response,
											function(index, element) {
												option += "<option value = '"+element.cveMarcaAlm+"'>"
														+ element.descMarca
														+ "</option>"
											});

							$("#cmbMarcas").append(option);

						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron marcas con los criterios ingresados");
							$("#myModalCorrect").modal();
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalError")
								.append(
										"No se encontraron entradas con los criterios ingresados");
						$("#myModalError").modal();
					}
				});

	};

	guardarArticulos = function() {
		$('#myModal').modal('hide');
		var cveArticulo = $("#hddCveArticulo").val() == 0 ? null : $(
				"#hddCveArticulo").val();
		var clave = $("#txtClave").val();
		var version = $("#txtVersion").val();
		var nomArticulo = $("#txtNomArticulo").val();
		var descripcion = $("#txtDescripcion").val();
		var capacidad = $("#txtCapacidad").val();
		var cvePartida = $("#cmbPartidas").val();
		var cveUnidad = $("#cmbUnidades").val();
		var cveMarca = $("#cmbMarcas").val();

		var flag = 1;
		var faltante = "";

		if (clave == "") {
			faltante += "Ingrese la clave del articulo<br>";
			flag = 0;
		}
		if (version == "") {
			faltante += "Ingrese la version del articulo<br>";
			flag = 0;
		}
		if (nomArticulo == "") {
			faltante += "Ingrese el nombre del articulo<br>";
			flag = 0;
		}
		if (descripcion == "") {
			faltante += "Ingrese la descripcion del articulo<br>";
			flag = 0;
		}
		// 		if(capacidad=""){
		// 			faltante+="Ingrese la clave del arriculo<br>";
		// 			flag=0;
		// 		}
		if (cvePartida == 0) {
			faltante += "Ingrese la partida para articulo<br>";
			flag = 0;
		}
		if (cveUnidad == 0) {
			faltante += "Ingrese la unidad del articulo<br>";
			flag = 0;
		}
		if (cveMarca == 0) {
			faltante += "Ingrese la marca para el articulo<br>";
			flag = 0;
		}
		if (flag == 1) {

			var dMarca = new Marca(cveMarca, null, null);
// 			dMarca = JSON.stringify(dMarca);
// 			console.log(dMarca);

			var dUnidad = new Unidad(cveUnidad, null, null);
// 			dUnidad = JSON.stringify(dUnidad);
// 			console.log(dUnidad);

			var dPartida = new Partida(cvePartida, null, null, null);
// 			dPartida = JSON.stringify(dPartida);
// 			console.log(dPartida);

			var dArticulo = new Articulo(cveArticulo, nomArticulo, descripcion,
					capacidad, dUnidad, dMarca, clave, version, dPartida);
			dArticulo = JSON.stringify(dArticulo);
			console.log(dArticulo);
			$
					.ajax({
						data : dArticulo,
						url : 'guardararticulo',
						dataType : 'json',
						contentType : "application/json",
						type : 'post',
						beforeSend : function() {
// 							$("#modalCorrect").empty();
// 							$("#modalCorrect").append("Procesando");
// 							$("#myModalCorrect").modal();
						},
						success : function(response) {
// 							$('#myModal').modal('hide');
// 							$("#modalCorrect").empty();
							if (response.cveArticulo > 0) {
								$("#modalCorrect").append(
										"Se guardo el articulo");
								limpiarArticulo();
							} else
								$("#modalCorrect")
										.append(
												"El articulo ya existe verifique los datos");
							$("#myModalCorrect").modal();
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

		} else {
			$("#modalErrorText").empty();
			$("#modalErrorText").append(faltante);

			$("#myModalError").modal();
		}

	}

	editarArticulo = function(cveArticulo) {

		var dMarca = new Marca(null, null, null);
		
		

		var dUnidad = new Unidad(null, null, null);
		

		var dPartida = new Partida(null, null, null, null);
		

		var dArticulo = new Articulo(cveArticulo, null, null, null, dUnidad,
				dMarca, null, null, dPartida);
		dArticulo = JSON.stringify(dArticulo);
		console.log(dArticulo);
		$
				.ajax({
					data : dArticulo,
					url : 'buscararticulos',
					dataType : 'json',
					contentType : "application/json",
					type : 'post',
					beforeSend : function() {
// 						$("#modalCorrect").empty();
// 						$("#modalCorrect").append("Procesando");
// 						$("#myModalCorrect").modal();
					},
					success : function(response) {
						limpiarArticulo();
// 						$("#myModalCorrect").modal("hide");
						$.each(response, function(index, element) {
							$("#hddCveArticulo").val(element.cveArticulo);
							$("#txtClave").val(element.clave);
							$("#txtVersion").val(element.version);
							$("#txtNomArticulo").val(element.descArticulo);
							$("#txtDescripcion").val(element.especificacion);
							$("#txtCapacidad").val(element.capacidad);
							$("#cmbPartidas").val(element.partidas.cvePartida);
							$("#cmbUnidades").val(element.unidades.cveUnidad);
							$("#cmbMarcas").val(element.marcas.cveMarcaAlm);
							
							$("#frmArticulos").show();
							$("#ListaArticulos").hide();
						});

					},
					error : function(xhr, ajaxOptions, thrownError) {
						$("#myModalCorrect").modal("hide");
						$("#myModalError")
								.append(
										"No se pudo registrar el articulo, verifique los datos");
						$("#myModalError").modal();
					}
				});

	}
	buscarArticulos = function() {
		$('#myModal').modal('hide');
		
		var clave = $("#txtClave").val()==""?null:$("#txtClave").val();
		var version = $("#txtVersion").val()==""?null:$("#txtVersion").val();
		var nomArticulo = $("#txtNomArticulo").val()==""?null: $("#txtNomArticulo").val();
		var descripcion = $("#txtDescripcion").val()==""?null:$("#txtDescripcion").val();
		var capacidad = $("#txtCapacidad").val()==""?null:$("#txtCapacidad").val();
		var cvePartida = $("#cmbPartidas").val()==0?null:$("#cmbPartidas").val();
		var cveUnidad = $("#cmbUnidades").val()==0?null:$("#cmbUnidades").val();
		var cveMarca = $("#cmbMarcas").val()==0?null:$("#cmbMarcas").val();

		
			var dMarca = new Marca(cveMarca, null, null);
// 			dMarca = JSON.stringify(dMarca);
// 			console.log(dMarca);

			var dUnidad = new Unidad(cveUnidad, null, null);
// 			dUnidad = JSON.stringify(dUnidad);
// 			console.log(dUnidad);

			var dPartida = new Partida(cvePartida, null, null, null);
// 			dPartida = JSON.stringify(dPartida);
// 			console.log(dPartida);

			var dArticulo = new Articulo(null, nomArticulo, descripcion,
					capacidad, dUnidad, dMarca, clave, version, dPartida);
			dArticulo = JSON.stringify(dArticulo);
			console.log(dArticulo);
			$("#ListaArticulos").empty();
			$
					.ajax({
						data : dArticulo,
						url : 'buscararticulos',
						dataType : 'json',
						contentType : "application/json",
						type : 'post',
						beforeSend : function() {
// 							$("#modalCorrect").empty();
// 							$("#modalCorrect").append("Procesando");
// 							$("#myModalCorrect").modal();
						},
						success : function(response) {
// 							$('#myModal').modal('hide');
// 							$("#modalCorrect").empty();
							if (response.length > 0) {
								var lista = "<table class=\"table\"> <thead>";
								lista += "<thead><tr><th>#</th> <th>Descripcion </th><th>Marca </th><th>&nbsp;</th></tr></thead><tbody>";
								
								$.each(response, function(index,element){
									lista+="<tr><th scope=\"row\">"+(index+1)+"</th><td>"+element.descArticulo+"</td><td>"+element.marcas.descMarca+"</td><td><img onclick=\"editarArticulo("+element.cveArticulo+")\" src=\"resources/images/edit.jpg\" width=40px heigth=40px class=\"img-thumbnail\"><img src=\"resources/images/delete.png\" width=40px heigth=40px class=\"img-thumbnail\"></td></tr>";
								});
								lista+="</tbody></table>";

								$("#ListaArticulos").append(lista);
								$("#frmArticulos").hide();
								$("#ListaArticulos").show();
								
								
							} else {
								$("#modalCorrect")
										.append(
												"No se encontraron articulos con los criterios ingresados");
								$("#myModalCorrect").modal();
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
	limpiarArticulo = function() {
		$("#hddCveArticulo").val(0);
		$("#txtClave").val("");
		$("#txtVersion").val("");
		$("#txtNomArticulo").val("");
		$("#txtDescripcion").val("");
		$("#txtCapacidad").val("");
		$("#cmbPartidas").val(0);
		$("#cmbUnidades").val(0);
		$("#cmbMarcas").val(0);
	}

	$(function() {
		cargarPartidas();
		cargarUnidades();
		cargarMarcas();
		$('#myModalCorrect').modal('hide');
		$('#myModal').modal('hide');

		$("#guardarArticulo").click(function() {
			$('#myModal').modal();
		});

	});
</script>
