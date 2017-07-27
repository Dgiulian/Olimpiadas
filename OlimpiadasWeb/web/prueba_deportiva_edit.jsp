<%@page import="transaccion.TEquipo_detalle"%>
<%@page import="bd.Delegacion"%>
<%@page import="bd.Jugador"%>
<%@page import="bd.Jugador"%>
<%@page import="bd.Equipo_detalle"%>
<%@page import="java.util.List"%>

<%@page import="bd.Equipo"%>
<%
Equipo equipo = (Equipo) request.getAttribute("equipo");
Delegacion delegacion = (Delegacion) request.getAttribute("delegacion");
List<Jugador> lstJugadores = (List<Jugador>) request.getAttribute("lstJugadores");
List<Equipo_detalle> lstDetalle = (List<Equipo_detalle>) request.getAttribute("lstEquipo_detalle");
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
                    <div class="">
                        <h3>Equipo: <%=equipo.getNombre()%></h3>                        
                        <h3>Delegaci&oacute;n: <%=delegacion.getNombre()%> </h3>
                    </div>
                    <div class="panel panel-default">
                        
                        <div class="panel-heading">
                            Jugadores del equipo <!-- <span id="btnNuevo" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nuevo</span>-->

                        </div>
                        <!-- /.panel-heading -->
                        
                        <div class="panel-body">
                            <form action="<%=PathCfg.EQUIPO_DETALLE_EDIT%>" method="POST">
                                <input id="id_equipo" name="id_equipo" type="hidden" value="<%=equipo.getId()%>">
                                <input id="id_delegacion" name="id_delegacion" type="hidden" value="<%=equipo.getId_delegacion()%>">
                                <div class="dataTable_wrapper">                                
                                    <select id="id_jugador" name="id_jugador" multiple>
                                        <% for(Jugador jugador: lstJugadores)  { 
                                            String selected = TEquipo_detalle.contiene(lstDetalle,jugador.getId())?"selected":"";                                        
                                        %>
                                            <option value="<%=jugador.getId()%>" <%=selected%>><%=jugador.getNombre_apellido()%></option>
                                        <% } %>
                                    </select>
                                </div>
                                <div class="row">
                                   <div class="col-lg-12">
                                       <button type="submit" class="btn btn-success">Guardar</button>
                                       <a type="reset" class="btn btn-default" href="<%=PathCfg.EQUIPO%>">Cancelar</a>
                                   </div>
                               </div>
                            </form>
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
<!--    <script id="equipo_detalle_list" type="text/x-handlebars-template">
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
    </script>    -->
    <script src="js/equipo_detalle.js"></script>
</body>

</html>
