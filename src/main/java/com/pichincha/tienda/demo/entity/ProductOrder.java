package com.pichincha.tienda.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;
    private Date dateOrder;
    //una orden puede tener muchos detalles
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OrderDetail> details;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserStore userOwner;
}
