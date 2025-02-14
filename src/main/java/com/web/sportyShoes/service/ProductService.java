package com.web.sportyShoes.service;

import com.web.sportyShoes.model.Product;
import com.web.sportyShoes.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        existingProduct.setName(product.getName());
        existingProduct.setSKU(product.getSKU());
        existingProduct.setPrice_per_unit(product.getPrice_per_unit());
        existingProduct.setActive(product.isActive());
        existingProduct.setCategories(product.getCategories());

        return productRepository.save(existingProduct);
    }
}