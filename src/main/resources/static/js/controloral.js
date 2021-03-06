cambiarPasos = function (e, required) {
    console.log("form-register cambiar pasos");
    let formu = document.querySelector('.form-register');
    let progressOptions = document.querySelectorAll('.progressbar-option');

    console.log("click");
    let element = e;
    let isButtonNext = element.classList.contains('step-button-next');
    let isButtonBack = element.classList.contains('step-button-back');
    console.log(element, "isButtonNext" + isButtonNext + " isButtonBack" + isButtonBack);
    if ((isButtonNext || isButtonBack)) {

        let currentStep = document.getElementById("step-" + element.dataset.step);
        let jumpStep = document.getElementById("step-" + element.dataset.to_step);
        console.log("currentStep" + currentStep.id + " jumpStep" + jumpStep);
        currentStep.addEventListener('animationend', function callback() {
            console.log("Callback function");
            currentStep.classList.remove('active');
            jumpStep.classList.add('active');
            if (isButtonNext & required) {
                console.log("Siguiente paso");

                currentStep.classList.add('to-left');
                progressOptions[element.dataset.to_step - 1].classList.add('active');
            } else {
                jumpStep.classList.remove('to-left');
                progressOptions[element.dataset.step - 1].classList.remove('active');
            }
            currentStep.removeEventListener('animationend', callback);
        });
        currentStep.classList.add('inactive');
        jumpStep.classList.remove('inactive');
    }



}

if (typeof pestana === 'undefined') {
    let pestana = document.querySelector(".icon-bar");
    pestana.addEventListener('click', function (e) {
        console.log("eleemento" + e.target.tagName);
        cambiarPestanas(e);



    });

    cambiarPestanas = function (e) {
        let element = null;
        let barra = false;
        let barra2 = false;
        let barra3 = false;
        if (e == null) {
            barra2 = true;
        } else {
            element = e.target;
            barra = element.classList.contains("barra1");
            barra2 = element.classList.contains("barra2");
            barra3 = element.classList.contains("barra3");

        }


        let pestanas = document.querySelectorAll(".barra");
        console.log("numero de elementos" + pestanas.length);
        [].forEach.call(pestanas, function (el) {
            el.classList.remove("active");
        });

        let carpetas = document.querySelectorAll(".carpeta");
        console.log("numero de elementos" + carpetas.length);
        [].forEach.call(carpetas, function (el) {
            el.classList.remove("active");
            el.classList.add("inactive");
        });



        if (barra) {
            let elemento = document.getElementById("barra1");
            elemento.classList.add("active");
            let pes = document.querySelector(".busquedacarpeta");
            pes.classList.add("active");
            pes.style.marginLeft = '0%';
            pes.classList.remove("inactive");

        } else if (barra2) {
            let elemento = document.getElementById("barra2");
            elemento.classList.add("active");
            let pes = document.querySelector(".registracarpeta");
            pes.classList.add("active");
            pes.style.marginLeft = '-100%';
            pes.classList.remove("inactive");

        } else if (barra3) {

            let elemento = document.getElementById("barra3");
            elemento.classList.add("active");
            let pes = document.querySelector(".audienciassigedepu");
            pes.classList.add("active");
            if (pes.style.marginLeft === "-100%")
                pes.style.marginLeft = '-200%';
            else
                pes.style.marginLeft = '-100%';
            pes.classList.remove("inactive");

            buscarDefensasSigedepu();

        }
    };
}

var listaDelitos = [];
var listaMedidas = [];
var listaGruposVulnerables = [];



var autoCompleteMedidas = {

};

buscarDefensasSigedepu = function () {

    $.ajax({
        url: "buscardefensassigedepu",
        type: 'POST',
        // data: JSON.stringify(carpeta),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {
            $("#listaAudienciasSigedepu").empty();
            $("#listaAudienciasSigedepu").hide();


            if (response.length > 0) {
                var table = "<table class=\"table table-striped\">" +
                        "<thead>" +
                        " <tr>" +
                        "  <th scope=\"col\">#</th>" +
                        " <th scope=\"col\">Carpeta</th>" +
                        " <th scope=\"col\">Adscripci&oacute;n</th>" +
                        "<th scope=\"col\">NUC</th>" +
                        "<th scope=\"col\">Defensor</th>" +
                        "<th scope=\"col\">&nbsp;</th>" +
                        " </tr>" +
                        "</thead><tbody>";

                $.each(response, function (index, element) {


                    var button = "<input type=\"button\" class=\"btn btn-primary\" onclick=\"actualizarCarpeta(" + element.id + ");\" value='Actualizar'>";
                    var nombre = element.solicitud.peticionario.nombre + " " + element.solicitud.peticionario.paterno + " " + element.solicitud.peticionario.materno;
                    table += "<tr>" +
                            "<th scope=\"row\">" + (index + 1) + "</th>" +
                            "<td>" + nombre + "</td>" +
                            "<td>" + element.solicitud.juzgado + "</td>" +
                            "<td>" + element.solicitud.nuc + "</td>" +
                            "<td>" + cambiarFecha2(element.fechaPresentacion) + "</td>" +
                            "<td>" + button + "</td>" +
                            "</tr>";
                });
                table += " </tbody>" +
                        "</table>";
                $("#listaAudienciasSigedepu").show();
                $("#listaAudienciasSigedepu").append(table);
            }


        }, error: function (xhr, ajaxOptions, thrownError) {
            bootbox.dialog({
                closeButton: true,
                message: "Error al buscar la carpeta",
                title: "Error",
                buttons: {
                    success: {
                        label: "Aceptar",
                        className: "btn-danger"
                    }
                }
            });
        }
    });

};

listarDelitos = function () {
    console.log("lista delito" + listaDelitos);
    var lista = "<ul>";
    $("#listDelitos").empty();
    $.each(listaDelitos, function (index, element) {
        if (element.activo == 'S')
            lista += "<li>" + element.descDelito + "<a onclick='eliminarDelito(" + element.cveDelito + ")'>X</a></li>";

    });
    lista += "</ul>";
    $("#listDelitos").append(lista);
};

listarMedidas = function () {
    console.log("lista medidas" + listaMedidas);
    var lista = "<ul>";
    $(".RepAudiencias").empty();
    $.each(listaMedidas, function (index, element) {
        if (element.activo == 'S') {
            lista = "<li>" + element.descMedidaCautelar + "<a onclick='eliminarMedida(" + element.cveMedidaCautelar + ")'>X</a></li>";
            $("#divRepresentadosAud" + element.idRepresentado).append(lista);
        }

    });
    lista += "</ul>";
    $("#listMedidas").append(lista);
};

listarGruposVulnerables = function () {
    console.log("lista delito" + listaGruposVulnerables);
    var lista = "<ul>";
    $("#listGruposVulnerables").empty();
    $.each(listaGruposVulnerables, function (index, element) {
        if (element.activo == 'S')
            lista += "<li>" + element.descGrupoVulnerable + "<a onclick='eliminarGrupo(" + element.cveGrupoVulnerable + ")'>X</a></li>";

    });
    lista += "</ul>";
    $("#listGruposVulnerables").append(lista);
};
eliminarGrupo = function (cveGrupoVulnerable) {
    console.log("eliminar grupo");

    $.each(listaGruposVulnerables, function (index, element) {
        if (element.cveGrupoVulnerable === cveGrupoVulnerable && element.activo === "S") {
            console.log("antes: " + listaGruposVulnerables[index].activo);
            listaGruposVulnerables[index].activo = 'N';
            console.log("despues: " + listaGruposVulnerables[index].activo);
            return false;
        }

    });
    listarGruposVulnerables();

};
eliminarDelito = function (cveDelito) {
    console.log("eliminar delito");

    $.each(listaDelitos, function (index, element) {
        if (element.cveDelito === cveDelito && element.activo === "S") {
            console.log("antes: " + listaDelitos[index].activo);
            listaDelitos[index].activo = 'N';
            console.log("despues: " + listaDelitos[index].activo);
            return false;
        }

    });
    listarDelitos();

};

eliminarMedida = function (cveMedidaCautelar) {
    console.log("eliminar Medida");

    $.each(listaMedidas, function (index, element) {
        console.log("cveMedidaCautelar" + element.cveMedidaCautelar + " estatus" + element.activo + " cveMedida Recibida" + cveMedidaCautelar);
        if (element.cveMedidaCautelar == cveMedidaCautelar && element.activo === "S") {
            console.log("antes: " + listaMedidas[index].activo);
            listaMedidas[index].activo = 'N';
            console.log("despues: " + listaMedidas[index].activo);
            return false;
        }

    });
    listarMedidas();

};


agregarDelitos = function (delito) {
    console.log("agregar delito" + listaDelitos);
    var encontrado = false;
    var tama??o = listaDelitos.length;
    if (listaDelitos.length > 0) {
        $.each(listaDelitos, function (index, element) {
            if (element.cveDelito === delito.cveDelito) {
                if (element.activo === 'N')
                    listaDelitos[index].activo = "S";
                encontrado = true;
                return;
            }

        });
    }
    if (!encontrado) {
        listaDelitos.push(delito);
        listarDelitos();
    }
    if (tama??o !== listaDelitos.length && tama??o > 0) {
        listarDelitos();
    }


};

agregarMedidas = function (medida) {
    console.log("agregar medida representado" + medida.idRepresentado);
    var encontrado = false;
    var tama??o = listaMedidas.length;
    if (listaMedidas.length > 0) {
        $.each(listaMedidas, function (index, element) {
            if (element.cveMedidaCautelar === medida.cveMedidaCautelar && element.idRepresentado === medida.idRepresentado) {
                if (element.activo === 'N') {
                    listaMedidas[index].activo = "S";
                    encontrado = false;
                } else
                    encontrado = true;
                return;
            }

        });
    }
    if (!encontrado) {
        listaMedidas.push(medida);
        listarMedidas();
    }
    if (tama??o !== listaMedidas.length && tama??o > 0) {
        listarMedidas();
    }


};

agregarGrupoVulnerable = function (grupoVulnerable) {
    console.log("agregar grupo" + listaGruposVulnerables);
    var encontrado = false;
    var tama??o = listaGruposVulnerables.length;
    if (listaGruposVulnerables.length > 0) {
        $.each(listaGruposVulnerables, function (index, element) {
            if (element.cveDelito === grupoVulnerable.cveGrupoVulnerable) {
                if (element.activo === 'N')
                    listaGruposVulnerables[index].activo = "S";
                encontrado = true;
                return;
            }

        });
    }
    if (!encontrado) {
        listaGruposVulnerables.push(grupoVulnerable);
        listarGruposVulnerables();
    }
    if (tama??o !== listaGruposVulnerables.length && tama??o > 0) {
        listarGruposVulnerables();
    }


};

nuevoRepresentado = function () {
    $("h1").unbind();
    $("#btnRegresarRepr").hide();
    $("#btnSiguienteRepr").hide();
    $("#bt-guardar-representado").show();
    $("#bt-cancelar-representado").show();
    $("#txtNombreImputado").prop("disabled", false);
    $("#txtPaternoImputado").prop("disabled", false);
    $("#txtMaternoImputado").prop("disabled", false);
    $("#txtFechaNacimientoImputado").prop("disabled", false);
    $("#txtEdadImputado").prop("disabled", false);
    $("#txtMunicipio").prop("disabled", false);
    $("#txtEtnia").prop("disabled", false);
    $("#txtDispacacidad").prop("disabled", false);
    $("#txtGrupoVulnerable").prop("disabled", false);
    $("#txtDelito").prop("disabled", false);
};

cancelarRepresentado = function () {

    listaDelitos = [];
    listaGruposVulnerables = [];
    $("#btnRegresarRepr").show();
    $("#btnSiguienteRepr").show();
    $("#bt-guardar-representado").hide();
    $("#bt-cancelar-representado").hide();
    $("#txtNombreImputado").val("");
    $("#txtPaternoImputado").val("");
    $("#txtMaternoImputado").val("");
    $("#txtFechaNacimientoImputado").val("");
    $("#txtEdadImputado").val("");
    $("#txtMunicipio").val("");
    $("#txtEtnia").val("");
    $("#txtDispacacidad").val("");
    $("#txtGrupoVulnerable").val("");
    $("#txtDelito").val("");
    $("#hddMunicipio").val(0);
    $("#hddCveEtnia").val(0);
    $("#hddIdRepresentado").val(0);
    $("#hddIdCarpetaRepresentado").val(0);
    $("#listGruposVulnerables").empty();
    $("#listDelitos").empty();

    $("#txtNombreImputado").prop("disabled", true);
    $("#txtPaternoImputado").prop("disabled", true);
    $("#txtMaternoImputado").prop("disabled", true);
    $("#txtFechaNacimientoImputado").prop("disabled", true);
    $("#txtEdadImputado").prop("disabled", true);
    $("#txtMunicipio").prop("disabled", true);
    $("#txtEtnia").prop("disabled", true);
    $("#txtDispacacidad").prop("disabled", true);
    $("#txtGrupoVulnerable").prop("disabled", true);
    $("#txtDelito").prop("disabled", true);





};

buscarCarpetas = function () {
    console.log("buscar carpetas valor " + $("#hddJuzgadoConsulta").val());
    var numeroExp = $("#txtNumeroExpConsulta").val() === "" ? null : $("#txtNumeroExpConsulta").val();
    var anioExp = $("#txtAnioExpConsulta").val() === "" ? null : $("#txtAnioExpConsulta").val();
    var juzgado = $("#hddJuzgadoConsulta").val() == 0 ? null : $("#hddJuzgadoConsulta").val();
    var idTipoCarpeta = $("#hddTipoCarpetaConsulta").val() == "0" ? null : $("#hddTipoCarpetaConsulta").val();
    var nuc = $("#txtNucConsulta").val() === "" ? null : $("#txtNucConsulta").val();

    var carpeta = new Object();
    var ads = new Object();
    if (juzgado > 0) {
        ads.cveAdscripcion = juzgado;
        carpeta.adscripcion = ads;
    } else {
        //carpeta.adscripcion = null;
    }
    carpeta.numero = numeroExp;
    carpeta.anio = anioExp;

    var tipoCarpeta = new Object();
    tipoCarpeta.idTipoCarpeta = idTipoCarpeta;

    carpeta.tipoCarpeta = tipoCarpeta;
    carpeta.nuc = nuc;

    var datos = numeroExp + "/" + anioExp + "<br>" + $("#txtJuzgadoConsulta").val() + "<br>" + $("#txtTipoCarpetaConsulta").val() + "<br>" + $("#txtNucConsulta").val();
    if (validarSesion()) {
        $.ajax({
            url: "consultarcarpetas",
            type: 'POST',
            data: JSON.stringify(carpeta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                $("#listaRepresentados").empty();
                $("#listaRepresentados").hide();

                $("#listaCarpetasConsulta").empty();
                if (response.length > 0) {
                    var table = "<table class=\"table table-striped\">" +
                            "<thead>" +
                            " <tr>" +
                            "  <th scope=\"col\">#</th>" +
                            " <th scope=\"col\">Carpeta</th>" +
                            " <th scope=\"col\">Adscripci&oacute;n</th>" +
                            "<th scope=\"col\">NUC</th>" +
                            "<th scope=\"col\">Defensor</th>" +
                            "<th scope=\"col\">&nbsp;</th>" +
                            " </tr>" +
                            "</thead><tbody>";

                    $.each(response, function (index, element) {


                        var button = "<input type=\"button\" class=\"btn btn-primary\" onclick=\"actualizarCarpeta(" + element.idCarpeta + ");\" value='Actualizar'>";

                        table += "<tr>" +
                                "<th scope=\"row\">" + (index + 1) + "</th>" +
                                "<td>" + element.numero + "/" + element.anio + "</td>" +
                                "<td>" + element.adscripcion.descAdscripcion + "</td>" +
                                "<td>" + (element.nuc == null ? "" : element.nuc) + "</td>" +
                                "<td>" + element.personal.nombre + " " + element.personal.paterno + "" + element.personal.materno + "</td>" +
                                "<td>" + button + "</td>" +
                                "</tr>";
                    });
                    table += " </tbody>" +
                            "</table>";
                    $("#listaCarpetasConsulta").show();
                    $("#listaCarpetasConsulta").append(table);
                } else {

                    bootbox.confirm({
                        message: "No Se encontro ninguna coincidencia con los siguientes datos <br><b>" + datos + "</b><br> ??Desea registrar la Carpeta?",
                        buttons: {
                            confirm: {
                                label: 'Yes',
                                className: 'btn-success'
                            },
                            cancel: {
                                label: 'No',
                                className: 'btn-danger'
                            }
                        },
                        callback: function (result) {
                            if (result) {
                                var expediente = new Object();
                                expediente.carpeta = carpeta;
                                expediente.txtJuzgado = $("#txtJuzgadoConsulta").val();
                                expediente.txtTipoCarpeta = $("#txtTipoCarpetaConsulta").val();
                                cargarDatosCarpeta(expediente);

                            }
                        }
                    });

                }


            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al buscar la carpeta",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });
    }
};

$(function () {

    $(".datepicker").datepicker({
        dateFormat: 'dd/mm/yy'
    });

});

cargarDatosCarpeta = function (expediente) {
    cambiarPestanas();
    console.log(expediente);
    $("#txtNumeroExpRegistro").val(expediente.carpeta.numero);
    $("#txtAnioExpRegistro").val(expediente.carpeta.anio);
    $("#txtJuzgadoConsultaRegistro").val(expediente.txtJuzgado);
    $("#txtTipoCarpetaRegistro").val(expediente.txtTipoCarpeta);
    $("#txtNucRegistro").val(expediente.carpeta.nuc);
    $("#hddJuzgadoRegistro").val(expediente.carpeta.adscripcion.cveAdscripcion);
    $("#hddTipoCarpetaRegistro").val(expediente.carpeta.tipoCarpeta.idTipoCarpeta);
    if (expediente.carpeta.idCarpeta !== null)
        $("#hddIdCarpeta").val(expediente.carpeta.idCarpeta);

};



$("#txtJuzgadoConsulta")
        .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtJuzgadoConsulta").val() == "" ? null
                                : "%" + $("#txtJuzgadoConsulta").val()
                                + "%";

                        var participante = new Object();
                        participante.descAdscripcion = nombre;
                        participante.cveAdscripcion = 1;



                        $
                                .ajax({
                                    data: JSON.stringify(participante),
                                    url: 'buscaradscripciones2',
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
                                                                label: item.adscripcion.descAdscripcion,
                                                                value: item.adscripcion.cveAdscripcion
                                                            }
                                                        }));
                                    },
                                    error: function (xhr, ajaxOptions,
                                            thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                                    }
                                });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                                + ui.item.label
                                : "Nothing selected, input was "
                                + this.value);
                        $("#txtJuzgadoConsulta").val(ui.item.label);
                        $("#hddJuzgadoConsulta").val(ui.item.value);

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

$("#txtJuzgadoConsultaRegistro")
        .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtJuzgadoConsultaRegistro").val() == "" ? null
                                : "%" + $("#txtJuzgadoConsultaRegistro").val()
                                + "%";
                        
                        console.log("texto "+nombre);
                        

                        var participante = new Object();
                        participante.descAdscripcion = nombre;
                        participante.cveAdscripcion = 1;


                        $
                                .ajax({
                                    data: JSON.stringify(participante),
                                    url: 'buscaradscripciones2',
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
                                                                label: item.adscripcion.descAdscripcion,
                                                                value: item.adscripcion.cveAdscripcion
                                                            }
                                                        }));
                                    },
                                    error: function (xhr, ajaxOptions,
                                            thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                                    }
                                });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                                + ui.item.label
                                : "Nothing selected, input was "
                                + this.value);
                        $("#txtJuzgadoConsultaRegistro").val(ui.item.label);
                        $("#hddJuzgadoRegistro").val(ui.item.value);

                    },
                    open: function () {
                        $(this).removeClass("ui-corner-all").addClass(
                                "ui-corner-top");
                    },
                    close: function () {
                        $(this).removeClass("ui-corner-top").addClass(
                                "ui-corner-all");
                        var nombre = $("#txtJuzgadoConsultaRegistro").val();
                        console.log("nombre " + nombre);
                        if (nombre == "")
                            $("#hddJuzgadoRegistro").val(0);
                    },
                    change:function(e,ui){
                        
                    }
                });



$("#txtTipoCarpetaConsulta")
        .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtTipoCarpetaConsulta").val() == "" ? null
                                : "%" + $("#txtTipoCarpetaConsulta").val()
                                + "%";

                        var participante = new Object();
                        participante.descTipoCarpeta = nombre;


                        $
                                .ajax({
                                    data: JSON.stringify(participante),
                                    url: 'listatipocarpeta',
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
                                                                label: item.descTipoCarpeta,
                                                                value: item.idTipoCarpeta
                                                            }
                                                        }));
                                    },
                                    error: function (xhr, ajaxOptions,
                                            thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                                    }
                                });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                                + ui.item.label
                                : "Nothing selected, input was "
                                + this.value);
                        $("#txtTipoCarpetaConsulta").val(ui.item.label);
                        $("#hddTipoCarpetaConsulta").val(ui.item.value);

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

$("#txtMunicipio")
        .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtMunicipio").val() == "" ? null
                                : "%" + $("#txtMunicipio").val()
                                + "%";

                        var participante = new Object();
                        participante.descMunicipio = nombre;


                        $
                                .ajax({
                                    data: JSON.stringify(participante),
                                    url: 'consultarmunicipios',
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
                                                                label: item.descMunicipio+", "+item.estado.descEstado,
                                                                value: item.cveMunicipio
                                                            }
                                                        }));
                                    },
                                    error: function (xhr, ajaxOptions,
                                            thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                                    }
                                });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                                + ui.item.label
                                : "Nothing selected, input was "
                                + this.value);
                        $("#txtMunicipio").val(ui.item.label);
                        $("#hddCveMunicipio").val(ui.item.value);

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

$("#txtTipoCarpetaRegistro")
        .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtTipoCarpetaRegistro").val() == "" ? null
                                : "%" + $("#txtTipoCarpetaRegistro").val()
                                + "%";

                        var participante = new Object();
                        participante.descTipoCarpeta = nombre;


                        $
                                .ajax({
                                    data: JSON.stringify(participante),
                                    url: 'listatipocarpeta',
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
                                                                label: item.descTipoCarpeta,
                                                                value: item.idTipoCarpeta
                                                            }
                                                        }));
                                    },
                                    error: function (xhr, ajaxOptions,
                                            thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                                    }
                                });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                                + ui.item.label
                                : "Nothing selected, input was "
                                + this.value);
                        $("#txtTipoCarpetaRegistro").val(ui.item.label);
                        $("#hddTipoCarpetaRegistro").val(ui.item.value);

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

guardarCarpeta = function (e) {
    var required = false;
    var numeroExp = $("#txtNumeroExpRegistro").val() === "" ? null : $("#txtNumeroExpRegistro").val();
    var anioExp = $("#txtAnioExpRegistro").val() === "" ? null : $("#txtAnioExpRegistro").val();
    var juzgado = $("#hddJuzgadoRegistro").val() == 0 ? null : $("#hddJuzgadoRegistro").val();
    var idTipoCarpeta = $("#hddTipoCarpetaRegistro").val() == "0" ? null : $("#hddTipoCarpetaRegistro").val();
    var nuc = $("#txtNucRegistro").val() === "" ? null : $("#txtNucRegistro").val();
    var idCarpeta = $("#hddIdCarpeta").val() == 0 ? null : $("#hddIdCarpeta").val();

    var carpeta = new Object();
    var ads = new Object();
    var tiposCarpeta = new Object();

    ads.cveAdscripcion = juzgado;
    carpeta.adscripcion = ads;
    carpeta.idCarpeta = idCarpeta;

    carpeta.numero = numeroExp;
    carpeta.anio = anioExp;


    carpeta.nuc = nuc;
    tiposCarpeta.idTipoCarpeta = idTipoCarpeta;
    carpeta.tipoCarpeta = tiposCarpeta;
    var buscar = false;
    if (juzgado > 0 && numeroExp !== null && anioExp !== null) {
        buscar = true;
        required = true;
    }
    if (validarSesion() && required)
        $.ajax({
            url: "guardarcarpetamanual",
            type: 'POST',
            data: JSON.stringify(carpeta),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                cambiarPasos(e, required);
                $("#hddIdCarpeta").val(response.idCarpeta);
                consultarImputados();
                bootbox.dialog({
                    closeButton: true,
                    message: "Se guardo la carpeta",
                    title: "Guardado",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-success"
                        }
                    }
                });

            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al buscar la carpeta",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });
    else {
        bootbox.dialog({
            closeButton: true,
            message: "Debe ingresar numero de carpeta y juzgado para continuar",
            title: "Error",
            buttons: {
                success: {
                    label: "Aceptar",
                    className: "btn-danger"
                }
            }
        });
    }


};

consultarImputados = function () {
    var idCarpeta = $("#hddIdCarpeta").val();

    var carpeta = new Object();
    carpeta.idCarpeta = idCarpeta;
    var carpetaRepresentado = new Object();
    carpetaRepresentado.carpeta = carpeta;

    $.ajax({
        url: "buscarrepresentados",
        type: 'POST',
        data: JSON.stringify(carpetaRepresentado),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {
            cancelarRepresentado();
            $("#listaAudienciasConsulta").empty();
            $("#listaAudienciasConsulta").hide();
            $("#listaRepresentados").empty();
            var table = "<table id ='tblimputados' class=\"table table-striped\">" +
                    "<thead>" +
                    " <tr>" +
                    "  <th scope=\"col\">#</th>" +
                    " <th scope=\"col\">Nombre del Representado</th>" +
                    " <th scope=\"col\">Edad&oacute;n</th>" +
                    "<th scope=\"col\">NUC</th>" +
                    "<th scope=\"col\"><input type='button' class='step-button' onclick='nuevoRepresentado()' value='Nuevo Imputado'></th>" +
                    "<th scope=\"col\">&nbsp;</th>" +
                    " </tr>" +
                    "</thead><tbody>";

            $.each(response, function (index, element) {


                var button = "<input type=\"button\" class=\"btn btn-primary\" onclick=\"cargarRepresentado(" + element.representado.idRepresentado + ");\" value='Actualizar'>";
                var nombre = element.representado.nombre + " " + element.representado.paterno + " " + element.representado.materno + " <br>Edad:" + element.representado.edad;
                table += "<tr>" +
                        "<th scope=\"row\">" + (index + 1) + "</th>" +
                        "<td colspan='4'>" + nombre.toUpperCase() + "</td>" +
                        "<td>" + button + "</td>" +
                        "</tr>";
            });
            table += " </tbody>" +
                    "</table>";
            $("#listaRepresentados").show();
            $("#listaRepresentados").append(table);

        }, error: function (xhr, ajaxOptions, thrownError) {
            bootbox.dialog({
                closeButton: true,
                message: "Error al buscar la carpeta",
                title: "Error",
                buttons: {
                    success: {
                        label: "Aceptar",
                        className: "btn-danger"
                    }
                }
            });
        }
    });


};

actualizarCarpeta = function (idCarpeta) {
    var carpeta = new Object();
    carpeta.idCarpeta = idCarpeta;

    $.ajax({
        url: "consultarcarpetas",
        type: 'POST',
        data: JSON.stringify(carpeta),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {

            $.each(response, function (index, element) {
                var expediente = new Object();
                var carpeta = new Object();
                var adscripcion = new Object();
                carpeta.numero = element.numero;
                carpeta.anio = element.anio;
                expediente.txtJuzgado = element.adscripcion.descAdscripcion;
                adscripcion.cveAdscripcion = element.adscripcion.cveAdscripcion;
                carpeta.adscripcion = adscripcion;
                expediente.txtTipoCarpeta = element.tipoCarpeta.descTipoCarpeta;
                carpeta.tipoCarpeta = element.tipoCarpeta;
                carpeta.nuc = element.nuc;
                carpeta.idCarpeta = element.idCarpeta;
                expediente.carpeta = carpeta;
                cargarDatosCarpeta(expediente);




            });

        }, error: function (xhr, ajaxOptions, thrownError) {
            bootbox.dialog({
                closeButton: true,
                message: "Error al buscar la carpeta",
                title: "Error",
                buttons: {
                    success: {
                        label: "Aceptar",
                        className: "btn-danger"
                    }
                }
            });
        }
    });

};

cargarRepresentado = function (idRepresentado) {
    var Representado = new Object();
    Representado.idRepresentado = idRepresentado;
    var CarpetaRepresentado = new Object();
    CarpetaRepresentado.representado = Representado;
    $.ajax({
        url: "buscarrepresentados",
        type: 'POST',
        data: JSON.stringify(CarpetaRepresentado),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {
            nuevoRepresentado();
            var descMunicipio = "";
            var cveMunicipio = 0;
            var descEtnia = "";
            var cveEtnia = 0;
            $.each(response, function (index, element) {
                descMunicipio = "";
                cveMunicipio = 0;
                descEtnia = "";
                cveEtnia = 0;
                $("#hddIdCarpetaRepresentado").val(element.idCarpetaRepresentado);
                var r = element.representado;
                if (element.representado.municipio !== null) {
                    descMunicipio = element.representado.municipio.descMunicipio;
                    cveMunicipio = element.representado.municipio.idMunicipio;

                }
                if (element.representado.etnia !== null) {
                    descEtnia = element.representado.etnia.descEtnia;
                    cveEtnia = element.representado.etnia.cveEtnia;

                }
                $("#hddIdRepresentado").val(r.idRepresentado);
                $("#txtNombreImputado").val(r.nombre);
                $("#txtPaternoImputado").val(r.paterno);
                $("#txtMaternoImputado").val(r.materno);
                $("#txtFechaNacimientoImputado").val(cambiarFecha2(r.fechaNacimiento));
                $("#txtEdadImputado").val((r.edad));

                $("#txtMunicipio").val(descMunicipio);
                $("#hddCveMunicipio").val(cveMunicipio);
                $("#txtEtnia").val(descEtnia);
                $("#hddCveEtnia").val(cveEtnia);
                $("#txtDispacacidad").val(r.descDiscapacidad);
                $("#cmdSexo").val(r.cveSexo);
                listaDelitos = [];
                $("#listDelitos").empty();
                if (r.delitosRepresentados.length > 0) {

                    $.each(r.delitosRepresentados, function (index, element) {
                        var delito = new Object();
                        delito.idDelitoRepresentado = element.idDelitoRepresentado;
                        delito.activo = element.activo;
                        delito.cveDelito = element.delito.cveDelito;
                        delito.descDelito = element.delito.descDelito;
                        listaDelitos.push(delito);


                    });
                    listarDelitos();
                }
                listaGruposVulnerables = [];
                $("#listGruposVulnerables").empty();
                if (r.gruposRepresentados.length > 0) {

                    $.each(r.gruposRepresentados, function (index, element) {
                        var grupoVulnerable = new Object();
                        grupoVulnerable.idRepresentadoGrupoVulnerable = element.idRepresentadoGrupoVulnerable;
                        grupoVulnerable.activo = element.activo;
                        grupoVulnerable.cveGrupoVulnerable = element.grupoVulnerable.cveGrupoVulnerable;
                        grupoVulnerable.descGrupoVulnerable = element.grupoVulnerable.descGrupoVulnerable;
                        listaGruposVulnerables.push(grupoVulnerable);


                    });
                    listarGruposVulnerables();
                }
                $("#bt-guardar-representado").show();

            });

        }, error: function (xhr, ajaxOptions, thrownError) {
            bootbox.dialog({
                closeButton: true,
                message: "Error al buscar la carpeta",
                title: "Error",
                buttons: {
                    success: {
                        label: "Aceptar",
                        className: "btn-danger"
                    }
                }
            });
        }
    });

};


$("#txtDelito")
        .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtDelito").val() == "" ? null
                                : "%" + $("#txtDelito").val()
                                + "%";

                        var delito = new Object();
                        delito.descDelito = nombre;


                        $
                                .ajax({
                                    data: JSON.stringify(delito),
                                    url: 'buscardelitos',
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
                                                                label: item.descDelito,
                                                                value: item.cveDelito
                                                            }
                                                        }));
                                    },
                                    error: function (xhr, ajaxOptions,
                                            thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                                    }
                                });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                                + ui.item.label
                                : "Nothing selected, input was "
                                + this.value);
                        $("#txtDelito").val("");
//                        $("#hddCveDelito").val(ui.item.value);
                        var delito = new Object();
                        delito.cveDelito = ui.item.value;
                        delito.descDelito = ui.item.label;
                        delito.activo = "S";
                        agregarDelitos(delito);

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



$("#txtEtnia")
        .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtEtnia").val() == "" ? null
                                : "%" + $("#txtEtnia").val()
                                + "%";

                        var etnia = new Object();
                        etnia.descEtnia = nombre;


                        $
                                .ajax({
                                    data: JSON.stringify(etnia),
                                    url: 'buscaretnias',
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
                                                                label: item.descEtnia,
                                                                value: item.cveEtnia
                                                            }
                                                        }));
                                    },
                                    error: function (xhr, ajaxOptions,
                                            thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                                    }
                                });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                                + ui.item.label
                                : "Nothing selected, input was "
                                + this.value);
                        $("#txtEtnia").val(ui.item.label);
                        $("#hddCveEtnia").val(ui.item.value);


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

guardarRepresentado = function () {
    var idRepresentado = $("#hddIdRepresentado").val() == 0 ? null : $("#hddIdRepresentado").val();
    var nombre = $("#txtNombreImputado").val();
    var paterno = $("#txtPaternoImputado").val();
    var materno = $("#txtMaternoImputado").val();
    var fechaNacimiento = $("#txtFechaNacimientoImputado").val();
    var edad = $("#txtEdadImputado").val();
    var cveMunicipio = $("#hddCveMunicipio").val();
    var cveSexo = $("#cmdSexo").val();

    var cveEtnia = $("#hddCveEtnia").val() == 0 ? null : $("#hddCveEtnia").val();
    var descDiscapacidad = $("#txtDispacacidad").val();

    var representado = new Object();
    representado.idRepresentado = idRepresentado;
    representado.nombre = nombre;
    representado.paterno = paterno;
    representado.materno = materno;
    representado.cveSexo = cveSexo;
    representado.edad = edad;

    representado.fechaNacimiento = cambiarfecha(fechaNacimiento);
    var municipio = new Object();
    municipio.idMunicipio = cveMunicipio;
    var etnia = new Object();
    if (cveEtnia > 0) {
        etnia.cveEtnia = cveEtnia;
        representado.etnia = etnia;
    }
    if (cveMunicipio > 0)
        representado.municipio = municipio;

    representado.descDiscapacidad = descDiscapacidad;
    if (listaDelitos.length > 0 && cveMunicipio > 0) {

        $
                .ajax({
                    data: JSON.stringify(representado),
                    url: 'guardarrepresentado',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {

                    },
                    success: function (data) {

                        guardarDelitosRepresentado(data.idRepresentado);
                        guardarRepresentadosGruposVulnerables(data.idRepresentado);
                        guardarCarpetasRepresentados(data.idRepresentado);
                        cancelarRepresentado();
                        consultarImputados();
                        $("#bt-guardar-representado").hide();
                    },
                    error: function (xhr, ajaxOptions,
                            thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                    }
                });
    } else {

        bootbox.dialog({
            closeButton: true,
            message: "Debe anotar los delitos del representado, elegir municipio originario",
            title: "Error",
            buttons: {
                success: {
                    label: "Aceptar",
                    className: "btn-danger"
                }
            }
        });


    }


};



guardarRepresentadosGruposVulnerables = function (idRepresentado) {

    var listaRepresentadosGruposVulnerables = [];

    $.each(listaGruposVulnerables, function (index, element) {
        var representadoGrupoVulnerable = new Object();
        var grupoVulnerable = new Object();
        var re = new Object();
        representadoGrupoVulnerable.idRepresentadoGrupoVulnerable = element.idRepresentadoGrupoVulnerable === null ? null : element.idRepresentadoGrupoVulnerable;
        re.idRepresentado = idRepresentado;
        grupoVulnerable.cveGrupoVulnerable = element.cveGrupoVulnerable;
        representadoGrupoVulnerable.representado = re;
        representadoGrupoVulnerable.grupoVulnerable = grupoVulnerable;
        representadoGrupoVulnerable.activo = element.activo;
        listaRepresentadosGruposVulnerables.push(representadoGrupoVulnerable);
    });

    $
            .ajax({
                data: JSON.stringify(listaRepresentadosGruposVulnerables),
                url: 'guardarrepresentadogrupovulnerable',
                dataType: 'json',
                contentType: "application/json",
                type: 'post',
                beforeSend: function () {

                },
                success: function (data) {
                    $("#bt-guardar-representado").show();
                },
                error: function (xhr, ajaxOptions,
                        thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                }
            });



};

guardarCarpetasRepresentados = function (idRepresentado) {

    var representado = new Object();
    var carpeta = new Object();
    var carpetaRepresentado = new Object();
    var idCarpeta = $("#hddIdCarpeta").val();
    representado.idRepresentado = idRepresentado;
    carpeta.idCarpeta = idCarpeta;
    carpetaRepresentado.carpeta = carpeta;
    carpetaRepresentado.representado = representado;
    carpetaRepresentado.activo = "S";
    var idCarpetaRepresentado = $("#hddIdCarpetaRepresentado").val();
    if (idCarpetaRepresentado > 0)
        carpetaRepresentado.idCarpetaRepresentado = idCarpetaRepresentado;


    $
            .ajax({
                data: JSON.stringify(carpetaRepresentado),
                url: 'guardarrepresentadocarpeta',
                dataType: 'json',
                async: false,
                contentType: "application/json",
                type: 'post',
                beforeSend: function () {

                },
                success: function (data) {
                    $("#bt-guardar-representado").show();
                },
                error: function (xhr, ajaxOptions,
                        thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                }
            });


};

guardarDelitosRepresentado = function (idRepresentado) {

    var listaDelitosRepresentados = [];

    $.each(listaDelitos, function (index, element) {
        var delitoRepresentado = new Object();
        var delito = new Object();
        var re = new Object();
        delitoRepresentado.idDelitoRepresentado = element.idDelitoRepresentado === null ? null : element.idDelitoRepresentado;
        re.idRepresentado = idRepresentado;
        delito.cveDelito = element.cveDelito;
        delitoRepresentado.representado = re;
        delitoRepresentado.delito = delito;
        delitoRepresentado.activo = element.activo;
        listaDelitosRepresentados.push(delitoRepresentado);
    });

    $
            .ajax({
                data: JSON.stringify(listaDelitosRepresentados),
                url: 'guardardelitosrepresentado',
                dataType: 'json',
                contentType: "application/json",
                type: 'post',
                beforeSend: function () {

                },
                success: function (data) {
                    $("#bt-guardar-representado").show();
                },
                error: function (xhr, ajaxOptions,
                        thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                }
            });



};


guardarMedidaCautelarRepresentado = function (idAudiencia) {

    var listaMedidasCautelares = [];

    $.each(listaMedidas, function (index, element) {

        var medidaCautelar = new Object();
        var representado = new Object();
        var audiencia = new Object();
        var AudienciaMedidaCautelar = new Object();

        AudienciaMedidaCautelar.idAudienciaMedidaCautelar = element.idAudienciaMedidaCautelar == null ? null : element.idAudienciaMedidaCautelar;
        representado.idRepresentado = element.idRepresentado;
        medidaCautelar.cveMedidaCautelar = element.cveMedidaCautelar;
        audiencia.idAudiencia = idAudiencia;
        AudienciaMedidaCautelar.audiencia = audiencia;
        AudienciaMedidaCautelar.representado = representado;
        AudienciaMedidaCautelar.medidaCautelar = medidaCautelar;
        AudienciaMedidaCautelar.activo = element.activo;




        listaMedidasCautelares.push(AudienciaMedidaCautelar);
    });
    if (listaMedidasCautelares.length > 0) {
        $
                .ajax({
                    data: JSON.stringify(listaMedidasCautelares),
                    url: 'guardarmedidas',
                    dataType: 'json',
                    contentType: "application/json",
                    type: 'post',
                    beforeSend: function () {

                    },
                    success: function (data) {
                        $("#bt-guardar-representado").show();
                        listaMedidas = [];
                    },
                    error: function (xhr, ajaxOptions,
                            thrownError) {
                        //                                                $('#myModal').modal('hide');
                        //                                                $("#myModalCorrect").modal(
                        //                                                        "hide");
                        //                                                $("#myModalError")
                        //                                                        .append(
                        //                                                                "Seleccione una dependencia de las opciones mostradas");
                        //                                                $("#myModalError").modal();
                    }
                });

    }


};



limpiarImputados = function (e) {

    var row = $("#tblimputados").find('tr');
    console.log("rows: " + row.length)
    if (row.length > 1) {
        cambiarPasos(e, true);
        $("#listaRepresentados").hide();
        representadosAudiencia();
        buscarAudienciasCarpetas();
    } else {
        bootbox.dialog({
            closeButton: true,
            message: "Error, debe ingresar representados a la lista.",
            title: "Error",
            buttons: {
                success: {
                    label: "Aceptar",
                    className: "btn-danger"
                }
            }
        });
    }
};



$("#txtTipoconclusiones")
        .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtTipoconclusiones").val() == "" ? null
                                : "%" + $("#txtTipoconclusiones").val()
                                + "%";

                        var tipoConclusion = new Object();
                        tipoConclusion.idTipoCarpeta = 1;
                        tipoConclusion.descTipoConclusiones = nombre;


                        $
                                .ajax({
                                    data: JSON.stringify(tipoConclusion),
                                    url: 'buscartiposconclusiones',
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
                                                                label: item.descTipoConclusiones,
                                                                value: item.cveTipoConclusion
                                                            }
                                                        }));
                                    },
                                    error: function (xhr, ajaxOptions,
                                            thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                                    }
                                });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                                + ui.item.label
                                : "Nothing selected, input was "
                                + this.value);
                        $("#txtTipoconclusiones").val(ui.item.label);
                        $("#hddCveTipoConclusion").val(ui.item.value);


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



$("#txtTipoAudiencia")
        .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtTipoAudiencia").val() == "" ? null
                                : "%" + $("#txtTipoAudiencia").val()
                                + "%";

                        var tipoAudiencia = new Object();
                        tipoAudiencia.idTipoCarpeta = 1;
                        tipoAudiencia.descTipoAudiencia = nombre;


                        $
                                .ajax({
                                    data: JSON.stringify(tipoAudiencia),
                                    url: 'buscaraudiencias',
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
                                                                label: item.descTipoAudiencia,
                                                                value: item.cveTipoAudiencia
                                                            }
                                                        }));
                                    },
                                    error: function (xhr, ajaxOptions,
                                            thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                                    }
                                });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                                + ui.item.label
                                : "Nothing selected, input was "
                                + this.value);
                        $("#txtTipoAudiencia").val(ui.item.label);
                        $("#hddCveTipoAudiencia").val(ui.item.value);


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


guardarAudiencia = function () {
    var idCarpeta = $("#hddIdCarpeta").val();
    var idAudiencia = $("#hddIdAudiencia").val();
    var fechaAudiencia = cambiarfecha($("#txtFechaAudiencia").val());
    var fechaVinculacion = cambiarfecha($("#txtFechaVinculacion").val());
    var fechaControlDeten = cambiarfecha($("#txtFechaControlDeten").val());
    var fechaFormulacion = cambiarfecha($("#txtFechaFormulacion").val());
    var fechaInicioInves = cambiarfecha($("#txtFechaInicioInvest").val());
    var fechaProrroga = cambiarfecha($("#txtFechaprorroga").val());
    var fechaMedidaCautelar = cambiarfecha($("#txtFechaMedida").val());
    var fechaPeticionPartes = cambiarfecha($("#txtFechapeticionpartes").val());
//    var fechaInicioJuicioOral = cambiarfecha($("#txtFechaInicioJuicioOral").val());
//    var fechaProximaAudiencia = cambiarfecha($("#txtFechaProximaAudiencia").val());
    var cveRecurso = $("#hddCveRecurso").val();
    var interno = $("#cmbEstadoBeneficiado").val();
    var fechaFinInves = cambiarfecha($("#txtFechaFinInvest").val());
    var defensa_id = $("#hddDefensa_id").val();

    var cveTipoAudiencia = $("#hddCveTipoAudiencia").val();

    var observaciones = $("#txtObservaciones").val();
    var recurso = new Object();
    var audiencia = new Object();
    var tipoAudiencia = new Object();
    var carpeta = new Object();
    carpeta.idCarpeta = idCarpeta;
    audiencia.carpeta = carpeta;
    if (idAudiencia > 0)
        audiencia.idAudiencia = idAudiencia;
    audiencia.fechaAudiencia = fechaAudiencia;
    audiencia.fechaVinculacion = fechaVinculacion;
    audiencia.fechaControlDetencion = fechaControlDeten;
    audiencia.fechaFormulacionImputacion = fechaFormulacion;
    audiencia.fechaPeriodoInvInicia = fechaInicioInves;
    audiencia.fechaProrroga = fechaProrroga;
    audiencia.fechaMedidaCautelar = fechaMedidaCautelar;
    audiencia.fechaPeticionPartes = fechaPeticionPartes;
//    audiencia.fechaInicioJuicioOral = fechaInicioJuicioOral;
    if (cveTipoAudiencia > 0) {
        tipoAudiencia.cveTipoAudiencia = cveTipoAudiencia;
        audiencia.tipoAudiencia = tipoAudiencia;
    }
    audiencia.fechaPeriodoInvFin = fechaFinInves;
    audiencia.observaciones = observaciones;
//    audiencia.fechaProximaAudiencia = fechaProximaAudiencia;
    audiencia.interno = interno;
    if (cveRecurso > 0) {
        recurso.cveRecurso = cveRecurso;
        audiencia.recurso = recurso;
    }
    if (defensa_id > 0)
        audiencia.defensa_id = defensa_id;

    represe = false;
    $('input[name=representadosAud]').each(function () {
        if (this.checked) {
            represe = true;
        }
    });
    if (fechaAudiencia != null & cveTipoAudiencia > 0 && represe)
        $.ajax({
            url: "guardaraudiencia",
            type: 'POST',
            data: JSON.stringify(audiencia),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                guardarConclusiones(response.idAudiencia);
                guardarMedidaCautelarRepresentado(response.idAudiencia);
                limpiarActuacion();
                buscarAudienciasCarpetas();

            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al buscar la carpeta",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });
    else {
        bootbox.dialog({
            closeButton: true,
            message: "Error, verifique datos, fecha de actuaci??n, tipo de audiencia y elegir representado son obligatorios.",
            title: "Error",
            buttons: {
                success: {
                    label: "Aceptar",
                    className: "btn-danger"
                }
            }
        });

    }


};

cargarAudiencia = function (idAudiencia) {

    var audiencia = new Object();
    audiencia.idAudiencia = idAudiencia;

    $.ajax({
        url: "buscaraudienciascarpetas",
        type: 'POST',
        data: JSON.stringify(audiencia),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {
            nuevaAudiencia();

            $.each(response, function (index, element) {
                listaMedidas = [];
                $(".RepAudiencias").empty();
                $("#hddIdAudiencia").val(element.idAudiencia);
                $("#txtFechaAudiencia").val(cambiarFecha2(element.fechaAudiencia));
                $("#txtFechaVinculacion").val(cambiarFecha2(element.fechaVinculacion));
                $("#txtFechaControlDeten").val(cambiarFecha2(element.fechaControlDetencion));
                $("#txtFechaFormulacion").val(cambiarFecha2(element.fechaFormulacionImputacion));
                $("#txtFechaInicioInvest").val(cambiarFecha2(element.fechaPeriodoInvInicia));
                $("#txtFechaInicioJuicioOral").val(cambiarFecha2(element.fechaInicioJuicioOral));
                $("#txtFechaFinInvest").val(cambiarFecha2(element.fechaPeriodoInvFin));

                $("#txtFechaprorroga").val(cambiarFecha2(element.fechaProrroga));
                $("#txtFechaMedida").val(cambiarFecha2(element.fechaMedidaCautelar));
                $("#txtFechapeticionpartes").val(cambiarFecha2(element.fechaPeticionPartes));

                $("#txtFechaProximaAudiencia").val(cambiarFecha2(element.fechaProximaAudiencia));
                if (element.recurso !== null) {
                    $("#txtRecurso").val(element.recurso.descRecurso);
                    $("#hddCveRecurso").val(element.recurso.cveRecurso);
                }

                $("#cmbEstadoBeneficiado").val((element.interno));
                if (element.tipoAudiencia !== null) {
                    $("#hddCveTipoAudiencia").val(element.tipoAudiencia.cveTipoAudiencia);
                    $("#txtTipoAudiencia").val(element.tipoAudiencia.descTipoAudiencia);
                }

                console.log("tamanio conclusiones" + element.conclusiones.length);
                if (element.conclusiones.length > 0) {
                    var ultimo = element.conclusiones.length - 1;
                    $("#hddCveTipoConclusion").val(element.conclusiones[ultimo].tipoConclusion.cveTipoConclusion);
                    $("#txtTipoconclusiones").val(element.conclusiones[ultimo].tipoConclusion.descTipoConclusiones);
                    $.each(element.conclusiones, function (index, element) {

                        var r = element.representado;

                        $('input[name=representadosAud]').each(function () {

                            var check = $(this).val();
                            console.log("marcando representados" + check + "<==>" + r.idRepresentado);
                            if (check == r.idRepresentado) {
                                console.log("representado encontrado");
                                $(this).prop("checked", true);
                                $("#representadosAud" + r.idRepresentado).prop("disabled", false);
                                return false;
                            }
                        });


                    });
                }
                if (element.medidasCautelares.length > 0) {
                    $.each(element.medidasCautelares, function (index, element) {
                        if (element.activo == "S") {
                            var medida = new Object();
                            medida.idAudienciaMedidaCautelar = element.idAudienciaMedidaCautelar;
                            medida.descMedidaCautelar = element.medidaCautelar.descMedidaCautelar;
                            medida.cveMedidaCautelar = element.medidaCautelar.cveMedidaCautelar;
                            medida.idRepresentado = element.representado.idRepresentado;
                            medida.activo = element.activo;
                            agregarMedidas(medida);
                        }
                    });

                }

                $("#hdddefensa_id").val(element.defensa_id);
                $("#txtObservaciones").val(element.observaciones);


            });
            $("#btn-guardar-audiencia").show();
            $("#btn-cancelar-audiencia").show();




        }, error: function (xhr, ajaxOptions, thrownError) {
            bootbox.dialog({
                closeButton: true,
                message: "Error al buscar la carpeta",
                title: "Error",
                buttons: {
                    success: {
                        label: "Aceptar",
                        className: "btn-danger"
                    }
                }
            });
        }
    });



};

guardarConclusiones = function (idAudiencia) {


    var audiencia = new Object();
    var tipoConclusion = new Object();
    var conclusiones = [];


    $('input[name=representadosAud]').each(function () {
        if (this.checked) {
            var representado = new Object();
            var conclusion = new Object();
            console.log($(this).val());
            representado.idRepresentado = $(this).val();
            audiencia.idAudiencia = idAudiencia;
            tipoConclusion.cveTipoConclusion = $("#hddCveTipoConclusion").val() == 0 ? 71 : $("#hddCveTipoConclusion").val();
            conclusion.representado = representado;
            conclusion.audiencia = audiencia;
            conclusion.tipoConclusion = tipoConclusion;
            conclusion.activo = "S";
            conclusiones.push(conclusion);

        }
    });

    $.ajax({
        url: "guardarconclusiones",
        type: 'POST',
        data: JSON.stringify(conclusiones),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {
            bootbox.dialog({
                closeButton: true,
                message: "Se guardo la Audiencia",
                title: "Error",
                buttons: {
                    success: {
                        label: "Aceptar",
                        className: "btn-success"
                    }
                }
            });

        }, error: function (xhr, ajaxOptions, thrownError) {
            bootbox.dialog({
                closeButton: true,
                message: "Error al buscar la carpeta",
                title: "Error",
                buttons: {
                    success: {
                        label: "Aceptar",
                        className: "btn-danger"
                    }
                }
            });
        }
    });

};

representadosAudiencia = function () {
    var idCarpeta = $("#hddIdCarpeta").val();

    var carpeta = new Object();
    carpeta.idCarpeta = idCarpeta;
    var carpetaRepresentado = new Object();
    carpetaRepresentado.carpeta = carpeta;

    if (validarSesion()) {

        $.ajax({
            url: "buscarrepresentados",
            type: 'POST',
            data: JSON.stringify(carpetaRepresentado),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                $("#divRepresentadosAudiencia").empty();
                var table = "<table  class=\"table table-striped\">" +
                        "<thead>" +
                        " <tr>" +
                        " <th scope=\"col\">Nombre del Representado</th>" +
                        " </tr>" +
                        "</thead><tbody>";

                $.each(response, function (index, element) {


                    var button = "<input type=\"button\" class=\"btn btn-primary\" onclick=\"cargarRepresentado(" + element.representado.idRepresentado + ");\" value='Actualizar'>";
                    var nombre = element.representado.nombre + " " + element.representado.paterno + " " + element.representado.materno;
                    table += "<tr>" +
                            "<td>" + nombre.toUpperCase() + "</td>" +
                            "<td><input type='checkbox' name='representadosAud' onChange ='activarMedida(" + element.representado.idRepresentado + ",this)' value='" + element.representado.idRepresentado + "'></td>" +
                            "</tr>";
                    table += "<tr>" +
                            "<td colspan='2'>Medida Cautelar<input type='text' class='autocompletar repMedidas' id='representadosAud" + element.representado.idRepresentado + "' disabled></td>" +
                            "</tr>";
                    table += "<tr>" +
                            "<td colspan='2'><div><ul class='RepAudiencias' id='divRepresentadosAud" + element.representado.idRepresentado + "'></ul></div></td>" +
                            "</tr>";


                });
                table += " </tbody>" +
                        "</table>";
                $("#divRepresentadosAudiencia").show();
                $("#divRepresentadosAudiencia").append(table);
                $.each(response, function (index, element) {
                    (function () {
                        var lista = "divRepresentadosAud" + element.representado.idRepresentado;
                        $("#representadosAud" + element.representado.idRepresentado).autocomplete({
                            source: function (request, response) {
                                console.log();
                                var nombre = $("#representadosAud" + element.representado.idRepresentado).val() == "" ? null
                                        : "%" + $("#representadosAud" + element.representado.idRepresentado).val()
                                        + "%";
                                var medidaCautelar = new Object();
                                medidaCautelar.descMedidaCautelar = nombre;
                                $
                                        .ajax({
                                            data: JSON.stringify(medidaCautelar),
                                            url: 'buscarmedidascautelares',
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
                                                                        label: item.descMedidaCautelar,
                                                                        value: item.cveMedidaCautelar
                                                                    }
                                                                }));
                                            },
                                            error: function (xhr, ajaxOptions,
                                                    thrownError) {

                                            }
                                        });

                            },
                            minlength: 2,
                            select: function (event, ui) {
                                event.preventDefault();
                                console.log(ui.item ? "Selected: "
                                        + ui.item.label
                                        : "Nothing selected, input was "
                                        + "#representadosAud" + element.representado.idRepresentado);
                                $("#representadosAud" + element.representado.idRepresentado).val("");
//                        $("#hddCveDelito").val(ui.item.value);
                                var medida = new Object();
                                medida.cveMedidaCautelar = ui.item.value;
                                medida.descMedidaCautelar = ui.item.label;
                                medida.activo = "S";
                                var clave = element.representado.idRepresentado;
                                medida.idRepresentado = clave;
                                agregarMedidas(medida);

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

                        // FIN AUTOCOMPLETE
                    })();

                });






            }, error: function (xhr, ajaxOptions, thrownError) {
                bootbox.dialog({
                    closeButton: true,
                    message: "Error al buscar la carpeta",
                    title: "Error",
                    buttons: {
                        success: {
                            label: "Aceptar",
                            className: "btn-danger"
                        }
                    }
                });
            }
        });
    }

};

activarMedida = function (idRepresentado, objeto) {
    console.log("Activar Medida" + objeto);
    var estado = $(objeto).is(":checked");
    console.log("Estado " + estado);
    if (estado)
        $("#representadosAud" + idRepresentado).prop("disabled", false);
    else
        $("#representadosAud" + idRepresentado).prop("disabled", true);


};

buscarAudienciasCarpetas = function () {
    var idCarpeta = $("#hddIdCarpeta").val();
    var audiencia = new Object();
    var carpeta = new Object();
    carpeta.idCarpeta = idCarpeta;
    audiencia.carpeta = carpeta;

    $.ajax({
        url: "buscaraudienciascarpetas",
        type: 'POST',
        data: JSON.stringify(audiencia),
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {
            limpiarActuacion();
            $("#listaAudienciasConsulta").empty();
            var table = "<table class=\"table table-striped\">" +
                    "<thead>" +
                    " <tr>" +
                    "  <th scope=\"col\">#</th>" +
                    " <th scope=\"col\">Actuaci??n</th>" +
                    " <th scope=\"col\">Fecha Actuaci??n</th>" +
                    "<th scope=\"col\">Conclusiones</th>" +
                    "<th scope=\"col\"><input type='button' class='step-button' onclick='nuevaAudiencia()' value = 'Nueva Actuaci??n'></th>" +
                    "<th scope=\"col\">&nbsp;</th>" +
                    " </tr>" +
                    "</thead><tbody>";

            $.each(response, function (index, element) {


                var button = "<input type=\"button\" class=\"btn btn-primary\" onclick=\"cargarAudiencia(" + element.idAudiencia + ");\" value = 'Actualizar'>";
                var descAudiencia = "";
                var descTipoConclusion = "";
                var defensor = "";
                if (element.tipoAudiencia != null)
                    descAudiencia = element.tipoAudiencia.descTipoAudiencia;
                if (element.conclusiones.length > 0) {
                    var tamanio = element.conclusiones.length;
                    descTipoConclusion = element.conclusiones[tamanio - 1].tipoConclusion.descTipoConclusiones;
                }
                if (element.personal !== null) {

                    defensor = element.personal.nombre + " " + element.personal.paterno + " " + element.personal.materno;
                }
                var registro = descAudiencia + " <br>Fecha: " + cambiarFecha2(element.fechaAudiencia) + " <br>Conclusion: " + descTipoConclusion + " <br>Defensor:" + defensor;
                //var nombre = element.personal.nombre + " " + element.personal.paterno + " " + element.personal.materno;
                table += "<tr>" +
                        "<th scope=\"row\">" + (index + 1) + "</th>" +
                        "<td colspan='3'>" + registro + "</td>" +
                        "<td>" + button + "</td>" +
                        "<td></td>" +
                        "</tr>";
            });
            table += " </tbody>" +
                    "</table>";
            $("#listaAudienciasConsulta").show();
            $("#listaAudienciasConsulta").append(table);
        }, error: function (xhr, ajaxOptions, thrownError) {
            bootbox.dialog({
                closeButton: true,
                message: "Error al buscar la carpeta",
                title: "Error",
                buttons: {
                    success: {
                        label: "Aceptar",
                        className: "btn-danger"
                    }
                }
            });
        }
    });

};



$("#txtGrupoVulnerable")
        .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtGrupoVulnerable").val() == "" ? null
                                : "%" + $("#txtGrupoVulnerable").val()
                                + "%";

                        var grupovulnerable = new Object();
                        grupovulnerable.descGrupoVulnerable = nombre;


                        $
                                .ajax({
                                    data: JSON.stringify(grupovulnerable),
                                    url: 'buscargruposvulnerables',
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
                                                                label: item.descGrupoVulnerable,
                                                                value: item.cveGrupoVulnerable
                                                            }
                                                        }));
                                    },
                                    error: function (xhr, ajaxOptions,
                                            thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                                    }
                                });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                                + ui.item.label
                                : "Nothing selected, input was "
                                + this.value);
                        $("#txtGrupoVulnerable").val("");
//                        $("#hddCveDelito").val(ui.item.value);
                        var grupovulnerable = new Object();
                        grupovulnerable.cveGrupoVulnerable = ui.item.value;
                        grupovulnerable.descGrupoVulnerable = ui.item.label;
                        grupovulnerable.activo = "S";
                        agregarGrupoVulnerable(grupovulnerable);

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


limpiarActuacion = function () {
    $("#txtFechaAudiencia").val("");
    $("#txtFechaVinculacion").val("");
    $("#txtFechaControlDeten").val("");
    $("#txtFechaFormulacion").val("");
    $("#txtFechaInicioInvest").val("");
    $("#txtFechaInicioJuicioOral").val("");
    $("#txtFechaFinInvest").val("");
    $("#txtTipoAudiencia").val("");
    $("#txtObservaciones").val("");
    $("#hddCveTipoAudiencia").val(0);
    $("#hddCveTipoConclusion").val(0);
    $("#hddIdAudiencia").val(0);

    $("#txtFechaprorroga").val("");
    $("#txtFechaMedida").val("");
    $("#txtFechapeticionpartes").val("");



    $("#txtFechaProximaAudiencia").val("");
    $("#txtRecurso").val("");
    $("#hddcveRecurso").val(0);
    $("#cmbEstadoBeneficiado").val("N");


    $("#txtFechaAudiencia").prop("disabled", true);
    $("#txtFechaVinculacion").prop("disabled", true);
    $("#txtFechaControlDeten").prop("disabled", true);
    $("#txtFechaFormulacion").prop("disabled", true);
    $("#txtFechaInicioInvest").prop("disabled", true);
    $("#txtFechaInicioJuicioOral").prop("disabled", true);
    $("#txtFechaFinInvest").prop("disabled", true);
    $("#txtTipoAudiencia").prop("disabled", true);
    $("#txtObservaciones").prop("disabled", true);
    $("#txtTipoconclusiones").prop("disabled", true);
    $("#btn-guardar-audiencia").hide();
    $("#btn-cancelar-audiencia").hide();
    $("#txtFechaProximaAudiencia").prop("disabled", true);
    $("#txtRecurso").prop("disabled", true);

    $("#cmbEstadoBeneficiado").prop("disabled", true);

    $('input[name=representadosAud]').each(function () {

        $(this).prop("checked", false);

    });
    listaMedidas = [];

    $(".repMedidas").prop("disabled", true);
    $("#txtFechaMedida").prop("disabled", true);
    $("#txtFechapeticionpartes").prop("disabled", true);
    $("#txtFechaprorroga").prop("disabled", true);




};
nuevaAudiencia = function () {

    $("#txtFechaAudiencia").val("");
    $("#txtFechaVinculacion").val("");
    $("#txtFechaControlDeten").val("");
    $("#txtFechaFormulacion").val("");
    $("#txtFechaInicioInvest").val("");
    $("#txtFechaInicioJuicioOral").val("");
    $("#txtFechaFinInvest").val("");
    $("#txtTipoAudiencia").val("");
    $("#txtObservaciones").val("");
    $("#txtTipoconclusiones").val("");
    $("#hddCveTipoAudiencia").val(0);
    $("#hddCveTipoConclusion").val(0);
    $("#hddIdAudiencia").val(0);
    $("#txtFechaProximaAudiencia").val("");
    $("#txtRecurso").val("");
    $("#hddcveRecurso").val(0);
    $("#cmbEstadoBeneficiado").val("N");

    $("#txtFechaAudiencia").prop("disabled", false);
    $("#txtFechaVinculacion").prop("disabled", false);
    $("#txtFechaControlDeten").prop("disabled", false);
    $("#txtFechaFormulacion").prop("disabled", false);
    $("#txtFechaInicioInvest").prop("disabled", false);
    $("#txtFechaInicioJuicioOral").prop("disabled", false);
    $("#txtFechaFinInvest").prop("disabled", false);
    $("#txtTipoAudiencia").prop("disabled", false);
    $("#txtObservaciones").prop("disabled", false);
    $("#txtTipoconclusiones").prop("disabled", false);
    $("#btn-guardar-audiencia").show();
    $("#btn-cancelar-audiencia").show();

    $("#txtFechaProximaAudiencia").prop("disabled", false);
    $("#txtRecurso").prop("disabled", false);

    $("#cmbEstadoBeneficiado").prop("disabled", false);


    $('input[name=representadosAud]').each(function () {

        $(this).prop("checked", false);

    });

    $("#txtFechaprorroga").prop("disabled", false);
    $("#txtFechaMedida").prop("disabled", false);
    $("#txtFechapeticionpartes").prop("disabled", false);


};

limpiarCarpetas = function () {

    $("#txtNumeroExpConsulta").val("");
    $("#txtAnioExpConsulta").val("");
    $("#txtJuzgadoConsulta").val("");
    $("#txtTipoCarpetaConsulta").val("");
    $("#txtNucConsulta").val("");
    $("#hddJuzgadoConsulta").val(0);
    $("#hddTipoCarpetaConsulta").val(1);
    $("#hddIdCarpeta").val(0);
    var cveFormulario = $("#cveFormulario").val();
    var rutaFormulario = $("#rutaFormulario").val();
    cargaForm(rutaFormulario, cveFormulario);
};

$("#txtRecurso")
        .autocomplete(
                {
                    source: function (request, response) {
                        var nombre = $("#txtRecurso").val() == "" ? null
                                : "%" + $("#txtRecurso").val()
                                + "%";

                        var recurso = new Object();
                        recurso.descRecurso = nombre;


                        $
                                .ajax({
                                    data: JSON.stringify(recurso),
                                    url: 'buscarrecursos',
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
                                                                label: item.descRecurso,
                                                                value: item.cveRecurso
                                                            }
                                                        }));
                                    },
                                    error: function (xhr, ajaxOptions,
                                            thrownError) {
//                                                $('#myModal').modal('hide');
//                                                $("#myModalCorrect").modal(
//                                                        "hide");
//                                                $("#myModalError")
//                                                        .append(
//                                                                "Seleccione una dependencia de las opciones mostradas");
//                                                $("#myModalError").modal();
                                    }
                                });

                    },
                    minlength: 2,
                    select: function (event, ui) {
                        event.preventDefault();
                        console.log(ui.item ? "Selected: "
                                + ui.item.label
                                : "Nothing selected, input was "
                                + this.value);
                        $("#txtRecurso").val(ui.item.label);
                        $("#hddCveRecurso").val(ui.item.value);


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

$(function () {

    $("#hddTipoCarpetaConsulta").val(1);
    $("#txtTipoCarpetaConsulta").val("CARPETA ADMINISTRATIVA");
    $("#hddTipoCarpetaRegistro").val(1);
    $("#txtTipoCarpetaRegistro").val("CARPETA ADMINISTRATIVA");

    $("#txtTipoCarpetaRegistro").prop("disabled", true);
    $("#txtTipoCarpetaConsulta").prop("disabled", true);






});