<input type="hidden" id="hddCvePrograma" value="0">
<div class="form-group row">
	<h1>Catalogo de Programas</h1>


</div>
<div id="frmProgramas">
	<div class="dropdown-divider"></div>
	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			id="guardarProgramas">Guardar</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarProgramas()">Buscar</button>
		<button type="button" class="btn btn-success btn-space"
			onclick="limpiarProgramas()">Limpiar</button>
		<button type="button" class="btn btn-danger btn-space">Eliminar</button>

	</div>
	<div class="dropdown-divider"></div>
	
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Nombre del programa</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtDescPrograma">
		</div>
	</div>
</div>
<div id="ListaProgramas" style="display: none"></div>

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
				<button type="button" class="btn btn-primary" onclick="guardarProgramas();">Guardar
					Programa</button>
			</div>
		</div>
	</div>
</div>

<script>
 Programa = function(cvePrograma,descPrograma,activo){
	 this.cvePrograma=cvePrograma;
	 this.descPrograma= descPrograma;
	 this.activo=activo;
	 
 }
 
	buscarProgramas = function() {

		var cvePrograma = $("#hddCvePrograma").val()=="0" ? "":$("#hddCvePrograma").val();
		
		var descPrograma = $("#txtDescPrograma").val()=="" ? null:"%"+$("#txtDescPrograma").val()+"%";
		
		
		var dPrograma = new Programa(null, descPrograma);
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
						$("#modalCorrect").empty();
						$("#modalCorrect").append("Procesando");
						$("#myModalCorrect").modal();
					},
					success : function(response) {
						$('#myModalCorrect').modal('hide');
						$("#modalCorrect").empty();
						$("#ListaProgramas").empty();
						
												if (response.length > 0) {
							var lista = "<table class=\"table\"> <thead>";
							lista += "<thead><tr><th>#</th> <th>Descripcion </th><th>&nbsp;</th></tr></thead><tbody>";
							
							$.each(response, function(index,element){
								lista+="<tr><th scope=\"row\">"+(index+1)+"</th><td>"+element.descPrograma+"</td><td><img onclick=\"editarProgramas("+element.cvePrograma+")\" src=\"resources/images/edit.jpg\" width=40px heigth=40px class=\"img-thumbnail\"><img src=\"resources/images/delete.png\" width=40px heigth=40px class=\"img-thumbnail\"></td></tr>";
							});
							lista+="</tbody></table>";

							$("#ListaProgramas").append(lista);
							$("#frmProgramas").hide();
							$("#ListaProgramas").show();
							
							
						} else {
							$("#modalCorrect")
									.append(
											"No se encontraron programas con los criterios ingresados");
							$("#myModalCorrect").modal();
						}
					},
					error: function (xhr, ajaxOptions, thrownError) {
						$("#myModalError")
						.append(
								"No se encontraron programas con los criterios ingresados");
				$("#myModalError").modal();
				      }
				});

	}
	
	guardarProgramas = function() {

		$('#myModal').modal('hide');
		var cvePrograma = $("#hddCvePrograma").val()=="0" ? "":$("#hddCvePrograma").val();
		
		var descPrograma = $("#txtDescPrograma").val()=="" ?"":$("#txtDescPrograma").val();
		var flag = 1;
		var faltante = "";
		
		if (descPrograma == "") {
			faltante += "Ingrese el Nombre del Programa<br>";
			flag = 0;
		}
		
		if (flag == 1) {

			
			var dPrograma = new Programa(cvePrograma, descPrograma,null);
			dPrograma = JSON.stringify(dPrograma);
			console.log(dPrograma);

			$.ajax({
				data : dPrograma,
				url : 'guardarprograma',
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
					if (response.cvePrograma > 0){
						$("#modalCorrect").append("Se guardo el programa");
						limpiarProgramas();
					}
					else
						$("#modalCorrect").append(
								"El nombre del programa ya existe verifique los datos");
					$("#myModalCorrect").modal();
				},
				error: function (xhr, ajaxOptions, thrownError) {
					
					
					$("#myModalCorrect").modal("hide");
					$("#myModalError")
					.append(
							"No se encontraron programas con los criterios ingresados");
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

	limpiarProgramas = function() {
		 $("#hddCvePrograma").val(0);
		 $("#txtDescPrograma").val("");
		 
	}
	editarProgramas = function(cvePrograma){
		limpiarProgramas();
		
		$('#myCorrect').modal('hide');
		var dPrograma = new Programa(cvePrograma, null,null);
		dPrograma = JSON.stringify(dPrograma);
		console.log(dPrograma);
		
		

		$.ajax({
			data : dPrograma,
			url : 'buscarprograma',
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
					$("#hddCvePrograma").val(element.cvePrograma)
					
					$("#txtDescPrograma").val(element.descPrograma)
					$("#frmProgramas").show();
					$("#ListaProgramas").hide();
					
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
		$("#guardarProgramas").click(function() {
			$('#myModal').modal();

		});
	})

</script>