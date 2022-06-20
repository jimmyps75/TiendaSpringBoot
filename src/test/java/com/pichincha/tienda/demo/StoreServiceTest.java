package com.pichincha.tienda.demo;

import com.pichincha.tienda.demo.Excepciones.StoreExcepcion;
import com.pichincha.tienda.demo.Repository.StoreRepository;
import com.pichincha.tienda.demo.dto.ResponseDto;
import com.pichincha.tienda.demo.dto.StoreDto;
import com.pichincha.tienda.demo.entity.Store;
import com.pichincha.tienda.demo.service.StoreService;
import com.pichincha.tienda.demo.service.impl.StoreServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class StoreServiceTest {


    @MockBean   // para este es una prueba de integracion es con base de datos.
    private StoreRepository storeRepository;

    @InjectMocks   //necesito el services inyecta con el mock
    private StoreServicesImpl storeService;   //debe ser mockeado

    private StoreDto storeDto;

    @BeforeEach
    private void initializate() {
        storeDto = new StoreDto();
        storeDto.setCategory("Legumbres");
        storeDto.setName("Tienda de prueba X");
        storeDto.setOwner("juan");
    }

    @Test
    public void givenStoreWithoutStock_whenSaveAStoreRequest_thenSaveStoreInDb() throws StoreExcepcion {
        Mockito.when(storeRepository.save(Mockito.any(Store.class)))
                .thenReturn(new Store(1L, "mock", null, null, null));
        ResponseDto responseDto = storeService.saveStore(storeDto);
        Assert.hasText(responseDto.getMessage(),"No se guardo en la BDD ");

    }

    //si hay problemas en grabar en la base de datos captura la excepcion
    @Test
    public void givenStoreWithoutStock_whenSaveAStoreRequestAndBddProblems_thenThrowsSaveStoreInDb() throws StoreExcepcion {
        //ese no cumple
    //    Mockito.when(storeRepository.save(Mockito.any(Store.class)))
    //            .thenReturn(new Store(1L, "mock", null, null, null));

        Mockito.when(storeRepository.save(Mockito.any(Store.class)))
                .thenThrow(new RuntimeException("Something wrong with Database"));
        StoreExcepcion exception = null;
        try {
            storeService.saveStore(storeDto);
        } catch (StoreExcepcion e){
            exception = e;
        }
        Assert.notNull(exception, "No se produjo la excepcion esperada StoreException");
    }


}
