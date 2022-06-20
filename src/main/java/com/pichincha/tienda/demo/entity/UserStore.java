package com.pichincha.tienda.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    private String userName;
    private Date createDate;
    //un usuario puede estar en muchas tiendas
    @OneToMany(mappedBy = "userOwner", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ProductOrder> requestOrders;
}