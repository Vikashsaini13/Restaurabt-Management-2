package com.example.RestaurantManagement2.model;


import com.example.RestaurantManagement2.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String userEmail;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String userPassword;
}
