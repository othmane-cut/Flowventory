/**package com.flowventorygrp.flowventory.pos.config;



import com.flowventorygrp.flowventory.pos.model.Product;
import com.flowventorygrp.flowventory.pos.model.ProductType;
import com.flowventorygrp.flowventory.pos.model.Role;
import com.flowventorygrp.flowventory.pos.model.User;
import com.flowventorygrp.flowventory.pos.repository.ProductRepository;
import com.flowventorygrp.flowventory.pos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        initAdminUser();
        initProducts();
    }

    private void initAdminUser() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(Role.ADMIN));

            userRepository.save(admin);
            System.out.println("✅ Admin user created: username='admin', password='admin123'");
        } else {
            System.out.println("ℹ️ Admin user already exists.");
        }
    }

    private void initProducts() {
        if (productRepository.count() == 0) {
            Random random = new Random();
            ProductType[] types = ProductType.values();

            for (int i = 1; i <= 50; i++) {
                Product product = new Product();
                product.setProductType(types[random.nextInt(types.length)]);
                product.setBrand("Brand" + (random.nextInt(5) + 1)); // 5 fake brands
                product.setModel("Model" + i);
                product.setPrice(200.0 + random.nextInt(1000)); // between 200 and 1200
                product.setYear(Year.of(2010 + random.nextInt(15))); // between 2010 and 2024

                productRepository.save(product);
            }

            System.out.println("✅ 50 demo products created for pagination testing!");
        } else {
            System.out.println("ℹ️ Products already exist in the database.");
        }
    }
}**/

