
var equipos = [];
var delegaciones   = [];
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarEquipo({});
    });
    loadDelegaciones();
    filtrarEquipo();
});
function filtrarEquipo(){
    var data = {};
    loadDataEquipo(data);
}
function loadDelegaciones(data){
    $.ajax({
        url: URLS.DELEGACION_LIST,
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
function loadDataEquipo(filter){
    var $tabla = $('#tblEquipo');
    $.ajax({
           url: URLS.EQUIPO_LIST,
           data: filter,
           method:"POST",
           dataType: "json",
           beforeSend:function(){
                var cant_cols = $tabla.find('thead th').length;
                $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='images/ajax-loader.gif'/></center></td></tr>");
           },
           success: function(result) {
               if(result.Result === "OK") {
                   equipos = result.Records;
                   createTable($tabla,equipos)
               }
           }
       });
    }
    function borrarEquipo(){
        var index = $(this).data('index');
        var id = equipos[index].id;        
        deleteData(URLS.EQUIPO_DEL,{id:id},function(result) {     
            if(result.Result === "OK") {
                filtrarEquipo();
            } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    function createTable(data){
        var template = Handlebars.compile($("#equipo_list").html());        
        $tabla.find('tbody').html(template({records:data}));
        $('.btn-del').click(borrarEquipo);
        $('.btn-edit').click(editarEquipo);
    }
    function editarEquipo(){
        var data = {};
        var index = $(this).data('index');
        data = equipos[index];
        agregarEquipo(data);
    }
    
    function agregarEquipo(data){
        var template = Handlebars.compile($('#equipo_edit').html());
        data.delegaciones = delegaciones;
        bootbox.dialog({
                title: "Configuraci&oacute;n de equipo",
                message: template(data), 
                buttons: {
                    success: {
                        label: "Guardar",
                        className: "btn-success",
                        callback: function () {
                            var data = recuperarCampos();
                            $.ajax({
                                url:URLS.EQUIPO_EDIT,
                                data: data,
                                method:'POST',
                                dataType:'json',
                                success:function(){
                                    filtrarEquipo();
                                }
                            });                            
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
    function recuperarCampos(){
        var data = {};
        data.id = $('#id').val();
        data.id_delegacion = $('#id_delegacion').val();
        data.nombre = $('#nombre').val();
        data.detalle  = $('#detalle').val();
        return data;   
    }