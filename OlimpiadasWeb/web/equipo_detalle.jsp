<%@page import="bd.Equipo"%>
<%
Equipo equipo = (Equipo) request.getAttribute("equipo");
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
                        <h3>Equipo: <%=equipo.getNombre()%> <a href="<%=PathCfg.EQUIPO%>" class="btn btn-default">Volver</a></h3>                        
                    <div class="panel panel-default">
                        
                        <div class="panel-heading">
                            Listado de Jugadores <span id="btnNuevo" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nuevo</span>
                            <input id="id_equipo" name="id_equipo" type="hidden" value="<%=equipo.getId()%>">
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-condensed" id="tblEquipo_detalle">
                                    <colgroup>
                                        <col style=""></col>                                        
                                        <col style="width:65%"></col>
                                        <col style="width:15%"></col>                                        
                                        <col style="width:10%"></col>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Nombre</th>
                                            <th>Matricula</th>                                            
                                            <th></th>
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
    <script id="equipo_detalle_list" type="text/x-handlebars-template">
        {{#each records}}
          <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{jugador.nombre_apellido}}</td>
            <td class="">{{jugador.matricula}}</td>
            <td class="">              
              <span href="" data-index="{{@index}}" class="btn btn-xs btn-danger btn-circle btn-del"><span class="fa fa-trash fw"></span></span>
            </td>
          </tr>
        {{else}}
            <tr>
            <td colspan="5"><center><strong>No se encontraron resultados</strong></center></td>
            </tr>
        {{/each}}
    </script>   
     <script id="equipo_detalle_edit" type="text/x-handlebars-template">
        <div class="row">
          <div class="col-md-12">
              <form class="form-vertical">
                  <input id="id" name="id" type="hidden" class="" value="{{id}}" >
                  
                  
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="id_jugador">Jugador</label>
                      <div class="col-md-8">
                        <select id="id_jugador" name="id_jugador" type="text" class="form-control input-md">
                        {{#select id_jugador}}
                        {{#each jugadores}}
                            <option value={{id}} >{{nombre_apellido}}</option>
                        {{/each}}
                        {{/select}}
                        </select>
                      </div>                                
                   </div>                                
                </form>
          </div>
        </div>                     
    </script>    
    <script src="js/equipo_detalle.js"></script>
</body>

</html>
