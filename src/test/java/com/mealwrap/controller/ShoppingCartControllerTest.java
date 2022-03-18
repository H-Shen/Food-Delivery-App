package com.mealwrap.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealwrap.common.ResultEnum;
import com.mealwrap.entity.ShoppingCart;
import com.mealwrap.service.ShoppingCartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
@Rollback
class ShoppingCartControllerTest {

    private final String              baseUrl = "/shoppingcart";
    @Resource
    private       MockMvc             mockMvc;
    @Resource
    private       ShoppingCartService shoppingCartService;

    @Test
    void listById() throws Exception {
        String  url = baseUrl + "/id";
        Integer id  = 1;
        RequestBuilder request = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(id));
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.SUCCESS.getCode()))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void listByIdNotExist() throws Exception {
        String  url = baseUrl + "/id";
        Integer id  = -1;
        RequestBuilder request = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(id));
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.msg").value("user id does not exist"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void listByIdInvalidType() throws Exception {
        String url = baseUrl + "/id";
        RequestBuilder request = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "a");
        mockMvc.perform(request)
                .andExpect(status().is(400))
                .andReturn();
    }

    @Test
    void listByIdNotProvided() throws Exception {
        String url = baseUrl + "/id";
        RequestBuilder request = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().is(400))
                .andReturn();
    }

    @Test
    void removeAnItemNotProvided() throws Exception {
        String url = baseUrl + "/remove";
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().is(400));
    }

    @Test
    void removeAnItemNoUserId() throws Exception {
        String              url         = baseUrl + "/remove";
        Map<String, Object> requestBody = new HashMap<>();
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.msg").value("request body does not contain 'userId'"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void removeAnItemNullUserId() throws Exception {
        String              url         = baseUrl + "/remove";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", null);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.msg").value("'userId' is null"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void removeAnItemInvalidTypeUserId() throws Exception {
        String              url         = baseUrl + "/remove";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", "1");
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.msg").value("'userId' type mismatched"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void removeAnItemUserIdNotExist() throws Exception {
        String              url         = baseUrl + "/remove";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", -1);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.msg").value("user does not exist"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void removeAnItemNoProductId() throws Exception {
        String              url         = baseUrl + "/remove";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.msg").value("request body does not contain 'productId'"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void removeAnItemNullProductId() throws Exception {
        String              url         = baseUrl + "/remove";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        requestBody.put("productId", null);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.msg").value("'productId' is null"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void removeAnItemInvalidTypeProductId() throws Exception {
        String              url         = baseUrl + "/remove";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        requestBody.put("productId", "1");
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.msg").value("'productId' type mismatched"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void removeAnItemProductIdNotExist() throws Exception {
        String              url         = baseUrl + "/remove";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        requestBody.put("productId", -1);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.msg").value("product does not exist"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void removeAnItem() throws Exception {
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<ShoppingCart>().eq("user_id", 1).eq(
                "product_id", 1);
        Assertions.assertNotNull(shoppingCartService.getOne(queryWrapper));
        String              url         = baseUrl + "/remove";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        requestBody.put("productId", 1);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.SUCCESS.getCode()))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
        Assertions.assertNull(shoppingCartService.getOne(queryWrapper));
    }

    @Test
    void removeAnItem2() throws Exception {
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<ShoppingCart>().eq("user_id", 1).eq(
                "product_id", 3);
        Assertions.assertNull(shoppingCartService.getOne(queryWrapper));
        String              url         = baseUrl + "/remove";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", 1);
        requestBody.put("productId", 3);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.SUCCESS.getCode()))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
        Assertions.assertNull(shoppingCartService.getOne(queryWrapper));
    }

    @Test
    void updateQuantityNotProvided() throws Exception {
        String url = baseUrl + "/update";
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().is(400));
    }

    @Test
    void updateQuantityNoUserId() throws Exception {
        String              url         = baseUrl + "/update";
        Map<String, Object> requestBody = new HashMap<>();
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.msg").value("request body does not contain 'userId'"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void updateQuantityNullUserId() throws Exception {
        String              url         = baseUrl + "/update";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", null);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.msg").value("'userId' is null"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void updateQuantityInvalidTypeUserId() throws Exception {
        // todo
    }

    @Test
    void insertAnItem() throws Exception {
        String              url         = baseUrl + "/insert";
        Integer             userId      = 1;
        Integer             productId   = 3;
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", userId);
        requestBody.put("productId", productId);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.SUCCESS.getCode()))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void insertAnDuplicatedItem() throws Exception {
        String              url         = baseUrl + "/insert";
        Integer             userId      = 1;
        Integer             productId   = 1;
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", userId);
        requestBody.put("productId", productId);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultEnum.BAD_REQUEST.getCode()))
                .andExpect(jsonPath("$.msg", containsString("Duplicate entry")))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }


}