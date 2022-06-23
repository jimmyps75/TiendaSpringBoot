package com.pichincha.tienda.demo.Repository;

import com.pichincha.tienda.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCode(String code);
}
