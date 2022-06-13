<div class="form-group row">
    <h1>Verificaci&oacute;n Informes Mensuales</h1>
    <input type="hidden" id="hddIdInforme" value='0'>


</div>
<div id="frmMateriaPenal">
    <div class="dropdown-divider"></div>

    <div id="ListaRequisiciones" style="display: block">
        <div class="dropdown-divider"></div>
        <div class="  form-group row btn-toolbar col-4">
            <div class="col-10">
                <label for="example-search-input" class="col-10 col-form-label">Nombre
                    Servidor Publico</label>

                <input class="form-control" type="text" id="txtnombre" />
                <input type="hidden" id="hddCveUsuario">
            </div>

            <select id="cmbListaEstatus" class="form-control" onchange='listaEntregaInformes()'>
                <option value="C">Cargados sin validar por Coordinador</option>
                <option value="R">Informe Rechazado</option>
                <option value="A">Informe Autorizado</option>
                <option value="E">Archivos con error</option>
                <option value="">Elija una opci&oacute;n</option>
            </select>
            <select id="cmbListaRegiones" class="form-control" onchange='listaEntregaInformes()'>
                <option value="1">Toluca</option>
                <option value="2">Nororiente</option>
                <option value="3">Oriente</option> 

            </select>
            <select name="ltsMeses" class="form-control" id="ltsMeses" onchange='listaEntregaInformes()'></select>
            <select name="ltsAnios" class="form-control" id="ltsAnios" onchange='listaEntregaInformes()'>
                <option value="2021">2021</option>
                <option value="2022">2022</option></select>
        </div>
        <div class="  form-group row btn-toolbar col-10">



            <div class="col-4">
                <button type="button" class="btn btn-warning btn-sm btn-space"
                    onclick="listaEntregaInformes()">Buscar</button>

            </div>


        </div>
        <div class="dropdown-divider"></div>
        <div id="tblListaInformes">

        </div>
    </div>
    <div class="dropdown-divider"></div>
    <div class="valida" id="nomDefensor" style="display: block"></div>
        <div class="valida" id="botones" style="display: block"><input type='button' class="btn btn-success" value="Autorizar" onclick="autorizar()"/><input type='button' class="btn btn-danger" value="Rechazar" onclick="rechazar()" /><input type='button' class="btn btn-warning" value="Regresar a la lista" onclick="regresarlista()"></div>
    <div class="valida" id="listaOficios" style="display: block"></div>


    </div>


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
         var docEditor;

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

        function autorizar(){
            var idInforme=$("#hddIdInforme").val();
            var informe = new Object();
            informe.idInforme=idInforme;
            $
                .ajax({
                    async: false,
                    data: JSON.stringify(informe),
                    url: "autorizarinforme",
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {
                        // 				$("#modalCorrect").empty();
                        // 				$("#modalCorrect").append("Procesando");
                        // 				$("#myModalCorrect").modal();
                    },
                    success: function (response) {
                        
                        regresarlista();
                        listaEntregaInformes();



                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        $("#myModalError").append("Error al cargar los datos");
                        $("#myModalError").modal();
                    }
                });


        }
        function rechazar(){
            var idInforme=$("#hddIdInforme").val();
            var informe = new Object();
            informe.idInforme=idInforme;
            $
                .ajax({
                    async: false,
                    data: JSON.stringify(informe),
                    url: "rechazarinforme",
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {
                        // 				$("#modalCorrect").empty();
                        // 				$("#modalCorrect").append("Procesando");
                        // 				$("#myModalCorrect").modal();
                    },
                    success: function (response) {
                        
                        regresarlista();
                        listaEntregaInformes();



                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        $("#myModalError").append("Error al cargar los datos");
                        $("#myModalError").modal();
                    }
                });


        }


        function regresarlista(){
            $(".valida").hide();
            $("#tblListaInformes").show();
            $("#listaOficios").empty();
            $("#ListaRequisiciones").show();
            $("#nomDefensor").empty();
            $("#hddIdInforme").val(0);
            docEditor.destroyEditor();
           
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


        function cargarExcel(idInforme) {
            $("#hddIdInforme").val(idInforme);
            $("#tblListaInformes").hide();
            $("#ListaRequisiciones").hide();
            $(".valida").show();
            var nombreArchivo = "";
            var informe = new Object();
            informe.idInforme = idInforme;
            $
                .ajax({
                    async: false,
                    data: JSON.stringify(informe),
                    url: "informeexcel",
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {
                        // 				$("#modalCorrect").empty();
                        // 				$("#modalCorrect").append("Procesando");
                        // 				$("#myModalCorrect").modal();
                    },
                    success: function (response) {
                        nombreArchivo = response[0].rutaArchivo;
                        nombres = nombreArchivo.split("/");
                        nombreArchivo = nombres[(nombres.length - 1)];
                        var nomDefensor = response[0].personal.nombre+" "+response[0].personal.paterno+" "+response[0].personal.materno;
                        $("#listaOficios").empty();
                        
                        var defensor = "<label> Informe de: "+nomDefensor+"</label>";
                        $("#nomDefensor").append(defensor);
                        config = {
                            "document": {
                                "fileType": "xlsx",

                                "permissions": { "download": false, "print": false },
                                "title": nombreArchivo, "url": "http://intranet.idpedomex.gob.mx/revision/" + nombreArchivo
                            },
                            "documentType": "spreadsheet",
                            "height": "550px",

                            "width": "100%"
                        };
                         docEditor = new DocsAPI.DocEditor("listaOficios", config);




                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        $("#myModalError").append("Error al cargar los datos");
                        $("#myModalError").modal();
                    }
                });


        }


        listaEntregaInformes = function () {
            console.log("lista Atencion");
            var cveEstatus = $("#cmbListaEstatus").val();
            var cveRegion = $("#cmbListaRegiones").val();
            var cveMes = $("#ltsMeses").val();
            var anio = $("#ltsAnios").val();

            var adscripcion = new Object();
            adscripcion.cveRegion = cveRegion;
            var informe = new Object();
            informe.adscripcion = adscripcion;
            informe.estatusInforme = cveEstatus;
            informe.cveMes = cveMes;
            informe.anio = anio;
            var cveUsuario = $("#hddCveUsuario").val();
            var operacion = "listaentregainformesfamiliares";
           /* if (cveUsuario > 0) {
                var personal = new Object();
                personal.cveUsuario = cveUsuario;
                informe.personal = personal;
                operacion = "listainformesservidorpublico";
            }*/




            var lista = "";
            var nombre = "";
            $
                .ajax({
                    async: false,
                    data: JSON.stringify(informe),
                    url: operacion,
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
                                        descripcion = "En proceso de valiaci&oacute;n del coordinador";
                                    else if (estatus === "E")
                                        descripcion = "Hubo un error al cargar el archivo";
                                    else if (estatus === "A")
                                        descripcion = "Informe Validado";
                                    else if (estatus === "R")
                                        descripcion = "Informe Rechazado";


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
                                        + "<td><img width='20' heigth='20'src='resources/images/test.png' onclick='cargarExcel(" + element.idInforme + ")'></td></tr>";


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


            $("#txtnombre")
                .autocomplete(
                    {
                        source: function (request, response) {
                            var nombre = $("#txtnombre").val() == "" ? null
                                : "%"
                                + $("#txtnombre").val().replace(
                                    /\s/g, "%") + "%";



                            var dNombre = new Object();
                            dNombre.nombreCompleto = nombre;

                            dNombre = JSON.stringify(dNombre);


                            $
                                .ajax({
                                    data: dNombre,
                                    url: 'buscarpersonal',
                                    dataType: 'json',
                                    contentType: "application/json",
                                    type: 'post',
                                    beforeSend: function () {

                                    },
                                    success: function (data) {
                                        response($
                                            .map(
                                                data,
                                                function (item) {
                                                    return {
                                                        label: item.nombreCompleto,
                                                        value: item.cveUsuario
                                                    }
                                                }));
                                    },
                                    error: function (xhr, ajaxOptions,
                                        thrownError) {
                                        $('#myModal').modal('hide');
                                        $("#myModalCorrect").modal("hide");
                                        $("#myModalError")
                                            .append(
                                                "No se pudo registrar el articulo, verifique los datos");
                                        $("#myModalError").modal();
                                    }
                                });

                        },
                        minlength: 2,
                        select: function (event, ui) {
                            event.preventDefault();
                            console.log(ui.item ? "Selected: " + ui.item.label
                                : "Nothing selected, input was "
                                + this.value);
                            $("#txtnombre").val(ui.item.label);
                            $("#hddCveUsuario").val(ui.item.value);

                        },
                        open: function () {
                            $(this).removeClass("ui-corner-all").addClass(
                                "ui-corner-top");
                        },
                        close: function () {
                            $(this).removeClass("ui-corner-top").addClass(
                                "ui-corner-all");
                        }
                    });

            cargarMeses();
            var idRegion = $("#idRegion").val();
            $("#cmbListaRegiones").val(idRegion);
            listaEntregaInformes();
            $(".valida").hide();

        });
    </script>