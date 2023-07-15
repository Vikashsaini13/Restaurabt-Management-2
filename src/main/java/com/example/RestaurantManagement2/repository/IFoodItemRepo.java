package com.example.RestaurantManagement2.repository;


import com.example.RestaurantManagement2.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodItemRepo extends JpaRepository<FoodItem,Long> {
}
