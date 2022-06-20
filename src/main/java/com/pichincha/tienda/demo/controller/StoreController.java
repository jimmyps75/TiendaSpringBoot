package com.pichincha.tienda.demo.controller;

import com.pichincha.tienda.demo.Excepciones.StoreExcepcion;
import com.pichincha.tienda.demo.dto.ResponseDto;
import com.pichincha.tienda.demo.dto.StoreDto;
import com.pichincha.tienda.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("")
    public ResponseEntity<ResponseDto> createStore(@RequestBody StoreDto store){
        try {
            return new ResponseEntity<>(storeService.saveStore(store), HttpStatus.OK  );
        } catch (StoreExcepcion e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST );
        }

    }
}