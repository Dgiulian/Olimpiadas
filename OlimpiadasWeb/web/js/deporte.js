var deportes = [];

$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarDeporte({});
    });
   window.Handlebars.registerHelper('tipoDeporte', function(tipo) {
       var tiposDeporte = ["","Individual","Colectivo"];
       return tiposDeporte[tipo];
    });
  
    filtrarDeporte();
    
});
function filtrarDeporte(){
    var data = {};
    loadDataDeporte(data);
}
function loadDataDeporte(filter){
    var $tabla = $('#tblDeporte');
    setLoader($tabla);    
    $.ajax({
           url: URLS.DEPORTE_LIST,
           data: filter,
           method:"POST",
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
    deleteData(URLS.DEPORTE_DEL,{id:id},function(result) {     
            if(result.Result === "OK") {
                filtrarDeporte();
            } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){
    var template = Handlebars.compile($("#deporte_list").html());
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
    var template = Handlebars.compile($('#deporte_edit').html());
    bootbox.dialog({
        title: "Configuraci&oacute;n de delegaci&oacute;n",
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
        url:URLS.DEPORTE_EDIT,
        data: data,
        method:'POST',
        dataType:'json',
        success: function(){
            filtrarDeporte();
        }
    });  
}
function recuperarCampos(){
    var data = {};
    data.id = $('#id').val();
    data.nombre = $('#nombre').val();
    data.tipo = $('#tipo').val();
    data.cantidad_jugadores = $('#cantidad_jugadores').val();
    data.detalle  = $('#detalle').val();
    return data;   
}