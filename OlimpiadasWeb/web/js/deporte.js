var deportes = [];
var templates = {};
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarDeporte({});
    });
   window.Handlebars.registerHelper('tipoDeporte', function(tipo) {
       var tiposDeporte = ["","Individual","Colectivo"];
       return tiposDeporte[tipo];
    });
     $('#btnSearch').click(filtrarDeporte);
    templates['list'] = Handlebars.compile($("#deporte_list").html());
    templates['edit'] = Handlebars.compile($('#deporte_edit').html());
    filtrarDeporte();
    
});
function filtrarDeporte(){
    var data = getSearchData();
    loadDataDeporte(data);
}
function loadDataDeporte(filter){
    var $tabla = $('#tblDeporte');
    setLoader($tabla);
    if(typeof filter==="undefined") filter = {};
    $.ajax({
           url: URLS.DEPORTE.LIST,
           data: filter,
           method:"GET",
           dataType: "json"
       }).done(function(result) {
            if(result.Result === "OK") {
                deportes = result.Records;
                createTable($tabla,deportes)
            }
        });
}
function borrarDeporte(){
    var index = $(this).data('index');
    var id = deportes[index].id;
    deleteData(URLS.DEPORTE.DEL,{id:id},function(result) {     
            if(result.Result === "OK") {
                filtrarDeporte();
            } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){
    var template = templates['list'];
    $tabla.find('tbody').html(template({records:data}));
    $('.btn-del').click(borrarDeporte);
    $('.btn-edit').click(editarDeporte);
}
function editarDeporte(){
    var data = {};
    var index = $(this).data('index');
    data = deportes[index];
    agregarDeporte(data);
}

function agregarDeporte(data){
    var template = templates['edit'];
    bootbox.dialog({
        title: "Configuraci&oacute;n de deporte",
        message: template(data), 
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function () {
                    var campos = recuperarCampos();
                    guardarDeporte(campos);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function () {
                }
            }
        }
    });
}
function guardarDeporte(data){
    $.ajax({
        url:URLS.DEPORTE.EDIT,
        data: data,
        method:'POST',
        processData:false,        
        dataType:'json',
        contentType:false,
    }).done(function(){
        filtrarDeporte();
    });  
}
function recuperarCampos(){
    var data = new FormData();
    data.append('id', $('#id').val());
    data.append('nombre', $('#nombre').val());
    data.append('tipo', $('#tipo').val());
    data.append('cantidad_jugadores', $('#cantidad_jugadores').val());
    data.append('detalle', $('#detalle').val());
    data.append('reglamento', $('#reglamento')[0].files[0]);
    return data;   
}
function getSearchData(){
    var data = {};
    data.nombre = $('#search_nombre').val();
    return data;
}