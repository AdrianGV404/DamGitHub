<?php	
require_once("Connectar.php");

//connectem a la base de dades. 
$connectar = new Connectar();
$conn = $connectar->connection();


//Comprovem la connecció
if (!$conn) {
      //die("La connexió a la base de dades ha fallat: " . mysqli_connect_error());
	  die("La connexió a la base de dades ha fallat: " . $conn->mysqli_connect_error());
}
 
echo "Connected successfully";
 
$sql = "DELETE FROM usuaris WHERE id='" . $_GET["id"] . "'";

if ($conn->query($sql)) {
      echo "Usuari esborrat satisfactòriament";
} else {
      //error_log("Error: " . $sql . "<br>" . mysqli_error($conn));
	  error_log("Error: " . $sql . "<br>" . $conn->mysqli_error());
}

$conn->close();
header('Location: index.php');
?>
