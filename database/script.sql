-- MySQL Script generated by MySQL Workbench
-- Thu Sep 15 11:16:08 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `Username` VARCHAR(20) NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Senha` BINARY(16) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Classificacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Classificacao` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Classificacao` (
  `UUID` CHAR(36) NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UUID`),
  UNIQUE INDEX `UUID_UNIQUE` (`UUID` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Postagem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Postagem` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Postagem` (
  `UUID` CHAR(36) NOT NULL,
  `Usuario` VARCHAR(20) NOT NULL,
  `Classificacao` CHAR(36) NOT NULL,
  `Titulo` VARCHAR(45) NOT NULL,
  `Data` DATETIME NOT NULL,
  `Conteudo` VARCHAR(300) NOT NULL,
  `Midia` VARCHAR(45) NULL,
  PRIMARY KEY (`UUID`, `Usuario`, `Classificacao`),
  UNIQUE INDEX `UUID_UNIQUE` (`UUID` ASC) VISIBLE,
  INDEX `fk_Postagem_Usuario_idx` (`Usuario` ASC) VISIBLE,
  INDEX `fk_Postagem_Classificacao1_idx` (`Classificacao` ASC) VISIBLE,
  CONSTRAINT `fk_Postagem_Usuario`
    FOREIGN KEY (`Usuario`)
    REFERENCES `mydb`.`Usuario` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Postagem_Classificacao1`
    FOREIGN KEY (`Classificacao`)
    REFERENCES `mydb`.`Classificacao` (`UUID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Curtida`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Curtida` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Curtida` (
  `Postagem` CHAR(36) NOT NULL,
  `Usuario` VARCHAR(20) NOT NULL,
  INDEX `fk_Curtida_Postagem1_idx` (`Postagem` ASC) VISIBLE,
  INDEX `fk_Curtida_Usuario1_idx` (`Usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Curtida_Postagem1`
    FOREIGN KEY (`Postagem`)
    REFERENCES `mydb`.`Postagem` (`UUID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Curtida_Usuario1`
    FOREIGN KEY (`Usuario`)
    REFERENCES `mydb`.`Usuario` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Seguidores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Seguidores` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Seguidores` (
  `Usuario` VARCHAR(20) NOT NULL,
  `Seguidor` VARCHAR(20) NOT NULL,
  INDEX `fk_Seguidores_Usuario1_idx` (`Usuario` ASC) VISIBLE,
  INDEX `fk_Seguidores_Usuario2_idx` (`Seguidor` ASC) VISIBLE,
  CONSTRAINT `fk_Seguidores_Usuario1`
    FOREIGN KEY (`Usuario`)
    REFERENCES `mydb`.`Usuario` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Seguidores_Usuario2`
    FOREIGN KEY (`Seguidor`)
    REFERENCES `mydb`.`Usuario` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
