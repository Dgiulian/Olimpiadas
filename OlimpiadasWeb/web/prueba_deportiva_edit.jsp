<%@page import="transaccion.TEquipo"%>
<%@page import="bd.Sede"%>
<%@page import="bd.Grupo"%>
<%@page import="bd.Categoria"%>
<%@page import="bd.Deporte"%>
<%@page import="utils.TFecha"%>
<%@page import="bd.Prueba_deportiva"%>
<%@page import="transaccion.TEquipo_detalle"%>
<%@page import="bd.Delegacion"%>
<%@page import="bd.Jugador"%>
<%@page import="bd.Jugador"%>
<%@page import="bd.Equipo_detalle"%>
<%@page import="java.util.List"%>

<%@page import="bd.Equipo"%>
<%
Prueba_deportiva prueba = (Prueba_deportiva) request.getAttribute("prueba");
List<Sede> sedes = (List<Sede>) request.getAttribute("sedes");
List<Deporte> deportes = (List<Deporte>) request.getAttribute("deportes");
List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
List<Grupo> grupos = (List<Grupo>) request.getAttribute("grupos");
List<Equipo> equipos = (List<Equipo>) request.getAttribute("equipos");
List<Equipo> equipos_sel = (List<Equipo>) request.getAttribute("equipos_sel");
String[] estados = {"En Agenda","En Curso","Finalizada","Suspendida"};

String[] puntajes = {"Puntos","Tiempo" };
String[] ordenes = {"Ascendente","Descendente" };

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
                    <div class="panel panel-default">
                        
                        <div class="panel-heading">
                            Prueba deportiva 
                        </div>
                        <!-- /.panel-heading -->
                        
                        <div class="panel-body">
                            <form action="<%=PathCfg.PRUEBA_EDIT%>" method="POST">
                                <div class="row">
                 <div class="col-md-12">

                   <input id="id" name="id" type="hidden" class="" value="<%=prueba.getId()%>" >
                       <div class="form-group">
                            <label class="col-md-2 control-label" for="id_estado">Fecha y Hora</label>
                            <div class="col-md-5">
                                <div class="controls">
                                    <div class="input-group date date-picker">
                                        <input type="text" id="fecha" name="fecha" class="form-control date-input "  value="<%=TFecha.formatearFechaBdVista(prueba.getFecha())%>">
                                      <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                                    </div>
                                </div>           
                            </div>
                            <div class="col-md-5">
                                <input type="text" name="hora" id="hora" class="form-control input-md hora" value="<%=prueba.getHora()%>">
                            </div>
                      </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label" for="id_sede">Sede</label>
                        <div class="col-md-10">
                           <select id="id_sede" name="id_sede" type="text" class="form-control input-md"  >
                        <% for(Sede sede:sedes) {
                            String selSede = sede.getId().equals(prueba.getId_sede())?"selected":"";
                        %>
                        <option value="<%=sede.getId()%>" <%=selSede%>><%=sede.getNombre()%></option>
                        
                        <% } %>
                      </select>                             
                         </div>
                     </div>
                      <div class="form-group">
                        <label class="col-md-2 control-label" for="id_estado">Estado</label>
                        <div class="col-md-10">
                          <select id="id_estado" name="id_estado" type="text" class="form-control input-md">
                              <% for(Integer i=0;i<estados.length;i++){
                                  String selEstado = i.equals(prueba.getId_estado())?"selected":"";
                              %>
                              
                                <option value="<%=i%>" <%=selEstado%>><%=estados[i]%></option>
                               <%}%>
                          </select>                      
                         </div>
                     </div>
                    
                    <div class="form-group">
                        <label class="col-md-2 control-label" for="tipo_puntaje">Puntaje </label>
                        <div class="col-md-5">
                          <select id="tipo_puntaje" name="tipo_puntaje" type="text" class="form-control input-md">
                               <% for(Integer i=0;i<puntajes.length;i++){
                                  String selPuntaje = i.equals(prueba.getTipo_puntaje())?"selected":"";
                              %>
                                <option value="<%=i%>"  <%=selPuntaje%>><%=puntajes[i]%></option>
                               <%}%>
                          
                          </select>
                        </div>
                        <div class="col-md-5">
                          <select id="orden_puntaje" name="orden_puntaje" type="text" class="form-control input-md">
                            <% for(Integer i=0;i<ordenes.length;i++){
                                  String selOrden = i.equals(prueba.getOrden_puntaje())?"selected":"";
                              %>
                                <option value="<%=i%>"  <%=selOrden%>><%=ordenes[i]%></option>
                               <%}%>
                          
                          </select>
                        </div>
                    </div>
                    
                   <div class="form-group">
                      <label class="col-md-2 control-label" for="id_deporte">Deporte</label>
                      <div class="col-md-10">
                        <select id="id_deporte" name="id_deporte" type="text" class="form-control input-md" >
                        
                        <% for(Deporte deporte:deportes) {
                            String selDeporte = deporte.getId().equals(prueba.getId_deporte())?"selected":"";
                        %>
                        <option value="<%=deporte.getId()%>" <%=selDeporte%>><%=deporte.getNombre()%></option>
                        
                        <% } %>
                        </select>
                      </div>
                   </div>
                   <div class="form-group">
                      <label class="col-md-2 control-label" for="id_categoria">Categor&iacute;a</label>
                      <div class="col-md-10">
                      <select id="id_categoria" name="id_categoria" type="text" class="form-control input-md"  >
                        <% for(Categoria categoria:categorias) {
                            String selCategoria = categoria.getId().equals(prueba.getId_categoria())?"selected":"";
                        %>
                        <option value="<%=categoria.getId()%>" <%=selCategoria%>><%=categoria.getNombre()%></option>
                        
                        <% } %>
                      </select>                      
                   </div>
                   </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label" for="id_grupo">Grupo</label>
                        <div class="col-md-10">
                          <select id="id_grupo" name="id_grupo" type="text" class="form-control input-md"  >
                            <% for(Grupo grupo:grupos) {
                            String selGrupo = grupo.getId().equals(prueba.getId_grupo())?"selected":"";
                            %>
                        <option value="<%=grupo.getId()%>" <%=selGrupo%>><%=grupo.getNombre()%></option>
                        
                        <% } %>
                          </select>                      
                         </div>
                     </div>
                   <div class="form-group">
                        <label class="col-md-2 control-label" for="id_estado">Equipos</label>
                        <div class="col-md-10">
                          <select id="equipos" name="equipos" type="text" class="form-control input-md" multiple  >
                            <% for(Equipo equipo:equipos) {
                                String selEquipo = equipos_sel.contains(equipo)?"selected":"";
                                
                            %>
                            <option value="<%=equipo.getId()%>" <%=selEquipo%>><%=equipo.getNombre()%></option>
                            <% } %>
                          </select>                      
                         </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label" for="observaciones">Observaciones</label>
                            <div class="col-md-10">                      
                            <textarea id="observaciones" name="observaciones" type="text" class="form-control input-md" ><%=prueba.getObservaciones()%></textarea>
                            </div>    
                        </div>                  
                             <div class="row">
                                   <div class="col-lg-12">
                                       <button type="submit" class="btn btn-success">Guardar</button>
                                       <a type="reset" class="btn btn-default" href="<%=PathCfg.PRUEBA%>">Cancelar</a>
                                   </div>
                               </div>
                        
                      </div>
                        </form>
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
 <script src="js/prueba_deportiva_edit.js"></script>
</body>

</html>
