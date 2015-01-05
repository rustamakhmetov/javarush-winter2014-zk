DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `isAdmin` bit DEFAULT false,
  `createDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
);