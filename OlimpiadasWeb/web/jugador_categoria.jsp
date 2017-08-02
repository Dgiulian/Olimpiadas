<%@page import="transaccion.TCategoria"%>
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
Jugador jugador = (Jugador) request.getAttribute("jugador");
List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
List<Categoria> inscripciones = (List<Categoria>) request.getAttribute("inscripciones");

TCategoria tcategoria = new TCategoria();
tcategoria.setOrderBy(" id_deporte ");
if(categorias==null) categorias = tcategoria.getListFiltro(null);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="tpl_head.jsp" %>  
    <style>
        .lblCategoria{
            width:100%;
            height: 100%;
            cursor:pointer;
            /*background-color: #ddd;*/
/*            padding-top: 0px;
            padding-bottom: 0px;*/
        }
        
        td{ height: 100%;            
            height: 100%;
        }
        td:hover{
            
        }
    </style>
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
                            <form action="<%=PathCfg.JUGADOR_CATEGORIA%>" method="POST">
                            <div class="row">
                        <div class="col-md-12">
                            <table class="table  table-bordered table-condensed" id="tblCategoria">
                            <% 
                                Integer id_deporte = null;
                                Integer cant = 0;
                                Integer max_col = 3;
                                for(Categoria categoria: categorias) { 
                                    String id_categoria = "id_categoria_" + categoria.getId().toString();
                                    String checked = inscripciones.contains(categoria)?"checked":"";
                                  cant +=1;
                                  if(!categoria.getId_deporte().equals(id_deporte) || cant >= max_col) {
                                    if(id_deporte!=null) { // Completo la FILA
                                        while(cant < max_col){ 
                                            out.print("<td></td><td></td>");
                                            cant +=1;
                                        }
                                        out.print("</tr>");
                                    }
                                    cant =0;
                                    id_deporte = categoria.getId_deporte();
                                    out.print("<tr>");
                                   } %>
                                    
                                    <td><label for="<%=id_categoria%>" class="lblCategoria"><%=categoria.getNombre()%></label></td>
                                    <td><input type='checkbox' id='<%=id_categoria%>' name='<%=id_categoria%>' <%=checked%>></td>
                                        
                            
                            <% } %>
                            <% 
                                while(cant < (max_col - 1)){
                                    cant +=1;
                                    out.print("<td></td><td></td>"); 
                                }
                            %>
                            </table>
                        <input id="id" name="id" type="hidden" class="" value="<%=jugador.getId()%>" >                                   
                        <div class="row">
                            <div class="col-lg-12">
                                <button type="submit" class="btn btn-success">Guardar</button>
                                <a type="reset" class="btn btn-default" href="<%=PathCfg.JUGADOR%>">Cancelar</a>
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
