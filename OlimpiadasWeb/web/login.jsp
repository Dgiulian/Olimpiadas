<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="transaccion.TParametro"%>
<%@page import="bd.Parametro"%>
<%@page import="utils.PathCfg"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<String> errores = (ArrayList) request.getAttribute("errores");
    String ref =  request.getParameter("ref");
    Parametro entorno = new TParametro().getById(PathCfg.PARAMETRO_ENTORNO);
    if (entorno==null) entorno = new Parametro();
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Olimpiadas Kinésiologicas</title>
     <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel">
                    <div class="panel-heading">
                        <img class="img-responsive" src="images/logotipo-OLI.png"/>
                        <h3 class="panel-title"><% if (entorno.getValor().equalsIgnoreCase("desarrollo"))  { %> <small><%=entorno.getValor()%></small> <% }%> </h3>
                    </div>
                    <div class="panel-body">
                        <% if (errores != null) {%>
                            <% for (String error : errores) {%>
                            <div class="alert alert-error">
                                <button data-dismiss="alert" class="close" type="button">&times;</button>
                                <strong>ERROR! </strong><%= error%>
                            </div>
                            <% }%>

                         <% }%>
                        <form action="<%= PathCfg.LOGIN %>" method="POST" role="form">
                            <% if(ref!=null) {%>
                            <input type="hidden" name="ref" id="ref" value="<%= ref%>">
                            <% } %> 
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Recordarme
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <button class="btn btn-lg btn-success btn-block">Login</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/sb-admin-2.js"></script>

</body>

</html>
