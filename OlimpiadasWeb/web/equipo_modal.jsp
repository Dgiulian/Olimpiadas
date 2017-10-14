<%@page import="bd.Delegacion"%>
<%@page import="java.util.List"%>
<%@page import="transaccion.TDelegacion"%>
<div class="modal fade" id="equipo_view">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" class="class pull-right"><span class="glyphicon glyphicon-remove"></span></a>
                <h3 class="modal-title" id="titulo">RESULTADO PRUEBA DEPORTIVA</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form class="form-vertical">                            
                            <div class="form-group">
                                <label for="nombre">Nombre</label>            
                                <input id="id_categoria_modal" name="id_categoria_modal" type="text" class="form-control input-md hidden" value="">
                                <input id="nombre" name="nombre_equipo" type="text" class="form-control input-md" value="">
                            </div>
                            <div class="form-group">
                                <%
                                    List<Delegacion> lista_delegacion = new TDelegacion().getList();
                                %>
                                <label for="id_delegacion">Delegacion</label>            
                                <select id="id_delegacion" name="id_delegacion" type="text" class="form-control input-md">
                                    <%
                                        for (Delegacion d : lista_delegacion) {
                                    %>
                                    <option value="<%=d.getId()%>"><%=d.getNombre()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>                                            
                        </form>
                    </div>
                </div>      
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Guardar</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function() {

    });
</script>