<%@page import="bd.Categoria"%>
<%@page import="bd.Delegacion"%>
<%@page import="java.util.List"%>
<%    
    List<Delegacion> delegaciones = (List<Delegacion>) request.getAttribute("delegaciones");
    List<Categoria> categorias    = (List<Categoria>)  request.getAttribute("categorias");
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
                        <h3 class="page-header">Equipos</h3>
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
                                                <label for="id_delegacion_filtro">Delegacion:</label>
                                                <select id="id_delegacion_filtro" name="id_delegacion" type="text" class="form-control input-md">
                                                    <option value="0">Seleccione la delegaci&oacute;n</option>
                                                    <% for (Delegacion delegacion : delegaciones) {%>
                                                    <option value="<%=delegacion.getId()%>"><%=delegacion.getNombre()%></option>
                                                    <% }%>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="id_categoria_filtro">Categor&iacute;a:</label>
                                                <select id="id_categoria_filtro" name="id_categoria" type="text" class="form-control input-md">
                                                    <option value="0">Seleccione la categor&iacute;a</option>
                                                    <% for (Categoria categoria : categorias) {%>
                                                    <option value="<%=categoria.getId()%>"><%=categoria.getNombre()%></option>
                                                    <% }%>
                                                </select>
                                            </div>    
                                            <button id="btnSearch" type="button" class="btn btn-default">Buscar</button>
                                        </form> 
                                    </div>
                                    <div class="dataTable_wrapper">
                                        <table class="table table-striped table-bordered table-condensed" id="tblEquipo">
                                            <colgroup>
                                                <col style="width:10%"></col>                                        
                                                <col style="width:25%"></col>
                                                <col style="width:15%"></col>                                        
                                                <col></col>  
                                                <col style="width:10%"></col>
                                                <col class="acciones"></col>
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Nombre</th>
                                                    <th>Delegacion</th>
                                                    <th>Observaciones</th>
                                                    <th>Integrantes</th>
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
        <script id="equipo_list" type="text/x-handlebars-template">
            {{#each records}}
            <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{nombre}}</td>
            <td class="">{{delegacion.nombre}}</td>
            <td class="">{{observaciones}}</td>
            <td>
            <a href="EquipoDetalle?id_equipo={{id}}" data-index="{{@index}}" class="btn btn-link btn-block btn-integraste">Integrantes</a>
            </td>
            <td class="">
            <span href="" data-index="{{@index}}" class="btn btn-xs btn-circle  btn-warning  btn-edit"><span class="fa fa-edit fw"></span></span>
            <span href="" data-index="{{@index}}" class="btn btn-xs btn-danger btn-circle btn-del"><span class="fa fa-trash fw"></span></span>
            </td>
            </tr>
            {{else}}
            <tr>
            <td colspan="5"><center><strong>No se encontraron resultados</strong></center></td>
            </tr>
            {{/each}}

        </script>   
        <script id="equipo_edit" type="text/x-handlebars-template">
            <div class="row">
            <div class="col-md-12">
            <form class="form-vertical">
            <input id="id" name="id" type="hidden" class="" value="{{id}}" >
            <div class="form-group">
            <label for="nombre">Nombre</label>            
            <input id="nombre" name="nombre" type="text" class="form-control input-md" value="{{nombre}}">
            </div>
            <div class="form-group">
            <label for="id_delegacion">Delegacion</label>            
            <select id="id_delegacion" name="id_delegacion" type="text" class="form-control input-md">
            {{#select id_delegacion}}
            {{#each delegaciones}}
            <option value="{{id}}" >{{nombre}}</option>
            {{/each}}
            {{/select}}
            </select>
            </div>
            <div class="form-group">
            <label for="observaciones">Detalle:</label>            
            <textarea id="observaciones" name="observaciones" type="text" class="form-control input-md" >{{detalle}}</textarea>            
            </div>                  
            </form>
            </div>
            </div>                     
        </script>    
        <script src="js/equipo.js"></script>
    </body>

</html>
