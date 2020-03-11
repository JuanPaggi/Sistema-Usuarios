-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'usuarios'
-- 
-- ---

DROP TABLE IF EXISTS `usuarios`;
		
CREATE TABLE `usuarios` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(32) NOT NULL,
  `clave` VARCHAR(32) NOT NULL,
  `nombre` VARCHAR(64) NOT NULL,
  `apellido` VARCHAR(64) NOT NULL,
  `dni` VARCHAR(12) NOT NULL,
  `correo` VARCHAR(128) NOT NULL,
  `verificar_correo` bit NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `nacionalidad` VARCHAR(128) NOT NULL,
  `sexo` VARCHAR(32) NOT NULL,
  `telefono` VARCHAR(32) NOT NULL,
  `imagen` MEDIUMBLOB NOT NULL,
  `imagen_checksum` BINARY(20) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY (`usuario`, `dni`, `correo`, `telefono`)
);

-- ---
-- Foreign Keys 
-- ---


-- ---
-- Table Properties
-- ---

-- ALTER TABLE `usuarios` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO `usuarios` (`id_usuario`,`usuario`,`clave`,`nombre`,`apellido`,`dni`,`correo`,`verificar_correo`,`fecha_nacimiento`,`nacionalidad`,`sexo`,`telefono`,`imagen`,`imagen_checksum`) VALUES
-- ('','','','','','','','','','','','','','');