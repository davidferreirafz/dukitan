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
-- Definition of table `constante`
--

DROP TABLE IF EXISTS `constante`;
CREATE TABLE `constante` (
  `chave` varchar(255) NOT NULL,
  `id_software` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`chave`,`id_software`),
  KEY `id_software` (`id_software`),
  CONSTRAINT `constante_ibfk_1` FOREIGN KEY (`id_software`) REFERENCES `software` (`id_software`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `constante`
--
SET AUTOCOMMIT=0;
COMMIT;

--
-- Definition of table `constante_versao`
--

DROP TABLE IF EXISTS `constante_versao`;
CREATE TABLE `constante_versao` (
  `id_versao` int(10) unsigned NOT NULL,
  `chave` varchar(255) NOT NULL,
  `id_software` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id_versao`,`chave`,`id_software`),
  KEY `chave` (`chave`,`id_software`),
  CONSTRAINT `constante_versao_ibfk_1` FOREIGN KEY (`id_versao`) REFERENCES `versao` (`id_versao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `constante_versao_ibfk_2` FOREIGN KEY (`chave`, `id_software`) REFERENCES `constante` (`chave`, `id_software`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `constante_versao`
--
SET AUTOCOMMIT=0;
COMMIT;

--
-- Definition of table `idioma`
--

DROP TABLE IF EXISTS `idioma`;
CREATE TABLE `idioma` (
  `id_idioma` int(10) unsigned NOT NULL auto_increment,
  `sigla` varchar(5) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_idioma`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `idioma`
--
SET AUTOCOMMIT=0;
INSERT INTO `idioma` (`id_idioma`,`sigla`,`descricao`) VALUES 
 (1,'en_US','Inglês'),
 (2,'pt_BR','Português Brasil'),
 (3,'es','Espanhol');
COMMIT;

--
-- Definition of table `software`
--

DROP TABLE IF EXISTS `software`;
CREATE TABLE `software` (
  `id_software` int(10) unsigned NOT NULL auto_increment,
  `nome` varchar(255) NOT NULL,
  `site` varchar(255) default NULL,
  PRIMARY KEY  (`id_software`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `software`
--
SET AUTOCOMMIT=0;
INSERT INTO `software` (`id_software`,`nome`,`site`) VALUES 
 (1,'Liferay - Portal','http://www.liferay.com');
COMMIT;

--
-- Definition of table `texto`
--

DROP TABLE IF EXISTS `texto`;
CREATE TABLE `texto` (
  `id_texto` int(10) unsigned NOT NULL auto_increment,
  `id_software` int(10) unsigned NOT NULL,
  `id_idioma` int(10) unsigned NOT NULL,
  `chave` varchar(255) NOT NULL,
  `texto` text NOT NULL,
  PRIMARY KEY  (`id_texto`),
  UNIQUE KEY `idioma_chave` (`id_idioma`,`chave`),
  KEY `chave` (`chave`,`id_software`),
  CONSTRAINT `texto_ibfk_1` FOREIGN KEY (`chave`, `id_software`) REFERENCES `constante` (`chave`, `id_software`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `texto_ibfk_2` FOREIGN KEY (`id_idioma`) REFERENCES `idioma` (`id_idioma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `texto`
--
SET AUTOCOMMIT=0;
COMMIT;

--
-- Definition of table `texto_padrao`
--

DROP TABLE IF EXISTS `texto_padrao`;
CREATE TABLE `texto_padrao` (
  `chave` varchar(255) NOT NULL,
  `id_software` int(10) unsigned NOT NULL,
  `id_idioma` int(10) unsigned NOT NULL,
  `texto` text NOT NULL,
  PRIMARY KEY  (`chave`,`id_software`),
  KEY `id_idioma` (`id_idioma`),
  CONSTRAINT `texto_padrao_ibfk_1` FOREIGN KEY (`chave`, `id_software`) REFERENCES `constante` (`chave`, `id_software`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `texto_padrao_ibfk_2` FOREIGN KEY (`id_idioma`) REFERENCES `idioma` (`id_idioma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `texto_padrao`
--
SET AUTOCOMMIT=0;
COMMIT;

--
-- Definition of table `versao`
--

DROP TABLE IF EXISTS `versao`;
CREATE TABLE `versao` (
  `id_versao` int(10) unsigned NOT NULL auto_increment,
  `id_software` int(10) unsigned NOT NULL,
  `versao` varchar(8) NOT NULL,
  PRIMARY KEY  (`id_versao`),
  UNIQUE KEY `software_versao` (`versao`),
  KEY `id_software` (`id_software`),
  CONSTRAINT `versao_ibfk_1` FOREIGN KEY (`id_software`) REFERENCES `software` (`id_software`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `versao`
--
SET AUTOCOMMIT=0;
INSERT INTO `versao` (`id_versao`,`id_software`,`versao`) VALUES 
 (1,1,'5.2.3');
COMMIT;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
