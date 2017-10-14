<%-- 
    Document   : delegacion_control
    Created on : 25-sep-2017, 9:44:13
    Author     : JuanMa
--%>

<%@page import="transaccion.TDelegacion"%>
<%@page import="transaccion.TDelegacion"%>
<%@page import="transaccion.TDelegacion"%>
<%@page import="java.util.HashMap"%>
<%@page import="transaccion.TJugador"%>
<%@page import="bd.detalle.Equipo_detalleDet"%>
<%@page import="bd.Jugador"%>
<%@page import="bd.Deporte"%>
<%@page import="transaccion.TDeporte"%>
<%@page import="bd.Categoria"%>
<%@page import="transaccion.TCategoria"%>
<%@page import="bd.detalle.EquipoDet"%>
<%@page import="java.util.List"%>
<%@page import="bd.Equipo"%>
<%@page import="transaccion.TEquipo"%>
<%@page import="bd.Delegacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <%@include file="tpl_head.jsp" %>
    </head>
    <% Categoria categoria = (Categoria) request.getAttribute("categoria");%>
    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <%@include file="tpl_navbar.jsp" %>

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h3 class="page-header">CONTROL (<%=categoria.getNombre()%>)
                            <button class='btn btn-primary btn-rounded'   data-toggle='modal' data-target='#equipo_view'>NUEVO EQUIPO</button>
                            <button class='btn btn-default btn-rounded' onclick="imprimir()">IMPRIMIR</button>
                        </h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <!-- /.panel-heading -->
                                <div class="panel-body" id="contenido">

                                    <%

                                        HashMap<String, String> filtro = new HashMap<String, String>();
                                        filtro.put("id_categoria", String.valueOf(categoria.getId()));
                                        List<Equipo> lista = new TEquipo().getListFiltro(filtro);
                                        int cont = 4;
                                        for (Equipo e : lista) {
                                            EquipoDet equipo = new EquipoDet(e);
                                            equipo.setearJugadores();
                                            if (equipo.getJugadores().size() >= 0) {
                                                if (cont == 4) {
                                    %>
                                    <div class="row">
                                        <%
                                            }

                                        %>
                                        <div class="col-lg-4">
                                            <table class="table table-striped table-condensed">
                                                <thead>                                                

                                                    <tr>
                                                        <th><button class="btn btn-danger btn-sm borrar" id_equipo="<%=equipo.getId()%>">BORRAR</button></th>
                                                        <th><button class="btn btn-primary btn-sm editar" id_equipo="<%=equipo.getId()%>">EDITAR JUGADORES</button></th>
                                                        <th><label class="checkbox-inline"><input class="bolsa" type="checkbox" value="<%=equipo.getId()%>" <%if (equipo.getObservaciones().trim().equalsIgnoreCase("SI")) {%>checked=""<%}%> >Bolsa de jugadores</label></th>
                                                    </tr>
                                                    <tr>
                                                        <th colspan="2"><b><%=categoria.getNombre()%></b></th>
                                                        <th><%=equipo.getDelegacion().getNombre()%></th>                                                                                                
                                                    </tr>

                                                </thead>
                                                <tbody>
                                                    <%
                                                        for (Equipo_detalleDet jugador : equipo.getJugadores()) {
                                                    %>
                                                    <tr>                                                        
                                                        <td colspan="2"><%=jugador.getJugador().getNombre_apellido()%></td>                                                
                                                        <td><%=jugador.getJugador().getDni()%></td>
                                                    </tr>
                                                    <%
                                                        }
                                                    %>

                                                </tbody>
                                            </table>
                                        </div>
                                        <%
                                            if (cont == 4) {
                                                cont = 2;
                                            } else {
                                                if (cont == 3) {
                                        %>
                                    </div>
                                    <%
                                                    }
                                                    cont++;
                                                }
                                            }
                                        }


                                    %>                                    

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

            <div class="modal fade" id="equipo_view">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <a href="#" data-dismiss="modal" class="class pull-right"><span class="glyphicon glyphicon-remove"></span></a>
                            <h3 class="modal-title" id="titulo">RESULTADO PRUEBA DEPORTIVA</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form-vertical">                            
                                        <div class="form-group">
                                            <label for="nombre">Nombre</label>            
                                            <input id="id_categoria_modal" name="id_categoria_modal" type="text" class="form-control input-md hidden" value="<%=categoria.getId()%>">
                                            <input id="nombre_equipo_modal" name="nombre_equipo_modal" type="text" class="form-control input-md" value="">
                                        </div>
                                        <div class="form-group">
                                            <%                                                List<Delegacion> lista_delegacion2 = new TDelegacion().getList();
                                            %>
                                            <label for="id_delegacion">Delegacion</label>            
                                            <select id="id_delegacion_modal" name="id_delegacion_modal" type="text" class="form-control input-md">
                                                <%
                                                    for (Delegacion d : lista_delegacion2) {
                                                %>
                                                <option value="<%=d.getId()%>"><%=d.getNombre()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </div>                                            
                                    </form>
                                </div>
                            </div>      
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                            <button type="button" class="btn btn-primary" onclick="nuevo_equipo();">Guardar</button>
                        </div>
                    </div>
                </div>
            </div>


        </div>
        <!-- /#wrapper -->
        <%@include file="tpl_scripts.jsp" %>     
        <script type="text/javascript">
            $(document).ready(function() {


                $(".editar").click(function() {
                    window.location = 'EquipoDetalle?id_equipo=' + $(this).attr("id_equipo");
                });

                $(".bolsa").on('change', function() {
                    var id = $(this).val();
                    var bolsa = 0;
                    if ($(this).is(':checked')) {
                        bolsa = 1;
                    } else {
                        bolsa = 0;
                    }

                    $.ajax({
                        url: "edit_bolsa",
                        data: "id_equipo=" + id + "&bolsa=" + bolsa,
                        method: "POST",
                        dataType: "json",
                    }).done(function(result) {
                        if (result.Result === "OK") {
                        } else {
                            alert("No se pudo crear bolsa");
                        }
                    });

                });

                $(".borrar").on('click', function() {
                    var id = $(this).attr("id_equipo");
                    $.ajax({
                        url: "eliminar_equipo",
                        data: "id_equipo=" + id,
                        method: "POST",
                        dataType: "json",
                    }).done(function(result) {
                        if (result.Result === "OK") {
                            window.location.reload();
                        } else {
                            alert("No se pudo eliminar equipo");
                        }
                    });

                });

            });

            function nuevo_equipo() {
                var nombre = $("#nombre_equipo_modal").val();
                var delegacion = $("#id_delegacion_modal").val();
                if (nombre !== '') {
                    $.ajax({
                        url: "equipo_nuevo",
                        data: "id_categoria=" + <%=categoria.getId()%> + "&nombre=" + nombre + "&delegacion=" + delegacion,
                        method: "POST",
                        dataType: "json",
                    }).done(function(result) {
                        if (result.Result === "OK") {
                            window.location.reload();
                        } else {
                            alert("No se pudo crear EQUIPO");
                        }
                    });
                }

            }

            function borrar() {


            }
            
            function imprimir(){
                imprSelec("contenido")
                
            }

            function imprSelec(nombre) {
                var ficha = document.getElementById(nombre);
                var ventimp = window.open(' ', 'popimpr');
                ventimp.document.write(ficha.innerHTML);
                ventimp.document.close();
                ventimp.print( );
                ventimp.close();
            }
        </script>


    </body>
</html>
