CREATE DATABASE  IF NOT EXISTS `debtmonkey` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `debtmonkey`;
-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: debtmonkey
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(45) NOT NULL,
  `Account_Type_Id` int(11) NOT NULL,
  `original_balance` double DEFAULT NULL,
  `current_balance` double DEFAULT NULL,
  `min_payment_due` double DEFAULT NULL,
  `date_last_payment` date DEFAULT NULL,
  `date_next_payment_due` date DEFAULT NULL,
  `date_opened` date DEFAULT NULL,
  `date_closed` date DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  `auto_payment` tinyint(4) DEFAULT NULL,
  `web_site` varchar(65) DEFAULT NULL,
  `Account_Owner_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Acct_Type` (`Account_Type_Id`),
  KEY `fk_Account_Account_Owner1_idx` (`Account_Owner_id`),
  CONSTRAINT `FK_Acct_Type` FOREIGN KEY (`Account_Type_Id`) REFERENCES `Account_Type` (`id`),
  CONSTRAINT `fk_Account_Account_Owner1` FOREIGN KEY (`Account_Owner_id`) REFERENCES `Account_Owner` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES (1,'Wayfair',13,1653.99,1271,NULL,'2016-11-17',NULL,'2016-04-27',NULL,'2016-09-05 16:25:09','2017-02-19 09:12:38',1,NULL,2),(2,'Merrick Bank',11,1600,461.41,NULL,'2016-11-24',NULL,'2014-12-28',NULL,'2016-09-05 19:55:00','2017-02-19 09:12:38',NULL,NULL,2),(3,'Blaze Bank',11,500,123.45,NULL,'2016-11-26',NULL,'2015-02-27',NULL,'2016-09-05 19:55:00','2017-02-19 09:12:38',NULL,NULL,2),(4,'First Savings Bank',11,2500,0,NULL,'2017-02-13',NULL,'2014-09-23',NULL,'2016-09-05 19:55:00','2017-02-19 09:12:38',NULL,NULL,2),(5,'Emblem CC',11,1925,899.95,NULL,'2016-11-10',NULL,'2013-08-28',NULL,'2016-09-05 19:55:00','2017-02-19 09:12:38',NULL,NULL,2),(6,'CapitalOne MC',11,800,0,NULL,'2016-10-31',NULL,'2014-12-29',NULL,'2016-09-05 20:09:13','2017-02-19 09:12:38',NULL,NULL,2),(7,'CapitalOne QuickSilver',11,2500,1254.56,NULL,'2016-11-16',NULL,'2014-12-29',NULL,'2016-09-05 20:09:13','2017-02-19 09:12:38',NULL,NULL,2),(8,'Nissan Motors',14,25800,14450,NULL,'2016-11-08',NULL,'2016-02-25','2016-12-29','2016-09-05 20:12:07','2017-02-19 09:12:38',NULL,NULL,2),(9,'Lending Group',15,14500,7825,NULL,'2016-11-16',NULL,'2015-05-28',NULL,'2016-09-05 20:12:07','2017-02-19 09:12:38',NULL,NULL,2),(10,'Citi Bank',11,500,77.59,NULL,'2016-11-12',NULL,'2016-07-18',NULL,'2016-09-06 17:04:48','2017-02-19 09:12:38',NULL,NULL,2),(11,'FedLoan',16,150000,143900,NULL,'2016-11-14',NULL,'2005-12-29',NULL,'2016-09-11 15:10:40','2017-02-19 09:12:38',NULL,NULL,2),(12,'Lending Group (acct2)',15,14000,14000,NULL,NULL,NULL,'2016-11-27',NULL,'2016-12-03 10:35:43','2017-02-19 09:12:38',NULL,NULL,2),(13,'Ally Car Refinance',14,16500,16500,NULL,NULL,NULL,'2016-12-29',NULL,'2017-01-08 11:32:41','2017-02-19 09:12:38',NULL,NULL,2);
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-26 18:17:35