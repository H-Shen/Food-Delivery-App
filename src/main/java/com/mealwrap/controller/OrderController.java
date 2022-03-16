package com.mealwrap.controller;

import com.mealwrap.common.Result;
import com.mealwrap.common.ResultEnum;
import com.mealwrap.entity.Order;
import com.mealwrap.entity.User;
import com.mealwrap.service.OrderService;
import com.mealwrap.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Api(tags = "Order Controller")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @ApiOperation("List all orders")
    @GetMapping("/all")
    public Result<List<Order>> list() {
        List<Order> orders = orderService.list();
        if (orders == null) {
            return Result.error(ResultEnum.BAD_REQUEST);
        }
        return Result.success(orders);
    }

    @ApiOperation("List all orders of a user")
    @GetMapping("/id")
    public Result<List<Order>> listById(
            @RequestParam("id") @NotNull Integer id) {
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
            List<Order> orders = orderService.listByMap(args);
            if (orders == null) {
                return Result.error(ResultEnum.BAD_REQUEST, "failed to obtain orders of the user");
            }
            return Result.success(orders);
        } catch (Exception e) {
            return Result.error(ResultEnum.BAD_REQUEST, e.getMessage());
        }
    }

    // post
    @ApiOperation("Insert an order")
    @PostMapping("/insert")
    public Result<Order> insert(
            @RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body is null");
        }

        if (!requestBody.containsKey("userId")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain user id");
        }
        if (requestBody.get("userId") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "user id is null");
        }
        if (!(requestBody.get("userId") instanceof Integer)) {
            return Result.error(ResultEnum.BAD_REQUEST, "user id type mismatched");
        }

        if (!requestBody.containsKey("merchantId")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain merchant id");
        }
        if (requestBody.get("merchantId") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "merchant id is null");
        }
        if (!(requestBody.get("merchantId") instanceof Integer)) {
            return Result.error(ResultEnum.BAD_REQUEST, "merchant id type mismatched");
        }

        if (!requestBody.containsKey("address")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain address");
        }
        if (requestBody.get("address") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "address is null");
        }
        if (!(requestBody.get("address") instanceof String)) {
            return Result.error(ResultEnum.BAD_REQUEST, "address type mismatched");
        }

        if (!requestBody.containsKey("phone")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain phone");
        }
        if (requestBody.get("phone") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "phone is null");
        }
        if (!(requestBody.get("phone") instanceof String)) {
            return Result.error(ResultEnum.BAD_REQUEST, "phone type mismatched");
        }

        if (!requestBody.containsKey("paymentMethod")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain payment method");
        }
        if (requestBody.get("paymentMethod") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "payment method is null");
        }
        if (!(requestBody.get("paymentMethod") instanceof String)) {
            return Result.error(ResultEnum.BAD_REQUEST, "payment method type mismatched");
        }
        return null;
    }

    // update (按postmapping写)
}
