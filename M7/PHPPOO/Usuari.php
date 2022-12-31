<?php
class Usuari {
    private $id;
    private $nom;
    private $cognoms;
    private $email;
    private $password;
	
	private $table;
     
    public function __construct() {
        $table="usuaris";        
    }
     
    public function getId() {
        return $this->id;
    }
 
    public function setId($id) {
        $this->id = $id;
    }
     
    public function getNom() {
        return $this->nom;
    }
 
    public function setNom($nom) {
        $this->nom = $nom;
    }
 
    public function getCognoms() {
        return $this->cognoms;
    }
 
    public function setCognoms($cognoms) {
        $this->cognoms = $cognoms;
    }
 
    public function getEmail() {
        return $this->email;
    }
 
    public function setEmail($email) {
        $this->email = $email;
    }
 
    public function getPassword() {
        return $this->password;
    }
 
    public function setPassword($password) {
        $this->password = $password;
    }
 
}
?>
