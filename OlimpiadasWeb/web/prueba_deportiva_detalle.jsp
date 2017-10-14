
<%@page import="bd.detalle.Prueba_deportivaDet"%>
<%@page import="bd.Prueba_deportiva"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bd.Grupo"%>
<%@page import="bd.Categoria"%>
<%@page import="bd.Deporte"%>
<%@page import="java.util.List"%>
<%    Prueba_deportivaDet prueba = (Prueba_deportivaDet) request.getAttribute("prueba");
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
                        <h3 class="page-header">DETALLE DE PRUEBA DEPORTIVA</h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">

                                <div class="panel-heading text-right">
                                    <input type="hidden" id="search_prueba" name="search_prueba" value="<%=prueba.getId()%>">
                                    <span id="btnPrint" class="btn btn-link"><span class="fa fa-print fa-fw"> </span>Imprimir</span>
                                </div>
                                <!-- /.panel-heading -->
                                <div class="panel-body">                                    
                                    <div class="dataTable_wrapper col-md-9">  
                                        <h4><%=prueba.getDeporte().getNombre()%> - <%=prueba.getCategoria().getNombre()%> - <%=prueba.getGrupo().getNombre()%></h4>                                        
                                        <table class="table table-striped table-bordered table-condensed" id="tblPruebaDetalle">
                                            <colgroup>
                                                <col style="width:25%"></col>      
                                                <col style="width:30%"></col>      
                                                <col style="width:15%"></col>      
                                                <col style="width:15%"></col>      
                                                <col style="width:15%"></col>
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th>Delegación</th>
                                                    <th>Equipo</th>
                                                    <th>Resultado</th>                                                    
                                                    <th>Medalla</th>                                                    
                                                    <th>Puntaje</th>                                                    
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
        <script src="vendor/maskedinput/jquery.maskedinput.min.js"></script>
        <script src="js/prueba_deportiva_detalle.js"></script>
        <script type="text/javascript">
            $(document).ready(init_prueba_deportiva_detalle);
        </script>
    </body>

</html>
