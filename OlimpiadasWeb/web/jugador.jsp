<%@page import="bd.Delegacion"%>
<%@page import="java.util.List"%>
<%@page import="transaccion.TDelegacion"%>
<!DOCTYPE html>
<html lang="en">
<%
    List<Delegacion> delegaciones = new TDelegacion().getList();
%>
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
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Listado de Jugadores <span id="btnNuevo" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nuevo</span>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                    <div class="col-lg-5">
                                         <div class="form-group">
                                             <label class="col-md-2 control-label" for="id_delegacion">Delegaci&oacute;n</label>
                                            <div class="col-md-8">
                                                <select id="id_delegacion_filtro" name="id_delegacion" type="text" class="form-control input-md">
                                                    <option value="0">Seleccione la delegaci&oacute;n</option>
                                                    <% for(Delegacion delegacion: delegaciones) {%>
                                                        <option value="<%=delegacion.getId()%>"><%=delegacion.getNombre()%></option>
                                                    <% }%>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                
                                <table class="table table-striped table-bordered table-condensed" id="tblJugador">
                                    <colgroup width="">
                                        <col style="width:5%"></col>
                                        <col style=""></col>
                                        <col style="width:20%"></col>
                                        <col style="width:20%"></col>
                                        <col style="width:10%"></col>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Nombre</th>
                                            <th>Matricula</th>
                                            <th>Dni</th>
                                            <th>Fecha Nacimiento</th>                                            
                                            <th>Delegacion</th>
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
    <script id="jugador_list" type="text/x-handlebars-template">
        {{#each records}}
          <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{nombre_apellido}}</td>
            <td class="">{{matricula}}</td>
            <td class="">{{dni}}</td>
                
            <td class="">{{convertirFecha fecha_nacimiento}}</td>
            <td class="">{{delegacion.nombre}}</td>
            <td class="">
              <span data-index="{{@index}}" class="btn btn-xs btn-circle  btn-warning btn-edit"><span class="fa fa-edit fw"></span></span>
              <span data-index="{{@index}}" class="btn btn-xs btn-danger btn-circle btn-del"><span class="fa fa-trash fw"></span></span>
            </td>
          </tr>
        {{else}}
            <tr>
            <td colspan="7"><center><strong>No se encontraron resultados</strong></center></td>
            </tr>
        {{/each}}
        
    </script>   
     <script id="jugador_edit" type="text/x-handlebars-template">
        <div class="row">
          <div class="col-md-12">
              <form class="form-vertical">
                  <input id="id" name="id" type="hidden" class="" value="{{id}}" >
                  <div class="form-group">
                      <label class="col-md-4 control-label" for="nombre_appellido">Nombre</label>
                      <div class="col-md-8">
                      <input id="nombre_apellido" name="nombre_apellido" type="text" class="form-control input-md" value="{{nombre_apellido}}">
                   </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="dni">Dni</label>
                      <div class="col-md-8">
                      <input id="dni" name="dni" type="text" class="form-control input-md" value="{{dni}}">
                   </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="fecha_nacimiento">Fecha Nacimiento</label>
                      <div class="col-md-8">
                        <div class="controls">
                            <div class="input-group date date-picker">
                              <input type="text" id="fecha_nacimiento" name="fecha_nacimiento" class="form-control date-input "  value="{{convertirFecha fecha_nacimiento}}">
                              <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                            </div>
                        </div>                      
                   </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="matricula">Matricula</label>
                      <div class="col-md-8">
                      <input id="matricula" name="matricula" type="text" class="form-control input-md" value="{{matricula}}">
                   </div>   
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="id_delegacion">Delegacion</label>
                      <div class="col-md-8">
                      <select id="id_delegacion" name="id_delegacion" type="text" class="form-control input-md">
                      {{#select id_delegacion}}
                      {{#each delegaciones}}
                          <option value={{id}} >{{nombre}}</option>
                      {{/each}}
                      {{/select}}
                      </select>
                      
                   </div>
                            
                </form>
          </div>
        </div>                     
    </script>    
    <script src="js/jugador.js"></script>
</body>

</html>
