<?php

require_once "ModeloJson.php";

/**
 * 
 */
class ControllerJson
{
	
	#Empleado
	public function createEmpleadoController($id_empleado,$dni, $nombre, $apellido, $categoria,$id_empresa,$estado,$fecha_ingreso,$fecha_cese){

		$datosController = array("id_empleado"=>$id_empleado,
			"dni"=>$dni,
			"nombre"=>$nombre,
			"apellido"=>$apellido,
			"categoria"=>$categoria,
			"id_empresa"=>$id_empresa,
			"estado"=>$estado,
			"fecha_ingreso"=>$fecha_ingreso,
			"fecha_cese"=>$fecha_cese);

		$respuesta = Datos::createEmpleadoModel($datosController, "empleados");
		return $respuesta;
	}

	public function readEmpleadoController(){

		$respuesta = Datos::readEmpleadoModel("empleados");
		return $respuesta;
	}

	
}

?>