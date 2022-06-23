package com.pichincha.tienda.demo.service;

import com.pichincha.tienda.demo.Excepciones.StoreNotFoundException;

public interface ProductService {
    void loadProductsFromWS(Long storeId) throws StoreNotFoundException;
}
