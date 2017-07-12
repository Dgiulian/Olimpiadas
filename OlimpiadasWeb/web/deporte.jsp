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
                            Listado de Deportes <span id="btnNuevo" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nuevo</span>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-condensed" id="tblDeporte">
                                    <colgroup>
                                        <col style="width:5%"></col>
                                        <col style=""></col>
                                        <col style="width:10%"></col>
                                        <col style="width:10%"></col>
                                        <col style="width:10%"></col>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Nombre</th>
                                            <th>Tipo</th>
                                            <th>Cantidad</th>
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
    <script id="deporte_list" type="text/x-handlebars-template">
        {{#each records}}
          <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{nombre}}</td>
            <td class="">{{tipoDeporte tipo}}</td>
            <td class="">{{cantidad_jugadores}}</td>
            <td class="">
              <span data-index="{{@index}}" class="btn btn-xs btn-circle  btn-warning btn-edit"><span class="fa fa-edit fw"></span></span>
              <span data-index="{{@index}}" class="btn btn-xs btn-danger btn-circle btn-del"><span class="fa fa-trash fw"></span></span>
            </td>
          </tr>
        {{else}}
            <tr>
            <td colspan="4"><center><strong>No se encontraron resultados</strong></center></td>
            </tr>
        {{/each}}
        
    </script>   
     <script id="deporte_edit" type="text/x-handlebars-template">
        <div class="row">
          <div class="col-md-12">
              <form class="form-vertical">
                  <input id="id" name="id" type="hidden" class="" value="{{id}}" >
                  <div class="form-group">
                      <label class="col-md-4 control-label" for="nombre">Nombre</label>
                      <div class="col-md-8">
                      <input id="nombre" name="nombre" type="text" class="form-control input-md" value="{{nombre}}">
                   </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="tipo">Tipo</label>
                      <div class="col-md-8">
                      <select id="tipo" name="tipo" type="text" class="form-control input-md">
                      {{#select tipo}}
                        <option value="0">Seleccione el tipo de deporte</option>
                        <option value="1">Individual</option>
                        <option value="2">Colectivo</option>
                      {{/select}}
                      </select>
                   </div>
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="cantidad_jugadores">Cantidad Jugadores</label>
                      <div class="col-md-8">
                      <input id="cantidad_jugadores" name="cantidad_jugadores" type="text" class="form-control input-md" value="{{cantidad_jugadores}}">
                   </div>           
                </form>
          </div>
        </div>                     
    </script>    
    <script src="js/deporte.js"></script>
</body>

</html>
