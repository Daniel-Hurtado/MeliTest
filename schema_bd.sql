CREATE SCHEMA `melidatabase` ;

CREATE TABLE `melidatabase`.`blacklist` (
  `blacklist_id` INT NOT NULL AUTO_INCREMENT,
  `ip_address` VARCHAR(45) NOT NULL,
  `date_block` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`blacklist_id`),
  INDEX `IX_BLACKLIST_IP_ADDRESS` (`ip_address` ASC) VISIBLE,
  INDEX `IX_BLACKLIST_DATE_BLOCK` (`date_block` ASC) VISIBLE);
  
ALTER TABLE `melidatabase`.`blacklist` 
ADD UNIQUE INDEX `ip_address_UNIQUE` (`ip_address` ASC) VISIBLE;
;
  
INSERT INTO `melidatabase`.`blacklist` (`ip_address`) VALUES ('127.0.0.1');
INSERT INTO `melidatabase`.`blacklist` (`ip_address`) VALUES ('127.0.1.1');