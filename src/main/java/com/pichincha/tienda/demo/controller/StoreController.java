package com.pichincha.tienda.demo.controller;

import com.pichincha.tienda.demo.Excepciones.StoreExcepcion;
import com.pichincha.tienda.demo.Excepciones.StoreNotFoundException;
import com.pichincha.tienda.demo.dto.ResponseDto;
import com.pichincha.tienda.demo.dto.StoreDto;
import com.pichincha.tienda.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> searchStoreByName(@RequestHeader String storeName)
    {
        try {
            return new ResponseEntity<>(storeService.findStoreByName(storeName), HttpStatus.OK  );
        } catch (StoreNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(path = "/{storeId}")
    public  ResponseEntity<?> updateStoreId(@RequestBody StoreDto storeDto, @PathVariable Long storeId)
    {
        try {
            return new ResponseEntity<>(storeService.updateStore(storeDto, storeId), HttpStatus.OK  );
        } catch (StoreNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>( HttpStatus.NOT_MODIFIED);
        }
    }
}