<?php

require_once 'ControllerJson.php';

//función validando todos los parametros disponibles
//pasaremos los parámetros requeridos a esta función

function isTheseParametersAvailable($params){
	//suponiendo que todos los parametros estan disponibles
	$available = true;
	$missingparams = "";

	foreach ($params as $param) {
		if(!isset($_POST[$param]) || strlen($_POST[$param]) <= 0){
			$available = false;
			$missingparams = $missingparams . ", " . $param;
		}
	}

	//si faltan parametros
	if(!$available){
		$response = array();
		$response['error'] = true;
		$response['message'] = 'Parametro' . substr($missingparams, 1, strlen($missingparams)) . ' vacio';

		//error de visualización
		echo json_encode($response);

		//detener la ejecición adicional
		die();
	}
}

//una matriza para mostrar las respuestas de nuestro api
$response = array();

//si se trata de una llamada api
//que significa que un parametro get llamado se establece un la URL
//y con estos parametros estamos concluyendo que es una llamada api

if(isset($_GET['apicall'])){

	//Aqui iran todos los llamados de nuestra api
	switch ($_GET['apicall']) {

		//opreacion createUsuarios
		case 'createEmpleado':
			
			//primero haremos la verificación de parametros.
		isTheseParametersAvailable(array('id_empleado','dni', 'nombre', 'apellido', 'categoria','id_empresa','estado','fecha_ingreso','fecha_cese'));

		$db = new ControllerJson();

		$result = $db->createEmpleadoController($_POST['id_empleado'],
			$_POST['dni'],
			$_POST['nombre'],
			$_POST['apellido'],
			$_POST['categoria'],
			$_POST['id_empresa'],
			$_POST['estado'],
			$_POST['fecha_ingreso'],
			$_POST['fecha_cese']);

		if($result){

			//esto significa que no hay ningun error
			$response['error'] = false;
			//mensaje que se ejecuto correctamente
			$response['message'] = 'empleado agregado correctamente';

			$response['contenido'] = $db->readEmpleadoController();
		}else{
			$response['error'] = true;
			$response['message'] = 'ocurrio un error, intenta nuevamente';
		}
			break;

		

	}

}else{
	//si no es un api el que se esta invocando
	//empujar los valores apropiados en la estructura json
	$response['error'] = true;
	$response['message'] = 'Invalid API Call';
}

echo json_encode($response);

?>