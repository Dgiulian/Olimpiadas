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
                <div class="row">
                    <div class="col-lg-12">
                        <h3 class="page-header">Listado de Novedades</h3>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="row">
                    <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading text-right">
                             <span id="btnNuevo" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nueva</span>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-condensed" id="tblNovedad">
                                    <colgroup>
                                        <col style="width:5%"></col>                                        
                                        <col style="width:5%"></col>
                                        <col style=""></col>
                                        <col style=""></col>
                                        <col class="acciones"></col>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Fecha</th>
                                            <th>T&iacute;tulo</th>
                                            <th>Subtitulo</th>                                            
                                            <th>Imagen</th>                                            
                                            <th class="acciones">Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
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
        <script id="novedad_list" type="text/x-handlebars-template">
            {{#each records}}
            <tr class="">
            <td class="">{{id}}</td>
            <td class="">{{convertirFecha  fecha}}</td>
            <td class="">{{titulo}}</td>
            <td class="">{{subtitulo}}</td>
            <td>
                {{#if imagen}}
                    <a href="Download?type=novedad&id={{id}}">{{imagen}}</a>
                {{/if}}  
            </td>
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
     <script id="novedad_edit" type="text/x-handlebars-template">
        <div class="row">
          <div class="col-md-12">
              <form class="form-vertical" enctype="multipart/form-data">
                  <input id="id" name="id" type="hidden" class="" value="{{id}}" >
                   <div class="form-group">
                      <label class="control-label" for="fecha">Fecha</label>                      
                        <div class="controls">
                            <div class="input-group date date-picker">
                              <input type="text" id="fecha" name="fecha" class="form-control date-input "  value="{{convertirFecha fecha}}">
                              <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                            </div>
                        </div>                      
                   </div>
                    
                  <div class="form-group">
                      <label class="control-label" for="titulo">T&iacute;tulo</label>                      
                      <input id="titulo" name="titulo" type="text" class="form-control input-md" value="{{titulo}}">
                   </div>
                   <div class="form-group">
                      <label class="control-label" for="subtitulo">Subt&iacute;tulo</label>                      
                      <input id="subtitulo" name="subtitulo" type="text" class="form-control input-md" value="{{subtitulo}}">
                   </div>
                   <div class="form-group">
                      <label class="control-label" for="no">Imagen</label>                      
                      <input id="imagen" name="imagen" type="file" class="form-control input-md" value="">
                   </div>                   
                   <div class="form-group">
                      <label class="control-label" for="detalle">Detalle:</label>                      
                      <textarea id="detalle" name="detalle" type="text" class="form-control input-md" >{{detalle}}</textarea>                         
                   </div>                  
                </form>
          </div>
        </div>                     
    </script>    
    <script src="js/novedad.js"></script>
</body>

</html>
