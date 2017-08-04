<div class="row">
    <div class="col-xs-2">
        <h3 >        
            <strong>Platos</strong>
        </h3>
    </div>
    <div class="col-xs-4 col-xs-offset-6 col-md-2 col-md-offset-8">
        <a class="btn btn-success" href="index.php&accion=altaPlato">
            Crear 
        </a>
        <a href="index.php" class='btn btn-default'>Volver</a>
    </div>
</div>
     
<table class="dataTable display"  cellspacing="0" width="100%">
    <thead>
        <tr>
            <th>Id</th>            
            <th>Descripci&oacute;n</th>
            <th>Categor&iacute;a</th>
            <th>Calificaci&oacuet;n</th>
            <th>Nombre del Plato</th>
            <th>Precio</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <?php foreach ($this->lista as $plato) { ?>                
            <tr>
                <td><?php echo $plato->idPlateMenu ?></td>
                <td><?php echo $plato->descripcion ?></td>
                <td><?php echo $plato->categoria ?></td>
                <td><?php echo $plato->averageCalification ?></td>
                <td><?php echo $plato->name ?></td>
                <td><?php echo $plato->price ?></td>                
                <td> 
                    <a title='Editar' class='btn btn-info' href='index.php?accion=editPlato&id=<?php echo $plato->idPlateMenu ?>'><i class='fa fa-pencil'></i></a>
                    <a title='Ver' class='btn btn-primary' href='index.php?accion=verPlato&id=<?php echo $plato->idPlateMenu ?>'><i class='fa fa-search'></i></a>
                </td>
            </tr>
        <?php } ?>
    </tbody>
</table>
