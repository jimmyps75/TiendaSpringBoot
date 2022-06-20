package com.pichincha.tienda.demo.service;

import com.pichincha.tienda.demo.Excepciones.StoreExcepcion;
import com.pichincha.tienda.demo.dto.ResponseDto;
import com.pichincha.tienda.demo.dto.StoreDto;

public interface StoreService  {
    ResponseDto saveStore(StoreDto storeDto) throws StoreExcepcion;
}
