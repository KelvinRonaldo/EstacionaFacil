-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: db_estacionamento
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_movimentacao`
--

DROP TABLE IF EXISTS `tbl_movimentacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_movimentacao` (
  `cod_entrada` int(11) NOT NULL AUTO_INCREMENT,
  `placa` varchar(8) NOT NULL,
  `hora_entrada` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hora_saida` datetime DEFAULT NULL,
  `tempo` int(11) DEFAULT NULL,
  `valor_pago` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`cod_entrada`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_movimentacao`
--

LOCK TABLES `tbl_movimentacao` WRITE;
/*!40000 ALTER TABLE `tbl_movimentacao` DISABLE KEYS */;
INSERT INTO `tbl_movimentacao` VALUES (1,'CHI-5012','2019-05-02 14:05:44','2019-05-02 15:39:22',93,NULL),(2,'POI-1254','2019-05-02 14:11:19',NULL,NULL,NULL),(3,'OKS-4521','2019-05-02 15:16:00',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tbl_movimentacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_preco`
--

DROP TABLE IF EXISTS `tbl_preco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_preco` (
  `cod_preco` int(11) NOT NULL AUTO_INCREMENT,
  `valor_hora_1` decimal(5,2) NOT NULL,
  `valor_demais_horas` decimal(5,2) NOT NULL,
  `dt_fim` date DEFAULT NULL,
  PRIMARY KEY (`cod_preco`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_preco`
--

LOCK TABLES `tbl_preco` WRITE;
/*!40000 ALTER TABLE `tbl_preco` DISABLE KEYS */;
INSERT INTO `tbl_preco` VALUES (1,5.00,3.00,NULL);
/*!40000 ALTER TABLE `tbl_preco` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-02 16:07:22
