<input type="hidden" id="hddCvePartida" value="0">
<div class="form-group row">
	<h1>Catalogo de Partidas</h1>


</div>
<div id="frmPartidas">
	<div class="dropdown-divider"></div>
	<div class="  form-group row btn-toolbar col-10">
		<button type="button" class="btn btn-primary btn-sm btn-space"
			id="entradaGuardarPartida">Guardar</button>
		<button type="button" class="btn btn-warning btn-sm btn-space"
			onClick="buscarPartidas()">Buscar</button>
		<button type="button" class="btn btn-success btn-space"
			onclick="limpiarPartida()">Limpiar</button>
		<button type="button" class="btn btn-danger btn-space">Eliminar</button>

	</div>
	<div class="dropdown-divider"></div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Clave</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtClave">
		</div>
	</div>
	<div class="form-group ">
		<label for="example-search-input" class="col-10 col-form-label">Descripcion
			de la Partida</label>
		<div class="col-10">
			<input class="form-control" type="text" value="" id="txtDescPartida">
		</div>
	</div>
</div>
<div id="ListaPartidas" style="display: none"></div>

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
				<button type="button" class="btn btn-primary" onclick="guardarPartida();">Guardar
					Entrada</button>
			</div>
		</div>
	</div>
</div>

<script>
 Partida = function(cvePartida,clavePartida,descPartida){
	 this.cvePartida=cvePartida;
	 this.clavePartida = clavePartida;
	 this.descPartida= descPartida;
	 
 }
 
	buscarPartidas = function() {

		var cvePartida = $("#hddCvePartida").val()=="0" ? "":$("#hddCvePartida").val();
		var clavePartida = $("#txtClave").val()=="" ? null:"%"+$("#txtClave").val()+"%";
		var descPartida = $("#txtDescPartida").val()=="" ? null:"%"+$("#txtDescPartida").val()+"%";
		
		
		var dPartida = new Partida(null, clavePartida,descPartida);
		dPartida = JSON.stringify(dPartida);
		console.log(dPartida);

		$
				.ajax({
					data : dPartida,
					url : 'buscarpartida',
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
						$("#ListaPartidas").empty();
						
												if (response.length > 0) {
							var lista = "<table class=\"table\"> <thead>";
							lista += "<thead><tr><th>#</th> <th>Clave Partida</th><th>Descripcion </th><th>&nbsp;</th></tr></thead><tbody>";
							
							$.each(response, function(index,element){
								lista+="<tr><th scope=\"row\">"+(index+1)+"</th><td>"+element.clavePartida+"</td><td>"+element.descPartida+"</td><td><img onclick=\"editarPartida("+element.cvePartida+")\" src=\"resources/images/edit.jpg\" width=40px heigth=40px class=\"img-thumbnail\"><img src=\"resources/images/delete.png\" width=40px heigth=40px class=\"img-thumbnail\"></td></tr>";
							});
							lista+="</tbody></table>";

							$("#ListaPartidas").append(lista);
							$("#frmPartidas").hide();
							$("#ListaPartidas").show();
							
							
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
	
	guardarPartida = function() {

		$('#myModal').modal('hide');
		var cvePartida = $("#hddCvePartida").val()=="0" ? "":$("#hddCvePartida").val();
		var clavePartida = $("#txtClave").val()=="" ?"":$("#txtClave").val();
		var descPartida = $("#txtDescPartida").val()=="" ?"":$("#txtDescPartida").val();
		var flag = 1;
		var faltante = "";
		if (clavePartida == "") {
			faltante += "Ingrese la clave de la partida<br>";
			flag = 0;
		}
		if (descPartida == "") {
			faltante += "Ingrese el Nombre de la Partida<br>";
			flag = 0;
		}
		
		if (flag == 1) {

			
			var dPartida = new Partida(null, clavePartida,descPartida);
			dPartida = JSON.stringify(dPartida);
			console.log(dPartida);

			$.ajax({
				data : dPartida,
				url : 'guardarpartida',
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
					if (response.cvePartida > 0){
						$("#modalCorrect").append("Se guardo la partida");
						limpiarPartida();
					}
					else
						$("#modalCorrect").append(
								"La partida ya existe verifique los datos");
					$("#myModalCorrect").modal();
				},
				error: function (xhr, ajaxOptions, thrownError) {
					$("#myModalCorrect").modal("hide");
					$("#myModalError")
					.append(
							"No se encontraron entradas con los criterios ingresados");
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

	limpiarPartida = function() {
		 $("#hddCvePartida").val(0);
		 $("#txtClave").val("");
		 $("#txtDescPartida").val("");
		 
	}
	editarPartida = function(cvePartida){
		limpiarPartida();
		
		$('#myCorrect').modal('hide');
		var dPartida = new Partida(cvePartida, null,null);
		dPartida = JSON.stringify(dPartida);
		console.log(dPartida);
		
		

		$.ajax({
			data : dPartida,
			url : 'buscarPartida',
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
					$("#hddCvePartida").val(element.cvePartida)
					$("#txtClave").val(element.clavePartida)
					$("#txtDescPartida").val(element.descPartida)
					$("#frmPartidas").show();
					$("#ListaPartidas").hide();
					
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
		$("#frmEntrada").show();
	}
	};
	$(function() {
		$(".datepicker").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("#entradaGuardarPartida").click(function() {
			$('#myModal').modal(function() {
				guardarPartida();

			});

		});
	})

</script>

