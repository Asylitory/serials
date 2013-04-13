-- MySQL dump 10.13  Distrib 5.1.68, for Win32 (ia32)
--
-- Host: localhost    Database: serials
-- ------------------------------------------------------
-- Server version	5.1.68-community

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
-- Table structure for table `seasons`
--

DROP TABLE IF EXISTS `seasons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seasons` (
  `season_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `serial_id` int(10) unsigned NOT NULL,
  `season_title` varchar(255) NOT NULL,
  PRIMARY KEY (`season_id`),
  KEY `fk_serial_id` (`serial_id`),
  CONSTRAINT `fk_serial_id` FOREIGN KEY (`serial_id`) REFERENCES `serials` (`serial_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seasons`
--

LOCK TABLES `seasons` WRITE;
/*!40000 ALTER TABLE `seasons` DISABLE KEYS */;
INSERT INTO `seasons` VALUES (1,1,'Season 1'),(2,2,'Season 5'),(3,2,'Season 6'),(4,2,'Season 7'),(5,3,'Season 1'),(6,3,'Season 2'),(7,4,'Season 1'),(8,4,'Season 2'),(9,4,'Season 3'),(10,4,'Season 4');
/*!40000 ALTER TABLE `seasons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serials`
--

DROP TABLE IF EXISTS `serials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serials` (
  `serial_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `serial_title` varchar(255) NOT NULL,
  PRIMARY KEY (`serial_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serials`
--

LOCK TABLES `serials` WRITE;
/*!40000 ALTER TABLE `serials` DISABLE KEYS */;
INSERT INTO `serials` VALUES (1,'Firefly'),(2,'Doctor Who'),(3,'Game of Thrones'),(4,'Farscape');
/*!40000 ALTER TABLE `serials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `series`
--

DROP TABLE IF EXISTS `series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `series` (
  `series_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `season_id` int(10) unsigned NOT NULL,
  `date` date NOT NULL,
  `series_title` varchar(255) NOT NULL,
  PRIMARY KEY (`series_id`),
  KEY `fk_season_id` (`season_id`),
  CONSTRAINT `fk_season_id` FOREIGN KEY (`season_id`) REFERENCES `seasons` (`season_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `series`
--

LOCK TABLES `series` WRITE;
/*!40000 ALTER TABLE `series` DISABLE KEYS */;
INSERT INTO `series` VALUES (1,1,'2002-09-20','The Train Job'),(2,1,'2002-09-27','Bushwhacked'),(3,1,'2002-09-20','Our Mrs. Reynolds'),(4,2,'2010-04-03','The Eleventh Hour'),(5,2,'2010-04-24','The Time of Angels'),(6,2,'2010-05-01','Flesh and Stone'),(7,3,'2011-04-23','The Impossible Astronaut'),(8,3,'2011-04-30','Day of the Moon'),(9,3,'2010-06-04','A Good Man Goes to War'),(10,4,'2012-07-01','Asylum of the Daleks'),(11,4,'2012-07-08','Dinosaurs on a Spaceship'),(12,4,'2012-07-29','The Angels Take Manhattan'),(13,5,'2011-04-17','Winter Is Coming'),(14,5,'2011-04-24','The Kingsroad'),(15,5,'2011-05-01','Lord Snow'),(16,6,'2012-04-01','The North remembers'),(17,6,'2012-04-08','The Night Lands'),(18,6,'2012-05-15','What Is Dead May Never Die'),(19,7,'1999-03-19','Premiere'),(20,7,'1999-04-17','PK Tech Girl'),(21,7,'1999-04-09','Throne for a Loss'),(22,8,'2000-03-24','Vitas Mortis'),(23,8,'2000-04-21','Picture If You Will'),(24,8,'2000-07-07','Out of Their Minds'),(25,9,'2001-03-16','Season of Death'),(26,9,'2001-06-15','Thanks For Sharing'),(27,9,'2001-08-24','Fractures'),(28,10,'2002-06-12','Promises'),(29,10,'2002-06-26','John Quixote'),(30,10,'2003-01-06','Terra Firma');
/*!40000 ALTER TABLE `series` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-04-11  7:18:26
