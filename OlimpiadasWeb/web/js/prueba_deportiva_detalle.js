
var pruebas = [];
var templates = {};
var categorias = [];
var categoria = {};
var ms;
var id_prueba_general = 0;

function init_prueba_deportiva_detalle() {
    var id_prueba = $("#search_prueba").val();
    filtrarPruebaDetalle(id_prueba);
}
function filtrarPruebaDetalle(id_prueba) {
    var data = "id_prueba=" + id_prueba;
    $id_prueba = id_prueba;
    loadDataPruebaDetalle(data);
}

function loadDataPruebaDetalle(data) {
    var $tabla = $('#tblPruebaDetalle');

    $.ajax({url: URLS.PRUEBA_DEPORTIVA.LIST,
        data: data,
        method: "POST",
        dataType: "json",
        beforeSend: function() {
            setLoader($tabla);
        },
        success: function(data) {
            if (data.Result == 'OK') {
                $tabla.find('tbody').html(cargar_detalle(data.Records));
                enmascarar();
            } else {
                var cant_cols = $tabla.find('thead tr th').length;
                $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center>NO EXISTEN REGISTROS</center></td></tr>");
            }
        }
    });
}

function cargar_detalle(data) {

    var html = "";
    var resultado_detalle = "";
    if (data.length > 0) {
        resultado_detalle = data[0].prueba.detalle_resultado;
    }

    for (var i = 0; i < data.length; i++) {
        html += "<tr class=''>";
        d = data[i];
        html += wrapTag('td', d.equipo.delegacion.nombre, '');
        html += wrapTag('td', d.equipo.nombre, '');
        html += wrapTag('td', cargar_input(d), '');
        html += wrapTag('td', select_medalla(d.id, d.medalla), '');
        html += wrapTag('td', cargar_input_puntos(d), '');
        html += "</tr>";
    }

    html += "<tr class='text-right'><td>Detalle</td><td colspan='" + (data.length + 1) + "'><textarea id='detalle_resultado' class='form-control'>" + resultado_detalle + "</textarea></td></tr>";
    html += "<tr class='text-right'><td colspan='" + (data.length + 2) + "'><div id='loader'></div><a class='btn btn-primary' onclick='cargar_resultados();'><span class='fa fa-file-o fa-fw'></span>GUARDAR RESULTADOS</a></td></tr>";

    return html;
}

function cargar_input(datos) {
    var categoria = datos.prueba.categoria;
    var input = "<input class='form-control ' id_prueba_detalle='" + datos.id + "' tipo='resultado' tipo_puntaje='" + categoria.tipo_puntaje + "' class='input-sm' value='" + datos.resultado + "'></input>";
    return input;
}

function cargar_input_puntos(datos) {
    var categoria = datos.prueba.categoria;
    if (categoria.tipo_modalidad == 3) {
        var input = "<input class='form-control input-xs' id='puntaje_" + datos.id + "' class='input-sm' value='" + datos.puntos + "'></input>";
        return input;
    } else {
        var input = "<input class='form-control input-xs' id='puntaje_" + datos.id + "' class='input-sm' disabled value='" + datos.puntos + "'></input>";
        return input;
    }
}

function cargar_resultados() {
    var $div_loader = $("#loader");
    var resultados = procesar_resultados();
    var observaciones = $("#detalle_resultado").val();
    data = "resultado=" + resultados + "&id_prueba=" + $id_prueba + "&detalle=" + observaciones;
    $.ajax({url: URLS.PRUEBA_DEPORTIVA.SAVE,
        data: data,
        method: "POST",
        dataType: "json",
        beforeSend: function() {
            setLoaderDiv($div_loader);
        },
        success: function(data) {
            if (data.Result == 'OK') {
                $div_loader.html("Guardado exitosamente");
            } else {
                $div_loader.html(data.Message);
            }
        }
    });

}

function procesar_resultados() {
    jsonResultados = [];
    $("input[tipo='resultado']").each(function(index) {
        item = {}
        item["id"] = $(this).attr("id_prueba_detalle");
        item["resultado"] = $(this).val();
        item["medalla"] = $("#medalla_" + item["id"]).val();
        item["puntos"] = $("#puntaje_" + item["id"]).val();
        jsonResultados.push(item);
    });
    return (JSON.stringify(jsonResultados));
}

function enmascarar() {
    $("input[tipo='resultado']").each(function(index) {
        var t_puntaje = $(this).attr("tipo_puntaje");
        if (t_puntaje == 1) {
            //$("input[tipo='resultado']").mask("99?99");
        }
        if (t_puntaje == 2) {
            $("input[tipo='resultado']").mask("99:99:99.99");
        }
    });
}

function select_medalla(id, id_medalla) {
    var selected = "";
    var select = '<select class="form-control" id="medalla_' + id + '" >' +
            '<option value="">Seleccionar medalla</option>';
    if (id_medalla == 'oro') {
        selected = 'selected';
    }
    select += '<option value="oro" ' + selected + '>ORO</option>';
    selected = "";
    if (id_medalla == 'plata') {
        selected = 'selected';
    }
    select += '<option value="plata"' + selected + '>PLATA</option>';
    selected = "";
    if (id_medalla == 'bronce') {
        selected = 'selected';
    }
    select += '<option value="bronce"' + selected + '>BRONCE</option>';
    select += '</select>';
    return select;

}