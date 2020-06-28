<?php
class Conexion
{
	
	public static function conectar(){

		$localhost = "127.0.0.1";
		$database = "comedores";
		$user = "root";
		$password = "angel";

		$link = new PDO("mysql:host=$localhost;dbname=$database",$user,$password);

		return $link;
	}
}
?>
