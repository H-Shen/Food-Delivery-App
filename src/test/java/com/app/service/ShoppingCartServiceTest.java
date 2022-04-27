package com.app.service;

import com.app.entity.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
@Rollback
class ShoppingCartServiceTest {

    @Resource
    private ShoppingCartService shoppingCartService;

    @Test
    void removeByMerchantId() {
        ShoppingCart shoppingCart1 = new ShoppingCart(1, 1, 2);
        ShoppingCart shoppingCart2 = new ShoppingCart(1, 2, 3);
        try {
            shoppingCartService.save(shoppingCart1);
        } catch (Exception ignored) {
        }
        try {
            shoppingCartService.save(shoppingCart2);
        } catch (Exception ignored) {
        }
        Assertions.assertEquals(shoppingCartService.removeByMerchantId(1, 4), 2);
    }
}