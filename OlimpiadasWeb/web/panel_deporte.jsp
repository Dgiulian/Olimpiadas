<%@page import="java.util.Iterator"%>
<%@page import="bd.Deporte"%>
<%@page import="java.util.List"%>
<%@page import="transaccion.TDeporte"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <%@include file="tpl_head.jsp" %>
        <%Deporte deporte = (Deporte) request.getAttribute("deporte");%>
    </head>

    <body>
        <%@include file="tpl_navbar.jsp" %>
        <div id="wrapper">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h3 class="page-header"><%=deporte.getNombre()%></h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
            </div>
            <!-- /#wrapper -->
        </div>


        

        <!-- jQuery -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!-- Metis Menu Plugin JavaScript -->
        <script src="vendor/metisMenu/metisMenu.min.js"></script>
        <!-- Custom Theme JavaScript -->
        <script src="js/sb-admin-2.js"></script>
        <script src="js/categoria_modal.js"></script>


    </body>
</html>
