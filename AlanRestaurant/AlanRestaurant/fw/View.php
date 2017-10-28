<?php

// fw/View.php

abstract class View {
    /*
     * La clase abstracta view permite mostrar las vistas que creemos
     * en el directorio html. Para ello obtiene el nombre de la clase que lo esta 
     * invocando (get_class($this)) para generar la ruta a cargar.
     * Ej. getClass(ListaCategoria)
     */

    public function render() {
        include 'html/' . get_class($this) . '.php';
    }

    public function renderInclude() {
        $vista=get_class($this);        
        include 'html/Menu.php';        
    }

    public function renderAndFinish() {
        $this->render();
        exit;
    }

    
}
