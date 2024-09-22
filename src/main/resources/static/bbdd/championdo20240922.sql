-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-09-2024 a las 21:17:02
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

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `codigo_gimnasio`, `position`, `edad_inicio`, `edad_fin`, `position_cinturon_inicio`, `position_cinturon_fin`, `inclusivo`, `preinfantil`, `infantil`, `adulto`, `id_cinturon_fin`, `id_cinturon_inicio`, `id_poomsae`) VALUES
(57, 'INCLUSIVO', 7, 0, 0, 0, 0, 28, b'1', b'0', b'0', b'0', 37, 9, 56),
(58, 'A', 7, 1, 3, 3, 0, 18, b'0', b'1', b'0', b'0', 27, 9, 38),
(59, 'B', 7, 2, 4, 4, 0, 18, b'0', b'1', b'0', b'0', 27, 9, 38),
(60, 'C', 7, 3, 5, 5, 0, 18, b'0', b'1', b'0', b'0', 27, 9, 38),
(61, 'D', 7, 4, 6, 6, 0, 18, b'0', b'1', b'0', b'0', 27, 9, 38),
(62, 'E', 7, 5, 7, 7, 0, 18, b'0', b'1', b'0', b'0', 27, 9, 38),
(63, 'F', 7, 6, 8, 8, 0, 18, b'0', b'1', b'0', b'0', 27, 9, 38),
(64, 'A1', 7, 7, 7, 7, 0, 2, b'0', b'0', b'1', b'0', 11, 9, 39),
(65, 'A2', 7, 8, 7, 7, 3, 5, b'0', b'0', b'1', b'0', 14, 12, 39),
(66, 'A3', 7, 9, 7, 7, 6, 18, b'0', b'0', b'1', b'0', 27, 15, 39),
(67, 'B1', 7, 10, 8, 9, 0, 3, b'0', b'0', b'1', b'0', 12, 9, 39),
(68, 'B2', 7, 11, 8, 9, 4, 7, b'0', b'0', b'1', b'0', 16, 13, 40),
(69, 'B3', 7, 12, 8, 9, 8, 18, b'0', b'0', b'1', b'0', 27, 17, 41),
(70, 'C1', 7, 13, 10, 11, 0, 3, b'0', b'0', b'1', b'0', 12, 9, 39),
(71, 'C2', 7, 14, 10, 11, 4, 7, b'0', b'0', b'1', b'0', 16, 13, 40),
(72, 'C3', 7, 15, 10, 11, 8, 13, b'0', b'0', b'1', b'0', 22, 17, 41),
(73, 'C4', 7, 16, 10, 11, 14, 18, b'0', b'0', b'1', b'0', 27, 23, 47),
(74, 'D1', 7, 17, 12, 13, 0, 3, b'0', b'0', b'1', b'0', 12, 9, 39),
(75, 'D2', 7, 18, 12, 13, 4, 7, b'0', b'0', b'1', b'0', 16, 13, 40),
(76, 'D3', 7, 19, 12, 13, 8, 13, b'0', b'0', b'1', b'0', 22, 17, 43),
(77, 'D4', 7, 20, 12, 13, 14, 18, b'0', b'0', b'1', b'0', 27, 23, 47),
(78, 'E1', 7, 21, 14, 15, 0, 3, b'0', b'0', b'1', b'0', 12, 9, 39),
(79, 'E2', 7, 22, 14, 15, 4, 7, b'0', b'0', b'1', b'0', 16, 13, 40),
(80, 'E3', 7, 23, 14, 15, 8, 13, b'0', b'0', b'1', b'0', 22, 17, 43),
(81, 'E4', 7, 24, 14, 15, 14, 18, b'0', b'0', b'1', b'0', 27, 23, 47),
(82, 'A1', 7, 25, 16, 18, 0, 2, b'0', b'0', b'0', b'1', 11, 9, 39),
(83, 'A2', 7, 26, 16, 18, 4, 6, b'0', b'0', b'0', b'1', 15, 13, 40),
(84, 'A3', 7, 27, 16, 18, 8, 13, b'0', b'0', b'0', b'1', 22, 17, 42),
(85, 'A4', 7, 28, 16, 18, 19, 28, b'0', b'0', b'0', b'1', 37, 28, 47),
(86, 'B1', 7, 29, 19, 30, 0, 2, b'0', b'0', b'0', b'1', 11, 9, 39),
(87, 'B2', 7, 30, 19, 30, 4, 6, b'0', b'0', b'0', b'1', 15, 13, 40),
(88, 'B3', 7, 31, 19, 30, 8, 13, b'0', b'0', b'0', b'1', 22, 17, 42),
(89, 'B4', 7, 32, 19, 30, 19, 28, b'0', b'0', b'0', b'1', 37, 28, 47),
(90, 'C1', 7, 33, 31, 40, 0, 2, b'0', b'0', b'0', b'1', 11, 9, 39),
(91, 'C2', 7, 34, 31, 40, 4, 6, b'0', b'0', b'0', b'1', 15, 13, 40),
(92, 'C3', 7, 35, 31, 40, 8, 13, b'0', b'0', b'0', b'1', 22, 17, 42),
(93, 'C4', 7, 36, 31, 40, 19, 28, b'0', b'0', b'0', b'1', 37, 28, 47),
(94, 'D1', 7, 37, 41, 50, 0, 2, b'0', b'0', b'0', b'1', 11, 9, 39),
(95, 'D2', 7, 38, 41, 50, 4, 6, b'0', b'0', b'0', b'1', 15, 13, 40),
(96, 'D3', 7, 39, 41, 50, 8, 13, b'0', b'0', b'0', b'1', 22, 17, 42),
(97, 'D4', 7, 40, 41, 50, 19, 28, b'0', b'0', b'0', b'1', 37, 28, 47),
(98, 'E1', 7, 41, 51, 99, 0, 2, b'0', b'0', b'0', b'1', 11, 9, 39),
(99, 'E2', 7, 42, 51, 99, 4, 6, b'0', b'0', b'0', b'1', 15, 13, 40),
(100, 'E3', 7, 43, 51, 99, 8, 13, b'0', b'0', b'0', b'1', 22, 17, 42),
(101, 'E4', 7, 44, 51, 99, 19, 28, b'0', b'0', b'0', b'1', 37, 28, 47),
(152, 'INCLUSIVO', 102, 0, 0, 0, 0, 28, b'1', b'0', b'0', b'0', 132, 104, 151),
(153, 'A', 102, 1, 3, 3, 0, 18, b'0', b'1', b'0', b'0', 122, 104, 133),
(154, 'B', 102, 2, 4, 4, 0, 18, b'0', b'1', b'0', b'0', 122, 104, 133),
(155, 'C', 102, 3, 5, 5, 0, 18, b'0', b'1', b'0', b'0', 122, 104, 133),
(156, 'D', 102, 4, 6, 6, 0, 18, b'0', b'1', b'0', b'0', 122, 104, 133),
(157, 'E', 102, 5, 7, 7, 0, 18, b'0', b'1', b'0', b'0', 122, 104, 133),
(158, 'F', 102, 6, 8, 8, 0, 18, b'0', b'1', b'0', b'0', 122, 104, 133),
(159, 'A1', 102, 7, 7, 7, 0, 2, b'0', b'0', b'1', b'0', 106, 104, 134),
(160, 'A2', 102, 8, 7, 7, 3, 5, b'0', b'0', b'1', b'0', 109, 107, 134),
(161, 'A3', 102, 9, 7, 7, 6, 18, b'0', b'0', b'1', b'0', 122, 110, 134),
(162, 'B1', 102, 10, 8, 9, 0, 3, b'0', b'0', b'1', b'0', 107, 104, 134),
(163, 'B2', 102, 11, 8, 9, 4, 7, b'0', b'0', b'1', b'0', 111, 108, 135),
(164, 'B3', 102, 12, 8, 9, 8, 18, b'0', b'0', b'1', b'0', 122, 112, 136),
(165, 'C1', 102, 13, 10, 11, 0, 3, b'0', b'0', b'1', b'0', 107, 104, 134),
(166, 'C2', 102, 14, 10, 11, 4, 7, b'0', b'0', b'1', b'0', 111, 108, 135),
(167, 'C3', 102, 15, 10, 11, 8, 13, b'0', b'0', b'1', b'0', 117, 112, 136),
(168, 'C4', 102, 16, 10, 11, 14, 18, b'0', b'0', b'1', b'0', 122, 118, 142),
(169, 'D1', 102, 17, 12, 13, 0, 3, b'0', b'0', b'1', b'0', 107, 104, 134),
(170, 'D2', 102, 18, 12, 13, 4, 7, b'0', b'0', b'1', b'0', 111, 108, 135),
(171, 'D3', 102, 19, 12, 13, 8, 13, b'0', b'0', b'1', b'0', 117, 112, 138),
(172, 'D4', 102, 20, 12, 13, 14, 18, b'0', b'0', b'1', b'0', 122, 118, 142),
(173, 'E1', 102, 21, 14, 15, 0, 3, b'0', b'0', b'1', b'0', 107, 104, 134),
(174, 'E2', 102, 22, 14, 15, 4, 7, b'0', b'0', b'1', b'0', 111, 108, 135),
(175, 'E3', 102, 23, 14, 15, 8, 13, b'0', b'0', b'1', b'0', 117, 112, 138),
(176, 'E4', 102, 24, 14, 15, 14, 18, b'0', b'0', b'1', b'0', 122, 118, 142),
(177, 'A1', 102, 25, 16, 18, 0, 2, b'0', b'0', b'0', b'1', 106, 104, 134),
(178, 'A2', 102, 26, 16, 18, 4, 6, b'0', b'0', b'0', b'1', 110, 108, 135),
(179, 'A3', 102, 27, 16, 18, 8, 13, b'0', b'0', b'0', b'1', 117, 112, 137),
(180, 'A4', 102, 28, 16, 18, 19, 28, b'0', b'0', b'0', b'1', 132, 123, 142),
(181, 'B1', 102, 29, 19, 30, 0, 2, b'0', b'0', b'0', b'1', 106, 104, 134),
(182, 'B2', 102, 30, 19, 30, 4, 6, b'0', b'0', b'0', b'1', 110, 108, 135),
(183, 'B3', 102, 31, 19, 30, 8, 13, b'0', b'0', b'0', b'1', 117, 112, 137),
(184, 'B4', 102, 32, 19, 30, 19, 28, b'0', b'0', b'0', b'1', 132, 123, 142),
(185, 'C1', 102, 33, 31, 40, 0, 2, b'0', b'0', b'0', b'1', 106, 104, 134),
(186, 'C2', 102, 34, 31, 40, 4, 6, b'0', b'0', b'0', b'1', 110, 108, 135),
(187, 'C3', 102, 35, 31, 40, 8, 13, b'0', b'0', b'0', b'1', 117, 112, 137),
(188, 'C4', 102, 36, 31, 40, 19, 28, b'0', b'0', b'0', b'1', 132, 123, 142),
(189, 'D1', 102, 37, 41, 50, 0, 2, b'0', b'0', b'0', b'1', 106, 104, 134),
(190, 'D2', 102, 38, 41, 50, 4, 6, b'0', b'0', b'0', b'1', 110, 108, 135),
(191, 'D3', 102, 39, 41, 50, 8, 13, b'0', b'0', b'0', b'1', 117, 112, 137),
(192, 'D4', 102, 40, 41, 50, 19, 28, b'0', b'0', b'0', b'1', 132, 123, 142),
(193, 'E1', 102, 41, 51, 99, 0, 2, b'0', b'0', b'0', b'1', 106, 104, 134),
(194, 'E2', 102, 42, 51, 99, 4, 6, b'0', b'0', b'0', b'1', 110, 108, 135),
(195, 'E3', 102, 43, 51, 99, 8, 13, b'0', b'0', b'0', b'1', 117, 112, 137),
(196, 'E4', 102, 44, 51, 99, 19, 28, b'0', b'0', b'0', b'1', 132, 123, 142),
(435, 'INCLUSIVO', 385, 0, 0, 0, 0, 26, b'1', b'0', b'0', b'0', 415, 387, 434),
(436, 'A', 385, 1, 3, 3, 0, 18, b'0', b'1', b'0', b'0', 405, 387, 416),
(437, 'B', 385, 2, 4, 4, 0, 18, b'0', b'1', b'0', b'0', 405, 387, 416),
(438, 'C', 385, 3, 5, 5, 0, 18, b'0', b'1', b'0', b'0', 405, 387, 416),
(439, 'D', 385, 4, 6, 6, 0, 18, b'0', b'1', b'0', b'0', 405, 387, 416),
(440, 'E', 385, 5, 7, 7, 0, 18, b'0', b'1', b'0', b'0', 405, 387, 416),
(441, 'F', 385, 6, 8, 8, 0, 18, b'0', b'1', b'0', b'0', 405, 387, 416),
(442, 'A1', 385, 7, 7, 7, 0, 2, b'0', b'0', b'1', b'0', 389, 387, 417),
(443, 'A2', 385, 8, 7, 7, 3, 5, b'0', b'0', b'1', b'0', 392, 390, 417),
(444, 'A3', 385, 9, 7, 7, 6, 18, b'0', b'0', b'1', b'0', 405, 393, 417),
(445, 'B1', 385, 10, 8, 9, 0, 3, b'0', b'0', b'1', b'0', 390, 387, 417),
(446, 'B2', 385, 11, 8, 9, 4, 7, b'0', b'0', b'1', b'0', 394, 391, 418),
(447, 'B3', 385, 12, 8, 9, 8, 18, b'0', b'0', b'1', b'0', 405, 395, 419),
(448, 'C1', 385, 13, 10, 11, 0, 3, b'0', b'0', b'1', b'0', 390, 387, 417),
(449, 'C2', 385, 14, 10, 11, 4, 7, b'0', b'0', b'1', b'0', 394, 391, 418),
(450, 'C3', 385, 15, 10, 11, 8, 13, b'0', b'0', b'1', b'0', 400, 395, 419),
(451, 'C4', 385, 16, 10, 11, 14, 18, b'0', b'0', b'1', b'0', 405, 401, 425),
(452, 'D1', 385, 17, 12, 13, 0, 3, b'0', b'0', b'1', b'0', 390, 387, 417),
(453, 'D2', 385, 18, 12, 13, 4, 7, b'0', b'0', b'1', b'0', 394, 391, 418),
(454, 'D3', 385, 19, 12, 13, 8, 13, b'0', b'0', b'1', b'0', 400, 395, 421),
(455, 'D4', 385, 20, 12, 13, 14, 18, b'0', b'0', b'1', b'0', 405, 401, 425),
(456, 'E1', 385, 21, 14, 15, 0, 3, b'0', b'0', b'1', b'0', 390, 387, 417),
(457, 'E2', 385, 22, 14, 15, 4, 7, b'0', b'0', b'1', b'0', 394, 391, 418),
(458, 'E3', 385, 23, 14, 15, 8, 13, b'0', b'0', b'1', b'0', 400, 395, 421),
(459, 'E4', 385, 24, 14, 15, 14, 18, b'0', b'0', b'1', b'0', 405, 401, 425),
(460, 'A1', 385, 25, 16, 18, 0, 2, b'0', b'0', b'0', b'1', 389, 387, 417),
(461, 'A2', 385, 26, 16, 18, 4, 6, b'0', b'0', b'0', b'1', 393, 391, 418),
(462, 'A3', 385, 27, 16, 18, 8, 13, b'0', b'0', b'0', b'1', 400, 395, 420),
(463, 'A4', 385, 28, 16, 18, 19, 26, b'0', b'0', b'0', b'1', 415, 406, 425),
(464, 'B1', 385, 29, 19, 30, 0, 2, b'0', b'0', b'0', b'1', 389, 387, 417),
(465, 'B2', 385, 30, 19, 30, 4, 6, b'0', b'0', b'0', b'1', 393, 391, 418),
(466, 'B3', 385, 31, 19, 30, 8, 13, b'0', b'0', b'0', b'1', 400, 395, 420),
(467, 'B4', 385, 32, 19, 30, 19, 26, b'0', b'0', b'0', b'1', 415, 406, 425),
(468, 'C1', 385, 33, 31, 40, 0, 2, b'0', b'0', b'0', b'1', 389, 387, 417),
(469, 'C2', 385, 34, 31, 40, 4, 6, b'0', b'0', b'0', b'1', 393, 391, 418),
(470, 'C3', 385, 35, 31, 40, 8, 13, b'0', b'0', b'0', b'1', 400, 395, 420),
(471, 'C4', 385, 36, 31, 40, 19, 26, b'0', b'0', b'0', b'1', 415, 406, 425),
(472, 'D1', 385, 37, 41, 50, 0, 2, b'0', b'0', b'0', b'1', 389, 387, 417),
(473, 'D2', 385, 38, 41, 50, 4, 6, b'0', b'0', b'0', b'1', 393, 391, 418),
(474, 'D3', 385, 39, 41, 50, 8, 13, b'0', b'0', b'0', b'1', 400, 395, 420),
(475, 'D4', 385, 40, 41, 50, 19, 26, b'0', b'0', b'0', b'1', 415, 406, 425),
(476, 'E1', 385, 41, 51, 99, 0, 2, b'0', b'0', b'0', b'1', 389, 387, 417),
(477, 'E2', 385, 42, 51, 99, 4, 6, b'0', b'0', b'0', b'1', 393, 391, 418),
(478, 'E3', 385, 43, 51, 99, 8, 13, b'0', b'0', b'0', b'1', 400, 395, 420),
(479, 'E4', 385, 44, 51, 99, 19, 26, b'0', b'0', b'0', b'1', 415, 406, 425),
(533, 'INCLUSIVO', 483, 0, 0, 0, 0, 28, b'1', b'0', b'0', b'0', 513, 485, 532),
(534, 'A', 483, 1, 3, 3, 0, 18, b'0', b'1', b'0', b'0', 503, 485, 514),
(535, 'B', 483, 2, 4, 4, 0, 18, b'0', b'1', b'0', b'0', 503, 485, 514),
(536, 'C', 483, 3, 5, 5, 0, 18, b'0', b'1', b'0', b'0', 503, 485, 514),
(537, 'D', 483, 4, 6, 6, 0, 18, b'0', b'1', b'0', b'0', 503, 485, 514),
(538, 'E', 483, 5, 7, 7, 0, 18, b'0', b'1', b'0', b'0', 503, 485, 514),
(539, 'F', 483, 6, 8, 8, 0, 18, b'0', b'1', b'0', b'0', 503, 485, 514),
(540, 'A1', 483, 7, 7, 7, 0, 2, b'0', b'0', b'1', b'0', 487, 485, 515),
(541, 'A2', 483, 8, 7, 7, 3, 5, b'0', b'0', b'1', b'0', 490, 488, 515),
(542, 'A3', 483, 9, 7, 7, 6, 18, b'0', b'0', b'1', b'0', 503, 491, 515),
(543, 'B1', 483, 10, 8, 9, 0, 3, b'0', b'0', b'1', b'0', 488, 485, 515),
(544, 'B2', 483, 11, 8, 9, 4, 7, b'0', b'0', b'1', b'0', 492, 489, 516),
(545, 'B3', 483, 12, 8, 9, 8, 18, b'0', b'0', b'1', b'0', 503, 493, 517),
(546, 'C1', 483, 13, 10, 11, 0, 3, b'0', b'0', b'1', b'0', 488, 485, 515),
(547, 'C2', 483, 14, 10, 11, 4, 7, b'0', b'0', b'1', b'0', 492, 489, 516),
(548, 'C3', 483, 15, 10, 11, 8, 13, b'0', b'0', b'1', b'0', 498, 493, 517),
(549, 'C4', 483, 16, 10, 11, 14, 18, b'0', b'0', b'1', b'0', 503, 499, 523),
(550, 'D1', 483, 17, 12, 13, 0, 3, b'0', b'0', b'1', b'0', 488, 485, 515),
(551, 'D2', 483, 18, 12, 13, 4, 7, b'0', b'0', b'1', b'0', 492, 489, 516),
(552, 'D3', 483, 19, 12, 13, 8, 13, b'0', b'0', b'1', b'0', 498, 493, 519),
(553, 'D4', 483, 20, 12, 13, 14, 18, b'0', b'0', b'1', b'0', 503, 499, 523),
(554, 'E1', 483, 21, 14, 15, 0, 3, b'0', b'0', b'1', b'0', 488, 485, 515),
(555, 'E2', 483, 22, 14, 15, 4, 7, b'0', b'0', b'1', b'0', 492, 489, 516),
(556, 'E3', 483, 23, 14, 15, 8, 13, b'0', b'0', b'1', b'0', 498, 493, 519),
(557, 'E4', 483, 24, 14, 15, 14, 18, b'0', b'0', b'1', b'0', 503, 499, 523),
(558, 'A1', 483, 25, 16, 18, 0, 2, b'0', b'0', b'0', b'1', 487, 485, 515),
(559, 'A2', 483, 26, 16, 18, 4, 6, b'0', b'0', b'0', b'1', 491, 489, 516),
(560, 'A3', 483, 27, 16, 18, 8, 13, b'0', b'0', b'0', b'1', 498, 493, 518),
(561, 'A4', 483, 28, 16, 18, 19, 28, b'0', b'0', b'0', b'1', 513, 504, 523),
(562, 'B1', 483, 29, 19, 30, 0, 2, b'0', b'0', b'0', b'1', 487, 485, 515),
(563, 'B2', 483, 30, 19, 30, 4, 6, b'0', b'0', b'0', b'1', 491, 489, 516),
(564, 'B3', 483, 31, 19, 30, 8, 13, b'0', b'0', b'0', b'1', 498, 493, 518),
(565, 'B4', 483, 32, 19, 30, 19, 28, b'0', b'0', b'0', b'1', 513, 504, 523),
(566, 'C1', 483, 33, 31, 40, 0, 2, b'0', b'0', b'0', b'1', 487, 485, 515),
(567, 'C2', 483, 34, 31, 40, 4, 6, b'0', b'0', b'0', b'1', 491, 489, 516),
(568, 'C3', 483, 35, 31, 40, 8, 13, b'0', b'0', b'0', b'1', 498, 493, 518),
(569, 'C4', 483, 36, 31, 40, 19, 28, b'0', b'0', b'0', b'1', 513, 504, 523),
(570, 'D1', 483, 37, 41, 50, 0, 2, b'0', b'0', b'0', b'1', 487, 485, 515),
(571, 'D2', 483, 38, 41, 50, 4, 6, b'0', b'0', b'0', b'1', 491, 489, 516),
(572, 'D3', 483, 39, 41, 50, 8, 13, b'0', b'0', b'0', b'1', 498, 493, 518),
(573, 'D4', 483, 40, 41, 50, 19, 28, b'0', b'0', b'0', b'1', 513, 504, 523),
(574, 'E1', 483, 41, 51, 99, 0, 2, b'0', b'0', b'0', b'1', 487, 485, 515),
(575, 'E2', 483, 42, 51, 99, 4, 6, b'0', b'0', b'0', b'1', 491, 489, 516),
(576, 'E3', 483, 43, 51, 99, 8, 13, b'0', b'0', b'0', b'1', 498, 493, 518),
(577, 'E4', 483, 44, 51, 99, 19, 28, b'0', b'0', b'0', b'1', 513, 504, 523);

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
(1106);

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

--
-- Volcado de datos para la tabla `cinturon`
--

INSERT INTO `cinturon` (`id`, `color`, `position`, `codigo_gimnasio`) VALUES
(9, 'Blanco', 0, 7),
(10, 'Blanco Amarillo', 1, 7),
(11, 'Amarillo', 2, 7),
(12, 'Amarillo Naranja', 3, 7),
(13, 'Naranja', 4, 7),
(14, 'Naranja Verde', 5, 7),
(15, 'Verde', 6, 7),
(16, 'Verde Azul', 7, 7),
(17, 'Azul', 8, 7),
(18, 'Azul Rojo', 9, 7),
(22, 'Rojo', 10, 7),
(23, 'Rojo Negro', 11, 7),
(24, 'Rojo Negro 1º PUM', 12, 7),
(25, 'Rojo Negro 2º PUM', 13, 7),
(26, 'Rojo Negro 3º PUM', 14, 7),
(27, 'Rojo Negro 4º PUM', 15, 7),
(28, 'Negro 1º DAN', 16, 7),
(29, 'Negro 2º DAN', 17, 7),
(30, 'Negro 3º DAN', 18, 7),
(31, 'Negro 4º DAN', 19, 7),
(32, 'Negro 5º DAN', 20, 7),
(33, 'Negro 6º DAN', 21, 7),
(34, 'Negro 7º DAN', 22, 7),
(35, 'Negro 8º DAN', 23, 7),
(36, 'Negro 9º DAN', 24, 7),
(37, 'Negro 10º DAN', 25, 7),
(104, 'Blanco', 0, 102),
(105, 'Blanco Amarillo', 1, 102),
(106, 'Amarillo', 2, 102),
(107, 'Amarillo Naranja', 3, 102),
(108, 'Naranja', 4, 102),
(109, 'Naranja Verde', 5, 102),
(110, 'Verde', 6, 102),
(111, 'Verde Azul', 7, 102),
(112, 'Azul', 8, 102),
(113, 'Azul Rojo', 9, 102),
(117, 'Rojo', 10, 102),
(118, 'Rojo Negro', 11, 102),
(119, 'Rojo Negro 1º PUM', 12, 102),
(120, 'Rojo Negro 2º PUM', 13, 102),
(121, 'Rojo Negro 3º PUM', 14, 102),
(122, 'Rojo Negro 4º PUM', 15, 102),
(123, 'Negro 1º DAN', 16, 102),
(124, 'Negro 2º DAN', 17, 102),
(125, 'Negro 3º DAN', 18, 102),
(126, 'Negro 4º DAN', 19, 102),
(127, 'Negro 5º DAN', 20, 102),
(128, 'Negro 6º DAN', 21, 102),
(129, 'Negro 7º DAN', 22, 102),
(130, 'Negro 8º DAN', 23, 102),
(131, 'Negro 9º DAN', 24, 102),
(132, 'Negro 10º DAN', 25, 102),
(387, 'Blanco', 0, 385),
(388, 'Blanco Amarillo', 1, 385),
(389, 'Amarillo', 2, 385),
(390, 'Amarillo Naranja', 3, 385),
(391, 'Naranja', 4, 385),
(392, 'Naranja Verde', 5, 385),
(393, 'Verde', 6, 385),
(394, 'Verde Azul', 7, 385),
(395, 'Azul', 8, 385),
(396, 'Azul Rojo', 9, 385),
(400, 'Rojo', 10, 385),
(401, 'Rojo Negro', 11, 385),
(402, 'Rojo Negro 1º PUM', 12, 385),
(403, 'Rojo Negro 2º PUM', 13, 385),
(404, 'Rojo Negro 3º PUM', 14, 385),
(405, 'Rojo Negro 4º PUM', 15, 385),
(406, 'Negro 1º DAN', 16, 385),
(407, 'Negro 2º DAN', 17, 385),
(408, 'Negro 3º DAN', 18, 385),
(409, 'Negro 4º DAN', 19, 385),
(410, 'Negro 5º DAN', 20, 385),
(411, 'Negro 6º DAN', 21, 385),
(412, 'Negro 7º DAN', 22, 385),
(413, 'Negro 8º DAN', 23, 385),
(414, 'Negro 9º DAN', 24, 385),
(415, 'Negro 10º DAN', 25, 385),
(485, 'Blanco', 0, 483),
(486, 'Blanco Amarillo', 1, 483),
(487, 'Amarillo', 2, 483),
(488, 'Amarillo Naranja', 3, 483),
(489, 'Naranja', 4, 483),
(490, 'Naranja Verde', 5, 483),
(491, 'Verde', 6, 483),
(492, 'Verde Azul', 7, 483),
(493, 'Azul', 8, 483),
(495, 'Azul Marrón', 9, 483),
(496, 'Marrón', 10, 483),
(497, 'Marrón Rojo', 11, 483),
(498, 'Rojo', 12, 483),
(499, 'Rojo Negro', 13, 483),
(500, 'Rojo Negro 1º PUM', 14, 483),
(501, 'Rojo Negro 2º PUM', 15, 483),
(502, 'Rojo Negro 3º PUM', 16, 483),
(503, 'Rojo Negro 4º PUM', 17, 483),
(504, 'Negro 1º DAN', 18, 483),
(505, 'Negro 2º DAN', 19, 483),
(506, 'Negro 3º DAN', 20, 483),
(507, 'Negro 4º DAN', 21, 483),
(508, 'Negro 5º DAN', 22, 483),
(509, 'Negro 6º DAN', 23, 483),
(510, 'Negro 7º DAN', 24, 483),
(511, 'Negro 8º DAN', 25, 483),
(512, 'Negro 9º DAN', 26, 483),
(513, 'Negro 10º DAN', 27, 483);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `document_manager`
--

CREATE TABLE `document_manager` (
  `id` int(11) NOT NULL,
  `creation_date` datetime DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  `extension` varchar(10) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `id_card` varchar(45) DEFAULT NULL,
  `id_gym` int(11) NOT NULL,
  `id_original_operative` int(11) NOT NULL,
  `name_gym` varchar(100) DEFAULT NULL,
  `needs_signature` bit(1) NOT NULL,
  `path` varchar(200) NOT NULL,
  `platform_document` bit(1) NOT NULL,
  `section` varchar(50) NOT NULL,
  `section_description` varchar(100) DEFAULT NULL,
  `signature` bit(1) NOT NULL,
  `signature_date` datetime DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `document_manager`
--

INSERT INTO `document_manager` (`id`, `creation_date`, `delete_date`, `extension`, `filename`, `id_card`, `id_gym`, `id_original_operative`, `name_gym`, `needs_signature`, `path`, `platform_document`, `section`, `section_description`, `signature`, `signature_date`, `name`) VALUES
(1010, '2024-03-27 18:47:36', '2024-03-27 18:54:26', '.pdf', 'autorizacionMayor1885234654N-544', '85234654N', 0, 544, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'autorizacionMayor18', NULL, b'0', NULL, NULL),
(1012, '2024-03-27 18:47:36', '2024-03-27 18:54:26', '.pdf', 'whatsapp85234654N-544', '85234654N', 0, 544, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'whatsapp', NULL, b'0', NULL, NULL),
(1013, '2024-03-27 18:48:02', '2024-03-27 18:54:26', '.pdf', 'normativaSEPAFirmado85234654N-544', '85234654N', 0, 544, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'normativaSEPAFirmado', NULL, b'0', NULL, NULL),
(1014, '2024-03-27 18:49:13', '2024-03-27 18:54:32', '.pdf', 'autorizacionMenor1885234654N-12345678A-547', '85234654N', 0, 547, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'autorizacionMenor18', NULL, b'0', NULL, NULL),
(1016, '2024-03-27 18:49:13', '2024-03-27 18:54:32', '.pdf', 'whatsapp85234654N-12345678A-547', '85234654N', 0, 547, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'whatsapp', NULL, b'0', NULL, NULL),
(1017, '2024-03-27 18:50:11', '2024-03-27 18:54:32', '.pdf', 'normativaSEPAFirmado85234654N-12345678A-547', '85234654N', 0, 547, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'normativaSEPAFirmado', NULL, b'0', NULL, NULL),
(1018, '2024-03-27 18:50:28', '2024-03-27 18:54:44', '.pdf', 'torneo85234654N-550', '85234654N', 0, 550, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'torneo', NULL, b'0', NULL, NULL),
(1019, '2024-03-27 18:51:11', '2024-03-27 18:54:50', '.pdf', 'torneo85234654N-12345678A-551', '85234654N', 0, 551, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'torneo', NULL, b'0', NULL, NULL),
(1020, '2024-03-27 18:52:11', '2024-03-27 18:54:56', '.pdf', 'torneo85234654N-01010101B-552', '85234654N', 0, 552, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'torneo', NULL, b'0', NULL, NULL),
(1024, '2024-03-28 15:12:12', '2024-03-28 15:34:13', '.pdf', 'mandato85234654N-562', '85234654N', 0, 562, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'mandato', NULL, b'0', NULL, NULL),
(1025, '2024-03-28 15:17:56', '2024-03-28 15:34:07', '.pdf', 'mandato85234654N-565', '85234654N', 0, 565, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'mandato', NULL, b'0', NULL, NULL),
(1026, '2024-03-28 15:21:06', '2024-03-28 15:25:25', '.pdf', 'mandato85234654N-01010101A-568', '85234654N', 0, 568, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'mandato', NULL, b'0', NULL, NULL),
(1027, '2024-03-28 15:21:42', '2024-03-28 15:24:59', '.pdf', 'mandato85234654N-571', '85234654N', 0, 571, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'mandato', NULL, b'0', NULL, NULL),
(1028, '2024-03-28 15:36:09', '2024-03-28 15:56:54', '.pdf', 'mandato85234654N-577', '85234654N', 0, 577, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'mandato', NULL, b'0', NULL, NULL),
(1029, '2024-03-28 15:55:07', '2024-03-28 15:56:33', '.pdf', 'autorizacionMayor1885234654N-580', '85234654N', 0, 580, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'autorizacionMayor18', NULL, b'1', '2024-03-28 15:55:27', NULL),
(1030, '2024-03-28 15:57:54', '2024-03-28 16:26:42', '.pdf', 'torneo85234654N-583', '85234654N', 0, 583, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'torneo', NULL, b'0', NULL, NULL),
(1031, '2024-03-28 16:21:53', '2024-03-28 17:06:20', '.pdf', 'autorizacionMayor1885234654N-584', '85234654N', 0, 584, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'autorizacionMayor18', NULL, b'1', '2024-03-28 16:21:53', NULL),
(1033, '2024-03-28 16:21:53', '2024-03-28 17:06:20', '.pdf', 'whatsapp85234654N-584', '85234654N', 0, 584, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'whatsapp', NULL, b'1', '2024-03-28 16:21:53', NULL),
(1034, '2024-03-28 16:22:58', '2024-03-28 17:06:15', '.pdf', 'autorizacionMayor1885234654N-588', '85234654N', 0, 588, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'autorizacionMayor18', NULL, b'1', '2024-03-28 16:22:58', NULL),
(1035, '2024-03-28 16:22:58', '2024-03-28 17:06:15', '.pdf', 'whatsapp85234654N-588', '85234654N', 0, 588, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'whatsapp', NULL, b'1', '2024-03-28 16:22:58', NULL),
(1036, '2024-03-28 16:25:14', '2024-03-28 17:06:09', '.pdf', 'autorizacionMenor1885234654N-591', '85234654N', 0, 591, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'autorizacionMenor18', NULL, b'1', '2024-03-28 16:25:14', NULL),
(1037, '2024-03-28 16:25:41', '2024-03-28 16:26:35', '.pdf', 'torneo85234654N-594', '85234654N', 0, 594, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'torneo', NULL, b'0', NULL, NULL),
(1038, '2024-03-28 16:27:28', '2024-03-28 17:06:55', '.pdf', 'torneo85234654N-595', '85234654N', 0, 595, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'torneo', NULL, b'0', NULL, NULL),
(1039, '2024-03-28 16:28:07', '2024-03-28 17:06:50', '.pdf', 'torneo85234654N-596', '85234654N', 0, 596, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'torneo', NULL, b'0', NULL, NULL),
(1040, '2024-03-28 16:33:48', '2024-03-28 17:06:44', '.pdf', 'torneo85234654N-597', '85234654N', 0, 597, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'torneo', NULL, b'0', NULL, NULL),
(1041, '2024-03-28 16:34:42', '2024-03-28 17:06:39', '.pdf', 'torneo85234654N-12345678A-598', '85234654N', 0, 598, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'torneo', NULL, b'0', NULL, NULL),
(1042, '2024-03-28 16:35:32', '2024-03-28 17:06:33', '.pdf', 'torneo85234654N-01010101B-599', '85234654N', 0, 599, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'torneo', NULL, b'0', NULL, NULL),
(1043, '2024-03-28 16:37:08', '2024-03-28 17:07:01', '.pdf', 'mandato85234654N-600', '85234654N', 0, 600, NULL, b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'mandato', NULL, b'1', '2024-03-28 16:37:08', NULL),
(1044, '2024-03-28 16:39:04', '2024-03-28 17:06:20', '.pdf', 'normativaSEPAFirmado85234654N-584', '85234654N', 0, 584, NULL, b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'normativaSEPAFirmado', NULL, b'0', NULL, NULL),
(1045, '2024-03-28 17:04:21', '2024-03-28 17:06:27', '.pdf', 'torneo85234654N-603', '85234654N', 483, 603, 'Championdo', b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'torneo', 'Inscripción a torneo', b'0', NULL, NULL),
(1046, '2024-03-29 21:00:27', '2024-03-29 21:43:23', '.pdf', 'autorizacionMayor1885234654N-604', '85234654N', 385, 604, 'Gimnasio Alcobendas', b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'autorizacionMayor18', 'Inscripción mayores de 18 años', b'1', '2024-03-29 21:00:27', NULL),
(1047, '2024-03-29 21:00:27', '2024-03-29 21:43:23', '.pdf', 'normativaSEPA85234654N-604', '85234654N', 385, 604, 'Gimnasio Alcobendas', b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'normativaSEPA', 'Autorización de adeudos SEPA', b'0', NULL, NULL),
(1048, '2024-03-29 21:00:27', '2024-03-29 21:43:23', '.pdf', 'whatsapp85234654N-604', '85234654N', 385, 604, 'Gimnasio Alcobendas', b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'whatsapp', 'Autorización para enviar información por WhatsApp', b'1', '2024-03-29 21:00:27', NULL),
(1049, '2024-03-29 21:17:02', '2024-03-29 21:40:09', '.pdf', 'autorizacionMenor1885234654N-12345678A-613', '85234654N', 385, 613, 'Gimnasio Alcobendas', b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'autorizacionMenor18', 'Inscripción menores de 18 años', b'1', '2024-03-29 21:17:02', NULL),
(1050, '2024-03-29 21:17:02', '2024-03-29 21:40:09', '.pdf', 'normativaSEPA85234654N-12345678A-613', '85234654N', 385, 613, 'Gimnasio Alcobendas', b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'normativaSEPA', 'Autorización de adeudos SEPA', b'0', NULL, NULL),
(1051, '2024-03-29 21:17:02', '2024-03-29 21:40:09', '.pdf', 'whatsapp85234654N-12345678A-613', '85234654N', 385, 613, 'Gimnasio Alcobendas', b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'whatsapp', 'Autorización para enviar información por WhatsApp', b'1', '2024-03-29 21:17:02', NULL),
(1052, '2024-03-29 21:36:47', '2024-03-29 21:46:44', '.pdf', 'torneo85234654N-622', '85234654N', 483, 622, 'Championdo', b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'torneo', 'Inscripción a torneo', b'0', NULL, NULL),
(1053, '2024-03-29 21:44:39', '2024-03-29 21:45:53', '.pdf', 'autorizacionMayor1885234654N-623', '85234654N', 385, 623, 'Gimnasio Alcobendas', b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'autorizacionMayor18', 'Inscripción mayores de 18 años', b'1', '2024-03-29 21:44:39', NULL),
(1055, '2024-03-29 21:45:15', '2024-03-29 21:45:53', '.pdf', 'normativaSEPAFirmado85234654N-623', '85234654N', 385, 623, 'Gimnasio Alcobendas', b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'normativaSEPAFirmado', 'Autorización de adeudos SEPA firmado', b'0', NULL, NULL),
(1056, '2024-03-30 12:05:29', NULL, '.pdf', 'autorizacionMenor1885234654N-626', '85234654N', 7, 626, 'Gimnasio Cobeña', b'1', 'src\\main\\resources\\static\\files\\autorizacionMenor18\\', b'0', 'autorizacionMenor18', 'Inscripción menores de 18 años', b'1', '2024-03-30 12:05:29', NULL),
(1057, '2024-03-30 12:06:04', NULL, '.pdf', 'autorizacionMayor1885234654N-629', '85234654N', 483, 629, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\autorizacionMayor18\\', b'0', 'autorizacionMayor18', 'Inscripción mayores de 18 años', b'1', '2024-03-30 12:06:04', NULL),
(1058, '2024-03-30 12:06:29', NULL, '.pdf', 'torneo85234654N-632', '85234654N', 483, 632, 'Championdo', b'0', 'src\\main\\resources\\static\\files\\torneo\\torneo20240420\\', b'0', 'torneo', 'Inscripción a torneo', b'0', NULL, NULL),
(1059, '2024-03-30 12:07:02', NULL, '.pdf', 'torneo85234654N-633', '85234654N', 483, 633, 'Championdo', b'0', 'src\\main\\resources\\static\\files\\torneo\\torneo20240420\\', b'0', 'torneo', 'Inscripción a torneo', b'0', NULL, NULL),
(1060, '2024-03-30 12:07:44', NULL, '.pdf', 'torneo85234654N-634', '85234654N', 483, 634, 'Championdo', b'0', 'src\\main\\resources\\static\\files\\torneo\\torneo20240420\\', b'0', 'torneo', 'Inscripción a torneo', b'0', NULL, NULL),
(1061, '2024-03-30 12:08:09', NULL, '.pdf', 'mandato85234654N-635', '85234654N', 483, 635, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\mandato\\', b'0', 'mandato', 'Autorización para presentar licencia federativa', b'1', '2024-03-30 12:08:09', NULL),
(1062, '2024-03-30 12:09:01', NULL, '.pdf', 'mandato85234654N-12345678A-638', '85234654N', 7, 638, 'Gimnasio Cobeña', b'1', 'src\\main\\resources\\static\\files\\mandato\\', b'0', 'mandato', 'Autorización para presentar licencia federativa', b'1', '2024-03-30 12:09:01', NULL),
(1063, '2024-03-30 12:09:33', NULL, '.pdf', 'mandato85234654N-01010101A-641', '85234654N', 385, 641, 'Gimnasio Alcobendas', b'1', 'src\\main\\resources\\static\\files\\mandato\\', b'0', 'mandato', 'Autorización para presentar licencia federativa', b'1', '2024-03-30 12:09:33', NULL),
(1064, '2024-03-30 13:32:37', NULL, '.pdf', 'autorizacionMayor1850026139N-644', '50026139N', 7, 644, 'Gimnasio Cobeña', b'1', 'src\\main\\resources\\static\\files\\autorizacionMayor18\\', b'0', 'autorizacionMayor18', 'Inscripción mayores de 18 años', b'1', '2024-03-30 13:32:37', NULL),
(1065, '2024-03-30 13:34:41', NULL, '.pdf', 'autorizacionMayor1858484019X-649', '58484019X', 483, 649, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\autorizacionMayor18\\', b'0', 'autorizacionMayor18', 'Inscripción mayores de 18 años', b'1', '2024-03-30 13:34:41', NULL),
(1066, '2024-03-30 14:07:34', NULL, '.pdf', 'autorizacionMenor1885234654N-652', '85234654N', 483, 652, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\autorizacionMenor18\\', b'0', 'autorizacionMenor18', 'Inscripción menores de 18 años', b'1', '2024-03-30 14:07:34', NULL),
(1067, '2024-03-30 14:09:25', NULL, '.pdf', 'autorizacionMenor1885234654N-655', '85234654N', 483, 655, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\autorizacionMenor18\\', b'0', 'autorizacionMenor18', 'Inscripción menores de 18 años', b'1', '2024-03-30 14:09:25', NULL),
(1068, '2024-04-01 18:22:03', '2024-04-01 18:31:13', '.pdf', 'autorizacionMayor1838345310D-658', '38345310D', 483, 658, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'autorizacionMayor18', 'Inscripción mayores de 18 años', b'1', '2024-04-01 18:22:03', NULL),
(1070, '2024-04-01 18:22:03', '2024-04-01 18:31:13', '.pdf', 'whatsapp38345310D-658', '38345310D', 483, 658, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'whatsapp', 'Autorización para enviar información por WhatsApp', b'1', '2024-04-01 18:22:03', NULL),
(1071, '2024-04-01 18:22:30', '2024-04-01 18:31:13', '.pdf', 'normativaSEPAFirmado38345310D-658', '38345310D', 483, 658, 'Championdo', b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'normativaSEPAFirmado', 'Autorización de adeudos SEPA firmado', b'0', NULL, NULL),
(1072, '2024-04-01 18:23:50', NULL, '.pdf', 'autorizacionMenor1838345310D-12345678A-661', '38345310D', 483, 661, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\autorizacionMenor18\\', b'0', 'autorizacionMenor18', 'Inscripción menores de 18 años', b'1', '2024-04-01 18:23:50', 'Daniel Giménez Sánchez'),
(1074, '2024-04-01 18:24:16', NULL, '.pdf', 'normativaSEPAFirmado38345310D-12345678A-661', '38345310D', 483, 661, 'Championdo', b'0', 'src\\main\\resources\\static\\files\\normativaSEPAFirmado\\', b'0', 'normativaSEPAFirmado', 'Autorización de adeudos SEPA firmado', b'0', NULL, 'Daniel Giménez Sánchez'),
(1075, '2024-04-01 18:31:58', NULL, '.pdf', 'autorizacionMayor1838345310D-664', '38345310D', 483, 664, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\autorizacionMayor18\\', b'0', 'autorizacionMayor18', 'Inscripción mayores de 18 años', b'1', '2024-04-01 18:31:58', 'Oliver Arietalaeanizbaezcoechea Ottovordemgentschefelde'),
(1077, '2024-04-01 18:31:58', NULL, '.pdf', 'whatsapp38345310D-664', '38345310D', 483, 664, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\whatsapp\\', b'0', 'whatsapp', 'Autorización para enviar información por WhatsApp', b'1', '2024-04-01 18:31:58', 'Oliver Arietalaeanizbaezcoechea Ottovordemgentschefelde'),
(1078, '2024-04-01 18:32:12', NULL, '.pdf', 'normativaSEPAFirmado38345310D-664', '38345310D', 483, 664, 'Championdo', b'0', 'src\\main\\resources\\static\\files\\normativaSEPAFirmado\\', b'0', 'normativaSEPAFirmado', 'Autorización de adeudos SEPA firmado', b'0', NULL, 'Oliver Arietalaeanizbaezcoechea Ottovordemgentschefelde'),
(1079, '2024-04-01 18:34:05', NULL, '.pdf', 'torneo38345310D-667', '38345310D', 483, 667, 'Championdo', b'0', 'src\\main\\resources\\static\\files\\torneo\\torneo20240420\\', b'0', 'torneo', 'Inscripción a torneo', b'0', NULL, 'Oliver Arietalaeanizbaezcoechea Ottovordemgentschefelde'),
(1080, '2024-04-01 18:34:38', NULL, '.pdf', 'torneo38345310D-12345678A-668', '38345310D', 483, 668, 'Championdo', b'0', 'src\\main\\resources\\static\\files\\torneo\\torneo20240420\\', b'0', 'torneo', 'Inscripción a torneo', b'0', NULL, 'Daniel Giménez Sánchez'),
(1081, '2024-04-01 18:35:14', NULL, '.pdf', 'torneo38345310D-01010101B-669', '38345310D', 483, 669, 'Championdo', b'0', 'src\\main\\resources\\static\\files\\torneo\\torneo20240420\\', b'0', 'torneo', 'Inscripción a torneo', b'0', NULL, 'Manuel Merino '),
(1082, '2024-04-01 18:36:40', '2024-04-10 21:00:55', '.pdf', 'mandato38345310D-670', '38345310D', 483, 670, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'mandato', 'Autorización para presentar licencia federativa', b'1', '2024-04-01 18:36:40', 'Oliver Arietalaeanizbaezcoechea Ottovordemgentschefelde'),
(1083, '2024-04-01 18:37:24', NULL, '.pdf', 'mandato38345310D-12345678A-673', '38345310D', 483, 673, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\mandato\\', b'0', 'mandato', 'Autorización para presentar licencia federativa', b'1', '2024-04-01 18:37:24', 'Daniel Acosta Giménez'),
(1084, '2024-04-01 18:38:06', NULL, '.pdf', 'mandato38345310D-01010101A-676', '38345310D', 483, 676, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\mandato\\', b'0', 'mandato', 'Autorización para presentar licencia federativa', b'1', '2024-04-01 18:38:06', 'Manuel Merino '),
(1085, '2024-04-03 00:18:33', NULL, '.pdf', 'mandato38345310D-679', '38345310D', 7, 679, 'Gimnasio Cobeña', b'1', 'src\\main\\resources\\static\\files\\mandato\\', b'0', 'mandato', 'Autorización para presentar licencia federativa', b'1', '2024-04-03 00:18:33', 'Oliver Arietalaeanizbaezcoechea Ottovordemgentschefelde'),
(1091, '2024-04-07 23:35:07', '2024-09-08 21:42:10', '.pdf', 'autorizacionMayor1838345310D-686', '38345310D', 385, 686, 'Gimnasio Alcobendas', b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'autorizacionMayor18', 'Inscripción mayores de 18 años', b'1', '2024-04-07 23:35:07', 'Oliver Arietalaeanizbaezcoechea Ottovordemgentschefelde'),
(1093, '2024-04-07 23:35:47', '2024-09-08 21:42:10', '.pdf', 'normativaSEPAFirmado38345310D-686', '38345310D', 385, 686, 'Gimnasio Alcobendas', b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'normativaSEPAFirmado', 'Autorización de adeudos SEPA firmado', b'0', NULL, 'Oliver Arietalaeanizbaezcoechea Ottovordemgentschefelde'),
(1094, '2024-04-10 21:01:51', NULL, '.pdf', 'mandato38345310D-689', '38345310D', 483, 689, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\mandato\\', b'0', 'mandato', 'Autorización para presentar licencia federativa', b'1', '2024-04-10 21:01:51', 'Oliver Arietalaeanizbaezcoechea Ottovordemgentschefelde'),
(1100, '2024-09-20 21:35:50', '2024-09-21 13:11:27', '.pdf', 'autorizacionMayor1801686706R-2', '01686706R', 483, 2, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'autorizacionMayor18', 'Inscripción mayores de 18 años', b'1', '2024-09-20 21:35:50', 'Víctor Pinto Sanmacario'),
(1102, '2024-09-20 21:35:50', '2024-09-21 13:11:27', '.pdf', 'whatsapp01686706R-2', '01686706R', 483, 2, 'Championdo', b'1', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'whatsapp', 'Autorización para enviar información por WhatsApp', b'1', '2024-09-20 21:35:50', 'Víctor Pinto Sanmacario'),
(1103, '2024-09-20 21:37:09', '2024-09-21 13:11:27', '.pdf', 'normativaSEPAFirmado01686706R-2', '01686706R', 483, 2, 'Championdo', b'0', 'src\\main\\resources\\static\\files\\tempDelete\\', b'0', 'normativaSEPAFirmado', 'Autorización de adeudos SEPA firmado', b'0', NULL, 'Víctor Pinto Sanmacario'),
(1104, '2024-09-21 13:15:41', NULL, '.pdf', 'autorizacionMenor1801686706R-52', '01686706R', 7, 52, 'Gimnasio Cobeña', b'1', 'src\\main\\resources\\static\\files\\autorizacionMenor18\\', b'0', 'autorizacionMenor18', 'Inscripción menores de 18 años', b'1', '2024-09-21 13:15:41', 'Laura Pinto Giménez'),
(1105, '2024-09-21 13:29:24', NULL, '.pdf', 'torneo01686706R-1', '01686706R', 385, 1, 'Gimnasio Alcobendas', b'0', 'src\\main\\resources\\static\\files\\torneo\\torneo20241214\\', b'0', 'torneo', 'Inscripción a torneo', b'0', NULL, 'Víctor Pinto Sanmacario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `firma`
--

CREATE TABLE `firma` (
  `id` int(11) NOT NULL,
  `firmado` bit(1) NOT NULL,
  `id_operacion` int(11) NOT NULL,
  `numero_intentos` int(11) NOT NULL,
  `codigo_gimnasio` int(11) NOT NULL,
  `operativa_original` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `firma`
--

INSERT INTO `firma` (`id`, `firmado`, `id_operacion`, `numero_intentos`, `codigo_gimnasio`, `operativa_original`) VALUES
(1, b'1', 2, 1, 0, 'inscripcionTaekwondo'),
(2, b'1', 52, 1, 0, 'inscripcionTaekwondo'),
(501, b'1', 499, 1, 0, 'inscripcionTaekwondo'),
(504, b'1', 502, 1, 0, 'inscripcionTaekwondo'),
(509, b'1', 507, 1, 0, 'inscripcionMandato'),
(512, b'1', 510, 1, 0, 'inscripcionMandato'),
(524, b'1', 522, 1, 0, 'inscripcionMandato'),
(526, b'1', 516, 1, 0, 'inscripcionTaekwondo'),
(528, b'1', 515, 1, 0, 'inscripcionTaekwondo'),
(531, b'1', 529, 1, 0, 'inscripcionTaekwondo'),
(534, b'1', 532, 1, 0, 'inscripcionTaekwondo'),
(537, b'1', 535, 1, 0, 'inscripcionTaekwondo'),
(540, b'1', 538, 1, 0, 'inscripcionTaekwondo'),
(543, b'1', 541, 1, 0, 'inscripcionTaekwondo'),
(546, b'1', 544, 1, 0, 'inscripcionTaekwondo'),
(549, b'1', 547, 1, 0, 'inscripcionTaekwondo'),
(555, b'1', 553, 1, 0, 'inscripcionMandato'),
(558, b'1', 556, 1, 0, 'inscripcionMandato'),
(561, b'1', 559, 1, 0, 'inscripcionMandato'),
(564, b'1', 562, 1, 0, 'inscripcionMandato'),
(567, b'1', 565, 1, 0, 'inscripcionMandato'),
(570, b'1', 568, 1, 0, 'inscripcionMandato'),
(573, b'1', 571, 1, 0, 'inscripcionMandato'),
(579, b'1', 577, 1, 0, 'inscripcionMandato'),
(582, b'1', 580, 1, 0, 'inscripcionTaekwondo'),
(587, b'1', 584, 1, 0, 'inscripcionTaekwondo'),
(590, b'1', 588, 1, 0, 'inscripcionTaekwondo'),
(593, b'1', 591, 1, 0, 'inscripcionTaekwondo'),
(602, b'1', 600, 1, 0, 'inscripcionMandato'),
(606, b'1', 604, 1, 0, 'inscripcionTaekwondo'),
(615, b'1', 613, 1, 0, 'inscripcionTaekwondo'),
(625, b'1', 623, 1, 0, 'inscripcionTaekwondo'),
(628, b'1', 626, 1, 0, 'inscripcionTaekwondo'),
(631, b'1', 629, 1, 0, 'inscripcionTaekwondo'),
(637, b'1', 635, 1, 0, 'inscripcionMandato'),
(640, b'1', 638, 1, 0, 'inscripcionMandato'),
(643, b'1', 641, 1, 0, 'inscripcionMandato'),
(646, b'1', 644, 1, 0, 'inscripcionTaekwondo'),
(651, b'1', 649, 1, 0, 'inscripcionTaekwondo'),
(654, b'1', 652, 1, 0, 'inscripcionTaekwondo'),
(657, b'1', 655, 1, 0, 'inscripcionTaekwondo'),
(660, b'1', 658, 1, 0, 'inscripcionTaekwondo'),
(663, b'1', 661, 1, 0, 'inscripcionTaekwondo'),
(666, b'1', 664, 1, 0, 'inscripcionTaekwondo'),
(672, b'1', 670, 1, 0, 'inscripcionMandato'),
(675, b'1', 673, 1, 0, 'inscripcionMandato'),
(678, b'1', 676, 1, 0, 'inscripcionMandato'),
(681, b'1', 679, 1, 0, 'inscripcionMandato'),
(688, b'1', 686, 1, 0, 'inscripcionTaekwondo'),
(691, b'1', 689, 1, 0, 'inscripcionMandato');

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

--
-- Volcado de datos para la tabla `firma_codigo`
--

INSERT INTO `firma_codigo` (`id`, `codigo`, `dni`, `fecha_caducidad`, `fecha_creacion`, `id_operacion`, `operativa_original`, `pagina_firma_ok`, `codigo_gimnasio`) VALUES
(102, '17MMT6', '01686706R', '2024-09-21 13:30:00', '2024-09-21 13:15:00', 52, 'inscripcionTaekwondo', 'formularioInscFinalizada', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `firma_codigo_seq`
--

CREATE TABLE `firma_codigo_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `firma_codigo_seq`
--

INSERT INTO `firma_codigo_seq` (`next_val`) VALUES
(201);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `firma_seq`
--

CREATE TABLE `firma_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `firma_seq`
--

INSERT INTO `firma_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gimnasio`
--

CREATE TABLE `gimnasio` (
  `id` int(11) NOT NULL,
  `nombre_gimnasio` varchar(100) DEFAULT NULL,
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
  `fecha_nacimiento` datetime DEFAULT NULL,
  `nombre_responsable` varchar(60) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `usuario_modificacion` varchar(45) DEFAULT NULL,
  `visibilidad_contratada` int(11) NOT NULL,
  `email_host` varchar(200) DEFAULT NULL,
  `email_password` varchar(60) DEFAULT NULL,
  `email_port` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `gimnasio`
--

INSERT INTO `gimnasio` (`id`, `nombre_gimnasio`, `apellido1responsable`, `apellido2responsable`, `cantidad_registros_contratados`, `cif_nif`, `correo`, `domicilio_calle`, `domicilio_cp`, `domicilio_localidad`, `domicilio_numero`, `domicilio_otros`, `enabled`, `fecha_alta`, `fecha_modificacion`, `fecha_nacimiento`, `nombre_responsable`, `telefono`, `usuario_modificacion`, `visibilidad_contratada`, `email_host`, `email_password`, `email_port`) VALUES
(7, 'Gimnasio Cobeña', 'Huertas', 'Cejudo', 30, '02262310F', 'damianjava@gmail.com', 'Calle Azorín', '28863', 'Cobeña', '18', '', b'1', '2023-12-05 21:21:14', '2024-01-24 21:48:14', '1977-02-10 00:00:00', 'Lidia María', '666555444', '02262310F', 200, 'smtp.gmail.com', 'ylmfgftrkhhzneui', '587'),
(102, 'Gimnasio Algete', 'Pérez', 'Rodríguez', 100, '30098850S', 'damianjava@gmail.com', 'Avenida Montes de Oca', '28760', 'Algete', '', '', b'1', '2023-11-12 21:06:56', '2024-03-10 20:20:23', '1979-01-01 00:00:00', 'Mariano', '666555444', '05959715R', 1, 'smtp.gmail.com', 'ylmfgftrkhhzneui', '587'),
(385, 'Gimnasio Alcobendas', 'Aranda', 'Alvarado', 100, 'A29603305', 'damianjava@gmail.com', 'Barranco del agua', '28760', 'Alcobendas', '', '', b'1', '2023-11-17 19:11:16', '2023-11-17 19:11:16', '1998-01-01 00:00:00', 'Ana María', '666555444', '05959715R', 1, 'smtp.gmail.com', 'ylmfgftrkhhzneui', '587'),
(483, 'Championdo', 'Gonzalo', 'Rodríguez', 100, 'Q9754068F', 'damianjava@gmail.com', 'Avenida Viñuelas', '28760', 'Tres Cantos', '30', '', b'1', '2023-11-23 23:14:44', '2024-01-24 23:21:10', '1970-01-01 00:00:00', 'José Luis', '666555444', '05959715R', 2, 'smtp.gmail.com', 'ylmfgftrkhhzneui', '587'),
(631, 'Gimnasio Borrar', 'Huertas', 'Cejudo', 0, '02262310F', 'damianjava@gmail.com', 'Calle Azorín', '28863', 'Cobeña', '', '', b'1', '2024-01-14 18:49:01', '2024-01-24 21:27:16', '1976-02-10 00:00:00', 'José Luis', '666555444', '05959715R', 0, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gimnasio_menu2`
--

CREATE TABLE `gimnasio_menu2` (
  `id` int(11) NOT NULL,
  `fecha_alta` datetime DEFAULT NULL,
  `id_gimnasio` int(11) NOT NULL,
  `id_menu2` int(11) NOT NULL,
  `username_alta` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `gimnasio_menu2`
--

INSERT INTO `gimnasio_menu2` (`id`, `fecha_alta`, `id_gimnasio`, `id_menu2`, `username_alta`) VALUES
(916, '2024-01-21 01:52:41', 102, 583, '30098850S'),
(917, '2024-01-21 01:53:14', 385, 582, 'A29603305'),
(918, '2024-01-21 01:53:14', 385, 583, 'A29603305'),
(919, '2024-01-21 01:53:14', 385, 584, 'A29603305'),
(920, '2024-01-21 01:53:42', 483, 582, 'Q9754068F'),
(921, '2024-01-21 01:53:42', 483, 584, 'Q9754068F'),
(1088, '2024-04-04 21:37:52', 7, 582, '02262310F'),
(1089, '2024-04-04 21:37:52', 7, 583, '02262310F'),
(1090, '2024-04-04 21:37:52', 7, 584, '02262310F');

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
(692);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion_taekwondo`
--

CREATE TABLE `inscripcion_taekwondo` (
  `id` int(11) NOT NULL,
  `fecha_inscripcion` datetime DEFAULT NULL,
  `codigo_gimnasio` int(11) NOT NULL,
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
  `extensionsepafirmado` varchar(10) DEFAULT NULL,
  `domiciliacionsepafirmada` bit(1) NOT NULL,
  `nombre_gimnasio` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inscripcion_taekwondo`
--

INSERT INTO `inscripcion_taekwondo` (`id`, `fecha_inscripcion`, `codigo_gimnasio`, `mayor_nombre`, `mayor_apellido1`, `mayor_apellido2`, `mayor_dni`, `mayor_correo`, `mayor_fecha_nacimiento`, `mayor_telefono`, `mayor_sexo`, `mayor_domicilio_calle`, `mayor_domicilio_numero`, `mayor_domicilio_otros`, `mayor_domicilio_localidad`, `mayor_domicilio_cp`, `mayor_pais`, `mayor_calidad`, `mayor_cinturon`, `mayor_licencia`, `mayor_autoriza_whats_app`, `autorizado_menor`, `autorizado_nombre`, `autorizado_apellido1`, `autorizado_apellido2`, `autorizado_dni`, `autorizado_fecha_nacimiento`, `autorizado_sexo`, `autorizado_pais`, `autorizado_cinturon`, `autorizado_licencia`, `domiciliacionsepa`, `titular_cuenta`, `iban`, `swift`, `notas`, `inscripcion_firmada`, `extensionsepafirmado`, `domiciliacionsepafirmada`, `nombre_gimnasio`) VALUES
(1, '2024-09-08 21:45:02', 483, 'Oliver', 'Arietalaeanizbaezcoechea', 'Ottovordemgentschefelde', '38345310D', 'dusheff@hotmail.com', NULL, '666555444', NULL, 'Carretera de Fuente el Saz', '1', '', 'Cobeña', '28863', NULL, 'Padre', NULL, b'0', b'1', b'1', 'Manuel', 'Arietalaeanizbaezcoechea', 'Benito', '', '2015-06-10 00:00:00', 'Masculino', 'España', 'Blanco', b'0', b'1', 'Damián Titu banco', 'ES1234567890123456', '', NULL, b'0', NULL, b'0', 'Championdo'),
(52, '2024-09-21 13:14:59', 7, 'Víctor', 'Pinto', 'Sanmacario', '01686706R', 'dusheff@hotmail.com', NULL, '666555444', NULL, 'Avenida de la industria', '16', '', 'Tres Cantos', '28760', NULL, 'Padre', NULL, b'0', b'0', b'1', 'Laura', 'Pinto', 'Giménez', '', '2013-11-19 00:00:00', 'Femenino', 'España', 'Blanco', b'0', b'0', NULL, NULL, NULL, NULL, b'1', NULL, b'0', 'Gimnasio Cobeña'),
(626, '2024-03-30 12:05:13', 7, 'Rubén', 'Sosa', 'Aristizabal', '85234654N', 'dusheff@hotmail.com', NULL, '666555444', NULL, 'Avenida Montes de Oca', '18', 'Ático derecha', 'San Sebastián de los Reyes', '28456', NULL, 'Padre', NULL, b'0', b'0', b'1', 'Daniel', 'Acosta', 'Giménez', '', '2010-02-16 00:00:00', 'Masculino', 'España', 'Naranja', b'0', b'0', NULL, NULL, NULL, NULL, b'1', NULL, b'0', 'Gimnasio Cobeña'),
(629, '2024-03-30 12:05:50', 483, 'Rubén', 'Sosa', 'Aristizabal', '85234654N', 'dusheff@hotmail.com', '1972-10-14 00:00:00', '666555444', 'Masculino', 'Avenida Montes de Oca', '18', 'Ático derecha', 'San Sebastián de los Reyes', '28456', 'España', NULL, 'Blanco', b'0', b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL, b'1', NULL, b'0', 'Championdo'),
(644, '2024-03-30 13:32:23', 7, 'María de los Ángeles', 'Velázquez', 'SegundoApellido8', '50026139N', 'dusheff@hotmail.com', '1992-04-26 00:00:00', '666555444', 'Femenino', 'Avenida Montes de Oca', '1', '', 'Madrid', '28006', 'España', NULL, 'Azul', b'0', b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL, b'1', NULL, b'0', 'Gimnasio Cobeña'),
(647, '2024-03-30 13:33:33', 7, 'María de los Ángeles', 'Velázquez', 'SegundoApellido8', '50026139N', 'dusheff@hotmail.com', NULL, '666555444', NULL, 'Avenida Montes de Oca', '1', '', 'Madrid', '28006', NULL, 'Madre', NULL, b'0', b'0', b'1', 'Laura', 'Salvador', '', '', '2015-05-16 00:00:00', 'Femenino', 'España', 'Blanco', b'0', b'0', NULL, NULL, NULL, NULL, b'0', NULL, b'0', 'Gimnasio Cobeña'),
(649, '2024-03-30 13:34:27', 483, 'Hernán Pablo', 'Vilella', 'Aristizabal', '58484019X', 'dusheff@hotmail.com', '1980-03-01 00:00:00', '666555444', 'Masculino', 'Calle Azorín', '18', 'Ático', 'Cobeña', '28863', 'España', NULL, 'Marrón', b'0', b'0', b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL, b'1', NULL, b'0', 'Championdo'),
(652, '2024-03-30 14:06:24', 483, 'Rubén', 'Sosa', 'Aristizabal', '85234654N', 'dusheff@hotmail.com', NULL, '666555444', NULL, 'Avenida Montes de Oca', '18', 'Ático derecha', 'San Sebastián de los Reyes', '28456', NULL, 'Padre', NULL, b'0', b'0', b'1', 'Ariadna', 'Acosta', '', '', '2012-04-16 00:00:00', 'Femenino', 'España', 'Naranja', b'0', b'0', NULL, NULL, NULL, NULL, b'1', NULL, b'0', 'Championdo'),
(655, '2024-03-30 14:08:31', 483, 'Rubén', 'Sosa', 'Aristizabal', '85234654N', 'dusheff@hotmail.com', NULL, '666555444', NULL, 'Avenida Montes de Oca', '18', 'Ático derecha', 'San Sebastián de los Reyes', '28456', NULL, 'Padre', NULL, b'0', b'0', b'1', 'Ariadna', 'Acosta', '', '', '2012-04-16 00:00:00', 'Femenino', 'España', 'Naranja', b'0', b'0', NULL, NULL, NULL, NULL, b'1', NULL, b'0', 'Championdo'),
(661, '2024-04-01 18:23:35', 483, 'Oliver', 'Arietalaeanizbaezcoechea', 'Ottovordemgentschefelde', '38345310D', 'dusheff@hotmail.com', NULL, '666555444', NULL, 'Carretera de Fuente el Saz', '1', '', 'Cobeña', '28863', NULL, 'Padre', NULL, b'0', b'0', b'1', 'Daniel', 'Giménez', 'Sánchez', '12345678A', '2010-04-15 00:00:00', 'Masculino', 'España', 'Naranja', b'0', b'1', 'Damián Titu banco', 'ES1234567890123456', '', NULL, b'1', '.pdf', b'1', 'Championdo'),
(664, '2024-04-01 18:31:37', 483, 'Oliver', 'Arietalaeanizbaezcoechea', 'Ottovordemgentschefelde', '38345310D', 'dusheff@hotmail.com', '2000-05-05 00:00:00', '666555444', 'Masculino', 'Carretera de Fuente el Saz', '1', '', 'Cobeña', '28863', 'España', NULL, 'Verde', b'0', b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', b'1', 'Damián Titu banco', 'ES1234567890123456', '', NULL, b'1', '.pdf', b'1', 'Championdo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion_taekwondo_seq`
--

CREATE TABLE `inscripcion_taekwondo_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inscripcion_taekwondo_seq`
--

INSERT INTO `inscripcion_taekwondo_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mandato`
--

CREATE TABLE `mandato` (
  `id` int(11) NOT NULL,
  `adulto` bit(1) NOT NULL,
  `codigo_gimnasio` int(11) NOT NULL,
  `apellido1autorizado` varchar(60) DEFAULT NULL,
  `apellido1mandante` varchar(60) DEFAULT NULL,
  `apellido2autorizado` varchar(60) DEFAULT NULL,
  `apellido2mandante` varchar(60) DEFAULT NULL,
  `calidad` varchar(20) DEFAULT NULL,
  `calidad_otro` varchar(20) DEFAULT NULL,
  `correo_mandante` varchar(100) DEFAULT NULL,
  `dni_autorizado` varchar(45) DEFAULT NULL,
  `dni_mandante` varchar(45) DEFAULT NULL,
  `domicilio_calle` varchar(100) DEFAULT NULL,
  `domicilio_cp` varchar(10) DEFAULT NULL,
  `domicilio_localidad` varchar(50) DEFAULT NULL,
  `domicilio_numero` varchar(30) DEFAULT NULL,
  `domicilio_otros` varchar(50) DEFAULT NULL,
  `fecha_alta` datetime DEFAULT NULL,
  `mandato_firmado` bit(1) NOT NULL,
  `nombre_autorizado` varchar(60) DEFAULT NULL,
  `nombre_mandante` varchar(60) DEFAULT NULL,
  `pais` varchar(20) DEFAULT NULL,
  `temporada` varchar(11) DEFAULT NULL,
  `licencia_abonada` bit(1) NOT NULL,
  `nombre_gimnasio` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `mandato`
--

INSERT INTO `mandato` (`id`, `adulto`, `codigo_gimnasio`, `apellido1autorizado`, `apellido1mandante`, `apellido2autorizado`, `apellido2mandante`, `calidad`, `calidad_otro`, `correo_mandante`, `dni_autorizado`, `dni_mandante`, `domicilio_calle`, `domicilio_cp`, `domicilio_localidad`, `domicilio_numero`, `domicilio_otros`, `fecha_alta`, `mandato_firmado`, `nombre_autorizado`, `nombre_mandante`, `pais`, `temporada`, `licencia_abonada`, `nombre_gimnasio`) VALUES
(635, b'1', 483, NULL, 'Sosa', NULL, 'Aristizabal', NULL, NULL, 'dusheff@hotmail.com', NULL, '85234654N', 'Avenida Montes de Oca', '28456', 'San Sebastián de los Reyes', '18', 'Ático derecha', '2024-03-30 12:07:57', b'1', NULL, 'Rubén', 'España', '2024', b'0', 'Championdo'),
(638, b'0', 7, 'Acosta', 'Sosa', 'Giménez', 'Aristizabal', 'Padre', '', 'dusheff@hotmail.com', '12345678A', '85234654N', 'Avenida Montes de Oca', '28456', 'San Sebastián de los Reyes', '18', 'Ático derecha', '2024-03-30 12:08:48', b'1', 'Daniel', 'Rubén', 'España', '2024', b'0', 'Gimnasio Cobeña'),
(641, b'0', 385, 'Merino', 'Sosa', 'En El País', 'Aristizabal', 'Tutor', '', 'dusheff@hotmail.com', '01010101A', '85234654N', 'Avenida Montes de Oca', '28456', 'San Sebastián de los Reyes', '18', 'Ático derecha', '2024-03-30 12:09:23', b'1', 'Manuel', 'Rubén', 'España', '2024', b'0', 'Gimnasio Alcobendas'),
(673, b'0', 483, 'Acosta', 'Arietalaeanizbaezcoechea', 'Giménez', 'Ottovordemgentschefelde', 'Padre', '', 'dusheff@hotmail.com', '12345678A', '38345310D', 'Carretera de Fuente el Saz', '28863', 'Cobeña', '1', '', '2024-04-01 18:37:08', b'1', 'Daniel', 'Oliver', 'España', '2024', b'0', 'Championdo'),
(676, b'0', 483, 'Merino', 'Arietalaeanizbaezcoechea', '', 'Ottovordemgentschefelde', 'Tutor', '', 'dusheff@hotmail.com', '01010101A', '38345310D', 'Carretera de Fuente el Saz', '28863', 'Cobeña', '1', '', '2024-04-01 18:37:51', b'1', 'Manuel', 'Oliver', 'España', '2024', b'0', 'Championdo'),
(679, b'1', 7, NULL, 'Arietalaeanizbaezcoechea', NULL, 'Ottovordemgentschefelde', NULL, NULL, 'dusheff@hotmail.com', NULL, '38345310D', 'Carretera de Fuente el Saz', '28863', 'Cobeña', '1', '', '2024-04-03 00:18:11', b'1', NULL, 'Oliver', 'España', '2024', b'0', 'Gimnasio Cobeña'),
(689, b'1', 483, NULL, 'Arietalaeanizbaezcoechea', NULL, 'Ottovordemgentschefelde', NULL, NULL, 'dusheff@hotmail.com', NULL, '38345310D', 'Carretera de Fuente el Saz', '28863', 'Cobeña', '1', '', '2024-04-10 21:01:13', b'1', NULL, 'Oliver', 'España', '2024', b'0', 'Championdo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mandato_seq`
--

CREATE TABLE `mandato_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `mandato_seq`
--

INSERT INTO `mandato_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menu1`
--

CREATE TABLE `menu1` (
  `id` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `position` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `menu1`
--

INSERT INTO `menu1` (`id`, `enabled`, `nombre`, `position`) VALUES
(578, b'1', 'Actividades', 0),
(579, b'1', 'Artes Marciales', 1),
(580, b'1', 'Gimnasios', 2),
(581, b'1', 'Más inscripciones', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menu2`
--

CREATE TABLE `menu2` (
  `id` int(11) NOT NULL,
  `aviso` varchar(30) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `id_menu1` int(11) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `position` int(11) NOT NULL,
  `url` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `menu2`
--

INSERT INTO `menu2` (`id`, `aviso`, `enabled`, `id_menu1`, `nombre`, `position`, `url`) VALUES
(582, 'inscripcionTaekwondo', b'1', 579, 'Inscripción Taekwondo', 0, '/gimnasio/tipoInscripcion'),
(583, 'inscripcionTorneo', b'1', 581, 'Inscripción Torneo Taekwondo', 0, '/tournamentRegistration/mainPage'),
(584, 'licenciaTaekwondo', b'1', 581, 'Mandato de Licencia Taekwondo', 1, '/mandato/mandatos'),
(594, 'avisoGimnasio', b'1', 580, 'Gimnasio Algete', 1, '/gimnasio/detalle/102'),
(595, 'avisoGimnasio', b'1', 580, 'Gimnasio Alcobendas', 0, '/gimnasio/detalle/385'),
(596, 'avisoGimnasio', b'1', 580, 'Gimnasio Cobeña', 2, '/gimnasio/detalle/7');

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
(2, 'Francia', 2),
(3, 'Portugal', 3),
(4, 'Bélgica', 1),
(620, 'Holanda', 4);

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

--
-- Volcado de datos para la tabla `poomsae`
--

INSERT INTO `poomsae` (`id`, `nombre`, `position`, `codigo_gimnasio`) VALUES
(38, 'KICHO', 0, 7),
(39, '1º POOMSAE', 1, 7),
(40, '2º POOMSAE', 2, 7),
(41, '3º POOMSAE', 3, 7),
(42, '4º POOMSAE', 4, 7),
(43, '5º POOMSAE', 5, 7),
(44, '6º POOMSAE', 6, 7),
(45, '7º POOMSAE', 7, 7),
(46, '8º POOMSAE', 8, 7),
(47, 'KORYO', 9, 7),
(48, 'KUMGANG', 10, 7),
(49, 'TAEBEK', 11, 7),
(50, 'PYONGWON', 12, 7),
(51, 'SYPCCHIN', 13, 7),
(52, 'CHITAE', 14, 7),
(53, 'CHUNGKWON', 15, 7),
(54, 'JANSU', 16, 7),
(55, 'ILIO', 17, 7),
(56, 'INCLUSIVO', 18, 7),
(133, 'KICHO', 0, 102),
(134, '1º POOMSAE', 1, 102),
(135, '2º POOMSAE', 2, 102),
(136, '3º POOMSAE', 3, 102),
(137, '4º POOMSAE', 4, 102),
(138, '5º POOMSAE', 5, 102),
(139, '6º POOMSAE', 6, 102),
(140, '7º POOMSAE', 7, 102),
(141, '8º POOMSAE', 8, 102),
(142, 'KORYO', 9, 102),
(143, 'KUMGANG', 10, 102),
(144, 'TAEBEK', 11, 102),
(145, 'PYONGWON', 12, 102),
(146, 'SYPCCHIN', 13, 102),
(147, 'CHITAE', 14, 102),
(148, 'CHUNGKWON', 15, 102),
(149, 'JANSU', 16, 102),
(150, 'ILIO', 17, 102),
(151, 'INCLUSIVO', 18, 102),
(416, 'KICHO', 0, 385),
(417, '1º POOMSAE', 1, 385),
(418, '2º POOMSAE', 2, 385),
(419, '3º POOMSAE', 3, 385),
(420, '4º POOMSAE', 4, 385),
(421, '5º POOMSAE', 5, 385),
(422, '6º POOMSAE', 6, 385),
(423, '7º POOMSAE', 7, 385),
(424, '8º POOMSAE', 8, 385),
(425, 'KORYO', 9, 385),
(426, 'KUMGANG', 10, 385),
(427, 'TAEBEK', 11, 385),
(428, 'PYONGWON', 12, 385),
(429, 'SYPCCHIN', 13, 385),
(430, 'CHITAE', 14, 385),
(431, 'CHUNGKWON', 15, 385),
(432, 'JANSU', 16, 385),
(433, 'ILIO', 17, 385),
(434, 'INCLUSIVO', 18, 385),
(514, 'KICHO', 0, 483),
(515, '1º POOMSAE', 1, 483),
(516, '2º POOMSAE', 2, 483),
(517, '3º POOMSAE', 3, 483),
(518, '4º POOMSAE', 4, 483),
(519, '5º POOMSAE', 5, 483),
(520, '6º POOMSAE', 6, 483),
(521, '7º POOMSAE', 7, 483),
(522, '8º POOMSAE', 8, 483),
(523, 'KORYO', 9, 483),
(524, 'KUMGANG', 10, 483),
(525, 'TAEBEK', 11, 483),
(526, 'PYONGWON', 12, 483),
(527, 'SYPCCHIN', 13, 483),
(528, 'CHITAE', 14, 483),
(529, 'CHUNGKWON', 15, 483),
(530, 'JANSU', 16, 483),
(531, 'ILIO', 17, 483),
(532, 'INCLUSIVO', 18, 483);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `token`
--

CREATE TABLE `token` (
  `id` varchar(255) NOT NULL,
  `attempts` int(11) NOT NULL,
  `codigo_gimnasio` int(11) NOT NULL,
  `expiration` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
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
  `codigo_gimnasio` int(11) NOT NULL,
  `adulto` bit(1) NOT NULL,
  `inclusivo` bit(1) NOT NULL,
  `menor` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `torneo`
--

INSERT INTO `torneo` (`id`, `nombre`, `direccion`, `fecha_torneo`, `fecha_comienzo_inscripcion`, `fecha_fin_inscripcion`, `codigo_gimnasio`, `adulto`, `inclusivo`, `menor`) VALUES
(4, 'Torneo Algete', 'Calle mayor 5 - Algete', '2024-01-10 00:00:00', '2023-10-01 00:00:00', '2023-12-31 00:00:00', 102, b'1', b'1', b'1'),
(51, 'Torneo Alcobendas 01', 'Avenida España 25, Alcobendas', '2024-02-01 00:00:00', '2023-11-29 00:00:00', '2024-01-20 00:00:00', 385, b'1', b'0', b'0'),
(53, 'Torneo Alcobendas 02', 'Avenida España 2, Alcobendas', '2023-11-15 00:00:00', '2023-10-01 00:00:00', '2023-11-01 00:00:00', 385, b'0', b'0', b'0'),
(55, 'Torneo Alcobendas 03', 'Avenida España 3, Alcobendas', '2024-09-01 00:00:00', '2024-05-01 00:00:00', '2024-08-16 00:00:00', 385, b'1', b'1', b'1'),
(279, 'Torneo Mariano', 'Calle mayor 5 - Algete', '2024-04-01 00:00:00', '2024-01-07 00:00:00', '2024-03-26 23:59:59', 102, b'1', b'1', b'1'),
(284, 'Torneo Cobeña', 'Calle del Olivo 7 - Cobeña', '2014-02-10 00:00:00', '2024-01-01 00:00:00', '2024-02-06 23:59:59', 7, b'1', b'1', b'1'),
(293, 'Torneo Alcobendas Navidades', 'Avenida España 25, Alcobendas', '2024-12-14 00:00:00', '2024-08-01 00:00:00', '2024-12-10 23:59:59', 385, b'1', b'1', b'1'),
(379, 'Torneo Tres Cantos', 'Avenida Viñuelas 30 - Tres Cantos', '2024-04-20 00:00:00', '2024-01-01 00:00:00', '2024-04-16 23:59:59', 483, b'1', b'1', b'1');

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

--
-- Volcado de datos para la tabla `torneo_gimnasio`
--

INSERT INTO `torneo_gimnasio` (`id`, `id_torneo`, `nombre_gimnasio`, `position`, `codigo_gimnasio`) VALUES
(5, 4, 'Gimnasio Algete', 0, 102),
(6, 4, 'Gimnasio Alcobendas', 1, 102),
(52, 51, 'Gimnasio Alcobendas', 0, 385),
(54, 53, 'Gimnasio Alcobendas', 0, 385),
(56, 55, 'Gimnasio Alcobendas', 0, 385),
(57, 51, 'Gimnasio Torneo 01-01', 1, 385),
(60, 53, 'Gimnasio Torneo 02-01', 1, 385),
(61, 53, 'Gimnasio Torneo 02-02', 2, 385),
(62, 53, 'Gimnasio Torneo 02-03', 3, 385),
(63, 55, 'Gimnasio Torneo 03-01', 1, 385),
(64, 55, 'Gimnasio Torneo 03-02', 2, 385),
(65, 55, 'Gimnasio Torneo 03-03', 3, 385),
(280, 279, 'Gimnasio Algete', 0, 102),
(281, 279, 'Gimnasio Cobeña', 1, 102),
(282, 279, 'Gimnasio Alcobendas', 2, 102),
(285, 284, 'Gimnasio Cobeña', 0, 7),
(287, 286, 'Gimnasio Cobeña', 0, 7),
(288, 286, 'Gimnasio Alcobendas', 1, 7),
(289, 286, 'Gimnasio Algete', 2, 7),
(290, 284, 'Gimnasio Algete', 1, 7),
(291, 284, 'Gimnasio Alcobendas', 2, 7),
(292, 284, 'Gimnasio Daganzo', 3, 7),
(294, 293, 'Gimnasio Alcobendas', 0, 385),
(295, 51, 'Gimnasio Torneo 01-03', 3, 385),
(296, 51, 'Gimnasio Torneo 01-02', 2, 385),
(380, 379, 'Championdo', 0, 483),
(381, 379, 'Gimnasio Alcobendas', 1, 483),
(382, 379, 'Gimnasio Tres Cantos', 2, 483);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `torneo_gimnasio_seq`
--

CREATE TABLE `torneo_gimnasio_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `torneo_gimnasio_seq`
--

INSERT INTO `torneo_gimnasio_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `torneo_seq`
--

CREATE TABLE `torneo_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `torneo_seq`
--

INSERT INTO `torneo_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tournament_registration`
--

CREATE TABLE `tournament_registration` (
  `id` int(11) NOT NULL,
  `authorizer1lastname` varchar(60) DEFAULT NULL,
  `authorizer2lastname` varchar(60) DEFAULT NULL,
  `authorizer_address_city` varchar(50) DEFAULT NULL,
  `authorizer_address_number` varchar(30) DEFAULT NULL,
  `authorizer_address_other` varchar(50) DEFAULT NULL,
  `authorizer_address_postcode` varchar(10) DEFAULT NULL,
  `authorizer_address_street` varchar(100) DEFAULT NULL,
  `authorizer_id_card` varchar(45) DEFAULT NULL,
  `authorizer_name` varchar(60) DEFAULT NULL,
  `belt` varchar(40) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `gym` varchar(100) DEFAULT NULL,
  `id_gym` int(11) NOT NULL,
  `id_tournament` int(11) NOT NULL,
  `notes` text DEFAULT NULL,
  `paid` bit(1) NOT NULL,
  `payment_date` datetime DEFAULT NULL,
  `poomsae` varchar(50) DEFAULT NULL,
  `registered1lastname` varchar(60) DEFAULT NULL,
  `registered2lastname` varchar(60) DEFAULT NULL,
  `registered_address_city` varchar(50) DEFAULT NULL,
  `registered_address_number` varchar(30) DEFAULT NULL,
  `registered_address_other` varchar(50) DEFAULT NULL,
  `registered_address_postcode` varchar(10) DEFAULT NULL,
  `registered_address_street` varchar(100) DEFAULT NULL,
  `registered_birthdate` datetime DEFAULT NULL,
  `registered_id_card` varchar(45) DEFAULT NULL,
  `registered_name` varchar(60) DEFAULT NULL,
  `registered_sex` varchar(9) NOT NULL,
  `registration_adult` bit(1) NOT NULL,
  `registration_date` datetime DEFAULT NULL,
  `registration_inclusive` bit(1) NOT NULL,
  `registration_young` bit(1) NOT NULL,
  `relationship` varchar(20) DEFAULT NULL,
  `tournament_address` varchar(200) DEFAULT NULL,
  `tournament_date` varchar(10) DEFAULT NULL,
  `tournament_name` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tournament_registration`
--

INSERT INTO `tournament_registration` (`id`, `authorizer1lastname`, `authorizer2lastname`, `authorizer_address_city`, `authorizer_address_number`, `authorizer_address_other`, `authorizer_address_postcode`, `authorizer_address_street`, `authorizer_id_card`, `authorizer_name`, `belt`, `category`, `country`, `gym`, `id_gym`, `id_tournament`, `notes`, `paid`, `payment_date`, `poomsae`, `registered1lastname`, `registered2lastname`, `registered_address_city`, `registered_address_number`, `registered_address_other`, `registered_address_postcode`, `registered_address_street`, `registered_birthdate`, `registered_id_card`, `registered_name`, `registered_sex`, `registration_adult`, `registration_date`, `registration_inclusive`, `registration_young`, `relationship`, `tournament_address`, `tournament_date`, `tournament_name`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Blanco', 'D1', 'España', 'Gimnasio Alcobendas', 385, 293, NULL, b'0', NULL, '1º POOMSAE', 'Pinto', 'Sanmacario', 'Tres Cantos', '16', '', '28760', 'Avenida de la industria', '1980-09-20 00:00:00', '01686706R', 'Víctor', 'Masculino', b'1', '2024-09-21 13:29:24', b'0', b'0', NULL, 'Avenida España 25, Alcobendas', '14-12-2024', 'Torneo Alcobendas Navidades'),
(632, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Rojo', 'E3', 'España', 'Championdo', 483, 379, NULL, b'0', NULL, '4º POOMSAE', 'Sosa', 'Aristizabal', 'San Sebastián de los Reyes', '18', 'Ático derecha', '28456', 'Avenida Montes de Oca', '1972-10-14 00:00:00', '85234654N', 'Rubén', 'Masculino', b'1', '2024-03-30 12:06:29', b'0', b'0', NULL, 'Avenida Viñuelas 30 - Tres Cantos', '20-04-2024', 'Torneo Tres Cantos'),
(633, 'Sosa', 'Aristizabal', 'San Sebastián de los Reyes', '18', 'Ático derecha', '28456', 'Avenida Montes de Oca', '85234654N', 'Rubén', 'Blanco', 'E1', 'España', 'Gimnasio Tres Cantos', 483, 379, NULL, b'0', NULL, '1º POOMSAE', 'Acosta', 'Giménez', NULL, NULL, NULL, NULL, NULL, '2010-02-15 00:00:00', '', 'Daniel', 'Masculino', b'0', '2024-03-30 12:07:02', b'0', b'1', 'Padre', 'Avenida Viñuelas 30 - Tres Cantos', '20-04-2024', 'Torneo Tres Cantos'),
(634, 'Sosa', 'Aristizabal', 'San Sebastián de los Reyes', '18', 'Ático derecha', '28456', 'Avenida Montes de Oca', '85234654N', 'Rubén', 'Amarillo', 'INCLUSIVO', 'España', 'Gimnasio Alcobendas', 483, 379, NULL, b'0', NULL, 'INCLUSIVO', 'Merino', 'En El País', NULL, NULL, NULL, NULL, NULL, '1980-04-16 00:00:00', '', 'Manuel', 'Masculino', b'0', '2024-03-30 12:07:44', b'1', b'0', 'Tutor', 'Avenida Viñuelas 30 - Tres Cantos', '20-04-2024', 'Torneo Tres Cantos'),
(667, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Verde', 'B2', 'España', 'Championdo', 483, 379, NULL, b'0', NULL, '2º POOMSAE', 'Arietalaeanizbaezcoechea', 'Ottovordemgentschefelde', 'Cobeña', '1', '', '28863', 'Carretera de Fuente el Saz', '2000-05-05 00:00:00', '38345310D', 'Oliver', 'Masculino', b'1', '2024-04-01 18:34:05', b'0', b'0', NULL, 'Avenida Viñuelas 30 - Tres Cantos', '20-04-2024', 'Torneo Tres Cantos'),
(668, 'Arietalaeanizbaezcoechea', 'Ottovordemgentschefelde', 'Cobeña', '1', '', '28863', 'Carretera de Fuente el Saz', '38345310D', 'Oliver', 'Azul', 'E3', 'España', 'Championdo', 483, 379, NULL, b'0', NULL, '5º POOMSAE', 'Giménez', 'Sánchez', NULL, NULL, NULL, NULL, NULL, '2010-04-15 00:00:00', '12345678A', 'Daniel', 'Masculino', b'0', '2024-04-01 18:34:38', b'0', b'1', 'Padre', 'Avenida Viñuelas 30 - Tres Cantos', '20-04-2024', 'Torneo Tres Cantos'),
(669, 'Arietalaeanizbaezcoechea', 'Ottovordemgentschefelde', 'Cobeña', '1', '', '28863', 'Carretera de Fuente el Saz', '38345310D', 'Oliver', 'Amarillo', 'INCLUSIVO', 'España', 'Championdo', 483, 379, NULL, b'0', NULL, 'INCLUSIVO', 'Merino', '', NULL, NULL, NULL, NULL, NULL, '1980-09-18 00:00:00', '01010101B', 'Manuel', 'Masculino', b'0', '2024-04-01 18:35:14', b'1', b'0', 'Tutor', 'Avenida Viñuelas 30 - Tres Cantos', '20-04-2024', 'Torneo Tres Cantos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tournament_registration_seq`
--

CREATE TABLE `tournament_registration_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tournament_registration_seq`
--

INSERT INTO `tournament_registration_seq` (`next_val`) VALUES
(51);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `lastname` varchar(60) DEFAULT NULL,
  `second_lastname` varchar(60) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `fecha_nacimiento` datetime DEFAULT NULL,
  `sexo` varchar(9) DEFAULT NULL,
  `domicilio_calle` varchar(100) DEFAULT NULL,
  `domicilio_numero` varchar(30) DEFAULT NULL,
  `domicilio_otros` varchar(50) DEFAULT NULL,
  `domicilio_cp` varchar(10) DEFAULT NULL,
  `domicilio_localidad` varchar(50) DEFAULT NULL,
  `id_pais` int(11) DEFAULT 0,
  `password` varchar(60) NOT NULL,
  `fecha_alta` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `username_modificacion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`username`, `enabled`, `name`, `lastname`, `second_lastname`, `telefono`, `correo`, `fecha_nacimiento`, `sexo`, `domicilio_calle`, `domicilio_numero`, `domicilio_otros`, `domicilio_cp`, `domicilio_localidad`, `id_pais`, `password`, `fecha_alta`, `fecha_modificacion`, `username_modificacion`) VALUES
('01686706R', b'1', 'Víctor', 'Pinto', 'Sanmacario', '666555444', 'dusheff@hotmail.com', '1980-09-20 00:00:00', 'Masculino', 'Avenida de la industria', '16', '', '28760', 'Tres Cantos', 1, '$2a$10$LJx2i7UqU3nS7RD3yHXVueiaIlwFD9ClNK.TaQQpken./S6efvq0K', '2024-09-20 21:29:57', '2024-09-20 21:33:32', NULL),
('02262310F', b'1', 'Lidia María', 'Huertas', 'Cejudos', '', 'dusheff@hotmail.com', '1977-02-10 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$ZOwGdhbHD5OhtadFpkjKY.pNfpvgqDdJYcCDJKFbUJJHFUefaYuOG', '2024-01-14 18:38:03', '2024-01-25 21:44:41', NULL),
('05959715R', b'1', 'Damián', 'Usheff', 'Vellianitis', '637955254', 'dusheff@hotmail.com', '1976-10-30 00:00:00', 'Masculino', 'Calle Azorín', '18', 'Ático', '28863', 'Cobeña', 1, '$2a$10$mRX05pSePQGx9tun.srtdewjdrwq8A8LYmxy/hbupSxTSs0yXj7pi', '2022-11-13 23:09:22', '2024-01-18 20:47:58', NULL),
('13919945T', b'1', 'Roger', 'Rabbit', '', '666555444', 'dusheff@hotmail.com', '1975-11-30 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$hJHJv1YYG5Jh9OG9TyFgqezLhI.Wr2I9lz6yYGho9wQrDG4cY93tG', '2024-01-22 21:09:30', '2024-01-22 21:09:30', NULL),
('29283702X', b'1', 'Elena', 'Sánchez', 'SegundoApellido4', '666555444', 'dusheff@hotmail.com', '1990-03-08 00:00:00', 'Femenino', 'Avenida Montes de Oca', '10', 'Portal 4 2ºB', '28456', 'San Sebastián de los Reyes', 1, '$2a$10$sPSAVSDdLZP6Gtvm2tj2GOBEnUMqRcYjIAyCEaVeuuJvfVVpVjFxO', '2023-11-15 14:39:50', '2023-12-03 23:02:35', NULL),
('30098850S', b'1', 'Mariano', 'Pérez', 'Rodríguez', '666555444', 'dusheff@hotmail.com', '1980-01-01 00:00:00', 'Masculino', 'Calle de Calderón de la Barca', '5', '2º C', '28110', 'Algete', 1, '$2a$10$j.d7ju.rKcO.ZEpWns3U.eDpZCgwrNN/66p1KKSMRnotJ4ij/eG8O', '2023-11-12 21:06:56', '2023-12-04 09:38:05', NULL),
('31390063P', b'1', 'Alicia', 'En El País', 'De Las Maravillas', '666555444', 'dusheff@hotmail.com', '1982-01-01 00:00:00', 'Femenino', 'Entrevías', '12', '', '28023', 'Madrid', 1, '$2a$10$1IsNb6uqi6yQXEvVRsN1juPemAaSvIGeYYEe/.ubUNoK7mmqDhAb6', '2023-11-22 16:57:45', '2024-01-16 22:55:25', NULL),
('32338321E', b'1', 'Daniel', 'Usheff', '', '666555444', 'dusheff@hotmail.com', '1980-05-01 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$aLRc4u.KudBoivQCpFDXc.pzLiejuIjW8XLAfi..gLsHGOCN0XMXW', '2023-11-22 22:20:43', '2024-01-07 23:21:43', 'A29603305'),
('34911517R', b'1', 'Alberto Federico', 'Acosta', '', '666555444', 'dusheff@hotmail.com', '1997-01-01 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$D7LeQo6Msv8T.m.mbu4yw.s5VGswO7iLnc.pq.SKKxrepsNVfT1ZW', '2023-11-17 20:03:33', '2023-11-17 20:03:33', NULL),
('38345310D', b'1', 'Oliver', 'Arietalaeanizbaezcoechea', 'Ottovordemgentschefelde', '666555444', 'dusheff@hotmail.com', '2000-05-05 00:00:00', 'Masculino', 'Carretera de Fuente el Saz', '1', '', '28863', 'Cobeña', 1, '$2a$10$d6vkBEsJI/K5k4eK1obG7.d9COLdlRzj1IbohZ4QiIH7VxL7oYnEa', '2023-12-09 12:30:16', '2024-04-10 21:12:17', NULL),
('45136604R', b'1', 'Tomás', 'Apellido8', 'SegundoApellido8', '666555444', 'dusheff@hotmail.com', '1980-03-01 00:00:00', 'Masculino', 'Gran Vía de Hortaleza', '30', '', '28760', 'San Sebastián de los Reyes', 1, '$2a$10$dUahLB0UTop.fBwdZ2c6wOkNv07Mh3bd5ygXRmAjE8TO8z9D/6ene', '2023-11-22 21:43:07', '2023-11-22 21:43:07', NULL),
('50026139N', b'1', 'María de los Ángeles', 'Velázquez', 'SegundoApellido8', '666555444', 'dusheff@hotmail.com', '1992-04-26 00:00:00', 'Femenino', 'Avenida Montes de Oca', '1', '', '28006', 'Madrid', 1, '$2a$10$smovshMGLaJ.HaSHLXy9MO/3.lpNPJWvdbSGW.mAKhSbOj1oWX.m.', '2023-12-28 21:17:50', '2023-12-28 21:17:50', NULL),
('51931797M', b'1', 'Verónica', 'Santángelo', '', '', 'dusheff@hotmail.com', '1977-01-01 00:00:00', 'Femenino', '', '', '', '', '', 1, '$2a$10$q.tWjzSM51042ScZRd/mhOFq3A8gwVeM59jV4yxv.mdJ/49M/ZJRS', '2023-11-22 16:51:44', '2023-11-22 16:51:44', NULL),
('57429061Q', b'1', 'Jesús', 'López', 'Villar', '666555444', 'dusheff@hotmail.com', '1990-03-09 00:00:00', 'Masculino', 'Calle Orense', '12', '6ºC', '28040', 'Madrid', 1, '$2a$10$A9Lkg1foBITFB3pwQ6TkgeesnQGaYTL757.NfNO/8z9mTK5H6Eco6', '2024-01-10 16:31:39', '2024-01-10 16:31:39', NULL),
('58484019X', b'1', 'Hernán Pablo', 'Vilella', 'Aristizabal', '666555444', 'dusheff@hotmail.com', '1980-03-01 00:00:00', 'Masculino', 'Calle Azorín', '18', 'Ático', '28863', 'Cobeña', 1, '$2a$10$OtGqlkAtdq1yLzRpA3cwPeEufYWtu8hiJ2gB4sLFx31y0fW1llm1O', '2023-11-22 21:47:08', '2023-11-22 22:00:06', NULL),
('60404053G', b'1', 'Santiago', 'Lablanca', 'Bártulos', '666555444', 'dusheff@hotmail.com', '1985-02-15 00:00:00', 'Masculino', 'Avenida de Barcelona', '70', 'Portal 2 5ºA', '28006', 'Madrid', 1, '$2a$10$w.NpEWoSfDqwXPODtehTVudYnF4Ts3AO3v5oazReu5QSF6JwbxBFS', '2024-01-10 16:40:36', '2024-01-10 16:40:36', NULL),
('63605674W', b'1', '', '', '', '', 'dusheff@hotmail.com', '1998-01-01 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$WdY17Q9oeniz7Chc5l8RGu6sMNPtRQyFviQE/SLgInw7BX3HTRiL2', '2023-11-15 14:40:39', '2023-11-24 00:39:02', '30098850S'),
('71620840J', b'1', 'Antonio', 'Pinto', 'SegundoApellido5', '666555444', 'dusheff@hotmail.com', '1978-01-01 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$Pib8waZz8cE2XcmKawZN2.a4WgoFup9V.lSEdxdo2qiX/9e9mTl8O', '2023-11-22 16:46:17', '2023-11-24 00:58:21', NULL),
('81824203R', b'1', 'Antonio', 'Guillén', 'Martín', '', 'dusheff@hotmail.com', '1990-03-25 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$GqfADVjflQnUV852JjpK1O9zreHRwooTHPTJ0r/K0Udo3UE9.fDIO', '2024-01-24 08:21:52', '2024-01-24 08:21:52', NULL),
('85234654N', b'1', 'Rubén', 'Sosa', 'Aristizabal', '666555444', 'dusheff@hotmail.com', '1972-10-14 00:00:00', 'Masculino', 'Avenida Montes de Oca', '18', 'Ático derecha', '28456', 'San Sebastián de los Reyes', 1, '$2a$10$o7iYIn4hXzGnoPwyGcCvwOeMXgfcKO6CVzxC31MXLL/xeFBP00i9W', '2024-03-17 13:35:20', '2024-03-19 23:55:07', NULL),
('92876960Q', b'1', 'María de la Ascención', 'Cejudo', 'Gil', '666555443', 'dusheff@hotmail.com', '1983-06-25 00:00:00', 'Femenino', 'Torrevieja', '10', '3ºA', 'Madrid', '28030', 1, '$2a$10$ljp0lXaVJIvLMtgRF/Ci4OhOcTvCTCjGJcLI5WYJpQwNcaksc0G2K', '2023-11-23 23:04:41', '2023-11-23 23:07:01', NULL),
('A29603305', b'1', 'Ana María', 'Aranda', 'Alvarado', '666555444', 'damianjava@gmail.com', '1998-01-01 00:00:00', 'Masculino', 'Calle Miguel Hernández', '22', '', '28600', 'Alcobendas', 1, '$2a$10$XN08IAqkdqYfmST4oBJk3ubk0Upe9xcgCIK3wJtIcy5l2b46Zsebi', '2023-11-17 19:11:16', '2024-01-16 22:40:41', NULL),
('Q9754068F', b'1', 'José Luis', 'Gonzalo', 'Rodríguez', '666555444', 'damianjava@gmail.com', '1970-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, 0, '$2a$10$28IUfGtSQPJ/QxMAOlluKO4hULq6qdNFmjVFOS8oIbzCFBsXbMWam', '2023-11-23 23:15:03', '2023-11-23 23:15:03', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_gym`
--

CREATE TABLE `user_gym` (
  `id` int(11) NOT NULL,
  `date_add` datetime DEFAULT NULL,
  `id_gym` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `username_add` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_gym`
--

INSERT INTO `user_gym` (`id`, `date_add`, `id_gym`, `username`, `username_add`) VALUES
(923, '2024-01-28 19:08:41', 102, '30098850S', '05959715R'),
(925, '2024-01-28 19:28:13', 7, '02262310F', '05959715R'),
(926, '2024-01-28 19:28:19', 631, '02262310F', '05959715R'),
(929, '2024-01-28 21:13:40', 631, '30098850S', '02262310F'),
(930, '2024-02-04 14:20:04', 483, 'Q9754068F', '05959715R'),
(931, '2024-02-04 14:20:35', 385, 'A29603305', '05959715R'),
(932, '2024-02-04 17:11:29', 7, '34911517R', '02262310F');

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
(2, 'ROLE_ADMIN', '02262310F'),
(430, 'ROLE_ADMIN', '13919945T'),
(3, 'ROLE_ADMIN', '30098850S'),
(418, 'ROLE_ADMIN', '32338321E'),
(15, 'ROLE_ADMIN', 'A29603305'),
(28, 'ROLE_ADMIN', 'Q9754068F'),
(1, 'ROLE_ROOT', '05959715R'),
(1099, 'ROLE_USER', '01686706R'),
(19, 'ROLE_USER', '29283702X'),
(22, 'ROLE_USER', '31390063P'),
(17, 'ROLE_USER', '34911517R'),
(207, 'ROLE_USER', '38345310D'),
(4, 'ROLE_USER', '45136604R'),
(243, 'ROLE_USER', '50026139N'),
(21, 'ROLE_USER', '51931797M'),
(306, 'ROLE_USER', '57429061Q'),
(25, 'ROLE_USER', '58484019X'),
(307, 'ROLE_USER', '60404053G'),
(30, 'ROLE_USER', '63605674W'),
(20, 'ROLE_USER', '71620840J'),
(391, 'ROLE_USER', '81824203R'),
(498, 'ROLE_USER', '85234654N'),
(27, 'ROLE_USER', '92876960Q');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_role_seq`
--

CREATE TABLE `user_role_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_role_seq`
--

INSERT INTO `user_role_seq` (`next_val`) VALUES
(101);

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
-- Volcado de datos para la tabla `util`
--

INSERT INTO `util` (`clave`, `codigo_gimnasio`, `valor`) VALUES
('inscripciones.campeonato.borrar', 7, 'true'),
('inscripciones.campeonato.borrar', 102, 'true'),
('inscripciones.campeonato.borrar', 385, 'true'),
('inscripciones.campeonato.borrar', 483, 'true'),
('inscripciones.cuenta.bancaria', 7, 'false'),
('inscripciones.cuenta.bancaria', 102, 'true'),
('inscripciones.cuenta.bancaria', 385, 'true'),
('inscripciones.cuenta.bancaria', 483, 'true'),
('inscripciones.taekwondo.borrar', 7, 'true'),
('inscripciones.taekwondo.borrar', 102, 'true'),
('inscripciones.taekwondo.borrar', 385, 'true'),
('inscripciones.taekwondo.borrar', 483, 'true');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `util_manager`
--

CREATE TABLE `util_manager` (
  `id` int(11) NOT NULL,
  `email` varchar(200) DEFAULT NULL,
  `email_host` varchar(200) DEFAULT NULL,
  `email_port` varchar(5) DEFAULT NULL,
  `host_page_name` varchar(200) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `util_manager`
--

INSERT INTO `util_manager` (`id`, `email`, `email_host`, `email_port`, `host_page_name`, `password`) VALUES
(912, 'damianjava@gmail.com', 'smtp.gmail.com', '587', 'http://localhost:8080', 'ylmfgftrkhhzneui');

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
-- Indices de la tabla `document_manager`
--
ALTER TABLE `document_manager`
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
-- Indices de la tabla `gimnasio_menu2`
--
ALTER TABLE `gimnasio_menu2`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `inscripcion_taekwondo`
--
ALTER TABLE `inscripcion_taekwondo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `mandato`
--
ALTER TABLE `mandato`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `menu1`
--
ALTER TABLE `menu1`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `menu2`
--
ALTER TABLE `menu2`
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
-- Indices de la tabla `token`
--
ALTER TABLE `token`
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
-- Indices de la tabla `tournament_registration`
--
ALTER TABLE `tournament_registration`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `user_gym`
--
ALTER TABLE `user_gym`
  ADD PRIMARY KEY (`id`);

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
-- Indices de la tabla `util_manager`
--
ALTER TABLE `util_manager`
  ADD PRIMARY KEY (`id`);

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
