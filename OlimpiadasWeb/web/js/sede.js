/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var sedes = [];
var templates = {};
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarSede({});
    });
    $('#btnSearch').click(filtrarSede);
    templates['list'] = Handlebars.compile($("#sede_list").html());
    templates['edit'] = Handlebars.compile($('#sede_edit').html());
    filtrarSede();
});
function filtrarSede(){
    var data = getSearchData();
    loadDataSede(data);
}
function loadDataSede(filter){
    var $tabla = $('#tblSede');
    setLoader($tabla);
    if(typeof filter==="undefined") filter = {};
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
    var template = templates['list'];
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
    var template = templates['edit'];
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
        processData:false,                
        contentType:false,
        dataType:'json' 
    }).done(function(response){
        if(response.Result !=="OK") bootbox.alert(response.Message);
        filtrarSede();
    });
}
function recuperarCampos(){
    var data = new FormData();
    var campos = getFormData($('form'));
    for( let d in campos){    
        data.append(d,campos[d]);
    }
    data.append('imagen', $('#imagen')[0].files[0]);    
    
    return data;
}
function getSearchData(){
    var data = {};
    data.nombre = $('#search_nombre').val();
    data.id_delegacion = $('#id_delegacion_filtro').val()
    return data;
}