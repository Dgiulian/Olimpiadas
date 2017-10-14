<%-- 
    Document   : delegacion_control
    Created on : 25-sep-2017, 9:44:13
    Author     : JuanMa
--%>

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
    <% Delegacion delegacion = (Delegacion) request.getAttribute("delegacion");%>
    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <%@include file="tpl_navbar.jsp" %>

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h3 class="page-header">CONTROL DEPORTES (<%=delegacion.getNombre()%>)</h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <!-- /.panel-heading -->
                                <div class="panel-body">

                                    <%

                                        TEquipo te = new TEquipo();
                                        List<Equipo> lista = te.getById_delegacion(delegacion.getId());
                                        System.out.println("Lista: " + lista.size());
                                        int cont = 4;
                                        int itera = 1;
                                        for (Equipo e : lista) {

                                            Categoria categoria = new TCategoria().getById(e.getId_categoria());
                                            if (categoria != null) {
                                            Deporte deporte = new TDeporte().getById(categoria.getId_deporte());
                                            EquipoDet equipo = new EquipoDet(e);
                                            equipo.setearJugadores();

                                            

                                                if (cont == 4) {
                                    %>
                                    <div class="row">
                                        <%
                                            }

                                        %>
                                        <div class="col-lg-4">
                                            <table class="table table-striped table-condensed">
                                                <thead|>
                                                <th><b><%=deporte.getNombre()%> (<%=equipo.getNombre()%>)</b></th>
                                                <th colspan="2"><%=categoria.getNombre()%></th>                                                
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

        </div>
        <!-- /#wrapper -->
        <%@include file="tpl_scripts.jsp" %>     

    </body>
</html>
