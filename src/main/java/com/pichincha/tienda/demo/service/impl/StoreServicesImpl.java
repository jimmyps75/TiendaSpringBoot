package com.pichincha.tienda.demo.service.impl;

import com.pichincha.tienda.demo.Excepciones.StoreExcepcion;
import com.pichincha.tienda.demo.Repository.StoreRepository;
import com.pichincha.tienda.demo.dto.ResponseDto;
import com.pichincha.tienda.demo.dto.StoreDto;
import com.pichincha.tienda.demo.entity.Store;
import com.pichincha.tienda.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServicesImpl implements StoreService {
    // @Autowired inyectar la dependencia del repositorio
    @Autowired
    private StoreRepository storeRepository;

    @Override
    public ResponseDto saveStore(StoreDto storeDto) throws StoreExcepcion   {
        //persistir el dto
        Store storeBdd = new Store();
        storeBdd.setName(storeDto.getName());
        storeBdd.setCategory(storeDto.getCategory());
        storeBdd.setOwner(storeDto.getOwner());
        try{
            Store savedStore = storeRepository.save(storeBdd);
            return new ResponseDto("Sore saved: " + savedStore.getId());
        }
        catch (Exception e){
            throw new StoreExcepcion("No se puede grabar en la BDD " , e );
        }
    }
}
