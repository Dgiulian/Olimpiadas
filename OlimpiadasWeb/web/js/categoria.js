
var categorias = [];
var deportes   = [];
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarCategoria({});
    });
    loadDeportes();
    filtrarCategoria();
});
function filtrarCategoria(){
    var data = {};
    loadDataCategoria(data);
}
function loadDeportes(data){
    $.ajax({
        url: URLS.DEPORTE.LIST,
        data: data,
        method:"POST",
        dataType: "json",
    }).done(function(data) {
        if(data.Result === "OK") {
            deportes = data.Records;
        }
    });
}
function loadDataCategoria(filter){
    var $tabla = $('#tblCategoria');
    setLoader($tabla);
    $.ajax({
        url: URLS.CATEGORIA.LIST,
        data: filter,
        method:"POST",
        dataType: "json"
    }).done(function(result) {
        if(result.Result === "OK") {
            categorias = result.Records;
            createTable($tabla,categorias)                   
        }
    });
}
function borrarCategoria(){
    var index = $(this).data('index');
    var id = categorias[index].id;        
    deleteData(URLS.CATEGORIA.DEL,{id:id},function(result) {     
        if(result.Result === "OK") {
            filtrarCategoria();
        } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){
    var template = Handlebars.compile($("#categoria_list").html());
    $tabla.find('tbody').html(template({records:data}));
    $('.btn-del').click(borrarCategoria);
    $('.btn-edit').click(editarCategoria);
}
function editarCategoria(){
    var data = {};
    var index = $(this).data('index');
    data = categorias[index];
    agregarCategoria(data);
}

function agregarCategoria(data){
    var template = Handlebars.compile($('#categoria_edit').html());
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
                    guardarCategoria(campos);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function () {}
            },
            grupo: {
                label: "Grupos",
                className:'btn-primary',
                callback: function () {
                    if(data.id){
                        window.location = URLS.GRUPO.BASE + "?id_categoria=" + data.id;
                    }else{
                        bootbox.alert("Debe guardar la categor&iacute; antes de editar los grupos");
                    }
                }
            }
        }
    });
}
function guardarCategoria(data){
    $.ajax({
        url:URLS.CATEGORIA.EDIT,
        data: data,
        method:'POST',
        dataType:'json'        
    }).done(function(){
        filtrarCategoria();
    });   
}
function recuperarCampos(){
    var data = {};
    data.id = $('#id').val();
    data.id_deporte = $('#id_deporte').val();
    data.nombre = $('#nombre').val();
    data.detalle  = $('#detalle').val();
    return data;   
}