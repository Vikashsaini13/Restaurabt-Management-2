package com.example.RestaurantManagement2.service;

import com.example.RestaurantManagement2.repository.IFoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {

    @Autowired
    IFoodItemRepo foodItemRepo;
}
