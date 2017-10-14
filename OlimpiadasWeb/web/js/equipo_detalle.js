
var equipo_detalles = [];
var jugadores = [];
var templates = {};
$(document).ready(function() {
    /*$('#id_jugador').multiSelect({
     selectableHeader: "<h4 class='muliselect_headder'>Jugadores</h4>",
     selectionHeader: "<h4 class='muliselect_headder'>Incluidos</h4>"
     });*/



    $('#id_jugador').multiSelect(
            {
                selectableHeader: "<input type='text' class='search-input form-control' autocomplete='off' placeholder='Buscar'>",
                selectionHeader: "<input type='text' class='search-input form-control' autocomplete='off' placeholder='Buscar'>",
                afterInit: function(ms) {
                    var that = this,
                            $selectableSearch = that.$selectableUl.prev(),
                            $selectionSearch = that.$selectionUl.prev(),
                            selectableSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selectable:not(.ms-selected)',
                            selectionSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selection.ms-selected';

                    that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
                            .on('keydown', function(e) {
                                if (e.which === 40) {
                                    that.$selectableUl.focus();
                                    return false;
                                }
                            });

                    that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
                            .on('keydown', function(e) {
                                if (e.which == 40) {
                                    that.$selectionUl.focus();
                                    return false;
                                }
                            });
                },
                afterSelect: function() {
                    this.qs1.cache();
                    this.qs2.cache();
                },
                afterDeselect: function() {
                    this.qs1.cache();
                    this.qs2.cache();
                }
            });
//    templates['list'] = Handlebars.compile($("#equipo_detalle_list").html());
//    templates['edit'] = Handlebars.compile($("#equipo_detalle_edit").html());
//    $('#btnNuevo').click(function(){
//        agregarEquipo_detalle({});
//    });
//    loadJugadores({id_delegacion:$('#id_delegacion').val()});
//    filtrarEquipo_detalle();


});
function filtrarEquipo_detalle() {
    var data = {};
    data.id_equipo = $('#id_equipo').val();
    loadDataEquipo_detalle(data);
}
function loadJugadores(filtro) {
    $.ajax({
        url: URLS.JUGADOR.LIST,
        data: filtro,
        method: "POST",
        dataType: "json",
        success: function(data) {
            if (data.Result === "OK") {
                jugadores = data.Records;
            }
        }
    });
}
function loadDataEquipo_detalle(filter) {
    var $tabla = $('#tblEquipo_detalle');
    setLoader($tabla);
    $.ajax({
        url: URLS.EQUIPO_DETALLE.LIST,
        data: filter,
        method: "POST",
        dataType: "json",
    }).done(function(result) {
        if (result.Result === "OK") {
            equipo_detalles = result.Records;
            createTable($tabla, equipo_detalles)
        }
    });
}
function borrarEquipo_detalle() {
    var index = $(this).data('index');
    var id = equipo_detalles[index].id;
    deleteData(URLS.EQUIPO_DETALLE.DEL, {id: id}, function(result) {
        if (result.Result === "OK") {
            filtrarEquipo_detalle();
        } else if (result.Message)
            bootbox.alert(result.Message);
    });
}
function createTable($tabla, data) {
    var template = templates['list'];
    $tabla.find('tbody').html(template({records: data}));
    $('.btn-del').click(borrarEquipo_detalle);
    $('.btn-edit').click(editarEquipo_detalle);
}
function editarEquipo_detalle() {
    var data = {};
    var index = $(this).data('index');
    data = equipo_detalles[index];
    agregarEquipo_detalle(data);
}

function agregarEquipo_detalle(data) {
    var template = templates['edit'];
    data.jugadores = jugadores;
    bootbox.dialog({
        title: "Configuraci&oacute;n de equipo_detalle",
        message: template(data),
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function() {
                    var campos = recuperarCampos();
                    guardarEquipo_detalle(campos);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function() {
                }
            }
        }
    });
}
function guardarEquipo_detalle(data) {
    $.ajax({
        url: URLS.EQUIPO_DETALLE.EDIT,
        data: data,
        method: 'POST',
        dataType: 'json',
    }).done(function() {
        filtrarEquipo_detalle();
    });
}
function recuperarCampos() {
    var data = {};
    data.id = $('#id').val();
    data.id_equipo = $('#id_equipo').val();
    data.id_jugador = $('#id_jugador').val();
    return data;
}