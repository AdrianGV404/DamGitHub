<?php	
require_once("Connectar.php"); 

//connectem a la base de dades. 
$connectar = new Connectar();
$conn = $connectar->connection();

 if ($conn->connect_errno) {
    printf("La connexió a la base de dades ha fallat: %s\n", $mysqli->connect_error);
    exit();
 }

$sql = "INSERT INTO Usuaris (id, nom, cognoms, email, password) VALUES (NULL,'" . $_POST["nom"] . "', '" . $_POST["cognoms"] . "' ,'" . $_POST["email"] . "', '" . sha1($_POST["password"])."')";

if ($conn->query($sql)) {
      
} else {
      // error_log("Error: " . $sql . "<br>" . mysqli_error($conn));
	  error_log("Error: " . $sql . "<br>" . $conn->mysqli_error());
	  
}
$conn->close();
header('Location: index.php'); 
?>
