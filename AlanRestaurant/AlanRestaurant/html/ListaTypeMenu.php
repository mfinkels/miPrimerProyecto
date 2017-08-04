<div class="row">
    <div class="col-xs-2">
        <h3 >        
            <strong>Tipo de Men&uacute</strong>
        </h3>
    </div>
    <div class="col-xs-4 col-xs-offset-6 col-md-2 col-md-offset-8">
        <a class="btn btn-success" href="index.php&accion=altaTypeMenu">
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
            <th>Branch</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <?php foreach ($this->lista as $typeMenu) { ?>                
            <tr>
                <td><?php echo $typeMenu->idUser ?></td>
                <td><?php echo $typeMenu->name ?></td>
                <td><?php //echo $typeMenu-> ?></td>
                <td> 
                    <a title='Editar' class='btn btn-info' href='index.php?accion=editTypeMenu&id=<?php echo $user->idTypeMenu ?>'><i class='fa fa-pencil'></i></a>
                    <a title='Ver' class='btn btn-primary' href='index.php?accion=verTypeMenu&id=<?php echo $user->idTypeMenu ?>'><i class='fa fa-search'></i></a>
                </td>
            </tr>
        <?php } ?>
    </tbody>
</table>
