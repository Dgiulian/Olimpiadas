
var novedades = [];
var deportes   = [];
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarNovedad({});
    });
    filtrarNovedad();
});
function filtrarNovedad(){
    var data = {};
    loadDataNovedad(data);
}

function loadDataNovedad(filter){
    var $tabla = $('#tblNovedad');
    setLoader($tabla);
    if(typeof filter==="undefined") filter = {};
    $.ajax({
        url: URLS.NOVEDAD.LIST,
        data: filter,
        method:"GET",
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
        processData:false,                
        contentType:false,
        dataType:'json'        
    }).done(function(response){
        if(response.Result !=="OK") bootbox.alert(response.Message);
        filtrarNovedad();
    });   
}
function recuperarCampos(){
    var data = new FormData();
    var campos = getFormData($('form'));
    for( let d in campos){    
        data.append(d,campos[d]);
    }
    data.append('imagen', $('#imagen')[0].files[0]);    
    
    return data;
}