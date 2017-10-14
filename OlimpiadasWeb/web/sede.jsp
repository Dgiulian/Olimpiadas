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
                        <h3 class="page-header">Sedes</h3>
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
                                    <div class="dataTable_wrapper">

                                        <form class="form-inline">
                                            <div class="form-group">
                                                <label for="search_nombre">Nombre:</label>
                                                <input type="text" class="form-control" id="search_nombre">
                                            </div>                                               
                                            <button id="btnSearch" type="button" class="btn btn-default">Buscar</button>
                                        </form> 
                                        <br>
                                        <table class="table table-striped table-bordered table-condensed" id="tblSede">
                                            <colgroup>
                                                <col style="width:5%"></col>
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col style=""></col>
                                                <col class="acciones"></col>
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Nombre</th>
                                                    <th>Direcci&oacute;n</th>
                                                    <th>Latitud</th>
                                                    <th>Longitud</th>
                                                    <th>Observaciones</th>
                                                    <th>Imagen</th>
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
        <script id="sede_list" type="text/x-handlebars-template">
            {{#each records}}
            <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{nombre}}</td>
            <td class="">{{direccion}}</td>
            <td class="">{{latitud}}</td>
            <td class="">{{longitud}}</td>
            <td class="">{{observaciones}}</td>
            <td>
                {{#if imagen}}
                    <a href="Download?type=sede&id={{id}}">{{imagen}}</a>
                {{/if}}  
            </td>
            <td class="">
            <span href="" data-index="{{@index}}" class="btn btn-xs btn-circle  btn-warning btn-edit"><span class="fa fa-edit fw"></span></span>
            <span href="" data-index="{{@index}}" class="btn btn-xs btn-danger btn-circle btn-del"><span class="fa fa-trash fw"></span></span>
            </td>
            </tr>
            {{else}}
            <tr>
            <td colspan="5"><center><strong>No se encontraron resultados</strong></center></td>
            </tr>
            {{/each}}    
        </script>   
        <script id="sede_edit" type="text/x-handlebars-template">
            <div class="row">
            <div class="col-md-12"> 
            <form class="form-vertical" enctype="multipart/form-data">
            <input id="id" name="id" type="hidden" class="" value="{{id}}">
            <div class="form-group">
            <label for="nombre">Nombre</label>            
            <input id="nombre" name="nombre" type="text" class="form-control input-group-lg" value="{{nombre}}">
            </div>
            <div class="form-group">
            <label for="direccion">Direcci&oacute;n:</label>            
            <input id="direccion" name="direccion" type="text" class="form-control input-md" value="{{direccion}}">
            </div>
            <div class="form-group">
            <label for="telefono">Telefono:</label>            
            <input id="telefono" name="telefono" type="text" class="form-control input-md" value="{{telefono}}">
            </div>
            <div class="form-group">
            <label class="control-label" for="no">Imagen</label>                      
            <input id="imagen" name="imagen" type="file" class="form-control input-md" value="">
             </div>                   
            <div class="form-group">
            <label for="latitud">Latitud:</label>            
            <input id="latitud" name="latitud" type="text" class="form-control input-md" value="{{latitud}}">
            </div>
            <div class="form-group">
            <label for="longitud">Longitud:</label>            
            <input id="longitud" name="longitud" type="text" class="form-control input-md" value="{{longitud}}">
            </div>
            <div class="form-group">
            <label for="observaciones">Detalle:</label>                            
            <textarea id="observaciones" name="observaciones" type="text" class="form-control input-md" >{{observaciones}}</textarea>
            </div>                
            </form>
            </div>
            </div>                     
        </script>    
        <script src="js/sede.js"></script>
    </body>

</html>
