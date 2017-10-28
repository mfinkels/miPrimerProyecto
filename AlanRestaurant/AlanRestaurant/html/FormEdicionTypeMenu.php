<div class="row">
    <div class="col-xs-2">
        <h3 >        
            <strong>Edici&oacute;n de Tipo de Men&uacute</strong>
        </h3>
    </div>
    <div class="col-xs-4 col-xs-offset-6 col-md-2 col-md-offset-8">
        <a href="index.php?accion=listaTypeMenu" class='btn btn-default'>Volver</a>
    </div>
</div>

<form action='index.php?accion=updateTypeMenu' method="POST">
    <inout type='hidden' id='typeMenuId' value='<?php echo $this->entidad->idTypeMenu ?>'>
    <div class="form-group">
    <label for="nombre">Nombre:</label>
    <input type="text" class="form-control" id="nombre" placeholder='Ingrese el Nombre' value='<?php echo $this->entidad->name ?>'>
  </div>
  <button type="submit" class="btn btn-success">Editar</button>
</form>
