
CREATE DATABASE IF NOT EXISTS modelo;

USE modelo;

DROP TABLE IF EXISTS `tb_perfis`;
CREATE TABLE `tb_perfis` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `registro` datetime NOT NULL,
  `situacao` bit(1) NOT NULL,
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_b5m0v5n3b62qaehjh0smvyedt` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_permissoes`;
CREATE TABLE `tb_permissoes` (
  `codigo` varchar(255) NOT NULL,
  `registro` datetime NOT NULL,
  `situacao` bit(1) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_permissoes_perfil`;
CREATE TABLE `tb_permissoes_perfil` (
  `perfil` bigint(20) NOT NULL,
  `permissao` varchar(255) NOT NULL,
  KEY `FKoeufmjnlimvafxqooori5ijjv` (`permissao`),
  KEY `FKe7nyirq1opnho7pxhy0wxccxg` (`perfil`),
  CONSTRAINT `FKe7nyirq1opnho7pxhy0wxccxg` FOREIGN KEY (`perfil`) REFERENCES `tb_perfis` (`codigo`),
  CONSTRAINT `FKoeufmjnlimvafxqooori5ijjv` FOREIGN KEY (`permissao`) REFERENCES `tb_permissoes` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_usuarios`;
CREATE TABLE `tb_usuarios` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `registro` datetime NOT NULL,
  `situacao` bit(1) NOT NULL,
  `login` varchar(60) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `perfil` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_9v12hr9s4xeuggcy1ss95jmwy` (`login`),
  KEY `FKh6o4ilj708d539wm49wysxt1c` (`perfil`),
  CONSTRAINT `FKh6o4ilj708d539wm49wysxt1c` FOREIGN KEY (`perfil`) REFERENCES `tb_perfis` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

