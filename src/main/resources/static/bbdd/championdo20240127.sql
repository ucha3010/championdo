-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-01-2024 a las 18:00:29
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
(923);

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
(35, b'1', 31, 3, 0, NULL),
(40, b'1', 38, 1, 0, NULL),
(43, b'1', 41, 1, 0, NULL),
(71, b'1', 69, 1, 0, NULL),
(74, b'1', 72, 1, 0, NULL),
(77, b'1', 75, 1, 0, NULL),
(80, b'1', 78, 1, 0, NULL),
(83, b'1', 81, 1, 0, NULL),
(86, b'1', 84, 1, 0, NULL),
(116, b'1', 114, 1, 0, NULL),
(119, b'1', 117, 1, 0, NULL),
(122, b'1', 120, 1, 0, NULL),
(125, b'1', 123, 1, 0, NULL),
(128, b'1', 126, 1, 0, NULL),
(131, b'1', 129, 1, 0, NULL),
(134, b'1', 132, 1, 0, NULL),
(137, b'1', 135, 1, 0, NULL),
(144, b'1', 140, 1, 0, NULL),
(146, b'1', 138, 1, 0, NULL),
(149, b'1', 147, 1, 0, NULL),
(154, b'1', 151, 1, 0, NULL),
(158, b'1', 155, 1, 0, NULL),
(167, b'1', 165, 1, 0, NULL),
(170, b'1', 168, 1, 0, NULL),
(175, b'1', 173, 1, 0, NULL),
(182, b'1', 180, 1, 0, NULL),
(189, b'1', 187, 1, 0, NULL),
(191, b'1', 185, 1, 0, NULL),
(194, b'1', 192, 1, 0, NULL),
(197, b'1', 195, 1, 0, NULL),
(200, b'1', 198, 1, 0, 'inscripcionMandato'),
(203, b'1', 201, 1, 0, 'inscripcionMandato'),
(206, b'1', 204, 1, 0, 'inscripcionGimnasio'),
(212, b'1', 210, 1, 0, 'inscripcionTaekwondo'),
(215, b'1', 213, 1, 0, 'inscripcionTaekwondo'),
(220, b'1', 218, 1, 0, 'inscripcionTaekwondo'),
(223, b'1', 221, 1, 0, 'inscripcionTaekwondo'),
(226, b'1', 224, 1, 0, 'inscripcionTaekwondo'),
(229, b'1', 227, 1, 0, 'inscripcionTaekwondo'),
(232, b'1', 230, 1, 0, 'inscripcionTaekwondo'),
(238, b'1', 233, 1, 0, 'inscripcionTaekwondo'),
(242, b'1', 239, 1, 0, 'inscripcionTaekwondo'),
(248, b'1', 244, 2, 0, 'inscripcionTaekwondo'),
(252, b'1', 250, 1, 0, 'inscripcionTaekwondo'),
(255, b'1', 253, 1, 0, 'inscripcionTaekwondo'),
(258, b'1', 256, 1, 0, 'inscripcionTaekwondo'),
(268, b'1', 266, 1, 0, 'inscripcionTaekwondo'),
(271, b'1', 269, 1, 0, 'inscripcionTaekwondo'),
(274, b'1', 272, 1, 0, 'inscripcionTaekwondo'),
(277, b'1', 275, 1, 0, 'inscripcionTaekwondo'),
(302, b'1', 300, 1, 0, 'inscripcionTaekwondo'),
(305, b'1', 303, 1, 0, 'inscripcionTaekwondo'),
(318, b'1', 316, 1, 0, 'inscripcionTaekwondo'),
(329, b'1', 327, 1, 0, 'inscripcionTaekwondo'),
(332, b'1', 330, 1, 0, 'inscripcionTaekwondo'),
(335, b'1', 333, 1, 0, 'inscripcionTaekwondo'),
(338, b'1', 336, 1, 0, 'inscripcionMandato'),
(341, b'1', 262, 1, 0, 'inscripcionMandato'),
(352, b'1', 350, 1, 0, 'inscripcionMandato'),
(355, b'1', 353, 1, 0, 'inscripcionMandato'),
(359, b'1', 356, 3, 0, 'inscripcionMandato'),
(362, b'1', 360, 1, 0, 'inscripcionTaekwondo'),
(366, b'1', 363, 1, 0, 'inscripcionTaekwondo'),
(375, b'1', 372, 1, 0, 'inscripcionMandato'),
(378, b'1', 376, 1, 0, 'inscripcionMandato'),
(385, b'1', 383, 1, 0, 'inscripcionTaekwondo'),
(389, b'1', 387, 1, 0, 'inscripcionTaekwondo'),
(394, b'1', 392, 1, 0, 'inscripcionTaekwondo'),
(397, b'1', 395, 1, 0, 'inscripcionTaekwondo'),
(400, b'1', 398, 1, 0, 'inscripcionTaekwondo'),
(404, b'1', 402, 1, 0, 'inscripcionTaekwondo'),
(408, b'1', 406, 1, 0, 'inscripcionTaekwondo');

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
(396, 'E5F5YY', '31390063P', '2024-01-24 23:26:56', '2024-01-24 23:11:56', 395, 'inscripcionTaekwondo', 'formularioInscFinalizada', 483),
(399, 'AVQZBG', '31390063P', '2024-01-24 23:31:34', '2024-01-24 23:16:34', 398, 'inscripcionTaekwondo', 'formularioInscFinalizada', 483),
(403, '2WINL9', '31390063P', '2024-01-24 23:33:14', '2024-01-24 23:18:14', 402, 'inscripcionTaekwondo', 'formularioInscFinalizada', 483),
(407, 'K9FVK9', '31390063P', '2024-01-24 23:37:21', '2024-01-24 23:22:21', 406, 'inscripcionTaekwondo', 'formularioInscFinalizada', 483),
(411, 'HYV7M9', '31390063P', '2024-01-25 20:30:37', '2024-01-25 20:15:37', 410, 'inscripcionTaekwondo', 'formularioInscFinalizada', 385),
(413, 'GW56D1', '31390063P', '2024-01-25 20:33:21', '2024-01-25 20:18:21', 412, 'inscripcionTaekwondo', 'formularioInscFinalizada', 7);

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
(102, 'Gimnasio Algete', 'Pérez', 'Rodríguez', 100, '30098850S', 'dusheff@hotmail.com', 'Avenida Montes de Oca', '28760', 'Algete', '', '', b'1', '2023-11-12 21:06:56', '2023-11-23 23:13:12', '1979-01-01 00:00:00', 'Mariano', '666555444', '05959715R', 1, 'smtp.gmail.com', 'ylmfgftrkhhzneui', '587'),
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
(913, '2024-01-21 01:51:28', 7, 582, '02262310F'),
(914, '2024-01-21 01:51:28', 7, 583, '02262310F'),
(915, '2024-01-21 01:51:28', 7, 584, '02262310F'),
(916, '2024-01-21 01:52:41', 102, 583, '30098850S'),
(917, '2024-01-21 01:53:14', 385, 582, 'A29603305'),
(918, '2024-01-21 01:53:14', 385, 583, 'A29603305'),
(919, '2024-01-21 01:53:14', 385, 584, 'A29603305'),
(920, '2024-01-21 01:53:42', 483, 582, 'Q9754068F'),
(921, '2024-01-21 01:53:42', 483, 584, 'Q9754068F');

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
(419);

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
  `codigo_gimnasio` int(11) NOT NULL,
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
  `id_torneo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inscripcion`
--

INSERT INTO `inscripcion` (`id`, `inscripcion_propia`, `inscripcion_menor`, `inscripcion_inclusiva`, `fecha_inscripcion`, `codigo_gimnasio`, `fecha_campeonato`, `nombre_campeonato`, `direccion_campeonato`, `categoria`, `nombre_inscripto`, `apellido1inscripto`, `apellido2inscripto`, `dni_inscripto`, `fecha_nacimiento_inscripto`, `sexo_inscripto`, `domicilio_calle_inscripto`, `domicilio_numero_inscripto`, `domicilio_otros_inscripto`, `domicilio_localidad_inscripto`, `domicilio_cp_inscripto`, `gimnasio`, `pais`, `cinturon`, `poomsae`, `nombre_autorizador`, `apellido1autorizador`, `apellido2autorizador`, `dni_autorizador`, `calidad`, `domicilio_calle_autorizador`, `domicilio_numero_autorizador`, `domicilio_otros_autorizador`, `domicilio_localidad_autorizador`, `domicilio_cp_autorizador`, `pago_realizado`, `fecha_pago`, `notas`, `id_torneo`) VALUES
(371, b'0', b'1', b'0', '2024-01-21 01:55:25', 7, '10-02-2014', 'Torneo Cobeña', 'Calle del Olivo 7 - Cobeña', 'B2', 'Daniel', 'Salvador', 'En El País', '12345678A', '2015-06-05 00:00:00', 'Masculino', NULL, NULL, NULL, NULL, NULL, 'Gimnasio Algete', 'España', 'Verde', '2º POOMSAE', 'Alicia', 'En El País', 'De Las Maravillas', '31390063P', 'Madre', 'Entrevías', '12', '', 'Madrid', '28023', b'0', NULL, NULL, 284),
(414, b'1', b'0', b'0', '2024-01-25 20:19:16', 7, '10-02-2014', 'Torneo Cobeña', 'Calle del Olivo 7 - Cobeña', 'D1', 'Alicia', 'En El País', 'De Las Maravillas', '31390063P', '1982-01-01 00:00:00', 'Femenino', 'Entrevías', '12', '', 'Madrid', '28023', 'Gimnasio Cobeña', 'España', 'Blanco', '1º POOMSAE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, 284),
(415, b'0', b'1', b'0', '2024-01-25 20:20:16', 7, '10-02-2014', 'Torneo Cobeña', 'Calle del Olivo 7 - Cobeña', 'E2', 'Daniel', 'Giménez', 'En El País', '12345678A', '2010-11-15 00:00:00', 'Masculino', NULL, NULL, NULL, NULL, NULL, 'Gimnasio Cobeña', 'España', 'Verde Azul', '2º POOMSAE', 'Alicia', 'En El País', 'De Las Maravillas', '31390063P', 'Madre', 'Entrevías', '12', '', 'Madrid', '28023', b'0', NULL, NULL, 284),
(416, b'0', b'0', b'1', '2024-01-25 20:38:34', 7, '10-02-2014', 'Torneo Cobeña', 'Calle del Olivo 7 - Cobeña', 'INCLUSIVO', 'Manuel', 'Merino', 'Sánchez', '', '1991-11-10 00:00:00', 'Masculino', NULL, NULL, NULL, NULL, NULL, 'Gimnasio Cobeña', 'España', 'Blanco', 'INCLUSIVO', 'Alicia', 'En El País', 'De Las Maravillas', '31390063P', 'Madre', 'Entrevías', '12', '', 'Madrid', '28023', b'0', NULL, NULL, 284),
(417, b'0', b'1', b'0', '2024-01-25 20:40:32', 7, '10-02-2014', 'Torneo Cobeña', 'Calle del Olivo 7 - Cobeña', 'B3', 'Daniel', 'Giménez', 'En El País', '', '2016-11-15 00:00:00', 'Masculino', NULL, NULL, NULL, NULL, NULL, 'Gimnasio Cobeña', 'España', 'Azul', '3º POOMSAE', 'Alicia', 'En El País', 'De Las Maravillas', '31390063P', 'Madre', 'Entrevías', '12', '', 'Madrid', '28023', b'0', NULL, NULL, 284);

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
(69, '2023-12-02 01:14:41', 385, 'Antonio', 'Pinto', 'SegundoApellido5', '71620840J', 'dusheff@hotmail.com', '1978-01-01 00:00:00', '666555444', 'Masculino', 'Calle Azorín', '18', '', 'Cobeña', '28863', 'España', NULL, 'Rojo', b'1', b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', b'1', 'Damián Titu banco', 'ES1234567890123456', '', NULL, b'1', '.pdf', b'0', 'Gimnasio Alcobendas'),
(316, '2024-01-13 00:23:02', 7, 'Santiago', 'Lablanca', 'Bártulos', '60404053G', 'dusheff@hotmail.com', '1985-02-15 00:00:00', '666555444', 'Masculino', 'Avenida de Barcelona', '70', 'Portal 2 5ºA', 'Madrid', '28006', 'España', NULL, 'Blanco', b'1', b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', b'1', 'Damián Titu banco', 'ES1234567890123456', '', NULL, b'1', NULL, b'0', NULL),
(363, '2024-01-21 00:49:47', 7, 'Alicia', 'En El País', 'De Las Maravillas', '31390063P', 'dusheff@hotmail.com', NULL, '666555444', NULL, 'Entrevías', '12', '', 'Madrid', '28023', NULL, 'Madre', NULL, b'0', b'1', b'1', 'Daniel', 'Salvador', 'En El País', '', '2015-06-15 00:00:00', 'Masculino', 'España', 'Naranja', b'1', b'1', 'Damián Titu banco', 'ES1234567890123456', '', NULL, b'1', '.pdf', b'1', 'Gimnasio Cobeña'),
(387, '2024-01-22 21:10:36', 385, 'Roger', 'Rabbit', '', '13919945T', 'dusheff@hotmail.com', '1975-11-30 00:00:00', '666555444', 'Masculino', 'Barranco del agua', '', '', 'Alcobendas', '28874', 'España', NULL, 'Azul', b'1', b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', b'1', 'Damián Titu banco', 'ES1234567890123456', '', NULL, b'1', NULL, b'0', 'Gimnasio Alcobendas'),
(410, '2024-01-25 20:13:12', 385, 'Alicia', 'En El País', 'De Las Maravillas', '31390063P', 'dusheff@hotmail.com', '1982-01-01 00:00:00', '666555444', 'Femenino', 'Entrevías', '12', '', 'Madrid', '28023', 'España', NULL, 'Verde', b'1', b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', b'1', 'Damián Titu banco', 'ES1234567890123456', '', NULL, b'0', NULL, b'0', 'Gimnasio Alcobendas'),
(412, '2024-01-25 20:18:21', 7, 'Alicia', 'En El País', 'De Las Maravillas', '31390063P', 'dusheff@hotmail.com', NULL, '666555444', NULL, 'Entrevías', '12', '', 'Madrid', '28023', NULL, 'Tutor', NULL, b'0', b'1', b'1', 'Manuel', 'Merino', '', '', '1991-11-16 00:00:00', 'Masculino', 'España', 'Blanco', b'1', b'0', NULL, NULL, NULL, NULL, b'0', NULL, b'0', 'Gimnasio Cobeña');

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
  `licencia_abonada` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `mandato`
--

INSERT INTO `mandato` (`id`, `adulto`, `codigo_gimnasio`, `apellido1autorizado`, `apellido1mandante`, `apellido2autorizado`, `apellido2mandante`, `calidad`, `calidad_otro`, `correo_mandante`, `dni_autorizado`, `dni_mandante`, `domicilio_calle`, `domicilio_cp`, `domicilio_localidad`, `domicilio_numero`, `domicilio_otros`, `fecha_alta`, `mandato_firmado`, `nombre_autorizado`, `nombre_mandante`, `pais`, `temporada`, `licencia_abonada`) VALUES
(310, b'1', 7, NULL, 'Lablanca', NULL, 'Bártulos', NULL, NULL, 'dusheff@hotmail.com', NULL, '60404053G', 'Avenida de Barcelona', '28006', 'Madrid', '70', 'Portal 2 5ºA', '2024-01-10 16:59:48', b'0', NULL, 'Santiago', 'España', '2024', b'0'),
(319, b'1', 7, NULL, 'Lablanca', NULL, 'Bártulos', NULL, NULL, 'dusheff@hotmail.com', NULL, '60404053G', 'Avenida de Barcelona', '28006', 'Madrid', '70', 'Portal 2 5ºA', '2024-01-13 00:23:19', b'1', NULL, 'Santiago', 'España', '2024', b'0'),
(376, b'0', 483, 'Salazar', 'En El País', 'En El País', 'De Las Maravillas', 'Madre', '', 'dusheff@hotmail.com', '12345678A', '31390063P', 'Entrevías', '28023', 'Madrid', '12', '', '2024-01-21 01:57:29', b'1', 'Daniel', 'Alicia', 'España', '2024', b'0'),
(390, b'1', 0, NULL, 'Rabbit', NULL, '', NULL, NULL, 'dusheff@hotmail.com', NULL, '13919945T', 'Barranco del agua', '28874', 'Alcobendas', '', '', '2024-01-22 21:10:52', b'1', NULL, 'Roger', 'España', '2024', b'0'),
(409, b'1', 0, NULL, 'En El País', NULL, 'De Las Maravillas', NULL, NULL, 'dusheff@hotmail.com', NULL, '31390063P', 'Entrevías', '28023', 'Madrid', '12', '', '2024-01-24 23:22:33', b'1', NULL, 'Alicia', 'España', '2024', b'0');

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
(286, 'Torneo Cobeña infantil', 'Calle del Olivo 7 - Cobeña', '2024-06-08 00:00:00', '2024-02-01 00:00:00', '2024-05-31 23:59:59', 7, b'0', b'0', b'1'),
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
('02262310F', b'1', 'Lidia María', 'Huertas', 'Cejudos', '', 'dusheff@hotmail.com', '1977-02-10 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$ZOwGdhbHD5OhtadFpkjKY.pNfpvgqDdJYcCDJKFbUJJHFUefaYuOG', '2024-01-14 18:38:03', '2024-01-25 21:44:41', NULL),
('05959715R', b'1', 'Damián', 'Usheff', 'Vellianitis', '637955254', 'dusheff@hotmail.com', '1976-10-30 00:00:00', 'Masculino', 'Calle Azorín', '18', 'Ático', '28863', 'Cobeña', 1, '$2a$10$mRX05pSePQGx9tun.srtdewjdrwq8A8LYmxy/hbupSxTSs0yXj7pi', '2022-11-13 23:09:22', '2024-01-18 20:47:58', NULL),
('13919945T', b'1', 'Roger', 'Rabbit', '', '666555444', 'dusheff@hotmail.com', '1975-11-30 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$hJHJv1YYG5Jh9OG9TyFgqezLhI.Wr2I9lz6yYGho9wQrDG4cY93tG', '2024-01-22 21:09:30', '2024-01-22 21:09:30', NULL),
('29283702X', b'1', 'Elena', 'Sánchez', 'SegundoApellido4', '666555444', 'dusheff@hotmail.com', '1990-03-08 00:00:00', 'Femenino', 'Avenida Montes de Oca', '10', 'Portal 4 2ºB', '28456', 'San Sebastián de los Reyes', 1, '$2a$10$sPSAVSDdLZP6Gtvm2tj2GOBEnUMqRcYjIAyCEaVeuuJvfVVpVjFxO', '2023-11-15 14:39:50', '2023-12-03 23:02:35', NULL),
('30098850S', b'1', 'Mariano', 'Pérez', 'Rodríguez', '666555444', 'dusheff@hotmail.com', '1980-01-01 00:00:00', 'Masculino', 'Calle de Calderón de la Barca', '5', '2º C', '28110', 'Algete', 1, '$2a$10$j.d7ju.rKcO.ZEpWns3U.eDpZCgwrNN/66p1KKSMRnotJ4ij/eG8O', '2023-11-12 21:06:56', '2023-12-04 09:38:05', NULL),
('31390063P', b'1', 'Alicia', 'En El País', 'De Las Maravillas', '666555444', 'dusheff@hotmail.com', '1982-01-01 00:00:00', 'Femenino', 'Entrevías', '12', '', '28023', 'Madrid', 1, '$2a$10$1IsNb6uqi6yQXEvVRsN1juPemAaSvIGeYYEe/.ubUNoK7mmqDhAb6', '2023-11-22 16:57:45', '2024-01-16 22:55:25', NULL),
('32338321E', b'1', 'Daniel', 'Usheff', '', '666555444', 'dusheff@hotmail.com', '1980-05-01 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$aLRc4u.KudBoivQCpFDXc.pzLiejuIjW8XLAfi..gLsHGOCN0XMXW', '2023-11-22 22:20:43', '2024-01-07 23:21:43', 'A29603305'),
('34911517R', b'1', 'Alberto Federico', 'Acosta', '', '666555444', 'dusheff@hotmail.com', '1997-01-01 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$D7LeQo6Msv8T.m.mbu4yw.s5VGswO7iLnc.pq.SKKxrepsNVfT1ZW', '2023-11-17 20:03:33', '2023-11-17 20:03:33', NULL),
('38345310D', b'1', 'Oliver', 'Arietalaeanizbaezcoechea', 'Ottovordemgentschefelde', '666555444', 'dusheff@hotmail.com', '2000-05-05 00:00:00', 'Masculino', 'Carretera de Fuente el Saz', '1', '', '28863', 'Cobeña', 1, '$2a$10$sz9J6SbTTDPPkIxQxiZaEubSwsi89eVadIef/jlk5ODOnARZPcpJW', '2023-12-09 12:30:16', '2023-12-09 12:30:16', NULL),
('45136604R', b'1', 'Tomás', 'Apellido8', 'SegundoApellido8', '666555444', 'dusheff@hotmail.com', '1980-03-01 00:00:00', 'Masculino', 'Gran Vía de Hortaleza', '30', '', '28760', 'San Sebastián de los Reyes', 1, '$2a$10$dUahLB0UTop.fBwdZ2c6wOkNv07Mh3bd5ygXRmAjE8TO8z9D/6ene', '2023-11-22 21:43:07', '2023-11-22 21:43:07', NULL),
('50026139N', b'1', 'María de los Ángeles', 'Velázquez', 'SegundoApellido8', '666555444', 'dusheff@hotmail.com', '1992-04-26 00:00:00', 'Femenino', 'Avenida Montes de Oca', '1', '', '28006', 'Madrid', 1, '$2a$10$smovshMGLaJ.HaSHLXy9MO/3.lpNPJWvdbSGW.mAKhSbOj1oWX.m.', '2023-12-28 21:17:50', '2023-12-28 21:17:50', NULL),
('51931797M', b'1', 'Verónica', 'Santángelo', '', '', 'dusheff@hotmail.com', '1977-01-01 00:00:00', 'Femenino', '', '', '', '', '', 1, '$2a$10$q.tWjzSM51042ScZRd/mhOFq3A8gwVeM59jV4yxv.mdJ/49M/ZJRS', '2023-11-22 16:51:44', '2023-11-22 16:51:44', NULL),
('57429061Q', b'1', 'Jesús', 'López', 'Villar', '666555444', 'dusheff@hotmail.com', '1990-03-09 00:00:00', 'Masculino', 'Calle Orense', '12', '6ºC', '28040', 'Madrid', 1, '$2a$10$A9Lkg1foBITFB3pwQ6TkgeesnQGaYTL757.NfNO/8z9mTK5H6Eco6', '2024-01-10 16:31:39', '2024-01-10 16:31:39', NULL),
('58484019X', b'1', 'Hernán Pablo', 'Vilella', 'Aristizabal', '666555444', 'dusheff@hotmail.com', '1980-03-01 00:00:00', 'Masculino', 'Calle Azorín', '18', 'Ático', '28863', 'Cobeña', 1, '$2a$10$OtGqlkAtdq1yLzRpA3cwPeEufYWtu8hiJ2gB4sLFx31y0fW1llm1O', '2023-11-22 21:47:08', '2023-11-22 22:00:06', NULL),
('60404053G', b'1', 'Santiago', 'Lablanca', 'Bártulos', '666555444', 'dusheff@hotmail.com', '1985-02-15 00:00:00', 'Masculino', 'Avenida de Barcelona', '70', 'Portal 2 5ºA', '28006', 'Madrid', 1, '$2a$10$w.NpEWoSfDqwXPODtehTVudYnF4Ts3AO3v5oazReu5QSF6JwbxBFS', '2024-01-10 16:40:36', '2024-01-10 16:40:36', NULL),
('63605674W', b'1', '', '', '', '', 'dusheff@hotmail.com', '1998-01-01 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$WdY17Q9oeniz7Chc5l8RGu6sMNPtRQyFviQE/SLgInw7BX3HTRiL2', '2023-11-15 14:40:39', '2023-11-24 00:39:02', '30098850S'),
('71620840J', b'1', 'Antonio', 'Pinto', 'SegundoApellido5', '666555444', 'dusheff@hotmail.com', '1978-01-01 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$Pib8waZz8cE2XcmKawZN2.a4WgoFup9V.lSEdxdo2qiX/9e9mTl8O', '2023-11-22 16:46:17', '2023-11-24 00:58:21', NULL),
('81824203R', b'1', 'Antonio', 'Guillén', 'Martín', '', 'dusheff@hotmail.com', '1990-03-25 00:00:00', 'Masculino', '', '', '', '', '', 1, '$2a$10$GqfADVjflQnUV852JjpK1O9zreHRwooTHPTJ0r/K0Udo3UE9.fDIO', '2024-01-24 08:21:52', '2024-01-24 08:21:52', NULL),
('92876960Q', b'1', 'María de la Ascención', 'Cejudo', 'Gil', '666555443', 'dusheff@hotmail.com', '1983-06-25 00:00:00', 'Femenino', 'Torrevieja', '10', '3ºA', 'Madrid', '28030', 1, '$2a$10$ljp0lXaVJIvLMtgRF/Ci4OhOcTvCTCjGJcLI5WYJpQwNcaksc0G2K', '2023-11-23 23:04:41', '2023-11-23 23:07:01', NULL),
('A29603305', b'1', 'Ana María', 'Aranda', 'Alvarado', '666555444', 'damianjava@gmail.com', '1998-01-01 00:00:00', 'Masculino', 'Calle Miguel Hernández', '22', '', '28600', 'Alcobendas', 1, '$2a$10$XN08IAqkdqYfmST4oBJk3ubk0Upe9xcgCIK3wJtIcy5l2b46Zsebi', '2023-11-17 19:11:16', '2024-01-16 22:40:41', NULL),
('Q9754068F', b'1', 'José Luis', 'Gonzalo', 'Rodríguez', '666555444', 'damianjava@gmail.com', '1970-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, 0, '$2a$10$28IUfGtSQPJ/QxMAOlluKO4hULq6qdNFmjVFOS8oIbzCFBsXbMWam', '2023-11-23 23:15:03', '2023-11-23 23:15:03', NULL);

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
(3, 'ROLE_ADMIN', '30098850S'),
(418, 'ROLE_ADMIN', '32338321E'),
(15, 'ROLE_ADMIN', 'A29603305'),
(28, 'ROLE_ADMIN', 'Q9754068F'),
(1, 'ROLE_ROOT', '05959715R'),
(386, 'ROLE_USER', '13919945T'),
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
(27, 'ROLE_USER', '92876960Q');

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
