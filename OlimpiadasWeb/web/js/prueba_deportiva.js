
var pruebas    = [];
var deportes   = [];
var equipos    = [];
var grupos     = [];
var categorias = [];
var ms;
$(document).ready(init);
function init(){
    $('#btnNuevo').click(function(){
        agregarPrueba({});
    });
    window.Handlebars.registerHelper('descPuntaje',function(tipo_puntaje,orden_puntaje){
        var puntajes = ["Puntos","Tiempo"];
        var ordenes = ["Ascendente","Descendente"];
        
        return "" + puntajes[tipo_puntaje] + " (" + ordenes[orden_puntaje] +")";
    });
    loadDeportes({});
    filtrarPrueba();
    
}
function filtrarPrueba(){
    var data = {};
    loadDataPrueba(data);
}

function loadDeportes(filtro){
    return getDataDeportes(filtro).done(function(data) {
        if(data.Result === "OK") {
            deportes = data.Records;
        }
    });
}


function loadCategorias(filtro){
    filtro.id_deporte = $('#id_deporte').val();
    return getDataCategorias(filtro).done(function(data) {
        if(data.Result === "OK") {
            categorias = data.Records;
        }
    });
}
function loadGrupos(filtro){
    filtro.id_categoria = $('#id_categoria').val();
    return getDataGrupos(filtro).done(function(data) {
        if(data.Result === "OK") {
            grupos = data.Records;
        }
    });
}

function loadEquipos(filtro){
    filtro.id_grupo = $('#id_grupo').val();
    return getDataEquipos(filtro).done(function(data) {
        if(data.Result === "OK") {
            equipos = data.Records;
        }
    });
}
function loadDataPrueba(filter){
    var $tabla = $('#tblPrueba');
    setLoader($tabla);
    getDataPruebas(filter).done(function(result) {
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
    Promise.all([loadDeportes,loadCategorias]).then(function(resolve,reject){
        var template = Handlebars.compile($('#prueba_edit').html());    
        data.deportes   = deportes;
        data.categorias = categorias;
        data.equipos    = equipos;
        data.grupos     = grupos;
        data.disabled = data.id_estado > 1;
        if(data.detalle) data.selected = data.detalle.map(function(el,ind){ return el.id}).join(",");
        bootbox.dialog({
            title: "Configuraci&oacute;n de prueba deportiva",
            message: template(data),
            size:"large",
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
            $('#id_deporte').change(changeDeporte);
            $('#id_categoria').change(changeCategoria);
            $('#id_grupo').change(changeGrupo);
            $('#id_deporte').trigger('change');

        });
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
function changeDeporte(e){
    var id_deporte = $('#id_deporte').val();
    getDataCategorias({id_deporte:id_deporte}).done(function(result){
        if(result.Result === "OK") {
            categorias = result.Records;
            createOptionsFromArray('#id_categoria',categorias);
            $('#id_categoria').trigger('change');
        }
    });
}
function changeCategoria(e){
    var id_categoria = $('#id_categoria').val();
    getDataGrupos({id_categoria:id_categoria}).done(function(result){
        if(result.Result === "OK") {
            pruebas = result.Records;
            createOptionsFromArray('#id_grupo',pruebas);
            $('#id_grupo').trigger('change');
        }
    });
}

function changeGrupo(e){
    var id_grupo = $('#id_grupo').val();
    getDataEquipos({id_grupo}).done(function(result){
        if(result.Result === "OK") {
            equipos = result.Records;
            createOptionsFromArray('#equipos',equipos);
            $('#equipos').multiSelect('refresh');
        }
    });
}
function recuperarCampos(){
    var data = getFormData($('form'));
    return data;   
}

function getDataDeportes(filtro){
    return $.ajax({
        url: URLS.DEPORTE.LIST,
        data: filtro,
        method:"POST",
        dataType: "json",
    });
}
function getDataEquipos(filtro){
    return $.ajax({
        url: URLS.EQUIPO.LIST,
        data: filtro,
        method:"POST",
        dataType: "json",
    });
}
function getDataCategorias(filtro){
    return $.ajax({
        url: URLS.CATEGORIA.LIST,
        data: filtro,
        method:"POST",
        dataType: "json",
    });
}
function getDataPruebas(filtro){
    return $.ajax({
           url: URLS.PRUEBA.LIST,
           data: filtro,
           method:"POST",
           dataType: "json"
    });
}
function getDataGrupos(filtro){
    return $.ajax({
        url: URLS.GRUPO.LIST,
        data: filtro,
        method:"POST",
        dataType: "json",
    });
}