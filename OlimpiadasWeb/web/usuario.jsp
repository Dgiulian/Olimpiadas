<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
     <%@include  file="tpl_head.jsp" %>
</head>

<body>

    <div id="wrapper">

        <%@include file="tpl_navbar.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Usuarios <a href="<%= PathCfg.USUARIO_EDIT%>" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nuevo</a></h1>
                    
                </div>

            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Listado de Usuarios
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="tblUsuario">
                                    <thead>
                                        <tr>
                                            <th>Username</th>                                            
                                            <th>Tipo</th>
                                            <th>Fecha Alta</th>
                                            <th>Activo</th>
                                            <th></th>                                            
                                        </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
<!--                            <div class="well">
                                <h4>DataTables Usage Information</h4>
                                <p>DataTables is a very flexible, advanced tables plugin for jQuery. In SB Admin, we are using a specialized version of DataTables built for Bootstrap 3. We have also customized the table headings to use Font Awesome icons in place of images. For complete documentation on DataTables, visit their website at <a target="_blank" href="https://datatables.net/">https://datatables.net/</a>.</p>
                                <a class="btn btn-default btn-lg btn-block" target="_blank" href="https://datatables.net/">View DataTables Documentation</a>
                            </div>-->
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
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
    
    <script src="bower_components/datatables-responsive/js/dataTables.responsive.js"></script>
    
    <script src="js/bootbox.min.js"></script>        
    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    <script src="js/moment-with-locales.min.js"></script>
    <script src="js/common.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        loadData({});

    });
    function loadData(data){
         var $tabla = $('#tblUsuario');
        $.ajax({
               url: '<%= PathCfg.USUARIO_LIST %>',
               data: data,
               method:"POST",
               dataType: "json",
                beforeSend:function(){
                    var cant_cols = $tabla.find('thead th').length;
                    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='images/ajax-loader.gif'/></center></td></tr>");
               },
               success: function(data) {
                   $tabla.find('tbody').html("");
                   if(data.Result === "OK") {
                       $tabla.find('tbody').html(createTable(data.Records));
                       $('.btn-del').click(borrarUsuario);
                       $tabla.DataTable({
                            responsive: true,
                            retrieve: true,
                            paging: false,
                            ordering: true,
                            searching: false,
                            lengthChange: false,
                            bInfo: false,
                            language: {
                                url:'bower_components/datatables-plugins/i18n/Spanish.json',
                            }
                        });
                   }
               }
           });
    }
    function borrarUsuario(){
        var id = $(this).data('index');
        var $tr = $(this).parent().parent();
        deleteData('<%= PathCfg.USUARIO_DEL %>',{id:id},function(result) {
                if(result.Result === "OK") {
                    $tr.remove();
                } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    function createTable(data){
        var html = "";
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           var d = data[i];           
            html += wrapTag('td',d.usu_mail,'');
            html += wrapTag('td',d.tipo_usuario,'');
            html += wrapTag('td',convertirFecha(d.usu_fcreacion),'');
            var activo = d.usu_activo?"Si":"No";
            
            html += wrapTag('td',activo,'');
            var htmlEdit = "<a href='<%= PathCfg.USUARIO_EDIT%>?id="+ d.id +"' class='btn btn-xs btn-circle  btn-warning'><span class='fa fa-edit fw'></span></a> ";
            var htmlDel = "<span href='' data-index='"+ d.id + "' class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash fw'></span></span> ";
            
            html +=wrapTag('td',htmlEdit + htmlDel,'');
           html +="</tr>";
       }
       return html;
    }
   
        </script>
    </script>
<%@include file="tpl_footer.jsp"%>
</body>

</html>
