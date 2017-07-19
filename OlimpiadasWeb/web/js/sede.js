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
function loadDataSede(filter){
    var $tabla = $('#tblSede');
    setLoader($tabla);
    $.ajax({
           url: URLS.SEDE.LIST,
           data: filter,
           method:"POST",
           dataType: "json"
        }).done(function(result) {
            if(result.Result === "OK") {
                 sedes = result.Records;
                 createTable($tabla,result.Records)
            }
        });
    }
function borrarSede(){
    var index = $(this).data('index');
    var id = sedes[index].id;        
    deleteData(URLS.SEDE.DEL,{id:id},function(result) {     
            if(result.Result === "OK") {
                filtrarSede();
            } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){
    var template = Handlebars.compile($("#sede_list").html());
    $tabla.find('tbody').html(template({records:data}));
    $('.btn-del').click(borrarSede);
    $('.btn-edit').click(editarSede);
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
                callback: function(){
                    var campos = recuperarCampos();
                    guardarSede(campos);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function () {}
            }
        }
    });
}
function guardarSede(data){
    $.ajax({
        url:URLS.SEDE.EDIT,
        data: data,
        method:'POST',
        dataType:'json'        
    }).done(function(){
        filtrarSede();
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