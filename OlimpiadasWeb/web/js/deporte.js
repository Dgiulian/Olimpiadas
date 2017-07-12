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
function loadDataDeporte(data){
    var $tabla = $('#tblDeporte');
    $.ajax({
           url: URLS.DEPORTE_LIST,
           data: data,
           method:"POST",
           dataType: "json",
           beforeSend:function(){
                var cant_cols = $tabla.find('thead th').length;
                $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='images/ajax-loader.gif'/></center></td></tr>");
           },
           success: function(data) {
               if(data.Result === "OK") {
                   deportes = data.Records;
                   $tabla.find('tbody').html(createTable(deportes));
                   $('.btn-del').click(borrarDeporte);
                   $('.btn-edit').click(editarDeporte);
               }
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
    function createTable(data){
        var template = Handlebars.compile($("#deporte_list").html());
        return template({records:data});    
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
                            var data = recuperarCampos();
                            $.ajax({
                                url:URLS.DEPORTE_EDIT,
                                data: data,
                                method:'POST',
                                dataType:'json',
                                success:function(){
                                    filtrarDeporte();
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
        data.tipo = $('#tipo').val();
        data.cantidad_jugadores = $('#cantidad_jugadores').val();
        data.detalle  = $('#detalle').val();
        return data;   
    }