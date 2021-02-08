package com.sujit.ordersystemspringweb.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

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

    @NotBlank(message = "item name cannot be null")
    private String itemName;

    @Column(name = "brand")

    @NotBlank(message = "brand name cannot be empty")
    @Size(min = 2, max = 30, message = "brand name should be between 2 to 30 characters")
    private String brand;

    @Column(name = "stored_At")
    private String store;

    @Column(name = "price")

    @Positive(message = "Price should be greater than 0 ")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "discount")
    @PositiveOrZero(message = "Discount cannot be negative")
    private Double discount;

}
