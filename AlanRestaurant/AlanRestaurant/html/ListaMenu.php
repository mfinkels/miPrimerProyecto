<div class="row">
    <div class="col-xs-2">
        <h3 >        
            <strong>Menues</strong>
        </h3>
    </div>
    <div class="col-xs-4 col-xs-offset-6 col-md-2 col-md-offset-8">
        <a class="btn btn-success" href="index.php&accion=altaMenu">
            Crear 
        </a>
        <a href="index.php" class='btn btn-default'>Volver</a>
    </div>
</div>
     
<table class="dataTable display"  cellspacing="0" width="100%">
    <thead>
        <tr>
            <th>Id</th>            
            <th>Branch</th>
            <th>Tipo de Men&uacute;</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <?php foreach ($this->lista as $user) { ?>                
            <tr>
                <td><?php echo $user->idUser ?></td>
                <td><?php //echo $user->name ?></td>
                <td><?php //echo $user->lastName ?></td>
                <td> 
                    <a title='Editar' class='btn btn-info' href='index.php?accion=editUser&id=<?php echo $user->idUser ?>'><i class='fa fa-pencil'></i></a>
                    <a title='Ver' class='btn btn-primary' href='index.php?accion=verUser&id=<?php echo $user->idUser ?>'><i class='fa fa-search'></i></a>
                </td>
            </tr>
        <?php } ?>
    </tbody>
</table>
