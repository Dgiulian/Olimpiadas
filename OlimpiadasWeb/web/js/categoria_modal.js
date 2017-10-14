
var categorias = [];
var deportes = [];
var templates = {};
$(document).ready(function() {
    $('#categoria_view').on('show.bs.modal', function(e) {
        //get data-id attribute of the clicked element                                                            
        var id_deporte = $(e.relatedTarget).data('id');
        var nombre_deporte = $(e.relatedTarget).data('nombre');
        var data = {};
        data.id_deporte = id_deporte;
        $("#titulo").html("<h3>" + nombre_deporte + "</h3>")
        loadDataCategoria(data);
    });

});

function loadDataCategoria(filter) {
    var $tabla = $('#tblCategoriaModal');
    setLoader($tabla);
    if (typeof filter === "undefined")
        filter = {};
    $.ajax({
        url: URLS.CATEGORIA.LIST,
        data: filter,
        method: "GET",
        dataType: "json"
    }).done(function(result) {
        if (result.Result === "OK") {
            categorias = result.Records;
            $tabla.find('tbody').html(createTableModal(categorias));
        }
    });
}

function createTableModal(data) {

    var html = "";

    for (var i = 0; i < data.length; i++) {
        html += "<tr class=''>";
        d = data[i];
        html += wrapTag('td', d.nombre, '');
        html += wrapTag('td', d.nombre_corto, '');
        html += wrapTag('td', d.deporte.nombre, '');
        html += wrapTag('td', "<button onclick='redirigir_grupo(" + d.id + ")'>VER</button>", '');
        html += "</tr>";
    }
    return html;
}

function redirigir_grupo(id) {
    window.location = "CategoriaPanel?id_categoria=" + id;
}

