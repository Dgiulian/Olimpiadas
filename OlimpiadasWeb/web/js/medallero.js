//Tipo 1 si es para rellenar el ABM o 2 para completar los items de una categoria
function loadDataMedallero() {

    var $tabla = $('#tblMedalla');
    setLoader($tabla);

    getDataMedallero().done(function(result) {
        if (result.Result === "OK") {
            delegaciones = result.Records;
            cargar_items_agenda2($tabla, delegaciones);
        }
    });
}

function getDataMedallero() {
    if (typeof filter === "undefined")
        filter = {};
    return $.ajax({
        url: 'MedalleroList',
        method: "GET",
        dataType: "json"
    });
}

function cargar_items_agenda2($contenedor, data) {

    var html = "";
    for (var i = 0; i < data.length; i++) {
        d = data[i];

        html += "<tr class=''>";
        html += wrapTag('td', '<b>'+(i+1)+'</b>', '');
        html += wrapTag('td', d.nombre, '');
        html += wrapTag('td', d.oro, '');
        html += wrapTag('td', d.plata, '');
        html += wrapTag('td', d.bronce, '');
        html += "</tr>";
    }
    $contenedor.find("tbody").html(html);
}
