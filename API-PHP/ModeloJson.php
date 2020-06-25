<?php


require_once "Conexion.php";

/**
 * 
 */
class Datos extends Conexion
{
	
	#USUARIOS
	//----------------------------------------------------------------------------------

	public function createEmpleadoModel($datosModel, $tabla){

		$stmt = Conexion::conectar()->prepare("INSERT INTO $tabla (id_empleado,dni, nombre, apellido, categoria,id_empresa,estado,fecha_ingreso,fecha_cese) VALUES (:id_empleado, :dni, :nombre, :apellido, :categoria,:id_empresa,:estado,:fecha_ingreso,:fecha_cese)");

		

		$stmt->bindParam(":id_empleado", $datosModel["id_empleado"], PDO::PARAM_STR);
		$stmt->bindParam(":dni", $datosModel["dni"], PDO::PARAM_STR);
		$stmt->bindParam(":nombre", $datosModel["nombre"], PDO::PARAM_STR);
		$stmt->bindParam(":apellido", $datosModel["apellido"], PDO::PARAM_STR);
		$stmt->bindParam(":categoria", $datosModel["categoria"], PDO::PARAM_STR);
		$stmt->bindParam(":id_empresa", $datosModel["id_empresa"], PDO::PARAM_STR);
		$stmt->bindParam(":estado", $datosModel["estado"], PDO::PARAM_STR);
		$stmt->bindParam(":fecha_ingreso", $datosModel["fecha_ingreso"], PDO::PARAM_STR);
		$stmt->bindParam(":fecha_cese", $datosModel["fecha_cese"], PDO::PARAM_STR);



		if($stmt->execute()){
			return true;
		}else{
			return false;
		}
	}

	public function readEmpleadoModel($tabla){

		$stmt = Conexion::conectar()->prepare("SELECT id_empleado,dni, nombre, apellido, categoria, id_empresa,estado FROM $tabla");
		$stmt->execute();

		$stmt->bindColumn("id_empleado", $id_empleado);
		$stmt->bindColumn("dni", $dni);
		$stmt->bindColumn("nombre", $nombre);
		$stmt->bindColumn("apellido", $apellido);
		$stmt->bindColumn("categoria", $categoria);
		$stmt->bindColumn("id_empresa", $id_empresa);
		$stmt->bindColumn("estado", $estado);

	

		$usuarios = array();

		while ($fila = $stmt->fetch(PDO::FETCH_BOUND)){
			$user = array();
			$user["id_empleado"] = utf8_encode($id_empleado);
			$user["dni"] = utf8_encode($dni);
			$user["nombre"] = utf8_encode($nombre);
			$user["apellido"] = utf8_encode($apellido);
			$user["categoria"] = utf8_encode($categoria);
			$user["id_empresa"] = utf8_encode($id_empresa);
			$user["estado"] = utf8_encode($estado);

			array_push($usuarios, $user);
		}

		return $usuarios;
	}

	

}

?>