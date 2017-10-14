<%@page import="bd.Categoria"%>
<%@page import="bd.Grupo"%>
<%    Grupo grupo = (Grupo) request.getAttribute("grupo");
    Categoria categoria = (Categoria) request.getAttribute("categoria");
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <%@include file="tpl_head.jsp" %>
    </head>

    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <%@include file="tpl_navbar.jsp" %>

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h3 class="page-header"><%=grupo.getNombre()%></h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>

                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel-body">
                                <div class="dataTable_wrapper" id="posiciones">

                                </div>

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

        <script src="js/grupo_resultado.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                var tabla_pos = cargarResultadoGrupo('<%=grupo.getNombre()%>', '<%=grupo.getId()%>', '<%=categoria.getTipo_puntaje()%>', '<%=grupo.getTipo_vista_tabla()%>' );
                $("#posiciones").append(tabla_pos);
            });

        </script>
    </body>

</html>
