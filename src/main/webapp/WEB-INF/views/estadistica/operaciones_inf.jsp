<div class="form-group row">
    <h1>Verificaci&oacute;n Informes Mensuales</h1>


</div>
<div id="frmMateriaPenal">
    <div class="dropdown-divider"></div>

    <div id="ListaRequisiciones" style="display: block">
        <div class="dropdown-divider"></div>
        <div class="  form-group row btn-toolbar col-4">

            <select id="cmbListaEstatus" class="form-control" onchange='listaEntregaInformes()'>
                <option value="C">Cargados sin validar por Coordinador</option>
                <option value="R">Informe Rechazado</option>
                <option value="A">Informe Autorizado</option>
                <option value="E">Archivos con error</option>
                <option value="V">Procesados por sistema</option>
                <option value="">Elija una opci&oacute;n</option>
            </select>
            <select id="cmbListaRegiones" class="form-control" onchange='listaEntregaInformes()'>
                <option value="1">Toluca</option>
                <option value="2">Nororiente</option>
                <option value="3">Oriente</option>

            </select>
            <select name="ltsMeses" class="form-control" id="ltsMeses" onchange='listaEntregaInformes()'></select>
        </div>
        <div class="  form-group row btn-toolbar col-10">



            <div class="col-4">
                <button type="button" class="btn btn-warning btn-sm btn-space"
                    onclick="listaEntregaInformes()">Buscar</button>
                <button type="button" class="btn btn-warning btn-sm btn-space" onclick="procesarPendientes()">Procesar
                    Pendientes</button>

            </div>


        </div>
        <div class="dropdown-divider"></div>
        <div id="tblListaInformes">

        </div>
    </div>
    <div class="dropdown-divider"></div>

    <div id="listaOficios" style="display: block"></div>


    <div class="modal fade" id="myModalCorrect" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="modalCorrect">hola</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>

                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModalError" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Se encontraron
                        algunos errores</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
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
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">Los datos que ingreso son correctos?</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    <button type="button" class="btn btn-primary" onclick="guardarMateriaPenal();">Guardar
                        Articulo</button>
                </div>
            </div>
        </div>
    </div>
    <script>

        meses = {
            1: 'Enero',
            2: "Febrero",
            3: "Marzo",
            4: "Abril",
            5: "Mayo",
            6: "Junio",
            7: "Julio",
            8: "Agosto",
            9: "Septiembre",
            10: "Octubre",
            11: "Noviembre",
            12: "Dicimebre"
        };

        function procesarPendientes() {
            $
                .ajax({
                    async: false,
                    // data: JSON.stringify(informe),
                    url: 'listapendientes',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {
                        // 				$("#modalCorrect").empty();
                        // 				$("#modalCorrect").append("Procesando");
                        // 				$("#myModalCorrect").modal();
                    },
                    success: function (response) {

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        $("#myModalError").append("Error al cargar los datos");
                        $("#myModalError").modal();
                    }
                });



        }

        function cargarMeses() {




            $("#ltsMeses").empty();
            var option = "";
            Object.entries(meses).forEach(([key, value]) => {
                console.log(key, value);
                option += "<option value='" + key + "'>" + value + "</option>";
            });

            $("#ltsMeses").append(option);

        }

        listaEntregaInformes = function () {
            console.log("lista Atencion");
            var cveEstatus = $("#cmbListaEstatus").val();
            var cveRegion = $("#cmbListaRegiones").val();
            var cveMes = $("#ltsMeses").val();
            var adscripcion = new Object();
            adscripcion.cveRegion = cveRegion;
            var informe = new Object();
            informe.adscripcion = adscripcion;
            informe.estatusInforme = cveEstatus;
            informe.cveMes = cveMes;



            var lista = "";
            var nombre = "";
            $
                .ajax({
                    async: false,
                    data: JSON.stringify(informe),
                    url: 'listaentregainformesfamiliares',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {
                        // 				$("#modalCorrect").empty();
                        // 				$("#modalCorrect").append("Procesando");
                        // 				$("#myModalCorrect").modal();
                    },
                    success: function (response) {
                        var lista = "<table class=\"table\"> <thead>";
                        lista += "<thead><tr><th>#</th> <th>Informe</th><th>Defensor</th><th>Mes que reporta</th><th>fecha Recepcion</th> <th>Estatus</th></tr></thead><tbody>";

                        $("#tblListaInformes").empty();


                        $
                            .each(
                                response,
                                function (index, element) {
                                    var estatus = element.estatusInforme;
                                    var descripcion = "";
                                    var nombre = element.personal.nombre + " " + element.personal.paterno + " " + element.personal.materno;
                                    var mes = meses[element.cveMes];
                                    if (estatus === "C")
                                        descripcion = "En Proceso validaci&oacute;n por coordinador";
                                    else if (estatus === "E")
                                        descripcion = "Hubo un error al cargar el archivo";
                                    else if (estatus === "I")
                                        descripcion = "Informe Validado";
                                    else if (estatus === "V")
                                        descripcion = "Procesado por sistema";

                                    else if (estatus === "R")
                                        descripcion = "Informe rechazado";

                                    lista += "<tr><th scope=\"row\">"
                                        + (index + 1)
                                        + "</th><td>"
                                        + element.adscripcion.descAdscripcion

                                        + "</td><td>"
                                        + nombre
                                        + "</td><td>"
                                        + mes

                                        + "</td><td>"
                                        + element.fechaRegistro
                                        + "</td>"
                                        + "</td><td>"
                                        + descripcion
                                        + "</td>"
                                        + "<td></td></tr>";


                                });
                        lista += "</tbody></table>";
                        $("#tblListaInformes").append(lista);

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        $("#myModalError").append("Error al cargar los datos");
                        $("#myModalError").modal();
                    }
                });

        };
        $(function () {

            cargarMeses();
            var idRegion = $("#idRegion").val();
            $("#cmbListaRegiones").val(idRegion);
            listaEntregaInformes();

        });
    </script>