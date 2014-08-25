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
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone` (
  `idPhone` int(11) NOT NULL AUTO_INCREMENT,
  `idPerson` int(11) NOT NULL,
  `countryCode` int(11) DEFAULT NULL,
  `operatorCode` int(11) DEFAULT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  `phoneType` varchar(45) DEFAULT NULL,
  `comment` text,
  PRIMARY KEY (`idPhone`,`idPerson`),
  KEY `Person_Phone_idx` (`idPerson`),
  CONSTRAINT `Person_Phone` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (1,13,375,44,'7223343','мобильный','Редко включён'),(2,13,375,17,'2019295','домашний','Автоответчик'),(3,18,375,29,'1234567','мобильный','Коби телефон'),(4,18,375,44,'7896542','мобильный','Коби телефон'),(5,19,375,29,'1560616','мобильный','Можно зваонить'),(6,19,375,29,'2019295','домашний','Можно зваонить'),(7,20,375,29,'2586975','домашний','Этот телефон принадлежит мне');
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-25 13:03:34
