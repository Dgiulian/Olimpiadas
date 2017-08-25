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
                        <h3 class="page-header">Deportes</h3>
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
                                        <button id="btnSearch" type="button" class="btn btn-default">Buscar</button>
                                    </form> 
                                    <br>                                    
                                    <div class="dataTable_wrapper">
                                        <table class="table table-striped table-bordered table-condensed" id="tblDeporte">
                                            <colgroup>
                                                <col style="width:5%"></col>
                                                <col style="width:20%"></col>
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col></col>                                                
                                                <col class="acciones"></col>
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Nombre</th>
                                                    <th>Tipo</th>
                                                    <th>Cantidad</th>
                                                    <th>Reglamento</th>                                                    
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
        <script id="deporte_list" type="text/x-handlebars-template">
            {{#each records}}
            <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{nombre}}</td>
            <td class="">{{tipoDeporte tipo}}</td>
            <td class="">{{cantidad_jugadores}}</td>
            <td class="">
            {{#if reglamento}}
            <a href="Download?type=reglamento&id={{id}}">{{reglamento}}</a>
            {{/if}}                    
            </td>
            <td class="">
            <span data-index="{{@index}}" class="btn btn-xs btn-circle  btn-warning btn-edit"><span class="fa fa-edit fw"></span></span>
            <span data-index="{{@index}}" class="btn btn-xs btn-danger btn-circle btn-del"><span class="fa fa-trash fw"></span></span>
            </td>
            </tr>
            {{else}}
            <tr>
            <td colspan="4"><center><strong>No se encontraron resultados</strong></center></td>
            </tr>
            {{/each}}

        </script>   
        <script id="deporte_edit" type="text/x-handlebars-template" method="POST" >
            <div class="row">
            <div class="col-md-12">
            <form class="form-vertical" enctype="multipart/form-data">
            <input id="id" name="id" type="hidden" class="" value="{{id}}" >
            <div class="form-group">
            <label for="nombre">Nombre</label>                      
            <input id="nombre" name="nombre" type="text" class="form-control input-md" value="{{nombre}}">
            </div>
            <div class="form-group">
            <label for="tipo">Tipo</label>                      
            <select id="tipo" name="tipo" type="text" class="form-control input-md">
            {{#select tipo}}
            <option value="0">Seleccione el tipo de deporte</option>
            <option value="1">Individual</option>
            <option value="2">Colectivo</option>
            {{/select}}
            </select>
            </div>
            <div class="form-group"> 
            <label for="cantidad_jugadores">Cantidad Jugadores</label>

            <input id="cantidad_jugadores" name="cantidad_jugadores" type="text" class="form-control input-md" value="{{cantidad_jugadores}}">
            </div>


            <div class="form-group">
            <label for="reglamento">Reglamento</label>

            {{#if reglamento}}                           
            {{/if}}
            <input id="reglamento" name="reglamento" type="file" class="" value="">

            </div>   
            </form>
            </div>
            </div>                     
        </script>    
        <script src="js/deporte.js"></script>
    </body>

</html>
