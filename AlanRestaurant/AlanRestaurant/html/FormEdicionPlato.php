<div class="row">
    <div class="col-xs-2">
        <h3 >        
            <strong>Edici&oacute;n de Plato</strong>
        </h3>
    </div>
    <div class="col-xs-4 col-xs-offset-6 col-md-2 col-md-offset-8">
        <a href="index.php?accion=listaPlato" class='btn btn-default'>Volver</a>
    </div>
</div>

<form action='index.php?accion=updateCategoria' method="POST">
    <inout type='hidden' id='userId' value='<?php echo $this->entidad->idPlato ?>'>
    <div class="form-group">
    <label for="categoria">Categor&iacute;a:</label>
    <input type="text" class="form-control" id="nombre" placeholder='Ingrese la categor&iacute;a' value='<?php echo $this->entidad->name ?>'>
  </div>
  <button type="submit" class="btn btn-success">Editar</button>
</form>
