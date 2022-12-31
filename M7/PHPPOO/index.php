
<!doctype html>
<?php
require_once("llistarUsuaris.php");

?>
<html lang="es">
    <head>
        <meta charset="utf-8"/>
        <title>Exemple PHP MySQL POO</title>
        
    </head>
    <body>
        <form action="afegirUsuari.php" method="post" >
            <h3>Afegir usuari</h3>
            <hr/>
            Nom: <input type="text" name="nom"/>
            Cognoms: <input type="text" name="cognoms" />
            Email: <input type="text" name="email" />
            Contrasenya: <input type="password" name="password" />
            <input type="submit" value="enviar" />
        </form>
         
        <div>
            <h3>Usuaris</h3>
            <hr/>
        </div>
        <div>
            <?php 
				if (isset($allusers)){
					foreach($allusers as $user) { //recorrem la llista d'usuaris ?>
						<?php echo $user->id; ?> -
						<?php echo $user->nom; ?> -
						<?php echo $user->cognoms; ?> -
						<?php echo $user->email; ?>
						<div >
							<a href="esborrarUsuari.php?id=<?php echo $user->id?>">Borrar</a>
						</div>
						<hr/>
			<?php   
					} 
				} 
			?>
        </div>
        <div>
            <hr/>
           Exemple PHP MySQL POO 
        </div>
    </body>
</html>
