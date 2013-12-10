-- phpMyAdmin SQL Dump
-- version 3.5.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 10, 2013 at 03:59 AM
-- Server version: 5.5.29
-- PHP Version: 5.4.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `so2`
--

-- --------------------------------------------------------

--
-- Table structure for table `contactos`
--



-- --------------------------------------------------------

--
-- Table structure for table `conversacion`
--

CREATE TABLE `conversacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `conversacion`
--

INSERT INTO `conversacion` (`id`, `nombre`) VALUES
(1, 'conv'),
(2, 'conv');

-- --------------------------------------------------------

--
-- Table structure for table `mensaje`
--

CREATE TABLE `mensaje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idconversacion` int(11) DEFAULT NULL,
  `idtipo` int(11) DEFAULT NULL,
  `idusuario` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `message` text,
  PRIMARY KEY (`id`),
  KEY `mensajeestipo_idx` (`idtipo`),
  KEY `mensajepertenceaconversacion_idx` (`idconversacion`),
  KEY `usuarioenviamensaje_idx` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tipomensaje`
--

CREATE TABLE `tipomensaje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `user` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `photo` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellido`, `correo`, `user`, `password`, `photo`) VALUES
(1, 'root', 'rut', 'root', 'root', 'root', 'http://macbook-air-de-eduardo.local:8888/RPSOL/images/psrsl-04.png'),
(2, 'test', 'one', 'test@one.local', 'testone', 'testone', 'http://macbook-air-de-eduardo.local:8888/RMI-Files/users/testone.jpg'),
(3, 'test', 'two', 'test@two.local', 'testtwo', 'testtwo', 'http://macbook-air-de-eduardo.local:8888/RMI-Files/users/testtwo.png');

-- --------------------------------------------------------

--
-- Table structure for table `usuarioperteneceaconversacion`
--

CREATE TABLE `usuarioperteneceaconversacion` (
  `idusuario` int(11) NOT NULL,
  `idconversacion` int(11) NOT NULL,
  PRIMARY KEY (`idusuario`,`idconversacion`),
  KEY `conversaciontieneusuario_idx` (`idconversacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuarioperteneceaconversacion`
--

INSERT INTO `usuarioperteneceaconversacion` (`idusuario`, `idconversacion`) VALUES
(1, 1),
(2, 1),
(3, 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `contactos`
--


--
-- Constraints for table `mensaje`
--
ALTER TABLE `mensaje`
  ADD CONSTRAINT `mensajeestipo` FOREIGN KEY (`idtipo`) REFERENCES `tipomensaje` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `mensajepertenceaconversacion` FOREIGN KEY (`idconversacion`) REFERENCES `conversacion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `usuarioenviamensaje` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `usuarioperteneceaconversacion`
--
ALTER TABLE `usuarioperteneceaconversacion`
  ADD CONSTRAINT `conversaciontieneusuario` FOREIGN KEY (`idconversacion`) REFERENCES `conversacion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `usuarioperteneceaconversacion` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
