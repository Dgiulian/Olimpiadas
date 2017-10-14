<%@page import="java.util.Iterator"%>
<%@page import="bd.Deporte"%>
<%@page import="java.util.List"%>
<%@page import="transaccion.TDeporte"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <%@include file="tpl_head.jsp" %>

    </head>

    <body>
        <%@include file="tpl_navbar.jsp" %>
        <div id="wrapper">
            <div id="page-wrapper">
                <br>
                <div class="row">
                    <%                    List<Deporte> lista = new TDeporte().getList();
                        Iterator it = lista.iterator();
                        while (it.hasNext()) {
                            Deporte deporte = (Deporte) it.next();
                    %>
                    <div class="col-md-2 panel-primary">

                        <div class="panel-heading">
                            <div class="row text-center">
                                <b><%=deporte.getNombre()%></b>                                
                            </div>

                        </div>
                        <a data-id='<%=deporte.getId()%>' data-nombre='<%=deporte.getNombre()%>' data-toggle='modal' data-target='#categoria_view' class=""><img src="images/deportes/<%=deporte.getImagen()%>" class="img-responsive"/></a>

                    </div>
                    <%
                        }
                    %>  
                </div>
            </div>
            <!-- /#wrapper -->
        </div>

        <%@include file="categoria_modal.jsp" %>        
        <%@include file="tpl_scripts.jsp" %>
        <script src="js/categoria_modal.js"></script>

    </body>
</html>
