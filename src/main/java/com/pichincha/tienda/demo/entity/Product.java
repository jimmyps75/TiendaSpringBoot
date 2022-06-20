package com.pichincha.tienda.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;
    private String name;
    private Double price;

    //Un producto esta en algunos stock
    @OneToMany(mappedBy = "productOwner", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<StoreStock> storeStock;


}
