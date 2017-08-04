<div class="row">
    <div class="col-xs-2">
        <h3 >        
            <strong>Restaurants</strong>
        </h3>
    </div>
    <div class="col-xs-4 col-xs-offset-6 col-md-2 col-md-offset-8">
        <a class="btn btn-success" href="index.php&accion=altaRestaurant">
            Crear 
        </a>
        <a href="index.php" class='btn btn-default'>Volver</a>
    </div>
</div>
     
<table class="dataTable display"  cellspacing="0" width="100%">
    <thead>
        <tr>
            <th>Id</th>            
            <th>Nombre</th>
            <th>Descripci&oacute;n</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <?php foreach ($this->lista as $user) { ?>                
            <tr>
                <td><?php echo $user->idRestaurant ?></td>
                <td><?php echo $user->name ?></td>
                <td><?php echo $user->description ?></td>
                <td> 
                    <a title='Editar' class='btn btn-info' href='index.php?accion=editRestaurant&id=<?php echo $user->idRestaurant ?>'><i class='fa fa-pencil'></i></a>                   
                    <a title='Ir a Branchs' class='btn btn-primary' href='index.php?accion=listaBranchRestaurant&id=<?php echo $user->idRestaurant ?>'><i class='fa fa-search'></i></a>
                </td>
            </tr>
        <?php } ?>
    </tbody>
</table>
