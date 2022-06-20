package com.pichincha.tienda.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StoreStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_stock_id")
    private Long id;
    private Double soldPrice;

    // Muchos productos en sotock|
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product productOwner;

    //Muchos productos estan en una tienda
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store storeOwner;

    private Integer stock;
}
