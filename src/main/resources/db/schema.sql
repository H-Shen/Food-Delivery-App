SET NAMES utf8mb4;
SET foreign_key_checks = 0;

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user
(
    `id`        INT AUTO_INCREMENT NOT NULL,
    `username`  VARCHAR(30)        NULL,
    `password`  VARCHAR(128)       NOT NULL COMMENT '加密密码',
    `phone`     VARCHAR(20)        NOT NULL,
    `address`   VARCHAR(256)       NULL,
    `create_at` DATETIME           NULL,
    `update_at` DATETIME           NULL,
    `role`      INT                NOT NULL DEFAULT 0, /** 0,1,2 **/
    PRIMARY KEY (`id`),
    UNIQUE KEY `phone` (`phone`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户';

DROP TABLE IF EXISTS t_product;
CREATE TABLE t_product
(
    `id`          INT AUTO_INCREMENT NOT NULL,
    `name`        VARCHAR(64)        NULL,
    `price`       DECIMAL            NULL,
    `percent_off` INT                NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='商品表';

DROP TABLE IF EXISTS t_merchant_product;
CREATE TABLE t_merchant_product
(
    `merchant_id` INT NOT NULL,
    `product_id`  INT NOT NULL,
    PRIMARY KEY (`merchant_id`, `product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='商家商品表';

DROP TABLE IF EXISTS t_shopping_cart;
CREATE TABLE t_shopping_cart
(
    `user_id`    INT NOT NULL,
    `product_id` INT NOT NULL,
    `quantity`   INT NULL,
    PRIMARY KEY (`user_id`, `product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='购物车表';

DROP TABLE IF EXISTS t_order;
CREATE TABLE t_order
(
    `order_id`        INT AUTO_INCREMENT NOT NULL,
    `buyer_id`        INT          NOT NULL,
    `merchant_id`     INT          NOT NULL,
    `address`         VARCHAR(256) NULL,
    `phone`           VARCHAR(20)  NULL,
    `payment_method`  INT          NULL, /** 0 1 2 **/
    `delivery_time`   DATETIME     NULL,
    `delivery_method` INT          NULL, /** 0 1 2 **/
    `total_price`     DECIMAL      NULL,
    `delivery_fee`    DECIMAL      NULL,
    `tax`             DECIMAL      NULL,
    `tip`             DECIMAL      NULL,
    `comment`         VARCHAR(512) NULL,
    `create_at`       DATETIME     NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='订单表';