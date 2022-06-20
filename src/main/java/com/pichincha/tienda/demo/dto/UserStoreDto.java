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

public class UserStoreDto {
    private Long id;
    private String userName;
    private Date createDate;
    private List<ProductOrderDto> requestOrders;
}