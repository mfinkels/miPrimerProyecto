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
        <!-- jquery -->

        <script type="text/javascript" src="includes/js/DataTable/jquery-1.11.3.min.js"></script>        

        <!-- bootstrap -->

        <link rel="stylesheet" href="includes/css/bootstrap.min.css">
        <!-- font-awesome -->
        <link rel="stylesheet" href="includes/css/font-awesome.min.css">
        <script src="includes/js/bootstrap.min.js"></script>
    </head>
    <body id="home">
        <div class="container" style="margin-top: 5%;">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">Login</div>
                    <div class="panel-body">
                        <!-- Login Form -->
                        <form role="form" action="index.php" method="POST">
                            <!-- Username Field -->
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label for="usuario"><span class="text-danger" style="margin-right:5px;">*</span>Usuario:</label>                            
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input id="usuario" type="text" class="form-control" name="usuario" placeholder="Ingrese su Usuario">
                                    </div>
                                    <label for="password"><span class="text-danger" style="margin-right:5px;">*</span>Password:</label>                                    
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                        <input id="password" type="password" class="form-control" name="password" placeholder="Ingrese su Password">
                                    </div>                            
                                </div>   
                            </div>   
                            <!-- Login Button -->
                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <input type="hidden" name="accion" value="verificarLogin"/>
                                    <button class="btn btn-primary" type="submit" >Ingresar</button>
                                </div>
                            </div>
                        </form>
                        <!-- End of Login Form -->
                    </div>
                </div>
            </div>    
        </div>
        <!-- end footer -->            
    </body>
</html>