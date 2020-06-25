-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.37-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win32
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para comedores
CREATE DATABASE IF NOT EXISTS `comedores` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `comedores`;

-- Volcando estructura para tabla comedores.comedor
CREATE TABLE IF NOT EXISTS `comedor` (
  `id_comedor` int(5) NOT NULL,
  `Nombre` varchar(50) NOT NULL DEFAULT '',
  `Dueño` varchar(50) NOT NULL DEFAULT '',
  `Ubicación` varchar(50) NOT NULL DEFAULT '',
  `Estado` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_comedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla comedores.comedor: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `comedor` DISABLE KEYS */;
INSERT INTO `comedor` (`id_comedor`, `Nombre`, `Dueño`, `Ubicación`, `Estado`) VALUES
	(1, 'Comedor Centro de Lima', 'Raphael Izquierdo', 'Plaza de Armas', 0);
/*!40000 ALTER TABLE `comedor` ENABLE KEYS */;

-- Volcando estructura para tabla comedores.comedor_servicio
CREATE TABLE IF NOT EXISTS `comedor_servicio` (
  `id_comedore_servicio` int(2) NOT NULL,
  `id_comedor` int(5) NOT NULL,
  `id_servicio` int(1) NOT NULL,
  `estado` int(11) NOT NULL,
  `precio` int(11) NOT NULL,
  PRIMARY KEY (`id_comedore_servicio`),
  KEY `FK_comedor_servicio_comedor` (`id_comedor`),
  KEY `FK_comedor_servicio_servicio` (`id_servicio`),
  CONSTRAINT `FK_comedor_servicio_comedor` FOREIGN KEY (`id_comedor`) REFERENCES `comedor` (`id_comedor`),
  CONSTRAINT `FK_comedor_servicio_servicio` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id_servicio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla comedores.comedor_servicio: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `comedor_servicio` DISABLE KEYS */;
INSERT INTO `comedor_servicio` (`id_comedore_servicio`, `id_comedor`, `id_servicio`, `estado`, `precio`) VALUES
	(1, 1, 1, 0, 10),
	(2, 1, 2, 0, 15),
	(3, 1, 3, 0, 12);
/*!40000 ALTER TABLE `comedor_servicio` ENABLE KEYS */;

-- Volcando estructura para tabla comedores.empleados
CREATE TABLE IF NOT EXISTS `empleados` (
  `id_empleado` int(3) NOT NULL DEFAULT '0',
  `dni` varchar(12) NOT NULL,
  `nombre` varchar(75) NOT NULL,
  `apellido` varchar(75) NOT NULL,
  `categoria` varchar(20) NOT NULL,
  `id_empresa` int(3) NOT NULL DEFAULT '0',
  `estado` int(1) NOT NULL DEFAULT '0',
  `fecha_ingreso` date DEFAULT '0000-00-00',
  `fecha_cese` date DEFAULT '0000-00-00',
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `id_empleado` (`id_empleado`),
  KEY `FK_empleados_empresa` (`id_empresa`),
  KEY `id_empleado_2` (`id_empleado`),
  CONSTRAINT `FK_empleados_empresa` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla comedores.empleados: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` (`id_empleado`, `dni`, `nombre`, `apellido`, `categoria`, `id_empresa`, `estado`, `fecha_ingreso`, `fecha_cese`) VALUES
	(2, '12345678', 'Pepito', 'Yactayo', 'obrero', 1, 0, '0000-00-00', '0000-00-00'),
	(3, '98765432', 'Daniel', 'Perez', 'obrero', 1, 0, '0000-00-00', '0000-00-00'),
	(4, '56785678', 'Juan', 'Perez', 'obrero', 1, 0, '0000-00-00', '0000-00-00'),
	(5, '87658765', 'Diego', 'Anyoza', 'obrero', 1, 0, '0000-00-00', '0000-00-00'),
	(6, '23412341', 'Juanito', 'Paredez', 'obrero', 1, 0, '0000-00-00', '0000-00-00');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;

-- Volcando estructura para tabla comedores.empleado_servicio
CREATE TABLE IF NOT EXISTS `empleado_servicio` (
  `id_empleado` int(3) NOT NULL,
  `id_comedor_servicio` int(2) NOT NULL,
  `estado` int(1) NOT NULL,
  KEY `FK_empleado_servicio_empleados` (`id_empleado`),
  KEY `FK_empleado_servicio_comedor_servicio` (`id_comedor_servicio`),
  CONSTRAINT `FK_empleado_servicio_comedor_servicio` FOREIGN KEY (`id_comedor_servicio`) REFERENCES `comedor_servicio` (`id_comedore_servicio`),
  CONSTRAINT `FK_empleado_servicio_empleados` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla comedores.empleado_servicio: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `empleado_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado_servicio` ENABLE KEYS */;

-- Volcando estructura para tabla comedores.empresa
CREATE TABLE IF NOT EXISTS `empresa` (
  `id_empresa` int(3) NOT NULL,
  `nombre` varchar(100) NOT NULL DEFAULT '',
  `ruc` varchar(20) NOT NULL DEFAULT '',
  `sedes` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla comedores.empresa: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` (`id_empresa`, `nombre`, `ruc`, `sedes`) VALUES
	(1, 'UTP', '20462509236', 4);
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;

-- Volcando estructura para tabla comedores.registro
CREATE TABLE IF NOT EXISTS `registro` (
  `id_registro` int(4) NOT NULL,
  `empleado_dni` varchar(10) NOT NULL DEFAULT '',
  `empleado_nombre` varchar(50) NOT NULL DEFAULT '',
  `servicio_nombre` varchar(50) NOT NULL DEFAULT '',
  `comedor_nombre` varchar(50) NOT NULL DEFAULT '',
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla comedores.registro: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro` ENABLE KEYS */;

-- Volcando estructura para tabla comedores.servicio
CREATE TABLE IF NOT EXISTS `servicio` (
  `id_servicio` int(1) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_servicio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla comedores.servicio: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` (`id_servicio`, `nombre`) VALUES
	(1, 'Desayuno'),
	(2, 'Almuerzo'),
	(3, 'Cena');
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
