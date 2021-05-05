CREATE DATABASE  IF NOT EXISTS `cash` ;
USE `cash`;
-- Server version	5.7.34
--
-- Table structure for table `loan`
--
DROP TABLE IF EXISTS `loan`;

CREATE TABLE `loan` (
  `id_loan` bigint(20) NOT NULL AUTO_INCREMENT,
  `total` double NOT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_loan`),
  KEY `FKnx6y4sq2u7xecyn0yqiwm05br` (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
