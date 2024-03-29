package com.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.app.common.Result;
import com.app.common.ResultEnum;
import com.app.entity.ShoppingCart;
import com.app.entity.User;
import com.app.service.MerchantService;
import com.app.service.ProductService;
import com.app.service.ShoppingCartService;
import com.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/v1/shoppingcart")
@Api(tags = "Shopping Cart Controller")
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    @Resource
    private UserService userService;

    @Resource
    private ProductService productService;

    @Resource
    private MerchantService merchantService;

    @ApiOperation("Remove all items of a user by giving user ID and merchant ID")
    @PostMapping("/removebymerchantid")
    public Result<Void> removeByMerchantId(
            @RequestBody @NotNull Map<String, Object> requestBody
    ) {
        if (requestBody == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body is null");
        }
        // User ID must be given
        if (!requestBody.containsKey("userId")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain 'userId'");
        }
        if (requestBody.get("userId") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "'userId' is null");
        }
        if (!(requestBody.get("userId") instanceof Integer)) {
            return Result.error(ResultEnum.BAD_REQUEST, "'userId' type mismatched");
        }
        Integer userId = (Integer) requestBody.get("userId");
        if (userService.getById(userId) == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "user does not exist");
        }
        // Merchant ID must be given
        if (!requestBody.containsKey("merchantId")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain 'merchantId'");
        }
        if (requestBody.get("merchantId") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "'merchantId' is null");
        }
        if (!(requestBody.get("merchantId") instanceof Integer)) {
            return Result.error(ResultEnum.BAD_REQUEST, "'merchantId' type mismatched");
        }
        Integer merchantId = (Integer) requestBody.get("merchantId");
        if (merchantService.getById(merchantId) == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "merchant does not exist");
        }
        try {
            shoppingCartService.removeByMerchantId(userId, merchantId);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.BAD_REQUEST, e.getMessage());
        }
    }

    @ApiOperation("List all products in the shopping cart of a user by giving user ID")
    @GetMapping("/id")
    public Result<List<Map<String, Object>>> listById(
            @RequestParam("id") @NotNull Integer id) {
        if (id == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "id is null");
        }
        User user = userService.getById(id);
        if (user == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "user id does not exist");
        }
        List<Map<String, Object>> products = shoppingCartService.listById(id);
        if (products == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "failed to obtain products with the given user id");
        }
        return Result.success(products);
    }

    @ApiOperation("Remove an item in the shopping cart of a user")
    @PostMapping("/remove")
    public Result<Void> removeAnItem(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body is null");
        }
        // User ID must be given
        if (!requestBody.containsKey("userId")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain 'userId'");
        }
        if (requestBody.get("userId") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "'userId' is null");
        }
        if (!(requestBody.get("userId") instanceof Integer)) {
            return Result.error(ResultEnum.BAD_REQUEST, "'userId' type mismatched");
        }
        Integer userId = (Integer) requestBody.get("userId");
        if (userService.getById(userId) == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "user does not exist");
        }
        // Product ID must be given
        if (!requestBody.containsKey("productId")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain 'productId'");
        }
        if (requestBody.get("productId") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "'productId' is null");
        }
        if (!(requestBody.get("productId") instanceof Integer)) {
            return Result.error(ResultEnum.BAD_REQUEST, "'productId' type mismatched");
        }
        Integer productId = (Integer) requestBody.get("productId");
        if (productService.getById(productId) == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "product does not exist");
        }
        try {
            shoppingCartService.remove(new QueryWrapper<ShoppingCart>().eq("user_id", userId).eq("product_id", productId));
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.BAD_REQUEST, e.getMessage());
        }
    }

    @ApiOperation("Adjust the quantity of an existed item in the shopping cart of a user")
    @PostMapping("/update")
    public Result<Void> updateQuantity(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body is null");
        }
        // User ID must be given
        if (!requestBody.containsKey("userId")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain 'userId'");
        }
        if (requestBody.get("userId") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "'userId' is null");
        }
        if (!(requestBody.get("userId") instanceof Integer)) {
            return Result.error(ResultEnum.BAD_REQUEST, "'userId' type mismatched");
        }
        Integer userId = (Integer) requestBody.get("userId");
        if (userService.getById(userId) == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "user does not exist");
        }
        // Product ID must be given
        if (!requestBody.containsKey("productId")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain 'productId'");
        }
        if (requestBody.get("productId") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "'productId' is null");
        }
        if (!(requestBody.get("productId") instanceof Integer)) {
            return Result.error(ResultEnum.BAD_REQUEST, "'productId' type mismatched");
        }
        Integer productId = (Integer) requestBody.get("productId");
        if (productService.getById(productId) == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "product does not exist");
        }
        // Quantity must be given
        if (!requestBody.containsKey("quantity")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain 'quantity'");
        }
        if (requestBody.get("quantity") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "'quantity' is null");
        }
        if (!(requestBody.get("quantity") instanceof Integer)) {
            return Result.error(ResultEnum.BAD_REQUEST, "'quantity' type mismatched");
        }
        Integer quantity = (Integer) requestBody.get("quantity");
        try {
            QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<ShoppingCart>()
                    .eq("user_id", userId)
                    .eq("product_id", productId);
            ShoppingCart shoppingCart = shoppingCartService.getOne(queryWrapper);
            if (shoppingCart == null) {
                return Result.error(ResultEnum.BAD_REQUEST, "the product is not added in the shopping cart");
            }
            shoppingCart.setQuantity(quantity);
            if (!shoppingCartService.update(shoppingCart, queryWrapper)) {
                return Result.error(ResultEnum.BAD_REQUEST, "failed to update the quantity of the product");
            }
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.BAD_REQUEST, e.getMessage());
        }
    }


    @ApiOperation("Insert an item to the shopping cart of a user")
    @PostMapping("/insert")
    public Result<ShoppingCart> insertAnItem(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body is null");
        }
        // User ID must be given
        if (!requestBody.containsKey("userId")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain 'userId'");
        }
        if (requestBody.get("userId") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "'userId' is null");
        }
        if (!(requestBody.get("userId") instanceof Integer)) {
            return Result.error(ResultEnum.BAD_REQUEST, "'userId' type mismatched");
        }
        Integer userId = (Integer) requestBody.get("userId");
        if (userService.getById(userId) == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "user does not exist");
        }
        // Product ID must be given
        if (!requestBody.containsKey("productId")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain 'productId'");
        }
        if (requestBody.get("productId") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "'productId' is null");
        }
        if (!(requestBody.get("productId") instanceof Integer)) {
            return Result.error(ResultEnum.BAD_REQUEST, "'productId' type mismatched");
        }
        Integer productId = (Integer) requestBody.get("productId");
        if (productService.getById(productId) == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "product does not exist");
        }
        try {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUserId(userId);
            shoppingCart.setProductId(productId);
            shoppingCart.setQuantity(1);
            if (!shoppingCartService.save(shoppingCart)) {
                return Result.error(ResultEnum.BAD_REQUEST, "failed to insert the item to the shopping cart");
            }
            return Result.success(shoppingCart);
        } catch (Exception e) {
            return Result.error(ResultEnum.BAD_REQUEST, e.getMessage());
        }
    }

    @ApiOperation("Remove all items in the shopping cart of a user")
    @PostMapping("/removeall")
    public Result<Void> removeAllItems(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body is null");
        }
        // User ID must be given
        if (!requestBody.containsKey("userId")) {
            return Result.error(ResultEnum.BAD_REQUEST, "request body does not contain 'userId'");
        }
        if (requestBody.get("userId") == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "'userId' is null");
        }
        if (!(requestBody.get("userId") instanceof Integer)) {
            return Result.error(ResultEnum.BAD_REQUEST, "'userId' type mismatched");
        }
        Integer userId = (Integer) requestBody.get("userId");
        if (userService.getById(userId) == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "user does not exist");
        }
        try {
            shoppingCartService.remove(new QueryWrapper<ShoppingCart>().eq("user_id", userId));
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.BAD_REQUEST, e.getMessage());
        }
    }
}
