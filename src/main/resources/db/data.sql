DELETE
FROM t_user;
INSERT INTO t_user (phone, username, password, address, create_at, update_at)
VALUES ('6135550143', 'Jone', '$2a$10$lwhSaNuNaLJxn48HKK1KDeHzREy6jv3qikRIrIBOozoyJJqIQvVt2', 'address1', NOW(),
        NOW()), /* password */
       ('6135550195', 'Jack', '$2a$10$TQXPTpDTsWcEHXPBWtj0L.RjEkbqtWfvG6gRpbMJ1XY8kSqi5htPu', 'address2', NOW(), NOW()),
       ('6135550119', 'Tom', '$2a$10$saXxj3xK.QHygAQrPX1uKeZalzf4f3j1ejmsoSnrJIFD9HGLsRH.S', 'address3', NOW(), NOW()),
       ('6135550101', 'Sandy', '$2a$10$saXxj3xK.QHygAQrPX1uKeZalzf4f3j1ejmsoSnrJIFD9HGLsRH.S', 'address3', NOW(),
        NOW()),
       ('6135550107', 'Billie', '$2a$10$evSlP2n2KXsCYXEFs6bDHuunG4UpDA2rNaPffakR4TPF6vnpIkoBK', 'address4', NOW(),
        NOW());

DELETE
FROM t_product;

DELETE
FROM t_shopping_cart;

DELETE
FROM t_merchant_product;

DELETE
FROM t_order;