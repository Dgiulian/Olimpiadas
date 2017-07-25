<%-- 
    Document   : tpl_navbar.jsp
    Created on : Jul 5, 2017, 6:28:06 PM
    Author     : Diego
--%>
 <%@page import="utils.PathCfg"%>
<%
    String email = (String)    session.getAttribute("email");
    if(email==null) email = "";
%>

<%@page import="utils.PathCfg"%>
<!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="">
                    
                </a>
                
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <%= email %> <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
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

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">                       
                        <li><a href="<%=request.getContextPath()%>"><i class="fa  fa-fw"></i> Inicio</a></li>
<!--                        <li><a href="/"><i class="fa fa-calendar fa-fw"></i> Agenda</a></li>-->
                        <li><a href="<%=PathCfg.CATEGORIA%>"><i class="fa fa-dashboard fa-fw"></i> Categor&iacute;a</a></li>
                        <li><a href="<%=PathCfg.DELEGACION%>"><i class="fa fa-flag fa-fw"></i> Delegaci&oacute;n</a></li>
                        <li><a href="<%=PathCfg.DEPORTE%>"><i class="fa fa-dribbble fa-fw"></i> Deportes</a></li>
                        <li><a href="<%=PathCfg.EQUIPO%>"><i class="fa fa-dashboard fa-fw"></i> Equipos</a></li>
                        <li><a href="<%=PathCfg.JUGADOR%>"><i class="fa fa-users fa-fw"></i> Jugadores</a></li>
                        <li><a href="<%=PathCfg.PRUEBA%>"><i class="fa fa-clock-o fa-fw"></i> Pruebas</a></li>
                        <li><a href="<%=PathCfg.NOVEDAD%>"><i class="fa fa-comments-o fa-fw"></i> Novedades</a></li>
                        <li><a href="<%=PathCfg.SEDE%>"><i class="fa fa-home fa-fw"></i> Sedes</a></li>     
                        <li>
                            <a href="<%=PathCfg.USUARIO%>"><i class="fa fa-user fa-fw"></i> Usuarios</a>
                        </li>

<!--                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Charts<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="">Flot Charts</a>
                                </li>
                                <li>
                                    <a href="">Morris.js Charts</a>
                                </li>
                            </ul>
                             /.nav-second-level 
                        </li>
                        <li>
                            <a href="tables.html"><i class="fa fa-table fa-fw"></i> Tables</a>
                        </li>
                        <li>
                            <a href="forms.html"><i class="fa fa-edit fa-fw"></i> Forms</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-wrench fa-fw"></i> UI Elements<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="panels-wells.html">Panels and Wells</a>
                                </li>
                                <li>
                                    <a href="buttons.html">Buttons</a>
                                </li>
                                <li>
                                    <a href="notifications.html">Notifications</a>
                                </li>
                                <li>
                                    <a href="typography.html">Typography</a>
                                </li>
                                <li>
                                    <a href="icons.html"> Icons</a>
                                </li>
                                <li>
                                    <a href="grid.html">Grid</a>
                                </li>
                            </ul>
                             /.nav-second-level 
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> Multi-Level Dropdown<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                    </ul>
                                     /.nav-third-level 
                                </li>
                            </ul>
                             /.nav-second-level 
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> Sample Pages<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="blank.html">Blank Page</a>
                                </li>
                                <li>
                                    <a href="login.html">Login Page</a>
                                </li>
                            </ul>
                             /.nav-second-level 
                        </li>-->
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
