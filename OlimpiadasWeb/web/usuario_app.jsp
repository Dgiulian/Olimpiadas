<%@page import="bd.Deporte"%>
<%@page import="java.util.List"%>

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
                        <h3 class="page-header">Usuarios App</h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading text-right">

                                    <span id="btnNuevo" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nuevo</span>
                                    <span id="btnPrint" class="btn btn-link"><span class="fa fa-print fa-fw"> </span>Imprimir</span>

                                </div>
                                <!-- /.panel-heading -->
                                <div class="panel-body">

                                    <form class="form-inline">
                                        <div class="form-group">
                                            <label for="search_username">Username</label>
                                            <input type="text" class="form-control" id="search_username">
                                        </div>
                                        <div class="form-group">
                                            <label for="search_email">Email</label>
                                            <input type="text" class="form-control" id="search_email">
                                        </div>
                                        <!--                                            <div class="form-group">
                                                                                        <label for="search_nombre">Nombre:</label>
                                                                                        <input type="text" class="form-control" id="search_nombre">
                                                                                    </div>
                                                                                     <div class="form-group">
                                                                                        <label for="search_apellido">Apellido:</label>
                                                                                        <input type="text" class="form-control" id="search_apellido">
                                                                                    </div>-->
                                        <button id="btnSearch" type="button" class="btn btn-default">Buscar</button>
                                    </form> 
                                    <br>
                                    <div class="dataTable_wrapper">
                                        <table class="table table-striped table-bordered table-condensed" id="tblUsuarioApp">
                                            <colgroup>
                                                <col style="width:5%"></col>                                        
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col></col>                                                
                                                <col style="width:10%"></col>
                                                <col class="acciones"></col>
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Username</th>
                                                    <th>DNI</th>                                                    
                                                    <th>Email</th>
                                                    <th>Creaci&oacute;n</th>
                                                    <th>Acceso</th>
                                                    <th class="acciones">Acciones</th>                                                    
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
        <script id="usuario_app_list" type="text/x-handlebars-template">
            {{#each records}}
            <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{username}}</td>
            <td class="">{{documento}}</td>            
            <td class="">{{email}}</td>
            <td class="">{{convertirFechayHora fcreacion}}</td>
            <td class="">{{convertirFechayHora ultimoacceso}}</td>
            <td class="">
            <span href="" data-index="{{@index}}" class="btn btn-xs btn-circle  btn-warning  btn-edit"><span class="fa fa-edit fw"></span></span>
            <span href="" data-index="{{@index}}" class="btn btn-xs btn-danger btn-circle btn-del"><span class="fa fa-trash fw"></span></span>
            </td>            
            </tr>
            {{else}}
            <tr>
            <td colspan="6"><center><strong>No se encontraron resultados</strong></center></td>
            </tr>
            {{/each}}

        </script>   
        <script id="usuario_app_edit" type="text/x-handlebars-template">
            <div class="row">
            <div class="col-md-12">
            <form class="form-vertical">
            <input id="id" name="id" type="hidden" class="" value="{{id}}" >
            <div class="form-group">
            <label for="username">Username</label>                      
            <input id="username" name="username" type="text" class="form-control input-group-lg" value="{{username}}">
            </div>    
            <div class="form-group">
            <label for="nombre">Documento</label>                      
            <input id="documento" name="documento" type="text" class="form-control input-group-lg" value="{{documento}}">
            </div>
            <div class="form-group">
            <label for="apellido">F. Creacion</label>                      
            <input id="apellido" name="apellido" type="text" class="form-control input-group-lg" value="{{fcreacion}}">
            </div>
            <div class="form-group">
            <label for="email">Email</label>                      
            <input id="email" name="email" type="text" class="form-control input-group-lg" value="{{email}}">
            </div>
            </form>
            </div>
            </div>                     
        </script>    
        <script src="js/usuario_app.js"></script>
    </body>

</html>
