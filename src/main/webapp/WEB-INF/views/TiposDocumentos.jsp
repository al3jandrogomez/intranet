<input type="hidden" id="hddCveTipoDocumento" value="0">
<div class="form-group row">
	<h1>Catalogo de Tipos Documentos</h1>


</div>
<div id="frmTiposDocumentos">
	<div class="dropdown-divider"></div>
	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			id="guardarTiposDocumentos">Guardar</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarTiposDocumentos()">Buscar</button>
		<button type="button" class="btn btn-success btn-space"
			onclick="limpiarTiposDocumentos()">Limpiar</button>
		<button type="button" class="btn btn-danger btn-space">Eliminar</button>

	</div>
	<div class="dropdown-divider"></div>
	
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Nombre del tipo de documento</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtDescTipoDocumento">
		</div>
	</div>
</div>
<div id="ListaTiposDocumentos" style="display: none"></div>

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
				<button type="button" class="btn btn-primary" onclick="guardarTiposDocumentos();">Guardar
					Tipo Documento</button>
			</div>
		</div>
	</div>
</div>

<script>
 TipoDocumento = function(cveTipoDocumento,descTipoDocumento,activo){
	 this.cveTipoDocumento=cveTipoDocumento;
	 this.descTipoDocumento= descTipoDocumento;
	 this.activo=activo;
 }
 
	buscarTiposDocumentos = function() {

		var cveTipoDocumento = $("#hddCveTipoDocumento").val()=="0" ? "":$("#hddCveTipoDocumento").val();
		
		var descTipDocumento = $("#txtDescTipoDocumento").val()=="" ? null:"%"+$("#txtDescTipoDocumento").val()+"%";
		
		
		var dTipoDocumento = new TipoDocumento(null, descTipDocumento,null);
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
						$("#modalCorrect").empty();
						$("#modalCorrect").append("Procesando");
						$("#myModalCorrect").modal();
					},
					success : function(response) {
						$('#myModalCorrect').modal('hide');
						$("#modalCorrect").empty();
						$("#ListaTiposDocumentos").empty();
						
												if (response.length > 0) {
							var lista = "<table class=\"table\"> <thead>";
							lista += "<thead><tr><th>#</th> <th>Descripcion </th><th>&nbsp;</th></tr></thead><tbody>";
							
							$.each(response, function(index,element){
								lista+="<tr><th scope=\"row\">"+(index+1)+"</th><td>"+element.descTipoDocumento+"</td><td><img onclick=\"editarTipoDocumento("+element.cveTipoDocumento+")\" src=\"resources/images/edit.jpg\" width=40px heigth=40px class=\"img-thumbnail\"><img src=\"resources/images/delete.png\" width=40px heigth=40px class=\"img-thumbnail\"></td></tr>";
							});
							lista+="</tbody></table>";

							$("#ListaTiposDocumentos").append(lista);
							$("#frmTiposDocumentos").hide();
							$("#ListaTiposDocumentos").show();
							
							
						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron tipos de documentos con los criterios ingresados");
							$("#myModalCorrect").modal();
						}
					},
					error: function (xhr, ajaxOptions, thrownError) {
						$("#myModalError")
						.append(
								"No se encontraron tipos de documentos con los criterios ingresados");
				$("#myModalError").modal();
				      }
				});

	}
	
	guardarTiposDocumentos = function() {

		$('#myModal').modal('hide');
		var cveTipoDocumento = $("#hddCveTipoDocumento").val()=="0" ? "":$("#hddCveTipoDocumento").val();
		
		var descTipoDocumento = $("#txtDescTipoDocumento").val()=="" ?"":$("#txtDescTipoDocumento").val();
		var flag = 1;
		var faltante = "";
		
		if (descTipoDocumento == "") {
			faltante += "Ingrese el Nombre del tipo de documento<br>";
			flag = 0;
		}
		
		if (flag == 1) {

			
			var dTipoDocumento = new TipoDocumento(cveTipoDocumento, descTipoDocumento,null);
			dTipoDocumento = JSON.stringify(dTipoDocumento);
			console.log(dTipoDocumento);

			$.ajax({
				data : dTipoDocumento,
				url : 'guardartiposdocumentos',
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
					if (response.cveTipoDocumento > 0){
						$("#modalCorrect").append("Se guardo el tipo de documento");
						limpiarTiposDocumentos();
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

	limpiarTiposDocumentos = function() {
		 $("#hddCveTipoDocumento").val(0);
		 $("#txtDescTipoDocumento").val("");
		 
	}
	editarTipoDocumento = function(cveTipoDocumento){
		limpiarTiposDocumentos();
		
		$('#myCorrect').modal('hide');
		var dTipoDocumento = new TipoDocumento(cveTipoDocumento, null,null);
		dTipoDocumento = JSON.stringify(dTipoDocumento);
		console.log(dTipoDocumento);
		
		

		$.ajax({
			data : dTipoDocumento,
			url : 'buscartiposdocumentos',
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
					$("#hddCveTipoDocumento").val(element.cveTipoDocumento)
					
					$("#txtDescTipoDocumento").val(element.descTipoDocumento)
					$("#frmTiposDocumentos").show();
					$("#ListaTiposDocumentos").hide();
					
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
		$("#guardarTiposDocumentos").click(function() {
			$('#myModal').modal();

		});
	})

</script>

