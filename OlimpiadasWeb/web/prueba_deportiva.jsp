<%@page import="java.util.ArrayList"%>
<%@page import="bd.Grupo"%>
<%@page import="bd.Categoria"%>
<%@page import="bd.Deporte"%>
<%@page import="java.util.List"%>
<%    List<Deporte> deportes = (List<Deporte>) request.getAttribute("deportes");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    List<Grupo> grupos = (List<Grupo>) request.getAttribute("grupos");
    String[] estados = {"Todos", "En Agenda", "En Curso", "Finalizada", "Suspendida"};
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
                        <h3 class="page-header">Listado de pruebas deportivas </h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading text-right">
                                    <a href="<%=PathCfg.PRUEBA_EDIT%>" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nuevo</a>
                                    <span id="btnPrint" class="btn btn-link"><span class="fa fa-print fa-fw"> </span>Imprimir</span>
                                </div>
                                <!-- /.panel-heading -->
                                <div class="panel-body">

                                    <form class="form-inline">
                                        <div class="form-group">                                                
                                            <label class="control-label" for="search_fecha">Fecha</label>
                                            <div class="controls">
                                                <div class="input-group date date-picker" style="diplay:inline">
                                                    <input type="text" id="search_fecha" name="search_fecha" class="form-control date-input "  value="">
                                                    <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                                                </div>
                                            </div>           
                                        </div>
                                        <div class="form-group">
                                            <label for="search_deportes">Deporte</label>
                                            <div class="controls">
                                                <select type="text" class="form-control" id="search_deporte">
                                                    <option value="0"> Todos</option>
                                                    <% for (Deporte deporte : deportes) {%>
                                                    <option value="<%=deporte.getId()%>"><%=deporte.getNombre()%></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class=" control-label" for="isearch_categoria">Categor&iacute;a</label>
                                            <div class="">

                                                <select id="search_categoria" name="search_categoria" type="text" class="form-control input-md"  >
                                                    <option value="0">Todas</option>
                                                    <% for (Categoria categoria : categorias) {%>
                                                    <option value="<%=categoria.getId()%>" ><%=categoria.getNombre()%></option>

                                                    <% } %>
                                                </select>                      
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class=" control-label" for="search_grupo">Grupo</label>
                                            <div class="">
                                                <select id="search_grupo" name="search_grupo" type="text" class="form-control input-md"  >
                                                    <option value="0">Todos</option>
                                                    <% for (Grupo grupo : grupos) {%>
                                                    <option value="<%=grupo.getId()%>" ><%=grupo.getNombre()%></option>
                                                    <% } %>
                                                </select>                      
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="controls">
                                                <label class=" control-label" for="search_estado">Estado</label>
                                                <div class="">
                                                    <select id="search_estado" name="search_estado" type="text" class="form-control input-md">
                                                        <% for (Integer i = 0; i < estados.length; i++) {%>
                                                        <option value="<%=i%>" ><%=estados[i]%></option>
                                                        <%}%>
                                                    </select>                      
                                                </div>
                                            </div>
                                        </div> 

                                        <button id="btnSearch" type="button" class="btn btn-default">Buscar</button>

                                    </form> 
                                    <br>
                                    <div class="dataTable_wrapper">
                                        <table class="table table-striped table-bordered table-condensed" id="tblPrueba">
                                            <colgroup>
                                                <col style="width:5%"></col>                                        
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col style=""></col>
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col class="acciones"></col>
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Fecha</th>
                                                    <th>Deporte</th>
                                                    <th>Categor&iacute;a</th>
                                                    <th>Grupo</th>
                                                    <th>Estado</th>
                                                    <th>Puntaje</th>
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

        <script id="prueba_list" type="text/x-handlebars-template">
            {{#each records}}
            <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{convertirFecha fecha}} {{hora}}</td>
            <td class="">{{deporte.nombre}}</td>
            <td class="">{{categoria.nombre}}</td>
            <td class="">{{grupo.nombre}}</td>
            <td class="">{{estado}}</td>
            <td class="acciones">
            <a  href="PruebaEdit?id={{id}}" class="btn btn-xs btn-circle  btn-warning  "><span class="fa fa-edit fw"></span></a>              
            <span  data-index="{{@index}}" class="btn btn-xs btn-danger btn-circle btn-del"><span class="fa fa-trash fw"></span></span>
            </td>
            </tr>
            {{else}}
            <tr>
            <td colspan="8"><center><strong>No se encontraron resultados</strong></center></td>
            </tr>
            {{/each}}
        </script>   
        <script id="prueba_edit" type="text/x-handlebars-template">
            <div class="row">
            <div class="col-md-12">
            <form class="form-vertical">
            <input id="id" name="id" type="hidden" class="" value="{{id}}" >
            <div class="form-group">
            <label class="col-md-4 control-label" for="fecha">Fecha y Hora</label>
            <div class="col-md-4">
            <div class="controls">
            <div class="input-group date date-picker">
            <input type="text" id="fecha" name="fecha" class="form-control date-input "  value="{{convertirFecha fecha}}">
            <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
            </div>
            </div>           
            </div>
            <div class="col-md-4">
            <input type="text" name="hora" id="hora" class="form-control input-md hora" value="{{hora}}">
            </div>
            </div>
            <div class="form-group">
            <label class="col-md-4 control-label" for="id_estado">Estado</label>
            <div class="col-md-8">
            <select id="id_estado" name="id_estado" type="text" class="form-control input-md">
            {{#select id_estado}}
            <option value="1">En Agenda</option>
            <option value="2">En Curso</option>
            <option value="3">Finalizada</option>
            <option value="4">Suspendida</option>
            {{/select}}
            </select>                      
            </div>
            </div>

            <div class="form-group">
            <label class="col-md-4 control-label" for="tipo_puntaje">Puntaje </label>
            <div class="col-md-4">
            <select id="tipo_puntaje" name="tipo_puntaje" type="text" class="form-control input-md">
            {{#select tipo_puntaje}}                            
            <option value="1" >Puntos</option>
            <option value="2" >Tiempo</option>
            {{/select}}
            </select>
            </div>
            <div class="col-md-4">
            <select id="orden_puntaje" name="orden_puntaje" type="text" class="form-control input-md">
            {{#select orden_puntaje}}                            
            <option value="1" >Ascendente</option>
            <option value="2" >Descendente</option>
            {{/select}}
            </select>
            </div>
            </div>

            <div class="form-group">
            <label class="col-md-4 control-label" for="id_deporte">Deporte</label>
            <div class="col-md-8">
            <select id="id_deporte" name="id_deporte" type="text" class="form-control input-md" {{#if disabled }} disabled {{/if}} >
            {{#select id_deporte}}
            {{#each deportes}}
            <option value="{{id}}" >{{nombre}}</option>
            {{/each}}
            {{/select}}
            </select>
            </div>
            </div>
            <div class="form-group">
            <label class="col-md-4 control-label" for="id_categoria">Categor&iacute;a</label>
            <div class="col-md-8">
            <select id="id_categoria" name="id_categoria" type="text" class="form-control input-md" {{#if disabled }} disabled {{/if}} >
            {{#select id_categoria}}
            {{#each categorias}}
            <option value="{{id}}" >{{nombre}}</option>
            {{/each}}
            {{/select}}
            </select>                      
            </div>
            </div>
            <div class="form-group">
            <label class="col-md-4 control-label" for="id_grupo">Grupo</label>
            <div class="col-md-8">
            <select id="id_grupo" name="id_grupo" type="text" class="form-control input-md" {{#if disabled }} disabled {{/if}} >
            {{#select id_grupo}}
            {{#each grupos}}
            <option value="{{id}}">{{nombre}}</option>
            {{/each}}                                
            {{/select}}
            </select>                      
            </div>
            </div>
            <div class="form-group">
            <label class="col-md-4 control-label" for="id_estado">Equipos</label>
            <div class="col-md-8">
            <select id="equipos" name="equipos" type="text" class="form-control input-md" multiple {{#if disabled }} disabled {{/if}} >
            {{#select selected}}
            {{#each equipos}}
            <option value="{{id}}">{{nombre}}</option>
            {{/each}}
            {{/select}}
            </select>                      
            </div>
            </div>
            <div class="form-group">
            <label class="col-md-4 control-label" for="observaciones">Observaciones</label>
            <div class="col-md-8">                      
            <textarea id="observaciones" name="observaciones" type="text" class="form-control input-md" >{{observaciones}}</textarea>
            </div>    
            </div>                  
            </form>
            </div>
            </div>                     
        </script>    
        <script src="js/prueba_deportiva.js"></script>
    </body>

</html>
