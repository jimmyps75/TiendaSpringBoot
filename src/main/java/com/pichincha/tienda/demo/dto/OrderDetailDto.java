package com.pichincha.tienda.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {

    private  Long id;


    private ProductDto productDto;

    private ProductOrderDto order;

    private Integer totalOrdered;

    private Double priceUnit;

    private Double priceTotal;

}
