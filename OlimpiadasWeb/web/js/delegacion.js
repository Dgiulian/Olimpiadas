var delegaciones = [];
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
    $.ajax({
           url: URLS.DELEGACION_LIST,
           data: data,
           method:"POST",
           dataType: "json",
           beforeSend:function(){
                var cant_cols = $tabla.find('thead th').length;
                $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='images/ajax-loader.gif'/></center></td></tr>");
           }
       }).done( function(data) {
            if(data.Result === "OK") {
                delegaciones = data.Records;
                createTable($tabla,delegaciones)
            }
        });
    }
    function borrarDelegacion(){
        var index = $(this).data('index');
        var id = delegaciones[index].id;
        deleteData(URLS.DELEGACION_DEL,{id:id},function(result) {     
                if(result.Result === "OK") {
                    filtrarDelegacion();
                } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    function createTable($tabla,data){
        var template = Handlebars.compile($("#delegacion_list").html());
        $tabla.find('tbody').html(template({records:data}));
        $('.btn-del').click(borrarDelegacion);
        $('.btn-edit').click(editarDelegacion);        
    }
    function editarDelegacion(){
        var data = {};
        var index = $(this).data('index');
        data = delegaciones[index];        
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
                        var campos = recuperarCampos();
                        guardarDelegacion(campos);
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
    function guardarDelegacion(data){
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
    function recuperarCampos(){
        var data = {};
        data.id = $('#id').val();
        data.nombre = $('#nombre').val();
        data.observaciones  = $('#observaciones').val();
        return data;   
    }