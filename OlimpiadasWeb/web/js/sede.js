/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var sedes = [];
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarSede({});
    });
    filtrarSede();
});
function filtrarSede(){
    var data = {};
    loadDataSede(data);
}
function loadDataSede(data){
    var $tabla = $('#tblSede');
    //$tabla.DataTable().destroy();
    $.ajax({
           url: URLS.SEDE_LIST,
           data: data,
           method:"POST",
           dataType: "json",
           beforeSend:function(){
                var cant_cols = $tabla.find('thead th').length;
                $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='images/ajax-loader.gif'/></center></td></tr>");
           },
           success: function(data) {
               if(data.Result === "OK") {
                   sedes = data.Records;
                   $tabla.find('tbody').html(createTable(data.Records));
                    $('.btn-del').click(borrarSede);
                    $('.btn-edit').click(editarSede);
//                    $tabla.DataTable({
//                            responsive: true,
//                            retrieve: true,
//                            paging: false,
//                            ordering: true,
//                            searching: false,
//                            lengthChange:false,
//                            bInfo: false,
//                            language: {
//                                url:'vendor/datatables-plugins/i18n/Spanish.json',
//                            }
//                    });
               }
           }
       });
    }
    function borrarSede(){
        var index = $(this).data('index');
        var id = sedes[index].id;        
        deleteData(URLS.SEDE_DEL,{id:id},function(result) {     
                if(result.Result === "OK") {
                    filtrarSede();
                } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    function createTable(data){
        var template = Handlebars.compile($("#sede_list").html());
        return template({records:data});    
    }
    function editarSede(){
        var data = {};
        var id = $(this).data('index');
        data = sedes[id];
        agregarSede(data);
    }
    
    function agregarSede(data){
        var template = Handlebars.compile($('#sede_edit').html());
        bootbox.dialog({
                title: "Configuraci&oacute;n de sede",
                message: template(data), 
                buttons: {
                    success: {
                        label: "Guardar",
                        className: "btn-success",
                        callback: function () {
                            var data = recuperarCampos();
                            $.ajax({
                                url:URLS.SEDE_EDIT,
                                data: data,
                                method:'POST',
                                dataType:'json',
                                success:function(){
                                    filtrarSede();
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
        data.direccion = $('#direccion').val();
        data.observaciones  = $('#observaciones').val();
        return data;   
    }