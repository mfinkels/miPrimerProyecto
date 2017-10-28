<?php

// fw/Model.php

abstract class Model {

    public $url;
    public $path;

    //Constructor
    public function __construct() {
        $this->url = 'http://apimorfandoort.azurewebsites.net';
    }

    function getUrl() {
        return $this->url;
    }

    function setUrl($url) {
        $this->url = $url;
    }

    function getPath() {
        return $this->path;
    }

    function setPath($path) {
        $this->path = $path;
    }

    //Métodos
    public function getTodos() {
        //Obtener todos los registros desde la API
        //Va a venir en formato json 

        set_error_handler(function($errno, $errstr, $errfile, $errline, array $errcontext) {
            // error was suppressed with the @-operator
            if (0 === error_reporting()) {
                return false;
            }
            throw new ErrorException($errstr, 0, $errno, $errfile, $errline);
        });

        try {
            $resultado = file_get_contents($this->getUrl() . '/api/' . $this->path);
            return json_decode($resultado);
        } catch (ErrorException $e) {
            $error = new stdClass();
            $error->mensaje = $e->getMessage();
            return $error;
        }
    }

    public function findById($id) {
        //Obtener un registro por Id
        try {
            $resultado = file_get_contents($this->getUrl() . '/api/user/' . $id);
            return json_decode($resultado);
        } catch (ErrorException $e) {
            $error = new stdClass();
            $error->mensaje = $e->getMessage();
            return $error;
        }
    }

}
