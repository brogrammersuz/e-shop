-- liquibase formatted sql

-- changeset Khasanboy-Akbarov:populate-category-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.TABLES WHERE TABLE_SCHEMA = 'eshop' AND TABLE_NAME='category';

INSERT INTO `eshop`.`category` (`id`, `name`) VALUES ('1', 'Fruits');
INSERT INTO `eshop`.`category` (`id`, `name`) VALUES ('2', 'Foods');
INSERT INTO `eshop`.`category` (`id`, `name`) VALUES ('3', 'Clothes');
INSERT INTO `eshop`.`category` (`id`, `name`) VALUES ('4', 'Books');
INSERT INTO `eshop`.`category` (`id`, `name`) VALUES ('5', 'Vagetables');
