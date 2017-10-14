var jugadores = [];
var delegaciones = [];
var templates = {};
$(document).ready(function() {

    

    $('#btnNuevo').click(function() {
        agregarJugador({});
    });
    $('#btnPrint').click(function() {
        imprimir($('#id_delegacion_filtro').val());
    });
    $('#id_delegacion_filtro').change(filtrarJugador);
    $('#btnSearch').click(filtrarJugador);
    templates['edit'] = Handlebars.compile($('#jugador_edit').html());
    templates['list'] = Handlebars.compile($("#jugador_list").html());
    loadDelegaciones();
    filtrarJugador();
});
function loadDelegaciones(filter) {
    if (typeof filter === "undefined")
        filter = {};
    $.ajax({
        url: URLS.DELEGACION.LIST,
        data: filter,
        method: "GET",
        dataType: "json",
        success: function(data) {
            if (data.Result === "OK") {
                delegaciones = data.Records;
            }
        }
    });
}
function filtrarJugador() {
    var data = getSearchData();
    data.id_delegacion = $('#id_delegacion_filtro').val();
    loadDataJugador(data);
}
function loadDataJugador(filter) {
    var $tabla = $('#tblJugador');
    setLoader($tabla);
    if (typeof filter === "undefined")
        filter = {};
    $.ajax({
        url: URLS.JUGADOR.LIST,
        data: filter,
        method: "POST",
        dataType: "json",
    }).done(function(result) {
        if (result.Result === "OK") {
            jugadores = result.Records;
            createTable($tabla, jugadores)
        }
    });
}
function borrarJugador() {
    var index = $(this).data('index');
    var id = jugadores[index].id;
    deleteData(URLS.JUGADOR.DEL, {id: id}, function(result) {
        if (result.Result === "OK") {
            filtrarJugador();
        } else if (result.Message)
            bootbox.alert(result.Message);
    });
}
function createTable($tabla, data) {
    var template = templates['list'];
    $tabla.find('tbody').html(template({records: data}));
    $('.btn-del').click(borrarJugador);
    $('.btn-edit').click(editarJugador);
}
function editarJugador() {
    var data = {};
    var index = $(this).data('index');
    data = jugadores[index];
    agregarJugador(data);
}

function agregarJugador(data) {
    var template = templates['edit'];
    data.delegaciones = delegaciones;
    bootbox.dialog({
        title: "Configuraci&oacute;n de jugador",
        message: template(data),
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function() {
                    var campos = recuperarCampos();
                    guardarJugador(campos);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function() {
                }
            }
        }
    }).init(initDialog);
}
function guardarJugador(data) {
    $.ajax({
        url: URLS.JUGADOR.EDIT,
        data: data,
        method: 'POST',
        dataType: 'json',
        processData: false,
        contentType: false,
    }).done(function() {
        filtrarJugador();
    });
}
function recuperarCampos() {
    /*var data = {};
     data.id = $('#id').val();
     data.nombre_apellido = $('#nombre_apellido').val();
     data.matricula = $('#matricula').val();
     data.dni = $('#dni').val();
     data.fecha_nacimiento = $('#fecha_nacimiento').val();
     data.id_delegacion = $('#id_delegacion').val();
     alert(JSON.stringify(data));
     
     return data;*/


    var data = new FormData();
    var campos = getFormData($('form'));
    for (let d in campos) {
        data.append(d, campos[d]);
    }

    return data;
}

function imprimir(id) {
    var url = "http://179.43.127.107/reportes_olimpiadas/delegacion.php?id=" + id;
    window.open(url, '_blank');
}

function getSearchData() {
    var data = {};
    data.nombre = $('#search_nombre').val();
    data.id_delegacion = $('#id_delegacion_filtro').val()
    return data;
}