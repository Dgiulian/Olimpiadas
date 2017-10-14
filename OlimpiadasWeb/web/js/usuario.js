 $(document).ready(function() {
    $('#btnSearch').click(filtrarUsuario);
    filtrarUsuario();
});
function filtrarUsuario(){
    var data = getSearchData();
    loadData(data);
}
function loadData(filter){
    var $tabla = $('#tblUsuario');
    setLoader($tabla);
    if(typeof filter==="undefined") filter = {};
    $.ajax({
        url: URLS.USUARIO.LIST,
        data: filter,
        method:"GET",
        dataType: "json",
    }).done(function(data) {
        if(data.Result === "OK") {
          createTable($tabla,data.Records)
        }
    });
}
function borrarUsuario(){
    var id = $(this).data('index');
    var $tr = $(this).parent().parent();
    deleteData(URLS.USUARIO.DEL,{id:id},function(result) {
        if(result.Result === "OK") {
        $tr.remove();
        } else if (result.Message) bootbox.alert(result.Message);
    });
}
function createTable($tabla,data){
    var html = "";
    if(data.length>0) {
    for(var i = 0;i< data.length;i++){
        var d = data[i];           
        html +="<tr class=''>";

        html += wrapTag('td',d.usu_mail,'');
        html += wrapTag('td',d.tipo_usuario,'');
        html += wrapTag('td',convertirFecha(d.usu_fcreacion),'');
        var activo = d.usu_activo?"Si":"No";

        html += wrapTag('td',activo,'');
        var htmlEdit = "<a href='" + URLS.USUARIO.EDIT + "?id="+ d.id +"' class='btn btn-xs btn-circle  btn-warning'><span class='fa fa-edit fw'></span></a> ";
        var htmlDel = "<span href='' data-index='"+ d.id + "' class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash fw'></span></span> ";

        html +=wrapTag('td',htmlEdit + htmlDel,'');
        html +="</tr>";
    }}
    else {
        html =" <tr>" +
                "<td colspan='5'><center><strong>No se encontraron resultados</strong></center></td>" +
                "</tr>";
    }
    $tabla.find('tbody').html(html);
    $('.btn-del').click(borrarUsuario);
}

function getSearchData(){
    var data={};
    data.email = $('#search_username').val();
    return data;
}