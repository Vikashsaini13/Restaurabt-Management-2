package com.example.RestaurantManagement2.repository;

import com.example.RestaurantManagement2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {

    User findFirstByUserEmail(String newEmail);
}
