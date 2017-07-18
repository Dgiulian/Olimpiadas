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
                            Listado de Equipos <span id="btnNuevo" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nuevo</span>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-condensed" id="tblEquipo">
                                    <colgroup>
                                        <col style="width:10%"></col>                                        
                                        <col style="width:55%"></col>
                                        <col style="width:15%"></col>                                        
                                        <col style="width:10%"></col>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Nombre</th>
                                            <th>Delegacion</th>
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
    <script id="equipo_list" type="text/x-handlebars-template">
        {{#each records}}
          <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{nombre}}</td>
            <td class="">{{delegacion.nombre}}</td>
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
     <script id="equipo_edit" type="text/x-handlebars-template">
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
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="observaciones">Detalle:</label>
                      <div class="col-md-8">                      
                      <textarea id="observaciones" name="observaciones" type="text" class="form-control input-md" >{{detalle}}</textarea>
                      </div>    
                   </div>                  
                </form>
          </div>
        </div>                     
    </script>    
    <script src="js/equipo.js"></script>
</body>

</html>
