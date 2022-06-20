package com.pichincha.tienda.demo.Repository;

import com.pichincha.tienda.demo.entity.StoreStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreStockRepository extends JpaRepository<StoreStock, Long> {

}
