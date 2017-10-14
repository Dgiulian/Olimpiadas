
var pruebas = [];
var deportes = [];
var equipos = [];
var grupos = [];
var categorias = [];
var templates = {};
var ms;
function init_prueba() {
    $('#btnNuevo').click(function() {
        agregarPrueba({});
    });
    window.Handlebars.registerHelper('descPuntaje', function(tipo_puntaje, orden_puntaje) {
        var puntajes = ["Puntos", "Tiempo"];
        var ordenes = ["Ascendente", "Descendente"];
        return "" + puntajes[tipo_puntaje] + " (" + ordenes[orden_puntaje] + ")";
    });
    $('#btnSearch').click(filtrarPrueba);
    $('#search_fecha').change(filtrarPrueba);
    $('#search_deporte').change(filtrarPrueba);
    $('#search_categoria').change(filtrarPrueba);
    $('#search_grupo').change(filtrarPrueba);
    $('#search_estado').change(filtrarPrueba);
    templates['list'] = Handlebars.compile($("#prueba_list").html());
    templates['edit'] = Handlebars.compile($('#prueba_edit').html());
    loadDeportes({});
    filtrarPrueba();
}

function init_prueba() {
    $('#btnNuevo').click(function() {
        agregarPrueba({});
    });
    window.Handlebars.registerHelper('descPuntaje', function(tipo_puntaje, orden_puntaje) {
        var puntajes = ["Puntos", "Tiempo"];
        var ordenes = ["Ascendente", "Descendente"];
        return "" + puntajes[tipo_puntaje] + " (" + ordenes[orden_puntaje] + ")";
    });
    $('#btnSearch').click(filtrarPrueba);
    $('#search_fecha').change(filtrarPrueba);
    $('#search_deporte').change(filtrarPrueba);
    $('#search_categoria').change(filtrarPrueba);
    $('#search_grupo').change(filtrarPrueba);
    $('#search_estado').change(filtrarPrueba);
    templates['list'] = Handlebars.compile($("#prueba_list").html());
    templates['edit'] = Handlebars.compile($('#prueba_edit').html());
    loadDeportes({});
    filtrarPrueba();
}

function init_prueba_panel(id_categoria, id_grupo) {

    $('#search_grupo').change(function() {
        filtrarPruebaPanel(id_categoria, id_grupo)
    });
    $('#search_fecha').change(function() {
        filtrarPruebaPanel(id_categoria, id_grupo)
    });
    $('#search_estado').change(function() {
        filtrarPruebaPanel(id_categoria, id_grupo)
    });
    filtrarPruebaPanel(id_categoria, id_grupo);
}

function filtrarPruebaPanel(id_categoria, id_grupo) {
    var data = getSearchDataPanel(id_categoria, id_grupo);
    loadDataPrueba(data, 2);
}

function filtrarPrueba() {
    var data = getSearchData();
    loadDataPrueba(data, 1);
}

function loadDeportes(filtro) {
    return getDataDeportes(filtro).done(function(data) {
        if (data.Result === "OK") {
            deportes = data.Records;
        }
    });
}


function loadCategorias(filtro) {
    filtro.id_deporte = $('#id_deporte').val();
    return getDataCategorias(filtro).done(function(data) {
        if (data.Result === "OK") {
            categorias = data.Records;
        }
    });
}
function loadGrupos(filtro) {
    filtro.id_categoria = $('#id_categoria').val();
    return getDataGrupos(filtro).done(function(data) {
        if (data.Result === "OK") {
            grupos = data.Records;
        }
    });
}

function loadEquipos(filtro) {
    filtro.id_grupo = $('#id_grupo').val();
    return getDataEquipos(filtro).done(function(data) {
        if (data.Result === "OK") {
            equipos = data.Records;
        }
    });
}

//Tipo 1 si es para rellenar el ABM o 2 para completar los items de una categoria
function loadDataPrueba(filter, tipo) {
    if (tipo == 1) {
        var $tabla = $('#tblPrueba');
        setLoader($tabla);
    } else {
        var $tabla = $('#tbl_agenda');
    }
    getDataPruebas(filter).done(function(result) {
        if (result.Result === "OK") {
            pruebas = result.Records;
            if (tipo == 1) {
                createTable($tabla, pruebas)
            } else {
                setheader($tabla, pruebas);
                cargar_items_agenda2($tabla, pruebas);
            }
        }
    });
}
function borrarPrueba() {
    var index = $(this).data('index');
    var id = pruebas[index].id;
    deleteData(URLS.PRUEBA.DEL, {id: id}, function(result) {
        if (result.Result === "OK") {
            filtrarPrueba();
        } else if (result.Message)
            bootbox.alert(result.Message);
    });
}
function createTable($tabla, data) {
    var template = templates['list'];
    console.log(data.deporte);
    $tabla.find('tbody').html(template({records: data}));
    $('.btn-del').click(borrarPrueba);
    $('.btn-edit').click(editarPrueba);
    $('.btn-resultados').click(redirigir_resultados);
}
function editarPrueba() {
    var data = {};
    var index = $(this).data('index');
    data = pruebas[index];
    agregarPrueba(data);
}

function redirigir_resultados() {
    var index = $(this).data('index');
    var id = pruebas[index].id;
    window.location = URLS.PRUEBA_DEPORTIVA.BASE + "?id_prueba=" + id;
}
function redirigir_imprimir(id_prueba, tipo_modalidad) {
    if (tipo_modalidad == 1) {
        var url = 'http://179.43.127.107/reportes_olimpiadas/prueba.php?id=' + id_prueba;
    } else {
        var url = 'http://179.43.127.107/reportes_olimpiadas/prueba_colectiva.php?id=' + id_prueba;
    }

    window.open(url, '_blank');
    //window.location = 'http://179.43.127.107/reportes_olimpiadas/prueba.php';
}

function agregarPrueba(data) {
    Promise.all([loadDeportes, loadCategorias]).then(function(resolve, reject) {
        var template = templates['edit'];
        data.deportes = deportes;
        data.categorias = categorias;
        data.equipos = equipos;
        data.grupos = grupos;
        data.disabled = data.id_estado > 1;
        if (data.detalle)
            data.selected = data.detalle.map(function(el, ind) {
                return el.id
            }).join(",");
        bootbox.dialog({
            title: "Configuraci&oacute;n de prueba deportiva",
            message: template(data),
            size: "large",
            buttons: {
                success: {
                    label: "Guardar",
                    className: "btn-success",
                    callback: function() {
                        var campos = recuperarCampos();
                        guardarPrueba(campos);
                    }
                },
                cancel: {
                    label: "Cancelar",
                    callback: function() {
                    }
                }
            }
        }).init(function() {
            initDialog();
            $('#equipos').multiSelect();
            $('#id_deporte').change(changeDeporte);
            $('#id_categoria').change(changeCategoria);
            $('#id_grupo').change(changeGrupo);
            $('#id_deporte').trigger('change');
        });
    });
}

function guardarPrueba(data) {
    $.ajax({
        url: URLS.PRUEBA.EDIT,
        data: data,
        method: 'POST',
        dataType: 'json'
    }).done(function() {
        filtrarPrueba();
    });
}
function changeDeporte(e) {
    var id_deporte = $('#id_deporte').val();
    getDataCategorias({id_deporte: id_deporte}).done(function(result) {
        if (result.Result === "OK") {
            categorias = result.Records;
            createOptionsFromArray('#id_categoria', categorias);
            $('#id_categoria').trigger('change');
        }
    });
}
function changeCategoria(e) {
    var id_categoria = $('#id_categoria').val();
    getDataGrupos({id_categoria: id_categoria}).done(function(result) {
        if (result.Result === "OK") {
            pruebas = result.Records;
            createOptionsFromArray('#id_grupo', pruebas);
            $('#id_grupo').trigger('change');
        }
    });
}

function changeGrupo(e) {
    var id_grupo = $('#id_grupo').val();
    getDataEquipos({id_grupo}).done(function(result) {
        if (result.Result === "OK") {
            equipos = result.Records;
            createOptionsFromArray('#equipos', equipos);
            $('#equipos').multiSelect('refresh');
        }
    }
    );
}
function recuperarCampos() {
    var data = getFormData($('form'));
    return data;
}

function getDataDeportes(filter) {
    if (typeof filter === "undefined")
        filter = {};
    return $.ajax({
        url: URLS.DEPORTE.LIST,
        data: filter,
        method: "GET",
        dataType: "json",
    });
}
function getDataEquipos(filter) {
    if (typeof filter === "undefined")
        filter = {};
    return $.ajax({
        url: URLS.EQUIPO.LIST,
        data: filter,
        method: "GET",
        dataType: "json",
    });
}
function getDataCategorias(filter) {
    if (typeof filter === "undefined")
        filter = {};
    return $.ajax({
        url: URLS.CATEGORIA.LIST,
        data: filter,
        method: "GET",
        dataType: "json",
    });
}
function getDataPruebas(filter) {
    if (typeof filter === "undefined")
        filter = {};
    return $.ajax({
        url: URLS.PRUEBA.LIST,
        data: filter,
        method: "GET",
        dataType: "json"
    });
}
function getDataGrupos(filter) {
    if (typeof filter === "undefined")
        filter = {};
    return $.ajax({
        url: URLS.GRUPO.LIST,
        data: filter,
        method: "GET",
        dataType: "json",
    });
}

function getSearchData() {
    var data = {};
    data.fecha = $('#search_fecha').val();
    data.id_deporte = $('#search_deporte').val();
    data.id_categoria = $('#search_categoria').val();
    data.id_grupo = $('#search_grupo').val();
    data.id_estado = $('#search_estado').val();
    return data;
}

function getSearchDataPanel(id_categoria, id_grupo) {
    var data = {};
    data.fecha = $('#search_fecha').val();
    data.id_categoria = id_categoria;
    data.id_grupo = id_grupo;
    data.id_estado = $('#search_estado').val();
    return data;
}

function cargar_items_agenda2($contenedor, data) {

    var html = "";
    for (var i = 0; i < data.length; i++) {
        d = data[i];
        if (d.categoria.tipo_modalidad == 1) {
            html += "<tr class=''>";
            html += wrapTag('td', d.fecha + " " + d.hora, '');
            html += wrapTag('td', d.grupo.nombre, '');
            html += wrapTag('td', '<img height="60px" src="images/delegaciones/' + d.equipos[0].delegacion.imagen + '" class=""/>', '');
            html += wrapTag('td', d.equipos[0].nombre, 'team', '');
            html += wrapTag('td', "VS", '');
            html += wrapTag('td', d.equipos[1].nombre, 'team', '');
            html += wrapTag('td', '<img height="60px" src="images/delegaciones/' + d.equipos[1].delegacion.imagen + '" class=""/>', '');
            html += wrapTag('td', d.sede.nombre, '');
            html += wrapTag('td', estado(d.id_estado), '');
        } else {
            html += "<tr class=''>";
            html += wrapTag('td', d.fecha + " " + d.hora, '');
            html += wrapTag('td', d.grupo.nombre, '');
            html += wrapTag('td', d.sede.nombre, '');
            html += wrapTag('td', estado(d.id_estado), '');
        }
        html += wrapTag('td', "<button class='btn btn-primary btn-rounded' data-id='" + d.id + "' data-toggle='modal' data-target='#prueba_deportiva_detalle_view'><i class='glyphicon glyphicon-list'></i></button>", '');
        html += wrapTag('td', "<button class='btn btn-success btn-rounded' onclick='redirigir_imprimir(" + d.id + "," + d.categoria.tipo_modalidad + ");'><i class='glyphicon glyphicon-print'></i></button>", '');
        html += "</tr>";
    }
    $contenedor.find("tbody").html(html);
}

function setheader($contenedor, data) {
    var html = "";
    if (data.length > 0) {
        tipo = data[0].categoria.tipo_modalidad;
        if (tipo == 1) {
            html += "<th>Fecha y Hora</th>";
            html += "<th>Grupo</th>";
            html += "<th colspan='2'>";
            html += "Equipo 1";
            html += "</th>";
            html += "<th></th>";
            html += "<th colspan='2'>";
            html += "Equipo 2";
            html += "</th>";
            html += "<th>";
            html += "Lugar";
            html += "</th>";
            html += "<th>";
            html += "Estado";
            html += "</th>";
            html += "<th>";
            html += "ACCIÓN";
            html += "</th>";
        } else {
            html += "<th>Fecha y Hora</th>";
            html += "<th>Grupo</th>";
            html += "<th>";
            html += "Lugar";
            html += "</th>";
            html += "<th>";
            html += "Estado";
            html += "</th>";
            html += "<th>";
            html += "ACCIÓN";
            html += "</th>";

        }
    }


    $contenedor.find("thead").html(html);

}



function estado(id) {
    if (id == 1) {
        return "En Agenda"
    }
    ;
    if (id == 2) {
        return "En curso"
    }
    ;
    if (id == 3) {
        return "Finalizada"
    }

}

function cargar_items_agenda($contenedor, data) {

    var html = '';
    for (var i = 0; i < data.length; i++) {

        d = data[i];
        html += '<div class="col-md-4">' +
                '<div class="row games-team">';
        if (d.categoria.tipo_modalidad == 1) {
            html += '<div class="col-md-5">' +
                    '<span>' + d.equipos[0].nombre + '</span><br>' +
                    '<img src="images/delegaciones/' + d.equipos[0].delegacion.imagen + '" class="img-responsive"/>' +
                    '</div>' +
                    '<div class="col-md-2">' +
                    '</div>' +
                    '<div class="col-md-5">' +
                    '<span>' + d.equipos[1].nombre + '</span><br>' +
                    '<img src="images/delegaciones/' + d.equipos[1].delegacion.imagen + '" class="img-responsive"/>' +
                    '</div>' +
                    '</div>';
        } else {
            html += '<div class="col-md-5">' +
                    '<span>Bangladesh</span>' +
                    '</div>' +
                    '<div class="col-md-2">' +
                    '</div>' +
                    '<div class="col-md-5">' +
                    '<span>Australia</span>' +
                    '</div>' +
                    '</div>';
        }


        html += '<div class="row games-info">' +
                '<div class="col-md-12">' +
                '<p><span class="glyphicon glyphicon-play-circle"></span> 19 March, 2014 (<small>15:30 local | 09:30 GMT</small>)</p>' +
                '<p class="games-dash"></p>' +
                '<p><small>Mirpur Internation Stadium, Dhaka</small></p>' +
                '</div>' +
                '</div>' +
                '</div>  ';
    }

    $contenedor.html(html);
}