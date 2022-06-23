package com.pichincha.tienda.demo.service.impl;

import com.pichincha.tienda.demo.Excepciones.StoreNotFoundException;
import com.pichincha.tienda.demo.Repository.ProductRepository;
import com.pichincha.tienda.demo.Repository.StoreRepository;
import com.pichincha.tienda.demo.Repository.StoreStockRepository;
import com.pichincha.tienda.demo.entity.*;
import com.pichincha.tienda.demo.service.ProductService;
import com.pichincha.tienda.demo.util.Configs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreStockRepository storeStockRepository;
    @Autowired
    private Configs config;


    @Override
    @Transactional
    public void loadProductsFromWS(Long storeId) throws StoreNotFoundException {
        // busca si encuntra la tienda por el id
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreNotFoundException(storeId.toString()));

        //Aqui llamamos al servicio rest para obtener informacion
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseWs> response = restTemplate.getForEntity(config.getUrl(), ResponseWs.class);

        //Realiza la busqueda de los productos que sea mayor a 5  Filtro
        List<ProductWs> products = response.getBody().getProducts().stream().filter(product -> product.getStock() > 5)
                .collect(Collectors.toList());

        //Buscar los productos que estan en la base--- para posteriormente poder persisteir  SELECT 1
        List<ProductWs> productsToSaveInDb;
        productsToSaveInDb = products.stream()
                .filter(product -> productRepository.findByCode(product.getCod()) == null).collect(Collectors.toList());

        //graba si existe
        productsToSaveInDb.stream().forEach(product -> {
            productRepository
                    .save(new Product(null, product.getCod(), product.getName(), product.getPrice(), null));
        });

        //obtiene todos los productos en el StoreStock
        List<StoreStock> productsInDb = products
                .stream().map(product -> new StoreStock(null, product.getPrice(),
                        productRepository.findByCode(product.getCod()), store, product.getStock()))
                .collect(Collectors.toList());

        //obtiene los productos que si estan en la base de datos y los actualiza el stock de los productos.
        List<StoreStock> stockToUpdate = productsInDb.stream().map(stock -> {
            StoreStock productStok = storeStockRepository.findStockByStoreAndProduct(store.getId(),
                    stock.getProductOwner().getId());
            if (productStok != null) {
                productStok.setStock(stock.getStock()); // si no existe lo crea
            } else {
                productStok = stock; // caso contrario actualiza
            }
            return productStok;
        }).collect(Collectors.toList());

        storeStockRepository.saveAll(stockToUpdate);

    }
}