<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="utils.PathCfg"%>
<%
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include  file="tpl_head.jsp" %>
</head>

<body>

    <div id="wrapper">

        <%@include file="tpl_navbar.jsp" %>

        <div id="page-wrapper">
            <div class="row" id="parametro">
                <div class="col-lg-12">
                    <h1 class="page-header">Par&aacute;metro <span id="btnNuevo" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nuevo</span></h1>
                </div>
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Listado de Parametros
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-condensed" id="tblParametro">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>N&uacute;mero</th>
                                            <th>C&oacute;digo</th>
                                            <th>Nombre</th>
                                            <th>Valor</th>
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
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <%@include file="tpl_scripts.jsp" %>
   
    <script id="parametro_list" type="text/x-handlebars-template">
        {{#each records}}
          <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{numero}}</td>
            <td class="">{{codigo}}</td>
            <td class="">{{nombre}}</td>
            <td class="">{{valor}}</td>
            <td class="">
              <span href="" data-index="{{id}}" data-numero={{numero}} data-codigo="{{codigo}} " data-nombre="{{nombre}}" data-valor="{{valor}}" class="btn btn-xs btn-circle  btn-warning  btn-edit"><span class="fa fa-edit fw"></span></span>
              <span href="" data-index="{{id}}" class="btn btn-xs btn-danger btn-circle btn-del"><span class="fa fa-trash fw"></span></span>
              </td>
          </tr>
          {{else}}
            <tr>
                <td colspan="5"><center><strong>No se encontraron resultados</strong></center></td>
            </tr>
        {{/each}}    
    </script>   
     <script id="parametro_edit" type="text/x-handlebars-template">
        <div class="row">
          <div class="col-md-12">
              <form class="form-vertical">
                  <input id="id" name="id" type="hidden" class="" value="{{id}}" >
                  <div class="form-group">
                      <label class="col-md-4 control-label" for="numero">N&uacute;mero:</label>
                      <div class="col-md-8">
                      <input id="numero" name="numero" type="text" class="form-control input-md" value="{{numero}}">
                   </div>

                   <div class="form-group">
                      <label class="col-md-4 control-label" for="codigo">Codigo:</label>
                      <div class="col-md-8">
                      <input id="codigo" name="codigo" type="text" class="form-control input-md" value="{{codigo}}">
                   </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="nombre">Nombre:</label>
                      <div class="col-md-8">
                      <input id="nombre" name="nombre" type="text" class="form-control input-md" value="{{nombre}}">
                   </div>
                  <div class="form-group">
                      <label class="col-md-4 control-label" for="valor">Valor: </label>
                     <div class="col-md-8">
                      <input id="valor" name="valor" type="text" class="form-control input-md" value="{{valor}}">
                      </div>
                  </div>
        <!--          <div class="form-group">
                      <label class="col-md-4 control-label" for="activo">Activo</label>
                      <div class="col-md-8">
                      <input id="activo" name="activo" type="checkbox" class="checkbox input-md" ' + checked + ' value="{{activo}}">
                      </div>
        -->
                    </div>
                </form>
          </div>
        </div>                     
    </script>    
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
            
<script src="js/parametro.js" >

</script>
<%@include file="tpl_footer.jsp"%>
</body>

</html>
