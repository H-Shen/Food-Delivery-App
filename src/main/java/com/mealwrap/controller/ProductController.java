package com.mealwrap.controller;

import com.mealwrap.common.Result;
import com.mealwrap.common.ResultEnum;
import com.mealwrap.entity.Product;
import com.mealwrap.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product")
@Api(tags = "Product Controller")
public class ProductController {
    @Resource
    private ProductService productService;

    @ApiOperation("List all products")
    @GetMapping("/all")
    public Result<List<Product>> list() {
        List<Product> products = productService.list();
        if (products == null) {
            return Result.error(ResultEnum.BAD_REQUEST);
        }
        return Result.success(products);
    }

    @ApiOperation("Return an image")
    @GetMapping(value = "/getimage")
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

    @ApiOperation("Upload an image")
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
