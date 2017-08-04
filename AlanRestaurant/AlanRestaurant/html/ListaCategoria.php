<div class="row">
    <div class="col-xs-2">
        <h3 >        
            <strong>Categor&iacute;as</strong>
        </h3>
    </div>
    <div class="col-xs-4 col-xs-offset-6 col-md-2 col-md-offset-8">
        <a class="btn btn-success" href="index.php&accion=altaCategoria">
            Crear 
        </a>
        <a href="index.php" class='btn btn-default'>Volver</a>
    </div>
</div>
     
<table class="dataTable display"  cellspacing="0" width="100%">
    <thead>
        <tr>
            <th>Id</th>            
            <th>Categor&iacute;a</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <?php foreach ($this->lista as $categoria) { ?>                
            <tr>
                <td><?php echo $categoria->idCategoryPlate ?></td>
                <td><?php echo $categoria->name ?></td>
                <td> 
                    <a title='Editar' class='btn btn-info' href='index.php?accion=editCategoria&id=<?php echo $categoria->idCategoryPlate ?>'><i class='fa fa-pencil'></i></a>
                    <a title='Ver' class='btn btn-primary' href='index.php?accion=verCategoria&id=<?php echo $categoria->idCategoryPlate ?>'><i class='fa fa-search'></i></a>
                </td>
            </tr>
        <?php } ?>
    </tbody>
</table>
