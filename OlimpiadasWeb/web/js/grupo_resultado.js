var resultados = [];
function cargarResultadoGrupo(nombre_grupo, id_grupo, tipotabla, vista_tabla) {
    var data = {};
    data.id_grupo = id_grupo;
    var $tabla_datos = loadDataGrupo(nombre_grupo, data, tipotabla, vista_tabla);
    return $tabla_datos;
}
function loadDataGrupo(nombre_grupo, filter, tipotabla, vista_tabla) {
    var $tabla = $("<table class='table table-striped table-bordered table-condensed'></table>");
    $tabla.append(encabezado(nombre_grupo, tipotabla, vista_tabla));
    if (typeof filter === "undefined")
        filter = {};
    $.ajax({
        url: "resultado_grupo",
        data: filter,
        method: "GET",
        dataType: "json"
    }).done(function(result) {
        if (result.Result === "OK") {
            resultados = result.Records;
            $tabla.append(cargar_detalle_resultado(resultados, tipotabla, vista_tabla));
        }
    });
    return $tabla;
}

function encabezado(nombre_grupo, tipotabla, vista_tabla) {
    var html = "";
    if (vista_tabla == 2) {

        html = '<thead><th colspan="3">' + nombre_grupo + '</th></thead>';


    } else {
        if (tipotabla == 1) {
            html = '<thead><th colspan="7">' + nombre_grupo + '</th></thead>';
            html +=
                    '<thead>' +
                    '<tr>' +
                    '<th>Pos</th>' +
                    '<th>Delegacion</th>' +
                    '<th>Equipo</th>' +
                    '<th>Puntos</th>' +
                    '<th>PJ</th>' +
                    '<th>AF</th>' +
                    '<th>EC</th>' +
                    '<th>DIF</th>' +
                    '</tr>' +
                    '</thead>';
        } else {
            html = '<thead><th colspan="4">' + nombre_grupo + '</th></thead>';
            html +=
                    '<thead>' +
                    '<tr>' +
                    '<th>Pos</th>' +
                    '<th>Delegacion</th>' +
                    '<th>Equipo</th>' +
                    '<th>Tiempo</th>' +
                    '</tr>' +
                    '</thead>';
        }

    }

    return html;

}

function cargar_detalle_resultado(data, tipotabla, vistatabla) {

    var html = "<tbody>";
    if (vistatabla == 2) {
        html += completarPruebas(data);
    } else {
        if (tipotabla == 1) {
            for (var i = 0; i < data.length; i++) {
                html += "<tr class=''>";
                d = data[i];
                html += wrapTag('td', (i + 1), '');
                html += wrapTag('td', d.delegacion, '');
                html += wrapTag('td', d.nombre, '');
                html += wrapTag('td', d.resultado, '');
                html += wrapTag('td', d.pj, '');
                html += wrapTag('td', d.afavor, '');
                html += wrapTag('td', d.encontra, '');
                html += wrapTag('td', (parseInt(d.afavor) - parseInt(d.encontra)), '');
                html += "</tr>";
            }
        } else {
            for (var i = 0; i < data.length; i++) {
                html += "<tr class=''>";
                d = data[i];
                html += wrapTag('td', (i + 1), '');
                html += wrapTag('td', d.delegacion, '');
                html += wrapTag('td', d.nombre, '');
                html += wrapTag('td', d.tiempo, '');
                html += "</tr>";
            }
        }
    }
    html += "</tbody>";
    return html;
}

function completarPruebas(datos) {

    var html = "";
    for (var i = 0; i < datos.length; i++) {
        var detalle_prueba = datos[i].detalle_prueba;
        html += "<thead style='background-color:#D8D8D8'><th colspan='3'>" + datos[i].fecha + " " + datos[i].hora + " - SEDE: " + datos[i].sede.nombre+"</thead>";
        html +=
                '<thead>' +
                '<tr>' +
                '<th>Delegacion</th>' +
                '<th>Nombre</th>' +
                '<th>Resultado</th>' +
                '</tr>' +
                '</thead>';
        for (var j = 0; j < detalle_prueba.length; j++) {
            dp = detalle_prueba[j];
            html += "<tr class=''>";
            html += wrapTag('td', dp.equipo.delegacion.nombre, '');
            html += wrapTag('td', dp.equipo.nombre, '');
            html += wrapTag('td', dp.resultado, '');
            html += "</tr>";
        }
    }
    return html;

}