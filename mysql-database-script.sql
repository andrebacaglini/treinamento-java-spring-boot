-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema treinamento
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `treinamento` ;

-- -----------------------------------------------------
-- Schema treinamento
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `treinamento` DEFAULT CHARACTER SET utf8mb4;
USE `treinamento` ;

-- -----------------------------------------------------
-- Table `treinamento`.`tb_categoria_curso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `treinamento`.`tb_categoria_curso` ;

CREATE TABLE IF NOT EXISTS `treinamento`.`tb_categoria_curso` (
  `ID` BIGINT NOT NULL AUTO_INCREMENT,
  `DS_CATEGORIA` VARCHAR(255) NOT NULL,
  `IN_EXCLUIDO` BIT(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `treinamento`.`tb_curso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `treinamento`.`tb_curso` ;

CREATE TABLE IF NOT EXISTS `treinamento`.`tb_curso` (
  `ID` BIGINT NOT NULL AUTO_INCREMENT,
  `NM_CURSO` VARCHAR(255) NOT NULL,
  `ID_CATEGORIA` BIGINT NOT NULL,
  `QT_ANOS_DURACAO` INT NOT NULL,
  `IN_HABILITADO_PCD` BIT(1) NOT NULL,
  `IN_EXCLUIDO` BIT(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`),
  INDEX `ID_CATEGORIA` (`ID_CATEGORIA` ASC),
  CONSTRAINT `tb_curso_ibfk_1`
    FOREIGN KEY (`ID_CATEGORIA`)
    REFERENCES `treinamento`.`tb_categoria_curso` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `treinamento`.`tb_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `treinamento`.`tb_log` ;

CREATE TABLE IF NOT EXISTS `treinamento`.`tb_log` (
  `ID` BIGINT NOT NULL AUTO_INCREMENT,
  `DS_ACAO` VARCHAR(255) NOT NULL,
  `DT_DATA_HORA` DATETIME NOT NULL,
  `NM_USUARIO` VARCHAR(255) NOT NULL,
  `ID_CURSO` BIGINT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `ID_CURSO` (`ID_CURSO` ASC),
  CONSTRAINT `tb_log_ibfk_1`
    FOREIGN KEY (`ID_CURSO`)
    REFERENCES `treinamento`.`tb_curso` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `treinamento`.`tb_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `treinamento`.`tb_usuario` ;

CREATE TABLE IF NOT EXISTS `treinamento`.`tb_usuario` (
  `ID` BIGINT NOT NULL AUTO_INCREMENT,
  `DS_EMAIL` VARCHAR(200) NOT NULL,
  `NM_NOME` VARCHAR(200) NOT NULL,
  `DS_SENHA` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
