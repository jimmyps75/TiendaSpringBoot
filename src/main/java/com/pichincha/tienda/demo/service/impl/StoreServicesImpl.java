package com.pichincha.tienda.demo.service.impl;

import com.pichincha.tienda.demo.Excepciones.StoreExcepcion;
import com.pichincha.tienda.demo.Excepciones.StoreNotFoundException;
import com.pichincha.tienda.demo.Repository.StoreRepository;
import com.pichincha.tienda.demo.dto.ResponseDto;
import com.pichincha.tienda.demo.dto.StoreDto;
import com.pichincha.tienda.demo.entity.Store;
import com.pichincha.tienda.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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
        if(Objects.isNull(store)){
            throw new StoreNotFoundException(storeName);
        }
        return new StoreDto(store.getId(), store.getName(), store.getCategory(), store.getOwner(),null);
    }

    @Override
    public Boolean updateStore(StoreDto storeDto, Long storeId) throws StoreNotFoundException {
        boolean isUpdate;
        Optional<Store> storeFindInBdd = storeRepository.findById(storeId);
            if(storeFindInBdd.isPresent()){
                Store  storeBdd = new Store();
                storeBdd.setCategory(storeDto.getCategory());
                storeBdd.setName(storeDto.getName());
                storeBdd.setOwner(storeDto.getOwner());
                storeRepository.save(storeBdd);

                isUpdate = true;
            }else {
                throw new StoreNotFoundException(storeId.toString());
            }
        return isUpdate;
    }


    }

