-- liquibase formatted sql

-- changeset Khasanboy-Akbarov:remover-productId-from-cart_item-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.COLUMNS WHERE TABLE_SCHEMA = 'eshop' AND TABLE_NAME='cart_item' AND COLUMN_NAME ='product_id';
ALTER TABLE `eshop`.`cart_item` DROP COLUMN `product_id`;


-- changeset Khasanboy-Akbarov:create-cart_item-product-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES WHERE TABLE_SCHEMA = 'eshop' AND TABLE_NAME='cart_item_product';
CREATE TABLE `eshop`.`cart_item_product` (
  `item_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`item_id`, `product_id`),
  INDEX `product_id_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `eshop`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `item_id`
    FOREIGN KEY (`item_id`)
    REFERENCES `eshop`.`cart_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- changeset Khasanboy-Akbarov:remove-productId-from-order_item-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.COLUMNS WHERE TABLE_SCHEMA = 'eshop' AND TABLE_NAME='order_item' AND COLUMN_NAME ='product_id';
ALTER TABLE `eshop`.`order_item` DROP COLUMN `product_id`;

-- changeset Khasanboy-Akbarov:create-order-item-product-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES WHERE TABLE_SCHEMA = 'eshop' AND TABLE_NAME='order_item_product';
CREATE TABLE `eshop`.`order_item_product` (
  `item_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`item_id`, `product_id`),
  INDEX `product_id_idx` (`product_id` ASC) VISIBLE,
    FOREIGN KEY (`product_id`)
    REFERENCES `eshop`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`item_id`)
    REFERENCES `eshop`.`order_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- changeset Khasanboy-Akbarov:remove-userId-from-order-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:1 select count(*) from information_schema.COLUMNS WHERE TABLE_SCHEMA = 'eshop' AND TABLE_NAME='orders' AND COLUMN_NAME ='user_id';
ALTER TABLE `eshop`.`orders` DROP COLUMN `user_id`;

-- changeset Khasanboy-Akbarov:create-order_user-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES WHERE TABLE_SCHEMA = 'eshop' AND TABLE_NAME='order_user';
CREATE TABLE `eshop`.`order_user` (
  `order_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `user_id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
    FOREIGN KEY (`user_id`)
    REFERENCES `eshop`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`order_id`)
    REFERENCES `eshop`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);