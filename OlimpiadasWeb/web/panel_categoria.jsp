<%@page import="bd.Grupo"%>
<%@page import="transaccion.TGrupo"%>
<%@page import="bd.Categoria"%>
<%@page import="java.util.Iterator"%>
<%@page import="bd.Deporte"%>
<%@page import="java.util.List"%>
<%@page import="transaccion.TDeporte"%>
<!DOCTYPE html>
<html lang="en">

    <%        List<Grupo> grupos = (List<Grupo>) request.getAttribute("grupos");
        String[] estados = {"Todos", "En Agenda", "En Curso", "Finalizada", "Suspendida"};
    %>

    <head>

        <%@include file="tpl_head.jsp" %>
        <%Categoria categoria = (Categoria) request.getAttribute("categoria");%>
        <%Deporte deporte = (Deporte) request.getAttribute("deporte");%>
        <style type="text/css">
            .table > tbody > tr > td {
                vertical-align: middle;
            }
            .team{
                font-weight: bold;
                font-family: cursive;
                font-size: 14px;
            }
            .selected_grupo{
                background-color: #d5d5d5;
            }
        </style>
    </head>

    <body>
        <%@include file="tpl_navbar.jsp" %>
        <div id="wrapper">
            <div id="page-wrapper">
                <br>
                <div class="row">
                    <div class="col-md-2">                        
                        <h3><%=deporte.getNombre()%></h3>
                        <h4><%=categoria.getNombre()%></h4>
                        <table class="table table-hover">                            
                            <thead>
                            <th>GRUPOS</th>
                            <th>Detalle</th>
                            </thead>
                            <tbody>
                                <tr class="selected_grupo grupo" id_grupo="0">
                                    <td>TODOS</td>
                                    <td><button type="button" class="btn btn-success btn-circle"><i class="glyphicon glyphicon-search"></i></button></td>
                                </tr>
                                <%
                                    List<Grupo> lista = grupos;
                                    Iterator it = lista.iterator();
                                    while (it.hasNext()) {
                                        Grupo grupo = (Grupo) it.next();
                                %>
                                <tr class="grupo" id_grupo="<%=grupo.getId()%>" nombre_grupo="<%=grupo.getNombre()%>" tipo_tabla="<%=grupo.getTipo_vista_tabla()%>">
                                    <td><%=grupo.getNombre()%></td>
                                    <td><button type="button" class="btn btn-success btn-circle"><i class="glyphicon glyphicon-search"></i></button></td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                        <img class="img-responsive" src="images/deportes/<%=deporte.getImagen()%>"/>

                    </div>
                    <div class="col-md-10">


                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#agenda" aria-controls="home" role="tab" data-toggle="tab">AGENDA</a></li>
                            <li role="presentation"><a  onclick="cargarTablas()" href="#tablas" aria-controls="tablas" role="tab" data-toggle="tab">TABLA DE POSICIONES</a></li>
                            <li role="presentation"><a onclick="cargarResultado()"href="#medallero" aria-controls="medallero" role="tab" data-toggle="tab">MEDALLERO</a></li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content" style="padding-top: 10px;">

                            <div role="tabpanel" class="tab-pane active" id="agenda">
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
                                </form> 
                                <br>
                                <table id="tbl_agenda" class="table table-hover table-condensed">
                                    <thead>                                    
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>

                            </div>

                            <div role="tabpanel" class="tab-pane" id="tablas">...</div>                            
                            <div role="tabpanel" class="tab-pane" id="medallero">...</div>                            
                        </div>

                    </div>



                </div>
            </div>
            <!-- /#wrapper -->
        </div>
        <%@include file="prueba_deportiva_detalle_modal.jsp" %>
        <%@include file="tpl_scripts.jsp" %>
        <script src="js/prueba_deportiva.js"></script>        
        <script src="js/prueba_deportiva_detalle.js"></script> 
        <script src="js/grupo_resultado.js"></script>
        <script src="js/tabla_categoria.js"></script>
        <script type="text/javascript">
                                $(document).ready(function() {
                                    init_prueba_panel(<%=categoria.getId()%>, 0);
                                    $(".grupo").click(function() {
                                        $(".grupo").removeClass("selected_grupo");
                                        $(this).addClass("selected_grupo");
                                        filtrarPruebaPanel(<%=categoria.getId()%>, $(this).attr("id_grupo"));
                                        cargarTablas();
                                    });
                                    $('#prueba_deportiva_detalle_view').on('show.bs.modal', function(e) {
                                        //get data-id attribute of the clicked element                                                                                
                                        var id_prueba = $(e.relatedTarget).data('id');
                                        filtrarPruebaDetalle(id_prueba);
                                    });
                                });
                                function cargarTablas() {
                                    if ($(".selected_grupo").attr("id_grupo") == 0) {
                                        $("#tablas").html("");
                                        $(".grupo").each(function() {
                                            if ($(this).attr("id_grupo") > 0) {
                                                var tabla_pos = cargarResultadoGrupo($(this).attr("nombre_grupo"), $(this).attr("id_grupo"), <%=categoria.getTipo_puntaje()%>, $(this).attr("tipo_tabla"));
                                                $("#tablas").append(tabla_pos);
                                            }
                                        });
                                    } else {
                                        var tabla_pos = cargarResultadoGrupo($(".selected_grupo").attr("nombre_grupo"), $(".selected_grupo").attr("id_grupo"), <%=categoria.getTipo_puntaje()%>, $(".selected_grupo").attr("tipo_tabla"));
                                        $("#tablas").html(tabla_pos);
                                    }


                                }

                                function cargarResultado() {
                                    var tabla_pos = cargarTablaResultadoCategoria(<%=categoria.getId()%>);
                                    $("#medallero").html(tabla_pos);

                                }
        </script>


    </body>
</html>
