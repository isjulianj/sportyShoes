package com.web.sportyShoes.controller;

import com.web.sportyShoes.model.Category;
import com.web.sportyShoes.model.Product;
import com.web.sportyShoes.service.ProductService;
import com.web.sportyShoes.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/edit-product";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product, @RequestParam("categories") Set<Long> categoryIds) {
        Set<Category> categories = categoryIds.stream()
                .map(categoryService::getCategoryById)
                .collect(Collectors.toSet());
        product.setCategories(categories);
        productService.updateProduct(product);
        return "redirect:/admin/products";
    }
}