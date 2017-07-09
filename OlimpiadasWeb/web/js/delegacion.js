$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarDelegacion({});
    });
    filtrarDelegacion();
});
function filtrarDelegacion(){
    var data = {};
    loadDataDelegacion(data);
}
function loadDataDelegacion(data){
    var $tabla = $('#tblDelegacion');
    $tabla.DataTable().destroy();
    $.ajax({
           url: URLS.DELEGACION_LIST,
           data: data,
           method:"POST",
           dataType: "json",
           beforeSend:function(){
                var cant_cols = $tabla.find('thead th').length;
                $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='images/ajax-loader.gif'/></center></td></tr>");
           },
           success: function(data) {
               if(data.Result === "OK") {
                   $tabla.find('tbody').html(createTable(data.Records));
                    $('.btn-del').click(borrarDelegacion);
                    $('.btn-edit').click(editarDelegacion);
               }
           }
       });
    }
    function borrarDelegacion(){
        var id = $(this).data('index');
        var $tr = $(this).parent().parent();
        deleteData(URLS.DELEGACION_DEL,{id:id},function(result) {     
                if(result.Result === "OK") {
                    $tr.remove();
                } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    function createTable(data){
        var template = Handlebars.compile($("#delegacion_list").html());
        return template({records:data});    
    }
    function editarDelegacion(){
        var data = {};
        data.id = $(this).data('index');
        data.nombre = $(this).data('nombre');
        data.observaciones  = $(this).data('observaciones');        
        agregarDelegacion(data);
    }
    
    function agregarDelegacion(data){
        var template = Handlebars.compile($('#delegacion_edit').html());
        bootbox.dialog({
                title: "Configuraci&oacute;n de delegaci&oacute;n",
                message: template(data), 
                buttons: {
                    success: {
                        label: "Guardar",
                        className: "btn-success",
                        callback: function () {
                            var data = recuperarCampos();
                            $.ajax({
                                url:URLS.DELEGACION_EDIT,
                                data: data,
                                method:'POST',
                                dataType:'json',
                                success:function(){
                                    filtrarDelegacion();
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
        data.nombre = $('#nombre').val();
        data.observaciones  = $('#observaciones').val();
        return data;   
    }