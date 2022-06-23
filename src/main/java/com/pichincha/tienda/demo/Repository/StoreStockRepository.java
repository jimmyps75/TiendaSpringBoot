package com.pichincha.tienda.demo.Repository;


import com.pichincha.tienda.demo.entity.StoreStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StoreStockRepository extends JpaRepository<StoreStock, Long> {
    @Query(value = "select * from store_stock where product_id = :productId and store_id = :storeId", nativeQuery = true)
    StoreStock findStockByStoreAndProduct(@Param("storeId") Long storeId, @Param("productId")  Long productId);

}
