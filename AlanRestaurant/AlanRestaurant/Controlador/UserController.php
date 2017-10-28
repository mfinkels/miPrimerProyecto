<?php

// Controlador/userController.php

require_once 'fw/fw.php';
require_once 'views/FormEdicionUser.php';
//require_once 'Modelo/Categoria.php'; No hace falta ya se incluyo anteriormente
//require '../views/AltaOK.php';
require_once 'views/ListaUser.php';

class UserController extends Controller {

    public function ejecutar() {
        //echo get_class($this);        
        echo $this->accion;
        //$clase= $this->getClass();
        switch ($this->accion) {
            case 'alta':
                $this->formularioAlta();
                break;
            case 'edit':
                $this->formularioEdicion($this->parametroGet['id']);
                break;
            case 'update':
                $e = new User();
                $e->update($this->parametroPost);
                break;
            case 'new':
                $e = new User();
                //if(!isset($_POST['nombre'])) die();
                //if(!isset($_POST['cargo'])) die();
                //$metodo=''.$clase;                
                $e->new($this->parametroPost['nombre']); //Completar con todos los datos que provienen del formulario por POST
                (new AltaOK)->renderInclude();
                break;
            case 'lista':
                $this->formularioLista();
                break;
            case 'login':
                if (isset($_SESSION)) {
                    session_destroy();
                }
                $v = new Login();
                $v->render();
                break;
            case 'verificarLogin':
                if ($this->parametroPost['usuario'] == 'ADMIN' && $this->parametroPost['password'] == '1234') {
                    session_start();
                    $_SESSION['usuario'] = 'ADMIN';
                    $v = new Menu;
                    $v->render();
                } else {
                    if (isset($_SESSION)) {
                        session_destroy();
                    }
                    header('Location: localhost:8080/AlanRestaurant/index.php');
                    //echo 'No se pudo loguear'; //Esto se puede cambiar por un alert de bootstrap que queda muy lindo 
                    //header('Location: localhost:8080/AlanRestaurant/index.php');
                }
                break;

            default:
                echo 'Accion no contemplada'; //Esto se puede cambiar por un alert de bootstrap que queda muy lindo 
                // header('Location: localhost:8080/AlanRestaurant/index.php');
                break;
        }
    }

}
