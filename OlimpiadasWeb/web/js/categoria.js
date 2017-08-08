
var categorias = [];
var deportes   = [];
var templates = {};
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarCategoria({});
    });
    $('#btnSearch').click(filtrarCategoria);
    templates['list'] = Handlebars.compile($("#categoria_list").html());
    templates['edit'] = Handlebars.compile($('#categoria_edit').html());
    loadDeportes({});
    filtrarCategoria();
});
function filtrarCategoria(){
    var data = getSearchData();
    loadDataCategoria(data);
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
function loadDataCategoria(filter){
    var $tabla = $('#tblCategoria');
    setLoader($tabla);
    if(typeof filter==="undefined") filter = {};
    $.ajax({
        url: URLS.CATEGORIA.LIST,
        data: filter,
        method:"GET",
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
    var template = templates['list'];
    $tabla.find('tbody').html(template({records:data}));
    $('.btn-del').click(borrarCategoria);
    $('.btn-edit').click(editarCategoria);
    $('.btn-grupo').click(redirigir_grupo);
}
function editarCategoria(){
    var data = {};
    var index = $(this).data('index');
    data = categorias[index];
    agregarCategoria(data);
}

function agregarCategoria(data){
    var template = templates['edit'];
    data.deportes = deportes;
    bootbox.dialog({
        title: "Configuraci&oacute;n de categoria",
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
    var data = getFormData($('form'));
    /*var data = {};
    data.id = $('#id').val();
    data.id_deporte = $('#id_deporte').val();
    data.nombre = $('#nombre').val();
    data.detalle  = $('#detalle').val();
    */
    return data;   
}

function redirigir_grupo(){
    var index = $(this).data('index');
    var id = categorias[index].id;        
    window.location = URLS.GRUPO.BASE + "?id_categoria=" + id;
}
function getSearchData(){
    var data = {};
    data.nombre = $('#search_nombre').val();
    data.id_deporte = $('#search_deporte').val();    
    return data;
}