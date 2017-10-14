var resultados = [];
function cargarTablaResultadoCategoria(id_categoria) {
    var data = {};
    data.id_categoria = id_categoria;
    var $tabla_datos = loadDataTablaResultadoCategoria(data);
    return $tabla_datos;
}
function loadDataTablaResultadoCategoria(filter) {
    var $tabla = $("<table class='table table-striped table-bordered table-condensed'></table>");
    $tabla.append(encabezado_categoria());
    if (typeof filter === "undefined")
        filter = {};
    $.ajax({
        url: "ListMedalleroCategoria",
        data: filter,
        method: "GET",
        dataType: "json"
    }).done(function(result) {
        if (result.Result === "OK") {
            resultados = result.Records;
            $tabla.append(cargar_detalle_resultado_categoria(resultados));
        }
    });
    return $tabla;
}

function encabezado_categoria() {
    var html = "";

    html +=
            '<thead>' +
            '<th>Delegacion</th>' +
            '<th>Equipo</th>' +
            '<th>Medalla</th>' +
            '</thead>';
    return html;

}

function cargar_detalle_resultado_categoria(data) {
    html = "<tbody>";
    for (var i = 0; i < data.length; i++) {
        html += "<tr class=''>";
        d = data[i];
        html += wrapTag('td', d.nombre, '');
        html += wrapTag('td', d.equipo, '');
        html += wrapTag('td', d.medalla, '');
        html += "</tr>";
    }
    html += "</tbody>";
    return html;
}