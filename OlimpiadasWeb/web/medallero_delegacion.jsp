<%@page import="bd.Grupo"%>
<%@page import="transaccion.TGrupo"%>
<%@page import="bd.Categoria"%>
<%@page import="java.util.Iterator"%>
<%@page import="bd.Deporte"%>
<%@page import="java.util.List"%>
<%@page import="transaccion.TDeporte"%>
<!DOCTYPE html>
<html lang="en">

    <%
    %>

    <head>

        <%@include file="tpl_head.jsp" %>       

    </head>

    <body>
        <%@include file="tpl_navbar.jsp" %>
        <div id="wrapper">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h3 class="page-header">MEDALLERO GENERAL</h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>                
                <div class="row">                            
                    <div class="col-md-12">
                        <br>
                        <table id="tblMedalla" class="table table-striped table-condensed">
                            <thead>   
                            <th>#</th>
                            <th>Delegación</th>
                            <th>ORO</th>
                            <th>PLATA</th>
                            <th>BRONCE</th>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#wrapper -->


        <%@include file="tpl_scripts.jsp" %>
        <script src="js/medallero.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                loadDataMedallero();
            });
        </script>


    </body>
</html>
