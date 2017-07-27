<%
    String titulo  = (String ) request.getAttribute("titulo");
    String mensaje = (String) request.getAttribute("mensaje");
    String back    = (String) request.getAttribute("back");
    if (titulo==null){ titulo= "";}
    if (mensaje==null){ mensaje= "";}
    
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
            <div class="container-fluid">
              	<div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header"><%=titulo%></h1>
                            <%=mensaje%>
                        </div>
                        <div class="col-lg-12">
                            <%if (back!=null) {%>
                            <a href="<%=back%>">Volver</a>
                            <%}%>
                        </div>
                        </div> <!-- /.row -->

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
