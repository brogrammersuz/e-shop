-- liquibase formatted sql

-- changeset Khasanboy-Akbarov:create-category-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='eshop.category';
CREATE TABLE `eshop`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

-- changeset Khasanboy-Akbarov:create-product-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='eshop.product';
CREATE TABLE `eshop`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL NOT NULL,
  `category_id` INT NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

-- changeset Khasanboy-Akbarov:create-order-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='eshop.order';
CREATE TABLE `eshop`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  `shipping_id` INT NULL,
  PRIMARY KEY (`id`));

-- changeset Khasanboy-Akbarov:create-order-item-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='eshop.order_item';
CREATE TABLE `eshop`.`order_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL,
  `price` DECIMAL NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`));

-- changeset Khasanboy-Akbarov:create-order-items-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='eshop.order_items';
 CREATE TABLE `eshop`.`order_items` (
      `item_id` INT NOT NULL,
      `order_id` INT NOT NULL,
      PRIMARY KEY (`item_id`, `order_id`),
      INDEX `order_id_idx` (`order_id` ASC) VISIBLE,
      CONSTRAINT `item_id`
        FOREIGN KEY (`item_id`)
        REFERENCES `eshop`.`order_item` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
      CONSTRAINT `order_id`
        FOREIGN KEY (`order_id`)
        REFERENCES `eshop`.`order` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);

-- changeset Khasanboy-Akbarov:create-shopping-cart-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='eshop.shopping_cart';
CREATE TABLE `eshop`.`shopping_cart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`));

-- changeset Khasanboy-Akbarov:create-shopping-cart-item-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='eshop.shopping_cart_item';
CREATE TABLE `eshop`.`shopping_cart_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`));

-- changeset Khasanboy-Akbarov:create-cart-items-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='eshop.cart_items';
 CREATE TABLE `eshop`.`cart_items` (
      `cart_item_id` INT NOT NULL,
      `cart_id` INT NOT NULL,
      PRIMARY KEY (`cart_item_id`, `cart_id`),
      INDEX `cart_id_idx` (`cart_id` ASC) VISIBLE,
      CONSTRAINT `cart_item_id`
        FOREIGN KEY (`cart_item_id`)
        REFERENCES `eshop`.`shopping_cart_item` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
      CONSTRAINT `cart_id`
        FOREIGN KEY (`cart_id`)
        REFERENCES `eshop`.`shopping_cart` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);

-- changeset Khasanboy-Akbarov:create-shipping-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='eshop.shipping';
CREATE TABLE `eshop`.`shipping` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `address` VARCHAR(255) NULL,
  `city` VARCHAR(255) NULL,
  `state` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

-- changeset Khasanboy-Akbarov:create-role-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='eshop.role';
CREATE TABLE `eshop`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

-- changeset Khasanboy-Akbarov:create-user-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='eshop.user';
CREATE TABLE `eshop`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created` DATETIME NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) UNIQUE NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

-- changeset Khasanboy-Akbarov:create-user_roles-table
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.TABLES where TABLE_NAME='eshop.user_roles';
 CREATE TABLE `eshop`.`user_roles` (
      `user_id` INT NOT NULL,
      `role_id` INT NOT NULL,
      PRIMARY KEY (`user_id`, `role_id`),
      INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
      CONSTRAINT `user_id`
        FOREIGN KEY (`user_id`)
        REFERENCES `eshop`.`user` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
      CONSTRAINT `role_id`
        FOREIGN KEY (`role_id`)
        REFERENCES `eshop`.`role` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);