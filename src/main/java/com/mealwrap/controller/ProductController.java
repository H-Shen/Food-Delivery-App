package com.mealwrap.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mealwrap.common.Result;
import com.mealwrap.common.ResultEnum;
import com.mealwrap.entity.Merchant;
import com.mealwrap.entity.Product;
import com.mealwrap.service.MerchantService;
import com.mealwrap.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@Api(tags = "Product Controller")
public class ProductController {
    @Resource
    private ProductService  productService;
    @Resource
    private MerchantService merchantService;

    @ApiOperation("List all products of a merchant without images by giving merchant id")
    @GetMapping("/merchant_id")
    public Result<List<Map<String, Object>>> listByMerchantId(
            @RequestParam Integer merchantId) {
        if (merchantId == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "merchant id is null");
        }
        Merchant merchant = merchantService.getById(merchantId);
        if (merchant == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "merchant id does not exist");
        }
        QueryWrapper<Product> queryWrapper = new QueryWrapper<Product>().select(Product.class,
                e -> !"image".equals(e.getColumn()))
                .eq("merchant_id", merchantId);
        List<Map<String, Object>> products = productService.listMaps(queryWrapper);
        if (products == null) {
            return Result.error(ResultEnum.BAD_REQUEST);
        }
        return Result.success(products);
    }

    @ApiOperation("List all products without images")
    @GetMapping("/all")
    public Result<List<Map<String, Object>>> list() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<Product>().select(Product.class,
                e -> !"image".equals(e.getColumn()));
        List<Map<String, Object>> products = productService.listMaps(queryWrapper);
        if (products == null) {
            return Result.error(ResultEnum.BAD_REQUEST);
        }
        return Result.success(products);
    }

    @ApiOperation("Get an image of a product")
    @GetMapping(value = "/image")
    public Result<Byte[]> getImage(
            @RequestParam("id") Integer id) {
        if (id == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "id is null");
        }
        Product product = productService.getById(id);
        if (product == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "product cannot be found");
        }
        // todo
        return null;
    }

    @ApiOperation("Upload an image of a product")
    @PostMapping(value = "/upload")
    public Result<Void> upload(
            @RequestParam("id") Integer id,
            @RequestPart("file") MultipartFile file) {
        if (id == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "id is null");
        }
        if (file == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "upload file is null");
        }
        Product product = productService.getById(id);
        if (product == null) {
            return Result.error(ResultEnum.BAD_REQUEST, "product cannot be found");
        }
        try {
            byte[] bytes = file.getBytes();
            product.setImage(bytes);
            if (!productService.updateById(product)) {
                return Result.error(ResultEnum.BAD_REQUEST, "failed to insert the image");
            }
            return Result.success("file uploaded successfully");
        } catch (Exception e) {
            return Result.error(ResultEnum.BAD_REQUEST, e.getMessage());
        }
    }
}
