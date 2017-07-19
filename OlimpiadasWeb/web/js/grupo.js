
var grupos = [];
var equipos   = [];
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarGrupo({});
    });
    loadEquipos();
    filtrarGrupo();
});
function filtrarGrupo(){
    var data = {};
    loadDataGrupo(data);
}
function loadEquipos(data){
    $.ajax({
        url: URLS.EQUIPO.LIST,
        data: data,
        method:"POST",
        dataType: "json",
    }).done(function(data) {
        if(data.Result === "OK") {
            equipos = data.Records;
        }
    });
}
function loadDataGrupo(filter){
    var $tabla = $('#tblGrupo');
    setLoader($tabla);
    $.ajax({
        url: URLS.GRUPO.LIST,
        data: filter,
        method:"POST",
        dataType: "json"
    }).done(function(result) {
        if(result.Result === "OK") {
            grupos = result.Records;
            createTable($tabla,grupos)                   
        }
    });
}
function borrarGrupo(){
    var index = $(this).data('index');
    var id = grupos[index].id;        
    deleteData(URLS.GRUPO.DEL,{id:id},function(result) {     
        if(result.Result === "OK") {
            filtrarGrupo();
        } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){
    var template = Handlebars.compile($("#grupo_list").html());
    $tabla.find('tbody').html(template({records:data}));
    $('.btn-del').click(borrarGrupo);
    $('.btn-edit').click(editarGrupo);
}
function editarGrupo(){
    var data = {};
    var index = $(this).data('index');
    data = grupos[index];
    agregarGrupo(data);
}

function agregarGrupo(data){
    var template = Handlebars.compile($('#grupo_edit').html());
    data.equipos = equipos;
    if(data.detalle) data.selected = data.detalle.map(function(el,ind){ return el.id}).toString();
    else data.selected = "";
    
    bootbox.dialog({
        title: "Configuraci&oacute;n de grupo",
        message: template(data), 
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function () {
                    var campos = recuperarCampos();
                    guardarGrupo(campos);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function () {}
            },            
        }
    }).init(function(){
        $('#arrEquipo').multiSelect();
    });
}
function guardarGrupo(data){
    $.ajax({
        url:URLS.GRUPO.EDIT,
        data: data,
        method:'POST',
        dataType:'json'        
    }).done(function(){
        filtrarGrupo();
    });   
}
function recuperarCampos(){
    return getFormData($('form'));
}