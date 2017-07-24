
var pruebas    = [];
var deportes   = [];
var equipos    = [];
var categorias = [];
$(document).ready(init);
function init(){
    $('#btnNuevo').click(function(){
        agregarPrueba({});
    });
    window.Handlebars.registerHelper('descPuntaje',function(tipo_puntaje,orden_puntaje){
        var puntajes = ["","Puntos","Tiempo"];
        var ordenes = ["","Ascendente","Descendente"];
        
        return "" + puntajes[tipo_puntaje] + " (" + ordenes[orden_puntaje] +")";
    });
    loadDeportes();
    loadCategorias();
    loadEquipos();
    filtrarPrueba();
    
}
function filtrarPrueba(){
    var data = {};
    loadDataPrueba(data);
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
function loadCategorias(data){
    $.ajax({
        url: URLS.CATEGORIA.LIST,
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
    setLoader($tabla);
    $.ajax({
           url: URLS.PRUEBA.LIST,
           data: filter,
           method:"POST",
           dataType: "json"
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
    deleteData(URLS.PRUEBA.DEL,{id:id},function(result) {     
        if(result.Result === "OK") {
            filtrarPrueba();
        } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){
    var template = Handlebars.compile($("#prueba_list").html());
    console.log(data.deporte);
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
    data.deportes   = deportes;
    data.categorias = categorias;
    data.equipos    = equipos;
    data.disabled = data.id_estado > 1;
    if(data.detalle) data.selected = data.detalle.map(function(el,ind){ return el.id}).join(",");
    bootbox.dialog({
        title: "Configuraci&oacute;n de prueba deportiva",
        message: template(data),
        //size:"large",
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
    }).init(function(){
        initDialog();
        $('#equipos').multiSelect();
    });
}
function guardarPrueba(data){
    $.ajax({
        url:URLS.PRUEBA.EDIT,
        data: data,
        method:'POST',
        dataType:'json'
    }).done(function(){
        filtrarPrueba();
    });
}
function recuperarCampos(){
    var data = getFormData($('form'));
    return data;   
}