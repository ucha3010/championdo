-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-12-2022 a las 16:42:11
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
  `edad_inicio` int(11) DEFAULT NULL,
  `edad_fin` int(11) DEFAULT NULL,
  `id_cinturon_inicio` int(11) DEFAULT NULL,
  `id_cinturon_fin` int(11) DEFAULT NULL,
  `inclusivo` bit(1) NOT NULL,
  `infantil` bit(1) NOT NULL,
  `id_poomsae` int(11) DEFAULT NULL,
  `nombre` varchar(45) NOT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `edad_inicio`, `edad_fin`, `id_cinturon_inicio`, `id_cinturon_fin`, `inclusivo`, `infantil`, `id_poomsae`, `nombre`, `position`) VALUES
(1, 0, 0, 1, 19, b'1', b'0', 11, 'INCLUSIVO', 0),
(2, 3, 3, 1, 11, b'0', b'0', 1, 'A', 1),
(3, 4, 4, 1, 11, b'0', b'0', 1, 'B', 2),
(4, 5, 5, 1, 11, b'0', b'0', 1, 'C', 3),
(5, 6, 6, 1, 11, b'0', b'0', 1, 'D', 4),
(6, 7, 7, 1, 11, b'0', b'0', 1, 'E', 5),
(7, 8, 8, 1, 11, b'0', b'0', 1, 'F', 6),
(8, 7, 7, 1, 2, b'0', b'1', 2, 'A1', 7),
(9, 7, 7, 3, 5, b'0', b'1', 2, 'A2', 8),
(10, 7, 7, 6, 11, b'0', b'1', 2, 'A3', 9),
(11, 8, 9, 1, 3, b'0', b'1', 2, 'B1', 10),
(12, 8, 9, 4, 7, b'0', b'1', 3, 'B2', 11),
(13, 8, 9, 8, 11, b'0', b'1', 4, 'B3', 12),
(14, 10, 11, 1, 3, b'0', b'1', 2, 'C1', 13),
(15, 10, 11, 4, 7, b'0', b'1', 3, 'C2', 14),
(16, 10, 11, 8, 10, b'0', b'1', 4, 'C3', 15),
(17, 10, 11, 11, 11, b'0', b'1', 10, 'C4', 16),
(18, 12, 13, 1, 3, b'0', b'1', 2, 'D1', 17),
(19, 12, 13, 4, 7, b'0', b'1', 3, 'D2', 18),
(20, 12, 13, 8, 10, b'0', b'1', 6, 'D3', 19),
(21, 12, 13, 11, 11, b'0', b'1', 10, 'D4', 20),
(22, 14, 15, 1, 3, b'0', b'1', 2, 'E1', 21),
(23, 14, 15, 4, 7, b'0', b'1', 3, 'E2', 22),
(24, 14, 15, 8, 10, b'0', b'1', 6, 'E3', 23),
(25, 14, 15, 11, 11, b'0', b'1', 10, 'E4', 24),
(26, 16, 18, 1, 3, b'0', b'0', 2, 'A1', 25),
(27, 16, 18, 4, 7, b'0', b'0', 3, 'A2', 26),
(28, 16, 18, 8, 11, b'0', b'0', 5, 'A3', 27),
(29, 16, 18, 12, 19, b'0', b'0', 10, 'A4', 28),
(30, 19, 30, 1, 3, b'0', b'0', 2, 'B1', 29),
(31, 19, 30, 4, 7, b'0', b'0', 3, 'B2', 30),
(32, 19, 30, 8, 11, b'0', b'0', 5, 'B3', 31),
(33, 19, 30, 12, 19, b'0', b'0', 10, 'B4', 32),
(34, 31, 40, 1, 3, b'0', b'0', 2, 'C1', 33),
(35, 31, 40, 4, 7, b'0', b'0', 3, 'C2', 34),
(36, 31, 40, 8, 11, b'0', b'0', 5, 'C3', 35),
(37, 31, 40, 12, 19, b'0', b'0', 10, 'C4', 36),
(38, 41, 50, 1, 3, b'0', b'0', 2, 'D1', 37),
(39, 41, 50, 4, 7, b'0', b'0', 3, 'D2', 38),
(40, 41, 50, 8, 11, b'0', b'0', 5, 'D3', 39),
(41, 41, 50, 12, 19, b'0', b'0', 10, 'D4', 40),
(42, 51, 99, 1, 3, b'0', b'0', 2, 'E1', 41),
(43, 51, 99, 4, 7, b'0', b'0', 3, 'E2', 42),
(44, 51, 99, 8, 11, b'0', b'0', 5, 'E3', 43),
(45, 51, 99, 12, 19, b'0', b'0', 10, 'E4', 44);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cinturon`
--

CREATE TABLE `cinturon` (
  `id` int(11) NOT NULL,
  `categoria` varchar(50) NOT NULL,
  `color` varchar(40) NOT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cinturon`
--

INSERT INTO `cinturon` (`id`, `categoria`, `color`, `position`) VALUES
(1, '10º KUP', 'Blanco', 0),
(2, '9º KUP', 'Amarillo', 2),
(3, '8º KUP', 'Amarillo Naranja', 3),
(4, '7º KUP', 'Naranja', 4),
(5, '6º KUP', 'Naranja Verde', 5),
(6, '5º KUP', 'Verde', 6),
(7, '4º KUP', 'Verde Azul', 7),
(8, '3º KUP', 'Azul', 8),
(9, '2º KUP', 'Azul Rojo', 9),
(10, '1º KUP', 'Rojo', 10),
(11, 'POOM', 'Rojo Negro', 11),
(12, '1º DAN', 'Negro 1º DAN', 12),
(13, '2º DAN', 'Negro 2º DAN', 13),
(14, '3º DAN', 'Negro 3º DAN', 14),
(15, '4º DAN', 'Negro 4º DAN', 15),
(16, '5º DAN', 'Negro 5º DAN', 16),
(17, '6º DAN', 'Negro 6º DAN', 17),
(18, '7º DAN', 'Negro 7º DAN', 18),
(19, '8º DAN', 'Negro 8º DAN', 19),
(72, '9º KUP', 'Blanco Amarillo', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gimnasio`
--

CREATE TABLE `gimnasio` (
  `id` int(11) NOT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `gimnasio`
--

INSERT INTO `gimnasio` (`id`, `direccion`, `nombre`, `position`) VALUES
(1, 'Avenida Viñuelas 30 - Tres Cantos', 'Champion Do', 0);

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
(135);

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
  `notas` text DEFAULT NULL
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
(84, 'Portugal', 2),
(85, 'Bélgica', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poomsae`
--

CREATE TABLE `poomsae` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `poomsae`
--

INSERT INTO `poomsae` (`id`, `nombre`, `position`) VALUES
(1, '1º KICHO', 0),
(2, '1º POOMSAE', 1),
(3, '2º POOMSAE', 2),
(4, '3º POOMSAE', 3),
(5, '4º POOMSAE', 4),
(6, '5º POOMSAE', 5),
(7, '6º POOMSAE', 6),
(8, '7º POOMSAE', 7),
(9, '8º POOMSAE', 8),
(10, 'KORYO', 9),
(11, 'INCLUSIVO', 10);

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
  `id_calidad` int(11) DEFAULT NULL,
  `id_cinturon` int(11) DEFAULT NULL,
  `id_gimnasio` int(11) DEFAULT NULL,
  `id_pais` int(11) DEFAULT NULL,
  `menor` bit(1) NOT NULL,
  `sexo` varchar(9) DEFAULT NULL,
  `usernameacargo` varchar(45) DEFAULT NULL,
  `username_modificacione` varchar(45) DEFAULT NULL,
  `dni_menor` varchar(15) DEFAULT NULL,
  `inclusivo` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`username`, `enabled`, `lastname`, `name`, `password`, `second_lastname`, `correo`, `domicilio_calle`, `domicilio_cp`, `domicilio_localidad`, `domicilio_numero`, `domicilio_otros`, `fecha_alta`, `fecha_modificacion`, `fecha_nacimiento`, `id_calidad`, `id_cinturon`, `id_gimnasio`, `id_pais`, `menor`, `sexo`, `usernameacargo`, `username_modificacione`, `dni_menor`, `inclusivo`) VALUES
('05959715R', b'1', 'Usheff', 'Damián', '$2a$10$HGDwXdR4PfWEPqq7fOsyu.qOKq8PHAQQI/43GaT67R/fNAfRNxtQ6', 'Vellianitis', 'dusheff@hotmail.com', 'Calle Azorín', '28863', 'Cobeña', '18', '', '2022-11-13 23:09:22', '2022-11-13 23:56:55', '1976-10-30 00:00:00', 0, 10, 1, 1, b'0', 'Masculino', NULL, NULL, NULL, b'0'),
('11111111A', b'1', 'Pérez', 'Ana María', '$2a$10$10qmPm2ocWlplp5LRnPH3uy6P8YHEBWKvg4hFnbVanHd1golw92oe', 'Rodríguez', 'dusheff@hotmail.com', '', '', '', '', '', '2022-11-01 23:14:31', '2022-11-13 23:50:01', '1980-04-25 00:00:00', 0, 4, 1, 1, b'0', 'Femenino', NULL, NULL, NULL, b'0'),
('22222222A', b'1', 'Acosta', 'Alberto Federico', '$2a$10$jJ19myPeQhJG/wtvw/31wusF2YIgfqFWW7zzcvzz0vY25yKXPX18i', '', 'dusheff@hotmail.com', 'Calle Montes de Oca', '', 'San Agustín de Guadalix', '256', '', '2022-11-01 23:13:58', '2022-12-09 01:35:54', NULL, 0, 6, 1, 1, b'0', 'Masculino', NULL, '11111111A', NULL, b'0'),
('33333333C', b'1', 'Apellido9', 'Alicia', '$2a$10$9a2J2DLcU38awGUD/RE7Pe7ldSLeHyuUlNd/tJwMiqNw2cwkBzlrW', 'SegundoApellido4', 'dusheff@hotmail.com', 'Calle del Águila', 'Madrid', '28062', '16', '', '2022-11-19 16:08:10', '2022-12-08 12:13:15', '1992-04-16 00:00:00', 0, 6, 90, 1, b'0', 'Femenino', NULL, NULL, NULL, b'0'),
('65014208Q', b'1', 'Merlo', 'Esteban', '$2a$10$95VlDNXOO/Om5h16rRxCQ.lKkyLWENe3VifKYF/DgDCJs7kzP13dK', 'Giménez', 'dusheff@hotmail.com', 'Calle Miguel Hernández', '28863', 'Cobeña', '10', '', '2022-12-07 11:47:05', '2022-12-07 14:00:38', '1984-06-15 00:00:00', 0, 16, 1, 1, b'0', 'Masculino', NULL, NULL, NULL, b'0'),
('X4903089H', b'1', 'Ucha', 'Damián', '$2a$10$DPLw0qfdWmekC5Qg6ZWXMeiSSFAOOq6II34lJYCagFI594DqKWZO6', '', 'dusheff@hotmail.com', '', '', '', '', '', '2022-11-27 18:33:55', '2022-11-27 18:33:56', '1976-10-30 00:00:00', 0, 1, 1, 1, b'0', 'Masculino', NULL, NULL, NULL, b'0');

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
(3, 'ROLE_ADMIN', '05959715R'),
(1, 'ROLE_ADMIN', '11111111A'),
(126, 'ROLE_USER', '22222222A'),
(4, 'ROLE_USER', '33333333C'),
(94, 'ROLE_USER', '65014208Q'),
(67, 'ROLE_USER', 'X4903089H');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `util`
--

CREATE TABLE `util` (
  `clave` varchar(30) NOT NULL,
  `valor` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `util`
--

INSERT INTO `util` (`clave`, `valor`) VALUES
('campeonato.direccion', 'Av. de la Luz, s/n, 28760 Tres Cantos, Madrid'),
('campeonato.fecha', '10-12-2022'),
('campeonato.nombre', 'CAMPEONATO DE TRES CANTOS'),
('gimnasio.correo', 'damianjava@gmail.com'),
('inscripciones.borrar', 'true');

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
-- Indices de la tabla `gimnasio`
--
ALTER TABLE `gimnasio`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
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
  ADD PRIMARY KEY (`clave`);

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
