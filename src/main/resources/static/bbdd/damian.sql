-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-06-2022 a las 14:11:22
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
-- Base de datos: `damian`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `name`) VALUES
(1, 'Resultados'),
(2, 'Cercanía'),
(3, 'Cuestionar'),
(4, 'Contribución'),
(5, 'Resolutivo'),
(6, 'Curiosidad');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `hours` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `course`
--

INSERT INTO `course` (`id`, `description`, `hours`, `name`, `price`) VALUES
(1, 'Bines no permitidos para enviar mensaje ayuda cuando el cliente no finaliza compra', 10, 'Damián Usheff Vellianitis', 56),
(2, 'Bines portugal 8 posiciones', 5, 'Juan Pérez', 30),
(3, 'Bines regalo 8 posiciones', 3, 'Alfredo Distéfano', 24),
(4, 'Secretario', 6, 'Sergio Gómez', 45);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrada`
--

CREATE TABLE `entrada` (
  `id` int(11) NOT NULL,
  `categoria` int(11) DEFAULT NULL,
  `detalle` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `subcategoria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `entrada`
--

INSERT INTO `entrada` (`id`, `categoria`, `detalle`, `fecha`, `subcategoria`) VALUES
(19, 1, 'Proyecto palancas. Debe subir el 10/04/2022. A fecha 01/04/2022 ya está todo preparado para subida.', '2022-04-01 00:00:00', 1),
(20, 1, 'Me entregan información a realizar para el proyecto de palancas. Elaboro documentación correspondiente a la funcionalidad así como todas las pruebas y con su cobertura correspondiente.', '2022-03-16 00:00:00', 2),
(21, 1, 'Cambio a realizar en operativa de exenciones explicado a muy alto nivel y para subirse a producción en menos de dos semanas. Cambio llevado a cabo según se esperaba.', '2022-03-28 00:00:00', 3),
(22, 1, 'Ante la urgencia de eliminar los paquetes log4j me dan un plan de acción. Antes de comenzar me anticipo e indico que, si bien el plan lo puedo llevar adelante de esa forma, el tiempo que me generaría probablemente exceda al tiempo que tenemos para resolverlo. Presento un nuevo plan mediante el cual los tiempos se acrotan y es más factible llegar a cumplir.', '2022-04-21 00:00:00', 4),
(23, 1, 'Me dan una modificación a realizarse y me indican el punto exacto donde tengo que insertar una llamada a una librería de otra UUAA. Mirando bien el código detecto que poner la llamada en ese punto haría que se llame siempre a esta librería de otra UUAA mientras que si pongo la llamada en otro punto cumple con la misma funcionalidad pero reduce las llamadas a menos de la mitad de las mismas (en un volumen de unas 500.000 llamadas diarias).', '2022-05-19 00:00:00', 4),
(24, 1, 'Con el problema surgido por la eliminación del paquete log4j debo realizar una explicación bien técnica a compañeros para indicar como resolver esta situación y una explicación muy funcional a los managers de BBVA para que puedan comprender el motivo del problema y la forma de resolverlo.', '2022-04-22 00:00:00', 5),
(25, 1, 'La consola Ether comienza a fallar en las promociones a entornos. Escalo la incidencia mediante la correcta apertura del ticket que corresponde. La incidencia llega al equipo correcto y proceden a solucionarla. https://globaldevtools.bbva.com/jira/servicedesk/customer/portal/26/GLEP-308579', '2022-04-21 00:00:00', 6),
(26, 1, 'Todas las tareas comprometidas para Q1 salieron en tiempo y forma y sin incidencias.', '2022-04-01 00:00:00', 7),
(27, 1, 'En Q2 surge una urgencia por la eliminación del paquete log4j por parte de arquitectura. Las tareas a realizar son muchas y el plazo es muy corto. Logro terminar todas las tareas antes del límite de plazo previsto.', '2022-05-06 00:00:00', 7),
(28, 1, 'Al ir a utilizar una nueva herramienta que nos indicó arquitectura (Thunder Client, que reemplaza al Postman) me encontré con un problema que no estaba estipulado en los manuales de la aplicación ni en los foros de consulta (desapareció el botón de acceso a la extensión Thunder Client dentro del Visual Studio Code). Me puse a investigar por internet como solucionarlo y lo solucioné. Y generé un post en Currents compartiendo la solución con todos mis compañeros de desarrollo para colaborar en la resolución de este problema. Mi ayuda está en Currents bajo el título \"Desaparición botón acceso a Thunder Client en Visual Studio Code\"', '2022-04-26 00:00:00', 8),
(29, 1, 'Con el teletrabajo, mis compañeros de equipo (todos de Minsait) perdieron acceso a las bases de datos. Yo soy el único que tiene acceso. Cada vez que necesitan consultar algo los ayudo brindándoles la información que necesiten.', '2022-05-11 00:00:00', 8),
(30, 2, 'Me escribe cliente por privado. Me consulta por una operación, si la puedo mirar. Veo que la operación está con error y que es por una respuesta tardía de Redsys. Comprendo que eso es lo que debe importarle al cliente. Se lo comunico. Efectívamente esto era lo que quería saber, el porqué había fallado la operación.', '2022-04-25 00:00:00', 9),
(31, 2, 'Antes de comenzar el siguiente trimestre realizamos una reunión con el cliente para evaluar las tareas y sus prioridades', '2022-03-30 00:00:00', 10),
(32, 2, 'Cada lunes y jueves a las 13:30hs me reuno con el cliente para realizar un seguimiento de los distintos proyectos', '2022-04-04 00:00:00', 10),
(33, 2, 'Reunión para programar las tareas de Q3 y Q4', '2022-05-24 00:00:00', 10),
(34, 3, 'Hoy, a las 11:45hs me indican que mañana a las 6hs se realizará una apertura por parte de Redsys de la operativa de exenciones y es necesaria mi presencia. Indico que me conectaré sin ningún problema.', '2022-04-04 00:00:00', 11),
(35, 3, 'Una subida de arquitectura provocó un problema con las librerías que utilizan log4j. La librería que quedó más expuesta a nivel banco fue TPCTR817, la cual llevo yo. Se me incluye en una reunión con los jefes de los jefes de los recontra jefes. En medio de la reunión me indican que me ponga a modificar la librería para adaptarla. Atiendo a la reunión y al mismo tiempo realizo el cambio en la librería. En menos de dos horas tengo la librería subida y sin llamadas a log4j.', '2022-04-08 00:00:00', 13),
(36, 3, 'Viernes 14:30hs. Se detectan problemas en operativas modificadas por un compañero y que se subieron el día anterior. Hay que tirar para atrás esas subidas, realizar las modificaciones necesarias y planificar para que se suban en la primer ventana del siguiente día laboral (martes 17/05/2022). El trabajo debe salir rápido porque lo debe aprobar la gente de BBVA los cuales ya están disfrutando del puente. Y debe salir bien porque subir dos veces errores a producción sería bastante problemático. Me quedo trabajando después de hora y todo sale correctamente.', '2022-05-13 00:00:00', 13),
(37, 3, 'Realizo una modificación en relación al proyecto log4j. A la semana entro a mirar la modificación para ver si puedo encontrar una mejor forma de proceder. La encuentro, implemento y confirmo el resultado positivo.', '2022-05-12 00:00:00', 14),
(38, 4, 'Me contacta un compañero de otro equipo el cual se sentaba cerca mío cuando estábamos en oficina. Me dice que le propusieron entrar en IT y quiere saber qué tal es la empresa. Le hablo maravillas de BBVA IT (realmente le expreso la realidad de lo que siento por la empresa). Al terminar la charla lo noto seguro de querer formar parte de BBVA IT.', '2022-03-24 00:00:00', 15),
(39, 4, 'En el gremio un desarrollador de otro equipo expone un manual de ayuda para el cambio que hay que llevar a cabo de la herramienta Postman a Thunder Client. En la exposición explica que una de las funcionalidades a exportar no le funcionó. Yo había logrado hacer con éxito esa exportación con lo cual me puse en contacto con el desarrollador y le expliqué como lo había hecho. Lo hizo de mi forma y le funcionó. Agregó mi ayuda en el manual que él había creado indicando que lo resolvió con mi ayuda.', '2022-05-06 00:00:00', 16),
(40, 4, 'Surge una incidencia en un job de la operativa 3DS. Lo detecta ANS y realiza un estudio inicial de la posible causa. Desde APX realizo pruebas en entornos previos para poder reproducir la indicencia. Logro reproducirla y detecto otra posible causa. En coordinación con ANS realizamos un estudio del problema y llegamos a la conclusión de la causa del mismo (largo de la línea en un archivo)', '2022-05-20 00:00:00', 17),
(41, 4, 'Participación en gremio', '2022-03-25 00:00:00', 19),
(42, 4, 'Participación en gremio', '2022-04-22 00:00:00', 19),
(43, 4, 'Participación en gremio', '2022-05-06 00:00:00', 19),
(44, 4, 'Participación en gremio', '2022-05-20 00:00:00', 19),
(45, 4, 'Participación en gremio', '2022-06-03 00:00:00', 19),
(46, 5, 'Para un proyecto de información del método de autenticación, me dan ciertas pautas. Comienzo a desarrollar y veo que puede haber problemas siguiendo ese camino. Planteo lo visto y propongo un cambio de rumbo (versionar una transacción la cual no se quería versionar). Se llega a la conclusión que mi propuesta es válida. Adapto todo el desarrollo al nuevo cambio.', '2022-04-04 00:00:00', 20),
(47, 5, 'En proyecto palancas trabajo con un project manager del cliente (Gonzalo) y el desarrollo lo realizo yo solo. En proyecto de reseteo de contadores trabajo con otro project manager del cliente (Isabel) y el desarrollo lo hago en conjunto con un compañero de equipo (Sergio).', '2022-05-13 00:00:00', 21),
(48, 6, 'En mis horas libres estoy realizando una formación por mi cuenta de Spring Boot y Desarrollo web', '2022-04-04 00:00:00', 22),
(49, 6, 'Me escribe el cliente indicando que se va a realizar una formación de MongoDB Compass cinco horas más tarde y me pide si puedo ir. Voy a la misma y luego le comento al cliente sobre qué fue la formación.', '2022-03-31 00:00:00', 23),
(50, 6, 'Asisto a formación sobre DataX para el envío de ficheros desde APX al servidor para poder utilizar esta herramienta en futuros proyectos.', '2022-05-19 00:00:00', 23),
(51, 6, 'Formación Lider 4.5 que se encuentra en mi itinerario de formación', '2022-05-31 00:00:00', 23),
(52, 6, 'Formación \"FUNDAMENTOS DE PROGRAMACIÓN EN JAVA\" que me enviaron desde personas indicándome \"está formación está en tu itinerario formativo y es importante para tu desarrollo\". Realizada y aprobada.', '2022-02-14 00:00:00', 24),
(53, 6, 'Charla Ninja para utilización de Compass de MongoDB.', '2022-05-17 00:00:00', 24);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(54);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `log`
--

CREATE TABLE `log` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `details` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subcategoria`
--

CREATE TABLE `subcategoria` (
  `id` int(11) NOT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `subcategoria`
--

INSERT INTO `subcategoria` (`id`, `id_categoria`, `name`, `descripcion`) VALUES
(1, 1, 'Plazos', 'Cumplimiento de plazos y costes en el desarrollo de las tareas que se le asignan'),
(2, 1, 'Documentación', 'A partir de los requerimientos de alto nivel recibidos, elaborar documento funcional detallado y con cobertura completa de las funcionalidades'),
(3, 1, 'Interpretación', 'Ser capaces de contextualizar los requerimientos técnicos definidos a alto nivel'),
(4, 1, 'Anticipación', 'Anticipar cualquier contratiempo que pueda producirse tanto en su trabajo como en el de las personas que pueda supervisar'),
(5, 1, 'Adaptación', 'Adaptar la expresión en las comunicaciones a la audiencia destinataria de la misma'),
(6, 1, 'Escalabilidad', 'Escalar y reportar de forma adecuada'),
(7, 1, 'Compromiso', 'Comprometerse al máximo con los plazos de las diferentes tareas'),
(8, 1, 'Colaboración', 'Ofrecer colaboración y soporte a los compañeros de equipo'),
(9, 2, 'Empatía', 'Ponerse en lugar del cliente para intentar entender no sólo que pide sino porqué lo pide'),
(10, 2, 'Reuniones', 'Regularidad en la realización de reuniones de trabajo con el cliente'),
(11, 3, 'Actitud', 'Mostrar una actitud positiva ante las dificultades, especialmente en las generadas en la coordinación con otros ámbitos/áreas o a los cambios de enfoque del cliente.'),
(12, 3, 'Ideas', 'Aportar ideas de mejora contínua sobre el funcionamiento habitual del grupo de trabajo'),
(13, 3, 'Presión', 'Capacidad de gestionar el trabajo en situaciones de presión'),
(14, 3, 'Crítico', 'Ser crítico con su propio trabajo y proponer mejoras sobre el mismo'),
(15, 4, 'Pertenencia', 'Promover el sentimiento de pertenencia a BBVA IT con los compañeros del propio equipo o de otros equipos'),
(16, 4, 'Relaciones', 'Fomentar las relaciones interpersonales entre personas de diferentes equipos'),
(17, 4, 'Coordinación', 'Coordinar tareas con otros equipos (ANS / Proyectos)'),
(18, 4, 'Formación impartida', 'Preparar e impartir formación funcional o técnica a otros equipos'),
(19, 4, 'Gremios', 'Participar en gremios'),
(20, 5, 'Agile', 'En su día a día es capaz de adaptarse a los continuos cambios de especificaciones propios en la forma de trabajar \"AGILE\" del banco.'),
(21, 5, 'Cambios', 'Adaptarse a cambios en la organización/composición de los equipos tanto propios como de cliente'),
(22, 6, 'Tendencias', 'Estar al día de las tendencias del sector'),
(23, 6, 'Formación recibida', 'Aprovechar las oportunidades proporcionadas por cursos y por la experiencia en las aplicaciones para aumentar su formación funcional.'),
(24, 6, 'Formación proactiva', 'Proactividad en mantenerse formado: asistencia a charlas Ninja, Digital Talks, campus, formación Ninja, workshops, ...');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `username` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `lastname` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `name` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `second_lastname` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`username`, `enabled`, `password`, `lastname`, `name`, `second_lastname`) VALUES
('damian', b'1', '$2a$10$iQ7EQWhL7KjA3sE7gsmMTusbhyjkZqpySMNXpkHqFl/mm3SqWrsry', 'Usheff', 'Damián', 'Vellianitis'),
('pepe', b'1', '$2a$10$GbaQ4LyAB8LEW4r5k0zGpepsXlkQWGVfA9kTmP83TcghSnU0vm1LC', 'Pérez', 'José', 'García');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_role`
--

CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL,
  `role` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `username` varchar(45) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `user_role`
--

INSERT INTO `user_role` (`user_role_id`, `role`, `username`) VALUES
(1, 'ROLE_ADMIN', 'pepe'),
(3, 'ROLE_USER', 'damian'),
(2, 'ROLE_USER', 'pepe');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `entrada`
--
ALTER TABLE `entrada`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `subcategoria`
--
ALTER TABLE `subcategoria`
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
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `user_role`
--
ALTER TABLE `user_role`
  MODIFY `user_role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
