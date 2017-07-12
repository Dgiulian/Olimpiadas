$(document).ready(function() {
    $('#btnNuevo').click(function(){
        agregarParametro({id:0,numero:'',codigo:'',nombre:'',valor:'',activo:1});
    });
    filtrarParametros();
});
function filtrarParametros(){
    var data = {};
    loadData({});
}
function loadData(filter){
    var $tabla = $('#tblParametro');
    setLoader($tabla);    
    $.ajax({
        url: URLS.PARAMETRO_LIST,
        data: filter,
        method:"POST",
        dataType: "json"
    }).done(function(result) {
        if(result.Result === "OK") {
            createTable($tabla,result.Records)
        }
    });
}
function borrarParametro(){
    var id = $(this).data('index');
    deleteData(URLS.PARAMETRO_DEL,{id:id},function(result) {
        if(result.Result === "OK") {
            filtrarParametros();    
        } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){   
    var template = Handlebars.compile($("#parametro_list").html());
    $tabla.find('tbody').html(template({records:data}));
    $('.btn-del').click(borrarParametro);
    $('.btn-edit').click(editarParametro);
}
function editarParametro(){
    var numero = $(this).data('numero');
    var codigo = $(this).data('codigo');
    var nombre = $(this).data('nombre');
    var valor  = $(this).data('valor');
    var index  = $(this).data('index');
    var activo = $(this).data('activo');
    agregarParametro({numero:numero,codigo:codigo,nombre:nombre,id:index,valor:valor,activo:activo});
}
function agregarParametro(data){
    data.checked = (data.activo)?"checked":"";
    var template = Handlebars.compile($('#parametro_edit').html());
    bootbox.dialog({
        title: "Configuraci&oacute;n de par&aacute;metro",
        message: template(data), 
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function () {
                    var campos = recuperarCampos();
                    guardarParametro(campos);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function () {}
            }
        }
    });
}
function guardarParametro(data){
    $.ajax({
        url: URLS.PARAMETRO_EDIT,
        data: data,
        method:'POST',
        dataType:'json',
        success:function(){
            loadData();
        }
    });
}
function recuperarCampos(){
    var data = {};
    data.id     = $('#id').val();
    data.numero = $('#numero').val();
    data.codigo = $('#codigo').val();
    data.nombre = $('#nombre').val();
    data.valor  = $('#valor').val();
    data.activo = $('#activo').prop('checked')?'1':'';
    data.activo = 1;
    return data;
}