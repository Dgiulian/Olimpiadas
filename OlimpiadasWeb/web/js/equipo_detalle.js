
var equipo_detalles = [];
var jugadores   = [];
$(document).ready(function(){
    $('#id_jugador').multiSelect({        
        selectableHeader:"<h4 class='muliselect_headder'>Jugadores</h4>",
        selectionHeader:"<h4 class='muliselect_headder'>Incluidos</h4>"
    });    
    
//    $('#btnNuevo').click(function(){
//        agregarEquipo_detalle({});
//    });
//    loadJugadores({id_delegacion:$('#id_delegacion').val()});
//    filtrarEquipo_detalle();
});
function filtrarEquipo_detalle(){
    var data = {};
    data.id_equipo = $('#id_equipo').val();
    loadDataEquipo_detalle(data);
}
function loadJugadores(filtro){
    $.ajax({
        url: URLS.JUGADOR_LIST,
        data: filtro,
        method:"POST",
        dataType: "json",           
        success: function(data) {
            if(data.Result === "OK") {
                jugadores = data.Records;
            }
        }
    });
}
function loadDataEquipo_detalle(filter){
    var $tabla = $('#tblEquipo_detalle');
    setLoader($tabla);
    $.ajax({
           url: URLS.EQUIPO_DETALLE_LIST,
           data: filter,
           method:"POST",
           dataType: "json",
       }).done(function(result) {
            if(result.Result === "OK") {
                equipo_detalles = result.Records;
                createTable($tabla,equipo_detalles)
            }
        });
    }
    function borrarEquipo_detalle(){
        var index = $(this).data('index');
        var id = equipo_detalles[index].id;        
        deleteData(URLS.EQUIPO_DETALLE_DEL,{id:id},function(result) {     
            if(result.Result === "OK") {
                filtrarEquipo_detalle();
            } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    function createTable($tabla,data){        
        var template = Handlebars.compile($("#equipo_detalle_list").html());        
        $tabla.find('tbody').html(template({records:data}));
        $('.btn-del').click(borrarEquipo_detalle);
        $('.btn-edit').click(editarEquipo_detalle);
    }
    function editarEquipo_detalle(){
        var data = {};
        var index = $(this).data('index');
        data = equipo_detalles[index];
        agregarEquipo_detalle(data);
    }
    
    function agregarEquipo_detalle(data){
        var template = Handlebars.compile($('#equipo_detalle_edit').html());
        data.jugadores = jugadores;
        bootbox.dialog({
            title: "Configuraci&oacute;n de equipo_detalle",
            message: template(data), 
            buttons: {
                success: {
                    label: "Guardar",
                    className: "btn-success",
                    callback: function () {
                        var campos = recuperarCampos();
                        guardarEquipo_detalle(campos);
                    }
                },
                cancel: {
                    label: "Cancelar",
                    callback: function () {}
                }
            }
        });
    }
function guardarEquipo_detalle(data){
    $.ajax({
        url:URLS.EQUIPO_DETALLE_EDIT,
        data: data,
        method:'POST',
        dataType:'json',
    }).done(function(){
        filtrarEquipo_detalle();
    });      
}
function recuperarCampos(){
    var data = {};
    data.id = $('#id').val();
    data.id_equipo = $('#id_equipo').val();
    data.id_jugador = $('#id_jugador').val();    
    return data;   
}