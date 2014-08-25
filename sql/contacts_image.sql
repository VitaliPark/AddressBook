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
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `idImage` int(11) NOT NULL AUTO_INCREMENT,
  `idPerson` int(11) NOT NULL,
  `fileName` varchar(256) DEFAULT NULL,
  `localFileName` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`idImage`,`idPerson`),
  KEY `Person_Image_idx` (`idPerson`),
  CONSTRAINT `Person_Image` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,20,'third_seventh_13.jpg','contact_image1408715054third_seventh_13.jpg'),(2,20,'images (1).jpg','contact_image1408715332images (1).jpg'),(3,13,'images (1).jpg','contact_image1408954530images (1).jpg'),(4,22,'DSC08290.JPG','contact_image1408897096DSC08290.JPG'),(5,29,'DSC08159.JPG','contact_image1408904075DSC08159.JPG'),(6,2,'Thom Yorke.jpg','contact_image1408913453Thom Yorke.jpg'),(7,1,'JustinVernon.jpg','contact_image1408913748JustinVernon.jpg'),(8,14,'MagnusBirgerrson.jpg','contact_image1408914084MagnusBirgerrson.jpg');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-25 13:03:30
