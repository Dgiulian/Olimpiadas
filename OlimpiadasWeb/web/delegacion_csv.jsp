<%@page import="bd.Delegacion"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <%@include file="tpl_head.jsp" %>
    </head>
    <% Delegacion delegacion = (Delegacion) request.getAttribute("delegacion");%>
    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <%@include file="tpl_navbar.jsp" %>

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h3 class="page-header">Carga Masiva Delegaciones (<%=delegacion.getNombre()%>)</h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">

                                <!-- /.panel-heading -->
                                <div class="panel-body">

                                    <form class="form-inline">
                                        <form class="form"> 
                                            <div class="form-group">
                                                <label for="exampleInputFile">Subir CSV</label>
                                                <input type="file" class="form-control-file" id="txtFileUpload" aria-describedby="fileHelp">
                                                <small id="fileHelp" class="form-text text-muted">Archivo de carga masiva de prestashop</small>
                                            </div>                                        

                                        </form> 
                                        <div class="form-group">
                                            <button class="btn btn-danger">LIMPIAR</button>
                                        </div>
                                        <div class="form-group">
                                            <button class="btn btn-primary" onclick="guardar()">GUARDAR</button>
                                        </div>
                                        <br>
                                        <div class="dataTable_wrapper">
                                            <div id="errores"></div>
                                            <table class="table table-striped table-bordered table-condensed" id="tblJugador">
                                                <colgroup width="">                                                    
                                                    <col style="width:15%"></col>
                                                    <col style="width:30%"></col>                                                
                                                    <col style="width:20%"></col>                                                
                                                    <col style="width:20%"></col>
                                                </colgroup>
                                                <thead>
                                                    <tr>                                                        
                                                        <th>Dni</th>
                                                        <th>Nombre</th>
                                                        <th>Matricula</th>                                                  
                                                        <th>Fecha Nacimiento</th>                                                                                                                                                    
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                        </div>

                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /.panel -->
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->
        <%@include file="tpl_scripts.jsp" %>     
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-csv/0.71/jquery.csv-0.71.min.js"></script>


        <script type="text/javascript">

                                                function cargar_jugador(csv_dni, csv_nombre, csv_matricula, csv_fechanac) {
                                                    var html = "";
                                                    html += "<tr class='linea_jugador'>";
                                                    html += "<td campo='dni'><b>" + csv_dni + "</b></td>";
                                                    html += "<td campo='nombre_apellido'><b>" + csv_nombre + "</b></td>";
                                                    html += "<td campo='matricula'><b>" + csv_matricula + "</b></td>";
                                                    html += "<td campo='fecha_nacimiento'><b>" + csv_fechanac + "</b></td>";
                                                    html += "</tr>";
                                                    return html;
                                                }

                                                function agregar_jugador_csv(csv_dni, csv_nombre, csv_matricula, csv_fechanac) {

                                                    var html = cargar_jugador(csv_dni, csv_nombre, csv_matricula, csv_fechanac);
                                                    $("#tblJugador").append(html);

                                                }

                                                function guardar() {
                                                    var datos = procesar_articulos();
                                                    $.ajax({
                                                        type: "POST",
                                                        url: "guardar_masiva",
                                                        dataType: "json",
                                                        data: "id=<%=delegacion.getId()%>&jugadores=" + datos,
                                                        success: function(datos) {
                                                            if (datos.Result === "OK") {
                                                                alert("DELEGACION GUARDADA EXITOSAMENTE");
                                                            } else {
                                                                var html = "";
                                                                for (var i = 0; i < datos.Records.length; i++) {
                                                                    html += "<span class='label label-danger'><br>";
                                                                    html += "Error al crear: " + datos.Records[i];
                                                                    html += "</span>";
                                                                }
                                                                $("#errores").html(html);
                                                            }
                                                        }
                                                    });
                                                }

                                                function procesar_articulos() {

                                                    jsonRendicion = [];

                                                    $(".linea_jugador").each(function(index) {
                                                        item = {}
                                                        $(this).find('td').each(function() {
                                                            if ($(this).hasAttr('campo')) {
                                                                var valor_corregido = $.trim($(this).text());
                                                                if ($(this).hasClass('input')) {
                                                                    valor_corregido = $.trim($(this).find("input").val());
                                                                } else {
                                                                    valor_corregido = $.trim($(this).text());
                                                                }
                                                                item[$(this).attr('campo')] = valor_corregido;
                                                            }
                                                        });
                                                        jsonRendicion.push(item);

                                                    });

                                                    return JSON.stringify(jsonRendicion);

                                                }

                                                $.fn.hasAttr = function(name) {
                                                    return this.attr(name) !== undefined;
                                                };


        </script>
        <%@include file="Upload.jsp" %>
    </body>

</html>
