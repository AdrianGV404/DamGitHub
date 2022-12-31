<?php
require_once("Usuari.php");
require_once("Connectar.php");

//connectem a la base de dades. 
$connectar = new Connectar();
$conn = $connectar->connection();


// Check connection
if (!$conn) {
      // die("La connexió a la base de dades ha fallat: " . mysqli_connect_error());
	  die("La connexió a la base de dades ha fallat: " . $conn->mysqli_connect_error());
}

$sql = "SELECT * FROM Usuaris";
$resultat = $conn->query($sql);

if ($resultat) {
	 while ($row = $resultat->fetch_object()) {
           $allusers[]=$row;
        }
         
} else {
      //error_log("Error: " . $sql . "<br>" . mysqli_error($conn));
	  error_log("Error: " . $sql . "<br>" . $conn->mysqli_error());
	  
}

$resultat->close();
$conn->close();

?>