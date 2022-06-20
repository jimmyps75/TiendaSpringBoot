package com.pichincha.tienda.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StoreStockDto {
    private Long id;
    private Double soldPrice;
    private ProductDto productDtoOwner;
    private Integer stock;
}
