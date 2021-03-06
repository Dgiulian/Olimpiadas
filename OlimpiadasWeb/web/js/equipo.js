
var equipos = [];
var delegaciones   = [];
var templates = {};
$(document).ready(function(){
    $('#btnNuevo').click(function(){
        agregarEquipo({});
    });
    $('#id_delegacion_filtro').change(filtrarEquipo);
    $('#id_categoria_filtro').change(filtrarEquipo);
    $('#btnSearch').click(filtrarEquipo);
    templates['list'] = Handlebars.compile($("#equipo_list").html());
    templates['edit'] = Handlebars.compile($('#equipo_edit').html());
    loadDelegaciones();
    filtrarEquipo();
});
function filtrarEquipo(){
    var data = getSearchData();
    loadDataEquipo(data);
}
function loadDelegaciones(filter){
    if(typeof filter==="undefined") filter = {};
    $.ajax({
        url: URLS.DELEGACION.LIST,
        data: filter,
        method:"GET",
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
    setLoader($tabla);
    if(typeof filter==="undefined") filter = {};
    $.ajax({
           url: URLS.EQUIPO.LIST,
           data: filter,
           method:"GET",
           dataType: "json",
    }).done(function(result) {
         if(result.Result === "OK") {
             equipos = result.Records;
             createTable($tabla,equipos)
         }
     });
}
    function borrarEquipo(){
        var index = $(this).data('index');
        var id = equipos[index].id;        
        deleteData(URLS.EQUIPO.DEL,{id:id},function(result) {     
            if(result.Result === "OK") {
                filtrarEquipo();
            } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    function createTable($tabla,data){
        var template = templates['list'];
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
        var template = templates['edit'];
        data.delegaciones = delegaciones;
        bootbox.dialog({
                title: "Configuraci&oacute;n de equipo",
                message: template(data), 
                buttons: {
                    success: {
                        label: "Guardar",
                        className: "btn-success",
                        callback: function () {
                            var campos = recuperarCampos();
                            guardarEquipo(campos);
                        }
                    },
                    cancel: {
                        label: "Cancelar",
                        callback: function () {}
                    },
                    detalle: {
                        label: "Integrantes",
                        className: 'btn-primary',
                        callback: function(){
                            if(data.id)
                                window.location = URLS.EQUIPO_DETALLE.BASE +"?id_equipo="+data.id;
                            else{
                                bootbox.alert("Debe guardar el equipo antes de editar los participantes");
                            }
                        }
                    }
                }
            });
    }
function guardarEquipo(data){
    $.ajax({
        url:URLS.EQUIPO.EDIT,
        data: data,
        method:'POST',
        dataType:'json',
    }).done(function(){
        filtrarEquipo();
    });      
}
function recuperarCampos(){
    var data = {};
    data.id = $('#id').val();
    data.id_delegacion = $('#id_delegacion').val();
    data.nombre = $('#nombre').val();
    data.observaciones  = $('#observaciones').val();
    return data;   
}

function getSearchData(){
    var data = {};
    data.nombre = $('#search_nombre').val();
    data.id_delegacion = $('#id_delegacion_filtro').val()
    data.id_categoria  = $('#id_categoria_filtro').val()
    return data;
}