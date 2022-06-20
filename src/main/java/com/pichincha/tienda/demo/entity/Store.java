package com.pichincha.tienda.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String owner;  //usuario

    // un store tiene algunos productos
    @OneToMany(mappedBy = "storeOwner", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<StoreStock> products;
}
