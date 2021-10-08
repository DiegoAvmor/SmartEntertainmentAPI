CREATE DATABASE  IF NOT EXISTS `smart_entertainment_api` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `smart_entertainment_api`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: smart_entertainment_api
-- ------------------------------------------------------
-- Server version	5.7.34-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_type` int(11) DEFAULT NULL,
  `image_url` longtext,
  `name` varchar(50) DEFAULT NULL,
  `description` longtext,
  `year_publish` int(11) DEFAULT NULL,
  `num_chapter` int(11) DEFAULT NULL,
  PRIMARY KEY (`resource_id`),
  KEY `resource_type` (`resource_type`),
  CONSTRAINT `resource_ibfk_1` FOREIGN KEY (`resource_type`) REFERENCES `resource_types` (`resource_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES (1,1,'https://images-na.ssl-images-amazon.com/images/I/81XrGNjRccL.jpg','Halo: The Fall of Reach','The twenty-sixth century. Humanity has expanded beyond Earth’s system to hundreds of planets that colonists now call home. But the United Earth Government and the United Nations Space Command is struggling to control this vast empire. After exhausting all strategies to keep seething colonial insurrections from exploding into a full-blown interplanetary civil war, the UNSC has one last hope. At the Office of Naval Intelligence, Dr. Catherine Halsey has been hard at work on a top-secret program that could bring an end to the conflict…and it starts with seventy-five children, among them a six-year-old boy named John. And Halsey could never guess that this child will eventually become the final hope against an even greater peril engulfing the galaxy--the inexorable confrontation with a theocratic military alliance of alien races known as the Covenant.',2009,10),(2,2,'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.sonypictures.com%2Fmovies%2Fmonsterhunter&psig=AOvVaw2N4YJ-sEGtSs-34aiPuq4Z&ust=1633793285222000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCNDh8LSQu_MCFQAAAAAdAAAAABAI','Monster hunter','Captain Artemis and her group of soldiers are pulled into a world where dangerous monsters and humans co-exist. Grappled by danger, they must find a way to escape with help from a mysterious hunter.',2020,1),(3,3,'https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FThe_Midnight_Gospel&psig=AOvVaw38jCaXapv4f5AnW-81HGsr&ust=1633793379956000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCPC-_OGQu_MCFQAAAAAdAAAAABAG','The Midnight Gospel','A space caster traverses trippy worlds inside his universe simulator, exploring existential questions about life, death and everything in between.',2020,8),(4,1,'url','Halo: The Flood','asdas',1233,4);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_types`
--

DROP TABLE IF EXISTS `resource_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource_types` (
  `resource_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`resource_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_types`
--

LOCK TABLES `resource_types` WRITE;
/*!40000 ALTER TABLE `resource_types` DISABLE KEYS */;
INSERT INTO `resource_types` VALUES (1,'Book'),(2,'Movie'),(3,'TVSerie');
/*!40000 ALTER TABLE `resource_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Started'),(2,'Paused'),(3,'Dropped'),(4,'Finished');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_resources`
--

DROP TABLE IF EXISTS `user_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_resources` (
  `user_resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `date_started` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_finished` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `is_favorite` tinyint(1) DEFAULT NULL,
  `current_chapter` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_resource_id`),
  KEY `user_id` (`user_id`),
  KEY `resource_id` (`resource_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `user_resources_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `user_resources_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`),
  CONSTRAINT `user_resources_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_resources`
--

LOCK TABLES `user_resources` WRITE;
/*!40000 ALTER TABLE `user_resources` DISABLE KEYS */;
INSERT INTO `user_resources` VALUES (1,1,1,2,'2018-11-21 23:13:13','2018-11-21 23:13:13',1,10);
/*!40000 ALTER TABLE `user_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'diego','test@gmail.com','password'),(2,'tests','test1@gmail.com','password');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-08 14:38:58
