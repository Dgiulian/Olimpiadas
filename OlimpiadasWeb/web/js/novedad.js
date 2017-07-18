
var novedades = [];
var deportes   = [];
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarNovedad({});
    });
    loadDeportes();
    filtrarNovedad();
});
function filtrarNovedad(){
    var data = {};
    loadDataNovedad(data);
}
function loadDeportes(data){
    $.ajax({
        url: URLS.DEPORTE_LIST,
        data: data,
        method:"POST",
        dataType: "json",
    }).done(function(data) {
        if(data.Result === "OK") {
            deportes = data.Records;
        }
    });
}
function loadDataNovedad(filter){
    var $tabla = $('#tblNovedad');
    setLoader($tabla);
    $.ajax({
        url: URLS.NOVEDAD.LIST,
        data: filter,
        method:"POST",
        dataType: "json"
    }).done(function(result) {
        if(result.Result === "OK") {
            novedades = result.Records;
            createTable($tabla,novedades)                   
        }
    });
}
function borrarNovedad(){
    var index = $(this).data('index');
    var id = novedades[index].id;        
    deleteData(URLS.NOVEDAD.DEL,{id:id},function(result) {     
        if(result.Result === "OK") {
            filtrarNovedad();
        } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){
    var template = Handlebars.compile($("#novedad_list").html());
    $tabla.find('tbody').html(template({records:data}));
    $('.btn-del').click(borrarNovedad);
    $('.btn-edit').click(editarNovedad);
}
function editarNovedad(){
    var data = {};
    var index = $(this).data('index');
    data = novedades[index];
    agregarNovedad(data);
}

function agregarNovedad(data){
    var template = Handlebars.compile($('#novedad_edit').html());
    data.deportes = deportes;
    bootbox.dialog({
        title: "Configuraci&oacute;n de delegaci&oacute;n",
        message: template(data), 
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function () {
                    var campos = recuperarCampos();
                    guardarNovedad(campos);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function () {}
            }
        }
    }).init(initDialog);
}
function guardarNovedad(data){
    $.ajax({
        url:URLS.NOVEDAD.EDIT,
        data: data,
        method:'POST',
        dataType:'json'        
    }).done(function(){
        filtrarNovedad();
    });   
}
function recuperarCampos(){
    return data = getFormData($('form'));
}