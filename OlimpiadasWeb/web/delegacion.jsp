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
                        <h3 class="page-header">Delegaciones</h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading text-right">

                                    <span id="btnNuevo" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nueva</span>
                                    <span id="btnPrint" class="btn btn-link"><span class="fa fa-print fa-fw"> </span>Imprimir</span>

                                </div>
                                <!-- /.panel-heading -->
                                <div class="panel-body">

                                    <form class="form-inline">
                                        <div class="form-group">
                                            <label for="search_nombre">Nombre:</label>
                                            <input type="text" class="form-control" id="search_nombre">
                                        </div>

                                        <button type="button" id="btnSearch" class="btn btn-default">Buscar</button>
                                    </form> 
                                    <br>
                                    <div class="dataTable_wrapper">
                                        <table class="table table-striped table-bordered table-condensed" id="tblDelegacion">
                                            <colgroup>
                                                <col style="width:5%"></col>                                        
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col style=""></col>
                                                <col style=""></col>
                                                <col class="acciones"></col>
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Nombre</th>                                            
                                                    <th>Nombre corto</th>                                            
                                                    <th>Observaciones</th>
                                                    <th></th>
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
        <script id="delegacion_list" type="text/x-handlebars-template">
            {{#each records}}
            <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{nombre}}</td>
            <td class="">{{nombre_corto}}</td>
            <td class="">{{observaciones}}</td>
            <td>
            <a href="Delegacion_masiva?id={{id}}" data-index="{{@index}}" class="btn btn-link btn-block btn-integraste">CARGA MASIVA</a>
            </td>
            <td>
            <a href="DelegacionEquipo?id={{id}}" data-index="{{@index}}" class="btn btn-link btn-block btn-integraste">CONTROL EQUIPOS</a>
            </td>
            <td class="">
            <span href="" data-index="{{@index}}" class="btn btn-xs btn-circle  btn-warning btn-edit"><span class="fa fa-edit fw"></span></span>
            <span href="" data-index="{{@index}}" class="btn btn-xs btn-danger btn-circle btn-del"><span class="fa fa-trash fw"></span></span>
            </td>
            </tr>
            {{else}}
            <tr>
            <td colspan="4"><center><strong>No se encontraron resultados</strong></center></td>
            </tr>
            {{/each}}

        </script>   
        <script id="delegacion_edit" type="text/x-handlebars-template">
            <div class="row">
            <div class="col-md-12">
            <form class="form-vertical">
            <input id="id" name="id" type="hidden" class="" value="{{id}}" >
            <div class="form-group">
            <label for="nombre">Nombre</label>
            <input id="nombre" name="nombre" type="text" class="form-control input-md" value="{{nombre}}">
            </div>
            <div class="form-group">
            <label for="nombre_corto">Nombre Corto</label>
            <input id="nombre_corto" name="nombre_corto" type="text" class="form-control input-md" value="{{nombre_corto}}">
            </div>
            <div class="form-group">
            <label  for="observaciones">Observaciones:</label>
            <textarea id="observaciones" name="observaciones" type="text" class="form-control input-md" >{{observaciones}}</textarea>
            </div>                  
            </form>
            </div>
            </div>                     
        </script>    
        <script src="js/delegacion.js"></script>
    </body>

</html>
