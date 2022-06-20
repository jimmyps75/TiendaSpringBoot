package com.pichincha.tienda.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDto {
    private Long id;
    private Date dateOrder;
    private List<OrderDetailDto> details;
    private UserStoreDto userOwner;
}
