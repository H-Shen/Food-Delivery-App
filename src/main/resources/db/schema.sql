SET NAMES utf8mb4;
SET foreign_key_checks = 0;

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user
(
    `id`        INT AUTO_INCREMENT NOT NULL,
    `phone`     VARCHAR(20)  NOT NULL,
    `username`  VARCHAR(30) NULL,
    `password`  VARCHAR(128) NOT NULL COMMENT 'encrypted password',
    `address`   VARCHAR(256) NULL,
    `role`      INT          NOT NULL DEFAULT 0, /** 0,1,2 **/
    `create_at` DATETIME NULL,
    `update_at` DATETIME NULL,
    `rank`      DECIMAL               DEFAULT 0.0,
    PRIMARY KEY (`id`),
    UNIQUE KEY `phone` (`phone`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='User';

DROP TABLE IF EXISTS t_product;
CREATE TABLE t_product
(
    `id`          INT AUTO_INCREMENT NOT NULL,
    `merchant_id` INT                NOT NULL,
    `name`        VARCHAR(64)        NULL,
    `price`       DECIMAL            NULL,
    `percent_off` INT                NULL,
    `image`       VARCHAR(64)        NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`merchant_id`, `name`) /** each product of one merchant has a unique name **/
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='Product';

DROP TABLE IF EXISTS t_merchant_tag;
CREATE TABLE t_merchant_tag
(
    `merchant_id` INT         NOT NULL,
    `tag`         VARCHAR(32) NOT NULL,
    PRIMARY KEY (`merchant_id`, `tag`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='Merchant To Tag';

DROP TABLE IF EXISTS t_shopping_cart;
CREATE TABLE t_shopping_cart
(
    `user_id`    INT NOT NULL,
    `product_id` INT NOT NULL,
    `quantity`   INT NULL,
    PRIMARY KEY (`user_id`, `product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='Shopping Cart';

DROP TABLE IF EXISTS t_order;
CREATE TABLE t_order
(
    `order_id`        INT AUTO_INCREMENT NOT NULL,
    `buyer_id`        INT NOT NULL,
    `merchant_id`     INT NOT NULL,
    `address`         VARCHAR(256) NULL,
    `phone`           VARCHAR(20) NULL,
    `payment_method`  INT NULL, /** 0 1 2 **/
    `delivery_time`   DATETIME           NULL,
    `delivery_method` INT NULL, /** 0 1 2 **/
    `total_price`     DECIMAL NULL,
    `delivery_fee`    DECIMAL NULL,
    `tax`             DECIMAL NULL,
    `tip`             DECIMAL NULL,
    `comment`         VARCHAR(512) NULL,
    `create_at`       DATETIME NULL,
    PRIMARY KEY (`order_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='Order';