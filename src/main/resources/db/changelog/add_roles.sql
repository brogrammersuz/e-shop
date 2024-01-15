-- liquibase formatted sql

-- changeset Khasanboy-Akbarov:populate-role-table

INSERT INTO `eshop`.`role` (`id`, `name`) VALUES('1', 'ROLE_USER');
INSERT INTO `eshop`.`role` (`id`, `name`) VALUES('2', 'ROLE_ADMIN');