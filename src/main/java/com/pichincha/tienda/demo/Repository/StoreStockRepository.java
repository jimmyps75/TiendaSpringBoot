package com.pichincha.tienda.demo.Repository;


import com.pichincha.tienda.demo.entity.StoreStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreStockRepository extends JpaRepository<StoreStock, Long> {
    Optional<StoreStock> findById(Long id);
}
