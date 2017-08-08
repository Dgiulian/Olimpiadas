
var usuarioapps = [];
var deportes   = [];
var templates = {};
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarUsuarioApp({});
    });
    $('#btnSearch').click(filtrarUsuarioApp);
    templates['list'] = Handlebars.compile($("#usuario_app_list").html());
    templates['edit'] = Handlebars.compile($('#usuario_app_edit').html());
    loadDeportes({});
    filtrarUsuarioApp();
});
function filtrarUsuarioApp(){
    var data = getSearchData();
    loadDataUsuarioApp(data);
}
function loadDeportes(filter){
    if(typeof filter ==="undefined") filter = {};
    $.ajax({
        url: URLS.DEPORTE.LIST,
        data: filter,
        method:"GET",
        dataType: "json",
    }).done(function(result) {
        if(result.Result === "OK") {
            deportes = result.Records;
        }
    });
}
function loadDataUsuarioApp(filter){
    var $tabla = $('#tblUsuarioApp');
    setLoader($tabla);
    if(typeof filter==="undefined") filter = {};
    $.ajax({
        url: URLS.USUARIOAPP.LIST,
        data: filter,
        method:"GET",
        dataType: "json"
    }).done(function(result) {
        if(result.Result === "OK") {
            usuarioapps = result.Records;
            createTable($tabla,usuarioapps)                   
        }
    });
}
function borrarUsuarioApp(){
    var index = $(this).data('index');
    var id = usuarioapps[index].id;        
    deleteData(URLS.USUARIOAPP.DEL,{id:id},function(result) {     
        if(result.Result === "OK") {
            filtrarUsuarioApp();
        } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){
    var template = templates['list'];
    $tabla.find('tbody').html(template({records:data}));
    $('.btn-del').click(borrarUsuarioApp);
    $('.btn-edit').click(editarUsuarioApp);    
}
function editarUsuarioApp(){
    var data = {};
    var index = $(this).data('index');
    data = usuarioapps[index];
    agregarUsuarioApp(data);
}

function agregarUsuarioApp(data){
    var template = templates['edit'];
    data.deportes = deportes;
    bootbox.dialog({
        title: "Configuraci&oacute;n de usuario App",
        message: template(data), 
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function () {
                    var campos = recuperarCampos();
                    guardarUsuarioApp(campos);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function () {}
            }
        }
    });
}
function guardarUsuarioApp(data){
    $.ajax({
        url:URLS.USUARIOAPP.EDIT,
        data: data,
        method:'POST',
        dataType:'json'        
    }).done(function(){
        filtrarUsuarioApp();
    });   
}
function recuperarCampos(){
    var data = getFormData($('form'));
    return data;   
}

function getSearchData(){
    var data = {};
    data.username = $('#search_username').val();
    data.email    = $('#search_email').val();
    data.nombre   = $('#search_nombre').val();
    data.apellido = $('#search_apellido').val();
    return data;
}