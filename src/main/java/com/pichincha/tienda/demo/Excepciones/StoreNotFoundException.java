package com.pichincha.tienda.demo.Excepciones;

public class StoreNotFoundException extends Exception {
    private String storeName;

    public StoreNotFoundException(String storeName) {
        super("Tienda '"+ storeName +"' no encontrada");
        this.storeName = storeName;
    }
}
