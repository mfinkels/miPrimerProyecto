<div class="row">
    <div class="col-xs-2">
        <h3 >        
            <strong>Edici&oacute;n de Usuario</strong>
        </h3>
    </div>
    <div class="col-xs-4 col-xs-offset-6 col-md-2 col-md-offset-8">
        <a href="index.php?accion=listaUser" class='btn btn-default'>Volver</a>
    </div>
</div>

<form action='index.php?accion=updateUser' method="POST">
    <inout type='hidden' id='userId' value='<?php echo $this->entidad->idUser ?>'>
    <div class="form-group">
    <label for="nombre">Nombre:</label>
    <input type="text" class="form-control" id="nombre" placeholder='Ingrese su Nombre' value='<?php echo $this->entidad->name ?>'>
  </div>
  <div class="form-group">
    <label for="Apellido">Apellido:</label>
    <input type="text" class="form-control" id="apellido" placeholder="Ingrese su Apellido" value='<?php echo $this->entidad->lastName ?>'>
  </div>
  <button type="submit" class="btn btn-success">Editar</button>
</form>
