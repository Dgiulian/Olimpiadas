<%@page import="bd.Deporte"%>
<%@page import="java.util.List"%>
<%
    List<Deporte> deportes = (List<Deporte>) request.getAttribute("deportes");
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
                        <h3 class="page-header">Categor&iacute;as</h3>
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
                                    <div class="row form-search">
                                        <form class="form-inline">
                                            <div class="form-group">
                                                <label for="search_nombre">Nombre:</label>
                                                <input type="text" class="form-control" id="search_nombre">
                                            </div>
                                            <div class="form-group">
                                                <label for="search_deportes">Deporte:</label>
                                                <select type="text" class="form-control" id="search_deporte">
                                                    <option value="0"> Todos</option>
                                                    <% for(Deporte deporte:deportes){%>
                                                    <option value="<%=deporte.getId()%>"><%=deporte.getNombre()%></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                            <button id="btnSearch" type="button" class="btn btn-default">Buscar</button>
                                        </form> 
                                    </div>
                                    <div class="dataTable_wrapper">
                                        <table class="table table-striped table-bordered table-condensed" id="tblCategoria">
                                            <colgroup>
                                                <col style="width:5%"></col>                                        
                                                <col style="width:30%"></col>
                                                <col style="width:15%"></col>
                                                <col style="width:15%"></col>
                                                <col></col>                                                
                                                <col style="width:5%"></col>
                                                <col class="acciones"></col>
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Nombre</th>
                                                    <th>Abreviaci&oacute;n</th>
                                                    <th>Deporte</th>
                                                    <th>Detalle</th>
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
        <script id="categoria_list" type="text/x-handlebars-template">
            {{#each records}}
            <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{nombre}}</td>
            <td class="">{{nombre_corto}}</td>
            <td class="">{{deporte.nombre}}</td>
            <td class="">{{detalle}}</td>
            <td>
            <span href="" data-index="{{@index}}" class="btn btn-link btn-block btn-grupo">Grupos</span>
            </td>
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
        <script id="categoria_edit" type="text/x-handlebars-template">
            <div class="row">
            <div class="col-md-12">
            <form class="form-vertical">
            <input id="id" name="id" type="hidden" class="" value="{{id}}" >
            <div class="form-group">
                <label for="nombre">Nombre</label>                      
                <input id="nombre" name="nombre" type="text" class="form-control input-group-lg" value="{{nombre}}">
            </div>
            <div class="form-group">
                <label for="nombre_corto">Nombre corto</label>                      
                <input id="nombre_corto" name="nombre_corto" type="text" class="form-control input-group-lg" value="{{nombre_corto}}">
            </div>
            <div class="form-group">
            <label  for="id_deporte">Deporte</label>
            <select id="id_deporte" name="id_deporte" type="text" class="form-control input-group-lg">
            {{#select id_deporte}}
            {{#each deportes}}
            <option value={{id}} >{{nombre}}</option>
            {{/each}}
            {{/select}}
            </select>
            </div>
            <div class="form-group">
            <label  for="detalle">Detalle:</label>
            <textarea id="detalle" name="detalle" type="text" class="form-control input-group-lg" >{{detalle}}</textarea>
            </div>                  
            </form>
            </div>
            </div>                     
        </script>    
        <script src="js/categoria.js"></script>
    </body>

</html>
