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
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `idPerson` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `secondName` varchar(45) NOT NULL,
  `patronymicName` varchar(45) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `maritalStatus` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `citizenship` varchar(45) DEFAULT NULL,
  `website` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `workplace` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPerson`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Джастин','Вернон','Эдисон','1981-04-30','MS_S','G_M','Америка','boniver.org','vitlen@inbox.ru','bon iver'),(2,'Том','Йорк','Эдвард','1968-10-07','MS_M','G_M','Англия','radiohead.com','','Radiohead'),(13,'Виталий','Пак','Валерьянович','1994-05-10','MS_S','G_M','Беларусь','','vitalipark@gmail.com',''),(14,'Магнус','Биргерссон','','1981-11-08','MS_S','G_M','Шведция','solarfields.org','','Solar fields'),(15,'Пётр','Петров','Петрович','1968-08-18','MS_M','G_M','Беларусь',NULL,'vitlen@inbox.ru','Автоваз'),(16,'Алефтина','Алеш','Петровна','1982-08-20','MS_M','','Беларусь','','vitlen@inbox.ru','Автоваз'),(18,'Коби','Брайант','Бин','1898-10-10','MS_M','G_M','','','','Лейкерс'),(19,'Лена','Пак','','1989-03-27','MS_S','G_F','','','','МинскГрадо'),(20,'Привет','Меня','Зовут Андрей','1986-02-14','MS_M','G_M','','','','БГУИР'),(22,'Женя','Ти','','1996-03-10','','G_M','','','',''),(29,'Андрей','Лян','Радионович','1998-12-25','MS_S','G_M','Россия','','',''),(31,'Ирина','Лян','Радионовна','1995-08-24','','G_F','Россия','','',''),(32,'Василий','Васютин','','1965-08-24','MS_M','G_M','','','','Интеграл');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-25 13:27:12
