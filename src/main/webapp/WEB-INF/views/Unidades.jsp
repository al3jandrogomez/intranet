<input type="hidden" id="hddCveUnidad" value="0">
<div class="form-group row">
	<h1>Catalogo de unidades</h1>


</div>
<div id="frmUnidades">
	<div class="dropdown-divider"></div>
	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			id="guardarUnidades">Guardar</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarUnidades()">Buscar</button>
		<button type="button" class="btn btn-success btn-space"
			onclick="limpiarUnidad()">Limpiar</button>
		<button type="button" class="btn btn-danger btn-space">Eliminar</button>

	</div>
	<div class="dropdown-divider"></div>
	
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Nombre de la Unidad de Medida</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtDescUnidad">
		</div>
	</div>
</div>
<div id="ListaUnidades" style="display: none"></div>

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
				<button type="button" class="btn btn-primary" onclick="guardarUnidades();">Guardar
					Entrada</button>
			</div>
		</div>
	</div>
</div>

<script>
 Unidad = function(cveUnidad,descUnidad,activo){
	 this.cveUnidad=cveUnidad;
	 this.descUnidad= descUnidad;
	 
 }
 
	buscarUnidades = function() {

		var cveUnidad = $("#hddCveUnidad").val()=="0" ? "":$("#hddCveUnidad").val();
		
		var descUnidad = $("#txtDescUnidad").val()=="" ? null:"%"+$("#txtDescUnidad").val()+"%";
		
		
		var dUnidad = new Unidad(null, descUnidad);
		dUnidad = JSON.stringify(dUnidad);
		console.log(dUnidad);

		$
				.ajax({
					data : dUnidad,
					url : 'buscarunidades',
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
						$("#ListaUnidades").empty();
						
												if (response.length > 0) {
							var lista = "<table class=\"table\"> <thead>";
							lista += "<thead><tr><th>#</th> <th>Descripcion </th><th>&nbsp;</th></tr></thead><tbody>";
							
							$.each(response, function(index,element){
								lista+="<tr><th scope=\"row\">"+(index+1)+"</th><td>"+element.descUnidad+"</td><td><img onclick=\"editarUnidad("+element.cveUnidad+")\" src=\"resources/images/edit.jpg\" width=40px heigth=40px class=\"img-thumbnail\"><img src=\"resources/images/delete.png\" width=40px heigth=40px class=\"img-thumbnail\"></td></tr>";
							});
							lista+="</tbody></table>";

							$("#ListaUnidades").append(lista);
							$("#frmUnidades").hide();
							$("#ListaUnidades").show();
							
							
						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron entradas con los criterios ingresados");
							$("#myModalCorrect").modal();
						}
					},
					error: function (xhr, ajaxOptions, thrownError) {
						$("#myModalError")
						.append(
								"No se encontraron entradas con los criterios ingresados");
				$("#myModalError").modal();
				      }
				});

	}
	
	guardarUnidades = function() {

		$('#myModal').modal('hide');
		var cveUnidad = $("#hddCveUnidad").val()=="0" ? "":$("#hddCveUnidad").val();
		
		var descUnidad = $("#txtDescUnidad").val()=="" ?"":$("#txtDescUnidad").val();
		var flag = 1;
		var faltante = "";
		
		if (descUnidad == "") {
			faltante += "Ingrese el Nombre de la Unidad de Medida<br>";
			flag = 0;
		}
		
		if (flag == 1) {

			
			var dUnidad = new Unidad(cveUnidad, descUnidad);
			dUnidad = JSON.stringify(dUnidad);
			console.log(dUnidad);

			$.ajax({
				data : dUnidad,
				url : 'guardarunidades',
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
					if (response.cveUnidad > 0){
						$("#modalCorrect").append("Se guardo la partida");
						limpiarUnidad();
					}
					else
						$("#modalCorrect").append(
								"La unidad de medida ya existe verifique los datos");
					$("#myModalCorrect").modal();
				},
				error: function (xhr, ajaxOptions, thrownError) {
					$("#myModalCorrect").modal("hide");
					$("#myModalError")
					.append(
							"No se encontraron unidades de medida con los criterios ingresados");
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

	limpiarUnidad = function() {
		 $("#hddCveUnidad").val(0);
		 $("#txtDescUnidad").val("");
		 
	}
	editarUnidad = function(cveUnidad){
		limpiarUnidad();
		
		$('#myCorrect').modal('hide');
		var dUnidad = new Unidad(cveUnidad, null,null);
		dUnidad = JSON.stringify(dUnidad);
		console.log(dUnidad);
		
		

		$.ajax({
			data : dUnidad,
			url : 'buscarunidades',
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
					$("#hddCveUnidad").val(element.cveUnidad)
					
					$("#txtDescUnidad").val(element.descUnidad)
					$("#frmUnidades").show();
					$("#ListaUnidades").hide();
					
				});
				
			}
		});
		
		
	}; 
	// FALTA EL METODO PARA ELIMINAR LAS ENTRADAS
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
		$("#guardarUnidades").click(function() {
			$('#myModal').modal(function() {
				guardarUnidades();

			});

		});
	})

</script>

