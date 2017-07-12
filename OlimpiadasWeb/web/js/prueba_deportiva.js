
var pruebas    = [];
var deportes   = [];
var categorias = [];
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarPrueba({});
    });
    loadDeportes();
    loadCategorias();
    filtrarPrueba();
});
function filtrarPrueba(){
    var data = {};
    loadDataPrueba(data);
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
function loadCategorias(data){
    $.ajax({
        url: URLS.CATEGORIA_LIST,
        data: data,
        method:"POST",
        dataType: "json",
     }).done(function(data) {
        if(data.Result === "OK") {
            categorias = data.Records;
        }
    });
}
function loadDataPrueba(filter){
    var $tabla = $('#tblPrueba');
    $.ajax({
           url: URLS.PRUEBA_LIST,
           data: filter,
           method:"POST",
           dataType: "json",
           beforeSend:function(){
                var cant_cols = $tabla.find('thead th').length;
                $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='images/ajax-loader.gif'/></center></td></tr>");
           },
    }).done(function(result) {
        if(result.Result === "OK") {
            pruebas = result.Records;
            createTable($tabla,pruebas)                   
        }
    });
}
function borrarPrueba(){
    var index = $(this).data('index');
    var id = pruebas[index].id;        
    deleteData(URLS.PRUEBA_DEL,{id:id},function(result) {     
        if(result.Result === "OK") {
            filtrarPrueba();
        } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){
    var template = Handlebars.compile($("#prueba_list").html());
    $tabla.find('tbody').html(template({records:data}));
    $('.btn-del').click(borrarPrueba);
    $('.btn-edit').click(editarPrueba);
}
function editarPrueba(){
    var data = {};
    var index = $(this).data('index');
    data = pruebas[index];
    agregarPrueba(data);
}

function agregarPrueba(data){
    var template = Handlebars.compile($('#prueba_edit').html());
    data.deportes  = deportes;
    data.categorias = categorias;
    bootbox.dialog({
        title: "Configuraci&oacute;n de prueba deportiva",
        message: template(data), 
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function () {
                    var campos = recuperarCampos();
                    guardarPrueba(campos);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function () {}
            }
        }
    });
}
function guardarPrueba(data){
    $.ajax({
        url:URLS.PRUEBA_EDIT,
        data: data,
        method:'POST',
        dataType:'json',
        success:function(){
            filtrarPrueba();
        }
    });   
}
function recuperarCampos(){
    var data = {};
    data.id = $('#id').val();
    data.id_deporte    = $('#id_deporte').val();
    data.id_categoria  = $('#id_categoria').val();        
    data.observaciones = $('#observaciones').val();
    return data;   
}