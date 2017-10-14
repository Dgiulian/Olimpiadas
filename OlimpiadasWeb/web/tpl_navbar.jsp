<%-- 
    Document   : tpl_navbar.jsp
    Created on : Jul 5, 2017, 6:28:06 PM
    Author     : Diego
--%>
<%@page import="utils.PathCfg"%>
<%
    String email = (String) session.getAttribute("email");
    if (email == null) {
        email = "";
    }
%>

<%@page import="utils.PathCfg"%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="" href="#"><img class="img-responsive" style="paddin-top:0px; height: 50px" src="images/Olimpiadas-kinesicas.png"/></a>
    </div>

    <div id="navbar" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li>
                <a href="#" data-toggle="dropdown" class='dropdown-toggle'>
                    <span>OKN 2017</span>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">   
                    <li><a href="index.jsp"><i class="fa fa-dashboard fa-fw"></i>Panel Deportes</a></li>
                    <li><a href="<%=PathCfg.MEDALLERO%>"><i class="fa fa-flag fa-fw"></i> Medallero</a></li>                    
                </ul>
            </li>
            <li>
                <a href="#" data-toggle="dropdown" class='dropdown-toggle'>
                    <span>Configuraci&oacute;n</span>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">   
                    <li><a href="<%=PathCfg.CATEGORIA%>"><i class="fa fa-dashboard fa-fw"></i> Categor&iacute;a</a></li>
                    <li><a href="<%=PathCfg.DELEGACION%>"><i class="fa fa-flag fa-fw"></i> Delegaci&oacute;n</a></li>                    
                    <li><a href="<%=PathCfg.DEPORTE%>"><i class="fa fa-dribbble fa-fw"></i> Deportes</a></li>
                    <li><a href="<%=PathCfg.SEDE%>"><i class="fa fa-home fa-fw"></i> Sedes</a></li> 
                </ul>
            </li>
            <li>
                <a href="#" data-toggle="dropdown" class='dropdown-toggle'>
                    <span>Gestión</span>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">   
                    <li><a href="<%=PathCfg.USUARIO%>"><i class="fa fa-user fa-fw"></i> Usuarios</a></li>                        
                    <li><a href="<%=PathCfg.USUARIO_APP%>"><i class="fa fa-mobile-phone fa-fw"></i> Usuarios App</a></li>
                </ul>
            </li>           


            <li><a href="<%=PathCfg.EQUIPO%>"><i class="fa fa-dashboard fa-fw"></i> Equipos</a></li>
            <li><a href="<%=PathCfg.JUGADOR%>"><i class="fa fa-users fa-fw"></i> Jugadores</a></li>
            <li><a href="<%=PathCfg.PRUEBA%>"><i class="fa fa-clock-o fa-fw"></i> Pruebas</a></li>
            <li><a href="<%=PathCfg.NOVEDAD%>"><i class="fa fa-comments-o fa-fw"></i> Novedades</a></li>
            <li><a href="<%=PathCfg.PUSH%>"><i class="fa fa-phone-square fa-fw"></i> Push</a></li>


        </ul>   
        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <%= email%> <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <!--                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                                            </li>
                                            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                                            </li>
                                            <li class="divider"></li>-->
                    <li><a href="<%= PathCfg.LOGOUT%>"><i class="fa fa-sign-out fa-fw"></i> Cerrar el sistema</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->
    </div><!--/.nav-collapse -->
    <!-- /.navbar-header -->    
</nav>
