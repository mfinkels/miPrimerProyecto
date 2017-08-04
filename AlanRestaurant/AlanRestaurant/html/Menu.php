<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--<link rel="shortcut icon" href="favicon/favicon.ico"> -->
        <!-- tipografias -->
        <!--<link rel="stylesheet" href="includes/css/stylesheet.css">-->
        <!-- bootstrap -->
        <link rel="stylesheet" href="includes/css/bootstrap.min.css">
        <!-- font-awesome -->
        <link rel="stylesheet" href="includes/css/font-awesome.min.css">
        <!-- custom -->
        <!--<link rel="stylesheet" href="includes/css/templatemo-style.css">-->
        <link href="includes/css/hover.css" rel="stylesheet" media="all">
        <link rel="stylesheet" href="includes/css/lightbox.css">
        <!-- DATA TABLE -->
        <script type="text/javascript" src="includes/js/DataTable/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="includes/js/DataTable/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="includes/js/DataTable/jquery.dataTables.css">
        <link rel="stylesheet" href="includes/css/style.css"> <!-- Estilos de Usuario -->
        <script src="includes/js/bootstrap.min.js"></script>
        <script src="includes/js/plugins.js"></script>
        <script src="includes/js/smoothscroll.js"></script>
        <script src="includes/js/lightbox.js"></script>
        <script src="includes/js/menu.js"></script>
    </head>
    <body id="home" data-spy="scroll" data-target=".navbar-collapse">
        <!-- start navigation -->
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" >Gesti&oacute;n de Restaurant</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <form>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                           
                            <li class="active">
                                <a href="index.php?accion=listaCategoria">Categor&iacute;as 
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li>
                                <a href="index.php?accion=listaUser">Usuarios</a>
                            </li>
                            <li>
                                <a href="index.php?accion=listaPlato">Platos</a>
                            </li>                            
                            <li>
                                <a href="index.php?accion=listaReserva">Reservas</a>
                            </li>
                            <li>
                                <a href="index.php?accion=listaRestaurant">Restaurant</a>
                            </li>
                            <li>
                                <a href="index.php?accion=listaTypeMenu">Tipo de Men&uacute;</a>
                            </li>
                        </ul>                    
                    </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>    
        <div class="wrapper">            
            <?php
            //Incluyo la vista correspondiente
            if (isset($vista) && !empty($vista)) {
                include($vista . '.php');
            }
            ?>
            <!--<p>El contenido de nuestra web va aca</p>-->
            <div class="push"></div>
        </div>
        <!-- start footer -->
        <div class="footer container">
                    </div>

        <!-- end footer -->            
    </body>
</html>
<script>
    $(document).ready(function () {
        table = $('.dataTable').DataTable({
            "scrollY": "400px",
            "scrollX": true,
            "scrollCollapse": true,
            "paging": false,
            "info": false,
            responsive: true,
            "filter": true,
            "order": [[0, "asc"]],
            "oLanguage": {
                "sSearch": "Buscar:"
            }
        });
    });
</script>

</script>