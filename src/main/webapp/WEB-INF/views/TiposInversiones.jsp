<input type="hidden" id="hddCveTipoInversion" value="0">
<div class="form-group row">
	<h1>Catalogo de TipoInversiones</h1>


</div>
<div id="frmTiposInversiones">
	<div class="dropdown-divider"></div>
	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			id="guardarTiposInversiones">Guardar</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarTiposInversiones()">Buscar</button>
		<button type="button" class="btn btn-success btn-space"
			onclick="limpiarTipoInversiones()">Limpiar</button>
		<button type="button" class="btn btn-danger btn-space">Eliminar</button>

	</div>
	<div class="dropdown-divider"></div>
	
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Nombre del tipo de inversion</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtDescTipoInversion">
		</div>
	</div>
</div>
<div id="ListaTiposInversiones" style="display: none"></div>

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
				<button type="button" class="btn btn-primary" onclick="guardarTiposInversiones();">Guardar
					Tipo de Inversion</button>
			</div>
		</div>
	</div>
</div>

<script>
 TipoInversion = function(cveTipoInversion,descTipoInversion,activo){
	 this.cveTipoInversion=cveTipoInversion;
	 this.descTipoInversion= descTipoInversion;
	 this.activo=activo;
	 
 }
 
	buscarTiposInversiones = function() {

		var cveTipoInversion = $("#hddCveTipoInversion").val()=="0" ? "":$("#hddCveTipoInversion").val();
		
		var descTipoInversion = $("#txtDescTipoInversion").val()=="" ? null:"%"+$("#txtDescTipoInversion").val()+"%";
		
		
		var dTipoInversion = new TipoInversion(null, descTipoInversion);
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
						$("#modalCorrect").empty();
						$("#modalCorrect").append("Procesando");
						$("#myModalCorrect").modal();
					},
					success : function(response) {
						$('#myModalCorrect').modal('hide');
						$("#modalCorrect").empty();
						$("#ListaTiposInversiones").empty();
						
												if (response.length > 0) {
							var lista = "<table class=\"table\"> <thead>";
							lista += "<thead><tr><th>#</th> <th>Descripcion </th><th>&nbsp;</th></tr></thead><tbody>";
							
							$.each(response, function(index,element){
								lista+="<tr><th scope=\"row\">"+(index+1)+"</th><td>"+element.descTipoInversion+"</td><td><img onclick=\"editarTipoInversiones("+element.cveTipoInversion+")\" src=\"resources/images/edit.jpg\" width=40px heigth=40px class=\"img-thumbnail\"><img src=\"resources/images/delete.png\" width=40px heigth=40px class=\"img-thumbnail\"></td></tr>";
							});
							lista+="</tbody></table>";

							$("#ListaTiposInversiones").append(lista);
							$("#frmTiposInversiones").hide();
							$("#ListaTiposInversiones").show();
							
							
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
								"No se encontro el tipo de inversion con los criterios ingresados");
				$("#myModalError").modal();
				      }
				});

	}
	
	guardarTiposInversiones = function() {

		$('#myModal').modal('hide');
		var cveTipoInversion = $("#hddCveTipoInversion").val()=="0" ? "":$("#hddCveTipoInversion").val();
		
		var descTipoInversion = $("#txtDescTipoInversion").val()=="" ?"":$("#txtDescTipoInversion").val();
		var flag = 1;
		var faltante = "";
		
		if (descTipoInversion == "") {
			faltante += "Ingrese el Nombre del TipoInversion<br>";
			flag = 0;
		}
		
		if (flag == 1) {

			
			var dTipoInversion = new TipoInversion(cveTipoInversion, descTipoInversion,null);
			dTipoInversion = JSON.stringify(dTipoInversion);
			console.log(dTipoInversion);

			$.ajax({
				data : dTipoInversion,
				url : 'guardartiposinversiones',
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
					if (response.cveTipoInversion > 0){
						$("#modalCorrect").append("Se guardo el tipo de inversion");
						limpiarTipoInversiones();
					}
					else
						$("#modalCorrect").append(
								"El nombre del tipo de inversion ya existe verifique los datos");
					$("#myModalCorrect").modal();
				},
				error: function (xhr, ajaxOptions, thrownError) {
					
					
					$("#myModalCorrect").modal("hide");
					$("#myModalError")
					.append(
							"No se encontro el tipo de inversion con los criterios ingresados");
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

	limpiarTipoInversiones = function() {
		 $("#hddCveTipoInversion").val(0);
		 $("#txtDescTipoInversion").val("");
		 
	}
	editarTipoInversiones = function(cveTipoInversion){
		limpiarTipoInversiones();
		
		$('#myCorrect').modal('hide');
		var dTipoInversion = new TipoInversion(cveTipoInversion, null,null);
		dTipoInversion = JSON.stringify(dTipoInversion);
		console.log(dTipoInversion);
		
		

		$.ajax({
			data : dTipoInversion,
			url : 'buscartiposinversiones',
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
					$("#hddCveTipoInversion").val(element.cveTipoInversion)
					
					$("#txtDescTipoInversion").val(element.descTipoInversion)
					$("#frmTiposInversiones").show();
					$("#ListaTiposInversiones").hide();
					
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
// 		$(".datepicker").datepicker({
// 			dateFormat : 'dd/mm/yy'
// 		});
		$("#guardarTiposInversiones").click(function() {
			$('#myModal').modal();

		});
	})

</script>

