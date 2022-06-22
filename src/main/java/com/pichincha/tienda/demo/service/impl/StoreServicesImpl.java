package com.pichincha.tienda.demo.service.impl;

import com.pichincha.tienda.demo.Excepciones.StoreExcepcion;
import com.pichincha.tienda.demo.Excepciones.StoreNotFoundException;
import com.pichincha.tienda.demo.Repository.StoreRepository;
import com.pichincha.tienda.demo.dto.ResponseDto;
import com.pichincha.tienda.demo.dto.StoreDto;
import com.pichincha.tienda.demo.entity.Store;
import com.pichincha.tienda.demo.entity.StoreStock;
import com.pichincha.tienda.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StoreServicesImpl implements StoreService {
    // @Autowired inyectar la dependencia del repositorio
    @Autowired
    private StoreRepository storeRepository;


    @Override
    public ResponseDto saveStore(StoreDto storeDto) throws StoreExcepcion {
        //persistir el dto
        Store storeBdd = new Store();
        storeBdd.setName(storeDto.getName());
        storeBdd.setCategory(storeDto.getCategory());
        storeBdd.setOwner(storeDto.getOwner());
        try {
            Store savedStore = storeRepository.save(storeBdd);
            return new ResponseDto("Sore saved: " + savedStore.getId());
        } catch (Exception e) {
            throw new StoreExcepcion("No se puede grabar en la BDD ", e);
        }
    }

    //realizar busqueda.
    @Override
    public StoreDto findStoreByName(String storeName) throws StoreNotFoundException {
        Store store = storeRepository.findByName(storeName);
        if (Objects.isNull(store)) {
            throw new StoreNotFoundException(storeName);
        }
        return new StoreDto(store.getId(), store.getName(), store.getCategory(), store.getOwner(), null);
    }

    @Override
    public ResponseDto deleteStore(Long storedId) throws StoreNotFoundException {
        Store storeFindInBdd = storeRepository.findById(storedId).orElseThrow(() ->
                new StoreNotFoundException("Store not found for this id :: " + storedId.toString()));
        List<StoreStock> listaProductos = storeFindInBdd.getProducts();
        if ( !listaProductos.isEmpty()   ) {
            return  new ResponseDto("no se puede eliminar: " + storedId);
        }
        storeRepository.delete(storeFindInBdd);
        return  new ResponseDto("Store eliminado: " + storedId);
    }


    //realizar busqueda por id
    /*@Override
    public Boolean findStoreStockById(Long id) {
        return Objects.isNull(storeStockRepository.findById(id)) ? true: false;
    }*/

    @Override
    public Boolean updateStore(StoreDto storeDto, Long storeId) throws StoreNotFoundException {
        Store storeFindInBdd = storeRepository.findById(storeId).orElseThrow(() -> new StoreNotFoundException("Employee not found for this id :: " + storeId.toString()));
        storeFindInBdd.setCategory(storeDto.getCategory());
        storeFindInBdd.setName(storeDto.getName());
        storeFindInBdd.setOwner(storeDto.getOwner());
        storeRepository.save(storeFindInBdd);
        return true;
    }
}

