-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-11-2022 a las 19:50:10
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
  `otro` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `calidad`
--

INSERT INTO `calidad` (`id`, `nombre`, `otro`) VALUES
(1, 'Madre', NULL),
(2, 'Padre', NULL),
(3, 'Tutor', NULL),
(4, 'Encargado', NULL);

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
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `edad_inicio`, `edad_fin`, `id_cinturon_inicio`, `id_cinturon_fin`, `inclusivo`, `infantil`, `id_poomsae`, `nombre`) VALUES
(1, 0, 0, 1, 19, b'1', b'0', 1, 'INCLUSIVO'),
(2, 3, 3, 1, 11, b'0', b'0', 1, 'A'),
(3, 4, 4, 1, 11, b'0', b'0', 1, 'B'),
(4, 5, 5, 1, 11, b'0', b'0', 1, 'C'),
(5, 6, 6, 1, 11, b'0', b'0', 1, 'D'),
(6, 7, 7, 1, 11, b'0', b'0', 1, 'E'),
(7, 8, 8, 1, 11, b'0', b'0', 1, 'F'),
(8, 7, 7, 1, 2, b'0', b'1', 2, 'A1'),
(9, 7, 7, 3, 5, b'0', b'1', 2, 'A2'),
(10, 7, 7, 6, 11, b'0', b'1', 2, 'A3'),
(11, 8, 9, 1, 3, b'0', b'1', 2, 'B1'),
(12, 8, 9, 4, 7, b'0', b'1', 3, 'B2'),
(13, 8, 9, 8, 11, b'0', b'1', 4, 'B3'),
(14, 10, 11, 1, 3, b'0', b'1', 2, 'C1'),
(15, 10, 11, 4, 7, b'0', b'1', 3, 'C2'),
(16, 10, 11, 8, 10, b'0', b'1', 4, 'C3'),
(17, 10, 11, 11, 11, b'0', b'1', 10, 'C4'),
(18, 12, 13, 1, 3, b'0', b'1', 2, 'D1'),
(19, 12, 13, 4, 7, b'0', b'1', 3, 'D2'),
(20, 12, 13, 8, 10, b'0', b'1', 6, 'D3'),
(21, 12, 13, 11, 11, b'0', b'1', 10, 'D4'),
(22, 14, 15, 1, 3, b'0', b'1', 2, 'E1'),
(23, 14, 15, 4, 7, b'0', b'1', 3, 'E2'),
(24, 14, 15, 8, 10, b'0', b'1', 6, 'E3'),
(25, 14, 15, 11, 11, b'0', b'1', 10, 'E4'),
(26, 16, 18, 1, 3, b'0', b'0', 2, 'A1'),
(27, 16, 18, 4, 7, b'0', b'0', 3, 'A2'),
(28, 16, 18, 8, 11, b'0', b'0', 5, 'A3'),
(29, 16, 18, 12, 19, b'0', b'0', 10, 'A4'),
(30, 19, 30, 1, 3, b'0', b'0', 2, 'B1'),
(31, 19, 30, 4, 7, b'0', b'0', 3, 'B2'),
(32, 19, 30, 8, 11, b'0', b'0', 5, 'B3'),
(33, 19, 30, 12, 19, b'0', b'0', 10, 'B4'),
(34, 31, 40, 1, 3, b'0', b'0', 2, 'C1'),
(35, 31, 40, 4, 7, b'0', b'0', 3, 'C2'),
(36, 31, 40, 8, 11, b'0', b'0', 5, 'C3'),
(37, 31, 40, 12, 19, b'0', b'0', 10, 'C4'),
(38, 41, 50, 1, 3, b'0', b'0', 2, 'D1'),
(39, 41, 50, 4, 7, b'0', b'0', 3, 'D2'),
(40, 41, 50, 8, 11, b'0', b'0', 5, 'D3'),
(41, 41, 50, 12, 19, b'0', b'0', 10, 'D4'),
(42, 51, 99, 1, 3, b'0', b'0', 2, 'E1'),
(43, 51, 99, 4, 7, b'0', b'0', 3, 'E2'),
(44, 51, 99, 8, 11, b'0', b'0', 5, 'E3'),
(45, 51, 99, 12, 19, b'0', b'0', 10, 'E4');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cinturon`
--

CREATE TABLE `cinturon` (
  `id` int(11) NOT NULL,
  `categoria` varchar(50) NOT NULL,
  `color` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cinturon`
--

INSERT INTO `cinturon` (`id`, `categoria`, `color`) VALUES
(1, '10º KUP', 'Blanco'),
(2, '9º KUP', 'Amarillo'),
(3, '8º KUP', 'Amarillo Naranja'),
(4, '7º KUP', 'Naranja'),
(5, '6º KUP', 'Naranja Verde'),
(6, '5º KUP', 'Verde'),
(7, '4º KUP', 'Verde Azul'),
(8, '3º KUP', 'Azul'),
(9, '2º KUP', 'Azul Rojo'),
(10, '1º KUP', 'Rojo'),
(11, 'POOM', 'Rojo Negro'),
(12, '1º DAN', 'Negro 1º DAN'),
(13, '2º DAN', 'Negro 2º DAN'),
(14, '3º DAN', 'Negro 3º DAN'),
(15, '4º DAN', 'Negro 4º DAN'),
(16, '5º DAN', 'Negro 5º DAN'),
(17, '6º DAN', 'Negro 6º DAN'),
(18, '7º DAN', 'Negro 7º DAN'),
(19, '8º DAN', 'Negro 8º DAN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gimnasio`
--

CREATE TABLE `gimnasio` (
  `id` int(11) NOT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `gimnasio`
--

INSERT INTO `gimnasio` (`id`, `direccion`, `nombre`) VALUES
(1, 'Avenida Viñuelas 30 - Tres Cantos', 'Champion Do');

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
(37);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion`
--

CREATE TABLE `inscripcion` (
  `id` int(11) NOT NULL,
  `inscripcion_propia` bit(1) NOT NULL,
  `inscripcion_menor` bit(1) NOT NULL,
  `inscripcion_inclusiva` bit(1) NOT NULL,
  `fecha_inscripcion` datetime NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `nombre_inscripto` varchar(60) NOT NULL,
  `apellido1inscripto` varchar(60) NOT NULL,
  `apellido2inscripto` varchar(60) DEFAULT NULL,
  `dni_inscripto` varchar(45) DEFAULT NULL,
  `fecha_nacimiento_inscripto` datetime NOT NULL,
  `sexo_inscripto` varchar(9) NOT NULL,
  `domicilio_calle_inscripto` varchar(100) DEFAULT NULL,
  `domicilio_numero_inscripto` varchar(30) DEFAULT NULL,
  `domicilio_otros_inscripto` varchar(50) DEFAULT NULL,
  `domicilio_localidad_inscripto` varchar(50) DEFAULT NULL,
  `domicilio_cp_inscripto` varchar(10) DEFAULT NULL,
  `id_gimnasio` int(11) NOT NULL,
  `id_pais` int(11) NOT NULL,
  `id_cinturon` int(11) NOT NULL,
  `nombre_autorizador` varchar(60) DEFAULT NULL,
  `apellido1autorizador` varchar(60) DEFAULT NULL,
  `apellido2autorizador` varchar(60) DEFAULT NULL,
  `dni_autorizador` varchar(45) DEFAULT NULL,
  `id_calidad` int(11) NOT NULL,
  `domicilio_calle_autorizador` varchar(100) DEFAULT NULL,
  `domicilio_numero_autorizador` varchar(30) DEFAULT NULL,
  `domicilio_otros_autorizador` varchar(50) DEFAULT NULL,
  `domicilio_localidad_autorizador` varchar(50) DEFAULT NULL,
  `domicilio_cp_autorizador` varchar(10) DEFAULT NULL,
  `envio_justificante_email` bit(1) NOT NULL,
  `pago_realizado` bit(1) NOT NULL,
  `fecha_pago` datetime DEFAULT NULL,
  `notas` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inscripcion`
--

INSERT INTO `inscripcion` (`id`, `inscripcion_propia`, `inscripcion_menor`, `inscripcion_inclusiva`, `fecha_inscripcion`, `id_categoria`, `nombre_inscripto`, `apellido1inscripto`, `apellido2inscripto`, `dni_inscripto`, `fecha_nacimiento_inscripto`, `sexo_inscripto`, `domicilio_calle_inscripto`, `domicilio_numero_inscripto`, `domicilio_otros_inscripto`, `domicilio_localidad_inscripto`, `domicilio_cp_inscripto`, `id_gimnasio`, `id_pais`, `id_cinturon`, `nombre_autorizador`, `apellido1autorizador`, `apellido2autorizador`, `dni_autorizador`, `id_calidad`, `domicilio_calle_autorizador`, `domicilio_numero_autorizador`, `domicilio_otros_autorizador`, `domicilio_localidad_autorizador`, `domicilio_cp_autorizador`, `envio_justificante_email`, `pago_realizado`, `fecha_pago`, `notas`) VALUES
(36, b'1', b'0', b'0', '2022-11-01 18:58:20', 43, 'Claudia', 'Suárez', 'Rodríguez', '22222222A', '1970-11-25 00:00:00', 'Femenino', 'Avenida Montes de Oca', '284', 'pasillo 23 escalera A', 'San Sebastián de los Reyes', '28456', 1, 1, 4, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, b'0', b'0', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pais`
--

CREATE TABLE `pais` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pais`
--

INSERT INTO `pais` (`id`, `nombre`) VALUES
(1, 'España'),
(2, 'Francia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poomsae`
--

CREATE TABLE `poomsae` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `poomsae`
--

INSERT INTO `poomsae` (`id`, `nombre`) VALUES
(1, '1º KICHO'),
(2, '1º POOMSAE'),
(3, '2º POOMSAE'),
(4, '3º POOMSAE'),
(5, '4º POOMSAE'),
(6, '5º POOMSAE'),
(7, '6º POOMSAE'),
(8, '7º POOMSAE'),
(9, '8º POOMSAE'),
(10, 'KORYO');

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
('11111111A', b'1', 'Pérez', 'José', '$2a$10$10qmPm2ocWlplp5LRnPH3uy6P8YHEBWKvg4hFnbVanHd1golw92oe', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, b'0', NULL, NULL, NULL, NULL, b'0'),
('11111111A-01', b'1', 'Pérez', 'Maria', '$2a$10$10qmPm2ocWlplp5LRnPH3uy6P8YHEBWKvg4hFnbVanHd1golw92oe', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, b'0', NULL, NULL, NULL, NULL, b'0'),
('22222222A', b'1', 'Acosta', 'Alberto Federico', '$2a$10$E0RzlX8K4217LX6dVAkwxuIx9wIokLH1lrKqb84S6deG8DvjUI8mW', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, b'0', NULL, NULL, NULL, NULL, b'0');

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
(1, 'ROLE_ADMIN', '11111111A'),
(2, 'ROLE_USER', '22222222A');

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
('campeonato.direccion', 'Polideportivo de la luz - Av. de la luz s/n (Tres Cantos)'),
('campeonato.fecha', '10-12-2022');

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
