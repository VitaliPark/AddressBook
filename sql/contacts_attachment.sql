CREATE DATABASE  IF NOT EXISTS `contacts` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `contacts`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: contacts
-- ------------------------------------------------------
-- Server version	5.5.38

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `idAttachment` int(11) NOT NULL AUTO_INCREMENT,
  `idPerson` int(11) NOT NULL,
  `fileName` varchar(256) DEFAULT NULL,
  `uploadDate` date DEFAULT NULL,
  `comment` text,
  `localFileName` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`idAttachment`,`idPerson`),
  KEY `Person_Attachment_idx` (`idPerson`),
  CONSTRAINT `Person_Attachment` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` VALUES (11,20,'tuxguitar.jnlp','2014-08-22','гитара','1408715318tuxguitar.jnlp'),(13,22,'Space_by_Viled.jpg','2014-08-23','','1408822640Space_by_Viled.jpg'),(16,13,'WISH I WAS HERE.pdf','2014-08-24','Сценарий','1408910953WISH I WAS HERE.pdf'),(18,13,'284639-1920x1200.jpg','2014-08-24','Journey','1408912431284639-1920x1200.jpg'),(19,2,'01 The Eraser.m4a','2014-08-24','','140891335901 The Eraser.m4a'),(20,2,'01 Before Your Very Eyes.flac','2014-08-24','','140891337801 Before Your Very Eyes.flac'),(21,1,'03 - Holocene.flac','2014-08-24','I was not magnificent','140891378803 - Holocene.flac'),(22,1,'03. Bon Iver - Skinny Love.flac','2014-08-24','','140891381003. Bon Iver - Skinny Love.flac'),(23,14,'11 -  Leaving Home - Solar Fields.mp3','2014-08-25','','140891400811 -  Leaving Home - Solar Fields.mp3'),(24,14,'01 - Introduction.mp3','2014-08-25','Mirrors edge','140891402701 - Introduction.mp3');
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-25 13:03:33
