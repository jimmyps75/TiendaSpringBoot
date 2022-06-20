package com.pichincha.tienda.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private Long id;
    private String name;
    private String category;
    private String owner;  //usuario
    private List<StoreStockDto> products;
}
