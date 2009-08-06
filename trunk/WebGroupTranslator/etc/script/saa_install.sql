-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.37-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema traducao_liferay
--

CREATE DATABASE IF NOT EXISTS traducao_liferay;
USE traducao_liferay;

--
-- Definition of table `saa_access`
--

DROP TABLE IF EXISTS `saa_access`;
CREATE TABLE `saa_access` (
  `id_group` int(10) unsigned NOT NULL,
  `id_user` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id_group`,`id_user`),
  KEY `saa_access_FKIndex1` (`id_user`),
  KEY `saa_access_FKIndex2` (`id_group`),
  CONSTRAINT `saa_access_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `saa_user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `saa_access_ibfk_2` FOREIGN KEY (`id_group`) REFERENCES `saa_group` (`id_group`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saa_access`
--

/*!40000 ALTER TABLE `saa_access` DISABLE KEYS */;
INSERT INTO `saa_access` (`id_group`,`id_user`) VALUES 
 (1,1);
/*!40000 ALTER TABLE `saa_access` ENABLE KEYS */;


--
-- Definition of table `saa_autorization`
--

DROP TABLE IF EXISTS `saa_autorization`;
CREATE TABLE `saa_autorization` (
  `id_group` int(10) unsigned NOT NULL,
  `id_resource` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id_group`,`id_resource`),
  KEY `saa_autorization_FKIndex1` (`id_group`),
  KEY `saa_autorization_FKIndex2` (`id_resource`),
  CONSTRAINT `saa_autorization_ibfk_1` FOREIGN KEY (`id_group`) REFERENCES `saa_group` (`id_group`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `saa_autorization_ibfk_2` FOREIGN KEY (`id_resource`) REFERENCES `saa_resource` (`id_resource`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saa_autorization`
--

/*!40000 ALTER TABLE `saa_autorization` DISABLE KEYS */;
INSERT INTO `saa_autorization` (`id_group`,`id_resource`) VALUES 
 (1,1),
 (1,2),
 (1,3),
 (1,4),
 (1,5),
 (1,6),
 (1,7),
 (1,8);
/*!40000 ALTER TABLE `saa_autorization` ENABLE KEYS */;


--
-- Definition of table `saa_group`
--

DROP TABLE IF EXISTS `saa_group`;
CREATE TABLE `saa_group` (
  `id_group` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id_group`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saa_group`
--

/*!40000 ALTER TABLE `saa_group` DISABLE KEYS */;
INSERT INTO `saa_group` (`id_group`,`name`) VALUES 
 (1,'Admin'),
 (2,'Lider'),
 (3,'Tradutor');
/*!40000 ALTER TABLE `saa_group` ENABLE KEYS */;


--
-- Definition of table `saa_resource`
--

DROP TABLE IF EXISTS `saa_resource`;
CREATE TABLE `saa_resource` (
  `id_resource` int(10) unsigned NOT NULL auto_increment,
  `value` varchar(255) NOT NULL,
  `type` enum('page','function','button') NOT NULL default 'page',
  PRIMARY KEY  (`id_resource`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saa_resource`
--

/*!40000 ALTER TABLE `saa_resource` DISABLE KEYS */;
INSERT INTO `saa_resource` (`id_resource`,`value`,`type`) VALUES 
 (1,'/wgt/package/action/importar_constante.php','page'),
 (2,'/wgt/package/action/atualizar_texto_traduzido.php   ','page'),
 (3,'/wgt/package/action/crud_texto.php','page'),
 (4,'/wgt/package/action/editar_texto_sem_traducao.php ','page'),
 (5,'/wgt/package/action/editar_texto_traduzido.php','page'),
 (6,'/wgt/package/action/exportar_texto_traduzido.php ','page'),
 (7,'/wgt/package/action/importar_texto_padrao.php','page'),
 (8,'/wgt/package/action/importar_texto_traduzido.php','page');
/*!40000 ALTER TABLE `saa_resource` ENABLE KEYS */;


--
-- Definition of table `saa_user`
--

DROP TABLE IF EXISTS `saa_user`;
CREATE TABLE `saa_user` (
  `id_user` int(10) unsigned NOT NULL auto_increment,
  `email` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id_user`),
  UNIQUE KEY `UNIQUE_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saa_user`
--

/*!40000 ALTER TABLE `saa_user` DISABLE KEYS */;
INSERT INTO `saa_user` (`id_user`,`email`,`pass`,`full_name`) VALUES 
 (1,'davidferreira.fz@gmail.com','senha','David Ferreira');
/*!40000 ALTER TABLE `saa_user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
