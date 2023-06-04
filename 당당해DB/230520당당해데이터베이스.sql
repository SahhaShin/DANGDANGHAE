-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dangdanghae
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dangdanghae
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dangdanghae` DEFAULT CHARACTER SET utf8 ;
USE `dangdanghae` ;

-- -----------------------------------------------------
-- Table `dangdanghae`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dangdanghae`.`users` (
  `id` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `name` VARCHAR(10) NOT NULL,
  `nick_name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `weight` FLOAT NULL,
  `muscle` FLOAT NULL,
  `blood_sugar` FLOAT NULL,
  `HbA1c` FLOAT NULL,
  `point` FLOAT NOT NULL DEFAULT 500,
  `rank` VARCHAR(50) NOT NULL DEFAULT '브론즈',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nick_name_UNIQUE` (`nick_name` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dangdanghae`.`challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dangdanghae`.`challenge` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `start_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  `point` FLOAT NOT NULL DEFAULT 500.0,
  `status` INT NOT NULL DEFAULT 0,
  `image` VARCHAR(500) NULL,
  `organizer` VARCHAR(50) NOT NULL,
  `organizer_team` INT NOT NULL,
  `modify` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`no`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_challenge`
    FOREIGN KEY (`organizer`)
    REFERENCES `dangdanghae`.`users` (`id`)
    ON DELETE cascade
    ON UPDATE cascade
    )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dangdanghae`.`users_has_challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dangdanghae`.`users_has_challenge` (
  `users_id` VARCHAR(50) NOT NULL,
  `challenge_no` INT NOT NULL,
  `team_no` INT NOT NULL,
  PRIMARY KEY (`users_id`, `challenge_no`),
  INDEX `fk_users_has_challenge_challenge1_idx` (`challenge_no` ASC) VISIBLE,
  INDEX `fk_users_has_challenge_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_challenge_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `dangdanghae`.`users` (`id`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_users_has_challenge_challenge1`
    FOREIGN KEY (`challenge_no`)
    REFERENCES `dangdanghae`.`challenge` (`no`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dangdanghae`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dangdanghae`.`team` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `team_name` VARCHAR(50) NOT NULL,
  `team_count` INT NOT NULL DEFAULT 0,
  `challenge_no` INT NOT NULL,
  PRIMARY KEY (`no`, `challenge_no`),
  INDEX `fk_team_challenge1_idx` (`challenge_no` ASC) VISIBLE,
  CONSTRAINT `fk_team_challenge1`
    FOREIGN KEY (`challenge_no`)
    REFERENCES `dangdanghae`.`challenge` (`no`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dangdanghae`.`one_day_exercise`
-- -----------------------------------------------------
-- 총 운동이 30분이 되어야 1회 인정  
CREATE TABLE IF NOT EXISTS `dangdanghae`.`one_day_exercise` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `total_time` VARCHAR(50) NOT NULL,
  `end_time` DATETIME NULL,
  `users_id` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`no`),
  INDEX `fk_one_day_exercise_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_one_day_exercise_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `dangdanghae`.`users` (`id`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dangdanghae`.`one_day_blood_sugar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dangdanghae`.`one_day_blood_sugar` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `cur_date` DATETIME NOT NULL,
  `blood_sugar` FLOAT NOT NULL,
  `users_id` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`no`),
  INDEX `fk_one_day_HbA1c_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_one_day_HbA1c_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `dangdanghae`.`users` (`id`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dangdanghae`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dangdanghae`.`board` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `write_date` DATETIME NOT NULL,
  `view_count` INT NOT NULL DEFAULT 0,
  `like` INT NOT NULL DEFAULT 0,
  `modify` TINYINT NOT NULL DEFAULT 0,
  `users_id` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`no`),
  INDEX `fk_board_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_board_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `dangdanghae`.`users` (`id`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dangdanghae`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dangdanghae`.`comment` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `comment_content` VARCHAR(500) NOT NULL,
  `write_date` DATETIME NOT NULL,
  `modify` TINYINT NOT NULL DEFAULT 0,
  `board_no` INT NOT NULL,
  `users_id` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`no`),
  INDEX `fk_comment_board1_idx` (`board_no` ASC) VISIBLE,
  INDEX `fk_comment_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_board1`
    FOREIGN KEY (`board_no`)
    REFERENCES `dangdanghae`.`board` (`no`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_comment_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `dangdanghae`.`users` (`id`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dangdanghae`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dangdanghae`.`product` (
  `no` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `image` VARCHAR(500) NOT NULL,
  `point` long NOT NULL,
  `company` VARCHAR(50) NOT NULL,
  `count` INT NOT NULL,
  `sale` TINYINT NOT NULL DEFAULT 1,
  `type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dangdanghae`.`users_has_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dangdanghae`.`users_has_product` (
  `users_id` VARCHAR(50) NOT NULL,
  `product_no` INT NOT NULL,
  PRIMARY KEY (`users_id`, `product_no`),
  INDEX `fk_users_has_product_product1_idx` (`product_no` ASC) VISIBLE,
  INDEX `fk_users_has_product_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_product_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `dangdanghae`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_product_product1`
    FOREIGN KEY (`product_no`)
    REFERENCES `dangdanghae`.`product` (`no`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

select * from users;
select * from one_day_blood_sugar;
select * from one_day_exercise;
select * from board;
select * from comment;
select * from challenge;
-- delete from challenge where no=4;
select * from team;
select * from users_has_challenge;
