<%@page import="bd.Categoria"%>
<%    Categoria categoria = (Categoria) request.getAttribute("categoria");
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
                        <h3 class="page-header">Grupos - <%=categoria.getNombre()%></h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>

                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading text-right">
                                    <span id="btnNuevo" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nueva</span>
                                    <span id="btnNuevo" class="btn btn-link"><span class="fa fa-print fa-fw"> </span>Imprimir</span>
                                    <input type="hidden" id="search_categoria" name="search_categoria" value="<%=categoria.getId()%>">
                                </div>
                                <!-- /.panel-heading -->
                                <div class="panel-body">
                                    <div class="dataTable_wrapper">
                                        <table class="table table-striped table-bordered table-condensed" id="tblGrupo">
                                            <colgroup>
                                                <col style="width:5%"></col>                                        
                                                <col style="width:30%"></col>
                                                <col style=""></col>
                                                <col class="acciones"></col>
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Nombre</th>
                                                    <th>Observacion</th>                                            
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
        <script id="grupo_list" type="text/x-handlebars-template">
            {{#each records}}
            <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{nombre}}</td>
            <td class="">{{observacion}}</td>
            <td>
            <span href="" data-index="{{@index}}" class="btn btn-link btn-block btn-tablaresultado">TABLA</span>
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
        <script id="grupo_edit" type="text/x-handlebars-template">
            <div class="row">
            <div class="col-md-12">
            <form class="form-vertical">
            <input id="id" name="id" type="hidden" class="" value="{{id}}" >
                  <input id="id_categoria" name="id_categoria" type="hidden" class="" value="{{id_categoria}}" >
            <div class="form-group">
            <label  for="nombre">Nombre</label>            
            <input id="nombre" name="nombre" type="text" class="form-control input-md" value="{{nombre}}">
            </div>  
            <div class="form-group">
                <label  for="tipo_vista_tabla">Tipo TABLA</label>
                <select id="tipo_vista_tabla" name="tipo_vista_tabla" type="text" class="form-control input-group-lg">
                {{#select tipo_vista_tabla}}                    
                    <option value="1" >TABLA</option>
                    <option value="2" >X RESULTADO</option>
                {{/select}}
                </select>
            </div>
            <div class="form-group">
            <label for="arrEquipo">Equipos (con jugadores cargados)</label>            
            <select id="arrEquipo" name="arrEquipo" type="text" class="form-control input-md" multiple>
            {{#select selected}}
            {{#each equipos}}
            <option value="{{id}}" >{{nombre}}</option>
            {{/each}}
            {{/select}}
            </select>                      
            </div>
            <div class="form-group">
            <label  for="observacion">Observaci&oacute;n</label>            
            <textarea id="observacion" name="observacion" type="text" class="form-control input-md" >{{observacion}}</textarea>
            </div>    
            
            </form>
            </div>
            </div>                     
        </script>    
        <script src="js/grupo.js"></script>
    </body>

</html>
