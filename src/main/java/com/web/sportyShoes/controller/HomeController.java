package com.web.sportyShoes.controller;

import com.web.sportyShoes.model.Product;
import com.web.sportyShoes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public @ResponseBody String hello(@RequestParam(name="name", required = false, defaultValue = "Sporty Shoes") String name) {

        List<Product> products =  productService.getAllProducts();
        for (Product product : products) {
            System.out.println(product.getName());
        }

        return "Welcome to " + name;
    }
}
