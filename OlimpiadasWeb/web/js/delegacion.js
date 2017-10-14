var delegaciones = [];
var templates = {};
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarDelegacion({});
    });
    $('#btnSearch').click(filtrarDelegacion);
    templates['list'] = Handlebars.compile($("#delegacion_list").html());
    templates['edit'] = Handlebars.compile($('#delegacion_edit').html());
    filtrarDelegacion();
});
function filtrarDelegacion(){
    var data = getSearchData();
    loadDataDelegacion(data);
}
function loadDataDelegacion(filter){
    var $tabla = $('#tblDelegacion');
    setLoader($tabla);
    if(typeof filter==="undefined") filter = {};
    $.ajax({
           url: URLS.DELEGACION.LIST,
           data: filter,
           method:"GET",
           dataType: "json"
       }).done( function(result) {
            if(result.Result === "OK") {
                delegaciones = result.Records;
                createTable($tabla,delegaciones)
            }
        });
    }
function borrarDelegacion(){
    var index = $(this).data('index');
    var id = delegaciones[index].id;
    deleteData(URLS.DELEGACION.DEL,{id:id},function(result) {     
            if(result.Result === "OK") {
                filtrarDelegacion();
            } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){
    var template = templates['list'];
    $tabla.find('tbody').html(template({records:data}));
    $('.btn-del').click(borrarDelegacion);
    $('.btn-edit').click(editarDelegacion);        
}
function editarDelegacion(){
    var data = {};
    var index = $(this).data('index');
    data = delegaciones[index];        
    agregarDelegacion(data);
}

function agregarDelegacion(data){
    var template = templates['edit'];
    bootbox.dialog({
        title: "Configuraci&oacute;n de delegaci&oacute;n",
        message: template(data), 
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function () {
                    var campos = recuperarCampos();
                    guardarDelegacion(campos);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function () {}
            }
        }
    });
}
function guardarDelegacion(data){
    $.ajax({
        url:URLS.DELEGACION.EDIT,
        data: data,
        method:'POST',
        dataType:'json'        
    }).done(function(){
        filtrarDelegacion();
    });
}
function recuperarCampos(){
    var data = {};
    data.id = $('#id').val();
    data.nombre = $('#nombre').val();
    data.nombre_corto = $('#nombre_corto').val();
    data.observaciones  = $('#observaciones').val();
    return data;   
}

function getSearchData(){
    var data = {};
    data.search_nombre = $('#search_nombre').val();
    return data;
}