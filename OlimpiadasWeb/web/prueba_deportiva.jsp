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
                            Listado de pruebas deportivas <span id="btnNuevo" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nueva</span>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-condensed" id="tblPrueba">
                                    <colgroup>
                                        <col style="width:5%"></col>                                        
                                        <col style="width:10%"></col>
                                        <col style="width:25%"></col>
                                        <col style=""></col>
                                        <col style="width:10%"></col>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Deporte</th>
                                            <th>Categor&iacute;a</th>
                                            <th>Observaciones</th>
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
    
    <script id="prueba_list" type="text/x-handlebars-template">
        {{#each records}}
          <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{deporte.nombre}}</td>
            <td class="">{{categoria.nombre}}</td>
            <td class="">{{observaciones}}</td>
            <td class="">
              <span href="" data-index="{{@index}}" class="btn btn-xs btn-circle  btn-warning  btn-edit"><span class="fa fa-edit fw"></span></span>
              <span href="" data-index="{{@index}}" class="btn btn-xs btn-danger btn-circle btn-del"><span class="fa fa-trash fw"></span></span>
              </td>
          </tr>
        {{else}}
            <tr>
            <td colspan="5"><center><strong>No se encontraron resultados</strong></center></td>
            </tr>
        {{/each}}
    </script>   
     <script id="prueba_edit" type="text/x-handlebars-template">
        <div class="row">
          <div class="col-md-12">
              <form class="form-vertical">
                  <input id="id" name="id" type="hidden" class="" value="{{id}}" >
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="id_deporte">Deporte</label>
                      <div class="col-md-8">
                      <select id="id_deporte" name="id_deporte" type="text" class="form-control input-md">
                      {{#select id_deporte}}
                      {{#each deportes}}
                          <option value={{id}} >{{nombre}}</option>
                      {{/each}}
                      {{/select}}
                      </select>                      
                   </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="id_deporte">Categor&iacute;a</label>
                      <div class="col-md-8">
                      <select id="id_categoria" name="id_categoria" type="text" class="form-control input-md">
                      {{#select id_categoria}}
                        {{#each categorias}}
                            <option value={{id}} >{{nombre}}</option>
                        {{/each}}
                      {{/select}}
                      </select>                      
                   </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="observaciones">Observaciones</label>
                      <div class="col-md-8">                      
                      <textarea id="observaciones" name="observaciones" type="text" class="form-control input-md" >{{detalle}}</textarea>
                      </div>    
                   </div>                  
                </form>
          </div>
        </div>                     
    </script>    
    <script src="js/prueba_deportiva.js"></script>
</body>

</html>
