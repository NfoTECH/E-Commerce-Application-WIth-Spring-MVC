package com.fortunate.week7tasknfotech.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    private Long cartId;

    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "product")
    private String product;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "date")
    private Timestamp date;

}
