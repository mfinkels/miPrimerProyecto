X<?php
 
// controllers/index.php

require_once 'fw/fw.php';
require_once 'views/Menu.php';
require_once 'views/Login.php';
require_once 'views/Error.php';
require_once 'Controlador/CategoriaController.php';
require_once 'Controlador/UserController.php';
require_once 'Controlador/PlatoController.php';
require_once 'Controlador/RestaurantController.php';
require_once 'Controlador/TypeMenuController.php';
require_once 'Modelo/Categoria.php';
require_once 'Modelo/User.php';
require_once 'Modelo/Plato.php';
require_once 'Modelo/Restaurant.php';
require_once 'Modelo/TypeMenu.php';


$accion = 'login';

if (isset($_GET['accion']) && !empty($_GET['accion'])) {
    $accion = $_GET['accion'];
}
if (isset($_POST['accion']) && !empty($_POST['accion'])) {
    $accion = $_POST['accion'];
}

/*
 * Por cada uno de las entidades en la api
 * Se debera tener un conjunto de lineas como las
 * siguientes
 */
echo 'La accion es:'.$accion;

/*
 * En el caso de no recibir por GET o por POST la 
 * accion se muestra simplemente el Menu Principal 
 */

if ($accion == 'inicio' ) {
    $v = new Menu;
    //Render es el método de la clase abstracta View
    //La utilizamos para cargar la vista en pantalla
    $v->render();
}

if (isset($_SESSION['usuario']) && !empty($_SESSION['usuario'])) {
    echo $_SESSION['usuario'];
    if (strpos($accion, 'Categoria')) {
        $controlador = new CategoriaController(getAccion($accion, 'Categoria'));
        if (isset($_POST)) {
            $controlador->setParametroPost($_POST);
        }
        if (isset($_GET)) {
            $controlador->setParametroGet($_GET);
        }
        $controlador->ejecutar();
    }
    if (strpos($accion, 'User')) {
        $controlador = new UserController(getAccion($accion, 'User'));
        if (isset($_POST)) {
            $controlador->setParametroPost($_POST);
        }
        if (isset($_GET)) {
            $controlador->setParametroGet($_GET);
        }

        $controlador->ejecutar();
    }
    if (strpos($accion, 'Plato')) {
        $controlador = new PlatoController(getAccion($accion, 'Plato'));
        if (isset($_POST)) {
            $controlador->setParametroPost($_POST);
        }
        if (isset($_GET)) {
            $controlador->setParametroGet($_GET);
        }

        $controlador->ejecutar();
    }
    if (strpos($accion, 'Restaurant')) {
        $controlador = new RestaurantController(getAccion($accion, 'Restaurant'));
        if (isset($_POST)) {
            $controlador->setParametroPost($_POST);
        }
        if (isset($_GET)) {
            $controlador->setParametroGet($_GET);
        }

        $controlador->ejecutar();
    }
    if (strpos($accion, 'TypeMenu')) {
        $controlador = new TypeMenuController(getAccion($accion, 'TypeMenu'));
        if (isset($_POST)) {
            $controlador->setParametroPost($_POST);
        }
        if (isset($_GET)) {
            $controlador->setParametroGet($_GET);
        }

        $controlador->ejecutar();
    }
} else {
    if ($_SERVER['REQUEST_URI'] != '/AlanRestaurant/index.php') {
        header('Location: localhost:8080/AlanRestaurant/index.php');
    }        
    $controlador = new UserController($accion);
    if (isset($_POST)) {
        $controlador->setParametroPost($_POST);
    }
    $controlador->ejecutar();
}

function getAccion($accion, $clase) {
    return substr($accion, 0, strpos($accion, $clase));
}
