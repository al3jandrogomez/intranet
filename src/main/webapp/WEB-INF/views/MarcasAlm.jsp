<input type="hidden" id="hddCveMarca" value="0">
<div class="form-group row">
	<h1>Catalogo de Marcas</h1>


</div>
<div id="frmMarcas">
	<div class="dropdown-divider"></div>
	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			id="guardarMarcas">Guardar</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarMarcas()">Buscar</button>
		<button type="button" class="btn btn-success btn-space"
			onclick="limpiarMarca()">Limpiar</button>
		<button type="button" class="btn btn-danger btn-space">Eliminar</button>

	</div>
	<div class="dropdown-divider"></div>
	
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Nombre de la Marca</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtDescMarca">
		</div>
	</div>
</div>
<div id="ListaMarcas" style="display: none"></div>

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
				<button type="button" class="btn btn-primary" onclick="guardarMarcas();">Guardar
					Marca</button>
			</div>
		</div>
	</div>
</div>

<script>
 Marca = function(cveMarcaAlm,descMarca,activo){
	 this.cveMarcaAlm=cveMarcaAlm;
	 this.descMarca= descMarca;
	 this.activo=activo
	 
 }
 
	buscarMarcas = function() {

		var cveMarca = $("#hddCveMarca").val()=="0" ? "":$("#hddCveMarca").val();
		
		var descMarca = $("#txtDescMarca").val()=="" ? null:"%"+$("#txtDescMarca").val()+"%";
		
		
		var dMarca = new Marca(null, descMarca,"S");
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
						$("#modalCorrect").empty();
						$("#modalCorrect").append("Procesando");
						$("#myModalCorrect").modal();
					},
					success : function(response) {
						$('#myModalCorrect').modal('hide');
						$("#modalCorrect").empty();
						$("#ListaMarcas").empty();
						
												if (response.length > 0) {
							var lista = "<table class=\"table\"> <thead>";
							lista += "<thead><tr><th>#</th> <th>Descripcion </th><th>&nbsp;</th></tr></thead><tbody>";
							
							$.each(response, function(index,element){
								lista+="<tr><th scope=\"row\">"+(index+1)+"</th><td>"+element.descMarca+"</td><td><img onclick=\"editarUnidad("+element.cveMarcaAlm+")\" src=\"resources/images/edit.jpg\" width=40px heigth=40px class=\"img-thumbnail\"><img src=\"resources/images/delete.png\" width=40px heigth=40px class=\"img-thumbnail\"></td></tr>";
							});
							lista+="</tbody></table>";

							$("#ListaMarcas").append(lista);
							$("#frmMarcas").hide();
							$("#ListaMarcas").show();
							
							
						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron marcas con los criterios ingresados");
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
	
	guardarMarcas = function() {

		$('#myModal').modal('hide');
		var cveMarca = $("#hddCveMarca").val()=="0" ? "":$("#hddCveMarca").val();
		
		var descMarca = $("#txtDescMarca").val()=="" ?"":$("#txtDescMarca").val();
		var flag = 1;
		var faltante = "";
		
		if (descMarca == "") {
			faltante += "Ingrese el Nombre de la Marca<br>";
			flag = 0;
		}
		
		if (flag == 1) {

			
			var dMarca = new Marca(null, descMarca);
			dMarca = JSON.stringify(dMarca);
			console.log(dMarca);

			$.ajax({
				data : dMarca,
				url : 'guardarmarcasAlm',
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
					if (response.cveMarcaAlm > 0){
						$("#modalCorrect").append("Se guardo la marca");
						limpiarMarca();
					}
					else
						$("#modalCorrect").append(
								"La marca ya existe verifique los datos");
					$("#myModalCorrect").modal();
				},
				error: function (xhr, ajaxOptions, thrownError) {
					$("#myModalCorrect").modal("hide");
					$("#myModalError")
					.append(
							"No se encontraron marcas con los criterios ingresados");
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

	limpiarMarca = function() {
		 $("#hddCveMarca").val(0);
		 $("#txtDescMarca").val("");
		 
	}
	editarMarca = function(cveMarca){
		limpiarMarca();
		
		$('#myCorrect').modal('hide');
		var dMarca = new Marca(cveMarca, null);
		dMarca = JSON.stringify(dMarca);
		console.log(dMarca);
		
		

		$.ajax({
			data : dMarca,
			url : 'buscarMarcas',
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
					$("#hddCveMarca").val(element.cveMarcaAlm)
					
					$("#txtDescMarca").val(element.descMarca)
					$("#frmMarcas").show();
					$("#ListaMarcas").hide();
					
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
		$("#guardarMarcas").click(function() {
			$('#myModal').modal(function() {
				guardarUnidades();

			});

		});
	})

</script>

