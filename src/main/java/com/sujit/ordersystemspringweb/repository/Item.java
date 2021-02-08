package com.sujit.ordersystemspringweb.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NamedQuery(name = "Item.findByBrand", query = "SELECT i FROM Item i WHERE LOWER(i.brand) = LOWER(?1)")
@Table(name = "ITEMS")
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "item_name")

    private String itemName;

    @Column(name = "brand")
    private String brand;

    @Column(name = "stored_At")
    private String store;

    @Column(name = "price")
    private String price;

    @Column(name = "description")
    private String description;

    @Column(name = "discount")
    private Double discount;


}
