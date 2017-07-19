var jugadores = [];
var delegaciones   = [];

$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarJugador({});
    });
    $('#id_delegacion').change(filtrarJugador);
    loadDelegaciones();
    filtrarJugador();
});
function loadDelegaciones(data){
    $.ajax({
        url: URLS.DELEGACION.LIST,
        data: data,
        method:"POST",
        dataType: "json",           
        success: function(data) {
            if(data.Result === "OK") {
                delegaciones = data.Records;
            }
        }
     });
}
function filtrarJugador(){
    var data = {};
    data.id_delegacion = $('#id_delegacion').val();
    loadDataJugador(data);
}
function loadDataJugador(filter){
    var $tabla = $('#tblJugador');
    setLoader($tabla);    
    $.ajax({
           url: URLS.JUGADOR.LIST,
           data: filter,
           method:"POST",
           dataType: "json"
       }).done(function(result) {
            if(result.Result === "OK") {
                jugadores = result.Records;
                createTable($tabla,jugadores)
            }
        });
}
function borrarJugador(){
    var index = $(this).data('index');
    var id = jugadores[index].id;
    deleteData(URLS.JUGADOR.DEL,{id:id},function(result) {     
            if(result.Result === "OK") {
                filtrarJugador();
            } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){    
    var template = Handlebars.compile($("#jugador_list").html());
    $tabla.find('tbody').html(template({records:data}));
    $('.btn-del').click(borrarJugador);
    $('.btn-edit').click(editarJugador);
}
function editarJugador(){
    var data = {};
    var index = $(this).data('index');
    data = jugadores[index];
    agregarJugador(data);
}

function agregarJugador(data){
    var template = Handlebars.compile($('#jugador_edit').html());
    data.delegaciones = delegaciones;
    bootbox.dialog({
        title: "Configuraci&oacute;n de jugador",
        message: template(data), 
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function () {
                    var campos = recuperarCampos();
                    guardarJugador(campos);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function () {
                }
            }
        }
    }).init(initDialog);
}
function guardarJugador(data){
    $.ajax({
        url:URLS.JUGADOR.EDIT,
        data: data,
        method:'POST',
        dataType:'json'        
    }).done(function(){
        filtrarJugador();
    });  
}
function recuperarCampos(){
    var data = {};
    data.id = $('#id').val();
    data.nombre_apellido = $('#nombre_apellido').val();
    data.matricula = $('#matricula').val();
    data.dni = $('#dni').val();
    data.fecha_nacimiento  = $('#fecha_nacimiento').val();
    data.id_delegacion = $('#id_delegacion').val();    
    
    return data;   
}