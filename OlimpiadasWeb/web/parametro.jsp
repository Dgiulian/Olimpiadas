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

    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
<!--    <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>-->
    <script src="js/bootbox.min.js"></script>
    <script src="vendor/handlebars/handlebars-v4.0.10.js"></script>    
    
    <!-- Custom Theme JavaScript -->
    <script src="js/sb-admin-2.js"></script>
    <script src="js/moment-with-locales.min.js"></script>
    <script src="js/common.js"></script>
    
   
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
    <script>
    $(document).ready(function() {
        loadData({});
        $('#btnNuevo').click(function(){
            agregarParametro({id:0,numero:'',codigo:'',nombre:'',valor:'',activo:1});
        });

    });
    function loadData(data){
        var $tabla = $('#tblParametro');
        $.ajax({
            url: PathCfg.PARAMETRO_LIST,
            data: data,
            method:"POST",
            dataType: "json",
            beforeSend:function(){
                 var cant_cols = $tabla.find('thead th').length;
                 $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='images/ajax-loader.gif'/></center></td></tr>");
            },
            success: function(result) {
                if(result.Result === "OK") {
                    $tabla.find('tbody').html(createTable(result.Records));
                    $('.btn-del').click(borrarParametro);
                    $('.btn-edit').click(editarParametro);
                }
            }
        });
    }
    function borrarParametro(){
        var id = $(this).data('index');
        var $tr = $(this).parent().parent();
        deleteData(PathCfg.PARAMETRO_DEL,{id:id},function(result) {
                if(result.Result === "OK") {
                    $tr.remove();
                } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    function createTable(data){   
        var template = Handlebars.compile($("#parametro_list").html());
        return template({records:data});       
    }
    function editarParametro(){
        var numero = $(this).data('numero');
        var codigo = $(this).data('codigo');
        var nombre = $(this).data('nombre');
        var valor  = $(this).data('valor');
        var index  = $(this).data('index');
        var activo = $(this).data('activo');
        agregarParametro({numero:numero,codigo:codigo,nombre:nombre,id:index,valor:valor,activo:activo});
    }
    function agregarParametro(data){
        data.checked = (data.activo)?"checked":"";
        var template = Handlebars.compile($('#parametro_edit').html());
        bootbox.dialog({
            title: "Configuraci&oacute;n de par&aacute;metro",
            message: template(data), 
            buttons: {
                success: {
                    label: "Guardar",
                    className: "btn-success",
                    callback: function () {
                        var data = recuperarCampos();
                        $.ajax({
                            url:PathCfg.PARAMETRO_EDIT,
                            data: data,
                            method:'POST',
                            dataType:'json',
                            success:function(){
                                loadData();
                            }
                        });
                        //bootbox.alert("Nombre " + nombre + ". Email: <b>" + email + "</b>");
                    }
                },
                cancel: {
                    label: "Cancelar",
                    callback: function () {
                    }
                }
            }
        });
    }
    function recuperarCampos(){
        var data = {};
        data.id     = $('#id').val();
        data.numero = $('#numero').val();
        data.codigo = $('#codigo').val();
        data.nombre = $('#nombre').val();
        data.valor  = $('#valor').val();
        data.activo = $('#activo').prop('checked')?'1':'';
        data.activo = 1;
        return data;
    }
</script>
<%@include file="tpl_footer.jsp"%>
</body>

</html>
