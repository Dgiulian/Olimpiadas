<%@page import="transaccion.TJugador"%>
<%@page import="bd.Usuario_app"%>
<%@page import="transaccion.TEquipo"%>
<%@page import="bd.Sede"%>
<%@page import="bd.Grupo"%>
<%@page import="bd.Categoria"%>
<%@page import="bd.Deporte"%>
<%@page import="utils.TFecha"%>
<%@page import="bd.Prueba_deportiva"%>
<%@page import="transaccion.TEquipo_detalle"%>
<%@page import="bd.Delegacion"%>
<%@page import="bd.Jugador"%>
<%@page import="bd.Jugador"%>
<%@page import="bd.Equipo_detalle"%>
<%@page import="java.util.List"%>

<%@page import="bd.Equipo"%>
<%    List<Usuario_app> lista_usuarios = (List<Usuario_app>) request.getAttribute("usuarios");
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <%@include file="tpl_head.jsp" %>    
        <link href="vendor/select2/css/select2.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <%@include file="tpl_navbar.jsp" %>

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h3 class="page-header">PANEL PUSH</h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">                    
                            <div class="panel panel-default">

                                <div class="panel-heading">
                                    Prueba deportiva 
                                </div>
                                <!-- /.panel-heading -->

                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-lg-6 col-lg-offset-3">                                                                                        
                                            <div id="errores"></div>
                                            <form id="form_push" role="form">                                                
                                                <div class="form-group">
                                                    <label>Titulo</label>
                                                    <input name="titulo" id="titulo" class="form-control" placeholder="Titulo de notificación">
                                                </div>                                                
                                                <div class="form-group">
                                                    <label>Texto</label>
                                                    <textarea name="texto" id="texto" class="form-control" rows="3"></textarea>
                                                </div>        
                                                <div class="form-group">
                                                    <label>Enviar a:</label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="to" id="todos" value="1" checked>Todos
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="to" id="personalizado" value="2">Destinatario
                                                    </label>                                                    
                                                </div>
                                                <div class="form-group hidden" id="group_destinatarios">
                                                    <label>Seleccionar Destinatarios</label>
                                                    <select class="form-control js-example-basic-multiple" name="destinatarios" id="destinatarios"  multiple="multiple">                                                        
                                                        <option value=""></option>
                                                        <%
                                                            for (Usuario_app usuario : lista_usuarios) {

                                                                Jugador jugador = new TJugador().getByDni(usuario.getDocumento());
                                                                if (jugador != null) {
                                                        %>
                                                        <option value="<%=usuario.getId()%>">(<%=jugador.getDni()%>) <%=jugador.getNombre_apellido()%></option>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </select>
                                                </div>
                                                <button type="reset" class="btn btn-default">Limpiar</button>
                                                <button type="button" class="btn btn-primary" onclick="enviar();">Enviar</button>
                                            </form>
                                        </div>

                                    </div>

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
    <script src="vendor/select2/select2.js"></script>
    <script src="js/prueba_deportiva_edit.js"></script>
    <script type="text/javascript">

                                                    $(document).ready(function() {

                                                        $("#destinatarios").select2({width: '100%'});

                                                        $('input:radio[name="to"]').change(function() {
                                                            if ($(this).val() == "1") {
                                                                $("#group_destinatarios").addClass("hidden");
                                                            } else {
                                                                $("#group_destinatarios").removeClass("hidden");
                                                            }
                                                        });



                                                    });

                                                    function enviar() {
                                                        var data = $("#form_push").serialize()
                                                        alert(data);

                                                        $.ajax({
                                                            url: URLS.PUSH.ENVIAR,
                                                            data: data,
                                                            method: 'POST',
                                                            dataType: 'json',
                                                            success: function(datos) {
                                                                if (datos.Result === "OK") {
                                                                    alert("ENVIO REALIZADO EXITOSAMENTE");
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





    </script>
</body>

</html>
