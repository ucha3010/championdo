-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-11-2023 a las 16:51:35
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `championdo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calidad`
--

CREATE TABLE `calidad` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `otro` varchar(200) DEFAULT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `calidad`
--

INSERT INTO `calidad` (`id`, `nombre`, `otro`, `position`) VALUES
(1, 'Madre', NULL, 0),
(2, 'Padre', NULL, 1),
(3, 'Tutor', NULL, 2),
(4, 'Encargado', NULL, 3),
(5, 'Otro', NULL, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `codigo_gimnasio` int(11) NOT NULL,
  `position` int(11) DEFAULT NULL,
  `edad_inicio` int(11) DEFAULT NULL,
  `edad_fin` int(11) DEFAULT NULL,
  `position_cinturon_inicio` int(11) DEFAULT NULL,
  `position_cinturon_fin` int(11) DEFAULT NULL,
  `inclusivo` bit(1) NOT NULL,
  `preinfantil` bit(1) NOT NULL,
  `infantil` bit(1) NOT NULL,
  `adulto` bit(1) NOT NULL,
  `id_cinturon_fin` int(11) NOT NULL,
  `id_cinturon_inicio` int(11) NOT NULL,
  `id_poomsae` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `change_low_sequence`
--

CREATE TABLE `change_low_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `change_low_sequence`
--

INSERT INTO `change_low_sequence` (`next_val`) VALUES
(6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cinturon`
--

CREATE TABLE `cinturon` (
  `id` int(11) NOT NULL,
  `color` varchar(40) NOT NULL,
  `position` int(11) DEFAULT NULL,
  `codigo_gimnasio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `firma`
--

CREATE TABLE `firma` (
  `id` int(11) NOT NULL,
  `firmado` bit(1) NOT NULL,
  `id_operacion` int(11) NOT NULL,
  `numero_intentos` int(11) NOT NULL,
  `codigo_gimnasio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `firma_codigo`
--

CREATE TABLE `firma_codigo` (
  `id` int(11) NOT NULL,
  `codigo` varchar(6) NOT NULL,
  `dni` varchar(45) NOT NULL,
  `fecha_caducidad` datetime DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `id_operacion` int(11) NOT NULL,
  `operativa_original` varchar(50) NOT NULL,
  `pagina_firma_ok` varchar(200) NOT NULL,
  `codigo_gimnasio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gimnasio`
--

CREATE TABLE `gimnasio` (
  `id` int(11) NOT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `position` int(11) DEFAULT NULL,
  `codigo_gimnasio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gimnasio_root`
--

CREATE TABLE `gimnasio_root` (
  `id` int(11) NOT NULL,
  `apellido1responsable` varchar(60) DEFAULT NULL,
  `apellido2responsable` varchar(60) DEFAULT NULL,
  `cantidad_registros_contratados` int(11) NOT NULL,
  `cif_nif` varchar(15) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `domicilio_calle` varchar(100) DEFAULT NULL,
  `domicilio_cp` varchar(10) DEFAULT NULL,
  `domicilio_localidad` varchar(50) DEFAULT NULL,
  `domicilio_numero` varchar(30) DEFAULT NULL,
  `domicilio_otros` varchar(50) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `fecha_alta` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `nombre_gimnasio` varchar(100) DEFAULT NULL,
  `nombre_responsable` varchar(60) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `usuario_modificacion` varchar(45) DEFAULT NULL,
  `visibilidad_contratada` int(11) NOT NULL,
  `fecha_nacimiento` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion`
--

CREATE TABLE `inscripcion` (
  `id` int(11) NOT NULL,
  `inscripcion_propia` bit(1) NOT NULL,
  `inscripcion_menor` bit(1) NOT NULL,
  `inscripcion_inclusiva` bit(1) NOT NULL,
  `fecha_inscripcion` datetime DEFAULT NULL,
  `fecha_campeonato` varchar(10) DEFAULT NULL,
  `nombre_campeonato` varchar(200) DEFAULT NULL,
  `direccion_campeonato` varchar(200) DEFAULT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  `nombre_inscripto` varchar(60) DEFAULT NULL,
  `apellido1inscripto` varchar(60) DEFAULT NULL,
  `apellido2inscripto` varchar(60) DEFAULT NULL,
  `dni_inscripto` varchar(45) DEFAULT NULL,
  `fecha_nacimiento_inscripto` datetime DEFAULT NULL,
  `sexo_inscripto` varchar(9) NOT NULL,
  `domicilio_calle_inscripto` varchar(100) DEFAULT NULL,
  `domicilio_numero_inscripto` varchar(30) DEFAULT NULL,
  `domicilio_otros_inscripto` varchar(50) DEFAULT NULL,
  `domicilio_localidad_inscripto` varchar(50) DEFAULT NULL,
  `domicilio_cp_inscripto` varchar(10) DEFAULT NULL,
  `gimnasio` varchar(100) DEFAULT NULL,
  `pais` varchar(20) DEFAULT NULL,
  `cinturon` varchar(40) DEFAULT NULL,
  `poomsae` varchar(50) DEFAULT NULL,
  `nombre_autorizador` varchar(60) DEFAULT NULL,
  `apellido1autorizador` varchar(60) DEFAULT NULL,
  `apellido2autorizador` varchar(60) DEFAULT NULL,
  `dni_autorizador` varchar(45) DEFAULT NULL,
  `calidad` varchar(20) DEFAULT NULL,
  `domicilio_calle_autorizador` varchar(100) DEFAULT NULL,
  `domicilio_numero_autorizador` varchar(30) DEFAULT NULL,
  `domicilio_otros_autorizador` varchar(50) DEFAULT NULL,
  `domicilio_localidad_autorizador` varchar(50) DEFAULT NULL,
  `domicilio_cp_autorizador` varchar(10) DEFAULT NULL,
  `pago_realizado` bit(1) NOT NULL,
  `fecha_pago` datetime DEFAULT NULL,
  `notas` text DEFAULT NULL,
  `codigo_gimnasio` int(11) NOT NULL,
  `id_torneo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion_taekwondo`
--

CREATE TABLE `inscripcion_taekwondo` (
  `id` int(11) NOT NULL,
  `fecha_inscripcion` datetime DEFAULT NULL,
  `mayor_nombre` varchar(60) NOT NULL,
  `mayor_apellido1` varchar(60) NOT NULL,
  `mayor_apellido2` varchar(60) DEFAULT NULL,
  `mayor_dni` varchar(45) NOT NULL,
  `mayor_correo` varchar(100) DEFAULT NULL,
  `mayor_fecha_nacimiento` datetime DEFAULT NULL,
  `mayor_telefono` varchar(20) DEFAULT NULL,
  `mayor_sexo` varchar(9) DEFAULT NULL,
  `mayor_domicilio_calle` varchar(100) DEFAULT NULL,
  `mayor_domicilio_numero` varchar(30) DEFAULT NULL,
  `mayor_domicilio_otros` varchar(50) DEFAULT NULL,
  `mayor_domicilio_localidad` varchar(50) DEFAULT NULL,
  `mayor_domicilio_cp` varchar(10) DEFAULT NULL,
  `mayor_pais` varchar(20) DEFAULT NULL,
  `mayor_calidad` varchar(20) DEFAULT NULL,
  `mayor_cinturon` varchar(40) DEFAULT NULL,
  `mayor_licencia` bit(1) NOT NULL,
  `mayor_autoriza_whats_app` bit(1) NOT NULL,
  `autorizado_menor` bit(1) NOT NULL,
  `autorizado_nombre` varchar(60) DEFAULT NULL,
  `autorizado_apellido1` varchar(60) DEFAULT NULL,
  `autorizado_apellido2` varchar(60) DEFAULT NULL,
  `autorizado_dni` varchar(45) DEFAULT NULL,
  `autorizado_fecha_nacimiento` datetime DEFAULT NULL,
  `autorizado_sexo` varchar(9) DEFAULT NULL,
  `autorizado_pais` varchar(20) DEFAULT NULL,
  `autorizado_cinturon` varchar(40) DEFAULT NULL,
  `autorizado_licencia` bit(1) NOT NULL,
  `domiciliacionsepa` bit(1) NOT NULL,
  `titular_cuenta` varchar(100) DEFAULT NULL,
  `iban` varchar(34) DEFAULT NULL,
  `swift` varchar(11) DEFAULT NULL,
  `notas` text DEFAULT NULL,
  `inscripcion_firmada` bit(1) NOT NULL,
  `mandatosepafirmado` bit(1) NOT NULL,
  `extensionsepafirmado` varchar(10) DEFAULT NULL,
  `codigo_gimnasio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pais`
--

CREATE TABLE `pais` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pais`
--

INSERT INTO `pais` (`id`, `nombre`, `position`) VALUES
(1, 'España', 0),
(2, 'Francia', 1),
(3, 'Portugal', 2),
(4, 'Bélgica', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poomsae`
--

CREATE TABLE `poomsae` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `position` int(11) DEFAULT NULL,
  `codigo_gimnasio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `torneo`
--

CREATE TABLE `torneo` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `fecha_torneo` datetime DEFAULT NULL,
  `fecha_comienzo_inscripcion` datetime DEFAULT NULL,
  `fecha_fin_inscripcion` datetime DEFAULT NULL,
  `codigo_gimnasio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `torneo_gimnasio`
--

CREATE TABLE `torneo_gimnasio` (
  `id` int(11) NOT NULL,
  `id_torneo` int(11) NOT NULL,
  `nombre_gimnasio` varchar(100) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `codigo_gimnasio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `lastname` varchar(60) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `second_lastname` varchar(60) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `domicilio_calle` varchar(100) DEFAULT NULL,
  `domicilio_cp` varchar(10) DEFAULT NULL,
  `domicilio_localidad` varchar(50) DEFAULT NULL,
  `domicilio_numero` varchar(30) DEFAULT NULL,
  `domicilio_otros` varchar(50) DEFAULT NULL,
  `fecha_alta` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `fecha_nacimiento` datetime DEFAULT NULL,
  `id_calidad` int(11) NOT NULL DEFAULT 0,
  `id_cinturon` int(11) NOT NULL DEFAULT 0,
  `id_gimnasio` int(11) NOT NULL DEFAULT 0,
  `id_pais` int(11) DEFAULT 0,
  `menor` bit(1) NOT NULL,
  `sexo` varchar(9) DEFAULT NULL,
  `usernameacargo` varchar(45) DEFAULT NULL,
  `username_modificacione` varchar(45) DEFAULT NULL,
  `dni_menor` varchar(15) DEFAULT NULL,
  `inclusivo` bit(1) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `codigo_gimnasio` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`username`, `enabled`, `lastname`, `name`, `password`, `second_lastname`, `correo`, `domicilio_calle`, `domicilio_cp`, `domicilio_localidad`, `domicilio_numero`, `domicilio_otros`, `fecha_alta`, `fecha_modificacion`, `fecha_nacimiento`, `id_calidad`, `id_cinturon`, `id_gimnasio`, `id_pais`, `menor`, `sexo`, `usernameacargo`, `username_modificacione`, `dni_menor`, `inclusivo`, `telefono`, `codigo_gimnasio`) VALUES
('05959715R', b'1', 'Usheff', 'Damián', '$2a$10$mRX05pSePQGx9tun.srtdewjdrwq8A8LYmxy/hbupSxTSs0yXj7pi', 'Vellianitis', 'dusheff@hotmail.com', 'Calle Azorín', '28863', 'Cobeña', '18', '', '2022-11-13 23:09:22', '2023-11-08 16:50:36', '1976-10-30 00:00:00', 0, 0, 0, 1, b'0', 'Masculino', NULL, NULL, NULL, b'0', NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_role`
--

CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_role`
--

INSERT INTO `user_role` (`user_role_id`, `role`, `username`) VALUES
(1, 'ROLE_ROOT', '05959715R');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `util`
--

CREATE TABLE `util` (
  `clave` varchar(100) NOT NULL,
  `codigo_gimnasio` int(11) NOT NULL,
  `valor` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `calidad`
--
ALTER TABLE `calidad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cinturon`
--
ALTER TABLE `cinturon`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `firma`
--
ALTER TABLE `firma`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `firma_codigo`
--
ALTER TABLE `firma_codigo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `gimnasio`
--
ALTER TABLE `gimnasio`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `gimnasio_root`
--
ALTER TABLE `gimnasio_root`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `inscripcion_taekwondo`
--
ALTER TABLE `inscripcion_taekwondo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pais`
--
ALTER TABLE `pais`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `poomsae`
--
ALTER TABLE `poomsae`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `torneo`
--
ALTER TABLE `torneo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `torneo_gimnasio`
--
ALTER TABLE `torneo_gimnasio`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_role_id`),
  ADD UNIQUE KEY `UKadnyt6agwl65jnnokuvnskhn2` (`role`,`username`),
  ADD KEY `FK2svos04wv92op6gs17m9omli1` (`username`);

--
-- Indices de la tabla `util`
--
ALTER TABLE `util`
  ADD PRIMARY KEY (`clave`,`codigo_gimnasio`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK2svos04wv92op6gs17m9omli1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
