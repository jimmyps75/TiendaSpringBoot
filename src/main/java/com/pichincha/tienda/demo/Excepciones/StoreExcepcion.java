package com.pichincha.tienda.demo.Excepciones;


public class StoreExcepcion extends Exception{
    private String message;

    public StoreExcepcion(String message, Throwable exception ) {
        super(message, exception);
        this.message = message;
    }
}
