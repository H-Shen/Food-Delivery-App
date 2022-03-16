package com.mealwrap.controller;

import com.mealwrap.common.Result;
import com.mealwrap.common.ResultEnum;
import com.mealwrap.entity.ShoppingCart;
import com.mealwrap.entity.User;
import com.mealwrap.service.ShoppingCartService;
import com.mealwrap.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shoppingcart")
@Api(tags = "Shopping Cart Controller")
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    @Resource
    private UserService userService;

    @ApiOperation("List all products in the shopping cart of a user")
    @GetMapping("/id")
    public Result<List<ShoppingCart>> listById(
            @RequestParam("id") Integer id) {
        if (id == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "id is null");
        }
        User user = userService.getById(id);
        if (user == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "user id does not exist");
        }
        Map<String, Object> args = new HashMap<>();
        args.put("user_id", id);
        try {
            List<ShoppingCart> items = shoppingCartService.listByMap(args);
            if (items == null) {
                return Result.error(ResultEnum.BAD_REQUEST, "failed to obtain items in the shopping cart");
            }
            return Result.success(items);
        } catch (Exception e) {
            return Result.error(ResultEnum.BAD_REQUEST, e.getMessage());
        }
    }

    // post

    // update (按postmapping写)

    // delete (按postmapping写)
}
