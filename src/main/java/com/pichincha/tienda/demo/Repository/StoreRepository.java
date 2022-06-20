package com.pichincha.tienda.demo.Repository;

import com.pichincha.tienda.demo.dto.StoreDto;
import com.pichincha.tienda.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
//aquui implementa lo que viende de la libreria
}
