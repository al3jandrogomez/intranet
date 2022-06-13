<input type="hidden" id="hddCveProveedor" value="0">
<div class="form-group row">
	<h1>Catalogo de Proveedores</h1>


</div>
<div id="frmProveedores">
	<div class="dropdown-divider"></div>
	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			id="guardarProveedores">Guardar</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarProveedores()">Buscar</button>
		<button type="button" class="btn btn-success btn-space"
			onclick="limpiarProveedores()">Limpiar</button>
		<button type="button" class="btn btn-danger btn-space">Eliminar</button>

	</div>
	<div class="dropdown-divider"></div>
	
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Nombre del proveedor</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtDescProveedor">
		</div>
	</div>
</div>
<div id="ListaProveedores" style="display: none"></div>

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
				<button type="button" class="btn btn-primary" onclick="guardarProveedores();">Guardar
					Proveedor</button>
			</div>
		</div>
	</div>
</div>

<script>
 Proveedor = function(cveProveedor,descProveedor,activo){
	 this.cveProveedor=cveProveedor;
	 this.descProveedor= descProveedor;
	 this.activo=activo;
	 
 }
 
	buscarProveedores = function() {

		var cveProveedor = $("#hddCveProveedor").val()=="0" ? "":$("#hddCveProveedor").val();
		
		var descProveedor = $("#txtDescProveedor").val()=="" ? null:"%"+$("#txtDescProveedor").val()+"%";
		
		
		var dProveedor = new Proveedor(null, descProveedor);
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
						$("#modalCorrect").empty();
						$("#modalCorrect").append("Procesando");
						$("#myModalCorrect").modal();
					},
					success : function(response) {
						$('#myModalCorrect').modal('hide');
						$("#modalCorrect").empty();
						$("#ListaProveedores").empty();
						
												if (response.length > 0) {
							var lista = "<table class=\"table\"> <thead>";
							lista += "<thead><tr><th>#</th> <th>Descripcion </th><th>&nbsp;</th></tr></thead><tbody>";
							
							$.each(response, function(index,element){
								lista+="<tr><th scope=\"row\">"+(index+1)+"</th><td>"+element.descProveedor+"</td><td><img onclick=\"editarProveedores("+element.cveProveedor+")\" src=\"resources/images/edit.jpg\" width=40px heigth=40px class=\"img-thumbnail\"><img src=\"resources/images/delete.png\" width=40px heigth=40px class=\"img-thumbnail\"></td></tr>";
							});
							lista+="</tbody></table>";

							$("#ListaProveedores").append(lista);
							$("#frmProveedores").hide();
							$("#ListaProveedores").show();
							
							
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
								"No se encontraron proveedores con los criterios ingresados");
				$("#myModalError").modal();
				      }
				});

	}
	
	guardarProveedores = function() {

		$('#myModal').modal('hide');
		var cveProveedor = $("#hddCveProveedor").val()=="0" ? "":$("#hddCveProveedor").val();
		
		var descProveedor = $("#txtDescProveedor").val()=="" ?"":$("#txtDescProveedor").val();
		var flag = 1;
		var faltante = "";
		
		if (descProveedor == "") {
			faltante += "Ingrese el Nombre del Proveedor<br>";
			flag = 0;
		}
		
		if (flag == 1) {

			
			var dProveedor = new Proveedor(cveProveedor, descProveedor,null);
			dProveedor = JSON.stringify(dProveedor);
			console.log(dProveedor);

			$.ajax({
				data : dProveedor,
				url : 'guardarproveedores',
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
					if (response.cveProveedor > 0){
						$("#modalCorrect").append("Se guardo el proveedor");
						limpiarProveedores();
					}
					else
						$("#modalCorrect").append(
								"El nombre del proveedor ya existe verifique los datos");
					$("#myModalCorrect").modal();
				},
				error: function (xhr, ajaxOptions, thrownError) {
					
					
					$("#myModalCorrect").modal("hide");
					$("#myModalError")
					.append(
							"No se encontraron proveedores con los criterios ingresados");
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

	limpiarProveedores = function() {
		 $("#hddCveProveedor").val(0);
		 $("#txtDescProveedor").val("");
		 
	}
	editarProveedores = function(cveProveedor){
		limpiarProveedores();
		
		$('#myCorrect').modal('hide');
		var dProveedor = new Proveedor(cveProveedor, null,null);
		dProveedor = JSON.stringify(dProveedor);
		console.log(dProveedor);
		
		

		$.ajax({
			data : dProveedor,
			url : 'buscarproveedores',
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
					$("#hddCveProveedor").val(element.cveProveedor)
					
					$("#txtDescProveedor").val(element.descProveedor)
					$("#frmProveedores").show();
					$("#ListaProveedores").hide();
					
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
		$("#guardarProveedores").click(function() {
			$('#myModal').modal();

		});
	})

</script>

