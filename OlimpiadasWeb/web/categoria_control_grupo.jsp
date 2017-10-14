<%-- 
    Document   : delegacion_control
    Created on : 25-sep-2017, 9:44:13
    Author     : JuanMa
--%>

<%@page import="bd.detalle.GrupoDet"%>
<%@page import="bd.Grupo_detalle"%>
<%@page import="bd.Grupo"%>
<%@page import="transaccion.TGrupo"%>
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
                        <h3 class="page-header">CONTROL GRUPOS(<%=categoria.getNombre()%>)
                            <button class="btn btn-primary editar" id_categoria="<%=categoria.getId()%>">EDITAR GRUPOS</button>
                        </h3>
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

                                        HashMap<String, String> filtro = new HashMap<String, String>();
                                        filtro.put("id_categoria", String.valueOf(categoria.getId()));
                                        List<Grupo> lista = new TGrupo().getListFiltro(filtro);
                                        int cont = 5;
                                        for (Grupo g : lista) {
                                            GrupoDet grupo = new GrupoDet(g);

                                            if (grupo.getDetalle().size() >= 0) {
                                                if (cont == 5) {
                                    %>
                                    <div class="row">
                                        <%
                                            }

                                        %>
                                        <div class="col-lg-3">
                                            <table class="table table-striped table-condensed">
                                                <thead>                                                                                                    
                                                    <tr>                                                        
                                                        <th colspan="2"><%=grupo.getNombre()%></th>   
                                                        <th></th>
                                                    </tr>

                                                </thead>
                                                <tbody>
                                                    <%
                                                        for (Equipo equipo : grupo.getDetalle()) {
                                                            EquipoDet equipo_det = new EquipoDet(equipo);
                                                    %>
                                                    <tr>                                                        
                                                        <td><%=equipo_det.getDelegacion().getNombre()%></td>                                                                                                        
                                                        <td><%=equipo_det.getNombre()%></td>   
                                                        <td><span href="" class="btn btn-xs btn-danger btn-circle borrar_equipo" id_grupo="<%=grupo.getId()%>" id_equipo="<%=equipo.getId()%>"><span class="fa fa-trash fw"></span></span> </td>
                                                    </tr>
                                                    <%
                                                        }
                                                    %>

                                                </tbody>
                                            </table>
                                        </div>
                                        <%
                                            if (cont == 5) {
                                                cont = 2;
                                            } else {
                                                if (cont == 4) {
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

        <script type="text/javascript">
            $(document).ready(function() {

                $(".editar").click(function() {
                    window.location = 'Grupo?id_categoria=' + $(this).attr("id_categoria");
                });

                $(".borrar_equipo").on('click', function() {
                    var id_equipo = $(this).attr("id_equipo");
                    var id_grupo = $(this).attr("id_grupo");
                    $.ajax({
                        url: "eliminar_equipo_grupo",
                        data: "id_grupo=" + id_grupo + "&id_equipo=" + id_equipo,
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

        </script>


    </body>
</html>
