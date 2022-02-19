CREATE DATABASE  IF NOT EXISTS `cs7cs3` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `cs7cs3`;
-- MariaDB dump 10.19  Distrib 10.6.4-MariaDB, for osx10.17 (arm64)
--
-- Host: localhost    Database: cs7cs3
-- ------------------------------------------------------
-- Server version	10.6.4-MariaDB

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `journey`
--

DROP TABLE IF EXISTS `journey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journey` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `created_time` bigint(20) NOT NULL DEFAULT 0,
  `end_time` bigint(20) NOT NULL DEFAULT 0,
  `from_latitude` double NOT NULL DEFAULT 0,
  `from_longitude` double NOT NULL DEFAULT 0,
  `max_member` int(11) NOT NULL DEFAULT 0,
  `status` int(11) NOT NULL DEFAULT 0,
  `to_latitude` double NOT NULL DEFAULT 0,
  `to_longitude` double NOT NULL DEFAULT 0,
  `host` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `c1200849-8810-4579-a690-2020ee4271a8` (`host`),
  CONSTRAINT `c1200849-8810-4579-a690-2020ee4271a8` FOREIGN KEY (`host`) REFERENCES `account` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `journey_members`
--

DROP TABLE IF EXISTS `journey_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journey_members` (
  `journey_id` varchar(255) NOT NULL DEFAULT '',
  `user_id` varchar(255) NOT NULL DEFAULT '',
  UNIQUE KEY `98dd5112-e277-4c48-88e3-242c2157a34e` (`user_id`),
  KEY `0bd04255-628a-415f-84a4-02073e5cdfd1` (`journey_id`),
  CONSTRAINT `0bd04255-628a-415f-84a4-02073e5cdfd1` FOREIGN KEY (`journey_id`) REFERENCES `journey` (`id`) ON DELETE CASCADE,
  CONSTRAINT `6197e6a4-8888-4fcc-b638-8cc88ed732d1` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `cs7cs3`.`journey_members_AFTER_DELETE` AFTER DELETE ON `journey_members` FOR EACH ROW
BEGIN
 IF (SELECT count(*) FROM `cs7cs3`.`journey_members` WHERE journey_id =	old.journey_id) = 0 THEN
	DELETE FROM `cs7cs3`.`journey` WHERE (`id` = old.journey_id); 
 END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` varchar(255) NOT NULL,
  `avatar_url` text NOT NULL DEFAULT '',
  `bio` text NOT NULL DEFAULT '',
  `rating` double NOT NULL DEFAULT 0,
  `status` int(11) NOT NULL DEFAULT 0,
  `username` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `856270d8-03e4-4485-b2ec-74f3a9723067` (`username`),
  CONSTRAINT `a37bfb1b-48aa-46e4-9199-dc10311d4c39` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_info_histories`
--

DROP TABLE IF EXISTS `user_info_histories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info_histories` (
  `user_id` varchar(255) NOT NULL DEFAULT '',
  `journey_id` varchar(255) DEFAULT '',
  KEY `ecd5d914-9449-40d1-8957-1daa48e98bd7` (`user_id`),
  KEY `6fdb4889-414b-4a00-9c2f-860a474e78ad` (`journey_id`),
  CONSTRAINT `6fdb4889-414b-4a00-9c2f-860a474e78ad` FOREIGN KEY (`journey_id`) REFERENCES `journey` (`id`) ON DELETE CASCADE,
  CONSTRAINT `ecd5d914-9449-40d1-8957-1daa48e98bd7` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_info_reviews`
--

DROP TABLE IF EXISTS `user_info_reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info_reviews` (
  `user_id` varchar(255) NOT NULL DEFAULT '',
  `review_id` varchar(255) DEFAULT '',
  KEY `02780f9f-a0c2-40bf-bbfd-9d5e6679a8cc` (`user_id`),
  KEY `87712e75-3396-4883-8733-cadcee95b005` (`review_id`),
  CONSTRAINT `02780f9f-a0c2-40bf-bbfd-9d5e6679a8cc` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE CASCADE,
  CONSTRAINT `87712e75-3396-4883-8733-cadcee95b005` FOREIGN KEY (`review_id`) REFERENCES `user_review` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_review`
--

DROP TABLE IF EXISTS `user_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_review` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `anonymous` bit(1) NOT NULL DEFAULT b'0',
  `content` varchar(255) NOT NULL DEFAULT '',
  `rating` double NOT NULL DEFAULT 0,
  `user_id` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'cs7cs3'
--

--
-- Dumping routines for database 'cs7cs3'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-19 18:30:54
