package com.qingkong.product.controller;

import com.qingkong.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    private Optional<ProductService> productServiceOptional;

    @GetMapping("hello")
    public Object hello(){
        return productServiceOptional.get().hello();
    }


}
