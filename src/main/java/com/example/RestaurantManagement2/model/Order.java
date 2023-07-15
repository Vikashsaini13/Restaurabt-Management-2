package com.example.RestaurantManagement2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_tbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private int orderQuantity;

    @Column(nullable = false)
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "fk_food_item_id", nullable = false)
    private FoodItem foodItem;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

}
