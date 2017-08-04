<?php

// fw/Model.php

abstract class Controller {

    //Atributos    
    protected $accion;
    protected $parametroPost;
    protected $parametroGet;

    //Getters y Setters
    function getAccion() {
        return $this->accion;
    }

    function setAccion($accion) {
        $this->accion = $accion;
    }

    function getParametroPost() {
        return $this->parametroPost;
    }

    function setParametroPost($parametroPost) {
        $this->parametroPost = $parametroPost;
    }

    function getParametroGet() {
        return $this->parametroGet;
    }

    function setParametroGet($parametroGet) {
        $this->parametroGet = $parametroGet;
    }

    //Constructor
    public function __construct($accion) {
        $this->accion = $accion;
        $this->parametroPost = array();
    }

    //Métodos
    protected function getClass() {
        return substr(get_class($this), 0, strpos(get_class($this), 'Controller'));
    }

    protected function formularioAlta() {
        $clase = $this->getClass();
        $form = 'FormAlta' . $clase;
        $v = new $form();
        $v->renderInclude();
    }

    protected function formularioEdicion($id) {
        $clase = $this->getClass();
        $form = 'FormEdicion' . $clase;
        $e = new $clase();
        $entidad = $e->findById($id);
        if (isset($entidad->mensaje)) {
            $v = new Error();
            $v->mensaje = $todos->mensaje;
            $v->renderInclude();
        } else {
            $v = new $form();
            $v->entidad = $entidad;
            $v->renderInclude();
        }
    }

    protected function formularioLista() {
        $clase = $this->getClass();
        $e = new $clase();
        //Metodo de la entidad a la que se esta llamando        
        $todos = $e->getTodos();
        if (isset($todos->mensaje)) {
            $v = new Error();
            $v->mensaje = $todos->mensaje;
            $v->renderInclude();
        } else {
            $lista = "Lista" . $clase;
            $v = new $lista();
            $v->lista = $todos;
            $v->renderInclude();
        }
    }

}
