<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <%@include  file="tpl_head.jsp" %>
    </head>

    <body>

        <div id="wrapper">

            <%@include file="tpl_navbar.jsp" %>

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h3 class="page-header">Usuarios ADMIN</h3>
                    </div>

                </div>

                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading text-right">
                                    <a href="<%= PathCfg.USUARIO_EDIT%>" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nuevo</a>
                                </div>
                                <!-- /.panel-heading -->
                                <div class="panel-body">
                                    <form class="form-inline">
                                        <div class="form-group">
                                            <label for="search_username">Username</label>
                                            <input type="text" class="form-control" name="search_username" id="search_username">
                                        </div>                                            
                                        <button id="btnSearch" type="button" class="btn btn-default">Buscar</button>
                                    </form> 
                                    <br>
                                    <div class="dataTable_wrapper">


                                        <table class="table table-striped table-bordered table-hover" id="tblUsuario">
                                            <thead>
                                                <tr>
                                                    <th>Username</th>                                            
                                                    <th>Tipo</th>
                                                    <th>Fecha Alta</th>
                                                    <th>Activo</th>
                                                    <th class="acciones">Acciones</th>                                            
                                                </tr>
                                            </thead>
                                            <tbody>

                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.table-responsive -->
                                    <!--                            <div class="well">
                                                                    <h4>DataTables Usage Information</h4>
                                                                    <p>DataTables is a very flexible, advanced tables plugin for jQuery. In SB Admin, we are using a specialized version of DataTables built for Bootstrap 3. We have also customized the table headings to use Font Awesome icons in place of images. For complete documentation on DataTables, visit their website at <a target="_blank" href="https://datatables.net/">https://datatables.net/</a>.</p>
                                                                    <a class="btn btn-default btn-lg btn-block" target="_blank" href="https://datatables.net/">View DataTables Documentation</a>
                                                                </div>-->

                                    <!-- /.panel-body -->
                                </div>
                                <!-- /.panel -->
                            </div>
                            <!-- /.col-lg-12 -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /#page-wrapper -->

                </div>
                <!-- /#wrapper -->

                <%@include file="tpl_scripts.jsp" %>
                <!-- Page-Level Demo Scripts - Tables - Use for reference -->
                <script src="js/usuario.js">

                </script>
                </script>
                <%@include file="tpl_footer.jsp"%>
                </body>

                </html>
