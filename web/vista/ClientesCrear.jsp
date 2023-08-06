<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.dlbs.modelo.*"%>
<%@page import="com.dlbs.dao.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="">
        <meta name="author" content="">
        <meta name="robots" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="DLBS: DLBSSaas Admin  Bootstrap 5 Template">
        <meta property="og:title" content="DLBS: DLBSSaas Admin  Bootstrap 5 Template">
        <meta property="og:description" content="DLBS: DLBSSaas Admin  Bootstrap 5 Template">
        <meta property="og:image" content="https://fillow.dexignlab.com/xhtml/social-image.png">
        <meta name="format-detection" content="telephone=no">

        <!-- PAGE TITLE HERE -->
        <title>Admin Dashboard</title>

        <!-- FAVICONS ICON -->
        <link rel="shortcut icon" type="image/png" href="images/favicon.png">
        <link href="vendor/jquery-nice-select/css/nice-select.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <!-- Datatable -->
        <link href="vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <body>

        <!--*******************
            Preloader start
        ********************-->
        <div id="preloader">
            <div class="lds-ripple">
                <div></div>
                <div></div>
            </div>
        </div>
        <!--*******************
            Preloader end
        ********************-->

        <!--**********************************
            Main wrapper start
        ***********************************-->
        <div id="main-wrapper">

            <!--**********************************
                Nav header start
            ***********************************-->
            <div class="nav-header">
                <a href="index.html" class="brand-logo">
                    <svg class="logo-abbr" width="55" height="55" viewbox="0 0 55 55" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M27.5 0C12.3122 0 0 12.3122 0 27.5C0 42.6878 12.3122 55 27.5 55C42.6878 55 55 42.6878 55 27.5C55 12.3122 42.6878 0 27.5 0ZM28.0092 46H19L19.0001 34.9784L19 27.5803V24.4779C19 14.3752 24.0922 10 35.3733 10V17.5571C29.8894 17.5571 28.0092 19.4663 28.0092 24.4779V27.5803H36V34.9784H28.0092V46Z" fill="url(#paint0_linear)"></path>
                    <defs>
                    </defs>
                    </svg>
                    <div class="brand-title">
                        <h2 class="">DLBS</h2>
                    </div>
                </a>
                <div class="nav-control">
                    <div class="hamburger">
                        <span class="line"></span><span class="line"></span><span class="line"></span>
                    </div>
                </div>
            </div>
            <!--**********************************
                Nav header end
            ***********************************-->

            <!--**********************************
        Header start
    ***********************************-->
            <div class="header">
                <div class="header-content">
                    <nav class="navbar navbar-expand">
                        <div class="collapse navbar-collapse justify-content-between">
                            <div class="header-left">
                                <div class="dashboard_bar">
                                    Clientes
                                </div>

                            </div>
                            <ul class="navbar-nav header-right">
                                <li class="nav-item dropdown">
                                    <a class="nav-link" href="javascript:void(0);" role="button" data-bs-toggle="dropdown">
                                        ${usuUsuario}
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-end">
                                        <a href="CerrarSesion" class="dropdown-item ai-icon">
                                            <svg id="icon-logout" xmlns="http://www.w3.org/2000/svg" class="text-danger" width="18" height="18" viewbox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path><polyline points="16 17 21 12 16 7"></polyline><line x1="21" y1="12" x2="9" y2="12"></line></svg>
                                            <span class="ms-2">Cerrar Sesion</span>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>

            <!--**********************************
                Header end ti-comment-alt
            ***********************************-->

            <!--**********************************
                Sidebar start
            ***********************************-->
            <div class="dlabnav">
                <div class="dlabnav-scroll">
                    <ul class="metismenu" id="menu">
                        <li>
                            <a href="Controlador?p=venta" >
                                <i class="fas fa-home"></i>
                                <span class="nav-text">Venta</span>
                            </a>
                        </li>
                        <li>
                            <a href="Controlador?p=menu" >
                                <i class="fas fa-home"></i>
                                <span class="nav-text">Menu</span>
                            </a>
                        </li>
                        <li>
                            <a href="Controlador?p=clientes" >
                                <i class="fas fa-home"></i>
                                <span class="nav-text">Clientes</span>
                            </a>
                        </li>
                        <li>
                            <a  href="Controlador?p=usuarios" >
                                <i class="fas fa-home"></i>
                                <span class="nav-text">Usuarios</span>
                            </a>

                        </li>
                    </ul>
                </div>
            </div>
            <!--**********************************
                Sidebar end
            ***********************************-->

            <!--**********************************
        Content body start
    ***********************************-->
            <div class="content-body">
                <!-- row -->
                <div class="container-fluid">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header mb-3">
                                <h3 class="modal-title">CREAR CLIENTE</h3>                                   
                            </div>
                            <div class="card-body">
                                <form method="post" action="Clientes">

                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-xl-6 mb-3">
                                                <div class="example">
                                                    <p class="mb-1 text-primary">Nombres:</p>
                                                    <input class="form-control" type="text" required="true" name="cliNombre">
                                                </div>
                                            </div>
                                            <div class="col-xl-6 mb-3">
                                                <div class="example">
                                                    <p class="mb-1 text-primary">Apellidos:</p>
                                                    <input type="text" class="form-control" required="true" name="cliApellido">
                                                </div>
                                            </div>                                            
                                        </div>                                                                     
                                        <div class="row">                            
                                            <div class="col-xl-6 mb-3">
                                                <div class="example">
                                                    <p class="mb-1 text-primary">Cedula:</p>
                                                    <input type="number"  class="form-control" maxlength="10"  required="true" name="cliCedula" >
                                                </div>
                                            </div>      
                                            <div class="col-xl-6 mb-3">
                                                <div class="example">
                                                    <p class="mb-1 text-primary">Email:</p>
                                                    <input class="form-control" type="email" required="true" name="cliEmail" >
                                                </div>
                                            </div>
                                        </div>                                                                     
                                        <div class="row">                            
                                            <div class="col-xl-6 mb-3">
                                                <div class="example">
                                                    <p class="mb-1 text-primary">Telefono:</p>
                                                    <input type="tel" maxlength="11" class="form-control" required="true" name="cliTelefono">
                                                </div>
                                            </div>                                            
                                        </div>                                                                                                                                                            
                                    </div>
                                    <div class="modal-footer p-3 gap-4">
                                        <button type="button" class="btn btn-danger light" data-bs-dismiss="modal">Cancelar</button>
                                        <input class="btn btn-primary ml-2" type="submit" name="accion" value="guardar">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--**********************************
                Content body end
            ***********************************-->


            <!--**********************************
                Footer start
            ***********************************-->
            <div class="footer">
                <div class="copyright">
                    <p>Copyright © Designed &amp; Developed by <a href="../index.htm" target="_blank">DexignLab</a> 2021</p>
                </div>
            </div>
            <!--**********************************
                Footer end
            ***********************************-->

            <!--**********************************
       Support ticket button start
    ***********************************-->

            <!--**********************************
               Support ticket button end
            ***********************************-->



        </div>
        <!--**********************************
            Main wrapper end
        ***********************************-->

        <!--**********************************
            Scripts
        ***********************************-->
        <!-- Required vendors -->
        <script src="vendor/global/global.min.js"></script>
        <script src="vendor/jquery-nice-select/js/jquery.nice-select.min.js"></script>
        <script src="js/custom.min.js"></script>
        <script src="js/dlabnav-init.js"></script>
        <script src="js/demo.js"></script>
        <script src="js/styleSwitcher.js"></script>
        <!-- Datatable -->
        <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
        <script src="js/plugins-init/datatables.init.js"></script>
    </body>
</html>