<input type="hidden" id="hddCveUsuario" value="0">

<div class="form-group row">
    <h1>Permisos a Modulos</h1>


</div>
<div id="frmPermisos">
    <div class="dropdown-divider"></div>
    <div class="  form-group row btn-toolbar col-10">
        <button type="button" class="btn btn-primary btn-sm btn-space"
                style="display: none;" id="entradaGuardar">Agregar</button>
        <button type="button" class="btn btn-warning btn-sm btn-space"
                onClick="buscar()">Buscar</button>
        <button type="button" class="btn btn-success btn-space"
                onclick="limpiar()">Limpiar</button>
        <button type="button" class="btn btn-success btn-space"
                onclick="sincronizar()">Sincronixar usuarios con SIGEDEPU</button>





    </div>
    <div class="dropdown-divider"></div>
    <div class="form-group ">
        <label for="example-search-input" class="col-10 col-form-label">Nombre
            Servidor Publico</label>
        <div class="col-10">
            <input class="form-control" type="text" value="" id="txtnombre">
        </div>
    </div>
    <div class="form-group ">
        <label for="example-search-input" class="col-10 col-form-label">Clave
            Servidor Publico</label>
        <div class="col-10">
            <input class="form-control" type="text" value="" id="txtCveServidor">
        </div>
    </div>
</div>
<div id="accordion"></div>




<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
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
            <div class="modal-body">Los datos que ingreso son correctos?</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary" onclick="guardar();">Guardar
                    Entrada</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
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
                        onclick="guardarDetalle();">Guardar Entrada</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModalEliminar" tabindex="-1" role="dialog"
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
            <div class="modal-body">Esta seguro que desea eliminar el
                articulo?</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button class="btnEliminarDetalle btn btn-primary" type="button">Eliminar
                    Articulo</button>
            </div>
        </div>
    </div>
</div>
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
            <div id="modalErrorText" class="modal-body">aqui va el mensaje</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>

            </div>
        </div>
    </div>
</div>
<script>
    NPersonalC = function (cveUsuario, nombreC, cveServPub, cveModulo) {
        this.cveUsuario = cveUsuario;
        this.nombreCompleto = nombreC;
        this.cveServidorPub = cveServPub;
        this.cveModulo = cveModulo;

    };

    sincronizar = function () {

        $
                .ajax({

                    url: 'sincronizarusuariosigedepu',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {

                    },
                    success: function (data) {
                        
                    },
                    error: function (xhr, ajaxOptions,
                            thrownError) {
                        $('#myModal').modal('hide');
                        $("#myModalCorrect").modal("hide");
                        $("#myModalError")
                                .append(
                                        "Error al sincronizar los usuarios");
                        $("#myModalError").modal();
                    }
                });

    };
    $("#txtnombre")
            .autocomplete(
                    {
                        source: function (request, response) {
                            var nombre = $("#txtnombre").val() == "" ? null
                                    : "%"
                                    + $("#txtnombre").val().replace(
                                    /\s/g, "%") + "%";
                            
                            

                            var dNombre = new NPersonalC(null, nombre, null);
                            dNombre = JSON.stringify(dNombre);
                            console.log(dNombre);

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
                            buscar();
                            buscarPersonal();
                            //$("#txtnombre").val(ui.item.value);

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

    buscarPersonal = function () {
        var cveUsuario = $("#hddCveUsuario").val();
        console.log("cveusuario=>" + cveUsuario);
        var dpersonal = new NPersonalC(cveUsuario, null, null);
        dpersonal = JSON.stringify(dpersonal);
        console.log(dpersonal);

        $.ajax({
            data: dpersonal,
            async: false,
            url: 'consultaPersonal',
            dataType: 'json',
            contentType: "application/json",
            type: 'post',
            beforeSend: function () {

            },
            success: function (response) {
                $("#modalCorrect").empty();
                $("#myModalCorrect").modal("hide");
                $("#myModalEliminar").modal('hide');
                //$("#accordion").append(card + card);
                console.log("opciones del modulo: " + response.length);
                

            },
            error: function (xhr, ajaxOptions, thrownError) {
                error("Ocurrio un error");

            }
        });

    };
    
    buscar = function () {
        var cveUsuario = $("#hddCveUsuario").val();
        console.log("cveusuario=>" + cveUsuario);
        var dpersonal = new NPersonalC(cveUsuario, null, null);
        dpersonal = JSON.stringify(dpersonal);
        console.log(dpersonal);

        var card = '<div class="card">'
                + '<div class="card-header" id="headingOne">'
                + '  <h5 class="mb-0">'
                + '   <button class="btn btn-link" data-toggle="collapse" data-target="#tarjeta" aria-expanded="true" aria-controls="collapseOne" onclick=cargarformularios(cveModulo)>'
                + '    TITULO'
                + ' </button>'
                + '</h5>'
                + '</div>'
                + '	    <div id="tarjeta" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">'
                + '	      <div class="card-body modulos" id="formscveModulo">'
                + '	       MODULO' + '	      </div>' + '	    </div>'
                + '	  </div>';

        $("#accordion").empty();
        var tempcard = "";
        $.ajax({
            data: dpersonal,
            async: false,
            url: 'permisosusuario',
            dataType: 'json',
            contentType: "application/json",
            type: 'post',
            beforeSend: function () {

            },
            success: function (response) {
                $("#modalCorrect").empty();
                $("#myModalCorrect").modal("hide");
                $("#myModalEliminar").modal('hide');
                //$("#accordion").append(card + card);
                console.log("opciones del modulo: " + response.length);
                if (response.length > 0) {
                    $.each(response, function (index, element) {
                        var re = new RegExp("tarjeta", 'g');
                        tempcard += card.replace("TITULO", element.descModulo)
                                //.replace(new RegExp("One", 'g'), index)
                                .replace("cveModulo", element.cveModulo)
                                .replace("formscveModulo",
                                        "forms" + element.cveModulo).replace(re, "tarjeta" + element.cveModulo);

                    });
                    $("#accordion").append(tempcard);

                } else {
                    error("El usuario no cuenta con ningun permiso");

                }

            },
            error: function (xhr, ajaxOptions, thrownError) {
                error("Ocurrio un error");

            }
        });

    }; 
    limpiar = function () {
        $("#accordion").empty();
        $("#txtnombre").val("");
        $("#txtCveServidor").val("");
        $("#hddCveUsuario").val(0);

    }
    error = function (mensaje) {
        $("#myModalEliminar").modal('hide');
        $("#modalErrorText").empty();
        $("#modalErrorText").append(mensaje);
        $("#myModalError").modal();

    };
    cargarformularios = function (cveModulo) {
        var cveUsuario = $("#hddCveUsuario").val();
        var dpersonal = new NPersonalC(cveUsuario, null, null, cveModulo);
        dpersonal = JSON.stringify(dpersonal);
        console.log(dpersonal);

        $(".modulos").empty();
        $
                .ajax({
                    data: dpersonal,
                    async: false,
                    url: 'listaformularios',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {

                    },
                    success: function (response) {
                        $("#modalCorrect").empty();
                        $("#myModalCorrect").modal("hide");
                        $("#myModalEliminar").modal('hide');
                        //$("#accordion").append(card + card);
                        var tabla = '<table class="table table-bordered table-hover dataTable no-footer" id="dataTables-example" role="grid" aria-describedby="dataTables-example_info">'
                                + '<thead>'
                                + '<tr class="active" role="row"><th class="col-sm-1 sorting_asc" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="#: activate to sort column descending" style="width: 8.33333px;">#</th><th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-label="Name: activate to sort column ascending" style="width: 0px;">Name</th><th class="sorting" tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1" aria-label="Action: activate to sort column ascending" style="width: 0px;">Action</th></tr>'
                                + '</thead>'
                                + '<tbody>'
                                + '        renglones</tbody>' + '	</table>';

                        var renglon = '<tr role="row" class="odd">'
                                + '        <td class="sorting_1">1</td>'
                                + '        <td>Admin</td>'
                                + '        <td>'
                                + '            <a href="#" class="btn btn-xs btn-info clase1" title="" data-toggle="tooltip" data-placement="top" data-original-title="Show Detail" onclick1 ><i class="fa fa-list-alt"></i></a>'
                                + '            <a href="#" class="btn btn-primary btn-xs clase2" title="" data-toggle="tooltip" data-placement="top" data-original-title="Edit" onclick2 ><i class="fa fa-pencil-square-o"></i></a>'
                                + '            <a href="#" class="btn btn-xs btn-danger clase3" title="" data-toggle="tooltip" data-placement="top" data-original-title="Delete" onclick3 ><i class="fa fa-trash-o"></i></a>'
                                + '        </td>' + '    </tr>';
                        var temprow = "";
                        var temprenglon = "";
                        if (response.length > 0) {
                            $
                                    .each(
                                            response,
                                            function (index, element) {

                                                temprenglon = renglon;


                                                if (element.crear === 'S') {
                                                    temprenglon = temprenglon
                                                            .replace(
                                                                    "onclick1",
                                                                    "onclick=permisos('C',"
                                                                    + element.cveFormulario
                                                                    + ",'N',"
                                                                    + cveModulo
                                                                    + ")");

                                                } else {
                                                    temprenglon = temprenglon
                                                            .replace(
                                                                    "btn-primary",
                                                                    " ");
                                                    temprenglon = temprenglon
                                                            .replace(
                                                                    "onclick1",
                                                                    "onclick=permisos('C',"
                                                                    + element.cveFormulario
                                                                    + ",'S',"
                                                                    + cveModulo
                                                                    + ")");

                                                }
                                                if (element.borrar === 'S') {
                                                    temprenglon = temprenglon
                                                            .replace(
                                                                    "onclick2",
                                                                    "onclick=permisos('E',"
                                                                    + element.cveFormulario
                                                                    + ",'N',"
                                                                    + cveModulo
                                                                    + ")");

                                                } else {
                                                    temprenglon = temprenglon
                                                            .replace(
                                                                    "btn-danger",
                                                                    " ");
                                                    temprenglon = temprenglon
                                                            .replace(
                                                                    "onclick2",
                                                                    "onclick=permisos('E',"
                                                                    + element.cveFormulario
                                                                    + ",'S',"
                                                                    + cveModulo
                                                                    + ")");
                                                }
                                                if (element.buscar === 'S') {
                                                    temprenglon = temprenglon
                                                            .replace(
                                                                    "onclick3",
                                                                    "onclick=permisos('B',"
                                                                    + element.cveFormulario
                                                                    + ",'N',"
                                                                    + cveModulo
                                                                    + ")");

                                                } else {
                                                    temprenglon = temprenglon
                                                            .replace(
                                                                    "btn-info",
                                                                    " ");
                                                    temprenglon = temprenglon
                                                            .replace(
                                                                    "onclick3",
                                                                    "onclick=permisos('B',"
                                                                    + element.cveFormulario
                                                                    + ",'S',"
                                                                    + cveModulo
                                                                    + ")");

                                                }


                                                temprow += temprenglon.replace(
                                                        "Admin",
                                                        element.descFormulario);

                                            });
                            tabla = tabla.replace("renglones", temprow);
                            $("#forms" + cveModulo).append(tabla);

                        } else {
                            error("El usuario no cuenta con ningun permiso");

                        }

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        error("Ocurrio un error");

                    }
                });

        var formulario = function (cveFormulario, ruta) {
            this.cveFormulario = cveFormulario;
            this.rutaFormulario = ruta;
        }

        permisos = function (operacion, cveFormulario, permiso, cveModulo) {
            var cveUsuario = $("#hddCveUsuario").val();
            console.log("clave del formulario= " + cveFormulario + " permiso= "
                    + permiso + " Operacion= " + operacion);
            //BORRAR
            if (operacion === "B") {

                operacion = "permisoborrar";
            }
            // CONSULTA
            else if (operacion === "C") {
                operacion = "permisoconsultar";
            }
            //EDITAR

            else if (operacion === "E") {
                operacion = "permisoeditar";
            }

            var dFormulario = new formulario(cveFormulario, cveUsuario);
            //console.log(dFormulario.cveFormulario);
            dFormulario = JSON.stringify(dFormulario);
            console.log(dFormulario);

            $.ajax({
                data: dFormulario,
                async: false,
                url: operacion,
                dataType: 'json',
                contentType: "application/json",
                type: 'post',
                beforeSend: function () {

                },
                success: function (response) {
                    $("#modalCorrect").empty();
                    $("#myModalCorrect").modal("hide");
                    $("#myModalEliminar").modal('hide');
                    //$("#accordion").append(card + card);

                    cargarformularios(cveModulo);





                },
                error: function (xhr, ajaxOptions, thrownError) {
                    error("Ocurrio un error");

                }
            });

        };

    };
</script>

