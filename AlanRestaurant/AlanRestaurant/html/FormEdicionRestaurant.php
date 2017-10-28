<div class="row">
    <div class="col-xs-2">
        <h3 >        
            <strong>Edici&oacute;n de Restaurant</strong>
        </h3>
    </div>
    <div class="col-xs-4 col-xs-offset-6 col-md-2 col-md-offset-8">
        <a href="index.php?accion=listaUser" class='btn btn-default'>Volver</a>
    </div>
</div>

<form action='index.php?accion=updateUser' method="POST">
    <inout type='hidden' id='restaurantId' value='<?php echo $this->entidad->idUser ?>'>
    <div class="form-group">
    <label for="nombre">Nombre:</label>
    <input type="text" class="form-control" id="nombre" placeholder='Ingrese el Nombre' value='<?php echo $this->entidad->name ?>'>
  </div>
  <div class="form-group">
    <label for="descripcion">Descripci&oacute;n:</label>
    <input type="text" class="form-control" id="descripcion" placeholder="Ingrese la descripci&oacute;n" value='<?php echo $this->entidad->description ?>'>
  </div>
  <button type="submit" class="btn btn-success">Editar</button>
</form>
