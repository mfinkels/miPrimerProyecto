<?php

// Controlador/userController.php

require_once 'fw/fw.php';
require_once 'views/FormEdicionPlato.php';
//require_once 'Modelo/Categoria.php'; No hace falta ya se incluyo anteriormente
//require '../views/AltaOK.php';
require_once 'views/ListaPlato.php';

class PlatoController extends Controller {

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
                $e = new Plato();
                $e->update($this->parametroPost);                
                break;            
            case 'new':                
                $e = new Plato();
                //if(!isset($_POST['nombre'])) die();
                //if(!isset($_POST['cargo'])) die();
                //$metodo=''.$clase;                
                $e->new($this->parametroPost['nombre']);//Completar con todos los datos que provienen del formulario por POST
                (new AltaOK)->renderInclude();
                break;            
            case 'lista':                     
                $this->formularioLista();
                break;
            default:
                break;
        }
    }
}
