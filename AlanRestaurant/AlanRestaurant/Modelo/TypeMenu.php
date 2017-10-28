<?php

// Modelo/TypeMenu.php

class TypeMenu extends Model {

//clase cliente que HEREDA metodos y variables miembros de clase MODEL ,
//a menos que en esta clase se redefinan ciertos metodos

    function __construct() {
        parent::__construct();
        $this->path = 'typeMenu';
    }

    public function update($usuario) {
        $getdata = http_build_query(
                array(
                    'name' => $usuario['name'],
                    'lastName' => $usuario['lastName']
                )
        );
        $opts = array('http' =>
            array(
                'method' => 'PUT',
                'content' => $getdata
            )
        );

        $context = stream_context_create($opts);
        try {
            $respuesta = file_get_contents($this->getUrl() . '/api/typeMenu/' . $id, false, $context);
            var_dump($respuesta);
            return $respuesta;
        } catch (ErrorException $e) {
            $error = new stdClass();
            $error->mensaje = $e->getMessage();
            return $error;
        }
    }

    public function isValid($id_cliente) {
        //validacion de id_cliente si esta en el sistema, o no
        //funcion miembro de clase cliente, de acceso publico isValid($id)
        //if(!ctype_digit($id_cliente)) die("error1");
        //if($id_cliente==0) return false;
        //$this->db->query("SELECT id_cliente
        //					from cliente
        //					where id_cliente=$id_cliente
        //					limit 1");
        //if($this->db->numRows() != 1) return false;
        //else return true;
        //si de instancia DATABASE - > db - > numero de filas es distinto de 1 retorno falso, 
        //o sea consulta con un error, transaccion incorrecta,  SINO transaccion CORRECTA
        return 1;
    }

    public function newCategoria() {//Poner parametros correspondientes al metodo de alta($nombre, $apellido, $tipo, $nro_documento, $domicilio, $partido, $estado_civil, $observaciones, $clase_cliente, $contacto_reservacion) {
        //Ver aca si hay que hacer algun tipo de tratamiento 
        //a los datos antes de enviarlos a la API
        //valido $id_cliente
        //if(!ctype_digit($id_cliente)) die("error1");
        //if($id_cliente<1) die('error 3');//checkeo el rango   < ------ INNECESARIO
        //valido $nombre
        if (strlen($nombre < 1))
            return false;
        //tamaño minimo
        $nombre = substr($nombre, 0, 50);
        //recorte del string $nombre que lo guardo en nombre
        $nombre = $this->db->escape($nombre);
        //escapo comillas
        $nombre = str_replace("%", "\%", $nombre);
        $nombre = str_replace("_", "\_", $nombre);
        //escapo comodines
        //valido $apellido
        if (strlen($apellido < 1))
            return false;
        $apellido = substr($apellido, 0, 50);
        $apellido = $this->db->escape($apellido);
        $apellido = str_replace("%", "\%", $apellido);
        $apellido = str_replace("_", "\_", $apellido);

        //valido $tipo
        if (strlen($tipo < 1))
            return false;
        $tipo = substr($tipo, 0, 50);
        $tipo = $this->db->escape($tipo);
        $tipo = str_replace("%", "\%", $tipo);
        $tipo = str_replace("_", "\_", $tipo);

        //valido $nro_documento
        if (!ctype_digit($nro_documento))
            die("error1");
        if ($nro_documento < 1)
            die('error 3'); //checkeo el rango












            
//valido $domicilio
        if (strlen($domicilio < 1))
            return false;
        $domicilio = substr($domicilio, 0, 50);
        $domicilio = $this->db->escape($domicilio);
        $domicilio = str_replace("%", "\%", $domicilio);
        $domicilio = str_replace("_", "\_", $domicilio);

        //valido $partido
        if (strlen($partido < 1))
            return false;
        $partido = substr($partido, 0, 50);
        $partido = $this->db->escape($partido);
        $partido = str_replace("%", "\%", $partido);
        $partido = str_replace("_", "\_", $partido);


        //valido $estado_civil
        if (strlen($estado_civil < 1))
            return false;
        $estado_civil = substr($estado_civil, 0, 50);
        $estado_civil = $this->db->escape($estado_civil);
        $estado_civil = str_replace("%", "\%", $estado_civil);
        $estado_civil = str_replace("_", "\_", $estado_civil);


        //valido $observaciones
        //if (strlen($observaciones<1) )return false;  puede ser nulo el campo
        $observaciones = substr($observaciones, 0, 50);
        $observaciones = $this->db->escape($observaciones);
        $observaciones = str_replace("%", "\%", $observaciones);
        $observaciones = str_replace("_", "\_", $observaciones);

        //valido $clase_cliente
        //if (strlen($clase_cliente<1) )return false;  puede ser nulo el campo
        $clase_cliente = substr($clase_cliente, 0, 50);
        $clase_cliente = $this->db->escape($clase_cliente);
        $clase_cliente = str_replace("%", "\%", $clase_cliente);
        $clase_cliente = str_replace("_", "\_", $clase_cliente);

        //valido $contacto_reservacion
        //if (strlen($contacto_reservacion<1) )return false;  puede ser nulo el campo
        $contacto_reservacion = substr($contacto_reservacion, 0, 50);
        $contacto_reservacion = $this->db->escape($contacto_reservacion);
        $contacto_reservacion = str_replace("%", "\%", $contacto_reservacion);
        $contacto_reservacion = str_replace("_", "\_", $contacto_reservacion);



        if (!(new cliente)->isValid($id_cliente))
            die("error2");
        // si (  no fue correcto !   la invocacion de creacion de un registro de cliente -- > y luego 
        // llamando a la funcion estaEnElSistema?($id_cliente)  caigo el sistema con mensaje 'error2'   ) BIEN

        $this->db->query("INSERT into cliente
						(, nombre,apellido, tipo, nro_documento,domicilio, 
	partido,estado_civil, observaciones,clase_cliente, contacto_reservacion) values 
						('$nombre', $cargoid     , '$nombre', '$apellido', '$tipo', $nro_documento,'$domicilio', 
	'$partido','$estado_civil', '$observaciones','$clase_cliente', '$contacto_reservacion') ");
    }

}
