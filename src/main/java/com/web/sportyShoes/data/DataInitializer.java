package com.web.sportyShoes.data;

import com.web.sportyShoes.model.*;
import com.web.sportyShoes.repository.CategoryRepository;
import com.web.sportyShoes.repository.OrderRepository;
import com.web.sportyShoes.repository.ProductRepository;
import com.web.sportyShoes.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;


@Configuration
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    public DataInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository, ProductRepository productRepository, UserRepository userRepository, OrderRepository orderRepository) {
        return args -> {
            // Create categories
            Category running = new Category();
            running.setName("Running");
            running.setSlug("running");
            running.setDescription("Shoes for running");

            Category basketball = new Category();
            basketball.setName("Basketball");
            basketball.setSlug("basketball");
            basketball.setDescription("Shoes for basketball");

            Category soccer = new Category();
            soccer.setName("Soccer");
            soccer.setSlug("soccer");
            soccer.setDescription("Shoes for soccer");

            Category tennis = new Category();
            tennis.setName("Tennis");
            tennis.setSlug("tennis");
            tennis.setDescription("Shoes for tennis");

            categoryRepository.save(running);
            categoryRepository.save(basketball);
            categoryRepository.save(soccer);
            categoryRepository.save(tennis);

            // Create products
            Set<Category> runningCategories = new HashSet<>();
            runningCategories.add(running);

            Set<Category> basketballCategories = new HashSet<>();
            basketballCategories.add(basketball);

            Set<Category> soccerCategories = new HashSet<>();
            soccerCategories.add(soccer);

            Set<Category> tennisCategories = new HashSet<>();
            tennisCategories.add(tennis);

            productRepository.save(createProduct("Nike Air Zoom Pegasus", "SKU001", 120.0f, true, runningCategories));
            productRepository.save(createProduct("Adidas Ultraboost", "SKU002", 180.0f, true, runningCategories));
            productRepository.save(createProduct("Asics Gel-Kayano", "SKU003", 160.0f, true, runningCategories));
            productRepository.save(createProduct("Nike LeBron 18", "SKU004", 200.0f, true, basketballCategories));
            productRepository.save(createProduct("Adidas Harden Vol. 5", "SKU005", 140.0f, true, basketballCategories));
            productRepository.save(createProduct("Under Armour Curry 8", "SKU006", 160.0f, true, basketballCategories));
            productRepository.save(createProduct("Nike Mercurial Superfly", "SKU007", 250.0f, true, soccerCategories));
            productRepository.save(createProduct("Adidas Predator", "SKU008", 230.0f, true, soccerCategories));
            productRepository.save(createProduct("Puma Future", "SKU009", 200.0f, true, soccerCategories));
            productRepository.save(createProduct("Nike Air Zoom Vapor", "SKU010", 150.0f, true, tennisCategories));
            productRepository.save(createProduct("Adidas Adizero Ubersonic", "SKU011", 140.0f, true, tennisCategories));
            productRepository.save(createProduct("Asics Gel-Resolution", "SKU012", 130.0f, true, tennisCategories));

            // Create a user
            User user = new User();
            user.setEmail("test@test.com");
            user.setSlug("test-user");
            user.setFirst_name("Test");
            user.setLast_name("User");
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());

            Password passwordEntity = new Password();
            passwordEntity.setHash(passwordEncoder.encode("javaproject"));
            user.setPassword(passwordEntity);
            passwordEntity.setUser(user);

            userRepository.save(user);

            // Create orders
            List<Product> products = productRepository.findAll();
            Set<Product> orderProducts = new HashSet<>(products.subList(0, 3));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            CustomerOrder order1 = new CustomerOrder();
            order1.setUser(user);
            order1.setProducts(orderProducts);
            order1.setTotalAmount(500.0f);
            order1.setCreatedAt(dateFormat.parse("2025-01-01"));
            order1.setShippedAt(new Date());
            orderRepository.save(order1);

            CustomerOrder order2 = new CustomerOrder();
            order2.setUser(user);
            order2.setProducts(orderProducts);
            order2.setTotalAmount(750.0f);
            order2.setCreatedAt(dateFormat.parse("2025-02-01"));
            order2.setShippedAt(new Date());
            orderRepository.save(order2);
        };
    }

    private Product createProduct(String name, String SKU, float price, boolean active, Set<Category> categories) {
        Product product = new Product();
        product.setName(name);
        product.setSKU(SKU);
        product.setPrice_per_unit(price);
        product.setActive(active);
        product.setCategories(categories);
        return product;
    }

    @Bean
    CommandLineRunner runTailwindCss() {
        return args -> {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "./tailwindcss-extra",
                    "-i", "./src/main/frontend/style.css",
                    "-o", "./src/main/resources/static/main.css"
            );
            processBuilder.inheritIO();
            try {
                Process process = processBuilder.start();
                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    throw new RuntimeException("Tailwind CSS command failed with exit code " + exitCode);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to run Tailwind CSS command", e);
            }
        };
    }

}