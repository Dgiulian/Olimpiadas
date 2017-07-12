
var categorias = [];
var deportes   = [];
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarCategoria({});
    });
    loadDeportes();
    filtrarCategoria();
});
function filtrarCategoria(){
    var data = {};
    loadDataCategoria(data);
}
function loadDeportes(data){
    $.ajax({
        url: URLS.DEPORTE_LIST,
        data: data,
        method:"POST",
        dataType: "json",           
        success: function(data) {
            if(data.Result === "OK") {
                deportes = data.Records;
            }
        }
     });
}
function loadDataCategoria(data){
    var $tabla = $('#tblCategoria');
    $.ajax({
           url: URLS.CATEGORIA_LIST,
           data: data,
           method:"POST",
           dataType: "json",
           beforeSend:function(){
                var cant_cols = $tabla.find('thead th').length;
                $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='images/ajax-loader.gif'/></center></td></tr>");
           },
           success: function(data) {
               if(data.Result === "OK") {
                   categorias = data.Records;
                   $tabla.find('tbody').html(createTable(categorias));
                    $('.btn-del').click(borrarCategoria);
                    $('.btn-edit').click(editarCategoria);
               }
           }
       });
    }
    function borrarCategoria(){
        var index = $(this).data('index');
        var id = categorias[index].id;        
        deleteData(URLS.CATEGORIA_DEL,{id:id},function(result) {     
            if(result.Result === "OK") {
                filtrarCategoria();
            } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    function createTable(data){
        var template = Handlebars.compile($("#categoria_list").html());
        return template({records:data});    
    }
    function editarCategoria(){
        var data = {};
        var index = $(this).data('index');
        data = categorias[index];
        agregarCategoria(data);
    }
    
    function agregarCategoria(data){
        var template = Handlebars.compile($('#categoria_edit').html());
        data.deportes = deportes;
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
                                url:URLS.CATEGORIA_EDIT,
                                data: data,
                                method:'POST',
                                dataType:'json',
                                success:function(){
                                    filtrarCategoria();
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
        data.id_deporte = $('#id_deporte').val();
        data.nombre = $('#nombre').val();
        data.detalle  = $('#detalle').val();
        return data;   
    }