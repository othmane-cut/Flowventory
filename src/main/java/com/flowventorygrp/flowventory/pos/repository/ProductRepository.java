package com.flowventorygrp.flowventory.pos.repository;

import com.flowventorygrp.flowventory.pos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
