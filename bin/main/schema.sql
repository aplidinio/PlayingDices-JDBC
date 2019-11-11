-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema playingdices
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema playingdices
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `playingdices` DEFAULT CHARACTER SET utf8 ;
USE `playingdices` ;

-- -----------------------------------------------------
-- Table `playingdices`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `playingdices`.`player` (
  `id_player` INT(11) NOT NULL AUTO_INCREMENT,
  `name_player` VARCHAR(45) NULL DEFAULT NULL,
  `date_register` DATE NULL DEFAULT NULL,
  `rate_success` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id_player`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `playingdices`.`games`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `playingdices`.`games` (
  `id_games` INT(11) NOT NULL,
  `result` BIT(1) NULL DEFAULT NULL,
  `player_id_player` INT(11) NOT NULL,
  PRIMARY KEY (`id_games`),
  INDEX `fk_games_player_idx` (`player_id_player` ASC),
  CONSTRAINT `fk_games_player`
    FOREIGN KEY (`player_id_player`)
    REFERENCES `playingdices`.`player` (`id_player`)
    ON DELETE CASCADE
	ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `playingdices`.`games_dices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `playingdices`.`games_dices` (
  `dices` INT(11) NULL DEFAULT NULL,
  `games_id_games` INT(11) NOT NULL,
  INDEX `fk_games_dices_games1_idx` (`games_id_games` ASC),
  CONSTRAINT `fk_games_dices_games1`
    FOREIGN KEY (`games_id_games`)
    REFERENCES `playingdices`.`games` (`id_games`)
    ON DELETE CASCADE
	ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
